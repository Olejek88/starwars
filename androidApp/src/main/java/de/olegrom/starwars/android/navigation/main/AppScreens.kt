package de.olegrom.starwars.android.navigation.main

sealed class Screen(val route: String) {
    object Films : Screen("films")
    object Film : Screen("film/{filmId}")
    object Peoples : Screen("peoples")
    object Planets : Screen("planets")
    object Starships : Screen("starships")
    object Starship : Screen("starship")
}
