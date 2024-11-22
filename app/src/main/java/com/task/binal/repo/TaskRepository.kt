package com.task.binal.repo

import androidx.lifecycle.LiveData
import com.task.binal.di.dao.TaskDao
import com.task.binal.model.Task
import jakarta.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    suspend fun insertTask(task: Task) = taskDao.insetTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    val getAllTaskSortedByDate: LiveData<List<Task>> = taskDao.getAllTaskSortedByDate()
}