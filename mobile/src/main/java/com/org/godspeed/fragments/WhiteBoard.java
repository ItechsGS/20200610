package com.org.godspeed.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleRelativeLayout;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.CoachAddExerciseScreen;
import com.org.godspeed.response_JsonS.AddTraining.AddTrainingProgram;
import com.org.godspeed.response_JsonS.AddTraining.Measurement;
import com.org.godspeed.response_JsonS.AddTraining.Type;
import com.org.godspeed.response_JsonS.GetWhiteBoardDatum.ExcerciseAllType;
import com.org.godspeed.response_JsonS.GetWhiteBoardDatum.Excersice;
import com.org.godspeed.response_JsonS.GetWhiteBoardDatum.GetWhiteBoardDatum;
import com.org.godspeed.response_JsonS.GetWhiteBoardDatum.MeasurementValue;
import com.org.godspeed.response_JsonS.GetWhiteBoardDatum.Meser;
import com.org.godspeed.response_JsonS.GetWhiteBoardDatum._0;
import com.org.godspeed.response_JsonS.SchoolData.GetSchools;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.TrainingProgramDetail;
import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
import com.org.godspeed.response_JsonS.getTeams.GetTeam;
import com.org.godspeed.service.OnSwipeListener;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.pxToDp;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.SearchAthlete;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
import static com.org.godspeed.allOtherClasses.LoginScreen.userTypeOf;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_ID_FOR_PRE;
import static com.org.godspeed.service.BackgroundLocationUpdateService.GetTeamORIGINAL;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.service.SchoolDataService.getSchoolsList;
import static com.org.godspeed.utility.UtilityClass.TODAY_START_UTC_TIME;
import static com.org.godspeed.utility.UtilityClass.getNextDate;
import static com.org.godspeed.utility.UtilityClass.getPreviousDate;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.UtilityClass.showAlertDailog;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class WhiteBoard extends Fragment implements GodSpeedInterface {
    public List<ExcerciseAllType> exerciseTypeNameWhiteBoard;
    Context context;
    ImageView arrowRLeftCalender, arrowRightCalender;
    RecyclerView TeamNameList;
    TextView CancelButtonOfSearch;
    ImageView calc_clear_txt_Prise;
    EditText calc_txt_Prise;
    Transition transition = new Slide(Gravity.TOP);
    WhiteBoardFilter whiteBoardFilterAdapter;
    LinearLayout LayoutPillar, LayoutType, LayoutExercise, LayoutSet;
    int PositionForDetails = 0;
    int Pillar = -1;
    int Types = -1;
    int Exercise = -1;
    int Set = 0;
    List<com.org.godspeed.response_JsonS.AddTraining.Pillar> pillar;
    List<Type> types;
    List<com.org.godspeed.response_JsonS.AddTraining.Exercise> exercises;
    List<Measurement> measurement;
    List<Meser> measurement1;
    List<GetTeam> getTeams;
    int x;
    PopupWindow popUp;
    ArrayList<String> arrayListSchool = new ArrayList<>();
    int y;
    List<Excersice> excersiceListWhiteBoard;
    List<GetWhiteBoardDatum> getWhiteBoardData;
    List<String> SportsFilterArrayList = new ArrayList<>();
    List<Measurement> measurementsListForFilter = new ArrayList<>();
    List<_0> PillarCLASS = new ArrayList<>();
    String formattedDate = "";
    List<Meser> MesurementArraySelectedList = new ArrayList<>();
    List<Meser> MesurementArrayAllList = new ArrayList<>();
    LinearLayout date_white_board;
    LinearLayout viewx;
    LinearLayoutManager linearLayoutManager;
    private LinearLayout lLayoutForTeamName;
    private String teamId = "";
    private String whichApiCalled;
    private BubblePopupWindow dialog;
    private Gson gson;
    private List<SelectedAthleteDEtail> AthleteDataX;
    private List<SelectedAthleteDEtail> AthleteDataLocal;
    private String sports_IDs = "";
    private long mCurrentStartTime;
    private white_board_list adapter;
    private RecyclerView ListOfWhiteBoard;
    private TextView textViewDate, PillarOption, TypeOption, ExerciseOption, SetOption, textViewTPName;
    private SimpleDateFormat dateFormate;
    private Calendar cal;
    private int daysInMonth;
    private String nameOfMonth;
    private int currentDate;
    private String ShowDialogOf = "ff";
    private FrameLayout SearchAthleteText;
    private RelativeLayout rSearchAthleteText;
    private TextView PillarText, TypeText, ExerciseText, SetText;
    private List<AddTrainingProgram> TrainingPrograms;
    private int ActiveId = 0;
    private TeamData teamData;
    private WebServices webServices = new WebServices();
    private int Phase = 0;
    private int Week = 0;
    private int Day = 0;
    private String WeightOf = "-";
    private int DIFFRENCE_OF_DAYS = -1;
    private int TrainingProgramId = -1;
    private List<GetWhiteBoardDatum> WhiteBoardDataLocal;
    private boolean SelectedExercise = false;
    private RadioGroup filterby, filterbyvalue, filterbyAthleteData;
    private RadioButton Ascending, Deascending, ByName, ByLastName, ByWeight, ByAthleteLevel;
    private RelativeLayout WhiteBoardData;
    private boolean DeAscendingOrder = false;
    private String textTPName = "";
    private int TrainingProgramIdForWhiteBoard = -1;
    private String coach_class_timing_id = "";
    private String ExerciseName = "";
    private int indexofMeasurementofI = 1;
    private int USERPOSITION = -1;
    private boolean showwaitIndicator = false;
    private int ExcerciseAllTypesPosition;
    private int MeserPosition;
    private ArrayList<TrainingProgramDetail> TeamAssignProgram;
    private int SETSIZE = 0;
    private LinearLayout schoolFilter;

    private RecyclerView schoolFilterList;
    private ImageView resetSchool;
    private String SelectedSchoolId = "";
    private Boolean removeSchoolFilter = false;
    private List<GetSchools> SchoolListLocalWhiteBoard = new ArrayList<>();
    private String SortByName = "ByLName";

    private int HeightOF = 0;
    private View AlertBoxView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View view = inflater.inflate(R.layout.fragment_white_board, container, false);

        gson = new Gson();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        context = getActivity();


        exerciseTypeNameWhiteBoard = new ArrayList<>();
        excersiceListWhiteBoard = new ArrayList<>();
        TeamAssignProgram = new ArrayList<>();

        transition.setDuration(300);
        transition.addTarget(R.id.rSearchAthleteText);

        mCurrentStartTime = TODAY_START_UTC_TIME;
        cal = Calendar.getInstance();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c);


        SearchAthleteText = view.findViewById(R.id.SearchAthleteText);
        CancelButtonOfSearch = view.findViewById(R.id.CancelButtonOfSearch);
        rSearchAthleteText = view.findViewById(R.id.rSearchAthleteText);
        calc_txt_Prise = view.findViewById(R.id.calc_txt_Prise);
        calc_clear_txt_Prise = view.findViewById(R.id.calc_clear_txt_Prise);
        date_white_board = view.findViewById(R.id.date_white_board);
        textViewTPName = view.findViewById(R.id.textViewTPName);
        WhiteBoardData = view.findViewById(R.id.WhiteBoardData);


        calc_clear_txt_Prise.setOnClickListener(view1 -> calc_txt_Prise.setText(""));
        CancelButtonOfSearch.setOnClickListener(view1 -> {
            toggle();
            calc_txt_Prise.setText("");
        });


        TeamNameList = view.findViewById(R.id.TeamNameList);

        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        TeamNameList.setLayoutManager(linearLayoutManager);


        textViewDate = view.findViewById(R.id.textViewDate);
        textViewDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewTPName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        PillarOption = view.findViewById(R.id.PillarOption);
        PillarText = view.findViewById(R.id.PillarText);
        PillarText.setTextColor(getResources().getColor(R.color.textColorYellow));

        TypeOption = view.findViewById(R.id.TypeOption);
        TypeText = view.findViewById(R.id.TypeText);
        TypeText.setTextColor(getResources().getColor(R.color.textColorYellow));

        ExerciseOption = view.findViewById(R.id.ExerciseOption);
        ExerciseText = view.findViewById(R.id.ExerciseText);
        ExerciseText.setTextColor(getResources().getColor(R.color.textColorYellow));

        SetOption = view.findViewById(R.id.SetOption);
        SetText = view.findViewById(R.id.SetText);
        SetText.setTextColor(getResources().getColor(R.color.textColorYellow));

        PillarOption.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        TypeOption.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        ExerciseOption.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        SetOption.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        LayoutPillar = view.findViewById(R.id.LayoutPillar);
        LayoutType = view.findViewById(R.id.LayoutType);
        LayoutExercise = view.findViewById(R.id.LayoutExercise);
        LayoutSet = view.findViewById(R.id.LayoutSet);

        LayoutPillar.setOnClickListener(view1 -> {
            if (TrainingPrograms != null && TrainingPrograms.size() == 0) {
                showAlertDailog(context, "Team don't have training program.");
                return;
            }

            LayoutPillar.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));
            LayoutType.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

            LayoutExercise.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutSet.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

            PillarOption.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            PillarText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            TypeOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            TypeText.setTextColor(getResources().getColor(R.color.textColorYellow));

            ExerciseText.setTextColor(getResources().getColor(R.color.textColorYellow));
            ExerciseOption.setTextColor(getResources().getColor(R.color.textColorYellow));

            SetOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            SetText.setTextColor(getResources().getColor(R.color.textColorYellow));
            ShowDialogOf = "Filter";
            showDialogBox(LayoutPillar, PillarOption.getText().toString(), 0);
        });
        LayoutType.setOnClickListener(view1 -> {
            if (TrainingPrograms != null && TrainingPrograms.size() == 0) {
                showAlertDailog(context, "Team don't have training program.");
                return;
            }
            ShowDialogOf = "Filter";
            showDialogBox(LayoutType, TypeOption.getText().toString(), 1);

            LayoutPillar.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutType.setBackgroundColor(getResources().getColor(R.color.textColorYellow));

            LayoutExercise.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutSet.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

            PillarOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            PillarText.setTextColor(getResources().getColor(R.color.textColorYellow));

            TypeOption.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            TypeText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            ExerciseText.setTextColor(getResources().getColor(R.color.textColorYellow));
            ExerciseOption.setTextColor(getResources().getColor(R.color.textColorYellow));

            SetOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            SetText.setTextColor(getResources().getColor(R.color.textColorYellow));
        });

        calc_clear_txt_Prise.setVisibility(GONE);

        calc_txt_Prise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                try {
                    adapter.filter(text);
                } catch (Exception n) {
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

        SearchAthlete.setVisibility(VISIBLE);

        //SearchAthlete.setOnClickListener(view12 -> toggle());

        LayoutExercise.setOnClickListener(view1 -> {
            if (TrainingPrograms != null && TrainingPrograms.size() == 0) {
                showAlertDailog(context, "Team don't have training program.");
                return;
            }
            LayoutPillar.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutType.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutExercise.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
            LayoutSet.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));


            PillarOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            PillarText.setTextColor(getResources().getColor(R.color.textColorYellow));


            TypeOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            TypeText.setTextColor(getResources().getColor(R.color.textColorYellow));

            ExerciseText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            ExerciseOption.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            SetOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            SetText.setTextColor(getResources().getColor(R.color.textColorYellow));

            ShowDialogOf = "Filter";
            //.getLocationOnScreen();
            showDialogBox(LayoutExercise, ExerciseOption.getText().toString(), 2);
        });
        LayoutSet.setOnClickListener(view1 -> {
            if (TrainingPrograms != null && TrainingPrograms.size() == 0) {
                showAlertDailog(context, "Team don't have training program.");
                return;
            }
            LayoutPillar.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutType.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutExercise.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutSet.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_right_yellow));


            PillarOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            PillarText.setTextColor(getResources().getColor(R.color.textColorYellow));


            TypeOption.setTextColor(getResources().getColor(R.color.textColorYellow));
            TypeText.setTextColor(getResources().getColor(R.color.textColorYellow));

            ExerciseText.setTextColor(getResources().getColor(R.color.textColorYellow));
            ExerciseOption.setTextColor(getResources().getColor(R.color.textColorYellow));

            SetOption.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            SetText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            ShowDialogOf = "Filter";
            showDialogBox(LayoutSet, SetOption.getText().toString(), 3);
        });


        arrowRLeftCalender = view.findViewById(R.id.arrowRLeftCalender);
        arrowRightCalender = view.findViewById(R.id.arrowRightCalender);
        ListOfWhiteBoard = view.findViewById(R.id.ListOfWhiteBoard);
        lLayoutForTeamName = view.findViewById(R.id.lLayoutForTeamName);


        //adapter = new white_board_list(context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        ListOfWhiteBoard.setLayoutManager(mLayoutManager);


        dateFormate = new SimpleDateFormat("dd - MMM - yyyy");
        imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.filtericon));


