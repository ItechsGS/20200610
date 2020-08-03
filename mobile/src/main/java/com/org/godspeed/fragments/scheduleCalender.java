package com.org.godspeed.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.style.ForegroundColorSpan;
import android.transition.AutoTransition;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleRelativeLayout;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.VerticalViewPager;
import com.org.godspeed.ViewHolderClasses.CircleOvelLongViewHolder;
import com.org.godspeed.allOtherClasses.AddTeamCoachAthleteScreen;
import com.org.godspeed.allOtherClasses.CoachAddExerciseScreen;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.allOtherClasses.VideoViewActivity;
import com.org.godspeed.calender.CustmMultipleDotSpan;
import com.org.godspeed.calender.calender_event;
import com.org.godspeed.calender.calender_helper_classes.OneDayDecorator;
import com.org.godspeed.response_JsonS.GetSchedules.GetSchedules;
import com.org.godspeed.response_JsonS.getVideoClass.GetVideoClas;
import com.org.godspeed.response_JsonS.getVideoClass.LiveClassCheckIn;
import com.org.godspeed.response_JsonS.getVideoClass.LiveClassExerciseVideo;
import com.org.godspeed.response_JsonS.getVideoClassCategory.GetVideoClassCategory;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.DividerItemDecorator;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.videoClassAdapter.AthleteCheckinRecycler;
import com.org.godspeed.zoom_video.initsdk.InitAuthSDKCallback;
import com.org.godspeed.zoom_video.initsdk.InitAuthSDKHelper;
import com.org.godspeed.zoom_video.startjoinmeeting.UserLoginCallback;
import com.org.godspeed.zoom_video.startjoinmeeting.emailloginuser.EmailUserLoginHelper;
import com.org.godspeed.zoom_video.ui.LoginUserStartJoinMeetingActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.OnWeekChangedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import us.zoom.sdk.ZoomApiError;
import us.zoom.sdk.ZoomAuthenticationError;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKAuthenticationListener;
import us.zoom.sdk.ZoomSDKInitializeListener;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.CloseDrawer;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LayoutForFolder;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LockDrawer;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.SearchAthlete;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.fm;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewSliderBackIcon;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewSliderDrawerToggleIcon;
import static com.org.godspeed.allOtherClasses.LoginScreen.MEMBERSHIP_STATUS;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.utility.GlobalClass.PillarName;
import static com.org.godspeed.utility.UtilityClass.getMonthDateFirstdate;
import static com.org.godspeed.utility.UtilityClass.getMonthDateFirstdateFromGivenDate;
import static com.org.godspeed.utility.UtilityClass.getMonthDateLastdate;
import static com.org.godspeed.utility.UtilityClass.getMonthDateLastdateFromGivenDate;
import static com.org.godspeed.utility.UtilityClass.getPreferences;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class scheduleCalender extends Fragment implements GodSpeedInterface, OnDateSelectedListener, OnMonthChangedListener,
        OnWeekChangedListener, UserLoginCallback.ZoomDemoAuthenticationListener,
        ZoomSDKInitializeListener,
        InitAuthSDKCallback,
        ZoomSDKAuthenticationListener {

    public static Boolean RefreshUI = false;
    public static String SelectedCategory = "";
    static Calendar selectedDateForRefresh;
    static boolean keepcurrentSelectedDate = false;
    static VerticalViewPager mViewPager;
    static String SelectedDateSchedule = "";
    static int id = -1;
    static Context contextXX;
    //static ViewPager mViewPager;
    static Activity activityXX;
    static scheduleCalender activityscheduleCalender;
    static Boolean VideoClass = false;
    static int viewpagerPosition1, EventPosition2, MainItemPosition3 = 0;
    static boolean ref = false;
    static String LiveClassExerciseVideo_ID = "";
    static String ClassVideoDate = "";
    static String CurrentDateSchedule = "";
    private final int CREATE_EVENT_REQUEST_CODE = 100;
    RecyclerView video_s;
    RecyclerView VideoList;
    View view;
    List<LiveClassExerciseVideo> classExerciseVideoList = new ArrayList<>();
    ProgressDialog pDialog;
    ImageView imageViewBackArrow;
    TextView textViewScreenName;
    PlayerView videoFullScreenPlayerXX;
    SimpleExoPlayer playerXX;
    ProgressBar spinnerVideoDetailsXX;
    int PositionOFVideoList;
    String videoUri;
    int usertype = GlobalClass.ivar1;
    RecyclerView schoolFilterList;
    LinearLayout linearLayout;
    TextView monthYearText;
    Context context;
    Boolean week = false;
    FragmentManager fragmentManager;
    WebServices webServices = new WebServices();
    BubblePopupWindow dialog;
    ZoomSDK mZoomSDK;
    EditText mEdtUserName;
    EditText mEdtPassord;
    View mProgressPanel;
    ProgressBar progressBar;
    TextView txtConnecting;
    LinearLayout layoutviewSavePassword;
    RelativeLayout mBtnLogin, MainZoomLayout, RJoinMeeting, RLivenow;
    Boolean Live = false;
    LinearLayout ChooeseLayout;
    String CurrentMonthStartDate = "";
    String CurrentMonthEndDate = "";
    RecyclerView video_category;
    VideoCategoryAdapter videoCategoryAdapter;
    scheduleCalender scheduleCalender;
    private boolean firstTime = true;
    private String[] mShortMonths;
    private CalendarDialog mCalendarDialog;
    private List<calender_event> mEventList = new ArrayList<>();
    private MaterialCalendarView calendarView;
    private List<GetSchedules> getSchedulesList;
    private List<GetVideoClas> VideoClassesList = new ArrayList<>();
    private Boolean WeekFlash = false;
    private String whichApiCalled = "";
    private String MonthDataRecord = "";
    private VideoCategory videoCategory;
    private boolean mbPendingStartMeeting = false;
    private boolean isResumed = false;
    private int ActiveId;


    public static void GOTONEXTSCREEN(Bundle bundle) {
        activityXX.startActivity(new Intent(contextXX, AddTeamCoachAthleteScreen.class).putExtras(bundle));
        activityXX.overridePendingTransition(R.anim.exit, R.anim.enter);
    }


    @Override
    public void onResume() {
        super.onResume();
        UserLoginCallback.getInstance().addListener(this);

        if (RefreshUI) {
            callAPI();
            RefreshUI = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        UserLoginCallback.getInstance().removeListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_calendar_view_with_notes_sdk_21, container, false);
        context = getActivity();
        contextXX = getActivity();
        activityscheduleCalender = this;
        activityXX = getActivity();
        fragmentManager = getFragmentManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int usertype = GlobalClass.ivar1;


        linearLayout = view.findViewById(R.id.LlayputForSize);

        video_category = view.findViewById(R.id.video_category);
        if (getArguments().getString("tag") != null && getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass")) {
            SelectedCategory = getArguments().getString("tag");
            video_category.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            imageViewMenuFilter.setVisibility(GONE);
            video_category.setVisibility(VISIBLE);
        }
        //
        if (getArguments().getString("FromScreen") != null) {
            if (getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass_Category")) {


                video_category.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

                video_category.setVisibility(VISIBLE);

                whichApiCalled = "getVideoClassCategoryList";

                webServices.getVideoClassCategory(context, this);
                linearLayout.setVisibility(GONE);

                week = true;

                return view;
            }
        }


        videoCategoryAdapter = new VideoCategoryAdapter();

        video_category.setAdapter(videoCategoryAdapter);


        mZoomSDK = ZoomSDK.getInstance();
        SearchAthlete.setImageDrawable(getResources().getDrawable(R.drawable.img_live_video));


        InitAuthSDKHelper.getInstance().initSDK(context, this);
        if (getArguments().getString("FromScreen") != null) {
            if (getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass")) {
                VideoClass = true;
                if (LoginJson.get(0).getUserType().equalsIgnoreCase("4")) {
                    SearchAthlete.setVisibility(GONE);
                }
            } else {
                VideoClass = false;
            }
        }


        if (mZoomSDK.isInitialized()) {
            ZoomSDK.getInstance().getMeetingSettingsHelper().enable720p(true);
            ZoomSDK.getInstance().getMeetingSettingsHelper().setCustomizedMeetingUIEnabled(false);
        }


        new Handler().postDelayed(() -> initializeUI(), 100);


        calendarView = view.findViewById(R.id.calendarViewX);


        calendarView.setTopbarVisible(false);
        //calendarView.addDecorators(new CurrentDayDecorator());


        //calendarView.setWeekDayLabels(new String[] { "Sun", "Mon","Tue","Wed","Thu","Fri","Sat"});
        calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader);
        calendarView.setWeekDayTextAppearance(R.style.CalendarWidgetHeader);
        calendarView.setDateTextAppearance(R.style.CalendarWidgetHeader);


        calendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.MONDAY)
                .setMinimumDate(CalendarDay.from(2000, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass") ? CalendarMode.WEEKS : CalendarMode.MONTHS)
                .commit();
        calendarView.setCurrentDate(LocalDate.now());


        calendarView.setSelected(true);
        SelectedDateSchedule = LocalDate.now().toString();
        CurrentDateSchedule = LocalDate.now().toString();


        //calendarView.getLayoutParams().width = pxToDp(linearLayout.getWidth());

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            Calendar cal = Calendar.getInstance();
            String dtStart = date.getDate().toString();
            Date dateX = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateX = format.parse(dtStart);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.setTime(dateX);

            SelectedDateSchedule = "";
            SelectedDateSchedule = date.getDate().toString();
            mCalendarDialog.setSelectedDate(cal);
            if (getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass")) {
                // videoCategory.findPos(date);
            }

        });


        monthYearText = view.findViewById(R.id.monthYearText);
        monthYearText.setAllCaps(true);
        imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.img_scheduling_week));

        CurrentMonthStartDate = sdf.format(getMonthDateFirstdate());
        CurrentMonthEndDate = sdf.format(getMonthDateLastdate());
        callAPI();


        //int month = LocalDate.now().getMonth();
        // int year = mCalendarView.getCurrentDate().get(Calendar.YEAR);

        monthYearText.setText(String.valueOf(LocalDate.now().getMonth()).substring(0, 3) + " " + LocalDate.now().getYear());
        MonthDataRecord = String.valueOf(LocalDate.now().getMonth()).substring(0, 3) + " " + LocalDate.now().getYear();
        calendarView.setOnMonthChangedListener(this);
        calendarView.setOnWeekChangedListener(this);
        calendarView.invalidateDecorators();


        return view;
    }

    private void initializeUI() {
        mViewPager = view.findViewById(R.id.viewPager_calendar);
        video_s = view.findViewById(R.id.video_s);


        mEventList = new ArrayList<>();


        mCalendarDialog = CalendarDialog.Builder.instance(context).setEventList(mEventList).create(fragmentManager);
    }

    public void filterfrom(int P) {
        if (P == 1) {
            WeekFlash = true;
            CloseDrawer();
            AutoTransition autoTransition = new AutoTransition();
            autoTransition.setDuration(200);
            TransitionManager.beginDelayedTransition(linearLayout, autoTransition);
            if (!week) {


                calendarView.state().edit()
                        .setCalendarDisplayMode(CalendarMode.WEEKS)
                        .setFirstDayOfWeek(DayOfWeek.of(1)).setShowWeekDays(true)
                        .commit();

                calendarView.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                calendarView.setShowOtherDates(MaterialCalendarView.SHOW_NONE);
                imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.img_scheduling_month));
                week = true;
            } else {
                calendarView.state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).setFirstDayOfWeek(DayOfWeek.of(1)).commit();
                calendarView.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.img_scheduling_week));
                week = false;
                calendarView.setShowOtherDates(MaterialCalendarView.SHOW_NONE);
            }
            new Handler().postDelayed(() -> {
                mCalendarDialog.buildView();
            }, 50);
            new Handler().postDelayed(() -> WeekFlash = false, 100);
        } else {

            showDialog();
        }
    }

    public void showDialog() {
        if (MEMBERSHIP_STATUS == 1) {
            UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
            return;
        }

        View rootView = LayoutInflater.from(context).inflate(R.layout.email_login_activity, null);
        BubbleRelativeLayout bubbleView = rootView.findViewById(R.id.CardX);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(rootView, bubbleView);
        dialog.setCancelOnTouch(false);

        dialog.setHeight(dpToPx(300));

        dialog.showAtLocation(rootView, Gravity.CENTER, 0, 0);


        mEdtUserName = rootView.findViewById(R.id.userName);
        mEdtPassord = rootView.findViewById(R.id.password);

        mBtnLogin = rootView.findViewById(R.id.btnLogin);
        MainZoomLayout = rootView.findViewById(R.id.MainZoomLayout);
        RJoinMeeting = rootView.findViewById(R.id.RJoinMeeting);
        RLivenow = rootView.findViewById(R.id.RLivenow);
        ChooeseLayout = rootView.findViewById(R.id.ChooeseLayout);

        ChooeseLayout.setVisibility(VISIBLE);
        RJoinMeeting.setOnClickListener(v -> {
            Live = false;
            MainZoomLayout.setVisibility(VISIBLE);
            ChooeseLayout.setVisibility(GONE);

            mEdtUserName.setHint("Meeting id");
            mEdtPassord.setHint("Meeting password");

            txtConnecting.setText("Join meeting");
        });
        RLivenow.setOnClickListener(v -> {
            Live = true;
            MainZoomLayout.setVisibility(VISIBLE);
            ChooeseLayout.setVisibility(GONE);


            if (getPreferences(context, "ZoomID") != null && getPreferences(context, "ZoomPass") != null) {
                mEdtUserName.setText(getPreferences(context, "ZoomID"));
                mEdtPassord.setText(getPreferences(context, "ZoomPass"));

                if (!getPreferences(context, "ZoomID").equalsIgnoreCase("")) {
                    layoutviewSavePassword.setVisibility(VISIBLE);
                    txtConnecting.setText("START MEETING");
                }
            }
        });


        mProgressPanel = rootView.findViewById(R.id.progressPanel);
        progressBar = rootView.findViewById(R.id.progressBar);
        txtConnecting = rootView.findViewById(R.id.txtConnecting);
        layoutviewSavePassword = rootView.findViewById(R.id.layoutviewSavePassword);
        layoutviewSavePassword.setVisibility(GONE);


        layoutviewSavePassword.setOnClickListener(v -> {
            UtilityClass.SetPreferences(context, "ZoomID", "");
            UtilityClass.SetPreferences(context, "ZoomPass", "");
            dialog.dismiss();
            mZoomSDK.logoutZoom();
        });

        mBtnLogin.setOnClickListener(viewX -> {
            if (Live) {
                if (mZoomSDK.isLoggedIn()) {
                    UserLoginCallback.getInstance().removeListener(this);
                    UtilityClass.SetPreferences(context, "ZoomID", mEdtUserName.getText().toString().trim());
                    UtilityClass.SetPreferences(context, "ZoomPass", mEdtPassord.getText().toString().trim());
                    Intent intent = new Intent(context, LoginUserStartJoinMeetingActivity.class).putExtra("Type", "0");
                    dialog.dismiss();
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                    return;
                }

                String userName = mEdtUserName.getText().toString().trim();
                String password = mEdtPassord.getText().toString().trim();
                if (userName.length() == 0 || password.length() == 0) {
                    UtilityClass.showAlertDailog(context, "You need to enter user name and password.");
                    return;
                }

                if (!(EmailUserLoginHelper.getInstance().login(userName, password) == ZoomApiError.ZOOM_API_ERROR_SUCCESS)) {
                    UtilityClass.showAlertDailog(context, "ZoomSDK has not been initialized successfully or sdk is logging in.");
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            } else {
                if (mEdtUserName.getText().toString().equalsIgnoreCase("")) {
                    UtilityClass.showAlertDailog(context, "Please enter meeting id.");
                    return;
                }
//               else if(mEdtPassord.getText().toString().equalsIgnoreCase("")){
//                   UtilityClass.showAlertDailog(context,"Please enter meeting password.");
//                   return;
//               }
                Intent intent = new Intent(context, LoginUserStartJoinMeetingActivity.class).putExtra("Type", "1").putExtra("meetingNo", mEdtUserName.getText().toString()).putExtra("meetingPassword", mEdtPassord.getText().toString());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
            }
        });


    }


    @Override
    public void onZoomSDKLoginResult(long result) {
        if (result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
            //Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show();

            UtilityClass.SetPreferences(context, "ZoomID", mEdtUserName.getText().toString().trim());
            UtilityClass.SetPreferences(context, "ZoomPass", mEdtPassord.getText().toString().trim());
            Intent intent = new Intent(context, LoginUserStartJoinMeetingActivity.class).putExtra("Type", "0");
            dialog.dismiss();
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);

        } else {
            UtilityClass.showAlertDailog(context, "Login failed result code = " + result);
        }
        mBtnLogin.setVisibility(View.VISIBLE);
        progressBar.setVisibility(GONE);
    }

    @Override
    public void onZoomSDKLogoutResult(long result) {
        if (result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
            //Toast.makeText(this, "Logout successfully", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "Logout failed result code = " + result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        monthYearText.setText(new DateFormatSymbols().getMonths()[date.getMonth() - 1].substring(0, 3) + " " + date.getYear());
        if (!MonthDataRecord.equalsIgnoreCase(new DateFormatSymbols().getMonths()[date.getMonth() - 1].substring(0, 3) + " " + date.getYear())) {


            CurrentMonthStartDate = getMonthDateFirstdateFromGivenDate(date.getDate().toString());
            CurrentMonthEndDate = getMonthDateLastdateFromGivenDate(date.getDate().toString());

            callAPI();
            MonthDataRecord = new DateFormatSymbols().getMonths()[date.getMonth() - 1].substring(0, 3) + " " + date.getYear();
        }
    }

    private void callAPI() {
        if (!WeekFlash) {
            new Handler().postDelayed(() -> {


                try {
                    calendarView.invalidateDecorators();
                    calendarView.removeDecorators();
                    calendarView.addDecorator(new DayViewDecorator() {
                        @Override
                        public boolean shouldDecorate(CalendarDay day) {
                            return day.equals(CalendarDay.today());
                        }

                        @Override
                        public void decorate(DayViewFacade view) {
                            view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));
                            view.addSpan(new ForegroundColorSpan(Color.BLACK));
                        }
                    });
                    calendarView.setDateSelected(CalendarDay.today(), true);
                } catch (Exception v) {

                }


                getSchedulesList = new ArrayList<>();
                VideoClassesList = new ArrayList<>();

                if (VideoClass) {
                    whichApiCalled = "getVideoClass";
                    webServices.getVideoClass(LoginJson.get(0).getUserId(), SelectedCategory, CurrentMonthStartDate, CurrentMonthEndDate, context, scheduleCalender.this);

                } else {
                    whichApiCalled = "getSchedules";
                    webServices.getSchedules(LoginJson.get(0).getUserId(), CurrentMonthStartDate, CurrentMonthEndDate, context, scheduleCalender.this);
                }

                try {

                    mEventList = new ArrayList<>();

                    mCalendarDialog = CalendarDialog.Builder.instance(context).setEventList(mEventList).create(fragmentManager);

                } catch (Exception v) {

                }
            }, 100);
        }
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        //widget.invalidate();
        OneDayDecorator oneDayDecorator = new OneDayDecorator();
        oneDayDecorator.setDate(date.getDate());
        widget.setDateSelected(CalendarDay.today(), true);
        calendarView.invalidateDecorators();

    }

    @Override
    public void ApiResponse(String result) {
        Log.d(VolleyLog.TAG, whichApiCalled + "************ " + whichApiCalled + "\n" + "ApiResponse: " + result);
        if (result != null && result.trim().length() > 0) {
            if (whichApiCalled.equalsIgnoreCase("getSchedules")) {
                parseRequiredData(result);
            } else if (whichApiCalled.equalsIgnoreCase("getVideoClass")) {
                parseRequiredData(result);
            } else if (whichApiCalled.equalsIgnoreCase("getVideoClassCategoryList")) {
                parseRequiredData(result);
            } else if (whichApiCalled.equalsIgnoreCase("add")) {
                hide();
                parseRequiredData(result);
            } else if (whichApiCalled.equalsIgnoreCase("delete")) {
                parseRequiredData(result);
                hide();
                //callAPI();

            }
        } else {
            hide();
        }
    }

    private void parseRequiredData(String result) {
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj
                    .getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");


            if (WebServices.responseCode == 200) {

                if (whichApiCalled.equalsIgnoreCase("add")) {
                    JSONObject jsonObject = jsonObj
                            .getJSONObject("data");
                    mEventList = new ArrayList<>();

                    mCalendarDialog = CalendarDialog.Builder.instance(context).setEventList(mEventList).create(fragmentManager);

                    for (int i = 0; i < VideoClassesList.size(); i++) {
                        if (ClassVideoDate.equalsIgnoreCase(VideoClassesList.get(i).getDate())) {
                            for (int i1 = 0; i1 < VideoClassesList.get(i).getLiveClassExerciseCategory().size(); i1++) {
                                if (LiveClassExerciseVideo_ID.equalsIgnoreCase(VideoClassesList.get(i).getLiveClassExerciseCategory().get(i1).getId())) {
                                    for (int i2 = 0; i2 < VideoClassesList.get(i).getLiveClassExerciseCategory().get(i1).getLiveClassExerciseVideo().size(); i2++) {
                                        VideoClassesList.get(i).getLiveClassExerciseCategory().get(i1).getLiveClassExerciseVideo().get(i2).getLiveClassCheckInList().add(new LiveClassCheckIn(
                                                jsonObject.getString("checkin_id") + "",
                                                jsonObject.getString("video_auto_id") + "",
                                                jsonObject.getString("user_id") + "",
                                                jsonObject.getString("last_name") + "",
                                                jsonObject.getString("first_name") + "",
                                                jsonObject.getString("user_image") + ""
                                        ));
                                    }
                                }
                            }
                        }
                    }
                    rerednerList();
                    //hide();
                    return;
                }

                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");
                Gson gson = new Gson();
                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    if (whichApiCalled.equalsIgnoreCase("getSchedules")) {
                        getSchedulesList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetSchedules[].class)));

                        for (int i = 0; i < getSchedulesList.size(); i++) {
                            List<CalendarDay> m = new ArrayList<>();
                            Calendar cal = Calendar.getInstance();
                            String dtStart = getSchedulesList.get(i).getStartDate();
                            Date dateX = null;
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                dateX = format.parse(dtStart);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            String formattedDate1 = new SimpleDateFormat("yyyy").format(dateX);
                            String formattedDate2 = new SimpleDateFormat("MM").format(dateX);
                            String formattedDate3 = new SimpleDateFormat("dd").format(dateX);

                            LocalDate v = LocalDate.of(Integer.parseInt(formattedDate1), Integer.parseInt(formattedDate2), Integer.parseInt(formattedDate3));
                            final CalendarDay day = CalendarDay.from(v);

                            m.add(day);
                            cal.setTime(dateX);
                            List<Integer> seriesOfNumbers = new ArrayList<>();
                            for (int x = 0; x < getSchedulesList.get(i).getTeam().size(); x++) {
                                Random rnd2 = new Random();

                                int color2;
                                try {
                                    color2 = Color.parseColor(getSchedulesList.get(i).getTeam().get(x).getTeamColor());
                                } catch (Exception vX) {
                                    color2 = Color.argb(255, rnd2.nextInt(256), rnd2.nextInt(256), rnd2.nextInt(256));
                                }


                                if (getSchedulesList.get(i).getTeam().get(x).getTiming() != null && getSchedulesList.get(i).getTeam().get(x).getTiming().size() > 0) {
                                    for (int i1 = 0; i1 < getSchedulesList.get(i).getTeam().get(x).getTiming().size(); i1++) {
                                        mEventList.add(new calender_event(
                                                getSchedulesList.get(i).getTeam().get(x).getTeamName(),
                                                getSchedulesList.get(i).getTeam().get(x).getEmailId(),
                                                getSchedulesList.get(i).getTeam().get(x).getFirstName(),
                                                getSchedulesList.get(i).getTeam().get(x).getLastName(),
                                                getSchedulesList.get(i).getTeam().get(x).getProgramName(),
                                                getSchedulesList.get(i).getTeam().get(x).getId(),
                                                getSchedulesList.get(i).getTeam().get(x).getTeamId(),
                                                getSchedulesList.get(i).getTeam().get(x).getAssignProgramId(),
                                                getSchedulesList.get(i).getTeam().get(x).getTrainingProgramId(),
                                                getSchedulesList.get(i).getTeam().get(x).getAssignprogramDate(),
                                                getSchedulesList.get(i).getTeam().get(x).getStartDate(),
                                                getSchedulesList.get(i).getTeam().get(x).getAthleteId(),
                                                getSchedulesList.get(i).getTeam().get(x).getEndDate(),
                                                getSchedulesList.get(i).getTeam().get(x).getTiming(),//!= null ? getSchedulesList.get(i).getTeam().get(x).getTiming() : getSchedulesList.get(i).getTeam().get(x).getCoachClassTiming(),
                                                cal, color2, false,
                                                getSchedulesList.get(i).getTeam().get(x).getTeamName(),
                                                getSchedulesList.get(i).getTeam().get(x).getSchoolId(),
                                                getSchedulesList.get(i).getTeam().get(x).getTeamSports(),
                                                getSchedulesList.get(i).getTeam().get(x).getTiming().get(i1).getId(),
                                                getSchedulesList.get(i).getTeam().get(x).getTiming().get(i1).getFrom(),
                                                getSchedulesList.get(i).getTeam().get(x).getTiming().get(i1).getTo()
                                        ));
                                    }

                                } else if (getSchedulesList.get(i).getTeam().get(x).getCoachClassTiming() != null && getSchedulesList.get(i).getTeam().get(x).getCoachClassTiming().size() > 0) {
                                    for (int i1 = 0; i1 < getSchedulesList.get(i).getTeam().get(x).getCoachClassTiming().size(); i1++) {
                                        mEventList.add(new calender_event(
                                                getSchedulesList.get(i).getTeam().get(x).getTeamName(),
                                                getSchedulesList.get(i).getTeam().get(x).getEmailId(),
                                                getSchedulesList.get(i).getTeam().get(x).getFirstName(),
                                                getSchedulesList.get(i).getTeam().get(x).getLastName(),
                                                getSchedulesList.get(i).getTeam().get(x).getProgramName(),
                                                getSchedulesList.get(i).getTeam().get(x).getId(),
                                                getSchedulesList.get(i).getTeam().get(x).getTeamId(),
                                                getSchedulesList.get(i).getTeam().get(x).getAssignProgramId(),
                                                getSchedulesList.get(i).getTeam().get(x).getTrainingProgramId(),
                                                getSchedulesList.get(i).getTeam().get(x).getAssignprogramDate(),
                                                getSchedulesList.get(i).getTeam().get(x).getStartDate(),
                                                getSchedulesList.get(i).getTeam().get(x).getAthleteId(),
                                                getSchedulesList.get(i).getTeam().get(x).getEndDate(),
                                                getSchedulesList.get(i).getTeam().get(x).getTiming(),//!= null ? getSchedulesList.get(i).getTeam().get(x).getTiming() : getSchedulesList.get(i).getTeam().get(x).getCoachClassTiming(),
                                                cal, color2, false,
                                                getSchedulesList.get(i).getTeam().get(x).getTeamName(),
                                                getSchedulesList.get(i).getTeam().get(x).getSchoolId(),
                                                getSchedulesList.get(i).getTeam().get(x).getTeamSports(),
                                                getSchedulesList.get(i).getTeam().get(x).getTiming().get(i1).getId(),
                                                getSchedulesList.get(i).getTeam().get(x).getTiming().get(i1).getFrom(),
                                                getSchedulesList.get(i).getTeam().get(x).getTiming().get(i1).getTo()
                                        ));
                                    }
                                } else {
                                    mEventList.add(new calender_event(
                                            getSchedulesList.get(i).getTeam().get(x).getTeamName(),
                                            getSchedulesList.get(i).getTeam().get(x).getEmailId(),
                                            getSchedulesList.get(i).getTeam().get(x).getFirstName(),
                                            getSchedulesList.get(i).getTeam().get(x).getLastName(),
                                            getSchedulesList.get(i).getTeam().get(x).getProgramName(),
                                            getSchedulesList.get(i).getTeam().get(x).getId(),
                                            getSchedulesList.get(i).getTeam().get(x).getTeamId(),
                                            getSchedulesList.get(i).getTeam().get(x).getAssignProgramId(),
                                            getSchedulesList.get(i).getTeam().get(x).getTrainingProgramId(),
                                            getSchedulesList.get(i).getTeam().get(x).getAssignprogramDate(),
                                            getSchedulesList.get(i).getTeam().get(x).getStartDate(),
                                            getSchedulesList.get(i).getTeam().get(x).getAthleteId(),
                                            getSchedulesList.get(i).getTeam().get(x).getEndDate(),
                                            getSchedulesList.get(i).getTeam().get(x).getTiming(),//!= null ? getSchedulesList.get(i).getTeam().get(x).getTiming() : getSchedulesList.get(i).getTeam().get(x).getCoachClassTiming(),
                                            cal, color2, false,
                                            getSchedulesList.get(i).getTeam().get(x).getTeamName(),
                                            getSchedulesList.get(i).getTeam().get(x).getSchoolId(),
                                            getSchedulesList.get(i).getTeam().get(x).getTeamSports(),
                                            "",
                                            "",
                                            ""
                                    ));
                                }

                                if (seriesOfNumbers.size() < 3) {
                                    seriesOfNumbers.add(color2);
                                }
                            }

                            new Handler().postDelayed(() -> {
                                mCalendarDialog.buildView();
                            }, 50);

                            calendarView.addDecorator(new EventDecorator(m, seriesOfNumbers));
                            seriesOfNumbers = new ArrayList<>();
                        }
                        hide();
                    } else if (whichApiCalled.equalsIgnoreCase("getVideoClass")) {
                        VideoClassesList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetVideoClas[].class)));

                        rerednerList();
                        hide();
                    } else if (whichApiCalled.equalsIgnoreCase("getVideoClassCategoryList")) {
                        CoachNevigationDrawerScreen.getVideoClassCategoryList = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), GetVideoClassCategory[].class)));

                        videoCategoryAdapter = new VideoCategoryAdapter();

                        video_category.setAdapter(videoCategoryAdapter);

                        hide();
                    }
                } else {
                    hide();
                }
            } else {
                hide();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            hide();
        } catch (Exception e) {
            e.printStackTrace();
            hide();
        }
    }


    private void rerednerList() {
        for (int i = 0; i < VideoClassesList.size(); i++) {
            List<CalendarDay> m = new ArrayList<>();
            Calendar cal = Calendar.getInstance();
            String dtStart = VideoClassesList.get(i).getDate();
            Date dateX = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateX = format.parse(dtStart);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String formattedDate1 = new SimpleDateFormat("yyyy").format(dateX);
            String formattedDate2 = new SimpleDateFormat("MM").format(dateX);
            String formattedDate3 = new SimpleDateFormat("dd").format(dateX);

            LocalDate v = LocalDate.of(Integer.parseInt(formattedDate1), Integer.parseInt(formattedDate2), Integer.parseInt(formattedDate3));
            final CalendarDay day = CalendarDay.from(v);

            m.add(day);
            cal.setTime(dateX);
            List<Integer> seriesOfNumbers = new ArrayList<>();
            for (int x = 0; x < VideoClassesList.get(i).getLiveClassExerciseCategory().size(); x++) {
                Random rnd2 = new Random();

                int color2;

                color2 = Color.argb(255, rnd2.nextInt(256), rnd2.nextInt(256), rnd2.nextInt(256));


                mEventList.add(new calender_event(
                        cal,
                        VideoClassesList.get(i).getLiveClassExerciseCategory().get(x).getId(),
                        VideoClassesList.get(i).getLiveClassExerciseCategory().get(x).getCategoryName(),
                        VideoClassesList.get(i).getLiveClassExerciseCategory().get(x).getCategoryImage(),
                        VideoClassesList.get(i).getLiveClassExerciseCategory().get(x).getLiveClassExerciseVideo()
                ));


                if (seriesOfNumbers.size() < 3) {
                    seriesOfNumbers.add(color2);
                }
            }

            new Handler().postDelayed(() -> {
                mCalendarDialog.buildView();
            }, 50);

            calendarView.addDecorator(new EventDecorator(m, seriesOfNumbers));
            seriesOfNumbers = new ArrayList<>();
        }
        if (getArguments().getString("tag") != null && getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass") && ref) {

            Calendar cal = Calendar.getInstance();
            String dtStart = SelectedDateSchedule;
            Date dateX = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateX = format.parse(dtStart);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.setTime(dateX);
            mCalendarDialog.setSelectedDate(cal);

            ref = false;
        }
    }


    @Override
    public void onZoomIdentityExpired() {

    }

    @Override
    public void onZoomSDKInitializeResult(int i, int i1) {

    }

    @Override
    public void onZoomAuthIdentityExpired() {

    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getActivity().getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onWeekChanged(MaterialCalendarView widget, CalendarDay date) {
        if (getArguments().getString("tag") != null && getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass")) {
            mEventList = new ArrayList<>();

            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date dateX;

            try {
                dateX = dateFormat.parse(date.getDate().toString());

                cal.setTime(dateX);
                cal.add(Calendar.DATE, +6);
                System.out.println(dateX.toString());

                String output = dateFormat.format(dateX);
                System.out.println(output);

                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                s.format(new Date(s.parse(output).getTime()));


                CurrentMonthStartDate = s.format(new Date(s.parse(output).getTime()));
                CurrentMonthEndDate = s.format(cal.getTime());
                callAPI();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    public static class CalendarDialog {

        private static final Calendar sToday = Calendar.getInstance();
        private static final float MIN_OFFSET = 0f;
        private static final float MAX_OFFSET = 0.5f;
        private static final float MIN_ALPHA = 0.5f;
        private static final float MIN_SCALE = 0.8f;
        private final String TAG = CalendarDialog.class.getSimpleName();
        private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        private final Context mContext;
        FragmentManager fragmentManager;
        CalendarEventAdapter calendarEventAdapter;
        RecyclerView VideoList;
        List<LiveClassExerciseVideo> classExerciseVideoList = new ArrayList<>();
        ProgressDialog pDialog;

        // private AlertDialog mAlertDialog;
        ImageView imageViewBackArrow;
        TextView textViewScreenName;
        PlayerView videoFullScreenPlayerXX;
        SimpleExoPlayer playerXX;
        ProgressBar spinnerVideoDetailsXX;
        int PositionOFVideoList;
        String videoUri;
        int usertype = GlobalClass.ivar1;
        WebServices webServices = new WebServices();
        BubblePopupWindow dialog;
        private Calendar mSelectedDate = sToday;
        private List<calender_event> mEventList = new ArrayList<>();
        private OnCalendarDialogListener mListener;
        private View mView;
        private ViewPagerAdapter mViewPagerAdapter;
        private Handler mHandler;
        private boolean firstTime = true;


        CalendarDialog(Context context, FragmentManager fragmentManagerX) {
            mContext = context;
            mHandler = new Handler();
            this.fragmentManager = fragmentManagerX;
            buildView();
        }

        private int diffYMD(Calendar date1, Calendar date2) {

            if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                    date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
                    date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH))
                return 0;


            return date1.before(date2) ? -1 : 1;
        }

        public void setSelectedDate(Calendar selectedDate) {
            mSelectedDate = selectedDate;


            mViewPagerAdapter.setSelectedDate(mSelectedDate);
            //selectedDateForRefresh = selectedDate;
            mViewPager.setCurrentItem(mViewPagerAdapter.initialPageAndDay.first);
            mViewPager.setOffscreenPageLimit(1);
        }

        public void setEventList(List<calender_event> eventList) {
            mEventList = eventList;
            mViewPagerAdapter.notifyDataSetChanged();
        }

        void setOnCalendarDialogListener(OnCalendarDialogListener listener) {
            mListener = listener;
        }


        @SuppressLint("ClickableViewAccessibility")
        private void buildView() {
            mView = View.inflate(mContext, R.layout.dialog_calendar, null);
            mViewPager.setClipToPadding(false);
            mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    updatePager(mViewPager.findViewWithTag(position), 1f - positionOffset);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            updatePager(mViewPager.findViewWithTag(position + 1), positionOffset);
                            updatePager(mViewPager.findViewWithTag(position + 2), 0);
                            updatePager(mViewPager.findViewWithTag(position - 1), 0);
                        }
                    }, 50);

                }
            });

            mViewPagerAdapter = new ViewPagerAdapter(mSelectedDate, mEventList);
            mViewPager.setAdapter(mViewPagerAdapter);
            //SelectedDateSchedule = mSelectedDate;


            mViewPager.setCurrentItem(mViewPagerAdapter.initialPageAndDay.first);

