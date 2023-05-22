package de.olegrom.starwars.util

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect val testCoroutineContext: CoroutineContext

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect fun runBlocking(block: suspend CoroutineScope.() -> Unit)

