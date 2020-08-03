package com.org.godspeed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private int mSwipeOrientation = VERTICAL;
    //private ScrollerCustomDuration mScroller = null;

    public VerticalViewPager(Context context) {
        super(context);
        mSwipeOrientation = VERTICAL;
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSwipeOrientation(VERTICAL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // return super.onTouchEvent(mSwipeOrientation == VERTICAL ? swapXY(event) : event);
        return super.onTouchEvent(swapXY(event));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mSwipeOrientation == VERTICAL) {
            boolean intercepted = super.onInterceptHoverEvent(swapXY(event));
            swapXY(event);
            return intercepted;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void setSwipeOrientation(int swipeOrientation) {
        if (swipeOrientation == HORIZONTAL || swipeOrientation == VERTICAL)
            mSwipeOrientation = swipeOrientation;
        else
            throw new IllegalStateException("Swipe Orientation can be either CustomViewPager.HORIZONTAL" +
                    " or CustomViewPager.VERTICAL");
        initSwipeMethods();
    }

//    private void setSwipeOrientation(Context context, AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewPager);
//        mSwipeOrientation = typedArray.getInteger(R.styleable.CustomViewPager_swipe_orientation, 0);
//        typedArray.recycle();
//        initSwipeMethods();
//    }

    private void initSwipeMethods() {
        if (mSwipeOrientation == VERTICAL) {
            // The majority of the work is done over here
            setPageTransformer(true, new VerticalPageTransformer());
            // The easiest way to get rid of the overscroll drawing that happens on the left and right
            setOverScrollMode(OVER_SCROLL_NEVER);
        }
    }

//    @Override
//    public void setCurrentItem(int item, boolean smoothScroll) {
//        super.setCurrentItem(item, false);
//    }
//
//    @Override
//    public void setCurrentItem(int item) {
//        super.setCurrentItem(item, false);
//    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        //mScroller.setScrollDurationFactor(scrollFactor);
    }

    private MotionEvent swapXY(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();

        float newX = (event.getY() / height) * width;
        float newY = (event.getX() / width) * height;

        event.setLocation(newX, newY);
        return event;
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if (position < -1) {
                // This page is way off-screen to the left
                page.setAlpha(0);
            } else if (position <= 1) {
                page.setAlpha(1);

                // Counteract the default slide transition
                page.setTranslationX(page.getWidth() * -position);

                // set Y position to swipe in from top
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            } else {
                // This page is way off screen to the right
                page.setAlpha(0);
            }
        }
    }


//    private class VerticalPageTransformerAnimate implements ViewPager.PageTransformer {
//        private static final float MIN_SCALE = 0.75f;
//
//        @Override
//        public void transformPage(View view, float position) {
//
//            int pageWidth = view.getWidth();
//            int pageHeight = view.getHeight();
//            float alpha = 0;
//            if (0 <= position && position <= 1) {
//                alpha = 1 - position;
//            } else if (-1 < position && position < 0) {
//                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                float verticalMargin = pageHeight * (1 - scaleFactor) / 2;
//                float horizontalMargin = pageWidth * (1 - scaleFactor) / 2;
//                if (position < 0) {
//                    view.setTranslationX(horizontalMargin - verticalMargin / 2);
//                } else {
//                    view.setTranslationX(-horizontalMargin + verticalMargin / 2);
//                }
//
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
//
//                alpha = position + 1;
//            }
//
//            view.setAlpha(alpha);
//            view.setTranslationX(view.getWidth() * -position);
//            float yPosition = position * view.getHeight();
//            view.setTranslationY(yPosition);
//
//        }
//    }

}


//
//public class VerticalViewPager extends ViewPager {
//
//    public VerticalViewPager(Context context) {
//        super(context);
//        init();
//    }
//
//    public VerticalViewPager(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    private void init() {
//       // setPageTransformer(true, new VerticalPageTransformer());
//        setPageTransformer(true, new VerticalPageTransformerAnimate());
//        setOverScrollMode(OVER_SCROLL_NEVER);
//    }
//
//    private class VerticalPageTransformer implements ViewPager.PageTransformer {
//        @Override
//        public void transformPage(View view, float position) {
//
//            if (position < -1) { // [-Infinity,-1)
//                // This page is way off-screen to the left.
//                view.setAlpha(0);
//
//            } else if (position <= 1) { // [-1,1]
//                view.setAlpha(1);
//
//                // Counteract the default slide transition
//                view.setTranslationX(view.getWidth() * -position);
//
//                //set Y position to swipe in from top
//                float yPosition = position * view.getHeight();
//                view.setTranslationY(yPosition);
//
//            } else { // (1,+Infinity]
//                // This page is way off-screen to the right.
//                view.setAlpha(0);
//            }
//
//
//        }
//    }
//
//    /**
//     * Swaps the X and Y coordinates of your touch event.
//     */
//    private MotionEvent swapXY(MotionEvent ev) {
//        float width = getWidth();
//        float height = getHeight();
//
//        float newX = (ev.getY() / height) * width;
//        float newY = (ev.getX() / width) * height;
//
//        ev.setLocation(newX, newY);
//
//        return ev;
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
//        swapXY(ev); // return touch coordinates to original reference frame for any child views
//        return intercepted;
//    }
//
//    private class VerticalPageTransformerAnimate implements ViewPager.PageTransformer {
//        private static final float MIN_SCALE = 0.75f;
//
//        @Override
//        public void transformPage(View view, float position) {
//
//            int pageWidth = view.getWidth();
//            int pageHeight = view.getHeight();
//            float alpha = 0;
//            if (0 <= position && position <= 1) {
//                alpha = 1 - position;
//            } else if (-1 < position && position < 0) {
//                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                float verticalMargin = pageHeight * (1 - scaleFactor) / 2;
//                float horizontalMargin = pageWidth * (1 - scaleFactor) / 2;
//                if (position < 0) {
//                    view.setTranslationX(horizontalMargin - verticalMargin / 2);
//                } else {
//                    view.setTranslationX(-horizontalMargin + verticalMargin / 2);
//                }
//
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
//
//                alpha = position + 1;
//            }
//
//            view.setAlpha(alpha);
//            view.setTranslationX(view.getWidth() * -position);
//            float yPosition = position * view.getHeight();
//            view.setTranslationY(yPosition);
//
//        }
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        return super.onTouchEvent(swapXY(ev));
//    }
//}