package com.hariomahlawat.generatorcare.screens.my_generators

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.R
import com.hariomahlawat.generatorcare.data.getGeneratorLogo
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.navigation.*
import com.hariomahlawat.generatorcare.screens.add_generator.GeneratorViewModel
import com.hariomahlawat.generatorcare.utils.formatDate
import kotlinx.coroutines.launch
import kotlin.math.round

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
        GeneratorsListScreenInner(
            navController=navController,
            generatorsList
        )
    }
}

@Composable
fun GeneratorsListScreenInner(navController: NavController,
                              generators: List<Generator>){

    LazyColumn(
        modifier = Modifier.padding(top = 10.dp)
    ){

        items(generators){generator ->
            GeneratorCard(navController=navController,generator = generator, image_id = getGeneratorLogo(generator.make))

        }
    }
}

@Composable
fun GeneratorCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    generator: Generator,
    image_id:Int
    ){
    Card(
        shape = RoundedCornerShape(14.dp),
        elevation = 9.dp,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate(GeneratorCareScreens.LogbookScreen.name+"/"+generator.id)
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(5.dp),
        ) {
            Image(painter = painterResource(id = image_id), contentDescription = null)

        Column(
                modifier = Modifier.padding(start = 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            )
            {

                Text(text = "Model: " +generator.model, style = MaterialTheme.typography.h6)
                Text(text = "KVA Rating: " +generator.kva_rating, style = MaterialTheme.typography.h6)
                Text(text = "Regd No: " +generator.registration_number, style = MaterialTheme.typography.h6)
                Text(text = "Hours run: " +generator.hours_run.toString(), style = MaterialTheme.typography.h6)
                Text(text = "Issue Date: " + generator.issueDate?.let { formatDate(it) }, style = MaterialTheme.typography.h6)
                Divider(thickness = 2.dp)
                Row(modifier=Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Digital logbook"
                        )

                    }
                    Text(text = "Digital logbook")
                }

            }

        }
        }
    }
