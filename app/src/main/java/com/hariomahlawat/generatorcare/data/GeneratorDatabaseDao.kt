package com.hariomahlawat.generatorcare.data

import androidx.room.*
import com.hariomahlawat.generatorcare.model.Generator
import kotlinx.coroutines.flow.Flow

@Dao
interface GeneratorDatabaseDao {
    @Query("SELECT * from generators_tbl")
    fun getGenerators(): Flow<List<Generator>>

    @Query("SELECT * from generators_tbl where id =:id")
    suspend fun getGeneratorById(id: String): Generator

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenerator(generator: Generator)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGenerator(generator: Generator)

    @Query("DELETE from generators_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteGenerator(generator: Generator)


}
