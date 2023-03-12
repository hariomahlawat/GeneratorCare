package com.hariomahlawat.generatorcare.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.model.CommonFault
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.navigation.GeneratorCareScreens

@Composable
fun GeneratorCareLogo(modifier: Modifier = Modifier) {
    Text(text = "Generator Care",
        modifier = modifier.padding(bottom = 16.dp),
        style = MaterialTheme.typography.h4,
        color = Color.Red.copy(alpha = 0.5f))
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG)
        .show()
}

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier) {
        Text(text)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent),
        maxLines = maxLine,
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}

// generator card for Home Screen
@Composable
fun GeneratorCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    generator: Generator,
    image_id:Int
){
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 9.dp,
        //backgroundColor = MaterialTheme.colors.background,
        //contentColor = MaterialTheme.colors.onBackground,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                navController.navigate(GeneratorCareScreens.LogbookScreen.name + "/" + generator.id)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Spacer(modifier = modifier.padding(vertical = 5.dp))
            Image(painter = painterResource(id = image_id), contentDescription = null)
            Spacer(modifier = modifier.padding(vertical = 5.dp))
            Text(text = "Regd No: " +generator.registration_number, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = modifier.padding(vertical = 2.dp))
            Text(text = "Model: " +generator.model, style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = modifier.padding(vertical = 2.dp))
            Text(text = "KVA Rating: " +generator.kva_rating, style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = modifier.padding(vertical = 2.dp))
            Text(text = "Hours run: " +generator.hours_run.toString(), style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = modifier.padding(vertical = 2.dp))
        }
    }
}

@Composable
fun CommonFaultCard(commonFault:CommonFault){
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = 9.dp,
        //backgroundColor = MaterialTheme.colors.background,
        //contentColor = MaterialTheme.colors.onBackground,
        modifier = Modifier.padding(5.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(10.dp)
            ) {

                Text(text = "Common Fault: "+ commonFault.fault, style = MaterialTheme.typography.h6)
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                var i:Int=1
                for (reason in commonFault.reasons){
                    Text(text = i.toString()+".    "+reason, style = MaterialTheme.typography.subtitle1)
                    Spacer(modifier = Modifier.padding(vertical = 3.dp))
                    i=i+1
                }
            }
        }
    }
}



@Composable
fun ShowAdvisoryList(title:String, points:List<String>){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant)
                .padding(5.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        var i:Int = 1
        for (point in points){
            ShowPoint(point = i.toString() +".    "+ point)
            i=i+1
        }
    }
}

@Composable
fun ShowPoint(point:String){
    Text(
        text = point,
        style = MaterialTheme.typography.subtitle1
    )
    Spacer(modifier = Modifier.padding(vertical = 3.dp))
}



