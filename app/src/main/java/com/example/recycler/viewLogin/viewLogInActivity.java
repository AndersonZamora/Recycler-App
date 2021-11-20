package com.example.recycler.viewLogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.recycler.MainActivity;
import com.example.recycler.R;
import com.example.recycler.presenter.loginPresenter.presenterLogin;
import com.example.recycler.presenter.progressPresenter.presenterProgress;
import com.example.recycler.presenter.validatePresenter.presenterValidate;
import com.example.recycler.utilities.ConstantsUtility;
import com.example.recycler.utilities.PreferenceM;
import com.example.recycler.viewLogin.viewLogUp.viewLogUpActivity;
import com.example.recycler.viewRecover.recoverActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class viewLogInActivity extends AppCompatActivity implements logInContract.attachViewLogin {

    private TextInputLayout email_input_app, password_input_app;
    private presenterProgress mPresenterProgress;
    private presenterValidate mValidate;
    private presenterLogin mPresenterLogin;
    private FirebaseAuth mAuth;
    private ConstraintLayout ConstraintLayout_enable;
    private PreferenceM mPreferenceM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log_in);

        mPreferenceM = new PreferenceM(this);
        ConstraintLayout_enable = findViewById(R.id.ConstraintLayout_enable);

        mPresenterProgress = new presenterProgress(new ProgressDialog(this));
        mValidate = new presenterValidate(this);
        mAuth = FirebaseAuth.getInstance();
        mPresenterLogin = new presenterLogin(mAuth);
        mPresenterLogin.attachView(this);
        email_input_app = findViewById(R.id.email_input_app);
        password_input_app = findViewById(R.id.password_input_app);

        findViewById(R.id.to_register).setOnClickListener(v -> startActivity(new Intent(this, viewLogUpActivity.class)));
        //log in
        findViewById(R.id.log_in_app).setOnClickListener(v -> logIn());
        //recover password
        findViewById(R.id.recover_password).setOnClickListener(v -> startActivity(new Intent(this, recoverActivity.class)));
    }

    private void logIn() {

        if (mValidate.CONNECTIVITY_SERVICE()) {
            if (!mValidate.validateField(email_input_app) | !mValidate.validateField(password_input_app)) {
                return;
            }
            String EMAIL = Objects.requireNonNull(email_input_app.getEditText()).getText().toString();
            String PASSWORD = Objects.requireNonNull(password_input_app.getEditText()).getText().toString();
            mPresenterLogin.signIn(EMAIL, PASSWORD);
        }
    }

    @Override
    public void starDashboard(String uid) {
        mPreferenceM.putString(ConstantsUtility.KEY_ID_USER, uid);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        mPresenterProgress.showProgress(getString(R.string.logging_in));
    }

    @Override
    public void dismissProgress() {
        mPresenterProgress.dismissProgress();
    }

    @Override
    public void errorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            ConstraintLayout_enable.setVisibility(View.VISIBLE);
        }
    }
}