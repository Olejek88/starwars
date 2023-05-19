package de.olegrom.starwars.domain.usecase.home

import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.data.repository.AbstractRepository
import kotlinx.coroutines.flow.flow

class GetFilmsUseCase(
    private val repository: AbstractRepository
) {
    operator fun invoke() = flow {
        val response = repository.getAllFilms().asDomainModel()
        emit(response)
    }
}