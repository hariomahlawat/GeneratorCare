package com.hariomahlawat.generatorcare.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.GeneratorCareApp
import com.hariomahlawat.generatorcare.components.AppButton
import com.hariomahlawat.generatorcare.components.GeneratorCard
import com.hariomahlawat.generatorcare.data.getGeneratorLogo
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.navigation.*
import com.hariomahlawat.generatorcare.screens.add_generator.GeneratorViewModel
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
                items = MenuItemData().loadMenuItems(),
                onItemClick = {
                    navController.navigate(route = it.navigation_address)
                }
            )
        }
    ) {
        val generatorViewModel = hiltViewModel<GeneratorViewModel>()
        var generatorsList = generatorViewModel.generatorList.collectAsState().value
        
        InventoryCard(navController,generatorsList)
    }

}
@Composable
fun InventoryCard(navController:NavController,generators: List<Generator>){
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
    ) {
        Text(
            text = "My Generators (Total - ${generators.count()})",
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp))

        Card(
            shape = RoundedCornerShape(4.dp),
            //elevation = 9.dp,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                //.padding(8.dp)
        ) {
            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)

                ) {
                LazyRow(modifier = Modifier.padding(4.dp)){
                    items(generators){generator ->
                        GeneratorCard(navController = navController, generator = generator, image_id = getGeneratorLogo(generator.make))
                    }
                }

                Row(horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()) {
                    //View all generators
                    AppButton(text = "View All", onClick = {
                        navController.navigate(GeneratorCareScreens.GeneratorsListScreen.name)
                    })
                    //add a new generator
                    AppButton(text = "Add New Generator", onClick = {
                        navController.navigate(GeneratorCareScreens.AddGeneratorScreen.name)
                    })

                }

            }

        }

    }

}






