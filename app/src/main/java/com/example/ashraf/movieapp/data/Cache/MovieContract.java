package com.example.ashraf.movieapp.data.Cache;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by ashraf on 11/4/2016.
 */

public class MovieContract {


    public static final class Cache  implements BaseColumns {
        public static final String TABLE_NAME="cache" ;
        public static final String _ID = "id";
        public static final String REQUEST = "request";
        public static final String RESPONCE = "responce";
        public static final String TIME = "time";
        public static final String MAX_AGE = "max_age";
        public static final String MAX_STALE = "max_stale";

    }

    public static final class Trailer implements BaseColumns {
        public static final String TABLE_NAME="trailer" ;
        public static final String _ID = "id";
        public static final String MOVIE_ID = "movie_id";
        public static final String KEY = "key";
        public static final String SITE = "site";

    }
    public static final class Review implements BaseColumns
    {
        public static final String TABLE_NAME="review" ;
        public static final String _ID="id" ;
        public static final String MOVIE_id="movie_id" ;
        public static final String AUTHOR="author" ;
        public static final String CONTENT="content" ;
        public static final String URL="url" ;
    }


    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "movie";
        public static final String _ID ="movie_id" ;
        public static final String TITLE="title" ;
        public static final String BACKDROP_PATH="backdrop_path" ;
        public static final String VOTE_AVERAGE="vote_average" ;
        public static final String POSTER_PATH="poster_path";
        public static final String OVERVIEW="overview" ;
        public static final String RELEASE_DATE ="release_date";
        public static final String RUNTIME ="runtime";


    }



}
