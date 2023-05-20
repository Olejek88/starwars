package de.olegrom.starwars.presentation.home

sealed class AllScreensSideEvent {
    object GetFilms : AllScreensSideEvent()
    data class GetFilm(val id: String) : AllScreensSideEvent()
    object GetStarship : AllScreensSideEvent()
    object GetPersons : AllScreensSideEvent()
    object GetPlanets : AllScreensSideEvent()
    object GetStarships : AllScreensSideEvent()
}