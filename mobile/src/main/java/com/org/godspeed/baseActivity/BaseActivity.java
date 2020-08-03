package com.org.godspeed.baseActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.util.Log;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.LoginScreen;
import com.org.godspeed.utility.UtilityClass;
import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult;
import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthPermissionManager;
import com.samsung.android.sdk.healthdata.HealthResultHolder;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.org.godspeed.fragments.AthleteProfileFragment.show_samsung_dialog;
import static com.org.godspeed.utility.UtilityClass.ONE_DAY;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfToday;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfWeek;

@SuppressLint("Registered")
public class BaseActivity extends Activity {
    public HealthDataStore mStore;

    String MaxHr = "";
    String AvgHr = "";
    private String Calories = "";
    private String Distance = "";
    private final HealthResultHolder.ResultListener<HealthPermissionManager.PermissionResult> mPermissionListener = result -> {
        Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = result.getResultMap();
        // Show a permission alarm and clear step count if permissions are not acquired
        if (resultMap.containsValue(Boolean.FALSE)) {
            showPermissionAlarmDialog();
        } else {
            // Get the daily step count of a particular day and display it
            if (isPermissionAcquired()) {
                //getDataNow();
            }
        }
    };
    private final HealthDataStore.ConnectionListener mConnectionListener = new HealthDataStore.ConnectionListener() {
        @Override
        public void onConnected() {
            Log.e(VolleyLog.TAG, "onConnected");
            if (isPermissionAcquired()) {
                if (isPermissionAcquired()) {
                    long startTime = getStartTimeOfWeek();
                    long endTime = startTime + ONE_DAY;
                    getSteps(startTime, endTime, "Day");
                    readTodayHeartRate(startTime, endTime, "Day");
                }
            } else {
                requestPermission();
            }
        }

        @Override
        public void onConnectionFailed(HealthConnectionErrorResult error) {
            Log.e(VolleyLog.TAG, "onConnectionFailed");
            showConnectionFailureDialog(error);
        }

        @Override
        public void onDisconnected() {
            Log.e(VolleyLog.TAG, "onDisconnected");
            if (isFinishing()) {
                mStore.connectService();
            }
        }
    };

    public void StartSamsungHealthService() {

        mStore = new HealthDataStore(this, mConnectionListener);
        try {
            mStore.connectService();
        } catch (Exception v) {

        }
    }

    protected void StopSamsungHealthService() {
        mStore.disconnectService();
    }

