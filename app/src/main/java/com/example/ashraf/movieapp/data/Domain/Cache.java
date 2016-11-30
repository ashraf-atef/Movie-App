package com.example.ashraf.movieapp.data.Domain;

/**
 * Created by ashraf on 11/4/2016.
 */

public class Cache {

    private long id;
    private String request;
    private String responce;
    private String time;
    private int max_age;
    private int max_stale;

    public int getMax_age() {
        return max_age;
    }

    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }

    public int getMax_stale() {
        return max_stale;
    }

    public void setMax_stale(int max_stale) {
        this.max_stale = max_stale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponce() {
        return responce;
    }

    public void setResponce(String responce) {
        this.responce = responce;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
