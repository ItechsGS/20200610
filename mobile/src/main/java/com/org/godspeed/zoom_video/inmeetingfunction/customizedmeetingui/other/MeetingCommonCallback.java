package com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.other;


import android.util.Log;

import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseCallback;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseEvent;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.SimpleInMeetingListener;

import us.zoom.sdk.InMeetingEventHandler;
import us.zoom.sdk.MeetingServiceListener;
import us.zoom.sdk.MeetingStatus;
import us.zoom.sdk.ZoomSDK;

import static android.content.ContentValues.TAG;

public class MeetingCommonCallback extends BaseCallback<MeetingCommonCallback.CommonEvent> {


    static MeetingCommonCallback instance;
    MeetingServiceListener serviceListener = new MeetingServiceListener() {
        @Override
        public void onMeetingStatusChanged(MeetingStatus meetingStatus, int errorCode, int internalErrorCode) {
            for (CommonEvent event : callbacks) {
                event.onMeetingStatusChanged(meetingStatus, errorCode, internalErrorCode);
            }
        }
    };
    SimpleInMeetingListener commonListener = new SimpleInMeetingListener() {
        @Override
        public void onWebinarNeedRegister() {
            for (CommonEvent event : callbacks) {
                event.onWebinarNeedRegister();
            }
        }

        public void onMeetingFail(int errorCode, int internalErrorCode) {
            for (CommonEvent event : callbacks) {
                event.onMeetingFail(errorCode, internalErrorCode);
            }
        }

        @Override
        public void onMeetingLeaveComplete(long ret) {
            for (CommonEvent event : callbacks) {
                event.onMeetingLeaveComplete(ret);
            }
        }


        @Override
        public void onMeetingNeedPasswordOrDisplayName(boolean needPassword, boolean needDisplayName, InMeetingEventHandler handler) {
            for (CommonEvent event : callbacks) {
                event.onMeetingNeedPasswordOrDisplayName(needPassword, needDisplayName, handler);
                Log.d(TAG, "onMeetingNeedPasswordOrDisplayName: " + needPassword + "   " + needDisplayName);
            }
        }

        @Override
        public void onMeetingNeedColseOtherMeeting(InMeetingEventHandler inMeetingEventHandler) {
            for (CommonEvent event : callbacks) {
                event.onMeetingNeedColseOtherMeeting(inMeetingEventHandler);
            }
        }

        @Override
        public void onJoinWebinarNeedUserNameAndEmail(InMeetingEventHandler inMeetingEventHandler) {
            for (CommonEvent event : callbacks) {
                event.onJoinWebinarNeedUserNameAndEmail(inMeetingEventHandler);
            }
        }

        @Override
        public void onSpotlightVideoChanged(boolean b) {

        }

        @Override
        public void onFreeMeetingReminder(boolean isOrignalHost, boolean canUpgrade, boolean isFirstGift) {
            for (CommonEvent event : callbacks) {
                event.onFreeMeetingReminder(isOrignalHost, canUpgrade, isFirstGift);
            }
        }
    };

    private MeetingCommonCallback() {
        init();
    }

    public static MeetingCommonCallback getInstance() {
        if (null == instance) {
            synchronized (MeetingCommonCallback.class) {
                if (null == instance) {
                    instance = new MeetingCommonCallback();
                }
            }
        }
        return instance;
    }

    protected void init() {
        ZoomSDK.getInstance().getInMeetingService().addListener(commonListener);
        ZoomSDK.getInstance().getMeetingService().addListener(serviceListener);
    }

    public interface CommonEvent extends BaseEvent {

        void onWebinarNeedRegister();

        void onMeetingFail(int errorCode, int internalErrorCode);

        void onMeetingLeaveComplete(long ret);

        void onMeetingStatusChanged(MeetingStatus meetingStatus, int errorCode, int internalErrorCode);

        void onMeetingNeedPasswordOrDisplayName(boolean needPassword, boolean needDisplayName, InMeetingEventHandler handler);

        void onMeetingNeedColseOtherMeeting(InMeetingEventHandler inMeetingEventHandler);

        void onJoinWebinarNeedUserNameAndEmail(InMeetingEventHandler inMeetingEventHandler);

        void onFreeMeetingReminder(boolean isOrignalHost, boolean canUpgrade, boolean isFirstGift);

    }


}
