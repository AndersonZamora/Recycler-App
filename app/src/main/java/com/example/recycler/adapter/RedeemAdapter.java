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
import com.example.recycler.model.redeemModel;
import com.example.recycler.viewDashboard.viewRedeemPoints.viewRedeemDetailActivity;

import java.util.List;

public class RedeemAdapter extends RecyclerView.Adapter<RedeemAdapter.viewHolderRedeem> {

    private final List<redeemModel> modelList;
    private final Context mContext;

    public RedeemAdapter(List<redeemModel> modelList, Context mContext) {
        this.modelList = modelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolderRedeem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderRedeem(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_redeem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RedeemAdapter.viewHolderRedeem holder, int position) {

        redeemModel redeemModel = modelList.get(position);
        holder.prize_text.setText(redeemModel.getName());
        holder.points_text_text.setText(redeemModel.getPoints());

        holder.relativeLayout_click.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, viewRedeemDetailActivity.class);
            intent.putExtra("model", redeemModel);
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

    static class viewHolderRedeem extends RecyclerView.ViewHolder {

        private final TextView prize_text, points_text_text;
        private final RelativeLayout relativeLayout_click;

        public viewHolderRedeem(@NonNull View itemView) {
            super(itemView);
            prize_text = itemView.findViewById(R.id.prize_text);
            points_text_text = itemView.findViewById(R.id.points_text_text);
            relativeLayout_click = itemView.findViewById(R.id.relativeLayout_click);
        }
    }
}
