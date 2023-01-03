package com.example.primerproyecto.domain.task

import com.example.primerproyecto.utils.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    val readAllData : Flow<List<TaskEntity>> get() = taskDao.readAllData()

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