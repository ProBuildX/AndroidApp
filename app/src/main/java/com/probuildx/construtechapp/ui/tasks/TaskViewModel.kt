package com.probuildx.construtechapp.ui.tasks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.probuildx.construtechapp.domain.tasks.Task

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun createNewTask() {
        // Lógica para crear una nueva tarea
    }

    fun onTaskSelected(task: Task) {
        // Lógica para seleccionar una tarea
    }
}
