package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.DaysClass;
import com.org.godspeed.utility.ExerciseDetailsDaysWise;
import com.org.godspeed.utility.ExpandableAdapter;
import com.org.godspeed.utility.TraningNameClass;

import java.util.Vector;

public class DaysWiseExerciseList extends Activity {

    public static boolean isExerciseDataSaved = false;
    int selectedExercise = 0;
    private Vector<DaysClass> vectorDaysNumberCategory;
    private Context context;
    private RelativeLayout rLayoutForActivationTitle, rLayoutForSkillsTitle, rLayoutForBuildTitle, rLayoutForRegenTitle, rLayoutForEnergyTitle, rLayoutForFuelTitle;
    private TextView textViewActivation;
    private TextView textViewRegen;
    private TextView textViewFuel;
    private TextView textViewBuild;
    private TextView textViewEnergy;
    private TextView textViewSkills;
    private View.OnClickListener pillarSelectionBackgroundClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            rLayoutForActivationTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
            rLayoutForBuildTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
            rLayoutForFuelTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
            rLayoutForEnergyTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
            rLayoutForRegenTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
            rLayoutForSkillsTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);

            textViewActivation.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));
            textViewSkills.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));
            textViewBuild.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));
            textViewRegen.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));
            textViewEnergy.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));
            textViewFuel.setTextColor(ContextCompat.getColor(context, R.color.color_gray_for_health_profile_button_unselected));

            switch (view.getId()) {

                case R.id.rLayoutForActivationTitle:
                    rLayoutForActivationTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewActivation.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForBuildTitle:
                    rLayoutForBuildTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewBuild.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForFuelTitle:
                    rLayoutForFuelTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewFuel.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForEnergyTitle:
                    rLayoutForEnergyTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewEnergy.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForRegenTitle:
                    rLayoutForRegenTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewRegen.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForSkillsTitle:
                    rLayoutForSkillsTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewSkills.setTextColor(ContextCompat.getColor(context, R.color.color_black_for_health_profile_button_selected));
                    break;

            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        selectedExercise = getIntent().getExtras().getInt("position");
        Log.e("Screen", "Date Wise Exercise List");
        initData();
        setContentView(R.layout.layout_expendable_listview_for_exercise);

        context = this;
        isExerciseDataSaved = false;
        ExpandableListView exList = findViewById(R.id.expandableListViewForExercise);
//        exList.setIndicatorBounds(getResources().getInteger(R.va));
//        int left = getPi
        exList.setIndicatorBounds(5, 5);
        ExpandableAdapter exAdpt = new ExpandableAdapter(vectorDaysNumberCategory, this);
        exList.setIndicatorBounds(5, 20);
//        exList.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
        exList.setAdapter(exAdpt);

        ImageView imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });

        ImageView imageViewSave = findViewById(R.id.imageViewSave);
        imageViewSave.setImageResource(R.drawable.save_training);
        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //Toast.makeText(context, "Call API For Save Data", Toast.LENGTH_LONG).show();
//                new AlertDialog.Builder(context).setTitle("God Speed \n Traning Programm Name")
//                showAlertDialog();

                // here we will put logic for saving exercise data based on day wise before giving training name...
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });

        setRelativeLayoutControl();
        initializeTextView();
    }

    private void initializeTextView() {


        TextView textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewActivation = findViewById(R.id.textViewActivation);
        textViewActivation.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewRegen = findViewById(R.id.textViewRegen);
        textViewRegen.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewFuel = findViewById(R.id.textViewFuel);
        textViewFuel.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewBuild = findViewById(R.id.textViewBuild);
        textViewBuild.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewEnergy = findViewById(R.id.textViewEnergy);
        textViewEnergy.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSkills = findViewById(R.id.textViewSkills);
        textViewSkills.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
    }

    private void setRelativeLayoutControl() {
        rLayoutForActivationTitle = findViewById(R.id.rLayoutForActivationTitle);
        rLayoutForActivationTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
        rLayoutForActivationTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        rLayoutForSkillsTitle = findViewById(R.id.rLayoutForSkillsTitle);
        rLayoutForSkillsTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
        rLayoutForSkillsTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        rLayoutForBuildTitle = findViewById(R.id.rLayoutForBuildTitle);
        rLayoutForBuildTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
        rLayoutForBuildTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        rLayoutForRegenTitle = findViewById(R.id.rLayoutForRegenTitle);
        rLayoutForRegenTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
        rLayoutForRegenTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        rLayoutForEnergyTitle = findViewById(R.id.rLayoutForEnergyTitle);
        rLayoutForEnergyTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
        rLayoutForEnergyTitle.setOnClickListener(pillarSelectionBackgroundClickListener);

        rLayoutForFuelTitle = findViewById(R.id.rLayoutForFuelTitle);
        rLayoutForFuelTitle.setBackgroundResource(R.drawable.unselected_transparent_button_icon);
        rLayoutForFuelTitle.setOnClickListener(pillarSelectionBackgroundClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (isExerciseDataSaved) {
//            //Toast.makeText(context, "Exercise Data saved by user.", Toast.LENGTH_LONG).show();
//        }
//
//        isExerciseDataSaved = false;
    }


    private void showAlertDialog() {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_dialog_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = promptsView
                .findViewById(R.id.editTextDialogUserInput);
        userInput.setSingleLine(true);

        // set dialog message
        alertDialogBuilder
                .setTitle(getString(R.string.app_name))
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                ////Toast.makeText(context, "Call API For Save Data", Toast.LENGTH_LONG).show();
                                onBackPressed();
                                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    private void initData() {
        vectorDaysNumberCategory = new Vector<DaysClass>();

//        DaysClass cat1 = createCategory("Day 1", 1, 1);
//        cat1.setExerciseList(createItems("Day 111", "This is the game n.", 5));
//
//        DaysClass cat2 = createCategory("Day 2", 2, 2);
//        cat2.setExerciseList(createItems("Day 222", "This is the phone n.", 5));
//
//        vectorDaysNumberCategory.add(cat1);
//        vectorDaysNumberCategory.add(cat2);


        final TraningNameClass objTrainingClass = CoachAddExerciseScreen.vectorTrining.get(selectedExercise);
        if (objTrainingClass != null) {
            for (int i = 0; i < (objTrainingClass.phaseInTraining.noOfWeeks * 7); i++) {
                int day = i + 1;
                DaysClass daysName = createCategory(getString(R.string.day) + " " + day, day, day);
                if (i < 5) {
                    daysName.setExerciseList(createItems("Day 111", "This is the game n.", 5));
                }
                vectorDaysNumberCategory.add(daysName);

            }
        } else {
            //Toast.makeText(context,"Error in parsing phase.",Toast.LENGTH_LONG).show();
        }


    }

    private DaysClass createCategory(String name, int phaseId, int id) {
        return new DaysClass(id, id, name);
    }


    private Vector<ExerciseDetailsDaysWise> createItems(String name, String descr, int num) {
        Vector<ExerciseDetailsDaysWise> result = new Vector<ExerciseDetailsDaysWise>();

        for (int i = 0; i < num; i++) {
            int id = i + 1;
            ExerciseDetailsDaysWise item = new ExerciseDetailsDaysWise(id, "Exercise " + id);
            result.add(item);
        }

        return result;
    }

}