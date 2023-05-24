package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.navigation.main.Screen
import de.olegrom.starwars.android.ui.common.ErrorWidget
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.data.remote.dto.PersonsDTO
import de.olegrom.starwars.data.remote.dto.PlanetsDTO
import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.domain.domain_model.PlanetDomainModel
import de.olegrom.starwars.presentation.home.*
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel

@Composable
fun PlanetsScreen(modifier: Modifier,
                navController: NavHostController,
                viewModel: PlanetsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    topAppBarViewModel.title.update { "Planets" }
    val listState = rememberLazyGridState()
    LaunchedEffect(key1 = Unit) {
        //viewModel.onIntent(AllScreensSideEvent.GetPlanets)
    }
    LazyVerticalGrid(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(
            20.dp,
            alignment = Alignment.CenterHorizontally
        ),
        state = listState,
        columns = GridCells.Adaptive(450.dp)
    ) {
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {}
        when (state) {
            is ListScreenState.Error -> {
                item { ErrorWidget((state as ListScreenState.Error).errorMessage) }
            }
            ListScreenState.Idle -> {}
            ListScreenState.Loading -> {
                placeholder()
            }
            is ListScreenState.Success -> {
                val planets = (state as ListScreenState.Success).entity as PlanetsDTO
                planets.asDomainModel().forEach { item ->
                    val planet = item as PlanetDomainModel
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        EntityCard(
                            StarWarsApp.PLANET_URL,
                            planet.name,
                            "Climate: ${planet.climate}, Surface: ${planet.surfaceWater}",
                            "Terrain: ${planet.terrain}"
                        ) {
                            navController.navigate(
                                Screen.Planet.route.replace("{planetId}", planet.id)
                            )
                        }
                    }
                }
            }
        }
    }
}
