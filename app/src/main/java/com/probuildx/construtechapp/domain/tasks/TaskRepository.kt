package com.probuildx.construtechapp.domain.tasks

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun getTaskById(id: String): Task?
    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(id: String)
}

