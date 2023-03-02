package com.hariomahlawat.generatorcare.screens.add_generator

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.hariomahlawat.generatorcare.model.Generator

class GeneratorViewModel : ViewModel() {
    private var generatorList = mutableStateListOf<Generator>()

    fun addgenerator(generator: Generator){
        generatorList.add(generator)
    }

    fun removeGenerator(generator: Generator){
        generatorList.remove(generator)
    }

    fun getAllGenerators():List<Generator>{
        return generatorList
    }

}