//        imageViewMenuFilter.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                ShowWhiteBoardFilter();
//            }
//        });


        textViewDate.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        textViewDate.setText(dateFormate.format(cal.getTime()));
        DividerItemDecoration divider = new DividerItemDecoration(ListOfWhiteBoard.getContext(), DividerItemDecoration.VERTICAL);


        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider_light));

        ListOfWhiteBoard.addItemDecoration(divider);


        arrowRightCalender.setOnTouchListener(new OnSwipeListener(context) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                textViewDate.setText(dateFormate.format(getNextDate(textViewDate.getText().toString(), "dd - MMM - yyyy")));
                SendDataOnServer();

            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();

                textViewDate.setText(dateFormate.format(getPreviousDate(textViewDate.getText().toString(), "dd - MMM - yyyy")));
                SendDataOnServer();
            }

            @Override
            public void onClick() {
                super.onClick();
                textViewDate.setText(dateFormate.format(getNextDate(textViewDate.getText().toString(), "dd - MMM - yyyy")));
                SendDataOnServer();
            }
        });
        arrowRLeftCalender.setOnTouchListener(new OnSwipeListener(context) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                textViewDate.setText(dateFormate.format(getNextDate(textViewDate.getText().toString(), "dd - MMM - yyyy")));
                SendDataOnServer();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                textViewDate.setText(dateFormate.format(getPreviousDate(textViewDate.getText().toString(), "dd - MMM - yyyy")));
                SendDataOnServer();

            }

            @Override
            public void onClick() {
                super.onClick();
                textViewDate.setText(dateFormate.format(getPreviousDate(textViewDate.getText().toString(), "dd - MMM - yyyy")));
                SendDataOnServer();

            }
        });
        date_white_board.setOnTouchListener(new OnSwipeListener(context) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                textViewDate.setText(dateFormate.format(getNextDate(textViewDate.getText().toString(), "dd - MMM - yyyy")));
                TrainingProgramId = -1;
                textTPName = "";
                textViewTPName.setText(textTPName);
                textViewTPName.setVisibility(GONE);


                SendDataOnServer();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                textViewDate.setText(dateFormate.format(getPreviousDate(textViewDate.getText().toString(), "dd - MMM - yyyy")));
                TrainingProgramId = -1;
                textTPName = "";
                textViewTPName.setText(textTPName);
                textViewTPName.setVisibility(GONE);

                TrainingProgramId = -1;
                SendDataOnServer();
            }

            @Override
            public void onClick() {
                super.onClick();
                DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        formattedDate = df.format(newDate.getTime());
                        textViewDate.setText(dateFormate.format(newDate.getTime()));
                        SendDataOnServer();
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                StartTime.show();
            }
        });


        try {
            if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
                if (getArguments().getString("FromScreen") != null) {
                    if (getArguments().getString("FromScreen").equalsIgnoreCase("ScheduleCalender")) {
                        SelectedSchoolId = getArguments().getString("school_id");
                    }
                } else {
                    if (SCHOOL_ID_FOR_PRE.equalsIgnoreCase("")) {
                        SelectedSchoolId = getSchoolsList.get(0).getSchoolId();
                    } else {
                        SelectedSchoolId = SCHOOL_ID_FOR_PRE;
                    }
                }
                arrayListSchool.add(SelectedSchoolId);
                whichApiCalled = "team";
                webServices = new WebServices();
                webServices.getTeams(LoginJson.get(0).getUserId(), sports_IDs, SelectedSchoolId, context, WhiteBoard.this);
            } else {
                if (GetTeamORIGINAL.size() != 0) {
                    getTeams = new ArrayList<>(GetTeamORIGINAL);

                    teamData = new TeamData(0, context, 0);

                    TeamNameList.setAdapter(teamData);

                    try {
                        if (getArguments().getString("FromScreen").equalsIgnoreCase("ScheduleCalender")) {
                            SimpleDateFormat dfX = new SimpleDateFormat("yyyy-MM-dd");
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(getArguments().getString("start_date"));
                            formattedDate = dfX.format(date);
                            textViewDate.setText(dateFormate.format(date));

                            TrainingProgramId = Integer.parseInt(getArguments().getString("training_program_id"));
                            teamId = getArguments().getString("team_id");
                            SendDataOnServer();
                            textViewTPName.setText(getArguments().getString("training_program_name"));
                            textViewTPName.setVisibility(VISIBLE);

                            int posititon = 0;
                            for (int i = 0; i < getTeams.size(); i++) {
                                if (teamId.equalsIgnoreCase(getTeams.get(i).getTeamId())) {
                                    posititon = i;
                                }
                            }
                            try {
                                int finalPosititon = posititon;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        TeamNameList.smoothScrollToPosition(finalPosititon);
                                    }
                                }, 200);

                            } catch (Exception v) {

                            }
                        } else {
                            onFailData();
                        }

                    } catch (Exception v) {
                        onFailData();
                    }

                } else {
                    whichApiCalled = "team";
                    webServices = new WebServices();
                    webServices.getTeams(LoginJson.get(0).getUserId(), sports_IDs, SelectedSchoolId, context, WhiteBoard.this);
                }
            }
        } catch (Exception v) {

        }


        return view;
    }

    public void toggle() {
        TransitionManager.beginDelayedTransition(rSearchAthleteText, transition);
        rSearchAthleteText.setVisibility(rSearchAthleteText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        if (rSearchAthleteText.getVisibility() == VISIBLE) {
            calc_txt_Prise.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(calc_txt_Prise, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void SendDataOnServer() {
        whichApiCalled = "GetWhiteBoardData";
        WebServices webServices = new WebServices();
        String id = "";
        if (TrainingProgramId == -1) {
            id = "";
        } else {
            id = TrainingProgramId + "";
        }
        webServices.GetWhiteBoardData(id + "", teamId, sports_IDs, UtilityClass.getDate(textViewDate.getText().toString()), context, WhiteBoard.this);
        ResetFields();
    }


    public void ShowWhiteBoardFilter() {
        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View mView = mInflater.inflate(R.layout.white_board_filter, null, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.setMargins(10, 10, 10, 10);
        mView.setLayoutParams(params);
        popUp = new PopupWindow(mView, params.width, params.height, false);
        popUp.setTouchable(true);
        popUp.setFocusable(true);
        popUp.setOutsideTouchable(true);
        popUp.setAnimationStyle(R.style.Animation);
        popUp.showAtLocation(mView, Gravity.NO_GRAVITY, 0, 0);
        TextView applyFilter = mView.findViewById(R.id.applyFilter);
        RelativeLayout closeFilter = mView.findViewById(R.id.closeFilter);
        schoolFilter = mView.findViewById(R.id.schoolFilter);

        schoolFilterList = mView.findViewById(R.id.schoolFilterList);
        Ascending = mView.findViewById(R.id.Ascending);
        Deascending = mView.findViewById(R.id.Deascending);
        ByName = mView.findViewById(R.id.ByName);
        ByLastName = mView.findViewById(R.id.ByLastName);
        ByWeight = mView.findViewById(R.id.ByWeight);
        filterbyvalue = mView.findViewById(R.id.filterbyValue);
        filterby = mView.findViewById(R.id.filterby);
        filterbyAthleteData = mView.findViewById(R.id.filterbyAthleteData);
        viewx = mView.findViewById(R.id.view);
        Ascending.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
        Deascending.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        schoolFilterList.setLayoutManager(linearLayoutManager);

        //final Boolean SortByAsecendingOrder = false;
        //Boolean SortByFistName = false;

        if (DeAscendingOrder) {
            Ascending.setChecked(false);
            Deascending.setChecked(true);
            Ascending.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
            Deascending.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));
        } else {
            Ascending.setChecked(true);
            Deascending.setChecked(false);
            Deascending.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));
            Ascending.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
        }

        if (SortByName.equalsIgnoreCase("ByFName")) {
            ByName.setChecked(true);
            ByLastName.setChecked(false);
            ByName.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
            ByLastName.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));
        } else {
            ByName.setChecked(false);
            ByLastName.setChecked(true);
            ByName.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));
            ByLastName.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
        }

        applyFilter.setOnClickListener(view -> {
            if (removeSchoolFilter) {
                whichApiCalled = "team";
                webServices = new WebServices();
                webServices.getTeams(LoginJson.get(0).getUserId(), sports_IDs, SelectedSchoolId + "", context, WhiteBoard.this);
                removeSchoolFilter = false;
            }

            // if(SortByOrder) {
//                if (DeAscendingOrder) {
//                    ArrayList<GetWhiteBoardDatum> tempElements = new ArrayList<GetWhiteBoardDatum>(WhiteBoardDataLocal);
//                    Collections.reverse(tempElements);
//                    WhiteBoardDataLocal = new ArrayList<>(tempElements);
//                    adapter.notifyDataSetChanged();
//                } else {
//                    ArrayList<GetWhiteBoardDatum> tempElements = new ArrayList<GetWhiteBoardDatum>(WhiteBoardDataLocal);
//                    Collections.reverse(tempElements);
//                    WhiteBoardDataLocal = new ArrayList<>(tempElements);
//                    adapter.notifyDataSetChanged();
//                }
            // SortByOrder = false;
            //}
            popUp.dismiss();
        });

        closeFilter.setOnClickListener(view -> {
            popUp.dismiss();
        });


        Ascending.setOnClickListener(view1 -> {
            ArrayList<GetWhiteBoardDatum> tempElements = new ArrayList<GetWhiteBoardDatum>(WhiteBoardDataLocal);
            Collections.reverse(tempElements);
            WhiteBoardDataLocal = new ArrayList<>(tempElements);
            try {
                adapter.notifyDataSetChanged();
            } catch (Exception v) {

            }
            DeAscendingOrder = false;
            Ascending.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
            Deascending.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));
            // SortByOrder = true;
        });


//        if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
//            schoolFilter.setVisibility(View.VISIBLE);
//            schoolFilterList.setAdapter(new school_filter_list(context));
//            resetSchool.setVisibility(GONE);
//        }
        resetSchool = mView.findViewById(R.id.resetSchool);
        if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
            filterbyvalue.setVisibility(VISIBLE);
            filterby.setVisibility(VISIBLE);
            schoolFilter.setVisibility(View.VISIBLE);

            if (SCHOOL_ID_FOR_PRE.equalsIgnoreCase("")) {
                SelectedSchoolId = getSchoolsList.get(0).getSchoolId();
            } else {
                SelectedSchoolId = SCHOOL_ID_FOR_PRE;
            }

            arrayListSchool.add(SelectedSchoolId);
            schoolFilterList.setAdapter(new school_filter_list(context));
        }

        Deascending.setOnClickListener(view1 -> {
            ArrayList<GetWhiteBoardDatum> tempElements = new ArrayList<GetWhiteBoardDatum>(WhiteBoardDataLocal);
            Collections.reverse(tempElements);
            WhiteBoardDataLocal = new ArrayList<>(tempElements);
            try {
                adapter.notifyDataSetChanged();
            } catch (Exception v) {

            }
            DeAscendingOrder = true;
            Deascending.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
            Ascending.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));

        });


        ByName.setOnClickListener(view1 -> {
            SortByName = "ByFName";
            Collections.sort(WhiteBoardDataLocal, new Comparator<GetWhiteBoardDatum>() {
                @Override
                public int compare(GetWhiteBoardDatum lhs, GetWhiteBoardDatum rhs) {
                    return lhs.getFirstName().compareTo(rhs.getFirstName());
                }
            });
            ByName.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
            ByLastName.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));
            try {
                adapter.notifyDataSetChanged();
            } catch (Exception v) {

            }

        });

        ByLastName.setOnClickListener(view1 -> {
            SortByName = "ByLName";
            Collections.sort(WhiteBoardDataLocal, new Comparator<GetWhiteBoardDatum>() {
                @Override
                public int compare(GetWhiteBoardDatum lhs, GetWhiteBoardDatum rhs) {
                    return lhs.getLastName().compareTo(rhs.getLastName());
                }
            });
            ByName.setTextColor(Color.parseColor(getResources().getString(R.color.color_white)));
            ByLastName.setTextColor(Color.parseColor(getResources().getString(R.color.textColorYellow)));
            try {
                adapter.notifyDataSetChanged();
            } catch (Exception v) {

            }
        });


