package com.probuildx.construtechapp.ui.projects

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

class ProjectFormFragment : Fragment() {

    private lateinit var viewModel: ProjectFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProjectFormViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                ProjectFormScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectFormScreen(viewModel: ProjectFormViewModel) {
    var projectName by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create / Edit Project") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveProject(projectName, projectDescription)
            }) {
                Icon(Icons.Default.Save, contentDescription = "Save Project")
            }
        }
    ) { paddingValues ->  // Usamos paddingValues proporcionado por Scaffold
        Column(
            modifier = Modifier
                .padding(paddingValues)  // Aplicamos el padding del Scaffold
                .padding(16.dp)  // Padding adicional
        ) {
            OutlinedTextField(
                value = projectName,
                onValueChange = { projectName = it },
                label = { Text("Project Name") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = projectDescription,
                onValueChange = { projectDescription = it },
                label = { Text("Project Description") }
            )
        }
    }
}
