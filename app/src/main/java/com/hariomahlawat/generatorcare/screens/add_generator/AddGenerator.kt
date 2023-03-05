package com.hariomahlawat.generatorcare.screens.add_generator

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.components.AppButton
import com.hariomahlawat.generatorcare.components.AppInputText
import com.hariomahlawat.generatorcare.components.showToast
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.navigation.*
import com.hariomahlawat.generatorcare.screens.home.HomeScreenInner
import com.hariomahlawat.generatorcare.utils.formatDate
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun AddGeneratorScreen(navController: NavController){

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title = "Add new Generator",
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
        AddGenerator(generators = generatorsList,
            onAddGenerator = {generatorViewModel.addGenerator(it)})
    }

}

@Composable
fun AddGenerator(generators: List<Generator>,
                 onAddGenerator: (Generator) -> Unit
){
    var registration_number by remember {mutableStateOf("")}
    var make by remember {mutableStateOf("")}
    var model by remember {mutableStateOf("")}
    var hours_run by remember {mutableStateOf("")}
    var kva_rating by remember {mutableStateOf("")}
    var issueDate by remember {mutableStateOf(LocalDate.now())}
    val dateDialogState = rememberMaterialDialogState()
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd MMM yyyy")
                .format(issueDate)
        }
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
        Divider(thickness = 1.dp)
        //-------------- date picker----------------------------------
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
            Button(onClick = {
                dateDialogState.show()
            }) {
                Text(text = "Pick Issue date")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = formattedDate)

        }
        Divider(thickness = 1.dp, modifier = Modifier.padding(5.dp))

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Ok")
                negativeButton(text = "Cancel")
            }
        ) {
            this.datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
            ){
                issueDate=it
            }
        }

        //--------------------------------------------------------
        AppInputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
            text = hours_run,
            label = "Hours Run",
            onTextChange = {
                hours_run = it
            })
        AppInputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
            text = kva_rating,
            label = "KVA Rating",
            onTextChange = {
                kva_rating = it
            })

        AppButton(text = "Save",
            onClick = {
                if (registration_number.isNotEmpty()
                    && make.isNotEmpty()
                    && model.isNotEmpty()
                    && hours_run.isNotEmpty()
                    && kva_rating.isNotEmpty()
                    && issueDate != LocalDate.now()
                ) {
                    // add to database
                    onAddGenerator(
                        Generator(
                            registration_number=registration_number,
                            make = make,
                            model = model,
                            kva_rating = kva_rating,
                            hours_run = hours_run.toInt(),
                            issueDate = Date.from(issueDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                        )
                    )

                    registration_number = ""
                    make = ""
                    model=""
                    hours_run=""
                    kva_rating=""
                    issueDate=LocalDate.now()
                    showToast(context,"Generator added.")
                }
                else{
                    showToast(context,"Fill all details")
                }
            })





    }
}


