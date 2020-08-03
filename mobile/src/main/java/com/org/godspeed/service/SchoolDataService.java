package com.org.godspeed.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.GeomagneticField;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.fragments.AthleteProfileFragment;
import com.org.godspeed.loginData.Login;
import com.org.godspeed.response_JsonS.GetPosition.GetPosition;
import com.org.godspeed.response_JsonS.GetSport.GetSport;
import com.org.godspeed.response_JsonS.LanguageArray.GetLanguage;
import com.org.godspeed.response_JsonS.SchoolData.GetSchools;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.GetTeamsDetailsClas;
import com.org.godspeed.response_JsonS.goalArray.GoalArray;
import com.org.godspeed.response_JsonS.timezone.timezoneList;
import com.org.godspeed.utility.GetTeamsDetailsClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.SchoolClass;
import com.org.godspeed.utility.SportsDataClass;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_ID_FOR_PRE;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_NAME_FOR_PRE;


public class SchoolDataService extends Service implements GodSpeedInterface, LocationListener {
    private static final int MINUTES = 1000 * 60 * 2;
    private static final long MIN_TIME_BW_UPDATES = 1; // 1 minute
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 20;
    public static List<Login> LoginJson;
    public static List<com.org.godspeed.response_JsonS.getTeams.GetTeam> GetTeam = new ArrayList<>();
    public static Vector<SchoolClass> vectorSchoolData = null;
    public static Vector<GetTeamsDetailsClass> vectorGetTeamsData = null;
    public static List<GetTeamsDetailsClas> getTeamsDetailsClas;
    public static List<GetSchools> getSchoolsList = new ArrayList<>();
    public static List<GetLanguage> getLanguageList = new ArrayList<>();
    public static List<GetPosition> getPositionList = new ArrayList<>();
    public static List<GetSport> getSportsList = new ArrayList<>();
    public static List<timezoneList> timezoneLists = new ArrayList<>();
    public static String[] schoolArray = null;
    public static String[] schoolIdArray = null;
    //    public static String[] positionTitleArray = null;
//    public static String[] positionTitleIdArray = null;
    public static String[] teamArray = null, schoolAddressArray = null, teamIdArray = null, sportsArray = null, sportsIdArray = null;
    public static List<GoalArray> goalArray;
    public static AthleteProfileFragment x = new AthleteProfileFragment();
    static Context Scontext;
    static SchoolDataService SActivity;
    private static String whichApiCalleds;
    public LocationManager locationManager;
    // public MyLocationListener listener;
    public Location previousBestLocation = null;
    Context context;
    Gson gson = new Gson();
    Context mContext;
    String MODULE = "AndroidLocationServices", TAG = "";
    double getLattitude = 0, getLogitude = 0;
    private boolean isGpsEnabled = false;


    /* For Google Fused API */
    private boolean isNetworkEnabled = false;
    private GeomagneticField geoField;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private boolean isGPSEnabled;

    private int NOTIFICATION_ID = 101;


    public static void getDataFromServer() {
        whichApiCalleds = "school";
        //initializeLayoutView();
        WebServices webServices = new WebServices();
        webServices.getSchools(Scontext, SActivity);
    }

