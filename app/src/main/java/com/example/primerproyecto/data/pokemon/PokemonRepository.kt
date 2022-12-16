package com.example.primerproyecto.data.pokemon

import javax.inject.Inject

interface PokemonRepository {
    suspend fun getAllCharacters(offset:Int): PokemonDto
}

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonAPI
) : PokemonRepository {

    override suspend fun getAllCharacters(offset: Int): PokemonDto {
        return api.getAllCharacters()
    }

}