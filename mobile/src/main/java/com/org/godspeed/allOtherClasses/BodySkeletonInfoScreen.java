package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleRelativeLayout;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.GetInjury.GetInjury;
import com.org.godspeed.response_JsonS.GetInjury.InjuryNotesDetail;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.fragments.CoachBoardFragments.AthleteData;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.utility.GlobalClass.ConvertImperialToMetrics;
import static com.org.godspeed.utility.GlobalClass.ConvertMetricsToImperial;
import static com.org.godspeed.utility.UtilityClass.TODAY_START_UTC_TIME;
import static com.org.godspeed.utility.UtilityClass.getFormattedTime;
import static com.org.godspeed.utility.UtilityClass.getNextMonthFirstDate;
import static com.org.godspeed.utility.UtilityClass.getPreviosMonthFirstDate;
import static com.org.godspeed.utility.UtilityClass.hide;

public class BodySkeletonInfoScreen extends Activity implements GodSpeedInterface {

    public static boolean isCalanderOptionNeedToShow = false;
    public static boolean isAutoCloseScreenAfterTimerComplete = false;
    private static int year;
    private static int month;
    private static int day;
    private static TextView TextMale, TextFemale, textViewDate;
    private static RelativeLayout rLayoutForFullScreenBodyPart;
    Calendar calendar = Calendar.getInstance();
    LinearLayout SettingLLayout, rLayoutMatric, llayoutText, lLayoutImperial;
    DatePickerDialog picker;
    RecyclerView dialogBoxRecyclerView;
    Gson gson;
    int IndexOfGender = 0;
    Map<String, Object> retMap;
    JSONArray jsonArrayForChild;
    int ListIndex = 0;
    JSONArray mainarray;
    String WebviewLink = "";
    Transition transition = new Slide(Gravity.BOTTOM);
    int NotesPosition = 0;
    String NotesText = "";
    JSONArray jsonArrayOfFirstPage;
    SkeletonProfile skeletonProfile;
    String whichApiCalled = "";
    ImageView arrowRLeftCalender, arrowRightCalender;
    private Context context;
    private Typeface fontFaceBoldAgency, fontFaceRegularAgency;
    private List<GetInjury> getInjuries;
    private ImageView imageViewBackArrow, imageViewBody, imageViewCloseView, imageViewCloseInjuryNotes;
    private RelativeLayout rLayoutForViewProfile;
    private RelativeLayout rLayoutForEditProfile;
    private RelativeLayout rLayoutForAddInjury;
    private RelativeLayout rLayoutButton;
    private LinearLayout lLayoutForViewEditInjury, lLayoutForViewInjuryNotes, rLAyoutForCalendarDialog, MainLayoutForAll;
    private TextView textViewReportedOn, textViewRecovered, textViewScreenName, textViewBodyCompositionHistory, textViewWeightLabel, textViewWeightValue1, textViewWeightValue2;
    private TextView textViewSMMLabel, textViewSMMValue1, textViewSMMValue2, textViewPBFLabel, textViewPBFValue1, textViewPBFValue2, textViewECWLabel, textViewECWValue1, textViewECWValue2;
    private TextView textViewInjuris, textViewViewProfile, textViewEditProfile, textViewPrintProfile, textViewMuscular, textViewBone, textViewCalenderMonthFullScreen;
    private TextView textViewInjuryTitle, textViewAddNewInjuryTitle, textViewInjuryPart, textViewInjuryName, textViewInjuryType, textViewInjuryDetails;
    private RecyclerView listViewInjuryNameAndDetails, RecyclerviewForNotes;
    //private ListViewAdapter adapter;
    private boolean isViewInjury = false;
    private ImageView imageViewCalender, FullScreen, Sekeleton2, Sekeleton;
    private ImageView imageViewMuscular, imageViewBone, imageViewAddInjuryNotes;
    private Calendar cal;
    private String nameOfMonth = "";
    private int daysInMonth = 0, currentDate = 0;
    private SimpleDateFormat dateFormate = new SimpleDateFormat("MMM. yyyy");
    private SimpleDateFormat dateFormateX = new SimpleDateFormat("dd-MMM-yyyy");
    private WebView webView;
    //private Dialog dialog;
    private TextView EvenText;
    private LinearLayout rMainDialogLayout;
    private ImageView backEventDialog;
    private boolean Muscular = true;
    private boolean Bone = false;
    private WebViewClient client;
    private WebServices webServices;
    private EditText EdittextSearchMuscular;
    private View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageViewBackArrow:
                    onBackPressed();
                    break;
                case R.id.rLayoutForViewProfile:
                    closeAllLayout();
                    isViewInjury = true;
                    break;
                case R.id.rLayoutForEditProfile:
                    rLayoutForFullScreenBodyPart.setVisibility(VISIBLE);
                    lLayoutForViewEditInjury.setVisibility(VISIBLE);
                    isViewInjury = false;
                    break;
                case R.id.imageViewCloseView:
                    closeAllLayout();
                    break;
                case R.id.imageViewCloseInjuryNotes:
                    lLayoutForViewInjuryNotes.setVisibility(GONE);
                    break;
            }
        }
    };
    private GetInjuriesProfile adapter;
    private GetInjuriesNotes adapterNotes;
    private String NoteString;
    private int PositionForAddNotes;
    private long mCurrentStartTime;
    private boolean coach;
    private View dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_body_skeleton);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        context = this;

        mCurrentStartTime = TODAY_START_UTC_TIME;
        textViewDate = findViewById(R.id.textViewDate);

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        gson = new Gson();
        webServices = new WebServices();
        webView = findViewById(R.id.webView);
        imageViewAddInjuryNotes = findViewById(R.id.imageViewAddInjuryNotes);
        webView.setHapticFeedbackEnabled(false);
        whichApiCalled = "getInjuries";
        String USERID = (GlobalClass.ivar1 == 1 ? AthleteData.get(ProfileScreenActivity.position).getUserId() : LoginScreen.userId);
        webServices.getInjuries(USERID, context, BodySkeletonInfoScreen.this);


        isViewInjury = false;
        fontFaceRegularAgency = CustomTypeface.load_AGENCYR_Fonts(context);
        fontFaceBoldAgency = CustomTypeface.load_AGENCYB_Fonts(context);

        int usertype = GlobalClass.ivar1;

        if (usertype == 1) {
            coach = true;
        }


        getInjuries = new ArrayList<>();
        List<GetInjuriesNotes> getInjuriesNotesx = new ArrayList<>();
        //textViewRecovered = (TextView) findViewById(R.id.textViewRecovered);
        TextMale = findViewById(R.id.TextM);
        arrowRLeftCalender = findViewById(R.id.arrowRLeftCalender);
        arrowRightCalender = findViewById(R.id.arrowRightCalender);
        TextFemale = findViewById(R.id.TextF);
        rLayoutForFullScreenBodyPart = findViewById(R.id.rLayoutForFullScreenBodyPart);
        rLayoutButton = findViewById(R.id.rLayoutButton);

        lLayoutForViewEditInjury = findViewById(R.id.lLayoutForViewEditInjury);
        lLayoutForViewInjuryNotes = findViewById(R.id.lLayoutForViewInjuryNotes);
        lLayoutImperial = findViewById(R.id.lLayoutImperial);
        rLayoutMatric = findViewById(R.id.rLayoutMatric);
        MainLayoutForAll = findViewById(R.id.MainLayoutForAll);

        // webView.loadDataWithBaseURL("", myCustomStyleString+htmlData, "text/html", "utf-8", null);
        WebviewLink = "Skelton/json-muscular/image_skelfov/musc54_new.html.json";
        //webView.setInitialScale(getScale(345));
        //webView.loadDataWithBaseURL("file:///android_asset/Skelton/innerbody/output/image_skelfov/musc54_new.html","var meta = document.createElement('meta');meta.setAttribute('name', 'viewport');meta.setAttribute('content', 'width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no');document.getElementsByTagName('head')[0].appendChild(meta);","text/html","utf-8",null);
        webView.loadUrl("file:///android_asset/Skelton/innerbody/output/image_skelfov/musc54_new.html");
        //webView.loadData("file:///android_asset/Skelton/innerbody/output/image_skelfov/musc54_new.html");


        arrowRLeftCalender.setOnClickListener(view -> {
            mCurrentStartTime = getPreviosMonthFirstDate(mCurrentStartTime);
            textViewDate.setText(getFormattedTime(mCurrentStartTime, "MMM. yyyy"));
        });
        arrowRightCalender.setOnClickListener(view -> {
            mCurrentStartTime = getNextMonthFirstDate(mCurrentStartTime);
            textViewDate.setText(getFormattedTime(mCurrentStartTime, "MMM. yyyy"));
        });

        //webView.loadDataWithBaseURL("file:///android_asset/Skelton/innerbody/output/image_skelfov/musc54_new.html","const meta = document.createElement('meta'); meta.setAttribute('content', 'width=width, initial-scale=0.5, maximum-scale=0.5, user-scalable=2.0'); meta.setAttribute('name', 'viewport'); document.getElementsByTagName('head')[0].appendChild(meta); ","text/html","utf-8",null);


        webView.getSettings().getUseWideViewPort();
        try {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
            settings.setLoadsImagesAutomatically(true);
            settings.setLightTouchEnabled(true);
            settings.setDomStorageEnabled(true);
        } catch (Exception e) {


            //Toast.makeText(context, e + "", Toast.LENGTH_SHORT).show();
        }
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(50);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {


                /* This call inject JavaScript into the page which just finished loading. */
                webView.loadUrl(
                        "javascript:(function() { " +
                                "const meta = document.createElement('meta');" +
                                " meta.setAttribute('content'," +
                                "'width=device-width,initial-scale=10, maximum-scale=10, user-scalable=no');" +
                                " meta.setAttribute('name', 'viewport');" +
                                " document.getElementsByTagName('head')[0].appendChild(meta); " +
                                "})()");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                try {

                    int o = 0;
                    String extension = url.substring(url.lastIndexOf("/") + 1);

                    int number = Integer.parseInt(extension);

                    String loadJsonForInjury = loadJSONFromAsset(context, WebviewLink);

                    JSONArray jsonArrayX;

                    jsonArrayX = new JSONArray(loadJsonForInjury);


                    AlertDialog alertDialog = null;
                    int lenghtofArray = jsonArrayX.length();


                    Log.e(VolleyLog.TAG, "shouldOverrideUrlLoading: " + jsonArrayX.getJSONObject(number).getString("name"));


                    String name = jsonArrayX.getJSONObject(number).getString("name");

//                    alertDialog = new AlertDialog.Builder(context)
//                            .setIcon(getResources().getDrawable(R.drawable.logo_main_small_alert))
//                            .setTitle(getResources().getString(R.string.app_name))
//                            .setMessage("Mark " + " \"" + name + "\"" + " injury ?")
//                            .setNegativeButton("Yes", (dialogInterface, x) -> {
//                                int usertype = GlobalClass.ivar1;
//                                String USERID = (GlobalClass.ivar1 == 1 ? AthleteData.get(ProfileScreenActivity.position).getUserId() : LoginScreen.userId);
//                                whichApiCalled = "add_injury";
//                                hide();
//                                webServices.add_injury(LoginScreen.userId, name, LoginScreen.userId, context, BodySkeletonInfoScreen.this);
//                            })
//                            .setPositiveButton("Cancel", (dialogInterface, x) -> {
//                            })
//                            .show();

                    final custom_alert_class mAlert = new custom_alert_class(context);
                    mAlert.setMessage("Mark " + " \"" + name + "\"" + " injury ?");
                    mAlert.setPositveButton("Yes", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //adapter.notifyDataSetChanged();
                            int usertype = GlobalClass.ivar1;
                            String USERID = (GlobalClass.ivar1 == 1 ? AthleteData.get(ProfileScreenActivity.position).getUserId() : LoginScreen.userId);
                            whichApiCalled = "add_injury";
                            hide();
                            webServices.add_injury(LoginScreen.userId, name, LoginScreen.userId, context, BodySkeletonInfoScreen.this);
                        }
                    });
                    mAlert.setNegativeButton("Cancel", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mAlert.dismiss();
                        }
                    });

                    mAlert.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });


        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);


        // {"id":"47","athlete_id":"178","injury_reported_on":"","injury_recovered_on":"","injury_part_name":"Gracilis Muscle","reported_by":"178","injury_notes_detail":[]}}

        rLayoutForViewProfile = findViewById(R.id.rLayoutForViewProfile);
        rLayoutForViewProfile.setOnClickListener(viewClickListener);

        rLayoutForEditProfile = findViewById(R.id.rLayoutForEditProfile);
        rLayoutForEditProfile.setOnClickListener(viewClickListener);


        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        FullScreen = findViewById(R.id.FullScreen);
        Sekeleton2 = findViewById(R.id.Sekeleton2);
        Sekeleton = findViewById(R.id.Sekeleton);
        imageViewBackArrow.setOnClickListener(viewClickListener);

        FullScreen.setOnClickListener(view -> {
            if (Sekeleton2.getVisibility() == VISIBLE) {
                Sekeleton2.setVisibility(GONE);
                MainLayoutForAll.setVisibility(VISIBLE);
                rLayoutButton.setVisibility(VISIBLE);
                //webView.setInitialScale(getScale(345));
            } else {
                Sekeleton2.setVisibility(VISIBLE);
                MainLayoutForAll.setVisibility(GONE);
                rLayoutButton.setVisibility(GONE);
                //webView.setInitialScale(getScale(210));
            }
        });
        Sekeleton2.setOnClickListener(view -> {
            int[] location = new int[2];
            Sekeleton2.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            showDialog(context, x, y, "", "su", view);
        });

        Sekeleton.setOnClickListener(view -> {
            int[] location = new int[2];
            Sekeleton.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            showDialog(context, x, y, "", "", view);
        });

