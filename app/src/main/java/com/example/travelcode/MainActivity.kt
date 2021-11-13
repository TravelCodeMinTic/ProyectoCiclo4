package com.example.travelcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val lugares = ArrayList<LugarTuristico>()

        lugares.add(LugarTuristico("Guatapé, Antioquia", "Uno de los pueblos más coloridos de Colombia, que se refleja en forma de zócalos y calles llenas de vida.", R.drawable.imagen1, 4.0))
        lugares.add(LugarTuristico("Lugar Turistico #2", "Descripcion de mi lugar turistico el cual se va a investigar...", R.drawable.imagen2, 3.5))
        lugares.add(LugarTuristico("Lugar Turistico #3", "Descripcion de mi lugar turistico el cual se va a investigar...", R.drawable.imagen3, 2.0))
        lugares.add(LugarTuristico("Lugar Turistico #4", "Descripcion de mi lugar turistico el cual se va a investigar...", R.drawable.imagen4, 5.0))
        lugares.add(LugarTuristico("Lugar Turistico #5", "Descripcion de mi lugar turistico el cual se va a investigar...", R.drawable.imagen5, 4.5))

        val adapter = CustomAdapter(lugares)
        recyclerView.adapter = adapter
    }

     fun intento(view: View): Intent {
        val intent : Intent = Intent(this, MainActivity2::class.java)
        return intent
    }
}