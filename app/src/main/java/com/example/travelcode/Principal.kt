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
    private val lugares = mutableListOf<LugarTuristico>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)
        obtenerData()
    }

    //Inciar una lista con los lugares turisticos
    private fun iniciarRecyclerView(){
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(lugares)
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
    private fun obtenerData(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiInterface::class.java).getData()
            val respuesta = call.body()

            //runOnUiThread para volver al hilo principal
            runOnUiThread {
                if(call.isSuccessful){
                    lugares.clear() //Se limpia la lista
                    //Añade la respuesta(lista o json) del servidor a la lista
                    lugares.addAll(respuesta ?: emptyList())
                    iniciarRecyclerView()
                } else{ notificacionError() } //Si pasa algun error al traer la data
            }
        }
    }

    //Funcion por si genera algun error al traer la data del servidor
    private fun notificacionError(){
        Toast.makeText(this, "Servidor Fallido", Toast.LENGTH_SHORT).show()
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

    /*override fun onResume() {
        super.onResume()
        val preferencias = PreferenceManager.getDefaultSharedPreferences(this)
        val notificacionesActivadas : Boolean = preferencias.getBoolean("sync", false)
        val sonidoNotificaciones : Boolean = preferencias.getBoolean("attachment", false)
        Toast.makeText(this, "Checkeado: "+notificacionesActivadas + "y" + sonidoNotificaciones, Toast.LENGTH_SHORT).show()
        if(notificacionesActivadas){
            crearNotificacionChannel()
            crearNotificacion()
        }
        else if(notificacionesActivadas && sonidoNotificaciones){
            crearNotificacionChannel()
            crearNotificacionConSonido()
        }
    }

    fun crearNotificacionChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "NOTIFICACION",
                "TravelCode",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun crearNotificacion(){
        val builder = NotificationCompat.Builder(this, "NOTIFICACION")
        builder.setSmallIcon(R.drawable.icono_notificaciones)
        builder.setContentTitle("Notificacion Android")
        builder.setContentText("Usted ha recibido una notificación")
        builder.color = Color.BLUE
        builder.setTicker("Notificación Nueva")
        builder.priority = NotificationCompat.PRIORITY_HIGH

        val compat = NotificationManagerCompat.from(this)
        compat.notify(0, builder.build())
    }

    private fun crearNotificacionConSonido(){
        val builder = NotificationCompat.Builder(this, "NOTIFICACION")
        builder.setSmallIcon(R.drawable.icono_notificaciones)
        builder.setContentTitle("Notificacion Android")
        builder.setContentText("Usted ha recibido una notificación")
        builder.color = Color.BLUE
        builder.setTicker("Notificación Nueva")
        builder.priority = NotificationCompat.PRIORITY_HIGH

        builder.setDefaults(Notification.DEFAULT_SOUND)

        val compat = NotificationManagerCompat.from(this)
        compat.notify(0, builder.build())
    }*/
}
