package de.olegrom.starwars.android.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.olegrom.starwars.android.ui.detail.FilmDetailScreen
import de.olegrom.starwars.android.ui.detail.StarshipDetailScreen
import de.olegrom.starwars.android.ui.home.FilmsScreen
import de.olegrom.starwars.android.ui.home.PersonsScreen
import de.olegrom.starwars.android.ui.home.PlanetsScreen
import de.olegrom.starwars.android.ui.home.ShipsScreen

@Composable
fun MainGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Screen.Films.route
    ) {
        composable(Screen.Films.route) {
            FilmsScreen(modifier, navController)
        }
        composable(Screen.Peoples.route) {
            PersonsScreen(modifier, navController)
        }
        composable(Screen.Starships.route) {
            ShipsScreen(modifier, navController)
        }
        composable(Screen.Planets.route) {
            PlanetsScreen(modifier, navController)
        }
        composable(Screen.Film.route) {
            val filmUrl = it.arguments?.getString("filmUrl")
            filmUrl?.let { id->
                FilmDetailScreen(id, navController, modifier)
            }
        }
        composable(Screen.Starship.route) {
            val starshipUrl = it.arguments?.getString("starshipUrl")
            starshipUrl?.let { id->
                StarshipDetailScreen(id, navController, modifier)
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
}
