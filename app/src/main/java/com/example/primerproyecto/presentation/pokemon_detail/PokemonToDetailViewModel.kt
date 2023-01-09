package com.example.primerproyecto.presentation.pokemon_detail

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primerproyecto.data.common.AsyncResult
import com.example.primerproyecto.data.models.pokemon_detail.Move
import com.example.primerproyecto.domain.pokemon_detail.*
import com.example.primerproyecto.utils.PokemonConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonToDetailViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private var pokemonDetailResult = MutableLiveData<AsyncResult<PokemonDetailBo?>>()
    private var permisionsState = MutableLiveData<Boolean>()

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

    fun checkPermisions(context: Context) {
        if (checkCameraHardware(context)){
            permisionsState.value = (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED)
        }
    }

    private fun checkCameraHardware(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    fun requestPermisions() : LiveData<Boolean>{
        return permisionsState
    }

}