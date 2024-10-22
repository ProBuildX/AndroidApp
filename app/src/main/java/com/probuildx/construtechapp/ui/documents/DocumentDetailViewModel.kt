package com.probuildx.construtechapp.ui.documents

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.documents.Document

class DocumentDetailViewModel : ViewModel() {
    private val _selectedDocument = MutableStateFlow<Document?>(null)
    val selectedDocument: StateFlow<Document?> = _selectedDocument

    fun editDocument() {
        // Lógica para editar el documento seleccionado
    }
}
