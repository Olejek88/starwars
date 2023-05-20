package de.olegrom.starwars.domain.usecase.detail

import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.data.repository.AbstractRepository
import kotlinx.coroutines.flow.flow

class GetStarshipUseCase(
    private val repository: AbstractRepository
) {
    operator fun invoke() = flow {
        val response = repository.getAllStarships().asDomainModel()
        emit(response)
    }
}