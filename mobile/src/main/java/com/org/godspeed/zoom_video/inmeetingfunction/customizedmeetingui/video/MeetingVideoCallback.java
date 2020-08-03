package com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.video;

import android.util.Log;

import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseCallback;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseEvent;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.SimpleInMeetingListener;

import us.zoom.sdk.ZoomSDK;

public class MeetingVideoCallback extends BaseCallback<MeetingVideoCallback.VideoEvent> {

    private static final String TAG = MeetingVideoCallback.class.getSimpleName();
    private static MeetingVideoCallback instance;
    SimpleInMeetingListener videoListener = new SimpleInMeetingListener() {

        @Override
        public void onUserVideoStatusChanged(long userId) {
            for (VideoEvent event : callbacks) {
                event.onUserVideoStatusChanged(userId);
            }
        }

        @Override
        public void onSpotlightVideoChanged(boolean on) {
            Log.d(TAG, "onSpotlightVideoChanged:" + on);

        }

        @Override
        public void onMeetingActiveVideo(long userId) {
            Log.d(TAG, "onMeetingActiveVideo:" + userId);
        }
    };

    private MeetingVideoCallback() {
        init();
    }

    public static MeetingVideoCallback getInstance() {
        if (null == instance) {
            synchronized (MeetingVideoCallback.class) {
                if (null == instance) {
                    instance = new MeetingVideoCallback();
                }
            }
        }
        return instance;
    }

    protected void init() {
        ZoomSDK.getInstance().getInMeetingService().addListener(videoListener);
    }


    public interface VideoEvent extends BaseEvent {
        void onUserVideoStatusChanged(long userId);
    }

}
