package de.olegrom.starwars

import android.os.Parcelable
import de.olegrom.starwars.presentation.detail.FilmDetailsViewModel
import de.olegrom.starwars.presentation.detail.PersonDetailsViewModel
import de.olegrom.starwars.presentation.detail.PlanetDetailsViewModel
import de.olegrom.starwars.presentation.detail.StarshipDetailsViewModel
import de.olegrom.starwars.presentation.home.*
import io.ktor.client.engine.android.*
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

/**
 * shared implementation of parcelable
 */
actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable


actual fun platformModule() = module {
    single {
        Android.create()
    }
    viewModel {
        FilmsViewModel(
            get()
        )
    }
    viewModel {
        ShipsViewModel(
            get()
        )
    }
    viewModel {
        PersonsViewModel(
            get()
        )
    }
    viewModel {
        PlanetsViewModel(
            get()
        )
    }
    viewModel {
        FilmDetailsViewModel(get())
    }
    viewModel {
        StarshipDetailsViewModel(get())
    }
    viewModel {
        PersonDetailsViewModel(get())
    }
    viewModel {
        PlanetDetailsViewModel(get())
    }
    single {
        TopAppBarViewModel()
    }
}
