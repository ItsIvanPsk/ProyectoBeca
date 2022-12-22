package com.example.primerproyecto.utils

import com.example.primerproyecto.data.pokemon.PokemonCharacterDto
import com.example.primerproyecto.data.pokemon.PokemonDetailDto
import com.example.primerproyecto.data.pokemon.PokemonDto
import com.example.primerproyecto.domain.pokemon.PokemonBo
import com.example.primerproyecto.domain.pokemon.PokemonDetailBo

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
    sprites = sprites
)