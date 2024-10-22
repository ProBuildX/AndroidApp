package com.probuildx.construtechapp.ui.documents

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.documents.Document

class DocumentViewModel : ViewModel() {
    private val _documents = MutableStateFlow<List<Document>>(emptyList())
    val documents: StateFlow<List<Document>> = _documents

    fun createNewDocument() {
        // Lógica para crear un nuevo documento
    }

    fun onDocumentSelected(document: Document) {
        // Lógica para seleccionar un documento y mostrar detalles
    }
}