//            mView.setOnTouchListener((v, event) -> {
//                dismissDialog();
//                return false;
//            });

            //mAlertDialog = new AlertDialog.Builder(mContext).create();
        }

        private void updatePager(View view, float offset) {
            if (view == null)
                return;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Date c = mSelectedDate.getTime();

                    String formattedDate = new SimpleDateFormat("yyyy").format(c);
                    String formattedDate2 = new SimpleDateFormat("MM").format(c);
                    String formattedDate3 = new SimpleDateFormat("dd").format(c);

                    LocalDate v = LocalDate.of(Integer.parseInt(formattedDate), Integer.parseInt(formattedDate2), Integer.parseInt(formattedDate3));
                }
            }, 50);
        }

        private void refreshList() {
            calendarEventAdapter.notifyDataSetChanged();
        }

        private void gotoWhiteBoard(calender_event event) {
            CoachNevigationDrawerScreen.FromSCreen = "WhiteBoard";
            CoachNevigationDrawerScreen.screenname = "white_board";
            LayoutForFolder.setVisibility(GONE);
            CoachNevigationDrawerScreen.textViewScreenName.setText("WHITE BOARD");
            imageViewMenuFilter.setVisibility(View.VISIBLE);
            SearchAthlete.setVisibility(View.VISIBLE);
            Fragment fragment;
            fragment = new WhiteBoard();

            Bundle args = new Bundle();
            args.putString("FromScreen", "ScheduleCalender");
            args.putString("team_id", event.getTeamId());
            args.putString("team_name", event.getTeamName());
            args.putString("training_program_id", event.getAssignProgramId());
            args.putString("training_program_name", event.getProgramName());
            args.putString("start_date", SelectedDateSchedule);
            args.putString("school_id", event.getSchool_id());
            fragment.setArguments(args);

            fragment.setEnterTransition(new Slide(Gravity.RIGHT));
            fragment.setExitTransition(new Slide(Gravity.LEFT));


            fm.beginTransaction().add(R.id.content_frame, fragment, "0").addToBackStack("true").commit();
            imageViewSliderDrawerToggleIcon.setVisibility(GONE);
            imageViewSliderBackIcon.setVisibility(View.VISIBLE);

            LockDrawer();
        }

        public interface OnCalendarDialogListener {
            void onEventClick(calender_event event);

            void onCreateEvent(Calendar calendar);

            void oon();
        }

        public static class Builder {

            private final CalendarDialogParams P;

            private Builder(Context context) {
                P = new CalendarDialogParams(context);
            }

            public static Builder instance(Context context) {
                return new Builder(context);
            }

            public Builder setEventList(List<calender_event> calendarEventList) {
                P.mEventList = calendarEventList;
                return this;
            }

            public Builder setSelectedDate(Calendar selectedDate) {
                P.mSelectedDate = selectedDate;
                return this;
            }

            public Builder setOnItemClickListener(OnCalendarDialogListener listener) {
                P.mOnCalendarDialogListener = listener;
                return this;
            }

            public CalendarDialog create(FragmentManager fragmentManager) {
                CalendarDialog calendarDialog = new CalendarDialog(P.mContext, fragmentManager);

                P.apply(calendarDialog);

                return calendarDialog;
            }
        }

        private static class CalendarDialogParams {

            Context mContext;

            Calendar mSelectedDate = sToday;

            List<calender_event> mEventList = new ArrayList<>();

            OnCalendarDialogListener mOnCalendarDialogListener;

            CalendarDialogParams(Context context) {
                mContext = context;
            }

            void apply(CalendarDialog calendarDialog) {
                calendarDialog.setSelectedDate(mSelectedDate);
                calendarDialog.setEventList(mEventList);
                calendarDialog.setOnCalendarDialogListener(mOnCalendarDialogListener);
            }
        }

        public class ViewPagerAdapter extends PagerAdapter {

            private static final String DEFAULT_MIN_DATE = "01/01/1992";
            private static final String DEFAULT_MAX_DATE = "01/01/2100";

            private Calendar mMinDate = getCalendarObjectForLocale(DEFAULT_MIN_DATE, Locale.getDefault());
            private Calendar mMaxDate = getCalendarObjectForLocale(DEFAULT_MAX_DATE, Locale.getDefault());

            private Pair<Integer, Calendar> initialPageAndDay;

            private int TOTAL_COUNT;


            ViewPagerAdapter(Calendar selectedDate, List<calender_event> eventList) {
                mEventList = eventList;
                //Total number of pages (between min and max date);
                TOTAL_COUNT = (int) TimeUnit.MILLISECONDS.toDays(Math.abs(mMaxDate.getTimeInMillis() - mMinDate.getTimeInMillis()));

                int initialPosition = (int) TimeUnit.MILLISECONDS.toDays(Math.abs(selectedDate.getTimeInMillis() - mMinDate.getTimeInMillis()));

                initialPageAndDay = new Pair<>(initialPosition, selectedDate);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup collection, int position) {

                final Calendar day = (Calendar) initialPageAndDay.second.clone();
                day.add(Calendar.DAY_OF_MONTH, position - initialPageAndDay.first);

                LayoutInflater inflater = LayoutInflater.from(collection.getContext());
                View view = inflater.inflate(R.layout.pager_calendar_day, collection, false);
                view.setTag(position);


                // view.setRotation(0);
                TextView tvDay = view.findViewById(R.id.tv_calendar_day);
                TextView tvDayOfWeek = view.findViewById(R.id.tv_calendar_day_of_week);
                TextView tv_calendar_day_of_Month = view.findViewById(R.id.tv_calendar_day_of_Month);
                RecyclerView rvDay = view.findViewById(R.id.rv_calendar_events);


                View rlNoAlerts = view.findViewById(R.id.rl_no_events);
                View fabCreate = view.findViewById(R.id.fab_create_event);

                fabCreate.setVisibility(GONE);
                List<calender_event> eventList = getCalendarEventsOfDay(day);

                LinearLayoutManager m = new LinearLayoutManager(collection.getContext(), LinearLayoutManager.VERTICAL, false);
                rvDay.setLayoutManager(m);

                tvDay.setText(new SimpleDateFormat("d", Locale.getDefault()).format(day.getTime()));
                tvDayOfWeek.setText(new SimpleDateFormat("EEEE", Locale.getDefault()).format(day.getTime()));
                tv_calendar_day_of_Month.setText(new SimpleDateFormat("MMM", Locale.getDefault()).format(day.getTime()));

                calendarEventAdapter = new CalendarEventAdapter(eventList, position);

                rvDay.setAdapter(calendarEventAdapter);
                rvDay.setVisibility(eventList.size() == 0 ? GONE : View.VISIBLE);

                rlNoAlerts.setVisibility(eventList.size() == 0 ? View.VISIBLE : GONE);

                collection.addView(view);


                return new ViewPagerAdapter.ViewHolder(view);
            }

            @Override
            public int getCount() {
                return TOTAL_COUNT;
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                final ViewPagerAdapter.ViewHolder holder = (ViewPagerAdapter.ViewHolder) object;
                return view == holder.container;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(((ViewPagerAdapter.ViewHolder) object).container);
            }

            private void setSelectedDate(Calendar selectedDate) {
                int position = (int) TimeUnit.MILLISECONDS.toDays(Math.abs(selectedDate.getTimeInMillis() - mMinDate.getTimeInMillis()));
                initialPageAndDay = new Pair<>(position, selectedDate);
            }

            private List<calender_event> getCalendarEventsOfDay(Calendar day) {
                List<calender_event> eventList = new ArrayList<>();
                for (calender_event e : mEventList) {
                    if (diffYMD(e.getDate(), day) == 0)
                        eventList.add(e);
                }
                return eventList;
            }

            private Calendar getCalendarObjectForLocale(String date, Locale locale) {
                Calendar calendar = Calendar.getInstance(locale);
                DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                if (date == null || date.isEmpty()) {
                    return calendar;
                }

                try {
                    final Date parsedDate = DATE_FORMATTER.parse(date);
                    if (calendar == null)
                        calendar = Calendar.getInstance();
                    calendar.setTime(parsedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return calendar;
            }

            private class ViewHolder {
                final View container;

                ViewHolder(View container) {
                    this.container = container;
                }
            }

        }

        private class CalendarEventAdapter extends RecyclerView.Adapter<ViewHolder> {
            private final List<calender_event> mCalendarEvents;
            int ViewpagerPosition = -1;
            ViewBinderHelper binderHelper = new ViewBinderHelper();
            RelativeLayout myViewXX;
            int oldPOistio;


            CalendarEventAdapter(List<calender_event> events, int positionX) {
                this.mCalendarEvents = events;
                this.ViewpagerPosition = positionX;
                this.oldPOistio = positionX;

            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater vi = LayoutInflater.from(parent.getContext());
                View v;

                v = vi.inflate(R.layout.list_item_calendar_event, parent, false);
                return new ViewHolder(v);
            }

            @Override
            public void onBindViewHolder(final ViewHolder holder, int position) {
                // holder.swipe_layoutTiming.setLockDrag(true);

                if (VideoClass) {
                    holder.video_recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                    holder.video_recycler.setAdapter(new CalendarDialog.VideoAdapter(mContext, mCalendarEvents.get(position).getLiveClassExerciseVideoList(), oldPOistio, position,
                            ""
                    ));

                    holder.video_recycler.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });
                } else {
                    calender_event event = mCalendarEvents.get(position);
                    binderHelper.setOpenOnlyOne(true);
                    binderHelper.bind(holder.swipe_layoutTiming, position + event.getId() + event.getTeamId());

                    holder.rclEventIcon.setCardBackgroundColor(mContext.getResources().getColor(R.color.color_black_for_toggle_selected));
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    holder.timeRecycler.setLayoutManager(linearLayoutManager1);
                    //holder.timeRecycler.setAdapter(new CalendarEventAdapterTiming(event.getTimingList(), event, binderHelper));
                    holder.timeRecycler.setVisibility(View.VISIBLE);

                    holder.ViewOFPlans.setVisibility(View.VISIBLE);

                    holder.timeRecycler.setVisibility(GONE);
                    holder.rclEventIcon.setVisibility(View.VISIBLE);

                    holder.rclEventIcon.setCardBackgroundColor(event.getColor());


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.rclEventIcon.getLayoutParams();
                    params.setMargins(10, 10, 10, 10);
                    holder.rclEventIcon.setLayoutParams(params);
                    holder.timeRecycler.setVisibility(GONE);
                    String defaultTitle = holder.itemView.getContext().getString(R.string.event_default_title);
                    String title = "";
                    try {
                        if (event.getTeamName().equalsIgnoreCase("") || event.getTeamName() == null) {
                            title = UtilityClass.getNameAthlete(event.getFirstName(), event.getLastName(), event.getEmailId());
                        } else {
                            title = event.getTeamName();
                        }
                    } catch (Exception v) {
                    }

                    holder.tvEventName.setText(title);
                    holder.tvEventStatus.setText(event.getProgramName());

                    holder.tvEventName.setTextColor(Color.WHITE);
                    holder.tvEventStatus.setTextColor(Color.WHITE);
                    holder.FromTime.setTextColor(Color.WHITE);
                    holder.ToTime.setTextColor(Color.WHITE);

                    holder.FromTime.setText(event.getFrom() != null || !event.getFrom().equalsIgnoreCase("") ? event.getFrom() : "-");
                    holder.ToTime.setText(event.getTo() != null || !event.getTo().equalsIgnoreCase("") ? event.getTo() : "-");

                    if (!event.getFrom().equalsIgnoreCase("")) {
                        holder.FromTime.setText(event.getFrom() != null || !event.getFrom().equalsIgnoreCase("") ? event.getFrom() : "-");
                    } else {
                        holder.FromTime.setText(" - : - ");
                    }
                    if (!event.getTo().equalsIgnoreCase("")) {
                        holder.ToTime.setText(event.getFrom());
                    } else {
                        holder.ToTime.setText(" - : - ");
                    }


                    holder.LayoutForColor.setBackgroundColor(event.getColor());

                    holder.ViewOFPlans.setOnClickListener(view1 -> {
                        holder.swipe_layoutTiming.close(true);
                        if (GlobalClass.ivar1 == 2 || event.getTeamId().equalsIgnoreCase("0")) {
                            return;
                        }
                        gotoWhiteBoard(event);
                    });

                    holder.Set_Time.setOnClickListener(view1 -> {
                        Bundle bundle = new Bundle();
                        holder.swipe_layoutTiming.close(true);
                        bundle.putString("screenName", mContext.getString(R.string.add_team));
                        bundle.putString("Type", "EditTeamSchedule");
                        bundle.putString("Teamid", event.getTeamId());
                        bundle.putString("Timing", new Gson().toJson(event.getTimingList()));
                        bundle.putString("TeamSports", new Gson().toJson(event.getTeamSports()));

                        bundle.putString("teamName", event.getTeamName());


                        if (event.getTeamId().equalsIgnoreCase("0")) {
                            UtilityClass.showAlertDailog(mContext, "You cannot set timing of individual.");
                            return;
                        }

                        GOTONEXTSCREEN(bundle);


                    });
                    holder.AddCoachFromSchedule.setVisibility(View.VISIBLE);
                    holder.AddCoachFromSchedule.setOnClickListener(view1 -> {
                        holder.swipe_layoutTiming.close(true);
                        Bundle bundle = new Bundle();
                        bundle.putString("screenName", mContext.getString(R.string.add_coach));
                        bundle.putString("Teamid", event.getTeamId());
                        if (event.getTeamId().equalsIgnoreCase("0")) {
                            UtilityClass.showAlertDailog(mContext, "You cannot assign coach to individual.");
                            return;
                        }
                        GOTONEXTSCREEN(bundle);
                    });

                    holder.swipe_layoutTiming.setSwipeListener(new SwipeRevealLayout.SwipeListener() {
                        @Override
                        public void onClosed(SwipeRevealLayout view) {
                        }

                        @Override
                        public void onOpened(SwipeRevealLayout view) {

                        }

                        @Override
                        public void onSlide(SwipeRevealLayout view, float slideOffset) {
                        }
                    });

                }
            }

            @Override
            public int getItemCount() {
                return mCalendarEvents.size();
            }


            private class VideoViewHolder extends RecyclerView.ViewHolder {

                VideoView videoview;
                RelativeLayout rLayout, myView, la;
                ImageView MyView;
                PlayerView videoFullScreenPlayer;
                TextView VideoTitleName, VideoTime, CheckinCount;

                ImageView imageViewExit, thumbnail, checkIN;


                VideoViewHolder(View view) {
                    super(view);
                    rLayout = view.findViewById(R.id.rLayout);
                    videoview = view.findViewById(R.id.video_view_home);
                    MyView = view.findViewById(R.id.MyView);


                    videoFullScreenPlayer = view.findViewById(R.id.videoFullScreenPlayer);
                    imageViewExit = view.findViewById(R.id.imageViewExit);
                    //spinnerVideoDetails = view.findViewById(R.id.spinnerVideoDetails);
                    myView = view.findViewById(R.id.myView);
                    thumbnail = view.findViewById(R.id.thumbnail);
                    VideoTitleName = view.findViewById(R.id.VideoTitleName);
                    la = view.findViewById(R.id.la);
                    VideoTime = view.findViewById(R.id.VideoTime);
                    checkIN = view.findViewById(R.id.checkIN);
                    CheckinCount = view.findViewById(R.id.CheckinCount);
                    thumbnail.setVisibility(VISIBLE);

                }


            }


        }


        private class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> implements Player.EventListener {
            SimpleExoPlayer Oldplayer;
            Context context;
            RelativeLayout myViewXX;

            int oldPOistio;
            int Eventposition;
            int viewpagerPosition;
            // Insert your Video URL
            List<LiveClassExerciseVideo> classExerciseVideoList;
            RecyclerView rvDay;


            public VideoAdapter(Context mContext, List<LiveClassExerciseVideo> liveClassExerciseVideoList, int viewpagerPosition, int Eventposition, String id) {
                this.context = mContext;
                this.classExerciseVideoList = liveClassExerciseVideoList;
                this.viewpagerPosition = viewpagerPosition;
                this.Eventposition = Eventposition;
            }

            @Override
            public VideoAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater vi = LayoutInflater.from(parent.getContext());
                View v = vi.inflate(R.layout.videoview_main, parent, false);
                return new VideoAdapter.VideoViewHolder(v);
            }

            @Override
            public void onBindViewHolder(final VideoAdapter.VideoViewHolder holder, int position) {

                RequestOptions requestOptions = new RequestOptions();

                if (classExerciseVideoList.get(position).getThumbnail_image() == null || classExerciseVideoList.get(position).getThumbnail_image().equalsIgnoreCase("")) {
                    Glide.with(context)
                            .load(classExerciseVideoList.get(position).getVideoName())
                            .apply(requestOptions)
                            .thumbnail(Glide.with(context)
                                    .load(classExerciseVideoList.get(position).getVideoName()))
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                    holder.MyView.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(holder.thumbnail);

                } else {
                    Glide.with(context).load(
                            WebServices.BASE_URL_FOR_IMAGES_ASSETS + classExerciseVideoList.get(position).getThumbnail_image())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.thumbnail);
                }
                if (classExerciseVideoList.get(position).getVideo_meeting_title() != null && !classExerciseVideoList.get(position).getVideo_meeting_title().equalsIgnoreCase("")) {
                    holder.la.setVisibility(VISIBLE);

                    holder.VideoTitleName.setText(classExerciseVideoList.get(position).getVideo_meeting_title());
                }
                holder.checkIN.setTag("false");

                for (int i = 0; i < classExerciseVideoList.get(position).getLiveClassCheckInList().size(); i++) {
                    if (LoginJson.get(0).getUserId().equalsIgnoreCase(classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).getUser_id())) {
                        holder.checkIN.setTag("true");
                        holder.VideoTitleName.setTag(classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).getCheckinid());
                        break;
                    }
                }


                holder.CheckinCount.setVisibility(classExerciseVideoList.get(position).getLiveClassCheckInList().size() > 0 ? VISIBLE : View.GONE);

                if (LoginJson.get(0).getUserType().equalsIgnoreCase("4")) {
                    holder.CheckinCount.setVisibility(View.GONE);
                }

                if (classExerciseVideoList.get(position).getIs_live().equalsIgnoreCase("0")) {
                    holder.CheckinCount.setVisibility(View.GONE);
                    holder.VideoTime.setVisibility(View.GONE);
                    holder.checkIN.setVisibility(View.GONE);
                }


                holder.CheckinCount.setText(classExerciseVideoList.get(position).getLiveClassCheckInList().size() + "");
                holder.VideoTime.setText(classExerciseVideoList.get(position).getStart_time() + "\n" + classExerciseVideoList.get(position).getEnd_time());


                if (classExerciseVideoList.get(position).getIs_live().equalsIgnoreCase("1")) {
                    if (holder.checkIN.getTag().toString().equalsIgnoreCase("true")) {
                        holder.checkIN.setImageDrawable(mContext.getDrawable(R.drawable.selected_tick_icon));
                    } else {
                        holder.checkIN.setImageDrawable(mContext.getDrawable(R.drawable.unselected_tick_icon));
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
                    String date = dateFormat.format(Calendar.getInstance().getTime());


                    Date event_date = null;
                    Date current_date = null;
                    try {
                        event_date = dateFormat.parse(classExerciseVideoList.get(position).getStart_time() + ":00 " + classExerciseVideoList.get(position).getDate());
                        current_date = dateFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        long MainTime = Minutes * 60 + Seconds;


                        if (MainTime <= 300) {
                            if (classExerciseVideoList.get(position).getDate().equalsIgnoreCase(CurrentDateSchedule)) {

                                holder.checkIN.setTag("isLive");

                                holder.checkIN.setImageDrawable(mContext.getDrawable(R.drawable.play_video_icon));

                                new CountDownTimer(MainTime * 1000 + 1000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        int seconds = (int) (millisUntilFinished / 1000);

                                        int hours = seconds / (60 * 60);
                                        int tempMint = (seconds - (hours * 60 * 60));
                                        int minutes = tempMint / 60;
                                        seconds = tempMint - (minutes * 60);

                                        holder.VideoTime.setText(
                                                String.format("%02d", minutes)
                                                        + ":" + String.format("%02d", seconds));
                                    }

                                    public void onFinish() {
                                        holder.VideoTime.setText("Started");
                                    }
                                }.start();
                            }
                        }
                    }
                }


                holder.checkIN.setOnClickListener(v -> {
                    if (holder.checkIN.getTag().toString().equalsIgnoreCase("isLive")) {
                        return;
                    } else if (holder.checkIN.getTag().toString().equalsIgnoreCase("true")) {
                        activityscheduleCalender.whichApiCalled = "delete";
                        ref = true;
                        webServices.deleteLiveClassCheckIn(holder.VideoTitleName.getTag().toString() + "", LoginJson.get(0).getUserId() + "",
                                context, activityscheduleCalender);
                        holder.checkIN.setImageDrawable(mContext.getDrawable(R.drawable.unselected_tick_icon));
                        holder.checkIN.setTag("false");
                        for (int i = 0; i < classExerciseVideoList.get(position).getLiveClassCheckInList().size(); i++) {
                            if (LoginJson.get(0).getUserId().equalsIgnoreCase(classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).getUser_id())) {
                                classExerciseVideoList.get(position).getLiveClassCheckInList().remove(i);
                                // classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).setUser_id("");

                                notifyDataSetChanged();

                                break;
                            }
                        }
                    } else if (holder.checkIN.getTag().toString().equalsIgnoreCase("false")) {
                        activityscheduleCalender.whichApiCalled = "add";
                        LiveClassExerciseVideo_ID = classExerciseVideoList.get(position).getId();

                        ClassVideoDate = classExerciseVideoList.get(position).getDate();
                        webServices.setLiveClassCheckIn(classExerciseVideoList.get(position).getId() + "",
                                LoginJson.get(0).getUserId() + "", context, activityscheduleCalender);
                        holder.checkIN.setImageDrawable(mContext.getDrawable(R.drawable.selected_tick_icon));
                        PositionOFVideoList = position;
                        holder.checkIN.setTag("true");

                        notifyDataSetChanged();
                    }
                });

                holder.CheckinCount.setOnClickListener(v -> {
                    showDialogOfCheckin(context, classExerciseVideoList.get(position).getLiveClassCheckInList(), v);
                });

                holder.coachList.setOnClickListener(view1 -> {
                    ShowInDialog(classExerciseVideoList.get(position), view1, true);
                });

                holder.VideoInfo.setVisibility(GONE);
                holder.VideoInfo.setOnClickListener(view1 -> {
                    activityXX.startActivity(new Intent(mContext, VideoViewActivity.class)
                            .putExtra("videoData", new Gson().toJson(classExerciseVideoList.get(position))));
                    activityXX.overridePendingTransition(R.anim.exit, R.anim.enter);
                });


                holder.MyView.setOnClickListener(view1 -> {
                    activityXX.startActivity(new Intent(mContext, VideoViewActivity.class).putExtra("videoData", new Gson().toJson(classExerciseVideoList.get(position))));
                    activityXX.overridePendingTransition(R.anim.exit, R.anim.enter);

//                    if (classExerciseVideoList.get(position).getIs_live().equalsIgnoreCase("1")) {
//                        Intent intent = new Intent(context, LoginUserStartJoinMeetingActivity.class).putExtra("Type", "1")
//                                .putExtra("meetingNo", classExerciseVideoList.get(position).getVideoName())
//                                .putExtra("meetingPassword", classExerciseVideoList.get(position).getPassword())
//                                .putExtra("UserName", UtilityClass.getNameAthlete(LoginJson.get(0).getFirstName(), LoginJson.get(0).getLastName(), LoginJson.get(0).getEmailId()));
//                        mContext.startActivity(intent);
//                        return;
//                    }
//
//                    LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    View v = vi.inflate(R.layout.video_view, null);
//                    releasePlayer();
//                    oldPOistio = position;
//                    videoUri = classExerciseVideoList.get(position).getVideoName();
//                    spinnerVideoDetailsXX = v.findViewById(R.id.spinnerVideoDetails);
//                    videoFullScreenPlayerXX = v.findViewById(R.id.videoFullScreenPlayer);
//
//
//                    holder.myView.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                    initializePlayer();
//                    setUp();
//                    myViewXX = holder.myView;


                });
                holder.thumbnail.setOnClickListener(view1 -> {

                    activityXX.startActivity(new Intent(mContext, VideoViewActivity.class).putExtra("videoData", new Gson().toJson(classExerciseVideoList.get(position))));
                    activityXX.overridePendingTransition(R.anim.exit, R.anim.enter);
//                    if (classExerciseVideoList.get(position).getIs_live().equalsIgnoreCase("1")) {
//                        Intent intent = new Intent(context, LoginUserStartJoinMeetingActivity.class).putExtra("Type", "1").putExtra("meetingNo", classExerciseVideoList.get(position).getVideoName())
//                                .putExtra("meetingPassword", classExerciseVideoList.get(position).getPassword())
//                                .putExtra("UserName", UtilityClass.getNameAthlete(LoginJson.get(0).getFirstName(), LoginJson.get(0).getLastName(), LoginJson.get(0).getEmailId()));
//                        mContext.startActivity(intent);
//                        return;
//                    }
//                    LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    View v = vi.inflate(R.layout.video_view, null);
//                    releasePlayer();
//                    oldPOistio = position;
//                    videoUri = classExerciseVideoList.get(position).getVideoName();
//                    spinnerVideoDetailsXX = v.findViewById(R.id.spinnerVideoDetails);
//                    videoFullScreenPlayerXX = v.findViewById(R.id.videoFullScreenPlayer);
//
//                    holder.myView.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                    initializePlayer();
//                    setUp();
//                    myViewXX = holder.myView;
                });
            }


            private void ShowInDialog(LiveClassExerciseVideo liveClassExerciseVideo, View view1, Boolean isCoachView) {
                View rootView = LayoutInflater.from(context).inflate(R.layout.videoview_main, null);
                BubbleRelativeLayout bubbleView = rootView.findViewById(R.id.CardX);
                bubbleView.setCornerRadius(70f);
                bubbleView.setArrowWidth(70f);
                bubbleView.setArrowHeight(30f);
                bubbleView.setFillColor(mContext.getResources().getColor(R.color.color_gray_for_toggle_unselected));

                ScrollView sepVideo = rootView.findViewById(R.id.sepVideo);
                RelativeLayout coach_video_profile = rootView.findViewById(R.id.coach_video_profile);
                LinearLayout video_layout = rootView.findViewById(R.id.video_layout);
                TextView time = rootView.findViewById(R.id.time);
                TextView CoachName = rootView.findViewById(R.id.CoachName);
                ImageView imageViewProfilePic = rootView.findViewById(R.id.imageViewProfilePic);
                LinearLayout rMainDialogLayout = rootView.findViewById(R.id.MainRly);


//

                if (isCoachView) {
                    sepVideo.setVisibility(GONE);
                    video_layout.setVisibility(GONE);
                    coach_video_profile.setVisibility(VISIBLE);
                    time.setText(liveClassExerciseVideo.getStart_time() + " - " + liveClassExerciseVideo.getEnd_time());

                    if (liveClassExerciseVideo.getLiveClassCoachList().size() > 0) {
                        CoachName.setText(UtilityClass.getNameAthlete(liveClassExerciseVideo.getLiveClassCoachList().get(0).getFirst_name(),
                                liveClassExerciseVideo.getLiveClassCoachList().get(0).getLast_name(),
                                liveClassExerciseVideo.getLiveClassCoachList().get(0).getEmailId()));

                        Log.d(TAG, "ShowInDialog: " + BASE_URL_FOR_IMAGES + "" + liveClassExerciseVideo.getLiveClassCoachList().get(0).getUser_image());
                        Glide.with(context).load(
                                BASE_URL_FOR_IMAGES + liveClassExerciseVideo.getLiveClassCoachList().get(0).getUser_image()
                        ).error(mContext.getDrawable(R.drawable.logo_f)).into(imageViewProfilePic);

                    }
                    rMainDialogLayout.getLayoutParams().height = CoachAddExerciseScreen.dpToPx(450);
                } else {
                    sepVideo.setVisibility(VISIBLE);
                    coach_video_profile.setVisibility(GONE);
                    rMainDialogLayout.getLayoutParams().height = CoachAddExerciseScreen.dpToPx(550);
                }

                rMainDialogLayout.getLayoutParams().height = CoachAddExerciseScreen.dpToPx(600);
                rMainDialogLayout.getLayoutParams().width = CoachAddExerciseScreen.dpToPx(450);

                dialog = new BubblePopupWindow(rootView, bubbleView);
                dialog.setCancelOnTouch(false);
                dialog.showAtLocation(view1, Gravity.CENTER, 0, 0);


            }

            private void setUp() {
                try {
                    initializePlayer();
                    buildMediaSource(Uri.parse(videoUri));
                } catch (Exception v) {

                }
            }


            private void initializePlayer() {

                if (playerXX == null) {
                    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                    TrackSelection.Factory videoTrackSelectionFactory =
                            new AdaptiveTrackSelection.Factory(bandwidthMeter);
                    TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
                    DefaultLoadControl loadControl = new DefaultLoadControl.Builder().setBufferDurationsMs(32 * 1024, 64 * 1024, 1024, 1024).createDefaultLoadControl();
                    playerXX = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

                    videoFullScreenPlayerXX.setPlayer(playerXX);


                    videoFullScreenPlayerXX.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);
                    playerXX.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                }

            }

            private void buildMediaSource(Uri mUri) {
                // Measures bandwidth during playback. Can be null if not required.
                DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                // Produces DataSource instances through which media data is loaded.
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                        Util.getUserAgent(context, mContext.getString(R.string.app_name)), bandwidthMeter);
                // This is the MediaSource representing the media to be played.
                MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(mUri);

                playerXX.prepare(videoSource);

                playerXX.setPlayWhenReady(true);
                playerXX.addListener(this);
                playerXX.setRepeatMode(Player.REPEAT_MODE_ALL);
            }

            private void releasePlayer() {
                if (playerXX != null) {
                    playerXX.release();
                    playerXX = null;
                    videoFullScreenPlayerXX = null;
                    if (myViewXX != null) {
                        myViewXX.removeAllViews();
                    }
                }
            }

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                switch (playbackState) {
                    case Player.STATE_BUFFERING:
                        spinnerVideoDetailsXX.setVisibility(VISIBLE);
                        break;
                    case Player.STATE_ENDED:
                        //resumePlayer();
                        // Activate the force enable
                        break;
                    case Player.STATE_IDLE:
                        break;
                    case Player.STATE_READY:
                        spinnerVideoDetailsXX.setVisibility(View.GONE);
                        break;
                    default:
                        // status = PlaybackStatus.IDLE;
                        break;
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {
            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
            }

            @Override
            public void onPositionDiscontinuity(int reason) {
            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            }

            @Override
            public void onSeekProcessed() {
            }

            private void showDialogOfCheckin(Context context, List<LiveClassCheckIn> f, View viewX) {


                View rootView = LayoutInflater.from(context).inflate(R.layout.cutom_dialogbox_athlete_screen, null);
                BubbleRelativeLayout bubbleView = rootView.findViewById(R.id.CardX);
                bubbleView.setCornerRadius(70f);
                bubbleView.setArrowWidth(70f);
                bubbleView.setArrowHeight(30f);
                bubbleView.setFillColor(mContext.getResources().getColor(R.color.color_gray_for_toggle_unselected));

                dialog = new BubblePopupWindow(rootView, bubbleView);
                dialog.setCancelOnTouch(false);

                dialog.showArrowTo(viewX, BubbleStyle.ArrowDirection.Up);

                //rootView.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                RelativeLayout mainRly = rootView.findViewById(R.id.mainRly);
                mainRly.invalidate();
                TextView EvenText = rootView.findViewById(R.id.EventName);

                EvenText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
                EvenText.setText("Check-Ins");

                LinearLayout rMainDialogLayout = rootView.findViewById(R.id.rMainDialogLayout);
                RelativeLayout RReventName;
                RReventName = rootView.findViewById(R.id.RReventName);


                RecyclerView dialogBoxRecyclerView = rootView.findViewById(R.id.dialogBoxRecyclerView);

                ImageView backEventDialog = rootView.findViewById(R.id.backEventDialog);
                ImageView SaveEventDialog = rootView.findViewById(R.id.SaveEventDialog);
                RecyclerView dialogBoxRecyclerData = rootView.findViewById(R.id.dialogBoxRecyclerData);

                dialogBoxRecyclerView.setHasFixedSize(true);
                dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider_dark_light));
                dialogBoxRecyclerView.addItemDecoration(dividerItemDecoration);

                dialogBoxRecyclerView.setAdapter(new AthleteCheckinRecycler(context, f));

