package com.example.recycler.viewDashboard.viewRedeemPoints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recycler.R;
import com.example.recycler.model.redeemModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class viewRedeemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_redeem_detail);

        ProgressBar redeem_progress = findViewById(R.id.redeem_progress);

        TextView prize_text = findViewById(R.id.prize_text);
        TextView points = findViewById(R.id.points);
        TextView pick_point = findViewById(R.id.pick_point);
        ImageView image_prize = findViewById(R.id.image_prize);

        redeemModel redeemModel = (redeemModel) getIntent().getSerializableExtra("model");

        prize_text.setText(redeemModel.getName());
        points.setText(redeemModel.getPoints());
        pick_point.setText(redeemModel.getExchange());


        Picasso.get()
                .load(redeemModel.getImage())
                .into(image_prize, new Callback() {
                    @Override
                    public void onSuccess() {
                        redeem_progress.setVisibility(View.GONE);
                        image_prize.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        redeem_progress.setVisibility(View.GONE);
                        image_prize.setImageResource(R.drawable.logo);
                    }
                });
    }
}