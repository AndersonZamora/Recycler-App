package com.example.recycler.viewLogin.viewLogUp;

public class registrationContract {
    public interface correctRegistration {

        void attachView(attachViewRegistration attachViewRegistration);
    }

    public interface attachViewRegistration {

        void starDashboard();

        void errorMessage(String message);

        void showProgress();

        void dismissProgress();

    }
}
