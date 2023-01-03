package com.example.primerproyecto.utils

import com.example.primerproyecto.data.pokemon_list.PokemonCharacterDto
import com.example.primerproyecto.data.pokemon_detail.PokemonDetailDto
import com.example.primerproyecto.data.pokemon_list.PokemonDto
import com.example.primerproyecto.domain.pokemon_list.PokemonBo
import com.example.primerproyecto.domain.pokemon_detail.PokemonDetailBo

fun PokemonCharacterDto.toListBo() = this.results.map {
    it.toBo()
}

fun PokemonDto.toBo() = PokemonBo(
    name = name,
    url = url
)

fun PokemonDetailDto.toBo() = PokemonDetailBo(
    id = id,
    name = name,
    height = height,
    weight = weight,
    sprites = sprites,
    moves = moves,
    stats = stats
)