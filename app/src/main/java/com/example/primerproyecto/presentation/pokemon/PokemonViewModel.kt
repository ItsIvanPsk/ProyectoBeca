package com.example.primerproyecto.presentation.pokemon

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.primerproyecto.domain.pokemon.PokemonBo
import com.example.primerproyecto.data.pokemon.PokemonRepositoryImpl
import com.example.primerproyecto.domain.pokemon.GetAllPokemonsUseCase
import com.example.primerproyecto.domain.pokemon.GetAllPokemonsUseCaseImpl
import com.example.primerproyecto.domain.pokemon.PokemonCharacterBo
import com.example.primerproyecto.utils.toBo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private var pokemonsResult = MutableLiveData<PokemonCharacterBo>()
    private val pokemonToDetail = MutableLiveData<PokemonBo>()

    fun getAllPokemons() {
        CoroutineScope(Dispatchers.IO).launch {
            pokemonsResult.value = repository.getAllCharacters(0).toBo()
        }
    }
}