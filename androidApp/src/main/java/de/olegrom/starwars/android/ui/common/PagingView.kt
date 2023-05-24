package de.olegrom.starwars.android.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingView(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<T>,
    content: LazyListScope.() -> Unit
) {
    if (list.loadState.refresh is Error) {
        val e = list.loadState.refresh as Error
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
        // ErrorScreen(message = e.error.message.toString(), onRetry = list::refresh)
    } else {
        LazyColumn(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            content()
            list.apply {
                when {
                    loadState.append is Loading -> item { ListLoadingView() }
                    loadState.append is Error && loadState.append !is Loading -> {
                        val e = loadState.append as Error
                        item {
                            ErrorWidget(e.error.message.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListLoadingView() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //SwipeRefreshIndicator(SwipeRefreshState(true), 50.dp)
    }
}

/*
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
*/
