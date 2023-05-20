package de.olegrom.starwars.data.repository

import de.olegrom.starwars.data.remote.dto.FilmsDTO
import de.olegrom.starwars.data.remote.dto.StarshipsDTO

abstract class AbstractRepository {
    abstract suspend fun getAllFilms(): FilmsDTO
    abstract suspend fun getAllStarships(): StarshipsDTO
}