package com.example.primerproyecto.domain.pokemon_detail

import com.example.primerproyecto.data.common.AsyncResult
import kotlinx.coroutines.flow.Flow

interface GetPokemonUseCase {
    suspend fun getPokemon(name: String) : Flow<AsyncResult<PokemonDetailBo?>>
}

class GetPokemonUseCaseImpl(
    private val pokemonRepository: PokemonDetailRepository
) : GetPokemonUseCase {
    override suspend fun getPokemon(name: String): Flow<AsyncResult<PokemonDetailBo?>> {
        return pokemonRepository.getCharacter(name).flow()
    }
}