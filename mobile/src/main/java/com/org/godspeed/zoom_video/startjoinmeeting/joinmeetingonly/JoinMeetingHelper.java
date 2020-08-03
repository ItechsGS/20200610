package com.org.godspeed.zoom_video.startjoinmeeting.joinmeetingonly;

import android.content.Context;
import android.util.Log;

import com.org.godspeed.zoom_video.inmeetingfunction.zoommeetingui.ZoomMeetingUISettingHelper;

import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.ZoomSDK;

public class JoinMeetingHelper {
    private final static String TAG = "JoinMeetingHelper";

    private static JoinMeetingHelper mJoinMeetingHelper;

    private ZoomSDK mZoomSDK;

    private String DISPLAY_NAME = "";

    private JoinMeetingHelper() {
        mZoomSDK = ZoomSDK.getInstance();
    }

    public synchronized static JoinMeetingHelper getInstance() {
        mJoinMeetingHelper = new JoinMeetingHelper();
        return mJoinMeetingHelper;
    }

    public int joinMeetingWithNumber(Context context, String meetingNo, String meetingPassword, String UserName) {
        DISPLAY_NAME = UserName;
        int ret = -1;
        MeetingService meetingService = mZoomSDK.getMeetingService();
        if (meetingService == null) {
            return ret;
        }

        JoinMeetingOptions opts = ZoomMeetingUISettingHelper.getJoinMeetingOptions();

        JoinMeetingParams params = new JoinMeetingParams();

        params.displayName = DISPLAY_NAME;
        params.meetingNo = meetingNo;
        params.password = meetingPassword;


        Log.d(TAG, "joinMeetingWithNumber: " + params.meetingNo + "  " + params.password);
        return meetingService.joinMeetingWithParams(context, params, opts);
    }
//
//    public int joinMeetingWithVanityId(Context context, String vanityId, String meetingPassword,String UserName) {
//        DISPLAY_NAME = UserName;
//        int ret = -1;
//        MeetingService meetingService = mZoomSDK.getMeetingService();
//        if(meetingService == null) {
//            return ret;
//        }
//
//        JoinMeetingOptions opts =ZoomMeetingUISettingHelper.getJoinMeetingOptions();
//        JoinMeetingParams params = new JoinMeetingParams();
//        params.displayName = DISPLAY_NAME;
//        params.vanityID = vanityId;
//        params.password = meetingPassword;
//        return meetingService.joinMeetingWithParams(context, params,opts);
//    }
}
