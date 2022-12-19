package com.example.primerproyecto.data.pokemon

import android.content.Context
import androidx.room.Room
import com.example.primerproyecto.data.task.TaskDatabase
import com.example.primerproyecto.domain.task.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PokemonApiModule {

    @Singleton
    @Provides
    fun provideApiModule(@ApplicationContext appContext: Context) = PokemonAPI.Builder()

}