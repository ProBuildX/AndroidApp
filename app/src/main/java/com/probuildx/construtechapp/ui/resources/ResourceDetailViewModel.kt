package com.probuildx.construtechapp.ui.resources

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.resources.Resource

class ResourceDetailViewModel : ViewModel() {
    private val _selectedResource = MutableStateFlow<Resource?>(null)
    val selectedResource: StateFlow<Resource?> = _selectedResource

    fun editResource() {
        // Lógica para editar el recurso seleccionado
    }
}
