package de.olegrom.starwars.android.navigation.main

sealed class Screen(val route: String) {
    object Films : Screen("Films")
    object Peoples : Screen("Peoples")
    object Planets : Screen("Planets")
    object Starships : Screen("Starships")
}
