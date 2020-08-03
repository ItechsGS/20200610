package com.org.godspeed.fragments;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyLog;
import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleRelativeLayout;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.LoginScreen;
import com.org.godspeed.response_JsonS.ExerciseNameList.Exercise;
import com.org.godspeed.response_JsonS.ExerciseNameList.ExerciseName;
import com.org.godspeed.response_JsonS.GetExerciseGraph.GetExerciseGraph;
import com.org.godspeed.response_JsonS.Perform_analytic.Exercy;
import com.org.godspeed.response_JsonS.Perform_analytic.PerformAnalytic;
import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
import com.org.godspeed.response_JsonS.getTeams.GetTeam;
import com.org.godspeed.response_JsonS.getTeams.TrainingProgramDetail;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
import static com.org.godspeed.allOtherClasses.LoginScreen.userTypeOf;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_ID_FOR_PRE;
import static com.org.godspeed.service.BackgroundLocationUpdateService.GetTeamORIGINAL;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.service.SchoolDataService.getSchoolsList;
import static com.org.godspeed.utility.UtilityClass.ONE_DAY;
import static com.org.godspeed.utility.UtilityClass.ONE_WEEK;
import static com.org.godspeed.utility.UtilityClass.getMonthDateFirstdate;
import static com.org.godspeed.utility.UtilityClass.getMonthDateLastdate;
import static com.org.godspeed.utility.UtilityClass.getNameAthlete;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfToday;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfWeek;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.UtilityClass.showAlertDailog;

public class FragmentComparativeAnalytics extends Fragment implements GodSpeedInterface {


