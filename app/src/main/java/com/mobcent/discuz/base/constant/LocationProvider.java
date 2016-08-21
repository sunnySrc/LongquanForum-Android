package com.mobcent.discuz.base.constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ubuntu on 16-8-22.
 */
public class LocationProvider {

    private Context mContext;
    private static LocationProvider instance = new LocationProvider();
    private LocationProvider() {

    }

    public static LocationProvider getInstance() {
        return instance;
    }

    public void init(Context context) {
        mContext = context;
    }

    public interface LocationHandler {
        public void onLocationUpdate(Location location);
    }

    public Location provideLocation(final LocationHandler handler) {
        final LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        String locationProvider;
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(mContext, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return null;
        }
        try {
            final String provider = locationProvider;
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location == null) {
                LocationListener listener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        handler.onLocationUpdate(location);
                        // locationManager.removeUpdates(this);
                        locationManager.setTestProviderEnabled(provider, false);
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        // TODO Auto-generated method stub
                        Log.i("onProviderDisabled", "come in");
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        // TODO Auto-generated method stub
                        Log.i("onProviderEnabled", "come in");
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        // TODO Auto-generated method stub
                        Log.i("onStatusChanged", "come in");
                    }
                };
                locationManager.requestLocationUpdates(locationProvider, 1000, (float) 1000.0, listener);
            }
            return location;
        } catch (Exception e) {
            return null;
        }
    }
}
