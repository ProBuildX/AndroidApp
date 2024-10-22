package com.probuildx.construtechapp.ui.documents

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class DocumentFormFragment : Fragment() {

    private lateinit var viewModel: DocumentFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DocumentFormViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                DocumentFormScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentFormScreen(viewModel: DocumentFormViewModel) {
    var documentName by remember { mutableStateOf("") }
    var documentDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Upload / Edit Document") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveDocument(documentName, documentDescription)
            }) {
                Icon(Icons.Default.Save, contentDescription = "Save Document")
            }
        }
    ) { paddingValues ->  // Utilizamos los paddingValues proporcionados por Scaffold
        Column(
            modifier = Modifier
                .padding(paddingValues)  // Aplicamos el padding del Scaffold
                .padding(16.dp)  // Además, agregamos un padding adicional para el contenido interno
        ) {
            OutlinedTextField(
                value = documentName,
                onValueChange = { documentName = it },
                label = { Text("Document Name") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = documentDescription,
                onValueChange = { documentDescription = it },
                label = { Text("Document Description") }
            )
        }
    }
}
