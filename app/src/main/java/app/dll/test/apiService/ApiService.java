package app.dll.test.apiService;

import app.dll.test.pojo.SefariaTextResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path; // Для использования {id}
import retrofit2.http.Query; // Для использования ?lang=he

public interface ApiService {
    @GET("texts/{book}.{chapter}")
    Call<SefariaTextResponse> getTehillimText(
            @Path("book") String bookName,
            @Path("chapter") int chapterNumber,
            @Query("lang") String language
    );

}
