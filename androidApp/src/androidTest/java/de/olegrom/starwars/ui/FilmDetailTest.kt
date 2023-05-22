package de.olegrom.starwars.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import de.olegrom.starwars.android.MainActivity
import de.olegrom.starwars.android.theme.StarWarsTheme
import de.olegrom.starwars.android.ui.detail.FilmDetailScreen
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.data.FakeRemoteDataSource
import de.olegrom.starwars.data.remote.dto.FilmDTO
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FilmDetailTest {
    lateinit var film: FilmDTO

    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setUp() {
        runBlocking {
            val movieDetailResponseMocked = FakeRemoteDataSource().fetchMovieDetail("4")
            film = movieDetailResponseMocked
        }
        composeRule.setContent {
            StarWarsTheme() {
                FilmDetailScreen("4", modifier = Modifier)
            }
        }
    }

    @Test
    fun myTest() {
        composeRule.onNodeWithTag(TestTag.appBarTitle).assertIsDisplayed()
        composeRule.onNodeWithTag(TestTag.appBarTitle).assertIsDisplayed()
    }
}