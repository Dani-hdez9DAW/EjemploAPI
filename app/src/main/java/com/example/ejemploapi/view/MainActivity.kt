package com.example.ejemploapi.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejemploapi.model.Language
import com.example.ejemploapi.model.LanguagesRepository
import com.example.ejemploapi.ui.theme.EjemploAPITheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploAPITheme {
                // Usamos Scaffold para estructurar la pantalla con un TopAppBar
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Idiomas disponibles") })
                    }
                ) { paddingValues ->
                    LanguagesScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

/**
 *
 * LanguagesScreen: Pantalla que muestra la lista de idiomas.
 * Realiza la petición a la API y muestra un indicador de carga o error si es necesario.
 *
 */

@Composable
fun LanguagesScreen(modifier: Modifier = Modifier) {
    // Estado que almacena la lista de idiomas
    var languages by remember { mutableStateOf<List<Language>>(emptyList()) }
    // Estado que indica si se está cargando la información
    var loading by remember { mutableStateOf(false) }
    // Estado para almacenar un posible mensaje de error
    var errorMessage by remember { mutableStateOf("") }
    // Obtenemos el scope para lanzar corrutinas en Compose


    // Lanzamos la petición al iniciarse la pantalla
    // LaunchedEffect se ejecuta una única vez al iniciar el Composable
    LaunchedEffect(Unit) {
        loading = true // Indicamos que se inicia la carga
        try {
            // Llamada a la función del Repository para obtener la lista de idiomas
            languages = LanguagesRepository.fetchLanguages()
        } catch (e: Exception) {
            // En caso de error, se almacena el mensaje de error
            errorMessage = e.message ?: "Error desconocido"
        } finally {
            // Finaliza la carga
            loading = false
        }
    }

    // Estructura principal de la pantalla: una columna que ocupa todo el tamaño disponible
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Se evalúan los estados para mostrar el contenido adecuado
        when {
            loading -> {
                // Mientras se carga la información, se muestra un indicador de progreso centrado
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            // Si hay un error, se muestra el mensaje de error
            errorMessage.isNotEmpty() -> {
                Text(text = "Error: $errorMessage", color = MaterialTheme.colorScheme.error)
            }
            else -> {
                // Cuando se han obtenido los datos, se muestra la lista de idiomas utilizando LazyColumn
                LazyColumn {
                    // Para cada idioma en la lista se invoca el Composable LanguageItem
                    items(languages) { language ->
                        LanguageItem(language)
                    }
                }
            }
        }
    }
}

/**
 * LanguageItem: Composable que muestra la información de un idioma.
 * Se muestra dentro de una Card con un Row que contiene el código y el nombre del idioma.
 */
@Composable
fun LanguageItem(language: Language) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = "${language.code} - ${language.name}")
        }
    }
}