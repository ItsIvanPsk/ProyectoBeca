package com.example.primerproyecto.domain.pokemon_list

import com.example.primerproyecto.data.pokemon_detail.PokemonDetailRepository
import com.example.primerproyecto.data.pokemon_list.PokemonRepository
import com.example.primerproyecto.domain.pokemon_detail.PokemonDetailBo
import com.example.primerproyecto.utils.AsyncResult

interface GetPokemonUseCase {
    suspend fun getPokemon(name: String) : AsyncResult<PokemonDetailBo?>
}

class GetPokemonUseCaseImpl(
    private val pokemonRepository: PokemonDetailRepository
) : GetPokemonUseCase {
    override suspend fun getPokemon(name: String): AsyncResult<PokemonDetailBo?> {
        return pokemonRepository.getCharacter(name).valueAsync().await()
    }
}