package com.hariomahlawat.generatorcare.screens

import com.hariomahlawat.generatorcare.R
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hariomahlawat.generatorcare.components.GeneratorCareLogo
import com.hariomahlawat.generatorcare.navigation.GeneratorCareScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true ){
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }))
        delay(2000L)
        navController.navigate(GeneratorCareScreens.HomeScreen.name)

    }

    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scale.value),
        shape = CircleShape,
        //color = Color.White,
        border = BorderStroke(width = 1.dp,
            color = Color.Blue)
    ) {
        Column(
            modifier = Modifier.padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //GeneratorCareLogo()
            Image(painter = painterResource(id = R.drawable.logo1), contentDescription = null)
            Spacer(modifier = Modifier.height(15.dp))
            Divider(thickness = 4.dp, color = Color.Blue)
            Divider(thickness = 4.dp, color = Color.Yellow)
            Divider(thickness = 2.dp, color = Color.Red)
            Divider(thickness = 4.dp, color = Color.Yellow)
            Divider(thickness = 4.dp, color = Color.Blue)



        }


    }
}