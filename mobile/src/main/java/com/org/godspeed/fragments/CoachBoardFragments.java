package com.org.godspeed.fragments;

/**
 * Created by Tanveer on 8/6/2017.
 * Modified by Tanveer on 5/7/2019.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleRelativeLayout;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.ViewHolderClasses.CircleOvelLongViewHolder;
import com.org.godspeed.allOtherClasses.AddTeamCoachAthleteScreen;
import com.org.godspeed.allOtherClasses.ProfileScreenActivity;
import com.org.godspeed.chat.chat_activity;
import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
import com.org.godspeed.response_JsonS.getTeams.GetTeam;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GetTeamsDetailsClass;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.AddTeamCoachAthleteScreen.AddteamBoolean;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.CloseDrawer;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.arrayofClasses;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
import static com.org.godspeed.allOtherClasses.LoginScreen.MEMBERSHIP_STATUS;
import static com.org.godspeed.allOtherClasses.LoginScreen.userTypeOf;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_ID_FOR_PRE;
import static com.org.godspeed.service.BackgroundLocationUpdateService.ALL_ATHLETE_LIST;
import static com.org.godspeed.service.BackgroundLocationUpdateService.GetTeamORIGINAL;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.service.SchoolDataService.getSchoolsList;
import static com.org.godspeed.service.SchoolDataService.sportsArray;
import static com.org.godspeed.service.SchoolDataService.sportsIdArray;
import static com.org.godspeed.utility.UtilityClass.getDeviceTypeMobile;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES_ASSETS;

//import com.handstudio.android.hzgrapherlib.graphview.RadarGraphView;
//

public class CoachBoardFragments extends Fragment implements GodSpeedInterface {

    public static List<SelectedAthleteDEtail> AthleteData;


    public List<SelectedAthleteDEtail> SELECTED_ATHLETE_LIST = new ArrayList<>();
    //public LinearLayout lLayoutForTeamName;
    public Fragment fragment = null;
    Context context;
    String showDialogOf = "";
    RelativeLayout rLayoutMain;
    Handler handler = new Handler();
    WebServices webServices = new WebServices();
    String SelectedLevel = "";
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayListSchool = new ArrayList<>();
    Gson gson;
    LinearLayout rMainDialogLayout;
    LinearLayout lLayoutForTimeClass, lLayoutForSportsClass, lLayoutForSchoolClass, lLayoutForFIlterOption;
    RelativeLayout RReventName;
    TextView TextViewForTimeClass, TextViewForSportsClass, TextViewForSchoolClass, TypeOfFilter;
    String Sports_ID = "";
    RelativeLayout CheckView;
    LinearLayout layout;
    //layout.setGravity(Gravity.CENTER);
    TextView textViewTeamName;
    RecyclerView TeamNameList;
    int ActiveId = 0;
    TextView CancelButtonOfSearch;
    ImageView calc_clear_txt_Prise;
    EditText calc_txt_Prise;
    View view;
    Transition transition = new Slide(Gravity.TOP);
    float dX, dY;
    int lastAction;
    RelativeLayout rootLayout;
    ArrayList<String> arrayList_coach_class_timing_id;
    //private Dialog dialog;
    BubblePopupWindow dialog;
    String Message = "";
    RecyclerView coachBoardRecycler;
    View AlertBoxView;
    private ImageView imageViewSettingIcon;
    private TextView textviewAddCoach, textviewAddAthlete, textviewDeleteAthlete, textviewTrainThisTeam, textviewAddTeam;
    private RecyclerView dialogBoxRecyclerView, dialogBoxRecyclerData;
    private String userId = "", teamId = "";
    private boolean isDeleteOptionSelected = false;
    private ProgressDialog prgDailog = null;
    private ImageView backEventDialog, SaveEventDialog;
    private Vector<GetTeamsDetailsClass> vectorTeamName;
    private TextView EvenText, textViewAthleteName, textViewHeightValue, textViewSMMValue, textViewKGValue, textViewBodyFatValue, TextViewAtheleteLevel;
    private String whichApiCalled = "";
    private boolean coach = false;
    private String AthleteID, CoachID = "";
    private RelativeLayout rLayoutForBottomViewSettingsOptions, rLayoutForAddTeam, rLayoutForAddAthlete, rLayoutForAddCoach, rLayoutForDeleteAthlete;
    private ImageView imageViewForZoomInOut, imageViewForSlideTransparent, imageViewSave;
    private Animation zoomIn, zoomOut;
    private boolean isAnimationStarted;
    private RelativeLayout imageViewSummaryIcon;
    private GestureDetectorCompat detector;
    private RelativeLayout rLayoutForSwipeViews;
    private TeamData teamData;
    private FrameLayout SearchAthleteText;
    private RelativeLayout rSearchAthleteText;
    private int _xDelta;
    private int _yDelta;
    private String coach_class_timing_id = "";
    private String SchoolIDs = "";
    private List<GetTeam> GetTeamOfCoachBoard = new ArrayList<>();
    private long mRequestStartTime;
    private CoachBoardGridView coachBoardGridView;

    public void toggle() {
        TransitionManager.beginDelayedTransition(rSearchAthleteText, transition);
        rSearchAthleteText.setVisibility(rSearchAthleteText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        if (rSearchAthleteText.getVisibility() == VISIBLE) {
            calc_txt_Prise.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(calc_txt_Prise, InputMethodManager.SHOW_IMPLICIT);
        }
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_layout_coachboard, container, false);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        vectorTeamName = new Vector<GetTeamsDetailsClass>();


        mRequestStartTime = System.currentTimeMillis();
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        teamId = "";
        whichApiCalled = "team";
        context = getActivity();
        isDeleteOptionSelected = false;

        arrayList_coach_class_timing_id = new ArrayList<>();
        //rLayoutMain = view.findViewById(R.id.rLayoutMain);
        TeamNameList = view.findViewById(R.id.TeamNameList);
        SearchAthleteText = view.findViewById(R.id.SearchAthleteText);
        CancelButtonOfSearch = view.findViewById(R.id.CancelButtonOfSearch);
        rSearchAthleteText = view.findViewById(R.id.rSearchAthleteText);
        calc_txt_Prise = view.findViewById(R.id.calc_txt_Prise);
        calc_clear_txt_Prise = view.findViewById(R.id.calc_clear_txt_Prise);
        coachBoardRecycler = view.findViewById(R.id.coachBoardRecycler);


        CancelButtonOfSearch.setOnClickListener(view1 -> {
            toggle();
            calc_txt_Prise.setText("");
        });


        WizardPagerAdapter adapterX = new WizardPagerAdapter();
        ViewPager pager = view.findViewById(R.id.pager);
        pager.setAdapter(adapterX);

        pager.setCurrentItem(1);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ////Log.e(VolleyLog.TAG, "onPageScrolled: " + position);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    if (ActiveId != 0) {
                        ActiveId = --ActiveId;
                        teamData.notifyDataSetChanged();
                        teamId = GetTeamOfCoachBoard.get(ActiveId).getTeamId();
                        getAthleteDataFromServer();
                        TeamNameList.getLayoutManager().scrollToPosition(ActiveId);
                    }
                }
                if (position == 2) {
                    ActiveId = ++ActiveId;
                    teamData.notifyDataSetChanged();
                    try {
                        teamId = GetTeamOfCoachBoard.get(ActiveId).getTeamId();
                        getAthleteDataFromServer();
                        TeamNameList.getLayoutManager().scrollToPosition(ActiveId);
                    } catch (Exception v) {
                        ActiveId = --ActiveId;
                        teamData.notifyDataSetChanged();
                    }
                }
                pager.setCurrentItem(1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                ////Log.e(VolleyLog.TAG, "onPageScrollStateChanged: " + state);
            }
        });


        transition.setDuration(300);
        transition.addTarget(R.id.rSearchAthleteText);

        calc_clear_txt_Prise.setVisibility(GONE);
        calc_clear_txt_Prise.setOnClickListener(view1 -> {
            calc_txt_Prise.setText("");
        });


        calc_txt_Prise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                try {
                    coachBoardGridView.filter(text);
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

        TeamNameList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


        CheckView = view.findViewById(R.id.CheckView);
        //dialog = new Dialog(context);
        userId = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
        Log.e("User id for get team", "##### " + userId);
        WebServices webServices = new WebServices();


        gson = new Gson();
        //lLayoutForTeamName = (LinearLayout) view.findViewById(R.id.lLayoutForTeamName);
        rLayoutForSwipeViews = view.findViewById(R.id.rLayoutForSwipeViews);


        rLayoutForBottomViewSettingsOptions = view.findViewById(R.id.rLayoutForBottomViewSettingsOptions);
        rLayoutForBottomViewSettingsOptions.setVisibility(View.GONE);

        imageViewSettingIcon = view.findViewById(R.id.imageViewSettingIcon);
        imageViewSettingIcon.setImageResource(R.drawable.setting_icon);
        imageViewSettingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rLayoutForBottomViewSettingsOptions.getVisibility() == View.VISIBLE) {
                    imageViewSettingIcon.setImageResource(R.drawable.setting_icon);
                    rLayoutForBottomViewSettingsOptions.setVisibility(View.GONE);
                    isDeleteOptionSelected = false;
                } else {
                    imageViewSettingIcon.setImageResource(R.drawable.drop_down);
                    rLayoutForBottomViewSettingsOptions.setVisibility(View.VISIBLE);
                }
            }
        });


        imageViewSummaryIcon = view.findViewById(R.id.imageViewSummaryIcon);


        imageViewSummaryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                showDialogOf = "training";
                showDialogBox(0, 0, "ASSIGNED TRAINING PROGRAM's", 0, v);
            }
        });


        int usertype = GlobalClass.ivar1;


        //athleteID = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));

        if (usertype == 1) {
            coach = true;
        }


        rLayoutForAddTeam = view.findViewById(R.id.rLayoutForAddTeam);
        rLayoutForAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("screenName", getString(R.string.add_team));
                startActivity(new Intent(getActivity(), AddTeamCoachAthleteScreen.class).putExtras(bundle));
                Log.d(VolleyLog.TAG, "*************** AddTeamCoachAthleteScreen *************");
                imageViewSettingIcon.setImageResource(R.drawable.setting_icon);
                rLayoutForBottomViewSettingsOptions.setVisibility(View.GONE);
                isDeleteOptionSelected = false;
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
            }
        });


        rLayoutForAddAthlete = view.findViewById(R.id.rLayoutForAddAthlete);
        rLayoutForAddAthlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("screenName", getString(R.string.add_athlete));
                startActivity(new Intent(getActivity(), AddTeamCoachAthleteScreen.class).putExtras(bundle));
                Log.d(VolleyLog.TAG, "*************** AddTeamCoachAthleteScreen *************");

                imageViewSettingIcon.setImageResource(R.drawable.setting_icon);
                rLayoutForBottomViewSettingsOptions.setVisibility(View.GONE);
                isDeleteOptionSelected = false;
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
            }
        });
        rLayoutForAddCoach = view.findViewById(R.id.rLayoutForAddCoach);
        rLayoutForAddCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("screenName", getString(R.string.add_coach));
                try {
                    bundle.putString("Teamid", GetTeamOfCoachBoard.get(ActiveId).getTeamId());
                } catch (Exception m) {

                }
                startActivity(new Intent(getActivity(), AddTeamCoachAthleteScreen.class).putExtras(bundle));
                Log.d(VolleyLog.TAG, "*************** AddTeamCoachAthleteScreen *************");

                imageViewSettingIcon.setImageResource(R.drawable.setting_icon);
                rLayoutForBottomViewSettingsOptions.setVisibility(View.GONE);
                isDeleteOptionSelected = false;
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
            }
        });

        rLayoutForDeleteAthlete = view.findViewById(R.id.rLayoutForDeleteAthlete);
        rLayoutForDeleteAthlete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                isDeleteOptionSelected = !isDeleteOptionSelected;
                if (coachBoardGridView != null) {
                    coachBoardGridView.notifyDataSetChanged();
                }
            }
        });

        textviewAddTeam = view.findViewById(R.id.textviewAddTeam);
        textviewAddTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        textviewAddCoach = view.findViewById(R.id.textviewAddCoach);
        textviewAddCoach.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getActivity().getApplicationContext()));
        textviewAddAthlete = view.findViewById(R.id.textviewAddAthlete);
        textviewAddAthlete.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getActivity().getApplicationContext()));
        textviewDeleteAthlete = view.findViewById(R.id.textviewDeleteAthlete);
        textviewDeleteAthlete.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getActivity().getApplicationContext()));
        textviewTrainThisTeam = view.findViewById(R.id.textviewTrainThisTeam);
        textviewTrainThisTeam.setTypeface(CustomTypeface.load_AGENCYB_Fonts(getActivity().getApplicationContext()));


        coachBoardGridView = new CoachBoardGridView(context);
        coachBoardRecycler.setLayoutManager(new GridLayoutManager(context, 2));

        if (!getDeviceTypeMobile) {
            coachBoardRecycler.setLayoutManager(new GridLayoutManager(context, 4));
        }

        coachBoardRecycler.setAdapter(coachBoardGridView);

//        gridViewAthlete.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                final int SWIPE_MIN_DISTANCE = 120;
//                final int SWIPE_MAX_OFF_PATH = 250;
//                final int SWIPE_THRESHOLD_VELOCITY = 200;
//                class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
//                    @Override
//                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//                                           float velocityY) {
//                        try {
//                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH){
//                                return false;
//                            }
//                            // right to left swipe
//                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
//                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                                Toast.makeText(context, "d", Toast.LENGTH_SHORT).show();
//                            }
//                            // left to right swipe
//                            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
//                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                                Toast.makeText(context, "f", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//
//                        }
//                        return false;
//                    }
//                }
//                return false;
//            }
//        });
        rootLayout = view.findViewById(R.id.rLayoutForSwipeViews);
//        gridViewAthlete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String a = String.valueOf(position);
//                ////Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
//            }
//        });SearchAthleteText
//        gridViewAthlete.setOnTouchListener(new OnSwipeTouchListener(context){
//            @Override
//            public void onSwipeLeft() {
//                super.onSwipeLeft();
//                ActiveId =  ++ActiveId;
//                teamData.notifyDataSetChanged();
//                toggleRight();
//                teamId = GetTeam.get(ActiveId).getTeamId();
//                Log.e(VolleyLog.TAG, "onSwipeLeft: "+teamId);
//                getAthleteDataFromServer();
//                TeamNameList.getLayoutManager().scrollToPosition(ActiveId);
//            }
//
//            @Override
//            public void onSwipeRight() {
//                super.onSwipeRight();
//                if(ActiveId != 0) {
//                    ActiveId = --ActiveId;
//                    teamData.notifyDataSetChanged();
//                    teamId = GetTeam.get(ActiveId).getTeamId();
//                    toggleLeft();
//                    getAthleteDataFromServer();
//                    TeamNameList.getLayoutManager().scrollToPosition(ActiveId);
//                }
//            }
//
//        });
//        rLayoutForSwipeViews.setOnTouchListener(new DragExperimentTouchListener(gridViewAthlete.getX(), gridViewAthlete.getY()));
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //toggle();
//            }
//        }, 70);

        if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
            try {
                if (SCHOOL_ID_FOR_PRE.equalsIgnoreCase("")) {
                    SchoolIDs = getSchoolsList.get(0).getSchoolId();
                } else {
                    SchoolIDs = SCHOOL_ID_FOR_PRE;
                }


                arrayListSchool.add(SchoolIDs);
            } catch (Exception v) {
                SchoolIDs = "";
            }
            webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
        } else {
            if (GetTeamORIGINAL.size() != 0) {

                GetTeamOfCoachBoard = new ArrayList<>(GetTeamORIGINAL);

                teamId = GetTeamOfCoachBoard.get(0).getTeamId();

                UtilityClass.hide();
                getAthleteDataFromServer();

                teamData = new TeamData(0, context, 0);
                TeamNameList.setAdapter(teamData);
            } else {
                webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
            }
        }
        return view;
    }


    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, result);
        if (result != null && result.trim().length() > 0) {
            if (whichApiCalled.equalsIgnoreCase("team")) {
                parseRequiredData(result);
            } else if (whichApiCalled.equalsIgnoreCase("athlete")) {
                parseRequiredData(result);
                //UtilityClass.hide();
                //prgDailog.dismiss();
                whichApiCalled = "";
            } else if (whichApiCalled.equalsIgnoreCase("attendance")) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    String responseMessage = jsonObj
                            .getString("responseMessage");
                    // UtilityClass.showAlertDailog(context, responseMessage);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //prgDailog.dismiss();
                //UtilityClass.hide();
                whichApiCalled = "";
                coachBoardGridView.notifyDataSetChanged();
            } else if (whichApiCalled.equalsIgnoreCase("addAthleteInTeam")) {
                getAthleteDataFromServer();
            } else if (whichApiCalled.equalsIgnoreCase("deleteTeams")) {
                parseRequiredData(result);
            }

        } else {
            UtilityClass.hide();
        }
    }

    public void filterfrom() {
        int[] location = new int[2];
        imageViewMenuFilter.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        Log.e(VolleyLog.TAG, "onClick: " + x + " " + y);
        //.getLocationOnScreen();
        showDialogOf = "Classes";
        showDialogBox(x, y, "ATHLETE CLASSES", 0, imageViewMenuFilter);
        CloseDrawer();
    }

    private void getAthleteDataFromServer() {
        //prgDailog.setMessage("Loading sports data. Please wait...");

        coachBoardRecycler.setAdapter(null);
        whichApiCalled = "athlete";
        WebServices webServices = new WebServices();

        mRequestStartTime = System.currentTimeMillis();
        ////UtilityClass.showWaitDialog(new Dialog(context),context);

        //UtilityClass.showWaitDialog(new Dialog(context),context);
        //webServices.getAthlete(userId, "3", Sports_ID  , context, CoachBoardFragments.this);
        webServices.getAthlete(userId, teamId, Sports_ID, SchoolIDs, context, CoachBoardFragments.this);
        ////Log.e(VolleyLog.TAG, "getAthleteDataFromServer: " + userId + " " + teamId);
    }

    private void parseRequiredData(String result) {
        ////Log.e(VolleyLog.TAG, "parseRequiredData:TAGS " + result);
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

                JSONArray jsonDataArray = jsonObj.getJSONArray("data");

                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    if (whichApiCalled.equalsIgnoreCase("athlete")) {
                        DateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.US);
                        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                        long totalRequestTime = System.currentTimeMillis() - mRequestStartTime;
                        String text = formatter.format(new Date(totalRequestTime));

                        Log.d(VolleyLog.TAG, "ATHLETE RESPONSE TIME: " + text);
                        AthleteData = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), SelectedAthleteDEtail[].class)));

                        coachBoardGridView = new CoachBoardGridView(context);
                        coachBoardRecycler.setAdapter(coachBoardGridView);
                        UtilityClass.hide();
                    } else if (whichApiCalled.equalsIgnoreCase("team")) {

                        for (int x = 0; x < jsonDataArray.length(); x++) {
                            JSONObject jsonAthleteObjDataX = jsonDataArray.getJSONObject(x);
                            try {
                                Object json = jsonAthleteObjDataX.get("Training_program_detail");
                                if (json instanceof String) {
                                    jsonAthleteObjDataX.put("Training_program_detail", new JSONArray());

                                }
                            } catch (Exception e) {
                            }
                        }

                        //lLayoutForTeamName.removeAllViews();

                        GetTeamOfCoachBoard = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetTeam[].class)));

                        teamId = GetTeamOfCoachBoard.get(0).getTeamId();

                        UtilityClass.hide();
                        getAthleteDataFromServer();

                        teamData = new TeamData(0, context, 0);
                        TeamNameList.setAdapter(teamData);

                    } else if (whichApiCalled.equalsIgnoreCase("addAthleteInTeam")) {
                        getAthleteDataFromServer();
                        Log.e(VolleyLog.TAG, "parseRequi ");
                    } else if (whichApiCalled.equalsIgnoreCase("deleteTeams")) {
                        whichApiCalled = "team";
                        WebServices webServices = new WebServices();
                        webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
                    }
                }
            } else {
                if (whichApiCalled.equalsIgnoreCase("team")) {
                    UtilityClass.showAlertDailog(context, responseMessage);
                }
                UtilityClass.hide();
            }
        } catch (JSONException e) {
            UtilityClass.hide();
            e.printStackTrace();
            if (whichApiCalled.equalsIgnoreCase("deleteTeams")) {
                whichApiCalled = "team";
                WebServices webServices = new WebServices();
                webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
            }
        } catch (Exception e) {
            UtilityClass.hide();
            e.printStackTrace();
            if (whichApiCalled.equalsIgnoreCase("deleteTeams")) {
                whichApiCalled = "team";
                WebServices webServices = new WebServices();
                webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (coachBoardGridView != null) {
            coachBoardGridView = new CoachBoardGridView(context);
            coachBoardRecycler.setAdapter(coachBoardGridView);
            coachBoardGridView.notifyDataSetChanged();
        }
        if (AddteamBoolean == true) {
            whichApiCalled = "team";
            WebServices webServices = new WebServices();
            webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
            AddteamBoolean = false;
        }
    }


    /********************** *??????????/////////////////*/
    private void showDialogBox(int x, int y, String athlete_level, int position, View viewX) {
        if (MEMBERSHIP_STATUS == 1) {
            UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
            return;
        }
        AlertBoxView = LayoutInflater.from(context).inflate(R.layout.cutom_dialogbox_athlete_screen, null);
        BubbleRelativeLayout bubbleView = AlertBoxView.findViewById(R.id.CardX);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);

        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));
        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);
        dialog.setCancelOnTouch(false);


        if (showDialogOf.equalsIgnoreCase("Classes")) {
            dialog.showAtLocation(viewX, Gravity.CENTER, 0, 0);
        } else {
            dialog.showArrowTo(viewX, BubbleStyle.ArrowDirection.Up);
        }


        //

        RelativeLayout mainRly = AlertBoxView.findViewById(R.id.mainRly);
        mainRly.invalidate();
        EvenText = AlertBoxView.findViewById(R.id.EventName);
        EvenText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        backEventDialog = AlertBoxView.findViewById(R.id.backEventDialog);
        SaveEventDialog = AlertBoxView.findViewById(R.id.SaveEventDialog);


        lLayoutForTimeClass = AlertBoxView.findViewById(R.id.lLayoutForTimeClass);
        lLayoutForSportsClass = AlertBoxView.findViewById(R.id.lLayoutForSportsClass);
        lLayoutForSchoolClass = AlertBoxView.findViewById(R.id.lLayoutForSchoolClass);

        if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
            lLayoutForSchoolClass.setVisibility(VISIBLE);
        } else {
            lLayoutForSchoolClass.setVisibility(GONE);
        }

        lLayoutForFIlterOption = AlertBoxView.findViewById(R.id.lLayoutForFIlterOption);


        RReventName = AlertBoxView.findViewById(R.id.RReventName);

        rMainDialogLayout = AlertBoxView.findViewById(R.id.rMainDialogLayout);
        TextViewForTimeClass = AlertBoxView.findViewById(R.id.TextViewForTimeClass);
        TextViewForSportsClass = AlertBoxView.findViewById(R.id.TextViewForSportsClass);
        TextViewForSchoolClass = AlertBoxView.findViewById(R.id.TextViewForSchoolClass);
        TypeOfFilter = AlertBoxView.findViewById(R.id.TypeOfFilter);

        lLayoutForTimeClass.setOnClickListener(view -> {
            if (MEMBERSHIP_STATUS == 1) {
                UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                return;
            }
            lLayoutForFIlterOption.setVisibility(View.GONE);
            TypeOfFilter.setVisibility(View.VISIBLE);
            TypeOfFilter.setText("Select Timing");
            dialogBoxRecyclerView.setVisibility(View.VISIBLE);
            dialogBoxRecyclerView.setAdapter(new AthleteLevelView(position, "Timing"));
            SaveEventDialog.setVisibility(View.VISIBLE);
            backEventDialog.setVisibility(View.VISIBLE);
        });

        if (athlete_level.equalsIgnoreCase("ATHLETE CLASSES") && !getDeviceTypeMobile) {
            rMainDialogLayout.getLayoutParams().width = dpToPx(300);
        }

        lLayoutForSportsClass.setOnClickListener(view -> {
            if (MEMBERSHIP_STATUS == 1) {
                UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                return;
            }
            lLayoutForFIlterOption.setVisibility(View.GONE);
            TypeOfFilter.setVisibility(View.VISIBLE);
            TypeOfFilter.setText("Select Sport");
            dialogBoxRecyclerView.setVisibility(View.VISIBLE);
            dialogBoxRecyclerView.setAdapter(new AthleteLevelView(position, "Sport"));
            SaveEventDialog.setVisibility(View.VISIBLE);
            backEventDialog.setVisibility(View.VISIBLE);
        });


        lLayoutForSchoolClass.setOnClickListener(view -> {
            lLayoutForFIlterOption.setVisibility(View.GONE);
            TypeOfFilter.setVisibility(View.VISIBLE);
            TypeOfFilter.setText("Select School");
            dialogBoxRecyclerView.setVisibility(View.VISIBLE);
            dialogBoxRecyclerView.setAdapter(new AthleteLevelView(position, "School"));
            SaveEventDialog.setVisibility(View.VISIBLE);
            backEventDialog.setVisibility(View.VISIBLE);
        });


        TextViewForSportsClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        TextViewForSchoolClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        TextViewForTimeClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        dialogBoxRecyclerView = AlertBoxView.findViewById(R.id.dialogBoxRecyclerView);


        if (showDialogOf.equalsIgnoreCase("Classes")) {
            rMainDialogLayout.setBackground(getResources().getDrawable(R.drawable.bg_white_rounded_dialo2g));
            EvenText.setText("FILTER(s)");
            lLayoutForFIlterOption.setVisibility(View.VISIBLE);
            TypeOfFilter.setVisibility(View.VISIBLE);
            dialogBoxRecyclerView.setVisibility(View.GONE);
        }


        //rMainDialogLayout.setLayoutParams(rMainDialogLayout.getHeight() = 320);


        dialogBoxRecyclerView.setHasFixedSize(true);
        dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        DividerItemDecoration divider = new
                DividerItemDecoration(dialogBoxRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(
                ContextCompat.getDrawable(context, R.drawable.divider_dark_light)
        );
        //}
        dialogBoxRecyclerView.addItemDecoration(divider);

        dialogBoxRecyclerView.setAdapter(new AthleteLevelView(position, ""));


//

    }


    private void toggleLeft() {
        TranslateAnimation animate = new TranslateAnimation(0, rLayoutForSwipeViews.getWidth(), 0, 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        rLayoutForSwipeViews.startAnimation(animate);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rLayoutForSwipeViews.clearAnimation();
            }
        }, 500);
    }

