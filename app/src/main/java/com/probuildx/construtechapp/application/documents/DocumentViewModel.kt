package com.probuildx.construtechapp.application.documents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.probuildx.construtechapp.domain.documents.Document
import com.probuildx.construtechapp.infrastructure.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DocumentViewModel(application: Application) : AndroidViewModel(application) {

    private val documentDao = DatabaseProvider.getDatabase(application).documentDao()

    fun addDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            documentDao.insertDocument(document)
        }
    }

    fun updateDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            documentDao.updateDocument(document)
        }
    }

    fun deleteDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            documentDao.deleteDocument(document)
        }
    }

    fun getDocumentById(documentId: String, callback: (Document?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val document = documentDao.getDocumentById(documentId)
            callback(document)
        }
    }

    fun getAllDocuments(callback: (List<Document>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val documents = documentDao.getAllDocuments()
            callback(documents)
        }
    }
}
