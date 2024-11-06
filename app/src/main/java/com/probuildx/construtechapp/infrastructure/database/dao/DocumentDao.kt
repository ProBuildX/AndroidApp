package com.probuildx.construtechapp.infrastructure.database.dao

import androidx.room.*
import com.probuildx.construtechapp.domain.documents.Document

@Dao
interface DocumentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(document: Document)

    @Update
    suspend fun updateDocument(document: Document)

    @Delete
    suspend fun deleteDocument(document: Document)

    @Query("SELECT * FROM document_table WHERE id = :documentId")
    suspend fun getDocumentById(documentId: String): Document?

    @Query("SELECT * FROM document_table")
    suspend fun getAllDocuments(): List<Document>
}
