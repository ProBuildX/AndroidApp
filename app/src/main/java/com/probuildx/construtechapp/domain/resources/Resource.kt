package com.probuildx.construtechapp.domain.resources

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "resource_table")
data class Resource(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val resourceType: ResourceType,
    val quantity: Int,
    val unitCost: Double
)

enum class ResourceType {
    MATERIAL, EQUIPMENT
}
