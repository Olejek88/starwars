package de.olegrom.starwars.presentation.home

sealed interface ScreenState {
    data class Success(val entity: Any) : ScreenState
    data class Error(val errorMessage: String) : ScreenState
}

