package com.org.godspeed.allOtherClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.android.volley.VolleyLog;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.godspeed.R;
import com.org.godspeed.baseActivity.BaseActivity;
import com.org.godspeed.response_JsonS.AddTraining.AddTrainingProgram;
import com.org.godspeed.response_JsonS.AddTraining.Exercise;
import com.org.godspeed.response_JsonS.AddTraining.Measurement;
import com.org.godspeed.response_JsonS.AddTraining.MeasurementValue;
import com.org.godspeed.response_JsonS.AddTraining.Type;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.PillarExerciseStatus;
import com.org.godspeed.response_JsonS.addIntoAthleteArray.ArrayofnewSetExersise;
import com.org.godspeed.service.NotificationScheduler;
import com.org.godspeed.service.OnSwipeListener;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.ExoPlayerActivity;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult;
import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthPermissionManager;
import com.samsung.android.sdk.healthdata.HealthResultHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.android.volley.VolleyLog.TAG;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
import static com.org.godspeed.fragments.AthleteProfileFragment.AvgHeartRate;
import static com.org.godspeed.fragments.AthleteProfileFragment.show_samsung_dialog;
import static com.org.godspeed.fragments.CoachBoardFragments.AthleteData;
import static com.org.godspeed.service.BackgroundLocationUpdateService.notification;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.utility.GlobalClass.PillarName;
import static com.org.godspeed.utility.GlobalClass.team_id;
import static com.org.godspeed.utility.UtilityClass.ONE_DAY;
import static com.org.godspeed.utility.UtilityClass.getDeviceTypeMobile;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfToday;
import static com.org.godspeed.utility.UtilityClass.getUnitBetweenDates;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.UtilityClass.prevent_touch_parent;
import static com.org.godspeed.utility.UtilityClass.showAlertDailog;
import static com.org.godspeed.utility.UtilityClass.urlEncoded;

//import com.org.godspeed.pojooftraining.Example;

public class AthleteExerciseSetActivity extends BaseActivity implements GodSpeedInterface {
    private final HealthResultHolder.ResultListener<HealthPermissionManager.PermissionResult> mPermissionListener = result -> {
        Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = result.getResultMap();
        // Show a permission alarm and clear step count if permissions are not acquired
        if (resultMap.containsValue(Boolean.FALSE)) {
            showPermissionAlarmDialog();
        } else {
            // Get the daily step count of a particular day and display it
            if (isPermissionAcquired()) {
                //getDataNow();
            }
        }
    };
    LinearLayout lLayoutForTrainingTypes;
    List<Type> typesOfList;
    List<ArrayofnewSetExersise> ArrayofnewSetExersise;


    ImageView imageViewCloseVideo, imageViewPauseVideoIcon;
    ProgressDialog progressDialog;

    JSONArray activationData = null;
    LinearLayout lLayoutForTeamName;
    RelativeLayout hs;
    String screenname;
    int IndexofPiller = 0;
    RecyclerView RecyclerViewtypesDataExecersise;
    int Phase = 0;
    int Week = 0;
    int Day = 0;


    int DayCountForShow;
    int WeekCountForShow;
    int PhaseCountForShow;
    int PhaseSize;
    int WeekSize;

    StringBuilder log = new StringBuilder();
    LinearLayout lLayoutForTrainingTypesNames;
    String StatusTag;
    int PHASE_SIZE = 0;
    String exercise_type_id = "0";
    //Handler handler;

    String APITOGGLE;
    String work_duration = "";
    //List<Json> example;
    List<AddTrainingProgram> getTrainingProgramAssignDetailByIdMultiples;
    String exercise_id = "0";
    long numberOfDays = 0;
    Handler CustomHandler = new Handler();
    WebServices webServices = new WebServices();
    TextView forSHOW;
    String program_id;
    RecyclerViewExerciseData recyclerViewExerciseData;
    int WeightCount = -1;
    int TypeIndex = 0;
    int ExerciseIndex = 0;
    Boolean TypeofScreenSummary = true;
    String ScreenType = "";
    Handler handlerTotal;
    Handler handlerRest;

    int TimeHour = 00;
    int TimeMinute = 00;
    int TimeSeconds = 00;


    int SecondsRest, MinutesRest, MilliSecondsRest;
    long MillisecondTimeRest, StartTimeRest, TimeBuffRest, UpdateTimeRest = 0L;
    public Runnable runnableRestTimer = new Runnable() {
        public void run() {
            MillisecondTimeRest = SystemClock.uptimeMillis() - StartTimeRest;
            UpdateTimeRest = TimeBuffRest + MillisecondTimeRest;
            SecondsRest = (int) (UpdateTimeRest / 1000);
            MinutesRest = SecondsRest / 60;
            SecondsRest = SecondsRest % 60;
            MilliSecondsRest = (int) (UpdateTimeRest % 1000);
            RestTimer.setText(String.format("%02d", MinutesRest) + ":" + String.format("%02d", SecondsRest));

            handlerRest.postDelayed(this, 0);
        }
    };
    int SecondsTotal, MinutesTotal, MilliSecondsTotal;


    //
    long MillisecondTimeTotal, StartTimeTotal, TimeBuffTotal, UpdateTimeTotal = 0L;
    Boolean ExerciseArray = false;
    Handler handlerX;
    Boolean ToggleNext = false;
    DecimalFormat twoDFormx = new DecimalFormat("#");
    Boolean SUMMARY_TEST_MODE = true;
    TextView StartTimer, RestTimerText, TotalTimerText, RestTimer;
    private ListView listViewExerciseSet;
    private Context context;
    public Runnable runnableTotalTime = new Runnable() {
        public void run() {

            MillisecondTimeTotal = SystemClock.uptimeMillis() - StartTimeTotal;
            UpdateTimeTotal = TimeBuffTotal + MillisecondTimeTotal;
            SecondsTotal = (int) (UpdateTimeTotal / 1000);
            MinutesTotal = SecondsTotal / 60;
            SecondsTotal = SecondsTotal % 60;
            MilliSecondsTotal = (int) (UpdateTimeTotal % 1000);
            StartTimer.setText(String.format("%02d", MinutesTotal) + ":" + String.format("%02d", SecondsTotal));
            work_duration = String.format(MinutesTotal + ":" + String.format("%02d", SecondsTotal) + ":" + String.format("%02d", MilliSecondsTotal));


            String v = String.format("%02d", SecondsTotal);
            String vX = String.format("%02d", MinutesTotal);

            if (notification == false) {

                UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION_TPNAME", getTrainingProgramAssignDetailByIdMultiples.get(0).getProgramName() + " - Still continuing the workout?");
                long Minu = TimeUnit.MINUTES.toMillis(TimeMinute);
                long Seco = TimeUnit.SECONDS.toMillis(TimeSeconds);
                long nm = Minu + Seco;
                notification = true;
                NotificationScheduler.setReminder(context, (TimeMinute - MinutesTotal), (TimeSeconds - SecondsTotal));
            }
            if (MinutesTotal >= TimeMinute && SecondsTotal >= TimeSeconds && TimeMinute > 00 && TimeSeconds > 00) {
                StartTimer.setTextColor(Color.RED);
            }

            Log.e(VolleyLog.TAG, "run: " + MinutesTotal + " " + TimeMinute + " " + SecondsTotal + " " + TimeSeconds);
            handlerTotal.postDelayed(this, 0);

        }
    };
    private RelativeLayout rLayoutForCalory_AvgHr_MaxHr_Option, rLayoutforAnimation, rLayoutVideoViewPlayer, rLayoutForWatchVideo;
    private ImageView imageViewAppIconForAnimation, imageViewBackArrow;
    //Declare a variable to hold count down timer's paused status
    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;
    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;
    private long millisInFuture = 60000;//900000; // 1 minute
    private long countDownInterval = 1000; //1 second
    private TextView textViewMetrics, textViewWorkout, textViewVelocity, textViewJumpHeight, textViewRSI, textViewAgilityTime, textViewVerticalJump, textViewSprintTime;
    private Gson gson;
    private String userId = "", TypeId = "";
    private TextView textViewScreenName, textViewScreenData, textViewComplex, textViewBone, textViewExerciseName, textViewWatchVideo, textViewCalories, textViewCaloriesValue, textViewAvgHr, textViewAvgHrValue, textViewMaxHr, textViewMaxHrValue;
    private Animation zoomIn;
    private boolean isAnimationStarted;
    private MediaController mediaController;
    private TextView textViewRunningTimeValue,
            textViewRecommandadTimeType,
            textViewRecommandadTimeValue,
            textViewRunningTimeValueMinute,
            textViewRunningTimeValueMiliseconds,
            textViewRunningTimeValueseconds;
    private TextView textViewRunningTimeValueshow,
            textViewRecommandadTimeTypeshow,
            textViewRecommandadTimeValueshow,
            textViewRunningTimeValueMinuteshow,
            textViewRunningTimeValueMilisecondsshow,
            textViewRunningTimeValuesecondsshow;
    private boolean startRun;
    private VideoView videoViewPlayer;
    //private TextView textViewStart, texViewReset, chkck;
    private int stopPosition;
    private int seconds = 0;
    private BubblePopupWindow dialog;
    private RelativeLayout videoView, SummaryLayout, WorkOutLayout;
    private ImageView RimageViewSession, imageViewComplex, closeVideo, imageViewHeaderIconRight, imageViewHeaderIconLeft;
    private RecyclerView SummaryRecyler, typesExercise;
    private int TIMER = 5000;
    private int update = 0;
    private int ActiveId;
    private TextView CoachNotesForExercise;
    private TypeData typeData;
    private String Distance = "";
    private String Calories = "";
    private String MaxHr = "";
    private String AvgHr = "";
    /*******SAMSUNG HEALTH**********/

    private final HealthDataStore.ConnectionListener mConnectionListener = new HealthDataStore.ConnectionListener() {
        @Override
        public void onConnected() {
            Log.e(VolleyLog.TAG, "onConnected");
            if (isPermissionAcquired()) {
                if (isPermissionAcquired()) {

                    long startTime = getStartTimeOfToday();
                    long endTime = startTime + ONE_DAY;
                    getSteps(startTime, endTime, "Day");
                    readTodayHeartRate(startTime, endTime, "Day");
                }
            } else {
                requestPermission();
            }
        }

        @Override
        public void onConnectionFailed(HealthConnectionErrorResult error) {
            Log.e(VolleyLog.TAG, "onConnectionFailed");
            showConnectionFailureDialog(error);
        }

        @Override
        public void onDisconnected() {
            Log.e(VolleyLog.TAG, "onDisconnected");
            if (!isFinishing()) {
                mStore.connectService();
            }
        }
    };
    private RelativeLayout rLayoutStartButton, rLayoutResetButton;
    private int usertype;
    private boolean coach;
    private SwipeRevealLayout swipe_layout;
    private String Timing = "";
    private NotificationManager notificationManager;
    private Float Timings = 0f;
    private Summary SummaryDataAdapter;
    private PillarExerciseStatus pillarExerciseStatus;
    private NotificationCompat.Builder notificationBuilder;
    private int currentNotificationID = 0;
    private String ExerciseTiming = "";
    private String ATHLETE_ID = "";
    private View rootView;
    private LinearLayout wod_layout, LTotalWorkOutTimer, LStartTotalTimer, LRestTotalTimer;
    private RelativeLayout rLayoutforRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        setContentView(R.layout.athlete_exercise_set_activity);

        InitializeViews();

        context = this;
        usertype = GlobalClass.ivar1;

        if (usertype == 1) {
            coach = true;
            ATHLETE_ID = AthleteData.get(ProfileScreenActivity.position).getUserId();
        } else {
            ATHLETE_ID = LoginJson.get(0).getUserId();
        }


        Log.e("Screen", "Exercise Set");

        ScreenType = getIntent().getStringExtra("ScreenType");
        if (ScreenType != null && ScreenType.equalsIgnoreCase("BeginTraining")) {
            TypeofScreenSummary = false;
        }
        notification = false;
        NotificationScheduler.cancelReminder(context);


        initializeView();


        RecyclerViewtypesDataExecersise = findViewById(R.id.typesDataExecersise);
        RecyclerViewtypesDataExecersise.setNestedScrollingEnabled(false);
        RecyclerViewtypesDataExecersise.setLayoutManager(new LinearLayoutManager(this));
        rLayoutforRecycler = findViewById(R.id.rLayoutforRecycler);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        SummaryRecyler = findViewById(R.id.SummaryRecyler);
        //wod_layout = findViewById(R.id.wod_layout);
        typesExercise = findViewById(R.id.typesExercise);
        CoachNotesForExercise = findViewById(R.id.CoachNotesForExercise);


        ScrollView notedScrollView = findViewById(R.id.notedScrollView);


        notedScrollView.setOnTouchListener(prevent_touch_parent);

        RimageViewSession.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if (startRun) {
                    ShowStopTimerDialog();
                    return;
                }
                WeightCount = 0;
                Timing = "0";
                Timings = 0f;
                StringBuilder v = new StringBuilder();
                try {
                    List<Type> typeList = new ArrayList<>(getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes());

                    ToggleNext = true;

                    typeList.forEach(types -> {
                        types.getExercises().forEach(ExerciseList -> {
                            Timing = ExerciseList.getWorkDuration();
                            try {
                                int hour = Integer.parseInt(Timing.substring(0, 2)) * 60;
                                int minute = Integer.parseInt(Timing.substring(3, 5));
                                int Seconds = Integer.parseInt(Timing.substring(6, 8));
                                String vx = hour + minute + "." + Seconds;
                                Timings += Float.parseFloat(vx);
                            } catch (Exception cv) {

                            }

                            if (ExerciseList.getStatus().equalsIgnoreCase("1") && ToggleNext) {
                                ExerciseList.getMeasurement().forEach(Measurement ->
                                {
                                    Measurement.getMeasurementValue().forEach(MValue ->
                                    {
                                        if (MValue.getMeasurementId().equalsIgnoreCase("6")) {
                                            WeightCount += Integer.parseInt(MValue.getMeasurementValue());
                                        }
                                    });
                                });
                            } else {
                                ToggleNext = false;
                            }
                        });
                    });
                } catch (Exception vx) {
                }
                if (ToggleNext) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("weight", WeightCount);
                    Intent intent = new Intent(AthleteExerciseSetActivity.this, Session_summery.class)
                            .putExtra("weight", WeightCount)
                            .putExtra("Timing", Timings);
                    startActivity(intent);

