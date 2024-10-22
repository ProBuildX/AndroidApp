package com.probuildx.construtechapp.ui.resources

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class ResourceDetailFragment : Fragment() {

    private lateinit var viewModel: ResourceDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ResourceDetailViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                ResourceDetailScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourceDetailScreen(viewModel: ResourceDetailViewModel) {
    val resource by viewModel.selectedResource.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resource Details") })
        }
    ) { paddingValues ->  // Agregamos los paddingValues proporcionados por Scaffold
        Column(
            modifier = Modifier
                .padding(paddingValues)  // Utilizamos el paddingValues para evitar superposiciones
                .padding(16.dp)  // Padding adicional para la UI interna
        ) {
            resource?.let {
                Text(text = it.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.editResource() }) {
                    Text("Edit Resource")
                }
            } ?: run {
                Text("No Resource Selected", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
