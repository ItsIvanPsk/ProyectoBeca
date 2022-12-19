package com.example.primerproyecto.domain.pokemon

import com.example.primerproyecto.data.pokemon.PokemonRepositoryImpl
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val repository: PokemonRepositoryImpl
){

}