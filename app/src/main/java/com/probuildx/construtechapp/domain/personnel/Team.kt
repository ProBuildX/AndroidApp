package com.probuildx.construtechapp.domain.personnel

data class Team(
    val id: String,
    val name: String,
    val members: List<Worker>
)
