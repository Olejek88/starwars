package de.olegrom.starwars.viewModel

import de.olegrom.starwars.data.remote.FakeRemoteDataSource
import de.olegrom.starwars.data.remote.service.ImplKtorService
import de.olegrom.starwars.data.repository.ImplRepository
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel
import de.olegrom.starwars.domain.usecase.detail.GetStarshipUseCase
import de.olegrom.starwars.presentation.detail.StarshipDetailsViewModel
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.DetailScreenState
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

@ExperimentalCoroutinesApi
class StarshipDetailViewModelTest {
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Test
    fun fetchDetail() = runTest {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val service = ImplKtorService(
            fakeRemoteDataSource.apiService.buildApiService("ship.json"),
            "https://swapi.dev/api"
        )
        val sharedViewModel = StarshipDetailsViewModel(GetStarshipUseCase(ImplRepository(service)))
        launch {
            sharedViewModel.onIntent(AllScreensSideEvent.GetStarship("1"))
            val state: FlowCollector<DetailScreenState> = FlowCollector {
                if (it is DetailScreenState.Success) {
                    val entity = (it).entity
                    assertTrue(entity is StarshipDomainModel)
                    assertTrue(entity.name == "CR90 corvette")
                    assertTrue(entity.model == "CR90 corvette")
                    assertTrue(entity.manufacturer == "Corellian Engineering Corporation")
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