package com.example.ashraf.movieapp.UI.Fragment.GridFragment;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ashraf.movieapp.data.Network.AsyncHttp;
import com.example.ashraf.movieapp.data.Network.HttpResponse;
import com.example.ashraf.movieapp.UI.Adaptor.CustomAdaptor;
import com.example.ashraf.movieapp.data.Domain.Movie;
import com.example.ashraf.movieapp.data.Domain.MoviesList;
import com.example.ashraf.movieapp.R;
import com.example.ashraf.movieapp.UI.Activity.SettingsActivity;
import com.example.ashraf.movieapp.Util.MyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ashraf on 11/25/2016.
 */

public class GridFragmentPresenterImpl implements GridFragmentPresenter {
    GridFragment gridFragment ;

    public GridFragmentPresenterImpl(GridFragment gridFragment) {
        this.gridFragment = gridFragment;
    }

    @Override
    public void onCreateView() {
        gridFragment.customAdaptor = new CustomAdaptor((AppCompatActivity) gridFragment.getActivity(), R.layout.grid_item_layout);
        gridFragment.gridView.setAdapter(gridFragment.customAdaptor);
        if (gridFragment.navigationView != null) setupDrawerContent(gridFragment.navigationView);
        gridFragment.floatingActionButton.setOnClickListener(gridFragment);

    }

    @Override
    public void oStart() {
      sendRequest(PreferenceManager.getDefaultSharedPreferences(gridFragment.getActivity())
                .getString(gridFragment.getString(R.string.pref_sort_key), gridFragment.getString(R.string.pref_sort_default)));
    }

    @Override
    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Closing drawer on item click
                gridFragment.mDrawerLayout.closeDrawers();
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        sendRequest(PreferenceManager.getDefaultSharedPreferences(gridFragment.getActivity())
                                .getString(gridFragment.getString(R.string.pref_sort_key), gridFragment.getString(R.string.pref_sort_default)));
                        return true;
                    // For rest of the options we just show a toast on click
                    case R.id.most_popular:
                        sendRequest("popular");
                        return true;
                    case R.id.top_rated:
                        sendRequest("top_rated");
                        return true;
                    case R.id.favourite:
                        getFavouriteMovies();
                        return true;
                    case R.id.setting:
                        gridFragment.getActivity().startActivity(new Intent(gridFragment.getActivity(), SettingsActivity.class));
                        return true;
                    default:
                        Toast.makeText(gridFragment.getContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

    }

    @Override
    public void sendRequest(String value) {
        final String BASE_URL =
                "https://api.themoviedb.org/3/movie/" + value;
        Map<String, Object> map = new HashMap<>();
        map.put("api_key", gridFragment.getString(R.string.api_key));

        gridFragment.load_sign.setVisibility(View.VISIBLE);
        AsyncHttp asyncHttpPost = new AsyncHttp<MoviesList>(MoviesList.class, BASE_URL, "GET", new HttpResponse<MoviesList>() {

            @Override
            public void onSuccess(MoviesList results) {
               gridFragment.customAdaptor.movieList.clear();
                gridFragment. customAdaptor.clear();
                if (results != null) {
                    List<Movie> movies = results.getMovieList();

                    for (Movie movie : movies) {
                        gridFragment.customAdaptor.movieList.add(movie);
                        gridFragment.customAdaptor.add(movie);

                    }
                }
                gridFragment.load_sign.setVisibility(View.INVISIBLE);
            }
        }, gridFragment.getContext());

        asyncHttpPost.execute(map);
    }

    @Override
    public void getFavouriteMovies() {
        gridFragment.load_sign.setVisibility(View.VISIBLE);
        gridFragment.customAdaptor.movieList.clear();
        gridFragment.customAdaptor.clear();

        List<Movie> movies = MyUtil.getDbAdaptor().getAllMovies();

        for (Movie movie : movies) {
            gridFragment.customAdaptor.movieList.add(movie);
            gridFragment.customAdaptor.add(movie);

        }

        gridFragment.load_sign.setVisibility(View.INVISIBLE);
    }
}
