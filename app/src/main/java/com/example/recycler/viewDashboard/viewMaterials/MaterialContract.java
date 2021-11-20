package com.example.recycler.viewDashboard.viewMaterials;


import com.example.recycler.model.materialsModel;

import java.util.List;

public class MaterialContract {
    public interface successfulMaterial {
        void attachView(attachViewMaterial attachViewMaterial);
    }

    public interface attachViewMaterial  {

        void set_data_material(List<materialsModel> modelList);

        void showProgress();

        void dismissProgress();

        void errorMessage(String message);
    }
}
