package com.example.primerproyecto.presentation.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.primerproyecto.data.pokemon.PokemonDto
import com.example.primerproyecto.data.pokemon.PokemonRepositoryImpl
import com.example.primerproyecto.domain.pokemon.PokemonCharacterBo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private val pokemonToDetail = MutableLiveData<PokemonCharacterBo>()

    suspend fun getAllPokemons(): PokemonDto {
        return repository.getAllCharacters(0)
    }

}