package com.example.primerproyecto.presentation.features.tasks

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId : Int,
    @NonNull @ColumnInfo val taskName : String,
    @NonNull @ColumnInfo val taskDescription : String,
    @NonNull @ColumnInfo val image : Boolean
    )
