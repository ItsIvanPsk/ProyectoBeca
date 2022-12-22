package com.example.primerproyecto.domain.pokemon

import com.example.primerproyecto.data.pokemon.PokemonRepository
import com.example.primerproyecto.utils.toBo

interface GetAllPokemonsUseCase {
    suspend fun getAllPokemons() : List<PokemonBo>
}

class GetAllPokemonsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetAllPokemonsUseCase {
    override suspend fun getAllPokemons(): List<PokemonBo> {
        var pokemons = pokemonRepository.getAllCharacters(0)
        return pokemons
    }
}