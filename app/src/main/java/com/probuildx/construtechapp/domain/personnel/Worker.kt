package com.probuildx.construtechapp.domain.personnel

import java.util.UUID

data class Worker(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val role: WorkerRole,
    val workedHours: Int = 0
)

enum class WorkerRole {
    ARCHITECT, ENGINEER, BUILDER, FOREMAN, LABORER
}
