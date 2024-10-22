package com.probuildx.construtechapp.ui.documents

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.probuildx.construtechapp.domain.documents.Document
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class DocumentListFragment : Fragment() {

    private lateinit var viewModel: DocumentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DocumentViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                DocumentListScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentListScreen(viewModel: DocumentViewModel) {
    val documents by viewModel.documents.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Documents") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.createNewDocument() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Document")
            }
        }
    ) { paddingValues ->  // Aquí aplicamos el paddingValues del Scaffold
        LazyColumn(
            contentPadding = paddingValues,  // Aplicamos el padding del Scaffold a la LazyColumn
            modifier = Modifier.fillMaxSize()
        ) {
            items(documents) { document ->
                DocumentItem(document, onDocumentClick = { viewModel.onDocumentSelected(it) })
            }
        }
    }
}

@Composable
fun DocumentItem(document: Document, onDocumentClick: (Document) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onDocumentClick(document) }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = document.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = document.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}