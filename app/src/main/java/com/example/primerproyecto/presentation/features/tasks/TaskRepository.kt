package com.example.primerproyecto.presentation.features.tasks

import androidx.lifecycle.LiveData
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val readAllData : LiveData<List<TaskEntity>> = taskDao.readAllData()

    fun addTask(task : TaskEntity){
        taskDao.addTask(task)
    }

    fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task.taskId)
    }

    fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task.taskId, task.taskName, task.image)
        println("After repo")
    }

    suspend fun updateTask2(task: TaskEntity) {
        taskDao.update(task)
        println("After repo")
    }

}