package com.example.manep.movielist;


/**
 * Created by manep on 7/28/2017.
 */

public class Movie {
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    private String originalTitle;
    private  String posterPath;
    private  String releaseDate;


    public Movie(String originalTitle, String posterPath, String releaseDate ) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
    }


}
