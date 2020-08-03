package com.org.godspeed.service;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeListener implements View.OnTouchListener {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    private Context context;

    public OnSwipeListener(Context c) {
        this.context = c;

        gestureDetector = new GestureDetector(c, new GestureListener(c));
    }

    public boolean onTouch(final View view, final MotionEvent motionEvent) {

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

    public double getAngle(float x1, float y1, float x2, float y2) {
        double rad = Math.atan2(y1 - y2, x2 - x1) + Math.PI;
        return (rad * 180 / Math.PI + 180) % 360;
    }

    public enum Direction {onSwipeUp, onSwipeDown, onSwipeLeft, onSwipeRight}

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        Context context;

        public GestureListener(Context c) {
            this.context = c;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onClick();
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x1 = e1.getX();
            float y1 = e1.getY();

            float x2 = e2.getX();
            float y2 = e2.getY();

            Direction direction = getDirection(x1, y1, x2, y2);


            return onSwipe(direction);
        }

        private Direction getDirection(float x1, float y1, float x2, float y2) {
            double angle = getAngle(x1, y1, x2, y2);
            return fromAngle(angle);
        }

        private Direction fromAngle(double angle) {
            if (inRange(angle, 45, 135)) {
                onSwipeDown();
                return Direction.onSwipeDown;
            } else if (inRange(angle, 0, 45) || inRange(angle, 315, 360)) {
                onSwipeRight();
                return Direction.onSwipeRight;
            } else if (inRange(angle, 225, 315)) {
                onSwipeUp();
                return Direction.onSwipeUp;
            } else {
                onSwipeLeft();
                return Direction.onSwipeLeft;
            }
        }

        private boolean inRange(double angle, float init, float end) {
            return (angle >= init) && (angle < end);
        }

        //        private float inrange(double angle, float init, float end){
//            return (angle >= init) && (angle < end);
//        }
        public boolean onSwipe(Direction direction) {
            return false;
        }
    }
}
