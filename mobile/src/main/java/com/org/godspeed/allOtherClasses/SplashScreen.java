package com.org.godspeed.allOtherClasses;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.VolleyLog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.service.BackgroundLocationUpdateService;
import com.org.godspeed.service.SchoolDataService;
import com.org.godspeed.utility.UtilityClass;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.utility.UtilityClass.GetDeviceType;
import static com.org.godspeed.utility.WebServices.BASE_URL;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES_ASSETS;

public class SplashScreen extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    final static int REQUEST_LOCATION = 199;
    public static Boolean GetPermissionStatus = false;
    public static Boolean GS_DEBUG_BUILD = false;
    public static Boolean PlayVideo = true;
    public static String SCHOOL_ID_FOR_PRE = "";


    public static String SCHOOL_NAME_FOR_PRE = "";
    public static String FCM_TOKEN = "";
    private String TAG = "BackgroundLocationUpdateService";
    /* For Google Fused API */
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private PendingResult<LocationSettingsResult> result;
    private Context context;
    private boolean stopService = false;

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    //public static HealthDataStore mStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        context = this;

        UtilityClass.SetPreferences(context, "SendLocation", "TRUE");

        if (UtilityClass.getPreferences(context, "debug") != null) {
            //                UtilityClass.SetPreferences(context, "agree", "true");
            //
            //                Date c = Calendar.getInstance().getTime();
            //                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            //                String formattedDate = df.format(c);
            //
            //
            //                //Toast.makeText(context,responseMessage,Toast.LENGTH_LONG).show();
            //                UtilityClass.SetPreferences(context, "SAVED_DATE", formattedDate);
            GS_DEBUG_BUILD = !UtilityClass.getPreferences(context, "debug").equalsIgnoreCase("true");
        }

        try {
            Log.d(TAG, "onCreate: GS_DEBUG_BUILD: " + GS_DEBUG_BUILD + "  debug: " + UtilityClass.getPreferences(context, "debug"));

            BASE_URL = GS_DEBUG_BUILD ? "http://www.dev.godspeedgo.com/" : "https://www.godspeedgo.com/";
            BASE_URL_FOR_IMAGES = GS_DEBUG_BUILD ? "http://www.dev.godspeedgo.com/assets/user_images" : "https://www.godspeedgo.com/assets/user_images";
            BASE_URL_FOR_IMAGES_ASSETS = GS_DEBUG_BUILD ? "http://www.dev.godspeedgo.com/assets" : "https://www.godspeedgo.com/assets";
            Log.d(TAG, "onCreate: " + BASE_URL + "  " + BASE_URL_FOR_IMAGES + " " + BASE_URL_FOR_IMAGES_ASSETS);
        } catch (Exception v) {
        }

        setContentView(R.layout.activity_splash_screen);

        TextView r = findViewById(R.id.debugView);
        r.setVisibility(GS_DEBUG_BUILD ? VISIBLE : GONE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        if (PlayVideo == false) {
            UtilityClass.SetPreferences(context, "SendLocation", "FALSE");
        }


        if (UtilityClass.getPreferences(context, "SCHOOL_ID_FOR_PREFIX") == null || UtilityClass.getPreferences(context, "SCHOOL_NAME_FOR_PREFIX") == null) {
            UtilityClass.SetPreferences(context, "SCHOOL_ID_FOR_PREFIX", "");
            UtilityClass.SetPreferences(context, "SCHOOL_NAME_FOR_PREFIX", "");
        }


//        new Samhealth(SplashScreen.this,context);
        GetDeviceType(this);
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w(TAG, "getInstanceId failed", task.getException());
                return;
            }
            // Get new Instance ID token
            FCM_TOKEN = task.getResult().getToken();
            Log.d("FCM", FCM_TOKEN);

            Log.e(VolleyLog.TAG, "c: " + new Gson().toJson(task));
        });

        boolean serviceRunningStatus = isServiceRunning(BackgroundLocationUpdateService.class);


        if (!serviceRunningStatus) {
            Intent someIntent = new Intent(context, BackgroundLocationUpdateService.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(someIntent);
            } else {
                context.startService(someIntent);
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermissionMaps();
            }
        }, 2000);


        //GotoScreen();


        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/agencyf.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/agencyf.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/agencyf.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/agencyf.ttf");

        if (UtilityClass.getPreferences(context, "unit_type") == null) {
            UtilityClass.SetPreferences(context, "unit_type", getString(R.string.Imperial));
        }


    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:

                        GotoScreen();

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {

                            status.startResolutionForResult(
                                    SplashScreen.this,
                                    REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        //...
                        break;
                }
            }
        });

    }


    private void checkPermissionMaps() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            mGoogleApiClient.connect();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
            }, 1234);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult()", Integer.toString(resultCode));
        switch (requestCode) {
            case REQUEST_LOCATION:
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        // All required changes were successfully made
                        GotoScreen();
                        //Toast.makeText(SplashScreen.this, "Location enabled by user!", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case Activity.RESULT_CANCELED: {
                        GotoScreen();
                        // The user was asked to change settings, but chose not to
                        //Toast.makeText(SplashScreen.this, "Location not enabled, user cancelled.", Toast.LENGTH_LONG).show();
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }


    private void GotoScreen() {
        //final ComponentName onBootReceiver = new ComponentName(getApplication().getPackageName(), autostart.class.getName());
//        if(getPackageManager().getComponentEnabledSetting(onBootReceiver) != PackageManager.COMPONENT_ENABLED_STATE_ENABLED)
//            getPackageManager().setComponentEnabledSetting(onBootReceiver,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
        startService(new Intent(this, SchoolDataService.class));

        //startActivity(new Intent(SplashScreen.this, LoginScreen.class));
//        Log.d(VolleyLog.TAG,"*************** LoginScreen *************");
//        Bundle bundle = new Bundle();
//        bundle.putInt("weight", 0);
//        Intent intent = new Intent(this, Session_summery.class)
//                .putExtra("weight", "0")
//                .putExtra("Timing", "0");
//        startActivity(intent);
//
//        Log.d(VolleyLog.TAG,"*************** AthleteExerciseSetActivity *************");
        //overridePendingTransition(R.anim.exit, R.anim.enter);
        startActivity(new Intent(SplashScreen.this, LoginScreen.class));
        overridePendingTransition(R.anim.exit, R.anim.enter);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // now, you have permission go ahead
            // TODO: something
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            mGoogleApiClient.connect();
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                GotoScreen();

                // now, user has denied permission (but not permanently!)

            } else {

            }

        }
        return;
    }

//    public void statusCheck() {
//        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            buildAlertMessageNoGps();
//        }
//    }

//    private void buildAlertMessageNoGps() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(final DialogInterface dialog, final int id) {
//                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(final DialogInterface dialog, final int id) {
//                        dialog.cancel();
//                        gotnExtSscreen();
//                    }
//                });
//        final AlertDialog alert = builder.create();
//        alert.show();
//    }

    private void gotnExtSscreen() {
        //startService(new Intent(this, SchoolDataService.class));
//        this.mStore = new HealthDataStore(context, mConnectionListener);
//
//        this.mStore.connectService();
        GotoScreen();
    }


}
