package de.olegrom.starwars.android.ui.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.ui.common.ImageCard
import de.olegrom.starwars.android.ui.common.ParametersCard
import de.olegrom.starwars.android.ui.common.SectionHeader
import de.olegrom.starwars.android.ui.common.TextCard
import de.olegrom.starwars.android.ui.getLabelByRoute
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.presentation.detail.FilmDetailsViewModel
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.DetailScreenState
import de.olegrom.starwars.presentation.home.TopAppBarViewModel
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel

@Composable
fun FilmDetailScreen(
    filmId: String,
    modifier: Modifier,
    viewModel: FilmDetailsViewModel = getViewModel(),
    topAppBarViewModel: TopAppBarViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val title by topAppBarViewModel.title.collectAsState()
    Log.d("[]","${title}")
    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(AllScreensSideEvent.GetFilm(filmId))
    }
    Column(
        modifier = modifier
            .testTag(TestTag.detailView)
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        when (state) {
            is DetailScreenState.Error -> {
                Text(
                    text = (state as DetailScreenState.Error).errorMessage ?: "",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            DetailScreenState.Idle -> {}
            DetailScreenState.Loading -> {}
            is DetailScreenState.Success -> {
                val film = (state as DetailScreenState.Success).entity as FilmDomainModel
                topAppBarViewModel.title.update { film.title }
                SectionHeader(modifier = Modifier.testTag(TestTag.detailHeader),
                    title = film.title, film.director)
                ImageCard(StarWarsApp.FILM_URL)
                TextCard(film.openingCrawl)
                ParametersCard(listOf(Pair("Director", film.director),
                    Pair("Producer", film.producer),
                    Pair("Release date", film.releaseDate)))
                //EntitiesListCard(listOf()) {}
            }
        }
    }
}