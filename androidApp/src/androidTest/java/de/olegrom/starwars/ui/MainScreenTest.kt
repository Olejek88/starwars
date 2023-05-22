package de.olegrom.starwars.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import de.olegrom.starwars.data.FakeRemoteDataSource
import de.olegrom.starwars.android.MainActivity
import de.olegrom.starwars.android.navigation.root.RootNavigationGraph
import de.olegrom.starwars.android.theme.StarWarsTheme
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.data.remote.dto.FilmDTO
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    lateinit var movie: FilmDTO
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
        composeRule.setContent {
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
    }
}