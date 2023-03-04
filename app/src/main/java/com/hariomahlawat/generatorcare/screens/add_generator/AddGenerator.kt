package com.hariomahlawat.generatorcare.screens.add_generator

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun AddGeneratorScreen(navController: NavController){

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
        val generatorViewModel = hiltViewModel<GeneratorViewModel>()

        var generatorsList = generatorViewModel.generatorList.collectAsState().value
        AddGenerator(generators = generatorsList,
            onAddGenerator = {generatorViewModel.addGenerator(it)},
            onRemoveGenerator = {generatorViewModel.removeGenerator(it)})
    }

}

@Composable
fun AddGenerator(generators: List<Generator>,
                 onAddGenerator: (Generator) -> Unit,
                 onRemoveGenerator: (Generator) -> Unit
){
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
                    onAddGenerator(
                        Generator(
                            registration_number=registration_number,
                            make = make,
                            model = model
                        )
                    )

                    registration_number = ""
                    make = ""
                    showToast(context,"Generator added.")
                }
                else{
                    showToast(context,"Fill all details")
                }
            })


        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn{
            items(generators){generator ->
                GeneratorRow(generator = generator,
                    onGeneratorClicked = {onRemoveGenerator(it)})
            }
        }


    }
}

@Composable
fun GeneratorRow(
    modifier: Modifier = Modifier,
    generator: Generator,
    onGeneratorClicked: (Generator) -> Unit) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp) {
        Column(modifier
            .clickable {onGeneratorClicked(generator) }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = generator.registration_number,
                style = MaterialTheme.typography.subtitle2)
            Text(text = generator.make, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(generator.entryDate.time),
                style = MaterialTheme.typography.caption)
        }
    }
}
