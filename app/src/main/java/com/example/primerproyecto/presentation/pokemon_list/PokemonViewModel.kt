package com.example.primerproyecto.presentation.pokemon_list

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
class PokemonViewModel @Inject constructor(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase
) : ViewModel() {

    private var pokemonsResult = MutableLiveData<List<PokemonBo>?>()
    private var pokemonLoading = MutableLiveData<Boolean>()

    fun getAllPokemons(){
        viewModelScope.launch {
            if(getAllPokemonsUseCase.getAllPokemons().isLoading()){
                pokemonLoading.value = true
                println("Loading...")
            } else if (getAllPokemonsUseCase.getAllPokemons().isSuccess()){
                pokemonLoading.value = false
                pokemonsResult.value = getAllPokemonsUseCase.getAllPokemons().data
                println("Success")
            } else if (getAllPokemonsUseCase.getAllPokemons().isError()){
                pokemonLoading.value = false
                println("Error!")
            }
        }
    }

    fun getPokemonList() : LiveData<List<PokemonBo>?> {
        return pokemonsResult
    }

    fun isListLoading(): LiveData<Boolean>{
        return pokemonLoading
    }

}