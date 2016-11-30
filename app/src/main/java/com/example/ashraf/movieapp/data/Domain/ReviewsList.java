package com.example.ashraf.movieapp.data.Domain;

import java.util.List;

/**
 * Created by ashraf on 11/2/2016.
 */

public class ReviewsList {
   private long id ;
    private int page ;
    private List<Review> results ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }
}
