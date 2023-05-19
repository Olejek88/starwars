package de.olegrom.starwars.android.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import de.olegrom.starwars.android.R
import de.olegrom.starwars.android.navigation.main.Screen

@Composable
fun BottomNavigationMenu(navController: NavController) {
    val items = getNavigationItems()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentTab = remember { mutableStateOf(Screen.Films.route) }
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEach { item ->
            val selected = item.screen_route == currentTab.value
            NavigationBarItem(
                selected = selected,
                icon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun getNavigationItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            stringResource(R.string.films),
            R.drawable.sv_film,
            Screen.Films.route
        ),
        BottomNavItem(
            stringResource(R.string.starships),
            R.drawable.sv_ship,
            Screen.Starships.route
        ),
        BottomNavItem(
            stringResource(R.string.persons),
            R.drawable.sv_jedy,
            Screen.Peoples.route
        ),
        BottomNavItem(
            stringResource(R.string.planets),
            R.drawable.sv_planet,
            Screen.Planets.route
        ),
    );
}

data class BottomNavItem(val title: String, val icon: Int, val screen_route: String)