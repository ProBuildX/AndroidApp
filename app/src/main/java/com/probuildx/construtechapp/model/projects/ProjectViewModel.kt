package com.probuildx.construtechapp.model.projects

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.probuildx.construtechapp.domain.personnel.Team
import com.probuildx.construtechapp.domain.personnel.Worker
import com.probuildx.construtechapp.domain.personnel.WorkerRole
import com.probuildx.construtechapp.domain.projects.Project
import com.probuildx.construtechapp.domain.resources.Resource
import com.probuildx.construtechapp.domain.resources.ResourceType
import com.probuildx.construtechapp.domain.tasks.Task
import com.probuildx.construtechapp.domain.tasks.TaskStatus
import kotlinx.coroutines.launch

class ProjectViewModel : ViewModel() {
    private val _projects = mutableStateListOf<Project>()
    val projects: List<Project> get() = _projects

    private val _teams = mutableStateListOf<Team>()
    val teams: List<Team> get() = _teams

    private val _resources = mutableStateListOf<Resource>()
    val resources: List<Resource> get() = _resources

    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> get() = _tasks

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

        _resources.addAll(
            listOf(
                Resource(name = "Cement", description = "Building material", resourceType = ResourceType.MATERIAL, quantity = 100, unitCost = 5.0),
                Resource(name = "Excavator", description = "Heavy equipment", resourceType = ResourceType.EQUIPMENT, quantity = 2, unitCost = 150.0)
            )
        )

        _tasks.addAll(
            listOf(
                Task(title = "Architectural Design", description = "Design main building layout", assignedTeam = "teamA", startDate = "2024-01-01", endDate = "2024-02-01", status = TaskStatus.IN_PROGRESS),
                Task(title = "Foundation Work", description = "Prepare and pour foundation", assignedTeam = "teamB", startDate = "2024-02-10", endDate = "2024-03-15", status = TaskStatus.PENDING)
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

    fun addResource(resource: Resource) {
        _resources.add(resource)
    }

    fun updateResource(updatedResource: Resource) {
        val index = _resources.indexOfFirst { it.id == updatedResource.id }
        if (index != -1) {
            _resources[index] = updatedResource
        }
    }

    fun removeResource(resourceId: String) {
        _resources.removeAll { it.id == resourceId }
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

    fun addTask(task: Task) {
        _tasks.add(task)
    }

    fun updateTask(updatedTask: Task) {
        val index = _tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            _tasks[index] = updatedTask
        }
    }

    fun removeTask(taskId: String) {
        _tasks.removeAll { it.id == taskId }
    }

    fun changeTaskStatus(taskId: String, newStatus: TaskStatus) {
        val index = _tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            _tasks[index] = _tasks[index].copy(status = newStatus)
        }
    }
}
