package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.usecase.home.GetFilmsUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class HomeScreenViewModel(private val getFilmsUseCase: GetFilmsUseCase) : ViewModel() {
}

