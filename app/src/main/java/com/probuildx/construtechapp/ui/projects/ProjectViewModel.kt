package com.probuildx.construtechapp.ui.projects

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.probuildx.construtechapp.domain.projects.Project

class ProjectViewModel : ViewModel() {
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    fun createNewProject() {
        // Lógica para crear un nuevo proyecto
    }

    fun onProjectSelected(project: Project) {
        // Lógica para seleccionar un proyecto
    }
}
