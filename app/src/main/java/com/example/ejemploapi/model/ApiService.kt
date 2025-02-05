import com.example.ejemploapi.model.Language
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

public interface ApiService {
    @Headers(
        "Authorization: Bearer a13bfad9c4535d7b15a803c8383e3a8c",
        "Content-Type: application/json"
    )
    @GET("languages")
    suspend fun getLanguages(): List<Language>
}

