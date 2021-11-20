package com.example.recycler.viewDashboard.viewEvents;


import com.example.recycler.model.eventsModel;

import java.util.List;

public class EventsContract {

    public interface successfulEvents {
        void attachView(attachViewEvents attachViewEvents);
    }

    public interface attachViewEvents {

        void set_data_events(List<eventsModel> modelList);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
