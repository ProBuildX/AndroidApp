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
import com.probuildx.construtechapp.domain.personnel.Team
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class TeamListFragment : Fragment() {

    private lateinit var viewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)

        activity?.setContent {
            ConstructechAppTheme {
                TeamListScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamListScreen(viewModel: TeamViewModel) {
    val teams by viewModel.teams.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Teams") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.createNewTeam() }) {
                Icon(Icons.Default.Add, contentDescription = "Add Team")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(teams) { team ->
                TeamItem(team, onTeamClick = { viewModel.onTeamSelected(it) })
            }
        }
    }
}

@Composable
fun TeamItem(team: Team, onTeamClick: (Team) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTeamClick(team) }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = team.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Members: ${team.members.size}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
