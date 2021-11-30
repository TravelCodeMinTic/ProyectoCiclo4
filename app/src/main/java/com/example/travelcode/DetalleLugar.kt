package com.example.travelcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetalleLugar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_lugar)

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
        Picasso.get().load(lugar.image).into(imagenTuristica)
        tituloLugar.text = lugar.title
        descripcionLugar.text = lugar.descriptionLarge
        puntuacionLugar.rating = lugar.score.toFloat()
        puntacionNum.text = lugar.score.toString()
        temperaturaLugar.text = lugar.temperature
        ubicacionLugar.text = lugar.location
        sitiosLugar.text = lugar.recommendedSites
    }
}