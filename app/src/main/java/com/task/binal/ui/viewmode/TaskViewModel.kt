package com.task.binal.ui.viewmode

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.binal.model.Task
import com.task.binal.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class TaskViewModel @Inject constructor(private val repo: TaskRepository) : ViewModel() {
//
//    val taskByDueDate: LiveData<List<Task>> = repo.getAllTaskSortedByDate
//
//    fun insertTask(task: Task) = viewModelScope.launch { repo.insertTask(task) }
//}
@HiltViewModel
class TaskViewModel @Inject constructor(private val repo: TaskRepository) : ViewModel() {
    val taskByDueDate: LiveData<List<Task>> = repo.getAllTaskSortedByDate

    fun insertTask(task: Task) = viewModelScope.launch { repo.insertTask(task) }
    fun deleteTask(task: Task) = viewModelScope.launch { repo.deleteTask(task) }
    fun updateTask(task: Task) = viewModelScope.launch { repo.updateTask(task) }
}