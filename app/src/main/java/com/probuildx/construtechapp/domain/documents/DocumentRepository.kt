package com.probuildx.construtechapp.domain.documents

interface DocumentRepository {
    suspend fun getAllDocuments(): List<Document>
    suspend fun getDocumentById(id: String): Document?
    suspend fun addDocument(document: Document)
    suspend fun updateDocument(document: Document)
    suspend fun deleteDocument(id: String)
}
