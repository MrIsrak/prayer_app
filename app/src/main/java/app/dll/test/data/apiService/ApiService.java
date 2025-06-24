package app.dll.test.data.apiService;

import app.dll.test.data.dto.SefriaTextResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path; // Для использования {id}
import retrofit2.http.Query; // Для использования ?lang=he

public interface ApiService {
    @GET("texts/{book}.{chapter}")
    Call<SefriaTextResponse> getTehillimText(
            @Path("book") String bookName,
            @Path("chapter") int chapterNumber,
            @Query("lang") String language
    );

}
