package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.navigation.main.Screen
import de.olegrom.starwars.android.ui.common.PagingView
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.domain.domain_model.PlanetDomainModel
import de.olegrom.starwars.presentation.home.TopAppBarViewModel
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel

@Composable
fun PlanetsScreen(
    modifier: Modifier,
    navController: NavHostController,
    pagedViewModel: PlanetsPagedViewModel
) {
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    topAppBarViewModel.title.update { "Planets" }
    val planets: LazyPagingItems<PlanetDomainModel> = remember { pagedViewModel.list }
        .collectAsLazyPagingItems()
    val listState: LazyListState = rememberLazyListState()
    PagingView(modifier = modifier, state = listState, list = planets) {
        items(
            count = planets.itemCount,
            key = planets.itemKey { it.name },
            contentType = planets.itemContentType()
        ) { index ->
            val planet = planets[index]
            planet?.let {
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
