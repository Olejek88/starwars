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
import de.olegrom.starwars.android.ui.common.PageLoadingView
import de.olegrom.starwars.android.ui.common.ParametersCard
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.domain.domain_model.PlanetDomainModel
import de.olegrom.starwars.presentation.detail.PlanetDetailsViewModel
import de.olegrom.starwars.presentation.home.DetailScreenState
import de.olegrom.starwars.presentation.home.TopAppBarViewModel
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel

@Composable
fun PlanetDetailScreen(
    planetId: String,
    modifier: Modifier,
    viewModel: PlanetDetailsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    LaunchedEffect(key1 = Unit) {
        viewModel.getPlanet(planetId)
    }
    Column(
        modifier = modifier
            .testTag(TestTag.detailView)
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        when (state) {
            is DetailScreenState.Error -> {
                ErrorWidget((state as DetailScreenState.Error).errorMessage)
            }
            DetailScreenState.Idle -> {}
            DetailScreenState.Loading -> {
                PageLoadingView(modifier)
            }
            is DetailScreenState.Success -> {
                val planet = (state as DetailScreenState.Success).entity as PlanetDomainModel
                topAppBarViewModel.title.update { planet.name }
                ImageCard(StarWarsApp.PLANET_URL)
                ParametersCard(
                    listOf(
                        Pair("Climate", planet.climate),
                        Pair("Terrain", planet.terrain),
                        Pair("Rotation period", planet.rotationPeriod),
                        Pair("Diameter", planet.diameter),
                        Pair("Gravity", planet.gravity),
                        Pair("Orbital period", planet.orbitalPeriod),
                        Pair("Population", planet.population),
                        Pair("Surface water", planet.surfaceWater),
                    )
                )
            }
        }
    }
}