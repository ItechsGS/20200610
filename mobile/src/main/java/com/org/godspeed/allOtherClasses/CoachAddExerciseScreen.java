package com.org.godspeed.allOtherClasses;

/**
 * Created by Tanveer on 9/7/2019.
 **/

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.AbrPojo.getAbrNames.GetAbrName;
import com.org.godspeed.response_JsonS.AddTraining.AddTrainingProgram;
import com.org.godspeed.response_JsonS.AddTraining.Day;
import com.org.godspeed.response_JsonS.AddTraining.Exercise;
import com.org.godspeed.response_JsonS.AddTraining.Measurement;
import com.org.godspeed.response_JsonS.AddTraining.Phase;
import com.org.godspeed.response_JsonS.AddTraining.Pillar;
import com.org.godspeed.response_JsonS.AddTraining.Type;
import com.org.godspeed.response_JsonS.AddTraining.Week;
import com.org.godspeed.response_JsonS.ExerciseNameList.ExerciseName;
import com.org.godspeed.response_JsonS.exerciseTypeName.ExerciseTypeName;
import com.org.godspeed.service.OnSwipeTouchListener;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.ExoPlayerActivity;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.TraningNameClass;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.android.volley.VolleyLog.TAG;
import static com.org.godspeed.utility.GlobalClass.PillarName;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.UtilityClass.urlEncoded;


public class CoachAddExerciseScreen extends Activity implements GodSpeedInterface {

    public static List<GetAbrName> getAbrNames;
    public static Boolean RefreshTrainingPhase = false;
    public static Vector<TraningNameClass> vectorTrining;
    public static ArrayList<String> mylist = new ArrayList<String>();
    public static ArrayList<ExerciseName> exerciseNameList;
    public static List<ExerciseTypeName> exerciseTypeNameList;
    public CartListAdapter mAdapter;
    RelativeLayout XCX;
    String[] ac = {"1", "2", "3"};
    ScrollView nestedScrollView;
    LinearLayout.LayoutParams layoutParamsX;
    String TrainingId = "";//
    WebServices webServices = new WebServices();
    String whichapicAlled;
    View view;
    int Phase, Week, Day, indexofPillar = 0;
    int PhaseForShow, WeekForShow, DayForShow, indexofPillarForShow = 0;
    int TotalPhase, TotalWeek, TotalDay, TotalPillar = 0;
    RelativeLayout rLayoutForPiller, videoView;
    RelativeLayout raddExercise, RlayoutForData;
    int typesPosition = 0;
    int ExercisePositiion = 0;
    String custom_dose_id = "";

    String custom_dose_coach_id_auto_copy = "";
    StringBuilder custom_dose_idSB = new StringBuilder();

    String custom_dose_coach_id_auto = "";
    StringBuilder custom_dose_coach_id_autoSB = new StringBuilder();

