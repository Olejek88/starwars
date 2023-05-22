package de.olegrom.starwars.presentation.home

sealed class AllScreensSideEvent {
    object GetFilms : AllScreensSideEvent()
    data class GetFilm(val url: String) : AllScreensSideEvent()
    data class GetStarship(val url: String) : AllScreensSideEvent()
    object GetPersons : AllScreensSideEvent()
    object GetPlanets : AllScreensSideEvent()
    object GetStarships : AllScreensSideEvent()
}