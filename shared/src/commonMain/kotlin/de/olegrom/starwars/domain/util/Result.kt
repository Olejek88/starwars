package de.olegrom.starwars.domain.util

import io.ktor.client.plugins.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed interface Result<out T> {
    object Loading : Result<Nothing>
    object Idle : Result<Nothing>
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: CustomMessage) : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .catch {
            it.printStackTrace()
            emit(Result.Error((it as Exception).getRealException()))
        }
}

sealed class CustomMessage(val message: String = "") {
    object NetworkError : CustomMessage("Something wrong with network, please try again.")
    object RandomError : CustomMessage("Something went wrong, please try again.")
    object ResponseError : CustomMessage("We are fixing your problem, Thank you for your patience.")
}

fun Exception.getRealException(): CustomMessage = when (this) {
    is HttpRequestTimeoutException -> {
        CustomMessage.NetworkError
    }
    is RedirectResponseException -> {
        CustomMessage.NetworkError
    }
    is ClientRequestException -> {
        CustomMessage.NetworkError
    }
    is ServerResponseException -> {
        CustomMessage.ResponseError
    }
    else -> {
        CustomMessage.RandomError
    }
}