    StringBuilder custom_dose_coach_id_auto_copyBuilder = new StringBuilder();
    int CopyPhaseFrom = -1;
    int CopyWeekFrom = -1;
    int CopyDayFrom = -1;
    Bundle bundle;
    RelativeLayout rLayoutMain;
    List<Integer> colorList = new ArrayList<>();
    private GestureDetectorCompat detector;
    private TraningNameClass objTrainingData;
    private View layoutNoOfPhases;
    private ImageView imageViewUpArrow, imageViewDownArrow, imageViewSave, imageViewBackArrow;
    //public static List<Abrdetails2> getAbrDetail;
    private ListView listViewTrainingPhases;
    //private ListViewAdapter adapter;
    private Context context;
    private TextView textViewNoOfPhases, textViewPhaseName, textViewScreenName;
    private TextView copyTrainingPhase, AddTrainingPhase, DeleteTrainingPhase;
    private TextView copyTrainingWeek, AddTrainingWeek, DeleteTrainingWeek;
    private TextView copyTrainingDay, AddTrainingDay, DeleteTrainingDay;
    private TextView PhaseTotalCountWeek, PhaseCountWeek;
    private TextView PhaseTotalCountDay, PhaseCountDay;
    private TextView PhaseTotalCountPhase;
    private TextView PhaseIdForL2, WeekIdforL2, DayIdforL2, PillarIdforL2;
    private TextView PhaseTotalCount, PhaseCount, PillerNameCount, textViewPhaseNameWeek;
    private SwipeRevealLayout swipe_layoutPhase, swipe_layoutWeek, swipe_layoutDay;
    private RecyclerView phaseRecycler;
    private LinearLayout LlayoutforData, L2, L3, LlayoutforDataForNewLayout;
    private boolean isAnimationStarted = false;
    private Animation zoomIn, zoomOut;
    private ImageView imageViewForZoomInOut;
    private List<AddTrainingProgram> addTrainingPrograms;
    private VideoView videoViewPlayer;
    private ImageView closeVideo;
    private MediaController mediaController;
    private ProgressDialog progressDialog;
    //private final ViewBinderHelper viewBinderHelperX = new ViewBinderHelper();
    private List<com.org.godspeed.response_JsonS.AddTraining.Day> dayList = new ArrayList<>();
    private List<com.org.godspeed.response_JsonS.AddTraining.Pillar> pillarList = new ArrayList<>();
    //private ExercisDoseDetail exercisDoseDetail;
    private List<com.org.godspeed.response_JsonS.AddTraining.Week> weekList = new ArrayList<>();
    private int noOfPhases = 0;
    private TraningNameClass.Phase objPhases = null;
    //PositionOfType positionofExercise
    private TrainingSetAdapter trainingSetAdapter;
    private BubblePopupWindow dialog;
    private String Screen = "";
    private String AthleteId = "";
    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            // navigation bar height
            int navigationBarHeight = 0;
            int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                navigationBarHeight = getResources().getDimensionPixelSize(resourceId);
            }

            // status bar height
            int statusBarHeight = 0;
            resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }

            // display window size for the app layout
            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        }
    };
    private View AlertBoxView;
    private String newStr = "";

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static String getCurrentTimeUsingDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
        ////Toast(context, formattedDate, Toast.LENGTH_SHORT).show();start
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.coach_add_exercise_screen);
        context = this;
        mAdapter = new CartListAdapter(context);

        //detector = new GestureDetecxtorCompat(context, onSwipeListener);
        TrainingId = getIntent().getStringExtra("TrainingId");
        Screen = getIntent().getStringExtra("Screen");
        //Toast.makeText(context, getIntent().getStringExtra("TrainingId")+"", Toast.LENGTH_SHORT).show();
        Log.e(VolleyLog.TAG, "TrainingId: " + TrainingId);
        //TrainingId = "205";
        //shareTextUrl();
        colorList.add(getResources().getColor(R.color.textColorYellow));
        colorList.add(getResources().getColor(R.color.color_orange));
        colorList.add(getResources().getColor(R.color.color_red_value));
        colorList.add(getResources().getColor(R.color.color_green_value));
        colorList.add(getResources().getColor(R.color.colorPrimary));
        colorList.add(getResources().getColor(R.color.colorPrimary));
        colorList.add(getResources().getColor(R.color.colorPrimary));
        colorList.add(getResources().getColor(R.color.colorPrimary));
        colorList.add(getResources().getColor(R.color.color_green_value));
        colorList.add(getResources().getColor(R.color.color_orange));
        colorList.add(getResources().getColor(R.color.color_green_value));


        addTrainingPrograms = new ArrayList<AddTrainingProgram>();
        Log.e("Screen", "Training Phases");
        textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        imageViewForZoomInOut = findViewById(R.id.imageViewForZoomInOut);

        textViewPhaseNameWeek = findViewById(R.id.textViewPhaseNameWeek);

        rLayoutMain = findViewById(R.id.rLayoutMain);

        copyTrainingPhase = findViewById(R.id.copyTrainingPhase);
        AddTrainingPhase = findViewById(R.id.AddTrainingPhase);
        DeleteTrainingPhase = findViewById(R.id.DeleteTrainingPhase);
        XCX = findViewById(R.id.XCX);

        LlayoutforData = findViewById(R.id.LlayoutforData);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        copyTrainingPhase.setOnClickListener(view1 -> {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipe_layoutPhase.close(true);
                }
            }, 50);
        });

        L2 = findViewById(R.id.L2);
        LlayoutforDataForNewLayout = findViewById(R.id.LlayoutforDataForNewLayout);


        raddExercise = findViewById(R.id.raddExercise);

        if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
            raddExercise.setVisibility(GONE);
        }

        raddExercise.setOnClickListener(view -> {
            int PhaseX = Phase + 1;
            int WeekX = Week + 1;
            int DayX = Day + 1;
            int indexofPillarX = indexofPillar + 1;
            Intent intent = new Intent(context, exercise_Activity.class);

            bundle = new Bundle();
            //exerciseNameList = new ArrayList<>();
            bundle.putSerializable("list", exerciseNameList);
            startActivity(
                    intent.putExtra("TrainingId", TrainingId)
                            .putExtra("phase", PhaseX + "")
                            .putExtra("week", WeekX + "")
                            .putExtra("day", DayX + "")
                            .putExtra("pillar", indexofPillarX + "")
                            .putExtra("wod", "0")
                            .putExtra("wod_description", "")
                            .putExtras(bundle)
            );
            Log.d(VolleyLog.TAG, "*************** exercise_Activity *************");

            overridePendingTransition(R.anim.exit, R.anim.enter);
        });


        copyTrainingWeek = findViewById(R.id.copyTrainingWeek);
        AddTrainingWeek = findViewById(R.id.AddTrainingWeek);
        DeleteTrainingWeek = findViewById(R.id.DeleteTrainingWeek);

        videoViewPlayer = findViewById(R.id.videoViewPlayer);
        videoView = findViewById(R.id.videoView);

        closeVideo = findViewById(R.id.closeVideo);
        closeVideo.setOnClickListener(view -> {
            videoViewPlayer.stopPlayback();
            videoViewPlayer.setMediaController(null);
            videoView.setVisibility(View.INVISIBLE);
            //MainScreen.setVisibility(VISIBLE);
        });

        copyTrainingDay = findViewById(R.id.copyTrainingDay);
        AddTrainingDay = findViewById(R.id.AddTrainingDay);
        DeleteTrainingDay = findViewById(R.id.DeleteTrainingDay);


        AddTrainingPhase.setOnClickListener(view -> {
            try {
                Week = 0;
                Day = 0;
                indexofPillar = 0;
                List<com.org.godspeed.response_JsonS.AddTraining.Day> daysss = new ArrayList<>();
                List<com.org.godspeed.response_JsonS.AddTraining.Week> weekArrayList = new ArrayList<>();

                Log.e(VolleyLog.TAG, "onCreate:befrore " + addTrainingPrograms.get(0).getPhase().size());
                addTrainingPrograms.get(0).getPhase().add(new Phase("", weekArrayList));

                TotalPhase = addTrainingPrograms.get(0).getPhase().size();

                Phase = TotalPhase - 1;

                List<Pillar> pillarArrayList = new ArrayList<>();

                addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().add(0, new Week("", daysss));

                addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(0).getDay().add(0, new Day("", pillarArrayList));
                Trainingprogram_detailadd();
//            UtilityClass.showAlertDailog(context, Phase + "    " +
//                    addTrainingPrograms.get(0).getPhase().get(addTrainingPrograms.get(0).getPhase().size() - 1).getWeek().get(0).getDay().size() + "    "
//                    + addTrainingPrograms.get(0).getPhase().get(addTrainingPrograms.get(0).getPhase().size() - 1).getWeek().size());
                phaseRecycler.setAdapter(null);
                phaseRecycler.setAdapter(mAdapter);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutPhase.close(true);
                    }
                }, 50);
                SetTextOn();
            } catch (Exception v) {

            }

        });

        AddTrainingWeek.setOnClickListener(view -> {
            try {
                indexofPillar = 0;
                Day = 0;
                //Week = 0;


                List<Day> daysss = new ArrayList<>();
                List<Pillar> pillarArrayList = new ArrayList<>();

                addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().add(new Week(TotalWeek + "", daysss));


                addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(0).getDay().add(new Day("", pillarArrayList));


                TotalWeek = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().size();
                Week = TotalWeek - 1;
                //TotalWeek = TotalWeek-1;
                mAdapter.notifyDataSetChanged();
                Trainingprogram_detailadd();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutWeek.close(true);
                    }
                }, 50);
                SetTextOn();
            } catch (Exception v) {

            }
            //addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().add(new Day(TotalDay+"",pillarList));


        });

        AddTrainingDay.setOnClickListener(view -> {
            try {
                addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().add(new Day(TotalDay + "", pillarList));


                TotalDay = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().size();
                Day = TotalDay - 1;
                indexofPillar = 0;
                phaseRecycler.setAdapter(null);
                phaseRecycler.setAdapter(mAdapter);

                Trainingprogram_detailadd();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutDay.close(true);
                    }
                }, 50);
                mAdapter.notifyDataSetChanged();
                SetTextOn();
            } catch (Exception v) {

            }


        });


        DeleteTrainingPhase.setOnClickListener(view -> {
            try {
                addTrainingPrograms.get(0).getPhase().remove(Phase - 1);
                Phase = Phase - 1;
                TotalPhase = TotalPhase - 1;
                phaseRecycler.setAdapter(null);
                phaseRecycler.setAdapter(mAdapter);
                SetTextOn();
            } catch (Exception v) {

            }
        });

        DeleteTrainingWeek.setOnClickListener(view -> {
            try {
                addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().remove(Week - 1);
                Week = Week - 1;
                TotalWeek = TotalWeek - 1;
                phaseRecycler.setAdapter(null);
                phaseRecycler.setAdapter(mAdapter);
                SetTextOn();
            } catch (Exception v) {

            }
        });

        DeleteTrainingDay.setOnClickListener(view -> {
            try {
                addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().remove(Day - 1);
                Day = Day - 1;
                TotalDay = TotalDay - 1;
                phaseRecycler.setAdapter(null);
                phaseRecycler.setAdapter(mAdapter);
                SetTextOn();
            } catch (Exception v) {

            }

        });


        imageViewUpArrow = findViewById(R.id.imageViewUpArrow);
        imageViewDownArrow = findViewById(R.id.imageViewDownArrow);
        L3 = findViewById(R.id.L3);

        PhaseTotalCountWeek = findViewById(R.id.PhaseTotalCountWeek);
        PhaseCountWeek = findViewById(R.id.PhaseCountWeek);
        PhaseTotalCountDay = findViewById(R.id.PhaseTotalCountDay);
        PhaseCountDay = findViewById(R.id.PhaseCountDay);
        PhaseTotalCount = findViewById(R.id.PhaseTotalCount);
        PhaseCount = findViewById(R.id.PhaseCount);

        PillerNameCount = findViewById(R.id.PillerNameCount);
        PhaseIdForL2 = findViewById(R.id.PhaseIdForL2);
        WeekIdforL2 = findViewById(R.id.WeekIdforL2);
        DayIdforL2 = findViewById(R.id.DayIdforL2);
        PillarIdforL2 = findViewById(R.id.PillarIdforL2);
        rLayoutForPiller = findViewById(R.id.rLayoutForPiller);

        swipe_layoutPhase = findViewById(R.id.swipe_layoutPhase);
        swipe_layoutWeek = findViewById(R.id.swipe_layoutWeek);
        swipe_layoutDay = findViewById(R.id.swipe_layoutDay);
        RlayoutForData = findViewById(R.id.RlayoutForData);


        imageViewUpArrow.setOnClickListener(view -> {
            L2.setVisibility(View.VISIBLE);
            L3.setVisibility(View.VISIBLE);
            LlayoutforDataForNewLayout.setVisibility(View.GONE);
            SetTextOn();
        });

        L2.setOnClickListener(view -> {
            L2.setVisibility(View.GONE);
            LlayoutforDataForNewLayout.setVisibility(View.VISIBLE);
            L3.setVisibility(View.GONE);
            SetTextOn();
        });

        PillerNameCount.setText(PillarName[indexofPillar]);

        nestedScrollView.setActivated(false);

        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            Rect scrollBounds = new Rect();
            nestedScrollView.getHitRect(scrollBounds);

            if (!LlayoutforData.getLocalVisibleRect(scrollBounds)) {

                L2.setVisibility(View.VISIBLE);
                SetTextOn();
            } else {
                L2.setVisibility(View.GONE);
                L3.setVisibility(View.GONE);
                SetTextOn();
                LlayoutforDataForNewLayout.setVisibility(View.VISIBLE);
            }
            AutoTransition autoTransition = new AutoTransition();

            autoTransition.setDuration(200);
            TransitionManager.beginDelayedTransition(RlayoutForData, autoTransition);
        });

        swipe_layoutPhase.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return true;
                });
                nestedScrollView.setActivated(false);
                swipe_layoutPhase.setLockDrag(true);

                swipe_layoutPhase.close(true);

                if (swipe_layoutPhase.isClosed()) {

                    if (Phase > 0) {
                        Phase = Phase - 1;
                        mAdapter.notifyDataSetChanged();
                        PhaseCount.setText((Phase) + "");
                        Week = 0;
                        Day = 0;
                        //indexofPillar = 0;
                    }
                    SetTextOn();


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipe_layoutWeek.close(true);
                            swipe_layoutDay.close(true);
                        }
                    }, 50);
                }
                nestedScrollView.setActivated(true);

                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return false;
                });
                //swipe_layoutPhase.setLockDrag(true);
                //mAdapter.notifyDataSetChanged();
                // PhaseCount.setText((Phase)+"");
            }

            @Override
            public void onSwipeLeft() {
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return true;
                });
                nestedScrollView.setActivated(false);
                if (Phase < TotalPhase - 1) {
                    Phase = Phase + 1;
                    phaseRecycler.setAdapter(null);
                    phaseRecycler.setAdapter(mAdapter);
                    PhaseCount.setText((Phase) + "");
                    Week = 0;
                    Day = 0;
                    //indexofPillar = 0;
                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutWeek.close(true);
                        swipe_layoutDay.close(true);
                    }
                }, 50);
                nestedScrollView.setActivated(true);

                SetTextOn();
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return false;
                });
                super.onSwipeLeft();
            }

            @Override
            public void onClick() {
                swipe_layoutPhase.requestDisallowInterceptTouchEvent(true);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                LlayoutforDataForNewLayout.setVisibility(View.GONE);
                SetTextOn();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutWeek.close(true);
                        swipe_layoutDay.close(true);
                    }
                }, 50);
            }

            @Override
            public void onLongClick() {
                swipe_layoutPhase.requestDisallowInterceptTouchEvent(true);
                int[] location = new int[2];
                swipe_layoutPhase.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                CopyPaste(context, x, y, "Phase", "", PhaseCount);
                super.onLongClick();
            }
        });

        swipe_layoutWeek.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return true;
                });
                Log.e(VolleyLog.TAG, "onSwipeRight: " + swipe_layoutWeek.isClosed());
                swipe_layoutWeek.setLockDrag(true);

                swipe_layoutWeek.close(true);

                if (swipe_layoutWeek.isClosed()) {
                    if (Week > 0) {
                        Week = Week - 1;
                        mAdapter.notifyDataSetChanged();
                        PhaseCountWeek.setText((Week) + "");
                        Day = 0;
                        //indexofPillar = 0;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                swipe_layoutWeek.close(true);
                            }
                        }, 50);
                    }
                    SetTextOn();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipe_layoutPhase.close(true);
                            swipe_layoutDay.close(true);
                        }
                    }, 50);
                }
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return false;
                });


                //swipe_layoutWeek.invalidate();

                super.onSwipeRight();
            }

            @Override
            public void onSwipeLeft() {
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return true;
                });
                swipe_layoutWeek.requestDisallowInterceptTouchEvent(true);

                if (Week < TotalWeek - 1) {
                    Week = Week + 1;
                    if (Day > TotalDay - 1) {
                        Day = Day - 1;
                        if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                            swipe_layoutDay.setLockDrag(false);
                        }
                    }
                    Day = 0;

                    //indexofPillar = 0;
                    mAdapter.notifyDataSetChanged();
                    PhaseCountWeek.setText((Week) + "");

                }
                swipe_layoutWeek.clearFocus();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutPhase.close(true);
                        swipe_layoutDay.close(true);
                    }
                }, 50);
                SetTextOn();
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return false;
                });
                super.onSwipeLeft();
            }

            @Override
            public void onClick() {
                swipe_layoutWeek.requestDisallowInterceptTouchEvent(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutPhase.close(true);
                        swipe_layoutDay.close(true);
                    }
                }, 50);
            }

            @Override
            public void onLongClick() {
                swipe_layoutWeek.requestDisallowInterceptTouchEvent(true);
                int[] location = new int[2];
                swipe_layoutWeek.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                CopyPaste(context, x, y, "Week", "", PhaseCountWeek);
                super.onLongClick();
            }

        });

        swipe_layoutDay.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return true;
                });
                swipe_layoutDay.requestDisallowInterceptTouchEvent(true);
                swipe_layoutDay.setLockDrag(true);

                swipe_layoutDay.close(true);

                if (swipe_layoutDay.isClosed()) {
                    if (Day > 0) {
                        Day = Day - 1;
                        mAdapter.notifyDataSetChanged();
                        PhaseCountDay.setText((Day) + "");
                        //indexofPillar = 0;
                    }
                    SetTextOn();

                    swipe_layoutDay.setLockDrag(true);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipe_layoutPhase.close(true);
                            swipe_layoutWeek.close(true);
                        }
                    }, 50);
                }
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return false;
                });
                super.onSwipeRight();
            }


            @Override
            public void onSwipeLeft() {
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return true;
                });
                Log.e(VolleyLog.TAG, "onSwipeLeft: " + Day + "    " + (TotalDay - 1));
                swipe_layoutDay.requestDisallowInterceptTouchEvent(true);
                if (Day < TotalDay) {

                    Day = Day + 1;
                    if (Day > TotalDay - 1) {
                        Day = Day - 1;
                        if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                            swipe_layoutDay.setLockDrag(false);
                        }
                    }
                    if (Day == TotalDay - 1) {
                        if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                            swipe_layoutDay.setLockDrag(false);
                        }
                    }

                    mAdapter.notifyDataSetChanged();
                    PhaseCountDay.setText((Day) + "");
                    //indexofPillar = 0;

                }
                SetTextOn();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutPhase.close(true);
                        swipe_layoutWeek.close(true);
                    }
                }, 50);
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return false;
                });

                super.onSwipeLeft();
            }

            @Override
            public void onClick() {
                swipe_layoutDay.requestDisallowInterceptTouchEvent(true);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutPhase.close(true);
                        swipe_layoutWeek.close(true);
                    }
                }, 50);
            }

            @Override
            public void onLongClick() {
                swipe_layoutDay.requestDisallowInterceptTouchEvent(true);
                int[] location = new int[2];
                swipe_layoutDay.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                CopyPaste(context, x, y, "Day", "", PhaseCountDay);
                super.onLongClick();
            }
        });

        rLayoutForPiller.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                rLayoutForPiller.requestDisallowInterceptTouchEvent(true);
                indexofPillar = ++indexofPillar;
                if (indexofPillar == 4) {
                    indexofPillar = 0;
                }

                Trainingprogram_detailadd();

                mAdapter.notifyDataSetChanged();
                SetTextOn();
                ////Log.e(VolleyLog.TAG, "onSwipeLeft: "+PillarName[indexofPillar]+" index " +indexofPillar);
                PillerNameCount.setText(PillarName[indexofPillar]);

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                swipe_layoutPhase.close(true);
                swipe_layoutWeek.close(true);
                swipe_layoutDay.close(true);
//                    }
//                },50);

            }

            @Override
            public void onSwipeRight() {

                super.onSwipeRight();

                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return true;
                });
                rLayoutForPiller.requestDisallowInterceptTouchEvent(true);
                indexofPillar = --indexofPillar;
                if (indexofPillar == -1) {
                    indexofPillar = 3;
                }
                int PhaseX = Phase + 1;
                int WeekX = Week + 1;
                int DayX = Day + 1;
                int indexofPillarX = indexofPillar + 1;
                Trainingprogram_detailadd();
                mAdapter.notifyDataSetChanged();
                SetTextOn();
                ////Log.e(VolleyLog.TAG, "onSwipeLeft: "+PillarName[indexofPillar]+" index " +indexofPillar);
                PillerNameCount.setText(PillarName[indexofPillar]);


//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                swipe_layoutPhase.close(true);
                swipe_layoutWeek.close(true);
                swipe_layoutDay.close(true);
