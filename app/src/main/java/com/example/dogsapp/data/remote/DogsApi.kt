package com.example.dogsapp.data.remote

import com.example.dogsapp.data.model.Dog
import retrofit2.http.GET

interface DogsApi {

    @GET("/randomdog")
    suspend fun getDog(): Dog

}