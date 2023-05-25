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

actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable


actual fun platformModule() = module {
    single {
        Android.create()
    }
    single {
        FilmsViewModel(
            get()
        )
    }
    single {
        ShipsViewModel(
            get()
        )
    }
    single {
        PersonsViewModel(
            get()
        )
    }
    single {
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
