package com.hariomahlawat.generatorcare.screens.logbook

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.data.getGeneratorLogo
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.navigation.AppBar
import com.hariomahlawat.generatorcare.navigation.DrawerBody
import com.hariomahlawat.generatorcare.navigation.DrawerHeader
import com.hariomahlawat.generatorcare.navigation.MenuItemData
import com.hariomahlawat.generatorcare.screens.add_generator.GeneratorViewModel
import com.hariomahlawat.generatorcare.utils.formatDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LogbookScreen(navController: NavController, generatorId: String?){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title="eLogbook",
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
        var generator: Generator? = null
        if (generatorId!=null){
            generatorViewModel.getGeneratorById(generatorId)
            generator = generatorViewModel.generator
        }


        if (generator!=null){
            GeneratorInfo(generator = generator)
        }
        else{
            NoGeneratorFound()
        }


    }
}

@Composable
fun GeneratorInfo( generator: Generator){
    //Text(text = "Logbook screen with generator Id "+generator.id)
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    )
    {
        val imageModifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
        Image(
            painter = painterResource(id = getGeneratorLogo(generator.make)),
            contentDescription = "Generator Make Logo",
            contentScale = ContentScale.FillWidth,
            modifier = imageModifier
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Divider(thickness = 1.dp, color = Color.DarkGray)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = "Generator Info Card",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary
        )
        Text(text = "Regd No : "+generator.registration_number)
        Text(text = "Model No : "+generator.model)
        Text(text = "Issue Date : "+ generator.issueDate?.let { formatDate(it) })
        Text(text = "KVA Rating : "+generator.kva_rating)
        Text(text = "Hours Run : "+generator.hours_run)

        Spacer(modifier = Modifier.padding(5.dp))
        Divider(thickness = 1.dp, color = Color.DarkGray)
        Spacer(modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun NoGeneratorFound(){
    Text(text = "Fetching Generator Details")
}