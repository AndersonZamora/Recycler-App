package com.example.recycler.presenter.dialogPresenter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.recycler.R;

public class presenterDialog implements presenterDialogI {

    private final Context mContext;
    private final Dialog dialogTable;

    public presenterDialog(Context mContext, Dialog dialogTable) {
        this.mContext = mContext;
        this.dialogTable = dialogTable;
    }

    @Override
    public void initDialogLogout() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialogTable.getWindow().getAttributes());

        dialogTable.setContentView(R.layout.dialog_logout);
        dialogTable.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogTable.getWindow().setAttributes(layoutParams);
    }
}
