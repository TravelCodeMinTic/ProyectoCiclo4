package com.example.travelcode

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(".")
    suspend fun getData(): Response<List<LugarTuristico>>
}