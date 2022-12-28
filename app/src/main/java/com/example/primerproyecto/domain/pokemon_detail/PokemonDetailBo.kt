package com.example.primerproyecto.domain.pokemon_detail

import com.example.primerproyecto.data.pokemon_detail.Sprites

data class PokemonDetailBo(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)