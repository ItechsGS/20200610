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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.AddFuelTypeScreen;

import java.util.List;
import java.util.Vector;

public class ExpandableAdapterForFuel extends BaseExpandableListAdapter {

    private List<DaysClass> catList;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context ctx;
    private RelativeLayout rLayoutForAddExercise;

    public ExpandableAdapterForFuel(Vector<DaysClass> catList, Context ctx) {

        this.itemLayoutId = R.layout.list_items_fuel_expandable;
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
            v = inflater.inflate(R.layout.list_items_fuel_expandable, parent, false);
        }

        TextView itemName = v.findViewById(R.id.textViewItemName);
        itemName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(ctx));

        TextView itemDescr = v.findViewById(R.id.textViewItemDescription);
        itemDescr.setTypeface(CustomTypeface.load_AGENCYR_Fonts(ctx));

        ExerciseDetailsDaysWise det = catList.get(groupPosition).getExerciseList().get(childPosition);

        itemName.setText(det.getName());
        itemDescr.setText(det.getDiscription());

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
        groupName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(ctx));
        groupName.setTextColor(ContextCompat.getColor(ctx, R.color.textColorYellow));

        TextView textViewAddexercise = v.findViewById(R.id.textViewAddExercise);
        textViewAddexercise.setVisibility(View.GONE);
        ImageView imageViewAddItem = v.findViewById(R.id.imageViewPlusIcon);
        imageViewAddItem.setVisibility(View.VISIBLE);
        imageViewAddItem.setAdjustViewBounds(false);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageViewAddItem.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageViewAddItem.setLayoutParams(params);


//        TextView groupDescr = (TextView) v.findViewById(R.id.groupDescr);
        rLayoutForAddExercise = v.findViewById(R.id.rLayoutForAddExercise);
        rLayoutForAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctx.startActivity(new Intent(ctx, AddFuelTypeScreen.class));
                Log.d(VolleyLog.TAG, "*************** LoginScreen *************");

                //getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                //.overridePendingTransition(R.anim.exit, R.anim.enter);

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
