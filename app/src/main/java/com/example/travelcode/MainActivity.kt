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

        lugares.add(LugarTuristico("Guatapé", "Uno de los pueblos más coloridos de Colombia, que se refleja en forma de zócalos y calles llenas de vida.", R.drawable.imagen1, 4.0, temperatura = "19°C"))
        lugares.add(LugarTuristico("Santa Marta", "La ciudad más antigua de América continental; en una montaña de casi 19.000 pies de altura, cuyas bases se sumergen en un mar tropical.", R.drawable.imagen2, 3.5, temperatura = "32°C"))
        lugares.add(LugarTuristico("Bogotá, D.C.", "Capital de la República de Colombia y el principal destino turístico del país. Epicentro de diversas actividades y eventos de nivel internacional.", R.drawable.imagen3, 2.0, temperatura = "13°C"))
        lugares.add(LugarTuristico("Santiago de Cali", "La Sultana del Valle o Sucursal del Cielo, es una ciudad con grandes espacios de valor histórico y de recreación y diversión nocturna y diurna.", R.drawable.imagen4_1, 5.0, temperatura = "28°C"))
        lugares.add(LugarTuristico("Cartagena de Indias", "La perla colonial de Colombia o La heroica, es una ciudad soñada, una de las más bellas y mejor conservadas de América.", R.drawable.imagen5_1, 4.5, temperatura = "32°C"))

        val adapter = CustomAdapter(lugares)
        recyclerView.adapter = adapter
    }

     fun intento(view: View): Intent {
        val intent : Intent = Intent(this, MainActivity2::class.java)
        return intent
    }
}