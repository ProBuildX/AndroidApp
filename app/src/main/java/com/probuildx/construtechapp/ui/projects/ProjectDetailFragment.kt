package com.probuildx.construtechapp.ui.projects

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

class ProjectDetailFragment : Fragment() {

    private lateinit var viewModel: ProjectDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProjectDetailViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                ProjectDetailScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(viewModel: ProjectDetailViewModel) {
    val project by viewModel.selectedProject.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Project Details") })
        }
    ) { paddingValues ->  // Usamos paddingValues proporcionados por Scaffold
        Column(
            modifier = Modifier
                .padding(paddingValues)  // Aplicamos padding del Scaffold
                .padding(16.dp)  // Padding adicional
        ) {
            project?.let {
                Text(text = it.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.editProject() }) {
                    Text("Edit Project")
                }
            } ?: run {
                Text("No Project Selected", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
