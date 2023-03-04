package com.hariomahlawat.generatorcare.screens.advisory

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.navigation.*
import com.hariomahlawat.generatorcare.screens.home.HomeScreenInner
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GeneratorAdvisoryScreen(navController: NavController){
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
                items = MenuItemData().loadMenuItems(),
                onItemClick = {
                    navController.navigate(route = it.navigation_address)
                }
            )
        }
    ) {
        AdvisoryScreenInner(navController = navController)
    }

}

@Composable
fun AdvisoryScreenInner(navController: NavController){

}