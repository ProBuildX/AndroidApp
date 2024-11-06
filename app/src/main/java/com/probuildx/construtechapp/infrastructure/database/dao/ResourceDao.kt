package com.probuildx.construtechapp.infrastructure.database.dao

import androidx.room.*
import com.probuildx.construtechapp.domain.resources.Resource

@Dao
interface ResourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResource(resource: Resource)

    @Update
    suspend fun updateResource(resource: Resource)

    @Delete
    suspend fun deleteResource(resource: Resource)

    @Query("SELECT * FROM resource_table WHERE id = :resourceId")
    suspend fun getResourceById(resourceId: String): Resource?

    @Query("SELECT * FROM resource_table")
    suspend fun getAllResources(): List<Resource>
}