                    Log.d(VolleyLog.TAG, "*************** AthleteExerciseSetActivity *************");
                    overridePendingTransition(R.anim.exit, R.anim.enter);
                } else {
//                    UtilityClass.showAlertDailog(context, "Please complete all exercise.");
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("weight", WeightCount);
//                    Intent intent = new Intent(AthleteExerciseSetActivity.this, Session_summery.class)
//                            .putExtra("weight", WeightCount)
//                            .putExtra("Timing", Timings);
//                    startActivity(intent);

                }
                // if(WeightCount != 0 ){
            }
        });


        //GlobalClass.Date = getIntent().getStringExtra("date");


        SummaryRecyler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        typesExercise.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        lLayoutForTrainingTypesNames = findViewById(R.id.lLayoutForTrainingTypesNames);
//        ViewGroup.LayoutParams params = rLayoutforRecycler.getLayoutParams();
//        params.height = height / 2;
//        rLayoutforRecycler.setLayoutParams(params);

//        if (TypeofScreenSummary) {
//            //lLayoutForTrainingTypesNames.setVisibility(VISIBLE);
//        }

        program_id = getIntent().getStringExtra("ShowExcersiseOfID");
        imageViewHeaderIconRight = findViewById(R.id.imageViewHeaderIconRight);
        imageViewHeaderIconLeft = findViewById(R.id.imageViewHeaderIconLeft);


        videoViewPlayer = findViewById(R.id.videoViewPlayer);
        videoView = findViewById(R.id.videoView);

        WorkOutLayout = findViewById(R.id.WorkOutLayout);
        SummaryLayout = findViewById(R.id.SummaryLayout);

//        if (TypeofScreenSummary) {
//            SummaryLayout.setVisibility(GONE);
//            textViewScreenData.setVisibility(GONE);
//            WorkOutLayout.setVisibility(GONE);
//        }


        mStore = new HealthDataStore(context, mConnectionListener);
        try {
            mStore.connectService();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    long startTime = getStartTimeOfToday();
                    long endTime = startTime + ONE_DAY;
                    getSteps(startTime, endTime, "Day");
                    readTodayHeartRate(startTime, endTime, "Day");

                }
            }, 100);

        } catch (Exception v) {
        }


//        WizardPagerAdapter adapterX = new WizardPagerAdapter();
//        ViewPager pager = (ViewPager) findViewById(R.id.pager);
//        pager.setAdapter(adapterX);
//
//        pager.setCurrentItem(1);
//
//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d(PlusOneDummyView.TAG, "onPageScrolled: " + position);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == 0) {
//                    if (ActiveId != 0) {
//                        ActiveId = --ActiveId;
//                        callFunctionOFTypes();
//                    }
//                }
//                if (position == 2) {
//                    ActiveId = ++ActiveId;
//                    try {
//                        callFunctionOFTypes();
//                    } catch (Exception v) {
//                        ActiveId = --ActiveId;
//                        typeData.notifyDataSetChanged();
//                    }
//                }
//                pager.setCurrentItem(1);
//            }
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                Log.d(PlusOneDummyView.TAG, "onPageScrollStateChanged: " + state);
//            }
//        });


        closeVideo = findViewById(R.id.closeVideo);

        closeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoViewPlayer.stopPlayback();
                videoViewPlayer.setMediaController(null);
                videoView.setVisibility(View.INVISIBLE);
                WorkOutLayout.setVisibility(VISIBLE);
            }
        });

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("mm/dd/yy");
        gson = gsonBuilder.create();


        lLayoutForTrainingTypes = findViewById(R.id.lLayoutForTrainingTypes);


        try {
            String dateX = getIntent().getExtras().getString("date");
            if (dateX != null) {
                screenname = getIntent().getExtras().getString("screenname");
                lLayoutForTeamName.removeAllViews();

                switch (screenname) {
                    case "Activation":
                        IndexofPiller = 0;
                        break;
                    case "Skills":
                        IndexofPiller = 1;
                        break;
                    case "Energy":
                        IndexofPiller = 2;
                        break;
                    case "Build":
                        IndexofPiller = 3;
                        break;
                }

                if (!program_id.equalsIgnoreCase("")) {
                    hide();
                    APITOGGLE = "getProgramAssignDetailbyID";
                    if (TypeofScreenSummary) {
                        webServices.workoutSummary(program_id, ATHLETE_ID, "1", "0", context, AthleteExerciseSetActivity.this);
                    } else {
                        webServices.getTrainingProgramAssignDetailByIdMultiple(program_id, ATHLETE_ID, context, AthleteExerciseSetActivity.this);
                    }


                } else {
                    Log.e(VolleyLog.TAG, "onCreate: ID IS NULL " + program_id);
                }

                try {
                    Log.e(VolleyLog.TAG, "dayData: " + getIntent().getStringExtra("dayData"));
                    pillarExerciseStatus = gson.fromJson(getIntent().getStringExtra("dayData"), PillarExerciseStatus.class);
                    Log.e(VolleyLog.TAG, "onCreate: " + pillarExerciseStatus.getDay());
                } catch (Exception m) {
                }


                if (getIntent().getStringExtra("dayTP") != null && !getIntent().getStringExtra("dayTP").equalsIgnoreCase("-1") && !getIntent().getStringExtra("dayTP").equalsIgnoreCase("0") && !getIntent().getStringExtra("weekTP").equalsIgnoreCase("0") && !getIntent().getStringExtra("phaseTP").equalsIgnoreCase("0")) {
                    Day = Integer.parseInt(getIntent().getStringExtra("dayTP")) - 1;
                    Week = Integer.parseInt(getIntent().getStringExtra("weekTP")) - 1;
                    DayCountForShow = Day + 1;
                    Phase = Integer.parseInt(getIntent().getStringExtra("phaseTP")) - 1;
                    WeekCountForShow = Week + 1;
                    PhaseCountForShow = Phase + 1;

                    Log.e(VolleyLog.TAG, "DATE: " + getIntent().getStringExtra("dayTP") + "  " + getIntent().getStringExtra("weekTP") + "   " + getIntent().getStringExtra("phaseTP") + "   ");
                } else {

                    SimpleDateFormat originalFormat = new SimpleDateFormat("dd - MMM - yyyy");
                    SimpleDateFormat originalFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date;
                    Date Datess;
                    try {
                        date = originalFormat1.parse(dateX);
                        Datess = originalFormat.parse(GlobalClass.Date);
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date date1 = formatter.parse(targetFormat.format(Datess));
                            Date date2 = formatter.parse(targetFormat.format(date));

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                            numberOfDays = getUnitBetweenDates(date2, date1, TimeUnit.DAYS);

                            PhaseCountForShow = 1;
                            if (numberOfDays < 0) {
                                DayCountForShow = Day + 1;
                                WeekCountForShow = Week + 1;
                            } else {
                                Day = (int) numberOfDays % 7;
                                Week = (int) numberOfDays / 7;
                                DayCountForShow = Day;
                                WeekCountForShow = Week;
                            }
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception E) {
            E.printStackTrace();
        }


        imageViewHeaderIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increasing();
            }
        });
        imageViewHeaderIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreasing();
            }
        });

        lLayoutForTrainingTypesNames.setOnTouchListener(new OnSwipeListener(AthleteExerciseSetActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                increasing();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                decreasing();
            }
        });
        forSHOW = findViewById(R.id.forSHOW);
        forSHOW.setText("DAY =" + Day + "; WEEK =" + Week + "; PHASE = " + Phase);


        if (UtilityClass.getPreferences(context, "start_time") == null) {
            UtilityClass.SetPreferences(context, "start_time", StartTimeTotal + "");
            UtilityClass.SetPreferences(context, "TimingStatus", "start");
        }


    }

    private void InitializeViews() {

    }


    private void callFunctionOFTypes() {
        try {
            String s = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(ActiveId).getExerciseTimeDuration();
            textViewRunningTimeValueMinuteshow.setText(s.substring(0, 3));
            textViewRunningTimeValuesecondsshow.setText(s.substring(3, 5) + ".");
            textViewRunningTimeValueMilisecondsshow.setText(s.substring(6, 8));

        } catch (Exception x) {
            textViewRunningTimeValueMinuteshow.setText("00:");
            textViewRunningTimeValuesecondsshow.setText("00.");
            textViewRunningTimeValueMilisecondsshow.setText("00");
        }
        TypeId = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(ActiveId).getId();
        exercise_type_id = TypeId;
        try {
            exercise_id = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(ActiveId).getExercises().get(0).getExerciseId();
        } catch (Exception v) {
            exercise_id = "0";
        }
        try {
            recyclerViewExerciseData = new RecyclerViewExerciseData(ActiveId, 1, getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(ActiveId).getExercises());
            RecyclerViewtypesDataExecersise.setAdapter(recyclerViewExerciseData);
            RecyclerViewtypesDataExecersise.setHasFixedSize(true);
        } catch (Exception x) {
            ////////Log.e(VolleyLog.TAG, "onClick: "+x);
        }

        callTimer(false);
        typeData.notifyDataSetChanged();
    }

    private void increasing() {
        //TIMER += 1000;
//        if (TypeofScreenSummary) {
//            IndexofPiller = IndexofPiller + 1;
//            if (IndexofPiller == 4) {
//                IndexofPiller = 0;
//            }
//            addview();
//
//            SummaryDataAdapter.notifyDataSetChanged();
//
//            return;
//        }
        if (startRun) {
            ShowStopTimerDialog();
            return;
        }
        lLayoutForTrainingTypes.setVisibility(GONE);
        IndexofPiller = IndexofPiller + 1;

        if (IndexofPiller == 4) {
            IndexofPiller = 0;
            Day = Day + 1;
        }
        if (Day >= 7) {
            Day = 0;
            int WEEKSHOW = Week + 1;
            try {
                Log.e(VolleyLog.TAG, "increasing: " + WEEKSHOW + "  " + Week + "   " + getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().size());
                if (getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().size() == WEEKSHOW) {
                    Phase = Phase + 1;
                    Week = 0;
                } else {
                    Week = Week + 1;
                }
            } catch (Exception v) {
                Week = Week + 1;
            }
        }

        addview();
        ExerciseTiming = "";
//        try {
//            SummaryRecyler.setAdapter(new Summary(context, getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes()));
//        } catch (Exception v) {
//
//        }
        Log.e(VolleyLog.TAG, "increasing: " + Phase + "  " + "  " + Week + "  " + Day);

    }

    @Override
    public void onPause() {

        //////Log.e(VolleyLog.TAG, "onPause called");
        stopPosition = videoViewPlayer.getCurrentPosition(); //stopPosition is an int
        videoViewPlayer.pause();
        handlerRest.removeCallbacksAndMessages(null);
        handlerTotal.removeCallbacksAndMessages(null);
        if (startRun) {
            UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION", "true");
            Log.e(VolleyLog.TAG, "onPause: ");
        }
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        handlerRest.removeCallbacksAndMessages(null);
        handlerTotal.removeCallbacksAndMessages(null);
        if (startRun) {
            UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION", "true");
            Log.e(VolleyLog.TAG, "onDestroy: ");
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //////Log.e(VolleyLog.TAG, "onResume called");
        videoViewPlayer.seekTo(stopPosition);
        try {
            if (getTrainingProgramAssignDetailByIdMultiples.size() > 0)
                CheckTImeOf_Exercise();
        } catch (Exception v) {

        }

        videoViewPlayer.start(); //Or use resume() if it doesn't work. I'm not sure
    }

    private void decreasing() {
        //TIMER += 1000;
//        if (TypeofScreenSummary) {
//            IndexofPiller = IndexofPiller - 1;
//            if (IndexofPiller == -1) {
//                IndexofPiller = 3;
//            }
//            addview();
//            try {
//                SummaryRecyler.setAdapter(new Summary(context, getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes()));
//            } catch (Exception v) {
//            }
//            return;
//        }
        if (startRun) {
            ShowStopTimerDialog();
            return;
        }
        lLayoutForTrainingTypes.setVisibility(GONE);
        IndexofPiller = IndexofPiller - 1;
        if (IndexofPiller == -1) {
            IndexofPiller = 3;
            Day = Day - 1;
        }
        if (Week == 0 && Day == -1 && Phase == 0) {
            IndexofPiller = 0;
            Phase = 0;
            Week = 0;
            Day = 0;
        }
        if (Day == -1) {
            Day = 6;
            Week = Week - 1;
            Phase = Phase - 1;
        }
        if (Phase == -1) {
            Phase = 0;
        }
        if (Week == -1) {
            Week = 0;
        }
        ExerciseTiming = "";
        addview();
        try {
            SummaryRecyler.setAdapter(new Summary(context, getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes()));
        } catch (Exception v) {
        }

    }


    public void playvideo(String videopath) {
        Intent mIntent =
                ExoPlayerActivity.getStartIntent(this, urlEncoded(videopath));
        startActivity(mIntent);
    }


    private void initializeView() {
        LStartTotalTimer = findViewById(R.id.LStartTotalTimer);
        LTotalWorkOutTimer = findViewById(R.id.LTotalWorkOutTimer);
        StartTimer = findViewById(R.id.StartTimer);

        TotalTimerText = findViewById(R.id.TotalTimerText);
        RestTimerText = findViewById(R.id.RestTimerText);
        RestTimer = findViewById(R.id.RestTimer);
        LRestTotalTimer = findViewById(R.id.LRestTotalTimer);


//        rLayoutResetButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!coach) {
//                    handler.removeCallbacks(runnable);
//                    startRun = false;
//                    seconds = 0;
//                    MillisecondTime = 0L;
//                    StartTime = 0L;
//                    TimeBuff = 0L;
//                    UpdateTime = 0L;
//                    Seconds = 0;
//                    Minutes = 0;
//                    MilliSeconds = 0;
//
//                    textViewRunningTimeValueMinute.setText("00:");
//                    textViewRunningTimeValueseconds.setText("00.");
//                    textViewRunningTimeValueMiliseconds.setText("00");
//
//
//                }
//            }
//        });


        LTotalWorkOutTimer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!coach) {
                    handlerTotal.removeCallbacks(runnableTotalTime);
                    SecondsTotal = 0;
                    MillisecondTimeTotal = 0L;
                    StartTimeTotal = 0L;
                    TimeBuffTotal = 0L;
                    UpdateTimeTotal = 0L;
                    SecondsTotal = 0;
                    MinutesTotal = 0;
                    MilliSecondsTotal = 0;
                    StartTimer.setText("00:00");
                    TotalTimerText.setText("Start");
                    UtilityClass.SetPreferences(context, "start_time", "0");
                    UtilityClass.SetPreferences(context, "TimingStatus", "false");
                    UtilityClass.SetPreferences(context, "Phase", "");
                    UtilityClass.SetPreferences(context, "Week", "");
                    UtilityClass.SetPreferences(context, "Day", "");
                    UtilityClass.SetPreferences(context, "Type", "");
                    UtilityClass.SetPreferences(context, "program_id", "");
                    UtilityClass.SetPreferences(context, "Pillar", "");
                    UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION_TPNAME", "");
                    if (!RestTimerText.getText().toString().toLowerCase().equalsIgnoreCase("start")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                LRestTotalTimer.performClick();
                            }
                        }, 200);
                    }
                }
                return false;
            }
        });
