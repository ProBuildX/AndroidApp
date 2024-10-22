package com.probuildx.construtechapp.ui.resources

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.resources.Resource

class ResourceFormViewModel : ViewModel() {
    private val _resource = MutableStateFlow<Resource?>(null)
    val resource: StateFlow<Resource?> = _resource

    fun saveResource(name: String, description: String) {
        // Lógica para guardar un nuevo recurso o actualizar uno existente
    }
}
