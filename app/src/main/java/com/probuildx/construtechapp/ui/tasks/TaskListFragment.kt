package com.probuildx.construtechapp.ui.tasks

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
import com.probuildx.construtechapp.domain.tasks.Task
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class TaskListFragment : Fragment() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                TaskListScreen(viewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tasks") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.createNewTask() }) {
                Icon(Icons.Default.Add, contentDescription = "Create Task")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            // Iteramos sobre la lista de tareas
            items(tasks) { task ->
                TaskItem(task, onTaskClick = { viewModel.onTaskSelected(it) })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskClick: (Task) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTaskClick(task) }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = task.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
