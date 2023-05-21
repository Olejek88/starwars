package de.olegrom.starwars.android.ui.detail

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.ui.common.ImageCard
import de.olegrom.starwars.android.ui.common.ParametersCard
import de.olegrom.starwars.android.ui.common.SectionHeader
import de.olegrom.starwars.android.ui.common.TextCard
import de.olegrom.starwars.presentation.detail.FilmDetailsViewModel
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.FilmScreenState
import org.koin.androidx.compose.getViewModel

@Composable
fun FilmDetailScreen(
    filmId: String,
    navController: NavHostController,
    modifier: Modifier,
    viewModel: FilmDetailsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(AllScreensSideEvent.GetFilm(filmId))
    }
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        when (state) {
            is FilmScreenState.Error -> {
                Text(
                    text = (state as FilmScreenState.Error).errorMessage ?: "",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            FilmScreenState.Idle -> {}
            FilmScreenState.Loading -> {}
            is FilmScreenState.Success -> {
                val film = (state as FilmScreenState.Success).film
                SectionHeader(title = film.title, film.director)
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