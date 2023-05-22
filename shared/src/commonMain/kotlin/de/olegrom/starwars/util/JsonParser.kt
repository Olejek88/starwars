package de.olegrom.starwars.util

import de.olegrom.starwars.data.remote.dto.FilmDTO
import de.olegrom.starwars.data.remote.dto.FilmsDTO
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalSerializationApi::class)
class JsonParser {
private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    fun <T> decodeToObject(resource: String, deserializer: DeserializationStrategy<T>): T {
        return json.decodeFromString(deserializer, resource)
    }

    fun <T> decodeToString(resource: T, deserializer: SerializationStrategy<T>): String {
        return json.encodeToString(deserializer, resource)
    }

    fun decodeMovieListResponse(response: String): FilmsDTO{
        return decodeToObject(response, FilmsDTO.serializer())
    }

    fun decodeMovieDetail(movieDetail: String): FilmDTO {
        return decodeToObject(movieDetail, FilmDTO.serializer())
    }
}