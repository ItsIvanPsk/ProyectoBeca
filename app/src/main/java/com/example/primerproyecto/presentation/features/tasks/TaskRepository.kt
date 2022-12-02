package com.example.primerproyecto.presentation.features.tasks

import androidx.lifecycle.LiveData
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val readAllData : LiveData<List<TaskEntity>> = taskDao.readAllData()

    fun addTask(task : TaskEntity){
        taskDao.addTask(task)
    }

}