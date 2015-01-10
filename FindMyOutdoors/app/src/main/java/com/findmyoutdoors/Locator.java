package com.findmyoutdoors;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsmith on 1/10/15. Implements logic to grab the device's location from GPS
 * at periodic intervals.
 */
public class Locator extends Service {

    private final IBinder mBinder = new Binder();
    private LocationManager locationManager;
    private AlarmManager alarmManager;
    private List<Location> locations;
    private static final int CLIMBING = 600000;

    private Locator() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locations = new ArrayList<Location>(100);
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
    }

    private static Locator locator = new Locator();

    public static Locator getLocator() {
        return locator;
    }

    /**
     * Begins periodically polling the device's GPS
     */
    public void startLocating() {
        locations.add(locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER));
        //alarmManager.set(alarmManager.ELAPSED_REALTIME_WAKEUP, CLIMBING, new PendingIntent() {
        //TODO: add functionality to call this method repeatedly
        //});
    }

    /**
     * Stops periodically polling the device's GPS
     */
    public void stopLocating() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
