package com.example.travelcode

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Principal : AppCompatActivity() {

    //Lista mutable para guardar la informacion al traerla de un servidor web
    private val places = mutableListOf<TouristSpot>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)
        getData()
    }

    //Inciar una lista con los lugares turisticos
    private fun initRecyclerView(){
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(places)
        recyclerView.adapter = adapter
    }

    //Funcion para obtener hacer la consulta a un servicio web
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())//Convierte a json
            .baseUrl("https://api-travelcode.herokuapp.com/api/v1/Sites/") //Paso la url
            .build()
    }

    //Funcion para obtener la data de un servicio web
    private fun getData(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiInterface::class.java).getData()
            val response = call.body()

            //runOnUiThread para volver al hilo principal
            runOnUiThread {
                if(call.isSuccessful){
                    places.clear() //Se limpia la lista
                    //Añade la respuesta(lista o json) del servidor a la lista
                    places.addAll(response ?: emptyList())
                    initRecyclerView()
                } else{ notificationError() } //Si pasa algun error al traer la data
            }
        }
    }

    //Funcion por si genera algun error al traer la data del servidor
    private fun notificationError(){
        Toast.makeText(this,"Server error",Toast.LENGTH_SHORT).show()
    }

    //Metodo para mostrar y ocultar el menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return true
    }

    //Metodo para asignar las funciones correspondientes a los items del menu
    override fun onOptionsItemSelected(itemConfig : MenuItem) : Boolean {
        val id = itemConfig.itemId //Obtenemos el item que se esta seleccionando
        if (id == R.id.itemConfiguration){
            val intent = Intent(this, Configuration::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(itemConfig)
    }
}
