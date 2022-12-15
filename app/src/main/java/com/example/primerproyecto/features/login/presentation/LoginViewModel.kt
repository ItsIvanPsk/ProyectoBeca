package com.example.primerproyecto.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.primerproyecto.features.tasks.TaskEntity

class LoginViewModel : ViewModel(){

    private val userList = listOf("Juanito", "Lupito")

    private val usernameLiveData = MutableLiveData<String>()
    private val checkUserLiveData = MutableLiveData<Boolean>()
    private val taskLiveData = MutableLiveData<List<TaskEntity>>()

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

    fun addTask(task : TaskEntity) {
        val tasks = taskLiveData.value.orEmpty().toMutableList().apply {
            add(task)
        }
        taskLiveData.value = tasks
    }
}