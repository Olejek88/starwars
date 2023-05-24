package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
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
    viewModel: FilmsViewModel = getViewModel()
) {
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    val pagedViewModel = FilmsPagedViewModel(viewModel)
    topAppBarViewModel.title.update { "Films" }
    val movies: LazyPagingItems<FilmDomainModel> = pagedViewModel.list.collectAsLazyPagingItems()
    PagingView(modifier = modifier, list = movies) {
        items(
            count = movies.itemCount,
            key = movies.itemKey(),
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

