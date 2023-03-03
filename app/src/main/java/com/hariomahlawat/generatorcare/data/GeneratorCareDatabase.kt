package com.hariomahlawat.generatorcare.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hariomahlawat.generatorcare.model.Generator
import com.hariomahlawat.generatorcare.utils.DateConverter
import com.hariomahlawat.generatorcare.utils.UUIDConverter

@Database(entities = [Generator::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class GeneratorCareDatabase : RoomDatabase() {
    abstract fun generatorDao() : GeneratorDatabaseDao
}