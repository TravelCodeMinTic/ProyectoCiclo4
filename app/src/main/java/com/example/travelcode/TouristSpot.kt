package com.example.travelcode

import java.io.Serializable

class TouristSpot(
    val id:String,
    val title: String,
    val descriptionShort: String,
    val descriptionLarge: String,
    val location: String,
    val temperature: String,
    val recommendedSites: String,
    val image: String,
    val score: Double,
):Serializable