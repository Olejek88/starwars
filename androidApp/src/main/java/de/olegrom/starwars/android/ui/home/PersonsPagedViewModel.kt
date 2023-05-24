package de.olegrom.starwars.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import de.olegrom.starwars.android.helper.PersonsListPagingSource
import de.olegrom.starwars.domain.domain_model.PersonDomainModel
import de.olegrom.starwars.presentation.home.PersonsViewModel
import kotlinx.coroutines.flow.Flow

class PersonsPagedViewModel (
    private val sharedViewModel: PersonsViewModel
) : ViewModel() {
    var list: Flow<PagingData<PersonDomainModel>> =
        Pager(PagingConfig(pageSize = 10), initialKey = 1) {
            PersonsListPagingSource(sharedViewModel)
    }.flow.cachedIn(viewModelScope)
}