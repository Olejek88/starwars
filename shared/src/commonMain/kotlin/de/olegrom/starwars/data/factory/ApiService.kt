package de.olegrom.starwars.data.factory

import io.ktor.client.*

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class ApiService() {
    fun build(): HttpClient
}