//        imageViewMenuFilter.setOnClickListener(view1 -> {
//            CloseDrawer();
//            if (!opened) {
//                viewx.setVisibility(View.VISIBLE);
//                imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.cross));
//
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        WhiteBoardData.setVisibility(View.GONE);
//                        viewx.animate().cancel();
//                        filterbyvalue.setVisibility(VISIBLE);
//                        filterby.setVisibility(VISIBLE);
//                        if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
//                            schoolFilter.setVisibility(View.VISIBLE);
//                            schoolFilterList.setAdapter(new school_filter_list(context));
//                            resetSchool.setVisibility(GONE);
//                        }
//                    }
//                }, 499);
//            } else {
//                WhiteBoardData.setVisibility(VISIBLE);
//
//                try {
//                    imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.filtericon));
//                } catch (Exception v) {
//
//                }
//
//
//                if (removeSchoolFilter) {
//                    whichApiCalled = "team";
//                    webServices = new WebServices();
//                    webServices.getTeams(LoginJson.get(0).getUserId(), sports_IDs, SelectedSchoolId + "", context, WhiteBoard.this);
//                    removeSchoolFilter = false;
//                    schoolFilterList.setAdapter(new school_filter_list(context));
//                }
//
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        viewx.animate().cancel();
//                        viewx.setVisibility(View.GONE);
//                        filterbyvalue.setVisibility(View.GONE);
//                        filterby.setVisibility(View.GONE);
//                        schoolFilter.setVisibility(GONE);
//                    }
//                }, 500);
//            }
//
//            try {
//                adapter.notifyDataSetChanged();
//                opened = !opened;
//            } catch (Exception v) {
//
//            }
//
//        });

        // dialog.show();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public void hideSoftKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public void setupUI(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                hideSoftKeyboard();
                return false;
            });
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    private void getAthleteDataFromServer() {
        //UtilityClass.hide();
        ListOfWhiteBoard.setAdapter(null);
        whichApiCalled = "GetWhiteBoardData";
        WebServices webServices = new WebServices();
        //webServices.GetWhiteBoardData(getTeams.get(ActiveId).getTrainingProgramDetail().get(i).getAssignProgramId(),teamId,formattedDate,context,GetWhiteBoardDatum.this);
        ResetFields();
    }

    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, "" + whichApiCalled);
        if (result != null && result.trim().length() > 0) {
            if (whichApiCalled.equalsIgnoreCase("team")) {
                parseRequiredData(result);

                //getAthleteDataFromServer();
            } else if (whichApiCalled.equalsIgnoreCase("athlete")) {
                parseRequiredData(result);
                UtilityClass.hide();
                //whichApiCalled = "";
            } else if (whichApiCalled.equalsIgnoreCase("attendance")) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    String responseMessage = jsonObj
                            .getString("responseMessage");
                    showAlertDailog(context, responseMessage);

                } catch (JSONException e) {

                    e.printStackTrace();

                } catch (Exception e) {

                    e.printStackTrace();
                }
                //prgDailog.dismiss();

                UtilityClass.hide();
                whichApiCalled = "";
            } else if (whichApiCalled.equalsIgnoreCase("GetWhiteBoardData")) {
                // Log.e(VolleyLog.TAG, "ApiResponse:of\"GetWhiteBoardData\" "+result);
                parseRequiredData(result);
            } else if (whichApiCalled.equalsIgnoreCase("GetWhiteBoardDataWithoutTraining")) {
                // Log.e(VolleyLog.TAG, "ApiResponse:of\"GetWhiteBoardData\" "+result);
                parseRequiredData(result);
            } else if (whichApiCalled.equalsIgnoreCase("Addset")) {
                parseRequiredData(result);
                hide();
            } else if (whichApiCalled.equalsIgnoreCase("getAssignProgramOfteam") || whichApiCalled.equalsIgnoreCase("getAssignProgramOfteamSelected")) {
                parseRequiredData(result);
                hide();
            }

        } else {
            UtilityClass.hide();
        }

    }


    private void onFailData() {
        teamId = getTeams.get(ActiveId).getTeamId();

        ShowDialogOf = "TeamAssignProgram";
        whichApiCalled = "GetWhiteBoardDataWithoutTraining";
        WebServices webServices = new WebServices();
        webServices.GetWhiteBoardData("", teamId, sports_IDs, formattedDate, context, WhiteBoard.this);
    }

    private void parseRequiredData(String result) {
        Log.e(VolleyLog.TAG, "parseRequiredData:TAGS " + result);
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj
                    .getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");

            Log.e("**********", "mymessge" + responseMessage);
            if (WebServices.responseCode == 200) {

                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");
                Log.e(VolleyLog.TAG, "parseRequiredData: " + jsonDataArray);
                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    if (whichApiCalled.equalsIgnoreCase("team")) {
                        for (int x = 0; x < jsonDataArray.length(); x++) {
                            JSONObject jsonAthleteObjDataX = jsonDataArray.getJSONObject(x);
                            try {
                                Object json = jsonAthleteObjDataX.get("Training_program_detail");
                                if (json instanceof String) {
                                    jsonAthleteObjDataX.put("Training_program_detail", new JSONArray());
                                }
                            } catch (Exception e) {
                                Log.e(VolleyLog.TAG, "Training_program_detail: " + e);
                            }
                        }

                        UtilityClass.hide();
                        getTeams = Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetTeam[].class));

                        teamData = new TeamData(0, context, 0);

                        TeamNameList.setAdapter(teamData);


                        try {
                            if (getArguments().getString("FromScreen").equalsIgnoreCase("ScheduleCalender")) {
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = format.parse(getArguments().getString("start_date"));
                                formattedDate = df.format(date);
                                textViewDate.setText(dateFormate.format(date));

                                TrainingProgramId = Integer.parseInt(getArguments().getString("training_program_id"));
                                teamId = getArguments().getString("team_id");
                                SendDataOnServer();
                                textViewTPName.setText(getArguments().getString("training_program_name"));
                                textViewTPName.setVisibility(VISIBLE);
                                Log.e(VolleyLog.TAG, "FromScreen: " +
                                        getArguments().getString("FromScreen") + " " +
                                        getArguments().getString("team_id") + " " +
                                        getArguments().getString("team_name") + " " +
                                        getArguments().getString("training_program_id") + " " +
                                        getArguments().getString("start_date") + " "
                                );

                                int posititon = 0;
                                for (int i = 0; i < getTeams.size(); i++) {
                                    if (teamId.equalsIgnoreCase(getTeams.get(i).getTeamId())) {
                                        posititon = i;
                                    }
                                }
                                try {
                                    int finalPosititon = posititon;

                                    // TeamNameList.smoothScrollToPosition(finalPosititon);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //  linearLayoutManager.smoothScrollToPosition(TeamNameList,new RecyclerView.State(),i);
                                            TeamNameList.smoothScrollToPosition(finalPosititon);
                                        }
                                    }, 200);

                                } catch (Exception v) {

                                }
                            } else {
                                onFailData();
                            }

                        } catch (Exception v) {
                            onFailData();
                        }


                    } else if (whichApiCalled.equalsIgnoreCase("GetWhiteBoardData")) {
                        ResetFields();
                        Log.e(VolleyLog.TAG, "parseRequiredData:jsonDataArray " + jsonDataArray);
                        JSONArray jsonArray = jsonObj.getJSONArray("data");
                        UtilityClass.hide();
                        getWhiteBoardData = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), GetWhiteBoardDatum[].class)));
                        WhiteBoardDataLocal = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), GetWhiteBoardDatum[].class)));


                        adapter = new white_board_list(context, WhiteBoardDataLocal);

                        ListOfWhiteBoard.setAdapter(adapter);


                        try {
                            exerciseTypeNameWhiteBoard = new ArrayList<>();
                            PillarCLASS = new ArrayList<>();
                            for (int i1 = 0; i1 < WhiteBoardDataLocal.size(); i1++) {
                                for (int x = 0; x < WhiteBoardDataLocal.get(i1).get0().size(); x++) {
                                    PillarCLASS.add(WhiteBoardDataLocal.get(i1).get0().get(x));
                                }
                            }
                            List<Meser> b = new ArrayList<>();
                            for (int x = 0; x < PillarCLASS.size(); x++) {
                                for (int j = x + 1; j < PillarCLASS.size(); j++) {
                                    if (PillarCLASS.get(x).getId().equalsIgnoreCase(PillarCLASS.get(j).getId())) {
                                        PillarCLASS.remove(j);
                                        j--;
                                    }
                                }
                            }


                        } catch (Exception v) {

                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TeamAssignProgram = new ArrayList<>();
                                whichApiCalled = "getAssignProgramOfteamSelected";
                                webServices.getAssignProgram(teamId, formattedDate, context, WhiteBoard.this);
                            }
                        }, 100);

                    } else if (whichApiCalled.equalsIgnoreCase("GetWhiteBoardDataWithoutTraining")) {
                        ResetFields();
                        JSONArray jsonArray = jsonObj.getJSONArray("data");
                        UtilityClass.hide();
                        getWhiteBoardData = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), GetWhiteBoardDatum[].class)));
                        WhiteBoardDataLocal = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), GetWhiteBoardDatum[].class)));

                        adapter = new white_board_list(context, WhiteBoardDataLocal);

                        ListOfWhiteBoard.setAdapter(adapter);

                        try {
                            exerciseTypeNameWhiteBoard = new ArrayList<>();
                            PillarCLASS = new ArrayList<>();

                            for (int i1 = 0; i1 < WhiteBoardDataLocal.size(); i1++) {
                                for (int x = 0; x < WhiteBoardDataLocal.get(i1).get0().size(); x++) {
                                    PillarCLASS.add(WhiteBoardDataLocal.get(i1).get0().get(x));
                                }
                            }
                            for (int x = 0; x < PillarCLASS.size(); x++) {
                                for (int j = x + 1; j < PillarCLASS.size(); j++) {
                                    if (PillarCLASS.get(x).getId().equalsIgnoreCase(PillarCLASS.get(j).getId())) {
                                        PillarCLASS.remove(j);
                                        j--;
                                    }
                                }
                            }


                        } catch (Exception v) {
                        }

                        //showDialogBox(lLayoutForTeamName, "TRAINING PROGRAMS", 0);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TeamAssignProgram = new ArrayList<>();
                                whichApiCalled = "getAssignProgramOfteam";
                                webServices.getAssignProgram(teamId, formattedDate, context, WhiteBoard.this);
                            }
                        }, 100);

                    } else if (whichApiCalled.equalsIgnoreCase("Addset")) {
                        JSONArray jsonArray = jsonObj.getJSONArray("data");
                        measurement1 = new ArrayList<>(new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), Meser[].class))));


                        WhiteBoardDataLocal.get(USERPOSITION).get0().get(Pillar).getExcerciseAllTypes().get(ExcerciseAllTypesPosition).getExcersice().get(MeserPosition).setMeser(measurement1);


                        adapter.notifyDataSetChanged();
                    } else if (whichApiCalled.equalsIgnoreCase("getAssignProgramOfteam") || whichApiCalled.equalsIgnoreCase("getAssignProgramOfteamSelected")) {
                        JSONArray jsonArray = jsonObj.getJSONArray("data");
                        TeamAssignProgram = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), TrainingProgramDetail[].class)));
                        Log.e(VolleyLog.TAG, "TrainingProgramId: " + TrainingProgramId);
                        if (!whichApiCalled.equalsIgnoreCase("getAssignProgramOfteamSelected")) {
                            try {
                                if (!getArguments().getString("FromScreen").equalsIgnoreCase("ScheduleCalender")) {
                                    ShowDialogOf = "TeamAssignProgram";
                                    showDialogBox(lLayoutForTeamName, "TRAINING PROGRAMS", 0);
                                }
                            } catch (Exception v) {
                                ShowDialogOf = "TeamAssignProgram";
                                showDialogBox(lLayoutForTeamName, "TRAINING PROGRAMS", 0);
                            }

                        }
                    }
                }
            } else {
                if (whichApiCalled.equalsIgnoreCase("getAssignProgramOfteam")) {
                    UtilityClass.showAlertDailog(context, responseMessage);
                }
                UtilityClass.hide();
            }
        } catch (JSONException e) {

            Log.e(VolleyLog.TAG, "parseRequiredData: Errror 1");
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(VolleyLog.TAG, "parseRequiredData: Errror 2");
            e.printStackTrace();
        }

    }


    private void CheckArray() {
        AthleteDataLocal = new ArrayList<>();
        if (Pillar >= 0 && Types >= 0 && Exercise >= 0) {
            AthleteDataLocal = AthleteDataX.stream().filter(article ->
                    article.getAthleteActiveExerciseStatus().getTrainingProgramId() == TrainingProgramId &&
                            Integer.parseInt(pillar.get(Pillar).getNumber()) - 1 == (article.getAthleteActiveExerciseStatus().getPillar()) &&
                            Integer.parseInt(types.get(Types).getId()) == (article.getAthleteActiveExerciseStatus().getExerciseTypeId()) &&
                            Integer.parseInt(exercises.get(Exercise).getExerciseId()) == (article.getAthleteActiveExerciseStatus().getExerciseId())
            ).collect(Collectors.toList());
            Log.e(VolleyLog.TAG, "CheckArray: " + 3);
        } else if (Pillar >= 0 && Types >= 0) {
            AthleteDataLocal = AthleteDataX.stream().filter(article ->
                    article.getAthleteActiveExerciseStatus().getTrainingProgramId() == TrainingProgramId &&
                            Integer.parseInt(pillar.get(Pillar).getNumber()) - 1 == (article.getAthleteActiveExerciseStatus().getPillar()) &&
                            Integer.parseInt(types.get(Types).getId()) == article.getAthleteActiveExerciseStatus().getExerciseTypeId()
            ).collect(Collectors.toList());
            Log.e(VolleyLog.TAG, "CheckArray: " + 2);
        } else if (Pillar >= 0) {
            AthleteDataLocal = AthleteDataX.stream().filter(article ->
                    article.getAthleteActiveExerciseStatus().getTrainingProgramId() == TrainingProgramId &&
                            Integer.parseInt(pillar.get(Pillar).getNumber()) - 1 == article.getAthleteActiveExerciseStatus().getPillar()
            ).collect(Collectors.toList());
            Log.e(VolleyLog.TAG, "CheckArray: " + 1);
        } else {
            AthleteDataLocal = new ArrayList<>(AthleteDataX);
        }
        adapter.notifyDataSetChanged();
    }


    private void checkPillar(int ConditionPo) {
        Log.e(VolleyLog.TAG, "checkPillar: " + ConditionPo);
        try {
            int val = Integer.parseInt(pillar.get(Pillar).getNumber()) - 1;

            AthleteDataLocal = new ArrayList<>();
            Log.e(VolleyLog.TAG, "checkPillar:pillar " + Integer.parseInt(pillar.get(Pillar).getNumber()) + "  TrainingProgramId" + TrainingProgramId);
            for (int x = 0; x < AthleteDataX.size(); x++) {
                if (AthleteDataX.get(x).getAthleteActiveExerciseStatus() != null) {

                    if (AthleteDataX.get(x).getAthleteActiveExerciseStatus().getTrainingProgramId() == TrainingProgramId) {
                        switch (ConditionPo) {
//                            case 1:
//                                if (Integer.parseInt(pillar.get(Pillar).getNumber()) == AthleteDataX.get(x).getAthleteActiveExerciseStatus().getPillar()) {
//                                    AthleteDataLocal.add(AthleteDataX.get(x));
//                                }
//                                break;
                            case 2:
                                if (val == (AthleteDataX.get(x).getAthleteActiveExerciseStatus().getPillar()) &&
                                        Integer.parseInt(types.get(Types).getId()) == AthleteDataX.get(x).getAthleteActiveExerciseStatus().getExerciseTypeId()
                                        && Integer.parseInt(exercises.get(Exercise).getExerciseId()) == AthleteDataX.get(x).getAthleteActiveExerciseStatus().getExerciseId()) {
                                }
                                break;
                            case 3:
                                if (val == (AthleteDataX.get(x).getAthleteActiveExerciseStatus().getPillar()) &&
                                        Integer.parseInt(types.get(Types).getId()) == AthleteDataX.get(x).getAthleteActiveExerciseStatus().getExerciseTypeId()) {
                                    AthleteDataLocal.add(AthleteDataX.get(x));
                                }
                                break;
                            case 4:
                                if (val == AthleteDataX.get(x).getAthleteActiveExerciseStatus().getPillar()) {
                                    AthleteDataLocal.add(AthleteDataX.get(x));
                                }
                                break;
                        }

                    }

                }
            }
            adapter.notifyDataSetChanged();
        } catch (Exception v) {
            Log.e(VolleyLog.TAG, "AthleteDataX:Errroror " + v);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    /********************** *??????????/////////////////*/
    public void showDialogBox(View layoutType, String athlete_level, int position) {

        try {
            if (ShowDialogOf.equalsIgnoreCase("Filter")) {
                if (position == 0) {
                    //pillar = TrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar();
                }
                if (position == 1) {
                    if (Pillar < 0) {
                        showAlertDailog(context, "Please select pillar.");
                        return;
                    }
                }
                if (position == 2) {
                    if (Types < 0) {
                        showAlertDailog(context, "Please select type.");
                        return;
                    }
                }
                if (position == 3) {
                    if (Exercise < 0) {
                        showAlertDailog(context, "Please select exercise.");
                        return;
                    }
                }
                if (position == 4) {
                    if (Set < 0) {
                        showAlertDailog(context, "Please select exercise.");
                        return;
                    }
                }
            } else if (ShowDialogOf.equalsIgnoreCase("TeamAssignProgram")) {
                if (TeamAssignProgram.size() == 0) {
                    UtilityClass.showAlertDailog(context, "Team don't have assigned training program's.");
                    return;
                }
            }
        } catch (Exception v) {

        }


        AlertBoxView = LayoutInflater.from(context).inflate(R.layout.cutom_dialogbox_athlete_screen, null);
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
        if (layoutType == null) {
            dialog.showAtLocation(WhiteBoardData, Gravity.CENTER, 0, 0);
        } else {
            dialog.showArrowTo(layoutType, BubbleStyle.ArrowDirection.Up);
        }

        TextView EventName = AlertBoxView.findViewById(R.id.EventName);
        ImageView SaveEventDialog = AlertBoxView.findViewById(R.id.SaveEventDialog);
        ImageView backEventDialog = AlertBoxView.findViewById(R.id.backEventDialog);


        //SaveEventDialog.setImageDrawable(R.drawable.);


        EventName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        EventName.setText(athlete_level);
        //EventName.setTextColor(getResources().getColor(R.color.textColorYellow));

        AlertBoxView.getLayoutParams().height = dpToPx(280);

        if (ShowDialogOf.equalsIgnoreCase("Filter")) {
            SaveEventDialog.setVisibility(VISIBLE);
            backEventDialog.setVisibility(View.INVISIBLE);
            SaveEventDialog.setImageDrawable(getResources().getDrawable(R.drawable.filter_icon));
            AlertBoxView.getLayoutParams().height = dpToPx(200);
        }
        if (athlete_level.equalsIgnoreCase("TRAINING PROGRAMS")) {
            rMainDialogLayout.getLayoutParams().width = dpToPx(300);
            EventName.setText("ASSIGNED TRAINING PROGRAMS");
        }


        RecyclerView dialogBoxRecyclerView = AlertBoxView.findViewById(R.id.dialogBoxRecyclerView);

        dialogBoxRecyclerView.setVisibility(View.VISIBLE);
        dialogBoxRecyclerView.setHasFixedSize(true);
        dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        DividerItemDecoration divider = new
                DividerItemDecoration(dialogBoxRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider_dark_yellow));

        dialogBoxRecyclerView.addItemDecoration(divider);

        //rMainDialogLayout.setBackground(getResources().getDrawable(R.drawable.bg_dark_semi_bottom_rounded));

        RelativeLayout RReventName = AlertBoxView.findViewById(R.id.RReventName);


        try {
            if (ShowDialogOf.equalsIgnoreCase("Filter")) {
                if (position == 0) {
                    //pillar = TrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar();
                }
                if (position == 1) {
                    if (Pillar < 0) {
                        showAlertDailog(context, "Please select pillar.");
                        return;
                    }
                }
                if (position == 2) {
                    if (Types < 0) {
                        showAlertDailog(context, "Please select type.");
                        return;
                    }
                }
                if (position == 3) {
                    if (Exercise < 0) {
                        showAlertDailog(context, "Please select exercise.");
                        return;
                    }
                }
                if (position == 4) {
                    if (Set < 0) {
                        showAlertDailog(context, "Please select exercise.");
                        return;
                    }
                }
            }
        } catch (Exception v) {

        }
        if (ShowDialogOf.equalsIgnoreCase("Sports")) {
            backEventDialog.setVisibility(View.INVISIBLE);
            SaveEventDialog.setVisibility(VISIBLE);
            AlertBoxView.getLayoutParams().width = dpToPx(300);
            EventName.setText("FILTER BY SPORTS");
        }

        SaveEventDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShowDialogOf.equalsIgnoreCase("Sports")) {

                    StringBuilder stringBuilder = new StringBuilder();
                    sports_IDs = "";
                    for (int x = 0; x < SportsFilterArrayList.size(); x++) {
                        Log.e(VolleyLog.TAG, "onBindViewHolder: " + SportsFilterArrayList.get(x));
                        sports_IDs = stringBuilder.append(SportsFilterArrayList.get(x) + ",").toString();
                    }

                    if (sports_IDs != null && sports_IDs.length() > 0 && sports_IDs.charAt(sports_IDs.length() - 1) == ',') {
                        sports_IDs = sports_IDs.substring(0, sports_IDs.length() - 1);
                    }
                    Log.e(VolleyLog.TAG, "onBindViewHolder: " + sports_IDs);
                    WebServices webServices = new WebServices();
                    if (TrainingProgramId >= 0) {
                        whichApiCalled = "GetWhiteBoardData";
                        webServices.GetWhiteBoardData(TrainingProgramId + "", teamId, sports_IDs, formattedDate, context, WhiteBoard.this);
                    } else {
                        whichApiCalled = "GetWhiteBoardDataWithoutTraining";
                        webServices.GetWhiteBoardData("", teamId, sports_IDs, formattedDate, context, WhiteBoard.this);
                    }
                    //ShowDialogOf.equalsIgnoreCase("");
                    dialog.dismiss();
                    getWhiteBoardData = new ArrayList<>();
                    WhiteBoardDataLocal = new ArrayList<>();
                    adapter.notifyDataSetChanged();

                    ResetFields();
                    //webServices.(LoginScreen.userId, sports_IDs,coach_class_timing_id, context, CoachBoardFragments.this);

                } else {
                    ListOfWhiteBoard.setAdapter(adapter);
                    if (position == 0) {
                        Pillar = -1;
                        PillarOption.setVisibility(View.GONE);
                        PillarText.setText("Pillar");

                        Types = -1;
                        TypeOption.setVisibility(View.GONE);
                        TypeText.setText("Type");

                        Exercise = -1;
                        ExerciseOption.setVisibility(View.GONE);
                        ExerciseText.setText("Exercise");

                        Set = 0;
                        SETSIZE = 0;
                        SetOption.setVisibility(View.GONE);
                        SetText.setText("Set");
                        SETSIZE = 0;
                    }
                    if (position == 1) {
                        Types = -1;
                        TypeOption.setVisibility(View.GONE);
                        TypeText.setText("Type");

                        Exercise = -1;
                        ExerciseOption.setVisibility(View.GONE);
                        ExerciseText.setText("Exercise");

                        SETSIZE = 0;
                        Set = 0;
                        SetOption.setVisibility(View.GONE);
                        SetText.setText("Set");
                        SETSIZE = 0;
                    }
                    if (position == 2) {
                        Exercise = -1;
                        ExerciseOption.setVisibility(View.GONE);
                        ExerciseText.setText("Exercise");
                        SETSIZE = 0;
                        Set = 0;
                        SetOption.setVisibility(View.GONE);
                        SetText.setText("Set");
                        SETSIZE = 0;
                    }
                    if (position == 3) {
                        Set = 0;
                        SetOption.setVisibility(View.GONE);
                        SetText.setText("Set");
                    }
                    //CheckArray();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });


        whiteBoardFilterAdapter = new WhiteBoardFilter(position, context);

        dialogBoxRecyclerView.setAdapter(whiteBoardFilterAdapter);
        //imageViewAppIconForAnimation = dialog.findViewById(R.id.imageViewAppIconForAnimation);


//        if (ShowDialogOf.equalsIgnoreCase("Filter")) {
//
//            WindowManager.LayoutParams wmlp = AlertBoxView.getWindow().getAttributes();
//            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
//            wmlp.x = x;
//            wmlp.y = y + 30;
//        }
//

        Log.e(VolleyLog.TAG, "showDialogBox: " + TeamAssignProgram.size());
//        else {
//            try {
//                getTeams.get(ActiveId).getTrainingProgramDetail().size();
//            } catch (Exception v) {
//                if (ShowDialogOf.equalsIgnoreCase("Training")) {
//                    UtilityClass.showAlertDailog(context, "Team don't have assigned training program's.");
//                    return;
//                }
//            }
//        }
        Log.e(VolleyLog.TAG, "showDialogBox:ShowDialogOf " + ShowDialogOf);
    }


    public void showDialogmn(View layoutType, Context context, int x, int y, String event, String whiteboardNotes, String whiteboardNotesId, String userId, String trainingExerciseId, String trainingProgramDetailId, List<Excersice> excersice, int Position2, int position) {
        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AlertBoxView = mInflater.inflate(R.layout.custom_dialog_box_for_sets, null, false);
//        dialog = new BubblePopupWindow(AlertBoxView, dpToPx(300), dpToPx(200), false);


        BubbleLinearLayout bubbleView = AlertBoxView.findViewById(R.id.mainRlyofAddset);


        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);
        dialog.setCancelOnTouch(false);

        dialog.showArrowTo(layoutType, BubbleStyle.ArrowDirection.Down);
        TextView txt = AlertBoxView.findViewById(R.id.EventName);
        ImageView addsetSave = AlertBoxView.findViewById(R.id.addsetSave);
        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        txt.setText(event);
        //LinearLayout mainRlyofAddset = AlertBoxView.findViewById(R.id.mainRlyofAddset);


        AlertBoxView.getLayoutParams().height = dpToPx(180);
        AlertBoxView.getLayoutParams().width = dpToPx(200);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        TextView txtData = AlertBoxView.findViewById(R.id.eventData);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        //txtData.setText(eventData);
        txtData.setVisibility(GONE);
        EditText addnotes = AlertBoxView.findViewById(R.id.addnotes);
        addnotes.setText(whiteboardNotes);
        addsetSave.setOnClickListener(view -> {
            WebServices webServices = new WebServices();
            if (addnotes.getText().toString().length() >= 0) {
                whichApiCalled = "addNoteWhiteBoard";
                webServices.AddNotesWhiteBoard(TrainingProgramId + "", userId, addnotes.getText().toString(), trainingExerciseId, trainingProgramDetailId, context, WhiteBoard.this);
                excersice.get(Position2).setWhiteboardNotes(addnotes.getText().toString());
                hide();
                popUp.dismiss();
            } else {
                UtilityClass.showAlertDailog(context, "Please enter note description.");
            }
        });

    }

    private void ShowAlertForEditTraining(Context context, View layoutType, String event, String userId, int position) {
        USERPOSITION = position;
        if (Exercise == -1) {
            UtilityClass.showAlertDailog(context, "Please select exercise.");
            ExerciseName = "";
            return;
        }
        int[] location = new int[2];


        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AlertBoxView = mInflater.inflate(R.layout.custom_dialog_box_for_sets, null, false);

        TextView txt = AlertBoxView.findViewById(R.id.EventName);
        txt.setSelected(true);
        ImageView addsetSave = AlertBoxView.findViewById(R.id.addsetSave);
        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        txt.setText(ExerciseName);
        BubbleLinearLayout mainRlyofAddset = AlertBoxView.findViewById(R.id.mainRlyofAddset);
        TextView txtData = AlertBoxView.findViewById(R.id.eventData);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        //txtData.setText(eventData);
        txtData.setVisibility(GONE);
        EditText addnotes = AlertBoxView.findViewById(R.id.addnotes);
        addnotes.setVisibility(GONE);
        RecyclerView AthleteTrainingData = AlertBoxView.findViewById(R.id.AthleteTrainingData);
        addnotes.setText(event);

        mainRlyofAddset.setCornerRadius(70f);
        mainRlyofAddset.setArrowWidth(70f);
        mainRlyofAddset.setArrowHeight(30f);

        measurement1 = new ArrayList<>();
        for (int c = 0; c < WhiteBoardDataLocal.get(USERPOSITION).get0().get(Pillar).getExcerciseAllTypes().size(); c++) {
            if (Types == Integer.parseInt(WhiteBoardDataLocal.get(USERPOSITION).get0().get(Pillar).getExcerciseAllTypes().get(c).getId())) {
                ExcerciseAllTypesPosition = c;
                for (int i2 = 0; i2 < WhiteBoardDataLocal.get(USERPOSITION).get0().get(Pillar).getExcerciseAllTypes().get(c).getExcersice().size(); i2++) {
                    if (Exercise == Integer.parseInt(WhiteBoardDataLocal.get(USERPOSITION).get0().get(Pillar).getExcerciseAllTypes().get(c).getExcersice().get(i2).getIdAuto())) {
                        MeserPosition = i2;
                        measurement1 = new ArrayList<>(WhiteBoardDataLocal.get(USERPOSITION).get0().get(Pillar).getExcerciseAllTypes().get(c).getExcersice().get(i2).getMeser());
                    }
                }
            }
        }

        TextView MeaurmentName1 = AlertBoxView.findViewById(R.id.MeaurmentName1);
        TextView MeaurmentName2 = AlertBoxView.findViewById(R.id.MeaurmentName2);
        TextView MeaurmentName3 = AlertBoxView.findViewById(R.id.MeaurmentName3);
        TextView MeaurmentName4 = AlertBoxView.findViewById(R.id.MeaurmentName4);
        TextView MeaurmentName5 = AlertBoxView.findViewById(R.id.MeaurmentName5);

        int HeightOfPopUp = dpToPx(230);
        try {
            MeaurmentName1.setText(measurement1.get(0).getMeasurementName());
            MeaurmentName1.setVisibility(VISIBLE);
            indexofMeasurementofI = 1;
        } catch (Exception v) {
        }
        try {
            MeaurmentName2.setText(measurement1.get(1).getMeasurementName());
            MeaurmentName2.setVisibility(VISIBLE);
            indexofMeasurementofI = 2;
        } catch (Exception v) {
        }
        try {
            MeaurmentName3.setText(measurement1.get(2).getMeasurementName());
            MeaurmentName3.setVisibility(VISIBLE);
            indexofMeasurementofI = 3;
        } catch (Exception v) {
        }
        try {
            MeaurmentName4.setText(measurement1.get(3).getMeasurementName());
            MeaurmentName4.setVisibility(VISIBLE);
            indexofMeasurementofI = 4;
            HeightOfPopUp = dpToPx(270);

        } catch (Exception v) {
        }
        try {
            MeaurmentName5.setText(measurement1.get(4).getMeasurementName());
            MeaurmentName5.setVisibility(VISIBLE);
            indexofMeasurementofI = 4;
            HeightOfPopUp = dpToPx(300);

        } catch (Exception v) {
        }

        dialog = new BubblePopupWindow(AlertBoxView, mainRlyofAddset);
        dialog.setCancelOnTouch(false);

        BubbleRelativeLayout bubbleView = AlertBoxView.findViewById(R.id.CardX);

        dialog.showArrowTo(layoutType, BubbleStyle.ArrowDirection.Down);


        AthleteTrainingData.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        AthleteTrainingDataUpdateWhiteBoard athleteTrainingDataUpdateWhiteBoard = new AthleteTrainingDataUpdateWhiteBoard(userId);
        AthleteTrainingData.setAdapter(athleteTrainingDataUpdateWhiteBoard);
        addsetSave.setVisibility(GONE);
//        addsetSave.setOnClickListener(view -> {
//
//        });
        dialog.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                showwaitIndicator = true;
            }
        });
        //  mainRlyofAddset.getLayoutParams().height = dpToPx(HeightOfPopUp);
    }

    private void ResetFields() {
        getWhiteBoardData = new ArrayList<>();
        TeamAssignProgram = new ArrayList<>();
        WhiteBoardDataLocal = new ArrayList<>();
        exerciseTypeNameWhiteBoard = new ArrayList<>();
        PillarCLASS = new ArrayList<>();
        WeightOf = "-";


        LayoutPillar.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
        LayoutType.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

        LayoutExercise.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
        LayoutSet.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

        PillarOption.setTextColor(getResources().getColor(R.color.textColorYellow));
        PillarText.setTextColor(getResources().getColor(R.color.textColorYellow));

        TypeOption.setTextColor(getResources().getColor(R.color.textColorYellow));
        TypeText.setTextColor(getResources().getColor(R.color.textColorYellow));

        ExerciseText.setTextColor(getResources().getColor(R.color.textColorYellow));
        ExerciseOption.setTextColor(getResources().getColor(R.color.textColorYellow));

        SetOption.setTextColor(getResources().getColor(R.color.textColorYellow));
        SetText.setTextColor(getResources().getColor(R.color.textColorYellow));

        PillarOption.setVisibility(View.GONE);
        ExerciseOption.setVisibility(View.GONE);

        TypeOption.setVisibility(View.GONE);
        SetOption.setVisibility(View.GONE);

        PillarText.setText("Pillar");
        ExerciseText.setText("Exercise");

        TypeText.setText("Type");
        SetText.setText("Set");


        Pillar = -1;
        PillarOption.setVisibility(View.GONE);
        PillarText.setText("Pillar");

        Types = -1;
        TypeOption.setVisibility(View.GONE);
        TypeText.setText("Type");

        Exercise = -1;
        ExerciseOption.setVisibility(View.GONE);
        ExerciseText.setText("Exercise");

        Set = 0;
        SetOption.setVisibility(View.GONE);
        SetText.setText("Set");

        SETSIZE = 0;

        PillarCLASS = new ArrayList<>();
        exerciseTypeNameWhiteBoard = new ArrayList<>();
        excersiceListWhiteBoard = new ArrayList<>();
        measurement = new ArrayList<>();


        try {
            adapter.notifyDataSetChanged();
        } catch (Exception b) {

        }
    }

    private void addIntoMesurementArraySelectedList(int position) {
        List<Meser> NEWmn;
        if (Pillar >= 0 && Types >= 0 && Exercise >= 0) {
            List<Excersice> excersices = new ArrayList<>();
            NEWmn = new ArrayList<>();
            for (int i = 0; i < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().size(); i++) {
                if (Integer.parseInt(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i).getId()) == Types) {
                    for (int i1 = 0; i1 < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i).getExcersice().size(); i1++) {
                        if (Integer.parseInt(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i).getExcersice().get(i1).getIdAuto()) == Exercise) {
                            NEWmn = new ArrayList<>(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i).getExcersice().get(i1).getMeser());
                        }
                    }
                }
            }
        } else if (Pillar >= 0 && Types >= 0 && Exercise <= 0) {
            List<Excersice> excersices = new ArrayList<>();
            for (int i = 0; i < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().size(); i++) {
                if (Integer.parseInt(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i).getId()) == Types) {
                    excersices = new ArrayList<>(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i).getExcersice());
                }
            }
            NEWmn = new ArrayList<>(excersices.get(0).getMeser());
        } else if (Pillar >= 0 && Types <= 0 && Exercise <= 0) {
            NEWmn = new ArrayList<>(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(0).getExcersice().get(0).getMeser());
        } else {
            NEWmn = new ArrayList<>(WhiteBoardDataLocal.get(position).get0().get(0).getExcerciseAllTypes().get(0).getExcersice().get(0).getMeser());
        }

        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("6")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//Weight
        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("16")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//rounds
        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("19")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//time
        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("17")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//distance
        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("11")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//height
        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("15")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//calories
        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("12")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//maxlift%
        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("18")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//reps
        NEWmn.forEach(article -> {
            if (article.getMeasurementId().equalsIgnoreCase("5")) {
                MesurementArraySelectedList.add(article);
                MesurementArrayAllList.add(article);
            }
        });//sets


        //filterby.addView();


    }

    private void MagicTouch() {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis() + 100;
        float x = 0.0f;
        float y = 0.0f;
// List of meta states found here:     developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
        int metaState = 0;
        MotionEvent motionEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                MotionEvent.ACTION_UP,
                x,
                y,
                metaState
        );

