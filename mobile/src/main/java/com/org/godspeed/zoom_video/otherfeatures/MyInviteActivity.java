package com.org.godspeed.zoom_video.otherfeatures;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.org.godspeed.R;

import us.zoom.androidlib.util.AndroidAppUtil;

public class MyInviteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_activity);

        TextView txtUrl = findViewById(R.id.txtUrl);
        TextView txtSubject = findViewById(R.id.txtSubject);
        TextView txtMeetingId = findViewById(R.id.txtMeetingId);
        TextView txtPassword = findViewById(R.id.txtPassword);
        TextView txtRawPassword = findViewById(R.id.txtRawPassword);
        EditText edtText = findViewById(R.id.edtText);

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if (uri != null)
            txtUrl.setText("URL:" + uri.toString());

        String subject = intent.getStringExtra(AndroidAppUtil.EXTRA_SUBJECT);
        if (subject != null)
            txtSubject.setText("Subject: " + subject);

        long meetingId = intent.getLongExtra(AndroidAppUtil.EXTRA_MEETING_ID, 0);
        if (meetingId > 0)
            txtMeetingId.setText("Meeting ID: " + meetingId);

        String meetingPassword = intent.getStringExtra(AndroidAppUtil.EXTRA_MEETING_PSW);
        if (meetingPassword != null)
            txtPassword.setText("Password: " + meetingPassword);

        String meetingRawPassword = intent.getStringExtra(AndroidAppUtil.EXTRA_MEETING_RAW_PSW);
        if (meetingRawPassword != null)
            txtRawPassword.setText("Raw Password: " + meetingRawPassword);

        String text = intent.getStringExtra(AndroidAppUtil.EXTRA_TEXT);
        if (text != null)
            edtText.setText(text);
    }

}
