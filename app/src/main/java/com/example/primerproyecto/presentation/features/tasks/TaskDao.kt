package com.example.primerproyecto.presentation.features.tasks

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task ORDER BY taskId ASC")
    fun getAll(): List<Task>

    @Query("SELECT * FROM Task WHERE taskId = " + 1 + ";") // TODO: Change the id values
    fun getTaskById(id : Int): List<Task>

}