//


//        imageViewBody = (ImageView) findViewById(R.id.imageViewBody);
        imageViewCloseView = findViewById(R.id.imageViewCloseView);
        imageViewCloseView.setOnClickListener(viewClickListener);

        transition.setDuration(500);
        transition.addTarget(R.id.rLayoutForFullScreenBodyPart);
        TransitionManager.beginDelayedTransition(rLayoutForFullScreenBodyPart, transition);

        rLayoutForFullScreenBodyPart.setVisibility(GONE);


        imageViewCloseInjuryNotes = findViewById(R.id.imageViewCloseInjuryNotes);
        imageViewCloseInjuryNotes.setOnClickListener(viewClickListener);

        listViewInjuryNameAndDetails = findViewById(R.id.listViewInjuryNameAndDetails);
        RecyclerviewForNotes = findViewById(R.id.RecyclerviewForNotes);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listViewInjuryNameAndDetails.setLayoutManager(mLayoutManager);

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        RecyclerviewForNotes.setLayoutManager(mLayoutManager2);

        adapter = new GetInjuriesProfile(context);

        cal = Calendar.getInstance();
        daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        nameOfMonth = month_date.format(cal.getTime());
        month_date = new SimpleDateFormat("DD");
        currentDate = Integer.parseInt(month_date.format(cal.getTime()));


        imperial();
        initializeTextView();

    }

    private void initializeTextView() {
        textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewBodyCompositionHistory = findViewById(R.id.textViewBodyCompositionHistory);
        textViewBodyCompositionHistory.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewWeightLabel = findViewById(R.id.textViewWeightLabel);
        textViewWeightLabel.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewWeightValue1 = findViewById(R.id.textViewWeightValue1);
        textViewWeightValue1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewWeightValue2 = findViewById(R.id.textViewWeightValue2);
        textViewWeightValue2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSMMLabel = findViewById(R.id.textViewSMMLabel);
        textViewSMMLabel.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSMMValue1 = findViewById(R.id.textViewSMMValue1);
        textViewSMMValue1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSMMValue2 = findViewById(R.id.textViewSMMValue2);
        textViewSMMValue2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewPBFLabel = findViewById(R.id.textViewPBFLabel);
        textViewPBFLabel.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewPBFValue1 = findViewById(R.id.textViewPBFValue1);
        textViewPBFValue1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewPBFValue2 = findViewById(R.id.textViewPBFValue2);
        textViewPBFValue2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewECWLabel = findViewById(R.id.textViewECWLabel);
        textViewECWLabel.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewECWValue1 = findViewById(R.id.textViewECWValue1);
        textViewECWValue1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewECWValue2 = findViewById(R.id.textViewECWValue2);
        textViewECWValue2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewInjuris = findViewById(R.id.textViewInjuris);
        textViewInjuris.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewViewProfile = findViewById(R.id.textViewViewProfile);
        textViewViewProfile.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewEditProfile = findViewById(R.id.textViewEditProfile);
        textViewEditProfile.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewPrintProfile = findViewById(R.id.textViewPrintProfile);
        textViewPrintProfile.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        textViewMuscular = findViewById(R.id.textViewMuscular);

        imageViewMuscular = findViewById(R.id.imageViewMuscular);
        imageViewBone = findViewById(R.id.imageViewBone);


        textViewMuscular.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewBone = findViewById(R.id.textViewBone);
        textViewBone.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        imageViewMuscular.setOnClickListener(view -> {
            Muscular = true;
            Bone = false;
            imageViewMuscular.setImageDrawable(getResources().getDrawable(R.drawable.health_profile_selected_button_icon));
            imageViewBone.setImageDrawable(getResources().getDrawable(R.drawable.health_profile_unselected_button_icon));
            textViewBone.setTextColor(getResources().getColor(R.color.color_gray_for_health_profile_button_unselected));
            textViewMuscular.setTextColor(getResources().getColor(R.color.color_black_for_health_profile_button_selected));
        });

        imageViewBone.setOnClickListener(view -> {
            Muscular = false;
            Bone = true;
            imageViewMuscular.setImageDrawable(getResources().getDrawable(R.drawable.health_profile_unselected_button_icon));
            imageViewBone.setImageDrawable(getResources().getDrawable(R.drawable.health_profile_selected_button_icon));
            textViewBone.setTextColor(getResources().getColor(R.color.color_black_for_health_profile_button_selected));
            textViewMuscular.setTextColor(getResources().getColor(R.color.color_gray_for_health_profile_button_unselected));
        });


        textViewInjuryTitle = findViewById(R.id.textViewInjuryTitle);
        textViewInjuryTitle.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

//        textViewAddNewInjuryTitle = (TextView) findViewById(R.id.textViewAddNewInjuryTitle);
//        textViewAddNewInjuryTitle.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewInjuryPart = findViewById(R.id.textViewInjuryPart);
        textViewInjuryPart.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewInjuryName = findViewById(R.id.textViewInjuryName);
        textViewInjuryName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewInjuryType = findViewById(R.id.textViewInjuryType);
        textViewInjuryType.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        String Label = "";
        if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
            Label = "(KG)";
        } else {
            Label = "(LB)";
        }


        textViewWeightLabel.setText("Weight " + Label);
        textViewSMMLabel.setText("SMM " + Label);
        textViewPBFLabel.setText("PBF " + Label);
        textViewECWLabel.setText("ECW/TBF " + Label);

        TextMale.setOnClickListener(view -> {
            imperial();
        });

        TextFemale.setOnClickListener(view -> {
            matric();
        });
        rLAyoutForCalendarDialog = findViewById(R.id.rLAyoutForCalendarDialog);


        imageViewCalender = findViewById(R.id.imageViewCalender);
        textViewDate.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        textViewDate.setText(dateFormate.format(cal.getTime()));
        imageViewCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogfragment = new DatePickerDialogClass();
                dialogfragment.show(getFragmentManager(), "Date Picker Dialog");
