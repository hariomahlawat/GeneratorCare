package com.hariomahlawat.generatorcare.screens.my_generators

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.navigation.AppBar
import com.hariomahlawat.generatorcare.navigation.DrawerBody
import com.hariomahlawat.generatorcare.navigation.DrawerHeader
import com.hariomahlawat.generatorcare.navigation.MenuItemData
import com.hariomahlawat.generatorcare.screens.add_generator.GeneratorRow
import com.hariomahlawat.generatorcare.screens.add_generator.GeneratorViewModel
import com.hariomahlawat.generatorcare.screens.maintenance_record.MaintenanceRecordScreenInner
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GeneratorsListScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title="My Generators",
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
        val generatorViewModel = hiltViewModel<GeneratorViewModel>()

        var generatorsList = generatorViewModel.generatorList.collectAsState().value
        GeneratorsListScreenInner(navController=navController,
            generatorsList,
            onRemoveGenerator = {generatorViewModel.removeGenerator(it)}
        )
    }
}

@Composable
fun GeneratorsListScreenInner(navController: NavController,
                              generators: List<Generator>,
                              onRemoveGenerator: (Generator) -> Unit){
    LazyColumn{
        items(generators){generator ->
            GeneratorRow(generator = generator,
                onGeneratorClicked = {onRemoveGenerator(it)})
        }
    }
}