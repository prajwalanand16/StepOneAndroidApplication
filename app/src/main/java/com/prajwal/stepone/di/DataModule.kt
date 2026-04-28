package com.prajwal.stepone.di

import android.content.Context
import androidx.room.Room
import com.prajwal.stepone.data.StepDao
import com.prajwal.stepone.data.StepDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StepDatabase {
        return Room.databaseBuilder(
            context,
            StepDatabase::class.java,
            "stepone_db"
        ).build()
    }

    @Provides
    fun provideStepDao(database: StepDatabase): StepDao {
        return database.stepDao()
    }
}
