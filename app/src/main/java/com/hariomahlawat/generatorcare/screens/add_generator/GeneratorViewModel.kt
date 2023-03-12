package com.hariomahlawat.generatorcare.screens.add_generator

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.repository.GeneratorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratorViewModel  @Inject constructor(private val repository: GeneratorRepository):ViewModel()
{


    private val _generatorList = MutableStateFlow<List<Generator>>(emptyList())
    val generatorList = _generatorList.asStateFlow()
    var generator: Generator? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllGenerators().distinctUntilChanged()
                .collect { listOfGenerators ->
                    if (listOfGenerators.isEmpty()) {
                        Log.d("Empty", ": Empty list")
                    } else {
                        _generatorList.value = listOfGenerators
                    }

                }
        }
        }

    fun addGenerator(generator: Generator) = viewModelScope.launch { repository.addGenerator(generator) }
    fun removeGenerator(generator: Generator) = viewModelScope.launch { repository.deleteGenerator(generator) }
    fun updateGenerator(generator: Generator) = viewModelScope.launch { repository.updateGenerator(generator) }

    fun getGeneratorById(id:String): Job = CoroutineScope(Dispatchers.IO).launch {
       generator = repository.getGeneratorById(id)
    }



}