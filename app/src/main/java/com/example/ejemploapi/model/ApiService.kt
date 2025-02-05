import com.example.ejemploapi.model.Language
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

public interface ApiService {
    @Headers(
        "Authorization: Bearer a13bfad9c4535d7b15a803c8383e3a8c",
        "Content-Type: application/json"
    )
    @GET("languages")
    suspend fun getLanguages(): List<Language>
}
val retrofit = Retrofit.Builder()
    .baseUrl("https://ws.detectlanguage.com/0.2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)
