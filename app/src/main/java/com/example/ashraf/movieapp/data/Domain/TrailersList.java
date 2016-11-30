package com.example.ashraf.movieapp.data.Domain;

import java.util.List;

/**
 * Created by ashraf on 11/2/2016.
 */

public class TrailersList {
    private long id ;
    private List<Trailer> results;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
