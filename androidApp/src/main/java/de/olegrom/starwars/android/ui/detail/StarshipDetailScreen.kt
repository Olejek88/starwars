package de.olegrom.starwars.android.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.ui.common.ImageCard
import de.olegrom.starwars.android.ui.common.ParametersCard
import de.olegrom.starwars.android.ui.common.SectionHeader
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel
import de.olegrom.starwars.presentation.detail.StarshipDetailsViewModel
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.DetailScreenState
import org.koin.androidx.compose.getViewModel

@Composable
fun StarshipDetailScreen(
    starshipUrl: String,
    navController: NavHostController,
    modifier: Modifier,
    viewModel: StarshipDetailsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(AllScreensSideEvent.GetStarship(starshipUrl))
    }
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        when (state) {
            is DetailScreenState.Error -> {
                Text(
                    text = (state as DetailScreenState.Error).errorMessage,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            DetailScreenState.Idle -> {}
            DetailScreenState.Loading -> {}
            is DetailScreenState.Success -> {
                val starship = (state as DetailScreenState.Success).entity as StarshipDomainModel
                SectionHeader(title = starship.name, starship.model)
                ImageCard(StarWarsApp.STARSHIP_URL)
                ParametersCard(listOf(Pair("Manufacturer", starship.manufacturer),
                    Pair("Starship class", starship.starshipClass),
                    Pair("Cargo capacity", starship.cargoCapacity),
                    Pair("Consumables", starship.consumables),
                    Pair("Cost", starship.cost),
                    Pair("Crew", starship.crew),
                    Pair("Max atmosphering speed", starship.maxAtmospheringSpeed),
                    Pair("Hyperdrive", starship.hyperdriveRating),
                    Pair("Passengers", starship.passengers)
                ))
                //EntitiesListCard(listOf()) {}
            }
        }
    }
}