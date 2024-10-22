package com.probuildx.construtechapp.domain.resources

import java.util.UUID

data class Resource(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val resourceType: ResourceType,
    val quantity: Int,
    val unitCost: Double
)

enum class ResourceType {
    MATERIAL, EQUIPMENT
}
