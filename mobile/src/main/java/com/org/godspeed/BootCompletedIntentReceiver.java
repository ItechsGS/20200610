package com.org.godspeed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.org.godspeed.service.BackgroundLocationUpdateService;
import com.org.godspeed.service.NotificationScheduler;
import com.org.godspeed.utility.UtilityClass;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent serviceIntent = new Intent(context, BackgroundLocationUpdateService.class);


            serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
            } else {
                context.startService(serviceIntent);
            }


        }
        if (UtilityClass.getPreferences(context, "SHOW_LOCAL_NOTIFICATION") != null && UtilityClass.getPreferences(context, "SHOW_LOCAL_NOTIFICATION").equalsIgnoreCase("true")) {
            NotificationScheduler.showNotification(context);
        }
    }
}