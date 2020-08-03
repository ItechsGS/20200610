package com.org.godspeed.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.RelativeSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.transition.AutoTransition;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleRelativeLayout;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.AthleteExerciseSetActivity;
import com.org.godspeed.allOtherClasses.BodySkeletonInfoScreen;
import com.org.godspeed.allOtherClasses.CoachAddExerciseScreen;
import com.org.godspeed.allOtherClasses.FileCaseWebViewScreen;
import com.org.godspeed.allOtherClasses.FuelScreenExpendibleList;
import com.org.godspeed.allOtherClasses.GraphActivity;
import com.org.godspeed.allOtherClasses.ProfileScreenActivity;
import com.org.godspeed.allOtherClasses.UserProfileScreen;
import com.org.godspeed.loginData.SelectedAthleteGoal;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.PillarExerciseStatus;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.TrainingProgramDetail;
import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
import com.org.godspeed.response_JsonS.get_athlete_health.GetAthleteHealth;
import com.org.godspeed.service.LinePagerIndicatorDecoration;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.DividerItemDecorator;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.HoloCircularProgressBar;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;
import com.samsung.android.sdk.healthdata.HealthDataStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
import static com.org.godspeed.allOtherClasses.LoginScreen.MEMBERSHIP_STATUS;
import static com.org.godspeed.allOtherClasses.LoginScreen.userTypeOf;
import static com.org.godspeed.fragments.CoachBoardFragments.AthleteData;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.service.SchoolDataService.goalArray;
import static com.org.godspeed.utility.GlobalClass.ConvertImperialToMetrics;
import static com.org.godspeed.utility.GlobalClass.heightValue;
import static com.org.godspeed.utility.GlobalClass.weightValue;
import static com.org.godspeed.utility.UtilityClass.DateFormatForServer;
import static com.org.godspeed.utility.UtilityClass.ONE_DAY;
import static com.org.godspeed.utility.UtilityClass.ONE_WEEK;
import static com.org.godspeed.utility.UtilityClass.getLastdateofyear;
import static com.org.godspeed.utility.UtilityClass.getMonthDateFirstdate;
import static com.org.godspeed.utility.UtilityClass.getMonthDateLastdate;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfToday;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfWeek;
import static com.org.godspeed.utility.UtilityClass.getfirstdateofyear;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES_ASSETS;

public class AthleteProfileFragment extends Fragment implements GodSpeedInterface {

    public static final int PICK_IMAGE = 1;
    private static final String IMAGE_DIRECTORY = "/GODSPEED";
    private static final int ACTION_REQUEST_GALLERY = 501;
    //Example exampl
    private static final int ACTION_REQUEST_CAMERA = 502;
    private static final int ACTION_REQUEST_CANCEL_LIST_ITEM_SELECTION = 503;
    public static String weightshow, heightshow;
    public static boolean isCalanderOptionNeedToShow = false;
    public static boolean show_samsung_dialog = true;
    public static boolean isAutoCloseScreenAfterTimerComplete = false;
    public static String CaloriesExercise, AvgHeartRate, MaxHeartRate = "0";
    public static boolean CallAPIOFTP = false;
    static Fragment AthleteProfileFragmentX;
    static AthleteProfileFragment SathleteProfileFragment;
    String SelectedGoal = "";
    //List<AthleteLevel> athlete_level;
    BubblePopupWindow dialog;
    View rootView;
    String showDialogOf = "";
    JSONArray activationData = null;
    Boolean coach = false;
    String SelectedLevel = "Select Level";
    WebServices webServices = new WebServices();
    //List<GoalArray> goalArray;
    LinearLayout rMainDialogLayout;
    RelativeLayout rAthleteLevel;
    TextView DayText, WeekText, YearText, MonthText;
    String Tag = "";
    DatePickerDialog picker;
    RelativeLayout rLayoutC;
    RelativeLayout rLayoutForCirclePiller;
    Context Scontext;
    LinearLayout LayoutDay, LayoutWeek, LayoutMonth, LayoutYear;
    String Calories = "0";
    String Distance = "0";
    String MaxHr = "0";
    String AvgHr = "0";
    String Steps = "";
    RecyclerView AthleteActivity;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    AthleteDataHealth AthleteDataHealth;
    int HeartBeatDLCount = 0;
    /*******SAMSUNG HEALTH**********/

    String[] NAmesOfAct = {"Warm Up", "Specialty", "Weights", "Conditioning", "Recovery", "Nutrition"};
    PillarLayout adapter;
    DecimalFormat twoDFormx = new DecimalFormat("#");
    int MValue = 0;
    int NValue = 0;
    SelectedAthleteDEtail selectedAthleteDEtail;
    String AthleteHealthDataMode = "1";
    JSONObject jsonObj2 = null;
    JSONArray array = new JSONArray();
    DecimalFormat twoDForm = new DecimalFormat("#.##");
    float weightOf = 0f;
    private RelativeLayout Activation, Skills, Energy, Build, Regen, rLayoutForFuel;
    private Animation zoomIn, zoomOut;
    private boolean isAnimationStarted;
    private Calendar cal;
    private Context context = null;
    private int GALLERY = 1, CAMERA = 2;
    private PackageManager packageManager;
    private String cameraDoesNotSupportMessage = "Camera is not available.";
    private String imagePath = "", folderName = "",
            profile_pic_name = "profile_pic.png";
    private byte[] byte_arr = null;
    private File directory = null;
    private ImageView backEventDialog, SaveEventDialog;
    private String whichAPICALLED;
    private Gson gson = new Gson();
    private ImageView imageViewBackArrow, AddimageAthlete;
    private String CoachID = "";
    private String AthleteID = "";
    private int position = ProfileScreenActivity.position;
    private ImageView imageViewProfilePic, imageViewClanderDrawerSliderIcon, imageViewAppIconForAnimation, imageViewAppIconForAnimationX, ImageviewCalender, fuellock, regenlock;
    private TextView textViewDate;
    private Animation zoom_in_2;
    private ImageView imageViewMoreIcon, imageViewLeftArrow, imageViewDoubleLeftArrow, imageViewRightArrow, imageViewDoubleRightArrow;
    private LinearLayout lLayoutforActionvationAndBuild;
    private RelativeLayout rLayoutForProfileImage, rLayoutForWorkoutcompelteInfo, rLAyoutAthleteProfileDetails, rLayoutForActivationHoloCircleContainer;
    private RelativeLayout rLayoutHeader, rLayoutForBottomView, rLayoutForFileCase, rLayoutForHelthProfile, rLayoutForUpdateInfo;
    private String nameOfMonth = "";
    private int daysInMonth = 0, currentDate = 0;
    private SimpleDateFormat dateFormate;
    private TextView textViewActivationText, textViewBuildText, textViewEnergyText, textViewRegenText, textViewSkillsText, textViewFuelText, textViewSessionText, textViewDownloadText;
    private TextView textViewActivationCirculValue, textViewVelocity, textViewSkillsCirculValue, textViewSkillsSleep, textViewEnergyCirculValue, textViewMaxHr, textViewBuildCirculValue, textViewBuild, textViewRegenCirculValue, textViewRegenSleep, textViewFuelCirculValue, textViewMaxHrFuel;
    private RecyclerView dialogBoxRecyclerView, dialogBoxRecyclerData, PillarRecycler;
    private View.OnClickListener ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (MEMBERSHIP_STATUS == 1) {
                UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                return;
            }
            switch (v.getId()) {
                case R.id.rLayoutForHelthProfile:
                    startActivity(new Intent(getActivity(), BodySkeletonInfoScreen.class));
                    ((Activity) context).overridePendingTransition(R.anim.exit, R.anim.enter);
                    Log.d(VolleyLog.TAG, "*************** BodySkeletonInfoScreen *************");

                    break;
                case R.id.rLayoutForFileCase:
                    startActivity(new Intent(getActivity(), FileCaseWebViewScreen.class));
                    ((Activity) context).overridePendingTransition(R.anim.exit, R.anim.enter);
                    Log.d(VolleyLog.TAG, "*************** FileCaseWebViewScreen *************");

                    break;

                case R.id.rLayoutForUpdateInfo:
                    startActivity(new Intent(getActivity(), UserProfileScreen.class));
                    ((Activity) context).overridePendingTransition(R.anim.exit, R.anim.enter);
                    Log.d(VolleyLog.TAG, "*************** UserProfileScreen *************");

                    break;
            }
        }
    };
    private TextView EvenText, textViewAthleteName, TextViewAtheleteLevel;
    private ViewPager viewPager, Athlete_profile_health_body;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private List<TrainingProgramDetail> TeamAssignProgram = new ArrayList<>();
    private HealthDataStore mStore;
    private List<GetAthleteHealth> getAthleteHealths = new ArrayList<>();
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    private LinearLayoutManager mLayoutManager;
    private String TodayDate = "";
    private float Activation_total_excercise = 0f;
    private float Skills_total_excercise = 0f;
    private float Energy_total_excercise = 0f;
    private float Build_total_excercise = 0f;
    private float Regen_total_excercise = 0f;
    private float Fuel_total_excercise = 0f;
    private float Activation_total_completed = 0f;
    private float Skills_total_completed = 0f;
    private float Energy_total_completed = 0f;
    private float Build_total_completed = 0f;
    private float Regen_total_completed = 0f;
    private float Fuel_total_completed = 0f;
    private float Activation_total_percent = 0f;
    private float Skills_total_percent = 0f;
    private float Energy_total_percent = 0f;
    private float Build_total_percent = 0f;
    private float Regen_total_percent = 0f;
    private RelativeLayout rLayoutForBottomViewMoreOptions, rLayoutForWorkoutAthlete;
    private TextView textViewFileCase, textViewUpdateInfo, textViewHelthProfile, TextViewMonth;
    //    public static void Athlete_profile_health_bodyFx() {
