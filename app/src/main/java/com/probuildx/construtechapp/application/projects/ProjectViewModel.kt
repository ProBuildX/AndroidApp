package com.probuildx.construtechapp.application.projects

import Project
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.probuildx.construtechapp.infrastructure.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProjectViewModel(application: Application) : AndroidViewModel(application) {

    private val projectDao = DatabaseProvider.getDatabase(application).projectDao()

    fun addProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            projectDao.insertProject(project)
        }
    }

    fun updateProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            projectDao.updateProject(project)
        }
    }

    fun deleteProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            projectDao.deleteProject(project)
        }
    }

    fun getProjectById(projectId: String, callback: (Project?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val project = projectDao.getProjectById(projectId)
            callback(project)
        }
    }

    fun getAllProjects(callback: (List<Project>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val projects = projectDao.getAllProjects()
            callback(projects)
        }
    }
}
