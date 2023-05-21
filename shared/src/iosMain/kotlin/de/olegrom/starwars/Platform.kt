package de.olegrom.starwars

import de.olegrom.starwars.presentation.detail.FilmDetailsViewModel
import de.olegrom.starwars.presentation.home.FilmsViewModel
import de.olegrom.starwars.presentation.home.ShipsViewModel
import org.koin.dsl.module
import io.ktor.client.engine.darwin.*
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

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
        FilmDetailsViewModel(get())
    }
}
actual interface CommonParcelable