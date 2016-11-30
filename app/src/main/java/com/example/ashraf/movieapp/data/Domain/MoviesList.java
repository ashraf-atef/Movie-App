package com.example.ashraf.movieapp.data.Domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ashraf on 10/27/2016.
 */

public class MoviesList {

    int page;

    @SerializedName("results")
    List<Movie>movieList;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
