package de.olegrom.starwars.android.navigation.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import de.olegrom.starwars.android.ui.detail.FilmDetailScreen
import de.olegrom.starwars.android.ui.detail.PersonDetailScreen
import de.olegrom.starwars.android.ui.detail.PlanetDetailScreen
import de.olegrom.starwars.android.ui.detail.StarshipDetailScreen
import de.olegrom.starwars.android.ui.home.*
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composable(
    route: String,
    content: @Composable () -> Unit,
) {
    composable(
        route = route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(800)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(800)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(800)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(800)
            )
        }
    ) {
        content()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Screen.Films.route
    ) {
        composable(Screen.Films.route) {
            FilmsScreen(modifier, navController, FilmsPagedViewModel(getViewModel()))
        }
        composable(Screen.Persons.route) {
            PersonsScreen(modifier, navController, PersonsPagedViewModel(getViewModel()))
        }
        composable(Screen.Starships.route) {
            ShipsScreen(modifier, navController, ShipsPagedViewModel(getViewModel()))
        }
        composable(Screen.Planets.route) {
            PlanetsScreen(modifier, navController, PlanetsPagedViewModel(getViewModel()))
        }
        composable(Screen.Film.route) {
            val filmId = it.arguments?.getString("filmId")
            filmId?.let { id ->
                FilmDetailScreen(id, modifier)
            }
        }
        composable(Screen.Starship.route) {
            val starshipId = it.arguments?.getString("starshipId")
            starshipId?.let { id ->
                StarshipDetailScreen(id, modifier)
            }
        }
        composable(Screen.Person.route) {
            val personId = it.arguments?.getString("personId")
            personId?.let { id ->
                PersonDetailScreen(id, modifier)
            }
        }
        composable(Screen.Planet.route) {
            val planetId = it.arguments?.getString("planetId")
            planetId?.let { id ->
                PlanetDetailScreen(id, modifier)
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
}