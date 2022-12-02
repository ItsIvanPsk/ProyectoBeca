package com.example.primerproyecto.presentation.features.tasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val taskId : Int = 0,
    @ColumnInfo val taskName : String,
    @ColumnInfo val image : Boolean
    )
