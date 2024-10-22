package com.probuildx.construtechapp.ui.resources

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

class ResourceFormFragment : Fragment() {

    private lateinit var viewModel: ResourceFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ResourceFormViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                ResourceFormScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourceFormScreen(viewModel: ResourceFormViewModel) {
    var resourceName by remember { mutableStateOf("") }
    var resourceDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create / Edit Resource") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveResource(resourceName, resourceDescription)
            }) {
                Icon(Icons.Default.Save, contentDescription = "Save Resource")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)  // Padding adicional para espacio interno
        ) {
            OutlinedTextField(
                value = resourceName,
                onValueChange = { resourceName = it },
                label = { Text("Resource Name") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = resourceDescription,
                onValueChange = { resourceDescription = it },
                label = { Text("Resource Description") }
            )
        }
    }
}
