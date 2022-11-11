package com.maria.pastelhub.profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.api.GetScore;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<GetScore.Data> arrayList;
    Context context;

    public ScoreAdapter(List<GetScore.Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvActName.setText(arrayList.get(position).getActivity_name());
        holder.tvScore.setText(arrayList.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvActName;
        public TextView tvScore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvActName = itemView.findViewById(R.id.tv_act_name);
            tvScore = itemView.findViewById(R.id.tv_score);
        }
    }
}
