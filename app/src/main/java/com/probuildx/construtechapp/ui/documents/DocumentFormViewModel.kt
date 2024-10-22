package com.probuildx.construtechapp.ui.documents

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.probuildx.construtechapp.domain.documents.Document

class DocumentFormViewModel : ViewModel() {
    private val _document = MutableStateFlow<Document?>(null)
    val document: StateFlow<Document?> = _document

    fun saveDocument(name: String, description: String) {
        // Lógica para guardar un nuevo documento o actualizar uno existente
    }
}
