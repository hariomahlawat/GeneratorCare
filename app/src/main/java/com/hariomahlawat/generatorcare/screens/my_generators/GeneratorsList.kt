package com.hariomahlawat.generatorcare.screens.my_generators

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.navigation.AppBar
import com.hariomahlawat.generatorcare.navigation.DrawerBody
import com.hariomahlawat.generatorcare.navigation.DrawerHeader
import com.hariomahlawat.generatorcare.navigation.MenuItemData
import com.hariomahlawat.generatorcare.screens.add_generator.GeneratorViewModel
import com.hariomahlawat.generatorcare.screens.maintenance_record.MaintenanceRecordScreenInner
import com.hariomahlawat.generatorcare.utils.formatDate
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

@Composable
fun GeneratorRow(
    modifier: Modifier = Modifier,
    generator: Generator,
    onGeneratorClicked: (Generator) -> Unit) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 13.dp, bottomStart = 13.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp) {
        Column(modifier
            .clickable {onGeneratorClicked(generator) }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = "Regd No: " +generator.registration_number, style = MaterialTheme.typography.subtitle2)
            Text(text = "Make: " +generator.make, style = MaterialTheme.typography.subtitle1)
            Text(text = "Model: " +generator.model, style = MaterialTheme.typography.subtitle1)
            Text(text = "KVA Rating: " +generator.kva_rating, style = MaterialTheme.typography.subtitle1)
            Text(text = "Hours run: " +generator.hours_run.toString(), style = MaterialTheme.typography.subtitle1)
            Text(text = "Issue Date: " + generator.issueDate?.let { formatDate(it.time) },
                style = MaterialTheme.typography.subtitle1)
            Text(text = "Data entry Date: " + formatDate(generator.entryDate.time),
                style = MaterialTheme.typography.caption)
        }
    }
}