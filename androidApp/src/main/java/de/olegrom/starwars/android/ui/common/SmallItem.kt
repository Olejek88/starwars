package de.olegrom.starwars.android.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import de.olegrom.starwars.android.R

@Composable
fun SmallItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    imageUrl: String,
    label: String? = null,
    itemClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(color = Color.Transparent)
            .clickable { itemClick() },
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Box {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background),
                    error = painterResource(R.drawable.sv_planet)
                )
            }
            if (label != null) {
                ItemLabel(title = label, modifier = Modifier
                    .padding(5.dp)
                    .align(alignment = Alignment.TopStart))
            }
        }
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}


@Composable
fun ItemLabel(
    title: String,
    modifier: Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}
