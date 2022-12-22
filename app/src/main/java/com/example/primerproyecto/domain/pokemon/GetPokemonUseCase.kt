package com.example.primerproyecto.domain.pokemon

import com.example.primerproyecto.data.pokemon.PokemonRepository

interface GetPokemonUseCase {
    suspend fun getAllPokemons(name: String) : PokemonDetailBo
}

class GetPokemonUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetPokemonUseCase {
    override suspend fun getAllPokemons(name: String): PokemonDetailBo {
        return pokemonRepository.getCharacter(name)
    }
}