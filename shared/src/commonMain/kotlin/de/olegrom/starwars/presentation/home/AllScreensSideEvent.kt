package de.olegrom.starwars.presentation.home

sealed class AllScreensSideEvent {
    object GetFilms : AllScreensSideEvent()
    object GetPersons : AllScreensSideEvent()
    object GetPlanets : AllScreensSideEvent()
    object GetShips : AllScreensSideEvent()
}