//                    }
//                },50);
                nestedScrollView.setOnTouchListener((view1, motionEvent) -> {
                    return false;
                });
            }

            @Override
            public void onClick() {
                rLayoutForPiller.requestDisallowInterceptTouchEvent(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_layoutPhase.close(true);
                        swipe_layoutWeek.close(true);
                        swipe_layoutDay.close(true);
                    }
                }, 50);
            }


        });

        phaseRecycler = findViewById(R.id.PhaseRecycler);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        phaseRecycler.setLayoutManager(mLayoutManager);


        //phaseRecycler.setItemAnimator(new DefaultItemAnimator());

        //phaseRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);

        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });

        if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
            whichapicAlled = "getTrainingProgramDetailByIdMultiple";
            AthleteId = getIntent().getStringExtra("AthleteId");
            webServices.getTrainingProgramAssignDetailByIdMultiple(TrainingId, AthleteId, context, CoachAddExerciseScreen.this);
        } else {
            whichapicAlled = "getTrainingProgramDetailById";
            webServices.getTrainingProgramDetailById(TrainingId, context, CoachAddExerciseScreen.this);
        }

        vectorTrining = new Vector<TraningNameClass>();
        objTrainingData = new TraningNameClass();
        objTrainingData.name = getString(R.string.phase) + " " + noOfPhases;
        objPhases = objTrainingData.new Phase();
        objPhases.noOfWeeks = 0;
        objPhases.phaseId = noOfPhases;
        objTrainingData.phaseInTraining = objPhases;
        vectorTrining.add(objTrainingData);


        vectorTrining = new Vector<TraningNameClass>();
        objTrainingData = new TraningNameClass();
        objTrainingData.name = getString(R.string.phase);
        objPhases = objTrainingData.new Phase();
        objPhases.noOfWeeks = 0;
        objPhases.phaseId = noOfPhases;
        objTrainingData.phaseInTraining = objPhases;
        vectorTrining.add(objTrainingData);

        objTrainingData = new TraningNameClass();
        objTrainingData.name = getString(R.string.week);
        objPhases = objTrainingData.new Phase();
        objPhases.noOfWeeks = 0;
        objPhases.phaseId = noOfPhases;
        objTrainingData.phaseInTraining = objPhases;
        vectorTrining.add(objTrainingData);

        objTrainingData = new TraningNameClass();
        objTrainingData.name = getString(R.string.day);
        objPhases = objTrainingData.new Phase();
        objPhases.noOfWeeks = 0;
        objPhases.phaseId = noOfPhases;
        objTrainingData.phaseInTraining = objPhases;
        vectorTrining.add(objTrainingData);


        objTrainingData = new TraningNameClass();
        objTrainingData.name = getString(R.string.pillars);
        objPhases = objTrainingData.new Phase();
        objPhases.noOfWeeks = 0;
        objPhases.phaseId = noOfPhases;
        objTrainingData.phaseInTraining = objPhases;
        vectorTrining.add(objTrainingData);


        imageViewSave = findViewById(R.id.imageViewSave);
        imageViewSave.setVisibility(GONE);
        imageViewSave.setImageResource(R.drawable.save_training);
        imageViewSave.setOnClickListener(v -> {
//                //Toast(context, "Call API For Save Data", Toast.LENGTH_LONG).show();
//                new AlertDialog.Builder(context).setTitle("God Speed \n Traning Programm Name")
            //showAlertDialog();
        });
    }

    private void SetTextOn() {
        try {
            TotalPhase = addTrainingPrograms.get(0).getPhase().size();
            if (TotalPhase == 0) {
                TotalPhase = 1;
            }
        } catch (Exception x) {
            Log.e(VolleyLog.TAG, "SetTextOn: x");
            if (TotalPhase == 0) {
                TotalPhase = 1;
            }
        }

        try {
            TotalWeek = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().size();
            if (TotalWeek == 0) {
                TotalWeek = 1;
            }
        } catch (Exception x) {
            Log.e(VolleyLog.TAG, "SetTextOn: " + x);
            if (TotalWeek == 0) {
                TotalWeek = 1;
            }
        }

        try {
            TotalDay = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().size();
            if (TotalDay == 0) {
                TotalDay = 1;
            }
        } catch (Exception x) {
            Log.e(VolleyLog.TAG, "SetTextOn: " + x);
            if (TotalDay == 0) {
                TotalDay = 1;
            }
        }
        try {

            if (addTrainingPrograms.size() != 0) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) raddExercise.getLayoutParams();
                params.setMargins(0, 0, 0, 0);
                raddExercise.setLayoutParams(params);
            }
        } catch (Exception x) {
            Log.e(VolleyLog.TAG, "SetTextOn: " + x);
        }


//            TotalWeek = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().size();
//            TotalDay = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().size();

        PhaseTotalCountDay.setText((TotalDay) + "");
        PhaseTotalCountWeek.setText((TotalWeek) + "");

        PillerNameCount.setText(PillarName[indexofPillar]);
        PhaseCount.setText((Phase + 1) + "");
        PhaseCountWeek.setText((Week + 1) + "");
        PhaseCountDay.setText((Day + 1) + "");

        if ((Phase + 1) == TotalPhase) {
            if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                swipe_layoutPhase.setLockDrag(false);
            }
        } else {
            swipe_layoutPhase.setLockDrag(true);
        }

        if (Week == TotalWeek - 1) {
            if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                swipe_layoutWeek.setLockDrag(false);
            }
        } else {
            swipe_layoutWeek.setLockDrag(true);
        }

        if (Day == TotalDay - 1) {
            if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                swipe_layoutDay.setLockDrag(false);
            }
        } else {
            swipe_layoutDay.setLockDrag(true);
        }


        PhaseIdForL2.setText((Phase + 1) + "");
        WeekIdforL2.setText((Week + 1) + "");
        DayIdforL2.setText((Day + 1) + "");
        PillarIdforL2.setText(PillarName[indexofPillar].substring(0, 3).toUpperCase() + "");


        PhaseTotalCount.setText((TotalPhase) + "");
        PhaseTotalCountWeek.setText((TotalWeek) + "");
        PhaseTotalCountDay.setText((TotalDay) + "");

        textViewPhaseNameWeek.setTextColor(colorList.get(Week));
    }

    private void SetVisiblityGone() {
        copyTrainingPhase.setVisibility(GONE);
        AddTrainingPhase.setVisibility(GONE);
        DeleteTrainingPhase.setVisibility(GONE);
    }

    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, WebServices.BASE_URL);

        startActivity(Intent.createChooser(share, "Share link!"));
    }


    // Method to share any image.
    private void shareImage() {
        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");

        // Make sure you put example png image named myImage.png in your
        // directory
        String imagePath = Environment.getExternalStorageDirectory()
                + "/myImage.png";

        File imageFileToShare = new File(imagePath);

        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(share, "Share Image!"));
    }

    private void SetVisiblity() {
        copyTrainingPhase.setVisibility(VISIBLE);
        AddTrainingPhase.setVisibility(VISIBLE);
        DeleteTrainingPhase.setVisibility(VISIBLE);
    }

    private void parseFolderData(String result) {
        Log.e(VolleyLog.TAG, "parseFolderData: " + result);
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);
            ////Log.e(VolleyLog.TAG, "parseFolderData:result " + result);
            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj.getString("responseMessage");
            Gson gson = new Gson();

            if (WebServices.responseCode == 200) {

                if (whichapicAlled.equalsIgnoreCase("getTrainingProgramDetailById") || whichapicAlled.equalsIgnoreCase("getTrainingProgramDetailByIdAgain") || whichapicAlled.equalsIgnoreCase("getTrainingProgramDetailByIdMultiple")) {

                    JSONArray jsonArray = jsonObj.getJSONArray("data");

                    addTrainingPrograms = Arrays.asList(gson.fromJson(jsonArray.toString(), AddTrainingProgram[].class));

                    textViewScreenName.setText(getString(R.string.plan_program) + " - " + addTrainingPrograms.get(0).getProgramName());

                    //addTrainingPrograms.get(0).getPhase().get(0).getWeek().get(0).getDay().get().getPillar().get().getTypes().get().get
                    //for(int phase = 0;phase<addTrainingPrograms.get(0).getPhase().size();phase++){
                    TotalPhase = addTrainingPrograms.get(0).getPhase().size();
                    if (whichapicAlled.equalsIgnoreCase("getTrainingProgramDetailByIdAgain")) {
                        whichapicAlled = "";
                    }

                    if (addTrainingPrograms.get(0).getPhase().size() == 0) {
                        whichapicAlled = "newAdded";
                        hide();
                        //UtilityClass.showWaitDialog(new Dialog(context),context);
                        webServices.Trainingprogram_detailadd(TrainingId, 1 + "", 1 + "", 1 + "", 1 + "", context, CoachAddExerciseScreen.this);

                    } else {
                        whichapicAlled = "getAbrName";
                        hide();
                        webServices.getPrescriptionAbr(context, CoachAddExerciseScreen.this);
                    }


                    PhaseTotalCount.setText((TotalPhase) + "");
                    PhaseTotalCountWeek.setText((TotalWeek) + "");
                    PhaseTotalCountDay.setText((TotalDay) + "");

                    phaseRecycler.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                    check();
                    SetTextOn();


                } else if (whichapicAlled.equalsIgnoreCase("getAbrName")) {
                    JSONArray jsonArray = jsonObj.getJSONArray("data");
                    getAbrNames = Arrays.asList(gson.fromJson(jsonArray.toString(), GetAbrName[].class));
                } else if (whichapicAlled.equalsIgnoreCase("newAdded")) {
                    //UtilityClass.showWaitDialog(new Dialog(context),context);
                    whichapicAlled = "getTrainingProgramDetailByIdAgain";
                    hide();
                    webServices.getTrainingProgramDetailById(TrainingId, context, CoachAddExerciseScreen.this);


                } else if (whichapicAlled.equalsIgnoreCase("getAbrDetail")) {
                    String usersData = jsonObj
                            .getString("data");
                } else if (whichapicAlled.equalsIgnoreCase("copyPhaseWeekExercise")) {
                    //UtilityClass.showWaitDialog(new Dialog(context),context);
                    whichapicAlled = "getTrainingProgramDetailByIdAgain";

                    webServices.getTrainingProgramDetailById(TrainingId, context, CoachAddExerciseScreen.this);
                } else if (whichapicAlled.equalsIgnoreCase("Trainingprogram_detailadd")) {

                    SetTextOn();


                } else if (whichapicAlled.equalsIgnoreCase("addCustomDoseByCoach") || whichapicAlled.equalsIgnoreCase("AddAndUpdateExerciseSet")) {
                    JSONArray jsonArray = jsonObj.getJSONArray("data");
                    List<Measurement> measurements;
                    measurements = Arrays.asList(gson.fromJson(jsonArray.toString(), Measurement[].class));


                    addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                            .getPillar().get(indexofPillar).getTypes().get(typesPosition).getExercises().get(ExercisePositiion).getMeasurement().clear();

                    mAdapter.notifyDataSetChanged();

                    addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                            .getPillar().get(indexofPillar).getTypes().get(typesPosition).getExercises().get(ExercisePositiion).getMeasurement().addAll(measurements);

                    mAdapter.notifyDataSetChanged();

                }
//                else if(whichapicAlled.equalsIgnoreCase("getTrainingProgramDetailByIdMultiple")){
//                    JSONArray jsonArray = jsonObj.getJSONArray("data");
//                    addTrainingPrograms = Arrays.asList(gson.fromJson(jsonArray.toString(), AddTrainingProgram[].class));
//                    textViewScreenName.setText(getString(R.string.plan_program) + " - " + addTrainingPrograms.get(0).getProgramName());
//                    TotalPhase = addTrainingPrograms.get(0).getPhase().size();
//                    if (whichapicAlled.equalsIgnoreCase("getTrainingProgramDetailByIdAgain")) {
//                        whichapicAlled = "";
//                    }
//
//                    if (addTrainingPrograms.get(0).getPhase().size() == 0) {
//                        whichapicAlled = "newAdded";
//                        hide();
//                        webServices.Trainingprogram_detailadd(TrainingId, 1 + "", 1 + "", 1 + "", 1 + "", context, CoachAddExerciseScreen.this);
//                    } else {
//                        whichapicAlled = "getAbrName";
//                        hide();
//                        webServices.getPrescriptionAbr(context, CoachAddExerciseScreen.this);
//                    }
//
//
//                    PhaseTotalCount.setText((TotalPhase) + "");
//                    PhaseTotalCountWeek.setText((TotalWeek) + "");
//                    PhaseTotalCountDay.setText((TotalDay) + "");
//
//                    phaseRecycler.setAdapter(mAdapter);
//                    mAdapter.notifyDataSetChanged();
//
//                }
            } else {
                if (!whichapicAlled.equalsIgnoreCase("Trainingprogram_detailadd")) {
                    UtilityClass.showAlertDailog(context, responseMessage);
                }
            }
        } catch (Exception e) {
            Log.e(VolleyLog.TAG, "parseFolderData:EE " + e);
        }


        if (!whichapicAlled.equalsIgnoreCase("newAdded") || !whichapicAlled.equalsIgnoreCase("getTrainingProgramDetailByIdAgain")) {
            UtilityClass.hide();
            //UtilityClass.showWaitDialog(new Dialog(context),context);
        }

