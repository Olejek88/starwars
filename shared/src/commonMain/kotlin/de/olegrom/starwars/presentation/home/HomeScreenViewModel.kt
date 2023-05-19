package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.usecase.home.GetFilmsUseCase
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.domain.util.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val getFilmsUseCase: GetFilmsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<ScreenState>(ScreenState.Idle)
    var state = _state.asStateFlow()
    private var page: Int = 1
    fun onIntent(intent: AllScreensSideEvent) {
        when (intent) {
            is AllScreensSideEvent.GetFilms -> {
                getFilms()
            }
            else -> {

            }
        }
    }

    private fun getFilms() {
        viewModelScope.launch {
            getFilmsUseCase.invoke(page = page).asResult().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        if (page == 1) {
                            _state.update {
                                ScreenState.Error(result.exception.message)
                            }
                        }
                    }
                    Result.Idle -> {
                        if (page == 1) {
                            _state.update {
                                ScreenState.Idle
                            }
                        }

                    }
                    Result.Loading -> {
                        if (page == 1) {
                            _state.update {
                                ScreenState.Loading
                            }
                        }
                    }
                    is Result.Success -> {
                        if (page == 1) {
                            _state.update {
                                ScreenState.Success(result.data)
                            }
                        } else {
                            _state.update {
                                (it as ScreenState.Success).copy(it.headlines + result.data)
                            }
                        }
                    }
                }

            }

        }
    }
}
