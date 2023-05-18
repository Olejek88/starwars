package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.FilmsDTO

abstract class AbstractKtorService {
    abstract suspend fun getFilms(
        pageSize: Int,
        page: Int
    ): FilmsDTO
}

