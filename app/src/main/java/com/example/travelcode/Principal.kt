package com.example.travelcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val lugares = ArrayList<LugarTuristico>()

        lugares.add(LugarTuristico(
            "Guatapé",
            "Uno de los pueblos más coloridos de Colombia, que se refleja en forma de zócalos y calles llenas de vida.",
            R.drawable.imagen1,
            4.0,
            "19°C",
            "Departamento de Antioquia, a 2 horas de Medellín.",
            "Parque principal de Guatapé, Piedra del Peñol, Embalse Guatapé-Peñol, Calle del Recuerdo, Plazoleta del Zócalo, Malecón de Guatapé, entre otros."
        ))
        lugares.add(LugarTuristico(
            "Santa Marta",
            "La ciudad más antigua de América continental; en una montaña de casi 19.000 pies de altura, cuyas bases se sumergen en un mar tropical.",
            R.drawable.imagen2,
            3.5,
            "32°C",
            "Departamento del Magdalena, en la costa Caribe y a pocos kilómetros de la Sierra Nevada.",
            "El Rodadero, Playa Blanca, Bello Horizonte, Pozos Colorados, Parque Nacional Natural Tayrona, Museo del Oro Tairona, entre otros."
        ))
        lugares.add(LugarTuristico(
            "Bogotá, D.C.",
            "Capital de la República de Colombia y el principal destino turístico del país. Epicentro de diversas actividades y eventos de nivel internacional.",
            R.drawable.imagen3,
            2.0,
            "13°C",
            "Departamento del Magdalena, en la costa Caribe y a pocos kilómetros de la Sierra Nevada.",
            "Cerro de Monserrate, Barrio La Candelaria, Usaquen, Centro Internacional, Parque Central, Simón Bolívar, Museo del Oro,  Museo Botero, Maloka, entre otros."
        ))
        lugares.add(LugarTuristico(
            "Santiago de Cali",
            "La Sultana del Valle o Sucursal del Cielo, es una ciudad con grandes espacios de valor histórico y de recreación y diversión nocturna y diurna.",
            R.drawable.imagen4_1,
            5.0,
            "28°C",
            "Departamento de Valle del Cauca, al occidente de Colombia.",
            "Cerro de Cristo Rey, Zoológico de Cali, Museo de arte moderno La Tertulia, Iglesia la Ermita, Boulevard del Río Cali, entre otros."
        ))
        lugares.add(LugarTuristico(
            "Cartagena de Indias",
            "La perla colonial de Colombia o La heroica, es una ciudad soñada, una de las más bellas y mejor conservadas de América.",
            R.drawable.imagen5_1,
            4.5,
            "32°C",
            "Departamento de Bolívar, al norte de Colombia y a orillas del Mar Caribe.",
            "Castillo San Felipe de Barajas, Murallas de Cartagena, Torre del Reloj, Convento de La Popa, Plaza de la Aduana, entre otros."
        ))

        val adapter = CustomAdapter(lugares)
        recyclerView.adapter = adapter
    }

    //Metodo para mostrar y ocultar el menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return true
    }

    //Metpdo para asignar las funciones correspondientes a los items del menu
    override fun onOptionsItemSelected(itemConfig : MenuItem) : Boolean {
        val id = itemConfig.itemId //Obtenemos el item que se esta seleccionando
        if (id == R.id.itemConfiguracion){
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(itemConfig)
    }

    override fun onResume() {
        super.onResume()
        val preferencias = PreferenceManager.getDefaultSharedPreferences(this)
        val notificacionesActivadas : Boolean = preferencias.getBoolean("sync", false)
        val sonidoNotificaciones : Boolean = preferencias.getBoolean("attachment", false)
        Toast.makeText(this, "Checkeado: "+notificacionesActivadas + "y" + sonidoNotificaciones, Toast.LENGTH_SHORT).show()
    }
}
