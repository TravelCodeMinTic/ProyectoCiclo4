package com.example.travelcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Instanciamos elemento en variable para mi TextView
        val tituloLugar : TextView = findViewById(R.id.tituloLugar)
        val imagenTuristica : ImageView = findViewById(R.id.imagenTuristica)
        val descripcionLugar : TextView = findViewById(R.id.descripcionLugar)
        val puntuacionLugar : RatingBar = findViewById(R.id.puntuacionLugar)
        val puntacionNum : TextView = findViewById(R.id.puntuacionNum)
        val temperaturaLugar : TextView = findViewById(R.id.temperaturaLugar)
        val ubicacionLugar: TextView = findViewById(R.id.ubicacionLugar)
        val sitiosLugar: TextView = findViewById(R.id.sitiosLugar)

        //Creacion de un Bundle para obtener la info de mi Activity principal
        val bundle : Bundle? = intent.extras

        //Guardamos mi objeto en la variable lugar
        val lugar: LugarTuristico = bundle?.getSerializable("info") as LugarTuristico

        //Asignamos valores a los componentes de la interfaz
        Glide.with(this).load(lugar.imagen).into(imagenTuristica)
        tituloLugar.text = lugar.titulo
        descripcionLugar.text = lugar.descripcion
        puntuacionLugar.rating = lugar.puntuacion.toFloat()
        puntacionNum.text = lugar.puntuacion.toString()
        temperaturaLugar.text = lugar.temperatura
        ubicacionLugar.text = lugar.ubicacion
        sitiosLugar.text = lugar.sitios
    }
}