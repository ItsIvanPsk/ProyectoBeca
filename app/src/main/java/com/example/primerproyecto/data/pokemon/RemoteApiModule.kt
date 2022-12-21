package com.example.primerproyecto.data.pokemon

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
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesPokemonAPI(retrofitBuilder: Retrofit.Builder): PokemonAPI {
        return retrofitBuilder.build().create(PokemonAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesPokemonRepository(): PokemonRepository {
        return PokemonRepositoryImpl(providesPokemonAPI(providesRetrofit()))
    }

}
