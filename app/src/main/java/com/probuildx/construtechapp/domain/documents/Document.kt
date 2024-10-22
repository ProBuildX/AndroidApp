package com.probuildx.construtechapp.domain.documents

import java.util.UUID

data class Document(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val filePath: String,
    val createdAt: String
)
