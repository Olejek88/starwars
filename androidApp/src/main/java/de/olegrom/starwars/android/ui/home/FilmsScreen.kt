package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.CircularProgressIndicator
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
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.navigation.main.Screen
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.FilmsViewModel
import de.olegrom.starwars.presentation.home.FilmsScreenState
import org.koin.androidx.compose.getViewModel

@Composable
fun FilmsScreen(modifier: Modifier,
                navController: NavHostController,
                viewModel: FilmsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyGridState()
    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(AllScreensSideEvent.GetFilms)
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
            is FilmsScreenState.Error -> {
                item {
                    Text(
                        text = (state as FilmsScreenState.Error).errorMessage ?: "",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

            }
            FilmsScreenState.Idle -> {}
            FilmsScreenState.Loading -> {
                placeholder()

            }
            is FilmsScreenState.Success -> {
                (state as FilmsScreenState.Success).films.forEachIndexed { index, item ->
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        EntityCard(
                            StarWarsApp.FILM_URL,
                            "${item.title} [Episode: ${item.episodeId}]",
                            "Director: ${item.director}, Producer: ${item.producer}",
                            "Release date: ${item.releaseDate}"
                        ) {
                            val params = item.url.split("/")
                            // TODO user regex
                            if (params.size>=2) {
                                val id = params[params.size-2];
                                navController.navigate(
                                    Screen.Film.route.replace("{filmId}", id)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun LazyGridScope.placeholder() {
    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    }
}
