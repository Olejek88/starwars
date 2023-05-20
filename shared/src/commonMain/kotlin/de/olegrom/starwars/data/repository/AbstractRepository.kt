package de.olegrom.starwars.data.repository

import de.olegrom.starwars.data.remote.dto.FilmDTO
import de.olegrom.starwars.data.remote.dto.FilmsDTO
import de.olegrom.starwars.data.remote.dto.StarshipDTO
import de.olegrom.starwars.data.remote.dto.StarshipsDTO

abstract class AbstractRepository {
    abstract suspend fun getAllFilms(): FilmsDTO
    abstract suspend fun getAllStarships(): StarshipsDTO
    abstract suspend fun getFilm(id: String): FilmDTO
    abstract suspend fun getStarship(id: String): StarshipDTO
}