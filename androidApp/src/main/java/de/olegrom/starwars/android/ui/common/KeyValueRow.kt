package de.olegrom.starwars.android.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun KeyValueRow(
    key: String,
    value: String,
    color: Color? = null,
    leadingIconResourceId: Int? = null,
    leadingIconColor: Color = Color.White,
) {
    Row(
        modifier = Modifier
            .padding(0.dp, 5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIconResourceId != null)
            Icon(
                painter = painterResource(leadingIconResourceId),
                contentDescription = "",
                tint = leadingIconColor,
                modifier = Modifier.width(35.dp)
            )
        Text(
            text = key,
            style = MaterialTheme.typography.bodyMedium,
            color = color ?: MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = color ?: MaterialTheme.colorScheme.onSurface
        )
    }
}