    private boolean isPermissionAcquired() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Check whether the permissions that this application needs are acquired
            Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = pmsManager.isPermissionAcquired(generatePermissionKeySet());
            return !resultMap.containsValue(Boolean.FALSE);
        } catch (Exception e) {
            Log.e("", "Permission request fails.", e);
        }
        return false;
    }

    private void showPermissionAlarmDialog() {
        if (isFinishing()) {
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.notice)
                .setMessage(R.string.msg_perm_acquired)
                .setPositiveButton(R.string.ok, (dialogInterface, x) -> {
                    if (LoginScreen.CoachCheckAthlete == false) {
                        long startTime = getStartTimeOfToday();
                        long endTime = startTime + ONE_DAY;
                        getSteps(startTime, endTime, "Day");
                        readTodayHeartRate(startTime, endTime, "Day");
                    }
                });
        // .show();
    }

    private Set<HealthPermissionManager.PermissionKey> generatePermissionKeySet() {
        Set<HealthPermissionManager.PermissionKey> pmsKeySet = new HashSet<>();
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.StepCount.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.HeartRate.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.USER_PROFILE_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.Sleep.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.WaterIntake.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.OxygenSaturation.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        return pmsKeySet;
    }

    private void requestPermission() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Show user permission UI for allowing user to change options
            pmsManager.requestPermissions(generatePermissionKeySet(), this)
                    .setResultListener(mPermissionListener);
        } catch (Exception e) {
            Log.e("", "Permission setting fails.", e);
        }
    }

    private void showConnectionFailureDialog(final HealthConnectionErrorResult error) {
//        if (getActivity().isFinishing()) {
//            return;
//        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        if (error.hasResolution()) {
            switch (error.getErrorCode()) {
                case HealthConnectionErrorResult.PLATFORM_NOT_INSTALLED:
                    alert.setMessage(R.string.msg_req_install);
                    break;
                case HealthConnectionErrorResult.OLD_VERSION_PLATFORM:
                    alert.setMessage(R.string.msg_req_upgrade);
                    break;
                case HealthConnectionErrorResult.PLATFORM_DISABLED:
                    alert.setMessage(R.string.msg_req_enable);
                    break;
                case HealthConnectionErrorResult.USER_AGREEMENT_NEEDED:
                    alert.setMessage(R.string.msg_req_agree);
                    break;
                default:
                    alert.setMessage(R.string.msg_req_available);
                    break;
            }
        } else {
            alert.setMessage(R.string.msg_conn_not_available);
        }

        alert.setPositiveButton(R.string.ok, (dialog, id) -> {
            if (error.hasResolution()) {
                error.resolve(this);
            }
        });

        if (error.hasResolution()) {
            alert.setNegativeButton(R.string.cancel, null);
        }

        try {
            if (show_samsung_dialog) {
                alert.show();
                show_samsung_dialog = false;
            }

        } catch (Exception m) {

        }

    }

    private void getTodaySteps(String Type) {
        long startTime = getStartTimeOfToday();
        long endTime = startTime + ONE_DAY;
        HealthDataResolver resolver = new HealthDataResolver(mStore, null);

        HealthDataResolver.AggregateRequest request = new HealthDataResolver.AggregateRequest.Builder()
                .setDataType(HealthConstants.StepCount.HEALTH_DATA_TYPE)
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.SUM,
                        Type.equalsIgnoreCase("Distance") ?
                                HealthConstants.StepCount.DISTANCE :
                                HealthConstants.StepCount.CALORIE, "sum")
                .setLocalTimeRange(HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET,
                        startTime, endTime)
                .setTimeGroup(HealthDataResolver.AggregateRequest.TimeGroupUnit.DAILY, 1,
                        HealthConstants.StepCount.TIME_OFFSET,
                        HealthConstants.StepCount.END_TIME, "day")
                .build();
        try {
            resolver.aggregate(request).setResultListener(healthData -> {
                Cursor c = healthData.getResultCursor();
                int ix = 0;
                int totalcount = 0;
                String day;
                if (c != null) {
                    while (c.moveToNext()) {
                        int sum = c.getInt(c.getColumnIndex("sum"));
                        if (Type.equalsIgnoreCase("Distance")) {
                            Log.e(VolleyLog.TAG, "getTodaySteps: " + sum);
                        } else {

                            Log.e(VolleyLog.TAG, "getTodaySteps: " + sum);
                        }


                        // ix++;
                        Log.d("TEST", "TESTXx sum " + sum + "  ");
                    }
                    //ix = 0;
                    c.close();
                } else {
                    Log.d("TEST", "There is no result.");
                }

            });
        } catch (Exception e) {
            Log.d("TEST", "Aggregating health data fails.");
        }


    }

    public void getSteps(long mCurrentStartTime, long mCurrentEndTime, String valueoftype) {
        HealthDataResolver.AggregateRequest request;
        HealthDataResolver resolver = new HealthDataResolver(mStore, null);
        HealthDataResolver.AggregateRequest.TimeGroupUnit TimeGroupUnit;
        String alias = "";
        String TileTypeX = HealthConstants.StepCount.CALORIE;
        int amount = 0;


        if (valueoftype.equalsIgnoreCase("Week")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.WEEKLY;
            alias = "week";
            amount = 1;
        } else if (valueoftype.equalsIgnoreCase("Month")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.MONTHLY;
            alias = "month";
            amount = 1;
        } else if (valueoftype.equalsIgnoreCase("Year")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.MONTHLY;
            alias = "month";
            amount = 6;
        } else {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.HOURLY;
            alias = "day";
            amount = 24;
        }


        request = new HealthDataResolver.AggregateRequest.Builder()
                .setDataType(HealthConstants.StepCount.HEALTH_DATA_TYPE)
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.SUM,
                        HealthConstants.StepCount.CALORIE, "calorie")
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.SUM,
                        HealthConstants.StepCount.DISTANCE, "distance")
                .setLocalTimeRange(HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET,
                        mCurrentStartTime, mCurrentEndTime)
                .setTimeGroup(TimeGroupUnit, amount,
                        HealthConstants.StepCount.TIME_OFFSET,
                        HealthConstants.StepCount.END_TIME, alias)
                .build();

        try {

            resolver.aggregate(request).setResultListener(healthData -> {
                Cursor c = healthData.getResultCursor();
                if (c != null) {
                    float distance = 0;
                    float calorie = 0;
                    while (c.moveToNext()) {
                        calorie += Float.parseFloat(c.getString(c.getColumnIndex("calorie")));
                        distance += Float.parseFloat(c.getString(c.getColumnIndex("distance")));
                    }
                    Calories = calorie + "";
                    Distance = distance + "";
                    c.close();
                } else {
                    Log.d("TEST", "There is no result.");
                }

            });
        } catch (Exception e) {
            Log.d("TEST", "Aggregating health data fails.");
        }

    }

    public void readTodayHeartRate(long mCurrentStartTime, long mCurrentEndTime, String valueoftype) {
        HealthDataResolver.AggregateRequest request;
        HealthDataResolver resolver = new HealthDataResolver(mStore, null);
        HealthDataResolver.AggregateRequest.TimeGroupUnit TimeGroupUnit;
        String alias = "";
        String TileTypeX = HealthConstants.HeartRate.HEART_RATE;
        int amount = 0;


        if (valueoftype.equalsIgnoreCase("Week")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.WEEKLY;
            alias = "week";
            amount = 1;
        } else if (valueoftype.equalsIgnoreCase("Month")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.MONTHLY;
            alias = "month";
            amount = 1;
        } else if (valueoftype.equalsIgnoreCase("Year")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.MONTHLY;
            alias = "month";
            amount = 6;
        } else {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.HOURLY;
            alias = "day";
            amount = 24;
        }

        request = new HealthDataResolver.AggregateRequest.Builder()
                .setDataType(HealthConstants.HeartRate.HEALTH_DATA_TYPE)
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.AVG,
                        HealthConstants.HeartRate.HEART_RATE, "average")
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.MAX,
                        HealthConstants.HeartRate.HEART_RATE, "max")
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.AVG,
                        TileTypeX, "sum")
                .setLocalTimeRange(HealthConstants.HeartRate.START_TIME, HealthConstants.HeartRate.TIME_OFFSET,
                        mCurrentStartTime, mCurrentEndTime)
                .setTimeGroup(TimeGroupUnit, amount,
                        HealthConstants.HeartRate.TIME_OFFSET,
                        HealthConstants.HeartRate.END_TIME, alias)
                .build();


        try {
            resolver.aggregate(request).setResultListener(healthData -> {
                Cursor c = healthData.getResultCursor();
                if (c != null) {
                    int maxhr = 0;
                    int avghr = 0;
                    while (c.moveToNext()) {
                        maxhr += c.getInt(c.getColumnIndex("max"));
                        avghr += c.getInt(c.getColumnIndex("average"));
                    }

                    AvgHr = avghr + "";
                    MaxHr = maxhr + "";


                    c.close();
                } else {
                    Log.d("TEST", "There is no result.");
                }

                //mStepCountTv.setText(""+totalcount);
            });
        } catch (Exception e) {
            Log.d("TEST", "Aggregating health data fails.");
        }

    }

    private void CheckTodayhealthData(Date date1) {
        long startTime = UtilityClass.atStartOfDay(date1);

        long endTime = startTime + ONE_DAY;
        getSteps(startTime, endTime, "Day");
        readTodayHeartRate(startTime, endTime, "Day");

    }

}
