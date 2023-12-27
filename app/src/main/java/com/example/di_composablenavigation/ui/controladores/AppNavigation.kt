package com.example.di_composablenavigation.ui.controladores

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.di_composablenavigation.ui.vistas.AutenticacionScreen
import com.example.di_composablenavigation.ui.vistas.CatalogoScreen
import com.example.di_composablenavigation.ui.vistas.DetallesScreen
import com.example.di_composablenavigation.ui.vistas.InicioScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.InicioScreen.route) {
        composable(route = AppScreens.InicioScreen.route) {
            InicioScreen(navController)
        }

        composable(route = AppScreens.AutenticacionScreen.route) {
            AutenticacionScreen(navController)
        }

        composable(route = AppScreens.CatalogoScreen.route) {
            CatalogoScreen(navController)
        }

        composable(
            route = AppScreens.DetallesScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) {
            DetallesScreen(navController, it.arguments?.getString("text"))

        }

    }
}
