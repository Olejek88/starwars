package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.domain_model.FilmDomainModel

sealed interface ScreenState{
    object Loading: ScreenState
    object Idle : ScreenState
    data class Success(val headlines: List<FilmDomainModel>) : ScreenState
    data class Error(val errorMessage: String) : ScreenState
}

