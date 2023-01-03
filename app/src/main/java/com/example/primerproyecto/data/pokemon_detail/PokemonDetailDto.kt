package com.example.primerproyecto.data.pokemon_detail

import com.example.primerproyecto.data.models.pokemon_detail.Sprites
import com.example.primerproyecto.data.models.pokemon_detail.Move
import com.example.primerproyecto.data.models.pokemon_detail.Stat

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val moves: List<Move>,
    val stats: List<Stat>
)
