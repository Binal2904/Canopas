package com.task.binal.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.task.binal.R
import com.task.binal.databinding.ItemTaskBinding
import com.task.binal.model.Task
import com.task.binal.utils.DateUtils.Companion.getDate

class TaskAdapter(
    private val tasks: MutableList<Task>, private val onTaskClick: (Task, Boolean) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var filteredTask: MutableList<Task> = tasks.toMutableList()

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTaskName.text = "Title: " + task.title
            binding.tvTaskDescription.text = "Description: " + task.description
            binding.tvTaskDueDate.text = "Due Date: " + getDate(task.dueDate, "dd/MM/YYYY")
            binding.tvTaskPriority.text = "Priority: " + task.priority
            binding.ivEdit.setOnClickListener {
                onTaskClick.invoke(task, true)
            }
            if (task.isOverDueDate) {
                binding.llMainItem.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, R.color.red
                    )
                )
            } else {
                binding.llMainItem.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, R.color.white
                    )
                )
            }

            binding.ivDelete.setOnClickListener {
                onTaskClick.invoke(task, false)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context))
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(filteredTask[position])
    }

    override fun getItemCount(): Int {
        return filteredTask.count()
    }

    fun filterTask(title: String? = null, priority: String? = null) {
        filteredTask = tasks.filter { task ->
            (title == null || task.title.contains(
                title, ignoreCase = true
            )) && (priority == null || task.priority.contains(priority, ignoreCase = true))

        }.toMutableList()
        notifyDataSetChanged()
    }

    fun setTaskList(list: List<Task>?) {
        tasks.clear()
        if (list != null) {
            tasks.addAll(list)
            filteredTask = tasks.toMutableList()
            notifyDataSetChanged()
        }
    }

    fun removeTask(task: Task) {
        val position = tasks.indexOf(task)
        if (position != -1) {
            tasks.removeAt(position)
            filteredTask.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}