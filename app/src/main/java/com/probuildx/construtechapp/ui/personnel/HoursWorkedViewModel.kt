package com.probuildx.construtechapp.ui.personnel

import androidx.lifecycle.ViewModel
import com.probuildx.construtechapp.domain.personnel.Worker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HoursWorkedViewModel : ViewModel() {
    private val _selectedPersonnel = MutableStateFlow<Worker?>(null)
    val selectedPersonnel: StateFlow<Worker?> = _selectedPersonnel

    fun saveWorkedHours(hours: Int) {
        // Lógica para guardar las horas trabajadas para el trabajador seleccionado
    }
}
