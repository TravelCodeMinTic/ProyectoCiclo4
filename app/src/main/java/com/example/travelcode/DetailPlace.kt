package com.example.travelcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.R.id
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.content.pm.PackageManager

import android.content.pm.ApplicationInfo

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
        val btnMap: View = findViewById(R.id.btnMap)

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

        btnMap.setOnClickListener { view ->
            if (isGoogleMapsInstalled()) {
                val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194")
                //val gmmIntentUri = Uri.parse("geo:${lugar.lat},${lugar.long}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            } else {
                val browserIntent = Intent(ACTION_VIEW, Uri.parse("https://maps.google.com?q=51.03841,-114.01679"))
                //val browserIntent = Intent(ACTION_VIEW, Uri.parse("https://maps.google.com?q=${lugar.lat},${lugar.long}"))
                startActivity(browserIntent)
            }
        }
    }

    fun isGoogleMapsInstalled(): Boolean {
        return try {
            val info = packageManager.getApplicationInfo("com.google.android.apps.maps", 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}