package de.olegrom.starwars.data.repository

import de.olegrom.starwars.data.remote.dto.FilmsDTO

abstract class AbstractRepository {
    abstract suspend fun getAllFilms(): FilmsDTO
}