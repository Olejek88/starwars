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
import de.olegrom.starwars.domain.domain_model.PersonDomainModel
import de.olegrom.starwars.domain.domain_model.PlanetDomainModel
import de.olegrom.starwars.presentation.home.*
import org.koin.androidx.compose.getViewModel

@Composable
fun PersonsScreen(modifier: Modifier,
                navController: NavHostController,
                viewModel: PersonsViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyGridState()
    LaunchedEffect(key1 = Unit) {
        viewModel.onIntent(AllScreensSideEvent.GetPersons)
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
                item {
                    Text(
                        text = (state as ListScreenState.Error).errorMessage,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

            }
            ListScreenState.Idle -> {}
            ListScreenState.Loading -> {
                placeholder()

            }
            is ListScreenState.Success -> {
                (state as ListScreenState.Success).entities.forEachIndexed { index, item ->
                    val person = item as PersonDomainModel
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        EntityCard(
                            StarWarsApp.PERSON_URL,
                            person.name,
                            "[${person.height}cm / ${person.mass}kg], Birth year: ${person.birthYear}",
                            "Gender: ${person.gender}"
                        ) {
                        }
                    }
                }
            }
        }
    }
}
