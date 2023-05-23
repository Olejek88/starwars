package de.olegrom.starwars.android.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ParametersCard(parameters: List<Pair<String, String>>) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 10.dp)),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            parameters.forEachIndexed { index, keyValue ->
                KeyValueRow(
                    key = keyValue.first,
                    value = keyValue.second,
                )
                if (index < parameters.lastIndex) {
                    Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}
