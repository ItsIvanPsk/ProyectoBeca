package com.example.primerproyecto.presentation.pokemon_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primerproyecto.domain.pokemon_detail.GetPokemonUseCase
import com.example.primerproyecto.domain.pokemon_detail.PokemonDetailBo
import com.example.primerproyecto.data.common.AsyncResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonToDetailViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private var pokemonDetailResult = MutableLiveData<AsyncResult<PokemonDetailBo?>>()

    fun getPokemon(name: String){
        viewModelScope.launch {
            getPokemonUseCase.getPokemon(name).collect{
                pokemonDetailResult.value = it
            }
        }
    }

    fun getPokemonDetails() : LiveData<AsyncResult<PokemonDetailBo?>> {
        return pokemonDetailResult
    }
}