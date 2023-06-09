package de.olegrom.starwars.domain.usecase.lists

import de.olegrom.starwars.data.repository.AbstractRepository
import kotlinx.coroutines.flow.flow

class GetStarshipsUseCase(
    private val repository: AbstractRepository
) {
    operator fun invoke(page: Int) = flow {
        val response = repository.getAllStarships(page)
        emit(response)
    }
}