package com.probuildx.construtechapp.ui.personnel

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

class HoursWorkedFragment : Fragment() {

    private lateinit var viewModel: HoursWorkedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HoursWorkedViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                HoursWorkedScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoursWorkedScreen(viewModel: HoursWorkedViewModel) {
    var workedHours by remember { mutableStateOf("") }
    val personnel by viewModel.selectedPersonnel.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Record Worked Hours") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveWorkedHours(workedHours.toIntOrNull() ?: 0)
            }) {
                Icon(Icons.Default.Save, contentDescription = "Save Worked Hours")
            }
        }
    ) { paddingValues ->  // Usamos paddingValues proporcionado por Scaffold
        Column(
            modifier = Modifier
                .padding(paddingValues)  // Aplicamos el padding del Scaffold
                .padding(16.dp)  // Padding adicional
        ) {
            personnel?.let {
                Text(text = "Recording hours for: ${it.name}", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = workedHours,
                    onValueChange = { workedHours = it },
                    label = { Text("Worked Hours") }
                )
            } ?: run {
                Text("No Personnel Selected", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
