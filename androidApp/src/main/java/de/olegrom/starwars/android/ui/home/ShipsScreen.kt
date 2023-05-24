package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.navigation.main.Screen
import de.olegrom.starwars.android.ui.common.PagingView
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel
import de.olegrom.starwars.presentation.home.ShipsViewModel
import de.olegrom.starwars.presentation.home.TopAppBarViewModel
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel

@Composable
fun ShipsScreen(
    modifier: Modifier,
    navController: NavHostController,
    pagedViewModel: ShipsPagedViewModel
) {
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    topAppBarViewModel.title.update { "Starships" }
    val listState: LazyListState = rememberLazyListState()
    val ships: LazyPagingItems<StarshipDomainModel> = pagedViewModel.list.collectAsLazyPagingItems()
    PagingView(modifier = modifier, state = listState, list = ships) {
        items(
            count = ships.itemCount,
            key = ships.itemKey { it.name },
            contentType = ships.itemContentType()
        ) { index ->
            val ship = ships[index]
            ship?.let {
                EntityCard(
                    StarWarsApp.STARSHIP_URL,
                    ship.model,
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

fun LazyGridScope.placeholder() {
    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    }
}
