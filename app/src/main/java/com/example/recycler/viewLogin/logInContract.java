package com.example.recycler.viewLogin;

public class logInContract {

    public interface successfulLogin {
        void attachView(attachViewLogin attachViewLogin);
    }

    public interface attachViewLogin {

        void starDashboard(String uid);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
