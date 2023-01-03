package com.example.primerproyecto.domain.pokemon_detail

import com.example.primerproyecto.data.common.AsyncResult
import kotlinx.coroutines.flow.Flow

interface GetMovesUseCase {
    suspend fun getMovesData(name: String) : Flow<AsyncResult<PokemonMovesBo?>>
}

class GetMovesUseCaseImpl(
    private val pokemonRepository: PokemonDetailRepository
) : GetMovesUseCase {

    override suspend fun getMovesData(name: String): Flow<AsyncResult<PokemonMovesBo?>> {
        return pokemonRepository.getMove(name).flow()
    }
}
