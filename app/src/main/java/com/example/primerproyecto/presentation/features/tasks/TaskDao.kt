package com.example.primerproyecto.presentation.features.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: TaskEntity)

    @Query("SELECT * FROM TaskEntity ORDER BY taskId ASC")
    fun readAllData() : LiveData<List<TaskEntity>>

}