    public static void getTeamDataFromServer() {
        whichApiCalleds = "teamX";
        WebServices webServices = new WebServices();
        webServices.getTeams(SCHOOL_ID_FOR_PRE, SchoolDataService.Scontext, SActivity);
    }


    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startid) {
        whichApiCalleds = "school";
        //initializeLayoutView();
        WebServices webServices = new WebServices();
        webServices.getSchools(context, SchoolDataService.this);


        try {
            if (isGPSEnabled) {
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } else {
            }
        } catch (Exception v) {

        }
    }

    @Override
    public void onCreate() {
        ////Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        context = this;
        SActivity = this;
        Scontext = this;
        vectorSchoolData = new Vector<SchoolClass>();

        vectorGetTeamsData = new Vector<GetTeamsDetailsClass>();
        getTeamsDetailsClas = new ArrayList<>();
        getSchoolsList = new ArrayList<>();

        mContext = getApplicationContext();
        if (locationManager == null) {
            locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        }
    }


    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app
     */


    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(SchoolDataService.this);
        }
    }

    @Override
    public void onDestroy() {
        context = this;
        SActivity = this;
        Scontext = this;
        vectorSchoolData = new Vector<SchoolClass>();

        vectorGetTeamsData = new Vector<GetTeamsDetailsClass>();
        getSchoolsList = new ArrayList<>();
        getTeamsDetailsClas = new ArrayList<>();
        ////Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, result);
        if (result != null && result.trim().length() > 0) {
            if (whichApiCalleds.equalsIgnoreCase("school")) {
                UtilityClass.SetPreferences(context, getString(R.string.school), result);
                parseRequiredData(result);
                whichApiCalleds = "sport";
                WebServices webServices = new WebServices();
                webServices.getSports(context, SchoolDataService.this);
            } else if (whichApiCalleds.equalsIgnoreCase("sport")) {
                UtilityClass.SetPreferences(context, getString(R.string.sport), result);
                parseRequiredData(result);
                whichApiCalleds = "position";
                WebServices webServices = new WebServices();
                webServices.getPosition(context, SchoolDataService.this);
            } else if (whichApiCalleds.equalsIgnoreCase("position")) {
                UtilityClass.SetPreferences(context, getString(R.string.position), result);
                parseRequiredData(result);
                whichApiCalleds = "team";
                WebServices webServices = new WebServices();
                webServices.getTeams(SCHOOL_ID_FOR_PRE, context, SchoolDataService.this);

            } else if (whichApiCalleds.equalsIgnoreCase("team")) {
                UtilityClass.SetPreferences(context, getString(R.string.team), result);
                parseRequiredData(result);
                whichApiCalleds = "GOAL ARRAY";
                WebServices webServices = new WebServices();
                webServices.getGoalArray(context, SchoolDataService.this);
            } else if (whichApiCalleds.equalsIgnoreCase("GOAL ARRAY")) {
                parseRequiredData(result);
                whichApiCalleds = "language";
                WebServices webServices = new WebServices();

                webServices.getLanguage(context, SchoolDataService.this);
            } else if (whichApiCalleds.equalsIgnoreCase("language")) {
                parseRequiredData(result);
            } else if (whichApiCalleds.equalsIgnoreCase("teamX")) {
                parseRequiredData(result);
            }


        } else {
        }

    }

    private void parseRequiredData(String result) {
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj
                    .getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");

            Log.e("**********", "" + responseMessage);
            if (WebServices.responseCode == 200) {

                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");
                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    if (whichApiCalleds.equalsIgnoreCase("school")) {
                        getSchoolsList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetSchools[].class)));

                        try {
                            Log.d(VolleyLog.TAG, "SCHOOL: " + getPackageName().indexOf("ed"));

                            int in = context.getPackageName().indexOf("ed") + 3;
                            Log.d(VolleyLog.TAG, "SCHOOL: " + in + "   " + getPackageName().substring(in));

                            for (int i = 0; i < getSchoolsList.size(); i++) {
                                if (getSchoolsList.get(i).getSchoolName().contains(getPackageName().substring(in))) {
                                    SCHOOL_ID_FOR_PRE = getSchoolsList.get(i).getSchoolId();
                                    SCHOOL_NAME_FOR_PRE = getSchoolsList.get(i).getSchoolName();


                                }
                            }
                        } catch (Exception m) {

                        }


                        vectorSchoolData.clear();
                        SchoolClass objSchoolData = null;
                        schoolArray = new String[jsonDataArray.length()];
                        schoolIdArray = new String[jsonDataArray.length()];
                        for (int i = 0; i < jsonDataArray.length(); i++) {
                            objSchoolData = new SchoolClass();
                            JSONObject objectSchoolData = jsonDataArray.getJSONObject(i);
                            objSchoolData.setId(objectSchoolData.getString(getString(R.string.school_id_tag)));
                            objSchoolData.setSchoolName(objectSchoolData.getString(getString(R.string.school_name_tag)));
                            schoolArray[i] = objSchoolData.getSchoolName();
                            schoolIdArray[i] = objSchoolData.getId();
                            objSchoolData.setSchoolAddress(objectSchoolData.getString(getString(R.string.school_address_tag)));
                            vectorSchoolData.add(objSchoolData);
                        }
                        Log.d(VolleyLog.TAG, "parseRequiredData: " + whichApiCalleds + "Success");
                    } else if (whichApiCalleds.equalsIgnoreCase("position")) {
                        getPositionList = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), GetPosition[].class)));

                        Log.d(VolleyLog.TAG, "parseRequiredData: " + whichApiCalleds + "Success");
                    } else if (whichApiCalleds.equalsIgnoreCase("team")) {
                        getTeamsDetailsClas = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetTeamsDetailsClas[].class)));
                        GetTeam = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), com.org.godspeed.response_JsonS.getTeams.GetTeam[].class)));
                        GetTeamsDetailsClass objTeamsDetails = null;
                        vectorGetTeamsData.clear();
                        teamArray = new String[jsonDataArray.length()];
                        teamIdArray = new String[jsonDataArray.length()];

                        Gson gson = new Gson();

                        Log.e(VolleyLog.TAG, "parseRequiredData: pfKS" + jsonDataArray);

                        for (int i = 0; i < jsonDataArray.length(); i++) {
                            objTeamsDetails = new GetTeamsDetailsClass();
                            JSONObject objectPositionData = jsonDataArray.getJSONObject(i);
                            objTeamsDetails.setTeam_id(objectPositionData.getString(getString(R.string.team_id_tag)));
                            objTeamsDetails.setTeam_name(objectPositionData.getString(getString(R.string.team_name_tag)));
                            teamArray[i] = objTeamsDetails.getTeam_name();
                            teamIdArray[i] = objTeamsDetails.getTeam_id();
                            Log.e("team name is = ", teamArray[i] + " *************");
                            vectorGetTeamsData.add(objTeamsDetails);
                        }
                        Log.d(VolleyLog.TAG, "parseRequiredData: " + whichApiCalleds + "Success");
                    } else if (whichApiCalleds.equalsIgnoreCase("teamX")) {
                        getTeamsDetailsClas = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetTeamsDetailsClas[].class)));
                        GetTeam = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), com.org.godspeed.response_JsonS.getTeams.GetTeam[].class)));
                    } else if (whichApiCalleds.equalsIgnoreCase("GOAL ARRAY")) {
                        JSONArray activationData = jsonObj.getJSONArray("data");
                        goalArray = Arrays.asList(gson.fromJson(activationData.toString(), GoalArray[].class));
                    } else if (whichApiCalleds.equalsIgnoreCase("language")) {
                        JSONArray activationData = jsonObj.getJSONArray("data");

                        getLanguageList = new ArrayList<>(Arrays.asList(gson.fromJson(activationData.toString(), GetLanguage[].class)));


                    } else if (whichApiCalleds.equalsIgnoreCase("sport")) {
                        getSportsList = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), GetSport[].class)));


                        SportsDataClass objSportsData = null;
                        sportsArray = new String[jsonDataArray.length()];
                        sportsIdArray = new String[jsonDataArray.length()];
                        for (int i = 0; i < jsonDataArray.length(); i++) {
                            objSportsData = new SportsDataClass();
                            JSONObject objJsonSportsData = jsonDataArray.getJSONObject(i);
                            objSportsData.setId(objJsonSportsData.getString(getString(R.string.sport_id_tag)));
                            objSportsData.setSports_name(objJsonSportsData.getString(getString(R.string.sport_name_tag)));
                            sportsArray[i] = objSportsData.getSports_name();
                            sportsIdArray[i] = objSportsData.getId();
                        }
                    }

                }
//                JSONObject userJson = new JSONObject(usersData);
            } else {
                UtilityClass.showAlertDailog(context, responseMessage);

            }

        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();


        Log.e(VolleyLog.TAG, "onLocationChanged: " + latitude + "   " + longitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}




