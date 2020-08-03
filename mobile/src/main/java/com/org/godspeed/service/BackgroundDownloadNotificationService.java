package com.org.godspeed.service;


import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.org.godspeed.allOtherClasses.VideoViewActivity.PROGRESS_UPDATE;

public class BackgroundDownloadNotificationService extends IntentService {

    public String basePath = "";
    public String fileN = null;
    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(PROGRESS_UPDATE)) {

                boolean downloadComplete = intent.getBooleanExtra("downloadComplete", false);
                //Log.d("API123", download.getProgress() + " current progress");

                if (downloadComplete) {

                    //Toast.makeText(getApplicationContext(), "File download completed", Toast.LENGTH_SHORT).show();

                    //File file = new File(videoUri);

                    // Picasso.get().load(file).into(imageView);

                }
            }
        }
    };


    public BackgroundDownloadNotificationService() {
        super("Service");
    }

    private void registerReceiver() {

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PROGRESS_UPDATE);
        bManager.registerReceiver(mBroadcastReceiver, intentFilter);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        registerReceiver();
        basePath = intent.getStringExtra("basePath");
        fileN = intent.getStringExtra("fileN");

        newDownload(intent.getStringExtra("videoName"));
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("id", "an", NotificationManager.IMPORTANCE_LOW);

            notificationChannel.setDescription("no sound");
            notificationChannel.setSound(null, null);
            notificationChannel.enableLights(false);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        notificationBuilder = new NotificationCompat.Builder(this, "id")
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setContentTitle(fileN)
                .setContentText("Downloading " + fileN)
                .setDefaults(0)
                .setAutoCancel(true);
        notificationManager.notify(0, notificationBuilder.build());

        //initRetrofit();

    }

    //hare you can start downloding video
    public void newDownload(String url) {
        final DownloadTask downloadTask = new DownloadTask(this);
        downloadTask.execute(url);
    }

    private void updateNotification(int currentProgress) {


        notificationBuilder.setProgress(100, currentProgress, false);
        notificationBuilder.setContentText("Downloaded: " + currentProgress + "%");
        notificationManager.notify(0, notificationBuilder.build());
    }
//    private void initRetrofit() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://unsplash.com/")
//                .build();
//
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<ResponseBody> request = retrofitInterface.downloadImage("photos/YYW9shdLIwo/download?force=true");
//        try {
//
//            downloadImage(request.execute().body());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//
//        }
//    }

//    private void downloadImage(ResponseBody body) throws IOException {
//
//        int count;
//        byte data[] = new byte[1024 * 4];
//        long fileSize = body.contentLength();
//        InputStream inputStream = new BufferedInputStream(body.byteStream(), 1024 * 8);
//        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "journaldev-image-downloaded.jpg");
//        OutputStream outputStream = new FileOutputStream(outputFile);
//        long total = 0;
//        boolean downloadComplete = false;
//        //int totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
//
//        while ((count = inputStream.read(data)) != -1) {
//
//            total += count;
//            int progress = (int) ((double) (total * 100) / (double) fileSize);
//
//
//            updateNotification(progress);
//            outputStream.write(data, 0, count);
//            downloadComplete = true;
//        }
//        onDownloadComplete(downloadComplete);
//        outputStream.flush();
//        outputStream.close();
//        inputStream.close();
//
//    }

    private void sendProgressUpdate(boolean downloadComplete) {

        Intent intent = new Intent(PROGRESS_UPDATE);
        intent.putExtra("downloadComplete", downloadComplete);
        LocalBroadcastManager.getInstance(BackgroundDownloadNotificationService.this).sendBroadcast(intent);
    }

    private void onDownloadComplete(boolean downloadComplete) {


        notificationManager.cancel(0);
//        notificationBuilder.setProgress(0, 0, false);
//        notificationBuilder.setContentText("Image Download Complete");
//        notificationManager.notify(0, notificationBuilder.build());

        final Context appContext = getApplicationContext();
        final Intent intent = new Intent(appContext, BackgroundDownloadNotificationService.class);
        appContext.startService(intent);
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
            // ...
        };
        appContext.bindService(intent, connection, 0);
        sendProgressUpdate(downloadComplete);


    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(0);
    }

    // DownloadTask for downloding video from URL
    public class DownloadTask extends AsyncTask<String, Integer, String> {
        Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                java.net.URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                int fileLength = connection.getContentLength();

                input = connection.getInputStream();
                ///fileN = "FbDownloader_" + UUID.randomUUID().toString().substring(0, 10) + ".mp4";
                File filename = new File(basePath + "/" + "video/", fileN);
                output = new FileOutputStream(filename);

                byte[] data = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;

                    if (fileLength > 0)
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);


                    // downloadComplete = true;
                }
                onDownloadComplete(true);

            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            //showDialog(progress_bar_type);

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            updateNotification(progress[0]);
            //pDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            // pDialog.setMessage("Download error: " + result);

            if (result != null) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //setUp();
                        // dismissDialog(progress_bar_type);

                    }
                }, 2000);
            } else {

                MediaScannerConnection.scanFile(context,
                        new String[]{basePath + "/video/" + fileN}, null,
                        (newpath, newuri) -> {
                            Log.i("ExternalStorage", "Scanned " + newpath + ":");
                            Log.i("ExternalStorage", "-> uri=" + newuri);
                        });
                //pDialog.setMessage("Download completed");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //dismissDialog(progress_bar_type);
                    }
                }, 2000);

            }
            onDownloadComplete(true);

        }
    }

}
