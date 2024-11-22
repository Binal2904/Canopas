package com.task.binal.di.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.task.binal.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)


    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    fun getAllTaskSortedByDate(): LiveData<List<Task>>


    @Query("SELECT * FROM tasks ORDER BY priority ASC")
    fun getAllTaskSortedByPriority(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks ORDER BY title LIKE :search")
    fun searchTask(search: String): LiveData<List<Task>>
}