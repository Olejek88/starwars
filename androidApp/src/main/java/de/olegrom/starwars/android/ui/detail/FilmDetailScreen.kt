package de.olegrom.starwars.android.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.ui.common.*
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.presentation.detail.FilmDetailsViewModel
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
    LaunchedEffect(key1 = Unit) {
        viewModel.getFilm(filmId)
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
                ErrorWidget((state as DetailScreenState.Error).errorMessage)
            }
            DetailScreenState.Idle -> {}
            DetailScreenState.Loading -> {
                PageLoadingView(modifier)
            }
            is DetailScreenState.Success -> {
                val film = (state as DetailScreenState.Success).entity as FilmDomainModel
                topAppBarViewModel.title.update { film.title }
                FilmDetailPageContent(film)
            }
        }
    }
}

@Composable
fun FilmDetailPageContent(film: FilmDomainModel) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SectionHeader(
            modifier = Modifier.testTag(TestTag.detailHeader),
            title = film.title, film.director
        )
        ImageCard(StarWarsApp.FILM_URL)
        TextCard(film.openingCrawl)
        ParametersCard(
            listOf(
                Pair("Director", film.director),
                Pair("Producer", film.producer),
                Pair("Release date", film.releaseDate)
            )
        )
    }
}

@Preview
@Composable
fun FilmDetailPageContentPreview() {
    FilmDetailPageContent(
        FilmDomainModel(
            "A new hope",
            director = "George Lukas",
            producer = "George Lukas",
            openingCrawl = "Once upon a time...."
        )
    )
}