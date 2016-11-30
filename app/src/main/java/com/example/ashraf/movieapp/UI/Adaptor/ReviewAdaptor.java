package com.example.ashraf.movieapp.UI.Adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ashraf.movieapp.data.Domain.Review;
import com.example.ashraf.movieapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashraf on 11/1/2016.
 */

public class ReviewAdaptor
       extends RecyclerView.Adapter<ReviewAdaptor.ItemViewHolder> {
    public List<Review> reviewList ;
    Context context;
    View v ;

    public ReviewAdaptor(Context context) {

        reviewList=new ArrayList<>();
        this.context = context;
    }


    @Override
    public int getItemCount() {

        return reviewList.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
         v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_layout, viewGroup, false);
        ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {

        itemViewHolder.authorCharacter_tv.setText((reviewList .get(i).getAutthor().charAt(0)+"").toUpperCase());
        itemViewHolder.authorName_tv.setText(reviewList .get(i).getAutthor());
        itemViewHolder.reviewContent_tv.setText(reviewList .get(i).getContent());
        v.setOnClickListener(clickListener);
        v.setTag(itemViewHolder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView authorCharacter_tv;
        TextView authorName_tv ;
        TextView reviewContent_tv ;

        ItemViewHolder(View itemView) {
            super(itemView);

            authorCharacter_tv = (TextView) itemView.findViewById(R.id.authotCharacter_tv);
            authorName_tv=(TextView)itemView.findViewById(R.id.authotName_tv);
            reviewContent_tv=(TextView)itemView.findViewById(R.id.reviewContent_tv);
        }

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ItemViewHolder holder = (ItemViewHolder) view.getTag();
            int position = holder.getPosition();

        }


} ;
}