package de.olegrom.starwars.android.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import de.olegrom.starwars.android.StarWarsApp
import de.olegrom.starwars.android.navigation.main.Screen
import de.olegrom.starwars.android.ui.common.PagingView
import de.olegrom.starwars.android.ui.home.widgets.EntityCard
import de.olegrom.starwars.domain.domain_model.PersonDomainModel
import de.olegrom.starwars.presentation.home.TopAppBarViewModel
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.getViewModel

@Composable
fun PersonsScreen(
    modifier: Modifier,
    navController: NavHostController,
    pagedViewModel: PersonsPagedViewModel
) {
    val topAppBarViewModel: TopAppBarViewModel = getViewModel()
    topAppBarViewModel.title.update { "Persons" }
    val persons: LazyPagingItems<PersonDomainModel> = pagedViewModel.list.collectAsLazyPagingItems()
    val listState: LazyListState = rememberLazyListState()
    PagingView(modifier = modifier, state = listState, list = persons) {
        items(
            count = persons.itemCount,
            key = persons.itemKey { it.name },
            contentType = persons.itemContentType()
        ) { index ->
            val person = persons[index]
            person?.let {
                EntityCard(
                    StarWarsApp.PERSON_URL,
                    person.name,
                    "[${person.height}cm / ${person.mass}kg], Birth year: ${person.birthYear}",
                    "Gender: ${person.gender}"
                ) {
                    navController.navigate(
                        Screen.Person.route.replace("{personId}", person.id)
                    )
                }
            }
        }
    }
}
