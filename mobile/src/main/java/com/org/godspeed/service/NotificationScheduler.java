package com.org.godspeed.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.android.volley.VolleyLog;
import com.org.godspeed.BootCompletedIntentReceiver;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.SplashScreen;
import com.org.godspeed.utility.UtilityClass;

import java.util.Calendar;
import java.util.Random;

import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;
import static android.content.Context.ALARM_SERVICE;

public class NotificationScheduler {
    public static final int DAILY_REMINDER_REQUEST_CODE = 100;
    public static final String TAG = "NotificationScheduler";
    static AlarmManager mAlarmManager;

    public static void setReminder(Context context, int minute, int seconds) {
        mAlarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, minute);
        cal.add(Calendar.SECOND, seconds);
        Log.e(VolleyLog.TAG, "setReminder: " + minute + "  " + seconds);
        //cal.add(Calendar.MINUTE, Minute);
        Intent notificationIntent = new Intent(context, BootCompletedIntentReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 90, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

    }

    public static void cancelReminder(Context context) {
        //AlarmManager alarmManager2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mAlarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context.getApplicationContext(), 90, new Intent(context, BootCompletedIntentReceiver.class), PendingIntent.FLAG_CANCEL_CURRENT);
        mAlarmManager.cancel(pendingIntent2);
        UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION", "false");

    }

    public static void showNotification(Context context) {
//        Intent intentX = new Intent(context, SplashScreen.class);
//        intentX.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        String CHANNEL_ID = context.getPackageName();
        String CHANNEL_NAME = context.getPackageName();

        Intent intent = new Intent(context, SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, new Random().nextInt(), intent, PendingIntent.FLAG_ONE_SHOT);
        //PendingIntent contentIntent = PendingIntent.getActivity(context, new Random().nextInt(), new Intent(context, SplashScreen.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT ), 0);
        UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION", "false");
        cancelReminder(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, CHANNEL_ID).setSmallIcon(R.drawable.ic_stat_name_gs)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.weightlift_gray_icon))
                .setContentText(UtilityClass.getPreferences(context, "SHOW_LOCAL_NOTIFICATION_TPNAME"))
                //.setContentText()//.setOngoing(true).setWhen(0)
                .setChannelId(CHANNEL_ID)
                .setAutoCancel(true)
                .setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_MAX);
        //.setBadgeIconType(NotificationCompat.COLOR_DEFAULT);
        builder.setContentIntent(contentIntent);
        Notification notification = builder.build();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("150");
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "150",
                    CHANNEL_ID,
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notification);

    }


}