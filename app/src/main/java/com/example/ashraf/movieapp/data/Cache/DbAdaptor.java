package com.example.ashraf.movieapp.data.Cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ashraf.movieapp.data.Domain.Cache;
import com.example.ashraf.movieapp.data.Domain.Movie;
import com.example.ashraf.movieapp.data.Domain.Review;
import com.example.ashraf.movieapp.data.Domain.Trailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashraf on 11/4/2016.
 */

public class DbAdaptor {
    public Context context = null;
    static MovieDbHelper movieDbHelper;
    static SQLiteDatabase db;

    public DbAdaptor(Context context) {
        this.context = context;
        movieDbHelper = new MovieDbHelper(context);
        open();
    }

    public void open() throws SQLException {
        db = movieDbHelper.getWritableDatabase();

    }

    public void close() {
        movieDbHelper.close();
    }


    public long insertMovie(Movie movie) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(MovieContract.MovieEntry._ID, movie.getId());
        initialValues.put(MovieContract.MovieEntry.TITLE, movie.getTitle());
        initialValues.put(MovieContract.MovieEntry.BACKDROP_PATH, movie.getBackdrop_path());
        initialValues.put(MovieContract.MovieEntry.VOTE_AVERAGE, movie.getVote_average());
        initialValues.put(MovieContract.MovieEntry.POSTER_PATH, movie.getPoster_path());
        initialValues.put(MovieContract.MovieEntry.OVERVIEW, movie.getOverview());
        initialValues.put(MovieContract.MovieEntry.RELEASE_DATE, movie.getRelease_date());
        initialValues.put(MovieContract.MovieEntry.RUNTIME, movie.getRuntime());
        return db.insert(MovieContract.MovieEntry.TABLE_NAME, null, initialValues);
    }


    public boolean deleteMovie(long id) {
        return db.delete(MovieContract.MovieEntry.TABLE_NAME, MovieContract.MovieEntry._ID + "=" + id, null) > 0;
    }


    public List<Movie> getAllMovies() {

        List<Movie> movieList=new ArrayList<>();
        Cursor mCursor = db.query(MovieContract.MovieEntry.TABLE_NAME, null, null, null, null, null, null);
        if (mCursor != null) {

            Movie movie;
            while (mCursor.moveToNext()) {
                movie = new Movie();
                movie.setId(mCursor.getLong(mCursor.getColumnIndex(MovieContract.MovieEntry._ID)));
                movie.setTitle(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.TITLE)));
                movie.setBackdrop_path(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.BACKDROP_PATH)));
                movie.setVote_average(mCursor.getDouble(mCursor.getColumnIndex(MovieContract.MovieEntry.VOTE_AVERAGE)));
                movie.setPoster_path(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.POSTER_PATH)));
                movie.setOverview(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.OVERVIEW)));
                movie.setRelease_date(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.RELEASE_DATE)));
                movie.setRuntime(mCursor.getInt(mCursor.getColumnIndex(MovieContract.MovieEntry.RUNTIME)));
                movieList.add(movie);

            }
        }
        return movieList;
    }

    public Movie getMovieById(long id) throws SQLException {

        Cursor mCursor =
                db.query(true, MovieContract.MovieEntry.TABLE_NAME, null, MovieContract.MovieEntry._ID + "=" + id, null,
                        null, null, null, null);
        if (mCursor != null) {
            if (mCursor.moveToFirst()) {
                Movie movie = new Movie();
                movie.setId(mCursor.getLong(mCursor.getColumnIndex(MovieContract.MovieEntry._ID)));
                movie.setTitle(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.TITLE)));
                movie.setBackdrop_path(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.BACKDROP_PATH)));
                movie.setVote_average(mCursor.getDouble(mCursor.getColumnIndex(MovieContract.MovieEntry.VOTE_AVERAGE)));
                movie.setPoster_path(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.POSTER_PATH)));
                movie.setOverview(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.OVERVIEW)));
                movie.setRelease_date(mCursor.getString(mCursor.getColumnIndex(MovieContract.MovieEntry.RELEASE_DATE)));
                movie.setRuntime(mCursor.getInt(mCursor.getColumnIndex(MovieContract.MovieEntry.RUNTIME)));
                return movie;
            }
        }
        return null;
    }


    public long insertTrailer(Trailer trailer, int movie_id) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(MovieContract.Trailer._ID, trailer.getId());
        initialValues.put(MovieContract.Trailer.KEY, trailer.getKey());
        initialValues.put(MovieContract.Trailer.SITE, trailer.getSite());
        initialValues.put(MovieContract.Trailer.MOVIE_ID, movie_id);

        return db.insert(MovieContract.Trailer.TABLE_NAME, null, initialValues);
    }


    public Cursor getAllTrailers() {
        return db.query(MovieContract.Trailer.TABLE_NAME, null, null, null, null, null, null);
    }

    public long insertReview(Review review, int movie_id) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(MovieContract.Review._ID, review.getId());
        initialValues.put(MovieContract.Review.AUTHOR, review.getAutthor());
        initialValues.put(MovieContract.Review.CONTENT, review.getContent());
        initialValues.put(MovieContract.Review.URL, review.getUrl());
        initialValues.put(MovieContract.Review.MOVIE_id, movie_id);

        return db.insert(MovieContract.Trailer.TABLE_NAME, null, initialValues);
    }


    public Cursor getAllReviewss() {
        return db.query(MovieContract.Review.TABLE_NAME, null, null, null, null, null, null);
    }

    public long insertCache(Cache cache) {
        ContentValues initialValues = new ContentValues();
//        initialValues.put(MovieContract.Cache._ID, "default");
        initialValues.put(MovieContract.Cache.REQUEST, cache.getRequest());
        initialValues.put(MovieContract.Cache.RESPONCE, cache.getResponce());
        initialValues.put(MovieContract.Cache.TIME, cache.getTime());
        initialValues.put(MovieContract.Cache.MAX_AGE, cache.getMax_age());
        initialValues.put(MovieContract.Cache.MAX_STALE, cache.getMax_stale());

        return db.insert(MovieContract.Cache.TABLE_NAME, null, initialValues);
    }

    public boolean updateCache(Cache cache) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(MovieContract.Cache.REQUEST, cache.getRequest());
        initialValues.put(MovieContract.Cache.RESPONCE, cache.getResponce());
        initialValues.put(MovieContract.Cache.TIME, cache.getTime());
        initialValues.put(MovieContract.Cache.MAX_AGE, cache.getMax_age());
        initialValues.put(MovieContract.Cache.MAX_STALE, cache.getMax_stale());
        return db.update(MovieContract.Cache.TABLE_NAME, initialValues, MovieContract.Cache._ID + "=" + cache.getId(), null) > 0;
    }

    public Cache getCacheByRequest(String request) throws SQLException {

        Cursor mCursor =
                db.query(true, MovieContract.Cache.TABLE_NAME, null, MovieContract.Cache.REQUEST + "='" + request + "'", null,
                        null, null, null, null);
        if (mCursor != null) {
            if (mCursor.moveToFirst()) {
                Cache cache = new Cache();
                cache.setId(mCursor.getLong(mCursor.getColumnIndex(MovieContract.Cache._ID)));
                cache.setRequest(mCursor.getString(mCursor.getColumnIndex(MovieContract.Cache.REQUEST)));
                cache.setResponce(mCursor.getString(mCursor.getColumnIndex(MovieContract.Cache.RESPONCE)));
                cache.setTime(mCursor.getString(mCursor.getColumnIndex(MovieContract.Cache.TIME)));
                cache.setMax_age(mCursor.getInt(mCursor.getColumnIndex(MovieContract.Cache.MAX_AGE)));
                cache.setMax_stale(mCursor.getInt(mCursor.getColumnIndex(MovieContract.Cache.MAX_STALE)));
                return cache;
            } else return null;

        }
        return null;
    }
}
