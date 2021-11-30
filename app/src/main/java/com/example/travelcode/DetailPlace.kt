package com.example.travelcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailPlace : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_place)

        //Instanciamos elemento en variable para mi TextView
        val titlePlace : TextView = findViewById(R.id.titlePlace)
        val imagePlace : ImageView = findViewById(R.id.imagePlace)
        val descriptionPlace : TextView = findViewById(R.id.descriptionPlace)
        val scorePlace : RatingBar = findViewById(R.id.scorePlace)
        val numScorePlace : TextView = findViewById(R.id.numScorePlace)
        val temperaturePlace : TextView = findViewById(R.id.temperaturePlace)
        val locationPlace: TextView = findViewById(R.id.locationPlace)
        val sitesPlace: TextView = findViewById(R.id.sitesPlace)

        //Creacion de un Bundle para obtener la info de mi Activity principal
        val bundle : Bundle? = intent.extras

        //Guardamos mi objeto en la variable lugar
        val lugar: TouristSpot = bundle?.getSerializable("info") as TouristSpot

        //Asignamos valores a los componentes de la interfaz
        Picasso.get().load(lugar.image).into(imagePlace)
        titlePlace.text = lugar.title
        descriptionPlace.text = lugar.descriptionLarge
        scorePlace.rating = lugar.score.toFloat()
        numScorePlace.text = lugar.score.toString()
        temperaturePlace.text = lugar.temperature
        locationPlace.text = lugar.location
        sitesPlace.text = lugar.recommendedSites
    }
}