package com.hariomahlawat.generatorcare.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List

class MenuItemData {
    fun loadMenuItems():List<MenuItem>{
        return listOf(
            MenuItem(id="home",
                title = "Home", contentDescription = "go to home",
            icon = Icons.Default.Home,
            navigation_address = GeneratorCareScreens.HomeScreen.name),
            MenuItem(
                id = "maintenance_Record",
                title = "Maintenance History",
                contentDescription = "Get Maintenance History",
                icon = Icons.Default.List,
                navigation_address = GeneratorCareScreens.MaintenanceRecordScreen.name
            ),
            MenuItem(
                id = "advisory",
                title = "Adviisory",
                contentDescription = "Go to advisory screen",
                icon = Icons.Default.Info,
                navigation_address = GeneratorCareScreens.AdvisoryScreen.name
            )
        )
    }
}