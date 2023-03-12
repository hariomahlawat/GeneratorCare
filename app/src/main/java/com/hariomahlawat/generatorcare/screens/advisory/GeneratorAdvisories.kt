package com.hariomahlawat.generatorcare.screens.advisory

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.data.getDosFromMaintAdvisory
import com.hariomahlawat.generatorcare.data.getStartingProcedureFromMaintAdvisory
import com.hariomahlawat.generatorcare.data.getStoppingProcedureFromMaintAdvisory
import com.hariomahlawat.generatorcare.navigation.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GeneratorAdvisoryScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title="Maintenance Advisory",
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
        Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(5.dp)

        ) {
            ShowAdvisoryList(title = "Starting Procedure", points = getStartingProcedureFromMaintAdvisory())
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            ShowAdvisoryList(title = "Stopping Procedure", points = getStoppingProcedureFromMaintAdvisory())
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            ShowAdvisoryList(title = "DOs ", points = getDosFromMaintAdvisory())
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
        }

    }

}

@Composable
fun AdvisoryScreenInner(navController: NavController){

}

@Composable
fun ShowAdvisoryList(title:String, points:List<String>){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant)
                .padding(5.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        var i:Int = 1
        for (point in points){
            ShowPoint(point = i.toString() +".    "+ point)
            i=i+1
        }
    }
}

@Composable
fun ShowPoint(point:String){
    Text(
        text = point,
        style = MaterialTheme.typography.subtitle1
    )
    Spacer(modifier = Modifier.padding(vertical = 3.dp))
}