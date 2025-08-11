package app.dll.test.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
/**
 * Represents a Tehillim (Psalms) text entity to be stored in a Room database.
 * This entity also includes annotations for Gson serialization/deserialization,
 * useful when fetching data from a remote API.
 */
@Entity(tableName = "tehillim_texts")
public class TehillimEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("book")
    private String book;

    @SerializedName("language")
    private String language;

    @SerializedName("chapter")
    private int chapter;

    @SerializedName("content")
    private String content;

    public TehillimEntity(String book, String language, int chapter, String content) {
        this.book = book;
        this.language = language;
        this.chapter = chapter;
        this.content = content;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public int getChapter() { return chapter; }
    public void setChapter(int chapter) { this.chapter = chapter; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "TehillimEntity{" +
                "id=" + id +
                ", book='" + book + '\'' +
                ", language='" + language + '\'' +
                ", chapter=" + chapter +
                ", content='" + content + '\'' +
                '}';
    }
}
