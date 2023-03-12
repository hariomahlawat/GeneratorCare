package com.hariomahlawat.generatorcare.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.hariomahlawat.generatorcare.R

class MenuItemData {
    fun loadMenuItems():List<MenuItem>{
        return listOf(
            MenuItem(id="home",
                title = "Home", contentDescription = "go to home",
            icon = Icons.Default.Home,
            navigation_address = GeneratorCareScreens.HomeScreen.name),
            MenuItem(
                id = "inventory",
                title = "My Generators",
                contentDescription = "Get My Generators",
                icon = Icons.Default.List,
                navigation_address = GeneratorCareScreens.GeneratorsListScreen.name
            ),
            MenuItem(
                id = "maintenance_Record",
                title = "Maintenance History",
                contentDescription = "Get Maintenance History",
                icon = Icons.Default.Done,
                navigation_address = GeneratorCareScreens.MaintenanceRecordScreen.name
            ),
            MenuItem(
                id = "advisory",
                title = "Maintenance Advisory",
                contentDescription = "Go to advisory screen",
                icon = Icons.Default.Info,
                navigation_address = GeneratorCareScreens.GeneratorAdvisoryScreen.name
            ),
            MenuItem(
                id = "checklist",
                title = "Maintenance Checklist",
                contentDescription = "Go to maintenance checklist screen",
                icon = Icons.Default.CheckCircle,
                navigation_address = GeneratorCareScreens.MaintenanceChecklistScreen.name
            ),



        )
    }
}