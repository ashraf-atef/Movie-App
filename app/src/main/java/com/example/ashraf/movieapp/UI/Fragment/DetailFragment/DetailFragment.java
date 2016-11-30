package com.example.ashraf.movieapp.UI.Fragment.DetailFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ashraf.movieapp.data.Domain.Movie;
import com.example.ashraf.movieapp.R;

import com.example.ashraf.movieapp.Util.MyUtil;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {

    public static Bundle setBundle(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Movie", movie);
        return bundle;
    }

    @BindView(R.id.title_tv)
    public TextView title_tv;
    @BindView(R.id.date_tv)
    public TextView date_tv;
    @BindView(R.id.rate_tv)
    public TextView rate_tv;
    @BindView(R.id.relative)
    RelativeLayout relativeLayout;
    @BindView(R.id.mToolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;
    @BindView(R.id.tab_viewpager)
    public ViewPager mPager;
    @BindView(R.id.CoordinatorLayout_out_main)
    CoordinatorLayout mCoordinator;
    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.fab_fav)
    public FloatingActionButton fav_button;
    @BindView(R.id.backgroundMovieImg)
    public ImageView backgraoundMovieImg;
    @BindView(R.id.backImg)
    public ImageView backImg;
    @BindView(R.id.shareImg)
    public ImageView shareImg;
    public View rootView;
    public boolean favFlag;
    public Movie movie;
    DetailFragmentPresenter detailFragmentPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        detailFragmentPresenter = new DetailFragmentPresenterImpl(this);
        detailFragmentPresenter.onCreateView();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        detailFragmentPresenter.onStart();
    }

    @Override
    public void onResume() {
//        https://www.google.com.eg/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=how+to+make+back+button+affect+activity+not+fragment
//        http://stackoverflow.com/questions/7992216/android-fragment-handle-back-button-press
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backImg:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.fab_fav:
                if (favFlag) {
                    // delete from fav
                    MyUtil.dbAdaptor.deleteMovie(movie.getId());
                    favFlag = false;
                    fav_button.setImageResource(R.drawable.gray_like);
                } else {
                    // add to fav
                    MyUtil.dbAdaptor.insertMovie(movie);
                    favFlag = true;
                    fav_button.setImageResource(R.drawable.red_like);
                }
                break;
            case R.id.shareImg :
                  detailFragmentPresenter.shareTrailer();
                break;
        }
    }
}
