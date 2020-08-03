package com.org.godspeed.allOtherClasses;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.RecyclerViewClickCheck;
import com.org.godspeed.loginData.Login;
import com.org.godspeed.response_JsonS.GetSport.GetSport;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.GetTeamsDetailsClas;
import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
import com.org.godspeed.response_JsonS.timezone.timezoneList;
import com.org.godspeed.service.SchoolDataService;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;
import com.org.godspeed.utility.custom_popup_class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.LoginScreen.CoachCheckAthlete;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_ID_FOR_PRE;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_NAME_FOR_PRE;
import static com.org.godspeed.fragments.CoachBoardFragments.AthleteData;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.service.SchoolDataService.getLanguageList;
import static com.org.godspeed.service.SchoolDataService.getPositionList;
import static com.org.godspeed.service.SchoolDataService.getSchoolsList;
import static com.org.godspeed.service.SchoolDataService.getTeamsDetailsClas;
import static com.org.godspeed.service.SchoolDataService.schoolArray;
import static com.org.godspeed.service.SchoolDataService.schoolIdArray;
import static com.org.godspeed.service.SchoolDataService.sportsArray;
import static com.org.godspeed.service.SchoolDataService.sportsIdArray;
import static com.org.godspeed.service.SchoolDataService.teamArray;
import static com.org.godspeed.service.SchoolDataService.teamIdArray;
import static com.org.godspeed.service.SchoolDataService.timezoneLists;
import static com.org.godspeed.utility.GlobalClass.ConvertImperialToMetrics;
import static com.org.godspeed.utility.GlobalClass.ConvertMetricsToImperial;
import static com.org.godspeed.utility.UtilityClass.hide;


public class UserProfileScreen extends FragmentActivity implements GodSpeedInterface, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, RecyclerViewClickCheck {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static boolean isAnimationStarted;
    public static ImageView imageViewAppIconForAnimation, imageViewBackArrow;
    public static Boolean setAddressFromMap = false;
    String HeightImp;
    String[] my = new String[3];
    String selectedIndex = "";
    boolean[] checkedItemsTEAM = null;
    ArrayList<String> selectedItemsTeams = new ArrayList<String>();
    ArrayList<Integer> selectedItemsTeamId = new ArrayList<Integer>();
    boolean[] checkedItemsSPORTS = null;
    ArrayList<String> selectedItemsSPORTS = new ArrayList<String>();
    ArrayList<Integer> selectedItemsSPORTSId = new ArrayList<Integer>();
    RelativeLayout rLayoutDivider5;
    String[] languageNameArray = new String[getLanguageList.size()];
    LocationManager locationManager;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleMap.OnCameraIdleListener onCameraIdleListener;
    Boolean locationc = false;
    List<Address> addressList;
    TextView text;
    ImageView locImg;
    RelativeLayout setAddress, bbRl;
    TextView Done;
    CardView changeView, bbCard;
    View locationButton;
    View rootView, MainRly;
    Boolean AddressStreet = false;
    String SelectionTypeOfList = "";
    private EditText editTextBirthday;
    private String WeightImp;
    private Context context;
    private EditText editTextGender, editTextLanguage, editTextSports, editTextName, editTextLastName, editTextBodyfat, editTextEmail, editTextSuite, editTextStreet, editTextZip, editTextCountry, editTextState, editTextCity, editTextSchool, editTextTeam2, editTextWeight, editTextheight, editTextSmm, editTextSchoolAddress, editTextSocialMedia, editTextTeam, editTextPosition;
    private EditText editTextNeck, editTextBicep, editTextChest, editTextWaist, editTextHips, editTextThigh, editTextTimezone;
    private String[] singleItemsChooseArray = null;
    private ImageView imageViewForZoomInOut, imageViewSave;
    private Animation zoomIn, zoomOut;
    private RelativeLayout rLayoutUpdateProfileButton;
    private String whichApiCalled = "";
    private WebServices webServices = null;
    private RelativeLayout rLAyoutForTeam, rLAyoutForPosition;
    private TextView textViewScreenName, textViewName, textViewLastName, textViewEmail, textViewDOB, textViewGender, textViewLanguage, textViewBodyfat, textViewSuite, textViewStreet, textViewZip, textViewCountry, textViewState, textViewCity, textViewSchool, textViewSchoolAddress, textViewPosition, textViewTeam, textViewSport, textViewSocialMedia, textViewtem2, textViewHeight, textViewWeight, textViewSmm;
    private Boolean coach = false;
    private RecyclerView mRecyclerView;
    // ArrayList<Integer> mUserItems = new ArrayList<>();
    private String userId, backgroundImage = "";
    private ArrayList<String> list = new ArrayList<String>();
    private Boolean isChecked;
    private String weight12, height12;
    private List<Login> loginData;
    private BubblePopupWindow dialog;
    private String[] arrayOFteam;
    private String[] arrayOFSports;
    private String[] positionTitleIdArray, positionTitleArray;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void toggle(boolean show, String s) {
        //1 = Street Address
        //2 = School Address
        AddressStreet = s.equalsIgnoreCase("1");

        ViewGroup parent = findViewById(R.id.MainRly);
        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(400);
        transition.addTarget(bbRl);

        TransitionManager.beginDelayedTransition(parent, transition);
        bbRl.setVisibility(show ? View.VISIBLE : View.GONE);

        if (show) {
            bbRl.setOnTouchListener((view, motionEvent) -> {
                toggle(false, "0");
                return true;
            });

        }
    }

    @Override
    public void OnItemClick(int position) {
        if (SelectionTypeOfList.equalsIgnoreCase("position")) {
            editTextPosition.setText(getPositionList.get(position).getPositionTitleName());
            editTextPosition.setTag(getPositionList.get(position).getPositionTitleId());
        } else if (SelectionTypeOfList.equalsIgnoreCase("team")) {

        } else if (SelectionTypeOfList.equalsIgnoreCase("teamList")) {

        } else if (SelectionTypeOfList.equalsIgnoreCase("sports")) {

        } else if (SelectionTypeOfList.equalsIgnoreCase("school")) {
            editTextSchool.setText(getSchoolsList.get(position).getSchoolName());
            editTextSchool.setTag(getSchoolsList.get(position).getSchoolId());
        } else if (SelectionTypeOfList.equalsIgnoreCase("language")) {
            editTextLanguage.setText(getLanguageList.get(position).getLanguageName());
            editTextLanguage.setTag(getLanguageList.get(position).getLanguageId());
        } else if (SelectionTypeOfList.equalsIgnoreCase("timezone")) {
            editTextTimezone.setText(timezoneLists.get(position).getTimeZoneName());
        }
    }

