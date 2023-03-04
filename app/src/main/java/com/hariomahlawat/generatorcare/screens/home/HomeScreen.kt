package com.hariomahlawat.generatorcare.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.GeneratorCareApp
import com.hariomahlawat.generatorcare.components.AppButton
import com.hariomahlawat.generatorcare.navigation.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home,
                        navigation_address = GeneratorCareScreens.HomeScreen.name

                    ),
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
                    ),

                    ),
                onItemClick = {
                    navController.navigate(route = it.navigation_address)
                }
            )
        }
    ) {
        HomeScreenInner(navController = navController)
    }

}
@Composable
fun HomeScreenInner(navController:NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = "Home Screen")
        AppButton(text = "Add New Generator", onClick = {
            navController.navigate(GeneratorCareScreens.AddGeneratorScreen.name)
        })
    }
}