//    private void showDialogBox(int x, int y, String athlete_level, int position) {
//
//    }

    private void toggleRight() {
        TranslateAnimation animate = new TranslateAnimation(0, -rLayoutForSwipeViews.getWidth(), 0, 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        rLayoutForSwipeViews.startAnimation(animate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rLayoutForSwipeViews.clearAnimation();
            }
        }, 500);

        //image.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void addNote(int x, int y, String event, String TeamIDNote, String AthleteIDNote, View view1) {
        AlertBoxView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_box_for_sets, null);
        BubbleLinearLayout bubbleView = AlertBoxView.findViewById(R.id.mainRlyofAddset);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);
        dialog.setCancelOnTouch(false);

        dialog.showArrowTo(view1, BubbleStyle.ArrowDirection.Up);

        if (event.equalsIgnoreCase("Send message team")) {
            AlertBoxView.getLayoutParams().height = dpToPx(180);
            AlertBoxView.getLayoutParams().width = dpToPx(200);
        }

        TextView txt = AlertBoxView.findViewById(R.id.EventName);
        ImageView addsetSave = AlertBoxView.findViewById(R.id.addsetSave);
        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        txt.setText(event);


        addsetSave.setImageDrawable(getResources().getDrawable(R.drawable.send_message_img));
        LinearLayout mainRlyofAddset = AlertBoxView.findViewById(R.id.mainRlyofAddset);


        //mainRlyofAddset.setBackgroundColor(Color.parseColor("#545454"));

        TextView txtData = AlertBoxView.findViewById(R.id.eventData);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        txtData.setVisibility(GONE);


        EditText addnotes = AlertBoxView.findViewById(R.id.addnotes);

        addnotes.setHint("Enter message");

        addnotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Message = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        addsetSave.setOnClickListener(view -> {
            if (MEMBERSHIP_STATUS == 1) {
                UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                return;
            }
            if (Message.equalsIgnoreCase("")) {
                UtilityClass.showAlertDailog(context, "Please enter message.");
                return;
            }
            webServices.send_PushNotification(
                    TeamIDNote
                    , AthleteIDNote,
                    Message,
                    LoginJson.get(0).getUserId(), context, CoachBoardFragments.this);
            Message = "";
            dialog.dismiss();
        });


