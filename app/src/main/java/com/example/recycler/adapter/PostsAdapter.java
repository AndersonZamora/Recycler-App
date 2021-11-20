package com.example.recycler.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler.R;
import com.example.recycler.model.purchaseModel;
import com.example.recycler.viewDashboard.viewPurchase.viewDetailPostsActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.viewHolderPosts> {

    private final List<purchaseModel> modelList;
    private final Context mContext;

    public PostsAdapter(List<purchaseModel> modelList, Context mContext) {
        this.modelList = modelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolderPosts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderPosts(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_purchase, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.viewHolderPosts holder, int position) {

        purchaseModel purchaseModel = modelList.get(position);
        holder.name_text.setText(purchaseModel.getTitle());
        holder.price_text_number.setText(purchaseModel.getPrice());

        Picasso.get()
                .load(purchaseModel.getImage())
                .into(holder.image_purchase, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.image_purchase.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        holder.image_purchase.setVisibility(View.VISIBLE);
                        holder.image_purchase.setImageResource(R.drawable.logo);
                    }
                });

        holder.relativeLayout_click.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, viewDetailPostsActivity.class);
            intent.putExtra("uuid", purchaseModel.conId);
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

    static class viewHolderPosts extends RecyclerView.ViewHolder {

        private final TextView name_text, price_text_number;
        private final RelativeLayout relativeLayout_click;
        private final ImageView image_purchase;

        public viewHolderPosts(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            price_text_number = itemView.findViewById(R.id.price_text_number);
            relativeLayout_click = itemView.findViewById(R.id.relativeLayout_click);
            image_purchase = itemView.findViewById(R.id.image_purchase);
        }
    }
}
