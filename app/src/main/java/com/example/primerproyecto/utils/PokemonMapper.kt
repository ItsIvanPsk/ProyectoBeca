package com.example.primerproyecto.utils

import com.example.primerproyecto.data.pokemon.PokemonDto
import com.example.primerproyecto.domain.pokemon.PokemonCharacterBo

fun PokemonDto.toPokemon() = PokemonCharacterBo(
    cant = results.size,
    results = results
)