//        if (whichapicAlled.equalsIgnoreCase("getAbrName")) {
//            UtilityClass.hide();
//        }
        ////Log.e(VolleyLog.TAG, "parseFolderData: " + whichapicAlled);

    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        boolean keepOriginal = true;
        StringBuilder sb = new StringBuilder(end - start);
        for (int i = start; i < end; i++) {
            char c = source.charAt(i);
            if (isCharAllowed(c)) // put your condition here
                sb.append(c);
            else
                keepOriginal = false;
        }
        if (keepOriginal)
            return null;
        else {
            if (source instanceof Spanned) {
                SpannableString sp = new SpannableString(sb);
                TextUtils.copySpansFrom((Spanned) source, start, end, null, sp, 0);
                return sp;
            } else {
                return sb;
            }
        }
    }

    private boolean isCharAllowed(char c) {
        return Character.isUpperCase(c) || Character.isDigit(c);
    }

    public void showDialog(Context context, View excerciseName, String event, String eventData, String typeofEvent, List<Measurement> measurement, int Position, String exercise) {

        AlertBoxView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_box_for_sets, null);
        BubbleLinearLayout bubbleView = AlertBoxView.findViewById(R.id.mainRlyofAddset);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);

        dialog.showArrowTo(excerciseName, BubbleStyle.ArrowDirection.Down);
        dialog.setCancelOnTouch(false);

        AlertBoxView.getLayoutParams().width = dpToPx(250);
        AlertBoxView.getLayoutParams().height = dpToPx(170);


        TextView txt = AlertBoxView.findViewById(R.id.EventName);
        ImageView addsetSave = AlertBoxView.findViewById(R.id.addsetSave);
        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txt.setText(event);


        TextView txtData = AlertBoxView.findViewById(R.id.eventData);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txtData.setText(eventData);
        txtData.setVisibility(GONE);


        TextView lbl1 = AlertBoxView.findViewById(R.id.lbl1);
        TextView lbl2 = AlertBoxView.findViewById(R.id.lbl2);
        TextView lbl3 = AlertBoxView.findViewById(R.id.lbl3);
        TextView lbl4 = AlertBoxView.findViewById(R.id.lbl4);
        TextView lbl5 = AlertBoxView.findViewById(R.id.lbl5);


        EditText edittext1 = AlertBoxView.findViewById(R.id.edittext1);
        EditText edittext2 = AlertBoxView.findViewById(R.id.edittext2);
        EditText edittext3 = AlertBoxView.findViewById(R.id.edittext3);
        EditText edittext4 = AlertBoxView.findViewById(R.id.edittext4);
        EditText edittext5 = AlertBoxView.findViewById(R.id.edittext5);


        edittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int m = 0;
                Boolean n = false;

                for (int i = 0; i < s.toString().length(); i++) {
                    String c = String.valueOf(s.charAt(i));
                    if (c.equalsIgnoreCase("/")) {
                        m++;
                        if (m == 2 && exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (m == 1 && !exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (n) {
                            String text = edittext1.getText().toString();
                            text = text.substring(0, i) + text.substring(i + 1);
                            edittext1.setText(text);
                            edittext1.setSelection(edittext1.getText().length());
                        }
                    }
                }
            }
        });
        edittext2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int m = 0;
                Boolean n = false;

                for (int i = 0; i < s.toString().length(); i++) {
                    String c = String.valueOf(s.charAt(i));
                    if (c.equalsIgnoreCase("/")) {
                        m++;
                        if (m == 2 && exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (m == 1 && !exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (n) {
                            String text = edittext2.getText().toString();
                            text = text.substring(0, i) + text.substring(i + 1);
                            edittext2.setText(text);
                            edittext2.setSelection(edittext2.getText().length());
                        }
                    }
                }
            }
        });
        edittext3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int m = 0;
                Boolean n = false;

                for (int i = 0; i < s.toString().length(); i++) {
                    String c = String.valueOf(s.charAt(i));
                    if (c.equalsIgnoreCase("/")) {
                        m++;
                        if (m == 2 && exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (m == 1 && !exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (n) {
                            String text = edittext3.getText().toString();
                            text = text.substring(0, i) + text.substring(i + 1);
                            edittext3.setText(text);
                            edittext3.setSelection(edittext3.getText().length());
                        }
                    }
                }
            }
        });
        edittext4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int m = 0;
                Boolean n = false;

                for (int i = 0; i < s.toString().length(); i++) {
                    String c = String.valueOf(s.charAt(i));
                    if (c.equalsIgnoreCase("/")) {
                        m++;
                        if (m == 2 && exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (m == 1 && !exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (n) {
                            String text = edittext4.getText().toString();
                            text = text.substring(0, i) + text.substring(i + 1);
                            edittext4.setText(text);
                            edittext4.setSelection(edittext4.getText().length());
                        }
                    }
                }
            }
        });
        edittext5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int m = 0;
                Boolean n = false;

                for (int i = 0; i < s.toString().length(); i++) {
                    String c = String.valueOf(s.charAt(i));
                    if (c.equalsIgnoreCase("/")) {
                        m++;
                        if (m == 2 && exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (m == 1 && !exercise.equalsIgnoreCase("1")) {
                            n = true;
                        }
                        if (n) {
                            String text = edittext5.getText().toString();
                            text = text.substring(0, i) + text.substring(i + 1);
                            edittext5.setText(text);
                            edittext5.setSelection(edittext5.getText().length());
                        }
                    }
                }
            }
        });

//            edittext1.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    if (exercise != null && exercise.equalsIgnoreCase("1")) {
//                        String k = edittext1.getText().toString();
//                        String newData = k.replace("//", "/");
//
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                edittext1.setText(newData);
//                            }
//                        },200);
//
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });
//            edittext2.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    if (exercise != null && exercise.equalsIgnoreCase("1")) {
//                        String k = edittext2.getText().toString();
//
//                        String newData = k.replaceAll("//", "/");
//
//
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                edittext2.setText(newData);
//                            }
//                        },200);
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });
//            edittext3.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    if (exercise != null && exercise.equalsIgnoreCase("1")) {
//                        String k = edittext3.getText().toString();
//
//                        String newData = k.replaceAll("//", "/");
//
//
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                edittext3.setText(newData);
//                            }
//                        },200);
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });
//            edittext4.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    if (exercise != null && exercise.equalsIgnoreCase("1")) {
//                        String k = edittext4.getText().toString();
//
//                        String newData = k.replaceAll("//", "/");
//
//
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                edittext4.setText(newData);
//                            }
//                        },200);
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });
//            edittext5.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    if (exercise != null && exercise.equalsIgnoreCase("1")) {
//                        String k = edittext5.getText().toString();
//
//                        String newData = k.replaceAll("//", "/");
//
//
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                edittext5.setText(newData);
//                            }
//                        },200);
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });


        LinearLayout llayoutAddset1 = AlertBoxView.findViewById(R.id.llayoutAddset1);
        LinearLayout llayoutAddset2 = AlertBoxView.findViewById(R.id.llayoutAddset2);
        LinearLayout llayoutAddset3 = AlertBoxView.findViewById(R.id.llayoutAddset3);
        LinearLayout llayoutAddset4 = AlertBoxView.findViewById(R.id.llayoutAddset4);
        LinearLayout llayoutAddset5 = AlertBoxView.findViewById(R.id.llayoutAddset5);


        LinearLayout ReventName = AlertBoxView.findViewById(R.id.lAddset);

        ReventName.setVisibility(VISIBLE);

        custom_dose_idSB = new StringBuilder();
        custom_dose_coach_id_autoSB = new StringBuilder();
        custom_dose_coach_id_auto_copyBuilder = new StringBuilder();


        for (int i = 0; i < measurement.size(); i++) {
            if (i == 0 && measurement.get(0).getMeasurementName() != null) {
                llayoutAddset1.setVisibility(VISIBLE);
                lbl1.setText(measurement.get(i).getMeasurementName());
                try {
                    if (measurement.get(i).getMeasurementValue().size() > 0) {
                        if (typeofEvent == "updateSet") {
                            edittext1.setText(measurement.get(i).getMeasurementValue().get(Position).getMeasurementValue());
                        }
                        if (measurement.get(i).getMeasurementValue().get(Position).getId() != null) {
                            custom_dose_id = custom_dose_idSB.append(measurement.get(i).getMeasurementValue().get(Position).getId() + ",").toString();
                        }

                        if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                            if (eventData.equalsIgnoreCase("add")) {
                                custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                            } else {
                                //custom_dose_coach_id_auto
                                if (measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() + ",").toString();
                                } else if (measurement.get(i).getMeasurementValue().get(Position).getIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getIdAuto() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                }

                                if (measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() != null) {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append(measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                                }
                            }
                        }


                    }
                } catch (Exception c) {

                }

                edittext1.setTag(measurement.get(i).getMeasurementId());
            }

            if (i == 1 && measurement.get(1).getMeasurementName() != null) {
                llayoutAddset2.setVisibility(VISIBLE);
                lbl2.setText(measurement.get(i).getMeasurementName());
                try {
                    if (measurement.get(i).getMeasurementValue().size() > 0 && measurement.get(i).getMeasurementValue().get(Position) != null) {
                        if (typeofEvent == "updateSet") {
                            edittext2.setText(measurement.get(i).getMeasurementValue().get(Position).getMeasurementValue());
                        }
                        if (measurement.get(i).getMeasurementValue().get(Position).getId() != null) {
                            custom_dose_id = custom_dose_idSB.append(measurement.get(i).getMeasurementValue().get(Position).getId() + ",").toString();
                        }

                        if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                            if (eventData.equalsIgnoreCase("add")) {
                                custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                            } else {
                                if (measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() + ",").toString();
                                } else if (measurement.get(i).getMeasurementValue().get(Position).getIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getIdAuto() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                }
                                if (measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() != null) {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append(measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                                }
                            }
                        }
                    }
                } catch (Exception v) {

                }

                edittext2.setTag(measurement.get(i).getMeasurementId());
            }

            if (i == 2 && measurement.get(2).getMeasurementName() != null) {
                llayoutAddset3.setVisibility(VISIBLE);
                lbl3.setText(measurement.get(i).getMeasurementName());

                try {
                    if (measurement.get(i).getMeasurementValue().size() > 0) {
                        if (typeofEvent == "updateSet") {

                            edittext3.setText(measurement.get(i).getMeasurementValue().get(Position).getMeasurementValue());
                        }
                        if (measurement.get(i).getMeasurementValue().get(Position).getId() != null) {
                            custom_dose_id = custom_dose_idSB.append(measurement.get(i).getMeasurementValue().get(Position).getId() + ",").toString();
                        }
                        if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                            if (eventData.equalsIgnoreCase("add")) {
                                custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                            } else {
                                if (measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() + ",").toString();
                                } else if (measurement.get(i).getMeasurementValue().get(Position).getIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getIdAuto() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                }
                                if (measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() != null) {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append(measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                                }
                            }
                        }


                    }
                } catch (Exception s) {
                }

                edittext3.setTag(measurement.get(i).getMeasurementId());
            }

            if (i == 3 && measurement.get(3).getMeasurementName() != null) {
                llayoutAddset4.setVisibility(VISIBLE);
                lbl4.setText(measurement.get(i).getMeasurementName());
                try {
                    if (measurement.get(i).getMeasurementValue().size() > 0) {
                        if (typeofEvent == "updateSet") {
                            edittext4.setText(measurement.get(i).getMeasurementValue().get(Position).getMeasurementValue());
                        }
                        if (measurement.get(i).getMeasurementValue().get(Position).getId() != null) {
                            custom_dose_id = custom_dose_idSB.append(measurement.get(i).getMeasurementValue().get(Position).getId() + ",").toString();
                        }
                        if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                            if (eventData.equalsIgnoreCase("add")) {
                                custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                            } else {
                                if (measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() + ",").toString();
                                } else if (measurement.get(i).getMeasurementValue().get(Position).getIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getIdAuto() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                }

                                if (measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() != null) {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append(measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                                }
                            }
                        }


                    }
                } catch (Exception e) {

                }

                edittext4.setTag(measurement.get(i).getMeasurementId());
            }

            if (i == 4 && measurement.get(4).getMeasurementName() != null) {
                llayoutAddset5.setVisibility(VISIBLE);
                lbl5.setText(measurement.get(i).getMeasurementName());
                try {
                    if (measurement.get(i).getMeasurementValue().size() > 0) {
                        if (typeofEvent == "updateSet") {
                            edittext5.setText(measurement.get(i).getMeasurementValue().get(Position).getMeasurementValue());
                        }
                        if (measurement.get(i).getMeasurementValue().get(Position).getId() != null) {
                            custom_dose_id = custom_dose_idSB.append(measurement.get(i).getMeasurementValue().get(Position).getId() + ",").toString();
                        }
                        if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                            if (eventData.equalsIgnoreCase("add")) {
                                custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                            } else {
                                if (measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getCustomDoseCoachIdAuto() + ",").toString();
                                } else if (measurement.get(i).getMeasurementValue().get(Position).getIdAuto() != null) {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append(measurement.get(i).getMeasurementValue().get(Position).getIdAuto() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto = custom_dose_coach_id_autoSB.append("0,").toString();
                                }

                                if (measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() != null) {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append(measurement.get(i).getMeasurementValue().get(Position).getAthleteCustomMeasurementValuesAutoId() + ",").toString();
                                } else {
                                    custom_dose_coach_id_auto_copy = custom_dose_coach_id_auto_copyBuilder.append("0,").toString();
                                }
                            }
                        }
                    }
                } catch (Exception v) {

                }

                edittext5.setTag(measurement.get(i).getMeasurementId());
            }
        }

        addsetSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean Allcheck = false;
                String MeasurementValue = "";
                StringBuilder sb = new StringBuilder();


                String MeasurementId = "";
                StringBuilder Measurement = new StringBuilder();


                try {
                    if (llayoutAddset1.getVisibility() == VISIBLE) {
                        Log.e(VolleyLog.TAG, "showDialog:1 " + edittext1.getText().toString());
                        if (edittext1.getText().toString().equalsIgnoreCase("")) {
                            Allcheck = false;
                            UtilityClass.showAlertDailog(context, "Please enter value of sets.");
                            return;
                        } else {
                            Allcheck = true;
                            MeasurementValue = sb.append(edittext1.getText().toString() + ",").toString();
                            MeasurementId = Measurement.append(edittext1.getTag().toString() + ",").toString();
                        }
                    }
                    if (llayoutAddset2.getVisibility() == VISIBLE) {
                        Log.e(VolleyLog.TAG, "showDialog:2 " + edittext2.getText().toString());
                        if (edittext2.getText().toString().equalsIgnoreCase("")) {
                            Allcheck = false;
                            UtilityClass.showAlertDailog(context, "Please enter value of sets.");
                            return;
                        } else {
                            Allcheck = true;
                            MeasurementValue = sb.append(edittext2.getText().toString() + ",").toString();
                            MeasurementId = Measurement.append(edittext2.getTag().toString() + ",").toString();
                        }
                    }
                    if (llayoutAddset3.getVisibility() == VISIBLE) {
                        Log.e(VolleyLog.TAG, "showDialog:3 " + edittext3.getText().toString());
                        if (edittext3.getText().toString().equalsIgnoreCase("")) {
                            Allcheck = false;
                            UtilityClass.showAlertDailog(context, "Please enter value of sets.");
                            return;
                        } else {
                            Allcheck = true;
                            MeasurementValue = sb.append(edittext3.getText().toString() + ",").toString();
                            MeasurementId = Measurement.append(edittext3.getTag().toString() + ",").toString();
                        }
                    }
                    if (llayoutAddset4.getVisibility() == VISIBLE) {
                        Log.e(VolleyLog.TAG, "showDialog:4 " + edittext4.getText().toString());
                        if (edittext4.getText().toString().equalsIgnoreCase("")) {
                            Allcheck = false;
                            UtilityClass.showAlertDailog(context, "Please enter value of sets.");
                            return;
                        } else {
                            Allcheck = true;
                            MeasurementValue = sb.append(edittext4.getText().toString() + ",").toString();
                            MeasurementId = Measurement.append(edittext4.getTag().toString() + ",").toString();
                        }
                    }
                    if (llayoutAddset5.getVisibility() == VISIBLE) {
                        Log.e(VolleyLog.TAG, "showDialog:5 " + edittext5.getText().toString());
                        if (edittext5.getText().toString().equalsIgnoreCase("")) {
                            Allcheck = false;
                            UtilityClass.showAlertDailog(context, "Please enter value of sets.");
                            return;
                        } else {
                            MeasurementValue = sb.append(edittext5.getText().toString() + ",").toString();
                            MeasurementId = Measurement.append(edittext5.getTag().toString() + ",").toString();
                            Allcheck = true;
                        }
                    }
                } catch (Exception xz) {
                    Log.e(TAG, "onClick: " + xz);
                }
                if (Allcheck) {
                    if (typeofEvent == "updateSet") {
                    } else {
                        custom_dose_id = "";
                    }
                    dialog.dismiss();

                    MeasurementId = removeComaString(MeasurementId);
                    MeasurementValue = removeComaString(MeasurementValue);
                    custom_dose_id = removeComaString(custom_dose_id);
                    custom_dose_coach_id_auto_copy = removeComaString(custom_dose_coach_id_auto_copy);
                    custom_dose_coach_id_auto = removeComaString(custom_dose_coach_id_auto);


                    hide();
                    if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                        if (custom_dose_id.equalsIgnoreCase("")) {
                            custom_dose_id = "0";
                        }
                        whichapicAlled = "AddAndUpdateExerciseSet";

                        webServices.AddAndUpdateExerciseSet(custom_dose_coach_id_auto, measurement.get(0).getTrainingExerciseId(), AthleteId, TrainingId, MeasurementId, MeasurementValue, custom_dose_coach_id_auto_copy, context, CoachAddExerciseScreen.this);
                    } else {
                        whichapicAlled = "addCustomDoseByCoach";
                        webServices.addCustomDoseByCoach(measurement.get(0).getTrainingExerciseId(), MeasurementId, MeasurementValue, custom_dose_id, context, CoachAddExerciseScreen.this);
                    }
                }
                custom_dose_id = "";
                custom_dose_coach_id_auto_copy = "";
                custom_dose_coach_id_auto = "";
            }
        });
    }

