package com.example.primerproyecto.data.pokemon_detail

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)