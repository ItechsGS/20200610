package com.org.godspeed.zoom_video.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.org.godspeed.R;
import com.org.godspeed.zoom_video.startjoinmeeting.UserLoginCallback;
import com.org.godspeed.zoom_video.startjoinmeeting.emailloginuser.EmailUserLoginHelper;

import us.zoom.sdk.ZoomApiError;
import us.zoom.sdk.ZoomAuthenticationError;
import us.zoom.sdk.ZoomSDK;

public class EmailUserLoginActivity extends Activity implements UserLoginCallback.ZoomDemoAuthenticationListener, View.OnClickListener {

    private final static String TAG = "ZoomSDKExample";

    private EditText mEdtUserName;
    private EditText mEdtPassord;
    private RelativeLayout mBtnLogin;
    private View mProgressPanel;
    private ZoomSDK mZoomSDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.email_login_activity);

        mEdtUserName = findViewById(R.id.userName);
        mEdtPassord = findViewById(R.id.password);

        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(this);

        mProgressPanel = findViewById(R.id.progressPanel);
    }

    @Override
    protected void onResume() {
        super.onResume();

        UserLoginCallback.getInstance().addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        UserLoginCallback.getInstance().removeListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            onClickBtnLogin();
        }
    }

    private void onClickBtnLogin() {
        mZoomSDK = ZoomSDK.getInstance();
        if (mZoomSDK.isLoggedIn()) {
            Intent intent = new Intent(this, LoginUserStartJoinMeetingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.exit, R.anim.enter);
            UserLoginCallback.getInstance().removeListener(this);
            //finish();
            return;
        }

        String userName = mEdtUserName.getText().toString().trim();
        String password = mEdtPassord.getText().toString().trim();
        if (userName.length() == 0 || password.length() == 0) {
            Toast.makeText(this, "You need to enter user name and password.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!(EmailUserLoginHelper.getInstance().login(userName, password) == ZoomApiError.ZOOM_API_ERROR_SUCCESS)) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully or sdk is logging in.", Toast.LENGTH_LONG).show();
        } else {
            mBtnLogin.setVisibility(View.GONE);
            mProgressPanel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onZoomSDKLoginResult(long result) {
        if (result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
            //Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginUserStartJoinMeetingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.exit, R.anim.enter);
            UserLoginCallback.getInstance().removeListener(this);
            finish();
        } else {
            Toast.makeText(this, "Login failed result code = " + result, Toast.LENGTH_SHORT).show();
        }
        mBtnLogin.setVisibility(View.VISIBLE);
        mProgressPanel.setVisibility(View.GONE);
    }

    @Override
    public void onZoomSDKLogoutResult(long result) {
        if (result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
            Toast.makeText(this, "Logout successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Logout failed result code = " + result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onZoomIdentityExpired() {
        //Zoom identity expired, please re-login;
    }

    @Override
    public void onZoomAuthIdentityExpired() {

    }
}