//

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
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private void MagicTouch() {
        View v = getCurrentFocus();
//        if (v instanceof EditText) {
//            Rect outRect = new Rect();
//            v.getGlobalVisibleRect(outRect);
        v.clearFocus();
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        }

    }

    private String removeComaString(String c) {
        if (c != null && c.length() > 0 && c.charAt(c.length() - 1) == ',') {
            c = c.substring(0, c.length() - 1);
            if (c.charAt(c.length() - 1) == ',') {
                c = c.substring(0, c.length() - 1);
            }
            if (c.charAt(c.length() - 1) == ',') {
                c = c.substring(0, c.length() - 1);
            }
        }
        return c;
    }

    public void addNote(Context context, int x, int y, String event, String eventData, String typeofEvent, List<Type> types, int position, View view) {


        AlertBoxView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_box_for_sets, null);
        BubbleLinearLayout bubbleView = AlertBoxView.findViewById(R.id.mainRlyofAddset);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);

        dialog.showArrowTo(view, BubbleStyle.ArrowDirection.Up);
        dialog.setCancelOnTouch(false);

        AlertBoxView.getLayoutParams().height = dpToPx(140);
        AlertBoxView.getLayoutParams().width = dpToPx(180);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        //popUp.showAtLocation(AlertBoxView, Gravity.NO_GRAVITY, x + 300, y + 50);
        TextView txt = AlertBoxView.findViewById(R.id.EventName);
        ImageView addsetSave = AlertBoxView.findViewById(R.id.addsetSave);
        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txt.setText(event);


        LinearLayout mainRlyofAddset = AlertBoxView.findViewById(R.id.mainRlyofAddset);


        //mainRlyofAddset.setBackgroundColor(Color.parseColor("#545454"));

        TextView txtData = AlertBoxView.findViewById(R.id.eventData);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txtData.setText(eventData);
        txtData.setVisibility(GONE);


        EditText addnotes = AlertBoxView.findViewById(R.id.addnotes);


        if (typeofEvent == "type") {
            addnotes.setText(types.get(position).getExerciseTypeNotesDetail());
        } else {
            addnotes.setText(types.get(typesPosition).getExercises().get(ExercisePositiion).getExerciseNotesDetail());
        }
        addsetSave.setOnClickListener(viewc -> {
            if (addnotes.getText().toString().equalsIgnoreCase("")) {
                UtilityClass.showAlertDailog(context, "Please enter note.");
                return;
            } else {
                String ExerciseTypeNotesId = "";
                String id = "";
                if (typeofEvent == "type") {
                    if (!types.get(typesPosition).getExerciseTypeNotesId().equalsIgnoreCase("")) {
                        ExerciseTypeNotesId = types.get(position).getExerciseTypeNotesId();
                    }

                    try {
                        id = types.get(typesPosition).getExercises().get(0).getId();
                        types.get(typesPosition).setExerciseTypeNotesDetail(addnotes.getText().toString());
                    } catch (Exception x1) {
                    }
                    hide();
                    webServices.addExerciseTypeNotes(id, addnotes.getText().toString(), getCurrentTimeUsingDate(), ExerciseTypeNotesId, context, CoachAddExerciseScreen.this);
                } else {
                    if (!types.get(typesPosition).getExercises().get(ExercisePositiion).getExerciseNotesId().equalsIgnoreCase("")) {
                        ExerciseTypeNotesId = types.get(position).getExerciseTypeNotesId();
                    }

                    try {
                        id = types.get(typesPosition).getExercises().get(ExercisePositiion).getId();
                        types.get(typesPosition).getExercises().get(ExercisePositiion).setExerciseNotesDetail(addnotes.getText().toString());
                    } catch (Exception x1) {
                    }
                    hide();
                    webServices.addExerciseNotes(id, addnotes.getText().toString(), getCurrentTimeUsingDate(), ExerciseTypeNotesId, context, CoachAddExerciseScreen.this);

                }
                dialog.dismiss();
                // dialog.dismiss();
            }
        });


