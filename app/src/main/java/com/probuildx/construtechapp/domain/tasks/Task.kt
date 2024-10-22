package com.probuildx.construtechapp.domain.tasks

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val assignedTeam: String,
    val startDate: String,
    val endDate: String,
    val status: TaskStatus
)

enum class TaskStatus {
    PENDING, IN_PROGRESS, COMPLETED, CANCELLED
}

