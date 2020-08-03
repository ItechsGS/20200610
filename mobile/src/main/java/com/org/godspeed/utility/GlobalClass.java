package com.org.godspeed.utility;

import android.util.Log;

import com.android.volley.VolleyLog;


public class GlobalClass {

    public static int ivar1, ivar2;

    public static String userImage, team_id, Date = "";


    public static Boolean logoutfromAthleteCoach = false;

    //public static int position = 0;

    public static String weightValue = "";

    public static String heightValue = "";

    public static String distance = "";


    public static String OnlyAthlete = "";


    public static String[] PillarName = {"ACTIVATION", "SKILLS", "ENERGY", "BUILD"};

    public static String ConvertImperialToMetrics(String value, String Type) {
        float temp;
        if (value.equalsIgnoreCase("")) {
            return "-";
        }
        float valueForConvert = Float.parseFloat(value);
        if (Type.equalsIgnoreCase("weight")) {
            temp = (float) (valueForConvert * 0.453592);
        } else if (Type.equalsIgnoreCase("height")) {
            temp = (float) (valueForConvert * 30.48);
        } else if (Type.equalsIgnoreCase("distance")) {
            temp = (float) (valueForConvert * 1.60);
        } else {
            temp = (float) (valueForConvert * 1.0);
        }
        // temp= Math.round(temp);

        Log.e(VolleyLog.TAG, "ConvertImperialToMetrics: " + valueForConvert + "    " + temp);
        return temp + "";
    }

    public static String ConvertMetricsToImperial(String value, String Type) {
        if (value.equalsIgnoreCase("") || value.equalsIgnoreCase("-")) {
            return "0";
        }
        float temp;
        float valueForConvert = Float.parseFloat(value);

        if (Type.equalsIgnoreCase("weight")) {
            temp = (float) (valueForConvert / 0.453592);
        } else if (Type.equalsIgnoreCase("height")) {
            temp = (float) (valueForConvert / 30.48);
        } else if (Type.equalsIgnoreCase("distance")) {
            temp = (float) (valueForConvert / 1.60);
        } else {
            temp = (float) (valueForConvert * 1.0);
        }

        //temp= Math.round(temp);
        Log.e(VolleyLog.TAG, "ConvertMetricsToImperial: " + valueForConvert + "    " + temp);
        return temp + "";
    }

}
