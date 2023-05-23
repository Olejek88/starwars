package de.olegrom.starwars.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import de.olegrom.starwars.android.MainActivity
import de.olegrom.starwars.android.theme.StarWarsTheme
import de.olegrom.starwars.android.ui.detail.StarshipDetailScreen
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.data.remote.service.FakeKtorService
import de.olegrom.starwars.data.repository.ImplRepository
import de.olegrom.starwars.domain.usecase.detail.GetStarshipUseCase
import de.olegrom.starwars.presentation.detail.StarshipDetailsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StarshipDetailTest {
    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setUp() {
        val viewModel = StarshipDetailsViewModel(GetStarshipUseCase(ImplRepository(FakeKtorService())))
        composeRule.activity.setContent {
            StarWarsTheme {
                StarshipDetailScreen("4", modifier = Modifier, viewModel = viewModel)
            }
        }
    }

    @Test
    fun myTest() {
        composeRule.waitUntil(2000) {
            composeRule.onAllNodesWithText("Death Star").fetchSemanticsNodes().size == 1
        }
        composeRule.onAllNodesWithText("DS-1 Orbital Battle Station").fetchSemanticsNodes().isNotEmpty()
        composeRule.onAllNodesWithText("Deep Space Mobile Battlestation").fetchSemanticsNodes().isNotEmpty()
        composeRule.onNodeWithTag(TestTag.detailHeader).assertIsDisplayed()
    }
}