package com.example.ashraf.movieapp.UI.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashraf.movieapp.data.Domain.Trailer;
import com.example.ashraf.movieapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashraf on 11/1/2016.
 */

public class TrailerAdaptor
       extends RecyclerView.Adapter<TrailerAdaptor.ItemViewHolder> {
    public List<Trailer> trailerList ;
    Context context;
    View v ;

    public TrailerAdaptor(Context context) {
        Log.d("TRAILER","CONSTR");
        trailerList=new ArrayList<>();
        this.context = context;
    }


    @Override
    public int getItemCount() {
        Log.d("TRAILER","SIZE");
        return trailerList.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
         v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_layout, viewGroup, false);
        ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.trailertitle.setText(trailerList.get(i).getName());
        v.setOnClickListener(clickListener);
        v.setTag(itemViewHolder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView trailerImg;
        TextView  trailertitle;

        ItemViewHolder(View itemView) {
            super(itemView);

            trailerImg = (ImageView) itemView.findViewById(R.id.trailer_img);
            trailertitle=(TextView)itemView.findViewById(R.id.trailer_title_tv);
        }

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ItemViewHolder holder = (ItemViewHolder) view.getTag();
            int position = holder.getPosition();
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+trailerList.get(position).getKey())));

        }


} ;
}