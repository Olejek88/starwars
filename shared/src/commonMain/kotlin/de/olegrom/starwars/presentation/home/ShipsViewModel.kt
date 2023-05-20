package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.usecase.lists.GetStarshipsUseCase
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.domain.util.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShipsViewModel(private val getStarshipsUseCase: GetStarshipsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<ShipsScreenState>(ShipsScreenState.Idle)
    var state = _state.asStateFlow()
    private var page: Int = 1
    fun onIntent(intent: AllScreensSideEvent) {
        if (intent is AllScreensSideEvent.GetShips) {
            getShips()
        }
    }

    private fun getShips() {
        viewModelScope.launch {
            getStarshipsUseCase.invoke().asResult().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update {
                            ShipsScreenState.Error(result.exception.message)
                        }
                    }
                    Result.Idle -> {
                        _state.update {
                            ShipsScreenState.Idle
                        }
                    }
                    Result.Loading -> {
                        _state.update {
                            ShipsScreenState.Loading
                        }
                    }
                    is Result.Success -> {
                        if (page == 1) {
                            _state.update {
                                ShipsScreenState.Success(result.data)
                            }
                        } else {
                            _state.update {
                                (it as ShipsScreenState.Success).copy(ships = it.ships + result.data)
                            }
                        }
                    }
                }
            }
        }
    }
}
