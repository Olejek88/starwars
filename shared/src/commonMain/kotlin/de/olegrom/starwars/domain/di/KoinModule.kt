package de.olegrom.starwars.domain.di

import de.olegrom.starwars.data.remote.service.AbstractKtorService
import de.olegrom.starwars.data.remote.service.ImplKtorService
import de.olegrom.starwars.data.repository.AbstractRepository
import de.olegrom.starwars.data.repository.ImplRepository
import de.olegrom.starwars.domain.usecase.detail.GetFilmUseCase
import de.olegrom.starwars.domain.usecase.detail.GetPersonUseCase
import de.olegrom.starwars.domain.usecase.detail.GetPlanetUseCase
import de.olegrom.starwars.domain.usecase.detail.GetStarshipUseCase
import de.olegrom.starwars.domain.usecase.lists.GetFilmsUseCase
import de.olegrom.starwars.domain.usecase.lists.GetPersonsUseCase
import de.olegrom.starwars.domain.usecase.lists.GetPlanetsUseCase
import de.olegrom.starwars.domain.usecase.lists.GetStarshipsUseCase
import de.olegrom.starwars.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.cache.*
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
    single {
        GetFilmsUseCase(get())
    }
    single {
        GetFilmUseCase(get())
    }
    single {
        GetStarshipsUseCase(get())
    }
    single {
        GetStarshipUseCase(get())
    }
    single {
        GetPersonsUseCase(get())
    }
    single {
        GetPlanetsUseCase(get())
    }
    single {
        GetPlanetUseCase(get())
    }
    single {
        GetPersonUseCase(get())
    }
}


fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
) =
    HttpClient(httpClientEngine) {

        install(HttpCache)
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

