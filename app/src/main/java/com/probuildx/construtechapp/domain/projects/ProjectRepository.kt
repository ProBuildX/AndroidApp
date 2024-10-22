package com.probuildx.construtechapp.domain.projects

interface ProjectRepository {
    suspend fun getAllProjects(): List<Project>
    suspend fun getProjectById(id: String): Project?
    suspend fun addProject(project: Project)
    suspend fun updateProject(project: Project)
    suspend fun deleteProject(id: String)
}
