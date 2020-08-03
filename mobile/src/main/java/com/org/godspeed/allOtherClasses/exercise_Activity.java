package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail.ABR;
import com.org.godspeed.response_JsonS.AbrPojo.getAbrNames.GetAbrName;
import com.org.godspeed.response_JsonS.ExerciseNameList.Exercise;
import com.org.godspeed.response_JsonS.ExerciseNameList.ExerciseName;
import com.org.godspeed.response_JsonS.ExerciseNameList.SelectedExercise;
import com.org.godspeed.response_JsonS.ExerciseNameList.Set;
import com.org.godspeed.response_JsonS.ExerciseTypeinExercise.ExerciseTypeinExercise;
import com.org.godspeed.response_JsonS.ResponseOfAddExercise.ResponseOfAddExercise;
import com.org.godspeed.response_JsonS.exerciseTypeName.ExerciseTypeName;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.ExoPlayerActivity;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.RecyclerSectionItemDecoration;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.exerciseTypeNameList;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.getAbrNames;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.UtilityClass.urlEncoded;

public class exercise_Activity extends Activity implements GodSpeedInterface {
    Bitmap unselected_tick_icon;
    Bitmap selected_tick_icon;
    int addTrainingExerciseCOUNT = 0;
    RecyclerSectionItemDecoration sectionItemDecoration;
    private Gson gson = new Gson();
    private Context context;
    private RelativeLayout videoView, floatingbutton;
    private List<String> SelectedExercises = new ArrayList<>();
    private String SelectedExerciseTypeName = "";
    private Boolean changeType = true;
    private String singleName;
    private ListofNames listofNames;
    private LinearLayout lLayoutImperial, rLayoutMatric;
    private TextView TextMatric, TextImperial, textSelectExerciseType, textSelectExerciseType2;
    private Boolean Exercise = false;
    private ImageView imgSteps;
    private ImageView imageViewBackArrow;
    private int SelectedItem = 0;
    private int SelectedItemSTEP2 = 0;
    private Step3Adapter step3adapter;
    private List<Exercise> exercise = new ArrayList<>();
    private Boolean step1, step2, step3, step4, step5 = false;
    private String program_id = "";
    private String phase = "";
    private String week = "";
    private String day = "";
    private String pillar = "";
    private String type_id = "";
    private String exercise_id = "";
    private String measurement_id = "";
    private Transition transition;
    private RelativeLayout SearchLayout;
    private ImageView imageViewSearch;
    private TextView Cancel;
    private WebServices webServices = new WebServices();
    private List<ABR> abrs = new ArrayList<>();
    private List<GetAbrName> getAbrNamesX = new ArrayList<>();
    private String whichapicAlled = "";
    private List<ExerciseTypeName> exerciseTypeNameListX = new ArrayList<>();
    private List<SelectedExercise> selectedExerciseList = new ArrayList<>();
    private RecyclerView selectedExerciseListRecycler, ExerciseView, step2ExerciseList, abrRecyclerView;
    private List<ExerciseTypeinExercise> exerciseTypeinExerciseList = new ArrayList<>();
    private step1Adapter myExerciseNameViewAdapter;
    private Step2Adapter step2Adapter;
    private VideoView videoViewPlayer;
    private ImageView closeVideo;
    private MediaController mediaController;
    private ProgressDialog progressDialog;
    private EditText searchViewExerciseType;
    private step5Adapter recyclerViewDoseData;
    private String id;
    private Animation zoomIn, zoomOut;
    private ImageView imageViewForZoomInOut;
    private boolean isAnimationStarted = false;
    private String SelectedABR = "";
    private List<ExerciseName> exerciseNameListSave = new ArrayList<>();
    private String StepSelectedExercise = "";
    private int SelectedPosition = 0;
    private Boolean AllComplete = false;
    private List<ExerciseTypeinExercise> exerciseTypeinExercise = new ArrayList<>();
    private String SelectedTypeName = "";
    private String WOD = "";
    private String WODDescription = "";

    private List<ExerciseTypeinExercise> exerciseTypeinExerciseForSend = new ArrayList<>();


    private List<GetAbrName> getAbrNamesForsend = new ArrayList<>();

    private List<ExerciseName> exerciseNameListX = new ArrayList<>();
    private int indexofll = 0;
    private String type_group_id = "";
    private int exercise_type_group_id_Count = 0;
    private Boolean bISSetExist = false, bISRepExist = false, bISWeightExist = false, bISHeightDistanceExist = false, bISTimeExist = false;
    private List<GetAbrName> filterListbyId = new ArrayList<>();
    private Dialog dialog;
    private DoseDetail recyclerViewDoseDetail;
    private String SelectedABRFORSHOW = "";

