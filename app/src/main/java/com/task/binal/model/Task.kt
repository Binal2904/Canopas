package com.task.binal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val dueDate: Long,
    val priority: String,
    val isOverDue: Boolean = false,
) : Serializable {
    val isOverDueDate: Boolean
        get() {
            return System.currentTimeMillis() >= dueDate
        }
}

