package com.example.primerproyecto.utils

import com.example.primerproyecto.data.pokemon.PokemonCharacterDto
import com.example.primerproyecto.domain.pokemon.PokemonCharacterBo

fun PokemonCharacterDto.toBo() = PokemonCharacterBo(
    cant = results.size,
    results = results
)