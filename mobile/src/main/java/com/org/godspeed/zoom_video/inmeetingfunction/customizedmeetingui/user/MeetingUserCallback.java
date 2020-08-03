package com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.user;

import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseCallback;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseEvent;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.SimpleInMeetingListener;

import java.util.List;

import us.zoom.sdk.ZoomSDK;

public class MeetingUserCallback extends BaseCallback<MeetingUserCallback.UserEvent> {

    static MeetingUserCallback instance;
    SimpleInMeetingListener userListener = new SimpleInMeetingListener() {


        @Override
        public void onMeetingUserJoin(List<Long> list) {

            for (UserEvent event : callbacks) {
                event.onMeetingUserJoin(list);
            }
        }

        @Override
        public void onMeetingUserLeave(List<Long> list) {
            for (UserEvent event : callbacks) {
                event.onMeetingUserLeave(list);
            }
        }

        @Override
        public void onSilentModeChanged(boolean inSilentMode) {
            for (UserEvent event : callbacks) {
                event.onSilentModeChanged(inSilentMode);
            }
        }

    };

    private MeetingUserCallback() {
        init();
    }

    public static MeetingUserCallback getInstance() {
        if (null == instance) {
            synchronized (MeetingUserCallback.class) {
                if (null == instance) {
                    instance = new MeetingUserCallback();
                }
            }
        }
        return instance;
    }

    protected void init() {
        ZoomSDK.getInstance().getInMeetingService().addListener(userListener);
    }

    public interface UserEvent extends BaseEvent {

        void onMeetingUserJoin(List<Long> list);

        void onMeetingUserLeave(List<Long> list);

        void onSilentModeChanged(boolean inSilentMode);
    }
}
