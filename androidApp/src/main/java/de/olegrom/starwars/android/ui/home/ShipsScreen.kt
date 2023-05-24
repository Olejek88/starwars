package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.navigation.main.Screen
import de.olegrom.starwars.android.ui.common.ErrorWidget
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel
import de.olegrom.starwars.presentation.home.*
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel

@Composable
fun ShipsScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: ShipsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    topAppBarViewModel.title.update { "Starships" }
    val listState = rememberLazyGridState()
    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(AllScreensSideEvent.GetStarships(1))
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
                //val lazyPagingItems = pager.collectAsLazyPagingItems()
                (state as ListScreenState.Success).entities.forEach { item ->
                    val ship = item as StarshipDomainModel
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        EntityCard(
                            StarWarsApp.STARSHIP_URL,
                            item.model,
                            "Manufacturer: ${ship.manufacturer}",
                            "Cost: ${ship.cost}"
                        ) {
                            navController.navigate(
                                Screen.Starship.route.replace("{starshipId}", ship.id)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun LazyGridScope.placeholder() {
    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    }
}