    LineChart lineChart;
    View view;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    LinearLayout rLayoutforSelectTeam;
    WebServices webServices = new WebServices();
    BubblePopupWindow dialog;
    RadioButton Avg, peak;
    Boolean coach = false;
    LinearLayout lLayoutForTimeClass, lLayoutForSportsClass, lLayoutForSchoolClass;
    RelativeLayout RReventName;
    TextView TextViewForTimeClass, TextViewForSportsClass, TextViewForSchoolClass, TypeOfFilter;
    LinearLayout LayoutDay, LayoutWeek, LayoutMonth, LayoutYear, LayoutCustom;
    TextView DayText, WeekText, YearText, MonthText, CustomText;
    List<PerformAnalytic> performAnalyticList = new ArrayList<>();
    List<PerformAnalytic> performAnalyticListSelected = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView ExerciseRecyclerByType;
    ExerciseByType exerciseByType;
    View viewXX;
    private RelativeLayout rLayoutGraphView, rLayoutforIndividual, rLayoutforTeam, rLayoutforTeamUnderline, rLayoutforIndividualUnderline, rLayoutforMovementUnderline, rLayoutforWorkoutUnderline, rLayoutforWorkout, rLayoutForMovement;
    private TextView textViewIndividual, textViewTeam, textViewMovement, textViewWorkout, textViewSelectTeamText, textViewExercise, textViewExerciseName, textViewMaxPower, textViewAverage, textViewPeak, textViewFrom, textViewFromDateValue, textViewTo, textViewToDateValue;
    private TextView startDate, endDate, textViewActivation, textViewRegen, textViewFuel, textViewBuild, textViewEnergy, textViewSkills;
    private RelativeLayout rLayoutForActivationTitle, rLayoutForSkillsTitle, rLayoutForBuildTitle, rLayoutForRegenTitle, rLayoutForEnergyTitle, rLayoutForFuelTitle;
    private String[] teamArray = null, teamIdArray = null;
    private ImageView backArrowEx;
    private SelectedTeamsAndAthlete selected_Teams_And_Athlete;
    private SelectedMovementAndWorkout selected_MOVEMENT_and_WORKOUT;
    private LinearLayout ForPerfom;
    private RecyclerView AllAthleteList, MoveMent_Workout_List;
    private String whichApiCalled = "";
    private List<SelectedAthleteDEtail> All_AthleteData = new ArrayList<>();
    private List<SelectedAthleteDEtail> Selected_AthleteData = new ArrayList<>();
    private LinearLayout CustomDateFilter;
    private RecyclerViewWorkoutAndMovement recyclerViewWorkoutAndMovement;
    private List<GetTeam> getTeamList = new ArrayList<>();
    private List<GetTeam> getTeamListSelected = new ArrayList<>();
    private List<TrainingProgramDetail> trainingProgramDetailAthlete = new ArrayList<>();
    private List<TrainingProgramDetail> trainingProgramDetailTeam = new ArrayList<>();
    private List<TrainingProgramDetail> SelectedtrainingProgramDetailAthlete = new ArrayList<>();
    private List<TrainingProgramDetail> SelectedtrainingProgramDetailTeam = new ArrayList<>();
    private String ViewType = "TEAMS";
    private RecyclerViewAllTeamData Teamadapter;
    private String ViewTypeOfSearch = "";
    private Transition transition;
    private LinearLayout ViewForListofAllData;
    private Calendar cal;
    private BubblePopupWindow popUp;
    private String SelectedTeamID = "";
    private String SelectedAthleteID = "";
    private Boolean WORKOUT = false;
    private String date_type = "1";
    private Context context;
    private View.OnClickListener pillarSelectionBackgroundClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rLayoutForActivationTitle:
                    rLayoutForActivationTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewActivation.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForBuildTitle:
                    rLayoutForBuildTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewBuild.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForFuelTitle:
                    rLayoutForFuelTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewFuel.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForEnergyTitle:
                    rLayoutForEnergyTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewEnergy.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForRegenTitle:
                    rLayoutForRegenTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewRegen.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
                    break;
                case R.id.rLayoutForSkillsTitle:
                    rLayoutForSkillsTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
                    textViewSkills.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
                    break;
            }
        }
    };

    private String ViewTypeExercise = "MOVEMENT";
    private ArrayList<Exercise> getExerciseListSelected = new ArrayList<>();
    private List<Exercise> getExerciseList = new ArrayList<>();
    private boolean WorkoutView = false;
    private LinearLayout ForPerfomMove;
    private ViewPager graphViewPager;
    private RecyclerView dialogBoxRecyclerView, dialogBoxRecyclerData;
    private ImageView backEventDialog, SaveEventDialog;
    private RelativeLayout rLayoutForBottomViewSettingsOptions, rLayoutForAddTeam, rLayoutForAddAthlete, rLayoutForAddCoach, rLayoutForDeleteAthlete;
    private TextView HeaderName, EvenText, textViewAthleteName, textViewHeightValue, textViewSMMValue, textViewKGValue, textViewBodyFatValue, TextViewAtheleteLevel;

    private LinearLayout rMainDialogLayout;
    private String from_date = "";
    private String to_date = "";

    private String SELECTED_TEAMS = "";
    private String SELECTED_ATHLETE = "";
    private String SELECTED_EXERCISE = "";
    private String SchoolIDs = "";
    private List<String> arrayListSchool = new ArrayList<>();
    private String exercise_type_id = "";

    private RelativeLayout rLayoutStartDate, rLayoutEndDate;

    private String CallApiFOR = "";
    private ArrayList<ExerciseName> exerciseNameList = new ArrayList<>();
    private List<GetExerciseGraph> getExerciseGraphList = new ArrayList<>();
    private CustomPagerAdapter graphViewPagerAdapter;
    private ArrayList<Exercy> getExerciseListSelectedByType = new ArrayList<>();
    private ArrayList<Exercy> getExerciseListMainByType = new ArrayList<>();
    private LinearLayout DialogMainRly, rLayoutForTeamsOrIndividualHeading;

    private JSONArray jsonArrayOFEx = new JSONArray();
    private int COUNTOFOBJECTSIN_TEAM_VALUES;

    private String data_avg_max = "0";
    private String exercise_id = "";
    private LinearLayout lLayoutForFIlterOption;
    private boolean GET_Perform_analytic = false;
    private Boolean ShowAlert = false;
    private List<Exercy> SelectedExercise = new ArrayList<>();
    private List<Exercy> ExerciseLIst = new ArrayList<>();
    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            int[] location = new int[2];
            int x;
            int y;
            switch (id) {
                case R.id.rLayoutforTeam:
                    rLayoutforIndividualUnderline.setVisibility(View.GONE);
                    rLayoutforTeamUnderline.setVisibility(View.VISIBLE);
                    textViewIndividual.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
                    textViewTeam.setTextColor(getResources().getColor(R.color.textColorYellow));
                    ViewType = "TEAMS";
                    selected_Teams_And_Athlete = new SelectedTeamsAndAthlete(context, ViewType);
                    Teamadapter = new RecyclerViewAllTeamData(context, getTeamList, "");
                    if (getTeamList.size() != 0) {
                        AllAthleteList.getLocationOnScreen(location);
                        x = location[0];
                        y = location[1];
                        showDialogmn(context, x, y, "AthleteTeam", v);
                    }
                    break;
                case R.id.rLayoutforIndividual:
                    rLayoutforIndividualUnderline.setVisibility(View.VISIBLE);
                    ViewType = "ATHLETE";
                    selected_Teams_And_Athlete = new SelectedTeamsAndAthlete(context, ViewType);
                    rLayoutforTeamUnderline.setVisibility(View.GONE);
                    textViewIndividual.setTextColor(getResources().getColor(R.color.textColorYellow));
                    textViewTeam.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
                    Teamadapter = new RecyclerViewAllTeamData(context, All_AthleteData);
                    if (All_AthleteData.size() == 0) {
                        whichApiCalled = "athlete";
                        webServices.getAthlete(LoginScreen.userId, "", "", SchoolIDs, context, FragmentComparativeAnalytics.this);
                    } else {
                        AllAthleteList.getLocationOnScreen(location);
                        x = location[0];
                        y = location[1];
                        showDialogmn(context, x, y, "AthleteTeam", v);
                    }


                    break;
                case R.id.rLayoutForMovement:
                    rLayoutforMovementUnderline.setVisibility(VISIBLE);
                    rLayoutforWorkoutUnderline.setVisibility(View.GONE);
                    textViewMovement.setTextColor(getResources().getColor(R.color.textColorYellow));
                    textViewWorkout.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
                    ViewTypeExercise = "MOVEMENT";
                    selected_MOVEMENT_and_WORKOUT = new SelectedMovementAndWorkout(context, "MOVEMENT");

                    if (exerciseNameList.size() == 0) {
                        whichApiCalled = "GetExerciseNewSecond";
                        webServices.GetExerciseNewSecond(context, FragmentComparativeAnalytics.this);
                    } else {
                        MoveMent_Workout_List.getLocationOnScreen(location);
                        x = location[0];
                        y = location[1];
                        showDialogmn(context, x, y, "MovementWorkout", v);
                    }
                    break;
                case R.id.rLayoutforWorkout:
                    rLayoutforWorkoutUnderline.setVisibility(View.VISIBLE);
                    ViewTypeExercise = "WORKOUT";
                    rLayoutforMovementUnderline.setVisibility(View.GONE);
                    textViewWorkout.setTextColor(getResources().getColor(R.color.textColorYellow));
                    textViewMovement.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
                    selected_MOVEMENT_and_WORKOUT = new SelectedMovementAndWorkout(context, "WORKOUT");


                    Log.e(VolleyLog.TAG, "onClick: " + performAnalyticList.size());
                    if (GET_Perform_analytic) {
                        ShowAlert = true;
                        whichApiCalled = "GetPerfomaltics";
                        performAnalyticList = new ArrayList<>();
                        //performAnalyticListSelected = new ArrayList<>();
                        exercise_type_id = "";
                        CallApiFOR = "";
                        webServices.Perform_analytic(date_type, SELECTED_TEAMS, SELECTED_ATHLETE, from_date, to_date, exercise_type_id, "", data_avg_max, context, FragmentComparativeAnalytics.this);
                        GET_Perform_analytic = false;
                    } else {
                        MoveMent_Workout_List.getLocationOnScreen(location);
                        x = location[0];
                        y = location[1];
                        showDialogmn(context, x, y, "MovementWorkout", v);
                    }


                    break;
            }

            AllAthleteList.setAdapter(selected_Teams_And_Athlete);
            MoveMent_Workout_List.setAdapter(selected_MOVEMENT_and_WORKOUT);
        }
    };

    private void initializeTextView(View view) {
        textViewIndividual = view.findViewById(R.id.textViewIndividual);
        textViewIndividual.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        textViewTeam = view.findViewById(R.id.textViewTeam);
        textViewTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
    }

    private void CallApiFor() {
        CallApiFOR = "";
        webServices.Perform_analytic(date_type, SELECTED_TEAMS, SELECTED_ATHLETE, from_date, to_date, exercise_type_id, exercise_id, data_avg_max, context, FragmentComparativeAnalytics.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_comparative_analytics, container, false);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        context = getActivity();
        // graphClass = new GraphClass();
        cal = Calendar.getInstance();


        AllAthleteList = view.findViewById(R.id.AllAthleteList);
        MoveMent_Workout_List = view.findViewById(R.id.MoveMent_Workout_List);
        ForPerfom = view.findViewById(R.id.ForPerfom);
        ForPerfom.setVisibility(GONE);
        ForPerfomMove = view.findViewById(R.id.ForPerfomMove);
        ForPerfomMove.setVisibility(GONE);
        CustomDateFilter = view.findViewById(R.id.CustomDateFilter);
        rLayoutForTeamsOrIndividualHeading = view.findViewById(R.id.rLayoutForTeamsOrIndividualHeading);

        rLayoutStartDate = view.findViewById(R.id.rLayoutStartDate);
        rLayoutEndDate = view.findViewById(R.id.rLayoutEndDate);

        exerciseByType = new ExerciseByType(ExerciseLIst);

        ForPerfom.setOnClickListener(view1 -> {
            int[] location = new int[2];
            AllAthleteList.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            showDialogmn(context, x, y, "AthleteTeam", view1);
        });
        int usertype = GlobalClass.ivar1;
        if (usertype == 1) {
            coach = true;
        } else {
            rLayoutForTeamsOrIndividualHeading.setVisibility(GONE);
            SELECTED_ATHLETE = LoginJson.get(0).getUserId();
            whichApiCalled = "GetPerfomaltics";
            exercise_type_id = "";
            CallApiFor();
        }


        ForPerfomMove.setOnClickListener(view1 -> {
            int[] location = new int[2];
            MoveMent_Workout_List.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            showDialogmn(context, x, y, "MovementWorkout", view1);
        });

        rLayoutStartDate.setOnClickListener(view1 -> {
            DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    from_date = df.format(newDate.getTime());
                    startDate.setText(from_date);
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            StartTime.show();
        });

        rLayoutEndDate.setOnClickListener(view1 -> {
            DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    to_date = df.format(newDate.getTime());
                    endDate.setText(to_date);
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            StartTime.show();
        });

        LayoutDay = view.findViewById(R.id.LayoutDay);
        LayoutWeek = view.findViewById(R.id.LayoutWeek);
        LayoutMonth = view.findViewById(R.id.LayoutMonth);
        LayoutYear = view.findViewById(R.id.LayoutYear);
        LayoutCustom = view.findViewById(R.id.LayoutCustom);

        DayText = view.findViewById(R.id.DayText);
        WeekText = view.findViewById(R.id.WeekText);
        MonthText = view.findViewById(R.id.MonthText);
        CustomText = view.findViewById(R.id.CustomText);
        YearText = view.findViewById(R.id.YearText);

        Avg = view.findViewById(R.id.Avg);

        peak = view.findViewById(R.id.peak);

        Avg.setOnClickListener(view1 -> {
            Avg.setTextColor(getResources().getColor(R.color.textColorYellow));
            peak.setTextColor(getResources().getColor(R.color.color_white));
            Avg.setChecked(true);
            peak.setChecked(false);
            data_avg_max = "0";
            if (!exercise_type_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            } else if (!exercise_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            }
        });

        peak.setOnClickListener(view1 -> {
            Avg.setTextColor(getResources().getColor(R.color.color_white));
            peak.setTextColor(getResources().getColor(R.color.textColorYellow));
            Avg.setChecked(false);
            peak.setChecked(true);
            data_avg_max = "1";
            if (!exercise_type_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            } else if (!exercise_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            }
        });

        LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));

        DayText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
        DayText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        CustomText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        LayoutDay.setOnClickListener(view1 -> {
            int[] location = new int[2];
            LayoutDay.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];

            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

            GET_Perform_analytic = true;

            CustomText.setTextColor(getResources().getColor(R.color.textColorYellow));
            DayText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));
            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));

            DayText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            CustomText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            long startTime = getStartTimeOfToday();
            long endTime = startTime + ONE_DAY;
            CustomDateFilter.setVisibility(GONE);
            date_type = "1";
            from_date = "";
            to_date = "";

            performAnalyticList = new ArrayList<>();
            if (!exercise_type_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            } else if (!exercise_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            }

        });

        CustomDateFilter.setVisibility(GONE);

        LayoutWeek.setOnClickListener(view1 -> {
            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            CustomText.setTextColor(getResources().getColor(R.color.textColorYellow));
            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));

            WeekText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));

            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));

            long startTime = getStartTimeOfWeek();
            GET_Perform_analytic = true;
            CustomDateFilter.setVisibility(GONE);
            long endTime = startTime + ONE_WEEK;
            date_type = "2";
            from_date = "";
            to_date = "";


            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            CustomText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            performAnalyticList = new ArrayList<>();

            if (!exercise_type_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            } else if (!exercise_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            }


        });

        LayoutMonth.setOnClickListener(view1 -> {
            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            CustomDateFilter.setVisibility(GONE);
            GET_Perform_analytic = true;
            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));
            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));
            CustomText.setTextColor(getResources().getColor(R.color.textColorYellow));
            MonthText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));
            long startTime = getMonthDateFirstdate();

            long endTime = getMonthDateLastdate();
            date_type = "3";

            from_date = "";

            to_date = "";

            performAnalyticList = new ArrayList<>();

            if (!exercise_type_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            } else if (!exercise_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            }

        });

        LayoutYear.setOnClickListener(view1 -> {
            int[] location = new int[2];
            LayoutYear.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            GET_Perform_analytic = true;
            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.color.textColorYellow));
            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            CustomText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));


            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));
            CustomText.setTextColor(getResources().getColor(R.color.textColorYellow));

            YearText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            performAnalyticList = new ArrayList<>();
            CustomDateFilter.setVisibility(GONE);
            date_type = "4";
            from_date = "";
            to_date = "";
            if (!exercise_type_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            } else if (!exercise_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            }
        });

        LayoutCustom.setOnClickListener(view1 -> {
            int[] location = new int[2];
            LayoutYear.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            GET_Perform_analytic = true;
            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_right_yellow));

            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            CustomText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));


            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));


            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));

            CustomText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            CustomDateFilter.setVisibility(VISIBLE);
            date_type = "5";

            performAnalyticList = new ArrayList<>();

            if (from_date.equalsIgnoreCase("") && to_date.equalsIgnoreCase("")) {
                from_date = df.format(cal.getTime());
                to_date = df.format(cal.getTime());
            }
            if (!exercise_type_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            } else if (!exercise_id.equalsIgnoreCase("")) {
                CallApiFOR = "getExerciseWithTypeID";
                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                    whichApiCalled = "GetPerfomaltics";
                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                    whichApiCalled = "GetExercise";
                }
                CallApiFor();
            }
        });

        graphViewPager = view.findViewById(R.id.graphViewPager);

        if (coach) {
            whichApiCalled = "getTeams";
            if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
                getTeamList = new ArrayList<>();
                arrayListSchool = new ArrayList<>();
                SchoolIDs = "";
                imageViewMenuFilter.setVisibility(VISIBLE);
                try {
                    if (SCHOOL_ID_FOR_PRE.equalsIgnoreCase("")) {
                        SchoolIDs = getSchoolsList.get(0).getSchoolId();
                    } else {
                        SchoolIDs = SCHOOL_ID_FOR_PRE;
                    }
                    arrayListSchool.add(SchoolIDs);
                } catch (Exception v) {
                }
                WebServices webServices = new WebServices();
                webServices.getTeams(LoginScreen.userId, SchoolIDs, context, FragmentComparativeAnalytics.this);
            } else {
                if (GetTeamORIGINAL.size() != 0) {
                    getTeamList = new ArrayList<>(GetTeamORIGINAL);
                } else {
                    webServices.getTeams(LoginScreen.userId, SchoolIDs, context, FragmentComparativeAnalytics.this);
                }
            }
        }


        rLayoutforSelectTeam = view.findViewById(R.id.rLayoutforSelectTeam);

        AllAthleteList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        MoveMent_Workout_List.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        selected_Teams_And_Athlete = new SelectedTeamsAndAthlete(context, ViewType);

        selected_MOVEMENT_and_WORKOUT = new SelectedMovementAndWorkout(context, "MOVEMENT");

        AllAthleteList.setAdapter(selected_Teams_And_Athlete);
        MoveMent_Workout_List.setAdapter(selected_MOVEMENT_and_WORKOUT);


        ViewForListofAllData = view.findViewById(R.id.ViewForListofAllData);
        startDate = view.findViewById(R.id.startDate);
        endDate = view.findViewById(R.id.endDate);


        textViewWorkout = view.findViewById(R.id.textViewWorkout);
        textViewMovement = view.findViewById(R.id.textViewMovement);

        startDate.setText(df.format(cal.getTime()));

        endDate.setText(df.format(cal.getTime()));

        rLayoutforIndividual = view.findViewById(R.id.rLayoutforIndividual);
        rLayoutForMovement = view.findViewById(R.id.rLayoutForMovement);
        rLayoutforWorkout = view.findViewById(R.id.rLayoutforWorkout);
        rLayoutforTeam = view.findViewById(R.id.rLayoutforTeam);

        rLayoutforIndividual.setOnClickListener(clickListener);
        rLayoutForMovement.setOnClickListener(clickListener);
        rLayoutforWorkout.setOnClickListener(clickListener);
        rLayoutforTeam.setOnClickListener(clickListener);

        rLayoutforTeamUnderline = view.findViewById(R.id.rLayoutforTeamUnderline);
        rLayoutforIndividualUnderline = view.findViewById(R.id.rLayoutforIndividualUnderline);
        rLayoutforWorkoutUnderline = view.findViewById(R.id.rLayoutforWorkoutUnderline);
        rLayoutforMovementUnderline = view.findViewById(R.id.rLayoutforMovementUnderline);

        rLayoutforIndividualUnderline.setVisibility(View.GONE);
        rLayoutforWorkoutUnderline.setVisibility(View.GONE);

        initializeTextView(view);

        return view;
    }

    public void showDialogmn(Context context, int x, int y, String movementWorkout, View viewX) {
        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View mView = mInflater.inflate(R.layout.empty_array, null, false);


        BubbleLinearLayout bubbleView = mView.findViewById(R.id.mainRlyofAddset);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));
        popUp = new BubblePopupWindow(mView, bubbleView);
        popUp.setCancelOnTouch(false);

        CardView cardView = mView.findViewById(R.id.VIewView);

        cardView.getLayoutParams().height = dpToPx(220);

        //popUp = new BubblePopupWindow(mView, dpToPx(350), dpToPx(250), false);
        popUp.setTouchable(true);
        popUp.setFocusable(true);
        popUp.setOutsideTouchable(true);
        popUp.setAnimationStyle(R.style.Animation);

        EditText calc_txt_Prise = mView.findViewById(R.id.calc_txt_Prise);
        ImageView calc_clear_txt_Prise = mView.findViewById(R.id.calc_clear_txt_Prise);
        recyclerView = mView.findViewById(R.id.custompopwindow);
        ExerciseRecyclerByType = mView.findViewById(R.id.ExerciseRecyclerByType);
        backArrowEx = mView.findViewById(R.id.backArrowEx);
        DialogMainRly = mView.findViewById(R.id.DialogMainRly);

        HeaderName = mView.findViewById(R.id.HeaderName);

        backArrowEx.setOnClickListener(view1 -> {
            ExerciseRecyclerByType.setVisibility(GONE);
            backArrowEx.setVisibility(GONE);
            recyclerView.setVisibility(VISIBLE);

            HeaderName.setText("WORKOUT");
            AutoTransition autoTransition = new AutoTransition();

            autoTransition.setDuration(200);
            TransitionManager.beginDelayedTransition(DialogMainRly, autoTransition);
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager1.setAutoMeasureEnabled(false);
        ExerciseRecyclerByType.setLayoutManager(linearLayoutManager1);

        Boolean performAnalyticListB = false;

        if (movementWorkout.equalsIgnoreCase("AthleteTeam")) {
            if (ViewType.equalsIgnoreCase("TEAMS")) {
                Teamadapter = new RecyclerViewAllTeamData(context, getTeamList, "");
            } else if (ViewType.equalsIgnoreCase("ATHLETE")) {
                Teamadapter = new RecyclerViewAllTeamData(context, All_AthleteData);
            }
            recyclerView.setAdapter(null);
            recyclerView.setAdapter(Teamadapter);
        } else {
            if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
                recyclerViewWorkoutAndMovement = new RecyclerViewWorkoutAndMovement(getExerciseList);
            } else {
                recyclerViewWorkoutAndMovement = new RecyclerViewWorkoutAndMovement(performAnalyticList, "");
                if (performAnalyticListB && performAnalyticList.size() == 0) {
                    performAnalyticListB = true;
                }
            }
            recyclerView.setAdapter(null);
            recyclerView.setAdapter(recyclerViewWorkoutAndMovement);
        }
        if (performAnalyticListB && performAnalyticList.size() == 0) {
            UtilityClass.showAlertDailog(context, "You don't have assigned training programs.");
            return;
        }

        selected_Teams_And_Athlete.notifyDataSetChanged();
        selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();


        calc_clear_txt_Prise.setOnClickListener(view1 -> calc_txt_Prise.setText(""));

        calc_txt_Prise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                try {
                    Teamadapter.filter(text);
                    recyclerViewWorkoutAndMovement.filter(text);
                    exerciseByType.filter(text);
                } catch (Exception m) {

                }
                if (text.length() > 0) {
                    calc_clear_txt_Prise.setVisibility(VISIBLE);
                } else {
                    calc_clear_txt_Prise.setVisibility(GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        popUp.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CallApiFOR.equalsIgnoreCase("")) {
                    if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
                        if (performAnalyticList.size() == 0) {
                            whichApiCalled = "GetPerfomaltics";
                            performAnalyticList = new ArrayList<>();
                            performAnalyticListSelected = new ArrayList<>();
                            exercise_type_id = "";
                            CallApiFor();
                        }
                    } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
                        whichApiCalled = "GetExercise";
                        CallApiFor();
                    }
                }
            }
        });

        if (viewX != null) {
            popUp.showArrowTo(viewX, BubbleStyle.ArrowDirection.Up);
        } else {
            popUp.showAtLocation(mView, Gravity.NO_GRAVITY, 0, 0);
        }

        viewXX = viewX;
    }


    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            parseRequiredData(result);
        }
    }

    private void parseRequiredData(String result) {
        Log.e(VolleyLog.TAG, "parseRequiredData: " + whichApiCalled);
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj.getString("responseMessage");

            Log.e("**********", "mymessge" + responseMessage);


            Log.e(VolleyLog.TAG, "result: " + result);

            if (WebServices.responseCode == 200) {
                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");
                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    Gson gson = new Gson();
                    if (whichApiCalled.equalsIgnoreCase("athlete")) {
                        All_AthleteData = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), SelectedAthleteDEtail[].class)));
                        UtilityClass.hide();

                        int[] location = new int[2];
                        AllAthleteList.getLocationOnScreen(location);
                        int x = location[0];
                        int y = location[1];
                        showDialogmn(context, x, y, "AthleteTeam", rLayoutforIndividual);
                        performAnalyticList = new ArrayList<>();
                        getTeamListSelected = new ArrayList<>();

                        performAnalyticListSelected = new ArrayList<>();

                        jsonArrayOFEx = new JSONArray();
                        graphViewPager.setAdapter(null);
                        graphViewPagerAdapter = new CustomPagerAdapter(context);
                        graphViewPager.setAdapter(graphViewPagerAdapter);

                        selected_Teams_And_Athlete.notifyDataSetChanged();
                        selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
                        recyclerViewWorkoutAndMovement.notifyDataSetChanged();

                    } else if (whichApiCalled.equalsIgnoreCase("getTeams")) {
                        hide();
                        getTeamList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetTeam[].class)));
                        performAnalyticList = new ArrayList<>();
                        getTeamListSelected = new ArrayList<>();

                        performAnalyticListSelected = new ArrayList<>();

                        jsonArrayOFEx = new JSONArray();
                        graphViewPager.setAdapter(null);
                        graphViewPagerAdapter = new CustomPagerAdapter(context);
                        graphViewPager.setAdapter(graphViewPagerAdapter);

                        selected_Teams_And_Athlete.notifyDataSetChanged();
                        selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
                        recyclerViewWorkoutAndMovement.notifyDataSetChanged();

                    } else if (whichApiCalled.equalsIgnoreCase("GetExerciseNewSecond")) {
                        exerciseNameList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), ExerciseName[].class)));
                        exerciseNameList.get(0).getExercise().forEach(m -> {
                            getExerciseList.add(m);
                        });

                        hide();
                        int[] location = new int[2];
                        MoveMent_Workout_List.getLocationOnScreen(location);
                        int x = location[0];
                        int y = location[1];
                        showDialogmn(context, x, y, "MovementWorkout", rLayoutForMovement);
                    } else if (whichApiCalled.equalsIgnoreCase("GetPerfomaltics")) {
                        Log.e(VolleyLog.TAG, "GetPerfomaltics: " + jsonDataArray);
                        //jsonArrayOFEx = new JSONArray();
                        performAnalyticList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), PerformAnalytic[].class)));
                        //performAnalyticListSelected = new ArrayList<>();
                        selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();

                        graphViewPager.setAdapter(null);

                        graphViewPagerAdapter = new CustomPagerAdapter(context);

                        graphViewPager.setAdapter(graphViewPagerAdapter);


                        if (ShowAlert) {
                            int[] location = new int[2];
                            MoveMent_Workout_List.getLocationOnScreen(location);
                            int x = location[0];
                            int y = location[1];
                            showDialogmn(context, x, y, "MovementWorkout", rLayoutforWorkout);
                            ShowAlert = false;
                        }

                        hide();
                    } else if (whichApiCalled.equalsIgnoreCase("GetExercise")) {

                        hide();
                        getExerciseGraphList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetExerciseGraph[].class)));

                        jsonArrayOFEx = jsonDataArray;

                        graphViewPager.setAdapter(null);

                        graphViewPagerAdapter = new CustomPagerAdapter(context);

                        graphViewPager.setAdapter(graphViewPagerAdapter);
                    }
                }
            } else {
                hide();
                if (whichApiCalled.equalsIgnoreCase("GetPerfomaltics")) {
                    UtilityClass.showAlertDailog(context, responseMessage);
                    performAnalyticList = new ArrayList<>();
                    performAnalyticListSelected = new ArrayList<>();
                    selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
                    recyclerViewWorkoutAndMovement.notifyDataSetChanged();
                    jsonArrayOFEx = new JSONArray();
                    graphViewPager.setAdapter(null);

                    graphViewPagerAdapter = new CustomPagerAdapter(context);

                    graphViewPager.setAdapter(graphViewPagerAdapter);
                }

            }
        } catch (Exception v) {
        }
    }

    @Override
    public void onDestroyView() {
        AllAthleteList.setAdapter(null);
        MoveMent_Workout_List.setAdapter(null);
        super.onDestroyView();
    }

    public void toggle() {
        showDialogBox(0, 0, "Select School");
    }

    public void showDialogBox(int x, int y, String athlete_level) {
        Log.e(VolleyLog.TAG, "showDialogBox: " + athlete_level);


        View AlertBoxView = LayoutInflater.from(context).inflate(R.layout.cutom_dialogbox_athlete_screen, null);

        BubbleRelativeLayout bubbleView = AlertBoxView.findViewById(R.id.CardX);


        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));
        //
        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);
        dialog.setCancelOnTouch(false);

        RelativeLayout mainRly = AlertBoxView.findViewById(R.id.mainRly);
        LinearLayout rMainDialogLayout = AlertBoxView.findViewById(R.id.rMainDialogLayout);

        dialog.showAtLocation(AlertBoxView, Gravity.CENTER, 0, 0);


        //imageViewAppIconForAnimation = dialog.findViewById(R.id.imageViewAppIconForAnimation);
        EvenText = AlertBoxView.findViewById(R.id.EventName);
        EvenText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        EvenText.setText(athlete_level);
        backEventDialog = AlertBoxView.findViewById(R.id.backEventDialog);
        SaveEventDialog = AlertBoxView.findViewById(R.id.SaveEventDialog);


        lLayoutForTimeClass = AlertBoxView.findViewById(R.id.lLayoutForTimeClass);
        lLayoutForSportsClass = AlertBoxView.findViewById(R.id.lLayoutForSportsClass);
        lLayoutForSchoolClass = AlertBoxView.findViewById(R.id.lLayoutForSchoolClass);


        lLayoutForFIlterOption = AlertBoxView.findViewById(R.id.lLayoutForFIlterOption);


        RReventName = AlertBoxView.findViewById(R.id.RReventName);

        rMainDialogLayout = AlertBoxView.findViewById(R.id.rMainDialogLayout);
        TextViewForTimeClass = AlertBoxView.findViewById(R.id.TextViewForTimeClass);
        TextViewForSportsClass = AlertBoxView.findViewById(R.id.TextViewForSportsClass);
        TextViewForSchoolClass = AlertBoxView.findViewById(R.id.TextViewForSchoolClass);
        TypeOfFilter = AlertBoxView.findViewById(R.id.TypeOfFilter);

        backEventDialog.setVisibility(View.INVISIBLE);
        lLayoutForSchoolClass.setOnClickListener(view -> {
            lLayoutForFIlterOption.setVisibility(View.GONE);
            TypeOfFilter.setVisibility(View.VISIBLE);
            TypeOfFilter.setText("Select School");
            dialogBoxRecyclerView.setVisibility(View.VISIBLE);
            dialogBoxRecyclerView.setAdapter(new AthleteLevelViewX("School"));
            backEventDialog.setVisibility(View.VISIBLE);
        });

        SaveEventDialog.setVisibility(View.VISIBLE);


        TextViewForSportsClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        TextViewForSchoolClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        TextViewForTimeClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        dialogBoxRecyclerView = AlertBoxView.findViewById(R.id.dialogBoxRecyclerView);


        //rMainDialogLayout.setLayoutParams(rMainDialogLayout.getHeight() = 320);


        dialogBoxRecyclerView.setHasFixedSize(true);
        dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        DividerItemDecoration divider = new
                DividerItemDecoration(dialogBoxRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
//        divider.setDrawable(
//                ContextCompat.getDrawable(context, R.drawable.line_divider)
//        );
//        if (showDialogOf.equalsIgnoreCase("Classes")) {
        divider.setDrawable(
                ContextCompat.getDrawable(context, R.drawable.divider_dark_light)
        );
        //}
        dialogBoxRecyclerView.addItemDecoration(divider);

//        if (!showDialogOf.equalsIgnoreCase("LEVEL")) {
//            ViewGroup.LayoutParams params = mainRly.getLayoutParams();
//            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//            params.height = (int) ((320 * displayMetrics.density) + 0.5);
//        }


        dialogBoxRecyclerView.setAdapter(new AthleteLevelViewX(""));


//        if (showDialogOf.equalsIgnoreCase("LEVEL")) {
//            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
//            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
//            wmlp.x = x - 50;
//            wmlp.y = y;
//        }


    }

    private class ListofNamesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout RlLayout;
        RelativeLayout UnderLineFOrAbr;
        TextView AthleteName;
        RecyclerView abrDetailRecyclerView;
        RelativeLayout ExcersiseSubmitButtonX, RSelectTickX, rlayoutDose;
        LinearLayout rLayoutforLBandREPS;
        RelativeLayout llayout, runSelectTick;
        ImageView arrow, unSelectTick;
        ImageView showDetail, HideDetail, playVideo;

        public ListofNamesViewHolder(@NonNull View itemView) {
            super(itemView);
            AthleteName = itemView.findViewById(R.id.DoseName);
            playVideo = itemView.findViewById(R.id.playVideo);
            unSelectTick = itemView.findViewById(R.id.unSelectTick);
            runSelectTick = itemView.findViewById(R.id.runSelectTick);
            ExcersiseSubmitButtonX = itemView.findViewById(R.id.ExcersiseSubmitButtonX);
            rLayoutforLBandREPS = itemView.findViewById(R.id.rLayoutforLBandREPS);
            llayout = itemView.findViewById(R.id.llayout);
            arrow = itemView.findViewById(R.id.arrow);
            abrDetailRecyclerView = itemView.findViewById(R.id.abrDetailRecyclerView);
            UnderLineFOrAbr = itemView.findViewById(R.id.UnderLineFOrAbr);
            RlLayout = itemView.findViewById(R.id.RlLayout);
            abrDetailRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        }
    }

    private class RecyclerViewAllTeamData extends RecyclerView.Adapter<ListofNamesViewHolder> {
        private List<ExerciseName> ForsearchArrayExerciseNames;
        private Context context;
        private List<GetTeam> ForsearchArrayTeam;
        private List<SelectedAthleteDEtail> ForsearchArrayAthlete;

        public RecyclerViewAllTeamData(Context context, List<GetTeam> All_AthleteData, String m) {
            this.context = context;
            this.ForsearchArrayTeam = new ArrayList<>(All_AthleteData);
        }

        public RecyclerViewAllTeamData(Context context, List<SelectedAthleteDEtail> selectedAthleteDEtails) {
            this.context = context;
            this.ForsearchArrayAthlete = new ArrayList<>(selectedAthleteDEtails);
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
            holder.AthleteName.setTag("NotSelected");

            holder.AthleteName.setTextColor(getResources().getColor(R.color.textColorYellow));
            holder.arrow.setPadding(5, 5, 5, 5);
            holder.arrow.setVisibility(GONE);
            holder.playVideo.setVisibility(GONE);
            holder.llayout.setBackgroundColor(Color.parseColor("#232324"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.llayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            holder.llayout.setLayoutParams(layoutParams);
            holder.UnderLineFOrAbr.setVisibility(VISIBLE);

            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));

            if (ViewType.equalsIgnoreCase("TEAMS")) {
                holder.AthleteName.setText(ForsearchArrayTeam.get(i).getTeamName());
                getTeamListSelected.forEach(m -> {
                    if (ForsearchArrayTeam.get(i).getTeamId().equalsIgnoreCase(m.getTeamId())) {
                        holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                        holder.AthleteName.setTag("Selected");
                    }
                });
                holder.llayout.setOnClickListener(view -> {
                    if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
                        for (int i1 = 0; i1 < getTeamListSelected.size(); i1++) {
                            if (getTeamListSelected.get(i1).getTeamId().equalsIgnoreCase(ForsearchArrayTeam.get(i).getTeamId())) {
                                getTeamListSelected.remove(i1);
                            }
                        }
                        holder.AthleteName.setTag("NotSelected");
                    } else {
                        if (getTeamListSelected.size() == 3) {
                            showAlertDailog(context, "You can't select more than three teams.");
                            return;
                        }
                        getTeamListSelected.add(ForsearchArrayTeam.get(i));

                        holder.AthleteName.setTag("Selected");

                    }
//                    SELECTED_TEAMS = "";
//                    StringBuilder m = new StringBuilder();
//                    for (int i1 = 0; i1 < getTeamListSelected.size(); i1++) {
//                        SELECTED_TEAMS = m.append(getTeamListSelected.get(i1).getTeamId() + (i1 != getTeamListSelected.size() - 1 ? "," : "")).toString();
//                    }
//                    getExerciseGraphList = new ArrayList<>();
//                    Log.e(VolleyLog.TAG, "SELECTED_TEAMS: " + SELECTED_TEAMS);
//                    CallApiFOR = "TeamSide";
                    selected_Teams_And_Athlete.notifyDataSetChanged();
                    notifyDataSetChanged();
                });
                HeaderName.setText("TEAMS");
            } else if (ViewType.equalsIgnoreCase("ATHLETE")) {
                String TextX = "";
//                if (ForsearchArrayAthlete.get(i).getFirstName().equalsIgnoreCase("") && ForsearchArrayAthlete.get(i).getLastName().equalsIgnoreCase("")) {
//                   try{
//                       TextX = ForsearchArrayAthlete.get(i).getEmailId().substring(0, ForsearchArrayAthlete.get(i).getEmailId().indexOf("@") );
//                   }catch (Exception m){
//                       Log.e(VolleyLog.TAG, "ATHLETE: "+i+"   "+ForsearchArrayAthlete.get(i).getUserId());
//                   }
//
//
//
//
//                } else {
//                    TextX = ForsearchArrayAthlete.get(i).getLastName() + " " + ForsearchArrayAthlete.get(i).getFirstName();
//                }

                HeaderName.setText("ATHLETE");
                holder.AthleteName.setText(getNameAthlete(ForsearchArrayAthlete.get(i).getFirstName(), ForsearchArrayAthlete.get(i).getLastName(), ForsearchArrayAthlete.get(i).getEmailId()));

                Selected_AthleteData.forEach(m -> {
                    if (ForsearchArrayAthlete.get(i).getUserId().equalsIgnoreCase(m.getUserId())) {
                        holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                        holder.AthleteName.setTag("Selected");
                    }
                });

                holder.llayout.setOnClickListener(view -> {
                    if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
                        for (int i1 = 0; i1 < Selected_AthleteData.size(); i1++) {
                            if (Selected_AthleteData.get(i1).getUserId().equalsIgnoreCase(ForsearchArrayAthlete.get(i).getUserId())) {
                                Selected_AthleteData.remove(i1);
                            }
                        }
                        holder.AthleteName.setTag("NotSelected");
                    } else {
                        if (Selected_AthleteData.size() == 5) {
                            showAlertDailog(context, "You can't select more than 5 athlete's..");
                            return;
                        }
                        Selected_AthleteData.add(ForsearchArrayAthlete.get(i));
                        holder.AthleteName.setTag("Selected");

                    }
//                    SELECTED_ATHLETE  = "";
//                    StringBuilder m = new StringBuilder();
//                    for (int i1 = 0; i1 < Selected_AthleteData.size(); i1++) {
//                        SELECTED_ATHLETE = m.append(Selected_AthleteData.get(i1).getUserId() + (i1 != Selected_AthleteData.size() - 1 ? "," : "")).toString();
//                    }
//                    getExerciseGraphList = new ArrayList<>();
//                    Log.e(VolleyLog.TAG, "SELECTED_ATHLETE: " + SELECTED_ATHLETE);
//                    CallApiFOR = "TeamSide";
                    selected_Teams_And_Athlete.notifyDataSetChanged();
                    notifyDataSetChanged();
                });
            }
        }

        @Override
        public int getItemCount() {
            int s = 0;
            try {
                if (ViewType.equalsIgnoreCase("TEAMS")) {
                    s = ForsearchArrayTeam.size();
                } else if (ViewType.equalsIgnoreCase("ATHLETE")) {
                    s = ForsearchArrayAthlete.size();
                }
            } catch (Exception v) {

            }


            return s;

        }

        private void filter(String s) {
            String text = s.toLowerCase().trim();
            if (text.length() == 0) {
                if (ViewType.equalsIgnoreCase("TEAMS")) {
                    ForsearchArrayTeam = new ArrayList<>(getTeamList);
                } else {
                    ForsearchArrayAthlete = new ArrayList<>(All_AthleteData);
                }

            } else {
                if (ViewType.equalsIgnoreCase("TEAMS")) {
                    ForsearchArrayTeam = new ArrayList<>();
                    for (int i = 0; i < getTeamList.size(); i++) {
                        if (getTeamList.get(i).getTeamName().toLowerCase().contains(text)) {
                            ForsearchArrayTeam.add(getTeamList.get(i));
                        }
                    }
                } else {
                    ForsearchArrayAthlete = new ArrayList<>();

                    for (int i = 0; i < All_AthleteData.size(); i++) {
                        String TextX = "";
                        TextX = UtilityClass.getNameAthlete(All_AthleteData.get(i).getFirstName(), All_AthleteData.get(i).getLastName(), All_AthleteData.get(i).getEmailId());

                        if (TextX.toLowerCase().contains(text)) {
                            ForsearchArrayAthlete.add(All_AthleteData.get(i));
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    private class ExerciseByType extends RecyclerView.Adapter<ListofNamesViewHolder> {
        private List<Exercy> ForsearchArrayExercise;

        public ExerciseByType(List<Exercy> exerciseNames) {
            this.ForsearchArrayExercise = new ArrayList<>(exerciseNames);
        }


        @NonNull
        @Override
        public ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.abr_name, viewGroup, false);
            return new ListofNamesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ListofNamesViewHolder holder, int i) {
            holder.AthleteName.setTag("NotSelected");

            holder.AthleteName.setTextColor(getResources().getColor(R.color.textColorYellow));
            holder.arrow.setPadding(5, 5, 5, 5);
            holder.arrow.setVisibility(GONE);
            holder.playVideo.setVisibility(GONE);
            holder.llayout.setBackgroundColor(Color.parseColor("#232324"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.llayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            holder.llayout.setLayoutParams(layoutParams);
            holder.UnderLineFOrAbr.setVisibility(VISIBLE);

            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));

            holder.AthleteName.setText(ForsearchArrayExercise.get(i).getExerciseName());

            SelectedExercise.forEach(m -> {
                if (ForsearchArrayExercise.get(i).getExerciseId().equalsIgnoreCase(m.getExerciseId())) {
                    holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                    holder.AthleteName.setTag("Selected");
                }
            });
            holder.llayout.setOnClickListener(view -> {
                performAnalyticListSelected = new ArrayList<>();
                if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
                    for (int i1 = 0; i1 < SelectedExercise.size(); i1++) {
                        if (SelectedExercise.get(i1).getExerciseId().equalsIgnoreCase(ForsearchArrayExercise.get(i).getExerciseId())) {
                            SelectedExercise.remove(i1);
                        }
                    }
                    holder.AthleteName.setTag("NotSelected");
                } else {
                    SelectedExercise.add(ForsearchArrayExercise.get(i));
                    holder.AthleteName.setTag("Selected");
                }

                exercise_type_id = "";
                exercise_id = "";
                StringBuilder m = new StringBuilder();
                for (int i1 = 0; i1 < SelectedExercise.size(); i1++) {
                    exercise_id = m.append(SelectedExercise.get(i1).getExerciseId() + (SelectedExercise.size() - 1 == i1 ? "" : ",")).toString();
                }
                CallApiFOR = "getExerciseWithTypeID";
                selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
                notifyDataSetChanged();

            });


        }

        @Override
        public int getItemCount() {
            int s = 0;
            try {

                s = ForsearchArrayExercise.size();

            } catch (Exception v) {

            }
            return s;

        }

        private void filter(String s) {
            String text = s.toLowerCase().trim();

            if (text.length() == 0) {
                ForsearchArrayExercise = new ArrayList<>(ExerciseLIst);
            } else {
                ForsearchArrayExercise = new ArrayList<>();
                for (int i = 0; i < ExerciseLIst.size(); i++) {
                    if (ExerciseLIst.get(i).getExerciseName().toLowerCase().contains(text)) {
                        ForsearchArrayExercise.add(ExerciseLIst.get(i));
                    }
                }

            }
            notifyDataSetChanged();
        }
    }

    private class SelectedMovementAndWorkout extends RecyclerView.Adapter<TeamDataHolder> {
        Context context;
        String viewType = "";

        public SelectedMovementAndWorkout(Context context, String viewType) {
            this.context = context;
            this.viewType = viewType;
        }

        @Override
        public TeamDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.team_name_list, viewGroup, false);
            return new TeamDataHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TeamDataHolder holder, int i) {
            holder.TypeArrow.setVisibility(GONE);
            if (viewType.equalsIgnoreCase("MOVEMENT")) {

                holder.teamName.setText(getExerciseListSelected.get(i).getExerciseName());
                holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
                holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
                holder.teamName.setTextColor(getResources().getColor(R.color.color_white));
                holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (viewType.equalsIgnoreCase("MOVEMENT")) {

                        } else if (viewType.equalsIgnoreCase("WORKOUT")) {

                        }
                    }
                });

            } else if (viewType.equalsIgnoreCase("WORKOUT")) {
                if (SelectedExercise.size() != 0) {
                    holder.teamName.setText(SelectedExercise.get(i).getExerciseName());
                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
                    holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
                    holder.teamName.setTextColor(getResources().getColor(R.color.color_white));
                    holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                } else {
                    holder.teamName.setText(performAnalyticListSelected.get(i).getTypeName());
                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
                    holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
                    holder.teamName.setTextColor(getResources().getColor(R.color.color_white));
                    holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                    holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (viewType.equalsIgnoreCase("MOVEMENT")) {

                            } else if (viewType.equalsIgnoreCase("WORKOUT")) {

                            }
                        }
                    });
                }
            }
            holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
            holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
            holder.teamName.setTextColor(getResources().getColor(R.color.color_white));

            holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (viewType.equalsIgnoreCase("MOVEMENT")) {

                    } else if (viewType.equalsIgnoreCase("WORKOUT")) {

                    }
                }
            });

        }


        @Override
        public int getItemCount() {
            int count = 0;
            if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
                count = getExerciseListSelected.size();
            } else {
                if (SelectedExercise.size() != 0) {
                    count = SelectedExercise.size();
                } else {
                    count = performAnalyticListSelected.size();
                }

            }
            return count;
        }


    }

    private class SelectedTeamsAndAthlete extends RecyclerView.Adapter<TeamDataHolder> {
        Context context;
        String viewType = "";

        public SelectedTeamsAndAthlete(Context context, String viewType) {
            this.context = context;
            this.viewType = viewType;
            if (viewType.equalsIgnoreCase("TEAMS")) {
                trainingProgramDetailTeam = new ArrayList<>();
            } else {
                trainingProgramDetailAthlete = new ArrayList<>();
            }
        }

        @Override
        public TeamDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.team_name_list, viewGroup, false);
            return new TeamDataHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TeamDataHolder holder, int i) {
            holder.TypeArrow.setVisibility(GONE);

            if (viewType.equalsIgnoreCase("TEAMS")) {

//                if (getTeamListSelected.get(i).getTeamId().equalsIgnoreCase("-1")) {
//                    holder.teamName.setTextSize(20f);
//                }

                holder.teamName.setText(getTeamListSelected.get(i).getTeamName());


                if (getTeamListSelected.get(i).getTrainingProgramDetail() != null) {
                    getTeamListSelected.get(i).getTrainingProgramDetail().forEach(t -> {
                                trainingProgramDetailTeam.add(new TrainingProgramDetail(t.getId(), t.getTeamId(), t.getTrainingProgramId(),
                                        t.getAssignprogramDate(), t.getStartDate(),
                                        t.getAthleteId(), t.getUserId(), t.getProgramName(), t.getCreateFolderParentId(), t.getTrainingProgramStatus(), t.getGymAccountId(), t.getAssignProgramId()
                                ));
                            }
                    );
                    for (int x = 0; x < trainingProgramDetailTeam.size(); x++) {
                        for (int j = x + 1; j < trainingProgramDetailTeam.size(); j++) {
                            if (trainingProgramDetailTeam.get(x).getAssignProgramId().equalsIgnoreCase(trainingProgramDetailTeam.get(j).getAssignProgramId())) {
                                trainingProgramDetailTeam.remove(j);
                                j--;
                            }
                        }
                    }
                    SELECTED_ATHLETE = "";
                    SELECTED_TEAMS = "";
                    StringBuilder m = new StringBuilder();
                    for (int i1 = 0; i1 < getTeamListSelected.size(); i1++) {
                        SELECTED_TEAMS = m.append(getTeamListSelected.get(i1).getTeamId() + (i1 != getTeamListSelected.size() - 1 ? "," : "")).toString();
                    }
                    getExerciseGraphList = new ArrayList<>();
                    Log.e(VolleyLog.TAG, "SELECTED_TEAMS: " + SELECTED_TEAMS);
                    CallApiFOR = "TeamSide";
                }


            } else if (viewType.equalsIgnoreCase("ATHLETE")) {
//                if (Selected_AthleteData.get(i).getTeamId().equalsIgnoreCase("-1")) {
//                    holder.teamName.setTextSize(20f);
//                }
                if (Selected_AthleteData.get(i).getFirstName().equalsIgnoreCase("") && Selected_AthleteData.get(i).getLastName().equalsIgnoreCase("")) {
                    try {
                        holder.teamName.setText(Selected_AthleteData.get(i).getEmailId().substring(0, Selected_AthleteData.get(i).getEmailId().indexOf("@")));
                    } catch (Exception x) {

                    }
                } else {
                    holder.teamName.setText(Selected_AthleteData.get(i).getLastName() + " " + Selected_AthleteData.get(i).getFirstName());
                }
                holder.teamName.setText(UtilityClass.getNameAthlete(Selected_AthleteData.get(i).getFirstName(), Selected_AthleteData.get(i).getLastName(), Selected_AthleteData.get(i).getEmailId()));
                if (Selected_AthleteData.get(i).getAssingProgramDetails() != null)
                    Selected_AthleteData.get(i).getAssingProgramDetails().forEach(t -> {
                                trainingProgramDetailAthlete.add(new TrainingProgramDetail(t.getId(),
                                        t.getTeamId(),
                                        t.getTrainingProgramId(),
                                        t.getAssignprogramDate(),
                                        t.getStartDate(),
                                        t.getAthleteId(), t.getUserId(), t.getProgramName(), t.getCreateFolderParentId(), t.getTrainingProgramStatus(), t.getGymAccountId(), t.getAssignProgramId()
                                ));
                            }
                    );
                for (int x = 0; x < trainingProgramDetailAthlete.size(); x++) {
                    for (int j = x + 1; j < trainingProgramDetailAthlete.size(); j++) {
                        if (trainingProgramDetailAthlete.get(x).getAssignProgramId().equalsIgnoreCase(trainingProgramDetailAthlete.get(j).getAssignProgramId())) {
                            trainingProgramDetailAthlete.remove(j);
                            j--;
                        }
                    }
                }
                SELECTED_ATHLETE = "";
                SELECTED_TEAMS = "";
                StringBuilder m = new StringBuilder();
                for (int i1 = 0; i1 < Selected_AthleteData.size(); i1++) {
                    SELECTED_ATHLETE = m.append(Selected_AthleteData.get(i1).getUserId() + (i1 != Selected_AthleteData.size() - 1 ? "," : "")).toString();
                }
                getExerciseGraphList = new ArrayList<>();
                Log.e(VolleyLog.TAG, "SELECTED_ATHLETE: " + SELECTED_ATHLETE);
                CallApiFOR = "TeamSide";
            }

            holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
            holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
            holder.teamName.setTextColor(getResources().getColor(R.color.color_white));

            holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (viewType.equalsIgnoreCase("TEAMS")) {

                        SelectedTeamID = getTeamListSelected.get(i).getTeamId();
                        notifyDataSetChanged();

                    } else if (viewType.equalsIgnoreCase("ATHLETE")) {

                        SelectedAthleteID = Selected_AthleteData.get(i).getUserId();
                        notifyDataSetChanged();

                    }
                }
            });

        }


        @Override
        public int getItemCount() {
            int count = 0;
            if (viewType.equalsIgnoreCase("TEAMS")) {
                count = getTeamListSelected.size();
            } else if (viewType.equalsIgnoreCase("ATHLETE")) {
                count = Selected_AthleteData.size();
            }
            return count;
        }


    }

    private class TeamDataHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        RelativeLayout rLayoutofTeam;
        ImageView TypeArrow;
        LinearLayout ForTeam, ForPerfom;

        public TeamDataHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.PerfromName);
            ForTeam = itemView.findViewById(R.id.ForTeam);
            ForTeam.setVisibility(GONE);
            ForPerfom = itemView.findViewById(R.id.ForPerfom);
            ForPerfom.setVisibility(VISIBLE);

            rLayoutofTeam = itemView.findViewById(R.id.rLayoutofPerfrom);
            TypeArrow = itemView.findViewById(R.id.TypeArrow);
        }
    }

    private class RecyclerViewWorkoutAndMovement extends RecyclerView.Adapter<ListofNamesViewHolder> {
        private List<Exercise> ForsearchArrayExercise;
        private List<PerformAnalytic> performAnalyticListX;

        public RecyclerViewWorkoutAndMovement(List<Exercise> exerciseNames) {
            this.ForsearchArrayExercise = new ArrayList<>(exerciseNames);
            ExerciseLIst = new ArrayList<>();
        }

//        public RecyclerViewWorkoutAndMovement(List<TrainingProgramDetail> trainingProgramDetailTeamX, List<TrainingProgramDetail> trainingProgramDetailAthleteX) {
//            this.ForSearchtrainingProgramDetailAthlete = trainingProgramDetailAthleteX;
//            this.ForSearchtrainingProgramDetailTeam = trainingProgramDetailTeamX;
//        }

        public RecyclerViewWorkoutAndMovement(List<PerformAnalytic> performAnalyticList, String s) {
            this.performAnalyticListX = performAnalyticList;
            ExerciseLIst = new ArrayList<>();
        }


        @NonNull
        @Override
        public ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.abr_name, viewGroup, false);
            return new ListofNamesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ListofNamesViewHolder holder, int i) {
            holder.AthleteName.setTag("NotSelected");
            holder.AthleteName.setTextColor(getResources().getColor(R.color.textColorYellow));
            holder.arrow.setPadding(5, 5, 5, 5);
            holder.arrow.setVisibility(GONE);
            holder.playVideo.setVisibility(GONE);
            holder.llayout.setBackgroundColor(Color.parseColor("#232324"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.llayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            holder.llayout.setLayoutParams(layoutParams);
            holder.UnderLineFOrAbr.setVisibility(VISIBLE);

            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));

            if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
                holder.AthleteName.setText(getExerciseList.get(i).getExerciseName());
                getExerciseListSelected.forEach(m -> {
                    if (getExerciseList.get(i).getExerciseId().equalsIgnoreCase(m.getExerciseId())) {
                        holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                        holder.AthleteName.setTag("Selected");
                    }
                });
                holder.llayout.setOnClickListener(view -> {
                    if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
                        for (int i1 = 0; i1 < getExerciseListSelected.size(); i1++) {
                            if (getExerciseListSelected.get(i1).getExerciseId().equalsIgnoreCase(getExerciseList.get(i).getExerciseId())) {
                                getExerciseListSelected.remove(i1);
                            }
                        }
                        holder.AthleteName.setTag("NotSelected");
                    } else {
                        getExerciseListSelected.add(getExerciseList.get(i));
                        holder.AthleteName.setTag("Selected");
                    }
                    selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
                    notifyDataSetChanged();
                });

                HeaderName.setText("MOVEMENT");
            } else if (ViewTypeExercise.equalsIgnoreCase("WORKOUT")) {
                HeaderName.setText("WORKOUT");
                holder.arrow.setVisibility(VISIBLE);
                holder.AthleteName.setText(performAnalyticListX.get(i).getTypeName());
                try {
//                    performAnalyticListSelected.forEach(m -> {
                    if (exercise_type_id.equalsIgnoreCase(performAnalyticListX.get(i).getTypeId())) {
                        holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                        holder.AthleteName.setTag("Selected");
                    }
//                    });
                } catch (Exception m) {

                }

                holder.runSelectTick.setOnClickListener(view -> {
                    SelectedExercise = new ArrayList<>();
                    exercise_id = "";

//                    if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
//                        for (int i1 = 0; i1 < performAnalyticListSelected.size(); i1++) {
//                            if (performAnalyticListSelected.get(i1).getTypeId().equalsIgnoreCase(performAnalyticListX.get(i).getTypeId())) {
//                                performAnalyticListSelected.remove(i1);
//                            }
//                        }
//                        holder.AthleteName.setTag("NotSelected");
//                    } else {
//                        if (performAnalyticListSelected.size() == 1) {
//                            //UtilityClass.showAlertDailog(context, "You can't select more than 3 type's.");
//                            return;
//                        }
//                        exercise_type_id = "";
//                        performAnalyticListSelected.add(performAnalyticListX.get(i));
//                        holder.AthleteName.setTag("Selected");
//
//                    }

                    exercise_type_id = "";
                    StringBuilder m = new StringBuilder();
                    performAnalyticListSelected = new ArrayList<>();
                    performAnalyticListSelected.add(performAnalyticListX.get(i));
                    for (int i1 = 0; i1 < performAnalyticListSelected.size(); i1++) {
                        exercise_type_id = m.append(performAnalyticListSelected.get(i1).getTypeId() + (performAnalyticListSelected.size() - 1 == i1 ? "" : ",")).toString();
                    }
                    holder.AthleteName.setTag("Selected");
                    CallApiFOR = "getExerciseWithTypeID";
                    selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();

                    notifyDataSetChanged();
                });

                holder.llayout.setOnClickListener(view1 -> {
                    try {
                        ExerciseLIst = new ArrayList<>(performAnalyticListX.get(i).getExercies());

                        for (int M = 0; M < ExerciseLIst.size(); M++) {
                            for (int j = M + 1; j < ExerciseLIst.size(); j++) {
                                if (ExerciseLIst.get(M).getExerciseId().equalsIgnoreCase(ExerciseLIst.get(j).getExerciseId())) {
                                    ExerciseLIst.remove(j);
                                    j--;
                                }
                            }
                        }

                        ExerciseRecyclerByType.setAdapter(new ExerciseByType(ExerciseLIst));
                        ExerciseRecyclerByType.setVisibility(VISIBLE);
                        recyclerView.setVisibility(GONE);
                        backArrowEx.setVisibility(VISIBLE);
                        AutoTransition autoTransition = new AutoTransition();

                        autoTransition.setDuration(200);
                        TransitionManager.beginDelayedTransition(DialogMainRly, autoTransition);
                        HeaderName.setText("EXERCISE");
                    } catch (Exception v) {
                    }
                });

            }

        }

        @Override
        public int getItemCount() {
            int s = 0;
            try {
                if (ViewTypeExercise.equalsIgnoreCase("WORKOUT")) {
                    s = performAnalyticListX.size();
                } else if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
                    s = ForsearchArrayExercise.size();
                }
            } catch (Exception v) {

            }
            return s;
        }

        private void filter(String s) {
            String text = s.toLowerCase().trim();

            if (text.length() == 0) {
                if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
                    ForsearchArrayExercise = new ArrayList<>(getExerciseList);
                } else {
                    performAnalyticListX = new ArrayList<>(performAnalyticList);
                }
            } else {
                if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
                    ForsearchArrayExercise = new ArrayList<>();
                    for (int i = 0; i < getExerciseList.size(); i++) {
                        if (getExerciseList.get(i).getExerciseName().toLowerCase().contains(text)) {
                            ForsearchArrayExercise.add(getExerciseList.get(i));
                        }
                    }
                } else {
                    performAnalyticListX = new ArrayList<>();
                    for (int i = 0; i < performAnalyticList.size(); i++) {
                        if (performAnalyticList.get(i).getTypeName().toLowerCase().contains(text)) {
                            performAnalyticListX.add(performAnalyticList.get(i));
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    private class CustomPagerAdapter extends PagerAdapter {

        Context mContext;

        LayoutInflater mLayoutInflater;

        //JsonArray jsonElementsName = new JsonArray();
        List<String> jsonElementsName = new ArrayList<>();

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            try {
                Iterator<String> jsonElements = jsonArrayOFEx.getJSONObject(0).getJSONArray("exercise").getJSONObject(0).getJSONArray("team_values").getJSONObject(0).keys();


                JSONArray jsonElements1 = jsonArrayOFEx.getJSONObject(0).getJSONArray("exercise").getJSONObject(0).getJSONArray("team_values").getJSONObject(0).names();

                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("weight")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }
                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("time")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }
                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("distance")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }

                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("calorie")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }
                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("height")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }

                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("lift")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }
                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("rounds")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }

                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("set")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }
                for (int i = 0; i < jsonElements1.length(); i++) {
                    if (jsonElements1.get(i).toString().toLowerCase().contains("reps")) {
                        jsonElementsName.add(jsonElements1.get(i).toString());
                        break;
                    }
                }


                Log.e(VolleyLog.TAG, "CustomPagerAdapter: " + jsonElementsName);
            } catch (JSONException e) {

            }
        }

        @Override
        public int getCount() {
            int ycount = 0;
            try {
                ycount = jsonArrayOFEx.getJSONObject(0).getJSONArray("exercise").length();
            } catch (JSONException e) {
                ycount = 0;
            }
            return ycount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.activity_graph, container, false);

            LineChart LineChartForPerfomalytics = itemView.findViewById(R.id.LineChartForPerfomalytics);

            XAxis xAxis = LineChartForPerfomalytics.getXAxis();
            xAxis.setTextColor(getResources().getColor(R.color.textColorYellow));

            LineChartForPerfomalytics.getAxisLeft().setTextColor(getResources().getColor(R.color.textColorYellow));
            LineChartForPerfomalytics.getAxisRight().setTextColor(getResources().getColor(R.color.textColorYellow));

            LineChartForPerfomalytics.getAxisRight().setStartAtZero(true);
            LineChartForPerfomalytics.getAxisLeft().setStartAtZero(true);
            xAxis.setAxisMinimum(0f);
            ArrayList<ILineDataSet> dataSetsX = new ArrayList<ILineDataSet>();
            Description description = new Description();


            try {
                description.setText(jsonArrayOFEx.getJSONObject(0).getJSONArray("exercise").getJSONObject(position).getString("exercise_name").toUpperCase());
                for (int TEAM_POSITION = 0; TEAM_POSITION < jsonArrayOFEx.length(); TEAM_POSITION++) {

                    List<String> xAxisValues = new ArrayList<>();
                    ArrayList<Entry> values = new ArrayList<>();
                    List<JSONObject> jsons = new ArrayList<JSONObject>();
                    for (int i = 0; i < jsonArrayOFEx.getJSONObject(TEAM_POSITION).getJSONArray("exercise").getJSONObject(position).getJSONArray("team_values").length(); i++) {
                        jsons.add(jsonArrayOFEx.getJSONObject(TEAM_POSITION).getJSONArray("exercise").getJSONObject(position).getJSONArray("team_values").getJSONObject(i));
                    }
                    if (!date_type.equalsIgnoreCase("4")) {
                        Collections.sort(jsons, new Comparator<JSONObject>() {
                            @Override
                            public int compare(JSONObject lhs, JSONObject rhs) {
                                String lid = null;
                                String rid = null;
                                try {
                                    lid = lhs.getString("date");
                                    rid = rhs.getString("date");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // Here you could parse string id to integer and then compare.
                                return lid.compareTo(rid);
                            }
                        });
                    }

                    Log.e(VolleyLog.TAG, "JSONArray: " + new JSONArray(jsons));

                    JSONArray jsonElements = new JSONArray(jsons);

                    for (int i1 = 0; i1 < jsonElements.length(); i1++) {
                        try {
                            values.add(new Entry(i1, Float.parseFloat(jsonElements.getJSONObject(i1).getInt(jsonElementsName.get(0)) + "")));
                        } catch (Exception v) {
                            values.add(new Entry(i1, 0f));
                        }

                        if (date_type.equalsIgnoreCase("4")) {
                            xAxisValues.add(jsonElements.getJSONObject(i1).getString("date").substring(0, 3));
                        } else {
                            xAxisValues.add(jsonElements.getJSONObject(i1).getString("date").substring(5));
                        }

                    }
                    String NAME = "";
                    try {
                        try {
                            if (jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("first_name").equalsIgnoreCase("") && jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("last_name").equalsIgnoreCase("")) {
                                String EmailID = jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("emailId");
                                NAME = EmailID.substring(0, EmailID.indexOf("@"));
                            } else {
                                NAME = jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("first_name") + " " + jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("last_name");
                            }

                        } catch (Exception x) {
                            NAME = jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("first_name") + " " + jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("last_name");
                        }
                    } catch (Exception x) {
                        NAME = jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("team_name");
                    }
                    LineDataSet set1 = new LineDataSet(values, NAME);
                    set1.setValueTextColor(Color.WHITE);
                    set1.setFillColor(Color.WHITE);
                    set1.setHighLightColor(Color.WHITE);
                    // set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                    try {
                        set1.setColor(Color.parseColor(jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("team_color")));
                    } catch (Exception v) {
                        Random rnd = new Random();
                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                        set1.setColor(color);
                    }


                    set1.setCircleColor(Color.WHITE);
                    set1.setLineWidth(2f);
                    set1.setCircleRadius(3f);
                    set1.setFillAlpha(65);
                    //set1.setFillColor(ColorTemplate.getHoloBlue());
                    set1.setHighLightColor(getResources().getColor(R.color.textColorYellow));
                    set1.setDrawCircleHole(false);
                    dataSetsX.add(set1);

                    LineChartForPerfomalytics.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));


                }
            } catch (Exception v) {
                Log.e(VolleyLog.TAG, "instantiateItem:VV " + v);
            }
            LineData dataX = new LineData(dataSetsX);
            dataX.setValueTextColor(Color.WHITE);
            dataX.setValueTextSize(9f);

            // set data
            LineChartForPerfomalytics.setData(dataX);

            LineChartForPerfomalytics.getLegend().setTextColor(Color.WHITE);

            description.setTextColor(Color.WHITE);
            LineChartForPerfomalytics.setDescription(description);
            container.addView(itemView);

            return itemView;
        }

        public JSONArray sort(JSONArray array, Comparator c) {
            List asList = new ArrayList(array.length());
            for (int i = 0; i < array.length(); i++) {
                asList.add(array.opt(i));
            }
            Collections.sort(asList, c);
            JSONArray res = new JSONArray();
            for (Object o : asList) {
                res.put(o);
            }
            return res;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }

        public class DayAxisValueFormatter extends ValueFormatter {
            private final BarLineChartBase<?> chart;

            public DayAxisValueFormatter(BarLineChartBase<?> chart) {
                this.chart = chart;
            }

            @Override
            public String getFormattedValue(float value) {
                return "";
            }
        }
    }

    private class AthleteLevelViewX extends RecyclerView.Adapter<AthleteLevelViewX.RecyclerViewHolder2> {
        int position;
        String Event;

        public AthleteLevelViewX(String Event) {
            this.Event = Event;
        }

        @Override
        public AthleteLevelViewX.RecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.three_item_layout, viewGroup, false);
            return new AthleteLevelViewX.RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final AthleteLevelViewX.RecyclerViewHolder2 Holder, int i) {

            Holder.LevelText.setVisibility(View.VISIBLE);
            Holder.LevelText.setTag("false");
            Holder.LevelImage.setVisibility(View.GONE);

            Holder.LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));

            Holder.LevelText.setText(getSchoolsList.get(i).getSchoolName());
            Holder.arrow1.setVisibility(View.GONE);

            for (int vc = 0; vc < arrayListSchool.size(); vc++) {
                if (getSchoolsList.get(i).getSchoolId().equalsIgnoreCase(arrayListSchool.get(vc))) {
                    Holder.LevelText.setTag("true");
                }
            }
            if (Holder.LevelText.getTag().toString().equalsIgnoreCase("true")) {
                Holder.rightSign.setVisibility(View.VISIBLE);
            }

            SaveEventDialog.setOnClickListener(view -> {
                StringBuilder stringBuilder = new StringBuilder();
                SchoolIDs = "";
                for (int x = 0; x < arrayListSchool.size(); x++) {
                    SchoolIDs = stringBuilder.append(arrayListSchool.get(x) + ",").toString();
                }

                if (SchoolIDs != null && SchoolIDs.length() > 0 && SchoolIDs.charAt(SchoolIDs.length() - 1) == ',') {
                    SchoolIDs = SchoolIDs.substring(0, SchoolIDs.length() - 1);
                }
                Log.e(VolleyLog.TAG, "onBindViewHolder: " + SchoolIDs);
                getTeamList = new ArrayList<>();

                whichApiCalled = "getTeams";
                WebServices webServices = new WebServices();
                webServices.getTeams(LoginJson.get(0).getUserId(),
                        SchoolIDs, context, FragmentComparativeAnalytics.this);
                dialog.dismiss();
            });

            Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Holder.rightSign.getVisibility() == VISIBLE) {
                        if (arrayListSchool.size() > 1) {
                            Holder.rightSign.setVisibility(View.GONE);
                            String GetID = getSchoolsList.get(i).getSchoolId();
                            arrayListSchool.remove(GetID);
                            Holder.LevelText.setTag("false");
                        }
                    } else {
                        Holder.rightSign.setVisibility(View.VISIBLE);
                        String GetID = getSchoolsList.get(i).getSchoolId();
                        arrayListSchool.add(GetID);
                        Holder.LevelText.setTag("true");
                    }
                    notifyDataSetChanged();
                    Log.e(VolleyLog.TAG, "onClick: " + arrayListSchool.size());
                }
            });
            backEventDialog.setOnClickListener(view -> {
                lLayoutForFIlterOption.setVisibility(View.VISIBLE);
                TypeOfFilter.setText("Select Filter");
                dialogBoxRecyclerView.setVisibility(View.GONE);
                //dialogBoxRecyclerView.setAdapter(new AthleteLevelViewX(position,"Sport"));
                SaveEventDialog.setVisibility(View.GONE);
                backEventDialog.setVisibility(View.GONE);
            });


        }

        @Override
        public int getItemCount() {
            int countofArray = 0;

            try {
                countofArray = getSchoolsList.size();
            } catch (Exception v) {
                countofArray = 0;
            }

            return countofArray;
        }

        private class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
            TextView LevelText, AtheleteLevelExerciseName, textViewExerciseName, textViewExerciseDate,
                    AtheleteLevelExerciseValues, AtheleteExerciseValueMultiple, MultiplyValueInc, MultiplyValueDcr;
            ImageView LevelImage, arrow1, rightSign;
            RelativeLayout LevelLayout, rAthleteLevelExercise, rLayoutForAthleteTraining;


            public RecyclerViewHolder2(@NonNull View itemView) {
                super(itemView);
                AtheleteLevelExerciseName = itemView.findViewById(R.id.AtheleteLevelExerciseName);
                AtheleteLevelExerciseValues = itemView.findViewById(R.id.AtheleteLevelExerciseValues);
                AtheleteExerciseValueMultiple = itemView.findViewById(R.id.AtheleteExerciseValueMultiple);
                LevelText = itemView.findViewById(R.id.LevelText);
                LevelImage = itemView.findViewById(R.id.LevelImage);
                LevelLayout = itemView.findViewById(R.id.LevelLayout);
                MultiplyValueInc = itemView.findViewById(R.id.MultiplyValueInc);
                MultiplyValueDcr = itemView.findViewById(R.id.MultiplyValueDcr);
                rAthleteLevelExercise = itemView.findViewById(R.id.rAthleteLevelExercise);
                rightSign = itemView.findViewById(R.id.rightSign);
                arrow1 = itemView.findViewById(R.id.arrow1);
                textViewExerciseDate = itemView.findViewById(R.id.textViewExerciseDate);
                textViewExerciseName = itemView.findViewById(R.id.textViewExerciseName);
                rLayoutForAthleteTraining = itemView.findViewById(R.id.rLayoutForAthleteTraining);
            }
        }
    }
}