// Dispatch touch event to view
        super.getActivity().dispatchTouchEvent(motionEvent);

    }

    private void removeFocus() {
        MagicTouch();
        InputMethodManager imm = (InputMethodManager)
                getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
    }

    public class white_board_list extends RecyclerView.Adapter<white_board_list.white_board_listViewHolder> {
        Context context;

        public white_board_list(Context context, List<GetWhiteBoardDatum> getWhiteBoardData) {
            this.context = context;
        }

        public void filter(String text) {
            text = text.trim().toLowerCase();
            if (text.length() == 0) {
                WhiteBoardDataLocal = new ArrayList<>(getWhiteBoardData);
            } else {
                WhiteBoardDataLocal = new ArrayList<>();
                for (int i = 0; i < getWhiteBoardData.size(); i++) {
                    String name = "";
                    name = UtilityClass.getNameAthlete(getWhiteBoardData.get(i).getFirstName(),
                            getWhiteBoardData.get(i).getLastName(),
                            getWhiteBoardData.get(i).getEmailId());
                    if (name.toLowerCase().contains(text)) {
                        WhiteBoardDataLocal.add(getWhiteBoardData.get(i));
                    }
//                    if (!getWhiteBoardData.get(i).getFirstName().equalsIgnoreCase("")) {
//                        try {
//                            if (getWhiteBoardData.get(i).getFirstName().toLowerCase().contains(text) || getWhiteBoardData.get(i).getLastName().toLowerCase().contains(text)) {
//                                WhiteBoardDataLocal.add(getWhiteBoardData.get(i));
//                            }
//                        } catch (Exception b) {
//                        }
//                    } else {
//                        try {
//                            String emailName = getWhiteBoardData.get(i).getEmailId().toLowerCase();
//                            emailName = emailName.substring(0, emailName.indexOf("@"));
//
//                        } catch (Exception b) {
//                        }
//                    }
                }
            }
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public white_board_listViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.white_board_layout, viewGroup, false);
            return new white_board_listViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull white_board_listViewHolder holder, int position) {

            holder.rLayoutC.setOnClickListener(view -> {

                holder.mnc.getParent().requestDisallowInterceptTouchEvent(false);
                if (TrainingProgramId >= 0) {
                    startActivity(new Intent(getActivity(), CoachAddExerciseScreen.class)
                            .putExtra("TrainingId", TrainingProgramId + "")
                            .putExtra("AthleteId", WhiteBoardDataLocal.get(position).getUserId())
                            .putExtra("Screen", "GetWhiteBoardDatum"));
                    getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);

                    Log.d(VolleyLog.TAG, "*************** CoachAddExerciseScreen *************");

                }
            });

            holder.EditTraining.setOnClickListener(view -> {
                ShowAlertForEditTraining(context, view, "", WhiteBoardDataLocal.get(position).getUserId(), position);
            });

            try {
                MesurementArraySelectedList = new ArrayList<>();
                holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_blank));
                if (Pillar >= 0 && Types >= 0 && Exercise >= 0) {
                    for (int i1 = 0; i1 < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().size(); i1++) {
                        if (Types == Integer.parseInt(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getId())) {
                            for (int i2 = 0; i2 < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().size(); i2++) {
                                if (WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getStatus().equalsIgnoreCase("1")) {
                                    if (Exercise == Integer.parseInt(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getIdAuto())) {
                                        holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_green));
                                    }
                                }
                                if (Exercise == Integer.parseInt(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getIdAuto())) {
                                    //showDialogBox();
                                }
                            }
                        }
                    }
                    try {
                        addIntoMesurementArraySelectedList(position);
                    } catch (Exception m) {
                        Log.e(VolleyLog.TAG, "onBindViewHolder: " + m);
                    }
                } else if (Pillar >= 0 && Types >= 0 && Exercise <= 0) {
                    for (int i1 = 0; i1 < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().size(); i1++) {
                        if (Types == Integer.parseInt(WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getId())) {
                            if (WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getStatus().equalsIgnoreCase("1")) {
                                holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_green));
                                break;
                            } else {
                                holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_blank));
                                for (int i = 0; i < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().size(); i++) {
                                    if (WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i).getStatus().equalsIgnoreCase("1")) {
                                        holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_orange));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    try {
                        addIntoMesurementArraySelectedList(position);
                    } catch (Exception m) {
                        Log.e(VolleyLog.TAG, "onBindViewHolder: " + m);
                    }
                } else if (Pillar >= 0 && Types <= 0 && Exercise <= 0) {
                    if (WhiteBoardDataLocal.get(position).get0().get(Pillar).getPillarStatus().equalsIgnoreCase("1")) {
                        holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_green));
                    } else {
                        holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_blank));
                        for (int i = 0; i < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().size(); i++) {
                            for (int i1 = 0; i1 < WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i).getExcersice().size(); i1++) {
                                if (WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i).getExcersice().get(i1).getStatus().equalsIgnoreCase("1")) {
                                    holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_orange));
                                    break;
                                }
                            }
                        }
                    }
                    try {
                        addIntoMesurementArraySelectedList(position);
                    } catch (Exception m) {
                        Log.e(VolleyLog.TAG, "onBindViewHolder: " + m);
                    }
                } else {
                    Boolean IsCompleted = false;
                    Boolean IsinCompleted = false;
                    for (int i = 0; i < WhiteBoardDataLocal.get(position).get0().size(); i++) {
                        for (int i1 = 0; i1 < WhiteBoardDataLocal.get(position).get0().get(i).getExcerciseAllTypes().size(); i1++) {
                            for (int i2 = 0; i2 < WhiteBoardDataLocal.get(position).get0().get(i).getExcerciseAllTypes().get(i1).getExcersice().size(); i2++) {
                                if (WhiteBoardDataLocal.get(position).get0().get(i).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getStatus().equalsIgnoreCase("1")) {
                                    IsCompleted = true;
                                } else {
                                    IsinCompleted = true;
                                }
                                if (IsCompleted && IsinCompleted) {
                                    holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_orange));
                                    break;
                                }
                            }
                        }
                    }
                    try {
                        addIntoMesurementArraySelectedList(position);
                    } catch (Exception m) {
                        Log.e(VolleyLog.TAG, "onBindViewHolder: " + m);
                    }
                    Log.e(VolleyLog.TAG, "onBindViewHolder: ");
                    if (IsCompleted && !IsinCompleted) {
                        holder.Circle.setImageDrawable(getResources().getDrawable(R.drawable.circle_green));
                    }

                }
            } catch (Exception v) {
            }

            try {
                holder.Level1.setText(" - " + WhiteBoardDataLocal.get(position).getSelectedAthleteLevel().get(0).getAthleteLevel());
            } catch (Exception c) {
                //holder.Level1.setText("-");
            }


            try {
                holder.Level5.setOnClickListener(view -> {
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    x = location[0];
                    y = location[1];

                    Log.e(VolleyLog.TAG, "onBindViewHolder: " + Pillar + " " + Types + " " + Exercise);


                    int positionCC = position;
                    if (Pillar >= 0 && Types >= 0 && Exercise >= 0) {
                        for (int i1 = 0; i1 < WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().size(); i1++) {
                            for (int i2 = 0; i2 < WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().size(); i2++) {
                                if (Exercise == Integer.parseInt(WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getIdAuto())) {
                                    if (WhiteBoardDataLocal.get(position).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getWhiteboardNotes().equalsIgnoreCase("")) {
                                        showDialogmn(view, context, x, y,
                                                "ADD WHITE BOARD NOTES",
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getExerciseNotesDetail(),
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getExerciseNotesId(),
                                                WhiteBoardDataLocal.get(positionCC).getUserId(),
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getId(),
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getTrainingProgramDetailId(),
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice(), i2, positionCC);

                                    } else {
                                        showDialogmn(view, context, x, y,
                                                "ADD WHITE BOARD NOTES",
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getWhiteboardNotes(),
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getWhiteboardNotesId(),
                                                WhiteBoardDataLocal.get(positionCC).getUserId(),
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getId(),
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice().get(i2).getTrainingProgramDetailId(),
                                                WhiteBoardDataLocal.get(positionCC).get0().get(Pillar).getExcerciseAllTypes().get(i1).getExcersice(), i2, positionCC);
                                    }
                                }
                            }
                        }
                        try {
                            addIntoMesurementArraySelectedList(position);
                        } catch (Exception m) {
                            Log.e(VolleyLog.TAG, "onBindViewHolder: " + m);
                        }
                    } else {
                        UtilityClass.showAlertDailog(context, "Please select exercise.");
                    }

                });
            } catch (Exception c) {
                //holder.Level5.setText("-");
            }


            //showDialog

            try {
                holder.Level2Text.setText(MesurementArraySelectedList.get(0).getMeasurementName());
                holder.Level3.setText(MesurementArraySelectedList.get(0).getMeasurementValue().get(Set).getMeasurementValue());


                holder.Level3Text.setText(MesurementArraySelectedList.get(1).getMeasurementName());
                holder.Level4.setText(MesurementArraySelectedList.get(1).getMeasurementValue().get(Set).getMeasurementValue());

            } catch (Exception m) {
            }

            try {
//                if (WhiteBoardDataLocal.get(position).getFirstName().equalsIgnoreCase("")) {
//                    try {
//                        holder.textViewAthleteName.setText(WhiteBoardDataLocal.get(position).getEmailId().substring(0, WhiteBoardDataLocal.get(position).getEmailId().indexOf("@")));
//                    } catch (Exception b) {
//                    }
//                } else {
//                    holder.textViewAthleteName.setText(WhiteBoardDataLocal.get(position).getLastName() + " " + WhiteBoardDataLocal.get(position).getFirstName());
//                }
                holder.textViewAthleteName.setText(UtilityClass.getNameAthlete(WhiteBoardDataLocal.get(position).getFirstName(), WhiteBoardDataLocal.get(position).getLastName(),
                        WhiteBoardDataLocal.get(position).getEmailId()));
            } catch (Exception v) {
            }


            try {
                if (WhiteBoardDataLocal.get(position).getUserImage().equalsIgnoreCase("")) {
                    holder.imageViewProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.logo));
                } else {
                    Glide.with(context).load(
                            WebServices.BASE_URL_FOR_IMAGES + WhiteBoardDataLocal.get(position).getUserImage()).error(getResources().getDrawable(R.drawable.logo))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.imageViewProfilePic);
                }
            } catch (Exception v) {
            }

        }

        @Override
        public int getItemCount() {
            return WhiteBoardDataLocal.size();
        }

        public class white_board_listViewHolder extends RecyclerView.ViewHolder {
            ImageView imageViewProfilePic, Circle;

            LinearLayout Level5;
            TextView textViewAthleteName, Level1, Level2, Level3, Level4, Level2Type;

            TextView LevelText, Level1Text, Level2Text, Level3Text, Level4Text;

            RelativeLayout rLayoutC, EditTraining;

            LinearLayout mnc;

            public white_board_listViewHolder(@NonNull View itemView) {
                super(itemView);
                imageViewProfilePic = itemView.findViewById(R.id.imageViewProfilePic);
                textViewAthleteName = itemView.findViewById(R.id.textViewAthleteName);
                Level1 = itemView.findViewById(R.id.Level1);
                Level2 = itemView.findViewById(R.id.Level2);
                Level3 = itemView.findViewById(R.id.Level3);
                Level4 = itemView.findViewById(R.id.Level4);
                Level5 = itemView.findViewById(R.id.Level5);
                EditTraining = itemView.findViewById(R.id.EditTraining);

                mnc = itemView.findViewById(R.id.mnc);


                LevelText = itemView.findViewById(R.id.LevelText);
                Level1Text = itemView.findViewById(R.id.Level1Text);
                Level2Text = itemView.findViewById(R.id.Level2Text);
                Level3Text = itemView.findViewById(R.id.Level3Text);
                Level4Text = itemView.findViewById(R.id.Level4Text);


                Level2Type = itemView.findViewById(R.id.Level2Type);
                Circle = itemView.findViewById(R.id.Circle);
                rLayoutC = itemView.findViewById(R.id.rLayoutC);
                textViewAthleteName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Level1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Level2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Level3.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Level4.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                //Level5.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                Level2Type.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                LevelText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Level1Text.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Level2Text.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Level3Text.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Level4Text.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                // Level1.setSelected(true);
            }
        }
    }

    public class school_filter_list extends RecyclerView.Adapter<school_filter_list.school_filter_Holder> {
        Context context;

        public school_filter_list(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public school_filter_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.radio_button_layout, viewGroup, false);
            return new school_filter_Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull school_filter_Holder holder, int position) {


            holder.schoolName.setTextColor(getResources().getColor(R.color.color_white));
            holder.unselected_tick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
            holder.unselected_tick.setTag("uncheck");
            for (int i = 0; i < arrayListSchool.size(); i++) {
                if (arrayListSchool.get(i).equalsIgnoreCase(getSchoolsList.get(position).getSchoolId())) {
                    holder.schoolName.setTextColor(getResources().getColor(R.color.textColorYellow));
                    holder.unselected_tick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                    holder.unselected_tick.setTag("check");
                }
            }
            holder.ExcersiseSubmitButtonX.setOnClickListener(view -> {
                SelectedSchoolId = "";
                StringBuilder stringBuilder = new StringBuilder();
                if (holder.unselected_tick.getTag().toString().equalsIgnoreCase("uncheck")) {
                    arrayListSchool.add(getSchoolsList.get(position).getSchoolId() + "");
                    holder.schoolName.setTextColor(getResources().getColor(R.color.textColorYellow));
                    holder.unselected_tick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                    holder.unselected_tick.setTag("check");
                } else {
                    if (arrayListSchool.size() > 1) {
                        holder.unselected_tick.setTag("uncheck");
                        holder.unselected_tick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
                        arrayListSchool.remove(getSchoolsList.get(position).getSchoolId());
                        holder.schoolName.setTextColor(getResources().getColor(R.color.color_white));

                    }

                }


                for (int i = 0; i < arrayListSchool.size(); i++) {
                    SelectedSchoolId = stringBuilder.append(arrayListSchool.get(i) + ",").toString();
                }
                if (SelectedSchoolId != null && SelectedSchoolId.length() > 0 && SelectedSchoolId.charAt(SelectedSchoolId.length() - 1) == ',') {
                    SelectedSchoolId = SelectedSchoolId.substring(0, SelectedSchoolId.length() - 1);
                }
                notifyDataSetChanged();
                //imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.save_training));
                removeSchoolFilter = true;
            });
            holder.schoolName.setText(getSchoolsList.get(position).getSchoolName());
        }


        @Override
        public int getItemCount() {
            return getSchoolsList.size();
        }

        public class school_filter_Holder extends RecyclerView.ViewHolder {
            RelativeLayout ExcersiseSubmitButtonX;
            ImageView unselected_tick;

            TextView schoolName;

            public school_filter_Holder(@NonNull View itemView) {
                super(itemView);
                ExcersiseSubmitButtonX = itemView.findViewById(R.id.ExcersiseSubmitButtonX);
                schoolName = itemView.findViewById(R.id.schoolName);
                unselected_tick = itemView.findViewById(R.id.unselected_tick);


            }
        }
    }


    public class WhiteBoardFilter extends RecyclerView.Adapter<WhiteBoardFilterViewHolder2> {
        int Position;
        Context context;

        public WhiteBoardFilter(int Position, Context context) {
            this.Position = Position;
            this.context = context;
        }

        @Override
        public WhiteBoardFilterViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.three_item_layout, viewGroup, false);
            return new WhiteBoardFilterViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final WhiteBoardFilterViewHolder2 Holder, final int i) {
            try {
                if (ShowDialogOf.equalsIgnoreCase("Filter")) {
                    Holder.LevelImage.setVisibility(View.GONE);
                    Holder.arrow1.setVisibility(View.GONE);
                    if (Position == 0) {
                        Holder.LevelImage.setVisibility(VISIBLE);
                        Holder.LevelImage.setColorFilter(Color.rgb(237, 187, 87));
                        Glide.with(context).load(WebServices.BASE_URL_FOR_IMAGES_ASSETS + "/pillar_images" + PillarCLASS.get(i).getPillarImage()).into(Holder.LevelImage);
                        Holder.LevelText.setText(PillarCLASS.get(i).getPillerName() + "");
                    }
                    if (Position == 1) {
                        Holder.LevelText.setPadding(5, 0, 0, 0);
                        Holder.LevelText.setText(exerciseTypeNameWhiteBoard.get(i).getName() + "");
                    }
                    if (Position == 2) {
                        Holder.LevelText.setPadding(5, 0, 0, 0);
                        Holder.LevelText.setText(excersiceListWhiteBoard.get(i).getExerciseName() + "");
                    }
                    if (Position == 3) {
                        Holder.LevelText.setPadding(5, 0, 0, 0);
                        Holder.LevelText.setText((i + 1) + "");
                    }
                    Holder.LevelLayout.setOnClickListener(view -> {
                        if (Position == 0) {
                            PillarOption.setVisibility(VISIBLE);
                            PillarText.setText(Holder.LevelText.getText().toString());
                            //PillarText.setTag(pillar.get(Pillar).getNumber());
                            Pillar = i;

                            Types = -1;
                            TypeOption.setVisibility(View.GONE);
                            TypeText.setText("Type");

                            Exercise = -1;
                            ExerciseOption.setVisibility(View.GONE);
                            ExerciseText.setText("Exercise");

                            Set = 0;
                            SETSIZE = 0;
                            SetOption.setVisibility(View.GONE);
                            SetText.setText("Set");


                            dialog.dismiss();
                            adapter.notifyDataSetChanged();

                            //exerciseTypeNameWhiteBoard =   WhiteBoardDataLocal.stream().filter(c-> return c.get0().get(Pillar).getExcerciseAllTypes());
                            exerciseTypeNameWhiteBoard = new ArrayList<>();


                            for (int i1 = 0; i1 < WhiteBoardDataLocal.size(); i1++) {
                                for (int c = 0; c < WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().size(); c++) {
                                    exerciseTypeNameWhiteBoard.add(WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().get(c));
                                }
                            }


                            for (int x = 0; x < exerciseTypeNameWhiteBoard.size(); x++) {
                                for (int j = x + 1; j < exerciseTypeNameWhiteBoard.size(); j++) {
                                    if (exerciseTypeNameWhiteBoard.get(x).getId().equalsIgnoreCase(exerciseTypeNameWhiteBoard.get(j).getId())) {
                                        exerciseTypeNameWhiteBoard.remove(j);
                                        j--;
                                    }
                                }
                            }


                        }
                        if (Position == 1) {
                            TypeOption.setVisibility(VISIBLE);
                            TypeText.setText(Holder.LevelText.getText().toString());
                            Types = Integer.parseInt(exerciseTypeNameWhiteBoard.get(i).getId());
                            dialog.dismiss();

                            Exercise = -1;
                            ExerciseOption.setVisibility(View.GONE);
                            ExerciseText.setText("Exercise");

                            Set = 0;
                            SETSIZE = 0;
                            SetOption.setVisibility(View.GONE);
                            SetText.setText("Set");

                            adapter.notifyDataSetChanged();

                            excersiceListWhiteBoard = new ArrayList<>();
                            for (int i1 = 0; i1 < WhiteBoardDataLocal.size(); i1++) {
                                for (int c = 0; c < WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().size(); c++) {
                                    if (exerciseTypeNameWhiteBoard.get(i).getId().equalsIgnoreCase(WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().get(c).getId())) {
                                        for (int i2 = 0; i2 < WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().get(c).getExcersice().size(); i2++) {
                                            excersiceListWhiteBoard.add(WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().get(c).getExcersice().get(i2));
                                        }
                                    }
                                }
                            }

                            // excersiceListWhiteBoard = exerciseTypeNameWhiteBoard.stream().filter(c-> Types == Integer.parseInt(c.getId()) ).collect(Collectors.toList());

                            for (int x = 0; x < excersiceListWhiteBoard.size(); x++) {
                                for (int j = x + 1; j < excersiceListWhiteBoard.size(); j++) {
                                    if (excersiceListWhiteBoard.get(x).getExerciseName().equalsIgnoreCase(excersiceListWhiteBoard.get(j).getExerciseName())) {
                                        excersiceListWhiteBoard.remove(j);
                                        j--;
                                    }
                                }
                            }

                            /*For Filter*/
                        }
                        if (Position == 2) {
                            Log.e(VolleyLog.TAG, "onBindViewHolder:this is no 3 ");
                            ExerciseOption.setVisibility(VISIBLE);
                            ExerciseText.setText(Holder.LevelText.getText().toString());
                            ExerciseName = "Edit - " + Holder.LevelText.getText().toString();
                            if (ExerciseName.equalsIgnoreCase("")) {
                                ExerciseName = "";
                            }
                            Exercise = Integer.parseInt(excersiceListWhiteBoard.get(i).getIdAuto());
                            Log.e(VolleyLog.TAG, "onBindViewHolder: " + Exercise);
                            dialog.dismiss();

                            Set = 0;
                            SETSIZE = 0;
                            SetOption.setVisibility(View.GONE);
                            SetText.setText("Set");
                            adapter.notifyDataSetChanged();


                            measurement1 = new ArrayList<>();
                            for (int i1 = 0; i1 < WhiteBoardDataLocal.size(); i1++) {
                                for (int c = 0; c < WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().size(); c++) {
                                    if (Types == Integer.parseInt(WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().get(c).getId())) {
                                        for (int i2 = 0; i2 < WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().get(c).getExcersice().size(); i2++) {
                                            if (Exercise == Integer.parseInt(WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().get(c).getExcersice().get(i2).getIdAuto())) {
                                                Log.e(VolleyLog.TAG, "measurement1: " + i1 + "  " + c + " " + i2);
                                                measurement1 = new ArrayList<>(WhiteBoardDataLocal.get(i1).get0().get(Pillar).getExcerciseAllTypes().get(c).getExcersice().get(i2).getMeser());
                                            }
                                        }
                                    }
                                }
                            }

                            for (int x = 0; x < measurement1.size(); x++) {
                                for (int j = x + 1; j < measurement1.size(); j++) {
                                    if (measurement1.get(x).getId().equalsIgnoreCase(measurement1.get(j).getId())) {
                                        measurement1.remove(j);
                                        j--;
                                    }
                                }
                            }


                            for (int i1 = 0; i1 < measurement1.size(); i1++) {
                                if (measurement1.get(i1).getMeasurementId().equalsIgnoreCase("5")) {
                                    SETSIZE = measurement1.get(i1).getMeasurementValue().size();
                                }
                            }
                        }
                        if (Position == 3) {
                            SetOption.setVisibility(VISIBLE);
                            SetText.setText("Set " + Holder.LevelText.getText().toString());
                            //SetText.setTag();
                            dialog.dismiss();
                            Set = i;

                            adapter.notifyDataSetChanged();

                            try {
                                if (measurement.get(Set).getMeasurementId().equalsIgnoreCase("6")) {
                                    WeightOf = measurement.get(Set).getMeasurementValue().get(Set).getMeasurementValue();
                                } else {
                                    WeightOf = "-";
                                }


                            } catch (Exception b) {
                                //WeightOf = "-";
                            }
                        }
                    });


                } else if (ShowDialogOf.equalsIgnoreCase("Sports")) {
                    Holder.LevelText.setVisibility(View.VISIBLE);
                    Holder.LevelImage.setVisibility(View.GONE);

                    Holder.LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));
                    Holder.LevelText.setText(getTeams.get(ActiveId).getTeamSports().get(i).getSportName());
                    Holder.arrow1.setVisibility(View.GONE);

                    for (int vc = 0; vc < SportsFilterArrayList.size(); vc++) {
                        if (Integer.parseInt(getTeams.get(ActiveId).getTeamSports().get(i).getSportId()) == Integer.parseInt(SportsFilterArrayList.get(vc))) {
                            Holder.rightSign.setVisibility(View.VISIBLE);
                        }
                    }

                    Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (Holder.rightSign.getVisibility() == VISIBLE) {
                                Holder.rightSign.setVisibility(View.GONE);
                                String GetID = getTeams.get(ActiveId).getTeamSports().get(i).getSportId();
                                SportsFilterArrayList.remove(GetID);
                            } else {
                                Holder.rightSign.setVisibility(View.VISIBLE);
                                String GetID = getTeams.get(ActiveId).getTeamSports().get(i).getSportId();
                                SportsFilterArrayList.add(GetID);
                            }
                            Log.e(VolleyLog.TAG, "onClick: " + SportsFilterArrayList.size());
                        }
                    });
                } else if (ShowDialogOf.equalsIgnoreCase("TeamAssignProgram")) {
                    Holder.rLayoutForAthleteTraining.setVisibility(View.VISIBLE);
                    Holder.textViewExerciseName.setText(TeamAssignProgram.get(i).getProgramName());
                    Holder.textViewExerciseDate.setText(TeamAssignProgram.get(i).getStartDate());
                    Holder.textViewExerciseName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                    Holder.textViewExerciseDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                    Holder.rLayoutForAthleteTraining.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            textTPName = TeamAssignProgram.get(i).getProgramName();

                            textViewTPName.setText(textTPName);

                            textViewTPName.setVisibility(VISIBLE);

                            TrainingProgramId = Integer.parseInt(TeamAssignProgram.get(i).getAssignProgramId());

                            TrainingProgramIdForWhiteBoard = Integer.parseInt(TeamAssignProgram.get(i).getId());

                            dialog.dismiss();

                            SendDataOnServer();
                        }
                    });
                }
