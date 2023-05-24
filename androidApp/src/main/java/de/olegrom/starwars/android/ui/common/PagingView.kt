package de.olegrom.starwars.android.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingView(
    modifier: Modifier = Modifier,
    state: LazyListState,
    list: LazyPagingItems<T>,
    content: LazyListScope.() -> Unit
) {
    when (list.loadState.refresh) {
        is Error -> {
            ErrorWidget("Check your internet connection")
        }
        is Loading -> {
            ListLoadingView(modifier)
        }
        else -> {
            LazyColumn(
                modifier = modifier.fillMaxHeight(),
                state = state,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                content()
                list.apply {
                    if (loadState.append is Loading) item { PageLoadingView() }
                    if (loadState.append is Error) {
                        val e = loadState.append as Error
                        item {
                            ErrorWidget(e.error.message.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListLoadingView(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}

@Composable
fun PageLoadingView() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}
