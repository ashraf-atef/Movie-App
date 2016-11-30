package com.example.ashraf.movieapp.UI.Fragment.RecycleFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ashraf.movieapp.UI.Adaptor.ReviewAdaptor;
import com.example.ashraf.movieapp.UI.Adaptor.TrailerAdaptor;
import com.example.ashraf.movieapp.data.Network.AsyncHttp;
import com.example.ashraf.movieapp.data.Network.HttpResponse;
import com.example.ashraf.movieapp.data.Domain.Review;
import com.example.ashraf.movieapp.data.Domain.ReviewsList;
import com.example.ashraf.movieapp.data.Domain.Trailer;
import com.example.ashraf.movieapp.data.Domain.TrailersList;
import com.example.ashraf.movieapp.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecycleviewFragment extends Fragment {


    @BindView(R.id.load_dialog)
    ProgressBar load_sign;
    @BindView(R.id.recycle)
    RecyclerView recyclerView ;
    View rootview;
    String operation;
    long id;
    public TrailerAdaptor trailerAdaptor;
    public ReviewAdaptor reviewAdaptor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_recycleview, container, false);
        ButterKnife.bind(this,rootview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        Bundle bundle = getArguments();
        operation = bundle.getString("operation");
        id = bundle.getLong("id");
        if (operation.equals(Trailer.class.getName())) {
            trailerAdaptor = new TrailerAdaptor(getContext());
            recyclerView.setAdapter(trailerAdaptor);

            final String BASE_URL =
                    "https://api.themoviedb.org/3/movie/" + id + "/videos";
            Map<String, Object> map = new HashMap<>();
            map.put("api_key", getString(R.string.api_key));
            load_sign.setVisibility(View.VISIBLE);
            AsyncHttp asyncHttpPost = new AsyncHttp<TrailersList>(TrailersList.class, BASE_URL, "GET", new HttpResponse<TrailersList>() {

                @Override
                public void onSuccess(TrailersList results) {

                    if (results != null) {
                        trailerAdaptor.trailerList = results.getResults();
                        trailerAdaptor.notifyDataSetChanged();
                    }
                    load_sign.setVisibility(View.INVISIBLE);
                }
            }, getContext());

            asyncHttpPost.execute(map);
        } else if (operation.equals(Review.class.getName())) {
            reviewAdaptor = new ReviewAdaptor(getContext());
            recyclerView.setAdapter(reviewAdaptor);

            final String BASE_URL =
                    "https://api.themoviedb.org/3/movie/" + id + "/reviews";
            Map<String, Object> map = new HashMap<>();
            map.put("api_key", getString(R.string.api_key));
            load_sign.setVisibility(View.VISIBLE);
            AsyncHttp asyncHttpPost = new AsyncHttp<ReviewsList>(ReviewsList.class, BASE_URL, "GET", new HttpResponse<ReviewsList>() {

                @Override
                public void onSuccess(ReviewsList results) {

                    if (results != null) {

                        reviewAdaptor.reviewList = results.getResults();
                        reviewAdaptor.notifyDataSetChanged();
                    }

                    load_sign.setVisibility(View.INVISIBLE);
                }
            }, getContext());

            asyncHttpPost.execute(map);
        }
        return rootview;
    }


}
