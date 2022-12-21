package com.example.primerproyecto.domain.pokemon

import androidx.lifecycle.LiveData
import com.example.primerproyecto.data.pokemon.PokemonDto
import com.example.primerproyecto.data.pokemon.PokemonRepository

interface GetAllPokemonsUseCase {
    fun getPokemons() : LiveData<List<PokemonDto>>
}

class GetAllPokemonsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetAllPokemonsUseCase {
    override fun getPokemons(): LiveData<List<PokemonDto>> {
        TODO("Not yet implemented")
    }
}