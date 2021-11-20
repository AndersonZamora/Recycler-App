package com.example.recycler.fragmentMaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recycler.R;
import com.example.recycler.model.locationModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class mapFragment extends Fragment implements OnMapReadyCallback {

    private final List<locationModel> locationModels;

    public mapFragment(List<locationModel> locationModels) {
        this.locationModels = locationModels;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        LatLng sydney = new LatLng(-7.1583243, -78.5191328);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14));

        for (locationModel list : locationModels) {

            LatLng sydney2 = new LatLng(Double.parseDouble(list.getLatitude()), Double.parseDouble(list.getLength()));
            googleMap.addMarker(new MarkerOptions().position(sydney2).title(list.getName()));
        }
    }

    public interface OnFragmentInteractionListener {

    }
}
