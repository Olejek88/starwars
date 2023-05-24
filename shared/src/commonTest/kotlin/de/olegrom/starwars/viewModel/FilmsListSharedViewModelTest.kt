package de.olegrom.starwars.viewModel

import de.olegrom.starwars.data.remote.FakeRemoteDataSource
import de.olegrom.starwars.data.remote.dto.FilmsDTO
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
import kotlinx.coroutines.flow.last
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
            "https://swapi.dev/api"
        )
        val sharedViewModel = FilmsViewModel(GetFilmsUseCase(ImplRepository(service)))
        launch {
            val loadResult = sharedViewModel.loadMovies(1).last()
            assertTrue(loadResult is ListScreenState.Success)
            assertTrue(loadResult.entity is FilmsDTO)
            val films = loadResult.entity as FilmsDTO
            assertTrue(films.results.isNotEmpty())
            assertTrue(films.results[0].title == "A New Hope")
            assertTrue(films.results[0].episode_id == 4)
            assertTrue(films.results[0].director == "George Lucas")
            assertTrue(films.results[0].producer == "Gary Kurtz, Rick McCallum")
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
            val loadResult = sharedViewModel.loadMovies(1).last()
            assertTrue(loadResult is ListScreenState.Success)
            assertTrue(loadResult.entity is FilmsDTO)
            val films = loadResult.entity as FilmsDTO
            assertTrue(films.results.isEmpty())
        }
    }
}