package com.org.godspeed.service;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class OnSwipeTouchListener implements View.OnTouchListener {

    DisplayMetrics dm;
    private GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context c) {
        this.dm = c.getResources().getDisplayMetrics();
        gestureDetector = new GestureDetector(c, new GestureListener());
    }


    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        view.getParent().getParent().requestDisallowInterceptTouchEvent(true);
        return gestureDetector.onTouchEvent(motionEvent);
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeUp() {
    }

    public void onSwipeDown() {
    }

    public void onClick() {
    }

    public void onDoubleClick() {
    }

    public void onLongClick() {
    }


    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 250;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        //        private  final int SWIPE_MIN_DISTANCE = (int)(1.0f * dm.densityDpi / 160.0f + 0.5);
//        private  final int SWIPE_MAX_OFF_PATH = (int)(250.0f * dm.densityDpi / 160.0f + 0.5);
//        private  final int SWIPE_THRESHOLD_VELOCITY = (int)(200.0f * dm.densityDpi / 160.0f + 0.5);
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onClick();
            return super.onSingleTapUp(e);
        }
//
//        @Override
//        public boolean onDoubleTap(MotionEvent e) {
//            onDoubleClick();
//            return super.onDoubleTap(e);
//        }

        @Override
        public void onLongPress(MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }

        // Determines the fling velocity and then fires the appropriate swipe event accordingly
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();

//                if (Math.abs(diffX) > Math.abs(diffY)) {
//                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
//
//                        if (diffX > 0) {
//                            onSwipeRight(); // Right swipe
//                        } else {
//                            onSwipeLeft();  // Left swipe
//                        }
//                    }
//                } else {
//                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                        if (diffY > 1) {
//                            onSwipeDown(); // Down swipe
//                        } else {
//                            onSwipeUp(); // Up swipe
//                        }
//                    }
//                }
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    onSwipeLeft();

                }
                // left to right swipe
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    onSwipeRight();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }
}