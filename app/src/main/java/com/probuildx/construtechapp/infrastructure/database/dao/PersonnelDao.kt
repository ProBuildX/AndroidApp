package com.probuildx.construtechapp.infrastructure.database.dao

import androidx.room.*
import com.probuildx.construtechapp.domain.personnel.Worker
import com.probuildx.construtechapp.domain.personnel.Team

@Dao
interface PersonnelDao {
    // Operaciones CRUD para Worker
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(worker: Worker)

    @Update
    suspend fun updateWorker(worker: Worker)

    @Delete
    suspend fun deleteWorker(worker: Worker)

    @Query("SELECT * FROM worker_table WHERE id = :workerId")
    suspend fun getWorkerById(workerId: String): Worker?

    @Query("SELECT * FROM worker_table")
    suspend fun getAllWorkers(): List<Worker>

    // Operaciones CRUD para Team
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: Team)

    @Update
    suspend fun updateTeam(team: Team)

    @Delete
    suspend fun deleteTeam(team: Team)

    @Query("SELECT * FROM team_table WHERE id = :teamId")
    suspend fun getTeamById(teamId: String): Team?

    @Query("SELECT * FROM team_table")
    suspend fun getAllTeams(): List<Team>
}
