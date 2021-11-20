package com.example.recycler.presenter.alertPresenter;

public class alertContract {

    public interface success {
        void attachView(attachView attachView);
    }

    public interface attachView {
        void onBack();
    }
}
