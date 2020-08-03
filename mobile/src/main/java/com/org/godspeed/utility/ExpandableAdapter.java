package com.org.godspeed.utility;

/**
 * Created by Tanveer on 1/31/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.ExerciseTypeScreen;

import java.util.List;
import java.util.Vector;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<DaysClass> catList;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context ctx;
    private RelativeLayout rLayoutForAddExercise;

    public ExpandableAdapter(Vector<DaysClass> catList, Context ctx) {

        this.itemLayoutId = R.layout.layout_exercise_list_days_based;
        this.groupLayoutId = R.layout.layout_days_name;
        this.catList = catList;
        this.ctx = ctx;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return catList.get(groupPosition).getExerciseList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return catList.get(groupPosition).getExerciseList().get(childPosition).hashCode();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.layout_exercise_list_days_based, parent, false);
        }

        TextView itemName = v.findViewById(R.id.textViewExerciseName);

//        TextView itemDescr = (TextView) v.findViewById(R.id.itemDescr);

        ExerciseDetailsDaysWise det = catList.get(groupPosition).getExerciseList().get(childPosition);

        itemName.setText(det.getName());
//        itemDescr.setText(det.getDescr());

        return v;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size = catList.get(groupPosition).getExerciseList().size();
        System.out.println("Child for group [" + groupPosition + "] is [" + size + "]");
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return catList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return catList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return catList.get(groupPosition).hashCode();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.layout_days_name, parent, false);
        }

        TextView groupName = v.findViewById(R.id.textViewDaysName);
//        TextView groupDescr = (TextView) v.findViewById(R.id.groupDescr);
        rLayoutForAddExercise = v.findViewById(R.id.rLayoutForAddExercise);
        rLayoutForAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctx.startActivity(new Intent(ctx, ExerciseTypeScreen.class));

                Log.d(VolleyLog.TAG, "*************** ExerciseTypeScreen *************");

                //ctxoverridePendingTransition(R.anim.exit, R.anim.enter);

            }
        });

        DaysClass cat = catList.get(groupPosition);

        groupName.setText(cat.getName());
//        groupDescr.setText(cat.getDescr());

        return v;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
