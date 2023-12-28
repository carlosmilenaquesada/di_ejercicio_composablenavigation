package com.example.di_composablenavigation.ui.vistas

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.di_composablenavigation.R
import com.example.di_composablenavigation.ui.controladores.AppScreens
import com.example.di_composablenavigation.ui.datos.getJuegos


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatalogoScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            actions = {
                IconButton(onClick = { navController.navigate(AppScreens.InicioScreen.route) }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White
                    )
                }
            },colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF495D92)),
            title = {
                Text(text = "Catálogo de juegos", color = Color.White)
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
            ContenidoCatalogo(navController)
        }
    }
}

@Composable
fun ContenidoCatalogo(navController: NavController) {
    val juegos = getJuegos()
    LazyVerticalGrid(columns = GridCells.Fixed(3),
        content = {
            items(juegos) { juego ->
                Card(
                    border = BorderStroke(2.dp, Color.Cyan),
                    shape = RoundedCornerShape(4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable(onClick = {
                            navController.navigate(
                                route = AppScreens.DetallesScreen.route + "/" + (juego.nombre + juego.plataforma)
                                    .replace(
                                        " ",
                                        ""
                                    )
                                    .lowercase()
                            )
                        }),
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = juego.portada),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = juego.nombre, textAlign = TextAlign.Center, maxLines = 1,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        })
}
