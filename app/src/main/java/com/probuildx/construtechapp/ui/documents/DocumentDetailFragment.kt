package com.probuildx.construtechapp.ui.documents

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class DocumentDetailFragment : Fragment() {

    private lateinit var viewModel: DocumentDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DocumentDetailViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                DocumentDetailScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentDetailScreen(viewModel: DocumentDetailViewModel) {
    val document by viewModel.selectedDocument.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Document Details") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            document?.let {
                Text(text = it.name, style = MaterialTheme.typography.displayLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.editDocument() }) {
                    Text("Edit Document")
                }
            } ?: run {
                Text("No Document Selected", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