//        LStartTotalTimer.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                if (!coach) {
//                    handlerTotal.removeCallbacks(runnableTotalTime);
//                    SecondsTotal = 0;
//                    MillisecondTimeTotal = 0L;
//                    StartTimeTotal = 0L;
//                    TimeBuffTotal = 0L;
//                    UpdateTimeTotal = 0L;
//                    SecondsTotal = 0;
//                    MinutesTotal = 0;
//                    MilliSecondsTotal = 0;
//                    StartTimer.setText("00:00");
//                    TotalTimerText.setText("Start");
//                    UtilityClass.SetPreferences(context, "start_time", "0");
//                    UtilityClass.SetPreferences(context, "TimingStatus", "false");
//                    UtilityClass.SetPreferences(context, "Phase", "");
//                    UtilityClass.SetPreferences(context, "Week", "");
//                    UtilityClass.SetPreferences(context, "Day", "");
//                    UtilityClass.SetPreferences(context, "Type", "");
//                    UtilityClass.SetPreferences(context, "program_id", "");
//                    UtilityClass.SetPreferences(context, "Pillar", "");
//                    UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION_TPNAME", "");
//                }
//                return false;
//            }
//        });
        LStartTotalTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!coach) {
                    if (ExerciseArray) {

                        if (TotalTimerText.getText().toString().toLowerCase().equalsIgnoreCase("start")) {
                            startRun = true;
                            if (UtilityClass.getPreferences(context, "TimingStatus").equalsIgnoreCase("pause")) {
                                StartTimeTotal = SystemClock.uptimeMillis();
                                handlerTotal.postDelayed(runnableTotalTime, 0);
                                TotalTimerText.setText("Pause");
                                UtilityClass.SetPreferences(context, "start_time", StartTimeTotal + "");
                                UtilityClass.SetPreferences(context, "TimingStatus", "start");
                            } else {
                                StartTimeTotal = SystemClock.uptimeMillis();
                                handlerTotal.postDelayed(runnableTotalTime, 0);
                                TotalTimerText.setText("Pause");
                                UtilityClass.SetPreferences(context, "start_time", StartTimeTotal + "");
                                UtilityClass.SetPreferences(context, "TimingStatus", "start");
                                UtilityClass.SetPreferences(context, "Phase", Phase + "");
                                UtilityClass.SetPreferences(context, "Pillar", IndexofPiller + "");
                                UtilityClass.SetPreferences(context, "program_id", program_id);
                                UtilityClass.SetPreferences(context, "Week", Week + "");
                                UtilityClass.SetPreferences(context, "Day", Day + "");
                                UtilityClass.SetPreferences(context, "Type", TypeId + "");

                            }
                            if (!RestTimerText.getText().toString().toLowerCase().equalsIgnoreCase("start")) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        LRestTotalTimer.performClick();
                                    }
                                }, 200);
                            }
                        } else {
                            startRun = false;
                            TimeBuffTotal += MillisecondTimeTotal;
                            UtilityClass.SetPreferences(context, "start_time", TimeBuffTotal + "");
                            UtilityClass.SetPreferences(context, "TimingStatus", "pause");
                            handlerTotal.removeCallbacks(runnableTotalTime);
                            TotalTimerText.setText("Start");
                            UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION_TPNAME", "");
                            NotificationScheduler.cancelReminder(context);
                            notification = false;
                        }


                    }
                }
            }
        });


        LRestTotalTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!coach) {
                    if (ExerciseArray) {
                        if (RestTimerText.getText().toString().toLowerCase().equalsIgnoreCase("start")) {


                            TotalTimerText.setText("Pause");
                            LStartTotalTimer.performClick();
                            StartTimeRest = SystemClock.uptimeMillis();
                            handlerRest.postDelayed(runnableRestTimer, 0);
                            RestTimerText.setText("reset");

                        } else {
                            RestTimerText.setText("Start");
                            handlerRest.removeCallbacks(runnableRestTimer);
                            SecondsRest = 0;
                            MillisecondTimeRest = 0L;
                            StartTimeRest = 0L;
                            TimeBuffRest = 0L;
                            UpdateTimeRest = 0L;
                            SecondsRest = 0;
                            MinutesRest = 0;
                            MilliSecondsRest = 0;
                            RestTimer.setText("00:00");

                        }
                    }
                }
            }
        });

//        LStartTotalTimer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!coach) {
//                    if (ExerciseArray) {
//                        if (textViewStart.getText().toString().equalsIgnoreCase("Start")) {
//                            startRun = true;
//                            if (UtilityClass.getPreferences(context, "TimingStatus").equalsIgnoreCase("pause")) {
//                                StartTimeTotal = SystemClock.uptimeMillis();
//                                handler.postDelayed(runnableTotalTime, 0);
//                                UtilityClass.SetPreferences(context, "start_time", StartTime + "");
//                                UtilityClass.SetPreferences(context, "TimingStatus", "start");
//                            } else {
//                                StartTimeTotal = SystemClock.uptimeMillis();
//                                handler.postDelayed(runnableTotalTime, 0);
//                                UtilityClass.SetPreferences(context, "start_time", StartTime + "");
//                                UtilityClass.SetPreferences(context, "TimingStatus", "start");
//                                UtilityClass.SetPreferences(context, "Phase", Phase + "");
//                                UtilityClass.SetPreferences(context, "Pillar", IndexofPiller + "");
//                                UtilityClass.SetPreferences(context, "program_id", program_id);
//                                UtilityClass.SetPreferences(context, "Week", Week + "");
//                                UtilityClass.SetPreferences(context, "Day", Day + "");
//                                UtilityClass.SetPreferences(context, "Type", TypeId + "");
//
//                            }
//                        } else {
//                            startRun = false;
//                            TimeBuffTotal += MillisecondTimeTotal;
//
//                            UtilityClass.SetPreferences(context, "start_time", TimeBuffTotal + "");
//                            UtilityClass.SetPreferences(context, "TimingStatus", "pause");
//                            handler.removeCallbacks(runnableTotalTime);
//                            textViewStart.setText("Start");
//                            UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION_TPNAME", "");
////                        WakefulReceiver wakefulReceiver = new WakefulReceiver();
////                            wakefulReceiver.cancelAlarm(context);
//                            NotificationScheduler.cancelReminder(context);
//                            notification = false;
//                        }
//                    }
//                }
//            }
//        });


        swipe_layout = findViewById(R.id.swipe_layout);
        lLayoutForTeamName = findViewById(R.id.lLayoutForTrainingTypes);
        textViewRunningTimeValueMinute = findViewById(R.id.textViewRunningTimeValueMinute);
        textViewRunningTimeValueseconds = findViewById(R.id.textViewRunningTimeValueseconds);
        textViewRunningTimeValueMiliseconds = findViewById(R.id.textViewRunningTimeValueMiliseconds);


        if (!getDeviceTypeMobile) {
            textViewRunningTimeValueMinute.setScaleX(2.8f);
            textViewRunningTimeValueseconds.setScaleX(2.8f);
            textViewRunningTimeValueMiliseconds.setScaleX(2.8f);
        }


        textViewRunningTimeValueMinuteshow = findViewById(R.id.textViewRunningTimeValueMinuteshow);
        textViewRunningTimeValuesecondsshow = findViewById(R.id.textViewRunningTimeValuesecondsshow);
        textViewRunningTimeValueMilisecondsshow = findViewById(R.id.textViewRunningTimeValueMilisecondsshow);


        textViewRunningTimeValueMinute.setLines(1);


        textViewRunningTimeValueMinuteshow.setTypeface(CustomTypeface.load_Digital_Normal_Fonts(context));
        textViewRunningTimeValuesecondsshow.setTypeface(CustomTypeface.load_Digital_Normal_Fonts(context));
        textViewRunningTimeValueMilisecondsshow.setTypeface(CustomTypeface.load_Digital_Normal_Fonts(context));

        handlerTotal = new Handler();
        handlerRest = new Handler();
        rLayoutStartButton = findViewById(R.id.rLayoutStartButton);
        rLayoutResetButton = findViewById(R.id.rLayoutResetButton);
