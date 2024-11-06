package com.probuildx.construtechapp.domain.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
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
