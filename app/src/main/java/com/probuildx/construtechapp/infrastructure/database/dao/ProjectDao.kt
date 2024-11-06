package com.probuildx.construtechapp.infrastructure.database.dao

import Project
import androidx.room.*

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project)

    @Update
    suspend fun updateProject(project: Project)

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("SELECT * FROM project_table WHERE id = :projectId")
    suspend fun getProjectById(projectId: String): Project?

    @Query("SELECT * FROM project_table")
    suspend fun getAllProjects(): List<Project>
}
