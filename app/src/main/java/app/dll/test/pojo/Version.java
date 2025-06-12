package app.dll.test.pojo;

import com.google.gson.annotations.SerializedName;

public class Version {
    @SerializedName("status")
    private String status;
    @SerializedName("priority")
    private int priority;
    @SerializedName("license")
    private String license;
    @SerializedName("versionNotes")
    private String versionNotes;
    @SerializedName("language")
    private String language;
    @SerializedName("title")
    private String title;
    @SerializedName("versionSource")
    private String versionSource;
    @SerializedName("versionTitle")
    private String versionTitle;

    public String getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public String getLicense() {
        return license;
    }

    public String getVersionNotes() {
        return versionNotes;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getVersionSource() {
        return versionSource;
    }

    public String getVersionTitle() {
        return versionTitle;
    }
}
