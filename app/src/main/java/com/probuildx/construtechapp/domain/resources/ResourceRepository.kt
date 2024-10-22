package com.probuildx.construtechapp.domain.resources

interface ResourceRepository {
    suspend fun getAllResources(): List<Resource>
    suspend fun getResourceById(id: String): Resource?
    suspend fun addResource(resource: Resource)
    suspend fun updateResource(resource: Resource)
    suspend fun deleteResource(id: String)
}
