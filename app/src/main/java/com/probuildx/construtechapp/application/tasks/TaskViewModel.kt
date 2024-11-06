package com.probuildx.construtechapp.application.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.probuildx.construtechapp.domain.tasks.Task
import com.probuildx.construtechapp.infrastructure.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = DatabaseProvider.getDatabase(application).taskDao()

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(task)
        }
    }

    fun getTaskById(taskId: String, callback: (Task?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val task = taskDao.getTaskById(taskId)
            callback(task)
        }
    }

    fun getAllTasks(callback: (List<Task>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = taskDao.getAllTasks()
            callback(tasks)
        }
    }
}
