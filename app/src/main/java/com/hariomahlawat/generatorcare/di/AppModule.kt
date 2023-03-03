package com.hariomahlawat.generatorcare.di

import android.content.Context
import androidx.room.Room
import com.hariomahlawat.generatorcare.data.GeneratorCareDatabase
import com.hariomahlawat.generatorcare.data.GeneratorDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideGeneratorsDao(generatorCareDatabase: GeneratorCareDatabase): GeneratorDatabaseDao
            = generatorCareDatabase.generatorDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): GeneratorCareDatabase
            = Room.databaseBuilder(
        context,
        GeneratorCareDatabase::class.java,
        "generator_care_db")
        .fallbackToDestructiveMigration()
        .build()
}