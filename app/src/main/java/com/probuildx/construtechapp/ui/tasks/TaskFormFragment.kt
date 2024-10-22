package com.probuildx.construtechapp.ui.tasks

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

class TaskFormFragment : Fragment() {

    private lateinit var viewModel: TaskFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                TaskFormScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(viewModel: TaskFormViewModel) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create / Edit Task") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveTask(taskTitle, taskDescription)
            }) {
                Icon(Icons.Default.Save, contentDescription = "Save Task")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = taskTitle,
                onValueChange = { taskTitle = it },
                label = { Text("Task Title") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = { Text("Task Description") }
            )
        }
    }
}
