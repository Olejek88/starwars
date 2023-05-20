package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.presentation.home.*
import org.koin.androidx.compose.getViewModel

@Composable
fun ShipsScreen(modifier: Modifier,
                navController: NavHostController,
                viewModel: ShipsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyGridState()
    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(AllScreensSideEvent.GetStarships)
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
            is ShipsScreenState.Error -> {
                item {
                    Text(
                        text = (state as ShipsScreenState.Error).errorMessage ?: "",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

            }
            ShipsScreenState.Idle -> {}
            ShipsScreenState.Loading -> {
                placeholder()

            }
            is ShipsScreenState.Success -> {
                (state as ShipsScreenState.Success).ships.forEach { item ->
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        EntityCard(
                            "https://static.wikia.nocookie.net/starwars/images/7/70/DSI-HDapproach.png/revision/latest?cb=20130221005853",
                            item.model,
                            "Manufacturer: ${item.manufacturer}",
                            "Cost: ${item.cost}"
                        )
                    }
                }
            }
        }
    }
}