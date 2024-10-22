package com.probuildx.construtechapp.ui.projects

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.projects.Project

class ProjectDetailViewModel : ViewModel() {
    private val _selectedProject = MutableStateFlow<Project?>(null)
    val selectedProject: StateFlow<Project?> = _selectedProject

    fun editProject() {
        // Lógica para editar el proyecto seleccionado
    }
}
