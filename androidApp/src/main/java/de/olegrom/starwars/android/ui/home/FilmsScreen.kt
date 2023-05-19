package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.rememberLazyListState
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
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.HomeScreenViewModel
import de.olegrom.starwars.presentation.home.ScreenState
import org.koin.androidx.compose.getViewModel

@Composable
fun FilmsScreen(modifier: Modifier,
                navController: NavHostController,
                viewModel: HomeScreenViewModel = getViewModel()
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
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        /*
            SectionHeader(
                stageStatus?.data?.headline ?: stringResource(R.string.discover_title),
                stageStatus?.data?.description ?: stringResource(R.string.discover_subtitle)
            )
*/
        }
        when (state) {
            is ScreenState.Error -> {
                item {
                    Text(
                        text = (state as ScreenState.Error).errorMessage ?: "",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

            }
            ScreenState.Idle -> {}
            ScreenState.Loading -> {
                placeholder()

            }
            is ScreenState.Success -> {
                (state as ScreenState.Success).films.forEach { item ->
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        EntityCard(
                            item.title, item.title, item.title
                        )
                    }
                }
            }
        }
    }
}

fun LazyGridScope.placeholder() {
    item {
        CircularProgressIndicator()
    }
}
