package de.olegrom.starwars.android.navigation.main

sealed class Screen(val route: String) {
    object Films : Screen("films")
    object Film : Screen("film/{filmId}")
    object Persons : Screen("persons")
    object Person : Screen("persons/{personId}")
    object Planets : Screen("planets")
    object Planet : Screen("planet/{planetId}")
    object Starships : Screen("starships")
    object Starship : Screen("starship/{starshipId}")
}
