package com.example.di_composablenavigation.ui.vistas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.di_composablenavigation.R
import com.example.di_composablenavigation.ui.datos.getJuegos


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetallesScreen(navController: NavController, text: String?) {

    Scaffold(topBar = {
        TopAppBar(
            actions = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF495D92)),
            title = {
                Text(text = "Detalles de juegos", color = Color.White)
            }
        )
    }) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_inicio),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            ContenidoDetalles(navController, text)
        }
    }

}

@Composable
fun ContenidoDetalles(navController: NavController, text: String?) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_inicio),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            text?.let {
                val juegos = getJuegos()
                juegos.find { it.esIgual(text) }?.let { it1 ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(25.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Título: ",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )
                                    Text(
                                        text = "Plataforma: ",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )
                                    Text(
                                        text = "Precio: ",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )
                                }
                                Column {
                                    Text(
                                        text = it1.nombre,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 24.sp
                                    )
                                    Text(
                                        text = it1.plataforma,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 24.sp
                                    )
                                    Text(
                                        text = it1.precio.toString() + "€",
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 24.sp
                                    )
                                }

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Image(
                                painter = painterResource(id = it1.portada),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(3.dp, Color.Black),
                                contentScale = ContentScale.Crop

                            )
                        }
                    }
                }
            }
        }
    }
}
