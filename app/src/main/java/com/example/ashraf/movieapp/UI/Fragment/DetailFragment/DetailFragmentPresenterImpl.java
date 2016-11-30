package com.example.ashraf.movieapp.UI.Fragment.DetailFragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.example.ashraf.movieapp.data.Domain.Movie;
import com.example.ashraf.movieapp.data.Domain.Review;
import com.example.ashraf.movieapp.data.Domain.Trailer;
import com.example.ashraf.movieapp.R;
import com.example.ashraf.movieapp.UI.Fragment.RecycleFragment.RecycleviewFragment;
import com.example.ashraf.movieapp.Util.MyUtil;
import com.example.ashraf.movieapp.UI.Adaptor.ViewPagerAdaptor;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;

/**
 * Created by ashraf on 11/25/2016.
 */

public class DetailFragmentPresenterImpl implements DetailFragmentPresenter {
    DetailFragment detailFragment ;
    public  RecycleviewFragment trailerFragmnet ;

    public DetailFragmentPresenterImpl(DetailFragment detailFragment) {
        this.detailFragment = detailFragment;
    }

    @Override
    public void onCreateView() {

        ButterKnife.bind(detailFragment, detailFragment.rootView);
        detailFragment. mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                detailFragment.mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (detailFragment.getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            detailFragment.backImg.setOnClickListener(detailFragment);
            detailFragment.backImg.setVisibility(View.VISIBLE);
        }
        else {
            detailFragment.backImg.setVisibility(View.INVISIBLE);
        }
        detailFragment.fav_button.setOnClickListener(detailFragment);
        detailFragment.shareImg.setOnClickListener(detailFragment);

        setData();
        setupViewPager(detailFragment.mPager);
        detailFragment.mTabLayout.setupWithViewPager(detailFragment.mPager);

    }

    @Override
    public void onStart() {

    }

    @Override
    public void setData() {

        detailFragment.movie = (Movie) detailFragment.getArguments().getSerializable("Movie");
        detailFragment.mCollapsingToolbarLayout.setTitle( detailFragment.movie.getOriginal_title());
        detailFragment.title_tv.setText(detailFragment.movie.getOriginal_title());
        detailFragment.date_tv.setText( detailFragment.movie.getRelease_date());
//        duration_tv.setText("120 Min");
        detailFragment.rate_tv.setText(String.valueOf( detailFragment.movie.getVote_average()));
//        description_tv.setText(movie.getOverview());
        if (MyUtil.dbAdaptor.getMovieById( detailFragment.movie.getId()) == null) {
            detailFragment.favFlag = false;
            detailFragment.fav_button.setImageResource(R.drawable.gray_like);
        } else {
            detailFragment.favFlag = true;
            detailFragment.fav_button.setImageResource(R.drawable.red_like);
        }
        Picasso.with( detailFragment.getContext())
                .load("http://image.tmdb.org/t/p/w185/" +  detailFragment.movie.getBackdrop_path())
                .placeholder(R.drawable.background)
                .into( detailFragment.backgraoundMovieImg);
    }

    @Override
    public void setupViewPager(ViewPager viewPager) {

        trailerFragmnet = new RecycleviewFragment();
        Bundle trailerBundle = new Bundle();
        trailerBundle.putString("operation", Trailer.class.getName());
        trailerBundle.putLong("id", detailFragment.movie.getId());
        trailerFragmnet.setArguments(trailerBundle);
        RecycleviewFragment reviewsFragment = new RecycleviewFragment();
        Bundle reviewsBundle = new Bundle();
        reviewsBundle.putString("operation", Review.class.getName());
        reviewsBundle.putLong("id", detailFragment.movie.getId());
        reviewsFragment.setArguments(reviewsBundle);
        ViewPagerAdaptor adapter = new ViewPagerAdaptor(detailFragment.getChildFragmentManager());
        adapter.addFrag(trailerFragmnet, "Trailer");
        adapter.addFrag(reviewsFragment, "Reviews");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void shareTrailer() {
        if (trailerFragmnet.trailerAdaptor!=null) {
            if (trailerFragmnet.trailerAdaptor.getItemCount() > 0) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,detailFragment.movie.getTitle()+"-"+trailerFragmnet.trailerAdaptor.trailerList.get(0).getName());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"https://www.youtube.com/watch?v="+trailerFragmnet.trailerAdaptor.trailerList.get(0).getKey());
                detailFragment.getActivity().startActivity(Intent.createChooser(sharingIntent, "Share Using"));
            }
            else
            {
                Toast.makeText(detailFragment.getContext(),"Please wait until trailer load",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
