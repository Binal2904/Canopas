package com.task.binal.workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.task.binal.model.Task

class OverdueNotificationManager(private var context: Context, params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        // Retrieve the task titles
        val taskTitlesString = inputData.getString("task_titles")
        val taskTitles: List<String> =
            Gson().fromJson(taskTitlesString, Array<String>::class.java).toList()

        // Create dummy Task objects from titles (you'll need to replace this with your own logic)
        val overdueTasks = taskTitles.map {
            Task(
                title = it,
                description = "",
                dueDate = System.currentTimeMillis(),
                priority = "High"
            )
        }

//        val overdueTasks = getOverDueTask()
        overdueTasks.forEach { task ->
            sendNotification(task)
        }
        return Result.success()
    }

    private fun sendNotification(task: Task) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "Overdue"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "Overdue Task", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val notification: Notification = NotificationCompat.Builder(applicationContext)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("OverDue Task: ${task.title}")
            .setPriority(NotificationCompat.PRIORITY_HIGH).setAutoCancel(true).build()

        notificationManager.notify(task.id, notification)
    }


    fun getOverDueTask(): List<Task> {
        return listOf()
    }
}