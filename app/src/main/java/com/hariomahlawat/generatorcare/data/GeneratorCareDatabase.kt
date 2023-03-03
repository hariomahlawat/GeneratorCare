package com.hariomahlawat.generatorcare.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hariomahlawat.generatorcare.model.Generator

@Database(entities = [Generator::class], version = 1, exportSchema = false)
abstract class GeneratorCareDatabase : RoomDatabase() {
    abstract fun generatorDao() : GeneratorDatabseDao
}