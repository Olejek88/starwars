package de.olegrom.starwars.data.repository

import de.olegrom.starwars.data.remote.service.AbstractKtorService

class ImplRepository(
    private val ktorService: AbstractKtorService
) : AbstractRepository() {

    override suspend fun getAllFilms(
        page: Int,
        pageSize: Int
    ) = ktorService.getFilms(
        pageSize = pageSize,
        page = page,
    )
}




