package com.example.primerproyecto.data.pokemon

data class PokemonDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)