    @Override
    public void OnItemClickListReturn(List<GetTeamsDetailsClas> teamList, List<GetSport> getSportList) {
        if (SelectionTypeOfList.equalsIgnoreCase("teamList")) {
            selectedIndex = "";
            selectedItemsTeamId = new ArrayList<>();
            selectedItemsTeams = new ArrayList<>();
            for (int os = 0; os < teamList.size(); os++) {
                selectedIndex += teamList.get(os).getTeamName() + ", ";
                selectedItemsTeamId.add(Integer.valueOf(teamList.get(os).getTeamId()));
                selectedItemsTeams.add(teamList.get(os).getTeamName());
            }

            if (selectedIndex.endsWith(", ")) {
                selectedIndex = selectedIndex.substring(0, selectedIndex.length() - 2);
            }
            setTextTeam();
            //editTextTeam.setText(selectedIndex);
        } else if (SelectionTypeOfList.equalsIgnoreCase("sports")) {
            // selectedIndex = "";
            selectedItemsSPORTSId = new ArrayList<>();
            selectedItemsSPORTS = new ArrayList<>();
            for (int os = 0; os < getSportList.size(); os++) {
                selectedItemsSPORTSId.add(Integer.valueOf(getSportList.get(os).getSportId()));
                selectedItemsSPORTS.add(getSportList.get(os).getSportName());
            }

            setTextSports();
        }
        //setText();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_user_profile);
        context = this;


        int usertype = GlobalClass.ivar1;
        if (usertype == 1) {
            coach = true;
        }
        try {
            arrayOFteam = new String[getTeamsDetailsClas.size() - 1];
            checkedItemsTEAM = new boolean[getTeamsDetailsClas.size() - 1];
            for (int i = 0; i < getTeamsDetailsClas.size(); i++) {
                if (!getTeamsDetailsClas.get(i).getTeamName().equalsIgnoreCase("All Athlete")) {
                    arrayOFteam[i] = getTeamsDetailsClas.get(i).getTeamName();
                }
            }
        } catch (Exception x) {
        }
        try {
            arrayOFSports = new String[sportsArray.length];
            checkedItemsSPORTS = new boolean[sportsArray.length];
            for (int i = 0; i < sportsArray.length; i++) {
                arrayOFSports[i] = sportsArray[i];
            }
        } catch (Exception x) {
        }
        try {
            for (int i = 0; i < arrayOFSports.length; i++) {
                checkedItemsSPORTS[i] = false;
            }
            for (int i = 0; i < arrayOFteam.length; i++) {
                checkedItemsTEAM[i] = false;
            }
        } catch (Exception c) {

        }


        bbCard = findViewById(R.id.bbCard);

        bbCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        //[training_program_id=40, athlete_id=853, phase=1, week=1, day=1, pillar=0, exercise_type_id=8, exercise_id=632]
        bbRl = findViewById(R.id.bbRl);

        bbRl.setVisibility(GONE);

        text = findViewById(R.id.text);

        changeView = findViewById(R.id.changeView);
        setAddress = findViewById(R.id.setAddress);
        Done = findViewById(R.id.Done);
        mapFrag = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mapFrag);
        fragmentTransaction.commit();
        mapFrag.getMapAsync(this);

        //imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        locImg = findViewById(R.id.locImg);
        locImg.setVisibility(View.GONE);
//        imageViewBackArrow.setOnClickListener(view -> {
//            onBackPressed();
//        });
        changeView.setOnClickListener(view -> {
            changeMapType();

        });

        configureCameraIdle();
        usertype = GlobalClass.ivar1;
        if (usertype == 1) {
            coach = true;
        }
        Done.setOnClickListener(view -> {
            try {
                if (addressList != null && addressList.size() > 0) {

                    Log.e(VolleyLog.TAG, "onCreate:addressList " + addressList.toString());
                    String locality = addressList.get(0).getAddressLine(0);

                    String address = addressList.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addressList.get(0).getLocality();
                    String state = addressList.get(0).getAdminArea();
                    String country = addressList.get(0).getCountryName();
                    String postalCode = addressList.get(0).getPostalCode();
                    String knownName = addressList.get(0).getFeatureName();

                    try {
                        int cou = 0;
                        int ind = 0;
                        for (int i = address.length() - 1; i >= 0; i--) {
                            //System.out.println(address.charAt(i));

                            Character m = address.charAt(i);
                            if (m.toString().equalsIgnoreCase(",")) {
                                cou++;
                                if (cou <= 3) {
                                    ind = i;
                                }

                            }
                        }
                        address = address.substring(0, ind);
                    } catch (Exception m) {
                        Log.e(VolleyLog.TAG, "setText: " + m);
                    }
                    if (!locality.isEmpty() && !country.isEmpty()) {

                        if (AddressStreet) {
                            editTextStreet.setText(address);
                            // editTextGender.setTag(AthleteData.get(ProfileScreenActivity.position).getSchoolId());
                            editTextZip.setText(postalCode);
                            editTextCountry.setText(country);
                            editTextState.setText(state);
                            editTextCity.setText(city);
                            editTextSchool.setText(AthleteData.get(ProfileScreenActivity.position).getSchoolName());
                        } else {
                            editTextSchoolAddress.setText(address);
                        }
                    }


                }
            } catch (Exception m) {
                Log.e(VolleyLog.TAG, "onCreate:Exception " + m);
            }
            toggle(false, "0");
        });


        webServices = new WebServices();

        userId = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
        parseRequiredData(UtilityClass.getPreferences(context, getString(R.string.sport)));
        //loadSchoolData();
        whichApiCalled = "getTimeZone";

        webServices.getTimeZone(context, UserProfileScreen.this);

        rLAyoutForTeam = findViewById(R.id.rLAyoutForTeam);
        rLAyoutForTeam.setVisibility(GONE);
        rLAyoutForPosition = findViewById(R.id.rLAyoutForPosition);
        rLAyoutForPosition.setVisibility(GONE);
        rLayoutDivider5 = findViewById(R.id.rLayoutDivider5);
        rLayoutDivider5.setVisibility(GONE);


//        if(LoginScreen.CoachCheckAthlete){
//            coach = true;
//            rLAyoutForTeam.setVisibility(View.VISIBLE);
//            rLayoutDivider5.setVisibility(View.VISIBLE);
//            rLAyoutForPosition.setVisibility(View.GONE);
//        }else {
//            rLAyoutForPosition.setVisibility(View.VISIBLE);
//            rLAyoutForTeam.setVisibility(View.GONE);
//            rLayoutDivider5.setVisibility(View.GONE);
//        }

        if (UtilityClass.getPreferences(context, getString(R.string.user_type_tag)).equalsIgnoreCase("3")) {
            rLAyoutForPosition.setVisibility(VISIBLE);
            rLAyoutForTeam.setVisibility(VISIBLE);
            rLayoutDivider5.setVisibility(VISIBLE);
        } else {
            rLAyoutForTeam.setVisibility(VISIBLE);
            rLayoutDivider5.setVisibility(VISIBLE);
        }

        if (CoachCheckAthlete == true) {
            rLAyoutForPosition.setVisibility(GONE);
            rLayoutDivider5.setVisibility(VISIBLE);
            rLAyoutForTeam.setVisibility(VISIBLE);
        }

        //webServices.getLanguage(context, UserProfileScreen.this);
        rLayoutUpdateProfileButton = findViewById(R.id.rLayoutUpdateProfileButton);
        rLayoutUpdateProfileButton.setVisibility(GONE);
        initializeTextView();


        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
                finish();
            }
        });


        imageViewSave = findViewById(R.id.imageViewSave);
        imageViewSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //UtilityClass.showWaitDialog(new Dialog(context),context);
                whichApiCalled = "updateprofile";
                Log.e(VolleyLog.TAG, "onClick: " + editTextSchool.getText().toString());

                String USERID = (coach ? AthleteData.get(ProfileScreenActivity.position).getUserId() : userId);

                if (UtilityClass.getPreferences(context, "unit_type") != null) {
                    if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
                        WeightImp = ConvertMetricsToImperial(editTextWeight.getText().toString(), "weight");
                        HeightImp = ConvertMetricsToImperial(editTextheight.getText().toString(), "height");
                    } else {
                        WeightImp = editTextWeight.getText().toString() + "";
                        HeightImp = editTextheight.getText().toString() + "";
                    }
                }
                hideKeyboard(view);

                StringBuilder sports_id = new StringBuilder();
                String Sports_id = "";
                for (int m = 0; m < selectedItemsSPORTSId.size(); m++) {
                    Sports_id = sports_id.append(selectedItemsSPORTSId.get(m) + ",").toString();
                }

                if (Sports_id != null && Sports_id.length() > 0 && Sports_id.charAt(Sports_id.length() - 1) == ',') {
                    Sports_id = Sports_id.substring(0, Sports_id.length() - 1);
                }

                StringBuilder teamidArray_id = new StringBuilder();
                String teamidArray = "";
                for (int m = 0; m < selectedItemsTeamId.size(); m++) {
                    teamidArray = sports_id.append(selectedItemsTeamId.get(m) + ",").toString();
                }

                if (teamidArray != null && teamidArray.length() > 0 && teamidArray.charAt(teamidArray.length() - 1) == ',') {
                    teamidArray = teamidArray.substring(0, teamidArray.length() - 1);
                }


                webServices.UpdateProfile(
                        USERID,
                        editTextName.getText().toString(),
                        editTextLastName.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextBirthday.getText().toString(),
                        editTextGender.getText().toString(),
                        editTextLanguage.getTag().toString(),
                        editTextSuite.getText().toString(),
                        editTextStreet.getText().toString(),
                        editTextZip.getText().toString(),
                        editTextCountry.getText().toString(),
                        editTextState.getText().toString(),
                        editTextCity.getText().toString(),
                        editTextSchool.getTag().toString(),
                        editTextSchoolAddress.getText().toString(),
                        teamidArray,
                        Sports_id,
                        editTextSocialMedia.getText().toString(),
                        WeightImp,
                        HeightImp,
                        editTextBodyfat.getText().toString(),
                        editTextSmm.getText().toString(),
                        backgroundImage,
                        editTextPosition.getTag().toString(),
                        editTextNeck.getText().toString(),
                        editTextBicep.getText().toString(),
                        editTextChest.getText().toString(),
                        editTextWaist.getText().toString(),
                        editTextHips.getText().toString(),
                        editTextThigh.getText().toString(),
                        editTextTimezone.getText().toString(),
                        context,
                        UserProfileScreen.this);
            }
        });

        //for birthday edit text
        editTextBirthday = findViewById(R.id.editTextBirthday);
        editTextBirthday.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextBirthday.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    Date date1 = null;
                    try {
                        date1 = new SimpleDateFormat("dd/MM/yyyy").parse(day + "/" + (month + 1) + "/" + year);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    android.text.format.DateFormat df = new android.text.format.DateFormat();
                    editTextBirthday.setText(DateFormat.format("yyyy-MMM-dd", date1));

                    hideKeyboard(view);
                    showChooseItemAlertDialog(editTextGender);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            StartTime.show();
        });


        editTextBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    final Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog StartTime = new DatePickerDialog(context, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            Date date1 = null;
                            try {
                                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(day + "/" + (month + 1) + "/" + year);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            android.text.format.DateFormat df = new android.text.format.DateFormat();
                            editTextBirthday.setText(DateFormat.format("yyyy-MMM-dd", date1));

                            hideKeyboard(view);
                            showChooseItemAlertDialog(editTextGender);
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    StartTime.show();
                }
            }
        });


