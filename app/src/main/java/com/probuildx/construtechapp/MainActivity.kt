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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Image
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme

data class Worker(val name: String, val role: String)
data class Team(val name: String, val members: List<Worker>)
data class Resource(val name: String, val quantity: Int)
data class Task(val title: String, val description: String)

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
                    composable("workers_and_teams") {
                        WorkersAndTeamsScreen(navController)
                    }
                    // Añadir las rutas faltantes:
                    composable("resource_management") {
                        ResourceManagementScreen(navController)
                    }
                    composable("tasks") {
                        TasksScreen(navController)
                    }
                    composable("add_project") {
                        AddProjectScreen(navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Projects") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_project") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Project")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF7F4F3)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Construtech",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Welcome Back!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "USER", fontSize = 16.sp, fontWeight = FontWeight.Light)
            Spacer(modifier = Modifier.height(16.dp))

            ProjectCard(
                title = "Office building in San Isidro",
                description = "Modern office building in the heart of San Isidro...",
                onClick = { navController.navigate("project_details/Office building in San Isidro") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            ProjectCard(
                title = "Road Maintenance in San Borja",
                description = "Road maintenance project to improve traffic flow in San Borja...",
                onClick = { navController.navigate("project_details/Road Maintenance in San Borja") }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("add_project") },
                colors = ButtonDefaults.buttonColors(Color(0xFFFFA726)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "NEW PROJECT", fontSize = 16.sp)
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
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
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
                    icon = { Icon(Icons.Filled.Info, contentDescription = "Project Profile") }
                )
                DetailOption(
                    title = "Workers & Teams",
                    icon = { Icon(Icons.Filled.Group, contentDescription = "Workers & Teams") },
                    onClick = { navController.navigate("workers_and_teams") }
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
fun DetailOption(title: String, onClick: (() -> Unit)? = null, icon: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .size(120.dp)
            .clickable { onClick?.invoke() },
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
fun WorkersAndTeamsScreen(navController: NavController) {
    // Datos de ejemplo
    val workers = listOf(
        Worker(name = "John Doe", role = "Engineer"),
        Worker(name = "Jane Smith", role = "Architect"),
        Worker(name = "Alice Johnson", role = "Project Manager"),
        Worker(name = "Bob Brown", role = "Foreman")
    )

    val teams = listOf(
        Team(name = "Team Alpha", members = workers.subList(0, 2)),
        Team(name = "Team Beta", members = workers.subList(2, 4))
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Workers & Teams") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3))
        ) {
            Text(
                text = "Workers",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Mostrar lista de trabajadores
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(workers) { worker ->
                    WorkerCard(worker)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Teams",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Mostrar lista de equipos
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(teams) { team ->
                    TeamCard(team)
                }
            }
        }
    }
}

@Composable
fun WorkerCard(worker: Worker) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${worker.name}", fontWeight = FontWeight.Bold)
            Text(text = "Role: ${worker.role}")
        }
    }
}

@Composable
fun TeamCard(team: Team) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Team: ${team.name}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Members:")
            team.members.forEach { member ->
                Text(text = "- ${member.name} (${member.role})")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourceManagementScreen(navController: NavController) {
    val resources = listOf(
        Resource(name = "Cement", quantity = 50),
        Resource(name = "Steel Bars", quantity = 100),
        Resource(name = "Wood", quantity = 200)
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resource Management") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3))
        ) {
            Text(
                text = "Resources",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(resources) { resource ->
                    ResourceCard(resource)
                }
            }
        }
    }
}

@Composable
fun ResourceCard(resource: Resource) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Resource: ${resource.name}", fontWeight = FontWeight.Bold)
            Text(text = "Quantity: ${resource.quantity}")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(navController: NavController) {
    val tasks = listOf(
        Task(title = "Foundation", description = "Digging and setting up the foundation."),
        Task(title = "Framing", description = "Building the framework of the building."),
        Task(title = "Roofing", description = "Installing the roof.")
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tasks") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3))
        ) {
            Text(
                text = "Tasks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(tasks) { task ->
                    TaskCard(task)
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Task: ${task.title}", fontWeight = FontWeight.Bold)
            Text(text = "Description: ${task.description}")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add New Project") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("project_list")
            }) {
                Icon(Icons.Default.Save, contentDescription = "Save Project")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Add New Project",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = { /* Lógica para seleccionar imagen */ },
                    colors = ButtonDefaults.buttonColors(Color(0xFFd9d9d9)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Image,
                        contentDescription = "Select Image",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )
                }

                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = budget,
                    onValueChange = { budget = it },
                    label = { Text("Budget") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
