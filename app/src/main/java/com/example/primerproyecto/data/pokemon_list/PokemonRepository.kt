package com.example.primerproyecto.data.pokemon_list

import com.example.primerproyecto.data.common.RepositoryResponse
import com.example.primerproyecto.data.common.remoteResponse
import com.example.primerproyecto.domain.pokemon_list.PokemonBo
import com.example.primerproyecto.utils.toListBo
import javax.inject.Inject

interface PokemonRepository {
    suspend fun getAllCharacters(offset : Int = 0): RepositoryResponse<List<PokemonBo>>
}

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonAPI,
) : PokemonRepository {

    override suspend fun getAllCharacters(offset: Int): RepositoryResponse<List<PokemonBo>> {
        return remoteResponse { api.getAllCharacters().toListBo() }
    }

}