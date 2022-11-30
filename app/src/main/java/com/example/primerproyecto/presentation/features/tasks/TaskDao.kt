package com.example.primerproyecto.presentation.features.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)

    @Query("SELECT * FROM task ORDER BY taskId ASC")
    fun readAllData() : LiveData<List<Task>>

}