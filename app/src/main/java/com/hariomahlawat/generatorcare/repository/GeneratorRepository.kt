package com.hariomahlawat.generatorcare.repository

import com.hariomahlawat.generatorcare.data.GeneratorDatabseDao
import com.hariomahlawat.generatorcare.model.Generator
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class GeneratorRepository @Inject constructor(private val generatorDatabseDao: GeneratorDatabseDao) {
    suspend fun addGenerator(generator:Generator) = generatorDatabseDao.insertGenerator(generator)
    suspend fun updateGenerator(generator:Generator) = generatorDatabseDao.updateGenerator(generator)
    suspend fun deleteGenerator(generator:Generator) = generatorDatabseDao.deleteGenerator(generator)
    suspend fun deleteAllGenerators() = generatorDatabseDao.deleteAll()
    fun getAllGenerators(): Flow<List<Generator>> = generatorDatabseDao.getGenerators().flowOn(
        Dispatchers.IO)
        .conflate()
}