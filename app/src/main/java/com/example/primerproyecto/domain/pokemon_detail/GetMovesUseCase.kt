package com.example.primerproyecto.domain.pokemon_detail

import com.example.primerproyecto.data.common.AsyncResult
import com.example.primerproyecto.data.models.pokemon_detail.Move
import kotlinx.coroutines.flow.Flow

interface GetMovesUseCase {
    suspend fun getMovesData(name: String) : AsyncResult<List<PokemonMovesBo?>>
}

class GetMovesUseCaseImpl(
    private val pokemonRepository: PokemonDetailRepository
) : GetMovesUseCase {

    override suspend fun getMovesData(name: String): AsyncResult<List<PokemonMovesBo?>> {
        TODO("Not yet implemented")
    }

}
