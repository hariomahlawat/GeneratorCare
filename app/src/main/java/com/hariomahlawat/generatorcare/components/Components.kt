package com.hariomahlawat.generatorcare.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GeneratorCareLogo(modifier: Modifier = Modifier) {
    Text(text = "Generator Care",
        modifier = modifier.padding(bottom = 16.dp),
        style = MaterialTheme.typography.h4,
        color = Color.Red.copy(alpha = 0.5f))
}