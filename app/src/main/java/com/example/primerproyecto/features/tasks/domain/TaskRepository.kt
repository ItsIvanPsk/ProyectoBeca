package com.example.primerproyecto.features.tasks.domain

import androidx.lifecycle.LiveData
import com.example.primerproyecto.features.tasks.TaskEntity
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val readAllData : LiveData<List<TaskEntity>> = taskDao.readAllData()

    fun addTask(task : TaskEntity){
        taskDao.addTask(task)
    }

    fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task.taskId)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.update(task)
    }

}