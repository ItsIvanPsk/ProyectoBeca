package com.example.primerproyecto.features.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primerproyecto.features.tasks.domain.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    private val taskToEdit = MutableLiveData<TaskEntity>()

    fun addTask(task : TaskEntity){
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun setTaskToEdit(task: TaskEntity){
        taskToEdit.value = task
    }

    fun getTasks(): LiveData<List<TaskEntity>>{
        return repository.readAllData
    }

    fun getTaskToEdit() : LiveData<TaskEntity>{
        return taskToEdit
    }

    fun deleteTask(task : TaskEntity){
        repository.deleteTask(task)
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }


}