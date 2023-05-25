package de.olegrom.starwars

import de.olegrom.starwars.presentation.detail.FilmDetailsViewModel
import de.olegrom.starwars.presentation.detail.StarshipDetailsViewModel
import de.olegrom.starwars.presentation.detail.PersonDetailsViewModel
import de.olegrom.starwars.presentation.detail.PlanetDetailsViewModel
import de.olegrom.starwars.presentation.home.FilmsViewModel
import de.olegrom.starwars.presentation.home.PersonsViewModel
import de.olegrom.starwars.presentation.home.PlanetsViewModel
import de.olegrom.starwars.presentation.home.ShipsViewModel
import org.koin.dsl.module
import io.ktor.client.engine.darwin.*
import platform.UIKit.UIDevice

actual fun platformModule() = module {
    single {
        Darwin.create()
    }
    //single or factory can be used to get a view-model object for swiftui
    single {
        FilmsViewModel(get())
    }
    single {
        ShipsViewModel(get())
    }
    single {
        PlanetsViewModel(get())
    }
    single {
        PersonsViewModel(get())
    }
    single {
        FilmDetailsViewModel(get())
    }
    single {
        StarshipDetailsViewModel(get())
    }
    single {
        PersonDetailsViewModel(get())
    }
    single {
        PlanetDetailsViewModel(get())
    }
}
actual interface CommonParcelable