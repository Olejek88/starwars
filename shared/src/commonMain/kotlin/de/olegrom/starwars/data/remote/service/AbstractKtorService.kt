package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.*

abstract class AbstractKtorService {
    abstract suspend fun getFilms(): FilmsDTO
    abstract suspend fun getStarships(): StarshipsDTO
    abstract suspend fun getFilm(id: String): FilmDTO
    abstract suspend fun getStarship(id: String): StarshipDTO
    abstract suspend fun getPlanets(): PlanetsDTO
    abstract suspend fun getPlanet(id: String): PlanetDTO
    abstract suspend fun getPersons(): PersonsDTO
    abstract suspend fun getPerson(id: String): PersonDTO
}

