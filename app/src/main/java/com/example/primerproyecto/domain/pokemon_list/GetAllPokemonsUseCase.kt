package com.example.primerproyecto.domain.pokemon_list

import com.example.primerproyecto.data.pokemon_list.PokemonRepository
import com.example.primerproyecto.utils.AsyncResult

interface GetAllPokemonsUseCase {
    suspend fun getAllPokemons() : AsyncResult<List<PokemonBo>>
}

class GetAllPokemonsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetAllPokemonsUseCase {
    override suspend fun getAllPokemons(): AsyncResult<List<PokemonBo>> {
        return pokemonRepository.getAllCharacters(0).valueAsync().await()
    }
}