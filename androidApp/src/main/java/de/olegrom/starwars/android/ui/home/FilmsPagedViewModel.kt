package de.olegrom.starwars.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import de.olegrom.starwars.android.helper.FilmsListPagingSource
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.presentation.home.FilmsViewModel
import kotlinx.coroutines.flow.Flow

class FilmsPagedViewModel(
    private val sharedViewModel: FilmsViewModel
) : ViewModel() {
    var list: Flow<PagingData<FilmDomainModel>> =
        Pager(
            PagingConfig(pageSize = 10, prefetchDistance = 3, initialLoadSize = 10),
            initialKey = 1
        ) {
            FilmsListPagingSource(sharedViewModel)
        }.flow.cachedIn(viewModelScope)
}