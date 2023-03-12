package com.hariomahlawat.generatorcare.screens.checklist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.components.ShowAdvisoryList
import com.hariomahlawat.generatorcare.data.*
import com.hariomahlawat.generatorcare.navigation.AppBar
import com.hariomahlawat.generatorcare.navigation.DrawerBody
import com.hariomahlawat.generatorcare.navigation.DrawerHeader
import com.hariomahlawat.generatorcare.navigation.MenuItemData
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MaintenanceChecklistScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title="Maintenance Checklist",
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
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            MaintenanceCheckListView()
        }
    }
}

@Composable
fun MaintenanceCheckListView(){
    val dailyMaint:List<String> = getDailyMaintenanceChecklist()
    val weeklyMaint:List<String> = getWeeklyMaintenanceChecklist()
    val monthlyMaint:List<String> = getMonthlyMaintenanceChecklist()
    val sixMonthlyMaint:List<String> = getSixMonthlyMaintenanceChecklist()
    val asNeededMaint:List<String> = getAsNeededMaintenanceChecklist()

    Spacer(modifier = Modifier.padding(vertical = 5.dp))
    ShowAdvisoryList(title = "Daily Maintenance Checklist", points = dailyMaint)
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    ShowAdvisoryList(title = "Weekly Maintenance Checklist", points = weeklyMaint)
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    ShowAdvisoryList(title = "Monthly Maintenance Checklist", points = monthlyMaint)
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    ShowAdvisoryList(title = "Six Monthly Maintenance Checklist", points = sixMonthlyMaint)
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    ShowAdvisoryList(title = "As Needed Maintenance Checklist", points = asNeededMaint)
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
}