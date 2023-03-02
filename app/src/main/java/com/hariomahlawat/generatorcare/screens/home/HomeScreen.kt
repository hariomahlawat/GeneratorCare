package com.hariomahlawat.generatorcare.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.components.AppButton
import com.hariomahlawat.generatorcare.navigation.GeneratorCareScreens

@Composable
fun HomeScreen(navController: NavController){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home Screen")
        AppButton(text = "Add New Generator", onClick = {
            navController.navigate(GeneratorCareScreens.AddGeneratorScreen.name)
        })
    }
}