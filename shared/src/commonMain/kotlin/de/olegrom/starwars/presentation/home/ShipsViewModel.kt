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
    private val _state = MutableStateFlow<ListScreenState>(ListScreenState.Idle)
    var state = _state.asStateFlow()
    private var page: Int = 1
    fun onIntent(intent: AllScreensSideEvent) {
        if (intent is AllScreensSideEvent.GetStarships) {
            getShips()
        }
    }

    private fun getShips() {
        viewModelScope.launch {
            getStarshipsUseCase.invoke().asResult().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update {
                            ListScreenState.Error(result.exception.message)
                        }
                    }
                    Result.Idle -> {
                        _state.update {
                            ListScreenState.Idle
                        }
                    }
                    Result.Loading -> {
                        _state.update {
                            ListScreenState.Loading
                        }
                    }
                    is Result.Success -> {
                        if (page == 1) {
                            _state.update {
                                ListScreenState.Success(result.data)
                            }
                        } else {
                            _state.update {
                                (it as ListScreenState.Success).copy(entities = it.entities + result.data)
                            }
                        }
                    }
                }
            }
        }
    }
}
