package com.notificationmaster.presentation.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.notificationmaster.presentation.dashboard.DashboardScreen
import com.notificationmaster.presentation.history.HistoryScreen
import com.notificationmaster.presentation.rules.AddRuleScreen
import com.notificationmaster.presentation.rules.RulesScreen
import com.notificationmaster.presentation.settings.SettingsScreen
import com.notificationmaster.ui.theme.md_theme_dark_background
import com.notificationmaster.ui.theme.CardBorder

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomScreens = listOf(
        Screen.Dashboard,
        Screen.Rules,
        Screen.History,
        Screen.Settings
    )

    // AddRule / EditRule ekranlarında bottom bar'ı gizle
    val showBottomBar = currentDestination?.route in bottomScreens.map { it.route }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (showBottomBar) {
                CustomBottomBar(
                    screens = bottomScreens,
                    currentDestination = currentDestination,
                    onTabSelected = { screen ->
                        navController.navigate(screen.route) {
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen()
            }

            composable(Screen.Rules.route) {
                RulesScreen(
                    onAddRule = { navController.navigate(SubScreen.ADD_RULE) },
                    onEditRule = { ruleId ->
                        navController.navigate(SubScreen.editRuleRoute(ruleId))
                    }
                )
            }

            composable(Screen.History.route) {
                HistoryScreen()
            }

            composable(Screen.Settings.route) {
                SettingsScreen()
            }

            composable(SubScreen.ADD_RULE) {
                AddRuleScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = SubScreen.EDIT_RULE,
                arguments = listOf(navArgument("ruleId") { type = NavType.LongType })
            ) {
                AddRuleScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

/**
 * Mockup'a uygun custom bottom navigation bar.
 * - Koyu arka plan (#101322) + üst border (slate-800)
 * - Seçili tab: mavi ikon + mavi label
 * - Seçilmeyen tab: gri ikon + gri label
 */
@Composable
private fun CustomBottomBar(
    screens: List<Screen>,
    currentDestination: androidx.navigation.NavDestination?,
    onTabSelected: (Screen) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(md_theme_dark_background)
    ) {
        // Üst ince border çizgisi
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(CardBorder)
                .align(Alignment.TopCenter)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true

                BottomNavItem(
                    screen = screen,
                    isSelected = isSelected,
                    onClick = { onTabSelected(screen) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    screen: Screen,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        },
        animationSpec = spring(stiffness = Spring.StiffnessHigh),
        label = "iconColor"
    )

    val labelColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        },
        animationSpec = spring(stiffness = Spring.StiffnessHigh),
        label = "labelColor"
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Seçili göstergesi — ikon arkasındaki pill shape
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isSelected) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                    } else {
                        Color.Transparent
                    }
                )
                .padding(horizontal = 20.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isSelected) screen.selectedIcon else screen.unselectedIcon,
                contentDescription = screen.title,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = screen.title,
            color = labelColor,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1
        )
    }
}
