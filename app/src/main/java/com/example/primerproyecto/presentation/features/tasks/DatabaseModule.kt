package com.example.primerproyecto.presentation.features.tasks

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskDatabase::class.java,
            "task_database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideTaskDao(database: TaskDatabase): TaskDao {
        return database.taskDao()
    }

}