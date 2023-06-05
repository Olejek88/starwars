package de.olegrom.starwars.android

import android.app.Application
import de.olegrom.starwars.domain.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class StarWarsApp : Application() {
    companion object {
        const val BASE_URL = "https://swapi.py4e.com/api"
        const val FILM_URL = "https://fontmeme.com/images/Star-Wars-Poster.jpg"
        const val STARSHIP_URL =
            "https://static.wikia.nocookie.net/starwars/images/7/70/DSI-HDapproach.png/revision/latest?cb=20130221005853"
        const val PLANET_URL =
            "https://static.wikia.nocookie.net/starwars/images/7/72/Teth-TVE.png/revision/latest?cb=20190423045047"
        const val PERSON_URL =
            "https://qph.cf2.quoracdn.net/main-qimg-12e1920f2b069fc36646ddd2967da899-pjlq"
    }

    override fun onCreate() {
        super.onCreate()
        initKoin(baseUrl = BASE_URL, enableNetworkLogs = BuildConfig.DEBUG) {
            androidContext(this@StarWarsApp)
            modules(
                listOf(module {
                    //android specific modules
                })
            )
        }
    }
}