package com.example.ejemploapi.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
  @Headers({
          "Authorization: Bearer <a13bfad9c4535d7b15a803c8383e3a8c>",
          "Content-Type: application/json"
  })
  @GET("languages")
  Call<List<Language>> getLanguages();
}

// Otras peticiones...