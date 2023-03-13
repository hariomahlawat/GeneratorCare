package com.hariomahlawat.generatorcare.screens.add_generator

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.hariomahlawat.generatorcare.data.getGeneratorMakeList
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.navigation.*
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
    val scrollState = rememberScrollState()
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            val generatorViewModel = hiltViewModel<GeneratorViewModel>()

            var generatorsList = generatorViewModel.generatorList.collectAsState().value
            AddGenerator(generators = generatorsList,
                onAddGenerator = {generatorViewModel.addGenerator(it)})
        }

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddGenerator(generators: List<Generator>,
                 onAddGenerator: (Generator) -> Unit
){
    //options for generator make/oem
    val options = getGeneratorMakeList()

    var registration_number by remember {mutableStateOf("")}
    var make by remember {mutableStateOf("")}
    var model by remember {mutableStateOf("")}
    var hours_run by remember {mutableStateOf("")}
    var kva_rating by remember {mutableStateOf("")}


    val context = LocalContext.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        AppInputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
            text = registration_number,
            label = "Registration Number",
            onTextChange = {
                registration_number = it
            } )
        //--- to select make from dropdown list
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Make / OEM") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        make = selectedOptionText
        //--------------------------------------------------------------------------------

        AppInputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
            text = model,
            label = "Model",
            onTextChange = {
                model = it
            })

        //-------------- date picker - Issue Date ----------------------------------
        Divider(thickness = 1.dp)

        var issueDate by remember {mutableStateOf(LocalDate.now())}
        val dateDialogState = rememberMaterialDialogState()
        val formattedDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd MMM yyyy")
                    .format(issueDate)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)) {
            Button(onClick = {
                dateDialogState.show()
            }) {
                Text(text = "Pick Issue date")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            if(issueDate==LocalDate.now()){
                Text(text = "- - / - - - / - - - -")
            }else{
                Text(text = formattedDate)
            }

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
        // ---------- date picker ends-------------------------------------------


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
        
        //----------------------------------------------------------------
        // ------- last maintenance done----------------------
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Divider(thickness = 13.dp)
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "Please provide the details of the last maintenance done. This is required to schedule your next maintenance.",
            modifier = Modifier.padding(horizontal = 25.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        //-------------- date picker - Last Engine Oil Change Date ----------------------------------
        Divider(thickness = 1.dp)

        var engineOilChangeDate by remember {mutableStateOf(LocalDate.now())}
        val engineOilChangeDateDialogState = rememberMaterialDialogState()
        val formattedEngineOilChangeDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd MMM yyyy")
                    .format(engineOilChangeDate)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)) {
            Button(onClick = {
                engineOilChangeDateDialogState.show()
            }) {
                Text(text = "Engine oil change")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            if(engineOilChangeDate==LocalDate.now()){
                Text(text = "- - / - - - / - - - -")
            }else{
                Text(text = formattedEngineOilChangeDate)
            }

        }
        Divider(thickness = 1.dp, modifier = Modifier.padding(5.dp))

        MaterialDialog(
            dialogState = engineOilChangeDateDialogState,
            buttons = {
                positiveButton(text = "Ok")
                negativeButton(text = "Cancel")
            }
        ) {
            this.datepicker(
                initialDate = LocalDate.now(),
                title = "Engine oil change date",
            ){
                engineOilChangeDate=it
            }
        }
        // ---------- date picker ends-------------------------------------------

        //-------------- date picker - Last Engine Oil Filter Change Date ----------------------------------
        Divider(thickness = 1.dp)

        var engineOilFilterChangeDate by remember {mutableStateOf(LocalDate.now())}
        val engineOilFilterChangeDateDialogState = rememberMaterialDialogState()
        val formattedEngineOilFilterChangeDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd MMM yyyy")
                    .format(engineOilFilterChangeDate)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)) {
            Button(onClick = {
                engineOilFilterChangeDateDialogState.show()
            }) {
                Text(text = "Oil filter change")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            if(engineOilFilterChangeDate==LocalDate.now()){
                Text(text = "- - / - - - / - - - -")
            }else{
                Text(text = formattedEngineOilFilterChangeDate)
            }

        }
        Divider(thickness = 1.dp, modifier = Modifier.padding(5.dp))

        MaterialDialog(
            dialogState = engineOilFilterChangeDateDialogState,
            buttons = {
                positiveButton(text = "Ok")
                negativeButton(text = "Cancel")
            }
        ) {
            this.datepicker(
                initialDate = LocalDate.now(),
                title = "Engine oil filter change date",
            ){
                engineOilFilterChangeDate=it
            }
        }
        // ---------- date picker ends-------------------------------------------

        //-------------- date picker - Last fuel filter change Date ----------------------------------
        Divider(thickness = 1.dp)

        var fuelFilterChangeDate by remember {mutableStateOf(LocalDate.now())}
        val fuelFilterChangeDateDialogState = rememberMaterialDialogState()
        val formattedFuelFilterChangeDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd MMM yyyy")
                    .format(fuelFilterChangeDate)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)) {
            Button(onClick = {
                fuelFilterChangeDateDialogState.show()
            }) {
                Text(text = "Fuel filter change")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            if(fuelFilterChangeDate==LocalDate.now()){
                Text(text = "- - / - - - / - - - -")
            }else{
                Text(text = formattedFuelFilterChangeDate)
            }

        }
        Divider(thickness = 1.dp, modifier = Modifier.padding(5.dp))

        MaterialDialog(
            dialogState = fuelFilterChangeDateDialogState,
            buttons = {
                positiveButton(text = "Ok")
                negativeButton(text = "Cancel")
            }
        ) {
            this.datepicker(
                initialDate = LocalDate.now(),
                title = "Fuel filter change date",
            ){
                fuelFilterChangeDate=it
            }
        }
        // ---------- date picker ends-------------------------------------------

        //-------------- date picker - Last air filter Date ----------------------------------
        Divider(thickness = 1.dp)

        var airFilterChangeDate by remember {mutableStateOf(LocalDate.now())}
        val airFilterChangeDateDialogState = rememberMaterialDialogState()
        val formattedAirFilterChangeDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd MMM yyyy")
                    .format(airFilterChangeDate)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)) {
            Button(onClick = {
                airFilterChangeDateDialogState.show()
            }) {
                Text(text = "Air filter change")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            if(airFilterChangeDate==LocalDate.now()){
                Text(text = "- - / - - - / - - - -")
            }else{
                Text(text = formattedAirFilterChangeDate)
            }

        }
        Divider(thickness = 1.dp, modifier = Modifier.padding(5.dp))

        MaterialDialog(
            dialogState = airFilterChangeDateDialogState,
            buttons = {
                positiveButton(text = "Ok")
                negativeButton(text = "Cancel")
            }
        ) {
            this.datepicker(
                initialDate = LocalDate.now(),
                title = "Air filter change date",
            ){
                airFilterChangeDate=it
            }
        }
        // ---------- date picker ends-------------------------------------------

        //-------------- date picker - Fuel Tank Cleaning Date ----------------------------------
        Divider(thickness = 1.dp)

        var fuelTankCleaningDate by remember {mutableStateOf(LocalDate.now())}
        val fuelTankCleaningDateDialogState = rememberMaterialDialogState()
        val formattedFuelTankCleaningDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd MMM yyyy")
                    .format(fuelTankCleaningDate)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)) {
            Button(onClick = {
                fuelTankCleaningDateDialogState.show()
            }) {
                Text(text = "Fuel tank Cleaning")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            if(fuelTankCleaningDate==LocalDate.now()){
                Text(text = "- - / - - - / - - - -")
            }else{
                Text(text = formattedFuelTankCleaningDate)
            }

        }
        Divider(thickness = 1.dp, modifier = Modifier.padding(5.dp))

        MaterialDialog(
            dialogState = fuelTankCleaningDateDialogState,
            buttons = {
                positiveButton(text = "Ok")
                negativeButton(text = "Cancel")
            }
        ) {
            this.datepicker(
                initialDate = LocalDate.now(),
                title = "Fuel tank clean date",
            ){
                fuelTankCleaningDate=it
            }
        }
        // ---------- date picker ends-------------------------------------------


        //========== Check box for confirmation before saving ============================
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        val checkedState = remember { mutableStateOf(false) }
        Row {
            Checkbox(
                checked = checkedState.value,
                modifier = Modifier.padding(16.dp),
                onCheckedChange = { checkedState.value = it },
            )
            Text(text = "I have checked the details and want to save the form.", modifier = Modifier.padding(25.dp))
        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))




        
        //--------Save button - to save the form in database
        AppButton(text = "Save",
            onClick = {
                if (
                    (registration_number.isNotEmpty()
                      && make=="Select OEM"
                      && model.isNotEmpty()
                      && hours_run.isNotEmpty()
                      && kva_rating.isNotEmpty()
                      && issueDate != LocalDate.now()
                      )
                    && checkedState.value
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
                    checkedState.value=false
                    showToast(context,"Generator added.")
                }
                else{
                    showToast(context,"Fill all details")
                }
            })





    }
}


