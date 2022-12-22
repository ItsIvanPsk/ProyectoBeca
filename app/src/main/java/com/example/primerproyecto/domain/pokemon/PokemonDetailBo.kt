package com.example.primerproyecto.domain.pokemon

import com.example.primerproyecto.data.pokemon.Sprites

data class PokemonDetailBo(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)