package com.example.primerproyecto.utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val taskId : Int = 0,
    @SerializedName("name") @ColumnInfo var taskName : String,
    @SerializedName("image") @ColumnInfo var image : Boolean
)
