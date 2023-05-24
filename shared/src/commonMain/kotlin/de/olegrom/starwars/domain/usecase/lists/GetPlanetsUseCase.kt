package de.olegrom.starwars.domain.usecase.lists

import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.data.repository.AbstractRepository
import kotlinx.coroutines.flow.flow

class GetPlanetsUseCase(
    private val repository: AbstractRepository
) {
    operator fun invoke(page: Int) = flow {
        val response = repository.getAllPlanets(page).asDomainModel()
        emit(response)
    }
}