    private String wod_dose_values, wod_details = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_exercise_);
        context = this;
        dialog = new Dialog(context);
        step2ExerciseList = findViewById(R.id.step2ExerciseList);


        unselected_tick_icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.unselected_tick_icon);

        selected_tick_icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.selected_tick_icon);
        selectedExerciseList = new ArrayList<>();

        exerciseTypeinExercise = new ArrayList<>();


        exerciseTypeinExerciseForSend = new ArrayList<>();

        getAbrNamesForsend = new ArrayList<>();
        exerciseNameListSave = new ArrayList<>();


        getArrayList();
        Bundle bundle = getIntent().getExtras();
        //exerciseNameListSave = (ArrayList<ExerciseName>) bundle.getSerializable("list");

        recyclerViewDoseDetail = new DoseDetail(context, new ArrayList<>(), true);
        ExerciseView = findViewById(R.id.ExerciseView);
        Cancel = findViewById(R.id.Cancel);
        imageViewSearch = findViewById(R.id.imageViewSearch);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        SearchLayout = findViewById(R.id.SearchLayout);

        if (UtilityClass.getDeviceTypeMobile) {
            ExerciseView.setLayoutManager(new GridLayoutManager(this, 4));
        } else {
            ExerciseView.setLayoutManager(new GridLayoutManager(this, 7));

            ExerciseView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }


        if (getIntent().getStringExtra("type_group_id") != null) {
            type_group_id = getIntent().getStringExtra("type_group_id");
        }


        transition = new Slide(Gravity.TOP);

        transition.setDuration(300);
        transition.addTarget(R.id.rSearchAthleteText);

        imageViewSearch.setOnClickListener(view -> {
            toggle();
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
                hideKeyBoard();
            }
        });
        //
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myExerciseNameViewAdapter = new step1Adapter(context);

                program_id = getIntent().getStringExtra("TrainingId");
                phase = getIntent().getStringExtra("phase");
                week = getIntent().getStringExtra("week");
                day = getIntent().getStringExtra("day");
                pillar = getIntent().getStringExtra("pillar");
                WOD = getIntent().getStringExtra("wod");
                WODDescription = getIntent().getStringExtra("wod_description");


                ExerciseView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
                    }
                });


                imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
                    }
                });


                textSelectExerciseType = findViewById(R.id.textSelectExerciseType);
                textSelectExerciseType.setTextColor(getResources().getColor(R.color.textColorYellow));

                selectedExerciseListRecycler = findViewById(R.id.selectedExerciseListRecycler);

                textSelectExerciseType2 = findViewById(R.id.textSelectExerciseType2);
                textSelectExerciseType2.setTextColor(getResources().getColor(R.color.textColorYellow));
                step3adapter = new Step3Adapter(context);

                imgSteps = findViewById(R.id.imgSteps);

                Log.e(VolleyLog.TAG, "run: " + WOD);


                videoViewPlayer = findViewById(R.id.videoViewPlayer);
                videoView = findViewById(R.id.videoView);

                lLayoutImperial = findViewById(R.id.lLayoutImperial);
                rLayoutMatric = findViewById(R.id.rLayoutMatric);
                TextMatric = findViewById(R.id.TextMatric);
                TextImperial = findViewById(R.id.TextImperial);

                floatingbutton = findViewById(R.id.floatingbutton);
                Step1and2();

                rLayoutMatric.setOnClickListener(v -> {
                    Step1and2();
                });
                floatingbutton.setOnClickListener(v -> {

                    if (WOD.equalsIgnoreCase("1")) {
                        addNote(context, SelectedTypeName + " - WOD", "");
                    } else {
                        if (selectedExerciseListRecycler.getVisibility() == VISIBLE) {
                            addSetAPI();
                        } else {
                            Step3and4();
                        }
                    }

                });

                lLayoutImperial.setOnClickListener(v -> {
                    if (WOD.equalsIgnoreCase("1")) {
                        addNote(context, SelectedTypeName + " - WOD", "");
                    } else {
                        if (selectedExerciseListRecycler.getVisibility() == VISIBLE) {
                            addSetAPI();
                        } else {
                            Step3and4();
                        }
                    }


                });

                closeVideo = findViewById(R.id.closeVideo);
                closeVideo.setOnClickListener(view -> {
                    videoViewPlayer.stopPlayback();
                    videoViewPlayer.setMediaController(null);
                    videoView.setVisibility(GONE);
                });
                SelectedExercises = new ArrayList<>();

                try {
                    SelectedExerciseTypeName = getIntent().getStringExtra("SelectedExerciseTypeName");
                    step1 = true;
                } catch (Exception x) {

                }
                if (SelectedExerciseTypeName == null) {
                    changeType = false;
                    SelectedExerciseTypeName = "";
                }


                step2ExerciseList.setNestedScrollingEnabled(false);

                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                linearLayoutManager1.setAutoMeasureEnabled(false);
                step2ExerciseList.setLayoutManager(linearLayoutManager1);


                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                linearLayoutManager.setAutoMeasureEnabled(false);
                selectedExerciseListRecycler.setLayoutManager(linearLayoutManager);
                step2ExerciseList.setItemViewCacheSize(20);
                step2ExerciseList.setDrawingCacheEnabled(true);
                step2ExerciseList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

                searchViewExerciseType = findViewById(R.id.searchViewExerciseType);
                searchViewExerciseType.setVisibility(View.VISIBLE);

                searchViewExerciseType.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String text = s.toString();
                        if (Exercise) {
                            step2Adapter.filter(text);
                        } else {
                            recyclerViewDoseData.filter(text);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                //UtilityClass.showWaitDialog(new Dialog(context),context);
            }
        }, 10);


    }


    public void toggle() {


        searchViewExerciseType.setText("");

        TransitionManager.beginDelayedTransition(SearchLayout, transition);

        SearchLayout.setVisibility(SearchLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);


        if (SearchLayout.getVisibility() == VISIBLE) {
            hideKeyBoard();
            searchViewExerciseType.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(searchViewExerciseType, InputMethodManager.SHOW_IMPLICIT);
            //gridViewAthlete.setEnabled(false);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void addNote(Context context, String event, String eventData) {
        if (SelectedExerciseTypeName.equalsIgnoreCase("")) {
            UtilityClass.showAlertDailog(context, "Please complete step 1.");
            return;
        }

        if (selectedExerciseList.size() < 1) {
            UtilityClass.showAlertDailog(context, "Please complete step 2.");
            return;
        }
        View rootView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_box_for_sets, null);
        BubbleLinearLayout bubbleView = rootView.findViewById(R.id.mainRlyofAddset);

        BubblePopupWindow dialog = new BubblePopupWindow(rootView, bubbleView);


        dialog.setCancelOnTouch(false);
        dialog.setCancelOnTouchOutside(true);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        CardView Cancel = rootView.findViewById(R.id.Cancel);
        CardView Submit = rootView.findViewById(R.id.Submit);
        EditText addnotesWod = rootView.findViewById(R.id.addnotesWod);

        addnotesWod.setText(WODDescription);

        Cancel.setOnClickListener(view -> {
            dialog.dismiss();
        });

        EditText rx1 = rootView.findViewById(R.id.rx1);
        EditText rx2 = rootView.findViewById(R.id.rx2);
        EditText rx3 = rootView.findViewById(R.id.rx3);

        TextView txt = rootView.findViewById(R.id.EventName);
        ImageView addsetSave = rootView.findViewById(R.id.addsetSave);
        addsetSave.setVisibility(VISIBLE);


        addsetSave.setOnClickListener(view -> {


            wod_dose_values = rx1.getText().toString().trim() + "," + rx2.getText().toString().trim() + "," + rx3.getText().toString().trim();
            wod_details = addnotesWod.getText().toString();
            if (wod_details.equalsIgnoreCase("")) {
                UtilityClass.showAlertDailog(context, "Please enter description.");
                return;
            }


            dialog.dismiss();
            getDataWOD();
        });


        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txt.setText(event);


        LinearLayout AddRxDoses = rootView.findViewById(R.id.AddRxDoses);

        AddRxDoses.setVisibility(VISIBLE);

        //  bubbleView.getLayoutParams().height = dpToPx(350);
        if (!UtilityClass.getDeviceTypeMobile) {

        }
        //mainRlyofAddset.setBackgroundColor(Color.parseColor("#545454"));

        TextView txtData = rootView.findViewById(R.id.eventData);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txtData.setText(eventData);
        txtData.setTextColor(Color.parseColor("#ffffff"));
        txtData.setVisibility(GONE);
        AddRxDoses.getLayoutParams().height = dpToPx(240);


        EditText addnotes = rootView.findViewById(R.id.addnotes);
        addnotes.setVisibility(GONE);

        addnotes.getLayoutParams().height = dpToPx(120);

        dialog.showAtLocation(rootView, Gravity.CENTER, 0, 0);


    }

    private void addSetAPI() {

        //webServices.CheckInterNetConnection(context,true);
        for (int i = 0; i < selectedExerciseList.size(); i++) {
            if (selectedExerciseList.get(i).getSets().size() > 0) {
                AllComplete = true;
//                if (selectedExerciseList.get(i).getGetDoses().size() > 0) {
//                    AllComplete = true;
//                } else {
//                    //AllComplete = false;
//                    UtilityClass.showAlertDailog(context, "Please complete step 4.");
//                    return;
//                }
            } else {
                AllComplete = false;
                UtilityClass.showAlertDailog(context, "Please complete step 3.");
                return;
            }
        }
        getData();
    }

    private void getDataWOD() {
        String exercise_id2 = "";
        String MeasurementId = "";
        String Doseid = "";
        StringBuilder sb = new StringBuilder();
        exercise_id2 = selectedExerciseList.get(addTrainingExerciseCOUNT).getExerciseId();


        whichapicAlled = "addTrainingExercise";

        String finalDoseid = Doseid;
        String finalExercise_id = exercise_id2;
        String finalMeasurementId = MeasurementId;
        int finalI = addTrainingExerciseCOUNT;


        WebServices webServices = new WebServices();
        webServices.addTrainingWODExercise(program_id, phase, week, day, pillar, type_id, finalExercise_id, "0,0,0",
                "0", type_group_id, "1", wod_dose_values, wod_details, context, exercise_Activity.this);

        if (type_group_id.equalsIgnoreCase("")) {
            exercise_type_group_id_Count = exercise_type_group_id_Count + 1;
        }

    }


    private void getData() {
        if (AllComplete) {
//            for (int i = exercise_type_group_id_Count; i < selectedExerciseList.size(); i++) {
            String exercise_id2 = "";
            String MeasurementId = "";
            String Doseid = "";
            StringBuilder sb = new StringBuilder();
            exercise_id2 = selectedExerciseList.get(addTrainingExerciseCOUNT).getExerciseId();
            for (int x = 0; x < selectedExerciseList.get(addTrainingExerciseCOUNT).getSets().size(); x++) {
                String id = selectedExerciseList.get(addTrainingExerciseCOUNT).getSets().get(x).getExerciseSetsId();
                MeasurementId = sb.append(id + ",").toString();
            }

            if (MeasurementId != null && MeasurementId.length() > 0 && MeasurementId.charAt(MeasurementId.length() - 1) == ',') {
                MeasurementId = MeasurementId.substring(0, MeasurementId.length() - 1);
            }

            try {
                Doseid = selectedExerciseList.get(addTrainingExerciseCOUNT).getGetDoses().get(0).getId();
            } catch (Exception v) {
                Doseid = "";
            }


            whichapicAlled = "addTrainingExercise";

            String finalDoseid = Doseid;
            String finalExercise_id = exercise_id2;
            String finalMeasurementId = MeasurementId;
            int finalI = addTrainingExerciseCOUNT;

            WebServices webServices = new WebServices();
            webServices.addTrainingExercise(program_id, phase, week, day, pillar, type_id, finalExercise_id, finalMeasurementId,
                    finalDoseid, type_group_id, context, exercise_Activity.this);
            Log.e(VolleyLog.TAG, "run: " + finalI);

            Log.e(VolleyLog.TAG, "addSetAPI: " + program_id + "  " + phase + "  " + week + "  " + day + "  " + pillar + "  " + type_id + "  " + finalExercise_id + "  " + finalMeasurementId + "  " +
                    finalDoseid + "  " + type_group_id + "  " + context);
            if (type_group_id.equalsIgnoreCase("")) {
                exercise_type_group_id_Count = exercise_type_group_id_Count + 1;
                //break;
            }

        }
        //}
        AllComplete = true;
    }

    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            Log.d("Result", result);
            parseFolderData(result);
        } else {
            hide();
        }
    }

    private void Step1and2() {
        try {
            while (step2ExerciseList.getItemDecorationCount() > 0) {
                step2ExerciseList.removeItemDecorationAt(0);
            }
        } catch (Exception v) {

        }

        SelectedPosition = 0;
        rLayoutMatric.setBackgroundColor(getResources().getColor(R.color.Maincolor));

        TextMatric.setTextColor(Color.parseColor("#000000"));
        TextMatric.setTypeface(CustomTypeface.load_AGENCYB_Fonts(getApplicationContext()));

        TextImperial.setTextColor(Color.parseColor("#edbb57"));
        TextImperial.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));


        lLayoutImperial.setBackground(ContextCompat.getDrawable(context, R.drawable.selectedradious));

        Exercise = true;
        imgSteps.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow));
        textSelectExerciseType.setText("Select Exercise Type (Step 1)");
        textSelectExerciseType2.setText("Select Exercise (Step 2)");
        selectedExerciseListRecycler.setVisibility(GONE);

        try {
            step2ExerciseList.setAdapter(step2Adapter);
            sectionItemDecoration =
                    new RecyclerSectionItemDecoration(100,
                            true,
                            getSectionCallback(exerciseNameListSave), step2ExerciseList, context);
            //step2ExerciseList.addItemDecoration(sectionItemDecoration);
        } catch (Exception x) {

        }
        step2ExerciseList.invalidateItemDecorations();
        try {
            ExerciseView.setAdapter(null);
            ExerciseView.setAdapter(myExerciseNameViewAdapter);
        } catch (Exception x) {

        }
        if (WOD.equalsIgnoreCase("0")) {
            imgSteps.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow));
        } else {
            imgSteps.setImageDrawable(getResources().getDrawable(R.drawable.save_training));
        }
    }

    private void Step3and4() {
        if (selectedExerciseList.size() > 0) {

            try {
                // step2ExerciseList.invalidate();

                sectionItemDecoration =
                        new RecyclerSectionItemDecoration(3,
                                true,
                                getSectionCallback(exerciseNameListSave), step2ExerciseList, context);
                step2ExerciseList.removeItemDecoration(sectionItemDecoration);
                step2ExerciseList.removeItemDecorationAt(0);
//                while (step2ExerciseList.getItemDecorationCount() > 0) {
//
//                }
            } catch (Exception v) {

            }
            step2ExerciseList.invalidateItemDecorations();
            selectedExerciseListRecycler.setVisibility(VISIBLE);


            //step2ExerciseList.addItemDecoration(sectionItemDecoration);
            lLayoutImperial.setBackgroundColor(getResources().getColor(R.color.Maincolor));
            //TextImperial.setTextColor(Color.parseColor("#ffffff"));
            rLayoutMatric.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            //TextMatric.setTextColor(Color.parseColor("#edbb57"));


            TextMatric.setTextColor(Color.parseColor("#edbb57"));
            TextMatric.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));

            TextImperial.setTextColor(Color.parseColor("#000000"));
            TextImperial.setTypeface(CustomTypeface.load_AGENCYB_Fonts(getApplicationContext()));


            Exercise = false;


            imgSteps.setImageDrawable(getResources().getDrawable(R.drawable.save_training));
            textSelectExerciseType.setText("Select Measurement - Select Multiple (Step 3)");
            textSelectExerciseType2.setText("Select Dosage (Step 4)");
            ExerciseView.setAdapter(null);
            ExerciseView.setAdapter(step3adapter);

            selectedExerciseListRecycler.setAdapter(new StepSelectedExerciseAdapter(context));
            recyclerViewDoseData = new step5Adapter(context, getAbrNames);


            step2ExerciseList.setAdapter(recyclerViewDoseData);

        }
    }

    private void EnableWod() {
        if (WOD.equalsIgnoreCase("1")) {
            WOD = "0";
            imgSteps.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow));
        } else {
            WOD = "1";
            imgSteps.setImageDrawable(getResources().getDrawable(R.drawable.save_training));
        }
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<ExerciseName> people) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || people.get(position)
                        .getPerantTypeName()
                        != people.get(position - 1)
                        .getPerantTypeName();
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                CharSequence m = "";
                try {
                    m = people.get(position)
                            .getPerantTypeName();
                } catch (Exception v) {
                    m = "";
                }
                return m;
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onPause() {
        super.onPause();
        //..stopPosition = videoViewPlayer.getCurrentPosition(); //stopPosition is an int
        videoViewPlayer.pause();
    }

    @Override
    public void onBackPressed() {
        if (videoView.getVisibility() == VISIBLE) {
            videoViewPlayer.stopPlayback();
            videoViewPlayer.setMediaController(null);
            videoView.setVisibility(GONE);
            floatingbutton.setVisibility(VISIBLE);
        } else {

        }
        selectedExerciseList.clear();

        exerciseTypeinExercise.clear();

        exerciseTypeinExerciseForSend.clear();
        getAbrNamesForsend.clear();

        try {
            selectedExerciseListRecycler.setAdapter(null);

        } catch (Exception x) {

        }
        try {
            ExerciseView.setAdapter(null);

        } catch (Exception x) {

        }
        try {
            step2ExerciseList.setAdapter(null);

        } catch (Exception x) {

        }
        try {
            abrRecyclerView.setAdapter(null);

        } catch (Exception x) {

        }


        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        finish();

    }

    public void hideKeyBoard() {
        View view1 = this.getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }

    private void playvideo(String videopath) {
        Log.e(VolleyLog.TAG, "playvideo: " + urlEncoded(videopath));

        if (videopath.equalsIgnoreCase("")) {
            UtilityClass.showAlertDailog(context, "Unable to play video");

            floatingbutton.setVisibility(VISIBLE);
            //onBackPressed();
            return;
        }
        Intent mIntent =
                ExoPlayerActivity.getStartIntent(this, urlEncoded(videopath));
        startActivity(mIntent);
//        if (mediaController == null) {
//            mediaController = new MediaController(context);
//
//            // Set the videoView that acts as the anchor for the MediaController.
//            mediaController.setAnchorView(videoViewPlayer);
//            // Set MediaController for VideoView
//            videoViewPlayer.setMediaController(mediaController);
//        }
//
//        try {
//            progressDialog = ProgressDialog.show(context, "",
//                    "Buffering video...", false);
//            progressDialog.setCancelable(true);
//            getWindow().setFormat(PixelFormat.TRANSLUCENT);
//
//            // mediaController = new MediaController(context);
//
//            Uri video = Uri.parse(urlEncoded(videopath));
//            videoViewPlayer.setMediaController(mediaController);
//            videoViewPlayer.setVideoURI(video);
//            videoViewPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                public void onPrepared(MediaPlayer mp) {
//                    mp.setLooping(true);
//                    progressDialog.dismiss();
//                    videoViewPlayer.start();
//                }
//            });
//
//
//        } catch (Exception e) {
//            progressDialog.dismiss();
//            System.out.println("Video Play Error :" + e.getMessage());
//        }
    }

    private void parseFolderData(String result) {
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);

            //Minu Mami k husaband aaeege

            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj.getString("responseMessage");
            Gson gson = new Gson();
            if (WebServices.responseCode == 200) {
                if (whichapicAlled.equalsIgnoreCase("addTrainingExercise")) {

                    CoachAddExerciseScreen.RefreshTrainingPhase = true;

                    JSONArray jsonArray = jsonObj.getJSONArray("data");
                    //jsonArray.getJSONObject()
                    if (type_group_id.equalsIgnoreCase("")) {
                        List<ResponseOfAddExercise> responseOfAddExercises = new ArrayList<>();
                        responseOfAddExercises = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), ResponseOfAddExercise[].class)));
                        type_group_id = responseOfAddExercises.get(0).getExerciseTypeGroupId();
                        // addSetAPI();
                    }

                    addTrainingExerciseCOUNT = ++addTrainingExerciseCOUNT;
                    if (addTrainingExerciseCOUNT == selectedExerciseList.size()) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                CoachAddExerciseScreen.RefreshTrainingPhase = true;
                                hide();
                                Log.e(VolleyLog.TAG, "parseFolderData: " + addTrainingExerciseCOUNT + "   " + (selectedExerciseList.size() - 1));
                                onBackPressed();
                            }
                        }, 3000);
                        return;
                    } else {
                        if (WOD.equalsIgnoreCase("0")) {
                            getData();
                        } else {
                            getDataWOD();
                        }
                    }
                } else if (whichapicAlled.equalsIgnoreCase("getExerciseName")) {
                    JSONArray jsonArray = jsonObj.getJSONArray("data");

                    exerciseTypeNameList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), ExerciseTypeName[].class)));
                    ExerciseView.setAdapter(myExerciseNameViewAdapter);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            whichapicAlled = "GetExerciseNewSecond";
                            hide();
                            webServices.GetExerciseNewSecond(context, exercise_Activity.this);
                        }
                    }, 100);
                } else if (whichapicAlled.equalsIgnoreCase("GetExerciseNewSecond")) {
                    JSONArray jsonArray = jsonObj.getJSONArray("data");


                    exerciseNameListX = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), ExerciseName[].class)));
                    Boolean tootlge = false;
                    exerciseNameListSave = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), ExerciseName[].class)));
