package com.example.travelcode

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(var lista: ArrayList<LugarTuristico>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //Cuando entra a crear la RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_lugar, parent, false)
        return ViewHolder(v)
    }

    //Pobla cada elemento por separada de RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        fun  bindItems(data:LugarTuristico){
            //Inicializamos los componentes
            val tituloLugar: TextView = itemView.findViewById(R.id.tituloLugar)
            val descripcionLugar: TextView = itemView.findViewById(R.id.descripcionLugar)
            val imgLugar: ImageView = itemView.findViewById(R.id.imgLugar)
            val puntuacion: RatingBar = itemView.findViewById(R.id.puntuacion)

            //Pasamos los valores a cada componente de la interfaz
            tituloLugar.text = data.titulo
            descripcionLugar.text = data.descripcion
            Glide.with(itemView.context).load(data.imagen).into(imgLugar)
            puntuacion.rating = data.puntuacion.toFloat()

            //Evento cuando se le da en una tarjeta
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetalleLugar::class.java)
                val bundle = Bundle()

                bundle.putSerializable("info", data)

                intent.putExtras(bundle)
                itemView.context.startActivity(intent)
            }
        }

    }
}