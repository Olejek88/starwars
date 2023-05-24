package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.*

abstract class AbstractKtorService {
    abstract suspend fun getFilms(page: Int): FilmsDTO
    abstract suspend fun getStarships(page: Int): StarshipsDTO
    abstract suspend fun getFilm(id: String): FilmDTO
    abstract suspend fun getStarship(id: String): StarshipDTO
    abstract suspend fun getPlanets(page: Int): PlanetsDTO
    abstract suspend fun getPlanet(id: String): PlanetDTO
    abstract suspend fun getPersons(page: Int): PersonsDTO
    abstract suspend fun getPerson(id: String): PersonDTO
}

