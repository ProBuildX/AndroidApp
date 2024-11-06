package com.probuildx.construtechapp.model.projects

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.probuildx.construtechapp.domain.personnel.Team
import com.probuildx.construtechapp.domain.personnel.Worker
import com.probuildx.construtechapp.domain.personnel.WorkerRole
import com.probuildx.construtechapp.domain.projects.Project
import kotlinx.coroutines.launch


class ProjectViewModel : ViewModel() {
    private val _projects = mutableStateListOf<Project>()
    val projects: List<Project> get() = _projects

    private val _teams = mutableStateListOf<Team>()
    val teams: List<Team> get() = _teams

    init {
        initializeSampleData()
    }

    private fun initializeSampleData() {
        // Datos de muestra para equipos y trabajadores
        val worker1 = Worker(name = "Alice Johnson", role = WorkerRole.ARCHITECT, workedHours = 120)
        val worker2 = Worker(name = "Bob Smith", role = WorkerRole.ENGINEER, workedHours = 95)
        val worker3 = Worker(name = "Carlos Sanchez", role = WorkerRole.BUILDER, workedHours = 150)
        val worker4 = Worker(name = "Diana Martin", role = WorkerRole.FOREMAN, workedHours = 80)
        val worker5 = Worker(name = "Evan Turner", role = WorkerRole.LABORER, workedHours = 60)

        val teamA = Team(id = "teamA", name = "Team Alpha", members = listOf(worker1, worker2))
        val teamB = Team(id = "teamB", name = "Team Beta", members = listOf(worker3, worker4, worker5))

        _teams.addAll(listOf(teamA, teamB))

        // Datos de muestra para proyectos
        _projects.addAll(
            listOf(
                Project(
                    title = "Office Building Project",
                    description = "Modern office building in downtown.",
                    address = "123 Main St, Cityville",
                    date = "2024-01-01",
                    budget = "500,000"
                ),
                Project(
                    title = "Highway Expansion",
                    description = "Expansion of the main highway.",
                    address = "Highway 1, Cityville",
                    date = "2024-05-10",
                    budget = "1,200,000"
                )
            )
        )
    }

    fun saveProject(project: Project) {
        viewModelScope.launch {
            val existingProjectIndex = _projects.indexOfFirst { it.title == project.title }
            if (existingProjectIndex >= 0) {
                _projects[existingProjectIndex] = project
            } else {
                _projects.add(project)
            }
        }
    }

    fun getProject(title: String): Project? {
        return _projects.find { it.title == title }
    }

    fun changeWorkerTeam(worker: Worker, newTeamId: String) {
        val currentTeam = _teams.find { it.members.contains(worker) }
        currentTeam?.let {
            _teams[_teams.indexOf(it)] = it.copy(members = it.members - worker)
        }

        val newTeam = _teams.find { it.id == newTeamId }
        newTeam?.let {
            _teams[_teams.indexOf(it)] = it.copy(members = it.members + worker)
        }
    }
}