//                else {
//                    Holder.rLayoutForAthleteTraining.setVisibility(View.VISIBLE);
//                    Holder.textViewExerciseName.setText(getTeams.get(ActiveId).getTrainingProgramDetail().get(i).getProgramName());
//                    Holder.textViewExerciseDate.setText(getTeams.get(ActiveId).getTrainingProgramDetail().get(i).getStartDate());
//                    Holder.textViewExerciseName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//                    Holder.textViewExerciseDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//                    Holder.rLayoutForAthleteTraining.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            textTPName = getTeams.get(ActiveId).getTrainingProgramDetail().get(i).getProgramName();
//
//                            textViewTPName.setText(textTPName);
//
//                            textViewTPName.setVisibility(VISIBLE);
//
//                            TrainingProgramId = Integer.parseInt(getTeams.get(ActiveId).getTrainingProgramDetail().get(i).getAssignProgramId());
//
//                            TrainingProgramIdForWhiteBoard = Integer.parseInt(getTeams.get(ActiveId).getTrainingProgramDetail().get(i).getId());
//
//                            dialog.dismiss();
//
//                            SendDataOnServer();
//                        }
//                    });
//                }
            } catch (Exception v) {

            }
        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                if (ShowDialogOf.equalsIgnoreCase("Filter")) {
                    if (Position == 0) {
                        count = PillarCLASS.size();
                    }
                    if (Position == 1) {
                        count = exerciseTypeNameWhiteBoard.size();
                    }
                    if (Position == 2) {
                        count = excersiceListWhiteBoard.size();
                    }
                    if (Position == 3) {
                        count = SETSIZE;
                    }
                } else if (ShowDialogOf.equalsIgnoreCase("Sports")) {
                    count = getTeams.get(ActiveId).getTeamSports().size();
                } else if (ShowDialogOf.equalsIgnoreCase("TeamAssignProgram")) {
                    count = TeamAssignProgram.size();
                }
