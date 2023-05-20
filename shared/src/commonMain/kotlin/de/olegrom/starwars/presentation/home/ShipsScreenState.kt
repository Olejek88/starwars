package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.domain_model.StarshipDomainModel

sealed interface ShipsScreenState{
    object Loading: ShipsScreenState
    object Idle : ShipsScreenState
    data class Success(val ships: List<StarshipDomainModel>) : ShipsScreenState
    data class Error(val errorMessage: String) : ShipsScreenState
}

