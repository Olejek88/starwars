package de.olegrom.starwars.presentation.detail

import de.olegrom.starwars.domain.usecase.detail.GetPersonUseCase
import de.olegrom.starwars.domain.util.Result
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.presentation.home.AllScreensSideEvent
import de.olegrom.starwars.presentation.home.DetailScreenState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonDetailsViewModel(private val getPersonUseCase: GetPersonUseCase) : ViewModel() {
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Idle)
    var state = _state.asStateFlow()
    fun onIntent(intent: AllScreensSideEvent) {
        if (intent is AllScreensSideEvent.GetPerson) {
            getPerson(intent.id)
        }
    }
    private fun getPerson(id: String) {
        viewModelScope.launch {
            getPersonUseCase.invoke(id).asResult().collectLatest { result ->
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

