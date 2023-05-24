package de.olegrom.starwars.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import de.olegrom.starwars.android.MainActivity
import de.olegrom.starwars.android.theme.StarWarsTheme
import de.olegrom.starwars.android.ui.detail.PersonDetailScreen
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.data.remote.service.FakeKtorService
import de.olegrom.starwars.data.repository.ImplRepository
import de.olegrom.starwars.domain.usecase.detail.GetPersonUseCase
import de.olegrom.starwars.presentation.detail.PersonDetailsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PersonDetailTest {
    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setUp() {
        val viewModel = PersonDetailsViewModel(GetPersonUseCase(ImplRepository(FakeKtorService())))
        composeRule.activity.setContent {
            StarWarsTheme {
                PersonDetailScreen("4", modifier = Modifier, viewModel = viewModel)
            }
        }
    }

    @Test
    fun personDetailsTest() {
        composeRule.waitUntil(5000) {
            composeRule.onAllNodesWithText("19 BBY").fetchSemanticsNodes().size == 1
        }
        composeRule.onAllNodesWithText("Blond").fetchSemanticsNodes().isNotEmpty()
        composeRule.onNodeWithTag(TestTag.detailParameters).assertIsDisplayed()
    }
}