package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.navigation.main.Screen
import de.olegrom.starwars.android.ui.common.PagingView
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.presentation.home.*
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import de.olegrom.starwars.domain.domain_model.FilmDomainModel

@Composable
fun FilmsScreen(
    modifier: Modifier,
    navController: NavHostController,
    pagedViewModel: FilmsPagedViewModel
) {
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    topAppBarViewModel.title.update { "Films" }
    val movies: LazyPagingItems<FilmDomainModel> = pagedViewModel.list.collectAsLazyPagingItems()
    val listState: LazyListState = rememberLazyListState()
    PagingView(modifier = modifier, state = listState, list = movies) {
        items(
            count = movies.itemCount,
            key = movies.itemKey { it.id },
            contentType = movies.itemContentType()
        ) { index ->
            val item = movies[index]
            item?.let {
                EntityCard(
                    StarWarsApp.FILM_URL,
                    "${item.title} [Episode: ${item.episodeId}]",
                    "Director: ${item.director}, Producer: ${item.producer}",
                    "Release date: ${item.releaseDate}"
                ) {
                    navController.navigate(
                        Screen.Film.route.replace("{filmId}", item.id)
                    )
                }
            }
        }
    }
}