////        SathleteProfileFragment.getAthleteDataofHealth();
////        SathleteProfileFragment.Athlete_bodyAdapter.notifyDataSetChanged();
//    }
    private float Fuel_total_percent = 0f;
    private Athlete_bodyAdapter Athlete_bodyAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_profile, container, false);
        Scontext = getActivity().getApplicationContext();
        context = getActivity();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        int usertype = GlobalClass.ivar1;


        if (usertype == 1) {
            coach = true;
            try {
                MaxHr = AthleteData.get(ProfileScreenActivity.position).getAthleteHeartRate().get(0).getMaxHeartRate();
                AvgHr = AthleteData.get(ProfileScreenActivity.position).getAthleteHeartRate().get(0).getAvgHeartRate();
            } catch (Exception m) {

            }
            //rLayoutHeader.setVisibility(View.VISIBLE);
        }


        PillarRecycler = view.findViewById(R.id.PillarRecycler);

        AthleteActivity = view.findViewById(R.id.AthleteActivity);

        SathleteProfileFragment = this;

        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        AthleteActivity.setLayoutManager(mLayoutManager);

        AthleteDataHealth = new AthleteDataHealth();

        AthleteActivity.setAdapter(AthleteDataHealth);

        AthleteActivity.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = AthleteActivity.getChildCount();
                totalItemCount = AthleteActivity.getAdapter().getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached
                    // Do something
                    loading = true;
                }
            }
        });


        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(AthleteActivity);

        AthleteActivity.addItemDecoration(new LinePagerIndicatorDecoration());

        adapter = new PillarLayout(context);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        PillarRecycler.setLayoutManager(mLayoutManager);
        PillarRecycler.setAdapter(adapter);


        AthleteProfileFragmentX = new AthleteProfileFragment();


        dateFormate = new SimpleDateFormat("dd - MMM - yyyy");

        ImageView img = view.findViewById(R.id.frameofIcon);
        rLayoutForProfileImage = view.findViewById(R.id.rLayoutForProfileImage);

        rLayoutHeader = view.findViewById(R.id.rLayoutHeader);
        rLayoutC = view.findViewById(R.id.rLayoutC);
        rLayoutHeader.setVisibility(GONE);


        textViewDate = view.findViewById(R.id.textViewDate);
        textViewDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        LayoutDay = view.findViewById(R.id.LayoutDay);
        LayoutWeek = view.findViewById(R.id.LayoutWeek);
        LayoutMonth = view.findViewById(R.id.LayoutMonth);
        LayoutYear = view.findViewById(R.id.LayoutYear);

        DayText = view.findViewById(R.id.DayText);
        WeekText = view.findViewById(R.id.WeekText);
        MonthText = view.findViewById(R.id.MonthText);
        YearText = view.findViewById(R.id.YearText);


        LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));
        DayText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
        DayText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        LayoutDay.setOnClickListener(view1 -> {
            int[] location = new int[2];
            LayoutDay.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];

            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));


            DayText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            DayText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));
            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));

            long startTime = getStartTimeOfToday();
            long endTime = startTime + ONE_DAY;
//
//              Samhealth myAsyncTasks = new Samhealth(startTime,endTime,"Day",mStore);
//            myAsyncTasks.execute();

            AthleteHealthDataMode = "1";
            CallGetAthleteHealth("New");
        });
        LayoutWeek.setOnClickListener(view1 -> {
            int[] location = new int[2];
            LayoutWeek.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];

            Log.e(VolleyLog.TAG, "onClick: " + x + " " + y);
            //.getLocationOnScreen();


            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));

            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));

            WeekText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));

            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));

            long startTime = getStartTimeOfWeek();

            long endTime = startTime + ONE_WEEK;

            AthleteHealthDataMode = "2";
            CallGetAthleteHealth("old");
        });
        LayoutMonth.setOnClickListener(view1 -> {
            int[] location = new int[2];
            LayoutMonth.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];

            Log.e(VolleyLog.TAG, "onClick: " + x + " " + y);

            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));


            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));
            long startTime = getMonthDateFirstdate();

            long endTime = getMonthDateLastdate();
            //UtilityClass.getStartTimeOfToday();

            AthleteHealthDataMode = "3";
            CallGetAthleteHealth("old");
            //.getLocationOnScreen();
        });
        LayoutYear.setOnClickListener(view1 -> {
            int[] location = new int[2];
            LayoutYear.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];

            Log.e(VolleyLog.TAG, "onClick: " + x + " " + y);
            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_right_yellow));

            DayText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            WeekText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            MonthText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            YearText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));


            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));


            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));


            YearText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            long startTime = getfirstdateofyear();

            long endTime = getLastdateofyear();
            //UtilityClass.getStartTimeOfToday();

            AthleteHealthDataMode = "4";
            CallGetAthleteHealth("old");
        });

        viewPager = view.findViewById(R.id.viewPager);
        Athlete_profile_health_body = view.findViewById(R.id.Athlete_profile_health_body);

        if (!UtilityClass.getDeviceTypeMobile) {
            ViewGroup.MarginLayoutParams layoutParamviewPager = (ViewGroup.MarginLayoutParams) viewPager.getLayoutParams();
            layoutParamviewPager.setMargins(0, 20, 0, 0);
            textViewDate.setPadding(5, 5, 5, 5);
        }


        getAthleteDataofHealth();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Athlete_bodyAdapter = new Athlete_bodyAdapter(context);

                Athlete_profile_health_body.setAdapter(Athlete_bodyAdapter);

            }
        }, 200);


        //JSON OBEJCT
        cal = Calendar.getInstance();
        daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        nameOfMonth = month_date.format(cal.getTime());
        month_date = new SimpleDateFormat("DD");
        currentDate = Integer.parseInt(month_date.format(cal.getTime()));

        sliderDotspanel = view.findViewById(R.id.SliderDotsX);


        initializeTextViewFields(view);
        initCoachandAthlete(view);

        if (coach) {
            weightOf = AthleteData.get(position).getWeight().equalsIgnoreCase("") ? 0f : Float.parseFloat(AthleteData.get(position).getWeight());
        } else {
            weightOf = LoginJson.get(0).getWeight().equalsIgnoreCase("") ? 0f : Float.parseFloat(LoginJson.get(0).getWeight());
        }
        try {
            weightOf = Float.parseFloat(twoDForm.format(weightOf));
        } catch (Exception v) {

        }
        return view;

    }

    private void getAthleteDataofHealth() {
        String Height = "0";
        String Weight = "0";
        String SMM = "0";
        String BodyFat = "0";
        String Neck = "0";
        String Bicep = "0";
        String Chest = "0";
        String Waist = "0";
        String Hips = "0";
        String Thigh = "0";
        if (coach) {
            Weight = AthleteData.get(position).getWeight() + "";
            Height = AthleteData.get(position).getHeight() + "";
            SMM = AthleteData.get(position).getSmm();
            BodyFat = AthleteData.get(position).getBodyFat();
            Neck = AthleteData.get(position).getNeck();
            Bicep = AthleteData.get(position).getBicep();
            Chest = AthleteData.get(position).getChest();
            Waist = AthleteData.get(position).getWaist();
            Hips = AthleteData.get(position).getHips();
            Thigh = AthleteData.get(position).getThigh();
        } else {
            try {
                if (UtilityClass.getPreferences(context, "unit_type") != null) {
                    if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
                        weightValue = "KG";
                        heightValue = "CM";

                        Weight = ConvertImperialToMetrics(LoginJson.get(0).getWeight(), "weight");
                        Height = ConvertImperialToMetrics(LoginJson.get(0).getHeight(), "height");
                    } else {
                        weightValue = "LB";
                        heightValue = "IN";
                        Weight = LoginJson.get(0).getWeight();

                        Height = LoginJson.get(0).getHeight();
                    }
                }
                SMM = LoginJson.get(0).getSmm();
                BodyFat = LoginJson.get(0).getBodyFat();
                Neck = LoginJson.get(0).getNeck();
                Bicep = LoginJson.get(0).getBicep();
                Chest = LoginJson.get(0).getChest();
                Waist = LoginJson.get(0).getWaist();
                Hips = LoginJson.get(0).getHips();
                Thigh = LoginJson.get(0).getThigh();
            } catch (Exception v) {

            }


        }
        try {
            array = new JSONArray();
            array.put(new JSONObject().put("name", "Height").put("value", Height).put("unit", heightValue));
            array.put(new JSONObject().put("name", "Weight").put("value", Weight).put("unit", weightValue));
            array.put(new JSONObject().put("name", "SMM").put("value", SMM).put("unit", ""));
            array.put(new JSONObject().put("name", "Body Fat").put("value", BodyFat).put("unit", ""));
            array.put(new JSONObject().put("name", "Neck").put("value", Neck).put("unit", ""));
            array.put(new JSONObject().put("name", "Bicep").put("value", Bicep).put("unit", ""));
            array.put(new JSONObject().put("name", "Chest").put("value", Chest).put("unit", ""));
            array.put(new JSONObject().put("name", "Waist").put("value", Waist).put("unit", ""));
            array.put(new JSONObject().put("name", "Hips").put("value", Hips).put("unit", ""));
            array.put(new JSONObject().put("name", "Thigh").put("value", Thigh).put("unit", ""));

            double pages = (double) array.length() / (double) 4;
            dotscount = (int) Math.ceil(pages);
            dots = new ImageView[dotscount];

            sliderDotspanel.removeAllViews();
            for (int i = 0; i < dotscount; i++) {
                dots[i] = new ImageView(context);

                dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nonactive_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(13, 13);

                params.setMargins(8, 0, 8, 0);

                sliderDotspanel.addView(dots[i], params);
            }

            dots[0].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));

            Athlete_profile_health_body.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    try {
                        for (int i = 0; i < dotscount; i++) {
                            dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nonactive_dot));
                        }
                        dots[position].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));

                    } catch (Exception v) {

                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        } catch (Exception v) {

        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            SetTextOn();
        } else {
        }
    }

    private void showPictureDialog() {
        if (MEMBERSHIP_STATUS == 1) {
            UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
            return;
        }
        final custom_alert_class mAlert = new custom_alert_class(context);
        mAlert.setMessage("Select Action");
        mAlert.setPositveButton("Select photo from gallery", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhotoFromGallary();
                //adapter.notifyDataSetChanged();

                mAlert.dismiss();
            }
        });
        mAlert.setNegativeButton("Capture photo from camera", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhotoFromCamera();
                //adapter.notifyDataSetChanged();

                mAlert.dismiss();
            }
        });
        mAlert.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        byte_arr = null;
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }

        String path = "";
        Bitmap bitmap = null, bitmapForMask = null;
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), contentURI);

                    Log.e(VolleyLog.TAG, "onActivityResult " + bitmap);
                    Glide.with(context).load(
                            bitmap)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageViewProfilePic);                 //
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");

            Glide.with(context).load(
                    bitmap)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewProfilePic);
        }

        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    stream);
            Glide.with(context).load(
                    bitmap)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewProfilePic);
            // compress
            byte_arr = stream.toByteArray();
        }
        if (byte_arr != null) {
            whichAPICALLED = "uploadImage";
            WebServices webService = new WebServices();
            if (coach) {
                webService.uploadUserImage(AthleteData.get(position).getUserId(), byte_arr, context, AthleteProfileFragment.this);
            } else {
                webService.uploadUserImage(LoginJson.get(0).getUserId(), byte_arr, context, AthleteProfileFragment.this);
            }
        }
        if (bitmap != null) {
            Glide.with(context).load(
                    bitmap)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewProfilePic);

            saveImage(bitmap);
        }

    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(context,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public void showDialog(Context context, int x, int y, String event, String eventData, View view) {
        if (MEMBERSHIP_STATUS == 1) {
            UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
            return;
        }

        rootView = LayoutInflater.from(context).inflate(R.layout.cutom_dialogbox_athlete_screen, null);
        BubbleRelativeLayout bubbleView = rootView.findViewById(R.id.CardX);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(rootView, bubbleView);
        dialog.setCancelOnTouch(false);

//        window.setCancelOnTouch(true);
//        window.setCancelOnTouchOutside(true);
        // window.setCancelOnLater(3000);


        if (showDialogOf.equalsIgnoreCase("AthleteTraningProgram")) {
            dialog.showAtLocation(view, Gravity.CENTER, x, y);
        } else {
            dialog.showArrowTo(view, BubbleStyle.ArrowDirection.Up);
        }

//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.cutom_dialogbox_athlete_screen);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.getWindow().setDimAmount(0);
        imageViewAppIconForAnimation = rootView.findViewById(R.id.imageViewAppIconForAnimation);
        //rootView.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RelativeLayout mainRly = rootView.findViewById(R.id.mainRly);
        mainRly.invalidate();
        EvenText = rootView.findViewById(R.id.EventName);

        EvenText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        EvenText.setText("ASSIGNED TRAINING PROGRAMS(S)");

        rMainDialogLayout = rootView.findViewById(R.id.rMainDialogLayout);
        RelativeLayout RReventName;
        RReventName = rootView.findViewById(R.id.RReventName);


        dialogBoxRecyclerView = rootView.findViewById(R.id.dialogBoxRecyclerView);

        backEventDialog = rootView.findViewById(R.id.backEventDialog);
        SaveEventDialog = rootView.findViewById(R.id.SaveEventDialog);
        dialogBoxRecyclerData = rootView.findViewById(R.id.dialogBoxRecyclerData);

        dialogBoxRecyclerView.setHasFixedSize(true);
        dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider_dark_light));
        dialogBoxRecyclerView.addItemDecoration(dividerItemDecoration);
        if (!showDialogOf.equalsIgnoreCase("LEVEL")) {
            ViewGroup.LayoutParams params = mainRly.getLayoutParams();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            params.height = (int) ((310 * displayMetrics.density) + 0.5);
        }
        //CardView Card = rootView.findViewById(R.id.Card);
        if (showDialogOf.equalsIgnoreCase("GOAL")) {
            ViewGroup.LayoutParams params = rMainDialogLayout.getLayoutParams();
            ViewGroup.LayoutParams params1 = bubbleView.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params1.height = dpToPx(175);
            bubbleView.setLayoutParams(params1);
        }

        if (showDialogOf.equalsIgnoreCase("AthleteTraningProgram")) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rMainDialogLayout.setLayoutParams(params);
        }

        if (!UtilityClass.getDeviceTypeMobile && showDialogOf.equalsIgnoreCase("AthleteTraningProgram")) {
            rMainDialogLayout.getLayoutParams().width = dpToPx(350);
        }
        dialogBoxRecyclerView.setAdapter(new AthleteLevelRecycler(0, context));

        if (showDialogOf.equalsIgnoreCase("AthleteTraningProgram")) {
            // RReventName.getLayoutParams().height = dpToPx(350);
        }
        if (showDialogOf.equalsIgnoreCase("LEVEL")) {
            if (coach) {
                if (AthleteData.get(ProfileScreenActivity.position).getAthleteLevel().size() == 0 && showDialogOf.equalsIgnoreCase("LEVEL")) {
                    rMainDialogLayout.getLayoutParams().height = CoachAddExerciseScreen.dpToPx(100);
                }
            } else {
                if (LoginJson.get(0).getAthleteLevel().size() == 0 && showDialogOf.equalsIgnoreCase("LEVEL")) {
                    rMainDialogLayout.getLayoutParams().height = CoachAddExerciseScreen.dpToPx(300);
                }
            }
        }
    }

    public void showDialogofTraining(Context context, int x, int y, String event, String eventData, int X) {
        if (MEMBERSHIP_STATUS == 1) {
            UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
            return;
        }
        Dialog dialogX = new Dialog(context);
        dialogX.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogX.setContentView(R.layout.custom_dialog_box_for_training_dialog);
        dialogX.setCanceledOnTouchOutside(true);
        dialogX.getWindow().setDimAmount(0);
        dialogX.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        LinearLayout BeginTraining, Cancel, workoutSummary;
        TextView EventName, EventNameX, begin, workout, cancel;

        BeginTraining = dialogX.findViewById(R.id.BeginTraining);
        Cancel = dialogX.findViewById(R.id.Cancel);
        workoutSummary = dialogX.findViewById(R.id.workoutSummary);
        begin = dialogX.findViewById(R.id.begin);
        workout = dialogX.findViewById(R.id.workout);
        cancel = dialogX.findViewById(R.id.cancel);

        EventName = dialogX.findViewById(R.id.EventName);

        EventNameX = dialogX.findViewById(R.id.EventNameX);

        if (coach) {
            begin.setText("Show Training");
        }


        EventName.setTypeface(CustomTypeface.load_Montserrat_Bold_Fonts(context));
        EventNameX.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        begin.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        workout.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        cancel.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));


        BeginTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (coach) {
                final Bundle bundle = new Bundle();
                PillarExerciseStatus pillarExerciseStatus = null;
                String day = "-1";
                String week = "-1";
                String phase = "-1";
                try {
                    day = TeamAssignProgram.get(X).getPillarExerciseStatus().get(0).getDay();
                } catch (Exception v) {
                    day = "-1";
                }
                try {
                    week = TeamAssignProgram.get(X).getPillarExerciseStatus().get(0).getWeek();
                } catch (Exception v) {
                    week = "-1";
                }
                try {
                    phase = TeamAssignProgram.get(X).getPillarExerciseStatus().get(0).getPhase();
                } catch (Exception v) {
                    phase = "-1";
                }
                bundle.putString("ShowExcersiseOfID", TeamAssignProgram.get(X).getAssignProgramId());
                bundle.putString("dayTP", day);
                bundle.putString("weekTP", week);
                bundle.putString("phaseTP", phase);
                bundle.putString("screenname", Tag);
                bundle.putString("ScreenType", "BeginTraining");
                bundle.putString("date", TeamAssignProgram.get(X).getStartDate());
                bundle.putString("dayData", pillarExerciseStatus == null ? "" : new Gson().toJson(pillarExerciseStatus));
                startActivity(new Intent(getActivity(), AthleteExerciseSetActivity.class).putExtras(bundle));
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** AthleteExerciseSetActivity *************");
                dialogX.dismiss();
//                } else {
//                }
            }
        });

        workoutSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = new Bundle();
                PillarExerciseStatus pillarExerciseStatus = null;
                String day = "-1";
                String week = "-1";
                String phase = "-1";
                try {
                    day = TeamAssignProgram.get(X).getPillarExerciseStatus().get(0).getDay();
                } catch (Exception v) {
                    day = "-1";
                }
                try {
                    week = TeamAssignProgram.get(X).getPillarExerciseStatus().get(0).getWeek();
                } catch (Exception v) {
                    week = "-1";
                }
                try {
                    phase = TeamAssignProgram.get(X).getPillarExerciseStatus().get(0).getPhase();
                } catch (Exception v) {
                    phase = "-1";
                }
