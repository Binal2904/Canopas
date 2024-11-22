package com.task.binal.ui.view

import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.task.binal.databinding.ActivityAddTaskBinding
import com.task.binal.model.Task
import com.task.binal.ui.viewmode.TaskViewModel
import com.task.binal.utils.Constant.Companion.priority
import com.task.binal.utils.DateUtils.Companion.getDate
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var selectedDueDate: Calendar
    var editData: Task? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    private fun initAdapter() {
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, priority)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.edTaskPriority.adapter = adapter
    }

    private fun init() {
        initAdapter()

        editData = intent.getSerializableExtra("id") as? Task

        if (editData != null) {
            binding.edTaskTitle.setText(editData!!.title)
            binding.edTaskDescription.setText(editData!!.description)
            selectedDueDate = Calendar.getInstance()
            selectedDueDate.timeInMillis = editData!!.dueDate
            val priorityIndex = priority.indexOf(editData!!.priority)
            if (priorityIndex >= 0) {
                binding.edTaskPriority.setSelection(priorityIndex)
            }
            binding.edTaskDueDate.setText(getDate(editData!!.dueDate, "dd/MM/YYYY"))
            binding.btnAddTask.text = "Update Task"
        }

        binding.edTaskDueDate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val dpd = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->

                    selectedDueDate = Calendar.getInstance()
                    selectedDueDate.set(year, monthOfYear, dayOfMonth)
                    binding.edTaskDueDate.setText(
                        getDate(
                            selectedDueDate.timeInMillis,
                            "dd/MM/YYYY"
                        )
                    )
                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            )

            dpd.show()
        }
        binding.btnAddTask.setOnClickListener {
            val title = binding.edTaskTitle.text.toString().trim()
            val description = binding.edTaskDescription.text.toString().trim()
            val priority = binding.edTaskPriority.selectedItem.toString().trim()

            if (title.isBlank() || description.isBlank() || priority.isBlank() || !::selectedDueDate.isInitialized) {
                Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show()
            } else {
                val dueDate = selectedDueDate.timeInMillis

                if (editData != null) {
                    taskViewModel.updateTask(
                        Task(
                            id = editData!!.id,
                            title = title,
                            description = description,
                            dueDate = dueDate,
                            priority = priority
                        )
                    )
                } else {
                    taskViewModel.insertTask(
                        Task(
                            title = title,
                            description = description,
                            dueDate = dueDate,
                            priority = priority
                        )
                    )
                }

                Toast.makeText(this, "Task added sucessfully", Toast.LENGTH_SHORT).show()

                finish()
            }

        }

    }
}