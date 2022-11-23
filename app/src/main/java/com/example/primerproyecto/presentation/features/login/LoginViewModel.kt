package com.example.primerproyecto.presentation.features.login

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.primerproyecto.R
import java.time.Duration

class LoginViewModel : ViewModel(){

    val userList = listOf("Juanito", "Lupito")

    var usernameLiveData = MutableLiveData<Boolean>()

    fun checkUsername(_username : String){
        usernameLiveData.value = userList.contains(_username)
    }

}