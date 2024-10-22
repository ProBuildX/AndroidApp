package com.probuildx.construtechapp.ui.projects

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.projects.Project

class ProjectFormViewModel : ViewModel() {
    private val _project = MutableStateFlow<Project?>(null)
    val project: StateFlow<Project?> = _project

    fun saveProject(name: String, description: String) {
        // Lógica para guardar un nuevo proyecto o actualizar uno existente
    }
}
