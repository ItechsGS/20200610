package com.org.godspeed.service;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.android.volley.VolleyLog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.gson.Gson;
import com.org.godspeed.BootCompletedIntentReceiver;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.LoginScreen;
import com.org.godspeed.allOtherClasses.SplashScreen;
import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
import com.org.godspeed.response_JsonS.getTeams.GetTeam;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BackgroundLocationUpdateService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GodSpeedInterface {
    public static List<GetTeam> GetTeamORIGINAL = new ArrayList<>();
    public static Boolean notification = false;
    public static List<SelectedAthleteDEtail> ALL_ATHLETE_LIST = new ArrayList<>();
    static Context context;
    /* Declare in manifest
    <service android:name=".BackgroundLocationUpdateService"/>
    */
    static BackgroundLocationUpdateService backgroundLocationUpdateService;
    private final String TAG = "BackgroundLocationUpdateService";
    private final String TAG_LOCATION = "TAG_LOCATION";
    /* For Google Fused API */
    protected GoogleApiClient mGoogleApiClient;
    protected LocationSettingsRequest mLocationSettingsRequest;
    WebServices webServices = new WebServices();
    private String whichApiCalled = "";
    private boolean stopService = false;
    private String latitude = "0.0", longitude = "0.0";
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    /* For Google Fused API */
//    public static boolean isMyServiceRunning(Activity activity, Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static void CallApiForTeamByCoach() {
        WebServices webServices = new WebServices();
        backgroundLocationUpdateService.whichApiCalled = "getTeams";
        webServices.getTeams(LoginScreen.userId, context, backgroundLocationUpdateService);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        // registerGpsStatusListener();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        backgroundLocationUpdateService = this;
        context = this;
        StartForeground();
        try {
            final ComponentName onBootReceiver = new ComponentName(getApplication().getPackageName(), BootCompletedIntentReceiver.class.getName());
            if (getPackageManager().getComponentEnabledSetting(onBootReceiver) != PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
                getPackageManager().setComponentEnabledSetting(onBootReceiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            }
        } catch (Exception n) {
        }
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (!stopService) {
                        //Perform your task here
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (!stopService) {
                        handler.postDelayed(this, TimeUnit.SECONDS.toMillis(10));
                    }
                }
            }
        };
        handler.postDelayed(runnable, 2000);

        buildGoogleApiClient();

        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startid) {


//        Intent intents = new Intent(getBaseContext(), BackgroundLocationUpdateService.class);
//        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        startActivity(intents);
        //Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onDestroy() {
        Log.e(TAG, "Service Stopped");
        stopService = true;
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            Log.e(TAG_LOCATION, "Location Update Callback Removed");
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void StartForeground() {

        String CHANNEL_ID = getApplicationContext().getPackageName();
        String CHANNEL_NAME = getApplicationContext().getPackageName();


        Intent intent = new Intent(this, SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        PendingIntent contentIntent = PendingIntent.getActivity(this, new Random().nextInt(), intent, 0);


        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(101);
        notificationManager.cancelAll();


        // Builds the notification and issues it.

        //String CHANNEL_ID = CHANNEL_ID;
        CharSequence name = CHANNEL_NAME;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            //channel.setShowBadge(true);
            channel.setShowBadge(false);
            channel.setVibrationPattern(null);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    channel.setShowBadge(false);
                }
            }, 100);

            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, CHANNEL_ID).setSmallIcon(R.drawable.ic_stat_name_gs)
                .setContentTitle(getResources().getString(R.string.app_name) + " is running.")
                //.setContentText()//.setOngoing(true).setWhen(0)
                .setChannelId(CHANNEL_ID)
                .setOngoing(false)
                .setVibrate(null)
                //.setBadgeIconType(NotificationCompat.COLOR_DEFAULT)
                .setPriority(NotificationCompat.DEFAULT_ALL);
        builder.setContentIntent(contentIntent);
        Notification notification = builder.build();
        startForeground(101, notification);

        UtilityClass.SetPreferences(context, "Service", "true");

    }


    @Override
    public void onLocationChanged(Location location) {

        if (latitude.equalsIgnoreCase(String.valueOf(location.getLatitude())) || longitude.equalsIgnoreCase(String.valueOf(location.getLongitude()))) {
            return;
        }

        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());

        if (latitude.equalsIgnoreCase("0.0") && longitude.equalsIgnoreCase("0.0")) {
            requestLocationUpdate();
        } else {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strDate = "Current Time : " + mdformat.format(calendar.getTime());
            Log.e(TAG_LOCATION, "Latitude : " + location.getLatitude() + "\tLongitude : " + location.getLongitude() + strDate + UtilityClass.getcurrentDateAndTime());

            WebServices webServices = new WebServices();
            String mn = UtilityClass.getPreferences(context, "SendLocation");
            Log.e(VolleyLog.TAG, "onLocationChanged: " + mn);
            if (mn.equalsIgnoreCase("TRUE")) {
                webServices.Add_location(latitude + "", longitude + "", UtilityClass.getcurrentDateAndTime(), UtilityClass.getPreferences(context, getString(R.string.user_id_tag)), context, BackgroundLocationUpdateService.this);//}
            }
        }


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

    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setSmallestDisplacement(10.0f);
        mLocationRequest.setInterval(10 * 1000);
        mLocationRequest.setFastestInterval(120 * 1000);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mLocationRequest.getInterval();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        mLocationSettingsRequest = builder.build();


        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(locationSettingsResponse -> {
                    Log.e(TAG_LOCATION, "GPS Success");
                    requestLocationUpdate();
                }).addOnFailureListener(e -> {
            int statusCode = ((ApiException) e).getStatusCode();
            Log.d(VolleyLog.TAG, "onConnected: " + statusCode);
            switch (statusCode) {
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                    //                        try {
                    //                            int REQUEST_CHECK_SETTINGS = 214;
                    //                            ResolvableApiException rae = (ResolvableApiException) e;
                    //                            //rae.startResolutionForResult( (AppCompatActivity)context, REQUEST_CHECK_SETTINGS);
                    //                        } catch (IntentSender.SendIntentException sie) {
                    //                            Log.e(TAG_LOCATION, "Unable to execute request.");
                    //                        }

                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    Log.e(TAG_LOCATION, "Location settings are inadequate, and cannot be fixed here. Fix in Settings.");
            }
        });
