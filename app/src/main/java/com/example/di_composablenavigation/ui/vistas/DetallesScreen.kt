package com.example.di_composablenavigation.ui.vistas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.di_composablenavigation.ui.datos.getJuegos


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetallesScreen(navController: NavController, text: String?) {
    ContenidoDetalles(navController, text)
}

@Composable
fun ContenidoDetalles(navController: NavController, text: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Detalles producto")
        text?.let {
            val juegos = getJuegos()
            juegos.find { it.esIgual(text) }?.let { it1 ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(25.dp)
                    ) {
                        Text(text = "Título: " + it1.nombre, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Plataforma: " + it1.plataforma, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Precio: " + it1.precio.toString() + "€", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = painterResource(id = it1.portada),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

            }

        }
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Volver Atrás")
        }
    }
}
