package com.example.ashraf.movieapp.data.Domain;

/**
 * Created by ashraf on 11/2/2016.
 */

public class Review {
   private String id ;
    private String author ;
    private String content ;
    private String url ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutthor() {
        return author;
    }

    public void setAutthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