//                .addOnCanceledListener(new OnCanceledListener() {
//            @Override
//            public void onCanceled() {
//                Log.e(TAG_LOCATION, "checkLocationSettings -> onCanceled");
//            }
//        });
    }

    @Override
    public void onConnectionSuspended(int i) {
        connectGoogleClient();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mSettingsClient = LocationServices.getSettingsClient(context);

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        connectGoogleClient();

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.e(TAG_LOCATION, "Location Received");
                mCurrentLocation = locationResult.getLastLocation();
                onLocationChanged(mCurrentLocation);
            }

        };
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        //create an intent that you want to start again.
        Intent intent = new Intent(getApplicationContext(), BackgroundLocationUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 5000, pendingIntent);
        super.onTaskRemoved(rootIntent);
    }

    private void connectGoogleClient() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(context);
        if (resultCode == ConnectionResult.SUCCESS) {
            mGoogleApiClient.connect();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestLocationUpdate() {
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    @SuppressLint("LongLogTag")
    @Override
    public void ApiResponse(String result) {

        //Log.e("Result BackgroundLocationUpdateService ", whichApiCalled + "  " + result);
        if (result != null && result.trim().length() > 0) {
            try {


                if (WebServices.responseCode == 200) {
                    JSONObject jsonObj = new JSONObject(result);
                    if (whichApiCalled.equalsIgnoreCase("getTeams")) {


//                        UtilityClass.showAlertDailog(context, jsonObj
//                                .getString("responseMessage"));

                        JSONArray jsonDataArray = jsonObj
                                .getJSONArray("data");

                        GetTeamORIGINAL = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), GetTeam[].class)));
                        whichApiCalled = "ATHLETE";

                        //webServices.getAthlete(LoginScreen.userId, context, backgroundLocationUpdateService);
                        //Toast.makeText(context, new Gson().toJson(GetTeamORIGINAL)+"", Toast.LENGTH_SHORT).show();
                    } else if (whichApiCalled.equalsIgnoreCase("ATHLETE")) {
                        JSONArray jsonDataArray = jsonObj
                                .getJSONArray("data");
                        Log.e(VolleyLog.TAG, "ApiResponse: " + jsonDataArray);
                        ALL_ATHLETE_LIST = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), SelectedAthleteDEtail[].class)));
                    } else {
//                        JSONObject jsonObj = new JSONObject(result);
//                        UtilityClass.showAlertDailog(context, jsonObj
//                                .getString("responseMessage"));
                    }
                }
//                Log.e("**********", "" + responseMessage);
            } catch (JSONException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        UtilityClass.hide();
    }


}
