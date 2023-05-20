package de.olegrom.starwars.android.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.olegrom.starwars.android.ui.common.ImageCard
import de.olegrom.starwars.android.ui.common.ParametersCard
import de.olegrom.starwars.android.ui.common.SectionHeader
import de.olegrom.starwars.android.ui.common.TextCard
import de.olegrom.starwars.android.ui.detail.widgets.EntitiesListCard

@Composable
fun FilmDetailScreen(
    filmId: String? = null,
    navController: NavHostController,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionHeader(title = "", null)
        ImageCard("")
        TextCard("")
        ParametersCard()
        EntitiesListCard(listOf()) {}
        Spacer(modifier = Modifier.size(20.dp))
    }
}