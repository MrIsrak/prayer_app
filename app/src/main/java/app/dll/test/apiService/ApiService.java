package app.dll.test.apiService;

import app.dll.test.pojo.SefariaTextResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path; // Для использования {id}
import retrofit2.http.Query; // Для использования ?lang=he

public interface ApiService {

    // Метод для получения текста по названию и языку
    // Пример: https://www.sefaria.org/api/texts/Psalms.29?lang=he
    @GET("texts/{book}.{chapter}") // {book} и {chapter} - это заменяемые части URL
    Call<SefariaTextResponse> getSefariaText(
            @Path("book") String bookName,    // Например, "Psalms"
            @Path("chapter") int chapterNumber, // Например, 29
            @Query("lang") String language    // Например, "he" или "en"
    );

}
