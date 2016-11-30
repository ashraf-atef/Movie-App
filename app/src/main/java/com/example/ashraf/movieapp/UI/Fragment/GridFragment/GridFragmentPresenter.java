package com.example.ashraf.movieapp.UI.Fragment.GridFragment;

import android.support.design.widget.NavigationView;

/**
 * Created by ashraf on 11/25/2016.
 */

public interface GridFragmentPresenter  {
    void onCreateView();
    void oStart();
    void setupDrawerContent(NavigationView navigationView);
    void sendRequest(String value);
    void getFavouriteMovies();
}
