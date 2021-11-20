package com.example.recycler.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler.R;
import com.example.recycler.model.recycleModel;
import com.example.recycler.viewDashboard.viewPoints.viewPointDetailActivity;

import java.util.List;

public class pointsAdapter extends RecyclerView.Adapter<pointsAdapter.viewHolderPoints> {

    private final List<recycleModel> modelList;
    private final Context mContext;

    public pointsAdapter(List<recycleModel> modelList, Context mContext) {
        this.modelList = modelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolderPoints onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderPoints(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_points, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull pointsAdapter.viewHolderPoints holder, int position) {

        recycleModel recycleModel = modelList.get(position);
        holder.points_total.setText(recycleModel.getPoints());
        holder.date_text_date.setText(recycleModel.getDate());
        holder.estate.setText(recycleModel.getState());

        holder.relativeLayout_click.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, viewPointDetailActivity.class);
            intent.putExtra("model", recycleModel);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class viewHolderPoints extends RecyclerView.ViewHolder {

        private final TextView points_total, estate, date_text_date;
        private final RelativeLayout relativeLayout_click;

        public viewHolderPoints(@NonNull View itemView) {
            super(itemView);
            points_total = itemView.findViewById(R.id.points_total);
            date_text_date = itemView.findViewById(R.id.date_text_date);
            estate = itemView.findViewById(R.id.estate);
            relativeLayout_click = itemView.findViewById(R.id.relativeLayout_click);

        }
    }
}
