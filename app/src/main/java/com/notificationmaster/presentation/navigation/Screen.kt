package com.notificationmaster.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Bottom Navigation sekmeleri — mockup'a uygun ikonlar.
 * Her tab'ın selected ve unselected ikonu ayrı.
 */
sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    // Geriye uyumluluk — mevcut kodlar "screen.icon" kullanıyor
    val icon: ImageVector get() = selectedIcon

    data object Dashboard : Screen(
        route = "dashboard",
        title = "Dashboard",
        selectedIcon = Icons.Outlined.GridView,
        unselectedIcon = Icons.Outlined.GridView
    )

    data object Rules : Screen(
        route = "rules",
        title = "Rules",
        selectedIcon = Icons.Filled.Security,
        unselectedIcon = Icons.Outlined.Security
    )

    data object History : Screen(
        route = "history",
        title = "History",
        selectedIcon = Icons.Filled.History,
        unselectedIcon = Icons.Outlined.History
    )

    data object Settings : Screen(
        route = "settings",
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
}

/**
 * Alt ekranlar (Bottom Nav dışında).
 */
object SubScreen {
    const val ADD_RULE = "add_rule"
    const val EDIT_RULE = "edit_rule/{ruleId}"
    const val APP_PICKER = "app_picker/{returnRoute}"

    fun editRuleRoute(ruleId: Long) = "edit_rule/$ruleId"
    fun appPickerRoute(returnRoute: String) = "app_picker/$returnRoute"
}
