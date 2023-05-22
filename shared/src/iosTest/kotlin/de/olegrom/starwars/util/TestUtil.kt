package de.olegrom.starwars.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual val testCoroutineContext: CoroutineContext = Dispatchers.Main
actual fun runBlocking(block: suspend CoroutineScope.() -> Unit) =
    kotlinx.coroutines.runBlocking(testCoroutineContext) { this.block() }