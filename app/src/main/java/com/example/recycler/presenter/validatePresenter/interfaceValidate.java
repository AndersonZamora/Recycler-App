package com.example.recycler.presenter.validatePresenter;

import com.google.android.material.textfield.TextInputLayout;

public interface interfaceValidate {

    boolean validateField(TextInputLayout criterion);

    boolean validateStockCodeEmpty(TextInputLayout code);

    boolean validateStockCode(TextInputLayout code);

    boolean validateQuantityBottle(TextInputLayout quantity);

    boolean validatePrice(TextInputLayout quantity);

    boolean validateNumber(TextInputLayout number);

    boolean validateQuantityPaperboard(TextInputLayout quantity);

    boolean CONNECTIVITY_SERVICE();

    boolean validateEmail(TextInputLayout email);

    boolean validatePhone(TextInputLayout phone);

    boolean confirmPassword(TextInputLayout password1, TextInputLayout password2);
}
