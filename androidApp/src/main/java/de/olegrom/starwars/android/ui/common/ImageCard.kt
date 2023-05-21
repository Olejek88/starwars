package de.olegrom.starwars.android.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun ImageCard(
    imageUrl: String,
    title: String? = "Image",
    backgroundColor: Color? = null,
    itemClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { itemClick() }
            .clip(shape = RoundedCornerShape(size = 10.dp)),
    ) {
        Column(
            Modifier
                .background(color = backgroundColor ?: MaterialTheme.colorScheme.onBackground)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                10.dp,
                alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
                model = ImageRequest.Builder(
                    LocalContext.current
                ).scale(Scale.FILL).data(imageUrl).crossfade(true).build(),
                contentDescription = title,
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
