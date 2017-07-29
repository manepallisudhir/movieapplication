package com.example.manep.movielist;

import java.util.List;

/**
 * Created by manep on 7/28/2017.
 */

public class MovieList {
    private int page;
    private List<Movie> results;

    public MovieList(int page, List<Movie> results) {
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