//        WindowManager.LayoutParams wmlp = mView.getWindow().getAttributes();
//        wmlp.gravity = Gravity.CENTER_VERTICAL | Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.CENTER;
//        wmlp.x = x - 150;
//        wmlp.y = y - 100;
//        dialog.show();
    }


    public void CopyPaste(Context context, String event, String eventData, View rLayoutofTeam, int TeamPosition) {
        if (MEMBERSHIP_STATUS == 1) {
            UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
            return;
        }
//        AlertBoxView = new Dialog(context);
//        AlertBoxView.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        AlertBoxView.setContentView(R.layout.black_menu_option);
//        AlertBoxView.setCanceledOnTouchOutside(true);
//        AlertBoxView.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        AlertBoxView.getWindow().setDimAmount(0.2f);


        AlertBoxView = LayoutInflater.from(context).inflate(R.layout.black_menu_option, null);
        BubbleLinearLayout bubbleView = AlertBoxView.findViewById(R.id.copyPaste);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);

        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));
        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);
        dialog.setCancelOnTouch(false);
        dialog.showArrowTo(rLayoutofTeam, BubbleStyle.ArrowDirection.Up);

        //AlertBoxView.getLayoutParams().height = dpToPx(53);

        LinearLayout mainRlyofAddset = AlertBoxView.findViewById(R.id.mainRlyofAddset);
        LinearLayout copyPaste = AlertBoxView.findViewById(R.id.copyPaste);
        LinearLayout ForAddNote = AlertBoxView.findViewById(R.id.ForAddNote);
        LinearLayout AddCoachL = AlertBoxView.findViewById(R.id.AddCoachL);
        LinearLayout AddAthleteByCoach = AlertBoxView.findViewById(R.id.AddAthleteByCoach);
        LinearLayout SendMessageByCoach = AlertBoxView.findViewById(R.id.SendMessageByCoach);
        SendMessageByCoach.setVisibility(VISIBLE);
        TextView TextCopy = AlertBoxView.findViewById(R.id.TextCopy);
        TextCopy.setText("Edit");
        //TextView CopyText = AlertBoxView.findViewById(R.id.CopyText);


        TextView PasteText = AlertBoxView.findViewById(R.id.TextPaste);
        TextView SendMessageByCoachText = AlertBoxView.findViewById(R.id.SendMessageByCoachText);

        copyPaste.setVisibility(VISIBLE);
        AddAthleteByCoach.setVisibility(VISIBLE);

        PasteText.setText("Delete");

        SendMessageByCoachText.setOnClickListener(view1 -> {
            dialog.dismiss();
            addNote(0, 0, "Send message team", GetTeamOfCoachBoard.get(ActiveId).getTeamId(), "", rLayoutofTeam);
        });

        LinearLayout Copy = AlertBoxView.findViewById(R.id.Copy);
        LinearLayout Paste = AlertBoxView.findViewById(R.id.Paste);


        Paste.setVisibility(VISIBLE);
        AddCoachL.setVisibility(VISIBLE);

        AddAthleteByCoach.setOnClickListener(view1 -> {


            //.getLocationOnScreen();
            showDialogOf = "AddAthlete";
            dialog.dismiss();
            showDialog(context, 0, 0, "AddAthlete", "", rLayoutofTeam);
        });


        Copy.setOnClickListener(view1 -> {
            if (MEMBERSHIP_STATUS == 1) {
                UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                return;
            }
            dialog.dismiss();
            Bundle bundle = new Bundle();
            bundle.putString("screenName", getString(R.string.add_team));
            bundle.putString("Type", "EditTeam");
            bundle.putString("Teamid", GetTeamOfCoachBoard.get(ActiveId).getTeamId());
            bundle.putInt("TeamPosition", TeamPosition);
            bundle.putString("teamName", GetTeamOfCoachBoard.get(ActiveId).getTeamName());
            startActivity(new Intent(getActivity(), AddTeamCoachAthleteScreen.class).putExtras(bundle));

            Log.d(VolleyLog.TAG, "*************** AddTeamCoachAthleteScreen *************");

            getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
        });
        AddCoachL.setOnClickListener(view1 -> {
            if (MEMBERSHIP_STATUS == 1) {
                UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                return;
            }
            dialog.dismiss();
            Bundle bundle = new Bundle();
            bundle.putString("screenName", getString(R.string.add_coach));
            bundle.putString("Type", "AddCoach");
            bundle.putString("Teamid", GetTeamOfCoachBoard.get(ActiveId).getTeamId());
            bundle.putInt("TeamPosition", TeamPosition);
            //GetTeam.get(ActiveId).get
            bundle.putString("teamName", GetTeamOfCoachBoard.get(ActiveId).getTeamName());
            startActivity(new Intent(getActivity(), AddTeamCoachAthleteScreen.class).putExtras(bundle));
            getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);

            Log.d(VolleyLog.TAG, "*************** AddTeamCoachAthleteScreen *************");

        });

        Paste.setOnClickListener(view1 -> {
            if (MEMBERSHIP_STATUS == 1) {
                UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                return;
            }
            whichApiCalled = "deleteTeams";
            webServices.deleteTeams(GetTeamOfCoachBoard.get(ActiveId).getTeamId(), LoginJson.get(0).getUserId(), context, CoachBoardFragments.this);
            dialog.dismiss();
        });


        TextView txtData = AlertBoxView.findViewById(R.id.eventData);


    }

    public void showDialog(Context context, int x, int y, String event, String eventData, View view1) {


        AlertBoxView = LayoutInflater.from(context).inflate(R.layout.layout_for_sekeleton, null);
        BubbleRelativeLayout bubbleView = AlertBoxView.findViewById(R.id.CardX);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);

        dialog.setHeight(dpToPx(150));
        dialog.setCancelOnTouch(false);

        dialog.showArrowTo(view1, BubbleStyle.ArrowDirection.Up);

        if (event.equalsIgnoreCase("AddAthlete")) {
            //AlertBoxView.getLayoutParams().height = dpToPx(190);
        }


        RelativeLayout mainRly = AlertBoxView.findViewById(R.id.mainRly);

        EditText EdittextSearchMuscular = AlertBoxView.findViewById(R.id.EdittextSearchMuscular);

        mainRly.invalidate();

        TextView EvenText = AlertBoxView.findViewById(R.id.EventName);

        rMainDialogLayout = AlertBoxView.findViewById(R.id.rMainDialogLayout);

        ViewGroup.LayoutParams params = rMainDialogLayout.getLayoutParams();

        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        rMainDialogLayout.setLayoutParams(params);

        EvenText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        EvenText.setText("ADD ATHLETE");
        EvenText.setTextSize(19f);

        ImageView addAthleteSave = AlertBoxView.findViewById(R.id.addAthleteSave);
        addAthleteSave.setVisibility(VISIBLE);
        addAthleteSave.setOnClickListener(view1x -> {
            String AthleteID = "";
            StringBuilder stringBuilder = new StringBuilder();
            for (int i1 = 0; i1 < SELECTED_ATHLETE_LIST.size(); i1++) {
                AthleteID = stringBuilder.append(SELECTED_ATHLETE_LIST.get(i1).getUserId() + (i1 != SELECTED_ATHLETE_LIST.size() - 1 ? "," : "")).toString();
            }
            if (AthleteID.equalsIgnoreCase("")) {
                UtilityClass.showAlertDailog(context, "Please select athlete's.");
                return;
            }
            whichApiCalled = "addAthleteInTeam";
            webServices.addAthleteInTeam(GetTeamOfCoachBoard.get(ActiveId).getTeamId(), AthleteID, context, CoachBoardFragments.this);
            dialog.dismiss();
        });
        dialogBoxRecyclerView = AlertBoxView.findViewById(R.id.dialogBoxRecyclerView);

        dialogBoxRecyclerView.setHasFixedSize(true);

        dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        DividerItemDecoration divider = new DividerItemDecoration(dialogBoxRecyclerView.getContext(), DividerItemDecoration.VERTICAL);

        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider_light));

        dialogBoxRecyclerView.addItemDecoration(divider);
        SELECTED_ATHLETE_LIST = new ArrayList<>();
        AthleteLevelView athleteLevelView = new AthleteLevelView(0, "");
        dialogBoxRecyclerView.setAdapter(athleteLevelView);

        EdittextSearchMuscular.setTextColor(getResources().getColor(R.color.color_white));
        EdittextSearchMuscular.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                athleteLevelView.SearchAthlete(text);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //dialogBoxRecyclerView.setAdapter(new SkeletonProfile(context,menus,Skeltons));

        backEventDialog = AlertBoxView.findViewById(R.id.backEventDialog);

        backEventDialog.setVisibility(View.INVISIBLE);

    }

    private void CallApiOfLevel(RecyclerViewHolder2 Holder, int Y, int i, int position, float weightOf) {
        try {
            String ID = "";
            String row_no = "";
            WebServices webServices = new WebServices();
            whichApiCalled = "updateAthleteDetailsDCRs";
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            DecimalFormat twoDFormx = new DecimalFormat("#");
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
                AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
            } else {
                LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
            }

            Holder.AtheleteExerciseValueMultiple.setText(ExerciseValues + "");

            webServices.updateMultiplierData(context, CoachBoardFragments.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);

        } catch (Exception m) {
        }
    }

    public class ViewHoler {
        private TextView textViewTeamName, textViewPresentAbsent;
        private ImageView imageViewPresentAbsent, imageViewAthelets, imageViewAtheletsx, imgAtheletsFrame, imageViewDeleteAthelets, AtlevelCoachScreenDialog;
        private RelativeLayout rLayoutMain, rLAyoutForAthleteAttendance, rLayoutForGalleryImage, messageCoach;
    }

    /*********************************** DialogBox Data *************************************/

    public class CoachBoardGridView extends RecyclerView.Adapter<CoachBoardGridView.GridViewHolder> {

        List<SelectedAthleteDEtail> AthleteDataLocal = AthleteData;
        private Context context;
        private LayoutInflater vi;
        private ViewHoler holder;

        public CoachBoardGridView(Context context) {
            this.context = context;
        }

        @Override
        public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.grid_view_items, viewGroup, false);
            return new GridViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final GridViewHolder holder, int position) {
            holder.messageCoach.setOnClickListener(view1 -> {
                if (MEMBERSHIP_STATUS == 1) {
                    UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                    return;
                }
                try {
                    dialog.dismiss();
                } catch (Exception v) {
                }
//
//                int[] location = new int[2];
//                view1.getLocationOnScreen(location);
//                int x = location[0];
//                int y = location[1];
//                addNote(x, y, "Send message athlete", "", AthleteDataLocal.get(position).getUserId());

                Bundle bundle = new Bundle();
                bundle.putString("client_name", UtilityClass.getNameAthlete(AthleteDataLocal.get(position).getFirstName(), AthleteDataLocal.get(position).getLastName(), AthleteDataLocal.get(position).getEmailId()));
                bundle.putString("client_id", AthleteDataLocal.get(position).getUserId());
                bundle.putString("client_message", "");
                startActivity(new Intent(context, chat_activity.class).putExtras(bundle));
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** chat_activity *************");

            });


            holder.AtlevelCoachScreenDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int[] location = new int[2];
                    holder.messageCoach.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    //.getLocationOnScreen();
                    int po = 0;
                    for (int i = 0; i < AthleteData.size(); i++) {
                        if (AthleteData.get(i).getUserId().equalsIgnoreCase(AthleteDataLocal.get(position).getUserId())) {
                            po = i;
                        }
                    }


                    showDialogOf = "LEVEL";
                    showDialogBox(x, y, "ATHLETE LEVEL", po, holder.textViewTeamName);
                    if (coach) {
                        AthleteID = AthleteDataLocal.get(position).getUserId();
                        CoachID = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
                        SelectedLevel = "";
                        try {
                            SelectedLevel = AthleteDataLocal.get(position).getAthleteLevel().get(0).getAthleteLevel();
                        } catch (Exception d) {
                            ////Log.e(VolleyLog.TAG, "onCreateView: " + d);
                        }
                    } else {
                        AthleteID = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
                        CoachID = AthleteID;
                    }
                }
            });

            holder.textViewTeamName.setText(UtilityClass.getNameAthlete(AthleteDataLocal.get(position).getFirstName(), AthleteDataLocal.get(position).getLastName(), AthleteDataLocal.get(position).getEmailId()));

            ////Toast.makeText(context, ""+vectorAthleteDetails.get(position).getEmailId(), Toast.LENGTH_LONG).show();

            ////Log.e(VolleyLog.TAG, "TAn: " + AthleteDataLocal.get(position).getEmailId());
            if (AthleteDataLocal.get(position).getAthleteAttendance().equalsIgnoreCase("1")) {
                holder.textViewPresentAbsent.setText(getString(R.string.present));
                holder.textViewPresentAbsent.setTextColor(getResources().getColor(R.color.textColorYellow));
                holder.imageViewPresentAbsent.setImageResource(R.drawable.select_exercise);
            } else {
                holder.textViewPresentAbsent.setText(getString(R.string.absent));
                holder.textViewPresentAbsent.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
                holder.imageViewPresentAbsent.setImageResource(R.drawable.absent);
            }

            (holder.rLayoutForGalleryImage.getBackground()).setColorFilter(Color.parseColor("#343436"), PorterDuff.Mode.SRC_IN);
            holder.rLayoutForGalleryImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MEMBERSHIP_STATUS == 1) {
                        UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                        return;
                    }
                    Bundle bundle = new Bundle();

                    bundle.putInt("position", position);
                    String coachId = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));

                    bundle.putString("coachID", coachId);
                    bundle.putString("atheleteID", AthleteDataLocal.get(position).getUserId());
                    TransitionManager.beginDelayedTransition(rSearchAthleteText, transition);
                    rSearchAthleteText.setVisibility(rSearchAthleteText.getVisibility() == VISIBLE ? GONE : View.GONE);
                    calc_txt_Prise.setText("");
                    //GlobalClass.position = position;
                    // bundle.putSerializable("trainingDataAssignedbyIDX", (Serializable) AthleteDataLocal);
                    startActivity(new Intent(getActivity(), ProfileScreenActivity.class).putExtras(bundle));
                    getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                    Log.d(VolleyLog.TAG, "*************** ProfileScreenActivity *************");
