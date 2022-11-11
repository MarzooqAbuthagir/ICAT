package com.maria.pastelhub.star_collecting.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.maria.pastelhub.R;
import com.maria.pastelhub.star_collecting.model.StarCollectingModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

// The adapter class which
// extends RecyclerView Adapter
public class StarCollectingAdapter extends RecyclerView.Adapter<StarCollectingAdapter.ProductViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private ArrayList<StarCollectingModel> arrayList;

    //getting the context and product list with constructor
    public StarCollectingAdapter(Context mCtx, ArrayList<StarCollectingModel> arrayList) {
        this.mCtx = mCtx;
        this.arrayList = arrayList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_collecting_star, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        StarCollectingModel product = arrayList.get(position);

        //binding the data with the viewholder views
        holder.tv.setText(product.getTitle());
        if (position == 0 || position == 4 || position == 5) {
            holder.cv.setCardBackgroundColor(mCtx.getResources().getColor(R.color.colorPrimary));
            //holder.iv.setColorFilter(Color.argb(255, 255, 255, 255)); // White Tint
        } else {
            holder.cv.setCardBackgroundColor(mCtx.getResources().getColor(R.color.grey_color));
        }

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;
        CardView cv;

        public ProductViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
            cv = itemView.findViewById(R.id.cv);
        }

    }

}
