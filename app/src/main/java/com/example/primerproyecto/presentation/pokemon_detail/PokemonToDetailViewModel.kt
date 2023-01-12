package com.example.primerproyecto.presentation.pokemon_detail

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primerproyecto.data.common.AsyncResult
import com.example.primerproyecto.domain.pokemon_detail.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonToDetailViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private var pokemonDetailResult = MutableLiveData<AsyncResult<PokemonDetailBo?>?>()
    private var allPermissionsGranted = MutableLiveData<Boolean>()

    fun getPokemon(name: String){
        viewModelScope.launch {
            getPokemonUseCase.getPokemon(name).collect{
                pokemonDetailResult.value = it
            }
        }
    }

    fun getPokemonDetails() : MutableLiveData<AsyncResult<PokemonDetailBo?>?> {
        return pokemonDetailResult
    }

    fun checkPermissions(context: Context) {
        if (checkCameraHardware(context)){
            allPermissionsGranted.value = (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED)
        }
    }

    private fun checkCameraHardware(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    fun allPermissionsGranted() : LiveData<Boolean>{
        return allPermissionsGranted
    }

    fun goBack() {
        allPermissionsGranted.value = false
    }


}