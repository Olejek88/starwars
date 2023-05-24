package de.olegrom.starwars.presentation.home

sealed class AllScreensSideEvent {
    data class GetFilms(val page: Int) : AllScreensSideEvent()
    data class GetFilm(val id: String) : AllScreensSideEvent()
    data class GetStarship(val id: String) : AllScreensSideEvent()
    data class GetPlanet(val id: String) : AllScreensSideEvent()
    data class GetPerson(val id: String) : AllScreensSideEvent()
    data class GetPersons(val page: Int) : AllScreensSideEvent()
    data class GetPlanets(val page: Int) : AllScreensSideEvent()
    data class GetStarships(val page: Int) : AllScreensSideEvent()
}