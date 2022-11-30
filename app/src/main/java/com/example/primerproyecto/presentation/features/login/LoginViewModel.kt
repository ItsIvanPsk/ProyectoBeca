package com.example.primerproyecto.presentation.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.primerproyecto.presentation.features.tasks.Task

class LoginViewModel : ViewModel(){

    private val userList = listOf("Juanito", "Lupito")

    private val usernameLiveData = MutableLiveData<String>()
    private val checkUserLiveData = MutableLiveData<Boolean>()
    private val taskLiveData = MutableLiveData<List<Task>>()

    fun getUsernameLiveData() : LiveData<String> {
        return usernameLiveData
    }

    fun getCheckLiveData() : LiveData<Boolean>{
        return checkUserLiveData
    }

    fun getTaskLiveData() : LiveData<List<Task>> {
        return taskLiveData
    }

    fun checkUsername(username : String){
        checkUserLiveData.value = userList.contains(username)
        usernameLiveData.value = username
        println(usernameLiveData.value)
    }

    fun addTask(task : Task) {
        val tasks = taskLiveData.value.orEmpty().toMutableList().apply {
            add(task)
        }
        taskLiveData.value = tasks
    }
}