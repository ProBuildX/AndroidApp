package com.probuildx.construtechapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.input.KeyboardType
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
import com.probuildx.construtechapp.domain.resources.Resource
import com.probuildx.construtechapp.domain.resources.ResourceType
import com.probuildx.construtechapp.domain.tasks.Task
import com.probuildx.construtechapp.domain.tasks.TaskStatus
import com.probuildx.construtechapp.model.projects.ProjectViewModel
import com.probuildx.construtechapp.ui.theme.ConstructechAppTheme
import java.util.UUID

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
                    navController.popBackStack()
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
                                onTeamChange(worker, team.id)
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
fun ResourceManagementScreen(viewModel: ProjectViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var resourceToEdit by remember { mutableStateOf<Resource?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resource Management", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA726),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    resourceToEdit = null
                    showDialog = true
                },
                containerColor = Color(0xFFFFA726)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Resource", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3)),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Total Cost: $${viewModel.resources.sumOf { it.quantity * it.unitCost }}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF424242),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(viewModel.resources) { resource ->
                    ResourceCard(
                        resource,
                        onEdit = {
                            resourceToEdit = resource
                            showDialog = true
                        },
                        onDelete = { viewModel.removeResource(resource.id) }
                    )
                }
            }
        }
    }

    if (showDialog) {
        ResourceDialog(
            initialResource = resourceToEdit,
            onDismiss = { showDialog = false },
            onSave = { resource ->
                if (resourceToEdit == null) {
                    viewModel.addResource(resource)
                } else {
                    viewModel.updateResource(resource)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun ResourceCard(resource: Resource, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = resource.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF424242)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Type: ${resource.resourceType}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF757575)
                )
                Text(
                    text = "Quantity: ${resource.quantity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF757575)
                )
                Text(
                    text = "Unit Cost: $${resource.unitCost}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF757575)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Resource",
                        tint = Color(0xFFFFA726)
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Resource",
                        tint = Color(0xFFFF7043)
                    )
                }
            }
        }
    }
}

@Composable
fun ResourceDialog(
    initialResource: Resource?,
    onDismiss: () -> Unit,
    onSave: (Resource) -> Unit
) {
    var name by remember { mutableStateOf(initialResource?.name ?: "") }
    var description by remember { mutableStateOf(initialResource?.description ?: "") }
    var resourceType by remember { mutableStateOf(initialResource?.resourceType ?: ResourceType.MATERIAL) }
    var quantity by remember { mutableStateOf(initialResource?.quantity ?: 0) }
    var unitCost by remember { mutableStateOf(initialResource?.unitCost ?: 0.0) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = if (initialResource == null) "Add Resource" else "Edit Resource") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Resource Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Resource Type Dropdown
                Box {
                    OutlinedTextField(
                        value = resourceType.name,
                        onValueChange = {},
                        label = { Text("Type") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isDropdownExpanded = true },
                        readOnly = true
                    )
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        ResourceType.values().forEach { type ->
                            DropdownMenuItem(
                                onClick = {
                                    resourceType = type
                                    isDropdownExpanded = false
                                },
                                text = { Text(type.name) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = quantity.toString(),
                    onValueChange = { quantity = it.toIntOrNull() ?: 0 },
                    label = { Text("Quantity") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = unitCost.toString(),
                    onValueChange = { unitCost = it.toDoubleOrNull() ?: 0.0 },
                    label = { Text("Unit Cost") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val newResource = Resource(
                        id = initialResource?.id ?: UUID.randomUUID().toString(),
                        name = name,
                        description = description,
                        resourceType = resourceType,
                        quantity = quantity,
                        unitCost = unitCost
                    )
                    onSave(newResource)
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(viewModel: ProjectViewModel = viewModel()) {
    var showTaskDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasks") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA726),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    taskToEdit = null
                    showTaskDialog = true
                },
                containerColor = Color(0xFFFFA726)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF7F4F3)),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LazyColumn {
                items(viewModel.tasks) { task ->
                    TaskCard(
                        task = task,
                        onEdit = {
                            taskToEdit = task
                            showTaskDialog = true
                        },
                        onDelete = { viewModel.removeTask(task.id) },
                        onStatusChange = { newStatus ->
                            viewModel.changeTaskStatus(task.id, newStatus)
                        }
                    )
                }
            }
        }
    }

    if (showTaskDialog) {
        TaskDialog(
            initialTask = taskToEdit,
            onDismiss = { showTaskDialog = false },
            onSave = { task ->
                if (taskToEdit == null) {
                    viewModel.addTask(task)
                } else {
                    viewModel.updateTask(task)
                }
                showTaskDialog = false
            }
        )
    }
}

@Composable
fun TaskCard(
    task: Task,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onStatusChange: (TaskStatus) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Assigned Team: ${task.assignedTeam}", fontSize = 14.sp, color = Color.Gray)
                Text("Status: ${task.status}", fontSize = 14.sp, color = Color.Gray)
                Text("Dates: ${task.startDate} - ${task.endDate}", fontSize = 14.sp, color = Color.Gray)
            }
            Row {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Task")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Task")
                }
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Change Status")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        TaskStatus.values().forEach { status ->
                            DropdownMenuItem(
                                onClick = {
                                    onStatusChange(status)
                                    expanded = false
                                },
                                text = { Text(status.name) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskDialog(
    initialTask: Task?,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit
) {
    var title by remember { mutableStateOf(initialTask?.title ?: "") }
    var description by remember { mutableStateOf(initialTask?.description ?: "") }
    var assignedTeam by remember { mutableStateOf(initialTask?.assignedTeam ?: "") }
    var startDate by remember { mutableStateOf(initialTask?.startDate ?: "") }
    var endDate by remember { mutableStateOf(initialTask?.endDate ?: "") }
    var status by remember { mutableStateOf(initialTask?.status ?: TaskStatus.PENDING) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add/Edit Task") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = assignedTeam,
                    onValueChange = { assignedTeam = it },
                    label = { Text("Assigned Team") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = startDate,
                    onValueChange = { startDate = it },
                    label = { Text("Start Date") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = endDate,
                    onValueChange = { endDate = it },
                    label = { Text("End Date") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Dropdown for Task Status
                Box {
                    OutlinedTextField(
                        value = status.name,
                        onValueChange = {},
                        label = { Text("Status") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true },
                        readOnly = true
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        TaskStatus.values().forEach { taskStatus ->
                            DropdownMenuItem(
                                onClick = {
                                    status = taskStatus
                                    expanded = false
                                },
                                text = { Text(taskStatus.name) }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    Task(
                        id = initialTask?.id ?: UUID.randomUUID().toString(),
                        title = title,
                        description = description,
                        assignedTeam = assignedTeam,
                        startDate = startDate,
                        endDate = endDate,
                        status = status
                    )
                )
                onDismiss()
            }) {
                Text("Save", color = Color(0xFF6A1B9A))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel", color = Color.Gray)
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
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
                navController.popBackStack()
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
