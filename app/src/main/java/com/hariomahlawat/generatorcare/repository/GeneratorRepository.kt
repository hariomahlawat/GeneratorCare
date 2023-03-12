package com.hariomahlawat.generatorcare.repository

import com.hariomahlawat.generatorcare.data.GeneratorDatabaseDao
import com.hariomahlawat.generatorcare.model.Generator
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class GeneratorRepository @Inject constructor(private val generatorDatabseDao: GeneratorDatabaseDao) {
    suspend fun addGenerator(generator:Generator) = generatorDatabseDao.insertGenerator(generator)
    suspend fun updateGenerator(generator:Generator) = generatorDatabseDao.updateGenerator(generator)
    suspend fun deleteGenerator(generator:Generator) = generatorDatabseDao.deleteGenerator(generator)
    suspend fun deleteAllGenerators() = generatorDatabseDao.deleteAll()
    suspend fun getGeneratorById(id:String):Generator = generatorDatabseDao.getGeneratorById(id)
    fun getAllGenerators(): Flow<List<Generator>> = generatorDatabseDao.getGenerators().flowOn(
        Dispatchers.IO)
        .conflate()
}