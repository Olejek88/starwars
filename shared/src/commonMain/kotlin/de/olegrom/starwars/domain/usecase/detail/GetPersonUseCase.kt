package de.olegrom.starwars.domain.usecase.detail

import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.data.repository.AbstractRepository
import kotlinx.coroutines.flow.flow

class GetPersonUseCase(
    private val repository: AbstractRepository
) {
    operator fun invoke(id: String) = flow {
        val response = repository.getPerson(id).asDomainModel()
        emit(response)
    }
}