package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.HomeScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FilmsScreen(modifier: Modifier,
                navController: NavHostController,
                viewModel: HomeScreenViewModel = getViewModel()
) {
    val films: List<FilmDomainModel> = listOf()
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
        films.forEach { item ->
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                EntityCard(
                    item.title, item.title, item.title
                )
            }
        }
    }
}