// for gender edit text
        editTextGender = findViewById(R.id.editTextGender);
        editTextGender.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextGender.setFocusable(false);
        editTextGender.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextBirthday.clearFocus();
                hideKeyboard(view);
                showChooseItemAlertDialog(editTextGender);
            }
        });


        editTextLanguage = findViewById(R.id.editTextLanguage);
        editTextLanguage.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextLanguage.setFocusable(false);

        editTextLanguage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                SelectionTypeOfList = "language";
                custom_popup_class mAlert = new custom_popup_class(context, "language", editTextLanguage.getTag().toString(), (RecyclerViewClickCheck) context);
                mAlert.setMessage("SELECT LANGUAGE");
                mAlert.show();
            }
        });

        editTextSports = findViewById(R.id.editTextSport);
        editTextSports.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextSports.setFocusable(false);

        editTextTimezone = findViewById(R.id.editTextTimezone);
        editTextTimezone.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextTimezone.setFocusable(false);


        editTextTimezone.setOnClickListener(view -> {
            hideKeyboard(view);
            SelectionTypeOfList = "timezone";
            custom_popup_class mAlert = new custom_popup_class(context, "timezone",
                    editTextTimezone.getText().toString(),
                    (RecyclerViewClickCheck) context);
            mAlert.setMessage("SELECT TIMEZONE");
            mAlert.show();

            //showChooseSportsItemAlertDialog(editTextSports, sportsArray, sportsIdArray);
        });
        editTextSports.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                SelectionTypeOfList = "sports";
                custom_popup_class mAlert = new custom_popup_class(context, "sports",
                        editTextSports.getTag().toString(),
                        (RecyclerViewClickCheck) context,
                        selectedItemsSPORTSId);
                mAlert.setMessage("SELECT SPORTS");
                mAlert.show();

                //showChooseSportsItemAlertDialog(editTextSports, sportsArray, sportsIdArray);
            }
        });

        editTextSchool = findViewById(R.id.editTextSchool);
        editTextSchool.setSelection(editTextSchool.getText().length());

        editTextSchool.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextSchool.setSingleLine(true);
        editTextSchool.setEllipsize(TextUtils.TruncateAt.END);
        editTextSchool.setFocusable(false);
        editTextSchool.setTag("");
        if (!SCHOOL_NAME_FOR_PRE.equalsIgnoreCase("")) {
            editTextSchool.setTag(SCHOOL_ID_FOR_PRE);
            editTextSchool.setText(SCHOOL_NAME_FOR_PRE);
        }


        editTextSchool.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SCHOOL_NAME_FOR_PRE.equalsIgnoreCase("")) {
                    return;
                }
                SelectionTypeOfList = "school";
                custom_popup_class mAlert = new custom_popup_class(context, "school", editTextSchool.getTag().toString(), (RecyclerViewClickCheck) context);
                mAlert.setMessage("SELECT SCHOOL");
                mAlert.show();
            }
        });


        editTextTeam = findViewById(R.id.editTextTeam);
        editTextTeam.setSelection(editTextTeam.getText().length());

        editTextTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextTeam.setSingleLine(true);
        editTextTeam.setEllipsize(TextUtils.TruncateAt.END);
        editTextTeam.setFocusable(false);
        editTextTeam.setTag("");
        editTextTeam.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectionTypeOfList = "teamList";
                custom_popup_class mAlert = new custom_popup_class(context, "teamList",
                        editTextSchool.getTag().toString(),
                        (RecyclerViewClickCheck) context,
                        selectedItemsTeamId);
                mAlert.setMessage("SELECT TEAM");
                mAlert.show();
            }
        });

        positionTitleArray = new String[getPositionList.size()];
        positionTitleIdArray = new String[getPositionList.size()];
        for (int i = 0; i < getPositionList.size(); i++) {
            positionTitleArray[i] = getPositionList.get(i).getPositionTitleName();
            positionTitleIdArray[i] = getPositionList.get(i).getPositionTitleId();
        }

        editTextPosition = findViewById(R.id.editTextPosition);
        editTextPosition.setSelection(editTextPosition.getText().length());
        editTextPosition.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextPosition.setSingleLine(true);
        editTextPosition.setEllipsize(TextUtils.TruncateAt.END);
        editTextPosition.setFocusable(false);
        editTextPosition.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectionTypeOfList = "position";
                custom_popup_class mAlert = new custom_popup_class(context, "position", editTextPosition.getTag().toString(), (RecyclerViewClickCheck) context);
                mAlert.setMessage("SELECT POSITION");
                mAlert.show();
            }
        });


        editTextName = findViewById(R.id.editTextName);


        editTextTeam.setText("");


        editTextName.setSelection(editTextName.getText().length());
        editTextName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextLastName = findViewById(R.id.editTextLastName);
        editTextLastName.setSelection(editTextLastName.getText().length());

        editTextLastName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.setSelection(editTextEmail.getText().length());

        editTextEmail.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextSuite = findViewById(R.id.editTextSuite);
        editTextSuite.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextSuite.setSelection(editTextSuite.getText().length());


        editTextStreet = findViewById(R.id.editTextStreet);
        editTextStreet.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextStreet.setSelection(editTextStreet.getText().length());


        editTextStreet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (view.isFocused()) {
                    hideKeyboard(view);
                    // bbRl.setVisibility(VISIBLE);
                    toggle(true, "1");
                    //showDialog(view);
                    //startActivity(new Intent(context, MapActivity.class));
                    //overridePendingTransition(R.anim.exit, R.anim.enter);
                }
            }
        });


        editTextZip = findViewById(R.id.editTextZip);
        editTextZip.setSelection(editTextZip.getText().length());

        //editTextZip.setEnabled(false);

        ImageView username_icon = findViewById(R.id.username_icon);
        ImageView School_address_icon = findViewById(R.id.School_address_icon);

        username_icon.setOnClickListener(view -> {
            toggle(true, "1");
        });

        School_address_icon.setOnClickListener(view -> {
            toggle(true, "2");
        });

        editTextZip.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextZip.setText(UtilityClass.getPreferences(context, getString(R.string.zip_tag)));
        editTextZip.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String zip = editTextZip.getText().toString().trim();
                if (zip.length() > 0) {
                    // String url = "https://maps.googleapis.com/maps/api/geocode/json?address=&components=postal_code:" + zip + "&sensor=false&key=AIzaSyBHXg-v7IzaKaEyc-peipu5MFTQWx-nK-Y";

                    //new RequestTask().execute(url);
                }

            }
        });


        editTextCountry = findViewById(R.id.editTextCountry);

        editTextCountry.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextState = findViewById(R.id.editTextState);

        editTextState.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextCity = findViewById(R.id.editTextCity);
        editTextCity.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextSchool.setText(UtilityClass.getPreferences(context, getString(R.string.school_name_tag)));
        editTextSchool.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextSchoolAddress = findViewById(R.id.editTextSchoolAddress);
        editTextSchoolAddress.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextSchoolAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (view.isFocused()) {
                    hideKeyboard(view);
                    toggle(true, "2");
                }
            }
        });


        editTextSocialMedia = findViewById(R.id.editTextSocialMedia);
        editTextSocialMedia.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextWeight = findViewById(R.id.editTextweight1);
        editTextWeight.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextheight = findViewById(R.id.editTextheight);
        editTextheight.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextBodyfat = findViewById(R.id.editTextbodyfat);

        editTextBodyfat.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextSmm = findViewById(R.id.editTextsmm);
        editTextSmm.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        initializeEditText();


        editTextCity.setOnEditorActionListener((exampleView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard(exampleView);
                editTextCity.clearFocus();
                showChooseItemAlertDialog(editTextSchool, schoolArray);// for coach
                return true;
            } else {
                return false;
            }
        });

        editTextSchoolAddress.setOnEditorActionListener((exampleView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard(exampleView);
                editTextSchoolAddress.clearFocus();
                if (LoginScreen.CoachCheckAthlete) {
                    if (teamArray != null)
                        showChooseTeamItemAlertDialog(editTextTeam, arrayOFteam);
                } else {
//                    positionTitleArray = new String[getPositionList.size()];
//                    positionTitleIdArray = new String[getPositionList.size()];
//                    for (int c = 0; c < getPositionList.size(); c++) {
//                        positionTitleArray[c] = getPositionList.get(c).getPositionTitleName();
//                        positionTitleIdArray[c] = getPositionList.get(c).getPositionTitleId();
//                    }
                    showChooseItemAlertDialog(editTextPosition, positionTitleArray);// for coach
                }
                return true;
            } else {
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (bbRl.getVisibility() == VISIBLE) {
            toggle(false, "0");
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        }
    }

    public void showKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @SuppressLint("ResourceAsColor")
    private void initializeEditText() {

        //editTextName.setTextColor(Color.parseColor("#edbb57"));
        editTextName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        //editTextLastName.setTextColor(Color.parseColor("#edbb57"));
        editTextLastName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        //editTextEmail.setTextColor(Color.parseColor("#edbb57"));
        editTextEmail.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        // editTextSuite.setTextColor(Color.parseColor("#edbb57"));
        editTextSuite.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        //editTextStreet.setTextColor(Color.parseColor("#edbb57"));
        editTextStreet.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        //editTextZip.setTextColor(Color.parseColor("#edbb57"));
        editTextZip.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextCountry.setTextColor(Color.parseColor("#edbb57"));
        editTextCountry.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextState.setTextColor(Color.parseColor("#edbb57"));
        editTextState.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextCity.setTextColor(Color.parseColor("#edbb57"));
        editTextCity.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextSchool.setTag(LoginJson.get(0).getSchoolId());
        //editTextSchool.setTextColor(Color.parseColor("#edbb57"));
        editTextSchool.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextSchoolAddress.setTextColor(Color.parseColor("#edbb57"));
        editTextSchoolAddress.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextSocialMedia.setTextColor(Color.parseColor("#edbb57"));
        editTextSocialMedia.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextWeight.setTextColor(Color.parseColor("#edbb57"));
        editTextWeight.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextheight.setTextColor(Color.parseColor("#edbb57"));
        editTextheight.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextBodyfat.setTextColor(Color.parseColor("#edbb57"));
        editTextBodyfat.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextSmm.setTextColor(Color.parseColor("#edbb57"));
        editTextSmm.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextBirthday.setTextColor(Color.parseColor("#edbb57"));
        editTextBirthday.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextTeam.setTextColor(Color.parseColor("#edbb57"));
        editTextTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextSports.setTextColor(Color.parseColor("#edbb57"));
        editTextSports.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextPosition.setTextColor(Color.parseColor("#edbb57"));
        editTextPosition.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextGender.setTextColor(Color.parseColor("#edbb57"));
        editTextGender.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        //editTextLanguage.setTextColor(Color.parseColor("#edbb57"));
        editTextLanguage.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextNeck = findViewById(R.id.editTextNeck);
        editTextNeck.setSelection(editTextNeck.getText().length());

        editTextBicep = findViewById(R.id.editTextBicep);
        editTextBicep.setSelection(editTextBicep.getText().length());

        editTextChest = findViewById(R.id.editTextChest);
        editTextChest.setSelection(editTextChest.getText().length());

        editTextWaist = findViewById(R.id.editTextWaist);
        editTextWaist.setSelection(editTextWaist.getText().length());

        editTextHips = findViewById(R.id.editTextHips);
        editTextHips.setSelection(editTextHips.getText().length());

        editTextThigh = findViewById(R.id.editTextThigh);
        editTextThigh.setSelection(editTextThigh.getText().length());

        setText();
    }

    @SuppressLint("SetTextI18n")
    private void setText() {

        if (coach == true || LoginScreen.CoachCheckAthlete == true) {
            editTextName.setText(AthleteData.get(ProfileScreenActivity.position).getFirstName());
            editTextLastName.setText(AthleteData.get(ProfileScreenActivity.position).getLastName());
            editTextEmail.setText(AthleteData.get(ProfileScreenActivity.position).getEmailId());
            editTextBirthday.setText(AthleteData.get(ProfileScreenActivity.position).getDob());
            editTextGender.setText(AthleteData.get(ProfileScreenActivity.position).getGender());
            editTextLanguage.setTag(AthleteData.get(ProfileScreenActivity.position).getLanguageName());
            editTextSuite.setText(AthleteData.get(ProfileScreenActivity.position).getSuit());
            editTextStreet.setText(AthleteData.get(ProfileScreenActivity.position).getStreet());
            // editTextGender.setTag(AthleteData.get(ProfileScreenActivity.position).getSchoolId());
            editTextZip.setText(AthleteData.get(ProfileScreenActivity.position).getZip());
            editTextCountry.setText(AthleteData.get(ProfileScreenActivity.position).getCountry());
            editTextState.setText(AthleteData.get(ProfileScreenActivity.position).getState());
            editTextCity.setText(AthleteData.get(ProfileScreenActivity.position).getCity());
            editTextSchool.setText(AthleteData.get(ProfileScreenActivity.position).getSchoolName());
            editTextSchoolAddress.setText(AthleteData.get(ProfileScreenActivity.position).getSchoolAddress());
            editTextTeam.setText(AthleteData.get(ProfileScreenActivity.position).getTeamName());
            //selectedItemsTeamId.add(AthleteData.get(ProfileScreenActivity.position).getTeamName());
            editTextSports.setText(AthleteData.get(ProfileScreenActivity.position).getSportName());
            editTextSocialMedia.setText(AthleteData.get(ProfileScreenActivity.position).getSocialHead());
            editTextSocialMedia.setText(AthleteData.get(ProfileScreenActivity.position).getSocialMedia());
            editTextWeight.setText(AthleteData.get(ProfileScreenActivity.position).getWeight());
            //Toast.makeText(context, AthleteData.get(ProfileScreenActivity.position).getWeight()+"", Toast.LENGTH_SHORT).show();
            editTextheight.setText(AthleteData.get(ProfileScreenActivity.position).getHeight());
            editTextBodyfat.setText(AthleteData.get(ProfileScreenActivity.position).getBodyFat());
            editTextSmm.setText(AthleteData.get(ProfileScreenActivity.position).getSmm());
            editTextPosition.setTag(AthleteData.get(ProfileScreenActivity.position).getPositionTitleId());
            // editTextPosition.setText(AthleteData.get(ProfileScreenActivity.position).getp());


            editTextNeck.setText(AthleteData.get(ProfileScreenActivity.position).getNeck());
            editTextBicep.setText(AthleteData.get(ProfileScreenActivity.position).getBicep());
            editTextChest.setText(AthleteData.get(ProfileScreenActivity.position).getChest());
            editTextWaist.setText(AthleteData.get(ProfileScreenActivity.position).getWaist());
            editTextHips.setText(AthleteData.get(ProfileScreenActivity.position).getHips());
            editTextThigh.setText(AthleteData.get(ProfileScreenActivity.position).getThigh());
            editTextTimezone.setText(AthleteData.get(ProfileScreenActivity.position).getTimezone());


            for (int x = 0; x < AthleteData.get(ProfileScreenActivity.position).getSports().size(); x++) {
                for (int i = 0; i < sportsIdArray.length; i++) {
                    if (AthleteData.get(ProfileScreenActivity.position).getSports().get(x).getSportId().equalsIgnoreCase(sportsIdArray[i])) {
                        checkedItemsSPORTS[i] = true;
                        selectedItemsSPORTS.add(sportsArray[i]);
                        selectedItemsSPORTSId.add(Integer.valueOf(SchoolDataService.sportsIdArray[i]));
                    }
                }
            }

            for (int x = 0; x < AthleteData.get(ProfileScreenActivity.position).getTeams().size(); x++) {
                for (int i = 0; i < getTeamsDetailsClas.size(); i++) {
                    if (AthleteData.get(ProfileScreenActivity.position).getTeams().get(x).getTeamId().equalsIgnoreCase(getTeamsDetailsClas.get(i).getTeamId())) {
                        checkedItemsTEAM[i] = true;
                        selectedItemsTeams.add(getTeamsDetailsClas.get(i).getTeamName());
                        selectedItemsTeamId.add(Integer.valueOf(getTeamsDetailsClas.get(i).getTeamId()));
                    }
                }
            }
        } else {

            editTextName.setText(LoginJson.get(0).getFirstName());
            editTextTimezone.setText(LoginJson.get(0).getTimezone());
            editTextLastName.setText(LoginJson.get(0).getLastName());
            editTextEmail.setText(LoginJson.get(0).getEmailId());
            editTextBirthday.setText(LoginJson.get(0).getDob());
            editTextGender.setText(LoginJson.get(0).getGender());
            editTextLanguage.setText(LoginJson.get(0).getLanguageName());
            editTextLanguage.setTag(LoginJson.get(0).getLanguageId());
            editTextSuite.setText(UtilityClass.getPreferences(context, getString(R.string.suit_tag)));

            editTextZip.setText(LoginJson.get(0).getZip());
            editTextCountry.setText(LoginJson.get(0).getCountry());
            editTextState.setText(LoginJson.get(0).getState());
            editTextCity.setText(LoginJson.get(0).getCity());

            if (SCHOOL_NAME_FOR_PRE.equalsIgnoreCase("")) {
                editTextSchool.setText(LoginJson.get(0).getSchoolName());
                editTextSchool.setTag(LoginJson.get(0).getSchoolId());
            } else {
                editTextSchool.setText(SCHOOL_NAME_FOR_PRE);
                editTextSchool.setTag(SCHOOL_ID_FOR_PRE);
            }


            editTextStreet.setText(LoginJson.get(0).getStreet());

            editTextSchoolAddress.setText(LoginJson.get(0).getSchoolAddress());


            editTextSports.setText(LoginJson.get(0).getSportName());
            editTextSports.setTag(UtilityClass.getPreferences(context, getString(R.string.sport_id_tag)));
            editTextSocialMedia.setText(LoginJson.get(0).getSocialHead());
            editTextPosition.setText(LoginJson.get(0).getPositionTitleName());

            editTextBodyfat.setText(LoginJson.get(0).getBodyFat());
            editTextSmm.setText(LoginJson.get(0).getSmm());
            editTextPosition.setTag(LoginJson.get(0).getPositionTitleId());

            editTextNeck.setText(LoginJson.get(0).getNeck());
            editTextBicep.setText(LoginJson.get(0).getBicep());
            editTextChest.setText(LoginJson.get(0).getChest());
            editTextWaist.setText(LoginJson.get(0).getWaist());
            editTextHips.setText(LoginJson.get(0).getHips());
            editTextThigh.setText(LoginJson.get(0).getThigh());


            if (UtilityClass.getPreferences(context, "unit_type") != null) {
                if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
                    editTextWeight.setText(ConvertImperialToMetrics(LoginJson.get(0).getWeight(), "weight"));
                    editTextheight.setText(ConvertImperialToMetrics(LoginJson.get(0).getHeight(), "height"));
                } else {
                    editTextWeight.setText(LoginJson.get(0).getWeight());
                    editTextheight.setText(LoginJson.get(0).getHeight());
                }
            }
            for (int x = 0; x < LoginJson.get(0).getSports().size(); x++) {
                for (int i = 0; i < sportsIdArray.length; i++) {
                    if (LoginJson.get(0).getSports().get(x).getSportId().equalsIgnoreCase(sportsIdArray[i])) {
                        checkedItemsSPORTS[i] = true;
                        selectedItemsSPORTS.add(sportsArray[i]);
                        selectedItemsSPORTSId.add(Integer.valueOf(SchoolDataService.sportsIdArray[i]));
                    }
                }
            }

            for (int x = 0; x < LoginJson.get(0).getTeams().size(); x++) {
                for (int i = 0; i < getTeamsDetailsClas.size(); i++) {
                    if (LoginJson.get(0).getTeams().get(x).getTeamId().equalsIgnoreCase(getTeamsDetailsClas.get(i).getTeamId())) {
                        checkedItemsTEAM[i] = true;
                        selectedItemsTeams.add(getTeamsDetailsClas.get(i).getTeamName());
                        selectedItemsTeamId.add(Integer.valueOf(getTeamsDetailsClas.get(i).getTeamId()));
                    }
                }
            }
        }

        editTextLastName.setSelection(editTextLastName.getText().length());
        editTextName.setSelection(editTextName.getText().length());
        editTextEmail.setSelection(editTextEmail.getText().length());
        editTextSuite.setSelection(editTextSuite.getText().length());
        editTextStreet.setSelection(editTextStreet.getText().length());
        editTextZip.setSelection(editTextZip.getText().length());
        editTextCountry.setSelection(editTextCountry.getText().length());
        editTextState.setSelection(editTextState.getText().length());
        editTextCity.setSelection(editTextCity.getText().length());
        editTextSchool.setSelection(editTextSchool.getText().length());
        editTextSchoolAddress.setSelection(editTextSchoolAddress.getText().length());
        editTextSocialMedia.setSelection(editTextSocialMedia.getText().length());
        editTextWeight.setSelection(editTextWeight.getText().length());
        editTextheight.setSelection(editTextheight.getText().length());
        editTextBodyfat.setSelection(editTextBodyfat.getText().length());
        editTextSmm.setSelection(editTextSmm.getText().length());
        editTextBirthday.setSelection(editTextBirthday.getText().length());
        editTextTeam.setSelection(editTextTeam.getText().length());
        editTextSports.setSelection(editTextSports.getText().length());
        editTextPosition.setSelection(editTextPosition.getText().length());
        editTextGender.setSelection(editTextGender.getText().length());
        editTextLanguage.setSelection(editTextLanguage.getText().length());
        editTextNeck.setSelection(editTextNeck.getText().length());

        editTextBicep.setSelection(editTextBicep.getText().length());

        editTextChest.setSelection(editTextChest.getText().length());

        editTextWaist.setSelection(editTextWaist.getText().length());

        editTextHips.setSelection(editTextHips.getText().length());

        editTextThigh.setSelection(editTextThigh.getText().length());

        setTextTeam();
        setTextSports();
    }

    private void initializeTextView() {

        textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));


        textViewName = findViewById(R.id.textViewName);
        textViewName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewLastName = findViewById(R.id.textViewLastName);
        textViewLastName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewEmail = findViewById(R.id.textViewEmail);
        textViewEmail.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewDOB = findViewById(R.id.textViewDOB);
        textViewDOB.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewGender = findViewById(R.id.textViewGender);
        textViewGender.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewLanguage = findViewById(R.id.textViewLanguage);
        textViewLanguage.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSuite = findViewById(R.id.textViewSuite);
        textViewSuite.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewStreet = findViewById(R.id.textViewStreet);
        textViewStreet.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewZip = findViewById(R.id.textViewZip);
        textViewZip.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewCountry = findViewById(R.id.textViewCountry);
        textViewCountry.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewState = findViewById(R.id.textViewState);
        textViewState.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewCity = findViewById(R.id.textViewCity);
        textViewCity.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSchool = findViewById(R.id.textViewSchool);
        textViewSchool.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSchoolAddress = findViewById(R.id.textViewSchoolAddress);
        textViewSchoolAddress.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        //textViewtem2 = (TextView) findViewById(R.id.textViewteamw);
        //textViewtem2.setTypeface(CustomTypeface.load_Montserrat_Light_Fonts(context));


        textViewPosition = findViewById(R.id.textViewPosition);
        textViewPosition.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewTeam = findViewById(R.id.textViewTeam);
        textViewTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSport = findViewById(R.id.textViewSport);
        textViewSport.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSocialMedia = findViewById(R.id.textViewSocialMedia);
        textViewSocialMedia.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewWeight = findViewById(R.id.textViewweight1);
        textViewWeight.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        textViewHeight = findViewById(R.id.textViewheight);
        textViewHeight.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        if (UtilityClass.getPreferences(context, "unit_type") != null) {
            if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
                textViewWeight.setText("Weight (KG)");
                textViewHeight.setText("Height (CM)");
            } else {
                textViewWeight.setText("Weight (LB)");
                textViewHeight.setText("Height (IN)");
            }
        }


        textViewBodyfat = findViewById(R.id.textViewbodyfat);
        textViewBodyfat.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSmm = findViewById(R.id.textViewsmm);
        textViewSmm.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

    }

    private void showChooseTeamItemAlertDialog(final View view, final String[] myArray) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);


        mBuilder.setMultiChoiceItems(myArray, checkedItemsTEAM, new DialogInterface.OnMultiChoiceClickListener() {
            int count = 0;

            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                if (isChecked) {
                    selectedItemsTeams.add(myArray[i]);
                    selectedItemsTeamId.add(Integer.valueOf(teamIdArray[i]));
                } else if (selectedItemsTeams.contains(myArray[i])) {
                    selectedItemsTeams.remove(myArray[i]);
                    selectedItemsTeamId.remove(Integer.valueOf(teamIdArray[i]));
                }
            }
        }).setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                setTextTeam();
                hideKeyboard(view);
                showChooseSportsItemAlertDialog(editTextSports, sportsArray, sportsIdArray);
            }
        }).show();
    }

    private void showChooseSportsItemAlertDialog(final View view, final String[] myArray, String[] sportsIdArray) {


        new AlertDialog.Builder(context).setMultiChoiceItems(myArray, checkedItemsSPORTS, new DialogInterface.OnMultiChoiceClickListener() {
            int count = 0;

            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                if (isChecked) {
                    selectedItemsSPORTS.add(myArray[i]);
                    selectedItemsSPORTSId.add(Integer.valueOf(SchoolDataService.sportsIdArray[i]));
                } else if (selectedItemsSPORTS.contains(myArray[i])) {
                    selectedItemsSPORTS.remove(myArray[i]);
                    selectedItemsSPORTSId.remove(Integer.valueOf(SchoolDataService.sportsIdArray[i]));
                }
            }
        }).setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                setTextSports();
                editTextSocialMedia.requestFocus();
                showKeyboard();
            }
        }).show();
    }

    private void setTextTeam() {
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(selectedItemsTeams);
        selectedItemsTeams.clear();
        selectedItemsTeams.addAll(hashSet);

        HashSet<Integer> hashSet2 = new HashSet<Integer>();
        hashSet2.addAll(selectedItemsTeamId);
        selectedItemsTeamId.clear();
        selectedItemsTeamId.addAll(hashSet2);

        editTextTeam.setText("");
        String selectedIndex = "";
        for (int os = 0; os < selectedItemsTeams.size(); os++) {
            selectedIndex += selectedItemsTeams.get(os) + ", ";
        }

        if (selectedIndex.endsWith(", ")) {
            selectedIndex = selectedIndex.substring(0, selectedIndex.length() - 2);
        }
        editTextTeam.setText(selectedIndex);
    }

    private void setTextSports() {
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(selectedItemsSPORTS);
        selectedItemsSPORTS.clear();
        selectedItemsSPORTS.addAll(hashSet);

        HashSet<Integer> hashSet2 = new HashSet<Integer>();
        hashSet2.addAll(selectedItemsSPORTSId);
        selectedItemsSPORTSId.clear();
        selectedItemsSPORTSId.addAll(hashSet2);

        editTextSports.setText("");
        String selectedIndex = "";
        for (int os = 0; os < selectedItemsSPORTS.size(); os++) {
            selectedIndex += selectedItemsSPORTS.get(os) + ", ";
        }

        Log.e(VolleyLog.TAG, "onClick: " + selectedIndex);
        if (selectedIndex.endsWith(", ")) {
            selectedIndex = selectedIndex.substring(0, selectedIndex.length() - 2);
        }
        editTextSports.setText(selectedIndex);
    }

    private void showChooseItemAlertDialog(final View view, final String[] myArray) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//        mBuilder.setTitle(getString(R.string.app_name));
        mBuilder.setSingleChoiceItems(myArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (view.getId()) {
                    case R.id.editTextSchool:
                        hideKeyboard(view);
                        editTextSchool.setText(myArray[i]);
                        editTextSchool.setTag(schoolIdArray[i]);
                        editTextSchoolAddress.requestFocus();
                        showKeyboard();
                        break;
                    case R.id.editTextSchoolAddress:
                        hideKeyboard(view);
                        editTextSchoolAddress.setText(myArray[i]);
                        editTextSchoolAddress.setTag(schoolIdArray[i]);
                    case R.id.editTextTeam:
                        hideKeyboard(view);
                        // editTextTeam.setText(myArray[i]);
                        //editTextTeam.setTag(teamIdArray[i]);
                        break;
                    case R.id.editTextPosition:
                        hideKeyboard(view);
                        editTextPosition.setText(myArray[i]);
                        editTextPosition.setTag(positionTitleIdArray[i]);
                        hideKeyboard(view);
                        showChooseSportsItemAlertDialog(editTextSports, sportsArray, sportsIdArray);
                        break;
                    case R.id.editTextSport:
                        hideKeyboard(view);
//                        editTextSports.setText(myArray[i]);
//                        editTextSports.setTag(sportsIdArray[i]);


                        editTextSocialMedia.requestFocus();
                        showKeyboard();

                        break;
                    case R.id.editTextLanguage:
                        hideKeyboard(view);
                        editTextLanguage.setText(myArray[i]);
                        editTextLanguage.setTag(getLanguageList.get(i).getLanguageId());
                        editTextLanguage.clearFocus();
                        editTextSuite.requestFocus();
                        showKeyboard();
                        break;
                }

                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, result);
        if (result != null && result.trim().length() > 0) {
            if (whichApiCalled.equalsIgnoreCase("language")) {
                UtilityClass.SetPreferences(context, "language", result);
                parseRequiredData(result);
//            } else if (whichApiCalled.equalsIgnoreCase("updateprofile")) {
//
            } else {
                //  ////Toast.makeText(context, "resuly", Toast.LENGTH_SHORT).show();
                parseProfileData(result);
            }

        }
    }

    private void parseProfileData(String result) {


        // Log.e("&&&&&&&&&&&&&&&&&&&&", "" + result);
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj
                    .getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");

            Log.e("**********", "" + responseMessage);
            if (WebServices.responseCode == 200) {
                String usersData = jsonObj
                        .getString("data");
                if (whichApiCalled.equalsIgnoreCase("getTimeZone")) {
                    timezoneLists =
                            new ArrayList<>(Arrays.asList(new Gson().fromJson(usersData, timezoneList[].class)));
                    hide();
                    return;
                }

                JSONObject userJson = new JSONObject(usersData);
                for (int x = 0; x < usersData.length(); x++) {
                    Object json = userJson.get("selected_athlete_level");
                    Object json1 = userJson.get("assing_program_details");
                    Object json2 = userJson.get("selected_athlete_goal");
                    Object json3 = userJson.get("athlete_level");
                    Object teams = userJson.get("teams");
                    if (json instanceof String) {
                        userJson.put("selected_athlete_level", new JSONArray());
                    }
                    if (json1 instanceof String) {
                        userJson.put("assing_program_details", new JSONArray());
                    }
                    if (json2 instanceof String) {
                        userJson.put("selected_athlete_goal", new JSONArray());
                    }
                    if (json3 instanceof String) {
                        userJson.put("athlete_level", new JSONArray());
                    }
                    if (teams instanceof String) {
                        userJson.put("teams", new JSONArray());
                    }
                }
                if (coach) {
                    //selectedIndex = "";
                    Gson gson = new Gson();
                    JSONArray Obj = new JSONArray();
                    Obj.put(userJson);

                    List<SelectedAthleteDEtail> list = new ArrayList<>(Arrays.asList(gson.fromJson(Obj.toString(), SelectedAthleteDEtail[].class)));

//                     List<SelectedAthleteDEtail> selectedAthleteDEtails = new ArrayList<>();
//                    selectedAthleteDEtails = Arrays.asList(gson.fromJson(Obj.toString(), selectedAthleteDEtails[].class));

                    // AthleteData.get(ProfileScreenActivity.position)(loginData);

                    //AthleteData.addAll(ProfileScreenActivity.position,selectedAthleteDEtails);


                    AthleteData.get(ProfileScreenActivity.position).setFirstName(list.get(0).getFirstName());
                    AthleteData.get(ProfileScreenActivity.position).setTimezone(list.get(0).getTimezone());
                    AthleteData.get(ProfileScreenActivity.position).setLastName(list.get(0).getLastName());
                    AthleteData.get(ProfileScreenActivity.position).setEmailId(list.get(0).getEmailId());
                    AthleteData.get(ProfileScreenActivity.position).setGender(list.get(0).getGender());
                    AthleteData.get(ProfileScreenActivity.position).setLanguageId(list.get(0).getLanguageId());
                    AthleteData.get(ProfileScreenActivity.position).setLanguageName(list.get(0).getLanguageName());
                    AthleteData.get(ProfileScreenActivity.position).setDob(list.get(0).getDob());
                    AthleteData.get(ProfileScreenActivity.position).setSuit(list.get(0).getSuit());
                    AthleteData.get(ProfileScreenActivity.position).setStreet(list.get(0).getStreet());
                    AthleteData.get(ProfileScreenActivity.position).setZip(list.get(0).getZip());
                    AthleteData.get(ProfileScreenActivity.position).setCountry(list.get(0).getCountry());
                    AthleteData.get(ProfileScreenActivity.position).setState(list.get(0).getState());
                    AthleteData.get(ProfileScreenActivity.position).setCity(list.get(0).getCity());
                    AthleteData.get(ProfileScreenActivity.position).setSchoolId(list.get(0).getSchoolId());
                    AthleteData.get(ProfileScreenActivity.position).setSchoolName(list.get(0).getSchoolName());
                    AthleteData.get(ProfileScreenActivity.position).setSchoolAddress(list.get(0).getSchoolAddress());
                    AthleteData.get(ProfileScreenActivity.position).setPositionTitleId(list.get(0).getPositionTitleId());
                    try {
                        AthleteData.get(ProfileScreenActivity.position).setSports(list.get(0).getSports());
                        AthleteData.get(ProfileScreenActivity.position).setTeams(list.get(0).getTeams());
                    } catch (Exception c) {

                    }

                    AthleteData.get(ProfileScreenActivity.position).setSportId(list.get(0).getSportId());
                    AthleteData.get(ProfileScreenActivity.position).setWeight(list.get(0).getWeight());
                    AthleteData.get(ProfileScreenActivity.position).setHeight(list.get(0).getHeight());
                    AthleteData.get(ProfileScreenActivity.position).setBodyFat(list.get(0).getBodyFat());
                    AthleteData.get(ProfileScreenActivity.position).setSmm(list.get(0).getSmm());
                    AthleteData.get(ProfileScreenActivity.position).setSocialMedia(list.get(0).getSocialHead());

                    AthleteData.get(ProfileScreenActivity.position).setNeck(list.get(0).getNeck());
                    AthleteData.get(ProfileScreenActivity.position).setBicep(list.get(0).getBicep());
                    AthleteData.get(ProfileScreenActivity.position).setChest(list.get(0).getChest());
                    AthleteData.get(ProfileScreenActivity.position).setWaist(list.get(0).getWaist());
                    AthleteData.get(ProfileScreenActivity.position).setHips(list.get(0).getHips());
                    AthleteData.get(ProfileScreenActivity.position).setThigh(list.get(0).getThigh());
                    //////Toast.makeText(context, loginData.get(0).getSocialMedia()+"", Toast.LENGTH_SHORT).show();


                } else {
                    selectedIndex = "";
                    Gson gson = new Gson();
                    JSONArray Obj = new JSONArray();
                    Obj.put(userJson);
                    loginData = Arrays.asList(gson.fromJson(Obj.toString(), Login[].class));
                    LoginJson = new ArrayList<>(loginData);


//                    userId = userJson
//                            .getString("user_id");
//                   LoginJson
//                    LoginJson.add(new Login(userId,loginData.get(0).getEmailId(),loginData.get(0).getPasswordType(),loginData.get(0).getSchoolId()
//                    ,loginData.get(0).getSchoolName(),loginData.get(0).getPositionTitleId(),loginData.get(0).getPositionTitleName(),loginData.get(0).getUserType(),
//                            loginData.get(0).getFirstName(),loginData.get(0).getLastName(),loginData.get(0).getDob(),loginData.get(0).getGender(),loginData.get(0).getDeviceToken(),
//                            loginData.get(0).getLanguageName(),loginData.get(0).getSuit(),loginData.get(0).getStreet(),loginData.get(0).getZip(),loginData.get(0).getCountry(),
//                            loginData.get(0).getCity(),loginData.get(0).getSchoolAddress(),loginData.get(0).getSportId(),loginData.get(0).getSportName(),loginData.get(0).getSocialHead()
//                    ,loginData.get(0).getTeamName(),loginData.get(0).getAthletePhoto(),loginData.get(0).getAddress(),loginData.get(0).getAge(),loginData.get(0).getSportLevel(),
//                            loginData.get(0).getSocialMedia(),loginData.get(0).getMusic(),loginData.get(0).getUserImage(),loginData.get(0).getHeight(),loginData.get(0).getWeight(),loginData.get(0).getHeightUnit(),
//                            loginData.get(0).getWeightUnit(),loginData.get(0).getSmm(),loginData.get(0).getBodyFat(),loginData.get(0).getGymAccountId(),loginData.get(0).getAthleteRefcoachId(),
//                            loginData.get(0).getQuestionnaire(),loginData.get(0).getSets(),loginData.get(0).getGymAccountId(),loginData.get(0).getAthleteRefcoachId(),loginData.get(0).getCoachReferby(),
//                            loginData.get(0).getQuestionnaire(),loginData.get(0).getSets()));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.user_id_tag), userId);

                    UtilityClass.SetPreferences(context,
                            getString(R.string.school_id_tag),
                            userJson.getString(getString(R.string.school_id_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.school_name_tag),
                            userJson
                                    .getString(getString(R.string.school_name_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.position_title_name_tag),
                            userJson
                                    .getString(getString(R.string.position_title_name_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.position_title_id_tag),
                            userJson
                                    .getString(getString(R.string.position_title_id_tag)));

                    String userType = userJson
                            .getString(getString(R.string.user_type_tag));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.user_type_tag),
                            userType);

                    UtilityClass.SetPreferences(context,
                            getString(R.string.background_image_tag),
                            userJson
                                    .getString(getString(R.string.background_image_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.emailId_tag),
                            userJson
                                    .getString(getString(R.string.emailId_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.first_name_tag),
                            userJson
                                    .getString(getString(R.string.first_name_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.last_name_tag),
                            userJson
                                    .getString(getString(R.string.last_name_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.dob_tag),
                            userJson
                                    .getString(getString(R.string.dob_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.gender_tag),
                            userJson
                                    .getString(getString(R.string.gender_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.language_name_tag),
                            userJson
                                    .getString(getString(R.string.language_name_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.language_id_tag),
                            userJson
                                    .getString(getString(R.string.language_id_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.suit_tag),
                            userJson
                                    .getString(getString(R.string.suit_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.street_tag),
                            userJson
                                    .getString(getString(R.string.street_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.zip_tag),
                            userJson
                                    .getString(getString(R.string.zip_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.country_tag),
                            userJson
                                    .getString(getString(R.string.country_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.state_tag),
                            userJson
                                    .getString(getString(R.string.state_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.city_tag),
                            userJson
                                    .getString(getString(R.string.city_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.school_address_tag),
                            userJson
                                    .getString(getString(R.string.school_address_tag)));
//                    UtilityClass.SetPreferences(context,
//                            getString(R.string.team_id_tag),
//                            userJson
//                                    .getString(getString(R.string.team_id_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.sport_id_tag),
                            userJson
                                    .getString(getString(R.string.sport_id_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.sport_name_tag),
                            userJson
                                    .getString(getString(R.string.sport_name_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.weight1_tag),
                            userJson
                                    .getString(getString(R.string.weight1_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.height1_tag),
                            userJson.getString(getString(R.string.height1_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.bodyfat1_tag),

                            userJson.getString(getString(R.string.bodyfat1_tag)));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.smm1_tag),
                            userJson.getString(getString(R.string.smm1_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.social_head_tag),
                            userJson
                                    .getString(getString(R.string.social_head_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.team_name_tag),
                            userJson
                                    .getString(getString(R.string.team_name_tag)));

                    UtilityClass.SetPreferences(context,
                            getString(R.string.athlete_photo_tag),
                            userJson
                                    .getString(getString(R.string.athlete_photo_tag)));
                }

                final custom_alert_class mAlert = new custom_alert_class(context);
                mAlert.setMessage(responseMessage);
                mAlert.OneButton(true);
                mAlert.setNegativeButton("Ok", new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Athlete_profile_health_bodyFx();
                        finish();
                        onBackPressed();

                        mAlert.dismiss();
                    }
                });
                mAlert.show();
//                new AlertDialog.Builder(this)
//                        //set icon
//                        .setIcon(getResources().getDrawable(R.drawable.logo_main_small_alert))
//                        //set title
//                        .setTitle(getResources().getString(R.string.app_name))
//                        //set message
//                        .setMessage(responseMessage)
//                        //set positive button
//                        .setPositiveButton("OK", (dialogInterface, i) -> {
//                            finish();
//                            onBackPressed();
//                        })
//                        .show();


//                UtilityClass.showAlertDailog(context,responseMessage);
            }

            //UtilityClass.showAlertDailog(context, responseMessage);
            hide();
        } catch (JSONException e) {

            e.printStackTrace();
            hide();
            Log.e("Error in json parsing", e.getMessage());
        } catch (Exception e) {

            e.printStackTrace();
            hide();
        }
        setText();
        //initializeEditText();
        hide();
    }

    private void hideKeyboard(View view) {
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        try {

            setText();
            setAddressFromMap = false;
        } catch (Exception v) {
        }
    }

    private void showChooseItemAlertDialog(final View view) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//        mBuilder.setTitle(getString(R.string.app_name));
        switch (view.getId()) {
            case R.id.editTextGender:
                mBuilder.setTitle(getString(R.string.select_gender));
                singleItemsChooseArray = getResources().getStringArray(R.array.gender_option);

                break;
            case R.id.editTextLanguage:
                mBuilder.setTitle(getString(R.string.select_language));
                singleItemsChooseArray = getResources().getStringArray(R.array.language_option);

                break;
            case R.id.editTextSport:
                mBuilder.setTitle(getString(R.string.select_sport));
                singleItemsChooseArray = getResources().getStringArray(R.array.sports_option);
                break;
        }


        mBuilder.setSingleChoiceItems(singleItemsChooseArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (view.getId()) {
                    case R.id.editTextGender:
                        editTextGender.setText(singleItemsChooseArray[i]);
                        hideKeyboard(view);
                        SelectionTypeOfList = "language";
                        custom_popup_class mAlert = new custom_popup_class(context, "language", editTextLanguage.getTag().toString(), (RecyclerViewClickCheck) context);
                        mAlert.setMessage("SELECT LANGUAGE");
                        mAlert.show();
                        break;
                    case R.id.editTextLanguage:
                        editTextLanguage.setText(singleItemsChooseArray[i]);
                        editTextSuite.requestFocus();
                        break;
                    case R.id.editTextSport:
                        editTextSports.setText(singleItemsChooseArray[i]);
                        break;
                }

                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

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

            Log.e("**********", "" + responseMessage);

//            vectorSportsData = new Vector<SportsDataClass>();
            JSONArray jsonDataArray = jsonObj
                    .getJSONArray("data");

        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void configureCameraIdle() {
        onCameraIdleListener = () -> {
            locationc = true;

            LatLng latLng = mGoogleMap.getCameraPosition().target;
            Geocoder geocoder = new Geocoder(this);


            try {
                addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                if (addressList != null && addressList.size() > 0) {
                    String locality = addressList.get(0).getAddressLine(0);
                    String country = addressList.get(0).getCountryName();

                    if (!locality.isEmpty() && !country.isEmpty()) {
                        text.setText(locality + "  " + country);
                    }
                    locImg.setVisibility(View.VISIBLE);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception m) {

            }

        };

    }

    public void changeMapType() {
        if (mGoogleMap != null) {
            int type = mGoogleMap.getMapType();
            switch (type) {
                case GoogleMap.MAP_TYPE_NORMAL:
                    mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                    break;
                case GoogleMap.MAP_TYPE_SATELLITE:
                    mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                    break;
                case GoogleMap.MAP_TYPE_TERRAIN:
                    mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                    break;
                case GoogleMap.MAP_TYPE_HYBRID:
                    mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    break;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mGoogleMap.setBuildingsEnabled(true);
        final float mCameraZoom = 10.00f;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); //this part;
        mGoogleMap.setTrafficEnabled(true);
        mGoogleMap.setIndoorEnabled(true);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 1000, new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
                mGoogleMap.moveCamera(cameraUpdate);
                locationManager.removeUpdates(this);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });


        // Change the visibility of my location button


        mGoogleMap.setOnCameraIdleListener(onCameraIdleListener);
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);

            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
        mGoogleMap.setOnMyLocationButtonClickListener(() -> {
            //TODO: Any custom actions
            return false;
        });

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        if (locationc) {
            return;
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location
                        .getLongitude())) //
                .zoom(9).build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mGoogleMap.setOnMyLocationButtonClickListener(() -> false);
        //  getMyLocation(location);

//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
//        move map camera


    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getParent(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}

