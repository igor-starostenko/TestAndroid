package com.example.igorstar.testapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//implements LocationListener
public class GetGeoActivity extends DetailActivity {
    private TextView CityView;
    private TextView LongView;
    private TextView LatView;
    public String TAG = "GetGeoActivity";
    public String cityName = null;;
    //LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_geo);
        CityView = (TextView) findViewById(R.id.location);
        CityView.setText("Scanning location...");
        LongView = (TextView) findViewById(R.id.longitude);
        LatView = (TextView) findViewById(R.id.latitude);

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public String cityName = null;
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                String longitude = "Longitude: " + location.getLongitude();
                Log.v("Longitude is; ", longitude);
                String latitude = "Latitude: " + location.getLatitude();
                Log.v("Latitide is: ", latitude);
                /*------- To get city name from coordinates -------- */
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        System.out.println(addresses.get(0).getLocality());
                        cityName = addresses.get(0).getLocality();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CityView.setText("City: "+ cityName);
                LongView.setText(longitude);
                LatView.setText(latitude);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

}
