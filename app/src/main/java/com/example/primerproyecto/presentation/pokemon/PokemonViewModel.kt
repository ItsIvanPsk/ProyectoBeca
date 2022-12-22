package com.example.primerproyecto.presentation.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primerproyecto.data.pokemon.PokemonRepository
import com.example.primerproyecto.domain.pokemon.GetAllPokemonsUseCase
import com.example.primerproyecto.domain.pokemon.GetPokemonUseCase
import com.example.primerproyecto.domain.pokemon.PokemonBo
import com.example.primerproyecto.domain.pokemon.PokemonDetailBo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase,
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private var pokemonsResult = MutableLiveData<List<PokemonBo>>()

    private var pokemonNameToDetail = MutableLiveData<String>()
    private var pokemonDetailResult = MutableLiveData<PokemonDetailBo>()


    fun getAllPokemons(){
        viewModelScope.launch {
            pokemonsResult.value = getAllPokemonsUseCase.getAllPokemons()
        }
    }

    fun getPokemon(name: String){
        viewModelScope.launch {
            pokemonDetailResult.value = getPokemonUseCase.getAllPokemons(name)
        }
    }

    fun getPokemonDetails() : LiveData<PokemonDetailBo?>{
        return pokemonDetailResult
    }

    fun getPokemonList() : LiveData<List<PokemonBo>> {
        return pokemonsResult
    }

    fun setPokemonToDetail(url: String){
        pokemonNameToDetail.value = url
    }

    fun getPokemonToDetail() : String? {
        return pokemonNameToDetail.value
    }

}