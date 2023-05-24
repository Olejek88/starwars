package de.olegrom.starwars.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import de.olegrom.starwars.android.helper.ShipsListPagingSource
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel
import de.olegrom.starwars.presentation.home.ShipsViewModel
import kotlinx.coroutines.flow.Flow

class ShipsPagedViewModel (
    private val sharedViewModel: ShipsViewModel
) : ViewModel() {
    var list: Flow<PagingData<StarshipDomainModel>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 3, initialLoadSize = 10), initialKey = 1) {
            ShipsListPagingSource(sharedViewModel)
    }.flow.cachedIn(viewModelScope)
}