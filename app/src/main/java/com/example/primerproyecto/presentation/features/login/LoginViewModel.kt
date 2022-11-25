package com.example.primerproyecto.presentation.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    val userList = listOf("Juanito", "Lupito")
    var taskList = arrayOf("Dev android", "Dev Unity", "Dev Kotlin")

    private val usernameLiveData = MutableLiveData<String>()
    private val checkUserLiveData = MutableLiveData<Boolean>()
    private val taskLiveData = MutableLiveData<Array<String>>()

    fun getUsernameLiveData() : LiveData<String> {
        return usernameLiveData
    }

    fun getCheckLiveData() : LiveData<Boolean>{
        return checkUserLiveData
    }

    fun getTaskLiveData() : LiveData<Array<String>> {
        return taskLiveData
    }

    fun checkUsername(username : String){
        checkUserLiveData.value = userList.contains(username)
        usernameLiveData.value = username
        println(usernameLiveData.value)
    }

    fun addTask(task : String) {
        taskList.set(taskList.size - 1, task)
    }
}