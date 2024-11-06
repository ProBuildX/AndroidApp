package com.probuildx.construtechapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.probuildx.construtechapp.domain.personnel.Team
import com.probuildx.construtechapp.domain.personnel.Worker
import com.probuildx.construtechapp.domain.projects.Project
import com.probuildx.construtechapp.model.projects.ProjectViewModel
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstructechAppTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "project_list") {
                    composable("project_list") {
                        ProjectListScreen(navController)
                    }
                    composable("project_details/{projectTitle}") { backStackEntry ->
                        val projectTitle = backStackEntry.arguments?.getString("projectTitle") ?: ""
                        ProjectDetailScreen(navController, projectTitle)
                    }
                    composable("add_project") {
                        AddProjectScreen(navController)
                    }
                    composable(
                        "project_profile/{projectTitle}/{description}/{address}/{date}/{budget}",
                        arguments = listOf(
                            navArgument("projectTitle") { type = NavType.StringType },
                            navArgument("description") { type = NavType.StringType },
                            navArgument("address") { type = NavType.StringType },
                            navArgument("date") { type = NavType.StringType },
                            navArgument("budget") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        ProjectProfileScreen(
                            projectTitle = backStackEntry.arguments?.getString("projectTitle") ?: "",
                            initialDescription = backStackEntry.arguments?.getString("description") ?: "",
                            initialAddress = backStackEntry.arguments?.getString("address") ?: "",
                            initialDate = backStackEntry.arguments?.getString("date") ?: "",
                            initialBudget = backStackEntry.arguments?.getString("budget") ?: "",
                            navController = navController
                        )
                    }
                    composable("workers_teams") {
                        val viewModel: ProjectViewModel = viewModel()
                        WorkersTeamsScreen(viewModel = viewModel)
                    }
                    composable("resource_management") {
                        ResourceManagementScreen()
                    }
                    composable("tasks") {
                        TasksScreen()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListScreen(navController: NavController, viewModel: ProjectViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Projects", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA726),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_project") },
                containerColor = Color(0xFFFFA726)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Project", tint = Color.White)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF7F4F3)),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.projects) { project ->
                ProjectCard(
                    title = project.title,
                    description = project.description,
                    onClick = {
                        navController.navigate(
                            "project_details/${project.title}"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun ProjectCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
            .shadow(6.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, fontSize = 14.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(navController: NavController, projectTitle: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Project Details") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = projectTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DetailOption(
                    title = "Project Profile",
                    icon = { Icon(Icons.Filled.Info, contentDescription = "Project Profile") },
                    onClick = {
                        navController.navigate(
                            "project_profile/${projectTitle}/Description Placeholder/Address Placeholder/Date Placeholder/Budget Placeholder"
                        )
                    }
                )
                DetailOption(
                    title = "Workers & Teams",
                    icon = { Icon(Icons.Filled.Group, contentDescription = "Workers & Teams") },
                    onClick = { navController.navigate("workers_teams") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DetailOption(
                    title = "Resource Management",
                    icon = { Icon(Icons.Filled.Storage, contentDescription = "Resource Management") },
                    onClick = { navController.navigate("resource_management") }
                )
                DetailOption(
                    title = "Tasks",
                    icon = { Icon(Icons.Filled.Checklist, contentDescription = "Tasks") },
                    onClick = { navController.navigate("tasks") }
                )
            }
        }
    }
}

@Composable
fun DetailOption(title: String, icon: @Composable () -> Unit, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .size(120.dp)
            .clickable(onClick = onClick)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            icon()
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectProfileScreen(
    projectTitle: String,
    initialDescription: String,
    initialAddress: String,
    initialDate: String,
    initialBudget: String,
    navController: NavController,
    viewModel: ProjectViewModel = viewModel()
) {
    var title by remember { mutableStateOf(projectTitle) }
    var description by remember { mutableStateOf(initialDescription) }
    var address by remember { mutableStateOf(initialAddress) }
    var date by remember { mutableStateOf(initialDate) }
    var budget by remember { mutableStateOf(initialBudget) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Project Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA726),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val updatedProject = Project(
                        title = title,
                        description = description,
                        address = address,
                        date = date,
                        budget = budget
                    )
                    viewModel.saveProject(updatedProject)
                    navController.popBackStack() // Regresar a Project Details
                },
                containerColor = Color(0xFFFFA726)
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save Changes", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3)),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Edit Project Details",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF424242)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Project Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Date") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = budget,
                onValueChange = { budget = it },
                label = { Text("Budget") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourceManagementScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Resource Management") }) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Resource Management Details")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkersTeamsScreen(viewModel: ProjectViewModel) {
    WorkersTeamsContent(
        teams = viewModel.teams,
        onTeamChange = { worker, newTeamId ->
            viewModel.changeWorkerTeam(worker, newTeamId)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkersTeamsContent(teams: List<Team>, onTeamChange: (Worker, String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Workers & Teams") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA726),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3)),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Recorrer cada equipo para mostrar trabajadores
            teams.forEach { team ->
                item {
                    TeamHeader(teamName = team.name)
                }
                items(team.members) { worker ->
                    WorkerCard(worker = worker, teams = teams, onTeamChange = onTeamChange)
                }
            }
        }
    }
}

@Composable
fun TeamHeader(teamName: String) {
    Text(
        text = teamName,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF424242),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkerCard(worker: Worker, teams: List<Team>, onTeamChange: (Worker, String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedTeam by remember { mutableStateOf(teams.find { team -> team.members.contains(worker) }?.name ?: "") }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = worker.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Role: ${worker.role}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Worked Hours: ${worker.workedHours}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Team: $selectedTeam",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Change Team")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    teams.forEach { team ->
                        DropdownMenuItem(
                            text = { Text(team.name) },
                            onClick = {
                                selectedTeam = team.name
                                onTeamChange(worker, team.id) // Callback para actualizar el equipo
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Tasks") }) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Tasks Details")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectScreen(navController: NavController, viewModel: ProjectViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add New Project") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val newProject = Project(
                    title = title,
                    description = description,
                    address = address,
                    date = date,
                    budget = budget
                )
                viewModel.saveProject(newProject)
                navController.popBackStack() // Regresar a la lista de proyectos
            }) {
                Icon(Icons.Default.Save, contentDescription = "Save Project")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3))
        ) {
            OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = budget, onValueChange = { budget = it }, label = { Text("Budget") }, modifier = Modifier.fillMaxWidth())
        }
    }
}
