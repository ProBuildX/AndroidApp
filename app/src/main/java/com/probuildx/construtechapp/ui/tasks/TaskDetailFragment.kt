package com.probuildx.construtechapp.ui.tasks

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

class TaskDetailFragment : Fragment() {

    private lateinit var viewModel: TaskDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TaskDetailViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                TaskDetailScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(viewModel: TaskDetailViewModel) {
    val task by viewModel.selectedTask.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Task Details") })
        }
    ) { paddingValues ->  // Usamos paddingValues aquí
        Column(
            modifier = Modifier
                .padding(paddingValues)  // Aplica el padding del Scaffold
                .padding(16.dp)  // Padding adicional
        ) {
            task?.let {
                Text(text = it.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.updateTaskStatus() }) {
                    Text("Update Task Status")
                }
            } ?: run {
                Text("No Task Selected", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