//        textViewStart = findViewById(R.id.textViewStart);
//        textViewStart.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//
////        rLayoutResetButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                if (!coach) {
////                    handler.removeCallbacks(runnable);
////                    startRun = false;
////                    seconds = 0;
////                    MillisecondTime = 0L;
////                    StartTime = 0L;
////                    TimeBuff = 0L;
////                    UpdateTime = 0L;
////                    Seconds = 0;
////                    Minutes = 0;
////                    MilliSeconds = 0;
////
////                    textViewRunningTimeValueMinute.setText("00:");
////                    textViewRunningTimeValueseconds.setText("00.");
////                    textViewRunningTimeValueMiliseconds.setText("00");
////
////                    textViewRunningTimeValueMinute.setTextColor(Color.WHITE);
////                    textViewRunningTimeValueseconds.setTextColor(Color.WHITE);
////                    textViewRunningTimeValueMiliseconds.setTextColor(Color.WHITE);
////
////
////                    textViewStart.setText("Start");
////                    rLayoutStartButton.setVisibility(VISIBLE);
////
////                    UtilityClass.SetPreferences(context, "start_time", "0");
////                    UtilityClass.SetPreferences(context, "TimingStatus", "false");
////
////                    UtilityClass.SetPreferences(context, "Phase", "");
////                    UtilityClass.SetPreferences(context, "Week", "");
////                    UtilityClass.SetPreferences(context, "Day", "");
////                    UtilityClass.SetPreferences(context, "Type", "");
////                    UtilityClass.SetPreferences(context, "program_id", "");
////                    UtilityClass.SetPreferences(context, "Pillar", "");
////                    UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION_TPNAME", "");
//////                WakefulReceiver wakefulReceiver = new WakefulReceiver();
//////                wakefulReceiver.cancelAlarm(context);
////                    notification = false;
////                    NotificationScheduler.cancelReminder(context);
////                }
////            }
////        });
////
////        rLayoutStartButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (!coach) {
////                    if (ExerciseArray) {
////                        if (textViewStart.getText().toString().equalsIgnoreCase("Start")) {
////                            startRun = true;
////                            if (UtilityClass.getPreferences(context, "TimingStatus").equalsIgnoreCase("pause")) {
////                                StartTime = SystemClock.uptimeMillis();
////                                handler.postDelayed(runnable, 0);
////                                textViewStart.setText("Pause");
////                                UtilityClass.SetPreferences(context, "start_time", StartTime + "");
////                                UtilityClass.SetPreferences(context, "TimingStatus", "start");
////                            } else {
////                                StartTime = SystemClock.uptimeMillis();
////                                handler.postDelayed(runnable, 0);
////                                textViewStart.setText("Pause");
////                                UtilityClass.SetPreferences(context, "start_time", StartTime + "");
////                                UtilityClass.SetPreferences(context, "TimingStatus", "start");
////                                UtilityClass.SetPreferences(context, "Phase", Phase + "");
////                                UtilityClass.SetPreferences(context, "Pillar", IndexofPiller + "");
////                                UtilityClass.SetPreferences(context, "program_id", program_id);
////                                UtilityClass.SetPreferences(context, "Week", Week + "");
////                                UtilityClass.SetPreferences(context, "Day", Day + "");
////                                UtilityClass.SetPreferences(context, "Type", TypeId + "");
////
////                            }
////                        } else {
////                            startRun = false;
////                            TimeBuff += MillisecondTime;
////
////                            UtilityClass.SetPreferences(context, "start_time", TimeBuff + "");
////                            UtilityClass.SetPreferences(context, "TimingStatus", "pause");
////                            handler.removeCallbacks(runnable);
////                            textViewStart.setText("Start");
////                            UtilityClass.SetPreferences(context, "SHOW_LOCAL_NOTIFICATION_TPNAME", "");
//////                        WakefulReceiver wakefulReceiver = new WakefulReceiver();
//////                            wakefulReceiver.cancelAlarm(context);
////                            NotificationScheduler.cancelReminder(context);
////                            notification = false;
////                        }
////                    }
////                }
////            }
////        });
//
//        texViewReset = findViewById(R.id.textViewReset);
//
//        texViewReset.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        rLayoutForCalory_AvgHr_MaxHr_Option = findViewById(R.id.rLayoutForCalory_AvgHr_MaxHr_Option);

        rLayoutforAnimation = findViewById(R.id.rLayoutforAnimation);

        rLayoutforAnimation.setVisibility(GONE);

        RelativeLayout layoutForViewsAthlete = findViewById(R.id.layoutForViewsAthlete);
        RelativeLayout timerLayout = findViewById(R.id.timerLayout);
        RelativeLayout buttonForView = findViewById(R.id.buttonForView);


        if (TypeofScreenSummary) {
            timerLayout.setVisibility(GONE);
        }

        ImageView HideImageView = findViewById(R.id.HideImageView);

        swipe_layout.setSwipeListener(new SwipeRevealLayout.SwipeListener() {
            @Override
            public void onClosed(SwipeRevealLayout view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HideImageView.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    }
                }, 50);
            }

            @Override
            public void onOpened(SwipeRevealLayout view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HideImageView.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    }
                }, 50);
            }

            @Override
            public void onSlide(SwipeRevealLayout view, float slideOffset) {
            }
        });

        buttonForView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swipe_layout.isClosed()) {
                    HideImageView.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipe_layout.open(true);
                        }
                    }, 50);

                } else {
                    HideImageView.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipe_layout.close(true);
                        }
                    }, 50);
                }
            }
        });

        imageViewAppIconForAnimation = findViewById(R.id.imageViewAppIconForAnimation);
        imageViewAppIconForAnimation.setVisibility(GONE);

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

    private void removeSelectionFromLinearLayout() {
        int count = lLayoutForTeamName.getChildCount();
        for (int i = 0; i < count; i++) {
            LinearLayout layout = (LinearLayout) lLayoutForTeamName.getChildAt(i);
            TextView textViewTeamName = (TextView) layout.getChildAt(1);
            textViewTeamName.setTextColor(getResources().getColor(R.color.headerBGColor));
            layout.setBackgroundResource(R.drawable.round_border_gray);
        }
    }


    private void initializeTextView() {
        textViewScreenName = findViewById(R.id.textViewScreenName);
        RimageViewSession = findViewById(R.id.imageViewSession);
        textViewScreenData = findViewById(R.id.textViewScreenData);

        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        textViewScreenData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        if (TypeofScreenSummary) {
            RimageViewSession.setVisibility(View.INVISIBLE);
            textViewScreenName.setText("WORKOUT SUMMARY");
        }

        textViewCalories = findViewById(R.id.textViewCalories);
        textViewCalories.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewCaloriesValue = findViewById(R.id.textViewCaloriesValue);
        textViewCaloriesValue.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        textViewAvgHr = findViewById(R.id.textViewAvgHr);
        textViewAvgHr.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewAvgHrValue = findViewById(R.id.textViewAvgHrValue);
        textViewAvgHrValue.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewMaxHr = findViewById(R.id.textViewMaxHr);
        textViewMaxHr.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewMaxHrValue = findViewById(R.id.textViewMaxHrValue);
        textViewMaxHrValue.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewMetrics = findViewById(R.id.textViewMetrics);
        textViewMetrics.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewWorkout = findViewById(R.id.textViewWorkout);
        textViewWorkout.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewVelocity = findViewById(R.id.textViewVelocity);
        textViewVelocity.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewJumpHeight = findViewById(R.id.textViewJumpHeight);
        textViewJumpHeight.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewRSI = findViewById(R.id.textViewRSI);
        textViewRSI.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewAgilityTime = findViewById(R.id.textViewAgilityTime);
        textViewAgilityTime.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewVerticalJump = findViewById(R.id.textViewVerticalJump);
        textViewVerticalJump.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSprintTime = findViewById(R.id.textViewSprintTime);
        textViewSprintTime.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
    }

    private void addview() {

        CoachNotesForExercise.setText("");
        ToggleNext = false;
        WeightCount = 0;
        exercise_type_id = "0";
        exercise_id = "0";
        //Day = Phase = Week = 0;
        forSHOW.setText("Phase =" + (Phase + 1) + " Week =" + (Week + 1) + " Day=" + (Day + 1));
        //ScreenNameRecyclerView.setAdapter(null);
        DayCountForShow = Day + 1;
        WeekCountForShow = Week + 1;
        PhaseCountForShow = Phase + 1;
        RecyclerViewtypesDataExecersise.setAdapter(null);

        ActiveId = 0;

        typesExercise.setAdapter(null);
        typeData = new TypeData(0, context, 0);
        typesExercise.setAdapter(typeData);

        //}
        // else {
        try {
            if (TypeofScreenSummary) {
                textViewScreenName.setText("SUMMARY - " + PillarName[IndexofPiller]);
            } else {
                textViewScreenName.setText(getTrainingProgramAssignDetailByIdMultiples.get(0).getProgramName().substring(0, 6) + " - " + PillarName[IndexofPiller]);
            }
            textViewScreenData.setText("PHASE: " + PhaseCountForShow + " WEEK: " + WeekCountForShow + " DAY: " + DayCountForShow);
        } catch (Exception x) {
            try {
                textViewScreenName.setText(getTrainingProgramAssignDetailByIdMultiples.get(0).getProgramName() + " - " + PillarName[IndexofPiller]);
                textViewScreenData.setText("PHASE: " + PhaseCountForShow + " WEEK: " + WeekCountForShow + " DAY: " + DayCountForShow);
            } catch (Exception v) {
            }
        }
        callTimer(true);


        //}
    }

    @Override
    public void onBackPressed() {
        if (videoView.getVisibility() == VISIBLE) {
            videoViewPlayer.stopPlayback();
            videoViewPlayer.setMediaController(null);
            videoView.setVisibility(GONE);
            WorkOutLayout.setVisibility(VISIBLE);
            return;
        } else {
            if (startRun) {
                ShowStopTimerDialog();

                return;
            }
            super.onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            finish();
        }
    }

    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, result);
        if (result != null && result.trim().length() > 0) {
            ////////Log.d("Result", result);
            parseProgramAssignbyDeatilData(result);
            lLayoutForTrainingTypesNames.setVisibility(VISIBLE);
        } else {
            // UtilityClass.hide();
        }
    }

    private void parseProgramAssignbyDeatilData(String result) {

        Log.e(VolleyLog.TAG, "parseProgramAssignbyDeatilData: " + APITOGGLE);
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj.getString("responseMessage");

            if (WebServices.responseCode == 200) {

                if (APITOGGLE.equalsIgnoreCase("getProgramAssignDetailbyID")) {


                    activationData = jsonObj.getJSONArray("data");
                    JSONObject jsonObject;

                    getTrainingProgramAssignDetailByIdMultiples = new ArrayList<>(Arrays.asList(gson.fromJson(activationData.toString(), AddTrainingProgram[].class)));
                    ActiveId = 0;
                    addview();

                    SummaryDataAdapter = new Summary(context, getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes());
                    SummaryRecyler.setAdapter(SummaryDataAdapter);
                    typeData = new TypeData(0, context, 0);
                    typesExercise.setAdapter(typeData);


                    try {
                        String s = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(0).getExerciseTimeDuration();
                        textViewRunningTimeValueMinuteshow.setText(s.substring(0, 3));
                        textViewRunningTimeValuesecondsshow.setText(s.substring(3, 5) + ".");
                        textViewRunningTimeValueMilisecondsshow.setText(s.substring(6, 8));

                    } catch (Exception xX) {
                        Log.e(VolleyLog.TAG, "insertTeamsInHorizontalScrollView: " + xX);
                        textViewRunningTimeValueMinuteshow.setText("00:");
                        textViewRunningTimeValuesecondsshow.setText("00.");
                        textViewRunningTimeValueMilisecondsshow.setText("00");
                    }

                    // Toast.makeText(context, getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().size()+"", Toast.LENGTH_SHORT).show();
                } else if (APITOGGLE.equalsIgnoreCase("updateAthleteExerciseStatus")) {
                    if (update == 1) {
                        callTimer(false);
                    }
                    update = 0;
                } else if (APITOGGLE.equalsIgnoreCase("addAthleteActiveExerciseStatus")) {

                } else if (APITOGGLE.equalsIgnoreCase("AddExerciseSet")) {

                    ArrayofnewSetExersise = Arrays.asList(gson.fromJson(jsonObj.toString(), ArrayofnewSetExersise.class));

                    Log.e(VolleyLog.TAG, "parseProgramAssignbyDeatilData: " + ArrayofnewSetExersise.get(0).getData().getRepsValue());
                } else if (APITOGGLE.equalsIgnoreCase("APITOGGLE")) {
                    activationData = jsonObj.getJSONArray("data");
                    JSONObject jsonObject;

                    getTrainingProgramAssignDetailByIdMultiples = Arrays.asList(gson.fromJson(activationData.toString(), AddTrainingProgram[].class));

                    // UtilityClass.showAlertDailog(context, result);
                } else if (APITOGGLE.equalsIgnoreCase("Addset")) {
                    JSONArray jsonArray = jsonObj.getJSONArray("data");
                    JSONObject jsonObject;


                    List<Measurement> measurements = new ArrayList<>();

                    measurements = Arrays.asList(gson.fromJson(jsonArray.toString(), Measurement[].class));

                    getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(TypeIndex).getExercises().get(ExerciseIndex).getMeasurement().clear();

                    getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(TypeIndex).getExercises().get(ExerciseIndex).getMeasurement().addAll(measurements);
                } else {
                    hide();
                    webServices.getTrainingProgramAssignDetailByIdMultiple(program_id, team_id, context, AthleteExerciseSetActivity.this);
                    APITOGGLE = "getProgramAssignDetailbyID";
                }
            } else {
                UtilityClass.hide();
                UtilityClass.showAlertDailog(context, responseMessage);
                Log.e(TAG, "parseProgramAssignbyDeatilData: " + responseMessage);
            }
        } catch (JSONException e) {

            e.printStackTrace();
            UtilityClass.hide();
            Log.e("Error in json parsing", e.getMessage());
        } catch (Exception e) {
            // UtilityClass.hide();

            e.printStackTrace();
        }
        UtilityClass.hide();
    }

    private void callTimer(Boolean WithTime) {
        if (TypeofScreenSummary) {
            return;
        }
        if (!coach) {
            if (WithTime) {
                if (handlerX != null) {
                    handlerX.removeCallbacksAndMessages(null);
                }
                handlerX = new Handler();
                handlerX.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addAthleteActiveExerciseStatus();

                    }
                }, TIMER);

            } else {
                addAthleteActiveExerciseStatus();
            }
        }

    }

    private void addAthleteActiveExerciseStatus() {
        APITOGGLE = "addAthleteActiveExerciseStatus";
        webServices.addAthleteActiveExerciseStatus(program_id, ATHLETE_ID, PhaseCountForShow + "", WeekCountForShow + "", DayCountForShow + "", IndexofPiller + "", exercise_type_id, exercise_id, context, AthleteExerciseSetActivity.this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /********************* REPS AND WEIGHT DATA ********************/
    protected void hideSoftKeyboard(View input) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
        getWindow().getDecorView().clearFocus();
    }

    private void addNote(Context context, int x, int y, String event, String eventData, View view) {
        //Toast(context,typeofEvent+ "", Toast.LENGTH_SHORT).show();

        //.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#801b5e20")));

        rootView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_box_for_sets, null);
        BubbleLinearLayout bubbleView = rootView.findViewById(R.id.mainRlyofAddset);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);


        dialog = new BubblePopupWindow(rootView, bubbleView);

        dialog.setWidth(dpToPx(100));
        dialog.setCancelOnTouch(false);

        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        TextView txt = rootView.findViewById(R.id.EventName);
        ImageView addsetSave = rootView.findViewById(R.id.addsetSave);
        addsetSave.setVisibility(VISIBLE);
        addsetSave.setImageDrawable(getResources().getDrawable(R.drawable.play_video_icon));
        addsetSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilityClass.showAlertDailog(context, "Unable to play Video.");
            }
        });
        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txt.setText(event);


        // LinearLayout mainRlyofAddset = rootView.findViewById(R.id.mainRlyofAddset);

        if (!UtilityClass.getDeviceTypeMobile) {
            bubbleView.getLayoutParams().width = dpToPx(100);
            bubbleView.getLayoutParams().height = dpToPx(100);
        }
        //mainRlyofAddset.setBackgroundColor(Color.parseColor("#545454"));

        TextView txtData = rootView.findViewById(R.id.eventData);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txtData.setText(eventData);
        txtData.setTextColor(Color.parseColor("#ffffff"));
        txtData.setVisibility(VISIBLE);


        EditText addnotes = rootView.findViewById(R.id.addnotes);
        addnotes.setVisibility(GONE);


        dialog.showArrowTo(view, BubbleStyle.ArrowDirection.Up);
    }

    private void MagicTouch() {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis() + 100;
        float x = 0.0f;
        float y = 0.0f;
        int metaState = 0;
        MotionEvent motionEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                MotionEvent.ACTION_UP,
                x,
                y,
                metaState);
        super.dispatchTouchEvent(motionEvent);
    }

    private void ShowStopTimerDialog() {
        showAlertDailog(context, "Please complete exercise or stop timer.");
    }

    private void removeFocus() {
        MagicTouch();
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private void updateExerciseStatus(List<Exercise> exercises, int i, String myStatus) {
        exercises.get(i).setStatus(myStatus);
        String excerId = exercises.get(i).getIdAuto();
        hide();
        APITOGGLE = "updateAthleteExerciseStatus";
        update = 1;
//        String ATHLETE_ID = "";
//        if(!coach) {
//            ATHLETE_ID =
//        }else {
//            ATHLETE_ID = LoginScreen.userId;
//        }
        webServices.updateAthleteExerciseStatus(excerId, work_duration, ATHLETE_ID, myStatus, program_id, context, AthleteExerciseSetActivity.this);

        if (!work_duration.equalsIgnoreCase("")) {
            work_duration = "00:00:00";
        }

        recyclerViewExerciseData.notifyItemChanged(i);

        try {
            exercise_id = exercises.get(i).getExerciseId();
        } catch (Exception v) {
            exercise_id = "0";
        }

        rLayoutResetButton.performClick();

    }

    private void CheckTImeOf_Exercise() {
        if (!coach) {
            try {
                if (!TypeofScreenSummary) {
                    StartTimeTotal = Long.parseLong(UtilityClass.getPreferences(context, "start_time"));
                    if (UtilityClass.getPreferences(context, "TimingStatus").equalsIgnoreCase("start")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                handlerTotal.postDelayed(runnableTotalTime, 0);
                                TotalTimerText.setText("Pause");


                            }
                        }, 100);

                    } else if (UtilityClass.getPreferences(context, "TimingStatus").equalsIgnoreCase("pause")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TotalTimerText.setText("start");

                                MillisecondTimeTotal = StartTimeTotal;

                                UpdateTimeTotal = MillisecondTimeTotal;

                                TimeBuffTotal = MillisecondTimeTotal;
                                SecondsTotal = (int) (UpdateTimeTotal / 1000);
                                MinutesTotal = SecondsTotal / 60;
                                SecondsTotal = SecondsTotal % 60;

                                MilliSecondsTotal = (int) (UpdateTimeTotal % 1000);


                                StartTimer.setText(String.format("%02d", MinutesTotal) + ":" + String.format("%02d", SecondsTotal));

                                work_duration = String.format(MinutesTotal + ":" + String.format("%02d", SecondsTotal) + ":" + String.format("%02d", MilliSecondsTotal));
                                StartTimeTotal = SystemClock.uptimeMillis() + MillisecondTimeTotal;

                            }
                        }, 100);
                    } else {
                        UtilityClass.SetPreferences(context, "start_time", "0");
                        UtilityClass.SetPreferences(context, "TimingStatus", "false");
                    }
                }

            } catch (Exception v) {
            }
        }
    }

    public void getSteps(long mCurrentStartTime, long mCurrentEndTime, String valueoftype) {
        HealthDataResolver.AggregateRequest request;
        HealthDataResolver resolver = new HealthDataResolver(mStore, null);
        HealthDataResolver.AggregateRequest.TimeGroupUnit TimeGroupUnit;
        String alias = "";
        String TileTypeX = HealthConstants.StepCount.CALORIE;
        int amount = 0;

        TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.HOURLY;
        alias = "day";
        amount = 24;


        request = new HealthDataResolver.AggregateRequest.Builder()
                .setDataType(HealthConstants.StepCount.HEALTH_DATA_TYPE)
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.SUM,
                        HealthConstants.StepCount.CALORIE, "calorie")
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.SUM,
                        HealthConstants.StepCount.DISTANCE, "distance")
                .setLocalTimeRange(HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET,
                        mCurrentStartTime, mCurrentEndTime)
                .setTimeGroup(TimeGroupUnit, amount,
                        HealthConstants.StepCount.TIME_OFFSET,
                        HealthConstants.StepCount.END_TIME, alias)
                .build();

        try {

            resolver.aggregate(request).setResultListener(healthData -> {
                Cursor c = healthData.getResultCursor();
                if (c != null) {
                    float distance = 0;
                    float calorie = 0;
                    while (c.moveToNext()) {
                        calorie += Float.parseFloat(c.getString(c.getColumnIndex("calorie")));
                        distance += Float.parseFloat(c.getString(c.getColumnIndex("distance")));
                    }
                    Distance = distance + "";

                    Calories = calorie + "";

                    c.close();
                } else {
                }

                Log.e(VolleyLog.TAG, "getSteps: " + Distance + "   " + Calories);
                // Toast.makeText(context, "getSteps: "+Distance+"   "+Calories, Toast.LENGTH_SHORT).show();
                if (Calories.equalsIgnoreCase("")) {
                    Calories = "0";
                }


                textViewCaloriesValue.setText(Calories);
            });

        } catch (Exception e) {
        }
    }

    public void readTodayHeartRate(long mCurrentStartTime, long mCurrentEndTime, String valueoftype) {
        HealthDataResolver.AggregateRequest request;
        HealthDataResolver resolver = new HealthDataResolver(mStore, null);
        HealthDataResolver.AggregateRequest.TimeGroupUnit TimeGroupUnit;
        String alias = "";
        String TileTypeX = HealthConstants.HeartRate.HEART_RATE;
        int amount = 0;


        TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.HOURLY;
        alias = "day";
        amount = 24;


        request = new HealthDataResolver.AggregateRequest.Builder()
                .setDataType(HealthConstants.HeartRate.HEALTH_DATA_TYPE)
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.AVG,
                        HealthConstants.HeartRate.HEART_RATE, "average")
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.MAX,
                        HealthConstants.HeartRate.HEART_RATE, "max")
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.AVG,
                        TileTypeX, "sum")
                .setLocalTimeRange(HealthConstants.HeartRate.START_TIME, HealthConstants.HeartRate.TIME_OFFSET,
                        mCurrentStartTime, mCurrentEndTime)
                .setTimeGroup(TimeGroupUnit, amount,
                        HealthConstants.HeartRate.TIME_OFFSET,
                        HealthConstants.HeartRate.END_TIME, alias)
                .build();


        try {
            resolver.aggregate(request).setResultListener(healthData -> {
                Cursor c = healthData.getResultCursor();
                int ix = 0;
                int totalcount = 0;
                String day;
                int sum;
                if (c != null) {
                    int maxhr = 0;
                    int avghr = 0;
                    while (c.moveToNext()) {
                        maxhr += c.getInt(c.getColumnIndex("max"));
                        avghr += c.getInt(c.getColumnIndex("average"));
                    }
                    MaxHr = maxhr + "";
                    AvgHr = avghr + "";
                    Log.e(VolleyLog.TAG, "readTodayHeartRate: " + MaxHr + "     avg  " + avghr);


                    c.close();
                } else {
                }
                AvgHeartRate = AvgHr;
                MaxHr = MaxHr;

                if (MaxHr.equalsIgnoreCase("")) {
                    MaxHr = "0";

                }
                if (AvgHr.equalsIgnoreCase("")) {
                    AvgHr = "0";
                }
                textViewCaloriesValue.setText(Calories);

                textViewAvgHrValue.setText(AvgHr);
                textViewMaxHrValue.setText(MaxHr);
                //mStepCountTv.setText(""+totalcount);
            });
        } catch (Exception e) {
        }


    }

    private void showPermissionAlarmDialog() {
        if (isFinishing()) {
            return;
        }


    }

    private boolean isPermissionAcquired() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = pmsManager.isPermissionAcquired(generatePermissionKeySet());
            return !resultMap.containsValue(Boolean.FALSE);
        } catch (Exception e) {
            Log.e("", "Permission request fails.", e);
        }
        return false;
    }

    private Set<HealthPermissionManager.PermissionKey> generatePermissionKeySet() {
        Set<HealthPermissionManager.PermissionKey> pmsKeySet = new HashSet<>();
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.StepCount.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.HeartRate.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.USER_PROFILE_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.Sleep.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.WaterIntake.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.OxygenSaturation.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        return pmsKeySet;
    }

    private void requestPermission() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Show user permission UI for allowing user to change options
            pmsManager.requestPermissions(generatePermissionKeySet(), AthleteExerciseSetActivity.this)
                    .setResultListener(mPermissionListener);
        } catch (Exception e) {
            Log.e("", "Permission setting fails.", e);
        }
    }

    private void showConnectionFailureDialog(final HealthConnectionErrorResult error) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        if (error.hasResolution()) {
            switch (error.getErrorCode()) {
                case HealthConnectionErrorResult.PLATFORM_NOT_INSTALLED:
                    alert.setMessage(R.string.msg_req_install);
                    break;
                case HealthConnectionErrorResult.OLD_VERSION_PLATFORM:
                    alert.setMessage(R.string.msg_req_upgrade);
                    break;
                case HealthConnectionErrorResult.PLATFORM_DISABLED:
                    alert.setMessage(R.string.msg_req_enable);
                    break;
                case HealthConnectionErrorResult.USER_AGREEMENT_NEEDED:
                    alert.setMessage(R.string.msg_req_agree);
                    break;
                default:
                    alert.setMessage(R.string.msg_req_available);
                    break;
            }
        } else {
            alert.setMessage(R.string.msg_conn_not_available);
        }

        alert.setPositiveButton(R.string.ok, (dialog, id) -> {
            if (error.hasResolution()) {
                error.resolve(AthleteExerciseSetActivity.this);
            }
        });

        if (error.hasResolution()) {
            alert.setNegativeButton(R.string.cancel, null);
        }

        try {
            if (show_samsung_dialog) {
                alert.show();
                show_samsung_dialog = false;
            }
        } catch (Exception v) {
        }

    }

    /********************** *************************/
    public class ViewHolder {
        TextView textViewExerciseSetParameters, textViewExerciseRepsParameters, textViewExerciseWtParameters, textViewExercisePerParameters;
        ImageView imageViewAddExercise;
    }

    private class WizardPagerAdapter extends PagerAdapter {

        public Object instantiateItem(ViewGroup collection, int position) {

            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.page_one;
                    break;

                case 1:
                    resId = R.id.wodMainlayout;
                    break;

                case 2:
                    resId = R.id.page_two;
                    break;
            }
            return collection.findViewById(resId);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // No super
        }
    }

    /**********************piller_excersise_summary *******************************/

    public class Summary extends RecyclerView.Adapter<SummaryViewHolder> {
        List<Type> types;
        Context context;

        public Summary(Context context, List<Type> types) {
            this.types = types;
            this.context = context;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @NonNull
        @Override
        public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.piller_excersise_data, viewGroup, false);
            return new SummaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final SummaryViewHolder holder, final int position) {
            Context context = holder.itemView.getContext();
            holder.type_name.setTypeface(CustomTypeface.load_AGENCYB_Fonts(getApplicationContext()));
            holder.type_name.setText(types.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return types.size();
        }
    }


    private class SummaryViewHolder extends RecyclerView.ViewHolder {
        LinearLayout SummaryLayoutData, CustomTexts;
        LinearLayout SummaryLayout;
        RelativeLayout forWorkOut;
        TextView type_name, Exercise_name, Set1, Set2, Set3, Set4;
        RecyclerView SummaryLayoutRecyclerData;

        public SummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            SummaryLayout = itemView.findViewById(R.id.SummaryLayout);
            SummaryLayoutRecyclerData = itemView.findViewById(R.id.SummaryLayoutRecyclerData);
            forWorkOut = itemView.findViewById(R.id.forWorkOut);
            type_name = itemView.findViewById(R.id.type_name);
            Exercise_name = itemView.findViewById(R.id.Exercise_name);
            SummaryLayoutData = itemView.findViewById(R.id.SummaryLayoutData);
            CustomTexts = itemView.findViewById(R.id.CustomTexts);
        }
    }

    /**********************piller_excersise_data *******************************/

    public class RecyclerViewExerciseData extends RecyclerView.Adapter<RecyclerViewHolder> {
        int X;
        String status;
        String training_exercise_id_auto;


        int CEllFx = 1;
        int indexofMeasurementofI = 1;

        List<Exercise> exercises;

        public RecyclerViewExerciseData(int X, int CEllFx, List<Exercise> exercises) {
            this.X = X;
            this.CEllFx = CEllFx;
            this.exercises = exercises;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.piller_excersise_data, viewGroup, false);
            return new RecyclerViewHolder(view);
        }

        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(@NonNull final RecyclerViewHolder Holder, final int i) {

            Holder.forWorkOut.setVisibility(VISIBLE);
            Context context = Holder.itemView.getContext();

            Holder.wod_layout.setOnClickListener(view -> {
                if (Holder.Cwod_description.getVisibility() == VISIBLE) {
                    Holder.Cwod_description.setVisibility(GONE);
                } else {
                    Holder.Cwod_description.setVisibility(VISIBLE);
                }
            });
            if (i == 0 && exercises.get(0).getWodType().equalsIgnoreCase("1")) {

                Holder.wod_layout.setVisibility(VISIBLE);
                Holder.addsetAthleteExcersiseDoseX.setVisibility(GONE);
                Holder.wod_description.setText(exercises.get(0).getWodDescription());


            }
            if (exercises.get(0).getWodType().equalsIgnoreCase("1")) {
                Holder.ExcerciseDoseDetails.setVisibility(GONE);
                Holder.rLayoutforLBandREPS.setVisibility(GONE);
                Holder.addsetAthleteExcersiseDoseX.setVisibility(GONE);
                if (exercises.get(i).getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("20") || exercises.get(i).getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("21") || exercises.get(i).getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("22")) {
                    Holder.ExcerciseDoseDetails.setVisibility(VISIBLE);
                    Holder.rLayoutforLBandREPS.setVisibility(VISIBLE);
                }


                RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                buttonLayoutParams.setMargins(10, 0, 10, 0);
                RecyclerViewtypesDataExecersise.setLayoutParams(buttonLayoutParams);
                RecyclerViewtypesDataExecersise.setBackground(getResources().getDrawable(R.drawable.shap_round_corner));
                RelativeLayout.LayoutParams buttonLayoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                buttonLayoutParams1.setMargins(10, 0, 10, 0);
                Holder.forWorkOut.setLayoutParams(buttonLayoutParams1);
                if (i == exercises.size() - 1) {
                    //buttonLayoutParams1.setMargins(10, 0, 10, 15);
                    //Holder.forWorkOut.setLayoutParams(buttonLayoutParams1);
                }

                if (i == exercises.size() - 1) {
                    Holder.ForWod.setVisibility(VISIBLE);
                }

            } else {
                RecyclerViewtypesDataExecersise.setBackground(null);
            }

            if (CEllFx == 0) {
                Holder.ExcerciseDoseDetails.smoothScrollToPosition(1);
            }
            try {
                if (i > 0 || exercises.get(0).getWodType().equalsIgnoreCase("1")) {
                    Holder.ExcerciseNameLayout.setBackground(getDrawable(R.drawable.border_for_corner_yellow));
                }
                Holder.ExcerciseNameLayout.setOnClickListener(view -> {
                    if (exercises.get(0).getWodType().equalsIgnoreCase("1") && !exercises.get(0).getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("20")) {
                        return;
                    }

                    if (Holder.ExcerciseDoseDetails.getVisibility() == VISIBLE) {
                        Holder.ExcerciseDoseDetails.setVisibility(GONE);
                        Holder.rLayoutforLBandREPS.setVisibility(GONE);
                    } else {
                        Holder.ExcerciseDoseDetails.setVisibility(VISIBLE);
                        Holder.rLayoutforLBandREPS.setVisibility(VISIBLE);
                    }
                });

                Holder.Info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int[] location = new int[2];
                        Holder.Info.getLocationOnScreen(location);
                        int x = location[0];
                        int y = location[1];
                        String note = "";

                        if (exercises.get(i).getWhiteboardNotes().equalsIgnoreCase("")) {
                            note = exercises.get(i).getExerciseNotesDetail();
                        } else {
                            note = exercises.get(i).getWhiteboardNotes();
                        }
                        addNote(context, x, y, "Notes", note, view);
                    }
                });


                //String.valueOf(exercises.get(i).getStatus());

                //////Log.e(VolleyLog.TAG, "onBindViewHolder: "+exercises.get(i).getDoseDetails().size());
                Holder.imageViewVideoPlayIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Videoname = "";
                        Videoname = String.valueOf(exercises.get(i).getExerciseVideoLink());
                        if (Videoname.equalsIgnoreCase("")) {
                            UtilityClass.showAlertDailog(context, "Unable to play video.");
                            return;
                        }
                        //videoView.setVisibility(VISIBLE);
                        //WorkOutLayout.setVisibility(View.INVISIBLE);
                        playvideo(Videoname);
                    }
                });

                String Exercisename = String.valueOf(exercises.get(i).getExerciseName());


                Holder.ExcerciseName.setText(Exercisename);
                Holder.ExcerciseName.setSelected(true);


                for (int indexofMeasurement = 0; indexofMeasurement < exercises.get(i).getMeasurement().size(); indexofMeasurement++) {
                    if (indexofMeasurement == 0) {
                        indexofMeasurementofI = 1;
                        Holder.MeaurmentName1.setVisibility(VISIBLE);
                        Holder.MeaurmentName1.setText(exercises.get(i).getMeasurement().get(indexofMeasurement).getMeasurementName());
                    }

                    if (indexofMeasurement == 1) {
                        Holder.MeaurmentName2.setVisibility(VISIBLE);
                        indexofMeasurementofI = 2;
                        Holder.MeaurmentName2.setText(exercises.get(i).getMeasurement().get(indexofMeasurement).getMeasurementName());
                    }

                    if (indexofMeasurement == 2) {
                        indexofMeasurementofI = 3;
                        Holder.MeaurmentName3.setVisibility(VISIBLE);
                        Holder.MeaurmentName3.setText(exercises.get(i).getMeasurement().get(indexofMeasurement).getMeasurementName());
                    }

                    if (indexofMeasurement == 3) {
                        indexofMeasurementofI = 4;
                        Holder.MeaurmentName4.setVisibility(VISIBLE);
                        Holder.MeaurmentName4.setText(exercises.get(i).getMeasurement().get(indexofMeasurement).getMeasurementName());
                    }
                }

                try {
                    if (exercises.get(i).getMeasurement().get(0).getMeasurementValue().size() == 0) {
                        Holder.addsetAthleteExcersiseDoseX.setVisibility(VISIBLE);
                    } else {
                        Holder.addsetAthleteExcersiseDoseX.setVisibility(GONE);
                    }
                } catch (Exception x) {
                    Holder.addsetAthleteExcersiseDoseX.setVisibility(VISIBLE);
                }


                try {

                    String Videoname = "";
                    Videoname = UtilityClass.urlEncoded(exercises.get(i).getExerciseVideoLink());


                    String measurementString = new Gson().toJson(exercises.get(i).getMeasurement());  //because when we remove from @MeasurementValue object it will this object from main array;

                    Holder.ExcerciseDoseDetails.setAdapter(new RecyclerViewDoseData(X, i, Holder.ExcerciseDoseDetails, Holder.forWorkOut, indexofMeasurementofI, exercises.get(i).getMeasurement(), Videoname, i, exercises.get(i), measurementString, exercises.get(i).getWodType()));
                    Holder.ExcerciseDoseDetails.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    Holder.ExcerciseDoseDetails.setHasFixedSize(true);
                } catch (Exception x) {
                    Log.e(VolleyLog.TAG, "onBindViewHolder:EMPTYYTY " + x);
                }

                status = String.valueOf(exercises.get(i).getStatus());

                if (status.equalsIgnoreCase("0")) {
                    Holder.Unselected.setVisibility(View.VISIBLE);
                    Holder.Selected.setVisibility(View.INVISIBLE);
                    if (ExerciseTiming.equalsIgnoreCase("")) {
                        ExerciseTiming = exercises.get(i).getTimeDuration();
                        Log.e(VolleyLog.TAG, "ExerciseTiming: " + ExerciseTiming + "  " + exercises.get(i).getTimeDuration());
                        try {
                            String timeof = ExerciseTiming;
                            View view = View.inflate(context, R.layout.time_dialog, null);

                            if (timeof != null && timeof.length() > 0) {
                                int x = timeof.indexOf(':');
                                TimeHour = Integer.parseInt(timeof.substring(0, x));
                                TimeMinute = Integer.parseInt(timeof.substring(x + 1, x + 3));
                                TimeSeconds = Integer.parseInt(timeof.substring(x + 4, x + 6));
                            }
                            Log.e(VolleyLog.TAG, "CheckTImeOf_Exercise: " + TimeHour + "  " + TimeMinute + "  " + TimeSeconds);
                        } catch (Exception v) {
                        }
                    }
                } else {
                    Holder.Selected.setVisibility(View.VISIBLE);
                    Holder.Unselected.setVisibility(View.INVISIBLE);
                }


                if (work_duration.equalsIgnoreCase("")) {
                    work_duration = "00:00:00";
                }

                training_exercise_id_auto = exercises.get(i).getIdAuto();

                Holder.ExcersiseSubmitButtonX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TypeofScreenSummary) {
                            return;
                        }
                        String myStatus = exercises.get(i).getStatus();
                        if (myStatus.equalsIgnoreCase("0")) {
                            if (work_duration.equalsIgnoreCase("00:00:00")) {
                                //UtilityClass.showAlertDailog(context, "Please start timer and complete exercise.");
                                //return;
                            }
                        }
                        if (myStatus.equalsIgnoreCase("0")) {
                            myStatus = "1";

                            startRun = false;
                            TimeBuffTotal += MillisecondTimeTotal;

                            UtilityClass.SetPreferences(context, "start_time", TimeBuffTotal + "");
                            UtilityClass.SetPreferences(context, "TimingStatus", "pause");
                            handlerTotal.removeCallbacks(runnableTotalTime);
                            TotalTimerText.setText("Start");


                            String finalMyStatus = myStatus;
                            updateExerciseStatus(exercises, i, finalMyStatus);
                            ExerciseTiming = "";

                        } else {
                            myStatus = "0";
                            exercises.get(i).setStatus("0");
                            updateExerciseStatus(exercises, i, myStatus);
                        }
