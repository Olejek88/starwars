package de.olegrom.starwars.domain.di

import de.olegrom.starwars.data.remote.service.AbstractKtorService
import de.olegrom.starwars.data.remote.service.ImplKtorService
import de.olegrom.starwars.data.repository.AbstractRepository
import de.olegrom.starwars.data.repository.ImplRepository
import de.olegrom.starwars.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun initKoin(
    enableNetworkLogs: Boolean = false,
    baseUrl: String,
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs, baseUrl))
    }

// called by iOS etc
fun initKoin(baseUrl: String) = initKoin(enableNetworkLogs = true, baseUrl) {}

fun commonModule(enableNetworkLogs: Boolean, baseUrl: String) =
    getUseCaseModule() + getDateModule(
        enableNetworkLogs,
        baseUrl
    ) + platformModule() + getHelperModule()

fun getHelperModule() = module {


}

fun getDateModule(enableNetworkLogs: Boolean, baseUrl: String) = module {

    single<AbstractRepository> {
        ImplRepository(
            get()
        )
    }

    single<AbstractKtorService> {
        ImplKtorService(
            get(),
            baseUrl
        )
    }

    single { createJson() }

    single {
        createHttpClient(
            get(),
            get(),
            enableNetworkLogs = enableNetworkLogs
        )
    }


}

fun getUseCaseModule() = module {
}


fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
) =
    HttpClient(httpClientEngine) {


        install(ContentNegotiation) {
            json(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

