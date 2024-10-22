package com.probuildx.construtechapp.ui.personnel

import androidx.lifecycle.ViewModel
import com.probuildx.construtechapp.domain.personnel.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TeamViewModel : ViewModel() {
    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams

    fun createNewTeam() {
        // Lógica para agregar un nuevo equipo
    }

    fun onTeamSelected(team: Team) {
        // Lógica para seleccionar un equipo y ver detalles
    }
}
