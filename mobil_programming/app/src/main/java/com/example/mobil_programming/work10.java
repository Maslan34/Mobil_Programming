package com.example.mobil_programming;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mobil_programming.databinding.ActivityWork10Binding;

import java.io.IOException;
import java.util.List;

public class work10 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityWork10Binding binding;

    EditText editTextLocation;
    ZoomControls zoomControls;
    Button buttonMapType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work10);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        editTextLocation = findViewById(R.id.edit_text_location);
        zoomControls = findViewById(R.id.zoom_control);
        buttonMapType = findViewById(R.id.button_map_type);


        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.moveCamera(CameraUpdateFactory.zoomIn());
            }
        });

        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.moveCamera(CameraUpdateFactory.zoomOut());
            }
        });


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Check location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // Setting map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Activating Zoom Controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }


        }

    }

    public void changeMapType(View view) {

        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            buttonMapType.setText("Normal");
        } else{

            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            buttonMapType.setText("Satellite");
        }

    }

    public void search(View view) {
        String location = editTextLocation.getText().toString();

        Geocoder geocoder = new Geocoder(this);

        try {
            List<Address> addresses = geocoder.getFromLocationName(location, 5);

            if (addresses != null && addresses.size() > 0) {
                mMap.clear(); // Clear existing markers

                for (int i = 0; i < addresses.size(); i++) {
                    Address address = addresses.get(i);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    // Changing Marker Icon
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.car_marker);

                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(latLng)
                            .icon(icon)
                            .title(address.getPostalCode());

                    mMap.addMarker(markerOptions);

                    // Limit to show only the first 5 results
                    if (i >= 4) break;
                }

                // Moving camera to the first result
                Address firstAddress = addresses.get(0);
                LatLng firstLatLng = new LatLng(firstAddress.getLatitude(), firstAddress.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 12));
            } else {
                Toast.makeText(this, "No results found for: " + location, Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}