package com.example.primerproyecto

import androidx.lifecycle.LiveData
import com.example.primerproyecto.presentation.features.tasks.Task
import com.example.primerproyecto.presentation.features.tasks.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData : LiveData<List<Task>> = taskDao.readAllData()

    fun addUser(task : Task){
        taskDao.addTask(task)
    }

}