package com.probuildx.construtechapp.ui.personnel

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
import com.probuildx.construtechapp.domain.personnel.Worker
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class WorkerListFragment : Fragment() {

    private lateinit var viewModel: WorkerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(WorkerViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                WorkerListScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkerListScreen(viewModel: WorkerViewModel) {
    val workers by viewModel.workers.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Workers") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.createNewWorker() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Worker")
            }
        }
    ) { paddingValues ->  // Usamos paddingValues proporcionado por Scaffold
        LazyColumn(
            contentPadding = paddingValues,  // Aplicamos el padding del Scaffold
            modifier = Modifier.fillMaxSize()
        ) {
            items(workers) { worker ->
                WorkerItem(worker, onWorkerClick = { viewModel.onWorkerSelected(it) })
            }
        }
    }
}

@Composable
fun WorkerItem(worker: Worker, onWorkerClick: (Worker) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onWorkerClick(worker) }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = worker.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = worker.role.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
