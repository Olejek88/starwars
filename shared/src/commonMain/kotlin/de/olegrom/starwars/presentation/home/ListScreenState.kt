package de.olegrom.starwars.presentation.home

sealed interface ListScreenState {
    data class Success(val entity: Any) : ListScreenState
    data class Error(val errorMessage: String) : ListScreenState
}

