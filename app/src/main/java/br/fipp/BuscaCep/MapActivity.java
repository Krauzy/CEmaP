package br.fipp.BuscaCep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView map;
    private GoogleMap gmap;
    private MarkerOptions marker = new MarkerOptions();
    private final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = findViewById(R.id.mapView);
        map.setClickable(true);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null)
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        lat = getIntent().getDoubleExtra("lat",0.0);
        lng = getIntent().getDoubleExtra("lng",0.0);
        map.onCreate(mapViewBundle);
        map.getMapAsync(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBund = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if(mapViewBund == null) {
            mapViewBund = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBund);
        }
        map.onSaveInstanceState(mapViewBund);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        gmap.setIndoorEnabled(true);
        Intent it = getIntent();
        UiSettings ponto = gmap.getUiSettings();
        ponto.setIndoorLevelPickerEnabled(true);
        ponto.setMyLocationButtonEnabled(true);
        ponto.setMapToolbarEnabled(true);
        ponto.setCompassEnabled(true);
        ponto.setZoomControlsEnabled(true);
        LatLng latLong = new LatLng(lat, lng);
        marker.position(latLong);
        gmap.addMarker(marker);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(latLong));
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        map.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        map.onStop();
    }

    @Override
    protected void onDestroy() {
        map.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }
}