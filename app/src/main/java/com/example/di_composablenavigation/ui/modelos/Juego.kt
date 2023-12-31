package com.example.di_composablenavigation.ui.modelos

data class Juego(var nombre: String, var plataforma: String, var portada: Int, var precio: Float) {
    fun esIgual(identificador: String): Boolean {
        return (this.nombre + this.plataforma).replace(" ", "").lowercase() == identificador
    }
}

