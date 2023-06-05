package de.olegrom.starwars.android.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.ui.common.ErrorWidget
import de.olegrom.starwars.android.ui.common.ImageCard
import de.olegrom.starwars.android.ui.common.ParametersCard
import de.olegrom.starwars.android.ui.common.SectionHeader
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel
import de.olegrom.starwars.presentation.detail.StarshipDetailsViewModel
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.DetailScreenState
import de.olegrom.starwars.presentation.home.TopAppBarViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun StarshipDetailScreen(
    starshipId: String,
    modifier: Modifier,
    viewModel: StarshipDetailsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(AllScreensSideEvent.GetStarship(starshipId))
    }
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        when (state) {
            is DetailScreenState.Error -> {
                ErrorWidget((state as DetailScreenState.Error).errorMessage)
            }
            DetailScreenState.Idle -> {}
            DetailScreenState.Loading -> {}
            is DetailScreenState.Success -> {
                val starship = (state as DetailScreenState.Success).entity as StarshipDomainModel
                topAppBarViewModel.title.value = starship.name
                SectionHeader(
                    modifier = Modifier.testTag(TestTag.detailHeader),
                    title = starship.name, subtitle = starship.model
                )
                ImageCard(StarWarsApp.STARSHIP_URL)
                ParametersCard(
                    listOf(
                        Pair("Manufacturer", starship.manufacturer),
                        Pair("Starship class", starship.starshipClass),
                        Pair("Cargo capacity", starship.cargoCapacity),
                        Pair("Consumables", starship.consumables),
                        Pair("Cost", starship.cost),
                        Pair("Crew", starship.crew),
                        Pair("Max atmosphering speed", starship.maxAtmospheringSpeed),
                        Pair("Hyperdrive", starship.hyperdriveRating),
                        Pair("Passengers", starship.passengers)
                    )
                )
                //EntitiesListCard(listOf()) {}
            }
        }
    }
}