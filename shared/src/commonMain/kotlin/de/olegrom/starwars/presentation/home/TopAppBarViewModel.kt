package de.olegrom.starwars.presentation.home

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class TopAppBarViewModel() : ViewModel() {
    var title = MutableStateFlow("Star Wars")
}
