package com.hariomahlawat.generatorcare.screens.add_generator

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.repository.GeneratorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class GeneratorViewModel  @Inject constructor(private val repository: GeneratorRepository):ViewModel(){
    private val _generatorList = MutableStateFlow<List<Generator>>(emptyList())
    val generatorList = _generatorList.asStateFlow()

    fun addGenerator(generator: Generator) = viewModelScope.launch { repository.addGenerator(generator) }
    fun removeGenerator(generator: Generator) = viewModelScope.launch { repository.deleteGenerator(generator) }
    fun updateGenerator(generator: Generator) = viewModelScope.launch { repository.updateGenerator(generator) }




}