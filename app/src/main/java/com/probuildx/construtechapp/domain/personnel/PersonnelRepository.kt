package com.probuildx.construtechapp.domain.personnel

interface PersonnelRepository {
    suspend fun getAllWorkers(): List<Worker>
    suspend fun getWorkerById(id: String): Worker?
    suspend fun addWorker(worker: Worker)
    suspend fun updateWorker(worker: Worker)
    suspend fun deleteWorker(id: String)

    suspend fun getAllTeams(): List<Team>
    suspend fun getTeamById(id: String): Team?
    suspend fun addTeam(team: Team)
    suspend fun updateTeam(team: Team)
    suspend fun deleteTeam(id: String)
}
