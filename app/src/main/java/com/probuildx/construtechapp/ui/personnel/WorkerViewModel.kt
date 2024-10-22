package com.probuildx.construtechapp.ui.personnel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.personnel.Worker

class WorkerViewModel : ViewModel() {
    private val _workers = MutableStateFlow<List<Worker>>(emptyList())
    val workers: StateFlow<List<Worker>> = _workers

    fun createNewWorker() {
        // Lógica para agregar un nuevo trabajador
    }

    fun onWorkerSelected(worker: Worker) {
        // Lógica para seleccionar un trabajador y mostrar detalles
    }
}
