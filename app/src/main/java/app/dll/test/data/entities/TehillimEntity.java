package app.dll.test.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tehillim_texts")
public class TehillimEntity {
    @PrimaryKey
    public int id;
    @SerializedName("book")
    public String book;
    @SerializedName("language")
    public String language;
    @SerializedName("chapeter")
    public int chapter;
    @SerializedName("content")
    public String content;

    public TehillimEntity(int id, String book, String language, int chapter, String content) {
        this.id = id;
        this.book = book;
        this.language = language;
        this.chapter = chapter;
        this.content = content;
    }


    public int getId() {return id;}
    public String getBook() {return book;}
    public String getLanguage() {return language;}
    public int getChapter() {return chapter;}
    public String getContent() {return content;}


    public void setContent(String content) {this.content = content;}
    public void setChapter(int chapter) {this.chapter = chapter;}
    public void setLanguage(String language) {this.language = language;}
    public void setBook(String book) {this.book = book;}
    public void setId(int id) {this.id = id;}

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
