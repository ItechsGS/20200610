package com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.share;

import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseCallback;
import com.org.godspeed.zoom_video.inmeetingfunction.customizedmeetingui.BaseEvent;

import us.zoom.sdk.InMeetingShareController;
import us.zoom.sdk.ZoomSDK;

public class MeetingShareCallback extends BaseCallback<MeetingShareCallback.ShareEvent> {

    static MeetingShareCallback instance;
    InMeetingShareController.InMeetingShareListener shareListener = new InMeetingShareController.InMeetingShareListener() {
        @Override
        public void onShareActiveUser(long userId) {

            for (ShareEvent event : callbacks) {
                event.onShareActiveUser(userId);
            }
        }

        @Override
        public void onShareUserReceivingStatus(long userId) {

            for (ShareEvent event : callbacks) {
                event.onShareUserReceivingStatus(userId);
            }
        }
    };

    private MeetingShareCallback() {
        init();
    }

    public static MeetingShareCallback getInstance() {
        if (null == instance) {
            synchronized (MeetingShareCallback.class) {
                if (null == instance) {
                    instance = new MeetingShareCallback();
                }
            }
        }
        return instance;
    }

    protected void init() {
        ZoomSDK.getInstance().getInMeetingService().getInMeetingShareController().addListener(shareListener);
    }

    public interface ShareEvent extends BaseEvent {

        void onShareActiveUser(long userId);

        void onShareUserReceivingStatus(long userId);

    }

}
