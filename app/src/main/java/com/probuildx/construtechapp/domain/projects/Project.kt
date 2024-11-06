package com.probuildx.construtechapp.domain.projects

import java.util.UUID

data class Project(
    var title: String,
    var description: String,
    var address: String,
    var date: String,
    var budget: String
)

enum class ProjectStatus {
    PLANNING, IN_PROGRESS, COMPLETED, ON_HOLD
}