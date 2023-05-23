package de.olegrom.starwars.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import de.olegrom.starwars.android.MainActivity
import de.olegrom.starwars.android.theme.StarWarsTheme
import de.olegrom.starwars.android.ui.detail.FilmDetailScreen
import de.olegrom.starwars.android.ui.detail.PlanetDetailScreen
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.data.remote.dto.FilmDTO
import de.olegrom.starwars.data.remote.service.FakeKtorService
import de.olegrom.starwars.data.repository.ImplRepository
import de.olegrom.starwars.domain.usecase.detail.GetFilmUseCase
import de.olegrom.starwars.domain.usecase.detail.GetPlanetUseCase
import de.olegrom.starwars.presentation.detail.FilmDetailsViewModel
import de.olegrom.starwars.presentation.detail.PlanetDetailsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlanetDetailTest {
    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setUp() {
        val viewModel = PlanetDetailsViewModel(GetPlanetUseCase(ImplRepository(FakeKtorService())))
        composeRule.activity.setContent {
            StarWarsTheme {
                PlanetDetailScreen("1", modifier = Modifier, viewModel = viewModel)
            }
        }
    }

    @Test
    fun myTest() {
        composeRule.waitUntil(5000) {
            composeRule.onAllNodesWithText("A New Hope").fetchSemanticsNodes().size == 1
        }
        composeRule.onAllNodesWithText("George Lucas").fetchSemanticsNodes().isNotEmpty()
        composeRule.onNodeWithTag(TestTag.detailHeader).assertIsDisplayed()
    }
}