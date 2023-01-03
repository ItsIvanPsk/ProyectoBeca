package com.example.primerproyecto.presentation.pokemon_list

import androidx.lifecycle.*
import com.example.primerproyecto.domain.pokemon_list.GetAllPokemonsUseCase
import com.example.primerproyecto.domain.pokemon_list.PokemonBo
import com.example.primerproyecto.data.common.AsyncResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase
) : ViewModel() {

    private var pokemonsResult = MutableLiveData<AsyncResult<List<PokemonBo>>>()

    fun getAllPokemons(){
        viewModelScope.launch {
            getAllPokemonsUseCase.getAllPokemons().collect{
                println(it.data.toString())
                pokemonsResult.value = it
            }
        }
    }

    fun getPokemonList() : LiveData<AsyncResult<List<PokemonBo>>> {
        return pokemonsResult
    }

}