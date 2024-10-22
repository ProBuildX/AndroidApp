package com.probuildx.construtechapp.domain.projects

import java.util.UUID

data class Project(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val status: ProjectStatus
)

enum class ProjectStatus {
    PLANNING, IN_PROGRESS, COMPLETED, ON_HOLD
}