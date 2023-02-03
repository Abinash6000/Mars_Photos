package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com" // bse URL for the web service

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder() // to create a retrofit object
    .addConverterFactory(MoshiConverterFactory.create(moshi)) //  The converter tells Retrofit what to do with the data it gets back from the web service
    .baseUrl(BASE_URL) // Retrofit needs the base URI for the web service
    .build() // Finally, call build() to create the Retrofit object

interface MarsApiService { // defines how Retrofit talks to the web server using HTTP requests
    @GET("photos") // Use the @GET annotation to tell Retrofit that this is GET request, and specify an endpoint, for that web service method. In this case the endpoint is called photos. As mentioned, you will be using '/photos' endpoint.
    suspend fun getPhotos(): List<MarsPhoto> // When the getPhotos() method is invoked, Retrofit appends the endpoint photos to the base URL
}

object MarsApi { //to initialize the Retrofit service
    //This is the public singleton object that can be accessed from the rest of the app.
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}

