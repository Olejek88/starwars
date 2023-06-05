package de.olegrom.starwars.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import de.olegrom.starwars.android.helper.PlanetsListPagingSource
import de.olegrom.starwars.domain.domain_model.PlanetDomainModel
import de.olegrom.starwars.presentation.home.PlanetsViewModel
import kotlinx.coroutines.flow.Flow

class PlanetsPagedViewModel(
    private val sharedViewModel: PlanetsViewModel
) : ViewModel() {
    var list: Flow<PagingData<PlanetDomainModel>> =
        Pager(
            PagingConfig(pageSize = 10, prefetchDistance = 3, initialLoadSize = 10),
            initialKey = 1
        ) {
            PlanetsListPagingSource(sharedViewModel)
        }.flow.cachedIn(viewModelScope)
}