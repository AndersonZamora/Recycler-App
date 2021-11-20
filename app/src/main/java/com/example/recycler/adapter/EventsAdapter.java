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
import com.example.recycler.model.eventsModel;
import com.example.recycler.viewDashboard.viewEvents.viewEventDetailActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.viewHolderEvents> {

    private final List<eventsModel> modelList;
    private final Context mContext;

    public EventsAdapter(List<eventsModel> modelList, Context mContext) {
        this.modelList = modelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolderEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderEvents(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_events, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.viewHolderEvents holder, int position) {

        eventsModel eventsModel = modelList.get(position);
        holder.name_text.setText(eventsModel.getName());
        holder.date_text.setText(eventsModel.getDate());

        Picasso.get()
                .load(eventsModel.getImage())
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.imageView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        holder.imageView.setVisibility(View.VISIBLE);
                        holder.imageView.setImageResource(R.drawable.logo);
                    }
                });

        holder.relativeLayout_click.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, viewEventDetailActivity.class);
            intent.putExtra("model", eventsModel);
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

    static class viewHolderEvents extends RecyclerView.ViewHolder {

        private final TextView name_text, date_text;
        private final RelativeLayout relativeLayout_click;
        private final ImageView imageView;

        public viewHolderEvents(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            date_text = itemView.findViewById(R.id.date_text);
            relativeLayout_click = itemView.findViewById(R.id.relativeLayout_click);
            imageView = itemView.findViewById(R.id.image_event);
        }
    }
}
