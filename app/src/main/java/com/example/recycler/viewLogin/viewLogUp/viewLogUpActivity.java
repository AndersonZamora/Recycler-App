package com.example.recycler.viewLogin.viewLogUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.recycler.MainActivity;
import com.example.recycler.R;
import com.example.recycler.presenter.logUpPresenter.presenterLogUp;
import com.example.recycler.presenter.progressPresenter.presenterProgress;
import com.example.recycler.presenter.registrationPresenter.presenterRegistration;
import com.example.recycler.presenter.validatePresenter.presenterValidate;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class viewLogUpActivity extends AppCompatActivity implements logUpContract.attachViewLogUp, registrationContract.attachViewRegistration {

    private presenterProgress mPresenterProgress;
    private TextInputLayout names_input, surnames_input, email_input, mobile_input, password_input, confirm_input;
    private presenterValidate mValidate;
    private FirebaseAuth mAuth;
    private presenterLogUp mPresenterLogUp;
    private presenterRegistration mRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log_up);

        mAuth = FirebaseAuth.getInstance();

        mPresenterLogUp = new presenterLogUp(mAuth);
        mPresenterLogUp.attachView(this);
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mRegistration = new presenterRegistration(mFirestore);
        mRegistration.attachView(this);

        mValidate = new presenterValidate(this);
        mPresenterProgress = new presenterProgress(new ProgressDialog(this));

        names_input = findViewById(R.id.names_input);
        surnames_input = findViewById(R.id.surnames_input);
        email_input = findViewById(R.id.email_input);
        mobile_input = findViewById(R.id.mobile_input);
        password_input = findViewById(R.id.password_input);
        confirm_input = findViewById(R.id.confirm_input);

        findViewById(R.id.cancel_button).setOnClickListener(v -> onBackPressed());
        findViewById(R.id.save_button).setOnClickListener(v -> logUp());
    }

    private void logUp() {

        if (!mValidate.validateField(names_input) |
                !mValidate.validateField(surnames_input) |
                !mValidate.validateField(email_input) |
                !mValidate.validateField(mobile_input) |
                !mValidate.validateField(password_input) |
                !mValidate.validateField(confirm_input)) {
            return;
        }
        if (!mValidate.validateEmail(email_input)) {
            return;
        }
        if (!mValidate.validatePhone(mobile_input)) {
            return;
        }

        if (!mValidate.confirmPassword(password_input, confirm_input)) {
            return;
        }

        String EMAIL = Objects.requireNonNull(email_input.getEditText()).getText().toString();
        String PASSWORD = Objects.requireNonNull(password_input.getEditText()).getText().toString();
        mPresenterLogUp.login(EMAIL, PASSWORD);
    }

    @Override
    public void showProgress() {
        mPresenterProgress.showProgress(getString(R.string.registering));
    }

    @Override
    public void dismissProgress() {
        mPresenterProgress.dismissProgress();
    }

    @Override
    public void registerUser() {

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();

            String fullName = Objects.requireNonNull(names_input.getEditText()).getText().toString().trim();
            String lastName = Objects.requireNonNull(surnames_input.getEditText()).getText().toString().trim();
            String email = Objects.requireNonNull(email_input.getEditText()).getText().toString().trim();
            String cellular = Objects.requireNonNull(mobile_input.getEditText()).getText().toString().trim();

            Map<String, Object> user = new HashMap<>();
            user.put("fullName", fullName);
            user.put("lastName", lastName);
            user.put("email", email);
            user.put("cellular", cellular);

            mRegistration.register(user, uid);
        }
    }

    @Override
    public void starDashboard() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void errorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}