//        WindowManager.LayoutParams wmlp = AlertBoxView.getWindow().getAttributes();
//        wmlp.gravity = Gravity.CENTER_VERTICAL | Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.CENTER;
//        wmlp.x = x - 150;
//        wmlp.y = y - 100;
//        //dialog.show();
    }

    public void CopyPaste(Context context, int x, int y, String event, String eventData, View rootView) {


        AlertBoxView = LayoutInflater.from(context).inflate(R.layout.black_menu_option, null);
        BubbleLinearLayout bubbleView = AlertBoxView.findViewById(R.id.copyPaste);
        //bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);

        dialog.showArrowTo(rootView, BubbleStyle.ArrowDirection.Down);
        dialog.setCancelOnTouch(false);

        LinearLayout copyPaste = AlertBoxView.findViewById(R.id.copyPaste);

        copyPaste.setVisibility(VISIBLE);
        LinearLayout Copy = AlertBoxView.findViewById(R.id.Copy);
        LinearLayout Paste = AlertBoxView.findViewById(R.id.Paste);

        if (CopyPhaseFrom == -1 || CopyWeekFrom == -1 || CopyDayFrom == -1) {
            Paste.setVisibility(GONE);
        }
        Copy.setOnClickListener(view -> {
            if (event.equalsIgnoreCase("Phase")) {
                CopyPhaseFrom = (Phase + 1);
                CopyWeekFrom = 0;
                CopyDayFrom = 0;
            } else {
                CopyPhaseFrom = (Phase + 1);
                CopyWeekFrom = (Week + 1);
                CopyDayFrom = 0;
            }
            if (event.equalsIgnoreCase("Day")) {
                CopyPhaseFrom = (Phase + 1);
                CopyWeekFrom = (Week + 1);
                CopyDayFrom = (Day + 1);
            }
            dialog.dismiss();
        });
        Paste.setOnClickListener(view -> {
            whichapicAlled = "copyPhaseWeekExercise";
            hide();
            if (event.equalsIgnoreCase("Phase")) {
                webServices.copyPhaseWeekExercise(TrainingId, CopyPhaseFrom + "", CopyWeekFrom + "", (Phase + 1) + "", "0", CopyDayFrom + "", "0", context, CoachAddExerciseScreen.this);
            } else if (event.equalsIgnoreCase("Day")) {
                webServices.copyPhaseWeekExercise(TrainingId, CopyPhaseFrom + "", CopyWeekFrom + "", (Phase + 1) + "", (Week + 1) + "", CopyDayFrom + "", (Day + 1) + "", context, CoachAddExerciseScreen.this);
            } else {
                webServices.copyPhaseWeekExercise(TrainingId, CopyPhaseFrom + "", CopyWeekFrom + "", (Phase + 1) + "", (Week + 1) + "", CopyDayFrom + "", "0", context, CoachAddExerciseScreen.this);
            }
            dialog.dismiss();
        });


    }

    @Override
    public void onPause() {
        ////////Log.e(VolleyLog.TAG, "onPause called");
        super.onPause();
        //..stopPosition = videoViewPlayer.getCurrentPosition(); //stopPosition is an int
        videoViewPlayer.pause();
    }

    @Override
    public void onBackPressed() {
        try {
            dialog.dismiss();
        } catch (Exception v) {

        }

        if (videoView.getVisibility() == VISIBLE) {
            videoViewPlayer.stopPlayback();
            videoViewPlayer.setMediaController(null);
            videoView.setVisibility(GONE);
        } else {
            super.onBackPressed();
//            exerciseTypeNameList = new ArrayList<>();
//            exerciseNameList = new ArrayList<>();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        }
    }

    private void removeFocus() {
        MagicTouch();
        //((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(rLayoutMain.getWindowToken(), 0);
//        InputMethodManager imm = (InputMethodManager)
//                getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (DaysWiseExerciseList.isExerciseDataSaved) {
            //////Toast.makeText(context, "Exercise Data saved by user.", Toast.LENGTH_LONG).show();
        }
        if (RefreshTrainingPhase) {
            whichapicAlled = "getTrainingProgramDetailById";
            hide();
            webServices.getTrainingProgramDetailById(TrainingId, context, CoachAddExerciseScreen.this);
            //UtilityClass.showWaitDialog(new Dialog(context),context);
            RefreshTrainingPhase = false;
        }
        DaysWiseExerciseList.isExerciseDataSaved = false;
        videoViewPlayer.start(); //Or use resume() if it doesn't work. I'm not sure
    }

    private void Trainingprogram_detailadd() {
        if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
            return;
        }
        int PhaseX = Phase + 1;
        int WeekX = Week + 1;
        int DayX = Day + 1;
        int indexofPillarX = indexofPillar + 1;
        whichapicAlled = "Trainingprogram_detailadd";
        hide();
        webServices.Trainingprogram_detailadd(TrainingId, PhaseX + "", WeekX + "", DayX + "", indexofPillarX + "", context, CoachAddExerciseScreen.this);
    }

    private void check() {
        if (Phase + 1 <= TotalPhase) {
            swipe_layoutPhase.setLockDrag(true);
        } else {
            if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                swipe_layoutPhase.setLockDrag(false);
            }
        }

        if (Week <= TotalWeek) {
            swipe_layoutWeek.setLockDrag(true);
        } else {
            if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                swipe_layoutWeek.setLockDrag(false);
            }
        }

        if (Day <= TotalDay) {
            swipe_layoutDay.setLockDrag(true);
        } else {
            if (!Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                swipe_layoutDay.setLockDrag(false);
            }
        }
    }

    public void playvideo(String videopath) {
        if (videopath.equalsIgnoreCase("")) {
            UtilityClass.showAlertDailog(context, "Unable to play video");
            onBackPressed();
            return;
        }
        Intent mIntent =
                ExoPlayerActivity.getStartIntent(this, urlEncoded(videopath));
        startActivity(mIntent);


        Log.e(VolleyLog.TAG, "playvideo: " + videopath);
//        if (mediaController == null) {
//            mediaController = new MediaController(context);
//            // Set the videoView that acts as the anchor for the MediaController.
//            mediaController.setAnchorView(videoViewPlayer);
//            // Set MediaController for VideoView
//            videoViewPlayer.setMediaController(mediaController);
//        }
//
//        try {
//            progressDialog = ProgressDialog.show(context, "",
//                    "Buffering video...", false);
//            progressDialog.setCancelable(false);
//            getWindow().setFormat(PixelFormat.TRANSLUCENT);
//
//            // mediaController = new MediaController(context);
//
//            Uri video = Uri.parse(UtilityClass.urlEncoded(videopath));
//            videoViewPlayer.setMediaController(mediaController);
//            videoViewPlayer.setVideoURI(video);
//            videoViewPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                public void onPrepared(MediaPlayer mp) {
//                    progressDialog.dismiss();
//                    videoViewPlayer.start();
//                    mp.setLooping(true);
//                }
//            });
//
//        } catch (Exception e) {
//            progressDialog.dismiss();
//            System.out.println("Video Play Error :" + e.getMessage());
//        }


    }

    private void setNoOfPhases() {
        if (noOfPhases <= 1) {
            textViewNoOfPhases.setText(noOfPhases + " Phase");
        } else {
            textViewNoOfPhases.setText(noOfPhases + " Phases");
        }
    }

    private int setWeek(String weeks, boolean isUp) {
        int week = 0;
        String[] splitWeekInfo = weeks.split(" ");
        week = Integer.parseInt(splitWeekInfo[0]);
        if (isUp) {
            week = week + 1;
        } else {
            if (week > 0) {
                week = week - 1;
            }
        }
        return week;
    }

    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            Log.d("result", result);
            parseFolderData(result);
        } else {
            UtilityClass.hide();
        }
    }

    private class type extends RecyclerView.ViewHolder {
        public LinearLayout wodMainlayout;
        TextView typeName, timeText, wod_description;
        ImageView TrainingInfo, infoType, plusicon;
        RecyclerView typeRecyclerView;
        RelativeLayout exercisescreen;
        LinearLayout tAdd;
        LinearLayout forFakemargin, wod_layout;
        EditText rx1, rx2, rx3;

        public type(@NonNull View itemView) {
            super(itemView);
            typeName = itemView.findViewById(R.id.typeName);
            rx1 = itemView.findViewById(R.id.rx1);
            wodMainlayout = itemView.findViewById(R.id.wodMainlayout);
            rx2 = itemView.findViewById(R.id.rx2);
            rx3 = itemView.findViewById(R.id.rx3);
            wod_description = itemView.findViewById(R.id.wod_description);
            wod_layout = itemView.findViewById(R.id.wod_layout);
            plusicon = itemView.findViewById(R.id.plusicon);
            forFakemargin = itemView.findViewById(R.id.forFakemargin);
            tAdd = itemView.findViewById(R.id.tAdd);
            infoType = itemView.findViewById(R.id.infoType);
            TrainingInfo = itemView.findViewById(R.id.info);
            timeText = itemView.findViewById(R.id.timeText);
            exercisescreen = itemView.findViewById(R.id.exercisescreen);
            typeRecyclerView = itemView.findViewById(R.id.typeRecyclerView);
            typeRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

            if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                plusicon.setVisibility(GONE);
                typeName.setPadding(15, 0, 0, 0);
            }
        }
    }

    public class CartListAdapter extends RecyclerView.Adapter<type> {
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();
        private Context context;

        public CartListAdapter(Context context) {
            this.context = context;
            binderHelper.setOpenOnlyOne(true);
        }

        @Override
        public type onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_training, parent, false);
            return new type(itemView);
        }

        @Override
        public void onBindViewHolder(type holder, int positionOFType) {
            holder.wod_layout.setVisibility(GONE);

            Type type = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                    .getPillar().get(indexofPillar)
                    .getTypes().get(positionOFType);
            holder.exercisescreen.setOnClickListener(view -> {
                if (Screen.equalsIgnoreCase("GetWhiteBoardDatum")) {
                    return;
                }
                Intent intent = new Intent(context, exercise_Activity.class);
                int PhaseX = Phase + 1;
                int WeekX = Week + 1;
                int DayX = Day + 1;
                int indexofPillarX = indexofPillar + 1;

                holder.forFakemargin.setVisibility(VISIBLE);

                Bundle bundle = new Bundle();
                //exerciseNameList = new ArrayList<>();
                bundle.putSerializable("list", exerciseNameList);

                startActivity(intent.putExtra("TrainingId", TrainingId)
                        .putExtra("SelectedExerciseTypeName", type.getId())
                        .putExtra("phase", PhaseX + "")
                        .putExtra("week", WeekX + "")
                        .putExtra("day", DayX + "")
                        .putExtra("wod", type.getWodType() + "")
                        .putExtra("wod_description", type.getWodDescription() + "")
                        .putExtra("pillar", indexofPillarX + "")
                        .putExtras(bundle)
                        .putExtra("type_group_id", type.getExerciseTypeGroupId()));

                Log.d(VolleyLog.TAG, "*************** exercise_Activity *************");
                overridePendingTransition(R.anim.exit, R.anim.enter);

            });
            try {

                if (type.getExercises().get(0).getWodType().equalsIgnoreCase("1")) {


                    holder.wod_layout.setVisibility(VISIBLE);
                    holder.wodMainlayout.setBackground(getResources().getDrawable(R.drawable.shap_round_corner));

                    holder.wod_description.setText(type.getWodDescription());


                    holder.rx1.setText(type.getExercises().get(0).getWodDoseDetails().get(0).getWodDoseValues());
                    holder.rx2.setText(type.getExercises().get(0).getWodDoseDetails().get(1).getWodDoseValues());
                    holder.rx3.setText(type.getExercises().get(0).getWodDoseDetails().get(2).getWodDoseValues());

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 15, 10, 0);
                    holder.wodMainlayout.setLayoutParams(layoutParams);

                    LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams1.setMargins(0, 5, 0, 15);
                    holder.typeRecyclerView.setLayoutParams(layoutParams1);


                }
                if (!type.getExerciseTimeDuration().equalsIgnoreCase("")) {
                    holder.timeText.setText(type.getExerciseTimeDuration());
                }
            } catch (Exception v) {

            }


            holder.infoType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int[] location = new int[2];
                    holder.infoType.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    typesPosition = positionOFType;
                    addNote(context, x, y, "Add Notes", "notes", "type", addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar()
                            .get(indexofPillar).getTypes(), positionOFType, view);

                }
            });

            holder.timeText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String timeof = holder.timeText.getText().toString();
                    View view = View.inflate(context, R.layout.time_dialog, null);
                    int TimeHour = 00;
                    int TimeMinute = 00;
                    int TimeSeconds = 00;

                    if (timeof != null && timeof.length() > 0) {
                        int i = timeof.indexOf(':');
                        TimeHour = Integer.parseInt(timeof.substring(0, i));
                        TimeMinute = Integer.parseInt(timeof.substring(i + 1, i + 3));
                        TimeSeconds = Integer.parseInt(timeof.substring(i + 4, i + 6));
                    }
                    final NumberPicker numberPickerHour = view.findViewById(R.id.numpicker_hours);
                    numberPickerHour.setMaxValue(23);
                    numberPickerHour.setValue(TimeHour);

                    final NumberPicker numberPickerMinutes = view.findViewById(R.id.numpicker_minutes);
                    numberPickerMinutes.setMaxValue(59);
                    numberPickerMinutes.setValue(TimeMinute);

                    final NumberPicker numberPickerSeconds = view.findViewById(R.id.numpicker_seconds);
                    numberPickerSeconds.setMaxValue(59);
                    numberPickerSeconds.setValue(TimeSeconds);

                    Button cancel = view.findViewById(R.id.cancel);
                    Button ok = view.findViewById(R.id.ok);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(view);
                    final AlertDialog alertDialog = builder.create();
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // holder.timeText.setText(numberPickerHour.getValue() + ":" + numberPickerMinutes.getValue() + ":" + numberPickerSeconds.getValue());
                            holder.timeText.setText(String.format("%1$02d:%2$02d:%3$02d", numberPickerHour.getValue(), numberPickerMinutes.getValue(), numberPickerSeconds.getValue()));

                            addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                    .getPillar().get(indexofPillar)
                                    .getTypes().get(positionOFType).setExerciseTimeDuration(holder.timeText.getText().toString());
                            webServices.UpdateTrainingExerciseTimeDuration(addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                    .getPillar().get(indexofPillar)
                                    .getTypes().get(positionOFType).getExercises().get(0).getTrainingProgramDetailId(), addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                    .getPillar().get(indexofPillar)
                                    .getTypes().get(positionOFType).getId(), holder.timeText.getText().toString(), context, CoachAddExerciseScreen.this);

                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            });
            binderHelper.setOpenOnlyOne(true);

            holder.typeName.setText(type.getWodType().equalsIgnoreCase("1") ? "WOD - " + type.getName() : type.getName());


            try {
                trainingSetAdapter = new TrainingSetAdapter(context, positionOFType, addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                        .getPillar().get(indexofPillar).getTypes(), holder.typeRecyclerView);

                holder.typeRecyclerView.setAdapter(trainingSetAdapter);
                //mLayoutManager = new LinearLayoutManager(context);
                //mRecyclerView.setLayoutManager(mLayoutManager);
                holder.typeRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } catch (Exception x) {

            }


        }

        @Override
        public int getItemCount() {
            Log.e(VolleyLog.TAG, "getItemCount: " + addTrainingPrograms.get(0).getPhase().size());
            int count = 0;
            try {
                count = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                        .getPillar().get(indexofPillar).getTypes().size();
            } catch (Exception c) {

            }
            return count;
        }
    }

    private class AdapterOfTrainingSetAdapter extends RecyclerView.ViewHolder {
        public TextView AddsetsText;
        TextView ExcerciseName, DoseNameAndchange, DoseNameAndchangeWod;
        RelativeLayout ExcersiseSubmitButtonX, ExcersiseSubmitButtonX1, Addsets, view_foregroundDay1;
        SwipeRevealLayout swipe_layoutrowReps, swipe_layoutS, ExcerciseNameLayout1;
        LinearLayout ExcerciseNameLayout, lLayoutofRecyclerview, view_foregroundDay, myLa, mylCheck, L11;
        ImageView hide, Unselected, imageViewVideoPlayIconX, infoSets, imageViewVideoPlayIcon;
        TextView set1, set2, set3, set4, set5, DeleteTrainingExercise, DeleteTrainingExerciseWod;
        TextView ExcerciseName1;

        ImageView hide1, imageViewVideoPlayIcon1, infoSets1;
        LinearLayout check;

        public AdapterOfTrainingSetAdapter(@NonNull View itemView) {
            super(itemView);
            ExcerciseName = itemView.findViewById(R.id.ExcerciseName);
            L11 = itemView.findViewById(R.id.L11);
            infoSets = itemView.findViewById(R.id.infoSets);
            AddsetsText = itemView.findViewById(R.id.AddsetsText);
            myLa = itemView.findViewById(R.id.myLa);
            Addsets = itemView.findViewById(R.id.Addsets);
            DoseNameAndchange = itemView.findViewById(R.id.DoseNameAndchange);
            DoseNameAndchangeWod = itemView.findViewById(R.id.DoseNameAndchangeWod);
            DeleteTrainingExerciseWod = itemView.findViewById(R.id.DeleteTrainingExerciseWod);
            imageViewVideoPlayIcon = itemView.findViewById(R.id.imageViewVideoPlayIcon);
            ExcersiseSubmitButtonX = itemView.findViewById(R.id.ExcersiseSubmitButtonX);
            swipe_layoutrowReps = itemView.findViewById(R.id.swipe_layoutrowReps);
            //ExcerciseDoseDetails = itemView.findViewById(R.id.ExcerciseDoseDetails);
            lLayoutofRecyclerview = itemView.findViewById(R.id.lLayoutofRecyclerview);
            ExcerciseNameLayout = itemView.findViewById(R.id.ExcerciseNameLayout);
            swipe_layoutS = itemView.findViewById(R.id.swipe_layoutS);
            hide = itemView.findViewById(R.id.hide);
            view_foregroundDay = itemView.findViewById(R.id.view_foregroundDay);
            view_foregroundDay1 = itemView.findViewById(R.id.view_foregroundDay1);
            set1 = itemView.findViewById(R.id.set1);
            set2 = itemView.findViewById(R.id.set2);
            set3 = itemView.findViewById(R.id.set3);
            set4 = itemView.findViewById(R.id.set4);
            set5 = itemView.findViewById(R.id.set5);
            check = itemView.findViewById(R.id.check);
            DeleteTrainingExercise = itemView.findViewById(R.id.DeleteTrainingExercise);
            /*Layout without swipe*/
            ExcerciseName1 = itemView.findViewById(R.id.ExcerciseName1);
            ExcerciseNameLayout1 = itemView.findViewById(R.id.ExcerciseNameLayout1);
            hide1 = itemView.findViewById(R.id.hide1);
            imageViewVideoPlayIcon1 = itemView.findViewById(R.id.imageViewVideoPlayIcon1);
            infoSets1 = itemView.findViewById(R.id.infoSets1);
        }
    }


    public class TrainingSetAdapter extends RecyclerView.Adapter<AdapterOfTrainingSetAdapter> {
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();
        private final ViewBinderHelper binderHelper1 = new ViewBinderHelper();
        int positionofType = 0;
        private Context context;
        //private List<Type> typeList;
        private ItemTouchHelper.Callback dragCallback = new ItemTouchHelper.Callback() {

            int dragFrom = -1;
            int dragTo = -1;

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(10, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(10);
                }
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

//
                if (dragFrom == -1) {
                    dragFrom = fromPosition;
                }
                dragTo = toPosition;

                recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
                if (fromPosition != toPosition) {
                    Collections.swap(addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                            .getTypes().get(positionofType).getExercises(), fromPosition, toPosition);
                }


                return false;
            }

            private void reallyMoved(int from, int to) {
                Log.e(VolleyLog.TAG, "reallyMoved: " + from + "  " + to);

                int toPos = to;
                int fromPos = from + 1;
                String training_exercise_id = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes().get(positionofType).getExercises().get(toPos).getId();
                String position = toPos + 1 + "";
                String training_program_detail_id = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes().get(positionofType).getExercises().get(toPos).getTrainingProgramDetailId() + "";

                String exercise_type_group_id = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar).getTypes().get(positionofType).getExercises().get(toPos).getExerciseTypeId() + "";

                String type_id = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar).getTypes().get(positionofType).getExercises().get(toPos).getTypeId() + "";
                WebServices webServices = new WebServices();
                webServices.exerciseTypeSequenceChange(training_exercise_id, position, training_program_detail_id, exercise_type_group_id, type_id,
                        addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar).getTypes().get(positionofType).getExercises().get(toPos).getExerciseName(), context, CoachAddExerciseScreen.this);


                // I guessed this was what you want...
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return false;
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);

                if (dragFrom != -1 && dragTo != -1 && dragFrom != dragTo) {
                    reallyMoved(dragFrom, dragTo);
                }

                dragFrom = dragTo = -1;


            }

        };

        public TrainingSetAdapter(Context context, int positionofExercise, List<Type> typeList, RecyclerView typeRecyclerView) {
            this.context = context;
            this.positionofType = positionofExercise;
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(dragCallback);

            itemTouchHelper.attachToRecyclerView(typeRecyclerView);
        }

        @Override
        public AdapterOfTrainingSetAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.training_name_video, parent, false);
            return new AdapterOfTrainingSetAdapter(itemView);
        }


        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBindViewHolder(AdapterOfTrainingSetAdapter holder, int positionOfExercise) {
            binderHelper.setOpenOnlyOne(true);
            binderHelper1.setOpenOnlyOne(true);


            Exercise exercise = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                    .getTypes().get(positionofType).getExercises().get(positionOfExercise);

            if (exercise.getWodType().equalsIgnoreCase("1")) {
//                holder.ExcerciseNameLayout1.setVisibility(VISIBLE);
//                holder.swipe_layoutS.setVisibility(GONE);
//                holder.hide1.setVisibility(GONE);
//                holder.DoseNameAndchangeWod.setVisibility(GONE);
                if (exercise.getWodType().equalsIgnoreCase("1") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("20") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("21") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("22")) {
                    holder.hide.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    holder.swipe_layoutS.setVisibility(GONE);
                    holder.hide.setVisibility(GONE);
                    holder.hide1.setVisibility(GONE);
                    holder.ExcerciseNameLayout1.setVisibility(VISIBLE);
                    holder.check.setBackground(getResources().getDrawable(R.drawable.top_rounded_bottom_sharp));
                }

                if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes().get(positionofType)
                        .getExercises().get(positionOfExercise).getMeasurement().get(0).getMeasurementValue().size() >= 1 && exercise.getWodType().equalsIgnoreCase("1")) {
                    holder.AddsetsText.setText("   ");
                }


            }
            holder.view_foregroundDay1.setBackgroundColor(colorList.get(Week));

            binderHelper.bind(holder.swipe_layoutS, positionOfExercise + " " + exercise.getId());
            binderHelper1.bind(holder.ExcerciseNameLayout1, positionOfExercise + " " + exercise.getId());
            //holder.ExcerciseDoseDetails.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            holder.imageViewVideoPlayIcon.setOnClickListener(view -> {
                String Videoname = exercise.getExerciseVideoLink();
                playvideo(Videoname);
            });


            holder.imageViewVideoPlayIcon1.setOnClickListener(view -> {
                String Videoname = exercise.getExerciseVideoLink();
                //videoView.setVisibility(VISIBLE);
                playvideo(Videoname);
            });


