package com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.audio;


import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseCallback;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseEvent;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.SimpleInMeetingListener;

import us.zoom.sdk.ZoomSDK;

public class MeetingAudioCallback extends BaseCallback<MeetingAudioCallback.AudioEvent> {

    static MeetingAudioCallback instance;
    SimpleInMeetingListener audioListener = new SimpleInMeetingListener() {

        @Override
        public void onUserAudioStatusChanged(long userId) {

            for (AudioEvent event : callbacks) {
                event.onUserAudioStatusChanged(userId);
            }
        }

        @Override
        public void onUserAudioTypeChanged(long userId) {
            for (AudioEvent event : callbacks) {
                event.onUserAudioTypeChanged(userId);
            }
        }

        @Override
        public void onMyAudioSourceTypeChanged(int type) {
            for (AudioEvent event : callbacks) {
                event.onMyAudioSourceTypeChanged(type);
            }
        }
    };

    private MeetingAudioCallback() {
        init();
    }

    public static MeetingAudioCallback getInstance() {
        if (null == instance) {
            synchronized (MeetingAudioCallback.class) {
                if (null == instance) {
                    instance = new MeetingAudioCallback();
                }
            }
        }
        return instance;
    }

    protected void init() {
        ZoomSDK.getInstance().getInMeetingService().addListener(audioListener);
    }


    public interface AudioEvent extends BaseEvent {

        void onUserAudioStatusChanged(long userId);

        void onUserAudioTypeChanged(long userId);

        void onMyAudioSourceTypeChanged(int type);
    }


}
