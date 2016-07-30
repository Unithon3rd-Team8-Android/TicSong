package eu.jpark.ticsong.DTO;

import java.io.Serializable;

public class MusicDTO implements Serializable{

    private String url;
    private String artist;
    private String title;
    private int mLevel;
    private int category;

    public MusicDTO() {
        // TODO Auto-generated constructor stub
    }

    public MusicDTO(String url, String artist, String title, int mLevel,
                    int category) {
        super();
        this.url = url;
        this.artist = artist;
        this.title = title;
        this.mLevel = mLevel;
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getmLevel() {
        return mLevel;
    }

    public void setmLevel(int mLevel) {
        this.mLevel = mLevel;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Music [url=" + url + ", artist=" + artist + ", title=" + title
                + ", mLevel=" + mLevel + ", category=" + category + "]";
    }

}