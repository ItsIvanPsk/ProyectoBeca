package com.example.primerproyecto.presentation.task

import androidx.lifecycle.*
import com.example.primerproyecto.domain.task.TaskRepository
import com.example.primerproyecto.utils.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val taskToEdit = MutableLiveData<TaskEntity>()

    fun addTask(task : TaskEntity){
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun setTaskToEdit(task: TaskEntity){
        taskToEdit.value = task
    }

    fun getTasks(): LiveData<List<TaskEntity>> =
        repository.readAllData.asLiveData()


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