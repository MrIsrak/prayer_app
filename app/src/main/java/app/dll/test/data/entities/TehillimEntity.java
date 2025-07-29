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
public class TehillimEntity{
    @SerializedName("book")
    public String book;
    @SerializedName("language")
    public String language;
    @SerializedName("chapeter")
    public int chapter;
    @SerializedName("content")
    public String content;

    public TehillimEntity(String book, String language, int chapter, String content) {
        this.book = book;
        this.language = language;
        this.chapter = chapter;
        this.content = content;
    }


    public String getBook() {return book;}
    public String getLanguage() {return language;}
    public int getChapter() {return chapter;}
    public String getContent() {return content;}


    public void setContent(String content) {this.content = content;}
    public void setChapter(int chapter) {this.chapter = chapter;}
    public void setLanguage(String language) {this.language = language;}
    public void setBook(String book) {this.book = book;}

    @Override
    public String toString() {
        return "TehillimEntity{" +
                ", book='" + book + '\'' +
                ", language='" + language + '\'' +
                ", chapter=" + chapter +
                ", content='" + content + '\'' +
                '}';
    }
}
