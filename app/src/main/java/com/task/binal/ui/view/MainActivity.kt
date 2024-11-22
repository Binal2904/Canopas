package com.task.binal.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.gson.Gson
import com.task.binal.databinding.ActivityMainBinding
import com.task.binal.model.Task
import com.task.binal.ui.view.adapter.TaskAdapter
import com.task.binal.ui.viewmode.TaskViewModel
import com.task.binal.utils.Constant.Companion.priority
import com.task.binal.workmanager.OverdueNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var taskAdapter: TaskAdapter? = null
    private val taskList: MutableList<Task> = mutableListOf()
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
    }

    private fun init() {
        initAdapter()

        taskViewModel.taskByDueDate.observe(this) { tasks ->
            tasks.let { taskAdapter?.setTaskList(it) }
            scheduleASWorker()

        }

        binding.ivAddTask.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }


        binding.edTaskPriority.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val title = binding.edSearch.text.toString()
                    val priority = binding.edTaskPriority.selectedItem as String
                    taskAdapter?.filterTask(title, priority)
                }

            }

        binding.edSearch.addTextChangedListener { text ->
            val title = text.toString()
            val priority = binding.edTaskPriority.selectedItem as String
            taskAdapter?.filterTask(title, priority)
        }
        checkAndSetNightMode()

    }

    private fun checkAndSetNightMode() {
        val isNightMode =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        binding.switchDarkMode.isChecked = isNightMode
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            }
        }
    }

    private fun initAdapter() {
        taskList.clear()
        taskAdapter = TaskAdapter(taskList) { task, isTaskEdit ->
            if (isTaskEdit) {
                startActivity(Intent(this, AddTaskActivity::class.java).putExtra("id", task))
            } else {
                taskViewModel.deleteTask(task)
                taskAdapter?.removeTask(task)
            }
        }
        binding.rvTodoListItems.layoutManager = LinearLayoutManager(this)
        binding.rvTodoListItems.adapter = taskAdapter


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priority)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.edTaskPriority.adapter = adapter
    }

    private fun scheduleASWorker() {
        val taskTitles = taskList.map { it.title }
        val taskTitlesString = Gson().toJson(taskTitles)
        val data = Data.Builder().putString("task_titles", taskTitlesString).build()

        val workRequest = PeriodicWorkRequestBuilder<OverdueNotificationManager>(
            24, TimeUnit.HOURS
        ).setInitialDelay(1, TimeUnit.HOURS).setInputData(data).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("overdue_task", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }
}