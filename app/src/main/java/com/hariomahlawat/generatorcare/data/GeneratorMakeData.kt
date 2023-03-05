package com.hariomahlawat.generatorcare.data

import com.hariomahlawat.generatorcare.R
import com.hariomahlawat.generatorcare.model.Generator

fun getGeneratorMakeList():List<String> {
    return listOf("Select OEM", "Honda", "Kirloskar", "Eicher")
}

fun getGeneratorLogo(make:String):Int{
    var image_id= R.drawable.eicher_logo;
    when(make){
        "Honda" -> image_id= R.drawable.honda_logo
        "Kirloskar" -> image_id= R.drawable.kirloskar_logo
        "Eicher" -> image_id= R.drawable.eicher_logo
    }
    return image_id
}