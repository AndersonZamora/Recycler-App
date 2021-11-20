package com.example.recycler.viewRecover;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.recycler.R;
import com.example.recycler.presenter.alertPresenter.alertContract;
import com.example.recycler.presenter.alertPresenter.presenterAlert;
import com.example.recycler.presenter.progressPresenter.presenterProgress;
import com.example.recycler.presenter.validatePresenter.presenterValidate;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class recoverActivity extends AppCompatActivity implements alertContract.attachView {

    private TextInputLayout email_input_re;
    private FirebaseAuth mAuth;
    private presenterValidate mValidate;
    private presenterAlert mAlert;
    private presenterProgress mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        mAuth = FirebaseAuth.getInstance();
        ProgressDialog mDialog = new ProgressDialog(this);
        mProgress = new presenterProgress(mDialog);
        email_input_re = findViewById(R.id.email_input_re);
        mValidate = new presenterValidate(this);
        mAlert = new presenterAlert(this);
        mAlert.attachView(this);

        findViewById(R.id.sent).setOnClickListener(v -> recoverPassword());
    }

    private void recoverPassword() {

        if (!mValidate.validateField(email_input_re)) {
            return;
        }
        if (!mValidate.validateEmail(email_input_re)) {
            return;
        }
        mProgress.showProgress(getString(R.string.sending));

        String email = Objects.requireNonNull(email_input_re.getEditText()).getText().toString().trim();
        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(unused -> {
            mProgress.dismissProgress();
            mAlert.resetPassword();
        }).addOnFailureListener(e -> {
            mProgress.dismissProgress();
            mAlert.errorResetPassword();
        });
    }

    @Override
    public void onBack() {
        onBackPressed();
    }
}