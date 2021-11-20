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
import com.example.recycler.model.materialsModel;
import com.example.recycler.viewDashboard.viewMaterials.viewMaterialDetailActivity;

import java.util.List;

public class MaterialsAdapter extends RecyclerView.Adapter<MaterialsAdapter.viewHolderMaterials> {

    private final List<materialsModel> modelList;
    private final Context mContext;

    public MaterialsAdapter(List<materialsModel> modelList, Context mContext) {
        this.modelList = modelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolderMaterials onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderMaterials(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_materials, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialsAdapter.viewHolderMaterials holder, int position) {

        materialsModel materialsModel = modelList.get(position);
        holder.name_text.setText(materialsModel.getName());
        holder.price_text_number.setText(materialsModel.getPrice());

        holder.relativeLayout_click.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, viewMaterialDetailActivity.class);
            intent.putExtra("model", materialsModel);
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

    static class viewHolderMaterials extends RecyclerView.ViewHolder {

        private final TextView name_text, price_text_number;
        private final RelativeLayout relativeLayout_click;

        public viewHolderMaterials(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            price_text_number = itemView.findViewById(R.id.price_text_number);
            relativeLayout_click = itemView.findViewById(R.id.relativeLayout_click);

        }
    }
}
