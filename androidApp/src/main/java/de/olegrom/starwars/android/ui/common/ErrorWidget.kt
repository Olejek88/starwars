package de.olegrom.starwars.android.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorWidget(errorText: String) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = errorText,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
