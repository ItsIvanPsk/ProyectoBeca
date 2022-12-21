package com.example.primerproyecto.data.pokemon

import com.example.primerproyecto.domain.pokemon.PokemonCharacterBo
import com.example.primerproyecto.utils.toBo
import javax.inject.Inject

interface PokemonRepository {
    suspend fun getAllCharacters(offset:Int): PokemonCharacterDto
}

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonAPI,
) : PokemonRepository {

    override suspend fun getAllCharacters(offset: Int): PokemonCharacterDto {
        return api.getAllCharacters()
    }

}