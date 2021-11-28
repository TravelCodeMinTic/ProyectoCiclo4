package com.example.travelcode

import java.io.Serializable

class LugarTuristico(
    var titulo: String,
    var descripcion: String,
    var imagen: Int,
    var puntuacion: Double,
    var temperatura: String,
    var ubicacion: String,
    var sitios: String
    ):Serializable