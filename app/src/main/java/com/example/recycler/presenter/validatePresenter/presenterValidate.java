package com.example.recycler.presenter.validatePresenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.recycler.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class presenterValidate implements interfaceValidate {

    private final Context mContext;

    public presenterValidate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public boolean validateField(TextInputLayout criterion) {

        String data = Objects.requireNonNull(criterion.getEditText()).getText().toString().trim();

        if (data.isEmpty()) {
            criterion.setError(mContext.getString(R.string.val_empty));
            return false;
        } else {
            criterion.setError(null);
            criterion.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean validateStockCodeEmpty(TextInputLayout code) {

        String data = Objects.requireNonNull(code.getEditText()).getText().toString().trim();
        if (data.isEmpty()) {
            code.setError(mContext.getString(R.string.val_empty));
            return false;
        } else {
            code.setError(null);
            code.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean validateStockCode(TextInputLayout code) {
        String data = Objects.requireNonNull(code.getEditText()).getText().toString().trim();
        int cod = Integer.parseInt(data);

        if (cod <= 0) {
            code.setError(mContext.getString(R.string.invalid_code));
            return false;
        } else {
            code.setError(null);
            code.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean validateQuantityBottle(TextInputLayout quantity) {
        String data = Objects.requireNonNull(quantity.getEditText()).getText().toString().trim();
        int cant = Integer.parseInt(data);

        if (cant <= 0 | cant >= 21) {
            quantity.setError(mContext.getString(R.string.Only_values));
            return false;
        } else {
            quantity.setError(null);
            quantity.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean validatePrice(TextInputLayout quantity) {

        String data = Objects.requireNonNull(quantity.getEditText()).getText().toString().trim();

        double cant = Double.parseDouble(data);

        if (cant <= 0 | cant >= 21) {
            quantity.setError(mContext.getString(R.string.Only_values_price));
            return false;
        } else {
            quantity.setError(null);
            quantity.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean validateNumber(TextInputLayout number) {

        String data = Objects.requireNonNull(number.getEditText()).getText().toString().trim();

        if (data.length() != 9) {
            number.setError(mContext.getString(R.string.valid_number));
            return false;
        } else {
            number.setError(null);
            number.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean validateQuantityPaperboard(TextInputLayout quantity) {
        String data = Objects.requireNonNull(quantity.getEditText()).getText().toString().trim();
        int cant = Integer.parseInt(data);

        if (cant <= 0 | cant >= 3) {
            quantity.setError(mContext.getString(R.string.Only_values_kg));
            return false;
        } else {
            quantity.setError(null);
            quantity.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean CONNECTIVITY_SERVICE() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public boolean validateEmail(TextInputLayout email) {

        String EMAIL = Objects.requireNonNull(email.getEditText()).getText().toString().replace(" ", "").trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!EMAIL.matches(emailPattern)) {
            email.setError(mContext.getString(R.string.invalid_email));
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean validatePhone(TextInputLayout phone) {

        String PHONE = Objects.requireNonNull(phone.getEditText()).getText().toString().replace(" ", "").trim();

        if (PHONE.length() > 5 && PHONE.length() <= 9) {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        } else {
            phone.setError(mContext.getString(R.string.invalid_phone));
            return false;
        }
    }

    @Override
    public boolean confirmPassword(TextInputLayout password1, TextInputLayout password2) {

        String pass1 = Objects.requireNonNull(password1.getEditText()).getText().toString();
        String pass2 = Objects.requireNonNull(password2.getEditText()).getText().toString();

        if (validateFields.validateLength(pass1)) {

            password1.setError(null);
            password1.setErrorEnabled(false);

            if (validateFields.validatePassword(pass1)) {

                password1.setError(null);
                password1.setErrorEnabled(false);

                if (!pass1.equals(pass2)) {
                    password1.setError(mContext.getString(R.string.passwords_match));
                    password2.setError(mContext.getString(R.string.passwords_match));
                    return false;
                } else {
                    password1.setError(null);
                    password1.setErrorEnabled(false);
                    password2.setError(null);
                    password2.setErrorEnabled(false);
                    return true;
                }
            }
            password1.setError(mContext.getString(R.string.contain_spaces));
            return false;
        }
        password1.setError(mContext.getString(R.string.size_password));
        return false;
    }
}
