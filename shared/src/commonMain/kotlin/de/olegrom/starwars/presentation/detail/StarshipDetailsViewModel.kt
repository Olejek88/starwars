package de.olegrom.starwars.presentation.detail

import de.olegrom.starwars.domain.usecase.detail.GetStarshipUseCase
import de.olegrom.starwars.domain.util.Result
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.DetailEntity
import de.olegrom.starwars.presentation.home.DetailScreenState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StarshipDetailsViewModel(private val getStarshipUseCase: GetStarshipUseCase) : ViewModel() {
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Idle)
    var state = _state.asStateFlow()
    fun onIntent(intent: AllScreensSideEvent) {
        if (intent is AllScreensSideEvent.GetStarship) {
            getStarship(intent.id)
        }
    }

    private fun getStarship(id: String) {
        viewModelScope.launch {
            getStarshipUseCase.invoke(id).asResult().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update {
                            DetailScreenState.Error(result.exception.message)
                        }
                    }
                    Result.Idle -> {
                        _state.update {
                            DetailScreenState.Idle
                        }
                    }
                    Result.Loading -> {
                        _state.update {
                            DetailScreenState.Loading
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            DetailScreenState.Success(result.data)
                        }
                    }
                }
            }
        }
    }
}

