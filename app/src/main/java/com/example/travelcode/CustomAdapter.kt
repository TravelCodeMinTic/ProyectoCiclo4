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
import com.squareup.picasso.Picasso

class CustomAdapter(private var listPlaces: List<TouristSpot>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //Cuando entra a crear la RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_place, parent, false)
        return ViewHolder(v)
    }

    //Pobla cada elemento por separada de RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(listPlaces[position])
    }

    override fun getItemCount(): Int {
        return listPlaces.size
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        fun  bindItems(data:TouristSpot){
            //Inicializamos los componentes
            val cardTitle: TextView = itemView.findViewById(R.id.cardTitle)
            val cardDescription: TextView = itemView.findViewById(R.id.cardDescription)
            val cardImage: ImageView = itemView.findViewById(R.id.cardImage)
            val cardScore: RatingBar = itemView.findViewById(R.id.cardScore)

            //Pasamos los valores a cada componente de la interfaz
            cardTitle.text = data.title
            cardDescription.text = data.descriptionShort
            Picasso.get().load(data.image).into(cardImage)
            cardScore.rating = data.score.toFloat()

            //Evento cuando se le da en una tarjeta
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailPlace::class.java)
                val bundle = Bundle()

                bundle.putSerializable("info", data)

                intent.putExtras(bundle)
                itemView.context.startActivity(intent)
            }
        }
    }
}