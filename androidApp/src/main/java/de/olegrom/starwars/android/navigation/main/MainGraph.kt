package de.olegrom.starwars.android.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.olegrom.starwars.android.ui.detail.FilmDetailScreen
import de.olegrom.starwars.android.ui.detail.PersonDetailScreen
import de.olegrom.starwars.android.ui.detail.PlanetDetailScreen
import de.olegrom.starwars.android.ui.detail.StarshipDetailScreen
import de.olegrom.starwars.android.ui.home.*
import org.koin.androidx.compose.getViewModel

@Composable
fun MainGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    val filmsPagedViewModel = FilmsPagedViewModel(getViewModel())
    val shipsPagedViewModel = ShipsPagedViewModel(getViewModel())
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Screen.Films.route
    ) {
        composable(Screen.Films.route) {
            FilmsScreen(modifier, navController, filmsPagedViewModel)
        }
        composable(Screen.Persons.route) {
            PersonsScreen(modifier, navController)
        }
        composable(Screen.Starships.route) {
            ShipsScreen(modifier, navController, shipsPagedViewModel)
        }
        composable(Screen.Planets.route) {
            PlanetsScreen(modifier, navController)
        }
        composable(Screen.Film.route) {
            val filmId = it.arguments?.getString("filmId")
            filmId?.let { id->
                FilmDetailScreen(id, modifier)
            }
        }
        composable(Screen.Starship.route) {
            val starshipId = it.arguments?.getString("starshipId")
            starshipId?.let { id->
                StarshipDetailScreen(id, modifier)
            }
        }
        composable(Screen.Person.route) {
            val personId = it.arguments?.getString("personId")
            personId?.let { id->
                PersonDetailScreen(id, modifier)
            }
        }
        composable(Screen.Planet.route) {
            val planetId = it.arguments?.getString("planetId")
            planetId?.let { id->
                PlanetDetailScreen(id, modifier)
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
}
