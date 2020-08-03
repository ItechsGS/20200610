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
import android.widget.TextView;

import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.DaysClass;
import com.org.godspeed.utility.ExerciseDetailsDaysWise;
import com.org.godspeed.utility.ExpandableAdapterForFuel;

import java.util.Vector;

public class FuelScreenExpendibleList extends Activity {

    public static boolean isExerciseDataSaved = false;
    int selectedExercise = 0;
    private Vector<DaysClass> vectorFoodsCategory;
    private ImageView imageViewBackArrow;
    private Context context;
    private TextView textViewScreenName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        initData();

        setContentView(R.layout.layout_expendable_listview_for_fuel);

        context = this;
        Log.e("Screen", "Fuel Screen Expendible List");
        isExerciseDataSaved = false;
        ExpandableListView exList = findViewById(R.id.expandableListViewForFuel);
//        exList.setIndicatorBounds(getResources().getInteger(R.va));
//        int left = getPi
        exList.setIndicatorBounds(5, 5);
        ExpandableAdapterForFuel exAdpt = new ExpandableAdapterForFuel(vectorFoodsCategory, this);
        exList.setIndicatorBounds(5, 20);
//        exList.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
        exList.setAdapter(exAdpt);

        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });

        initializeTextView();
    }

    private void initializeTextView() {


        textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

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
                                //Toast.makeText(context, "Call API For Save Data", Toast.LENGTH_LONG).show();
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
        vectorFoodsCategory = new Vector<DaysClass>();

        DaysClass cat1 = createCategory("Breakfast", 1, 1);
        cat1.setExerciseList(createItems("breakfast", "This is for breakfast", 3));

        DaysClass cat2 = createCategory("Lunch", 2, 2);
        cat2.setExerciseList(createItems("lunch", "This is for lunch", 2));

        DaysClass cat3 = createCategory("dinner", 2, 2);
        cat3.setExerciseList(createItems("dinner", "This is for dinner", 4));

        DaysClass cat4 = createCategory("Snacks", 2, 2);
        cat4.setExerciseList(createItems("snacks", "This is for snacks", 2));

        DaysClass cat5 = createCategory("Water", 2, 2);
        cat5.setExerciseList(createItems("water", "This is water", 3));

        vectorFoodsCategory.add(cat1);
        vectorFoodsCategory.add(cat2);
        vectorFoodsCategory.add(cat3);
        vectorFoodsCategory.add(cat4);
        vectorFoodsCategory.add(cat5);


//        final TraningNameClass objTrainingClass = CoachAddExerciseScreen.vectorTrining.get(selectedExercise);
//        if(objTrainingClass!=null)
//        {
//            for(int i = 0; i<(objTrainingClass.phaseInTraining.noOfWeeks*7);i++)
//            {
//                int day = i+1;
//                DaysClass daysName = createCategory(getString(R.string.day) + " " + day, day, day);
//                if(i<5)
//                {
//                    daysName.setExerciseList(createItems("Day 111", "This is the game n.", 5));
//                }
//                vectorDaysNumberCategory.add(daysName);
//
//            }
//        }
//        else
//        {
//            //Toast.makeText(context,"Error in parsing phase.",Toast.LENGTH_LONG).show();
//        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    private DaysClass createCategory(String name, int phaseId, int id) {
        return new DaysClass(id, id, name);
    }


    private Vector<ExerciseDetailsDaysWise> createItems(String name, String descr, int num) {
        Vector<ExerciseDetailsDaysWise> result = new Vector<ExerciseDetailsDaysWise>();

        if (name.equalsIgnoreCase("breakfast")) {
            for (int i = 0; i < num; i++) {
                int id = i + 1;
                ExerciseDetailsDaysWise item = new ExerciseDetailsDaysWise(id, "Breakfast item " + id, descr);
                result.add(item);
            }
        } else if (name.equalsIgnoreCase("lunch")) {
            for (int i = 0; i < num; i++) {
                int id = i + 1;
                ExerciseDetailsDaysWise item = new ExerciseDetailsDaysWise(id, "Lunch item " + id, descr);
                result.add(item);
            }
        } else if (name.equalsIgnoreCase("dinner")) {
            for (int i = 0; i < num; i++) {
                int id = i + 1;
                ExerciseDetailsDaysWise item = new ExerciseDetailsDaysWise(id, "dinner item " + id, descr);
                result.add(item);
            }
        } else if (name.equalsIgnoreCase("snacks")) {
            for (int i = 0; i < num; i++) {
                int id = i + 1;
                ExerciseDetailsDaysWise item = new ExerciseDetailsDaysWise(id, "snacks item " + id, descr);
                result.add(item);
            }
        } else if (name.equalsIgnoreCase("water")) {
            for (int i = 0; i < num; i++) {
                int id = i + 1;
                ExerciseDetailsDaysWise item = new ExerciseDetailsDaysWise(id, "Water item " + id, descr);
                result.add(item);
            }
        }


        return result;
    }

}