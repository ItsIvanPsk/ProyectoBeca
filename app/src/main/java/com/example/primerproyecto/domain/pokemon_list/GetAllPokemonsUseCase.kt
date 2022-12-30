package com.example.primerproyecto.domain.pokemon_list

import com.example.primerproyecto.data.pokemon_list.PokemonRepository
import com.example.primerproyecto.utils.AsyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetAllPokemonsUseCase {
    suspend fun getAllPokemons() : Flow<AsyncResult<List<PokemonBo>>>
}

class GetAllPokemonsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetAllPokemonsUseCase {
    override suspend fun getAllPokemons(): Flow<AsyncResult<List<PokemonBo>>> {
        return pokemonRepository.getAllCharacters(0).flow()
    }
}