package com.probuildx.construtechapp.domain.personnel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_table")
data class Team(
    @PrimaryKey val id: String,
    val name: String
)
