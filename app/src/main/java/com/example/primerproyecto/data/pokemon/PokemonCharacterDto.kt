package com.example.primerproyecto.data.pokemon

import com.example.primerproyecto.domain.pokemon.PokemonBo

data class PokemonCharacterDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonBo>
)