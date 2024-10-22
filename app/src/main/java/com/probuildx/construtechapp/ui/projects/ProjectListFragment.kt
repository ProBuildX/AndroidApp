package com.probuildx.construtechapp.ui.projects

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
import com.probuildx.construtechapp.domain.projects.Project
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class ProjectListFragment : Fragment() {

    private lateinit var viewModel: ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                ProjectListScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListScreen(viewModel: ProjectViewModel) {
    val projects by viewModel.projects.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Projects") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.createNewProject() }) {
                Icon(Icons.Default.Add, contentDescription = "Create Project")
            }
        }
    ) { paddingValues ->  // Utilizamos paddingValues proporcionado por el Scaffold
        LazyColumn(
            contentPadding = paddingValues,  // Aplicamos el paddingValues a LazyColumn
            modifier = Modifier.fillMaxSize()
        ) {
            items(projects) { project ->
                ProjectItem(project, onProjectClick = { viewModel.onProjectSelected(it) })
            }
        }
    }
}

@Composable
fun ProjectItem(project: Project, onProjectClick: (Project) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProjectClick(project) }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = project.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = project.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}