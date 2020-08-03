package com.org.godspeed.utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class UtilityClass {


    public static final long TODAY_START_UTC_TIME;
    public static final long ONE_DAY = 24 * 60 * 60 * 1000;
    public static final long ONE_WEEK = 24 * 60 * 60 * 7000;
    public static final long ONE_MONTH = 24 * 60 * 60 * 1000 * 30000;
    public static final long ONE_YEAR = 24 * 60 * 60 * 1000 * 365;
    public static final String TAG = "GODSPEED:******    ";
    public static Dialog dialogX;
    public static Handler handler;
    public static SimpleDateFormat DateFormatForServer = new SimpleDateFormat("yyyy-MM-dd");
    public static Boolean getDeviceTypeMobile = true;
    public static View.OnTouchListener prevent_touch_parent = new View.OnTouchListener() {
        private int CLICK_ACTION_THRESHOLD = 100;
        private float startX;
        private float startY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    startY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    float endX = event.getX();
                    float endY = event.getY();
                    if (isAClick(startX, endX, startY, endY)) {

                    }
                    break;
            }
            v.getParent().requestDisallowInterceptTouchEvent(true); //specific to my project
            return false; //specific to my project
        }

        private boolean isAClick(float startX, float endX, float startY, float endY) {
            float differenceX = Math.abs(startX - endX);
            float differenceY = Math.abs(startY - endY);
            return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
        }
    };

    static {
        TODAY_START_UTC_TIME = getTodayStartUtcTime();
    }

    Bitmap bitmap = null;

    public static String urlEncoded(String path) {
        String urlEncoded = "";
        urlEncoded = Uri.encode(path, "@#&=*+-_.,:!?()/~'%");

        return urlEncoded;
    }

    public static String getDeviceId(Context context) {
        String deviceId = "";
//		TelephonyManager mTelephonyMgr = (TelephonyManager) context
//				.getSystemService(Context.TELEPHONY_SERVICE);
//		deviceId = mTelephonyMgr.getDeviceId();
//		if (deviceId == null || deviceId.trim().length() == 0) {
//			deviceId = "14"
//					+ // we make this look like a valid IMEI
//					Build.BOARD.length() % 10 + Build.BRAND.length() % 10
//					+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
//					+ Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
//					+ Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
//					+ Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
//					+ Build.TAGS.length() % 10 + Build.TYPE.length() % 10
//					+ Build.USER.length() % 10; // 13 digits
//
//
        return deviceId;
    }

    public static void GetDeviceType(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        // 6.5inch device or bigger
        // smaller device
        getDeviceTypeMobile = !(diagonalInches >= 6.5);
    }

    public static long getTodayStartUtcTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static long getPreviosMonthFirstDate(long mCurrentStartTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mCurrentStartTime);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTimeInMillis();
    }

    public static long getNextMonthFirstDate(long mCurrentStartTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mCurrentStartTime);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, +1);
        return calendar.getTimeInMillis();
    }

    public static long getUnitBetweenDates(Date startDate, Date endDate, TimeUnit unit) {
        final long oneDayInMillis = 24 * 60 * 60 * 1000;
        Date c = new Date((endDate.getTime() / oneDayInMillis + 1) * oneDayInMillis - 1);
        Log.d(VolleyLog.TAG, "getUnitBetweenDates: " + c);
        long timeDiff = c.getTime() - startDate.getTime();
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

    public static String getFormattedTime(long mCurrentStartTime, String format) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(mCurrentStartTime);
    }

    public static Boolean isDeviceInternetAvailable(Context con) {
        ConnectivityManager connectivityManager;
        boolean connected = false;
        try {
            connectivityManager = (ConnectivityManager) con
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            connected = true;
                        }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connected;
    }

    /**************************************************** Set Image Preferences ***************************************************/

    public static void SetImagePreferences(Context con, Bitmap bitmap, String user_profile_image) {
        // save the data
        SharedPreferences preferences = con.getSharedPreferences(
                con.getString(R.string.app_name), 0);
        SharedPreferences.Editor editor = preferences.edit();
        if (bitmap != null) {
            editor.putString(user_profile_image, encodeTobase64(bitmap));
        } else {
            editor.putString(user_profile_image, null);
        }
        editor.apply();
    }

    /**************************************************** get Image Preferences ***************************************************/
    public static String getImagePreferences(Context con) {

        SharedPreferences sharedPreferences = con.getSharedPreferences(
                con.getString(R.string.app_name), 0);
        String value = sharedPreferences.getString("PHOTO", null);
        return value;

    }

    public static void showWaitDialog(Dialog dialogx, Context context) {
        hide();
        dialogX = new Dialog(context);
        handler = new Handler();
        dialogX = dialogx;
        //dialogX.setView(view);
        try {
            dialogX.requestWindowFeature(Window.FEATURE_NO_TITLE);
        } catch (Exception c) {
        }
        dialogX.setContentView(R.layout.godspeed_main_logo_wait_indicator);
        dialogX.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ImageView imageView = dialogX.findViewById(R.id.imageViewForZoomInOut);
        //slideUp(imageView);

        dialogX.setCanceledOnTouchOutside(false);
        dialogX.getWindow().setDimAmount(0);
        Activity activity = (Activity) context;
        //dialogX.setCancelable(false);
        slideUp(imageView);
        if (activity != null && !activity.isFinishing()) {
            dialogX.show();
        }
    }

    public static void hide() {
        try {
            dialogX.dismiss();
            handler.removeCallbacksAndMessages(null);
            dialogX.cancel();
        } catch (Exception c) {

        }
    }

    private static void slideUp(ImageView imageView) {
        imageView.setBackgroundResource(R.drawable.animation_wait_window);
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.start();

        handler.postDelayed(() -> slideDown(imageView), 970);

    }

    private static void slideDown(ImageView imageView) {
        imageView.setBackgroundResource(R.drawable.animation_wait_window2);
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.start();


        handler.postDelayed(() -> slideUp(imageView), 970);
    }

    public static String getDate(String mDate) {
        String inputString = ""; // i.e. (dd/MM/yyyy) format

        SimpleDateFormat fromUser = new SimpleDateFormat("dd - MMM - yyyy");

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateFromUser = fromUser.parse(mDate); // Parse it to the exisitng date pattern and return Date type
            inputString = myFormat.format(dateFromUser); // format it to the date pattern you prefer
            System.out.println(inputString); // outputs : 2009-05-19

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e(VolleyLog.TAG, "getDate: " + inputString);
        return inputString;
    }

    public static long GettimeInMiliseconds(String someDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd - MMM - yyyy"); // Month.Day.Year
        Date d = null;
        try {
            d = formatter.parse(someDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e(VolleyLog.TAG, "GettimeInMiliseconds: " + getFormattedTime(d.getTime()));
        return d.getTime();
    }

    public static long getNextDate(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        Calendar calendar = Calendar.getInstance();

        // get a date to represent "today"
        try {
            calendar.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();
        return tomorrow.getTime();
    }

    public static long getPreviousDate(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, -1);
        Date tomorrow = calendar.getTime();
        return tomorrow.getTime();
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap bitmap_image = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap_image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        return imageEncoded;
    }

    public static String getNameAthlete(String Fname, String Lname, String EmailId) {
        String Name = "";
        String FnameX = Fname != null ? Fname.trim() : "";
        String LnameX = Lname != null ? Lname.trim() : "";
        String EmailIdX = EmailId != null ? EmailId.trim() : "";

        if (FnameX.equalsIgnoreCase("") && LnameX.equalsIgnoreCase("")) {
            try {
                Name = EmailIdX.substring(0, EmailId.indexOf("@"));
            } catch (Exception m) {
                Name = EmailId;
            }
        } else {
            Name = LnameX + " " + FnameX;
        }
        return Name;
    }

    public static void showAlertDailog(Context context, String msg) {

        final custom_alert_class mAlert = new custom_alert_class(context);
        mAlert.setMessage(msg);
        mAlert.OneButton(true);
        mAlert.setNegativeButton("Ok", view -> mAlert.dismiss());
        mAlert.show();
    }

    /*****************************************************
     * set shared preferences
     **************************************************/
    public static void SetPreferences(Context con, String key, String value) {
        // save the data
        SharedPreferences preferences = con.getSharedPreferences(
                con.getString(R.string.app_name), 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /****************************************************
     * get shared preferences
     ***************************************************/
    public static String getPreferences(Context con, String key) {

        SharedPreferences sharedPreferences = con.getSharedPreferences(
                con.getString(R.string.app_name), 0);
        String value = sharedPreferences.getString(key, null);
        return value;

    }

    public static Point getDeviceSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int densityDpi = (int) (metrics.density * 160f);

        return size;
    }

    public static long getPreviosMonthLastDate(long X) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(X);
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        calendar.add(Calendar.MONTH, +1);
        int lastDate = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDate);
        return calendar.getTimeInMillis();
    }

    public static long getfirstdateoflastyear(long mCurrentStartTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCurrentStartTime);
        cal.add(Calendar.YEAR, -1);
        return cal.getTimeInMillis();
    }

    public static long getLastDateofLastYear(long mCurrentStartTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCurrentStartTime);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        return cal.getTimeInMillis();
    }

    public static long getfirstdateofNextyear(long mCurrentStartTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCurrentStartTime);
        cal.add(Calendar.YEAR, +1);
        return cal.getTimeInMillis();
    }

    public static long getLastDateofNextYear(long mCurrentStartTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCurrentStartTime);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        return cal.getTimeInMillis();
    }

    public static long getNextMonthLastDate(long X) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(X);
        calendar.add(Calendar.MONTH, 0);
        int lastDate = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDate);
        return calendar.getTimeInMillis();
    }

    public static long getStartTimeOfToday() {
//		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//		cal.set(Calendar.DATE, cal.get(Calendar.DATE));
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        //calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getEndTimeOfToday() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    public static long getStartTimeOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

        return cal.getTimeInMillis();
    }

    public static String getFormattedTime(long mCurrentStartTime) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Log.e(VolleyLog.TAG, "getFormattedTime: " + dateFormat.format(mCurrentStartTime));
        return dateFormat.format(mCurrentStartTime);
    }

    public static String getFormattedTimeX(long mCurrentStartTime) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(mCurrentStartTime);
    }

    public static long getfirstdateofyear() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return cal.getTimeInMillis();
    }

    public static long getLastdateofyear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        return cal.getTimeInMillis();
    }

    public static long getMonthDateFirstdate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.clear(Calendar.HOUR);
        return c.getTimeInMillis();
    }

    public static long getMonthDateLastdate() {
        Calendar calendar = Calendar.getInstance();
        int lastDate = calendar.getActualMaximum(Calendar.DATE);

        calendar.set(Calendar.DATE, lastDate);
        // int lastDay = calendar.get(Calendar.DAY_OF_WEEK);

        return calendar.getTimeInMillis();
        //return lastDate.
    }

    public static String getMonthDateFirstdateFromGivenDate(String StartDateFrom) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(StartDateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertedDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTimeInMillis());
    }

    public static String getMonthDateLastdateFromGivenDate(String lastDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(lastDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertedDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTimeInMillis());
        //return lastDate.
    }

    public static String getcurrentDateAndTime() {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(c);
        return formattedDate;
    }

    public static long atStartOfDay(Date date) {
        //getFormattedTime(long mCurrentStartTime)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {


        Thread thread = new Thread() {
            @Override
            public void run() {


                MediaMetadataRetriever mediaMetadataRetriever = null;
                try {
                    mediaMetadataRetriever = new MediaMetadataRetriever();
                    if (Build.VERSION.SDK_INT >= 14)
                        mediaMetadataRetriever.setDataSource("https://www.rpmhoover.com/Videos/Bench%20Press%20-%202%20Arm%20-%20Alternating%20-%20DB.mov", new HashMap<String, String>());
                    else
                        mediaMetadataRetriever.setDataSource("https://www.rpmhoover.com/Videos/Bench%20Press%20-%202%20Arm%20-%20Alternating%20-%20DB.mov");
                    //   mediaMetadataRetriever.setDataSource(videoPath);
                    bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                } finally {
                    if (mediaMetadataRetriever != null) {
                        mediaMetadataRetriever.release();
                    }
                }
            }
        };

        thread.start();

        return bitmap;
    }

    public String getMd5Key(String key) {

        try {
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(key.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";

    }

    public long atEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    public class FavoritesUserData {
        public String Favorites_Id = "";
        public String Alias_Name = "";
        public String Actual_Name = "";
    }


//	public static String savePushNotificationMessage(String message,
//			Context context) {
//		String type="";
//		try {
//			JSONObject obj = new JSONObject(message);
//			// Type":"Invitation","
//			type = obj.getString(context
//					.getString(R.string.TypeParameter));
//			SetPreferences(context,
//					context.getString(R.string.TypeParameter),
//					obj.getString(context
//							.getString(R.string.TypeParameter)));
//			if (type.equalsIgnoreCase("Invitation")) {
//
//
//				SetPreferences(context,
//						context.getString(R.string.MessageParameter),
//						obj.getString(context
//								.getString(R.string.MessageParameter)));
//				SetPreferences(context,
//						context.getString(R.string.Session_IdParameter),
//						obj.getString(context
//								.getString(R.string.Session_IdParameter)));
//				SetPreferences(
//						context,
//						context.getString(R.string.FlagParameter),
//						obj.getString(context.getString(R.string.FlagParameter)));
//				SetPreferences(
//						context,
//						context.getString(R.string.Invited_User_TypeParameter),
//						obj.getString(context
//								.getString(R.string.Invited_User_TypeParameter)));
//				SetPreferences(context,
//						context.getString(R.string.Invited_User_IdParameter),
//						obj.getString(context
//								.getString(R.string.Invited_User_IdParameter)));
//				SetPreferences(context,
//						context.getString(R.string.FreeMinutesParameter),
//						obj.getString(context
//								.getString(R.string.FreeMinutesParameter)));
//				// below invited username is same as username but creating
//				// different key to hold invited username.
//				SetPreferences(context,
//						context.getString(R.string.Invited_UserNameParameter),
//						obj.getString(context
//								.getString(R.string.User_NameParameter)));
//				SetPreferences(
//						context,
//						context.getString(R.string.RateParameter),
//						obj.getString(context.getString(R.string.RateParameter)));
//			}
//			else
//			{
//				SetPreferences(context,
//						context.getString(R.string.MessageParameter),
//						obj.getString(context
//								.getString(R.string.MessageParameter)));
//			}
//
//		} catch (JSONException e) {
//
//			e.printStackTrace();
//		}
//		return  type;
//	}
//
//	public static String isApplicationRunningInBackground(Context context)
//	{
//		String appRunningInBackground = "false";
//		ActivityManager am = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
//		List l = am.getRunningAppProcesses();
//		Iterator i = l.iterator();
//		PackageManager pm = context.getPackageManager();
//		while(i.hasNext()) {
//		  ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo)(i.next());
//		  try {
//		    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
//		    Log.w("LABEL", c.toString());
//		    if(c.toString().equalsIgnoreCase(context.getString(R.string.app_name)))
//		    {
//		    	appRunningInBackground="true";
//		    	break;
//		    }
//		  }catch(Exception e) {
//		    //Name Not FOund Exception
//		  }
//		}
//
//		return appRunningInBackground;
//	}
//

}
