package de.olegrom.starwars.android.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorWidget(errorText: String) {
    val dialogState: MutableState<Boolean> = remember { mutableStateOf(false) }
    if (dialogState.value) {
        AlertDialog(
            onDismissRequest = { dialogState.value = false },
            confirmButton = {
                TextButton(onClick = {
                    // TODO reload page
                    dialogState.value = false
                }) { Text("OK") }
            },
            title = { Text(text = "Error") },
            text = { Text(text = errorText) }
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = errorText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.error)
        )
    }
}

@Preview
@Composable
fun FilmDetailScreenPreview() {
    ErrorWidget("Sample error")
}