//
                }
            });

            holder.rLAyoutForAthleteAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MEMBERSHIP_STATUS == 1) {
                        UtilityClass.showAlertDailog(context, "Your membership is expired! \n please renew it.");
                        return;
                    }
                    whichApiCalled = "attendance";
                    WebServices webServices = new WebServices();
                    String coachId = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
                    if (!AthleteDataLocal.get(position).getAthleteAttendance().equalsIgnoreCase("0")) {
                        AthleteData.get(position).setAthleteAttendance("0");
                        holder.textViewPresentAbsent.setText(getString(R.string.absent));
                        holder.textViewPresentAbsent.setTextColor(getResources().getColor(R.color.color_for_absent_palyer));
                        holder.imageViewPresentAbsent.setImageResource(R.drawable.absent);
                        webServices.setAtheleteAttandance(context, AthleteDataLocal.get(position).getUserId(), coachId, "0", CoachBoardFragments.this);
                    } else {
                        holder.textViewPresentAbsent.setText(getString(R.string.present));
                        holder.textViewPresentAbsent.setTextColor(getResources().getColor(R.color.textColorYellow));
                        holder.imageViewPresentAbsent.setImageResource(R.drawable.select_exercise);
                        AthleteData.get(position).setAthleteAttendance("1");

                        webServices.setAtheleteAttandance(context, AthleteDataLocal.get(position).getUserId(), coachId, "1", CoachBoardFragments.this);
                    }
                    notifyDataSetChanged();

                }
            });

            holder.imageViewDeleteAthelets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            if (isDeleteOptionSelected) {
                holder.imageViewDeleteAthelets.setVisibility(View.VISIBLE);
            } else {
                holder.imageViewDeleteAthelets.setVisibility(View.GONE);
            }

            if (AthleteDataLocal.get(position).getUserImage().equalsIgnoreCase("")) {
                holder.imageViewAthelets.setImageDrawable(getResources().getDrawable(R.drawable.logo_f));
            } else {
                Glide.with(context).load(
                        WebServices.BASE_URL_FOR_IMAGES + AthleteDataLocal.get(position).getUserImage()).error(getResources().getDrawable(R.drawable.logo_f))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageViewAthelets);
            }
        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = AthleteDataLocal.size();
            } catch (Exception v) {
            }
            return count;
        }

        public void filter(String text) {
            text = text.trim().toLowerCase();
            Log.e(VolleyLog.TAG, "filter: " + text);

            if (text.length() == 0) {
                AthleteDataLocal = new ArrayList<>();
                AthleteDataLocal = AthleteData;
            } else {
                AthleteDataLocal = new ArrayList<>();
                for (int i = 0; i < AthleteData.size(); i++) {
                    String name = "";
                    name = UtilityClass.getNameAthlete(AthleteData.get(i).getFirstName(), AthleteData.get(i).getLastName(), AthleteData.get(i).getEmailId());
                    if (name.trim().toLowerCase().contains(text.toLowerCase())) {
                        AthleteDataLocal.add(AthleteData.get(i));
                    }
                }
            }
            notifyDataSetChanged();
        }

        public class GridViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewTeamName, textViewPresentAbsent;
            private ImageView imageViewPresentAbsent, imageViewAthelets, imageViewAtheletsx, imgAtheletsFrame, imageViewDeleteAthelets, AtlevelCoachScreenDialog;
            private RelativeLayout rLayoutMain, rLAyoutForAthleteAttendance, rLayoutForGalleryImage, messageCoach;

            public GridViewHolder(@NonNull View convertView) {
                super(convertView);
                textViewTeamName = convertView.findViewById(R.id.textViewAthleteName);
                textViewTeamName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                textViewPresentAbsent = convertView.findViewById(R.id.textViewPresentAbsent);
                textViewPresentAbsent.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                imageViewPresentAbsent = convertView.findViewById(R.id.imageViewPresentAbsent);

                // imgAtheletsFrame = (ImageView) convertView.findViewById(R.id.imageViewAtheletsFrame);

                imageViewAthelets = convertView.findViewById(R.id.imageViewAthelets);
                imageViewAthelets.setImageResource(R.drawable.logo);
                imageViewAthelets.setDrawingCacheEnabled(true);

                imageViewDeleteAthelets = convertView.findViewById(R.id.imageViewDeleteAthelets);
                imageViewDeleteAthelets.setVisibility(View.GONE);

                rLayoutMain = convertView.findViewById(R.id.rLayoutMain);
                rLayoutForGalleryImage = convertView.findViewById(R.id.rLayoutForGalleryImage);

                rLAyoutForAthleteAttendance = convertView.findViewById(R.id.rLAyoutForAthleteAttendance);
                messageCoach = convertView.findViewById(R.id.messageCoach);
                AtlevelCoachScreenDialog = convertView.findViewById(R.id.AtlevelCoachScreenDialog);

            }
        }
    }

