package de.olegrom.starwars.android.navigation.main

sealed class Screen(val route: String) {
    object Films : Screen("films")
    object Film : Screen("film/{filmId}")
    object Peoples : Screen("peoples")
    object People : Screen("people/{peopleId}")
    object Planets : Screen("planets")
    object Planet : Screen("planet/{planetId}")
    object Starships : Screen("starships")
    object Starship : Screen("starship/{starshipId}")
}
