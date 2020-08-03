package com.org.godspeed.utility;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.godspeed.R;

public class RecyclerSectionItemDecoration extends RecyclerView.ItemDecoration {

    private final int headerOffset;
    private final boolean sticky;
    private final SectionCallback sectionCallback;
    RelativeLayout mainRlyforExercise;
    RecyclerView RecyclernameOfexercise;
    RecyclerView step2ExerciseList;
    Context context;
    private View headerView;
    private TextView header;

    public RecyclerSectionItemDecoration(int headerHeight, boolean sticky, @NonNull SectionCallback sectionCallback, RecyclerView step2ExerciseList, Context context) {
        headerOffset = headerHeight;
        this.sticky = sticky;
        this.sectionCallback = sectionCallback;
        this.step2ExerciseList = step2ExerciseList;
        this.context = context;

    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);
        if (sectionCallback.isSection(pos)) {
            outRect.top = headerOffset;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c,
                parent,
                state);

        if (headerView == null) {

            headerView = inflateHeaderView(parent);
            header = headerView.findViewById(R.id.Name);
            RecyclernameOfexercise = headerView.findViewById(R.id.RecyclernameOfexercise);
            mainRlyforExercise = headerView.findViewById(R.id.mainRlyforExercise);

            fixLayoutSize(headerView,
                    parent);


        }

        CharSequence previousHeader = "";
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            final int position = parent.getChildAdapterPosition(child);


            CharSequence title = sectionCallback.getSectionHeader(position);

            header.setText(title);

            if (!previousHeader.equals(title) || sectionCallback.isSection(position)) {
                drawHeader(c,
                        child,
                        headerView);
                previousHeader = title;

            }
        }
    }

    private void drawHeader(Canvas c, View child, View headerView) {
        c.save();
        if (sticky) {
            c.translate(0,
                    Math.max(0,
                            child.getTop() - headerView.getHeight()));
        } else {
            c.translate(0,
                    child.getTop() - headerView.getHeight());
        }
        headerView.draw(c);
        c.restore();
    }

    private View inflateHeaderView(RecyclerView parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stet_2_exercise_layout,
                        parent,
                        false);
    }

    /**
     * Measures the header view to make sure its size is greater than 0 and will be drawn
     * https://yoda.entelect.co.za/view/9627/how-to-android-recyclerview-item-decorations
     */
    private void fixLayoutSize(View view, ViewGroup parent) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
                View.MeasureSpec.UNSPECIFIED);

        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(),
                view.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                parent.getPaddingTop() + parent.getPaddingBottom(),
                view.getLayoutParams().height);

        view.measure(childWidth,
                childHeight);

        view.layout(0,
                0,
                view.getMeasuredWidth(),
                view.getMeasuredHeight());
    }

    public interface SectionCallback {

        boolean isSection(int position);

        CharSequence getSectionHeader(int position);

    }
}