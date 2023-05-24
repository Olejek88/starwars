package de.olegrom.starwars.data.repository

import de.olegrom.starwars.data.remote.dto.*

abstract class AbstractRepository {
    abstract suspend fun getAllFilms(page: Int): FilmsDTO
    abstract suspend fun getAllStarships(page: Int): StarshipsDTO
    abstract suspend fun getFilm(id: String): FilmDTO
    abstract suspend fun getStarship(id: String): StarshipDTO
    abstract suspend fun getAllPlanets(page: Int): PlanetsDTO
    abstract suspend fun getAllPersons(page: Int): PersonsDTO
    abstract suspend fun getPerson(id: String): PersonDTO
    abstract suspend fun getPlanet(id: String): PlanetDTO
}