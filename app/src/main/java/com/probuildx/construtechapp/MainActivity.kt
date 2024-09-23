package com.probuildx.construtechapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.probuildx.construtechapp.ui.theme.ConstrutechAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstrutechAppTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "project_list") {
                    composable("project_list") {
                        ProjectListScreen(navController)
                    }
                    composable("project_details/{projectTitle}") { backStackEntry ->
                        val projectTitle = backStackEntry.arguments?.getString("projectTitle") ?: ""
                        ProjectDetailScreen(projectTitle)
                    }
                    composable("add_project") {
                        AddProjectScreen(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF7F4F3)), // Color de fondo
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la aplicación
        Text(
            text = "Construtech",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Welcome Back!",
            fontSize = 20.sp, // Tamaño reducido
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "USER",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProjectCard(
            title = "Office building in San Isidro",
            description = "Construcción de un moderno edificio de oficinas en el corazón de San Isidro, diseñado para fomentar un ambiente de trabajo colaborativo. El edificio contará con espacios flexibles, áreas de descanso, y tecnología de punta para asegurar la comodidad y productividad de los ocupantes",
            onClick = { navController.navigate("project_details/Office building in San Isidro") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProjectCard(
            title = "Road Maintenance in San Borja",
            description = "Mantenimiento de carreteras en San Borja tiene como objetivo mejorar la infraestructura vial del distrito, garantizando un tránsito seguro y eficiente. Se llevarán a cabo trabajos de repavimentación, reparación de baches, y mejora de la señalización.",
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
//
//@Composable
//fun ProjectCard(title: String, description: String, onClick: () -> Unit) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = description, fontSize = 14.sp)
//        }
//    }
//}
//
//// Pantalla de detalles del proyecto
//@Composable
//fun ProjectDetailScreen(projectTitle: String) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .background(Color(0xFFF7F4F3)),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = projectTitle,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Muestra las opciones del proyecto
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
//            DetailOption(title = "Project Profile") { Icon(Icons.Filled.Info, contentDescription = "Project Profile") }
//            DetailOption(title = "Workers & Teams") { Icon(Icons.Filled.Group, contentDescription = "Workers & Teams") }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
//            DetailOption(title = "Resource Management") { Icon(Icons.Filled.Storage, contentDescription = "Resource Management") }
//            DetailOption(title = "Tasks") { Icon(Icons.Filled.Checklist, contentDescription = "Tasks") }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//            DetailOption(title = "Options") { Icon(Icons.Filled.Settings, contentDescription = "Options") }
//        }
//    }
//}
//
//@Composable
//fun DetailOption(title: String, icon: @Composable () -> Unit) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        modifier = Modifier.size(120.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            // Usar el ícono como composable
//            icon()
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
//        }
//    }
//}
//
//@Composable
//fun AddProjectScreen(navController: NavController) {
//    var title by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//    var address by remember { mutableStateOf("") }
//    var date by remember { mutableStateOf("") }
//    var budget by remember { mutableStateOf("") }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .background(Color(0xFFF7F4F3))
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Top
//        ) {
//            Text(
//                text = "Add New Project",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            // Botón para seleccionar una imagen (circular y más grande)
//            Button(
//                onClick = { /* Lógica para seleccionar imagen */ },
//                colors = ButtonDefaults.buttonColors(Color(0xFFd9d9d9)),
//                shape = RoundedCornerShape(50), // Círculo
//                modifier = Modifier
//                    .size(80.dp) // Tamaño más grande del botón
//                    .align(Alignment.CenterHorizontally) // Centrado
//                    .padding(bottom = 16.dp) // Espacio inferior
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Image, // Usa el ícono de imagen
//                    contentDescription = "Select Image",
//                    modifier = Modifier.size(40.dp), // Ajusta el tamaño del ícono
//                    tint = Color.Black // Cambia el color si es necesario
//                )
//            }
//
//            // Campos de entrada
//            TextField(
//                value = title,
//                onValueChange = { title = it },
//                label = { Text("Title") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//
//            TextField(
//                value = description,
//                onValueChange = { description = it },
//                label = { Text("Description") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//
//            TextField(
//                value = address,
//                onValueChange = { address = it },
//                label = { Text("Address") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//
//            TextField(
//                value = date,
//                onValueChange = { date = it },
//                label = { Text("Date") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//
//            TextField(
//                value = budget,
//                onValueChange = { budget = it },
//                label = { Text("Budget") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.weight(1f)) // Esto empuja el botón hacia abajo
//        }
//
//        // Botón para guardar en la parte inferior
//        Button(
//            onClick = { /* Lógica para guardar el proyecto */ },
//            colors = ButtonDefaults.buttonColors(Color(0xFFFFA726)),
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier
//                .align(Alignment.BottomCenter) // Pegado a la parte inferior
//                .fillMaxWidth()
//                .padding(vertical = 16.dp)
//        ) {
//            Text(text = "Save", fontSize = 16.sp)
//        }
//    }
//}
//