//                else {
//                    count = getTeams.get(ActiveId).getTrainingProgramDetail().size();
//                }
            } catch (Exception b) {
                count = 0;

            }
            return count;
        }


    }

    private class WhiteBoardFilterViewHolder2 extends RecyclerView.ViewHolder {
        TextView LevelText, AtheleteLevelExerciseName, textViewExerciseName, textViewExerciseDate,
                AtheleteLevelExerciseValues, AtheleteExerciseValueMultiple, MultiplyValueInc, MultiplyValueDcr;
        ImageView LevelImage, arrow1, rightSign, SaveEventDialog;
        RelativeLayout LevelLayout, rAthleteLevelExercise, rLayoutForAthleteTraining;

        public WhiteBoardFilterViewHolder2(@NonNull View itemView) {
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
            SaveEventDialog = itemView.findViewById(R.id.SaveEventDialog);


            rLayoutForAthleteTraining = itemView.findViewById(R.id.rLayoutForAthleteTraining);


            arrow1.setColorFilter(Color.argb(255, 255, 255, 255));
            LevelImage.setColorFilter(Color.argb(255, 255, 255, 255));

            arrow1.getLayoutParams().width = pxToDp(100);
            arrow1.getLayoutParams().height = pxToDp(100);


            LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));

            Glide.with(context).load(getResources().getDrawable(R.drawable.img26)).into(LevelImage);

        }
    }

    private class TeamData extends RecyclerView.Adapter<TeamData.TeamDataHolder> {
        int Y;
        Context context;
        int position;

        public TeamData(int Y, Context context, int position) {
            this.Y = Y;
            this.context = context;
            this.position = position;
        }

        @Override
        public TeamData.TeamDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.team_name_list, viewGroup, false);
            return new TeamData.TeamDataHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TeamData.TeamDataHolder holder, int i) {

            if (getTeams.get(i).getTeamName() != null && getTeams.get(i).getTeamName().equalsIgnoreCase("All Athlete")) {
                holder.rLayoutofTeam.setVisibility(View.GONE);
            } else {
                holder.rLayoutofTeam.setVisibility(VISIBLE);
            }


//            if(userTypeOf.equalsIgnoreCase("1") ||userTypeOf.equalsIgnoreCase("2")|| userTypeOf.equalsIgnoreCase("3")  ){
//                if(SchoolDataService.GetTeam.get(i).getTeamId().equalsIgnoreCase("0")){
//                    holder.teamName.setText(SchoolDataService.GetTeam.get(i).getTeamName());
//                }else {
//                    holder.teamName.setText(SchoolDataService.GetTeam.get(i).getTeamName() + " - "+ SchoolDataService.GetTeam.get(i).getSchoolName());
//                }
//            }
//            else {
            holder.teamName.setText(getTeams.get(i).getTeamName());
            // }
            try {
                if (teamId.equalsIgnoreCase(getTeams.get(i).getTeamId())) {
                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_bg_yellow);
                    holder.teamName.setTextColor(Color.BLACK);

                    ActiveId = i;


                } else {
                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
                    holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
                }


            } catch (Exception v) {

            }

            holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.rLayoutofTeam.setOnClickListener(view -> {
                if (ActiveId == i) {
//                    ShowDialogOf = "Training";
//                    showDialogBox(view, "TRAINING PROGRAMS", 0);

                    ShowDialogOf = "TeamAssignProgram";
                    showDialogBox(view, "TRAINING PROGRAMS", 0);
                } else {

                    try {
                        ActiveId = i;
                        teamId = getTeams.get(i).getTeamId();
                        ShowDialogOf = "TeamAssignProgram";
                        ListOfWhiteBoard.setAdapter(null);
                        ResetFields();
                        whichApiCalled = "GetWhiteBoardDataWithoutTraining";
                        WebServices webServices = new WebServices();
                        webServices.GetWhiteBoardData("", teamId, sports_IDs, formattedDate, context, WhiteBoard.this);
                        //getAthleteDataFromServer();
                        notifyDataSetChanged();
                        textTPName = "";
                        textViewTPName.setText(textTPName);
                        textViewTPName.setVisibility(GONE);
                    } catch (Exception b) {
                    }
                }
            });

            holder.rLayoutofTeam.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ShowDialogOf = "Sports";
                    showDialogBox(view, "Sports", 0);
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            int itemCount = 0;
            try {
                itemCount = getTeams.size();
            } catch (Exception x) {
                //SaveEventDialog.setVisibility(View.INVISIBLE);
            }
            return itemCount;
        }

        private class TeamDataHolder extends RecyclerView.ViewHolder {
            TextView teamName;
            RelativeLayout rLayoutofTeam;

            public TeamDataHolder(@NonNull View itemView) {
                super(itemView);
                teamName = itemView.findViewById(R.id.teamName);
                rLayoutofTeam = itemView.findViewById(R.id.rLayoutofTeam);
            }
        }
    }

    public class AthleteTrainingDataUpdateWhiteBoard extends RecyclerView.Adapter<AthleteTrainingDataUpdateWhiteBoard.AthleteTrainingViewHolder> {
        int Y;
        int position;
        int X;
        String userId;

        public AthleteTrainingDataUpdateWhiteBoard(String userId) {
            this.userId = userId;
        }

        @NonNull
        @Override
        public AthleteTrainingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Context context = viewGroup.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.excersise_dose_list, viewGroup, false);

            return new AthleteTrainingViewHolder(view, Y, position);
        }


        @Override
        public void onBindViewHolder(@NonNull final AthleteTrainingViewHolder Holder, final int i) {
            Context context = Holder.itemView.getContext();

            int count = i;
            count = count + 1;
            Holder.OpenAddset.setVisibility(GONE);
            Holder.setName.setText("SET " + count);
            Log.e(VolleyLog.TAG, "onBindViewHolder:indexofMeasurementofI " + indexofMeasurementofI);
            try {
                Holder.edittext1.setText(String.valueOf(Math.round(Float.parseFloat(measurement1.get(0).getMeasurementValue().get(i).getMeasurementValue()))));
                Holder.edittext1.setBackground(getResources().getDrawable(R.drawable.bg_dark_semi_rounded));
                Holder.edittext1.setVisibility(VISIBLE);
                Holder.addsetAthleteExcersiseDose.setMinimumHeight(dpToPx(42));
                Holder.addsetAthleteExcersiseDose.getLayoutParams().height = dpToPx(42);
            } catch (Exception x) {

            }


            try {
                Holder.edittext2.setText(String.valueOf(Math.round(Float.parseFloat(measurement1.get(1).getMeasurementValue().get(i).getMeasurementValue()))));
                Holder.edittext2.setVisibility(VISIBLE);
                Holder.edittext2.setBackground(getResources().getDrawable(R.drawable.bg_dark_semi_rounded));
            } catch (Exception x) {

            }

            try {
                Holder.edittext3.setText(String.valueOf(Math.round(Float.parseFloat(measurement1.get(2).getMeasurementValue().get(i).getMeasurementValue()))));
                // Holder.Repsvalue.setText(exercises.get(Y).getSetLevelMultiple().get(i).getRepsValue());
                Holder.edittext3.setVisibility(VISIBLE);
                Holder.edittext3.setBackground(getResources().getDrawable(R.drawable.bg_dark_semi_rounded));
                Holder.addsetAthleteExcersiseDose.setMinimumHeight(dpToPx(42) * 3);
            } catch (Exception x) {

            }

            try {
                Holder.edittext4.setText(String.valueOf(Math.round(Float.parseFloat(measurement1.get(3).getMeasurementValue().get(i).getMeasurementValue()))));
                // Holder.Repsvalue.setText(exercises.get(Y).getSetLevelMultiple().get(i).getRepsValue());
                Holder.edittext4.setVisibility(VISIBLE);
                Holder.edittext4.setBackground(getResources().getDrawable(R.drawable.bg_dark_semi_rounded));
                Holder.addsetAthleteExcersiseDose.setMinimumHeight(dpToPx(42) * 4);
            } catch (Exception x) {

            }

            try {
                Holder.edittext5.setText(String.valueOf(Math.round(Float.parseFloat(measurement1.get(4).getMeasurementValue().get(i).getMeasurementValue()))));
                // Holder.Repsvalue.setText(exercises.get(Y).getSetLevelMultiple().get(i).getRepsValue());
                Holder.edittext5.setVisibility(VISIBLE);
                Holder.edittext5.setBackground(getResources().getDrawable(R.drawable.bg_dark_semi_rounded));
                Holder.addsetAthleteExcersiseDose.setMinimumHeight(dpToPx(42) * 5);
            } catch (Exception x) {

            }


            Holder.addsetAthleteExcersiseDose.getLayoutParams().height = dpToPx(42) * indexofMeasurementofI;
            if (indexofMeasurementofI == 1) {
                Holder.addsetAthleteExcersiseDose.getLayoutParams().height = dpToPx(36) * indexofMeasurementofI;
            }


            if (count == getItemCount()) {
                Holder.addsetAthleteExcersiseDose.setVisibility(VISIBLE);
                Holder.addsetAthleteExcersiseDose.setBackground(getResources().getDrawable(R.drawable.bg_dark_semi_rounded));
            } else {
                Holder.addsetAthleteExcersiseDose.setVisibility(GONE);
            }

            Holder.addsetAthleteExcersiseDose.setPadding(3, 0, 0, 0);

            Holder.addsetAthleteExcersiseDose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String assign_program_id = TrainingProgramId + "";
                    String Athlete_id = userId;
                    String training_exercise_id = measurement1.get(0).getTrainingExerciseId();
                    if (indexofMeasurementofI == 1) {
                        String measurement_id = measurement1.get(0).getMeasurementId();
                        try {
                            measurement1.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                    }
                    if (indexofMeasurementofI == 2) {
                        try {
                            measurement1.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(1).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                    }
                    if (indexofMeasurementofI == 3) {
                        try {
                            measurement1.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(1).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(2).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(2).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                    }
                    if (indexofMeasurementofI == 4) {
                        try {
                            measurement1.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(1).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(2).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(2).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(3).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(3).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                    }
                    if (indexofMeasurementofI == 5) {
                        try {
                            measurement1.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(1).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(2).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(2).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(3).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(3).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                        try {
                            measurement1.get(4).getMeasurementValue().add(new MeasurementValue("0", measurement1.get(0).getTrainingExerciseId(), measurement1.get(4).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0"));
                        } catch (Exception x) {
                        }
                    }
                    notifyDataSetChanged();
                }
            });

            Holder.edittext1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE ||
                            keyEvent.getAction() == KeyEvent.ACTION_DOWN || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
//                        TypeIndex = X;
                        hideSoftKeyboard();
//                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext1.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
//                    TypeIndex = X;
//                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 1");
                } else {
                    MagicTouch();
                }
            });
//
            Holder.edittext2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                        hideSoftKeyboard();
//                        TypeIndex = X;
//                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext2.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
//                    TypeIndex = X;
//                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 2");
                } else {
                    MagicTouch();
                }
            });
