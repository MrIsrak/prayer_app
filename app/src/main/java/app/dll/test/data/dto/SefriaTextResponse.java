package app.dll.test.data.dto;

import com.google.gson.annotations.SerializedName;



import java.util.List;

public class SefriaTextResponse {
    @SerializedName("ref")
    private String ref;
    @SerializedName("heRef")
    private String heRef;
    @SerializedName("isComplex")
    private boolean isComplex;
    @SerializedName("text")
    private List<String> text;
    @SerializedName("he")
    private List<String> he;

    @SerializedName("heTitle")
    private String heTitle;
    @SerializedName("titleVariants")
    private List<String> titleVariants;
    @SerializedName("book")
    private String book;
    @SerializedName("sections")
    private List<Integer> sections;


    public String getRef() {
        return ref;
    }

    public String getHeRef() {
        return heRef;
    }

    public List<String> getText() {
        return text;
    }

    public List<String> getHe() {
        return he;
    }

    public String getHeTitle() {
        return heTitle;
    }

    public String getBook() {
        return book;
    }


}