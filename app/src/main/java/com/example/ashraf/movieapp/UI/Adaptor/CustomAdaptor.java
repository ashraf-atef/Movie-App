package com.example.ashraf.movieapp.UI.Adaptor;

/**
 * Created by ashraf on 10/13/2016.
 */


import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashraf.movieapp.UI.Fragment.DetailFragment.DetailFragment;
import com.example.ashraf.movieapp.data.Domain.Movie;
import com.example.ashraf.movieapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class CustomAdaptor extends ArrayAdapter<Movie> {
    private AppCompatActivity activity;
    public List<Movie> movieList;

    public CustomAdaptor(AppCompatActivity activity, int resource) {
        super(activity, resource);
        this.activity = activity;
        movieList = new ArrayList<>();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        final ViewHolder viewHolder;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.grid_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.poster_image_view = (ImageView) v.findViewById(R.id.movie_holder_image_view);
            viewHolder.movie_title_textview = (TextView) v.findViewById(R.id.tv_movie_title);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        Picasso.with(activity).load("http://image.tmdb.org/t/p/w185/" + movieList.get(position).getPoster_path()).placeholder(R.drawable.background).into(viewHolder.poster_image_view);
        viewHolder.movie_title_textview.setText(movieList.get(position).getTitle());
        viewHolder.poster_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm1 = activity.getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft1 = fm1.beginTransaction();
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(detailFragment.setBundle(movieList.get(position)));
                if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    ft1.setCustomAnimations(R.anim.slide_right,R.anim.slide_left);
                    ft1.replace(R.id.frame1, detailFragment,"DetailFragment").addToBackStack(null).commit();
                } else {

                        ft1.replace(R.id.frame2,detailFragment,"DetailFragment").commit();
                }
            }
        });
        return v;
    }

    static class ViewHolder {
        ImageView poster_image_view;
        TextView movie_title_textview;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Nullable
    @Override
    public Movie getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
