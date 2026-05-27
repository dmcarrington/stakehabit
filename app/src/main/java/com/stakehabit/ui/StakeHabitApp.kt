package com.stakehabit.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.stakehabit.ui.habits.HabitsScreen
import com.stakehabit.ui.settings.SettingsScreen
import com.stakehabit.ui.staking.StakingScreen
import com.stakehabit.ui.stats.StatsScreen

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Habits : BottomNavItem("habits", "Habits", Icons.Filled.Favorite, Icons.Outlined.Favorite)
    data object Staking : BottomNavItem("staking", "Staking", Icons.Filled.Add, Icons.Outlined.Add)
    data object Stats : BottomNavItem("stats", "Stats", Icons.Filled.BarChart, Icons.Outlined.BarChart)
    data object Settings : BottomNavItem("settings", "Settings", Icons.Filled.Settings, Icons.Outlined.Settings)
}

@Composable
fun StakeHabitApp() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Habits,
        BottomNavItem.Staking,
        BottomNavItem.Stats,
        BottomNavItem.Settings
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (selected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) },
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Habits.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Habits.route) { HabitsScreen() }
            composable(BottomNavItem.Staking.route) { StakingScreen() }
            composable(BottomNavItem.Stats.route) { StatsScreen() }
            composable(BottomNavItem.Settings.route) { SettingsScreen() }
        }
    }
}
