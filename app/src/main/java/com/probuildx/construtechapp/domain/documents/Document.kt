package com.probuildx.construtechapp.domain.documents

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "document_table")
data class Document(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val filePath: String,
    val createdAt: String
)
