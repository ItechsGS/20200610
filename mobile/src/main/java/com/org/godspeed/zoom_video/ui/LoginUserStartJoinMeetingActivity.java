package com.org.godspeed.zoom_video.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.zoom_video.initsdk.AuthConstants;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.MyMeetingActivity;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.RawDataMeetingActivity;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.view.MeetingWindowHelper;
import com.org.godspeed.zoom_video.otherfeatures.scheduleforloginuser.PreMeetingExampleActivity;
import com.org.godspeed.zoom_video.startjoinmeeting.emailloginuser.EmailLoginUserStartMeetingHelper;
import com.org.godspeed.zoom_video.startjoinmeeting.joinmeetingonly.JoinMeetingHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

import us.zoom.sdk.MeetingError;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.MeetingServiceListener;
import us.zoom.sdk.MeetingStatus;
import us.zoom.sdk.ZoomAuthenticationError;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKAuthenticationListener;

import static us.zoom.sdk.MeetingStatus.MEETING_STATUS_DISCONNECTING;


public class LoginUserStartJoinMeetingActivity extends Activity implements AuthConstants, MeetingServiceListener, ZoomSDKAuthenticationListener, GodSpeedInterface {

    private final static String TAG = "ZoomSDKExample";
    private final static int STYPE = MeetingService.USER_TYPE_API_USER;
    String meetingNo = "";
    String meetingPassword = "";
    int Live = 0;
    ZoomSDK zoomSDK;
    private EditText mEdtMeetingNo;
    private EditText mEdtMeetingPassword;
    private EditText mEdtVanityId;
    private Button mBtnStartInstantMeeting;
    private Button mBtnPreMeeting;
    private Button mBtnLoginOut;
    private Button mBtnSettings;
    private Button mReturnMeeting;
    private boolean mbPendingStartMeeting = false;
    private boolean isResumed = false;
    private String UserName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_user_start_join);
        zoomSDK = ZoomSDK.getInstance();
        mEdtMeetingNo = findViewById(R.id.edtMeetingNo);
        mEdtVanityId = findViewById(R.id.edtVanityUrl);
        mEdtMeetingPassword = findViewById(R.id.edtMeetingPassword);
        mBtnStartInstantMeeting = findViewById(R.id.btnLoginUserStartInstant);
        mBtnPreMeeting = findViewById(R.id.btnPreMeeting);
        mBtnLoginOut = findViewById(R.id.btnLogout);
        mBtnSettings = findViewById(R.id.btn_settings);
        mReturnMeeting = findViewById(R.id.btn_return);
        registerListener();
        Live = Integer.parseInt(getIntent().getStringExtra("Type"));

        if (Live == 0) {
            onClickBtnLoginUserStartInstant(null);
        } else {

            meetingNo = getIntent().getStringExtra("meetingNo");
            meetingPassword = getIntent().getStringExtra("meetingPassword");
            UserName = getIntent().getStringExtra("UserName");

            Log.d(TAG, "onCreate: " + meetingNo + "   " + meetingPassword);
            onClickBtnJoinMeeting(null);


        }
    }

    private void registerListener() {
        zoomSDK.addAuthenticationListener(this);
        MeetingService meetingService = zoomSDK.getMeetingService();
        if (meetingService != null) {
            Log.d(TAG, "registerListener: " + meetingService.getCurrentMeetingUrl());
            meetingService.addListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;

        Log.d(TAG, "onResume: ");
        refreshUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onResume:onPause ");
        isResumed = false;
    }

    @Override
    protected void onDestroy() {
        zoomSDK = ZoomSDK.getInstance();
        zoomSDK.removeAuthenticationListener(this);//unregister ZoomSDKAuthenticationListener
        if (zoomSDK.isInitialized()) {
            MeetingService meetingService = zoomSDK.getMeetingService();
            meetingService.removeListener(this);//unregister meetingServiceListener
        }
        MeetingWindowHelper.getInstance().removeOverlayListener();

        Log.d(TAG, "onResume:onDestroy ");
        super.onDestroy();
    }

    public void onClickBtnJoinMeeting(View view) {

        String vanityId = mEdtVanityId.getText().toString().trim();


        if (meetingPassword == null) {
            meetingPassword = "";
        }

        if (meetingNo.length() != 0 && vanityId.length() != 0) {
            Toast.makeText(this, "Both meeting number and vanity id have value,  just set one of them", Toast.LENGTH_LONG).show();
            return;
        }

        ZoomSDK zoomSDK = ZoomSDK.getInstance();

        if (!zoomSDK.isInitialized()) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
            return;
        }

        MeetingService meetingService = zoomSDK.getMeetingService();


        int ret = -1;

        ret = JoinMeetingHelper.getInstance().joinMeetingWithNumber(this, meetingNo, meetingPassword, UserName);

        Log.i(TAG, "onClickBtnJoinMeeting, ret=" + ret);
    }

    public void onClickBtnStartMeeting(View view) {
        String meetingNo = mEdtMeetingNo.getText().toString().trim();

        if (meetingNo.length() == 0) {
            Toast.makeText(this, "You need to enter a meeting number/ vanity  which you want to join.", Toast.LENGTH_LONG).show();
            return;
        }


        if (!zoomSDK.isInitialized()) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
            return;
        }

        final MeetingService meetingService = zoomSDK.getMeetingService();

        if (meetingService.getMeetingStatus() != MeetingStatus.MEETING_STATUS_IDLE) {
            long lMeetingNo = 0;
            try {
                lMeetingNo = Long.parseLong(meetingNo);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid meeting number: " + meetingNo, Toast.LENGTH_LONG).show();
                return;
            }

            if (meetingService.getCurrentRtcMeetingNumber() == lMeetingNo) {
                meetingService.returnToMeeting(this);
                return;
            }

            new AlertDialog.Builder(this)
                    .setMessage("Do you want to leave current meeting and start another?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        mbPendingStartMeeting = true;
                        meetingService.leaveCurrentMeeting(false);
                    })
                    .setNegativeButton("No", (dialog, which) -> {

                    })
                    .show();
            return;
        }

        int ret = -1;