//            new Handler().postDelayed(() -> {
            holder.lLayoutofRecyclerview.setVisibility(VISIBLE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            holder.myLa.setLayoutParams(layoutParams);
//            }, 200);


            holder.DeleteTrainingExercise.setOnClickListener(view -> {
                holder.swipe_layoutS.close(true);
                holder.ExcerciseNameLayout1.close(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webServices.Trainingexercisedelete(exercise.getId(), context, CoachAddExerciseScreen.this);

                        Log.e(VolleyLog.TAG, "run: " + addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                .getPillar().get(indexofPillar).getTypes().get(positionofType).getExercises().get(positionOfExercise).getExerciseName());
                        addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                .getPillar().get(indexofPillar).getTypes().get(positionofType).getExercises().remove(positionOfExercise);

                        notifyItemRemoved(positionOfExercise);


                        if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                .getTypes().get(positionofType).getExercises().size() == 0) {
                            addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                    .getTypes().remove(positionofType);
                            notifyDataSetChanged();
                        }
                        mAdapter.notifyDataSetChanged();
                        //notifyItemRangeChanged(positionOfExercise, getItemCount());
                    }
                }, 100);


                ////Log.e(VolleyLog.TAG, "onBindViewHolderMNMNNN: "+typeList.get(positionofType).getExercises().size());
            });

            holder.DeleteTrainingExerciseWod.setOnClickListener(view -> {
                holder.swipe_layoutS.close(true);
                holder.ExcerciseNameLayout1.close(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webServices.Trainingexercisedelete(exercise.getId(), context, CoachAddExerciseScreen.this);

                        Log.e(VolleyLog.TAG, "run: " + addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                .getPillar().get(indexofPillar).getTypes().get(positionofType).getExercises().get(positionOfExercise).getExerciseName());
                        addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                .getPillar().get(indexofPillar).getTypes().get(positionofType).getExercises().remove(positionOfExercise);

                        notifyItemRemoved(positionOfExercise);


                        if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                .getTypes().get(positionofType).getExercises().size() == 0) {
                            addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                    .getTypes().remove(positionofType);
                            notifyDataSetChanged();
                        }
                        mAdapter.notifyDataSetChanged();
                        //notifyItemRangeChanged(positionOfExercise, getItemCount());
                    }
                }, 100);


                ////Log.e(VolleyLog.TAG, "onBindViewHolderMNMNNN: "+typeList.get(positionofType).getExercises().size());
            });


            holder.hide.setOnClickListener(view -> {
                if (holder.lLayoutofRecyclerview.getVisibility() == VISIBLE) {
                    holder.hide.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    holder.swipe_layoutS.setVisibility(GONE);
                    holder.ExcerciseNameLayout1.setVisibility(VISIBLE);
                    holder.check.setBackground(getResources().getDrawable(R.drawable.top_rounded_bottom_sharp));
                } else {
                    holder.hide.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    holder.lLayoutofRecyclerview.setVisibility(VISIBLE);

                    ViewGroup.LayoutParams params = holder.swipe_layoutS.getLayoutParams();
                    mAdapter.notifyItemChanged(positionOfExercise);
                    holder.check.setBackground(getResources().getDrawable(R.drawable.semi_radius));
                }
            });

            holder.L11.setOnClickListener(view -> {
                if (exercise.getWodType().equalsIgnoreCase("1") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("20") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("21") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("22")) {
                    return;
                }
                holder.swipe_layoutS.setVisibility(VISIBLE);
                holder.ExcerciseNameLayout1.setVisibility(GONE);
                holder.check.setBackground(getResources().getDrawable(R.drawable.semi_radius));
//                holder.swipe_layoutS.setVisibility(VISIBLE);
//                holder.ExcerciseNameLayout1.setVisibility(GONE);
//                holder.check.setBackground(getResources().getDrawable(R.drawable.semi_radius));

            });

            holder.ExcerciseNameLayout.setOnClickListener(view -> {
                if (exercise.getWodType().equalsIgnoreCase("1") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("20") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("21") && !exercise.getMeasurement().get(0).getMeasurementId().equalsIgnoreCase("22")) {
                    return;
                }
                if (holder.lLayoutofRecyclerview.getVisibility() == VISIBLE) {
                    holder.hide.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    holder.swipe_layoutS.setVisibility(GONE);
                    holder.ExcerciseNameLayout1.setVisibility(VISIBLE);
                    holder.check.setBackground(getResources().getDrawable(R.drawable.top_rounded_bottom_sharp));
                } else {
                    holder.hide.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    holder.lLayoutofRecyclerview.setVisibility(VISIBLE);
                    ViewGroup.LayoutParams params = holder.swipe_layoutS.getLayoutParams();
                    mAdapter.notifyItemChanged(positionOfExercise);
                    holder.check.setBackground(getResources().getDrawable(R.drawable.semi_radius));
                }
            });


            holder.hide1.setOnClickListener(view -> {
                holder.swipe_layoutS.setVisibility(VISIBLE);
                holder.ExcerciseNameLayout1.setVisibility(GONE);
                holder.check.setBackground(getResources().getDrawable(R.drawable.semi_radius));
            });

            holder.infoSets.setOnClickListener(view -> {
                int[] location = new int[2];
                holder.infoSets.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                addNote(context, x, y, "Add Notes", "notes", "exercise", addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes(), positionOfExercise, view);
                typesPosition = positionofType;
                ExercisePositiion = positionOfExercise;
            });


            holder.infoSets1.setOnClickListener(view -> {
                int[] location = new int[2];
                holder.infoSets1.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                addNote(context, x, y, "Add Notes", "notes", "exercise", addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes(), positionOfExercise, view);
                typesPosition = positionofType;
                ExercisePositiion = positionOfExercise;
            });


            holder.lLayoutofRecyclerview.setOrientation(LinearLayout.VERTICAL);
            holder.view_foregroundDay.setOrientation(LinearLayout.VERTICAL);

            if (exercise.getMeasurement().size() > 0) {
                holder.view_foregroundDay1.setVisibility(VISIBLE);
            }

            ArrayList<String> setList = new ArrayList<>();
            for (int i = 0; i < exercise.getMeasurement().size(); i++) {
                if (i == 0 && exercise.getMeasurement().get(i).getMeasurementName() != null) {
                    holder.set1.setVisibility(VISIBLE);
                    holder.set1.setText(exercise.getMeasurement().get(i).getMeasurementName());
                }
                if (i == 1 && exercise.getMeasurement().get(i).getMeasurementName() != null) {
                    holder.set2.setVisibility(VISIBLE);
                    holder.set2.setText(exercise.getMeasurement().get(i).getMeasurementName());
                }
                if (i == 2 && exercise.getMeasurement().get(i).getMeasurementName() != null) {
                    holder.set3.setVisibility(VISIBLE);
                    holder.set3.setText(exercise.getMeasurement().get(i).getMeasurementName());
                }
                if (i == 3 && exercise.getMeasurement().get(i).getMeasurementName() != null) {
                    holder.set4.setVisibility(VISIBLE);
                    holder.set4.setText(exercise.getMeasurement().get(i).getMeasurementName());
                }
                if (i == 4 && exercise.getMeasurement().get(i).getMeasurementName() != null) {
                    holder.set5.setVisibility(VISIBLE);
                    holder.set5.setText(exercise.getMeasurement().get(i).getMeasurementName());
                }
                setList.add(exercise.getMeasurement().get(i).getMeasurementId());
            }
            holder.DoseNameAndchange.setOnClickListener(view -> {
                holder.swipe_layoutS.close(true);
                holder.ExcerciseNameLayout1.close(true);
                Intent intent = new Intent(context, AbrActivity.class);
                intent.putExtra("training_exercise_id", exercise.getId());
                intent.putExtra("dose_id", exercise.getDoseId());
                intent.putExtra("week", (Week + 1) + "");
                intent.putExtra("day", (Day + 1) + "");
                intent.putExtra("SetList", setList);

                Log.d(VolleyLog.TAG, "*************** AbrActivity *************");
                startActivity(intent);
                overridePendingTransition(R.anim.exit, R.anim.enter);
            });
            holder.DoseNameAndchangeWod.setOnClickListener(view -> {
                holder.swipe_layoutS.close(true);
                holder.ExcerciseNameLayout1.close(true);
                Intent intent = new Intent(context, AbrActivity.class);
                intent.putExtra("training_exercise_id", exercise.getId());
                intent.putExtra("dose_id", exercise.getDoseId());
                intent.putExtra("week", (Week + 1) + "");
                intent.putExtra("day", (Day + 1) + "");
                intent.putExtra("SetList", setList);
                startActivity(intent);
                Log.d(VolleyLog.TAG, "*************** AbrActivity *************");
                overridePendingTransition(R.anim.exit, R.anim.enter);
            });


            try {
                ViewBinderHelper binderHelperX = new ViewBinderHelper();
                for (int x = 0; x < addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes().get(positionofType)
                        .getExercises().get(positionOfExercise).getMeasurement().get(0).getMeasurementValue().size(); x++) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    LinearLayout.LayoutParams lparamsX = new
                            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    linearLayout.setLayoutParams(lparamsX);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    binderHelperX.setOpenOnlyOne(true);

                    View childView = getLayoutInflater().inflate(R.layout.swipelayout, null);

                    TextView DeleteTrainingDose = childView.findViewById(R.id.DeleteTrainingDose);
                    int finalX = x;
                    DeleteTrainingDose.setOnClickListener(view -> {
                        String MeasurementId = "";
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < exercise.getMeasurement().size(); i++) {
                            MeasurementId = sb.append(addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                    .getTypes().get(positionofType)
                                    .getExercises().get(positionOfExercise).getMeasurement().get(i).getMeasurementValue().get(finalX).getId() + ",").toString();

                            addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                    .getTypes().get(positionofType)
                                    .getExercises().get(positionOfExercise).getMeasurement().get(i).getMeasurementValue().remove(finalX);
                        }
                        Log.e(VolleyLog.TAG, "onClick:X " + MeasurementId);
                        MeasurementId = MeasurementId.replace(",,", "");

                        webServices.deleteCustomDoseByCoach(MeasurementId, context, CoachAddExerciseScreen.this);
                        mAdapter.notifyItemChanged(positionofType);
                    });


                    binderHelperX.bind(childView.findViewById(R.id.swipe_layoutrowRepsX), x + "");

                    LinearLayout view_foregroundDay1 = childView.findViewById(R.id.view_foregroundDay1);


                    childView.setOnTouchListener(new View.OnTouchListener() {
                        private int CLICK_ACTION_THRESHOLD = 100;
                        private float startX;
                        private float startY;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    startX = event.getX();
                                    startY = event.getY();
                                    break;
                                case MotionEvent.ACTION_UP:
                                    float endX = event.getX();
                                    float endY = event.getY();
                                    if (isAClick(startX, endX, startY, endY)) {

                                        Log.e(VolleyLog.TAG, "onTouch: " + event.getY());
                                        showDialog(context, v, "Update Set", "update", "updateSet", exercise.getMeasurement(), finalX, addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                                .getTypes().get(positionofType)
                                                .getExercises().get(positionOfExercise).getWodType());
                                        typesPosition = positionofType;
                                        ExercisePositiion = positionOfExercise;
                                    }
                                    break;
                            }
                            v.getParent().requestDisallowInterceptTouchEvent(true); //specific to my project
                            return false; //specific to my project
                        }

                        private boolean isAClick(float startX, float endX, float startY, float endY) {
                            float differenceX = Math.abs(startX - endX);
                            float differenceY = Math.abs(startY - endY);
                            return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
                        }
                    });

                    TextView set1 = childView.findViewById(R.id.set1);
                    TextView set2 = childView.findViewById(R.id.set2);
                    TextView set3 = childView.findViewById(R.id.set3);
                    TextView set4 = childView.findViewById(R.id.set4);
                    TextView set5 = childView.findViewById(R.id.set5);


                    set1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                    set2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                    set3.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                    set4.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                    set5.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                    if (holder.set1.getVisibility() == VISIBLE) {
                        set1.setVisibility(VISIBLE);
                    }
                    if (holder.set2.getVisibility() == VISIBLE) {
                        set2.setVisibility(VISIBLE);
                    }
                    if (holder.set3.getVisibility() == VISIBLE) {
                        set3.setVisibility(VISIBLE);
                    }
                    if (holder.set4.getVisibility() == VISIBLE) {
                        set4.setVisibility(VISIBLE);
                    }
                    if (holder.set5.getVisibility() == VISIBLE) {
                        set5.setVisibility(VISIBLE);
                    }


                    try {
                        if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                .getTypes().get(positionofType)
                                .getExercises().get(positionOfExercise).getMeasurement().get(0).getMeasurementValue().get(x).getMeasurementValue() != null) {
                            set1.setVisibility(VISIBLE);
                            set1.setText(addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                    .getTypes().get(positionofType)
                                    .getExercises().get(positionOfExercise).getMeasurement().get(0).getMeasurementValue().get(x).getMeasurementValue());
                        }
                    } catch (Exception xa) {
                    }

                    try {
                        if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                .getTypes().get(positionofType)
                                .getExercises().get(positionOfExercise).getMeasurement().get(1).getMeasurementValue().get(x).getMeasurementValue() != null) {
                            set2.setVisibility(VISIBLE);
                            set2.setText(addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                    .getTypes().get(positionofType)
                                    .getExercises().get(positionOfExercise).getMeasurement().get(1).getMeasurementValue().get(x).getMeasurementValue());
                        }
                    } catch (Exception xa) {
                    }

                    try {
                        if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                .getTypes().get(positionofType)
                                .getExercises().get(positionOfExercise).getMeasurement().get(2).getMeasurementValue().get(x).getMeasurementValue() != null) {
                            set3.setVisibility(VISIBLE);
                            set3.setText(addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                    .getPillar().get(indexofPillar).getTypes().get(positionofType)
                                    .getExercises().get(positionOfExercise).getMeasurement().get(2).getMeasurementValue().get(x).getMeasurementValue());
                        }
                    } catch (Exception xa) {
                    }

                    try {
                        if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                .getTypes().get(positionofType)
                                .getExercises().get(positionOfExercise).getMeasurement().get(3).getMeasurementValue().get(x).getMeasurementValue() != null) {
                            set4.setVisibility(VISIBLE);
                            set4.setText(addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                    .getPillar().get(indexofPillar).getTypes().get(positionofType)
                                    .getExercises().get(positionOfExercise).getMeasurement().get(3).getMeasurementValue().get(x).getMeasurementValue());
                        }
                    } catch (Exception xa) {
                    }

                    try {
                        if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                                .getTypes().get(positionofType)
                                .getExercises().get(positionOfExercise).getMeasurement().get(4).getMeasurementValue().get(x).getMeasurementValue() != null) {
                            set5.setVisibility(VISIBLE);
                            set5.setText(addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day)
                                    .getPillar().get(indexofPillar).getTypes().get(positionofType)
                                    .getExercises().get(positionOfExercise).getMeasurement().get(4).getMeasurementValue().get(x).getMeasurementValue());
                        }
                    } catch (Exception xa) {
                    }


                    holder.ExcerciseName.setSelected(true);
                    holder.ExcerciseName1.setSelected(true);

                    linearLayout.addView(childView);


                    if (x % 2 == 1) {
                        linearLayout.setBackgroundColor(Color.parseColor("#e6e6e6"));
                    } else {
                        linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                    }


                    holder.view_foregroundDay.addView(linearLayout);
                }
            } catch (Exception v) {
            }

            if (!exercise.getAbr().equalsIgnoreCase("")) {
                holder.DoseNameAndchange.setText(exercise.getAbr());
                holder.DoseNameAndchangeWod.setText(exercise.getAbr());
            } else {
                holder.DoseNameAndchange.setText("Add Dose");
                holder.DoseNameAndchangeWod.setText("Add Dose");
            }


            holder.ExcerciseName.setText(exercise.getExerciseName());

            holder.ExcerciseName1.setText(exercise.getExerciseName());

            holder.Addsets.setOnClickListener(viewc -> {
                if (addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes().get(positionofType)
                        .getExercises().get(positionOfExercise).getMeasurement().get(0).getMeasurementValue().size() >= 1 && exercise.getWodType().equalsIgnoreCase("1")) {

                    return;
                }
                showDialog(context, viewc, "Add Set", "add", "addCustomDoseByCoach", exercise.getMeasurement(), positionOfExercise, addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes().get(positionofType)
                        .getExercises().get(positionOfExercise).getWodType());
                typesPosition = positionofType;
                ExercisePositiion = positionOfExercise;
            });


        }


        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = addTrainingPrograms.get(0).getPhase().get(Phase).getWeek().get(Week).getDay().get(Day).getPillar().get(indexofPillar)
                        .getTypes().get(positionofType).getExercises().size();
            } catch (Exception c) {

            }
            return count;
        }
    }

    private class PhaseSwipeList extends RecyclerView.ViewHolder {
        SwipeRevealLayout swipe_layoutMain;
        TextView copy, Add, Delete;
        TextView Count, TotalCount, textViewNameX;
        ImageView imageViewUpArrowFor;

        public PhaseSwipeList(@NonNull View itemView) {
            super(itemView);
            swipe_layoutMain = itemView.findViewById(R.id.swipe_layoutMain);
            copy = itemView.findViewById(R.id.copy);
            Add = itemView.findViewById(R.id.Add);
            Delete = itemView.findViewById(R.id.Delete);
            Count = itemView.findViewById(R.id.Count);
            TotalCount = itemView.findViewById(R.id.TotalCount);
            textViewNameX = itemView.findViewById(R.id.textViewNameX);
            imageViewUpArrowFor = itemView.findViewById(R.id.imageViewUpArrowFor);
        }
    }


