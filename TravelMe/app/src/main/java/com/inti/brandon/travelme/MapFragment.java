package com.inti.brandon.travelme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, CompoundButton.OnCheckedChangeListener {

    private final String SAVED_CAMERA_STATE = "state_map_camera";

    private GoogleMap map;
    private CameraPosition camera;

    RadioButton rb1, rb2, rb3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (savedInstanceState != null)
            camera = savedInstanceState.getParcelable(SAVED_CAMERA_STATE);

        rb1 = (RadioButton) view.findViewById(R.id.radio_NORMAL);
        rb1.setOnCheckedChangeListener(this);
        rb2 = (RadioButton) view.findViewById(R.id.radio_HYBRID);
        rb2.setOnCheckedChangeListener(this);
        rb3 = (RadioButton) view.findViewById(R.id.radio_SATELLITE);
        rb3.setOnCheckedChangeListener(this);

        return view;
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.radio_NORMAL) {
                this.map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                rb2.setChecked(false);
                rb3.setChecked(false);
            }
            if (buttonView.getId() == R.id.radio_HYBRID) {
                this.map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                rb1.setChecked(false);
                rb3.setChecked(false);
            }
            if (buttonView.getId() == R.id.radio_SATELLITE) {
                this.map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                rb1.setChecked(false);
                rb2.setChecked(false);
            }
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (map == null)
            return;

        // save camera state
        outState.putParcelable(SAVED_CAMERA_STATE, map.getCameraPosition());
    }

    @Override
    public void onMapReady(GoogleMap map) {

        this.map = map;
        this.map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        UiSettings mUiSettings = map.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);

        // restore the camera state
        if (camera != null) {
            map.moveCamera(CameraUpdateFactory.newCameraPosition(camera));
        }
    }
}