package de.olegrom.starwars.presentation.home

sealed interface DetailScreenState {
    object Loading : DetailScreenState
    object Idle : DetailScreenState
    data class Success(val entity: Any) : DetailScreenState
    data class Error(val errorMessage: String) : DetailScreenState
}

