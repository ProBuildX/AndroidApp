package com.probuildx.construtechapp.ui.tasks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.tasks.Task

class TaskDetailViewModel : ViewModel() {
    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    fun updateTaskStatus() {
        // Lógica para actualizar el estado de la tarea seleccionada
    }
}
