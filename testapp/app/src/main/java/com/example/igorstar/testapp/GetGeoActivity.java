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
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GetGeoActivity extends DetailActivity implements LocationListener {
    private TextView locationView;
    public String TAG = "GetGeoActivity";
    //LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_geo);
        locationView = (TextView) findViewById(R.id.location);
        locationView.setText("Scanning location...");

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new MyLocationListener();
        //try  {
        if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER))
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

//        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener);

//            locationManager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        //} catch(Exception e){
//            locationManager.requestLocationUpdates(
//                    LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
//        }
//        MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
//            @Override
//            public void gotLocation(Location location){
//                //Got the location!
//                Log.v(TAG, "Got the location!");
//            }
//        };
//        MyLocation myLocation = new MyLocation();
//        myLocation.getLocation(this, locationResult);
//        Log.e(TAG, myLocation.toString());
    }

    @Override
    public void onLocationChanged(Location loc) {
            Toast.makeText(
                    getBaseContext(),
                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " + loc.getLongitude();
            Log.v("Longitude is; ", longitude);
            String latitude = "Latitude: " + loc.getLatitude();
            Log.v("Latitide is: ", latitude);

        /*------- To get city name from coordinates -------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                    + cityName;
            Log.e("", s);
        }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
//    @Override
//    public void onLocationChanged(Location loc) {
//        Toast.makeText(
//                getBaseContext(),
//                "Location changed: Lat: " + loc.getLatitude() + " Lng: "
//                        + loc.getLongitude(), Toast.LENGTH_SHORT).show();
//        String longitude = "Longitude: " + loc.getLongitude();
//        Log.v(TAG, longitude);
//        String latitude = "Latitude: " + loc.getLatitude();
//        Log.v(TAG, latitude);
//        /*-------to get City-Name from coordinates -------- */
//        String cityName = null;
//        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
//        List<Address> addresses;
//        try {
//            addresses = gcd.getFromLocation(loc.getLatitude(),
//                    loc.getLongitude(), 1);
//            if (addresses.size() > 0)
//                System.out.println(addresses.get(0).getLocality());
//            cityName = addresses.get(0).getLocality();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
//                + cityName;
//    }
//    @Override
//    public void onProviderDisabled(String provider) {}
//
//    @Override
//    public void onProviderEnabled(String provider) {}
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {}

}