//                    try {
//                        List<Exercise> exercise = new ArrayList<Exercise>();
//                        for (int i = 0; i < exerciseNameListX.size(); i++) {
//
//                            for (int x = 0; x < exerciseNameListX.get(i).getExercise().size(); x++) {
//                                String exerciseId = exerciseNameListX.get(i).getExercise().get(x).getExerciseId();
//                                String exerciseName = exerciseNameListX.get(i).getExercise().get(x).getExerciseName();
//                                String exerciseType = exerciseNameListX.get(i).getExercise().get(x).getExerciseType();
//                                String gymAccountId = exerciseNameListX.get(i).getExercise().get(x).getGymAccountId();
//                                String lastModifyTime = exerciseNameListX.get(i).getExercise().get(x).getLastModifyTime();
//                                String exerciseTypeId = exerciseNameListX.get(i).getExercise().get(x).getExerciseTypeId();
//                                String exerciseVideoLink = exerciseNameListX.get(i).getExercise().get(x).getExerciseVideoLink();
//                                String id2 = exerciseNameListX.get(i).getExercise().get(x).getId();
//                                String perantTypeName2 = exerciseNameListX.get(i).getExercise().get(x).getPerantTypeName();
//                                String baseValue2 = exerciseNameListX.get(i).getExercise().get(x).getBaseValue();
//
//                                List<com.org.godspeed.response_JsonS.ExerciseNameList.Set> sets = exerciseNameListX.get(i).getExercise().get(x).getSets();
//
//                                for (int r = 0; r < sets.size(); r++) {
//                                    for (int j = r + 1; j < sets.size(); j++) {
//                                        if (sets.get(r).getSetName().equalsIgnoreCase(sets.get(j).getSetName())) {
//                                            sets.remove(j);
//                                            j--;
//                                        }
//                                    }
//                                }
//
//                                exercise.add(new Exercise(exerciseId, exerciseName, exerciseType, gymAccountId, lastModifyTime,
//                                        exerciseTypeId, exerciseVideoLink, id2, perantTypeName2, baseValue2, false,
//                                        sets));
//                                tootlge = true;
//                            }
//                            String id = exerciseNameListX.get(i).getId();
//                            String perantTypeName = exerciseNameListX.get(i).getPerantTypeName();
//                            String baseValue = exerciseNameListX.get(i).getBaseValue();
//                            exerciseNameListSave.add(new ExerciseName(id, perantTypeName, baseValue, exercise));
//
//                            tootlge = false;
//                            exercise = new ArrayList<Exercise>();
//                        }
//
//                    } catch (Exception x) {
//                        Log.e(VolleyLog.TAG, "FolderData: "+x);
//                    }

                    // exerciseNameListX.clear();
                    //exerciseNameListSave = (ArrayList<ExerciseName>) exerciseNameList.clone();

                    for (int i = 0; i < exerciseNameListX.size(); i++) {

                        for (int x = 0; x < exerciseNameListX.get(i).getExercise().size(); x++) {
                            if (exerciseNameListX.get(i).getExercise().get(x).getSets() == null) {
                                Log.e(VolleyLog.TAG, "parseFolderData: " + exerciseNameListX.get(i).getExercise().get(x).getExerciseName());
                            }
                        }

                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            step2Adapter = new Step2Adapter(context);
                            step2ExerciseList.setAdapter(step2Adapter);
                            sectionItemDecoration =
                                    new RecyclerSectionItemDecoration(100,
                                            true,
                                            getSectionCallback(exerciseNameListSave), step2ExerciseList, context);
                            //UtilityClass.showWaitDialog(new Dialog(context),context);
                            hide();
                            whichapicAlled = "SelectMeasurement";
                            webServices.SelectMeasurement(context, exercise_Activity.this);

                            step2ExerciseList.setAdapter(step2Adapter);
                            sectionItemDecoration =
                                    new RecyclerSectionItemDecoration(100,
                                            true,
                                            getSectionCallback(exerciseNameListSave), step2ExerciseList, context);
                        }
                    }, 10);
                    saveArrayList();

                } else if (whichapicAlled.equalsIgnoreCase("SelectedABR")) {
                    String usersData = jsonObj
                            .getString("data");

                    JSONObject userJson = new JSONObject(usersData);

                    JSONObject jsonObjX = new JSONObject(userJson.getString("week"));
                    JSONArray js = new JSONArray();


                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("1", jsonObjX.getJSONArray("1"));
                    js.put(0, jsonObject);

                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("2", jsonObjX.getJSONArray("2"));
                    js.put(1, jsonObject2);

                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("3", jsonObjX.getJSONArray("3"));
                    js.put(2, jsonObject3);

                    JSONObject jsonObject4 = new JSONObject();
                    jsonObject4.put("4", jsonObjX.getJSONArray("4"));
                    js.put(3, jsonObject4);

                    abrs = Arrays.asList(gson.fromJson(js.toString(), ABR[].class));
                    //recyclerViewDoseData.notifyDataSetChanged();

                    recyclerViewDoseDetail = new DoseDetail(context, abrs, true);

                    recyclerViewDoseData.notifyDataSetChanged();
                    // recyclerViewDoseDetail.notifyDataSetChanged();

                    hide();
                    ////UtilityClass.hide();
                } else if (whichapicAlled.equalsIgnoreCase("SelectMeasurement")) {
                    JSONArray usersData = jsonObj.getJSONArray("data");
                    for (int i = 0; i < usersData.length(); i++) {
                        usersData.getJSONObject(i).put("Selectedset", false);
                    }
                    exerciseTypeinExerciseList = new ArrayList<>(Arrays.asList(gson.fromJson(usersData.toString(), ExerciseTypeinExercise[].class)));
                    // UtilityClass.hide();

                    hide();
                    if (getAbrNames == null || getAbrNames.size() == 0) {
                        whichapicAlled = "getAbrName";
                        hide();
                        webServices.getPrescriptionAbr(context, exercise_Activity.this);
                    }
                } else if (whichapicAlled.equalsIgnoreCase("getAbrName")) {
                    JSONArray jsonArray = jsonObj.getJSONArray("data");
                    getAbrNames = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), GetAbrName[].class)));
                    recyclerViewDoseData = new step5Adapter(context, getAbrNames);
                    //step2ExerciseList.setAdapter(recyclerViewDoseData);
                    recyclerViewDoseData.notifyDataSetChanged();
                    hide();
                }
            } else {
                UtilityClass.showAlertDailog(context, responseMessage);
                hide();
            }
        } catch (Exception e) {
            Log.e(VolleyLog.TAG, "ERROR: " + e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            selectedExerciseList = new ArrayList<>();

            exerciseTypeinExercise = new ArrayList<>();

            exerciseTypeinExerciseForSend = new ArrayList<>();

            getAbrNamesForsend = new ArrayList<>();

        } catch (Exception v) {

        }

    }

    private void filterDoses() {
        bISSetExist = false;
        bISRepExist = false;
        bISWeightExist = false;
        bISHeightDistanceExist = false;
        bISTimeExist = false;
        for (int ixy = 0; ixy < selectedExerciseList.get(SelectedPosition).getSets().size(); ixy++) {
            if (selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getExerciseSetsId().equalsIgnoreCase("5")) {
                bISSetExist = true;
            } else if (selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getExerciseSetsId().equalsIgnoreCase("18")) {
                bISRepExist = true;
            } else if (selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getExerciseSetsId().equalsIgnoreCase("6")) {
                bISWeightExist = true;
            } else if (selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getExerciseSetsId().equalsIgnoreCase("11") || selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getExerciseSetsId().equalsIgnoreCase("17")) {
                bISHeightDistanceExist = true;
            } else if (selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getExerciseSetsId().equalsIgnoreCase("19") || selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getExerciseSetsId().equalsIgnoreCase("14")) {
                bISTimeExist = true;
            }
        }
        filterListbyId = new ArrayList<>();

        if (selectedExerciseList.get(SelectedPosition).getSets().size() > 5) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerViewDoseData = new step5Adapter(context, getAbrNames);
                    step2ExerciseList.setAdapter(recyclerViewDoseData);
                }
            }, 30);

        } else {
            if (bISTimeExist) {
                filterListbyId = getAbrNames.stream().filter(article -> Integer.parseInt(article.getId()) >= 35 && Integer.parseInt(article.getId()) <= 42).collect(Collectors.toList());
            } else if (bISHeightDistanceExist) {
                filterListbyId = getAbrNames.stream().filter(article -> Integer.parseInt(article.getId()) >= 43 && Integer.parseInt(article.getId()) <= 89).collect(Collectors.toList());
            } else if (bISSetExist && bISRepExist && bISWeightExist) {
                filterListbyId = getAbrNames.stream().filter(article -> Integer.parseInt(article.getId()) >= 1 && Integer.parseInt(article.getId()) <= 34 ||
                        Integer.parseInt(article.getId()) >= 90 && Integer.parseInt(article.getId()) <= 94).collect(Collectors.toList());
            } else {
                filterListbyId = getAbrNames;
                ///Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerViewDoseData = new step5Adapter(context, filterListbyId);
                    step2ExerciseList.setAdapter(recyclerViewDoseData);
                }
            }, 5);
        }
        //recyclerViewDoseData.filter("");
        //recyclerViewDoseData.notifyDataSetChanged();
    }

    private void saveArrayList() {
        String json = new Gson().toJson(exerciseNameListSave);
        UtilityClass.SetPreferences(context, "ExerciseList", json);
        Log.e(VolleyLog.TAG, "saveArrayList: " + json);
    }

    private void getArrayList() {

        try {
            Gson gson = new Gson();

            String json = UtilityClass.getPreferences(context, "ExerciseList");
            exerciseNameListSave = new ArrayList<>(Arrays.asList(gson.fromJson(json, ExerciseName[].class)));
        } catch (Exception v) {

        }

        if (null != exerciseTypeNameList && exerciseNameListSave != null) {
            step2Adapter = new Step2Adapter(context);
            step2ExerciseList.setAdapter(null);
            step2ExerciseList.setAdapter(step2Adapter);
            //UtilityClass.showWaitDialog(new Dialog(context),context);
            whichapicAlled = "SelectMeasurement";
            hide();
            webServices.SelectMeasurement(context, exercise_Activity.this);
        } else {
            whichapicAlled = "getExerciseName";
            hide();
            webServices.getExerciseName(context, exercise_Activity.this);
        }
    }

    private class step1Adapter extends RecyclerView.Adapter<Step1and3ViewHolder> {
        step1Adapter(Context context) {
        }

        @Override
        @NonNull
        public Step1and3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.add_excersise_type_layout, parent, false);
            return new Step1and3ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Step1and3ViewHolder holder, int position) {
            holder.exercisetype_text.setText(exerciseTypeNameList.get(position).getTypeName());
            if (exerciseTypeNameList.get(position).getExerciseTypeId().equalsIgnoreCase(SelectedExerciseTypeName)) {
                type_id = exerciseTypeNameList.get(position).getExerciseTypeId();
                holder.exercisetype_text.setTextColor(getResources().getColor(R.color.color_black_for_health_profile_button_selected));
                holder.lLayoutforexercisetype.setBackground(getResources().getDrawable(R.drawable.round_bg_yellow));
                SelectedTypeName = exerciseTypeNameList.get(position).getTypeName();
            } else {
                holder.exercisetype_text.setTextColor(getResources().getColor(R.color.color_white));
                holder.lLayoutforexercisetype.setBackground(getResources().getDrawable(R.drawable.round_border_gray));
            }
            if (WOD.equalsIgnoreCase("1") && exerciseTypeNameList.get(position).getExerciseTypeId().equalsIgnoreCase("12")) {
                holder.exercisetype_text.setTextColor(getResources().getColor(R.color.color_black_for_health_profile_button_selected));
                holder.lLayoutforexercisetype.setBackground(getResources().getDrawable(R.drawable.round_bg_yellow));
            }

            holder.lLayoutforexercisetype.setOnClickListener(view -> {
                if (!changeType) {
                    if (exerciseTypeNameList.get(position).getExerciseTypeId().equalsIgnoreCase("12")) {
                        EnableWod();
                    } else {
                        SelectedTypeName = exerciseTypeNameList.get(position).getTypeName();
                        type_id = exerciseTypeNameList.get(position).getExerciseTypeId();
                        SelectedExerciseTypeName = exerciseTypeNameList.get(position).getExerciseTypeId();
                    }
                    notifyDataSetChanged();
                    if (SelectedExerciseTypeName.equalsIgnoreCase("1")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                selectedExerciseList = new ArrayList<>();
                                step2Adapter.notifyDataSetChanged();
                            }
                        }, 80);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = exerciseTypeNameList.size();
            } catch (Exception x) {

            }
            return count;
        }
    }

    private class Step2Adapter extends RecyclerView.Adapter<ViewHolder> {
        ListofNames listofNames;


        LinearLayoutManager mLayoutManager;
        private List<ExerciseName> ForsearchArray; // Values to be displayed

        // data is passed into the constructor
        Step2Adapter(Context context) {
            this.ForsearchArray = new ArrayList<>(exerciseNameListSave);
        }

        // inflates the cell layout from xml when needed
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.stet_2_exercise_layout, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each cell
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            //holder.RecyclernameOfexercise.setLayoutManager();
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            mLayoutManager.setAutoMeasureEnabled(false);

            holder.RecyclernameOfexercise.setLayoutManager(mLayoutManager);


            holder.RecyclernameOfexercise.setAdapter(null);

            holder.RecyclernameOfexercise.setItemViewCacheSize(10);
            holder.RecyclernameOfexercise.setDrawingCacheEnabled(false);
            holder.RecyclernameOfexercise.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            //listofNames =;
            holder.RecyclernameOfexercise.setAdapter(new ListofNames(context, ForsearchArray.get(position).getExercise()));

            //holder.Name.setText(ForsearchArray.get(position).getPerantTypeName());
            holder.Name.setTag(ForsearchArray.get(position).getId());

            holder.mainRlyforExercise.setVisibility(GONE);
            //sectionItemDecoration.Setclick(holder.RecyclernameOfexercise);
//            holder.mainRlyforExercise.setOnClickListener(view -> {
//                if (holder.RecyclernameOfexercise.getVisibility() == VISIBLE) {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            holder.RecyclernameOfexercise.setVisibility(GONE);
//                            holder.showArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
//                        }
//                    }, 500);
//
//                } else {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            holder.RecyclernameOfexercise.setVisibility(VISIBLE);
//                            holder.showArrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
//                        }
//                    }, 500);
//
//                }
//            });


        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = ForsearchArray.size();
            } catch (Exception x) {
            }
            return count;
        }

