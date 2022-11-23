package com.example.primerproyecto.presentation.features.login

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.primerproyecto.R
import java.time.Duration

class LoginViewModel : ViewModel(){

    val userList = listOf("Juanito", "Lupito")

    private val usernameLiveData = MutableLiveData<String>()
    private val checkUserLiveData = MutableLiveData<Boolean>()

    fun getUsernameLiveData() : LiveData<String> {
        return usernameLiveData
    }

    fun getCheckLiveData() : LiveData<Boolean>{
        return checkUserLiveData
    }

    fun checkUsername(username : String){
        checkUserLiveData.value = userList.contains(username)
        usernameLiveData.value = username
        println(usernameLiveData.value)
    }
}