//    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//        @Override
//        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//            final int fromPosition = viewHolder.getAdapterPosition();
//            final int toPosition = target.getAdapterPosition();
//            if (fromPosition < toPosition) {
//                for (int i = fromPosition; i < toPosition; i++) {
//                    Collections.swap(mAdapter.getCapitolos(), i, i + 1);
//                }
//            } else {
//                for (int i = fromPosition; i > toPosition; i--) {
//                    Collections.swap(mAdapter.getCapitolos(), i, i - 1);
//                }
//            }
//            mAdapter.notifyItemMoved(fromPosition, toPosition);
//            return true;
//        }
//
//        @Override
//        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//            HelpScreenData.CustomAdapter.MyViewHolder svH = (HelpScreenData.CustomAdapter.MyViewHolder) viewHolder;
//            int index = mAdapter.getCapitolos().indexOf(svH.currentItem);
//            mAdapter.getCapitolos().remove(svH.currentItem);
//            mAdapter.notifyItemRemoved(index);
//            if (emptyView != null) {
//                if (mAdapter.getCapitolos().size() > 0) {
//                    emptyView.setVisibility(TextView.GONE);
//                } else {
//                    emptyView.setVisibility(TextView.VISIBLE);
//                }
//            }
//        }
//
//        @Override
//        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//            super.clearView(recyclerView, viewHolder);
//            reorderData();
//        }
//    };
//
//    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
//    itemTouchHelper.attachToRecyclerView(recList);


}