//        public  void mm(ViewHolder holder){
//            holder.RecyclernameOfexercise.removeAllViews();
//        }

        private void filter(String s) {
            String text = s.toLowerCase();
            int countx = 0;
            Boolean tootlge = false;
            text = text.toLowerCase().trim();
            if (text.length() == 0) {
                ForsearchArray = new ArrayList<>(exerciseNameListSave);
            } else {
                ForsearchArray = new ArrayList<>();
                List<Exercise> exercise = new ArrayList<Exercise>();
                for (int i = 0; i < exerciseNameListSave.size(); i++) {
                    for (int x = 0; x < exerciseNameListSave.get(i).getExercise().size(); x++) {
                        if (exerciseNameListSave.get(i).getExercise().get(x).getExerciseName().toLowerCase().contains(text)) {
                            String exerciseId = exerciseNameListSave.get(i).getExercise().get(x).getExerciseId();
                            String exerciseName = exerciseNameListSave.get(i).getExercise().get(x).getExerciseName();
                            String exerciseType = exerciseNameListSave.get(i).getExercise().get(x).getExerciseType();
                            String gymAccountId = exerciseNameListSave.get(i).getExercise().get(x).getGymAccountId();
                            String lastModifyTime = exerciseNameListSave.get(i).getExercise().get(x).getLastModifyTime();
                            String exerciseTypeId = exerciseNameListSave.get(i).getExercise().get(x).getExerciseTypeId();
                            String exerciseVideoLink = exerciseNameListSave.get(i).getExercise().get(x).getExerciseVideoLink();
                            String id2 = exerciseNameListSave.get(i).getExercise().get(x).getId();
                            String perantTypeName2 = exerciseNameListSave.get(i).getExercise().get(x).getPerantTypeName();
                            String baseValue2 = exerciseNameListSave.get(i).getExercise().get(x).getBaseValue();
                            Boolean getSelectedExercise = exerciseNameListSave.get(i).getExercise().get(x).getSelectedExercise();
                            exercise.add(new Exercise(exerciseId, exerciseName, exerciseType, gymAccountId, lastModifyTime,
                                    exerciseTypeId, exerciseVideoLink, id2, perantTypeName2, baseValue2, getSelectedExercise,
                                    exerciseNameListSave.get(i).getExercise().get(x).getSets()));
                            tootlge = true;
                        }
                    }
                    if (tootlge == true) {
                        String id = exerciseNameListSave.get(i).getId();
                        String perantTypeName = exerciseNameListSave.get(i).getPerantTypeName();
                        String baseValue = exerciseNameListSave.get(i).getBaseValue();
                        ForsearchArray.add(new ExerciseName(id, perantTypeName, baseValue, exercise));
                        tootlge = false;
                        exercise = new ArrayList<Exercise>();
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    private class ListofNames extends RecyclerView.Adapter<ListofNamesViewHolder> {
        // data is passed into the constructor
        int PositionOfNames;
        List<Exercise> exerciseforSearch = new ArrayList<>();

        int positionforrefresh;

        ListofNames(Context context, List<com.org.godspeed.response_JsonS.ExerciseNameList.Exercise> PositionOfNames) {
            this.exerciseforSearch = PositionOfNames;
        }


        @Override
        @NonNull
        public ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.abr_name, parent, false);
            return new ListofNamesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListofNamesViewHolder holder, int position) {
            exerciseforSearch.get(position).setSelectedExercise(false);
            holder.DoseName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));

            holder.DoseName.setTextColor(getResources().getColor(R.color.textColorWhite));

            holder.DoseName.setText(exerciseforSearch.get(position).getExerciseName());
            holder.DoseName.setSelected(true);
            holder.playVideo.setOnClickListener(view -> {
                //videoView.setVisibility(VISIBLE);
                //floatingbutton.setVisibility(GONE);
                playvideo(exerciseforSearch.get(position).getExerciseVideoLink());
            });

            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));

            for (int x = 0; x < selectedExerciseList.size(); x++) {
                if (selectedExerciseList.get(x).getExerciseId().toLowerCase().equalsIgnoreCase(exerciseforSearch.get(position).getExerciseId().toLowerCase())) {
                    exerciseforSearch.get(position).setSelectedExercise(true);
                    holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                }
            }

            if (exerciseforSearch.get(position).getSelectedExercise()) {
                holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
            } else {
                holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
            }


            holder.llayout.setOnClickListener(view -> {
                if (!SelectedExerciseTypeName.equalsIgnoreCase("")) {
                    List<Set> setList = new ArrayList<>();
                    try {
                        setList = new ArrayList<>(exerciseforSearch.get(position).getSets());
                        for (int r = 0; r < setList.size(); r++) {
                            for (int j = r + 1; j < setList.size(); j++) {
                                if (setList.get(r).getSetName().equalsIgnoreCase(setList.get(j).getSetName())) {
                                    setList.remove(j);
                                    j--;
                                }
                            }
                        }
                    } catch (Exception m) {
                    }

                    if (SelectedExerciseTypeName.trim().equalsIgnoreCase("1")) {
                        selectedExerciseList = new ArrayList<>();

                        List<GetAbrName> getAbrNames1X = new ArrayList<GetAbrName>();


                        selectedExerciseList.add(0, new SelectedExercise(
                                exerciseforSearch.get(position).getExerciseId(),
                                exerciseforSearch.get(position).getExerciseName(),
                                exerciseforSearch.get(position).getExerciseType(),
                                exerciseforSearch.get(position).getGymAccountId(),
                                exerciseforSearch.get(position).getLastModifyTime(),
                                exerciseforSearch.get(position).getExerciseTypeId(),
                                exerciseforSearch.get(position).getExerciseVideoLink(),
                                exerciseforSearch.get(position).getId(),
                                exerciseforSearch.get(position).getPerantTypeName(),
                                exerciseforSearch.get(position).getBaseValue(), false,
                                setList,
                                new ArrayList<GetAbrName>()));
                        exerciseforSearch.get(position).setSelectedExercise(true);

                        if (selectedExerciseList.get(0).getExerciseId().equalsIgnoreCase(exerciseforSearch.get(position).getExerciseId())) {
                            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                        } else {
                            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    step2ExerciseList.removeAllViews();
                                    notifyDataSetChanged();
                                } catch (Exception x) {
                                }
                                positionforrefresh = position;
                            }
                        }, 100);
                    } else {
                        if (exerciseforSearch.get(position).getSelectedExercise()) {
                            for (int x = 0; x < selectedExerciseList.size(); x++) {
                                if (selectedExerciseList.get(x).getExerciseName().equalsIgnoreCase(exerciseforSearch.get(position).getExerciseName())) {
                                    selectedExerciseList.remove(x);
                                    exerciseforSearch.get(position).setSelectedExercise(false);
                                    holder.unSelectTick.setImageBitmap(unselected_tick_icon);
                                }
                            }
                        } else {
                            List<GetAbrName> getAbrNames1X = new ArrayList<GetAbrName>();
                            selectedExerciseList.add(new SelectedExercise(
                                    exerciseforSearch.get(position).getExerciseId(),
                                    exerciseforSearch.get(position).getExerciseName(),
                                    exerciseforSearch.get(position).getExerciseType(),
                                    exerciseforSearch.get(position).getGymAccountId(),
                                    exerciseforSearch.get(position).getLastModifyTime(),
                                    exerciseforSearch.get(position).getExerciseTypeId(),
                                    exerciseforSearch.get(position).getExerciseVideoLink(),
                                    exerciseforSearch.get(position).getId(),
                                    exerciseforSearch.get(position).getPerantTypeName(),
                                    exerciseforSearch.get(position).getBaseValue(), false,
                                    setList,
                                    getAbrNames1X));
                            exerciseforSearch.get(position).setSelectedExercise(true);
                            holder.unSelectTick.setImageBitmap(selected_tick_icon);

                        }
                        //notifyDataSetChanged();
                        //notifyItemRemoved(position);
                        //notifyItemChanged(position);
                        //notifyItemInserted(position);
                    }
                }

            });

        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = exerciseforSearch.size();
            } catch (Exception x) {

            }
            return count;
        }

        @Override
        public long getItemId(int position) {
            return exerciseforSearch.get(position).hashCode(); //id()
        }
    }

    private class Step1and3ViewHolder extends RecyclerView.ViewHolder {
        TextView exercisetype_text;
        LinearLayout lLayoutforexercisetype;

        Step1and3ViewHolder(View itemView) {
            super(itemView);
            lLayoutforexercisetype = itemView.findViewById(R.id.lLayoutforexercisetype);
            exercisetype_text = itemView.findViewById(R.id.exercisetype_text);
        }
    }

    private class Step3Adapter extends RecyclerView.Adapter<Step1and3ViewHolder> {
        int Selected = 0;

        Step3Adapter(Context context) {
        }

        @Override
        public Step1and3ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.add_excersise_type_layout, parent, false);
            return new Step1and3ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Step1and3ViewHolder holder, int position) {

            if (position == 0) {
                filterDoses();
            }
            holder.exercisetype_text.setTextColor(getResources().getColor(R.color.color_white));
            holder.lLayoutforexercisetype.setBackground(getResources().getDrawable(R.drawable.round_border_gray));

            holder.exercisetype_text.setText(exerciseTypeinExerciseList.get(position).getSetName());
            //Toast.makeText(context, SelectedPosition + "", Toast.LENGTH_SHORT).show();

            try {
                for (int ixy = 0; ixy < selectedExerciseList.get(SelectedPosition).getSets().size(); ixy++) {
                    if (selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getSetName().toLowerCase().equalsIgnoreCase(exerciseTypeinExerciseList.get(position).getSetName().toLowerCase())) {
                        holder.exercisetype_text.setTextColor(getResources().getColor(R.color.color_black_for_health_profile_button_selected));
                        holder.lLayoutforexercisetype.setBackground(getResources().getDrawable(R.drawable.round_bg_yellow));
                        exerciseTypeinExerciseList.get(position).setSelectedset(true);
                    }
                }
            } catch (Exception b) {

            }


            holder.lLayoutforexercisetype.setOnClickListener(view -> {
                if (exerciseTypeinExerciseList.get(position).getSelectedset()) {
                    exerciseTypeinExerciseList.get(position).setSelectedset(false);
                    for (int ixy = 0; ixy < selectedExerciseList.get(SelectedPosition).getSets().size(); ixy++) {
                        if (selectedExerciseList.get(SelectedPosition).getSets().get(ixy).getExerciseSetsId().toLowerCase().equalsIgnoreCase(exerciseTypeinExerciseList.get(position).getExerciseSetsId().toLowerCase())) {
                            selectedExerciseList.get(SelectedPosition).getSets().remove(ixy);
                        }
                    }
                } else {
                    if (selectedExerciseList.get(SelectedPosition).getSets().size() < 5) {
                        exerciseTypeinExerciseList.get(position).setSelectedset(true);
                        selectedExerciseList.get(SelectedPosition).getSets().add(new Set(
                                exerciseTypeinExerciseList.get(position).getExerciseSetsId(),
                                exerciseTypeinExerciseList.get(position).getExerciseId(),
                                exerciseTypeinExerciseList.get(position).getSetName(),
                                exerciseTypeinExerciseList.get(position).getTempo(),
                                exerciseTypeinExerciseList.get(position).getWeight(),
                                exerciseTypeinExerciseList.get(position).getReps(),
                                exerciseTypeinExerciseList.get(position).getGymAccountId(),
                                exerciseTypeinExerciseList.get(position).getLastModifyTime()));
                    }
                }
                notifyDataSetChanged();
            });


        }


        // total number of cells
        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = exerciseTypeinExerciseList.size();
            } catch (Exception x) {

            }
            return count;
        }


    }

    private class StepSelectedExerciseAdapter extends RecyclerView.Adapter<Step1and3ViewHolder> {

        StepSelectedExerciseAdapter(Context context) {

        }

        @Override
        @NonNull
        public Step1and3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.add_excersise_type_layout, parent, false);
            return new Step1and3ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Step1and3ViewHolder holder, int position) {
            holder.exercisetype_text.setText(selectedExerciseList.get(position).getExerciseName());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT); // or set height to any fixed value you want
            lp.setMargins(10, 5, 10, 5);
            holder.lLayoutforexercisetype.setLayoutParams(lp);
            holder.exercisetype_text.setPadding(30, 13, 30, 13);

            if (SelectedPosition == position) {
                holder.exercisetype_text.setTextColor(getResources().getColor(R.color.color_black_for_health_profile_button_selected));
                holder.lLayoutforexercisetype.setBackground(getResources().getDrawable(R.drawable.round_bg_yellow));
                selectedExerciseList.get(position).setSelectedExercise(true);
            } else {
                selectedExerciseList.get(position).setSelectedExercise(false);
                holder.exercisetype_text.setTextColor(getResources().getColor(R.color.color_white));
                holder.lLayoutforexercisetype.setBackground(getResources().getDrawable(R.drawable.round_border_gray));
            }

            holder.lLayoutforexercisetype.setOnClickListener(view -> {
                StepSelectedExercise = selectedExerciseList.get(position).getExerciseName();
                selectedExerciseList.get(position).setSelectedExercise(true);
                SelectedPosition = position;

                //step3adapter = new Step3Adapter(context);

                step3adapter = new Step3Adapter(context);
                ExerciseView.setAdapter(null);
                ExerciseView.setAdapter(step3adapter);

                try {
                    step2ExerciseList.invalidate();
                } catch (Exception v) {

                }
//                recyclerViewDoseData = new step5Adapter(context, getAbrNames);
//                step2ExerciseList.setAdapter(recyclerViewDoseData);
                //.notifyDataSetChanged();
                notifyDataSetChanged();

            });
        }

        // total number of cells
        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = selectedExerciseList.size();
            } catch (Exception x) {

            }
            return count;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        LinearLayout lLayoutforexercisetype;
        ImageView showArrow;
        RelativeLayout mainRlyforExercise;
        RecyclerView RecyclernameOfexercise;

        ViewHolder(View itemView) {
            super(itemView);
            lLayoutforexercisetype = itemView.findViewById(R.id.lLayoutforexercisetype);
            Name = itemView.findViewById(R.id.Name);
            showArrow = itemView.findViewById(R.id.showArrow);
            mainRlyforExercise = itemView.findViewById(R.id.mainRlyforExercise);
            RecyclernameOfexercise = itemView.findViewById(R.id.RecyclernameOfexercise);
        }
    }

    private class ListofNamesViewHolder extends RecyclerView.ViewHolder {
        TextView DoseName, DosePhaseType;
        RecyclerView abrDetailRecyclerView;
        RelativeLayout ExcersiseSubmitButtonX, RSelectTickX, rlayoutDose;
        LinearLayout rLayoutforLBandREPS, RlLayout;
        RelativeLayout llayout, UnderLineFOrAbr;
        ImageView arrow, unSelectTick;
        ImageView showDetail, HideDetail, playVideo;

        public ListofNamesViewHolder(@NonNull View itemView) {
            super(itemView);
            DoseName = itemView.findViewById(R.id.DoseName);
            DosePhaseType = itemView.findViewById(R.id.DosePhaseType);
            RlLayout = itemView.findViewById(R.id.RlLayout);
            playVideo = itemView.findViewById(R.id.playVideo);
            unSelectTick = itemView.findViewById(R.id.unSelectTick);
            ExcersiseSubmitButtonX = itemView.findViewById(R.id.ExcersiseSubmitButtonX);
            rLayoutforLBandREPS = itemView.findViewById(R.id.rLayoutforLBandREPS);
            llayout = itemView.findViewById(R.id.llayout);
            arrow = itemView.findViewById(R.id.arrow);
            abrDetailRecyclerView = itemView.findViewById(R.id.abrDetailRecyclerView);
            UnderLineFOrAbr = itemView.findViewById(R.id.UnderLineFOrAbr);
            LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            mLayoutManager1.setAutoMeasureEnabled(false);
            abrDetailRecyclerView.setLayoutManager(mLayoutManager1);
        }
    }

    private class step5Adapter extends RecyclerView.Adapter<ListofNamesViewHolder> {
        int Y = 0;
        int position;
        Context context;
        List<GetAbrName> ForsearchArray;
        List<GetAbrName> ForTempArray;


        public step5Adapter(Context context, List<GetAbrName> filterListbyId) {
            this.context = context;
            this.ForsearchArray = new ArrayList<>(filterListbyId);
            this.ForTempArray = new ArrayList<>(ForsearchArray);
        }


        @NonNull
        @Override
        public ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.abr_name, viewGroup, false);
            return new ListofNamesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ListofNamesViewHolder holder, final int i) {


            holder.DoseName.setText(ForsearchArray.get(i).getAbr());
            holder.abrDetailRecyclerView.setVisibility(GONE);
            holder.DoseName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

            holder.DosePhaseType.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.DosePhaseType.setText("(" + ForsearchArray.get(i).getPhase() + " " + ForsearchArray.get(i).getType() + ")");

            holder.DoseName.setTextColor(getResources().getColor(R.color.textColorYellow));
            holder.DosePhaseType.setTextColor(getResources().getColor(R.color.textColorYellow));

            holder.arrow.setPadding(5, 5, 5, 5);
            holder.playVideo.setVisibility(GONE);

            holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
            holder.llayout.setBackgroundColor(Color.parseColor("#232324"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.llayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            holder.llayout.setLayoutParams(layoutParams);
            holder.UnderLineFOrAbr.setVisibility(VISIBLE);
            if (SelectedABRFORSHOW.equalsIgnoreCase(ForsearchArray.get(i).getId())) {
                holder.abrDetailRecyclerView.setVisibility(VISIBLE);
                holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                try {
                    holder.abrDetailRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerViewDoseDetail = new DoseDetail(context, abrs, true);
                    holder.abrDetailRecyclerView.setAdapter(recyclerViewDoseDetail);
                } catch (Exception v) {
                }

            }
//            if(holder.abrDetailRecyclerView.getVisibility() == VISIBLE)
//            {
//                holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
//            }
            try {
                if (ForsearchArray.get(i).getId().equalsIgnoreCase(selectedExerciseList.get(SelectedPosition).getGetDoses().get(0).getId())) {
                    holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                } else {
                    holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
                }
            } catch (Exception x) {
            }


            holder.llayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<GetAbrName> getAbrNamesX = new ArrayList<>();
                    getAbrNamesX.add(0, new GetAbrName(ForsearchArray.get(i).getAbr(), ForsearchArray.get(i).getId(), ForsearchArray.get(i).getType(), ForsearchArray.get(i).getPhase()));
                    selectedExerciseList.get(SelectedPosition).setGetDoses(getAbrNamesX);//  (new GetAbrName(ForsearchArray.get(i).getAbr(),ForsearchArray.get(i).getId()));

                    notifyDataSetChanged();
                }
            });

            holder.RlLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (holder.abrDetailRecyclerView.getVisibility() == VISIBLE) {
                        holder.abrDetailRecyclerView.setVisibility(GONE);
                        holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    } else {
                        if (SelectedABRFORSHOW.equalsIgnoreCase(ForsearchArray.get(i).getId())) {
                            holder.abrDetailRecyclerView.setVisibility(VISIBLE);
                            holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                        } else {
                            SelectedABR = ForsearchArray.get(i).getId();
                            SelectedABRFORSHOW = ForsearchArray.get(i).getId();

                            holder.abrDetailRecyclerView.setVisibility(VISIBLE);
                            holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                            whichapicAlled = "SelectedABR";
                            hide();
                            abrs = new ArrayList<>();
                            webServices.getPrescriptionAbrDetail(getAbrNames.get(i).getId(), context, exercise_Activity.this);
                            notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            int s = 0;
            try {
                s = ForsearchArray.size();
            } catch (Exception x) {
            }
            return s;
        }

        private void filter(String s) {

            String text = s.toLowerCase();

            int countx = 0;
            Boolean tootlge = false;
            text = text.toLowerCase().trim();
            //ForTempArray = new ArrayList<>(ForsearchArray);
            if (text.length() == 0) {
                ForsearchArray = ForTempArray;
            } else {
                ForsearchArray = new ArrayList<GetAbrName>();
                List<Exercise> exercise = new ArrayList<Exercise>();
                for (int i = 0; i < ForTempArray.size(); i++) {
                    if (ForTempArray.get(i).getAbr().toLowerCase().contains(text) || ForTempArray.get(i).getType().toLowerCase().contains(text) || ForTempArray.get(i).getPhase().toLowerCase().contains(text)) {
                        ForsearchArray.add(ForTempArray.get(i));
                    }
                }
            }

            notifyDataSetChanged();
        }

    }

}