//
//    public void showDialogmn(Context context, int x, int y) {
//        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View mView = mInflater.inflate(R.layout.empty_array, null, false);
//        PopupWindow popUp = new PopupWindow(mView, dpToPx(350), dpToPx(250), false);
//        popUp.setTouchable(true);
//        popUp.setFocusable(true);
//        popUp.setOutsideTouchable(true);
//        popUp.setAnimationStyle(R.style.Animation);
//        popUp.showAtLocation(mView, Gravity.NO_GRAVITY, x, y + 30);
//
//        EditText calc_txt_Prise = mView.findViewById(R.id.calc_txt_Prise);
//        ImageView calc_clear_txt_Prise = mView.findViewById(R.id.calc_clear_txt_Prise);
//        RecyclerView recyclerView = mView.findViewById(R.id.custompopwindow);
//        RecyclerView ExerciseRecyclerByType = mView.findViewById(R.id.ExerciseRecyclerByType);
//        ImageView backArrowEx = mView.findViewById(R.id.backArrowEx);
//        LinearLayout DialogMainRly = mView.findViewById(R.id.DialogMainRly);
//
//        TextView HeaderName = mView.findViewById(R.id.HeaderName);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//
//        HeaderName.setText("ADD ATHLETE");
//        Boolean performAnalyticListB = false;
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
//                    athleteLevelView.SearchAthlete(text);
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
//
//    }

    public class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
        TextView LevelText, AtheleteLevelExerciseName, textViewExerciseName, textViewExerciseDate,
                AtheleteExerciseValueMultiple, MultiplyValueInc, MultiplyValueDcr;
        ImageView LevelImage, arrow1, rightSign;
        RelativeLayout LevelLayout, rAthleteLevelExercise, rLayoutForAthleteTraining;

        EditText AtheleteLevelExerciseValuesEditText;

        public RecyclerViewHolder2(@NonNull View itemView) {
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

        }
    }

    public class AthleteLevelView extends RecyclerView.Adapter<RecyclerViewHolder2> {
        int position;
        String Event;
        List<SelectedAthleteDEtail> athleteDEtailsList = new ArrayList<>(ALL_ATHLETE_LIST);

        public AthleteLevelView(int position, String Event) {
            this.position = position;
            this.Event = Event;
        }

        @Override
        public RecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.three_item_layout, viewGroup, false);
            return new RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder2 Holder, int i) {
            Holder.LevelText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            if (showDialogOf.equalsIgnoreCase("LEVEL")) {
                EvenText.setText("ATHLETE LEVEL");

                Holder.LevelText.setTextColor(getResources().getColor(R.color.color_white));
                Holder.LevelImage.setColorFilter(Color.rgb(255, 255, 255));
                Holder.LevelText.setText(AthleteData.get(position).getAthleteLevel().get(i).getAthleteLevel());
                if (SelectedLevel.equalsIgnoreCase(AthleteData.get(position).getAthleteLevel().get(i).getAthleteLevel())) {
                    Holder.LevelImage.setColorFilter(Color.rgb(237, 187, 87));
                    Holder.LevelText.setTextColor(Color.rgb(237, 187, 87));
                }

                Glide.with(context).load(BASE_URL_FOR_IMAGES_ASSETS + AthleteData.get(position).getAthleteLevel().get(i).getAthleteLevelImage()).error(getResources().getDrawable(R.drawable.logo_f)).into(Holder.LevelImage);
                Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EvenText.setText(AthleteData.get(position).getAthleteLevel().get(i).getAthleteLevel());
                        //slideDown(view);
                        dialogBoxRecyclerView.setVisibility(View.GONE);
                        dialogBoxRecyclerData = AlertBoxView.findViewById(R.id.dialogBoxRecyclerData);
                        dialogBoxRecyclerData.setVisibility(View.VISIBLE);
                        dialogBoxRecyclerData.setHasFixedSize(true);
                        dialogBoxRecyclerData.setLayoutManager(new LinearLayoutManager(context));
                        DividerItemDecoration divider = new
                                DividerItemDecoration(dialogBoxRecyclerView.getContext(),
                                DividerItemDecoration.VERTICAL);
                        divider.setDrawable(
                                ContextCompat.getDrawable(context, R.drawable.line_divider)
                        );
                        dialogBoxRecyclerData.addItemDecoration(divider);

                        dialogBoxRecyclerData.setAdapter(new AthleteLevelData(i, context, position));
                        Holder.LevelLayout.setVisibility(View.GONE);
                        Holder.rAthleteLevelExercise.setVisibility(View.VISIBLE);
                        backEventDialog.setVisibility(View.VISIBLE);
                        SaveEventDialog.setVisibility(View.VISIBLE);
                    }
                });
                backEventDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EvenText.setText("ATHLETE LEVEL");
                        backEventDialog.setVisibility(View.GONE);
                        SaveEventDialog.setVisibility(View.GONE);
                        Holder.rAthleteLevelExercise.setVisibility(View.GONE);
                        dialogBoxRecyclerData.setVisibility(View.GONE);
                        dialogBoxRecyclerView.setAdapter(new AthleteLevelView(0, ""));

                        Holder.itemView.animate()
                                .translationYBy(0f)
                                .translationX(0f)
                                .setDuration(500)
                                .setStartDelay(50)
                                .setListener(new AnimatorListenerAdapter() {

                                    @Override
                                    public void onAnimationStart(final Animator animation) {
                                        dialogBoxRecyclerView.setVisibility(View.VISIBLE);
                                        Holder.LevelLayout.setVisibility(View.VISIBLE);

                                    }
                                })
                                .start();
                    }
                });


            } else if (showDialogOf.equalsIgnoreCase("Classes")) {
                if (Event.equalsIgnoreCase("Timing")) {
                    Holder.LevelText.setVisibility(View.VISIBLE);
                    Holder.LevelImage.setVisibility(View.GONE);
                    try {

                        for (int vc = 0; vc < arrayofClasses.get(0).getOptions().size(); vc++) {
                            if (Integer.parseInt(arrayofClasses.get(0).getOptions().get(i).getId()) == Integer.parseInt(arrayList_coach_class_timing_id.get(vc))) {
                                Holder.rightSign.setVisibility(View.VISIBLE);
                            }
                        }
                    } catch (Exception m) {

                    }

                    Holder.LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));

                    Holder.LevelText.setText(arrayofClasses.get(0).getOptions().get(i).getFrom() + " - " + arrayofClasses.get(0).getOptions().get(i).getTo());

                    Holder.arrow1.setVisibility(View.GONE);

                    Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (Holder.rightSign.getVisibility() == VISIBLE) {
                                Holder.rightSign.setVisibility(View.GONE);
                                String GetID = arrayofClasses.get(0).getOptions().get(i).getId();
                                arrayList_coach_class_timing_id.remove(GetID);
                            } else {
                                Holder.rightSign.setVisibility(View.VISIBLE);
                                String GetID = arrayofClasses.get(0).getOptions().get(i).getId();
                                arrayList_coach_class_timing_id.add(GetID);
                            }
                        }
                    });
                    SaveEventDialog.setOnClickListener(view -> {

                        StringBuilder stringBuilder = new StringBuilder();
                        coach_class_timing_id = "";
                        for (int x = 0; x < arrayList_coach_class_timing_id.size(); x++) {
                            coach_class_timing_id = stringBuilder.append(arrayList_coach_class_timing_id.get(x) + ",").toString();
                        }

                        if (coach_class_timing_id != null && coach_class_timing_id.length() > 0 && coach_class_timing_id.charAt(coach_class_timing_id.length() - 1) == ',') {
                            coach_class_timing_id = coach_class_timing_id.substring(0, coach_class_timing_id.length() - 1);
                        }

                        whichApiCalled = "team";
                        WebServices webServices = new WebServices();
                        ////UtilityClass.showWaitDialog(new Dialog(context),context);
                        //UtilityClass.showWaitDialog(new Dialog(context),context);
                        webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
                        dialog.dismiss();
                    });

                    backEventDialog.setOnClickListener(view -> {
                        lLayoutForFIlterOption.setVisibility(View.VISIBLE);
                        TypeOfFilter.setText("Select Filter");
                        dialogBoxRecyclerView.setVisibility(View.GONE);
                        //dialogBoxRecyclerView.setAdapter(new AthleteLevelView(position,"Sport"));
                        SaveEventDialog.setVisibility(View.GONE);
                        backEventDialog.setVisibility(View.GONE);
                    });

                } else if (Event.equalsIgnoreCase("School")) {
                    Holder.LevelText.setVisibility(View.VISIBLE);
                    Holder.LevelImage.setVisibility(View.GONE);

                    Holder.LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));

                    Holder.LevelText.setText(getSchoolsList.get(i).getSchoolName());
                    Holder.arrow1.setVisibility(View.GONE);

                    Log.e(VolleyLog.TAG, "onBindViewHolder: " + arrayList.size());
                    for (int vc = 0; vc < arrayListSchool.size(); vc++) {
                        if (Integer.parseInt(getSchoolsList.get(i).getSchoolId()) == Integer.parseInt(arrayListSchool.get(vc))) {
                            Holder.rightSign.setVisibility(View.VISIBLE);
                        }
                    }
                    SaveEventDialog.setOnClickListener(view -> {
                        StringBuilder stringBuilder = new StringBuilder();
                        Sports_ID = "";
                        for (int x = 0; x < arrayListSchool.size(); x++) {
                            SchoolIDs = stringBuilder.append(arrayListSchool.get(x) + ",").toString();
                        }

                        if (SchoolIDs != null && SchoolIDs.length() > 0 && SchoolIDs.charAt(SchoolIDs.length() - 1) == ',') {
                            SchoolIDs = SchoolIDs.substring(0, SchoolIDs.length() - 1);
                        }
                        whichApiCalled = "team";
                        WebServices webServices = new WebServices();
                        webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
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
                                }
                            } else {
                                Holder.rightSign.setVisibility(View.VISIBLE);
                                arrayListSchool.add(getSchoolsList.get(i).getSchoolId());
                            }
                            notifyDataSetChanged();
                            Log.e(VolleyLog.TAG, "onClick: " + arrayListSchool.size());
                        }
                    });
                    backEventDialog.setOnClickListener(view -> {
                        lLayoutForFIlterOption.setVisibility(View.VISIBLE);
                        TypeOfFilter.setText("Select Filter");
                        dialogBoxRecyclerView.setVisibility(View.GONE);
                        //dialogBoxRecyclerView.setAdapter(new AthleteLevelView(position,"Sport"));
                        SaveEventDialog.setVisibility(View.GONE);
                        backEventDialog.setVisibility(View.GONE);
                    });
                } else {
                    Holder.LevelText.setVisibility(View.VISIBLE);
                    Holder.LevelImage.setVisibility(View.GONE);

                    Holder.LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));

                    Holder.LevelText.setText(sportsArray[i]);
                    Holder.arrow1.setVisibility(View.GONE);

                    Log.e(VolleyLog.TAG, "onBindViewHolder: " + arrayList.size());
                    for (int vc = 0; vc < arrayList.size(); vc++) {
                        Log.e(VolleyLog.TAG, "sportsIdArray[vc]: " + sportsIdArray[vc]);
                        if (Integer.parseInt(sportsIdArray[i]) == Integer.parseInt(arrayList.get(vc))) {
                            Holder.rightSign.setVisibility(View.VISIBLE);
                        }
                    }
                    SaveEventDialog.setOnClickListener(view -> {
                        StringBuilder stringBuilder = new StringBuilder();
                        Sports_ID = "";
                        for (int x = 0; x < arrayList.size(); x++) {
                            Log.e(VolleyLog.TAG, "onBindViewHolder: " + arrayList.get(x));
                            Sports_ID = stringBuilder.append(arrayList.get(x) + ",").toString();
                        }

                        if (Sports_ID != null && Sports_ID.length() > 0 && Sports_ID.charAt(Sports_ID.length() - 1) == ',') {
                            Sports_ID = Sports_ID.substring(0, Sports_ID.length() - 1);
                        }
                        Log.e(VolleyLog.TAG, "onBindViewHolder: " + Sports_ID);

                        whichApiCalled = "team";
                        WebServices webServices = new WebServices();
                        webServices.getTeams(userId, Sports_ID, coach_class_timing_id, SchoolIDs, context, CoachBoardFragments.this);
                        dialog.dismiss();
                    });

                    Holder.LevelLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (Holder.rightSign.getVisibility() == VISIBLE) {
                                Holder.rightSign.setVisibility(View.GONE);
                                String GetID = sportsIdArray[i];

                                arrayList.remove(GetID);
                            } else {
                                Holder.rightSign.setVisibility(View.VISIBLE);
                                String GetID = sportsIdArray[i];
                                arrayList.add(GetID);
                            }
                            Log.e(VolleyLog.TAG, "onClick: " + arrayList.size());
                        }
                    });
                    backEventDialog.setOnClickListener(view -> {
                        lLayoutForFIlterOption.setVisibility(View.VISIBLE);
                        TypeOfFilter.setText("Select Filter");
                        dialogBoxRecyclerView.setVisibility(View.GONE);
                        //dialogBoxRecyclerView.setAdapter(new AthleteLevelView(position,"Sport"));
                        SaveEventDialog.setVisibility(View.GONE);
                        backEventDialog.setVisibility(View.GONE);
                    });
                }
            } else if (showDialogOf.equalsIgnoreCase("AddAthlete")) {
                Holder.LevelText.setTextColor(getResources().getColor(R.color.color_white));
                Holder.LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));
                Holder.LevelImage.setColorFilter(Color.rgb(255, 255, 255));
                Holder.LevelImage.setVisibility(GONE);


                if (athleteDEtailsList.get(i).getFirstName().equalsIgnoreCase("") && athleteDEtailsList.get(i).getLastName().equalsIgnoreCase("")) {
                    Holder.LevelText.setText(UtilityClass.getNameAthlete(athleteDEtailsList.get(i).getFirstName(), athleteDEtailsList.get(i).getLastName(), athleteDEtailsList.get(i).getEmailId()));
                } else {
                    Holder.LevelText.setText(athleteDEtailsList.get(i).getLastName() + " " + athleteDEtailsList.get(i).getFirstName());
                }

                Holder.rightSign.setVisibility(View.GONE);

                SELECTED_ATHLETE_LIST.forEach(m -> {
                    if (m.getUserId().equalsIgnoreCase(athleteDEtailsList.get(i).getUserId())) {
                        Holder.rightSign.setVisibility(VISIBLE);
                    }
                });


                Glide.with(context).load(BASE_URL_FOR_IMAGES + athleteDEtailsList.get(i).getUserImage()).error(getResources().getDrawable(R.drawable.logo_f)).into(Holder.LevelImage);
                Holder.LevelLayout.setOnClickListener(view1 -> {
                    SELECTED_ATHLETE_LIST.add(athleteDEtailsList.get(i));
                    notifyDataSetChanged();
                });

            }

