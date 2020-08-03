package com.org.godspeed.calender;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import java.util.List;

public class CustmMultipleDotSpan implements LineBackgroundSpan {
    private final float radius;
    //private int[] color = new int[0];
    private List<Integer> color;


    public CustmMultipleDotSpan(float radius, List<Integer> color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {


        int total = color.size() > 4 ? 4 : color.size();
        int leftMost = (total - 1) * -10;

        for (int i = 0; i < total; i++) {
            int oldColor = paint.getColor();
            if (color.get(i) != 0) {
                paint.setColor(color.get(i));
            }
            canvas.drawCircle((left + right) / 2 - leftMost, bottom + radius, radius, paint);
            paint.setColor(oldColor);
            leftMost = leftMost + 20;
        }
    }
}
