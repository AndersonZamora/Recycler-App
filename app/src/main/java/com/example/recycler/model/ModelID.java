package com.example.recycler.model;

import androidx.annotation.NonNull;

public class ModelID {
    public String conId;

    public <T extends ModelID> T withId(@NonNull final String id) {
        this.conId = id;
        //noinspection unchecked
        return (T) this;
    }
}
