package com.example.primerproyecto.di.pokemon

import com.example.primerproyecto.data.common.AuthInterceptor
import com.example.primerproyecto.data.common.Constants
import com.example.primerproyecto.data.pokemon_list.PokemonAPI
import com.example.primerproyecto.data.pokemon_list.PokemonRepository
import com.example.primerproyecto.data.pokemon_list.PokemonRepositoryImpl
import com.example.primerproyecto.domain.pokemon_detail.*
import com.example.primerproyecto.domain.pokemon_list.GetAllPokemonsUseCase
import com.example.primerproyecto.domain.pokemon_list.GetAllPokemonsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteApiModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesPokemonAPI(retrofitBuilder: Retrofit.Builder): PokemonAPI {
        return retrofitBuilder.build().create(PokemonAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesPokemonRepository(api: PokemonAPI)
        = PokemonRepositoryImpl(api) as PokemonRepository

    @Singleton
    @Provides
    fun providesPokemonDetailRepository(api: PokemonAPI)
            = PokemonDetailRepositoryImpl(api) as PokemonDetailRepository

    @Singleton
    @Provides
    fun providesGetAllPokemonUseCase(repository: PokemonRepository)
        = GetAllPokemonsUseCaseImpl(repository) as GetAllPokemonsUseCase

    @Singleton
    @Provides
    fun providesGetPokemonUseCase(repository: PokemonDetailRepository)
        = GetPokemonUseCaseImpl(repository) as GetPokemonUseCase

    @Singleton
    @Provides
    fun providesGetMovesUseCase(repository: PokemonDetailRepository)
            = GetMovesUseCaseImpl(repository) as GetMovesUseCase


}
