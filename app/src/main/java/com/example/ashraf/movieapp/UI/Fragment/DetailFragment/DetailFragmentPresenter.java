package com.example.ashraf.movieapp.UI.Fragment.DetailFragment;

import android.support.v4.view.ViewPager;

/**
 * Created by ashraf on 11/25/2016.
 */

public interface DetailFragmentPresenter {
    void onCreateView();
    void onStart();
    void setData();
    void setupViewPager(ViewPager viewPager);
    void shareTrailer();

}
