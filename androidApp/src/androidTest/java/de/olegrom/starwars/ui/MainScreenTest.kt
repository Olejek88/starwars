package de.olegrom.starwars.ui

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import de.olegrom.starwars.android.MainActivity
import de.olegrom.starwars.android.navigation.root.RootNavigationGraph
import de.olegrom.starwars.android.theme.StarWarsTheme
import de.olegrom.starwars.android.utils.TestTag
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        runBlocking {
        }
        setContent()
    }

    private fun setContent() {
        composeRule.activity.setContent {
            StarWarsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigationGraph(navHostController = rememberNavController())
                }
            }
        }
    }

    @Test
    fun testMainScreen() {
        composeRule.onNodeWithTag(TestTag.appBarTitle).assertIsDisplayed()
        composeRule.onNodeWithText("Films").assertIsDisplayed()
        composeRule.onAllNodesWithTag(TestTag.listElement).fetchSemanticsNodes().isNotEmpty()
        composeRule.onNodeWithTag(TestTag.menuShips).performClick()
        composeRule.onNodeWithText("Starships").assertIsDisplayed()
        composeRule.onAllNodesWithTag(TestTag.listElement).fetchSemanticsNodes().isNotEmpty()
        composeRule.onNodeWithTag(TestTag.menuPersons).performClick()
        composeRule.onNodeWithText("Persons").assertIsDisplayed()
        composeRule.onAllNodesWithTag(TestTag.listElement).fetchSemanticsNodes().isNotEmpty()
        composeRule.onNodeWithTag(TestTag.menuPlanets).performClick()
        composeRule.onNodeWithText("Planets").assertIsDisplayed()
        composeRule.onAllNodesWithTag(TestTag.listElement).fetchSemanticsNodes().isNotEmpty()

        // details
        composeRule.onNodeWithTag(TestTag.menuFilms).performClick()
        composeRule.onAllNodesWithTag(TestTag.listElement).fetchSemanticsNodes().isNotEmpty()
        composeRule.onAllNodesWithTag(TestTag.listElement)[0].performClick()
    }
}