package com.hariomahlawat.generatorcare.screens.add_generator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.components.AppButton
import com.hariomahlawat.generatorcare.components.AppInputText
import com.hariomahlawat.generatorcare.components.showToast

@Composable
fun AddGeneratorScreen(navController: NavController, ){

    var registration_number by remember {
        mutableStateOf("")
    }
    var make by remember {
        mutableStateOf("")
    }
    var model by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current


    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        AppInputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
            text = registration_number,
            label = "Registration Number",
            onTextChange = {
                registration_number = it
            } )

        AppInputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
            text = make,
            label = "Make",
            onTextChange = {
                make = it
            })

        AppInputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
            text = model,
            label = "Model",
            onTextChange = {
                model = it
            })

        AppButton(text = "Save",
            onClick = {
                if (registration_number.isNotEmpty() && make.isNotEmpty()) {
                    // add to database
                    registration_number = ""
                    make = ""
                    showToast(context,"Generator added.")
                }
                else{
                    showToast(context,"Fill all details")
                }
            })

    }
}