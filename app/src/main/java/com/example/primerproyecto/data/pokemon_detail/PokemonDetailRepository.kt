package com.example.primerproyecto.data.pokemon_detail

import com.example.primerproyecto.data.common.RepositoryResponse
import com.example.primerproyecto.data.common.remoteResponse
import com.example.primerproyecto.data.pokemon_list.PokemonAPI
import com.example.primerproyecto.domain.pokemon_detail.PokemonDetailBo
import com.example.primerproyecto.utils.toBo
import javax.inject.Inject


interface PokemonDetailRepository {
    suspend fun getCharacter(name : String) : RepositoryResponse<PokemonDetailBo>
}

class PokemonDetailRepositoryImpl @Inject constructor(
    private val api: PokemonAPI,
) : PokemonDetailRepository {

    override suspend fun getCharacter(name:String): RepositoryResponse<PokemonDetailBo> {
        return remoteResponse { api.getCharacter(name).toBo() }
    }

}