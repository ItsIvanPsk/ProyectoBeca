package com.example.primerproyecto.presentation.features.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    fun addTask(task : TaskEntity){
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun getTasks(): LiveData<List<TaskEntity>>{
        return repository.readAllData
    }

}