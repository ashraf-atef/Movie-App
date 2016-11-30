package com.example.ashraf.movieapp.data.Network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ashraf.movieapp.data.Domain.Cache;
import com.example.ashraf.movieapp.Util.MyUtil;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by ashraf on 10/21/2016.
 */


public class AsyncHttp<S> extends AsyncTask<Object, Void, String> {

    private final String LOG_TAG = "ERROR";
    String strUrl = "";
    String method_type;
    private HttpResponse httpResponse;
    Class<S> mClass;
    Context context;

    Cache cache;

    boolean expiredCacheFlag=false ;

    public AsyncHttp(Class<S> classss, String strUrl, String method_type, HttpResponse httpResponse, Context context) {
        this.strUrl = strUrl;
        this.method_type = method_type;
        this.httpResponse = httpResponse;
        mClass = classss;
        this.context = context;


    }

    protected void onPreExecute() {


    }

    @Override
    protected String doInBackground(Object... params) {


        cache =  MyUtil.dbAdaptor.getCacheByRequest(strUrl);
        if (cache == null) {

            Log.d("Cache", "Cashe is null  network  ?");
            return do_inback(params[0]);
        } else {

            if (MyUtil.ch(context)) {
                if (MyUtil.time_compare_hours(MyUtil.time_now(), MyUtil.time_addition(cache.getTime(), cache.getMax_age()))) {
                    Log.d("Cache", "Cashe not null and not expired network avilabile");
                    return cache.getResponce();
                } else {
                    Log.d("Cache", "Cashe not null and  expired network  avilabile");
                    expiredCacheFlag=true  ;
                    return do_inback(params[0]);
                }
            } else {
                if (MyUtil.time_compare_hours(MyUtil.time_now(), MyUtil.time_addition(cache.getTime(), cache.getMax_stale()))) {
                    Log.d("Cache", "Cashe not null and not expired  not network avilabile");
                    return cache.getResponce();
                } else {
                    Log.d("Cache", "Cashe not null and  expired  not network avilabile");
//                    Toast.makeText(context,"Pleace connect to network",Toast.LENGTH_LONG).show();
                    return null;
                }
            }


        }


    }

    private String do_inback(Object params) {
        Map<String, Object> data = (Map<String, Object>) params;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        URL url = null;

        // Will contain the raw JSON response as a string.
        String jsonStr = null;


        try {

            Uri.Builder builturl = new Uri.Builder();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                builturl.appendQueryParameter(key, (String) value);

            }

            Uri mainbuilturl = builturl.build();
            Log.d("MYURL", mainbuilturl.toString());
            if (method_type.equals("GET")) {
                url = new URL(strUrl + mainbuilturl.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
            } else {
//                http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post
                url = new URL(strUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(mainbuilturl.toString().substring(1, mainbuilturl.toString().length()));
                writer.flush();
                writer.close();
                os.close();

            }
            Log.d("NETWORK:REQUEST", url.toString());
            // Create the request to OpenWeatherMap, and open the connection
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod(method_type);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(false);
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonStr = buffer.toString();
            return jsonStr;

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
    }


    protected void onPostExecute(String result) {
        Log.d("NETWORK:RESPONSE", String.valueOf(result));
        if (result != null) {


            if (cache == null) {
                cache = new Cache();
                cache.setRequest(strUrl);
                cache.setResponce(result);
                cache.setTime(MyUtil.time_now());
                cache.setMax_age(1);
                cache.setMax_stale(60 * 60 * 24 * 28);
                long id =  MyUtil.dbAdaptor.insertCache(cache);
                Log.d("Cache", "Cashe is null inserted " + id);
            } else {
                if ( expiredCacheFlag ) {
                    cache.setRequest(strUrl);
                    cache.setResponce(result);
                    cache.setTime(MyUtil.time_now());
                    cache.setMax_age(1);
                    cache.setMax_stale(60 * 60 * 24 * 28);
                    MyUtil. dbAdaptor.updateCache(cache);
                    Log.d("Cache", "Cashe updated ");
                }
                else
                {
                    Log.d("Cache", "Cashe not updated");
                }

            }
            S results = new Gson().fromJson(result, mClass);
            httpResponse.onSuccess(results);

        } else {
            httpResponse.onSuccess(null);

        }
        Log.d("Cache", "--------------------------------------------------------------");
    }
}