//                DialogFragment dialogfragment = new DatePickerDialogClass();
//                dialogfragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogfragment = new DatePickerDialogClass();
                dialogfragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });


        if (coach) {
            if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
                textViewWeightValue1.setText(ConvertImperialToMetrics(AthleteData.get(ProfileScreenActivity.position).getWeight(), "weight"));
            } else {
                textViewWeightValue1.setText(ConvertMetricsToImperial(AthleteData.get(ProfileScreenActivity.position).getWeight(), "weight"));
            }
            textViewSMMValue1.setText(AthleteData.get(ProfileScreenActivity.position).getSmm());
        } else {
            if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
                textViewWeightValue1.setText(ConvertImperialToMetrics(LoginJson.get(0).getWeight(), "weight"));
            } else {
                textViewWeightValue1.setText(LoginJson.get(0).getWeight());
            }
            textViewSMMValue1.setText(LoginJson.get(0).getSmm());
        }

        if (textViewSMMValue1.getText().toString().equalsIgnoreCase("")) {
            textViewSMMValue1.setText("-");
        }
        if (textViewWeightValue1.getText().toString().equalsIgnoreCase("")) {
            textViewWeightValue1.setText("-");
        }


    }

    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            Log.d("Result", result);
            parseFolderData(result);
        } else {
            UtilityClass.hide();
        }
    }

    private void parseFolderData(String result) {
        String responseMessage = "";

        try {

            JSONObject jsonObj = new JSONObject(result);

            Log.e(VolleyLog.TAG, "parseFolderData:result " + result);

            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj.getString("responseMessage");


            if (WebServices.responseCode == 200) {
                if (whichApiCalled.equalsIgnoreCase("getInjuries")) {
                    Gson gson = new Gson();
                    getInjuries = new ArrayList<GetInjury>();
                    JSONArray jsonArray = jsonObj.getJSONArray("data");
                    getInjuries = new ArrayList(Arrays.asList(gson.fromJson(jsonArray.toString(), GetInjury[].class)));
                    // getInjuries = new Arrays.asList(gson.fromJson(jsonArray.toString(), GetInjury[].class));
                    listViewInjuryNameAndDetails.setAdapter(adapter);

                    Log.e(VolleyLog.TAG, "parseFoldAddInjury.javaerData: " + getInjuries.get(0).getInjuryPartName());
                    UtilityClass.hide();
                }
                if (whichApiCalled.equalsIgnoreCase("add_injury")) {
                    Gson gson = new Gson();
                    List<InjuryNotesDetail> injuryNotesDetail = new ArrayList<>();
                    JSONObject jsonArray = jsonObj.getJSONObject("data");
                    List<GetInjury> addInjuriesx;
                    addInjuriesx = new ArrayList(Arrays.asList(gson.fromJson(jsonArray.toString(), GetInjury.class)));
                    getInjuries.addAll(addInjuriesx);
                    adapter.notifyDataSetChanged();
                    UtilityClass.hide();
                }
                if (whichApiCalled.equalsIgnoreCase("AddInjuriesNote")) {
                    JSONObject jsonArray = jsonObj.getJSONObject("data");
                    List<InjuryNotesDetail> injuryNotesDetails = new ArrayList<>();

                    injuryNotesDetails = new ArrayList(Arrays.asList(gson.fromJson(jsonArray.toString(), InjuryNotesDetail.class)));

                    //getInjuries.get(PositionForAddNotes).getInjuryNotesDetail().add(new InjuryNotesDetail(injuryNotesDetails.get(0).getId(),injuryNotesDetails.get(0).getInjuryId(),injuryNotesDetails.get(0).getNoteDate(),injuryNotesDetails.get(0).getNotes()));

                    getInjuries.get(PositionForAddNotes).getInjuryNotesDetail().addAll(injuryNotesDetails);

                    adapterNotes = new GetInjuriesNotes(context, getInjuries.get(PositionForAddNotes).getInjuryNotesDetail(), PositionForAddNotes);

                    adapterNotes.notifyDataSetChanged();
                    RecyclerviewForNotes.setAdapter(adapterNotes);
                    UtilityClass.hide();
                    // adapter.notifyDataSetChanged();
                }
            } else {
                UtilityClass.hide();
            }
        } catch (Exception e) {
            Log.e(VolleyLog.TAG, "parseFolderData:EE " + e);
        }
    }


    private void imperial() {
        IndexOfGender = 0;
        TextFemale.setTextColor(Color.parseColor("#edbb57"));
        TextMale.setTextColor(Color.parseColor("#4b4b4f"));
        rLayoutMatric.setBackgroundColor(getResources().getColor(R.color.Maincolor));
        lLayoutImperial.setBackground(ContextCompat.getDrawable(context, R.drawable.selectedradious));
    }

    private void matric() {
        IndexOfGender = 1;
        TextFemale.setTextColor(Color.parseColor("#4b4b4f"));
        TextMale.setTextColor(Color.parseColor("#edbb57"));
        rLayoutMatric.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
        lLayoutImperial.setBackgroundColor(getResources().getColor(R.color.Maincolor));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((BodySkeletonInfoScreen) context).overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);

    }

    private void closeAllLayout() {


        TransitionManager.beginDelayedTransition(rLayoutForFullScreenBodyPart, transition);

        rLayoutForFullScreenBodyPart.setVisibility(rLayoutForFullScreenBodyPart.getVisibility() == GONE ? View.VISIBLE : View.GONE);

        isViewInjury = false;

    }

    public void showDialog(Context context, int x, int y, String event, String eventData, View view) {
        //dialog = new Dialog(context);

        ListIndex = 0;

        dialog = LayoutInflater.from(context).inflate(R.layout.layout_for_sekeleton, null);
        BubbleRelativeLayout bubbleView = dialog.findViewById(R.id.CardX);
        bubbleView.setCornerRadius(100f);
        bubbleView.setArrowWidth(30f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        BubblePopupWindow window = new BubblePopupWindow(dialog, bubbleView);

//        window.setCancelOnTouch(true);
//        window.setCancelOnTouchOutside(true);
        // window.setCancelOnLater(3000);
        window.showArrowTo(view, BubbleStyle.ArrowDirection.Up);
        window.setCancelOnTouch(false);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        dialog.setContentView(R.layout.layout_for_sekeleton);
//
//        dialog.setCanceledOnTouchOutside(true);
//
//        dialog.getWindow().setDimAmount(0);
//
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        RelativeLayout mainRly = dialog.findViewById(R.id.mainRly);

        EdittextSearchMuscular = dialog.findViewById(R.id.EdittextSearchMuscular);

        mainRly.invalidate();

        EvenText = dialog.findViewById(R.id.EventName);

        rMainDialogLayout = dialog.findViewById(R.id.rMainDialogLayout);

        ViewGroup.LayoutParams params = rMainDialogLayout.getLayoutParams();

        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        rMainDialogLayout.setLayoutParams(params);

        EvenText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        if (Muscular) {
            EvenText.setText("MUSCULAR SYSTEM");
        } else {
            EvenText.setText("BONE SYSTEM");
        }

        dialogBoxRecyclerView = dialog.findViewById(R.id.dialogBoxRecyclerView);

        dialogBoxRecyclerView.setHasFixedSize(true);

        dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        DividerItemDecoration divider = new DividerItemDecoration(dialogBoxRecyclerView.getContext(), DividerItemDecoration.VERTICAL);

        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider_light));

        dialogBoxRecyclerView.addItemDecoration(divider);


        EdittextSearchMuscular.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                skeletonProfile.filter(text);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //dialogBoxRecyclerView.setAdapter(new SkeletonProfile(context,menus,Skeltons));

        backEventDialog = dialog.findViewById(R.id.backEventDialog);


        // WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        ListView mylistView = dialog.findViewById(R.id.my_list);

        //ListView myListView = findViewById(R.id.my_list);
        try {


            String mv = "";
            if (Muscular) {
                mv = loadJSONFromAsset(context, "Skelton/menu.json");
            } else if (Bone) {
                mv = loadJSONFromAsset(context, "Skelton/menuBone.json");
            }
            if (IndexOfGender == 0) {
                jsonArrayOfFirstPage = new JSONObject(mv).getJSONObject("male").getJSONArray("child").getJSONObject(0).getJSONArray("views");
            } else {
                jsonArrayOfFirstPage = new JSONObject(mv).getJSONObject("female").getJSONArray("child").getJSONObject(0).getJSONArray("views");
            }
            Log.e(VolleyLog.TAG, "showDialog: " + jsonArrayOfFirstPage);


            int length = jsonArrayOfFirstPage.length();
            skeletonProfile = new SkeletonProfile(context, length, jsonArrayOfFirstPage);
            dialogBoxRecyclerView.setAdapter(skeletonProfile);
            backEventDialog.setOnClickListener(viewx -> {
                if (ListIndex == 2) {
                    ListIndex = 0;
                    mainarray = new JSONArray();
                    skeletonProfile = new SkeletonProfile(context, length, jsonArrayOfFirstPage);
                    dialogBoxRecyclerView.setAdapter(skeletonProfile);
                }
                if (ListIndex == 1) {
                    if (mainarray != null && mainarray.length() > 0) {
                        ListIndex = 2;
                        skeletonProfile = new SkeletonProfile(context, length, mainarray);
                        dialogBoxRecyclerView.setAdapter(skeletonProfile);
                    } else {
                        ListIndex = 0;
                        mainarray = new JSONArray();
                        skeletonProfile = new SkeletonProfile(context, length, jsonArrayOfFirstPage);
                        dialogBoxRecyclerView.setAdapter(skeletonProfile);
                    }
                }
            });
        } catch (Exception e) {
            // this is just an example
        }