//                if (!coach) {
                bundle.putString("dayTP", day);
                bundle.putString("weekTP", week);
                bundle.putString("phaseTP", phase);
                bundle.putString("ShowExcersiseOfID", TeamAssignProgram.get(X).getAssignProgramId());
//                    bundle.putString("ShowExcersiseOfID", TeamAssignProgram.get(X).getAssignProgramId());
//                    bundle.putString("screenname", Tag);
//                    bundle.putString("date", TeamAssignProgram.get(X).getStartDate());
//                    bundle.putString("ScreenType", "workoutSummary");
//                    startActivity(new Intent(getActivity(), AthleteExerciseSetActivity.class).putExtras(bundle));
//                    getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
//                    dialog.dismiss();
//                } else {
                bundle.putString("ShowExcersiseOfID", TeamAssignProgram.get(X).getAssignProgramId());
                bundle.putString("screenname", Tag);
                bundle.putString("date", TeamAssignProgram.get(X).getStartDate());
                bundle.putString("ScreenType", "workoutSummary");

                startActivity(new Intent(getActivity(), AthleteExerciseSetActivity.class).putExtras(bundle));

                Log.d(VolleyLog.TAG, "*************** AthleteExerciseSetActivity *************");
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                dialogX.dismiss();
                // }
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogX.dismiss();
            }
        });


        if (!showDialogOf.equalsIgnoreCase("AthleteTraningProgram")) {

            WindowManager.LayoutParams wmlp = dialogX.getWindow().getAttributes();
            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
            wmlp.x = x;
            wmlp.y = y - 30;
        }

        dialogX.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isAutoCloseScreenAfterTimerComplete) {
            // this code will work only when timer will complete all piller workout.
            isAutoCloseScreenAfterTimerComplete = false;
            if (rLAyoutAthleteProfileDetails.getVisibility() == VISIBLE) {
                rLayoutForWorkoutcompelteInfo.setVisibility(VISIBLE);
                rLAyoutAthleteProfileDetails.setVisibility(GONE);
                isCalanderOptionNeedToShow = true;
                rLayoutForBottomView.setVisibility(GONE);
            }
        }
        if (CallAPIOFTP) {

        }
        try {
            new Handler().postDelayed(() -> {

                getAthleteDataofHealth();

                Athlete_bodyAdapter = new Athlete_bodyAdapter(context);

                Athlete_profile_health_body.setAdapter(Athlete_bodyAdapter);

            }, 100);

        } catch (Exception m) {
        }
        SetTextOn();
        isAutoCloseScreenAfterTimerComplete = false;
    }

    private void initCoachandAthlete(View view) {
        AddimageAthlete = view.findViewById(R.id.AddimageAthlete);

        imageViewProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                showPictureDialog();
            }
        });


        if (coach) {
            AthleteID = AthleteData.get(position).getUserId();
            CoachID = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
            try {
                SelectedLevel = AthleteData.get(position).getSelectedAthleteLevel().get(0).getAthleteLevel();
            } catch (Exception d) {
                Log.e(VolleyLog.TAG, "onCreateView: " + d);
            }
            try {
                SelectedGoal = AthleteData.get(position).getSelectedAthleteGoal().get(0).getGoalName();
            } catch (Exception d) {
                Log.e(VolleyLog.TAG, "onCreateView: " + d);
            }
            try {
                if (AthleteData.get(ProfileScreenActivity.position).getUserImage().equalsIgnoreCase("")) {
                    ImageView img = view.findViewById(R.id.frameofIcon);
                    img.setVisibility(GONE);
                    imageViewProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.logo_big));
                    img.setImageDrawable(getResources().getDrawable(R.drawable.logo_big));
                } else {
                    Glide.with(context).load(
                            WebServices.BASE_URL_FOR_IMAGES + AthleteData.get(position).getUserImage()).error(getResources().getDrawable(R.drawable.logo_f))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageViewProfilePic);
                }
            } catch (Exception xx) {
                if (GlobalClass.userImage.length() != 0) {
                    Glide.with(context).load(
                            WebServices.BASE_URL_FOR_IMAGES + GlobalClass.userImage).error(getResources().getDrawable(R.drawable.logo_f))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageViewProfilePic);
                } else {
                    Glide.with(context).load(
                            getResources().getDrawable(R.drawable.logo_f))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageViewProfilePic);
                }
            }
        } else {
            AthleteID = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
            CoachID = AthleteID;
            try {
                SelectedGoal = LoginJson.get(0).getSelectedAthleteGoal().get(0).getGoalName();
            } catch (Exception e) {
                Log.e(VolleyLog.TAG, "onCreateView: " + e);
            }
            try {
                SelectedLevel = LoginJson.get(0).getSelectedAthleteLevel().get(0).getAthleteLevel();
                TextViewAtheleteLevel.setText(SelectedLevel);
            } catch (Exception e) {
                Log.e(VolleyLog.TAG, "onCreateView: " + e);
                TextViewAtheleteLevel.setText("Select Level");
            }
            if (GlobalClass.userImage.length() != 0) {
                Glide.with(context).load(
                        WebServices.BASE_URL_FOR_IMAGES + GlobalClass.userImage).error(getResources().getDrawable(R.drawable.logo_f))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageViewProfilePic);
            } else {
                Glide.with(context).load(
                        getResources().getDrawable(R.drawable.logo_f))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageViewProfilePic);
            }
        }

    }

    private void initializeTextViewFields(View view) {
        TextViewAtheleteLevel = view.findViewById(R.id.TextViewatheleteLevel);

        imageViewProfilePic = view.findViewById(R.id.imageViewProfilePic);
        rLayoutForBottomView = view.findViewById(R.id.rLayoutForBottomView);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //rLayoutC.setVisibility(View.VISIBLE);
//                        Athlete_profile_health_body.getLayoutParams().height = rLayoutC.getHeight() - 50;
//
//                    }
//                }, 50);
//                //rLayoutC.setVisibility(View.VISIBLE);
//            }
//        }, 50);

        rAthleteLevel = view.findViewById(R.id.rAthleteLevel);
        if (!UtilityClass.getDeviceTypeMobile) {

            ViewGroup.MarginLayoutParams layoutParamsX = (ViewGroup.MarginLayoutParams) rLayoutForProfileImage.getLayoutParams();
            layoutParamsX.setMargins(20, 0, 20, 0);


            ViewGroup.MarginLayoutParams layoutParamrAthleteLevel = (ViewGroup.MarginLayoutParams) rAthleteLevel.getLayoutParams();
            layoutParamrAthleteLevel.setMargins(0, 0, 20, 0);
        }

        imageViewAppIconForAnimation = view.findViewById(R.id.imageViewAppIconForAnimation);
        imageViewAppIconForAnimationX = view.findViewById(R.id.imageViewAppIconForAnimation);

        rLAyoutAthleteProfileDetails = view.findViewById(R.id.rLAyoutAthleteProfileDetails);

        rLayoutForHelthProfile = view.findViewById(R.id.rLayoutForHelthProfile);
        rLayoutForHelthProfile.setOnClickListener(ClickListener);

        rLayoutForFileCase = view.findViewById(R.id.rLayoutForFileCase);
        rLayoutForFileCase.setOnClickListener(ClickListener);

        rLayoutForUpdateInfo = view.findViewById(R.id.rLayoutForUpdateInfo);
        rLayoutForUpdateInfo.setOnClickListener(ClickListener);

        imageViewClanderDrawerSliderIcon = view.findViewById(R.id.imageViewClanderDrawerSliderIcon);
        imageViewClanderDrawerSliderIcon.setVisibility(GONE);
        imageViewClanderDrawerSliderIcon.setOnClickListener(ClickListener);


        ImageviewCalender = view.findViewById(R.id.imageViewDoubleLeftArrow);

        textViewDate = view.findViewById(R.id.textViewDate);
        textViewDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        textViewDate.setText(dateFormate.format(cal.getTime()));
        whichAPICALLED = "getAssignProgram";
        try {
            if (coach) {
                webServices.getAssignProgram(AthleteData.get(position).getUserId(), DateFormatForServer.format(cal.getTime()), context, AthleteProfileFragment.this);
            } else {
                webServices.getAssignProgram(LoginJson.get(0).getUserId(), DateFormatForServer.format(cal.getTime()), context, AthleteProfileFragment.this);
            }
        } catch (Exception m) {
        }


        GlobalClass.Date = dateFormate.format(cal.getTime());
        ImageviewCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });


        TextViewAtheleteLevel.setText("Select Level");
        TextViewAtheleteLevel.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        rAthleteLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MEMBERSHIP_STATUS == 1 || LoginJson.get(0).getPaymentStatus().equalsIgnoreCase("1")) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                if (MEMBERSHIP_STATUS == 0 || coach) {
                    int[] location = new int[2];
                    TextViewAtheleteLevel.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    showDialogOf = "LEVEL";
                    if (userTypeOf.equalsIgnoreCase("4")) {
                        UtilityClass.showAlertDailog(context, "You do not have any assigned program for Today. Please contact to your Coach.");
                        return;
                    }
                    showDialog(context, x, y, "AP", "", view);
                }
            }
        });


        textViewSessionText = view.findViewById(R.id.textViewSessionText);
        textViewSessionText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        textViewDownloadText = view.findViewById(R.id.textViewDownloadText);
        textViewDownloadText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        textViewAthleteName = view.findViewById(R.id.textViewAthleteName);
        textViewAthleteName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        textViewFileCase = view.findViewById(R.id.textViewFileCase);
        textViewFileCase.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        textViewFileCase.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewUpdateInfo = view.findViewById(R.id.textViewUpdateInfo);
        textViewUpdateInfo.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        textViewUpdateInfo.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewHelthProfile = view.findViewById(R.id.textViewHelthProfile);
        textViewHelthProfile.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        SetTextOn();
    }

    private void setDate() {
        DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int day) {

                Date date1 = null;
                try {
                    date1 = new SimpleDateFormat("dd - MM - yyyy").parse(day + " - " + (month + 1) + " - " + year);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                android.text.format.DateFormat df = new android.text.format.DateFormat();

                textViewDate.setText(DateFormat.format("dd - MMM - yyyy", date1));
                GlobalClass.Date = textViewDate.getText().toString();
                //CheckTodayhealthData(date1);
                whichAPICALLED = "getAssignProgram";
                TeamAssignProgram = new ArrayList<>();
                if (coach) {
                    webServices.getAssignProgram(AthleteData.get(ProfileScreenActivity.position).getUserId(), DateFormatForServer.format(date1), context, AthleteProfileFragment.this);
                } else {
                    webServices.getAssignProgram(LoginJson.get(0).getUserId(), DateFormatForServer.format(date1), context, AthleteProfileFragment.this);
                }
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        StartTime.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isCalanderOptionNeedToShow = false;
    }

    public void SetTextOn() {
        if (coach) {
            textViewAthleteName.setText(UtilityClass.getNameAthlete(AthleteData.get(ProfileScreenActivity.position).getFirstName(), AthleteData.get(ProfileScreenActivity.position).getLastName(), AthleteData.get(ProfileScreenActivity.position).getEmailId()));
        } else {
            if (UtilityClass.getPreferences(context, "unit_type") != null) {
                if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
                    weightValue = "KG";
                    heightValue = "CM";
                    //heightV= textViewIn.getText().toString();
                } else {
                    weightValue = "LB";
                    heightValue = "IN";
                }
            }
            try {
                textViewAthleteName.setText(UtilityClass.getNameAthlete(LoginJson.get(0).getFirstName(), LoginJson.get(0).getLastName(), LoginJson.get(0).getEmailId()));
            } catch (Exception v) {

            }

        }


    }

    private void showAlertDialog(String tag, View view) {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = format1.parse("2013-02-21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(format2.format(date));
        if (TeamAssignProgram.size() == 0) {
            if (!coach) {
                UtilityClass.showAlertDailog(context, "You dont have assigned training program's.");
            } else {
                UtilityClass.showAlertDailog(context, "Athlete dont have assigned training program's.");
            }
        } else {
            showDialogOf = "AthleteTraningProgram";
            showDialog(context, 0, 0, "", "", view);
        }
    }

    private void CallGetAthleteHealth(String aNew) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd - MMM - yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format1.parse(textViewDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        whichAPICALLED = "GetAthleteHealth";
        if (coach) {
            webServices.GetAthleteHealth(AthleteHealthDataMode, AthleteData.get(position).getUserId(), format2.format(date), aNew, context, AthleteProfileFragment.this);
        } else {
            webServices.GetAthleteHealth(AthleteHealthDataMode, LoginJson.get(0).getUserId(), format2.format(date), aNew, context, AthleteProfileFragment.this);
        }
    }

    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, result);
        if (result != null && result.trim().length() > 0) {
            parseProgramAssignbyDeatilData(result);
        } else {
            UtilityClass.hide();
        }
    }


    private void parseProgramAssignbyDeatilData(String result) {
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj.getString("responseMessage");

            if (WebServices.responseCode == 200) {
                TextViewAtheleteLevel.setText(SelectedLevel);
                if (whichAPICALLED.equalsIgnoreCase("updateAthleteLevel")) {
                    hide();
                } else if (whichAPICALLED.equalsIgnoreCase("GOAL ARRAY")) {
                } else if (whichAPICALLED.equalsIgnoreCase("setGoal")) {
                    hide();
                } else if (whichAPICALLED.equalsIgnoreCase("GetAthleteHealth")) {
                    JSONArray jsonDataArray = jsonObj.getJSONArray("data");
                    getAthleteHealths = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), GetAthleteHealth[].class)));

                    AthleteDataHealth = new AthleteDataHealth();
                    AthleteActivity.setAdapter(null);
                    AthleteActivity.setAdapter(AthleteDataHealth);
                } else if (whichAPICALLED.equalsIgnoreCase("uploadImage")) {
                    String j = jsonObj.getString("data");
                    if (coach) {
                        AthleteData.get(position).setUserImage(j);
                    } else {
                        GlobalClass.userImage = j;
                    }
                } else if (whichAPICALLED.equalsIgnoreCase("getAssignProgram")) {

                    JSONArray jsonDataArray = jsonObj
                            .getJSONArray("data");
                    TeamAssignProgram = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), TrainingProgramDetail[].class)));
                    Activation_total_excercise = 0f;
                    Skills_total_excercise = 0f;
                    Energy_total_excercise = 0f;
                    Build_total_excercise = 0f;
                    Regen_total_excercise = 0f;
                    Fuel_total_excercise = 0f;

                    Activation_total_completed = 0f;
                    Skills_total_completed = 0f;
                    Energy_total_completed = 0f;

                    Build_total_completed = 0f;
                    Regen_total_completed = 0f;
                    Fuel_total_completed = 0f;


                    for (int i = 0; i < TeamAssignProgram.size(); i++) {
                        if (TeamAssignProgram.get(i).getPillarExerciseStatus() != null) {
                            for (int i1 = 0; i1 < TeamAssignProgram.get(i).getPillarExerciseStatus().size(); i1++) {
                                try {
                                    PillarExerciseStatus pillarExerciseStatus = TeamAssignProgram.get(i).getPillarExerciseStatus().get(i1);
                                    if (pillarExerciseStatus.getTotalExcercise() != null) {
                                        if (pillarExerciseStatus.getPillar().equalsIgnoreCase("1")) {
                                            Activation_total_excercise += Integer.parseInt(pillarExerciseStatus.getTotalExcercise());
                                            Activation_total_completed += Integer.parseInt(pillarExerciseStatus.getTotalExcerciseCompleted());
                                        } else if (pillarExerciseStatus.getPillar().equalsIgnoreCase("2")) {
                                            Skills_total_excercise += Integer.parseInt(pillarExerciseStatus.getTotalExcercise());
                                            Skills_total_completed += Integer.parseInt(pillarExerciseStatus.getTotalExcerciseCompleted());
                                        } else if (pillarExerciseStatus.getPillar().equalsIgnoreCase("3")) {
                                            Energy_total_excercise += Integer.parseInt(pillarExerciseStatus.getTotalExcercise());
                                            Energy_total_completed += Integer.parseInt(pillarExerciseStatus.getTotalExcerciseCompleted());
                                        } else if (pillarExerciseStatus.getPillar().equalsIgnoreCase("4")) {

                                            Build_total_excercise += Integer.parseInt(pillarExerciseStatus.getTotalExcercise());
                                            Build_total_completed += Integer.parseInt(pillarExerciseStatus.getTotalExcerciseCompleted());

                                        } else if (pillarExerciseStatus.getPillar().equalsIgnoreCase("5")) {

                                            Regen_total_excercise += Integer.parseInt(pillarExerciseStatus.getTotalExcercise());
                                            Regen_total_completed += Integer.parseInt(pillarExerciseStatus.getTotalExcerciseCompleted());

                                        } else if (pillarExerciseStatus.getPillar().equalsIgnoreCase("6")) {
                                            Fuel_total_excercise += Integer.parseInt(pillarExerciseStatus.getTotalExcercise());
                                            Fuel_total_completed += Integer.parseInt(pillarExerciseStatus.getTotalExcerciseCompleted());
                                        }
                                    }
                                } catch (Exception n) {

                                }
                            }
                        }
                    }

                    Activation_total_percent = getScorePercent(Activation_total_excercise, Activation_total_completed, "Activation_total_excercise");
                    Skills_total_percent = getScorePercent(Skills_total_excercise, Skills_total_completed, "Skills_total_percent");
                    Energy_total_percent = getScorePercent(Energy_total_excercise, Energy_total_completed, "Energy_total_percent");
                    Build_total_percent = getScorePercent(Build_total_excercise, Build_total_completed, "Build_total_excercise");
                    Regen_total_percent = getScorePercent(Regen_total_excercise, Regen_total_completed, "Regen_total_excercise");
                    Fuel_total_percent = getScorePercent(Fuel_total_excercise, Fuel_total_excercise, "Fuel_total_excercise");

                    hide();
                    if (HeartBeatDLCount == 0 && coach) {
                        String startDate = UtilityClass.getcurrentDateAndTime() + " 00:00:00";
                        String endDate = UtilityClass.getcurrentDateAndTime() + " 23:59:59";
                        HeartBeatDLCount = 10;
                    }
                    adapter.notifyDataSetChanged();
                    CallGetAthleteHealth("New");
                } else if (whichAPICALLED.equalsIgnoreCase("getHeartBeat")) {
                    Log.e(VolleyLog.TAG, "parseProgramAssignbyDeatilData: " + jsonObj);
                }
            } else {
                if (whichAPICALLED.equalsIgnoreCase("getAssignProgram")) {
                    CallGetAthleteHealth("New");
                    //UtilityClass.showAlertDailog(context,responseMessage);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error in json parsing", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(VolleyLog.TAG, "parseProgramAssignbyDeatilData: " + e);
        }
        hide();
    }


    public float getScorePercent(float questions, float correct, String m) {
        float v = (10f * correct / questions) / 10;
        v = v;

        if (questions == 0f && correct == 0f) {
            return 0f;
        }
        return v;
    }

    private void gotoscreenNext(int i) {
        if (MEMBERSHIP_STATUS == 1) {
            UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
            return;
        }
        showDialogofTraining(context, 0, 0, "", "", i);
    }

    public void slideDown(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }


    private void removeFocus() {
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        // imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
    }

    private void CallApiOfLevel(RecyclerViewHolder2 Holder, int Y, int i) {
        try {
            String ID = "";
            String row_no = "";
            WebServices webServices = new WebServices();
            whichAPICALLED = "updateAthleteDetailsDCRs";

            String AthleteID = "";

            float val = Float.parseFloat(Holder.AtheleteLevelExerciseValuesEditText.getText().toString());

            float ExerciseValues = 0;

            Holder.AtheleteLevelExerciseValuesEditText.setText(twoDFormx.format(val));
            if (coach) {

                AthleteID = AthleteData.get(position).getUserId();

                row_no = AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getRowNo();


                ID = AthleteData.get(position).getAthleteLevel().get(Y).getId();

            } else {

                AthleteID = LoginJson.get(0).getSelectedAthleteLevel().get(0).getAthleteId();

                row_no = LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).getRowNo();


                ID = LoginJson.get(0).getAthleteLevel().get(Y).getId();

            }


            ExerciseValues = val / weightOf; //(((weightOf) / Float.parseFloat(val + "")) * 100);
            ExerciseValues = Float.parseFloat(twoDForm.format(ExerciseValues));


            if (coach) {
                AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                //AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
            } else {
                LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                //LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
            }


            Holder.AtheleteExerciseValueMultiple.setText(ExerciseValues + "");


            webServices.updateMultiplierData(context, AthleteProfileFragment.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);
            dialogBoxRecyclerData.setAdapter(new AthleteLevelRecyclerData(Y, context));
        } catch (Exception m) {
        }
    }

    public class AthleteLevelRecycler extends RecyclerView.Adapter<AthleteProfileFragment.RecyclerViewHolder2> {
        int Y;
        Context context;

        public AthleteLevelRecycler(int Y, Context context) {
            this.Y = Y;
            this.context = context;
        }

        @Override
        public AthleteProfileFragment.RecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.three_item_layout, viewGroup, false);
            return new AthleteProfileFragment.RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final AthleteProfileFragment.RecyclerViewHolder2 Holder, final int i) {
            Holder.LevelText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            if (showDialogOf.equalsIgnoreCase("LEVEL")) {
                EvenText.setText("ATHLETE LEVEL");
                if (coach) {

                    Holder.LevelText.setText(AthleteData.get(ProfileScreenActivity.position).getAthleteLevel().get(i).getAthleteLevel());
                    if (SelectedLevel.equalsIgnoreCase(AthleteData.get(ProfileScreenActivity.position).getAthleteLevel().get(i).getAthleteLevel())) {
                        Holder.LevelImage.setColorFilter(Color.rgb(237, 187, 87));
                        Holder.LevelText.setTextColor(Color.rgb(237, 187, 87));
                    }
                    Glide.with(context).load(BASE_URL_FOR_IMAGES_ASSETS + AthleteData.get(position).getAthleteLevel().get(i).getAthleteLevelImage()).error(getResources().getDrawable(R.drawable.logo_f)).into(Holder.LevelImage);
                    Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            EvenText.setText(AthleteData.get(position).getAthleteLevel().get(i).getAthleteLevel());

                            CallDataView(i);
                        }
                    });
                } else {
                    Holder.LevelText.setText(LoginJson.get(0).getAthleteLevel().get(i).getAthleteLevel());
                    if (SelectedLevel.equalsIgnoreCase(LoginJson.get(0).getAthleteLevel().get(i).getAthleteLevel())) {
                        Holder.LevelImage.setColorFilter(Color.rgb(237, 187, 87));
                        Holder.LevelText.setTextColor(Color.rgb(237, 187, 87));
                    }
                    Glide.with(context).load(BASE_URL_FOR_IMAGES_ASSETS + LoginJson.get(0).getAthleteLevel().get(i).getAthleteLevelImage()).error(getResources().getDrawable(R.drawable.logo_f)).into(Holder.LevelImage);
                    Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            EvenText.setText(LoginJson.get(0).getAthleteLevel().get(i).getAthleteLevel());
                            CallDataView(i);
                        }
                    });
                }
            } else if (showDialogOf.equalsIgnoreCase("AthleteTraningProgram")) {
                Holder.rLayoutForAthleteTraining.setVisibility(VISIBLE);


                Holder.textViewExerciseName.setText(TeamAssignProgram.get(i).getProgramName());
                Holder.textViewExerciseDate.setText(TeamAssignProgram.get(i).getStartDate());

                Holder.textViewExerciseName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                Holder.textViewExerciseDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                Holder.textViewExerciseName.setTextColor(getResources().getColor(R.color.textColorYellow));
                Holder.textViewExerciseDate.setTextColor(getResources().getColor(R.color.textColorYellow));

                Holder.rLayoutForAthleteTraining.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        gotoscreenNext(i);
                    }
                });
            } else {
                EvenText.setText("SELECT GOAL");

                Holder.arrow1.setVisibility(GONE);
                Holder.LevelText.setText(goalArray.get(i).getGoalName());
                if (SelectedGoal.equalsIgnoreCase(goalArray.get(i).getGoalName())) {
                    Holder.LevelImage.setColorFilter(Color.rgb(237, 187, 87));
                    Holder.LevelText.setTextColor(Color.rgb(237, 187, 87));
                    Holder.rightSign.setVisibility(VISIBLE);
                } else {
                    Holder.rightSign.setVisibility(View.INVISIBLE);
                }
                Glide.with(context).load(BASE_URL_FOR_IMAGES_ASSETS + goalArray.get(i).getImage()).error(getResources().getDrawable(R.drawable.logo_f)).into(Holder.LevelImage);

                Log.e(VolleyLog.TAG, "onBindViewHolder: " + BASE_URL_FOR_IMAGES_ASSETS + goalArray.get(i).getImage());
                Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SelectedGoal = goalArray.get(i).getGoalName();

                        Holder.LevelImage.setColorFilter(Color.rgb(255, 255, 255));
                        Holder.LevelText.setTextColor(Color.rgb(255, 255, 255));

                        if (SelectedGoal == goalArray.get(i).getGoalName()) {
                            Holder.rightSign.setVisibility(VISIBLE);
                            Holder.LevelImage.setColorFilter(Color.rgb(237, 187, 87));
                            Holder.LevelText.setTextColor(Color.rgb(237, 187, 87));
                        }


                        if (coach) {
                            try {
                                //AthleteData = new ArrayList<SelectedAthleteDEtail>();
                                //SelectedAthleteGoal  x = new ArrayList<SelectedAthleteGoal>();
                                // List<SelectedAthleteGoal> x  = new ArrayList<SelectedAthleteGoal>();
                                //x = new ArrayList<SelectedAthleteGoal>();
                                //x.add(0,new SelectedAthleteGoal(goalArray.get(i).getId(),goalArray.get(i).getGoalName(),goalArray.get(i).getImage(),goalArray.get(i).getPosition(),AthleteID,CoachID,goalArray.get(i).getId()));
                                Log.e(VolleyLog.TAG, "onClick: " + AthleteData.get(position).getSelectedAthleteGoal().size());
                                //CoachBoardFragments.setAthleteGoal(goalArray, position, i, AthleteID, CoachID);
                                Log.e(VolleyLog.TAG, "onClick: " + AthleteData.get(position).getSelectedAthleteGoal().get(0).getGoalName());
                            } catch (Exception x) {
                                Log.e(VolleyLog.TAG, "onClick: " + x);
                            }
                        } else {
                            try {
                                //LoginJson.get(0).getSelectedAthleteGoal().get(0);
                                //LoginJson.get(0).getSelectedAthleteGoal() = new ArrayList<SelectedAthleteGoal>();
                                LoginJson.get(0).getSelectedAthleteGoal().add(0, new SelectedAthleteGoal(goalArray.get(i).getId(), goalArray.get(i).getGoalName()
                                        , goalArray.get(i).getImage(), goalArray.get(i).getPosition(), AthleteID, CoachID, goalArray.get(i).getId()));
                            } catch (Exception c) {
                                Log.e(VolleyLog.TAG, "onClick: " + c);
                            }

                        }
                        String selectedID = goalArray.get(i).getId();
                        whichAPICALLED = "setGoal";
                        //                        Selected = AthleteData.get(position).getSelectedAthleteLevel().get(0).getAthleteLevel();
                        // SelectedGoal = AthleteData.get(position).getSelectedAthleteGoal().a
                        Log.e(VolleyLog.TAG, "onClick: " + AthleteID + "   " + CoachID + "  " + selectedID);
                        webServices.setGoal(AthleteID, CoachID, selectedID, context, AthleteProfileFragment.this);
                        dialogBoxRecyclerView.setAdapter(new AthleteLevelRecycler(0, context));
                    }
                });
            }
        }

        private void CallDataView(int i) {
            AutoTransition autoTransition = new AutoTransition();

            autoTransition.setDuration(200);
            //TransitionManager.beginDelayedTransition(dialogBoxRecyclerData, autoTransition);
            dialogBoxRecyclerData.setVisibility(VISIBLE);
            dialogBoxRecyclerData.setHasFixedSize(true);
            dialogBoxRecyclerData.setLayoutManager(new LinearLayoutManager(context));
            DividerItemDecoration divider = new
                    DividerItemDecoration(context,
                    DividerItemDecoration.VERTICAL);
            divider.setDrawable(
                    ContextCompat.getDrawable(context, R.drawable.line_divider)
            );

            dialogBoxRecyclerData.addItemDecoration(divider);

            dialogBoxRecyclerData.setAdapter(new AthleteLevelRecyclerData(i, context));
        }

        @Override
        public int getItemCount() {
            int countofArray = 0;
            if (showDialogOf.equalsIgnoreCase("LEVEL")) {
                if (coach) {
                    countofArray = AthleteData.get(ProfileScreenActivity.position).getAthleteLevel().size();
                } else {
                    countofArray = LoginJson.get(0).getAthleteLevel().size();
                }
            } else if (showDialogOf.equalsIgnoreCase("AthleteTraningProgram")) {
                countofArray = TeamAssignProgram.size();
            } else {
                if (goalArray != null) {
                    countofArray = goalArray.size();
                }
            }
            return countofArray;
        }
    }

    /*********************************** DialogBox Data *************************************/

    public class AthleteLevelRecyclerData extends RecyclerView.Adapter<AthleteProfileFragment.RecyclerViewHolder2> {
        int Y;
        Context context;
        DecimalFormat twoDForm = new DecimalFormat("#.##");

        public AthleteLevelRecyclerData(int Y, Context context) {
            this.Y = Y;
            this.context = context;
        }

        @Override
        public AthleteProfileFragment.RecyclerViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.three_item_layout, viewGroup, false);
            return new AthleteProfileFragment.RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final AthleteProfileFragment.RecyclerViewHolder2 Holder, final int i) {
            Holder.LevelLayout.setVisibility(GONE);
            Holder.itemView.animate()
                    .translationYBy(0f)
                    .setDuration(500)
                    .setStartDelay(50)
                    .setListener(new AnimatorListenerAdapter() {

                        @Override
                        public void onAnimationStart(final Animator animation) {

                            Holder.rAthleteLevelExercise.setVisibility(VISIBLE);
                            backEventDialog.setVisibility(VISIBLE);
                            SaveEventDialog.setVisibility(VISIBLE);
                        }
                    })
                    .start();


            float ExerciseValues = 0;

            backEventDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EvenText.setText("ATHLETE LEVEL");
                    backEventDialog.setVisibility(GONE);
                    SaveEventDialog.setVisibility(GONE);
                    Holder.rAthleteLevelExercise.setVisibility(GONE);
                    dialogBoxRecyclerData.setVisibility(GONE);
                    dialogBoxRecyclerView.setAdapter(new AthleteLevelRecycler(0, context));

                    Holder.itemView.animate()
                            .translationYBy(0f)
                            .translationX(0f)
                            .setDuration(500)
                            .setStartDelay(50)
                            .setListener(new AnimatorListenerAdapter() {

                                @Override
                                public void onAnimationStart(final Animator animation) {
                                    dialogBoxRecyclerView.setVisibility(VISIBLE);
                                    Holder.LevelLayout.setVisibility(VISIBLE);
                                }
                            })
                            .start();
                }
            });

            SaveEventDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    whichAPICALLED = "updateAthleteLevel";
                    webServices = new WebServices();
                    String select_level = "";
                    if (coach) {
                        select_level = AthleteData.get(position).getAthleteLevel().get(Y).getId();
                        SelectedLevel = AthleteData.get(position).getAthleteLevel().get(Y).getAthleteLevel();
                        //CoachBoardFragments.setAthleteLevel(position, Y, AthleteID, CoachID);
                    } else {
                        select_level = LoginJson.get(0).getAthleteLevel().get(Y).getId();
                        SelectedLevel = LoginJson.get(0).getAthleteLevel().get(Y).getAthleteLevel();
                        LoginJson.get(0).getAthleteLevel().get(Y).setAthleteLevel(SelectedLevel);
                        LoginJson.get(0).getAthleteLevel().get(Y).setId(select_level);
                    }
                    webServices.updateAthleteLevel(AthleteID, CoachID, select_level, context, AthleteProfileFragment.this);
                    dialog.dismiss();
                }
            });

            if (coach) {


                ExerciseValues = (weightOf * Float.parseFloat(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getMultiple()));


                Holder.AtheleteLevelExerciseName.setText(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getPerantTypeName());
                //Holder.AtheleteLevelExerciseValues.setText(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getBaseValue());
                Holder.AtheleteExerciseValueMultiple.setText(twoDForm.format(Float.parseFloat(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getMultiple())));


            } else {

                ExerciseValues = (weightOf * Float.parseFloat(LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).getMultiple()));


                Holder.AtheleteLevelExerciseName.setText(LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).getPerantTypeName());

                //Holder.AtheleteExerciseValueMultiple.setText(twoDForm.format(((weightOf / Float.parseFloat(LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).getMultiple()))* 100)));
                Holder.AtheleteExerciseValueMultiple.setText(twoDForm.format(Float.parseFloat(LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).getMultiple())));
            }

            Holder.AtheleteLevelExerciseValuesEditText.setText(twoDFormx.format(ExerciseValues));


            Holder.AtheleteLevelExerciseValuesEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId,
                                              KeyEvent keyEvent) { //triggered when done editing (as clicked done on keyboard)
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        textView.clearFocus();
                        try {
                            String ID = "";
                            String row_no = "";
                            WebServices webServices = new WebServices();
                            whichAPICALLED = "updateAthleteDetailsDCRs";

                            String AthleteID = "";

                            float val = Float.parseFloat(Holder.AtheleteLevelExerciseValuesEditText.getText().toString());

                            float ExerciseValues = 0;


                            if (coach) {

                                AthleteID = AthleteData.get(position).getUserId();

                                row_no = AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getRowNo();


                                ID = AthleteData.get(position).getAthleteLevel().get(Y).getId();

                            } else {

                                AthleteID = LoginJson.get(0).getSelectedAthleteLevel().get(0).getAthleteId();

                                row_no = LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).getRowNo();


                                ID = LoginJson.get(0).getAthleteLevel().get(Y).getId();

                            }


                            ExerciseValues = val / weightOf; //(((weightOf) / Float.parseFloat(val + "")) * 100);
                            ExerciseValues = Float.parseFloat(twoDForm.format(ExerciseValues));

                            Holder.AtheleteLevelExerciseValuesEditText.setText(twoDFormx.format(val));

                            if (coach) {
                                AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                                //AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                            } else {
                                LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                                //LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                            }


                            Holder.AtheleteExerciseValueMultiple.setText(ExerciseValues + "");


                            webServices.updateMultiplierData(context, AthleteProfileFragment.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);
                            dialogBoxRecyclerData.setAdapter(new AthleteLevelRecyclerData(Y, context));
                        } catch (Exception m) {
                        }
                        notifyDataSetChanged();
                    }
                    return false;
                }
            });

            Holder.MultiplyValueDcr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String ID = "";
                        String row_no = "";
                        WebServices webServices = new WebServices();
                        whichAPICALLED = "updateAthleteDetailsDCRs";
                        DecimalFormat twoDForm = new DecimalFormat("#.##");
                        String AthleteID = "";

                        float val = Float.parseFloat(Holder.AtheleteLevelExerciseValuesEditText.getText().toString());
                        val = val - 5;
                        if (val <= 0) {
                            return;
                        }

                        float ExerciseValues = 0;


                        if (coach) {

                            AthleteID = AthleteData.get(position).getUserId();

                            row_no = AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getRowNo();


                            ID = AthleteData.get(position).getAthleteLevel().get(Y).getId();

                        } else {

                            AthleteID = LoginJson.get(0).getSelectedAthleteLevel().get(0).getAthleteId();

                            row_no = LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).getRowNo();


                            ID = LoginJson.get(0).getAthleteLevel().get(Y).getId();

                        }


                        ExerciseValues = val / weightOf; //(((weightOf) / Float.parseFloat(val + "")) * 100);
                        ExerciseValues = Float.parseFloat(twoDForm.format(ExerciseValues));

                        if (coach) {
                            AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                            //AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                        } else {
                            LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                            //LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                        }

                        Holder.AtheleteLevelExerciseValuesEditText.setText(twoDFormx.format(val));

                        Holder.AtheleteExerciseValueMultiple.setText(twoDForm.format(ExerciseValues));


                        webServices.updateMultiplierData(context, AthleteProfileFragment.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);
                    } catch (Exception m) {
                    }
                    notifyDataSetChanged();
                }
            });

            Holder.MultiplyValueInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String ID = "";
                        String row_no = "";
                        WebServices webServices = new WebServices();
                        whichAPICALLED = "updateAthleteDetailsINC";
                        String AthleteID = "";
                        float val = Float.parseFloat(Holder.AtheleteLevelExerciseValuesEditText.getText().toString());
                        val = val + 5;

                        Log.e(VolleyLog.TAG, "onClick: " +
                                Float.parseFloat(Holder.AtheleteLevelExerciseValuesEditText.getText().toString()) + " " + (val));

                        float ExerciseValues = 0;

                        if (coach) {

                            AthleteID = AthleteData.get(position).getUserId();

                            row_no = AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getRowNo();

                            ID = AthleteData.get(position).getAthleteLevel().get(Y).getId();

                        } else {

                            AthleteID = LoginJson.get(0).getSelectedAthleteLevel().get(0).getAthleteId();

                            row_no = LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).getRowNo();

                            ID = LoginJson.get(0).getAthleteLevel().get(Y).getId();

                        }

                        ExerciseValues = val / weightOf; //(((weightOf) / Float.parseFloat(val + "")) * 100);

                        ExerciseValues = Float.parseFloat(twoDForm.format(ExerciseValues));

                        Holder.AtheleteLevelExerciseValuesEditText.setText(twoDFormx.format(val));

                        if (coach) {
                            AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                            //AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                        } else {
                            LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                            //LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                        }

                        Holder.AtheleteExerciseValueMultiple.setText(ExerciseValues + "");

                        Log.e(VolleyLog.TAG, "onClick: " + weightOf);

                        webServices.updateMultiplierData(context, AthleteProfileFragment.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);
                    } catch (Exception m) {
                    }
                    notifyDataSetChanged();
                }
            });

        }

        @Override
        public int getItemCount() {
            int itemCount = 0;
            if (coach) {
                itemCount = AthleteData.get(ProfileScreenActivity.position).getAthleteLevel().get(Y).getValues().size();
            } else {
                itemCount = LoginJson.get(0).getAthleteLevel().get(Y).getValues().size();
            }
            return itemCount;
        }
    }

    public class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
        TextView LevelText, AtheleteLevelExerciseName, textViewExerciseName, textViewExerciseDate,
                AtheleteExerciseValueMultiple, MultiplyValueInc, MultiplyValueDcr;
        EditText AtheleteLevelExerciseValuesEditText;
        ImageView LevelImage, arrow1, rightSign;
        RelativeLayout LevelLayout, rAthleteLevelExercise, rLayoutForAthleteTraining, MainRLYLayoutLevel;

        public RecyclerViewHolder2(View itemView) {
            super(itemView);
            AtheleteLevelExerciseName = itemView.findViewById(R.id.AtheleteLevelExerciseName);
            AtheleteLevelExerciseValuesEditText = itemView.findViewById(R.id.AtheleteLevelExerciseValues);
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
            MainRLYLayoutLevel = itemView.findViewById(R.id.MainRLYLayoutLevel);
        }
    }

    /********************SELECT GOAL****************/


    public class PillarLayout extends RecyclerView.Adapter<PillarLayout.PillarViewHolder> {

        public PillarLayout(Context context) {

        }


        private void animate(final HoloCircularProgressBar progressBar, final Animator.AnimatorListener listener,
                             final float progress, final int duration) {
            ObjectAnimator mProgressBarAnimator;
            mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
            mProgressBarAnimator.setDuration(duration);

            mProgressBarAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationCancel(final Animator animation) {
                }

                @Override
                public void onAnimationEnd(final Animator animation) {
                    progressBar.setProgress(progress);
                }

                @Override
                public void onAnimationRepeat(final Animator animation) {
                }

                @Override
                public void onAnimationStart(final Animator animation) {
                }
            });
            if (listener != null) {
                mProgressBarAnimator.addListener(listener);
            }
            mProgressBarAnimator.reverse();
            mProgressBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(final ValueAnimator animation) {
                    progressBar.setProgress((Float) animation.getAnimatedValue());
                }
            });
            progressBar.setMarkerProgress(progress);
            mProgressBarAnimator.start();
        }

        @Override
        public PillarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.piller_layout, viewGroup, false);
            return new PillarViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PillarViewHolder holder, int position) {
            holder.lock.setVisibility(VISIBLE);
            holder.textViewText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            holder.textViewSleep.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            holder.textViewCirculValue.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            float TextOfPillars = 0f;

            Glide.with(context).load(BASE_URL_FOR_IMAGES_ASSETS + LoginJson.get(0).getPillarDetail().get(position).getPillarImage()).into(holder.imageViewIcon);
            holder.textViewText.setText(LoginJson.get(0).getPillarDetail().get(position).getPillerName());
            switch (Integer.parseInt(LoginJson.get(0).getPillarDetail().get(position).getPillerId())) {
                case 1: {

                    holder.textViewTextActType.setText("Warm Up");
                    animate(holder.holoCircul, null, Activation_total_percent, 1000);
                    holder.textViewCirculValue.setText(Activation_total_percent + "");
                    TextOfPillars = (Activation_total_percent * 100);


                }
                break;

                case 2: {

                    holder.textViewTextActType.setText("Specialty");
                    animate(holder.holoCircul, null, Skills_total_percent, 1000);
                    TextOfPillars = (Skills_total_percent * 100);
                }
                break;

                case 3: {

                    holder.textViewTextActType.setText("Conditioning");
                    animate(holder.holoCircul, null, Energy_total_percent, 1000);
                    TextOfPillars = (Energy_total_percent * 100);
                }
                break;

                case 4: {

                    holder.textViewTextActType.setText("Weights");
                    animate(holder.holoCircul, null, Build_total_percent, 1000);
                    TextOfPillars = (Build_total_percent * 100);
                }
                break;

                case 5: {

                    holder.textViewTextActType.setText("Recovery");
                    holder.lock.setVisibility(VISIBLE);
                    animate(holder.holoCircul, null, Regen_total_percent, 1000);
                    TextOfPillars = (Regen_total_percent * 100);
                    holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.changepassw));
                }
                break;

                case 6: {

                    holder.textViewTextActType.setText("Nutrition");
                    holder.lock.setVisibility(VISIBLE);
                    animate(holder.holoCircul, null, Fuel_total_percent, 1000);
                    TextOfPillars = (Fuel_total_percent * 100);

                }
                break;
            }


            if (TextOfPillars <= 0) {
                holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.circle_red));
            } else if (TextOfPillars > 0 && TextOfPillars < 99.50) {
                holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.circle_orange));
            } else if (TextOfPillars >= 99.50) {
                holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.circle_green));
            }

            if (position == 0 && Activation_total_excercise < 1) {
                holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.circle_blank));
            }
            if (position == 1 && Skills_total_excercise < 1) {
                holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.circle_blank));
            }
            if (position == 2 && Energy_total_excercise < 1) {
                holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.circle_blank));
            }
            if (position == 3 && Build_total_excercise < 1) {
                holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.circle_blank));
            }


            if (LoginJson.get(0).getPillarDetail().get(position).getStatus().equalsIgnoreCase("0")) {
                holder.lock.setImageDrawable(getResources().getDrawable(R.drawable.changepassw));
            }


            String sa = "";
            try {
                Log.e(VolleyLog.TAG, "onBindViewHolder: " + TextOfPillars);
                DecimalFormat df = new DecimalFormat("#.##");
                sa = df.format(TextOfPillars);
                if (sa.equalsIgnoreCase("0")) {
                    sa = "0.0";
                }
            } catch (Exception m) {
                Log.e(VolleyLog.TAG, "ERROR: " + m);
                sa = "0.0";
            }

            //TextOfPillars = sa;
            sa = sa + "%";
            SpannableString spannableString = new SpannableString(sa);
            ColorStateList redColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{0xffa10901});
            TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, redColor, null);

            spannableString.setSpan(new RelativeSizeSpan(1.2f), 0, sa.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new RelativeSizeSpan(0.7f), sa.length() - 1, sa.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.textViewCirculValue.setText(spannableString);


            if (!UtilityClass.getDeviceTypeMobile) {
                ViewGroup.MarginLayoutParams layoutParamrLayoutForTitle = (ViewGroup.MarginLayoutParams) holder.rLayoutForTitle.getLayoutParams();
                ViewGroup.MarginLayoutParams layoutParamrLayoutForHoloCirculer = (ViewGroup.MarginLayoutParams) holder.rLayoutForHoloCirculer.getLayoutParams();
                layoutParamrLayoutForHoloCirculer.setMargins(7, 8, 0, 8);
                layoutParamrLayoutForHoloCirculer.setMargins(30, 20, 20, 20);
                int m = (int) getResources().getDimension(R.dimen.grid_padding);
                holder.rLayoutForHoloCirculer.setPadding(m, m, m, m);
                holder.rLayoutForTitle.setPadding(m, m, m, m);
            }

            (holder.RlayoutPillar.getBackground()).setColorFilter(Color.parseColor("#343436"), PorterDuff.Mode.SRC_IN);
            holder.RlayoutPillar.setOnClickListener(view -> {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                if (LoginJson.get(0).getPillarDetail().get(position).getStatus().equalsIgnoreCase("0")) {
                    holder.lock.performClick();
                    return;
                }
                switch (Integer.parseInt(LoginJson.get(0).getPillarDetail().get(position).getPillerId())) {

                    case 1:
                        showAlertDialog("Activation", view);
                        Tag = "Activation";
                        break;
                    case 2:
                        showAlertDialog("Skills", view);
                        Tag = "Skills";
                        break;
                    case 3:
                        showAlertDialog("Energy", view);
                        Tag = "Energy";
                        break;
                    case 4:
                        showAlertDialog("Build", view);
                        Tag = "Build";
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), GraphActivity.class));
                        getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), FuelScreenExpendibleList.class));
                        getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                        break;
                }
            });


            holder.lock.setOnClickListener(view1 -> {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                if (LoginJson.get(0).getPillarDetail().get(position).getStatus().equalsIgnoreCase("0")) {
                    final custom_alert_class mAlert = new custom_alert_class(context);
                    mAlert.setMessage("Please contact admin to unlock this pillar.");
                    mAlert.OneButton(true);
                    mAlert.setPositveButton("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            if (position == 4) {
//                                startActivity(new Intent(getActivity(), GraphActivity.class));
//                                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
//                                Log.d(VolleyLog.TAG, "*************** GraphActivity *************");
//                            }
//                            if (position == 5) {
//                                startActivity(new Intent(getActivity(), FuelScreenExpendibleList.class));
//                                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
//                                Log.d(VolleyLog.TAG, "*************** FuelScreenExpendibleList *************");
//                            }
                            mAlert.dismiss();
                        }
                    });
                    mAlert.setNegativeButton("OK", view -> mAlert.dismiss());
                    mAlert.show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return LoginJson.get(0).getPillarDetail().size();
        }

        public class PillarViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout RlayoutPillar, rLayoutForHoloCirculer, rLayoutForTitle;
            ImageView imageViewIcon, lock;
            TextView textViewText, textViewSleep, textViewTextActType, textViewCirculValue;
            HoloCircularProgressBar holoCircul;

            public PillarViewHolder(@NonNull View itemView) {
                super(itemView);
                RlayoutPillar = itemView.findViewById(R.id.RlayoutPillar);
                textViewCirculValue = itemView.findViewById(R.id.textViewCirculValue);
                rLayoutForHoloCirculer = itemView.findViewById(R.id.rLayoutForHoloCirculer);
                rLayoutForTitle = itemView.findViewById(R.id.rLayoutForTitle);
                imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
                lock = itemView.findViewById(R.id.lock);
                textViewText = itemView.findViewById(R.id.textViewText);
                textViewSleep = itemView.findViewById(R.id.textViewSleep);
                textViewTextActType = itemView.findViewById(R.id.textViewTextActType);
                holoCircul = itemView.findViewById(R.id.holoCircul);
            }
        }
    }

    private class AthleteDataHealth extends RecyclerView.Adapter<AthleteDataHealth.AthleteDataHealthHolder> {

        @Override
        public AthleteDataHealthHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.list_items_exercise, viewGroup, false);
            return new AthleteDataHealthHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AthleteDataHealthHolder view, int position) {

            int POSX = position * 2;


            view.name.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            view.name1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            view.textViewValue.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            view.textViewValue2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            view.textViewIn.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            view.textViewIn2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            try {
                Glide.with(context).load(
                        BASE_URL_FOR_IMAGES_ASSETS + getAthleteHealths.get(POSX).getHealthTypeImage()).error(getResources().getDrawable(R.drawable.logo_main))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(view.SliderImage1);
                view.name.setText(getAthleteHealths.get(POSX).getName());
                view.textViewIn.setText(getAthleteHealths.get(POSX).getUnitName());
                view.textViewValue.setText(getAthleteHealths.get(POSX).getValue());
                try {
                    view.progressBar.invalidate();
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        view.progressBar.setProgress((int) (Float.parseFloat(getAthleteHealths.get(POSX).getValue())), true);
                    }
                    if (Float.parseFloat(getAthleteHealths.get(POSX).getValue()) > 600) {
                        view.progressBar.setMax((int) (Float.parseFloat(getAthleteHealths.get(POSX).getValue()) + 100));
                    } else {
                        view.progressBar.setMax(600);
                    }
                } catch (Exception v) {
                    view.progressBar.setMax(600);
                }
                view.L1.setVisibility(VISIBLE);
            } catch (Exception v) {
            }
            try {
                Glide.with(context).load(
                        BASE_URL_FOR_IMAGES_ASSETS + getAthleteHealths.get(POSX + 1).getHealthTypeImage()).error(getResources().getDrawable(R.drawable.logo_main))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(view.SliderImage2);
                view.name1.setText(getAthleteHealths.get(POSX + 1).getName());
                view.textViewIn2.setText(getAthleteHealths.get(POSX + 1).getUnitName());
                view.textViewValue2.setText(getAthleteHealths.get(POSX + 1).getValue());
                try {
                    view.progressBar2.invalidate();
                    String m = view.textViewValue2.getText().toString();
                    m = m.replace(".", "");
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        view.progressBar2.setProgress(Integer.parseInt(m), true);
                    }
                    if (Integer.parseInt(m) > 500) {
                        view.progressBar2.setMax(Integer.parseInt(m) + 100);
                    } else {
                        view.progressBar2.setMax(500);
                    }
                } catch (Exception v) {
                    view.progressBar2.setMax(500);
                }
                view.L2.setVisibility(VISIBLE);
            } catch (Exception v) {
            }

        }


        @Override
        public int getItemCount() {
            double pages = (double) getAthleteHealths.size() / (double) 2;
            return (int) Math.ceil(pages);
        }

        public class AthleteDataHealthHolder extends RecyclerView.ViewHolder {
            ImageView SliderImage1, SliderImage2;
            ProgressBar progressBar, progressBar2;
            TextView name, name1, textViewValue, textViewValue2, textViewIn, textViewIn2;
            LinearLayout L1, L2;

            public AthleteDataHealthHolder(@NonNull View view) {
                super(view);
                SliderImage1 = view.findViewById(R.id.SliderImage1);

                SliderImage2 = view.findViewById(R.id.SliderImage2);

                name = view.findViewById(R.id.name);

                name1 = view.findViewById(R.id.name1);

                textViewValue = view.findViewById(R.id.textViewValue);

                textViewValue2 = view.findViewById(R.id.textViewValue2);

                textViewIn = view.findViewById(R.id.textViewIn);

                textViewIn2 = view.findViewById(R.id.textViewIn2);

                progressBar = view.findViewById(R.id.progressBar);

                progressBar2 = view.findViewById(R.id.progressBar2);

                L2 = view.findViewById(R.id.L2);

                L1 = view.findViewById(R.id.L1);

            }
        }
    }

    private class Athlete_bodyAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;

        public Athlete_bodyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public boolean isViewFromObject(final View view, final Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(final View container, final int position, final Object object) {
            ((ViewPager) container).removeView((View) object);
        }


        @Override
        public int getCount() {
            double pages = (double) array.length() / (double) 4;
            return 3;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {


            int POSX = 0;
            POSX = position * 4;
            TextView textView1Value, textView2Value, textView2, textView2Text, textViewHeightValue, textView1, textView1Text, textViewKgText, textViewIn, textViewHeightText, textViewKg, textViewKGValue;
            LinearLayout lLayoutAthName, lLayoutAthName2;
            ImageView imgViewKg;
            RelativeLayout rLayoutForKG;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.athlete_health_profile_detail, null);

            textView1Value = view.findViewById(R.id.textView1Value);
            textViewHeightValue = view.findViewById(R.id.textViewHeightValue);
            textView1 = view.findViewById(R.id.textView1);
            textView1Text = view.findViewById(R.id.textView1Text);
            textViewIn = view.findViewById(R.id.textViewIn);
            textViewHeightText = view.findViewById(R.id.textViewHeightText);
            textViewKGValue = view.findViewById(R.id.textViewKGValue);
            textViewKg = view.findViewById(R.id.textViewKg);
            textViewKgText = view.findViewById(R.id.textViewKgText);
            imgViewKg = view.findViewById(R.id.imgViewKg);
            rLayoutForKG = view.findViewById(R.id.rLayoutForKG);


            textView2 = view.findViewById(R.id.textView2);
            textView2Value = view.findViewById(R.id.textView2Value);
            textView2Text = view.findViewById(R.id.textView2Text);

            textView1Value.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textView1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textView2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textView1Text.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textView2Value.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textView2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textView2Text.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewHeightValue.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewIn.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewHeightText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewKg.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewKgText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewKGValue.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewKg.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            lLayoutAthName = view.findViewById(R.id.lLayoutAthName);

            lLayoutAthName2 = view.findViewById(R.id.lLayoutAthName2);


            textViewKGValue.setTextColor(Color.parseColor("#ffffff"));
            textViewKg.setTextColor(Color.parseColor("#ffffff"));

            imgViewKg.setVisibility(GONE);
            if (position == 2) {
                lLayoutAthName.setVisibility(GONE);
                lLayoutAthName2.setVisibility(GONE);
            }


            textViewKGValue.setTextColor(Color.parseColor("#ffffff"));
            textViewKg.setTextColor(Color.parseColor("#ffffff"));
            try {

                textViewHeightText.setText(array.getJSONObject(POSX).getString("name"));

                float HeightValue = 0;
                try {
                    HeightValue = Float.parseFloat(array.getJSONObject(POSX).getString("value"));
                } catch (Exception m) {
                    Log.e(VolleyLog.TAG, "instantiateItem: " + m);
                }
                textViewHeightValue.setText(twoDForm.format(HeightValue));
                textViewIn.setText(array.getJSONObject(POSX).getString("unit"));
                textViewKgText.setText(array.getJSONObject(POSX + 1).getString("name"));


                float KGValue = 0;
                try {
                    KGValue = Float.parseFloat(array.getJSONObject(POSX + 1).getString("value"));
                } catch (Exception m) {

                }
                textViewKGValue.setText(twoDForm.format(KGValue));
                textViewKg.setText(array.getJSONObject(POSX + 1).getString("unit"));


                lLayoutAthName.setVisibility(VISIBLE);


                textView1Text.setText(array.getJSONObject(POSX + 2).getString("name"));

                float Value = 0;
                try {
                    Value = Float.parseFloat(array.getJSONObject(POSX + 2).getString("value"));
                } catch (Exception m) {

                }
                textView1Value.setText(twoDForm.format(Value));
                textView1.setText(array.getJSONObject(POSX + 2).getString("unit"));


                textView2Text.setText(array.getJSONObject(POSX + 3).getString("name"));
                float Value2 = 0;
                try {
                    Value2 = Float.parseFloat(array.getJSONObject(POSX + 3).getString("value"));
                } catch (Exception m) {

                }
                textView2Value.setText(twoDForm.format(Value2));
                textView2Text.setText(array.getJSONObject(POSX + 3).getString("unit"));


                lLayoutAthName2.setVisibility(VISIBLE);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            if (position == 0) {
                imgViewKg.setVisibility(VISIBLE);
                rLayoutForKG.setOnClickListener(view1 -> {
                    int[] location = new int[2];
                    view1.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    showDialogOf = "GOAL";
                    showDialog(context, x, y, "SELECT GOAL", "", view1);
                });
                //textViewKGValue.setTextColor(Color.parseColor("#00ff00"));
                //textViewKg.setTextColor(Color.parseColor("#00ff00"));
            }


            if (textView1Value.getText().toString().equalsIgnoreCase("")) {
                textView1Value.setText("-");
            }

            if (textView2Value.getText().toString().equalsIgnoreCase("")) {
                textView2Value.setText("-");
            }
            if (textViewKGValue.getText().toString().equalsIgnoreCase("")) {
                textViewKGValue.setText("-");
            }
            if (textViewHeightValue.getText().toString().equalsIgnoreCase("")) {
                textViewHeightValue.setText("-");
            }


            //ViewPager vp = (ViewPager) view;
            container.addView(view, 0);


            return view;

        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);

        }

    }
}