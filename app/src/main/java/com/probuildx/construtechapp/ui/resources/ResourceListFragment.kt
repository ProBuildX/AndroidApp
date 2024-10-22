package com.probuildx.construtechapp.ui.resources

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
import com.probuildx.construtechapp.domain.resources.Resource
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class ResourceListFragment : Fragment() {

    private lateinit var viewModel: ResourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ResourceViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                ResourceListScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourceListScreen(viewModel: ResourceViewModel) {
    val resources by viewModel.resources.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resources") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.createNewResource() }) {
                Icon(Icons.Default.Add, contentDescription = "Create Resource")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(resources) { resource ->
                ResourceItem(resource, onResourceClick = { viewModel.onResourceSelected(it) })
            }
        }
    }
}

@Composable
fun ResourceItem(resource: Resource, onResourceClick: (Resource) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onResourceClick(resource) }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = resource.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = resource.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