//
                rMainDialogLayout.getLayoutParams().height = CoachAddExerciseScreen.dpToPx(300);


            }


            @Override
            public int getItemCount() {
                return classExerciseVideoList.size();
            }


            private class VideoViewHolder extends RecyclerView.ViewHolder {

                public ImageView coachList;
                VideoView videoview;
                RelativeLayout rLayout, myView, la;
                ImageView MyView, VideoInfo;
                PlayerView videoFullScreenPlayer;
                TextView VideoTitleName, VideoTime, CheckinCount;

                LinearLayout video_layout;
                ImageView imageViewExit, thumbnail, checkIN;


                VideoViewHolder(View view) {
                    super(view);
                    rLayout = view.findViewById(R.id.rLayout);
                    videoview = view.findViewById(R.id.video_view_home);
                    MyView = view.findViewById(R.id.MyView);
                    coachList = view.findViewById(R.id.coachList);
                    VideoInfo = view.findViewById(R.id.VideoInfo);
                    video_layout = view.findViewById(R.id.video_layout);
                    video_layout.setVisibility(VISIBLE);


                    videoFullScreenPlayer = view.findViewById(R.id.videoFullScreenPlayer);
                    imageViewExit = view.findViewById(R.id.imageViewExit);
                    //spinnerVideoDetails = view.findViewById(R.id.spinnerVideoDetails);
                    myView = view.findViewById(R.id.myView);
                    thumbnail = view.findViewById(R.id.thumbnail);
                    VideoTitleName = view.findViewById(R.id.VideoTitleName);
                    la = view.findViewById(R.id.la);
                    VideoTime = view.findViewById(R.id.VideoTime);
                    checkIN = view.findViewById(R.id.checkIN);
                    CheckinCount = view.findViewById(R.id.CheckinCount);
                    thumbnail.setVisibility(VISIBLE);

                }
            }
        }

        private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            CardView rclEventIcon;
            TextView tvEventName;
            TextView tvEventStatus, FromTime, ToTime, Set_Time, AddCoachFromSchedule;
            LinearLayout LayoutForAddTiming, LlayoutForEvents, LayoutForColor;
            RecyclerView timeRecycler, video_recycler;
            RelativeLayout ViewOFPlans;
            SwipeRevealLayout swipe_layoutTiming;


            ViewHolder(View view) {
                super(view);
                rclEventIcon = view.findViewById(R.id.rcl_calendar_event_icon);
                AddCoachFromSchedule = view.findViewById(R.id.AddCoachFromSchedule);
                timeRecycler = view.findViewById(R.id.timeRecycler);
                tvEventName = view.findViewById(R.id.tv_calendar_event_name);
                tvEventStatus = view.findViewById(R.id.tv_calendar_event_status);
                LayoutForAddTiming = view.findViewById(R.id.LayoutForAddTiming);
                LlayoutForEvents = view.findViewById(R.id.LlayoutForEvents);
                LayoutForColor = view.findViewById(R.id.LayoutForColor);
                FromTime = view.findViewById(R.id.FromTime);
                ToTime = view.findViewById(R.id.ToTime);
                ViewOFPlans = view.findViewById(R.id.ViewOFPlans);
                swipe_layoutTiming = view.findViewById(R.id.swipe_layoutTiming);
                Set_Time = view.findViewById(R.id.Set_Time);

                /*FOR VIDEO CLASS*/


                video_recycler = view.findViewById(R.id.video_recycler);

                if (VideoClass) {
                    rclEventIcon.setVisibility(GONE);
                    video_recycler.setVisibility(View.VISIBLE);
                }
                /*FOR VIDEO CLASS*/
            }


            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onEventClick(mEventList.get(getAdapterPosition()));
            }
        }


    }

    private class EventDecorator implements DayViewDecorator {

        //private final int[] colors;
        private List<Integer> colors = new ArrayList();
        private HashSet<CalendarDay> dates;


        public EventDecorator(Collection<CalendarDay> dates, List<Integer> colorsX) {
            this.dates = new HashSet<>(dates);

            this.colors = colorsX;
        }


        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {

            view.addSpan((new CustmMultipleDotSpan(5, colors)));

        }
    }

    private class VideoCategoryAdapter extends RecyclerView.Adapter<VideoCategoryAdapter.ViewHolder> {
        @Override
        public VideoCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater vi = LayoutInflater.from(parent.getContext());
            View v = vi.inflate(R.layout.video_category_layout, parent, false);
            return new VideoCategoryAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VideoCategoryAdapter.ViewHolder holder, int position) {
            Glide.with(context).load(WebServices.BASE_URL_FOR_IMAGES_ASSETS + CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getCategoryImage())
                    .into(holder.myImageView);
            holder.myImageViewText.setText(CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getShortTitle());
            //holder.MainCardForCat.setCardBackgroundColor(getResources().getColor(R.color.grey));
            holder.MainCardForCat.setOnClickListener(v -> {
                if ((getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass"))) {
                    SelectedCategory = CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getId();
                    CoachNevigationDrawerScreen.textViewScreenName.setText(CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getCategoryName());

                    callAPI();
                    notifyDataSetChanged();
                } else {
                    Fragment fragment = new scheduleCalender();
                    Bundle bundle = new Bundle();
                    bundle.putString("tag", CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getId());
                    bundle.putString("FromScreen", "VideoClass");
                    fragment.setArguments(bundle);
                    fragment.setEnterTransition(new Slide(Gravity.RIGHT));
                    fragment.setExitTransition(new Slide(Gravity.LEFT));
                    fm.beginTransaction().add(R.id.content_frame, fragment, CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getId()).hide(scheduleCalender.this).addToBackStack(null).commit();
                    imageViewSliderDrawerToggleIcon.setVisibility(GONE);
                    imageViewSliderBackIcon.setVisibility(View.VISIBLE);
                    SearchAthlete.setVisibility(VISIBLE);
                    imageViewMenuFilter.setVisibility(VISIBLE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText(CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getCategoryName());
                }
            });

            holder.smallCat.setOnClickListener(v -> {
                if ((getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass"))) {
                    SelectedCategory = CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getId();
                    CoachNevigationDrawerScreen.textViewScreenName.setText(CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getCategoryName());
                    keepcurrentSelectedDate = true;
                    // mCalendarDialog.setSelectedDate(selectedDateForRefresh);
                    callAPI();
                    notifyDataSetChanged();
                } else {
                    Fragment fragment = new scheduleCalender();
                    Bundle bundle = new Bundle();
                    bundle.putString("tag", CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getId());
                    bundle.putString("FromScreen", "VideoClass");
                    fragment.setArguments(bundle);
                    fragment.setEnterTransition(new Slide(Gravity.RIGHT));
                    fragment.setExitTransition(new Slide(Gravity.LEFT));
                    fm.beginTransaction().add(R.id.content_frame, fragment, CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getId()).hide(scheduleCalender.this).addToBackStack(null).commit();
                    imageViewSliderDrawerToggleIcon.setVisibility(GONE);
                    imageViewSliderBackIcon.setVisibility(View.VISIBLE);
                    SearchAthlete.setVisibility(VISIBLE);
                    imageViewMenuFilter.setVisibility(GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText(CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getCategoryName());
                }
            });


            Glide.with(context).load(WebServices.BASE_URL_FOR_IMAGES_ASSETS +
                    CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getCategoryIcon())
                    .into(holder.catImg);

            holder.catName.setText(CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getShortTitle());


            if (SelectedCategory.equalsIgnoreCase(CoachNevigationDrawerScreen.getVideoClassCategoryList.get(position).getId())) {
                holder.catName.setTextColor(getResources().getColor(R.color.Maincolor));
                holder.catImg.setColorFilter(getResources().getColor(R.color.Maincolor));
            } else {
                holder.catName.setTextColor(getResources().getColor(R.color.white));
                holder.catImg.setColorFilter(getResources().getColor(R.color.white));
            }
        }

        @Override
        public int getItemCount() {
            return CoachNevigationDrawerScreen.getVideoClassCategoryList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView myImageView;
            TextView myImageViewText, catName;
            LinearLayout AddExerciseForPurhase, smallCat;
            CardView MainCardForCat;
            RelativeLayout relativelayout;
            ImageView catImg;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                AddExerciseForPurhase = itemView.findViewById(R.id.AddExerciseForPurhase);
                myImageViewText = itemView.findViewById(R.id.myImageViewText);
                myImageView = itemView.findViewById(R.id.myImageView);
                MainCardForCat = itemView.findViewById(R.id.MainCardForCat);
                relativelayout = itemView.findViewById(R.id.relativelayout);
                catImg = itemView.findViewById(R.id.catImg);
                catName = itemView.findViewById(R.id.catName);
                smallCat = itemView.findViewById(R.id.smallCat);

                if ((getArguments().getString("FromScreen").equalsIgnoreCase("VideoClass"))) {
                    relativelayout.setVisibility(GONE);
                    MainCardForCat.setVisibility(GONE);
                    smallCat.setVisibility(VISIBLE);
                    MainCardForCat.setRadius(0f);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 0, 0, 0);
                    MainCardForCat.setLayoutParams(params);

                } else {
                    relativelayout.setVisibility(VISIBLE);
                    MainCardForCat.setVisibility(VISIBLE);
                    smallCat.setVisibility(GONE);
                }

            }
        }
    }



    /*Video View Adapter*/

    private class VideoCategory extends RecyclerView.Adapter<VideoCategory.ViewHolder> {
        @Override
        public VideoCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater vi = LayoutInflater.from(parent.getContext());
            View v = vi.inflate(R.layout.pager_calendar_day, parent, false);
            return new VideoCategory.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VideoCategory.ViewHolder holder, int position) {

            try {
                holder.tvDay.setText(new SimpleDateFormat("d", Locale.getDefault()).format(mEventList.get(position).getDate().getTime()));
                holder.tvDayOfWeek.setText(new SimpleDateFormat("EEEE", Locale.getDefault()).format(mEventList.get(position).getDate().getTime()));
                holder.tv_calendar_day_of_Month.setText(new SimpleDateFormat("MMM", Locale.getDefault()).format(mEventList.get(position).getDate().getTime()));

            } catch (Exception v) {

            }
            // holder.rvDay.setAdapter(new VideoAdapterX(context, mEventList.get(position).getLiveClassExerciseVideoList(), holder.rvDay));
        }

        @Override
        public int getItemCount() {
            return mEventList.size();
        }

        private void findPos(CalendarDay date) {

            int po = 0;
            for (int i = 0; i < mEventList.size(); i++) {

                String d = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(mEventList.get(i).getDate().getTime());
                if (d.equalsIgnoreCase(date.getDate().toString())) {
                    int finalI = i;

                    video_s.post(new Runnable() {
                        @Override
                        public void run() {

                            video_s.smoothScrollToPosition(finalI);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    video_s.smoothScrollToPosition(finalI);

                                }
                            }, 10);


                        }
                    });
                }
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView myImageView;
            TextView tvDay, tvDayOfWeek, tv_calendar_day_of_Month;
            LinearLayout AddExerciseForPurhase, smallCat;
            CardView MainCardForCat;
            RelativeLayout relativelayout;
            RecyclerView rvDay;
            ImageView catImg;
            View rlNoAlerts, fabCreate;

            public ViewHolder(@NonNull View view) {
                super(view);
                tvDay = view.findViewById(R.id.tv_calendar_day);
                tvDayOfWeek = view.findViewById(R.id.tv_calendar_day_of_week);
                tv_calendar_day_of_Month = view.findViewById(R.id.tv_calendar_day_of_Month);
                rvDay = view.findViewById(R.id.rv_calendar_events);


                rlNoAlerts = view.findViewById(R.id.rl_no_events);
                fabCreate = view.findViewById(R.id.fab_create_event);

                fabCreate.setVisibility(GONE);

                LinearLayoutManager m = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                rvDay.setLayoutManager(m);


            }
        }
    }


    public class TeamData extends RecyclerView.Adapter<CircleOvelLongViewHolder> {
        int Y;
        Context context;
        int position;

        public TeamData(int Y, Context context, int position) {
            this.Y = Y;
            this.context = context;
            this.position = position;
        }

        @Override
        public CircleOvelLongViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.team_name_list, viewGroup, false);
            return new CircleOvelLongViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CircleOvelLongViewHolder holder, int i) {

//            if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2") || userTypeOf.equalsIgnoreCase("3")) {
            holder.teamName.setText(PillarName[i]);

//
            try {
                if (ActiveId == i) {
                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_bg_yellow);
                    holder.teamName.setTextColor(Color.BLACK);
                } else {
                    holder.rLayoutofTeam.setBackgroundResource(R.drawable.round_border_gray);
                    holder.teamName.setTextColor(getResources().getColor(R.color.headerBGColor));
                }
            } catch (Exception v) {
            }

            holder.teamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.rLayoutofTeam.setOnClickListener(view -> {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                //TranslateAnimation animate = new TranslateAnimation(0,-gridViewAthlete.getWidth(),0,0);
                if (ActiveId == i) {
                    // CopyPaste(context, "Day", "", view, i);
                } else {
                    try {
                        ActiveId = i;
                        notifyDataSetChanged();
                    } catch (Exception b) {

                    }
                }

            });

            holder.rLayoutofTeam.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    //CopyPaste(context, "Day", "", view, i);
                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            int itemCount = 0;
            try {
                itemCount = PillarName.length;
            } catch (Exception x) {
                //SaveEventDialog.setVisibility(View.INVISIBLE);
            }
            return itemCount;
        }


    }

}