//


                    }
                });
            } catch (Exception e) {
                Log.e(VolleyLog.TAG, "onBindViewHolder: " + e);
            }


            Holder.addsetAthleteExcersiseDoseX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TypeofScreenSummary) {
                        return;
                    }
                    String assign_program_id = exercises.get(0).getAssignProgramId();
                    String training_exercise_id = exercises.get(0).getMeasurement().get(0).getTrainingExerciseId();
                    if (indexofMeasurementofI == 1) {
                        String measurement_id = exercises.get(0).getMeasurement().get(0).getMeasurementValue().get(i).getMeasurementId();
                        exercises.get(i).getMeasurement().get(0).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                    }
                    if (indexofMeasurementofI == 2) {
                        exercises.get(i).getMeasurement().get(0).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        Holder.addsetAthleteExcersiseDoseX.setMinimumHeight(dpToPx(42) * indexofMeasurementofI);
                        exercises.get(i).getMeasurement().get(1).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                    }
                    if (indexofMeasurementofI == 3) {
                        exercises.get(i).getMeasurement().get(0).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));


                        exercises.get(i).getMeasurement().get(1).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        Holder.addsetAthleteExcersiseDoseX.setMinimumHeight(dpToPx(42) * indexofMeasurementofI);
                        exercises.get(i).getMeasurement().get(2).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(2).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                    }
                    if (indexofMeasurementofI == 4) {
                        exercises.get(i).getMeasurement().get(0).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        exercises.get(i).getMeasurement().get(1).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        exercises.get(i).getMeasurement().get(2).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(2).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        exercises.get(i).getMeasurement().get(3).getMeasurementValue().add(new MeasurementValue("0", training_exercise_id, exercises.get(i).getMeasurement().get(3).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        Holder.addsetAthleteExcersiseDoseX.setMinimumHeight(dpToPx(42) * indexofMeasurementofI);
                    }
                    notifyDataSetChanged();

                }
            });
            Holder.addsetAthleteExcersiseDoseX.setMinimumHeight(dpToPx(42) * indexofMeasurementofI);

            Holder.LWodMissed.setOnClickListener(view -> {
                Holder.LWodMissed.setBackgroundResource(R.drawable.round_bg_yellow);
                Holder.MissedTextWod.setTextColor(Color.BLACK);
                Holder.LWodComplete.setBackgroundResource(R.drawable.round_border_gray);
                Holder.CompleteTextWod1.setTextColor(getResources().getColor(R.color.headerBGColor));
            });
            Holder.CompleteTextWod1.setOnClickListener(view -> {
                Holder.LWodComplete.setBackgroundResource(R.drawable.round_bg_yellow);
                Holder.CompleteTextWod1.setTextColor(Color.BLACK);
                Holder.LWodMissed.setBackgroundResource(R.drawable.round_border_gray);
                Holder.MissedTextWod.setTextColor(getResources().getColor(R.color.headerBGColor));
            });
            Holder.LWod.setOnClickListener(view -> {
                Holder.LWod.setBackgroundResource(R.drawable.round_bg_yellow);
                Holder.DoseTextWod.setTextColor(Color.BLACK);

                Holder.LWod1.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod1.setTextColor(getResources().getColor(R.color.headerBGColor));

                Holder.LWod2.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod2.setTextColor(getResources().getColor(R.color.headerBGColor));

                Holder.LWod3.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod3.setTextColor(getResources().getColor(R.color.headerBGColor));
            });
            Holder.LWod1.setOnClickListener(view -> {
                Holder.LWod1.setBackgroundResource(R.drawable.round_bg_yellow);
                Holder.DoseTextWod1.setTextColor(Color.BLACK);

                Holder.LWod.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod.setTextColor(getResources().getColor(R.color.headerBGColor));

                Holder.LWod2.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod2.setTextColor(getResources().getColor(R.color.headerBGColor));

                Holder.LWod3.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod3.setTextColor(getResources().getColor(R.color.headerBGColor));
            });
            Holder.LWod2.setOnClickListener(view -> {
                Holder.LWod2.setBackgroundResource(R.drawable.round_bg_yellow);
                Holder.DoseTextWod2.setTextColor(Color.BLACK);

                Holder.LWod1.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod1.setTextColor(getResources().getColor(R.color.headerBGColor));

                Holder.LWod.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod.setTextColor(getResources().getColor(R.color.headerBGColor));

                Holder.LWod3.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod3.setTextColor(getResources().getColor(R.color.headerBGColor));
            });
            Holder.LWod3.setOnClickListener(view -> {
                Holder.LWod3.setBackgroundResource(R.drawable.round_bg_yellow);
                Holder.DoseTextWod3.setTextColor(Color.BLACK);

                Holder.LWod.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod.setTextColor(getResources().getColor(R.color.headerBGColor));

                Holder.LWod2.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod2.setTextColor(getResources().getColor(R.color.headerBGColor));

                Holder.LWod.setBackgroundResource(R.drawable.round_border_gray);
                Holder.DoseTextWod.setTextColor(getResources().getColor(R.color.headerBGColor));
            });
            if (exercises.get(0).getWodType().equalsIgnoreCase("1")) {

                Holder.addsetAthleteExcersiseDoseX.setVisibility(GONE);

            }

        }

        @Override
        public int getItemCount() {
            return exercises.size();
        }

        public void check() {
            for (int cvc = 0; cvc < exercises.size(); cvc++) {
                String value = exercises.get(cvc).getStatus();
                if (value.equalsIgnoreCase("0")) {
                    ToggleNext = false;
                    break;
                } else {
                    ToggleNext = true;
                }
            }
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView ExcerciseName;
        RecyclerView ExcerciseDoseDetails;
        RelativeLayout ExcersiseSubmitButtonX, forWorkOut;
        LinearLayout ExcerciseNameLayout, rLayoutforLBandREPS, addsetAthleteExcersiseDoseX, ForWod;
        ImageView Selected, Unselected, imageViewVideoPlayIcon, Info;
        TextView MeaurmentName1, MeaurmentName2, MeaurmentName3, MeaurmentName4, MeaurmentName5;

        ///WOD LAYOUT
        CardView Cwod_description;
        LinearLayout wod_layout;
        TextView wod_description;
        EditText rx1, rx2, rx3, ExResult;
        LinearLayout LWod, LWod1, LWod2, LWod3, LWodMissed, LWodComplete;
        TextView DoseTextWod, DoseTextWod1, DoseTextWod2, DoseTextWod3, CompleteTextWod1, MissedTextWod;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            LWodMissed = itemView.findViewById(R.id.LWodMissed);
            LWodComplete = itemView.findViewById(R.id.LWodComplete);
            ExResult = itemView.findViewById(R.id.ExResult);
            MissedTextWod = itemView.findViewById(R.id.MissedTextWod);
            CompleteTextWod1 = itemView.findViewById(R.id.CompleteTextWod1);
            DoseTextWod = itemView.findViewById(R.id.DoseTextWod);
            DoseTextWod1 = itemView.findViewById(R.id.DoseTextWod1);
            DoseTextWod2 = itemView.findViewById(R.id.DoseTextWod2);
            DoseTextWod3 = itemView.findViewById(R.id.DoseTextWod3);

            LWod = itemView.findViewById(R.id.LWod);
            LWod1 = itemView.findViewById(R.id.LWod1);
            LWod2 = itemView.findViewById(R.id.LWod2);
            LWod3 = itemView.findViewById(R.id.LWod3);


            wod_layout = itemView.findViewById(R.id.wod_layout);
            wod_description = itemView.findViewById(R.id.wod_description);
            Cwod_description = itemView.findViewById(R.id.Cwod_description);
            rx1 = itemView.findViewById(R.id.rx1);
            rx2 = itemView.findViewById(R.id.rx2);
            rx3 = itemView.findViewById(R.id.rx3);


            ExcerciseName = itemView.findViewById(R.id.ExcerciseName);
            ExcersiseSubmitButtonX = itemView.findViewById(R.id.ExcersiseSubmitButtonX);
            if (TypeofScreenSummary) {
                //ExcerciseName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ExcersiseSubmitButtonX.setVisibility(View.GONE);
            }

            ForWod = itemView.findViewById(R.id.ForWod);

            forWorkOut = itemView.findViewById(R.id.forWorkOut);
            Selected = itemView.findViewById(R.id.selected_tick);
            Unselected = itemView.findViewById(R.id.unselected_tick);
            imageViewVideoPlayIcon = itemView.findViewById(R.id.imageViewVideoPlayIcon);

            ExcerciseDoseDetails = itemView.findViewById(R.id.ExcerciseDoseDetails);
            rLayoutforLBandREPS = itemView.findViewById(R.id.rLayoutforLBandREPS);
            ExcerciseNameLayout = itemView.findViewById(R.id.ExcerciseNameLayout);
            addsetAthleteExcersiseDoseX = itemView.findViewById(R.id.addsetAthleteExcersiseDoseX);
            Info = itemView.findViewById(R.id.Info);


            MeaurmentName1 = itemView.findViewById(R.id.MeaurmentName1);
            MeaurmentName2 = itemView.findViewById(R.id.MeaurmentName2);
            MeaurmentName3 = itemView.findViewById(R.id.MeaurmentName3);
            MeaurmentName4 = itemView.findViewById(R.id.MeaurmentName4);
            MeaurmentName5 = itemView.findViewById(R.id.MeaurmentName5);


            ExcerciseDoseDetails.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
    }

    private class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
        TextView setName;
        EditText edittext1, edittext2, edittext3, edittext4, edittext5;
        ImageView Selected, Unselected, OpenAddset, video_thumb;
        LinearLayout addsetAthleteExcersiseDose, LayoutForHideAdddset;

        RelativeLayout rLayoutx;

        public RecyclerViewHolder2(View itemView, final int y, int position) {
            super(itemView);


            edittext1 = itemView.findViewById(R.id.edittext1);
            edittext2 = itemView.findViewById(R.id.edittext2);
            edittext3 = itemView.findViewById(R.id.edittext3);
            edittext4 = itemView.findViewById(R.id.edittext4);
            edittext5 = itemView.findViewById(R.id.edittext5);
            setName = itemView.findViewById(R.id.setName);
            video_thumb = itemView.findViewById(R.id.video_thumb);
            addsetAthleteExcersiseDose = itemView.findViewById(R.id.addsetAthleteExcersiseDose);
            rLayoutx = itemView.findViewById(R.id.rLayoutx);
            OpenAddset = itemView.findViewById(R.id.OpenAddset);
            LayoutForHideAdddset = itemView.findViewById(R.id.LayoutForHideAdddset);

            edittext1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            edittext2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            edittext3.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            edittext4.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            edittext5.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            if (TypeofScreenSummary) {
                edittext1.setEnabled(false);
                edittext2.setEnabled(false);
                edittext3.setEnabled(false);
                edittext4.setEnabled(false);
                edittext5.setEnabled(false);
            }
        }
    }

    public class RecyclerViewDoseData extends RecyclerView.Adapter<RecyclerViewHolder2> {
        int Y;
        int position;
        int indexofMeasurementofI;
        List<Measurement> measurement;
        int X;
        RecyclerView excerciseDoseDetails;
        RelativeLayout forWorkOut;
        String Videoname = "";
        Exercise exercise;
        String wodType;

        public RecyclerViewDoseData(int X, int Y, RecyclerView excerciseDoseDetails, RelativeLayout forWorkOut
                , int indexofMeasurementofI, List<Measurement> measurement,
                                    String videoname, int i, Exercise exercise, String measurementString, String wodType) {
            this.Y = Y;
            this.X = X;
            this.indexofMeasurementofI = indexofMeasurementofI;

            this.excerciseDoseDetails = excerciseDoseDetails;
            this.Videoname = Videoname;
            this.forWorkOut = forWorkOut;
            this.exercise = exercise;
            this.wodType = wodType;

//            if (TypeofScreenSummary) {
//                //because when we remove from @MeasurementValue object it will this object from main array;
//
//                List<Measurement> measurementListOfY = new ArrayList<>(Arrays.asList(new Gson().fromJson(measurementString, Measurement[].class)));
//                List<Measurement> measurementListOfX = new ArrayList<>();
//                int pos = 0;
//                List<String> stringList = new ArrayList<>();
//
//                try {
//
//
//                    int counter = 1;
//                    String indexOfmeasureA = "";
//                    String indexOfmeasureA_Plus_One = "";
//                    JsonArray jsonElements = new JsonArray();
//                    JsonObject jsonObject = new JsonObject();
//                    ArrayList<List<String>> myArray = new ArrayList<>();
//
//
//                    int LAstStoredCount = 0;
//
//
//                    for (int A = 0; A < measurementListOfY.get(0).getMeasurementValue().size(); A++) {
//
//                        StringBuilder stringBuilder = new StringBuilder();
//                        StringBuilder stringBuilderX = new StringBuilder();
//                        List<String> integerList = new ArrayList<>();
//                        for (int iv = 0; iv < measurementListOfY.size(); iv++) {
//                            indexOfmeasureA_Plus_One = stringBuilder.append(measurementListOfY.get(iv).getMeasurementValue().get(A).getMeasurementValue()).append(iv != measurementListOfY.size() - 1 ? "x" : "").toString().trim();
//                            integerList.add(measurementListOfY.get(iv).getMeasurementValue().get(A).getMeasurementValue());
//                            try {
//                                indexOfmeasureA = stringBuilderX.append(measurementListOfY.get(iv).getMeasurementValue().get(A + 1).getMeasurementValue()).append(iv != measurementListOfY.size() - 1 ? "x" : "").toString().trim();
//                            } catch (Exception v) {
//                                indexOfmeasureA = "";
//                            }
//                        }
//
//
//                        try {
//                            if (indexOfmeasureA_Plus_One.equalsIgnoreCase(indexOfmeasureA)) {
//                                counter = ++counter;
//                                try {
//                                    for (int iv = 0; iv < measurementListOfY.size(); iv++) {
//                                        for (int i1 = 0; i1 < integerList.size(); i1++) {
//                                            if (integerList.get(i1).equalsIgnoreCase(measurementListOfY.get(iv).getMeasurementValue().get(A).getMeasurementValue())) {
//                                                try {
//                                                    measurementListOfY.get(iv).getMeasurementValue().get(A + 1).setSet_Name(((A + 1) + " - " + ((A) + counter)) + " (" + ((((A) + counter) - (A + 1)) + 1) + ")");
//                                                } catch (Exception v) {
//                                                }
//                                                measurementListOfY.get(iv).getMeasurementValue().remove(A);
//                                            }
//                                        }
//                                    }
//                                } catch (Exception n) {
//
//                                }
//                            } else {
//                                try {
//                                    for (int iv = 0; iv < measurementListOfY.size(); iv++) {
//                                        for (int i1 = 0; i1 < integerList.size(); i1++) {
//                                            if (integerList.get(i1).equalsIgnoreCase(measurementListOfY.get(iv).getMeasurementValue().get(A).getMeasurementValue())) {
//                                                try {
//                                                    int v = counter == 1 ? counter : counter - 1;
//                                                    measurementListOfY.get(iv).getMeasurementValue().get(A).setSet_Name(((A + 1) + " - " + ((A) + v) + " (" + ((((A) + v) - (A + 1)) + 1) + ")"));
//                                                } catch (Exception v) {
//                                                }
//                                            }
//                                        }
//                                    }
//                                } catch (Exception n) {
//
//                                }
//                                counter = 1;
//                            }
//                        } catch (Exception v) {
//                        }
//                        // indexOfmeasureA = indexOfmeasureA_Plus_One;
//                    }
//                } catch (Exception v) {
//
//                }
//
//
//                this.measurement = new ArrayList<>(measurementListOfY);
//            } else {
            this.measurement = new ArrayList<>(measurement);
            //}


        }

        @NonNull
        @Override
        public RecyclerViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Context context = viewGroup.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.excersise_dose_list, viewGroup, false);
            return new RecyclerViewHolder2(view, Y, position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerViewHolder2 Holder, int i) {
            Context context = Holder.itemView.getContext();
            if (i == 0) {
            }

            int count = i;
            count = count + 1;


            if (TypeofScreenSummary) {
                int s = i + Integer.parseInt(measurement.get(0).getMeasurementValue().get(i).getWorkout_summary_count());
                //if (measurement.get(0).getMeasurementValue().get(i).getSet_Name() != null && !measurement.get(0).getMeasurementValue().get(i).getSet_Name().equalsIgnoreCase("")) {

                if (measurement.get(0).getMeasurementValue().get(i).getWorkout_summary_count().equalsIgnoreCase("1")) {
                    Holder.setName.setText("SET " + count + " (" + measurement.get(0).getMeasurementValue().get(i).getWorkout_summary_count() + ")");
                } else {
                    Holder.setName.setText("SET " + count + " - " + s + " (" + measurement.get(0).getMeasurementValue().get(i).getWorkout_summary_count() + ")");
                }
                //Log.d(TAG, "onBindViewHolder: "+ count + " - " + s + " (" + measurement.get(0).getMeasurementValue().get(i).getWorkout_summary_count() + ")");

                // }
            } else {
                Holder.setName.setText("SET " + count);
            }

            if (wodType.equalsIgnoreCase("1")) {
                Holder.addsetAthleteExcersiseDose.setVisibility(GONE);
            }

            Holder.OpenAddset.setOnClickListener(view -> {
                if (Holder.addsetAthleteExcersiseDose.getVisibility() == VISIBLE) {
                    Holder.addsetAthleteExcersiseDose.setVisibility(GONE);
                    Holder.OpenAddset.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            excerciseDoseDetails.scrollToPosition(excerciseDoseDetails.getAdapter().getItemCount() - 1);
                            AutoTransition autoTransition = new AutoTransition();
                            autoTransition.setDuration(200);
                            TransitionManager.beginDelayedTransition(forWorkOut, autoTransition);
                        }
                    }, 300);
                } else {
                    Holder.addsetAthleteExcersiseDose.setVisibility(VISIBLE);
                    Holder.OpenAddset.setImageDrawable(getResources().getDrawable(R.drawable.left_arrow));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            excerciseDoseDetails.scrollToPosition(excerciseDoseDetails.getAdapter().getItemCount() - 1);
                            AutoTransition autoTransition = new AutoTransition();
                            autoTransition.setDuration(200);
                            TransitionManager.beginDelayedTransition(forWorkOut, autoTransition);
                        }
                    }, 300);
                }
            });
            Log.e(VolleyLog.TAG, "onBindViewHolder:indexofMeasurementofI " + indexofMeasurementofI);

            try {
                if (wodType.equalsIgnoreCase("1")) {
                    Holder.edittext1.setText(measurement.get(0).getMeasurementValue().get(i).getMeasurementValue());

                } else {
                    Holder.edittext1.setText(String.valueOf(Math.round(Float.parseFloat(measurement.get(0).getMeasurementValue().get(i).getMeasurementValue()))));
                }

                Holder.edittext1.setVisibility(VISIBLE);
                Holder.addsetAthleteExcersiseDose.setMinimumHeight(dpToPx(42));
                Holder.addsetAthleteExcersiseDose.getLayoutParams().height = dpToPx(42);
                Holder.LayoutForHideAdddset.setMinimumHeight(dpToPx(40));
                Holder.LayoutForHideAdddset.getLayoutParams().height = dpToPx(40);
            } catch (Exception x) {

            }


            try {

                if (wodType.equalsIgnoreCase("1")) {
                    Holder.edittext2.setText(measurement.get(1).getMeasurementValue().get(i).getMeasurementValue());

                } else {
                    Holder.edittext2.setText(String.valueOf(Math.round(Float.parseFloat(measurement.get(1).getMeasurementValue().get(i).getMeasurementValue()))));
                }
                //Holder.edittext2.setText(String.valueOf(Math.round(Float.parseFloat(measurement.get(1).getMeasurementValue().get(i).getMeasurementValue()))));
                //Holder.edittext2.setText(measurement.get(1).getMeasurementValue().get(i).getMeasurementValue());
                Holder.edittext2.setVisibility(VISIBLE);
            } catch (Exception x) {

            }

            try {
                if (wodType.equalsIgnoreCase("1")) {
                    Holder.edittext3.setText(measurement.get(2).getMeasurementValue().get(i).getMeasurementValue());

                } else {
                    Holder.edittext3.setText(String.valueOf(Math.round(Float.parseFloat(measurement.get(2).getMeasurementValue().get(i).getMeasurementValue()))));
                }
                //Holder.edittext3.setText(measurement.get(2).getMeasurementValue().get(i).getMeasurementValue());
                // Holder.Repsvalue.setText(exercises.get(Y).getSetLevelMultiple().get(i).getRepsValue());
                Holder.edittext3.setVisibility(VISIBLE);
                Holder.addsetAthleteExcersiseDose.setMinimumHeight(dpToPx(40) * 3);
                Holder.LayoutForHideAdddset.setMinimumHeight(dpToPx(40) * 3);
            } catch (Exception x) {

            }

            try {
                if (wodType.equalsIgnoreCase("1")) {
                    Holder.edittext4.setText(measurement.get(3).getMeasurementValue().get(i).getMeasurementValue());
                } else {

                    Holder.edittext4.setText(String.valueOf(Math.round(Float.parseFloat(measurement.get(3).getMeasurementValue().get(i).getMeasurementValue()))));
                }
                // Holder.edittext4.setText(measurement.get(3).getMeasurementValue().get(i).getMeasurementValue());
                // Holder.Repsvalue.setText(exercises.get(Y).getSetLevelMultiple().get(i).getRepsValue());
                Holder.edittext4.setVisibility(VISIBLE);
                Holder.addsetAthleteExcersiseDose.setMinimumHeight(dpToPx(40) * 4);
                Holder.LayoutForHideAdddset.setMinimumHeight(dpToPx(40) * 4);
            } catch (Exception x) {

            }


            Holder.addsetAthleteExcersiseDose.getLayoutParams().height = dpToPx(40) * indexofMeasurementofI;
            Holder.LayoutForHideAdddset.getLayoutParams().height = dpToPx(40) * indexofMeasurementofI;
            if (indexofMeasurementofI == 1) {
                Holder.LayoutForHideAdddset.getLayoutParams().height = dpToPx(34) * indexofMeasurementofI;
            }


            if (count == getItemCount()) {
                Holder.LayoutForHideAdddset.setVisibility(VISIBLE);
            } else {
                Holder.LayoutForHideAdddset.setVisibility(GONE);
            }
            try {
                UtilityClass utilityClass = new UtilityClass();
                Log.e(VolleyLog.TAG, "Videoname: " + Videoname);
                if (TypeofScreenSummary) {
                    Holder.video_thumb.setVisibility(VISIBLE);
                    Holder.video_thumb.setImageBitmap(utilityClass.retriveVideoFrameFromVideo(Videoname));
                }


            } catch (Throwable v) {
                Log.e(VolleyLog.TAG, "onBindViewHolder: " + v);
            }


            Holder.addsetAthleteExcersiseDose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TypeofScreenSummary) {
                        return;
                    }
                    String assign_program_id = getTrainingProgramAssignDetailByIdMultiples.get(0).getAssignProgramId();
                    String Athlete_id = getTrainingProgramAssignDetailByIdMultiples.get(0).getAthleteId();
                    String training_exercise_id = measurement.get(0).getTrainingExerciseId();
                    if (indexofMeasurementofI == 1) {
                        String measurement_id = measurement.get(0).getMeasurementId();
                        try {
                            measurement.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                    }
                    if (indexofMeasurementofI == 2) {
                        try {
                            measurement.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                        try {
                            measurement.get(1).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                    }
                    if (indexofMeasurementofI == 3) {
                        try {
                            measurement.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                        try {
                            measurement.get(1).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                        try {
                            measurement.get(2).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(2).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                    }
                    if (indexofMeasurementofI == 4) {
                        try {
                            measurement.get(0).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(0).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                        try {
                            measurement.get(1).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(1).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                        try {
                            measurement.get(2).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(2).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
                        } catch (Exception x) {
                        }
                        try {
                            measurement.get(3).getMeasurementValue().add(new MeasurementValue("0", measurement.get(0).getTrainingExerciseId(), measurement.get(3).getMeasurementId(), "0", assign_program_id, "0", "0", "0", "0", "", ""));
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
                        TypeIndex = X;
                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext1.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    TypeIndex = X;
                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 1");
                } else {
                    MagicTouch();
                }
            });
            Holder.edittext2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                        TypeIndex = X;
                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext2.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    TypeIndex = X;
                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 2");
                } else {
                    MagicTouch();
                }
            });
            Holder.edittext3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
                        TypeIndex = X;
                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext3.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    TypeIndex = X;
                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 3");
                }
            });
            Holder.edittext4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
                        TypeIndex = X;
                        ExerciseIndex = Y;
                        sendValueonServerAddSet(Holder, i);
                        return false;
                    }
                    return false;
                }
            });
            Holder.edittext4.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    TypeIndex = X;
                    ExerciseIndex = Y;
                    sendValueonServerAddSet(Holder, i);
                    Log.e(VolleyLog.TAG, "EDITTEXT 4");
                }
            });

        }


        @Override
        public int getItemCount() {
            int s = 0;
            try {
                s = measurement.get(0).getMeasurementValue().size();
            } catch (Exception x) {
            }
            return s;
        }


        private void sendValueonServerAddSet(RecyclerViewHolder2 Holder, int POSITION) {
            removeFocus();
            try {
                String assign_program_id = getTrainingProgramAssignDetailByIdMultiples.get(0).getAssignProgramId();
                String Athlete_id = LoginScreen.userId;
                String training_exercise_id = measurement.get(0).getTrainingExerciseId();

                String measurement_id_send = "";
                StringBuilder measurement_id_send_autoBuilder = new StringBuilder();

                String custom_dose_coach_id_auto = "";
                StringBuilder custom_dose_coach_id_autoBuilder = new StringBuilder();

                String MeasurementValue = "";
                StringBuilder MeasurementValueBuilder = new StringBuilder();

                String custom_dose_coach_id_auto_copy = "";
                StringBuilder custom_dose_coach_id_auto_copyBuilder = new StringBuilder();


                for (int c = 0; c < indexofMeasurementofI; c++) {
                    if (measurement.get(c).getMeasurementId() != null) {
                        measurement_id_send = measurement_id_send_autoBuilder.append(measurement.get(c).getMeasurementId() + ",").toString();
                    } else {
                        measurement_id_send = measurement_id_send_autoBuilder.append("0,").toString();
                    }

                    if (measurement.get(c).getMeasurementValue().get(POSITION).getAthleteCustomMeasurementValuesAutoId() != null) {
                        custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append(measurement.get(c).getMeasurementValue().get(POSITION).getAthleteCustomMeasurementValuesAutoId() + ",").toString();
                    } else {
                        custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                    }

                    if (measurement.get(c).getMeasurementValue().get(POSITION).getCustomDoseCoachIdAuto() != null) {
                        custom_dose_coach_id_auto = custom_dose_coach_id_autoBuilder.append(measurement.get(c).getMeasurementValue().get(POSITION).getCustomDoseCoachIdAuto() + ",").toString();
                    } else if (measurement.get(c).getMeasurementValue().get(POSITION).getIdAuto() != null) {
                        custom_dose_coach_id_auto = custom_dose_coach_id_autoBuilder.append(measurement.get(c).getMeasurementValue().get(POSITION).getIdAuto() + ",").toString();
                    } else {
                        custom_dose_coach_id_auto = custom_dose_coach_id_autoBuilder.append("0,").toString();
                    }

                }


                if (indexofMeasurementofI >= 1) {
                    MeasurementValue = MeasurementValueBuilder.append(Holder.edittext1.getText().toString() + ",").toString();
                    measurement.get(0).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext1.getText().toString());
                }
                if (indexofMeasurementofI >= 2) {
                    measurement.get(1).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext2.getText().toString());
                    MeasurementValue = MeasurementValueBuilder.append(Holder.edittext2.getText().toString() + ",").toString();
                }
                if (indexofMeasurementofI >= 3) {
                    measurement.get(2).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext3.getText().toString());
                    MeasurementValue = MeasurementValueBuilder.append(Holder.edittext3.getText().toString() + ",").toString();
                }
                if (indexofMeasurementofI >= 4) {
                    measurement.get(3).getMeasurementValue().get(POSITION).setMeasurementValue(Holder.edittext4.getText().toString());
                    MeasurementValue = MeasurementValueBuilder.append(Holder.edittext4.getText().toString() + ",").toString();
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


//                    AddAndUpdateExerciseSet("Addset", custom_dose_coach_id_auto, measurement.get(0).getTrainingExerciseId(),
//                            Athlete_id, assign_program_id, measurement_id_send, MeasurementValue);
                webServices = new WebServices();
                APITOGGLE = "Addset";
                hide();
                webServices.AddAndUpdateExerciseSet(custom_dose_coach_id_auto, measurement.get(0).getTrainingExerciseId(), ATHLETE_ID, assign_program_id, measurement_id_send, MeasurementValue, custom_dose_coach_id_auto_copy, context, AthleteExerciseSetActivity.this);

            } catch (Exception v) {

            }
            //notifyDataSetChanged();
        }


    }


    private class TypeData extends RecyclerView.Adapter<TypeData.TeamDataHolder> {
        int Y;
        Context context;
        int position;

        public TypeData(int Y, Context context, int position) {
            this.Y = Y;
            this.context = context;
            this.position = position;
        }

        @Override
        public TypeData.TeamDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.team_name_list, viewGroup, false);
            return new TypeData.TeamDataHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TypeData.TeamDataHolder holder, int i) {
            if (getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().size() == 1) {
                holder.TypeArrow.setVisibility(GONE);
            } else {
                holder.TypeArrow.setVisibility(VISIBLE);
            }
            if (i == getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().size() - 1) {
                holder.TypeArrow.setVisibility(GONE);
            }

            holder.teamName.setText(getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getName());
            try {
                if (ActiveId == i) {
                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_bg_yellow);
                    holder.teamName.setTextColor(Color.BLACK);
                    CoachNotesForExercise.setText(getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getExerciseTypeNotesDetail());
                    try {
                        String s = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getExerciseTimeDuration();
                        textViewRunningTimeValueMinuteshow.setText(s.substring(0, 3));
                        textViewRunningTimeValuesecondsshow.setText(s.substring(3, 5) + ".");
                        textViewRunningTimeValueMilisecondsshow.setText(s.substring(6, 8));
                    } catch (Exception x) {
                        textViewRunningTimeValueMinuteshow.setText("00:");
                        textViewRunningTimeValuesecondsshow.setText("00.");
                        textViewRunningTimeValueMilisecondsshow.setText("00");
                    }
                    TypeId = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getId();
                    exercise_type_id = TypeId;
                    try {
                        exercise_id = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getExercises().get(0).getExerciseId();
                    } catch (Exception v) {
                        exercise_id = "0";
                    }
                    try {
                        recyclerViewExerciseData = new RecyclerViewExerciseData(i, 1, getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getExercises());
                        RecyclerViewtypesDataExecersise.setAdapter(recyclerViewExerciseData);
                        RecyclerViewtypesDataExecersise.setHasFixedSize(true);


                    } catch (Exception x) {
                    }
                    if (Phase == Integer.parseInt(UtilityClass.getPreferences(context, "Phase")) &&
                            Week == Integer.parseInt(UtilityClass.getPreferences(context, "Week")) &&
                            Day == Integer.parseInt(UtilityClass.getPreferences(context, "Day")) &&
                            IndexofPiller == Integer.parseInt(UtilityClass.getPreferences(context, "Pillar")) &&
                            TypeId.equalsIgnoreCase(UtilityClass.getPreferences(context, "Type")) &&
                            program_id.equalsIgnoreCase(UtilityClass.getPreferences(context, "program_id"))) {
                        CheckTImeOf_Exercise();
                    }
                } else {
                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
                    holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
                }
            } catch (Exception v) {

            }

            holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.rLayoutofTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (ActiveId == i) {

                    } else {
                        if (startRun) {

                            ShowStopTimerDialog();
                            return;
                        }
                        ActiveId = i;
                        try {
                            String s = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getExerciseTimeDuration();
                            textViewRunningTimeValueMinuteshow.setText(s.substring(0, 3));
                            textViewRunningTimeValuesecondsshow.setText(s.substring(3, 5) + ".");
                            textViewRunningTimeValueMilisecondsshow.setText(s.substring(6, 8));

                        } catch (Exception x) {
                            textViewRunningTimeValueMinuteshow.setText("00:");
                            textViewRunningTimeValuesecondsshow.setText("00.");
                            textViewRunningTimeValueMilisecondsshow.setText("00");
                            Log.e(VolleyLog.TAG, "insertTeamsInHorizontalScrollView: " + x);
                        }

                        TypeId = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getId();
                        exercise_type_id = TypeId;
                        try {
                            exercise_id = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getExercises().get(0).getExerciseId();
                        } catch (Exception v) {
                            exercise_id = "0";
                        }

                        try {
                            recyclerViewExerciseData = new RecyclerViewExerciseData(i, 1, getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getExercises());
                            RecyclerViewtypesDataExecersise.setAdapter(recyclerViewExerciseData);
                            RecyclerViewtypesDataExecersise.setHasFixedSize(true);
                        } catch (Exception x) {
                            ////////Log.e(VolleyLog.TAG, "onClick: "+x);
                        }

                        callTimer(false);
                        notifyDataSetChanged();

                    }
                    if (TypeofScreenSummary) {
                        return;
                    }
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];

                    addNote(context, x, y, "Notes", getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().get(i).getExerciseTypeNotesDetail(), view);
                }
            });
        }

        @Override
        public int getItemCount() {
            int itemCount = 0;
            try {
                itemCount = getTrainingProgramAssignDetailByIdMultiples.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(IndexofPiller).getTypes().size();
            } catch (Exception x) {
            }
            ExerciseArray = itemCount > 0;
            return itemCount;
        }

        private class TeamDataHolder extends RecyclerView.ViewHolder {
            TextView teamName;
            RelativeLayout rLayoutofTeam;
            ImageView TypeArrow;

            public TeamDataHolder(@NonNull View itemView) {
                super(itemView);
                teamName = itemView.findViewById(R.id.teamName);
                rLayoutofTeam = itemView.findViewById(R.id.rLayoutofTeam);
                TypeArrow = itemView.findViewById(R.id.TypeArrow);
            }
        }
    }


}

