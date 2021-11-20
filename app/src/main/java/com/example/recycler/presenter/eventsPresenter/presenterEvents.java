package com.example.recycler.presenter.eventsPresenter;

import com.example.recycler.model.eventsModel;
import com.example.recycler.viewDashboard.viewEvents.EventsContract;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class presenterEvents implements presenterEventsI, EventsContract.successfulEvents {

    private EventsContract.attachViewEvents attachViewEvents;
    private final FirebaseFirestore db;

    public presenterEvents(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public void get_data() {
        attachViewEvents.showProgress();
        CollectionReference reference = db.collection("events");
        reference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                List<eventsModel> modelList = new ArrayList<>();

                QuerySnapshot document = task.getResult();
                assert document != null;
                if (!document.isEmpty()) {

                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        eventsModel model = snapshot.toObject(eventsModel.class).withId(snapshot.getId());
                        modelList.add(model);
                    }
                    attachViewEvents.dismissProgress();
                } else {
                    attachViewEvents.dismissProgress();
                    attachViewEvents.errorMessage("No se registran eventos");
                }
                attachViewEvents.set_data_events(modelList);
            }
        });
    }

    @Override
    public void attachView(EventsContract.attachViewEvents attachViewEvents) {
        this.attachViewEvents = attachViewEvents;
    }
}
