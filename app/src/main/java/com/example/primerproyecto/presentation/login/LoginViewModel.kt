package com.example.primerproyecto.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.primerproyecto.utils.TaskEntity

class LoginViewModel : ViewModel(){

    private val userList = listOf("Juanito", "Lupito")

    private val usernameLiveData = MutableLiveData<String>()
    private val checkUserLiveData = MutableLiveData<Boolean>()
    private val taskLiveData = MutableLiveData<List<TaskEntity>>()

    fun getUsernameLiveData() : LiveData<String> {
        return usernameLiveData
    }

    fun setCheckLiveData(state : Boolean){
        checkUserLiveData.value = state
    }

    fun setUsernameLiveData(state : String){
        usernameLiveData.value = state
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