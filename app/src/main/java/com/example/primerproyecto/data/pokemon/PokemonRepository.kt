package com.example.primerproyecto.data.pokemon

import com.example.primerproyecto.domain.pokemon.PokemonBo
import com.example.primerproyecto.domain.pokemon.PokemonDetailBo
import com.example.primerproyecto.utils.toBo
import com.example.primerproyecto.utils.toListBo
import javax.inject.Inject

interface PokemonRepository {
    suspend fun getAllCharacters(offset : Int = 0): List<PokemonBo>
    suspend fun getCharacter(name : String) : PokemonDetailBo
}

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonAPI,
) : PokemonRepository {

    override suspend fun getAllCharacters(offset: Int): List<PokemonBo> {
        return api.getAllCharacters().toListBo()
    }

    override suspend fun getCharacter(name:String): PokemonDetailBo {
        return api.getCharacter(name).toBo()
    }

}