//
//package com.org.godspeed.fragments;
//
//
//        import android.app.DatePickerDialog;
//        import android.app.Dialog;
//        import android.app.Fragment;
//        import android.content.Context;
//        import android.graphics.Color;
//        import android.graphics.drawable.ColorDrawable;
//        import android.os.Build;
//        import android.os.Bundle;
//        import android.text.Editable;
//        import android.text.TextWatcher;
//        import android.transition.AutoTransition;
//        import android.transition.Transition;
//        import android.transition.TransitionManager;
//        import android.util.Log;
//        import android.view.Gravity;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.View.OnClickListener;
//        import android.view.ViewGroup;
//        import android.view.Window;
//        import android.view.WindowManager;
//        import android.widget.DatePicker;
//        import android.widget.EditText;
//        import android.widget.ImageView;
//        import android.widget.LinearLayout;
//        import android.widget.PopupWindow;
//        import android.widget.RadioButton;
//        import android.widget.RelativeLayout;
//        import android.widget.TextView;
//
//        import androidx.annotation.NonNull;
//        import androidx.annotation.RequiresApi;
//        import androidx.core.content.ContextCompat;
//        import androidx.recyclerview.widget.DividerItemDecoration;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//        import androidx.viewpager.widget.PagerAdapter;
//        import androidx.viewpager.widget.ViewPager;
//
//        import com.github.mikephil.charting.charts.BarLineChartBase;
//        import com.github.mikephil.charting.charts.LineChart;
//        import com.github.mikephil.charting.components.AxisBase;
//        import com.github.mikephil.charting.components.Description;
//        import com.github.mikephil.charting.components.XAxis;
//        import com.github.mikephil.charting.data.Entry;
//        import com.github.mikephil.charting.data.LineData;
//        import com.github.mikephil.charting.data.LineDataSet;
//        import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
//        import com.github.mikephil.charting.formatter.ValueFormatter;
//        import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
//        import com.google.gson.Gson;
//        import com.org.godspeed.R;
//        import com.org.godspeed.allOtherClasses.LoginScreen;
//        import com.org.godspeed.response_JsonS.ExerciseNameList.Exercise;
//        import com.org.godspeed.response_JsonS.ExerciseNameList.ExerciseName;
//        import com.org.godspeed.response_JsonS.GetExerciseGraph.GetExerciseGraph;
//        import com.org.godspeed.response_JsonS.Perform_analytic.Exercy;
//        import com.org.godspeed.response_JsonS.Perform_analytic.PerformAnalytic;
//        import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
//        import com.org.godspeed.response_JsonS.getTeams.GetTeam;
//        import com.org.godspeed.response_JsonS.getTeams.TrainingProgramDetail;
//        import com.org.godspeed.utility.CustomTypeface;
//        import com.org.godspeed.utility.GlobalClass;
//        import com.org.godspeed.utility.GodSpeedInterface;
//        import com.org.godspeed.utility.UtilityClass;
//        import com.org.godspeed.utility.WebServices;
//
//        import org.json.JSONArray;
//        import org.json.JSONException;
//        import org.json.JSONObject;
//
//        import java.text.SimpleDateFormat;
//        import java.util.ArrayList;
//        import java.util.Arrays;
//        import java.util.Calendar;
//        import java.util.Collections;
//        import java.util.Comparator;
//        import java.util.Iterator;
//        import java.util.List;
//        import java.util.Random;
//
//        import static android.view.View.GONE;
//        import static android.view.View.VISIBLE;
//
//        import static com.android.volley.VolleyLog.TAG;
//        import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
//        import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
//        import static com.org.godspeed.service.SchoolDataService.LoginJson;
//        import static com.org.godspeed.allOtherClasses.LoginScreen.userTypeOf;
//        import static com.org.godspeed.service.BackgroundLocationUpdateService.GetTeamORIGINAL;
//        import static com.org.godspeed.service.SchoolDataService.getSchoolsList;
//        import static com.org.godspeed.utility.UtilityClass.ONE_DAY;
//        import static com.org.godspeed.utility.UtilityClass.ONE_WEEK;
//        import static com.org.godspeed.utility.UtilityClass.getMonthDateFirstdate;
//        import static com.org.godspeed.utility.UtilityClass.getMonthDateLastdate;
//        import static com.org.godspeed.utility.UtilityClass.getNameAthlete;
//        import static com.org.godspeed.utility.UtilityClass.getStartTimeOfToday;
//        import static com.org.godspeed.utility.UtilityClass.getStartTimeOfWeek;
//        import static com.org.godspeed.utility.UtilityClass.hide;
//        import static com.org.godspeed.utility.UtilityClass.showAlertDailog;
//
//public class FragmentComparativeAnalytics extends Fragment implements GodSpeedInterface {
//
//
//    private RelativeLayout rLayoutGraphView, rLayoutforIndividual, rLayoutforTeam, rLayoutforTeamUnderline, rLayoutforIndividualUnderline, rLayoutforMovementUnderline, rLayoutforWorkoutUnderline, rLayoutforWorkout, rLayoutForMovement;
//    private TextView textViewIndividual, textViewTeam, textViewMovement, textViewWorkout, textViewSelectTeamText, textViewExercise, textViewExerciseName, textViewMaxPower, textViewAverage, textViewPeak, textViewFrom, textViewFromDateValue, textViewTo, textViewToDateValue;
//    LineChart lineChart;
//    private TextView startDate, endDate, textViewActivation, textViewRegen, textViewFuel, textViewBuild, textViewEnergy, textViewSkills;
//    private RelativeLayout rLayoutForActivationTitle, rLayoutForSkillsTitle, rLayoutForBuildTitle, rLayoutForRegenTitle, rLayoutForEnergyTitle, rLayoutForFuelTitle;
//    private String[] teamArray = null, teamIdArray = null;
//
//    private ImageView backArrowEx;
//    private SelectedTeamsAndAthlete selected_Teams_And_Athlete;
//    private SelectedMovementAndWorkout selected_MOVEMENT_and_WORKOUT;
//
//    private LinearLayout ForPerfom;
//    private RecyclerView AllAthleteList, MoveMent_Workout_List;
//    private String whichApiCalled = "";
//    private List<SelectedAthleteDEtail> All_AthleteData = new ArrayList<>();
//    private List<SelectedAthleteDEtail> Selected_AthleteData = new ArrayList<>();
//    private LinearLayout CustomDateFilter;
//    private RecyclerViewWorkoutAndMovement recyclerViewWorkoutAndMovement;
//
//    private List<GetTeam> getTeamList = new ArrayList<>();
//    private List<GetTeam> getTeamListSelected = new ArrayList<>();
//    private List<TrainingProgramDetail> trainingProgramDetailAthlete = new ArrayList<>();
//    private List<TrainingProgramDetail> trainingProgramDetailTeam = new ArrayList<>();
//    private List<TrainingProgramDetail> SelectedtrainingProgramDetailAthlete = new ArrayList<>();
//
//    private List<TrainingProgramDetail> SelectedtrainingProgramDetailTeam = new ArrayList<>();
//    private String ViewType = "TEAMS";
//    private RecyclerViewAllTeamData Teamadapter;
//    View view;
//    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//    LinearLayout rLayoutforSelectTeam;
//    private String ViewTypeOfSearch = "";
//    private Transition transition;
//    private LinearLayout ViewForListofAllData;
//    private Calendar cal;
//    private PopupWindow popUp;
//    WebServices webServices = new WebServices();
//    private String SelectedTeamID = "";
//    private String SelectedAthleteID = "";
//    Dialog dialog;
//    RadioButton Avg, peak;
//    Boolean coach = false;
//
//    private Boolean WORKOUT = false;
//    private String date_type = "1";
//    LinearLayout lLayoutForTimeClass, lLayoutForSportsClass, lLayoutForSchoolClass;
//    RelativeLayout RReventName;
//    TextView TextViewForTimeClass, TextViewForSportsClass, TextViewForSchoolClass, TypeOfFilter;
//    LinearLayout LayoutDay, LayoutWeek, LayoutMonth, LayoutYear, LayoutCustom;
//    TextView DayText, WeekText, YearText, MonthText, CustomText;
//    List<PerformAnalytic> performAnalyticList = new ArrayList<>();
//    List<PerformAnalytic> performAnalyticListSelected = new ArrayList<>();
//    RecyclerView recyclerView;
//    RecyclerView ExerciseRecyclerByType;
//    ExerciseByType exerciseByType;
//    private Context context;
//
//
//    private View.OnClickListener pillarSelectionBackgroundClickListener = new View.OnClickListener() {
//        @RequiresApi(api = Build.VERSION_CODES.M)
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.rLayoutForActivationTitle:
//                    rLayoutForActivationTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
//                    textViewActivation.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
//                    break;
//                case R.id.rLayoutForBuildTitle:
//                    rLayoutForBuildTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
//                    textViewBuild.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
//                    break;
//                case R.id.rLayoutForFuelTitle:
//                    rLayoutForFuelTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
//                    textViewFuel.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
//                    break;
//                case R.id.rLayoutForEnergyTitle:
//                    rLayoutForEnergyTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
//                    textViewEnergy.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
//                    break;
//                case R.id.rLayoutForRegenTitle:
//                    rLayoutForRegenTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
//                    textViewRegen.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
//                    break;
//                case R.id.rLayoutForSkillsTitle:
//                    rLayoutForSkillsTitle.setBackgroundResource(R.drawable.health_profile_selected_button_icon);
//                    textViewSkills.setTextColor(context.getColor(R.color.color_black_for_health_profile_button_selected));
//                    break;
//            }
//        }
//    };
//
//    private String ViewTypeExercise = "MOVEMENT";
//    private ArrayList<Exercise> getExerciseListSelected = new ArrayList<>();
//    private List<Exercise> getExerciseList = new ArrayList<>();
//    private boolean WorkoutView = false;
//    private LinearLayout ForPerfomMove;
//    private ViewPager graphViewPager;
//    private RecyclerView dialogBoxRecyclerView, dialogBoxRecyclerData;
//    private ImageView backEventDialog, SaveEventDialog;
//    private RelativeLayout rLayoutForBottomViewSettingsOptions, rLayoutForAddTeam, rLayoutForAddAthlete, rLayoutForAddCoach, rLayoutForDeleteAthlete;
//    private TextView HeaderName, EvenText, textViewAthleteName, textViewHeightValue, textViewSMMValue, textViewKGValue, textViewBodyFatValue, TextViewAtheleteLevel;
//
//    private LinearLayout rMainDialogLayout;
//    private String from_date = "";
//    private String to_date = "";
//
//    private String SELECTED_TEAMS = "";
//    private String SELECTED_ATHLETE = "";
//    private String SELECTED_EXERCISE = "";
//    private String SchoolIDs = "";
//    private List<String> arrayListSchool = new ArrayList<>();
//    private String exercise_type_id = "";
//
//    private RelativeLayout rLayoutStartDate, rLayoutEndDate;
//
//    private String CallApiFOR = "";
//    private ArrayList<ExerciseName> exerciseNameList = new ArrayList<>();
//    private List<GetExerciseGraph> getExerciseGraphList = new ArrayList<>();
//    private CustomPagerAdapter graphViewPagerAdapter;
//    private ArrayList<Exercy> getExerciseListSelectedByType = new ArrayList<>();
//    private ArrayList<Exercy> getExerciseListMainByType = new ArrayList<>();
//    private LinearLayout DialogMainRly, rLayoutForTeamsOrIndividualHeading;
//
//    private JSONArray jsonArrayOFEx = new JSONArray();
//    private int COUNTOFOBJECTSIN_TEAM_VALUES;
//
//    private String data_avg_max = "0";
//    private String exercise_id = "";
//    private LinearLayout lLayoutForFIlterOption;
//
//    private void initializeTextView(View view) {
//        textViewIndividual = (TextView) view.findViewById(R.id.textViewIndividual);
//        textViewIndividual.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//        textViewTeam = (TextView) view.findViewById(R.id.textViewTeam);
//        textViewTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//    }
//
//    private boolean GET_Perform_analytic = false;
//    private Boolean ShowAlert = false;
//    private List<Exercy> SelectedExercise = new ArrayList<>();
//    private List<Exercy> ExerciseLIst = new ArrayList<>();
//    private OnClickListener clickListener = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            int id = v.getId();
//            int[] location = new int[2];
//            int x;
//            int y;
//            switch (id) {
//                case R.id.rLayoutforTeam:
//                    rLayoutforIndividualUnderline.setVisibility(View.GONE);
//                    rLayoutforTeamUnderline.setVisibility(View.VISIBLE);
//                    textViewIndividual.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
//                    textViewTeam.setTextColor(getResources().getColor(R.color.textColorYellow));
//                    ViewType = "TEAMS";
//                    selected_Teams_And_Athlete = new SelectedTeamsAndAthlete(context, ViewType);
//                    Teamadapter = new RecyclerViewAllTeamData(context, getTeamList, "");
//                    if (getTeamList.size() != 0) {
//                        AllAthleteList.getLocationOnScreen(location);
//                        x = location[0];
//                        y = location[1];
//                        showDialogmn(context, x, y, "AthleteTeam");
//                    }
//                    break;
//                case R.id.rLayoutforIndividual:
//                    rLayoutforIndividualUnderline.setVisibility(View.VISIBLE);
//                    ViewType = "ATHLETE";
//                    selected_Teams_And_Athlete = new SelectedTeamsAndAthlete(context, ViewType);
//                    rLayoutforTeamUnderline.setVisibility(View.GONE);
//                    textViewIndividual.setTextColor(getResources().getColor(R.color.textColorYellow));
//                    textViewTeam.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
//                    Teamadapter = new RecyclerViewAllTeamData(context, All_AthleteData);
//                    if (All_AthleteData.size() == 0) {
//                        whichApiCalled = "athlete";
//                        webServices.getAthlete(LoginScreen.userId, "", "", context, FragmentComparativeAnalytics.this);
//                    } else {
//                        AllAthleteList.getLocationOnScreen(location);
//                        x = location[0];
//                        y = location[1];
//                        showDialogmn(context, x, y, "AthleteTeam");
//                    }
//
//                    break;
//                case R.id.rLayoutForMovement:
//                    rLayoutforMovementUnderline.setVisibility(VISIBLE);
//                    rLayoutforWorkoutUnderline.setVisibility(View.GONE);
//                    textViewMovement.setTextColor(getResources().getColor(R.color.textColorYellow));
//                    textViewWorkout.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
//                    ViewTypeExercise = "MOVEMENT";
//                    selected_MOVEMENT_and_WORKOUT = new SelectedMovementAndWorkout(context, "MOVEMENT");
//
//                    if (exerciseNameList.size() == 0) {
//                        whichApiCalled = "GetExerciseNewSecond";
//                        webServices.GetExerciseNewSecond(context, FragmentComparativeAnalytics.this);
//                    } else {
//                        MoveMent_Workout_List.getLocationOnScreen(location);
//                        x = location[0];
//                        y = location[1];
//                        showDialogmn(context, x, y, "MovementWorkout");
//                    }
//                    break;
//                case R.id.rLayoutforWorkout:
//                    rLayoutforWorkoutUnderline.setVisibility(View.VISIBLE);
//                    ViewTypeExercise = "WORKOUT";
//                    rLayoutforMovementUnderline.setVisibility(View.GONE);
//                    textViewWorkout.setTextColor(getResources().getColor(R.color.textColorYellow));
//                    textViewMovement.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
//                    selected_MOVEMENT_and_WORKOUT = new SelectedMovementAndWorkout(context, "WORKOUT");
//
//
//                    Log.e(VolleyLog.TAG, "onClick: " + performAnalyticList.size());
//                    if (GET_Perform_analytic) {
//                        ShowAlert = true;
//                        whichApiCalled = "GetPerfomaltics";
//                        performAnalyticList = new ArrayList<>();
//                        //performAnalyticListSelected = new ArrayList<>();
//                        exercise_type_id = "";
//                        CallApiFOR = "";
//                        webServices.Perform_analytic(date_type, SELECTED_TEAMS, SELECTED_ATHLETE, from_date, to_date, exercise_type_id, "", data_avg_max, context, FragmentComparativeAnalytics.this);
//                        GET_Perform_analytic = false;
//                    } else {
//                        MoveMent_Workout_List.getLocationOnScreen(location);
//                        x = location[0];
//                        y = location[1];
//                        showDialogmn(context, x, y, "MovementWorkout");
//                    }
//
//
//                    break;
//            }
//
//            AllAthleteList.setAdapter(selected_Teams_And_Athlete);
//            MoveMent_Workout_List.setAdapter(selected_MOVEMENT_and_WORKOUT);
//        }
//    };
//
//    private void CallApiFor() {
//        CallApiFOR = "";
//        webServices.Perform_analytic(date_type, SELECTED_TEAMS, SELECTED_ATHLETE, from_date, to_date, exercise_type_id, exercise_id, data_avg_max, context, FragmentComparativeAnalytics.this);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        view = inflater.inflate(R.layout.fragment_comparative_analytics, container, false);
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        context = getActivity();
//        // graphClass = new GraphClass();
//        cal = Calendar.getInstance();
//
//
//        AllAthleteList = (RecyclerView) view.findViewById(R.id.AllAthleteList);
//        MoveMent_Workout_List = (RecyclerView) view.findViewById(R.id.MoveMent_Workout_List);
//        ForPerfom = (LinearLayout) view.findViewById(R.id.ForPerfom);
//        ForPerfom.setVisibility(GONE);
//        ForPerfomMove = (LinearLayout) view.findViewById(R.id.ForPerfomMove);
//        ForPerfomMove.setVisibility(GONE);
//        CustomDateFilter = (LinearLayout) view.findViewById(R.id.CustomDateFilter);
//        rLayoutForTeamsOrIndividualHeading = (LinearLayout) view.findViewById(R.id.rLayoutForTeamsOrIndividualHeading);
//
//        rLayoutStartDate = (RelativeLayout) view.findViewById(R.id.rLayoutStartDate);
//        rLayoutEndDate = (RelativeLayout) view.findViewById(R.id.rLayoutEndDate);
//
//        exerciseByType = new ExerciseByType(ExerciseLIst);
//
//        ForPerfom.setOnClickListener(view1 -> {
//            int[] location = new int[2];
//            AllAthleteList.getLocationOnScreen(location);
//            int x = location[0];
//            int y = location[1];
//            showDialogmn(context, x, y, "AthleteTeam");
//        });
//        int usertype = GlobalClass.ivar1;
//        if (usertype == 1) {
//            coach = true;
//        } else {
//            rLayoutForTeamsOrIndividualHeading.setVisibility(GONE);
//            SELECTED_ATHLETE = LoginJson.get(0).getUserId();
//            whichApiCalled = "GetPerfomaltics";
//            exercise_type_id = "";
//            CallApiFor();
//        }
//
//
//        ForPerfomMove.setOnClickListener(view1 -> {
//            int[] location = new int[2];
//            MoveMent_Workout_List.getLocationOnScreen(location);
//            int x = location[0];
//            int y = location[1];
//            showDialogmn(context, x, y, "MovementWorkout");
//        });
//
//        rLayoutStartDate.setOnClickListener(view1 -> {
//            DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
//                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                    Calendar newDate = Calendar.getInstance();
//                    newDate.set(year, monthOfYear, dayOfMonth);
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                    from_date = df.format(newDate.getTime());
//                    startDate.setText(from_date);
//                }
//            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
//            StartTime.show();
//        });
//
//        rLayoutEndDate.setOnClickListener(view1 -> {
//            DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
//                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                    Calendar newDate = Calendar.getInstance();
//                    newDate.set(year, monthOfYear, dayOfMonth);
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                    to_date = df.format(newDate.getTime());
//                    endDate.setText(to_date);
//                }
//            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
//            StartTime.show();
//        });
//
//        LayoutDay = (LinearLayout) view.findViewById(R.id.LayoutDay);
//        LayoutWeek = (LinearLayout) view.findViewById(R.id.LayoutWeek);
//        LayoutMonth = (LinearLayout) view.findViewById(R.id.LayoutMonth);
//        LayoutYear = (LinearLayout) view.findViewById(R.id.LayoutYear);
//        LayoutCustom = (LinearLayout) view.findViewById(R.id.LayoutCustom);
//
//        DayText = (TextView) view.findViewById(R.id.DayText);
//        WeekText = (TextView) view.findViewById(R.id.WeekText);
//        MonthText = (TextView) view.findViewById(R.id.MonthText);
//        CustomText = (TextView) view.findViewById(R.id.CustomText);
//        YearText = (TextView) view.findViewById(R.id.YearText);
//
//        Avg = view.findViewById(R.id.Avg);
//
//        peak = view.findViewById(R.id.peak);
//
//        Avg.setOnClickListener(view1 -> {
//            Avg.setTextColor(getResources().getColor(R.color.textColorYellow));
//            peak.setTextColor(getResources().getColor(R.color.color_white));
//            Avg.setChecked(true);
//            peak.setChecked(false);
//            data_avg_max = "0";
//            if (!exercise_type_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            } else if (!exercise_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            }
//        });
//
//        peak.setOnClickListener(view1 -> {
//            Avg.setTextColor(getResources().getColor(R.color.color_white));
//            peak.setTextColor(getResources().getColor(R.color.textColorYellow));
//            Avg.setChecked(false);
//            peak.setChecked(true);
//            data_avg_max = "1";
//            if (!exercise_type_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            } else if (!exercise_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            }
//        });
//
//        LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));
//
//        DayText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
//        DayText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//        WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//        MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//        YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//        CustomText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//        LayoutDay.setOnClickListener(view1 -> {
//            int[] location = new int[2];
//            LayoutDay.getLocationOnScreen(location);
//            int x = location[0];
//            int y = location[1];
//
//            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));
//            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//
//            GET_Perform_analytic = true;
//
//            CustomText.setTextColor(getResources().getColor(R.color.textColorYellow));
//            DayText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
//
//            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));
//            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            DayText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            CustomText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//
//
//
//            long startTime = getStartTimeOfToday();
//            long endTime = startTime + ONE_DAY;
//            CustomDateFilter.setVisibility(GONE);
//            date_type = "1";
//            from_date = "";
//            to_date = "";
//
//            performAnalyticList = new ArrayList<>();
//            if (!exercise_type_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            } else if (!exercise_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            }
//
//        });
//
//        CustomDateFilter.setVisibility(GONE);
//
//        LayoutWeek.setOnClickListener(view1 -> {
//            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutWeek.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
//            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            CustomText.setTextColor(getResources().getColor(R.color.textColorYellow));
//            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            WeekText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
//
//            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            long startTime = getStartTimeOfWeek();
//            GET_Perform_analytic = true;
//            CustomDateFilter.setVisibility(GONE);
//            long endTime = startTime + ONE_WEEK;
//            date_type = "2";
//            from_date = "";
//            to_date = "";
//
//
//            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            WeekText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            CustomText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//            performAnalyticList = new ArrayList<>();
//
//            if (!exercise_type_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            } else if (!exercise_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            }
//
//
//        });
//
//        LayoutMonth.setOnClickListener(view1 -> {
//            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutMonth.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
//            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//
//            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            MonthText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//            CustomDateFilter.setVisibility(GONE);
//            GET_Perform_analytic = true;
//            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));
//            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));
//            CustomText.setTextColor(getResources().getColor(R.color.textColorYellow));
//            MonthText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
//            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));
//            long startTime = getMonthDateFirstdate();
//
//            long endTime = getMonthDateLastdate();
//            date_type = "3";
//
//            from_date = "";
//
//            to_date = "";
//
//            performAnalyticList = new ArrayList<>();
//
//            if (!exercise_type_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            } else if (!exercise_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            }
//
//        });
//
//        LayoutYear.setOnClickListener(view1 -> {
//            int[] location = new int[2];
//            LayoutYear.getLocationOnScreen(location);
//            int x = location[0];
//            int y = location[1];
//            GET_Perform_analytic = true;
//            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.color.textColorYellow));
//            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//
//            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            YearText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//            CustomText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//
//            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//
//            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));
//            CustomText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            YearText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
//
//            performAnalyticList = new ArrayList<>();
//            CustomDateFilter.setVisibility(GONE);
//            date_type = "4";
//            from_date = "";
//            to_date = "";
//            if (!exercise_type_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            } else if (!exercise_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            }
//        });
//
//        LayoutCustom.setOnClickListener(view1 -> {
//            int[] location = new int[2];
//            LayoutYear.getLocationOnScreen(location);
//            int x = location[0];
//            int y = location[1];
//            GET_Perform_analytic = true;
//            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
//            LayoutCustom.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_right_yellow));
//
//            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//            CustomText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//
//            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//
//            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//
//            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            CustomText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
//
//            CustomDateFilter.setVisibility(VISIBLE);
//            date_type = "5";
//
//            performAnalyticList = new ArrayList<>();
//
//            if (from_date.equalsIgnoreCase("") && to_date.equalsIgnoreCase("")) {
//                from_date = df.format(cal.getTime());
//                to_date = df.format(cal.getTime());
//            }
//            if (!exercise_type_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            } else if (!exercise_id.equalsIgnoreCase("")) {
//                CallApiFOR = "getExerciseWithTypeID";
//                if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                    whichApiCalled = "GetPerfomaltics";
//                } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                    whichApiCalled = "GetExercise";
//                }
//                CallApiFor();
//            }
//        });
//
//        graphViewPager = (ViewPager) view.findViewById(R.id.graphViewPager);
//
//        if (coach) {
//            whichApiCalled = "getTeams";
//            if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
//                getTeamList = new ArrayList<>();
//                arrayListSchool = new ArrayList<>();
//                SchoolIDs = "";
//                imageViewMenuFilter.setVisibility(VISIBLE);
//                try {
//                    SchoolIDs = getSchoolsList.get(0).getSchoolId();
//                    arrayListSchool.add(getSchoolsList.get(0).getSchoolId());
//                } catch (Exception v) {
//                }
//                WebServices webServices = new WebServices();
//                webServices.getTeams(LoginScreen.userId, SchoolIDs, context, FragmentComparativeAnalytics.this);
//            } else {
//                if (GetTeamORIGINAL.size() != 0) {
//                    getTeamList = new ArrayList<>(GetTeamORIGINAL);
//                } else {
//                    webServices.getTeams(LoginScreen.userId, SchoolIDs, context, FragmentComparativeAnalytics.this);
//                }
//            }
//        }
//
//
//        rLayoutforSelectTeam = (LinearLayout) view.findViewById(R.id.rLayoutforSelectTeam);
//
//        AllAthleteList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//
//        MoveMent_Workout_List.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//
//        selected_Teams_And_Athlete = new SelectedTeamsAndAthlete(context, ViewType);
//
//        selected_MOVEMENT_and_WORKOUT = new SelectedMovementAndWorkout(context, "MOVEMENT");
//
//        AllAthleteList.setAdapter(selected_Teams_And_Athlete);
//        MoveMent_Workout_List.setAdapter(selected_MOVEMENT_and_WORKOUT);
//
//
//        ViewForListofAllData = (LinearLayout) view.findViewById(R.id.ViewForListofAllData);
//        startDate = (TextView) view.findViewById(R.id.startDate);
//        endDate = (TextView) view.findViewById(R.id.endDate);
//
//
//        textViewWorkout = (TextView) view.findViewById(R.id.textViewWorkout);
//        textViewMovement = (TextView) view.findViewById(R.id.textViewMovement);
//
//        startDate.setText(df.format(cal.getTime()));
//
//        endDate.setText(df.format(cal.getTime()));
//
//        rLayoutforIndividual = (RelativeLayout) view.findViewById(R.id.rLayoutforIndividual);
//        rLayoutForMovement = (RelativeLayout) view.findViewById(R.id.rLayoutForMovement);
//        rLayoutforWorkout = (RelativeLayout) view.findViewById(R.id.rLayoutforWorkout);
//        rLayoutforTeam = (RelativeLayout) view.findViewById(R.id.rLayoutforTeam);
//
//        rLayoutforIndividual.setOnClickListener(clickListener);
//        rLayoutForMovement.setOnClickListener(clickListener);
//        rLayoutforWorkout.setOnClickListener(clickListener);
//        rLayoutforTeam.setOnClickListener(clickListener);
//
//        rLayoutforTeamUnderline = (RelativeLayout) view.findViewById(R.id.rLayoutforTeamUnderline);
//        rLayoutforIndividualUnderline = (RelativeLayout) view.findViewById(R.id.rLayoutforIndividualUnderline);
//        rLayoutforWorkoutUnderline = (RelativeLayout) view.findViewById(R.id.rLayoutforWorkoutUnderline);
//        rLayoutforMovementUnderline = (RelativeLayout) view.findViewById(R.id.rLayoutforMovementUnderline);
//
//        rLayoutforIndividualUnderline.setVisibility(View.GONE);
//        rLayoutforWorkoutUnderline.setVisibility(View.GONE);
//
//        initializeTextView(view);
//
//        return view;
//    }
//
//    public void showDialogmn(Context context, int x, int y, String movementWorkout) {
//        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View mView = mInflater.inflate(R.layout.empty_array, null, false);
//        popUp = new PopupWindow(mView, dpToPx(350), dpToPx(250), false);
//        popUp.setTouchable(true);
//        popUp.setFocusable(true);
//        popUp.setOutsideTouchable(true);
//        popUp.setAnimationStyle(R.style.Animation);
//        popUp.showAtLocation(mView, Gravity.NO_GRAVITY, x, y + 30);
//
//        EditText calc_txt_Prise = mView.findViewById(R.id.calc_txt_Prise);
//        ImageView calc_clear_txt_Prise = mView.findViewById(R.id.calc_clear_txt_Prise);
//        recyclerView = mView.findViewById(R.id.custompopwindow);
//        ExerciseRecyclerByType = mView.findViewById(R.id.ExerciseRecyclerByType);
//        backArrowEx = mView.findViewById(R.id.backArrowEx);
//        DialogMainRly = mView.findViewById(R.id.DialogMainRly);
//
//        HeaderName = mView.findViewById(R.id.HeaderName);
//
//        backArrowEx.setOnClickListener(view1 -> {
//            ExerciseRecyclerByType.setVisibility(GONE);
//            backArrowEx.setVisibility(GONE);
//            recyclerView.setVisibility(VISIBLE);
//
//            HeaderName.setText("WORKOUT");
//            AutoTransition autoTransition = new AutoTransition();
//
//            autoTransition.setDuration(200);
//            TransitionManager.beginDelayedTransition(DialogMainRly, autoTransition);
//        });
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        linearLayoutManager.setAutoMeasureEnabled(false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        linearLayoutManager1.setAutoMeasureEnabled(false);
//        ExerciseRecyclerByType.setLayoutManager(linearLayoutManager1);
//
//        Boolean performAnalyticListB = false;
//
//        if (movementWorkout.equalsIgnoreCase("AthleteTeam")) {
//            if (ViewType.equalsIgnoreCase("TEAMS")) {
//                Teamadapter = new RecyclerViewAllTeamData(context, getTeamList, "");
//            } else if (ViewType.equalsIgnoreCase("ATHLETE")) {
//                Teamadapter = new RecyclerViewAllTeamData(context, All_AthleteData);
//            }
//            recyclerView.setAdapter(null);
//            recyclerView.setAdapter(Teamadapter);
//        } else {
//            if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
//                recyclerViewWorkoutAndMovement = new RecyclerViewWorkoutAndMovement(getExerciseList);
//            } else {
//                recyclerViewWorkoutAndMovement = new RecyclerViewWorkoutAndMovement(performAnalyticList, "");
//                if (performAnalyticListB && performAnalyticList.size() == 0) {
//                    performAnalyticListB = true;
//                }
//            }
//            recyclerView.setAdapter(null);
//            recyclerView.setAdapter(recyclerViewWorkoutAndMovement);
//        }
//        if (performAnalyticListB && performAnalyticList.size() == 0) {
//            UtilityClass.showAlertDailog(context, "You don't have assigned training programs.");
//            return;
//        }
//
//        selected_Teams_And_Athlete.notifyDataSetChanged();
//        selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
//
//
//        calc_clear_txt_Prise.setOnClickListener(view1 -> calc_txt_Prise.setText(""));
//
//        calc_txt_Prise.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String text = s.toString();
//                try {
//                    Teamadapter.filter(text);
//                    recyclerViewWorkoutAndMovement.filter(text);
//                    exerciseByType.filter(text);
//                } catch (Exception m) {
//
//                }
//                if (text.length() > 0) {
//                    calc_clear_txt_Prise.setVisibility(VISIBLE);
//                } else {
//                    calc_clear_txt_Prise.setVisibility(GONE);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//
//        popUp.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                if (!CallApiFOR.equalsIgnoreCase("")) {
//                    if (CallApiFOR.equalsIgnoreCase("TeamSide")) {
//                        if (performAnalyticList.size() == 0) {
//                            whichApiCalled = "GetPerfomaltics";
//                            performAnalyticList = new ArrayList<>();
//                            performAnalyticListSelected = new ArrayList<>();
//                            exercise_type_id = "";
//                            CallApiFor();
//                        }
//                    } else if (CallApiFOR.equalsIgnoreCase("getExerciseWithTypeID")) {
//                        whichApiCalled = "GetExercise";
//                        CallApiFor();
//                    }
//                }
//            }
//        });
//    }
//
//
//    @Override
//    public void ApiResponse(String result) {
//        if (result != null && result.trim().length() > 0) {
//            parseRequiredData(result);
//        }
//    }
//
//    private void parseRequiredData(String result) {
//        Log.e(VolleyLog.TAG, "parseRequiredData: " + whichApiCalled);
//        String responseMessage = "";
//        try {
//            JSONObject jsonObj = new JSONObject(result);
//
//
//            String respCode = jsonObj.getString("responseCode");
//
//            WebServices.responseCode = Integer.parseInt(respCode);
//
//            responseMessage = jsonObj.getString("responseMessage");
//
//            Log.e("**********", "mymessge" + responseMessage);
//
//
//            Log.e(VolleyLog.TAG, "result: " + result);
//
//            if (WebServices.responseCode == 200) {
//                JSONArray jsonDataArray = jsonObj
//                        .getJSONArray("data");
//                if (jsonDataArray != null && jsonDataArray.length() > 0) {
//                    Gson gson = new Gson();
//                    if (whichApiCalled.equalsIgnoreCase("athlete")) {
//                        All_AthleteData = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), SelectedAthleteDEtail[].class)));
//                        UtilityClass.hide();
//
//                        int[] location = new int[2];
//                        AllAthleteList.getLocationOnScreen(location);
//                        int x = location[0];
//                        int y = location[1];
//                        showDialogmn(context, x, y, "AthleteTeam");
//                        performAnalyticList = new ArrayList<>();
//                        getTeamListSelected = new ArrayList<>();
//
//                        performAnalyticListSelected = new ArrayList<>();
//
//                        jsonArrayOFEx = new JSONArray();
//                        graphViewPager.setAdapter(null);
//                        graphViewPagerAdapter = new CustomPagerAdapter(context);
//                        graphViewPager.setAdapter(graphViewPagerAdapter);
//
//                        selected_Teams_And_Athlete.notifyDataSetChanged();
//                        selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
//                        recyclerViewWorkoutAndMovement.notifyDataSetChanged();
//
//                    } else if (whichApiCalled.equalsIgnoreCase("getTeams")) {
//                        hide();
//                        getTeamList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetTeam[].class)));
//                        performAnalyticList = new ArrayList<>();
//                        getTeamListSelected = new ArrayList<>();
//
//                        performAnalyticListSelected = new ArrayList<>();
//
//                        jsonArrayOFEx = new JSONArray();
//                        graphViewPager.setAdapter(null);
//                        graphViewPagerAdapter = new CustomPagerAdapter(context);
//                        graphViewPager.setAdapter(graphViewPagerAdapter);
//
//                        selected_Teams_And_Athlete.notifyDataSetChanged();
//                        selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
//                        recyclerViewWorkoutAndMovement.notifyDataSetChanged();
//
//                    } else if (whichApiCalled.equalsIgnoreCase("GetExerciseNewSecond")) {
//                        exerciseNameList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), ExerciseName[].class)));
//                        exerciseNameList.get(0).getExercise().forEach(m -> {
//                            getExerciseList.add(m);
//                        });
//
//                        hide();
//                        int[] location = new int[2];
//                        MoveMent_Workout_List.getLocationOnScreen(location);
//                        int x = location[0];
//                        int y = location[1];
//                        showDialogmn(context, x, y, "MovementWorkout");
//                    } else if (whichApiCalled.equalsIgnoreCase("GetPerfomaltics")) {
//                        Log.e(VolleyLog.TAG, "GetPerfomaltics: " + jsonDataArray);
//                        //jsonArrayOFEx = new JSONArray();
//                        performAnalyticList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), PerformAnalytic[].class)));
//                        //performAnalyticListSelected = new ArrayList<>();
//                        selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
//
//                        graphViewPager.setAdapter(null);
//
//                        graphViewPagerAdapter = new CustomPagerAdapter(context);
//
//                        graphViewPager.setAdapter(graphViewPagerAdapter);
//
//
//                        if (ShowAlert) {
//                            int[] location = new int[2];
//                            MoveMent_Workout_List.getLocationOnScreen(location);
//                            int x = location[0];
//                            int y = location[1];
//                            showDialogmn(context, x, y, "MovementWorkout");
//                            ShowAlert = false;
//                        }
//
//                        hide();
//                    } else if (whichApiCalled.equalsIgnoreCase("GetExercise")) {
//
//                        hide();
//                        getExerciseGraphList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetExerciseGraph[].class)));
//
//                        jsonArrayOFEx = jsonDataArray;
//
//                        graphViewPager.setAdapter(null);
//
//                        graphViewPagerAdapter = new CustomPagerAdapter(context);
//
//                        graphViewPager.setAdapter(graphViewPagerAdapter);
//                    }
//                }
//            } else {
//                hide();
//                if (whichApiCalled.equalsIgnoreCase("GetPerfomaltics")) {
//                    UtilityClass.showAlertDailog(context, responseMessage);
//                    performAnalyticList = new ArrayList<>();
//                    performAnalyticListSelected = new ArrayList<>();
//                    selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
//                    recyclerViewWorkoutAndMovement.notifyDataSetChanged();
//                    jsonArrayOFEx = new JSONArray();
//                    graphViewPager.setAdapter(null);
//
//                    graphViewPagerAdapter = new CustomPagerAdapter(context);
//
//                    graphViewPager.setAdapter(graphViewPagerAdapter);
//                }
//
//            }
//        } catch (Exception v) {
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        AllAthleteList.setAdapter(null);
//        MoveMent_Workout_List.setAdapter(null);
//        super.onDestroyView();
//    }
//
//    public void toggle() {
//        showDialogBox(0, 0, "Select School");
//    }
//
//    private class ListofNamesViewHolder extends RecyclerView.ViewHolder {
//        LinearLayout RlLayout;
//        RelativeLayout UnderLineFOrAbr;
//        TextView AthleteName;
//        RecyclerView abrDetailRecyclerView;
//        RelativeLayout ExcersiseSubmitButtonX, RSelectTickX, rlayoutDose;
//        LinearLayout rLayoutforLBandREPS;
//        RelativeLayout llayout, runSelectTick;
//        ImageView arrow, unSelectTick;
//        ImageView showDetail, HideDetail, playVideo;
//
//        public ListofNamesViewHolder(@NonNull View itemView) {
//            super(itemView);
//            AthleteName = itemView.findViewById(R.id.DoseName);
//            playVideo = itemView.findViewById(R.id.playVideo);
//            unSelectTick = itemView.findViewById(R.id.unSelectTick);
//            runSelectTick = itemView.findViewById(R.id.runSelectTick);
//            ExcersiseSubmitButtonX = itemView.findViewById(R.id.ExcersiseSubmitButtonX);
//            rLayoutforLBandREPS = itemView.findViewById(R.id.rLayoutforLBandREPS);
//            llayout = itemView.findViewById(R.id.llayout);
//            arrow = itemView.findViewById(R.id.arrow);
//            abrDetailRecyclerView = itemView.findViewById(R.id.abrDetailRecyclerView);
//            UnderLineFOrAbr = itemView.findViewById(R.id.UnderLineFOrAbr);
//            RlLayout = itemView.findViewById(R.id.RlLayout);
//            abrDetailRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//        }
//    }
//
//    private class RecyclerViewAllTeamData extends RecyclerView.Adapter<ListofNamesViewHolder> {
//        private List<ExerciseName> ForsearchArrayExerciseNames;
//        private Context context;
//        private List<GetTeam> ForsearchArrayTeam;
//        private List<SelectedAthleteDEtail> ForsearchArrayAthlete;
//
//        public RecyclerViewAllTeamData(Context context, List<GetTeam> All_AthleteData, String m) {
//            this.context = context;
//            this.ForsearchArrayTeam = new ArrayList<>(All_AthleteData);
//        }
//
//        public RecyclerViewAllTeamData(Context context, List<SelectedAthleteDEtail> selectedAthleteDEtails) {
//            this.context = context;
//            this.ForsearchArrayAthlete = new ArrayList<>(selectedAthleteDEtails);
//        }
//
//
//        @NonNull
//        @Override
//        public ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//            View view = layoutInflater.inflate(R.layout.abr_name, viewGroup, false);
//            return new ListofNamesViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull final ListofNamesViewHolder holder, final int i) {
//            holder.AthleteName.setTag("NotSelected");
//
//            holder.AthleteName.setTextColor(getResources().getColor(R.color.textColorYellow));
//            holder.arrow.setPadding(5, 5, 5, 5);
//            holder.arrow.setVisibility(GONE);
//            holder.playVideo.setVisibility(GONE);
//            holder.llayout.setBackgroundColor(Color.parseColor("#232324"));
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.llayout.getLayoutParams();
//            layoutParams.setMargins(0, 0, 0, 0);
//            holder.llayout.setLayoutParams(layoutParams);
//            holder.UnderLineFOrAbr.setVisibility(VISIBLE);
//
//            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
//
//            if (ViewType.equalsIgnoreCase("TEAMS")) {
//                holder.AthleteName.setText(ForsearchArrayTeam.get(i).getTeamName());
//                getTeamListSelected.forEach(m -> {
//                    if (ForsearchArrayTeam.get(i).getTeamId().equalsIgnoreCase(m.getTeamId())) {
//                        holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
//                        holder.AthleteName.setTag("Selected");
//                    }
//                });
//                holder.llayout.setOnClickListener(view -> {
//                    if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
//                        for (int i1 = 0; i1 < getTeamListSelected.size(); i1++) {
//                            if (getTeamListSelected.get(i1).getTeamId().equalsIgnoreCase(ForsearchArrayTeam.get(i).getTeamId())) {
//                                getTeamListSelected.remove(i1);
//                            }
//                        }
//                        holder.AthleteName.setTag("NotSelected");
//                    } else {
//                        if (getTeamListSelected.size() == 3) {
//                            showAlertDailog(context, "You can't select more than three teams.");
//                            return;
//                        }
//                        getTeamListSelected.add(ForsearchArrayTeam.get(i));
//
//                        holder.AthleteName.setTag("Selected");
//
//                    }
////                    SELECTED_TEAMS = "";
////                    StringBuilder m = new StringBuilder();
////                    for (int i1 = 0; i1 < getTeamListSelected.size(); i1++) {
////                        SELECTED_TEAMS = m.append(getTeamListSelected.get(i1).getTeamId() + (i1 != getTeamListSelected.size() - 1 ? "," : "")).toString();
////                    }
////                    getExerciseGraphList = new ArrayList<>();
////                    Log.e(VolleyLog.TAG, "SELECTED_TEAMS: " + SELECTED_TEAMS);
////                    CallApiFOR = "TeamSide";
//                    selected_Teams_And_Athlete.notifyDataSetChanged();
//                    notifyDataSetChanged();
//                });
//                HeaderName.setText("TEAMS");
//            } else if (ViewType.equalsIgnoreCase("ATHLETE")) {
//                String TextX = "";
////                if (ForsearchArrayAthlete.get(i).getFirstName().equalsIgnoreCase("") && ForsearchArrayAthlete.get(i).getLastName().equalsIgnoreCase("")) {
////                   try{
////                       TextX = ForsearchArrayAthlete.get(i).getEmailId().substring(0, ForsearchArrayAthlete.get(i).getEmailId().indexOf("@") );
////                   }catch (Exception m){
////                       Log.e(VolleyLog.TAG, "ATHLETE: "+i+"   "+ForsearchArrayAthlete.get(i).getUserId());
////                   }
////
////
////
////
////                } else {
////                    TextX = ForsearchArrayAthlete.get(i).getLastName() + " " + ForsearchArrayAthlete.get(i).getFirstName();
////                }
//
//                HeaderName.setText("ATHLETE");
//                holder.AthleteName.setText(getNameAthlete(ForsearchArrayAthlete.get(i).getFirstName(), ForsearchArrayAthlete.get(i).getLastName(), ForsearchArrayAthlete.get(i).getEmailId()));
//
//                Selected_AthleteData.forEach(m -> {
//                    if (ForsearchArrayAthlete.get(i).getUserId().equalsIgnoreCase(m.getUserId())) {
//                        holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
//                        holder.AthleteName.setTag("Selected");
//                    }
//                });
//
//                holder.llayout.setOnClickListener(view -> {
//                    if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
//                        for (int i1 = 0; i1 < Selected_AthleteData.size(); i1++) {
//                            if (Selected_AthleteData.get(i1).getUserId().equalsIgnoreCase(ForsearchArrayAthlete.get(i).getUserId())) {
//                                Selected_AthleteData.remove(i1);
//                            }
//                        }
//                        holder.AthleteName.setTag("NotSelected");
//                    } else {
//                        if (Selected_AthleteData.size() == 5) {
//                            showAlertDailog(context, "You can't select more than 5 athlete's..");
//                            return;
//                        }
//                        Selected_AthleteData.add(ForsearchArrayAthlete.get(i));
//                        holder.AthleteName.setTag("Selected");
//
//                    }
////                    SELECTED_ATHLETE  = "";
////                    StringBuilder m = new StringBuilder();
////                    for (int i1 = 0; i1 < Selected_AthleteData.size(); i1++) {
////                        SELECTED_ATHLETE = m.append(Selected_AthleteData.get(i1).getUserId() + (i1 != Selected_AthleteData.size() - 1 ? "," : "")).toString();
////                    }
////                    getExerciseGraphList = new ArrayList<>();
////                    Log.e(VolleyLog.TAG, "SELECTED_ATHLETE: " + SELECTED_ATHLETE);
////                    CallApiFOR = "TeamSide";
//                    selected_Teams_And_Athlete.notifyDataSetChanged();
//                    notifyDataSetChanged();
//                });
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            int s = 0;
//            try {
//                if (ViewType.equalsIgnoreCase("TEAMS")) {
//                    s = ForsearchArrayTeam.size();
//                } else if (ViewType.equalsIgnoreCase("ATHLETE")) {
//                    s = ForsearchArrayAthlete.size();
//                }
//            } catch (Exception v) {
//
//            }
//
//
//            return s;
//
//        }
//
//        private void filter(String s) {
//            String text = s.toString().toLowerCase().trim();
//            if (text.length() == 0) {
//                if (ViewType.equalsIgnoreCase("TEAMS")) {
//                    ForsearchArrayTeam = new ArrayList<>(getTeamList);
//                } else {
//                    ForsearchArrayAthlete = new ArrayList<>(All_AthleteData);
//                }
//
//            } else {
//                if (ViewType.equalsIgnoreCase("TEAMS")) {
//                    ForsearchArrayTeam = new ArrayList<>();
//                    for (int i = 0; i < getTeamList.size(); i++) {
//                        if (getTeamList.get(i).getTeamName().toLowerCase().contains(text)) {
//                            ForsearchArrayTeam.add(getTeamList.get(i));
//                        }
//                    }
//                } else {
//                    ForsearchArrayAthlete = new ArrayList<>();
//
//                    for (int i = 0; i < All_AthleteData.size(); i++) {
//                        String TextX = "";
//                        TextX = UtilityClass.getNameAthlete(All_AthleteData.get(i).getFirstName(), All_AthleteData.get(i).getLastName(), All_AthleteData.get(i).getEmailId());
//
//                        if (TextX.toLowerCase().contains(text)) {
//                            ForsearchArrayAthlete.add(All_AthleteData.get(i));
//                        }
//                    }
//                }
//            }
//            notifyDataSetChanged();
//        }
//    }
//
//    public void showDialogBox(int x, int y, String athlete_level) {
//        Log.e(VolleyLog.TAG, "showDialogBox: " + athlete_level);
//        dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.cutom_dialogbox_athlete_screen);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.getWindow().setDimAmount(0);
//        //imageViewAppIconForAnimation = dialog.findViewById(R.id.imageViewAppIconForAnimation);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        RelativeLayout mainRly = dialog.findViewById(R.id.mainRly);
//        mainRly.invalidate();
//        EvenText = (TextView) dialog.findViewById(R.id.EventName);
//        EvenText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        backEventDialog = dialog.findViewById(R.id.backEventDialog);
//        SaveEventDialog = dialog.findViewById(R.id.SaveEventDialog);
//
//
//        lLayoutForTimeClass = (LinearLayout) dialog.findViewById(R.id.lLayoutForTimeClass);
//        lLayoutForSportsClass = (LinearLayout) dialog.findViewById(R.id.lLayoutForSportsClass);
//        lLayoutForSchoolClass = (LinearLayout) dialog.findViewById(R.id.lLayoutForSchoolClass);
//
//
//        lLayoutForFIlterOption = (LinearLayout) dialog.findViewById(R.id.lLayoutForFIlterOption);
//
//
//        RReventName = (RelativeLayout) dialog.findViewById(R.id.RReventName);
//
//        rMainDialogLayout = dialog.findViewById(R.id.rMainDialogLayout);
//        TextViewForTimeClass = (TextView) dialog.findViewById(R.id.TextViewForTimeClass);
//        TextViewForSportsClass = (TextView) dialog.findViewById(R.id.TextViewForSportsClass);
//        TextViewForSchoolClass = (TextView) dialog.findViewById(R.id.TextViewForSchoolClass);
//        TypeOfFilter = (TextView) dialog.findViewById(R.id.TypeOfFilter);
//
//        backEventDialog.setVisibility(View.INVISIBLE);
//        lLayoutForSchoolClass.setOnClickListener(view -> {
//            lLayoutForFIlterOption.setVisibility(View.GONE);
//            TypeOfFilter.setVisibility(View.VISIBLE);
//            TypeOfFilter.setText("Select School");
//            dialogBoxRecyclerView.setVisibility(View.VISIBLE);
//            dialogBoxRecyclerView.setAdapter(new AthleteLevelViewX("School"));
//            backEventDialog.setVisibility(View.VISIBLE);
//        });
//
//        SaveEventDialog.setVisibility(View.VISIBLE);
//
//
//        TextViewForSportsClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//        TextViewForSchoolClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//        TextViewForTimeClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//        dialogBoxRecyclerView = dialog.findViewById(R.id.dialogBoxRecyclerView);
//
//
//        //rMainDialogLayout.setLayoutParams(rMainDialogLayout.getHeight() = 320);
//
//
//        dialogBoxRecyclerView.setHasFixedSize(true);
//        dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//        DividerItemDecoration divider = new
//                DividerItemDecoration(dialogBoxRecyclerView.getContext(),
//                DividerItemDecoration.VERTICAL);
////        divider.setDrawable(
////                ContextCompat.getDrawable(context, R.drawable.line_divider)
////        );
////        if (showDialogOf.equalsIgnoreCase("Classes")) {
//        divider.setDrawable(
//                ContextCompat.getDrawable(context, R.drawable.divider_dark_light)
//        );
//        //}
//        dialogBoxRecyclerView.addItemDecoration(divider);
//
////        if (!showDialogOf.equalsIgnoreCase("LEVEL")) {
////            ViewGroup.LayoutParams params = mainRly.getLayoutParams();
////            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
////            params.height = (int) ((320 * displayMetrics.density) + 0.5);
////        }
//
//
//        dialogBoxRecyclerView.setAdapter(new AthleteLevelViewX(""));
//
//
////        if (showDialogOf.equalsIgnoreCase("LEVEL")) {
////            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
////            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
////            wmlp.x = x - 50;
////            wmlp.y = y;
////        }
//
//        dialog.show();
//
//    }
//
//    private class ExerciseByType extends RecyclerView.Adapter<ListofNamesViewHolder> {
//        private List<Exercy> ForsearchArrayExercise;
//
//        public ExerciseByType(List<Exercy> exerciseNames) {
//            this.ForsearchArrayExercise = new ArrayList<>(exerciseNames);
//        }
//
//
//        @NonNull
//        @Override
//        public ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//            View view = layoutInflater.inflate(R.layout.abr_name, viewGroup, false);
//            return new ListofNamesViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ListofNamesViewHolder holder, int i) {
//            holder.AthleteName.setTag("NotSelected");
//
//            holder.AthleteName.setTextColor(getResources().getColor(R.color.textColorYellow));
//            holder.arrow.setPadding(5, 5, 5, 5);
//            holder.arrow.setVisibility(GONE);
//            holder.playVideo.setVisibility(GONE);
//            holder.llayout.setBackgroundColor(Color.parseColor("#232324"));
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.llayout.getLayoutParams();
//            layoutParams.setMargins(0, 0, 0, 0);
//            holder.llayout.setLayoutParams(layoutParams);
//            holder.UnderLineFOrAbr.setVisibility(VISIBLE);
//
//            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
//
//            holder.AthleteName.setText(ForsearchArrayExercise.get(i).getExerciseName());
//
//            SelectedExercise.forEach(m -> {
//                if (ForsearchArrayExercise.get(i).getExerciseId().equalsIgnoreCase(m.getExerciseId())) {
//                    holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
//                    holder.AthleteName.setTag("Selected");
//                }
//            });
//            holder.llayout.setOnClickListener(view -> {
//                performAnalyticListSelected = new ArrayList<>();
//                if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
//                    for (int i1 = 0; i1 < SelectedExercise.size(); i1++) {
//                        if (SelectedExercise.get(i1).getExerciseId().equalsIgnoreCase(ForsearchArrayExercise.get(i).getExerciseId())) {
//                            SelectedExercise.remove(i1);
//                        }
//                    }
//                    holder.AthleteName.setTag("NotSelected");
//                } else {
//                    SelectedExercise.add(ForsearchArrayExercise.get(i));
//                    holder.AthleteName.setTag("Selected");
//                }
//
//                exercise_type_id = "";
//                exercise_id = "";
//                StringBuilder m = new StringBuilder();
//                for (int i1 = 0; i1 < SelectedExercise.size(); i1++) {
//                    exercise_id = m.append(SelectedExercise.get(i1).getExerciseId() + (SelectedExercise.size() - 1 == i1 ? "" : ",")).toString();
//                }
//                CallApiFOR = "getExerciseWithTypeID";
//                selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
//                notifyDataSetChanged();
//
//            });
//
//
//        }
//
//        @Override
//        public int getItemCount() {
//            int s = 0;
//            try {
//
//                s = ForsearchArrayExercise.size();
//
//            } catch (Exception v) {
//
//            }
//            return s;
//
//        }
//
//        private void filter(String s) {
//            String text = s.toString().toLowerCase().trim();
//
//            if (text.length() == 0) {
//                ForsearchArrayExercise = new ArrayList<>(ExerciseLIst);
//            } else {
//                ForsearchArrayExercise = new ArrayList<>();
//                for (int i = 0; i < ExerciseLIst.size(); i++) {
//                    if (ExerciseLIst.get(i).getExerciseName().toLowerCase().contains(text)) {
//                        ForsearchArrayExercise.add(ExerciseLIst.get(i));
//                    }
//                }
//
//            }
//            notifyDataSetChanged();
//        }
//    }
//
//    private class SelectedMovementAndWorkout extends RecyclerView.Adapter<TeamDataHolder> {
//        Context context;
//        String viewType = "";
//
//        public SelectedMovementAndWorkout(Context context, String viewType) {
//            this.context = context;
//            this.viewType = viewType;
//        }
//
//        @Override
//        public TeamDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//            View view = layoutInflater.inflate(R.layout.team_name_list, viewGroup, false);
//            return new TeamDataHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull TeamDataHolder holder, int i) {
//            holder.TypeArrow.setVisibility(GONE);
//            if (viewType.equalsIgnoreCase("MOVEMENT")) {
//
//                holder.teamName.setText(getExerciseListSelected.get(i).getExerciseName());
//                holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
//                holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
//                holder.teamName.setTextColor(getResources().getColor(R.color.color_white));
//                holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//                holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (viewType.equalsIgnoreCase("MOVEMENT")) {
//
//                        } else if (viewType.equalsIgnoreCase("WORKOUT")) {
//
//                        }
//                    }
//                });
//
//            } else if (viewType.equalsIgnoreCase("WORKOUT")) {
//                if (SelectedExercise.size() != 0) {
//                    holder.teamName.setText(SelectedExercise.get(i).getExerciseName());
//                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
//                    holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
//                    holder.teamName.setTextColor(getResources().getColor(R.color.color_white));
//                    holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//                } else {
//                    holder.teamName.setText(performAnalyticListSelected.get(i).getTypeName());
//                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
//                    holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
//                    holder.teamName.setTextColor(getResources().getColor(R.color.color_white));
//                    holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//                    holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if (viewType.equalsIgnoreCase("MOVEMENT")) {
//
//                            } else if (viewType.equalsIgnoreCase("WORKOUT")) {
//
//                            }
//                        }
//                    });
//                }
//            }
//            holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
//            holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
//            holder.teamName.setTextColor(getResources().getColor(R.color.color_white));
//
//            holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//            holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (viewType.equalsIgnoreCase("MOVEMENT")) {
//
//                    } else if (viewType.equalsIgnoreCase("WORKOUT")) {
//
//                    }
//                }
//            });
//
//        }
//
//
//        @Override
//        public int getItemCount() {
//            int count = 0;
//            if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
//                count = getExerciseListSelected.size();
//            } else {
//                if (SelectedExercise.size() != 0) {
//                    count = SelectedExercise.size();
//                } else {
//                    count = performAnalyticListSelected.size();
//                }
//
//            }
//            return count;
//        }
//
//
//    }
//
//    private class SelectedTeamsAndAthlete extends RecyclerView.Adapter<TeamDataHolder> {
//        Context context;
//        String viewType = "";
//
//        public SelectedTeamsAndAthlete(Context context, String viewType) {
//            this.context = context;
//            this.viewType = viewType;
//            if (viewType.equalsIgnoreCase("TEAMS")) {
//                trainingProgramDetailTeam = new ArrayList<>();
//            } else {
//                trainingProgramDetailAthlete = new ArrayList<>();
//            }
//        }
//
//        @Override
//        public TeamDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//            View view = layoutInflater.inflate(R.layout.team_name_list, viewGroup, false);
//            return new TeamDataHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull TeamDataHolder holder, int i) {
//            holder.TypeArrow.setVisibility(GONE);
//
//            if (viewType.equalsIgnoreCase("TEAMS")) {
//
////                if (getTeamListSelected.get(i).getTeamId().equalsIgnoreCase("-1")) {
////                    holder.teamName.setTextSize(20f);
////                }
//
//                holder.teamName.setText(getTeamListSelected.get(i).getTeamName());
//
//
//                if (getTeamListSelected.get(i).getTrainingProgramDetail() != null) {
//                    getTeamListSelected.get(i).getTrainingProgramDetail().forEach(t -> {
//                                trainingProgramDetailTeam.add(new TrainingProgramDetail(t.getId(), t.getTeamId(), t.getTrainingProgramId(),
//                                        t.getAssignprogramDate(), t.getStartDate(),
//                                        t.getAthleteId(), t.getUserId(), t.getProgramName(), t.getCreateFolderParentId(), t.getTrainingProgramStatus(), t.getGymAccountId(), t.getAssignProgramId()
//                                ));
//                            }
//                    );
//                    for (int x = 0; x < trainingProgramDetailTeam.size(); x++) {
//                        for (int j = x + 1; j < trainingProgramDetailTeam.size(); j++) {
//                            if (trainingProgramDetailTeam.get(x).getAssignProgramId().equalsIgnoreCase(trainingProgramDetailTeam.get(j).getAssignProgramId())) {
//                                trainingProgramDetailTeam.remove(j);
//                                j--;
//                            }
//                        }
//                    }
//                    SELECTED_ATHLETE = "";
//                    SELECTED_TEAMS = "";
//                    StringBuilder m = new StringBuilder();
//                    for (int i1 = 0; i1 < getTeamListSelected.size(); i1++) {
//                        SELECTED_TEAMS = m.append(getTeamListSelected.get(i1).getTeamId() + (i1 != getTeamListSelected.size() - 1 ? "," : "")).toString();
//                    }
//                    getExerciseGraphList = new ArrayList<>();
//                    Log.e(VolleyLog.TAG, "SELECTED_TEAMS: " + SELECTED_TEAMS);
//                    CallApiFOR = "TeamSide";
//                }
//
//
//            } else if (viewType.equalsIgnoreCase("ATHLETE")) {
////                if (Selected_AthleteData.get(i).getTeamId().equalsIgnoreCase("-1")) {
////                    holder.teamName.setTextSize(20f);
////                }
//                if (Selected_AthleteData.get(i).getFirstName().equalsIgnoreCase("") && Selected_AthleteData.get(i).getLastName().equalsIgnoreCase("")) {
//                    try {
//                        holder.teamName.setText(Selected_AthleteData.get(i).getEmailId().substring(0, Selected_AthleteData.get(i).getEmailId().indexOf("@")));
//                    } catch (Exception x) {
//
//                    }
//                } else {
//                    holder.teamName.setText(Selected_AthleteData.get(i).getLastName() + " " + Selected_AthleteData.get(i).getFirstName());
//                }
//                holder.teamName.setText(UtilityClass.getNameAthlete(Selected_AthleteData.get(i).getFirstName(), Selected_AthleteData.get(i).getLastName(), Selected_AthleteData.get(i).getEmailId()));
//                if (Selected_AthleteData.get(i).getAssingProgramDetails() != null)
//                    Selected_AthleteData.get(i).getAssingProgramDetails().forEach(t -> {
//                                trainingProgramDetailAthlete.add(new TrainingProgramDetail(t.getId(),
//                                        t.getTeamId(),
//                                        t.getTrainingProgramId(),
//                                        t.getAssignprogramDate(),
//                                        t.getStartDate(),
//                                        t.getAthleteId(), t.getUserId(), t.getProgramName(), t.getCreateFolderParentId(), t.getTrainingProgramStatus(), t.getGymAccountId(), t.getAssignProgramId()
//                                ));
//                            }
//                    );
//                for (int x = 0; x < trainingProgramDetailAthlete.size(); x++) {
//                    for (int j = x + 1; j < trainingProgramDetailAthlete.size(); j++) {
//                        if (trainingProgramDetailAthlete.get(x).getAssignProgramId().equalsIgnoreCase(trainingProgramDetailAthlete.get(j).getAssignProgramId())) {
//                            trainingProgramDetailAthlete.remove(j);
//                            j--;
//                        }
//                    }
//                }
//                SELECTED_ATHLETE = "";
//                SELECTED_TEAMS = "";
//                StringBuilder m = new StringBuilder();
//                for (int i1 = 0; i1 < Selected_AthleteData.size(); i1++) {
//                    SELECTED_ATHLETE = m.append(Selected_AthleteData.get(i1).getUserId() + (i1 != Selected_AthleteData.size() - 1 ? "," : "")).toString();
//                }
//                getExerciseGraphList = new ArrayList<>();
//                Log.e(VolleyLog.TAG, "SELECTED_ATHLETE: " + SELECTED_ATHLETE);
//                CallApiFOR = "TeamSide";
//            }
//
//            holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
//            holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
//            holder.teamName.setTextColor(getResources().getColor(R.color.color_white));
//
//            holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//            holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (viewType.equalsIgnoreCase("TEAMS")) {
//
//                        SelectedTeamID = getTeamListSelected.get(i).getTeamId();
//                        notifyDataSetChanged();
//
//                    } else if (viewType.equalsIgnoreCase("ATHLETE")) {
//
//                        SelectedAthleteID = Selected_AthleteData.get(i).getUserId();
//                        notifyDataSetChanged();
//
//                    }
//                }
//            });
//
//        }
//
//
//        @Override
//        public int getItemCount() {
//            int count = 0;
//            if (viewType.equalsIgnoreCase("TEAMS")) {
//                count = getTeamListSelected.size();
//            } else if (viewType.equalsIgnoreCase("ATHLETE")) {
//                count = Selected_AthleteData.size();
//            }
//            return count;
//        }
//
//
//    }
//
//    private class TeamDataHolder extends RecyclerView.ViewHolder {
//        TextView teamName;
//        RelativeLayout rLayoutofTeam;
//        ImageView TypeArrow;
//        LinearLayout ForTeam, ForPerfom;
//
//        public TeamDataHolder(@NonNull View itemView) {
//            super(itemView);
//            teamName = itemView.findViewById(R.id.PerfromName);
//            ForTeam = itemView.findViewById(R.id.ForTeam);
//            ForTeam.setVisibility(GONE);
//            ForPerfom = itemView.findViewById(R.id.ForPerfom);
//            ForPerfom.setVisibility(VISIBLE);
//
//            rLayoutofTeam = itemView.findViewById(R.id.rLayoutofPerfrom);
//            TypeArrow = (ImageView) itemView.findViewById(R.id.TypeArrow);
//        }
//    }
//
//    private class RecyclerViewWorkoutAndMovement extends RecyclerView.Adapter<ListofNamesViewHolder> {
//        private List<Exercise> ForsearchArrayExercise;
//        private List<PerformAnalytic> performAnalyticListX;
//
//        public RecyclerViewWorkoutAndMovement(List<Exercise> exerciseNames) {
//            this.ForsearchArrayExercise = new ArrayList<>(exerciseNames);
//            ExerciseLIst = new ArrayList<>();
//        }
//
////        public RecyclerViewWorkoutAndMovement(List<TrainingProgramDetail> trainingProgramDetailTeamX, List<TrainingProgramDetail> trainingProgramDetailAthleteX) {
////            this.ForSearchtrainingProgramDetailAthlete = trainingProgramDetailAthleteX;
////            this.ForSearchtrainingProgramDetailTeam = trainingProgramDetailTeamX;
////        }
//
//        public RecyclerViewWorkoutAndMovement(List<PerformAnalytic> performAnalyticList, String s) {
//            this.performAnalyticListX = performAnalyticList;
//            ExerciseLIst = new ArrayList<>();
//        }
//
//
//        @NonNull
//        @Override
//        public ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//            View view = layoutInflater.inflate(R.layout.abr_name, viewGroup, false);
//            return new ListofNamesViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ListofNamesViewHolder holder, int i) {
//            holder.AthleteName.setTag("NotSelected");
//            holder.AthleteName.setTextColor(getResources().getColor(R.color.textColorYellow));
//            holder.arrow.setPadding(5, 5, 5, 5);
//            holder.arrow.setVisibility(GONE);
//            holder.playVideo.setVisibility(GONE);
//            holder.llayout.setBackgroundColor(Color.parseColor("#232324"));
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.llayout.getLayoutParams();
//            layoutParams.setMargins(0, 0, 0, 0);
//            holder.llayout.setLayoutParams(layoutParams);
//            holder.UnderLineFOrAbr.setVisibility(VISIBLE);
//
//            holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
//
//            if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
//                holder.AthleteName.setText(getExerciseList.get(i).getExerciseName());
//                getExerciseListSelected.forEach(m -> {
//                    if (getExerciseList.get(i).getExerciseId().equalsIgnoreCase(m.getExerciseId())) {
//                        holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
//                        holder.AthleteName.setTag("Selected");
//                    }
//                });
//                holder.llayout.setOnClickListener(view -> {
//                    if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
//                        for (int i1 = 0; i1 < getExerciseListSelected.size(); i1++) {
//                            if (getExerciseListSelected.get(i1).getExerciseId().equalsIgnoreCase(getExerciseList.get(i).getExerciseId())) {
//                                getExerciseListSelected.remove(i1);
//                            }
//                        }
//                        holder.AthleteName.setTag("NotSelected");
//                    } else {
//                        getExerciseListSelected.add(getExerciseList.get(i));
//                        holder.AthleteName.setTag("Selected");
//                    }
//                    selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
//                    notifyDataSetChanged();
//                });
//
//                HeaderName.setText("MOVEMENT");
//            } else if (ViewTypeExercise.equalsIgnoreCase("WORKOUT")) {
//                HeaderName.setText("WORKOUT");
//                holder.arrow.setVisibility(VISIBLE);
//                holder.AthleteName.setText(performAnalyticListX.get(i).getTypeName());
//                try {
////                    performAnalyticListSelected.forEach(m -> {
//                    if (exercise_type_id.equalsIgnoreCase(performAnalyticListX.get(i).getTypeId())) {
//                        holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
//                        holder.AthleteName.setTag("Selected");
//                    }
////                    });
//                } catch (Exception m) {
//
//                }
//
//                holder.runSelectTick.setOnClickListener(view -> {
//                    SelectedExercise = new ArrayList<>();
//                    exercise_id = "";
//
////                    if (holder.AthleteName.getTag().toString().equalsIgnoreCase("Selected")) {
////                        for (int i1 = 0; i1 < performAnalyticListSelected.size(); i1++) {
////                            if (performAnalyticListSelected.get(i1).getTypeId().equalsIgnoreCase(performAnalyticListX.get(i).getTypeId())) {
////                                performAnalyticListSelected.remove(i1);
////                            }
////                        }
////                        holder.AthleteName.setTag("NotSelected");
////                    } else {
////                        if (performAnalyticListSelected.size() == 1) {
////                            //UtilityClass.showAlertDailog(context, "You can't select more than 3 type's.");
////                            return;
////                        }
////                        exercise_type_id = "";
////                        performAnalyticListSelected.add(performAnalyticListX.get(i));
////                        holder.AthleteName.setTag("Selected");
////
////                    }
//
//                    exercise_type_id = "";
//                    StringBuilder m = new StringBuilder();
//                    performAnalyticListSelected = new ArrayList<>();
//                    performAnalyticListSelected.add(performAnalyticListX.get(i));
//                    for (int i1 = 0; i1 < performAnalyticListSelected.size(); i1++) {
//                        exercise_type_id = m.append(performAnalyticListSelected.get(i1).getTypeId() + (performAnalyticListSelected.size() - 1 == i1 ? "" : ",")).toString();
//                    }
//                    holder.AthleteName.setTag("Selected");
//                    CallApiFOR = "getExerciseWithTypeID";
//                    selected_MOVEMENT_and_WORKOUT.notifyDataSetChanged();
//
//                    notifyDataSetChanged();
//                });
//
//                holder.llayout.setOnClickListener(view1 -> {
//                    try {
//                        ExerciseLIst = new ArrayList<>(performAnalyticListX.get(i).getExercies());
//
//                        for (int M = 0; M < ExerciseLIst.size(); M++) {
//                            for (int j = M + 1; j < ExerciseLIst.size(); j++) {
//                                if (ExerciseLIst.get(M).getExerciseId().equalsIgnoreCase(ExerciseLIst.get(j).getExerciseId())) {
//                                    ExerciseLIst.remove(j);
//                                    j--;
//                                }
//                            }
//                        }
//
//                        ExerciseRecyclerByType.setAdapter(new ExerciseByType(ExerciseLIst));
//                        ExerciseRecyclerByType.setVisibility(VISIBLE);
//                        recyclerView.setVisibility(GONE);
//                        backArrowEx.setVisibility(VISIBLE);
//                        AutoTransition autoTransition = new AutoTransition();
//
//                        autoTransition.setDuration(200);
//                        TransitionManager.beginDelayedTransition(DialogMainRly, autoTransition);
//                        HeaderName.setText("EXERCISE");
//                    } catch (Exception v) {
//                    }
//                });
//
//            }
//
//        }
//
//        @Override
//        public int getItemCount() {
//            int s = 0;
//            try {
//                if (ViewTypeExercise.equalsIgnoreCase("WORKOUT")) {
//                    s = performAnalyticListX.size();
//                } else if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
//                    s = ForsearchArrayExercise.size();
//                }
//            } catch (Exception v) {
//
//            }
//            return s;
//        }
//
//        private void filter(String s) {
//            String text = s.toString().toLowerCase().trim();
//
//            if (text.length() == 0) {
//                if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
//                    ForsearchArrayExercise = new ArrayList<>(getExerciseList);
//                } else {
//                    performAnalyticListX = new ArrayList<>(performAnalyticList);
//                }
//            } else {
//                if (ViewTypeExercise.equalsIgnoreCase("MOVEMENT")) {
//                    ForsearchArrayExercise = new ArrayList<>();
//                    for (int i = 0; i < getExerciseList.size(); i++) {
//                        if (getExerciseList.get(i).getExerciseName().toLowerCase().contains(text)) {
//                            ForsearchArrayExercise.add(getExerciseList.get(i));
//                        }
//                    }
//                } else {
//                    performAnalyticListX = new ArrayList<>();
//                    for (int i = 0; i < performAnalyticList.size(); i++) {
//                        if (performAnalyticList.get(i).getTypeName().toLowerCase().contains(text)) {
//                            performAnalyticListX.add(performAnalyticList.get(i));
//                        }
//                    }
//                }
//            }
//            notifyDataSetChanged();
//        }
//    }
//
//    private class CustomPagerAdapter extends PagerAdapter {
//
//        Context mContext;
//
//        LayoutInflater mLayoutInflater;
//
//        //JsonArray jsonElementsName = new JsonArray();
//        List<String> jsonElementsName = new ArrayList<>();
//
//        public CustomPagerAdapter(Context context) {
//            mContext = context;
//            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            try {
//                Iterator<String> jsonElements = jsonArrayOFEx.getJSONObject(0).getJSONArray("exercise").getJSONObject(0).getJSONArray("team_values").getJSONObject(0).keys();
//
//
//                JSONArray jsonElements1 = jsonArrayOFEx.getJSONObject(0).getJSONArray("exercise").getJSONObject(0).getJSONArray("team_values").getJSONObject(0).names();
//
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("weight")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("time")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("distance")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("calorie")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("height")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("lift")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("rounds")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("set")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//                for (int i = 0; i < jsonElements1.length(); i++) {
//                    if (jsonElements1.get(i).toString().toLowerCase().contains("reps")) {
//                        jsonElementsName.add(jsonElements1.get(i).toString());
//                        break;
//                    }
//                }
//
//
//                Log.e(VolleyLog.TAG, "CustomPagerAdapter: " + jsonElementsName);
//            } catch (JSONException e) {
//
//            }
//        }
//
//        @Override
//        public int getCount() {
//            int ycount = 0;
//            try {
//                ycount = jsonArrayOFEx.getJSONObject(0).getJSONArray("exercise").length();
//            } catch (JSONException e) {
//                ycount = 0;
//            }
//            return ycount;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == ((LinearLayout) object);
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            View itemView = mLayoutInflater.inflate(R.layout.activity_graph, container, false);
//
//            LineChart LineChartForPerfomalytics = itemView.findViewById(R.id.LineChartForPerfomalytics);
//
//            XAxis xAxis = LineChartForPerfomalytics.getXAxis();
//            xAxis.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            LineChartForPerfomalytics.getAxisLeft().setTextColor(getResources().getColor(R.color.textColorYellow));
//            LineChartForPerfomalytics.getAxisRight().setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            LineChartForPerfomalytics.getAxisRight().setStartAtZero(true);
//            LineChartForPerfomalytics.getAxisLeft().setStartAtZero(true);
//            xAxis.setAxisMinimum(0f);
//            ArrayList<ILineDataSet> dataSetsX = new ArrayList<ILineDataSet>();
//            Description description = new Description();
//
//
//            try {
//                description.setText(jsonArrayOFEx.getJSONObject(0).getJSONArray("exercise").getJSONObject(position).getString("exercise_name").toUpperCase());
//                for (int TEAM_POSITION = 0; TEAM_POSITION < jsonArrayOFEx.length(); TEAM_POSITION++) {
//
//                    List<String> xAxisValues = new ArrayList<>();
//                    ArrayList<Entry> values = new ArrayList<>();
//                    List<JSONObject> jsons = new ArrayList<JSONObject>();
//                    for (int i = 0; i < jsonArrayOFEx.getJSONObject(TEAM_POSITION).getJSONArray("exercise").getJSONObject(position).getJSONArray("team_values").length(); i++) {
//                        jsons.add(jsonArrayOFEx.getJSONObject(TEAM_POSITION).getJSONArray("exercise").getJSONObject(position).getJSONArray("team_values").getJSONObject(i));
//                    }
//                    if (!date_type.equalsIgnoreCase("4")) {
//                        Collections.sort(jsons, new Comparator<JSONObject>() {
//                            @Override
//                            public int compare(JSONObject lhs, JSONObject rhs) {
//                                String lid = null;
//                                String rid = null;
//                                try {
//                                    lid = lhs.getString("date");
//                                    rid = rhs.getString("date");
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                // Here you could parse string id to integer and then compare.
//                                return lid.compareTo(rid);
//                            }
//                        });
//                    }
//
//                    Log.e(VolleyLog.TAG, "JSONArray: " + new JSONArray(jsons));
//
//                    JSONArray jsonElements = new JSONArray(jsons);
//
//                    for (int i1 = 0; i1 < jsonElements.length(); i1++) {
//                        try {
//                            values.add(new Entry(i1, Float.parseFloat(jsonElements.getJSONObject(i1).getInt(jsonElementsName.get(0)) + "")));
//                        } catch (Exception v) {
//                            values.add(new Entry(i1, 0f));
//                        }
//
//                        if (date_type.equalsIgnoreCase("4")) {
//                            xAxisValues.add(jsonElements.getJSONObject(i1).getString("date").substring(0, 3));
//                        } else {
//                            xAxisValues.add(jsonElements.getJSONObject(i1).getString("date").substring(5));
//                        }
//
//                    }
//                    String NAME = "";
//                    try {
//                        try {
//                            if (jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("first_name").equalsIgnoreCase("") && jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("last_name").equalsIgnoreCase("")) {
//                                String EmailID = jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("emailId");
//                                NAME = EmailID.substring(0, EmailID.indexOf("@"));
//                            } else {
//                                NAME = jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("first_name") + " " + jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("last_name");
//                            }
//
//                        } catch (Exception x) {
//                            NAME = jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("first_name") + " " + jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("last_name");
//                        }
//                    } catch (Exception x) {
//                        NAME = jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("team_name");
//                    }
//                    LineDataSet set1 = new LineDataSet(values, NAME);
//                    set1.setValueTextColor(Color.WHITE);
//                    set1.setFillColor(Color.WHITE);
//                    set1.setHighLightColor(Color.WHITE);
//                    // set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//                    try {
//                        set1.setColor(Color.parseColor(jsonArrayOFEx.getJSONObject(TEAM_POSITION).getString("team_color")));
//                    } catch (Exception v) {
//                        Random rnd = new Random();
//                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//                        set1.setColor(color);
//                    }
//
//
//                    set1.setCircleColor(Color.WHITE);
//                    set1.setLineWidth(2f);
//                    set1.setCircleRadius(3f);
//                    set1.setFillAlpha(65);
//                    //set1.setFillColor(ColorTemplate.getHoloBlue());
//                    set1.setHighLightColor(getResources().getColor(R.color.textColorYellow));
//                    set1.setDrawCircleHole(false);
//                    dataSetsX.add(set1);
//
//                    LineChartForPerfomalytics.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
//
//
//                }
//            } catch (Exception v) {
//                Log.e(VolleyLog.TAG, "instantiateItem:VV " + v);
//            }
//            LineData dataX = new LineData(dataSetsX);
//            dataX.setValueTextColor(Color.WHITE);
//            dataX.setValueTextSize(9f);
//
//            // set data
//            LineChartForPerfomalytics.setData(dataX);
//
//            LineChartForPerfomalytics.getLegend().setTextColor(Color.WHITE);
//
//            description.setTextColor(Color.WHITE);
//            LineChartForPerfomalytics.setDescription(description);
//            container.addView(itemView);
//
//            return itemView;
//        }
//
//        public JSONArray sort(JSONArray array, Comparator c) {
//            List asList = new ArrayList(array.length());
//            for (int i = 0; i < array.length(); i++) {
//                asList.add(array.opt(i));
//            }
//            Collections.sort(asList, c);
//            JSONArray res = new JSONArray();
//            for (Object o : asList) {
//                res.put(o);
//            }
//            return res;
//        }
//
//        public class DayAxisValueFormatter extends ValueFormatter {
//            private final BarLineChartBase<?> chart;
//
//            public DayAxisValueFormatter(BarLineChartBase<?> chart) {
//                this.chart = chart;
//            }
//
//            @Override
//            public String getFormattedValue(float value) {
//                return "";
//            }
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((LinearLayout) object);
//        }
//    }
//
//    private class AthleteLevelViewX extends RecyclerView.Adapter<AthleteLevelViewX.RecyclerViewHolder2> {
//        int position;
//        String Event;
//
//        public AthleteLevelViewX(String Event) {
//            this.Event = Event;
//        }
//
//        @Override
//        public AthleteLevelViewX.RecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
//            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//            View view = layoutInflater.inflate(R.layout.three_item_layout, viewGroup, false);
//            return new AthleteLevelViewX.RecyclerViewHolder2(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final AthleteLevelViewX.RecyclerViewHolder2 Holder, int i) {
//
//            Holder.LevelText.setVisibility(View.VISIBLE);
//            Holder.LevelText.setTag("false");
//            Holder.LevelImage.setVisibility(View.GONE);
//
//            Holder.LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));
//
//            Holder.LevelText.setText(getSchoolsList.get(i).getSchoolName());
//            Holder.arrow1.setVisibility(View.GONE);
//
//            for (int vc = 0; vc < arrayListSchool.size(); vc++) {
//                if (getSchoolsList.get(i).getSchoolId().equalsIgnoreCase(arrayListSchool.get(vc))) {
//                    Holder.LevelText.setTag("true");
//                }
//            }
//            if (Holder.LevelText.getTag().toString().equalsIgnoreCase("true")) {
//                Holder.rightSign.setVisibility(View.VISIBLE);
//            }
//
//            SaveEventDialog.setOnClickListener(view -> {
//                StringBuilder stringBuilder = new StringBuilder();
//                SchoolIDs = "";
//                for (int x = 0; x < arrayListSchool.size(); x++) {
//                    SchoolIDs = stringBuilder.append(arrayListSchool.get(x).toString() + ",").toString();
//                }
//
//                if (SchoolIDs != null && SchoolIDs.length() > 0 && SchoolIDs.charAt(SchoolIDs.length() - 1) == ',') {
//                    SchoolIDs = SchoolIDs.substring(0, SchoolIDs.length() - 1);
//                }
//                Log.e(VolleyLog.TAG, "onBindViewHolder: " + SchoolIDs);
//                getTeamList = new ArrayList<>();
//
//                whichApiCalled = "getTeams";
//                WebServices webServices = new WebServices();
//                webServices.getTeams(LoginJson.get(0).getUserId(),
//                        SchoolIDs, context, FragmentComparativeAnalytics.this);
//                dialog.dismiss();
//            });
//
//            Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (Holder.rightSign.getVisibility() == VISIBLE) {
//                        if (arrayListSchool.size() > 1) {
//                            Holder.rightSign.setVisibility(View.GONE);
//                            String GetID = getSchoolsList.get(i).getSchoolId();
//                            arrayListSchool.remove(GetID);
//                            Holder.LevelText.setTag("false");
//                        }
//                    } else {
//                        Holder.rightSign.setVisibility(View.VISIBLE);
//                        String GetID = getSchoolsList.get(i).getSchoolId();
//                        arrayListSchool.add(GetID);
//                        Holder.LevelText.setTag("true");
//                    }
//                    notifyDataSetChanged();
//                    Log.e(VolleyLog.TAG, "onClick: " + arrayListSchool.size());
//                }
//            });
//            backEventDialog.setOnClickListener(view -> {
//                lLayoutForFIlterOption.setVisibility(View.VISIBLE);
//                TypeOfFilter.setText("Select Filter");
//                dialogBoxRecyclerView.setVisibility(View.GONE);
//                //dialogBoxRecyclerView.setAdapter(new AthleteLevelViewX(position,"Sport"));
//                SaveEventDialog.setVisibility(View.GONE);
//                backEventDialog.setVisibility(View.GONE);
//            });
//
//
//        }
//
//        @Override
//        public int getItemCount() {
//            int countofArray = 0;
//
//            try {
//                countofArray = getSchoolsList.size();
//            } catch (Exception v) {
//                countofArray = 0;
//            }
//
//            return countofArray;
//        }
//
//        private class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
//            TextView LevelText, AtheleteLevelExerciseName, textViewExerciseName, textViewExerciseDate,
//                    AtheleteLevelExerciseValues, AtheleteExerciseValueMultiple, MultiplyValueInc, MultiplyValueDcr;
//            ImageView LevelImage, arrow1, rightSign;
//            RelativeLayout LevelLayout, rAthleteLevelExercise, rLayoutForAthleteTraining;
//
//
//            public RecyclerViewHolder2(@NonNull View itemView) {
//                super(itemView);
//                AtheleteLevelExerciseName = itemView.findViewById(R.id.AtheleteLevelExerciseName);
//                AtheleteLevelExerciseValues = itemView.findViewById(R.id.AtheleteLevelExerciseValues);
//                AtheleteExerciseValueMultiple = itemView.findViewById(R.id.AtheleteExerciseValueMultiple);
//                LevelText = itemView.findViewById(R.id.LevelText);
//                LevelImage = itemView.findViewById(R.id.LevelImage);
//                LevelLayout = itemView.findViewById(R.id.LevelLayout);
//                MultiplyValueInc = itemView.findViewById(R.id.MultiplyValueInc);
//                MultiplyValueDcr = itemView.findViewById(R.id.MultiplyValueDcr);
//                rAthleteLevelExercise = itemView.findViewById(R.id.rAthleteLevelExercise);
//                rightSign = itemView.findViewById(R.id.rightSign);
//                arrow1 = itemView.findViewById(R.id.arrow1);
//                textViewExerciseDate = itemView.findViewById(R.id.textViewExerciseDate);
//                textViewExerciseName = itemView.findViewById(R.id.textViewExerciseName);
//                rLayoutForAthleteTraining = itemView.findViewById(R.id.rLayoutForAthleteTraining);
//            }
//        }
//    }
//}