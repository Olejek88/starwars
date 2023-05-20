package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.usecase.lists.GetFilmsUseCase
import de.olegrom.starwars.domain.usecase.lists.GetStarshipsUseCase
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.domain.util.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmsViewModel(private val getFilmsUseCase: GetFilmsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<FilmsScreenState>(FilmsScreenState.Idle)
    var state = _state.asStateFlow()
    private var page: Int = 1
    fun onIntent(intent: AllScreensSideEvent) {
        if (intent is AllScreensSideEvent.GetFilms) {
            getFilms()
        }
    }

    private fun getFilms() {
        viewModelScope.launch {
            getFilmsUseCase.invoke().asResult().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update {
                            FilmsScreenState.Error(result.exception.message)
                        }
                    }
                    Result.Idle -> {
                        _state.update {
                            FilmsScreenState.Idle
                        }
                    }
                    Result.Loading -> {
                        _state.update {
                            FilmsScreenState.Loading
                        }
                    }
                    is Result.Success -> {
                        if (page == 1) {
                            _state.update {
                                FilmsScreenState.Success(result.data)
                            }
                        } else {
                            _state.update {
                                (it as FilmsScreenState.Success).copy(films = it.films + result.data)
                            }
                        }
                    }
                }
            }
        }
    }
}
