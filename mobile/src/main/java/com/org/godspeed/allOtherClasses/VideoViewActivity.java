package com.org.godspeed.allOtherClasses;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.getVideoClass.LiveClassExerciseVideo;
import com.org.godspeed.service.BackgroundDownloadNotificationService;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.zoom_video.ui.LoginUserStartJoinMeetingActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.view.View.VISIBLE;
import static com.org.godspeed.service.SchoolDataService.LoginJson;

public class VideoViewActivity extends Activity implements Player.EventListener {

    public static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 123;
    public static final String PROGRESS_UPDATE = "progress_update";
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    private static final String TAG = "ExoPlayerActivity";
    private static final String KEY_VIDEO_URI = "video_uri";
    public int volume_level = 0;
    RelativeLayout la;
    int pressCount = 0;
    AudioManager mAudioManager;
    ScrollView sepVideo;
    String basePath = "";
    String fileN = null;
    ImageView my_image, imageViewBackArrow, thumbnail, MyView;
    LinearLayout downloadVideo;
    List<LiveClassExerciseVideo> liveClassExerciseVideo = new ArrayList<>();
    RelativeLayout rLayoutHeader;
    boolean result;
    String urlString;
    boolean IsVideAvailable = false;
    PlayerView videoFullScreenPlayer;
    ProgressBar spinnerVideoDetails;
    ImageView imageViewExit;
    String videoUri;
    SimpleExoPlayer player;
    Handler mHandler;
    Context context;
    Runnable mRunnable;
    RelativeLayout myView;
    LinearLayout video_layout;
    // Progress Dialog
    private ProgressDialog pDialog;
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

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // Get the layout from video_main.xml

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.videoview_main);
        context = this;
        // Find your VideoView in your video_main.xml layout
        la = findViewById(R.id.la);
        downloadVideo = findViewById(R.id.downloadVideo);
        rLayoutHeader = findViewById(R.id.rLayoutHeader);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        sepVideo = findViewById(R.id.sepVideo);
        thumbnail = findViewById(R.id.thumbnail);
        MyView = findViewById(R.id.MyView);
        myView = findViewById(R.id.myView);
        thumbnail.setVisibility(VISIBLE);
        la.setVisibility(View.GONE);
        sepVideo.setVisibility(View.VISIBLE);
        videoFullScreenPlayer = findViewById(R.id.videoFullScreenPlayer);

        video_layout = findViewById(R.id.video_layout);
        video_layout.setVisibility(VISIBLE);
        //imageViewExit = findViewById(R.id.imageViewExit);
        //spinnerVideoDetails = findViewById(R.id.spinnerVideoDetails);

        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        rLayoutHeader.setVisibility(View.VISIBLE);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volume_level = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        imageViewBackArrow.setOnClickListener(v -> {
            onBackPressed();
        });

        initializePlayer();
        String s = getIntent().getStringExtra("videoData");
        try {
            liveClassExerciseVideo = Arrays.asList(new Gson().fromJson(s, LiveClassExerciseVideo.class));


            basePath = Environment.getExternalStorageDirectory().toString() + "/Android/data/" +
                    getPackageName();


            fileN = liveClassExerciseVideo.get(0).getVideoName();
            String number = fileN.substring(fileN.lastIndexOf("live_class_exercise_video/"));
            number = number.substring(number.lastIndexOf("/") + 1);

            fileN = number;
            Log.d(VolleyLog.TAG, "Tanveeer  onCreate: " + number + "  " + fileN);

            videoUri = basePath + "/video/" + fileN;


            File f = new File(basePath + "/video/");

            File[] files = f.listFiles();

            for (File file : files) {

                if (file.getName().equalsIgnoreCase(fileN)) {
                    IsVideAvailable = true;
                    //setUp();
                    break;
                }
                //check if file name contains model number here using contains
                //or split the string on underscore and check the id index
            }
        } catch (Exception v) {
            Log.d(VolleyLog.TAG, "onCreate: " + v);
        }
        downloadVideo.setOnClickListener(v -> {
            if (IsVideAvailable) {
                UtilityClass.showAlertDailog(this, "Video already downloaded");
                return;
            }

            if (!liveClassExerciseVideo.get(0).getIs_live().equalsIgnoreCase("1")) {
                //newDownload(liveClassExerciseVideo.get(0).getVideoName());

                if (checkPermission()) {
                    startImageDownload(basePath, fileN, liveClassExerciseVideo.get(0).getVideoName());
                } else {
                    requestPermission();
                }

            }


        });

        if (liveClassExerciseVideo.get(0).getThumbnail_image() == null || liveClassExerciseVideo.get(0).getThumbnail_image().equalsIgnoreCase("")) {
            RequestOptions requestOptions = new RequestOptions();
            Glide.with(context)
                    .load(liveClassExerciseVideo.get(0).getVideoName())
                    .apply(requestOptions)
                    .thumbnail(Glide.with(context)
                            .load(liveClassExerciseVideo.get(0).getVideoName()))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                            //MyView.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(thumbnail);

        } else {
            Glide.with(context).load(
                    WebServices.BASE_URL_FOR_IMAGES_ASSETS + liveClassExerciseVideo.get(0).getThumbnail_image())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(thumbnail);
        }

        myView.setOnClickListener(vx -> {
            if (!liveClassExerciseVideo.get(0).getIs_live().equalsIgnoreCase("1")) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = vi.inflate(R.layout.video_view, null);
                releasePlayer();
                spinnerVideoDetails = v.findViewById(R.id.spinnerVideoDetails);
                videoFullScreenPlayer = v.findViewById(R.id.videoFullScreenPlayer);


                myView.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                setUp();
                MyView.setVisibility(View.GONE);
            } else {
                Intent intent = new Intent(context, LoginUserStartJoinMeetingActivity.class).putExtra("Type", "1")
                        .putExtra("meetingNo", liveClassExerciseVideo.get(0).getVideoName())
                        .putExtra("meetingPassword", liveClassExerciseVideo.get(0).getPassword())
                        .putExtra("UserName", UtilityClass.getNameAthlete(LoginJson.get(0).getFirstName(), LoginJson.get(0).getLastName(), LoginJson.get(0).getEmailId()));

                Log.d(TAG, "onCreate: " + liveClassExerciseVideo.get(0).getVideoName() + "  " + liveClassExerciseVideo.get(0).getPassword());
                startActivity(intent);
                return;
            }
        });


        result = checkPermission();

        if (result) {
            checkFolder();
        }

        if (!isConnectingToInternet(this)) {
            //Toast.makeText(this, "Please Connect to Internet", Toast.LENGTH_LONG).show();
        }


        // registerReceiver();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        finish();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        if (pressCount == 0) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                pressCount = 1;
                mAudioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        volume_level,
                        0);
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                pressCount = 1;
                mAudioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        volume_level,
                        0);
                return true;
            }
        }
        Log.e(VolleyLog.TAG, "onKeyUp: " + keyCode);
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(VideoViewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(VideoViewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(VideoViewActivity.this);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Write Storage permission is necessary to Download Images and Videos!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(VideoViewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(VideoViewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void checkAgain() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(VideoViewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(VideoViewActivity.this);
            alertBuilder.setCancelable(true);
            alertBuilder.setTitle("Permission necessary");
            alertBuilder.setMessage("Write Storage permission is necessary to Download Images and Videos!!!");
            alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(VideoViewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
                }
            });
            AlertDialog alert = alertBuilder.create();
            alert.show();
        } else {
            ActivityCompat.requestPermissions(VideoViewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
        }
    }

    //Here you can check App Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkFolder();
                } else {
                    //code for deny
                    checkAgain();
                }
                break;
        }
    }

    public void checkFolder() {
        File dir = new File(basePath + "/video/");
        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated) {
            Log.d("Folder", "Already Created");
        }
    }

    private void setUp() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.video_view, null);
        releasePlayer();
        spinnerVideoDetails = v.findViewById(R.id.spinnerVideoDetails);
        videoFullScreenPlayer = v.findViewById(R.id.videoFullScreenPlayer);


        myView.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initializePlayer();
        playVideo();
    }

    private void playVideo() {
        if (videoUri == null) {
            return;
        }
        buildMediaSource(Uri.parse(videoUri));
    }

    private void initializePlayer() {
        if (player == null) {

//


            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
            DefaultLoadControl loadControl = new DefaultLoadControl.Builder().setBufferDurationsMs(32 * 1024, 64 * 1024, 1024, 1024).createDefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

            videoFullScreenPlayer.setPlayer(player);
        }
    }

    private void buildMediaSource(Uri mUri) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mUri);
        player.prepare(videoSource);

        player.setPlayWhenReady(true);
        player.addListener(this);
        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

        //player.getAudioComponent().setVolume(0f);


    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    private void pausePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
        }
    }

    private void resumePlayer() {
        if (player != null) {
            player.setPlayWhenReady(true);
            player.getPlaybackState();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pausePlayer();
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resumePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {
            case Player.STATE_BUFFERING:
                spinnerVideoDetails.setVisibility(View.VISIBLE);
                break;
            case Player.STATE_ENDED:
                //resumePlayer();
                // Activate the force enable
                break;
            case Player.STATE_IDLE:
                break;
            case Player.STATE_READY:
                spinnerVideoDetails.setVisibility(View.GONE);
                break;
            default:
                // status = PlaybackStatus.IDLE;
                break;
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
    }

    @Override
    public void onPositionDiscontinuity(int reason) {
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override
    public void onSeekProcessed() {
    }

    private void registerReceiver() {

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PROGRESS_UPDATE);
        bManager.registerReceiver(mBroadcastReceiver, intentFilter);

    }


//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        return result == PackageManager.PERMISSION_GRANTED;
//    }

    private void startImageDownload(String basePath, String fileN, String videoName) {

        Intent intent;
        intent = new Intent(this, BackgroundDownloadNotificationService.class)
                .putExtra("basePath", basePath)
                .putExtra("fileN", fileN)
                .putExtra("videoName", videoName);
        startService(intent);

    }

    private void requestPermission() {

        // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    startImageDownload();
//                } else {
//
//                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
//
//                }
//                break;
//        }
//
}


