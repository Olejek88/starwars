package de.olegrom.starwars.viewModel

import de.olegrom.starwars.data.remote.FakeRemoteDataSource
import de.olegrom.starwars.data.remote.service.ImplKtorService
import de.olegrom.starwars.data.repository.ImplRepository
import de.olegrom.starwars.domain.usecase.lists.GetFilmsUseCase
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.FilmsViewModel
import de.olegrom.starwars.presentation.home.ListScreenState
import de.olegrom.starwars.util.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class FilmsListSharedViewModelTest {
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Test
    fun fetchFilmsShouldReturnSuccess() = runTest {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val service = ImplKtorService(
            fakeRemoteDataSource.apiService.buildApiService("films.json"),
            "https://swapi.py4e.com/api"
        )
        val sharedViewModel = FilmsViewModel(GetFilmsUseCase(ImplRepository(service)))
        launch {
            sharedViewModel.onIntent(AllScreensSideEvent.GetFilms)
            val state : FlowCollector<ListScreenState> = FlowCollector {
                println(it)
                if (it is ListScreenState.Success) {
                    val films = (it).entities
                    assertTrue(films.isNotEmpty())
                    assertTrue(films[0].title == "A New Hope")
                    assertTrue(films[0].episodeId == 4)
                    assertTrue(films[0].director == "George Lucas")
                    assertTrue(films[0].producer == "Gary Kurtz, Rick McCallum")
                    cancel()
                }
                if (it is ListScreenState.Error) {
                    assertFails("API call error") {}
                    cancel()
                }
            }
            sharedViewModel.state.collect(state)
        }
    }


    @Test
    fun fetchMovieShouldReturnError() = runBlocking {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val service = ImplKtorService(
            fakeRemoteDataSource.apiService.buildApiService("empty.json"),
            "https://swapi.py4e.com/api"
        )
        val sharedViewModel = FilmsViewModel(GetFilmsUseCase(ImplRepository(service)))
        launch {
            sharedViewModel.onIntent(AllScreensSideEvent.GetFilms)
            val state : FlowCollector<ListScreenState> = FlowCollector {
                println(it)
                if (it is ListScreenState.Success) {
                    val films = (it).entities
                    assertTrue(films.isEmpty())
                    cancel()
                }
                if (it is ListScreenState.Error) {
                    assertFails("API call error") {}
                    cancel()
                }
            }
            sharedViewModel.state.collect(state)
        }
    }
}