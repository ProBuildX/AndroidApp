package com.probuildx.construtechapp.ui.resources

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.resources.Resource

class ResourceViewModel : ViewModel() {
    private val _resources = MutableStateFlow<List<Resource>>(emptyList())
    val resources: StateFlow<List<Resource>> = _resources

    fun createNewResource() {
        // Lógica para crear un nuevo recurso (material o maquinaria)
    }

    fun onResourceSelected(resource: Resource) {
        // Lógica para seleccionar un recurso y mostrar detalles
    }
}
