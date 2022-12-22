package com.example.primerproyecto.data.pokemon

import com.example.primerproyecto.domain.pokemon.GetAllPokemonsUseCase
import com.example.primerproyecto.domain.pokemon.GetAllPokemonsUseCaseImpl
import com.example.primerproyecto.domain.pokemon.GetPokemonUseCase
import com.example.primerproyecto.domain.pokemon.GetPokemonUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun providesGetAllPokemonUseCase(repository: PokemonRepository)
        = GetAllPokemonsUseCaseImpl(repository) as GetAllPokemonsUseCase

    @Singleton
    @Provides
    fun providesGetPokemonUseCase(repository: PokemonRepository)
        = GetPokemonUseCaseImpl(repository) as GetPokemonUseCase

}
