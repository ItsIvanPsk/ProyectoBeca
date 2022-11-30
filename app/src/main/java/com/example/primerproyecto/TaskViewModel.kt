package com.example.primerproyecto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.primerproyecto.presentation.features.tasks.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData : LiveData<List<Task>>
    private val repository : TaskRepository

    init {
        val userDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addTask(task : Task){
        viewModelScope.launch {
            repository.addUser(task)
        }
    }
}