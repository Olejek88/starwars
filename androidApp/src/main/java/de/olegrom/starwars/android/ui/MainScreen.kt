package de.olegrom.starwars.android.ui

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import de.olegrom.starwars.android.navigation.main.MainGraph
import de.olegrom.starwars.android.navigation.main.Screen
import de.olegrom.starwars.android.utils.TestTag
import de.olegrom.starwars.presentation.home.TopAppBarViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(navController: NavHostController = rememberAnimatedNavController(),
               topAppBarViewModel: TopAppBarViewModel = getViewModel()) {
    var canPop by remember { mutableStateOf(false) }
    val title by topAppBarViewModel.title.collectAsState()
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, _, _ ->
            canPop = false
            controller.currentBackStackEntry?.destination?.route?.let {
                canPop = (it != Screen.Planets.route)
                        && (it != Screen.Films.route)
                        && (it != Screen.Persons.route)
                        && (it != Screen.Starships.route)

            }
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().testTag(TestTag.appBarTitle),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    navigationIcon = {
                        if (canPop) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    actions = {
                        // Set right padding as size = IconButtonTokens.StateLayerSize in order
                        // to center the app bar title
                        if (canPop) {
                            Box(modifier = Modifier.size(24.dp))
                        }
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        scrolledContainerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                )
            },
            content = { padding ->
                val modifier = Modifier
                    .padding(padding)
                    .padding(start = 10.dp, end = 10.dp)
                    .padding(top = 0.dp)
                MainGraph(
                    navController = navController,
                    modifier = modifier,
                )
            },
            bottomBar = {
                BottomNavigationMenu(navController = navController)
            },
        )
    }
}

fun getLabelByRoute(route: String): String {
    return when (route) {
        Screen.Films.route -> "Films"
        Screen.Starships.route -> "Starships"
        Screen.Persons.route -> "Persons"
        Screen.Planets.route -> "Planets"
        else -> "Star Wars"
    }
}
