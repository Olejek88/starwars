package de.olegrom.starwars.android.navigation.main

sealed class Screen(val route: String) {
    object Films : Screen("films")
    object Film : Screen("film/{filmUrl}")
    object Peoples : Screen("peoples")
    object People : Screen("people/{peopleUrl}")
    object Planets : Screen("planets")
    object Planet : Screen("planet/{planetUrl}")
    object Starships : Screen("starships")
    object Starship : Screen("starship/{starshipUrl}")
}
