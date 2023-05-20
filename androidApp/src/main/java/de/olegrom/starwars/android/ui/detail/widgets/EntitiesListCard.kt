package de.olegrom.starwars.android.ui.detail.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import de.olegrom.starwars.android.ui.common.SmallItem
import de.olegrom.starwars.domain.domain_model.EntityModel

@Composable
fun EntitiesListCard(entities: List<EntityModel>, itemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(size = 10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(15.dp)
        ) {
            items(entities.size) {
                SmallItem(title = entities[it].title,
                    subtitle = entities[it].subtitle,
                    imageUrl = entities[it].imageUrl,
                    modifier = Modifier.width(140.dp)
                ) {
                   itemClick(entities[it].title)
                }
            }
        }
    }
}
