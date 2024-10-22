package com.probuildx.construtechapp.ui.tasks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.tasks.Task

class TaskFormViewModel : ViewModel() {
    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?> = _task

    fun saveTask(title: String, description: String) {
        // Lógica para guardar una nueva tarea o actualizar una existente
    }
}
