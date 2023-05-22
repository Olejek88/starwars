package de.olegrom.starwars.viewModel

import de.olegrom.starwars.data.remote.FakeRemoteDataSource
import de.olegrom.starwars.data.remote.service.ImplKtorService
import de.olegrom.starwars.data.repository.ImplRepository
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.domain.usecase.detail.GetFilmUseCase
import de.olegrom.starwars.presentation.detail.FilmDetailsViewModel
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.DetailScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class FilmsDetailViewModelTest {
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Test
    fun fetchDetail() = runTest {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val service = ImplKtorService(
            fakeRemoteDataSource.apiService.buildApiService("film.json"),
            "https://swapi.py4e.com/api"
        )
        val sharedViewModel = FilmDetailsViewModel(GetFilmUseCase(ImplRepository(service)))
        launch {
            sharedViewModel.onIntent(AllScreensSideEvent.GetFilm("4"))
            val state : FlowCollector<DetailScreenState> = FlowCollector {
                println(it)
                if (it is DetailScreenState.Success) {
                    val entity = (it).entity
                    assertTrue(entity is FilmDomainModel)
                    assertTrue(entity.title == "A New Hope")
                    assertTrue(entity.episodeId == 4)
                    assertTrue(entity.director == "George Lucas")
                    assertTrue(entity.producer == "Gary Kurtz, Rick McCallum")
                    cancel()
                }
                if (it is DetailScreenState.Error) {
                    assertFails("API call error") {}
                    cancel()
                }
            }
            sharedViewModel.state.collect(state)
        }
    }
}