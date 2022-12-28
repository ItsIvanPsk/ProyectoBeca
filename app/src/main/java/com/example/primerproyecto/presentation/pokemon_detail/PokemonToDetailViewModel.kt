package com.example.primerproyecto.presentation.pokemon_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primerproyecto.domain.pokemon_list.GetAllPokemonsUseCase
import com.example.primerproyecto.domain.pokemon_list.GetPokemonUseCase
import com.example.primerproyecto.domain.pokemon_list.PokemonBo
import com.example.primerproyecto.domain.pokemon_detail.PokemonDetailBo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonToDetailViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private var pokemonsResult = MutableLiveData<List<PokemonBo>?>()

    private var pokemonDetailResult = MutableLiveData<PokemonDetailBo?>()
    private var pokemonLoading = MutableLiveData<Boolean>()

    fun getPokemon(name: String){
        viewModelScope.launch {
            var state = getPokemonUseCase.getPokemon(name)
            if(state.isLoading()){
                pokemonLoading.value = true
                println("Loading...")
            } else if (state.isSuccess()){
                pokemonLoading.value = false
                println("Success")
                pokemonDetailResult.value = getPokemonUseCase.getPokemon(name).data
            } else if (state.isError()){
                pokemonLoading.value = false
                println("Error!")
            }
        }
    }

    fun getPokemonDetails() : LiveData<PokemonDetailBo?> {
        return pokemonDetailResult
    }

    fun isLoading(): LiveData<Boolean> {
        return pokemonLoading
    }

}