//        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
//
//        wmlp.x = x;
//
//        wmlp.y = y - 30;

//        dialog.show();
    }

    public String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void addNote(Context context, int x, int y, String event, String injuryID, List<InjuryNotesDetail> injuryNotesDetail, int position, int PositionOFgetInjury, View view) {
        //Toast(context,typeofEvent+ "", Toast.LENGTH_SHORT).show();


        dialog = LayoutInflater.from(context).inflate(R.layout.custom_dialog_box_for_sets, null);
        BubbleLinearLayout bubbleView = dialog.findViewById(R.id.mainRlyofAddset);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        BubblePopupWindow window = new BubblePopupWindow(dialog, bubbleView);

        window.showArrowTo(view, BubbleStyle.ArrowDirection.Up);
        window.setCancelOnTouch(false);

        //dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.color_transparant));
        //.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#801b5e20")));
        TextView txt = dialog.findViewById(R.id.EventName);
        ImageView AddNoteSave = dialog.findViewById(R.id.addsetSave);
        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txt.setText(event);


        //LinearLayout mainRlyofAddset = dialog.findViewById(R.id.mainRlyofAddset);

        bubbleView.setBackgroundColor(Color.parseColor("#545454"));

        TextView txtData = dialog.findViewById(R.id.eventData);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        //txtData.setText(eventData);
        txtData.setVisibility(GONE);


        EditText addnotes = dialog.findViewById(R.id.addnotes);

        if (event.equalsIgnoreCase("Edit Note")) {
            addnotes.setText(injuryNotesDetail.get(position).getNotes());
        }

        AddNoteSave.setOnClickListener(viewx -> {
            if (addnotes.getText().toString().equalsIgnoreCase("")) {
                UtilityClass.showAlertDailog(context, "Please enter note.");
                return;
            } else {
                String ExerciseTypeNotesId = "";
                String id = "";

                whichApiCalled = "AddInjuriesNote";
                if (event.equalsIgnoreCase("Edit Note")) {
                    hide();
                    webServices.AddInjuriesNote(injuryID, injuryNotesDetail.get(position).getNoteDate(), addnotes.getText().toString(), injuryNotesDetail.get(position).getId(), context, BodySkeletonInfoScreen.this);
                    getInjuries.get(PositionOFgetInjury).getInjuryNotesDetail().get(position).setNotes(addnotes.getText().toString());
                    adapterNotes.notifyItemChanged(position);
                } else {
                    hide();
                    webServices.AddInjuriesNote(injuryID, dateFormateX.format(cal.getTime()), addnotes.getText().toString(), "", context, BodySkeletonInfoScreen.this);

                    //if(getInjuries.get(PositionForAddNotes).getInjuryNotesDetail().size() != 0){
                    //getInjuries.get(PositionForAddNotes).getInjuryNotesDetail().add(new InjuryNotesDetail("",injuryID,dateFormateX.format(cal.getTime()),addnotes.getText().toString()));
//                    }else {
//                        List<InjuryNotesDetail> injuryNotesDetailsx = new ArrayList<>();
//                        injuryNotesDetailsx.add(new InjuryNotesDetail("",injuryID,,addnotes.getText().toString()));
//                        getInjuries.get(PositionForAddNotes).setInjuryNotesDetail(injuryNotesDetailsx);
//                    }


                    adapterNotes = new GetInjuriesNotes(context, getInjuries.get(PositionForAddNotes).getInjuryNotesDetail(), PositionForAddNotes);

                    adapterNotes.notifyDataSetChanged();
                    //RecyclerviewForNotes.setAdapter(adapterNotes);
                }


                //getInjuries.get(position).setInjuryNotesDetail( new InjuryNotesDetail("",injuryID,dateFormateX.format(cal.getTime()),addnotes.getText().toString()));
                //getInjuries.get(position).getInjuryNotesDetail().add();
                //NoteString = addnotes.getText().toString();
                //injuryNotesDetail.add(new InjuryNotesDetail("","","",""));

            }
        });


//        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
//        wmlp.gravity = Gravity.CENTER_VERTICAL | Gravity.TOP | Gravity.CENTER_HORIZONTAL | Gravity.CENTER;
//        wmlp.x = x - 150;
//        wmlp.y = y - 100;
//        dialog.show();
    }

    private int getScale(int i) {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width) / new Double(webView.getRight() - webView.getLeft());
        val = val * 47d;
        return val.intValue();
    }

    public static class DatePickerDialogClass extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(), R.style.datepickerCustom, this, year, month, day);
            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Date date1 = null;
            try {
                date1 = new SimpleDateFormat("dd - MM - yyyy").parse(day + " - " + (month + 1) + " - " + year);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            android.text.format.DateFormat df = new android.text.format.DateFormat();
            textViewDate.setText(DateFormat.format("MMM. yyyy", date1));
            //GlobalClass.PhaseDate = df.format("dd - MMM - yyyy", date1).toString();
        }
    }

    private class MyBonesViewHolder extends RecyclerView.ViewHolder {
        TextView BoneName;
        RelativeLayout rLayoutforBonename;

        public MyBonesViewHolder(@NonNull View itemView) {
            super(itemView);
            BoneName = itemView.findViewById(R.id.BoneName);
            rLayoutforBonename = itemView.findViewById(R.id.rLayoutforBonename);
        }
    }

    public class SkeletonProfile extends RecyclerView.Adapter<MyBonesViewHolder> {
        int length;
        JSONArray jsonArray;
        Context context;


        public SkeletonProfile(Context context, int length, JSONArray jsonArray) {
            this.context = context;
            this.jsonArray = jsonArray;
            this.length = length;

        }

        @Override
        public MyBonesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.bones_layout, viewGroup, false);
            return new MyBonesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyBonesViewHolder holder, int i) {
            holder.BoneName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            holder.BoneName.setSelected(true);
//            try {
//
//                if (jsonArray.getJSONObject(i).getString("url").toLowerCase().indexOf("html".toLowerCase()) != -1) {
//                    webView.loadUrl("file:///android_asset/Skelton/innerbody/output" + jsonArray.getJSONObject(i).getString("url"));
//                } else {
//                    webView.loadUrl("file:///android_asset/Skelton/innerbody/output" + jsonArray.getJSONObject(i).getString("url") + ".html");
//                }
//            }catch (Exception x){
//
//            }
            try {
                if (ListIndex == 0 || ListIndex == 2) {
                    if (ListIndex == 2) {
                        backEventDialog.setVisibility(VISIBLE);
                    } else {
                        backEventDialog.setVisibility(View.INVISIBLE);
                    }
                    //Toast.makeText(context,jsonArray.getJSONObject(i)+ "", Toast.LENGTH_SHORT).show();
                    holder.BoneName.setText(jsonArray.getJSONObject(i).getString("name"));
                } else if (ListIndex == 1) {
                    backEventDialog.setVisibility(VISIBLE);
                    holder.BoneName.setText(jsonArray.getJSONObject(i).getString("title"));
                    //Toast.makeText(context,jsonArray.getJSONObject(i)+ "", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            holder.rLayoutforBonename.setOnClickListener(view -> {
                if (ListIndex == 0 || ListIndex == 2) {
                    try {
                        jsonArrayForChild = new JSONArray();
                        try {
                            jsonArrayForChild = jsonArray.getJSONObject(i).getJSONObject("child").getJSONArray("parent");
                            ListIndex = 1;
                            skeletonProfile = new SkeletonProfile(context, length, jsonArrayForChild);
                            dialogBoxRecyclerView.setAdapter(skeletonProfile);
                            backEventDialog.setVisibility(VISIBLE);
                        } catch (Exception x) {
                            JSONObject issueObj = new JSONObject(jsonArray.getJSONObject(i).getJSONObject("child").toString());

                            Iterator iterator = issueObj.keys();

                            mainarray = new JSONArray();


                            while (iterator.hasNext()) {

                                String key = (String) iterator.next();

                                JSONObject name = new JSONObject();

                                JSONObject name2 = new JSONObject();

                                name2.put("parent", issueObj.getJSONArray(key));

                                name.put("name", key);

                                name.put("child", name2);

                                mainarray.put(name);
                            }
                            ListIndex = 2;
                            skeletonProfile = new SkeletonProfile(context, length, mainarray);
                            dialogBoxRecyclerView.setAdapter(skeletonProfile);
                            backEventDialog.setVisibility(VISIBLE);
                        }
                    } catch (JSONException e) {
                        Log.e(VolleyLog.TAG, "onBindViewHolder:" + e);
                    }
                } else {
                    try {

                        if (jsonArray.getJSONObject(i).getString("url").toLowerCase().indexOf("html".toLowerCase()) != -1) {
                            webView.loadUrl("file:///android_asset/Skelton/innerbody/output" + jsonArray.getJSONObject(i).getString("url"));
                        } else {
                            webView.loadUrl("file:///android_asset/Skelton/innerbody/output" + jsonArray.getJSONObject(i).getString("url") + ".html");
                        }

                        webView.evaluateJavascript("(function(){return window.document.body.outerHTML})();",
                                new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html) {
                                        Log.e(VolleyLog.TAG, "onReceiveValue: " + html);
                                    }
                                });

                        Log.e(VolleyLog.TAG, "onBindViewHolder: " + WebviewLink);
                        //if(WebviewLink.)
//                        dialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            int Count;
            if (jsonArray != null) {
                Count = jsonArray.length();
            } else {
                Count = 0;
            }

            return Count;

        }

        public void filter(String text) {
            String textOfString = text.toLowerCase();
            int countx = 0;
            textOfString = textOfString.toLowerCase().trim();
            if (textOfString.length() == 0) {
                if (ListIndex == 0) {
                    jsonArray = jsonArrayOfFirstPage;
                }
                if (ListIndex == 1) {
                    jsonArray = jsonArrayForChild;
                }
                if (ListIndex == 2) {
                    jsonArray = mainarray;
                }
            } else {
                jsonArray = new JSONArray();
                if (ListIndex == 0) {
                    for (int x = 0; x < jsonArrayOfFirstPage.length(); x++) {
                        try {
                            if (jsonArrayOfFirstPage.getJSONObject(x).getString("name").toLowerCase().contains(textOfString.toLowerCase())) {
                                jsonArray.put(jsonArrayOfFirstPage.getJSONObject(x));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                if (ListIndex == 2) {
                    for (int x = 0; x < mainarray.length(); x++) {
                        try {
                            if (mainarray.getJSONObject(x).getString("name").toLowerCase().contains(textOfString.toLowerCase())) {
                                jsonArray.put(mainarray.getJSONObject(x));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                if (ListIndex == 1) {
                    for (int x = 0; x < jsonArrayForChild.length(); x++) {
                        try {
                            if (jsonArrayForChild.getJSONObject(x).getString("title").toLowerCase().contains(textOfString.toLowerCase())) {
                                jsonArray.put(jsonArrayForChild.getJSONObject(x));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    private class GetInjuriesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInjuryPartName, textViewReportedOnDate, textViewRecoveredOnDate;
        RelativeLayout rLayoutforViewEditNotes, lLayoutForInjuryHeading, topLine, bottomLine;

        public GetInjuriesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInjuryPartName = itemView.findViewById(R.id.textViewInjuryPartName);
            textViewReportedOnDate = itemView.findViewById(R.id.textViewReportedOnDate);
            textViewRecoveredOnDate = itemView.findViewById(R.id.textViewRecoveredOnDate);
            rLayoutforViewEditNotes = itemView.findViewById(R.id.rLayoutforViewEditNotes);
            //lLayoutForInjuryHeading = itemView.findViewById(R.id.lLayoutForInjuryHeading);
            topLine = itemView.findViewById(R.id.topLine);
            bottomLine = itemView.findViewById(R.id.bottomLine);
        }
    }

    public class GetInjuriesProfile extends RecyclerView.Adapter<GetInjuriesViewHolder> {
        int length;
        JSONArray jsonArray;
        Context context;

        public GetInjuriesProfile(Context context) {
            this.context = context;
        }

        @Override
        public GetInjuriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.list_items_injury, viewGroup, false);
            return new GetInjuriesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GetInjuriesViewHolder holder, int position) {

            if (position == 0) {
                holder.topLine.setVisibility(VISIBLE);
            } else {
                holder.topLine.setVisibility(GONE);
            }

            imageViewAddInjuryNotes.setOnClickListener(viewx -> {
                int[] location = new int[2];
                textViewInjuris.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                addNote(context, x, y, "Add Note", getInjuries.get(position).getId(), getInjuries.get(position).getInjuryNotesDetail(), position, 0, viewx);

            });

            if (position == adapter.getItemCount()) {
                holder.bottomLine.setVisibility(GONE);
            }

            holder.textViewInjuryPartName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.textViewReportedOnDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.textViewRecoveredOnDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            holder.textViewInjuryPartName.setOnClickListener(view -> {
                Log.e(VolleyLog.TAG, "onBindViewHolderBefore: " + getInjuries.size());

                List<GetInjury> getInjuries1 = new ArrayList<>();

                try {
                    //List<InjuryNotesDetail> injuryNotesDetails = new ArrayList<>();
                    //getInjuries.add(new GetInjury("","","","","","SDCDX",injuryNotesDetails));
                    //notifyDataSetChanged();
                } catch (Exception x) {

                    Log.e(VolleyLog.TAG, "onBindViewHolder: " + x);
                }

                Log.e(VolleyLog.TAG, "onBindViewHolderAfter: " + getInjuries.size());
            });


            holder.textViewInjuryPartName.setText(getInjuries.get(position).getInjuryPartName());

            holder.textViewReportedOnDate.setText((getInjuries.get(position).getInjuryReportedOn().equalsIgnoreCase("") ? "-" : getInjuries.get(position).getInjuryReportedOn()));

            holder.textViewRecoveredOnDate.setText(getInjuries.get(position).getInjuryRecoveredOn().equalsIgnoreCase("") ? "-" : getInjuries.get(position).getInjuryRecoveredOn());

            holder.textViewRecoveredOnDate.setOnClickListener(view -> {
                DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        getInjuries.get(position).setInjuryRecoveredOn(dateFormateX.format(newDate.getTime()));
                        holder.textViewRecoveredOnDate.setText(dateFormateX.format(newDate.getTime()));
                        whichApiCalled = "updateInjuryDate";
                        hide();
                        webServices.updateInjuryDate(getInjuries.get(position).getInjuryReportedOn(), getInjuries.get(position).getInjuryRecoveredOn(), getInjuries.get(position).getId(), context, BodySkeletonInfoScreen.this);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                StartTime.show();
            });

            holder.textViewReportedOnDate.setOnClickListener(view -> {
                DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        getInjuries.get(position).setInjuryReportedOn(dateFormateX.format(newDate.getTime()));
                        holder.textViewReportedOnDate.setText(dateFormateX.format(newDate.getTime()));
                        whichApiCalled = "updateInjuryDate";
                        hide();
                        webServices.updateInjuryDate(getInjuries.get(position).getInjuryReportedOn(), getInjuries.get(position).getInjuryRecoveredOn(), getInjuries.get(position).getId(), context, BodySkeletonInfoScreen.this);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                StartTime.show();
            });

            holder.rLayoutforViewEditNotes.setOnClickListener(view -> {
                adapterNotes = new GetInjuriesNotes(context, getInjuries.get(position).getInjuryNotesDetail(), position);
                adapterNotes.notifyDataSetChanged();
                RecyclerviewForNotes.setAdapter(adapterNotes);
                adapterNotes.notifyDataSetChanged();
                textViewInjuryName.setText(getInjuries.get(position).getInjuryPartName());
                //Toast.makeText(context,position+ "", Toast.LENGTH_SHORT).show();
                lLayoutForViewInjuryNotes.setVisibility(VISIBLE);


                PositionForAddNotes = position;
            });

        }

        @Override
        public int getItemCount() {
            return getInjuries.size();
        }
    }

    public class GetInjuriesNotes extends RecyclerView.Adapter<GetInjuriesViewHolder> {
        JSONArray jsonArray;

        Context context;
        List<InjuryNotesDetail> injuryNotesDetail;

        int PositionOFgetInjury = 0;

        public GetInjuriesNotes(Context context, List<InjuryNotesDetail> injuryNotesDetail, int x) {
            this.context = context;
            this.injuryNotesDetail = injuryNotesDetail;
            this.PositionOFgetInjury = x;
        }

        @Override
        public GetInjuriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.list_items_injury, viewGroup, false);
            return new GetInjuriesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GetInjuriesViewHolder holder, int position) {

            Log.e(VolleyLog.TAG, "onBindViewHolder: " + PositionOFgetInjury);
            if (position == 0) {
                holder.topLine.setVisibility(VISIBLE);
            } else {
                holder.topLine.setVisibility(GONE);
            }

            if (position == adapter.getItemCount()) {
                holder.bottomLine.setVisibility(GONE);
            }

            holder.textViewInjuryPartName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.textViewInjuryPartName.setOnClickListener(view -> {
                int[] location = new int[2];
                textViewInjuris.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                addNote(context, x, y, "Edit Note", getInjuries.get(PositionOFgetInjury).getId(), injuryNotesDetail, position, PositionOFgetInjury, view);
            });

            holder.textViewReportedOnDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.textViewRecoveredOnDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.textViewInjuryPartName.setText(injuryNotesDetail.get(position).getNoteDate() + "  " + injuryNotesDetail.get(position).getNotes());

            holder.textViewReportedOnDate.setVisibility(GONE);

            holder.textViewRecoveredOnDate.setVisibility(GONE);

            holder.rLayoutforViewEditNotes.setVisibility(GONE);

        }

        @Override
        public int getItemCount() {
            return injuryNotesDetail.size();
        }
    }
}
