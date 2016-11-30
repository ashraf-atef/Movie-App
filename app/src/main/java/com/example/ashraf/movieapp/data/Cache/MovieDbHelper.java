package com.example.ashraf.movieapp.data.Cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ashraf on 11/4/2016.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "weather.db";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "create table " + MovieContract.MovieEntry.TABLE_NAME +
                " ( " + MovieContract.MovieEntry._ID + " integer primary key autoincrement " + " , "
                + MovieContract.MovieEntry.TITLE + " text not null " + " , "
                + MovieContract.MovieEntry.BACKDROP_PATH + " text not null " + " , "
                + MovieContract.MovieEntry.VOTE_AVERAGE + " real not null  " + " , "
                + MovieContract.MovieEntry.POSTER_PATH + " text not null " + " , "
                + MovieContract.MovieEntry.OVERVIEW + " text not null " + " , "
                + MovieContract.MovieEntry.RELEASE_DATE + " text not null " + " , "
                + MovieContract.MovieEntry.RUNTIME + " integer not null" + ")";
        final String SQL_CREATE_REVIEW_TABLE = "create table " + MovieContract.Review.TABLE_NAME
                + " ( " + MovieContract.Review._ID + " integer primary key autoincrement " + " , "
                + MovieContract.Review.AUTHOR + " text not null " + " , "
                + MovieContract.Review.CONTENT + " text not null " + " , "
                + MovieContract.Review.URL + " text not null " + " , "
                + MovieContract.Review.MOVIE_id + " integer not null " + " , "
                + " FOREIGN KEY (" + MovieContract.Review.MOVIE_id + ") REFERENCES " +
                MovieContract.MovieEntry.TABLE_NAME + " (" + MovieContract.MovieEntry._ID + ") on delete cascade on update cascade "
                + ")";
        final String SQL_CREATE_TRAILER_TABLE = "create table " + MovieContract.Trailer.TABLE_NAME
                + " ( " + MovieContract.Trailer._ID + " integer primary key autoincrement " + " , "
                + MovieContract.Trailer.KEY + " text not null " + " , "
                + MovieContract.Trailer.SITE + " text not null " + " , "
                + MovieContract.Trailer.MOVIE_ID + " integer not null " + " , "
                + " FOREIGN KEY (" + MovieContract.Trailer.MOVIE_ID + ") REFERENCES " +
                MovieContract.MovieEntry.TABLE_NAME + " (" + MovieContract.MovieEntry._ID + ") on delete cascade on update cascade "
                + ")";
        final String SQL_CREATE_CACHE_TABLE = "create table " + MovieContract.Cache.TABLE_NAME
                + " ( " + MovieContract.Cache._ID + " integer primary key autoincrement " + " , "
                + MovieContract.Cache.REQUEST + " text UNIQUE not null " + " , "
                + MovieContract.Cache.RESPONCE + " text not null " + " , "
                + MovieContract.Cache.TIME + " text not null " + " , "
                + MovieContract.Cache.MAX_AGE + " integer not null " + " , "
                + MovieContract.Cache.MAX_STALE + " integer not null "
                + ")";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_REVIEW_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRAILER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CACHE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.Trailer.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.Review.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.Cache.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