//        if (vanityId.length() != 0) {
//            ret = EmailLoginUserStartMeetingHelper.getInstance().startMeetingWithVanityId(this, vanityId);
//        } else {
//            ret = EmailLoginUserStartMeetingHelper.getInstance().startMeetingWithNumber(this, meetingNo);
//        }
        Log.i(TAG, "onClickBtnStartMeeting,XXxxxxxxxXXX ret=" + ret + "  " + zoomSDK.getMeetingService().getCurrentMeetingUrl());
    }

    public void onClickBtnLoginUserStartInstant(View view) {
        zoomSDK = ZoomSDK.getInstance();

        if (!zoomSDK.isInitialized()) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
            return;
        }

        int ret = EmailLoginUserStartMeetingHelper.getInstance().startInstanceMeeting(this);


    }

    public void onClickReturnMeeting(View view) {
        MeetingWindowHelper.getInstance().hiddenMeetingWindow(true);
        Intent intent = new Intent(this, MyMeetingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        overridePendingTransition(R.anim.exit, R.anim.enter);
    }

    public void onClickBtnPreMeeting(View view) {
        Intent intent = new Intent(this, PreMeetingExampleActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.exit, R.anim.enter);
    }

    public void onClickBtnLogout(View view) {
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        if (!zoomSDK.logoutZoom()) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, MeetingSettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMeetingStatusChanged(MeetingStatus meetingStatus, int errorCode,
                                       int internalErrorCode) {
        Log.i(TAG, "onMeetingStatusChanged, meetingStatus=" + meetingStatus + ", errorCode=" + errorCode
                + ", internalErrorCode=" + internalErrorCode);

        if (meetingStatus == MeetingStatus.MEETING_STATUS_FAILED && errorCode == MeetingError.MEETING_ERROR_CLIENT_INCOMPATIBLE) {
            Toast.makeText(this, "Version of ZoomSDK is too low!", Toast.LENGTH_LONG).show();
        }


        if (mbPendingStartMeeting && meetingStatus == MeetingStatus.MEETING_STATUS_IDLE) {
            mbPendingStartMeeting = false;
            onClickBtnStartMeeting(null);
        }
        if (meetingStatus == MeetingStatus.MEETING_STATUS_CONNECTING) {
            showMeetingUi();
        }
        refreshUI();
        try {

            Log.i(TAG, "onClickBtnLoginUserStartInstant,XXxxxxxxxXXX ret=" + "  " + zoomSDK.getInMeetingService().getCurrentMeetingNumber() + "  " + zoomSDK.getInMeetingService().getMeetingPassword() + "             GSON   " + new Gson().toJson(zoomSDK.getInMeetingService()));
            WebServices webServices = new WebServices();
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");


            if (zoomSDK.getInMeetingService().getCurrentMeetingNumber() != 0) {
                if (Live == 0) {
                    webServices.addLiveClass("8", format2.format(new Date()) + "",
                            zoomSDK.getInMeetingService().getCurrentMeetingNumber() + "", zoomSDK.getInMeetingService().getMeetingPassword() + "",
                            this, LoginUserStartJoinMeetingActivity.this);
                }

            }

        } catch (Exception v) {

        }


        if (meetingStatus == MEETING_STATUS_DISCONNECTING) {
            //onBackPressed();,
            finish();
        }
    }

    private void refreshUI() {
        if (null == ZoomSDK.getInstance().getMeetingService()) {
            return;
        }
        ZoomSDK.getInstance().getMeetingSettingsHelper().setCustomizedMeetingUIEnabled(false);
        MeetingStatus meetingStatus = ZoomSDK.getInstance().getMeetingService().getMeetingStatus();
        if (meetingStatus == MeetingStatus.MEETING_STATUS_CONNECTING || meetingStatus == MeetingStatus.MEETING_STATUS_INMEETING
                || meetingStatus == MeetingStatus.MEETING_STATUS_RECONNECTING) {
            mBtnSettings.setVisibility(View.GONE);
            mReturnMeeting.setVisibility(View.GONE);
        } else {
            mBtnSettings.setVisibility(View.GONE);
            mReturnMeeting.setVisibility(View.GONE);
        }
        if (ZoomSDK.getInstance().getMeetingSettingsHelper().isCustomizedMeetingUIEnabled()) {
            if (meetingStatus == MeetingStatus.MEETING_STATUS_INMEETING && isResumed) {
                MeetingWindowHelper.getInstance().showMeetingWindow(this);
            } else {
                MeetingWindowHelper.getInstance().hiddenMeetingWindow(true);
            }
        }
    }

    private void showMeetingUi() {
        ZoomSDK.getInstance().getMeetingSettingsHelper().setCustomizedMeetingUIEnabled(false);
        if (ZoomSDK.getInstance().getMeetingSettingsHelper().isCustomizedMeetingUIEnabled()) {
            SharedPreferences sharedPreferences = getSharedPreferences("UI_Setting", Context.MODE_PRIVATE);
            boolean enable = sharedPreferences.getBoolean("enable_rawdata", false);
            Intent intent = null;
//            if (!enable) {
//                intent = new Intent(this, MyMeetingActivity.class);
//            } else {
            intent = new Intent(this, RawDataMeetingActivity.class);
            //intent = new Intent(this, RawDataMeetingActivity.class);
            //}
            intent.putExtra("from", 1);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            this.startActivity(intent);
            overridePendingTransition(R.anim.exit, R.anim.enter);
        }
    }

    @Override
    public void onBackPressed() {
        if (ZoomSDK.getInstance().isLoggedIn()) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onZoomSDKLoginResult(long l) {

    }

    @Override
    public void onZoomSDKLogoutResult(long result) {
        if (result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
            Toast.makeText(this, "Logout successfully", Toast.LENGTH_SHORT).show();
            //showLoginView();
        } else {
            Toast.makeText(this, "Logout failed result code = " + result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onZoomIdentityExpired() {
        ZoomSDK.getInstance().logoutZoom();
    }

    @Override
    public void onZoomAuthIdentityExpired() {

    }

    private void showLoginView() {
        mEdtMeetingPassword.postDelayed(() -> {
            if (ZoomSDK.getInstance().isInitialized()) {
                Intent intent = new Intent(LoginUserStartJoinMeetingActivity.this, EmailUserLoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.exit, R.anim.enter);
            }
            finish();
        }, 500);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MeetingWindowHelper.getInstance().onActivityResult(requestCode, this);
    }

    @Override
    public void ApiResponse(String result) {
        Log.d(TAG, "ApiResponse: " + result);
    }
}