package com.example.ejemploapi.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers(
        "Authorization: Bearer a13bfad9c4535d7b15a803c8383e3a8c",
        "Content-Type: application/json"
    )
    @GET("languages")
    suspend fun getLanguages(): List<Language>
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://ws.detectlanguage.com/0.2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService: ApiService = retrofit.create(ApiService::class.java)

object LanguagesRepository {
    // Función suspend para obtener la lista de idiomas utilizando el apiService
    suspend fun fetchLanguages(): List<Language> {
        return apiService.getLanguages()
    }
}