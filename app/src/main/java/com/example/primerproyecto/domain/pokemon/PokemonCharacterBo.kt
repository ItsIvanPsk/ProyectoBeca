package com.example.primerproyecto.domain.pokemon

import com.example.primerproyecto.data.pokemon.Result

data class PokemonCharacterBo(
    val cant : Int,
    val results: List<Result>
)