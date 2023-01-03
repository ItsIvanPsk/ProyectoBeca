package com.example.primerproyecto.presentation.pokemon_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primerproyecto.data.common.AsyncResult
import com.example.primerproyecto.data.models.pokemon_detail.Move
import com.example.primerproyecto.domain.pokemon_detail.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonToDetailViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getMovesUseCase: GetMovesUseCase
) : ViewModel() {

    private var pokemonDetailResult = MutableLiveData<AsyncResult<PokemonDetailBo?>>()
    private var pokemonMoves = MutableLiveData<MutableList<AsyncResult<PokemonMovesBo?>>>()

    fun getPokemon(name: String){
        viewModelScope.launch {
            getPokemonUseCase.getPokemon(name).collect{
                pokemonDetailResult.value = it
            }
        }
    }

    fun refreshPokemonMoves(moves: List<Move>){
        viewModelScope.launch {

            for(position in moves){
                getMovesUseCase.getMovesData(moves.get(
                    moves.indexOf(position)
                ).move.name).collect{
                    pokemonMoves.value?.add(it)
                }
            }

        }
    }

    fun getPokemonDetails() : LiveData<AsyncResult<PokemonDetailBo?>> {
        return pokemonDetailResult
    }

    fun getPokemonMoves(): LiveData<MutableList<AsyncResult<PokemonMovesBo?>>>? {
        return pokemonMoves.value
    }
}