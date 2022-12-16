package com.example.primerproyecto.domain.task

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.primerproyecto.utils.TaskEntity


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: TaskEntity)

    @Query("SELECT * FROM TaskEntity ORDER BY taskId ASC")
    fun readAllData() : LiveData<List<TaskEntity>>

    @Query("DELETE FROM taskentity WHERE taskId = :taskId")
    fun deleteTask(taskId : Int)

    @Query("UPDATE TaskEntity SET TaskName = :taskName, image= :image WHERE taskId =:taskId")
    fun updateTask(taskId : Int, taskName : String, image : Boolean)

    @Update
    suspend fun update(vararg task: TaskEntity)

}