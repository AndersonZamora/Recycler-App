package com.example.recycler.viewLogin.viewLogUp;

public class logUpContract {
    public interface correctRegistration {

        void attachView(attachViewLogUp attachViewLogUp);
    }

    public interface attachViewLogUp {

        void showProgress();

        void dismissProgress();

        void registerUser();

        void errorMessage(String message);
    }
}