//
            Holder.edittext3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
//                        TypeIndex = X;
                        hideSoftKeyboard();
//                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext3.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
//                    TypeIndex = X;
//                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 3");
                }
            });
//
            Holder.edittext4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
//                        TypeIndex = X;

//                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext5.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
//                    TypeIndex = X;
//                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 4");
                }
            });

            Holder.edittext5.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
//                        TypeIndex = X;
                        hideSoftKeyboard();
//                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext4.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
//                    TypeIndex = X;
//                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 4");
                }
            });

        }


        @Override
        public int getItemCount() {
            int s = 0;
            try {
                s = measurement1.get(0).getMeasurementValue().size();
            } catch (Exception x) {
            }
            return s;
        }

        private void sendValueonServerAddSet(AthleteTrainingViewHolder Holder, int POSITION) {
            removeFocus();

            String assign_program_id = TrainingProgramId + "";
            String Athlete_id = userId;
            String training_exercise_id = measurement1.get(0).getTrainingExerciseId();

            String measurement_id_send = "";
            StringBuilder measurement_id_send_autoBuilder = new StringBuilder();

            String custom_dose_coach_id_auto = "";
            StringBuilder custom_dose_coach_id_autoBuilder = new StringBuilder();

            String MeasurementValue = "";
            StringBuilder MeasurementValueBuilder = new StringBuilder();

            String custom_dose_coach_id_auto_copy = "";
            StringBuilder custom_dose_coach_id_auto_copyBuilder = new StringBuilder();


            for (int c = 0; c < indexofMeasurementofI; c++) {
                if (measurement1.get(c).getMeasurementId() != null) {
                    measurement_id_send = measurement_id_send_autoBuilder.append(measurement1.get(c).getMeasurementId() + ",").toString();
                } else {
                    measurement_id_send = measurement_id_send_autoBuilder.append("0,").toString();
                }

                if (measurement1.get(c).getMeasurementValue().get(POSITION).getAthleteCustomMeasurementValuesAutoId() != null) {
                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append(measurement1.get(c).getMeasurementValue().get(POSITION).getAthleteCustomMeasurementValuesAutoId() + ",").toString();
                } else {
                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                }

                if (measurement1.get(c).getMeasurementValue().get(POSITION).getCustomDoseCoachIdAuto() != null) {
                    custom_dose_coach_id_auto = custom_dose_coach_id_autoBuilder.append(measurement1.get(c).getMeasurementValue().get(POSITION).getCustomDoseCoachIdAuto() + ",").toString();
                } else if (measurement1.get(c).getMeasurementValue().get(POSITION).getIdAuto() != null) {
                    custom_dose_coach_id_auto = custom_dose_coach_id_autoBuilder.append(measurement1.get(c).getMeasurementValue().get(POSITION).getIdAuto() + ",").toString();
                } else {
                    custom_dose_coach_id_auto = custom_dose_coach_id_autoBuilder.append("0,").toString();
                }

            }


            if (indexofMeasurementofI >= 1) {
                MeasurementValue = MeasurementValueBuilder.append(Holder.edittext1.getText().toString() + ",").toString();
                measurement1.get(0).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext1.getText().toString());
            }
            if (indexofMeasurementofI >= 2) {
                measurement1.get(1).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext2.getText().toString());
                MeasurementValue = MeasurementValueBuilder.append(Holder.edittext2.getText().toString() + ",").toString();
            }
            if (indexofMeasurementofI >= 3) {
                measurement1.get(2).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext3.getText().toString());
                MeasurementValue = MeasurementValueBuilder.append(Holder.edittext3.getText().toString() + ",").toString();
            }
            if (indexofMeasurementofI >= 4) {
                measurement1.get(3).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext4.getText().toString());
                MeasurementValue = MeasurementValueBuilder.append(Holder.edittext4.getText().toString() + ",").toString();
            }
            if (indexofMeasurementofI >= 5) {
                measurement1.get(4).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext5.getText().toString());
                MeasurementValue = MeasurementValueBuilder.append(Holder.edittext5.getText().toString() + ",").toString();
            }


            if (custom_dose_coach_id_auto != null && custom_dose_coach_id_auto.length() > 0 && custom_dose_coach_id_auto.charAt(custom_dose_coach_id_auto.length() - 1) == ',') {
                custom_dose_coach_id_auto = custom_dose_coach_id_auto.substring(0, custom_dose_coach_id_auto.length() - 1);
            }
            if (MeasurementValue != null && MeasurementValue.length() > 0 && MeasurementValue.charAt(MeasurementValue.length() - 1) == ',') {
                MeasurementValue = MeasurementValue.substring(0, MeasurementValue.length() - 1);
            }
            if (measurement_id_send != null && measurement_id_send.length() > 0 && measurement_id_send.charAt(measurement_id_send.length() - 1) == ',') {
                measurement_id_send = measurement_id_send.substring(0, measurement_id_send.length() - 1);
            }
            if (custom_dose_coach_id_auto_copy != null && custom_dose_coach_id_auto_copy.length() > 0 && custom_dose_coach_id_auto_copy.charAt(custom_dose_coach_id_auto_copy.length() - 1) == ',') {
                custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copy.substring(0, custom_dose_coach_id_auto_copy.length() - 1);
            }

            webServices = new WebServices();
            whichApiCalled = "Addset";
            hide();
            Log.e(VolleyLog.TAG, "sendValueonServerAddSet: ");
            // webServices.AddAndUpdateExerciseSet(custom_dose_coach_id_auto, measurement1.get(0).getTrainingExerciseId(), LoginScreen.userId, assign_program_id, measurement_id_send, MeasurementValue, custom_dose_coach_id_auto_copy, context, AthleteExerciseSetActivity.this);

            Log.e(VolleyLog.TAG, "sendValueonServerAddSet: " + showwaitIndicator);
            //showwaitIndicator  = false;
            webServices.AddAndUpdateExerciseSet(custom_dose_coach_id_auto + ""
                    , measurement1.get(0).getTrainingExerciseId() + "", userId + ""
                    , TrainingProgramId + "", measurement_id_send + "",
                    MeasurementValue + "", custom_dose_coach_id_auto_copy + "", context, WhiteBoard.this);
            hide();
            //notifyDataSetChanged();
        }


        public class AthleteTrainingViewHolder extends RecyclerView.ViewHolder {
            TextView setName;
            EditText edittext1, edittext2, edittext3, edittext4, edittext5;
            ImageView Selected, Unselected, OpenAddset;
            LinearLayout addsetAthleteExcersiseDose;
            RelativeLayout rLayoutx, Repsvaluex;

            public AthleteTrainingViewHolder(View itemView, final int y, int position) {
                super(itemView);
                edittext1 = itemView.findViewById(R.id.edittext1);
                edittext2 = itemView.findViewById(R.id.edittext2);
                edittext3 = itemView.findViewById(R.id.edittext3);
                edittext4 = itemView.findViewById(R.id.edittext4);
                edittext5 = itemView.findViewById(R.id.edittext5);
                OpenAddset = itemView.findViewById(R.id.OpenAddset);
                setName = itemView.findViewById(R.id.setName);
                addsetAthleteExcersiseDose = itemView.findViewById(R.id.addsetAthleteExcersiseDose);
                rLayoutx = itemView.findViewById(R.id.rLayoutx);
                Repsvaluex = itemView.findViewById(R.id.Repsvaluex);
                rLayoutx.getLayoutParams().width = dpToPx(70);
                Repsvaluex.getLayoutParams().width = dpToPx(70);
                edittext1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                edittext2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                edittext3.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                edittext4.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                edittext5.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                OpenAddset.setVisibility(VISIBLE);
            }
        }

    }


}