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
import de.olegrom.starwars.android.ui.common.ErrorWidget
import de.olegrom.starwars.android.ui.getLabelByRoute
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.domain.domain_model.PersonDomainModel
import de.olegrom.starwars.presentation.home.*
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel

@Composable
fun FilmsScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: FilmsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    topAppBarViewModel.title.update { "Films" }
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
            is ListScreenState.Error -> {
                item { ErrorWidget((state as ListScreenState.Error).errorMessage) }
            }
            ListScreenState.Idle -> {}
            ListScreenState.Loading -> {
                placeholder()
            }
            is ListScreenState.Success -> {
                (state as ListScreenState.Success).entities.forEach { item ->
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        val film = item as FilmDomainModel
                        EntityCard(
                            StarWarsApp.FILM_URL,
                            "${film.title} [Episode: ${film.episodeId}]",
                            "Director: ${film.director}, Producer: ${film.producer}",
                            "Release date: ${film.releaseDate}"
                        ) {
                            navController.navigate(
                                Screen.Film.route.replace("{filmId}", film.id)
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
