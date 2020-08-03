package com.org.godspeed.fragments;

/**
 * Created by Tanveer on 8/6/2017.
 */

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;

public class FragmentAnalytics extends Fragment {


    private String[] myTeamArray = {"All Team", "Team 1", "Team 2", "Team 3"};
    private LinearLayout lLayoutForTeamName;

    public FragmentAnalytics() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_analytics, container,
                false);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Log.e("Screen", "Fragment Analytics");

        lLayoutForTeamName = view.findViewById(R.id.lLayoutForTeamName);
        for (int i = 0; i < myTeamArray.length; i++) {
            lLayoutForTeamName.addView(insertTeamsInHorizontalScrollView(myTeamArray[i]));
        }
        return view;
    }


    private View insertTeamsInHorizontalScrollView(String teamName) {


        LinearLayout layout = new LinearLayout(getActivity().getApplicationContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setGravity(Gravity.CENTER);

        TextView textViewTeamName = new TextView(getActivity().getApplicationContext());
        textViewTeamName.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textViewTeamName.setText(teamName);
        textViewTeamName.setTextColor(getResources().getColor(R.color.headerBGColor));
        textViewTeamName.setGravity(Gravity.CENTER);
        textViewTeamName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewTeamName.setPadding(15, 10, 15, 10);
        textViewTeamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getActivity()));
        if (teamName.equalsIgnoreCase(myTeamArray[1])) {
            textViewTeamName.setBackgroundResource(R.drawable.round_button_coach_board_screen);
            textViewTeamName.setTextColor(Color.BLACK);
        }


        layout.addView(textViewTeamName);
        return layout;
    }
}