//            if(showDialogOf.equalsIgnoreCase("training")){
//                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//                Holder.rLayoutForAthleteTraining.setVisibility(View.VISIBLE);
//
//                Holder.textViewExerciseName.setText(GetTeam.get(ActiveId).getTrainingProgramDetail().get(i).getProgramName());
//                Holder.textViewExerciseDate.setText(GetTeam.get(ActiveId).getTrainingProgramDetail().get(i).getStartDate());
//
//                Holder.textViewExerciseName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//                Holder.textViewExerciseDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//                Holder.rLayoutForAthleteTraining.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                        //gotoscreenNext(i);
//                    }
//                });
//            }
        }

        @Override
        public int getItemCount() {
            int countofArray = 0;
            if (showDialogOf.equalsIgnoreCase("Classes")) {
                if (Event.equalsIgnoreCase("Timing")) {
                    try {
                        countofArray = arrayofClasses.get(0).getOptions().size();
                    } catch (Exception v) {
                        countofArray = 0;
                    }

                } else if (Event.equalsIgnoreCase("School")) {
                    try {
                        countofArray = getSchoolsList.size();
                    } catch (Exception v) {
                        countofArray = 0;
                    }

                } else {
                    if (sportsArray != null) {
                        countofArray = sportsArray.length;
                    } else {
                        countofArray = 0;
                    }

                }
            } else if (showDialogOf.equalsIgnoreCase("training")) {
                try {
                    countofArray = GetTeamOfCoachBoard.get(ActiveId).getTrainingProgramDetail().size();
                } catch (Exception v) {
                    countofArray = 0;
                }
            } else if (showDialogOf.equalsIgnoreCase("LEVEL")) {
                countofArray = AthleteData.get(position).getAthleteLevel().size();
            } else if (showDialogOf.equalsIgnoreCase("AddAthlete")) {
                countofArray = athleteDEtailsList.size();
            } else {
                countofArray = 0;
            }

            return countofArray;
        }

        private void SearchAthlete(String text) {
            text = text.trim().toLowerCase();
            if (text.length() == 0) {
                athleteDEtailsList = new ArrayList<>();
                athleteDEtailsList = ALL_ATHLETE_LIST;
            } else {
                athleteDEtailsList = new ArrayList<>();
                for (int i = 0; i < ALL_ATHLETE_LIST.size(); i++) {
                    String name = "";
                    name = UtilityClass.getNameAthlete(ALL_ATHLETE_LIST.get(i).getFirstName(), ALL_ATHLETE_LIST.get(i).getLastName(), ALL_ATHLETE_LIST.get(i).getEmailId());
                    if (name.trim().toLowerCase().contains(text.toLowerCase())) {
                        athleteDEtailsList.add(ALL_ATHLETE_LIST.get(i));
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    private class AthleteLevelData extends RecyclerView.Adapter<RecyclerViewHolder2> {
        int Y;
        Context context;
        int position;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        float weightOf = 0f;
        DecimalFormat twoDFormx = new DecimalFormat("#");

        public AthleteLevelData(int Y, Context context, int position) {
            this.Y = Y;
            this.context = context;
            this.position = position;
        }

        @Override
        public RecyclerViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.three_item_layout, viewGroup, false);
            return new RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder2 Holder, int i) {
            weightOf = AthleteData.get(position).getWeight().equalsIgnoreCase("") ? 0f : Float.parseFloat(AthleteData.get(position).getWeight());
            try {
                weightOf = Float.parseFloat(twoDFormx.format(weightOf));
            } catch (Exception v) {

            }
            Log.e(VolleyLog.TAG, "AthleteLevelData: " + UtilityClass.getNameAthlete(AthleteData.get(position).getFirstName(), AthleteData.get(position).getLastName(), AthleteData.get(position).getEmailId()));
            Holder.LevelLayout.setVisibility(View.GONE);

            Holder.itemView.animate()
                    .translationYBy(0f)
                    .setDuration(500)
                    .setStartDelay(50)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(final Animator animation) {
                            Holder.rAthleteLevelExercise.setVisibility(View.VISIBLE);
                            backEventDialog.setVisibility(View.VISIBLE);
                            SaveEventDialog.setVisibility(View.VISIBLE);
                        }
                    })
                    .start();


            Log.e(VolleyLog.TAG, "getWeight: " + AthleteData.get(position).getWeight());

            //float weightOf = AthleteData.get(position).getWeight().equalsIgnoreCase("") ?0f:Float.parseFloat(AthleteData.get(position).getWeight());

            float ExerciseValues = (weightOf * Float.parseFloat(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getMultiple()));

            Log.e(VolleyLog.TAG, "onBindViewHolder: " + ExerciseValues + "  " + weightOf + "  " + Float.parseFloat(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getMultiple()));

            Holder.AtheleteLevelExerciseValuesEditText.setText(twoDFormx.format(ExerciseValues));


            backEventDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EvenText.setText("ATHLETE LEVEL");
                    backEventDialog.setVisibility(View.GONE);
                    SaveEventDialog.setVisibility(View.GONE);
                    Holder.rAthleteLevelExercise.setVisibility(View.GONE);
                    dialogBoxRecyclerData.setVisibility(View.GONE);
                    dialogBoxRecyclerView.setAdapter(new AthleteLevelView(0, ""));

                    Holder.itemView.animate()
                            .translationYBy(0f)
                            .translationX(0f)
                            .setDuration(500)
                            .setStartDelay(50)
                            .setListener(new AnimatorListenerAdapter() {

                                @Override
                                public void onAnimationStart(final Animator animation) {
                                    dialogBoxRecyclerView.setVisibility(View.VISIBLE);
                                    Holder.LevelLayout.setVisibility(View.VISIBLE);
                                }
                            })
                            .start();
                }
            });


            SaveEventDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    webServices = new WebServices();
                    String select_level = AthleteData.get(position).getAthleteLevel().get(Y).getId();
                    SelectedLevel = AthleteData.get(position).getAthleteLevel().get(Y).getAthleteLevel();
                    webServices.updateAthleteLevel(AthleteID, CoachID, select_level, context, CoachBoardFragments.this);
                }
            });


            Holder.MultiplyValueInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String ID = "";
                    String row_no = "";
                    WebServices webServices = new WebServices();
                    whichApiCalled = "updateAthleteDetailsINC";
                    String AthleteID = "";
                    float val = Float.parseFloat(Holder.AtheleteLevelExerciseValuesEditText.getText().toString());
                    val = val + 5;
                    Holder.AtheleteLevelExerciseValuesEditText.setText(val + "");
                    float ExerciseValues = 0;
                    AthleteID = AthleteData.get(position).getUserId();
                    row_no = AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getRowNo();
                    ID = AthleteData.get(position).getAthleteLevel().get(Y).getId();
                    weightOf = Float.parseFloat(twoDForm.format(weightOf));
                    ExerciseValues = val / weightOf; //(((weightOf) / Float.parseFloat(val + "")) * 100);
                    ExerciseValues = Float.parseFloat(twoDForm.format(ExerciseValues));
                    AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                    AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                    Holder.AtheleteExerciseValueMultiple.setText(twoDForm.format(ExerciseValues));
                    webServices.updateMultiplierData(context, CoachBoardFragments.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);
                }
            });


            Holder.LevelLayout.setVisibility(GONE);


            Holder.AtheleteLevelExerciseValuesEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId,
                                              KeyEvent keyEvent) { //triggered when done editing (as clicked done on keyboard)
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        textView.clearFocus();
//                        CallApiOfLevel(Holder, Y, i, position, weightOf);
                        try {
                            String ID = "";
                            String row_no = "";
                            WebServices webServices = new WebServices();
                            whichApiCalled = "updateAthleteDetailsDCRs";
                            DecimalFormat twoDForm = new DecimalFormat("#.##");
                            DecimalFormat twoDFormx = new DecimalFormat("#");
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
                                AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                            } else {
                                LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                                LoginJson.get(0).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");
                            }

                            Holder.AtheleteExerciseValueMultiple.setText(ExerciseValues + "");

                            webServices.updateMultiplierData(context, CoachBoardFragments.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);

                        } catch (Exception m) {
                        }
                        notifyDataSetChanged();
                    }
                    return false;
                }
            });


            Holder.AtheleteLevelExerciseName.setText(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getPerantTypeName());
            //Holder.AtheleteLevelExerciseValues.setText(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getBaseValue());
            Holder.AtheleteExerciseValueMultiple.setText(twoDForm.format(Float.parseFloat(AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getMultiple())));


            Holder.MultiplyValueInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String ID = "";
                        String row_no = "";
                        WebServices webServices = new WebServices();
                        whichApiCalled = "updateAthleteDetailsINC";
                        String AthleteID = "";
                        float val = Float.parseFloat(Holder.AtheleteLevelExerciseValuesEditText.getText().toString());
                        val = val + 5;


                        float ExerciseValues = 0;


                        AthleteID = AthleteData.get(position).getUserId();

                        row_no = AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getRowNo();

                        ID = AthleteData.get(position).getAthleteLevel().get(Y).getId();

                        Log.e(VolleyLog.TAG, "onClick: " + val + "  " + weightOf);
                        weightOf = Float.parseFloat(twoDForm.format(weightOf));

                        ExerciseValues = val / weightOf; //(((weightOf) / Float.parseFloat(val + "")) * 100);


                        ExerciseValues = Float.parseFloat(twoDForm.format(ExerciseValues));


                        AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");
                        AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");


                        Holder.AtheleteExerciseValueMultiple.setText(twoDForm.format(ExerciseValues));

                        Log.e(VolleyLog.TAG, "onClick: " + weightOf);
                        Holder.AtheleteLevelExerciseValuesEditText.setText(twoDFormx.format(val));
                        webServices.updateMultiplierData(context, CoachBoardFragments.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);
                    } catch (Exception m) {
                    }
                }
            });


            Holder.MultiplyValueDcr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String ID = "";
                        String row_no = "";
                        WebServices webServices = new WebServices();
                        whichApiCalled = "updateAthleteDetailsDCRs";
                        DecimalFormat twoDForm = new DecimalFormat("#.##");
                        String AthleteID = "";

                        float val = Float.parseFloat(Holder.AtheleteLevelExerciseValuesEditText.getText().toString());
                        val = val - 5;
                        if (val <= 0) {
                            return;
                        }

                        float ExerciseValues = 0;


                        AthleteID = AthleteData.get(position).getUserId();

                        row_no = AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).getRowNo();

                        ID = AthleteData.get(position).getAthleteLevel().get(Y).getId();

                        ExerciseValues = val / weightOf; //(((weightOf) / Float.parseFloat(val + "")) * 100);

                        ExerciseValues = Float.parseFloat(twoDForm.format(ExerciseValues));

                        AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setMultiple(ExerciseValues + "");

                        AthleteData.get(position).getAthleteLevel().get(Y).getValues().get(i).setBaseValue(val + "");

                        Holder.AtheleteExerciseValueMultiple.setText(twoDForm.format(ExerciseValues));
                        Holder.AtheleteLevelExerciseValuesEditText.setText(twoDFormx.format(val));
                        webServices.updateMultiplierData(context, CoachBoardFragments.this, AthleteID, CoachID, row_no, Holder.AtheleteExerciseValueMultiple.getText().toString(), ID);
                    } catch (Exception m) {
                    }
                }
            });


        }

        @Override
        public int getItemCount() {

            int itemCount = 0;
            try {
                itemCount = AthleteData.get(position).getAthleteLevel().get(Y).getValues().size();
            } catch (Exception x) {
                SaveEventDialog.setVisibility(View.INVISIBLE);
            }
            return itemCount;
        }
    }

    private class TeamData extends RecyclerView.Adapter<CircleOvelLongViewHolder> {
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
            if (GetTeamOfCoachBoard.get(i).getTeamId().equalsIgnoreCase("0")) {
                holder.teamName.setText(GetTeamOfCoachBoard.get(i).getTeamName());
            } else {
                holder.teamName.setText(GetTeamOfCoachBoard.get(i).getTeamName());
            }
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
                    CopyPaste(context, "Day", "", view, i);
                } else {
                    try {
                        ActiveId = i;
                        teamId = GetTeamOfCoachBoard.get(i).getTeamId();
                        getAthleteDataFromServer();
                        notifyDataSetChanged();
                    } catch (Exception b) {

                    }
                }

            });

            holder.rLayoutofTeam.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    CopyPaste(context, "Day", "", view, i);
                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            int itemCount = 0;
            try {
                itemCount = GetTeamOfCoachBoard.size();
            } catch (Exception x) {
                SaveEventDialog.setVisibility(View.INVISIBLE);
            }
            return itemCount;
        }


    }

    private class WizardPagerAdapter extends PagerAdapter {

        public Object instantiateItem(ViewGroup collection, int position) {

            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.page_one;
                    break;

                case 1:
                    resId = R.id.rLayoutForSwipeViews;
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

    public class DragExperimentTouchListener implements View.OnTouchListener {

        boolean isDragging = false;
        float lastX;
        float lastY;
        float deltaX;

        public DragExperimentTouchListener(float initalX, float initialY) {
            lastX = initalX;
            lastY = initialY;
        }

        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {

            arg0.getParent().requestDisallowInterceptTouchEvent(true);
            int action = arg1.getAction();

            if (action == MotionEvent.ACTION_DOWN && !isDragging) {
                isDragging = true;
                deltaX = arg1.getX();
                return true;
            } else if (isDragging) {
                if (action == MotionEvent.ACTION_MOVE) {
                    arg0.setX(arg0.getX() + arg1.getX() - deltaX);
                    arg0.setY(arg0.getY());
                    return true;
                } else if (action == MotionEvent.ACTION_UP) {
                    isDragging = false;
                    lastX = arg1.getX();
                    lastY = arg1.getY();
                    return true;
                } else if (action == MotionEvent.ACTION_CANCEL) {
                    arg0.setX(lastX);
                    arg0.setY(lastY);
                    isDragging = false;
                    return true;
                }
            }
            return false;
        }
    }

}