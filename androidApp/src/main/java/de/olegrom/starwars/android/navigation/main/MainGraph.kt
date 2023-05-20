package de.olegrom.starwars.android.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.olegrom.starwars.android.ui.detail.FilmDetailScreen
import de.olegrom.starwars.android.ui.home.FilmsScreen
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
            ShipsScreen(modifier, navController)
        }
        composable(Screen.Starships.route) {
            ShipsScreen(modifier, navController)
        }
        composable(Screen.Planets.route) {
            FilmsScreen(modifier, navController)
        }
        composable(Screen.Film.route) {
            val filmId = it.arguments?.getString("filmId")
            filmId?.let { id->
                FilmDetailScreen(id, navController, modifier)
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
}
