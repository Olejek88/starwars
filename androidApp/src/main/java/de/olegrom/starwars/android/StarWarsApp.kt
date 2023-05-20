package de.olegrom.starwars.android

import android.app.Application
import de.olegrom.starwars.domain.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class StarWarsApp : Application(){
    companion object {
        const val FILM_URL = "https://fontmeme.com/images/Star-Wars-Poster.jpg"
    }

    override fun onCreate() {
        super.onCreate()
        initKoin(baseUrl = "https://swapi.py4e.com/api", enableNetworkLogs = BuildConfig.DEBUG) {
            androidContext(this@StarWarsApp)
            modules(
                listOf(module {
                    //android specific modules
                })
            )
        }
    }
}