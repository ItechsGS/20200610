package com.org.godspeed.allOtherClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.RecyclerViewClickCheck;
import com.org.godspeed.response_JsonS.GetSchedules.Timing;
import com.org.godspeed.response_JsonS.GetSport.GetSport;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.GetTeamsDetailsClas;
import com.org.godspeed.response_JsonS.getTeams.GetTeam;
import com.org.godspeed.response_JsonS.getTeams.TeamCoach;
import com.org.godspeed.response_JsonS.getTeams.TeamSport;
import com.org.godspeed.service.SchoolDataService;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.SportsDataClass;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_popup_class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import static com.android.volley.VolleyLog.TAG;
import static com.org.godspeed.service.BackgroundLocationUpdateService.CallApiForTeamByCoach;
import static com.org.godspeed.service.SchoolDataService.getPositionList;
import static com.org.godspeed.service.SchoolDataService.getTeamDataFromServer;
import static com.org.godspeed.service.SchoolDataService.getTeamsDetailsClas;
import static com.org.godspeed.service.SchoolDataService.sportsArray;
import static com.org.godspeed.service.SchoolDataService.sportsIdArray;
import static com.org.godspeed.service.SchoolDataService.teamIdArray;

//import com.handstudio.android.hzgraphlib.vo.scattergraph.ScatterGraph;
//

public class AddTeamCoachAthleteScreen extends Activity implements GodSpeedInterface, RecyclerViewClickCheck {
    public static boolean AddteamBoolean = false;
    public final Pattern EMAIL_ADDRESS_PATTERN =
            Pattern.compile("[a-zA-Z0-9+._%-+]{1,256}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "." + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
    List<RecyclerData> recyclerDataList = new ArrayList<>();
    ArrayList<String> classTimeArray = new ArrayList<String>();
    ClassTime classTime;
    boolean[] checkedItemsSPORTS;
    boolean[] checkedItemsTEAM = null;
    ArrayList<String> selectedItemsTeams = new ArrayList<>();
    ArrayList<Integer> selectedItemsTeamId = new ArrayList<>();
    ArrayList<String> selectedItemsSPORTS = new ArrayList<>();
    ArrayList<Integer> selectedItemsSPORTSId = new ArrayList<>();
    RecyclerView recyclerViewForAddclassTime, recyclerViewForCoachOfTeam;
    String TimeOfClass = "";
    StringBuilder TimeofClasses;
    RecyclerData recyclerData;
    ScrollView rLayoutForAddTeam;
    CalendarEventAdapterTiming calendarEventAdapterTiming;
    private List<com.org.godspeed.response_JsonS.getTeams.GetTeam> GetTeamX;
    private String screenName = "";
    private TextView textViewScreenName;
    private EditText editTextSport, editTextTeam, editTextAge, editTextName, editTextLastName, editTextCoachName, editTextClass, editTextGender, editTextEmail;
    private EditText editTextEmailAthlete, editTextPosition, editTextSelectTeam;
    private ImageView imageViewBackArrow, imageViewSave;
    private String whichApiCalled = "";
    private Context context;
    private String[] schoolArray = null, teamArray = null, genderArray = null, languageArray = null;
    private Vector<SportsDataClass> vectorSportsData = null;
    private TextView textViewCoachName, textViewEmail, textViewPosition, textViewTeam, textViewSport, textViewAthleteName, textViewEmailAthlete, textViewAge, textViewGender, textViewClass;
    private TextView textViewName, textViewLastName, textViewSelectTeamText;
    private String[] arr;
    private ImageView imageViewForZoomInOut;
    private Animation zoomIn, zoomOut;
    private boolean isAnimationStarted;
    private Dialog dialog;
    private String SelectedTeamName = "";
    private String Type = "";
    private int TeamPosition;
    private String team_id = "";
    private RelativeLayout rLayoutForAddCoach, rLayoutForAddAthlete;
    private String CoachName = "";
    private RelativeLayout rLAyoutForPosition, rLAyoutForCoachOfTeam;


    private String[] positionTitleIdArray, positionTitleArray;
    private String SelectionTypeOfList = "";

    @Override
    public void OnItemClickListReturn(List<GetTeamsDetailsClas> teamListX, List<GetSport> getSportListX) {
        if (SelectionTypeOfList.equalsIgnoreCase("teamList")) {

            selectedItemsTeamId = new ArrayList<>();
            selectedItemsTeams = new ArrayList<>();
            for (int os = 0; os < teamListX.size(); os++) {
                selectedItemsTeamId.add(Integer.valueOf(teamListX.get(os).getTeamId()));
                selectedItemsTeams.add(teamListX.get(os).getTeamName());
            }


            setTextTeam(editTextSelectTeam);
            //editTextTeam.setText(selectedIndex);
        } else if (SelectionTypeOfList.equalsIgnoreCase("sports")) {
            // selectedIndex = "";
            selectedItemsSPORTSId = new ArrayList<>();
            selectedItemsSPORTS = new ArrayList<>();
            for (int os = 0; os < getSportListX.size(); os++) {
                selectedItemsSPORTSId.add(Integer.valueOf(getSportListX.get(os).getSportId()));
                selectedItemsSPORTS.add(getSportListX.get(os).getSportName());
            }

            setTextSports();
        }
        //setText();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_team_coach_athlete);
        context = this;
        dialog = new Dialog(this);

        screenName = getIntent().getExtras().getString("screenName");

        Type = getIntent().getExtras().getString("Type") != null ? getIntent().getExtras().getString("Type") : "";

        TeamPosition = getIntent().getExtras().getInt("TeamPosition");

        team_id = getIntent().getExtras().getString("Teamid") != null ? getIntent().getExtras().getString("Teamid") : "";


        editTextSport = findViewById(R.id.editTextSport);
        editTextTeam = findViewById(R.id.editTextTeam);
        rLAyoutForPosition = findViewById(R.id.rLAyoutForPosition);
        //editTextrLAyoutForCoachOfTeam = findViewById(R.id.editTextrLAyoutForCoachOfTeam);

        classTimeArray.add("i");

        try {
            arr = new String[getTeamsDetailsClas.size() - 1];
            for (int i = 0; i < getTeamsDetailsClas.size(); i++) {
                if (!getTeamsDetailsClas.get(i).getTeamName().equalsIgnoreCase("All Athlete")) {
                    arr[i] = getTeamsDetailsClas.get(i).getTeamName();
                }
            }
        } catch (Exception x) {

        }

        positionTitleArray = new String[getPositionList.size()];
        positionTitleIdArray = new String[getPositionList.size()];
        for (int i = 0; i < getPositionList.size(); i++) {
            positionTitleArray[i] = getPositionList.get(i).getPositionTitleName();
            positionTitleIdArray[i] = getPositionList.get(i).getPositionTitleId();
        }


        recyclerViewForCoachOfTeam = findViewById(R.id.recyclerViewForCoachOfTeam);


        recyclerViewForCoachOfTeam.setLayoutManager(new LinearLayoutManager(context));


//        for (int i = 0; i < sportsIdArray.length; i++) {
//            if (LoginJson.get(0).getSports().get(x).getSportId().equals(sportsIdArray[i])) {
//                checkedItemsSPORTS[i] = true;
//                selectedItemsSPORTS.add(myArray[i]);
//                selectedItemsSPORTSId.add(Integer.valueOf(SchoolDataService.sportsIdArray[i]));
//            }
//        }

        try {
            checkedItemsSPORTS = new boolean[sportsArray.length];
        } catch (Exception m) {

        }
        try {
            checkedItemsTEAM = new boolean[arr.length];
        } catch (Exception m) {

        }

        try {
            for (int i = 0; i < sportsArray.length; i++) {
                checkedItemsSPORTS[i] = false;
            }

            for (int i = 0; i < arr.length; i++) {
                checkedItemsTEAM[i] = false;
            }
        } catch (Exception m) {

        }

        try {
            selectedItemsSPORTS = new ArrayList<>();
            if (Type.equalsIgnoreCase("EditTeam")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int m = 0; m < sportsArray.length; m++) {
                            for (int c = 0; c < SchoolDataService.GetTeam.get(TeamPosition).getTeamSports().size(); c++) {
                                if (sportsArray[m].equalsIgnoreCase(SchoolDataService.GetTeam.get(TeamPosition).getTeamSports().get(c).getSportName())) {
                                    checkedItemsSPORTS[m] = true;
                                    selectedItemsSPORTS.add(sportsArray[m]);
                                    selectedItemsSPORTSId.add(Integer.valueOf(SchoolDataService.sportsIdArray[m]));
                                }
                            }
                        }//setTextSports();
                        setTextSports();
                    }
                }, 300);
            } else if (Type.equalsIgnoreCase("EditTeamSchedule")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<TeamSport> teamSports = new ArrayList<>();
                        Gson gson = new Gson();
                        teamSports = new ArrayList<>(Arrays.asList(gson.fromJson(getIntent().getExtras().getString("TeamSports"), TeamSport[].class)));


                        if (teamSports == null) {
                            return;
                        }
                        for (int m = 0; m < sportsArray.length; m++) {
                            for (int c = 0; c < teamSports.size(); c++) {
                                if (sportsArray[m].equalsIgnoreCase(teamSports.get(c).getSportName())) {
                                    checkedItemsSPORTS[m] = true;
                                    selectedItemsSPORTS.add(sportsArray[m]);
                                    selectedItemsSPORTSId.add(Integer.valueOf(SchoolDataService.sportsIdArray[m]));
                                }
                            }
                        }
                        setTextSports();
                    }
                }, 300);
            }


        } catch (Exception m) {
            Log.e(VolleyLog.TAG, "onCreate:onCreate " + m);
        }


        imageViewForZoomInOut = findViewById(R.id.imageViewForZoomInOut);
        rLAyoutForCoachOfTeam = findViewById(R.id.rLAyoutForCoachOfTeam);


        // recyclerDataList.add(new RecyclerData("00:00", "00:00"));

        try {
            if (Type.equalsIgnoreCase("EditTeam")) {
                if (SchoolDataService.GetTeam.get(TeamPosition).getCoachClassTiming().size() > 0) {
                    for (int c = 0; c < SchoolDataService.GetTeam.get(TeamPosition).getCoachClassTiming().size(); c++) {
                        recyclerDataList.add(new RecyclerData(SchoolDataService.GetTeam.get(TeamPosition).getCoachClassTiming().get(c).getFrom(),
                                SchoolDataService.GetTeam.get(TeamPosition).getCoachClassTiming().get(c).getTo()));
                    }
                } else {
                    recyclerDataList.add(new RecyclerData("00:00", "00:00"));
                }
                StringBuilder stringBuilder = new StringBuilder();

//                Log.e(VolleyLog.TAG, "onCreate:3 " + team_id);
//                for (int c = 0; c < SchoolDataService.GetTeam.size(); c++) {
//                    Log.e(VolleyLog.TAG, "onCreate:2 " + new Gson().toJson(SchoolDataService.GetTeam.get(c).getTeamCoaches()));
//                    if (team_id.equalsIgnoreCase(SchoolDataService.GetTeam.get(c).getTeamId())) {
//                        calendarEventAdapterTiming = new CalendarEventAdapterTiming(SchoolDataService.GetTeam.get(c).getTeamCoaches());
//                        recyclerViewForCoachOfTeam.setAdapter(calendarEventAdapterTiming);
//                        rLAyoutForCoachOfTeam.setVisibility(View.VISIBLE);
//                        Log.e(VolleyLog.TAG, "onCreate:SLECTED " + SchoolDataService.GetTeam.get(c).getTeamId() + "    " + team_id);
//                    }
//                }
            } else if (Type.equalsIgnoreCase("EditTeamSchedule")) {

                List<Timing> timings = new ArrayList<>();
                Gson gson = new Gson();
                timings = new ArrayList<>(Arrays.asList(gson.fromJson(getIntent().getExtras().getString("Timing"), Timing[].class)));

                timings.forEach(m -> {
                    recyclerDataList.add(new RecyclerData(m.getFrom(), m.getTo()));
                });
                if (recyclerDataList.size() == 0) {
                    recyclerDataList.add(new RecyclerData("00:00", "00:00"));
                    Log.e(VolleyLog.TAG, "recyclerDataList: " + recyclerDataList.size());
                }
            } else {
                recyclerDataList.add(new RecyclerData("00:00", "00:00"));
            }
        } catch (Exception v) {
            recyclerDataList.add(new RecyclerData("00:00", "00:00"));
            Log.e(VolleyLog.TAG, "onCreate: " + v);
        }

        try {
            if (Type.equalsIgnoreCase("EditTeam")) {
                for (int c = 0; c < SchoolDataService.GetTeam.size(); c++) {
                    Log.e(VolleyLog.TAG, "onCreate:2 " + new Gson().toJson(SchoolDataService.GetTeam.get(c).getTeamCoaches()));
                    if (team_id.equalsIgnoreCase(SchoolDataService.GetTeam.get(c).getTeamId())) {
                        calendarEventAdapterTiming = new CalendarEventAdapterTiming(SchoolDataService.GetTeam.get(c).getTeamCoaches());
                        recyclerViewForCoachOfTeam.setAdapter(calendarEventAdapterTiming);
                        rLAyoutForCoachOfTeam.setVisibility(View.VISIBLE);
                        Log.e(VolleyLog.TAG, "onCreate:SLECTED " + SchoolDataService.GetTeam.get(c).getTeamId() + "    " + team_id);
                    }
                }
            }
        } catch (Exception v) {

        }

        textViewScreenName = findViewById(R.id.textViewScreenName);


        classTime = new ClassTime();


        recyclerViewForAddclassTime = findViewById(R.id.recyclerViewForAddclassTime);


        recyclerViewForAddclassTime.setAdapter(classTime);

        recyclerViewForAddclassTime.setLayoutManager(new LinearLayoutManager(context));

        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        textViewScreenName.setText(screenName);

        initializeTextView();

        rLayoutForAddCoach = findViewById(R.id.rLayoutForAddCoach);

        rLayoutForAddCoach.setVisibility(View.GONE);

        rLayoutForAddTeam = findViewById(R.id.rLayoutForAddTeam);
        rLayoutForAddTeam.setVisibility(View.GONE);

        rLayoutForAddAthlete = findViewById(R.id.rLayoutForAddAthlete);
        rLayoutForAddAthlete.setVisibility(View.GONE);

        if (screenName.equalsIgnoreCase(getString(R.string.add_athlete))) {
            // athlete relative layout need to show
            rLayoutForAddAthlete.setVisibility(View.VISIBLE);
        } else if (screenName.equalsIgnoreCase(getString(R.string.add_coach))) {
            // coach relative layout need to show
            rLayoutForAddCoach.setVisibility(View.VISIBLE);
            if (Type.equalsIgnoreCase("AddCoach")) {
                textViewScreenName.setText(getString(R.string.add_coach) + " - " + getIntent().getExtras().getString("teamName"));
            }
            rLAyoutForPosition.setVisibility(View.GONE);
        } else if (screenName.equalsIgnoreCase(getString(R.string.add_team))) {
            // team relative layout need to show
            rLayoutForAddTeam.setVisibility(View.VISIBLE);
        }

        editTextCoachName = findViewById(R.id.editTextCoachName);
        editTextCoachName.setHint(getString(R.string.enter) + " " + getString(R.string.coach_name));
        editTextCoachName.setSingleLine(true);
        editTextCoachName.setText("");
        editTextCoachName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.setHint(getString(R.string.enter) + " " + getString(R.string.email));
        editTextEmail.setSingleLine(true);
        editTextEmail.setText("");
        editTextEmail.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextSelectTeam = findViewById(R.id.editTextSelectTeam);
        editTextSelectTeam.setSingleLine(true);
        editTextSelectTeam.setText("");
        editTextSelectTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextEmailAthlete = findViewById(R.id.editTextEmailAthlete);
        editTextEmailAthlete.setHint(getString(R.string.enter) + " " + getString(R.string.email));
        editTextEmailAthlete.setSingleLine(true);
        editTextEmailAthlete.setText("");
        editTextEmailAthlete.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextPosition = findViewById(R.id.editTextPosition);
        editTextPosition.setHint(getString(R.string.enter) + " " + getString(R.string.position));
        editTextPosition.setSingleLine(true);
        editTextPosition.setText("");
        editTextPosition.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextPosition.setOnClickListener(view -> {
            hideKeyboard(view);
            showChooseItemAlertDialog(editTextPosition, positionTitleArray); // for coach
        });


        editTextSelectTeam.setOnClickListener(view -> {
            //Toast.makeText(context, arr+"", Toast.LENGTH_SHORT).show();
            hideKeyboard(view);
            SelectionTypeOfList = "teamList";
            custom_popup_class mAlert = new custom_popup_class(context, "teamList",
                    "",
                    (RecyclerViewClickCheck) context,
                    selectedItemsTeamId);
            mAlert.setMessage("SELECT TEAM");
            mAlert.show();
//            showChooseTeamItemAlertDialog(editTextSelectTeam, arr);
//            hideKeyboard(view);
        });


        //editTextTeam = findViewById(R.id.editTextTeam);
        editTextTeam.setHint(getString(R.string.enter) + " " + getString(R.string.team) + " Name");
        editTextTeam.setSingleLine(true);
        editTextTeam.setText("");
        editTextTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        try {
            SelectedTeamName = getIntent().getExtras().getString("teamName");
            editTextTeam.setText(SelectedTeamName);
        } catch (Exception v) {

        }


        // editTextTeam.setFocusable(false);
//        editTextTeam.setOnClickListener(view -> {
//            try {
//                arr = new String[CoachBoardFragments.GetTeam.size()];
//                for (int i = 0; i < CoachBoardFragments.GetTeam.size(); i++) {
//                    arr[i] = CoachBoardFragments.GetTeam.get(i).getTeamName();
//                }
//
//            } catch (Exception x) {
//
//            }
//            // Toast.makeText(context, arr+"", Toast.LENGTH_SHORT).show();
//            showChooseItemAlertDialog(editTextTeam, arr);
//            hideKeyboard(view);
//        });
//        editTextTeam.setFocusable(false);
//        editTextTeam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                teamArray = new String[LoginScreen.vectorGetTeamsData.size()];
//                teamArray = new String[LoginScreen.vectorGetTeamsData.size()];
//                for (int i = 0; i < LoginScreen.vectorGetTeamsData.size(); i++) {
//                    GetTeamsDetailsClass objTeamData = LoginScreen.vectorGetTeamsData.get(i);
//                    teamArray[i] = objTeamData.getTeam_name();
//                }
//                hideKeyboard(view);
//                showChooseItemAlertDialog(editTextTeam, teamArray);
//
//            }
//        });

        parseRequiredData(UtilityClass.getPreferences(context, getString(R.string.sport)));

        editTextSport.setHint(getString(R.string.sport) + " " + getString(R.string.sport_type));
        editTextSport.setSingleLine(true);
        editTextSport.setEllipsize(TextUtils.TruncateAt.END);
        editTextSport.setText("");
        editTextSport.setFocusable(false);
        editTextSport.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
//                showChooseSportsItemAlertDialog(editTextSport, sportsArray, sportsIdArray);

                SelectionTypeOfList = "sports";
                custom_popup_class mAlert = new custom_popup_class(context, "sports",
                        "",
                        (RecyclerViewClickCheck) context,
                        selectedItemsSPORTSId);
                mAlert.setMessage("SELECT SPORTS");
                mAlert.show();
            }
        });

        editTextName = findViewById(R.id.editTextName);
        editTextName.setHint(getString(R.string.enter) + " First " + getString(R.string.name));
        editTextName.setSingleLine(true);
        editTextName.setEllipsize(TextUtils.TruncateAt.END);
        editTextName.setText("");
        editTextName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextLastName = findViewById(R.id.editTextLastName);
        editTextLastName.setSingleLine(true);
        editTextLastName.setEllipsize(TextUtils.TruncateAt.END);
        editTextLastName.setText("");
        editTextLastName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        editTextAge = findViewById(R.id.editTextAge);
        editTextAge.setHint(getString(R.string.enter) + " " + getString(R.string.age));
        editTextAge.setSingleLine(true);
        editTextAge.setText("");
        editTextAge.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        editTextGender = findViewById(R.id.editTextGender);
        editTextGender.setHint(getString(R.string.select) + " " + getString(R.string.gender));
        editTextGender.setText("");
        editTextGender.setFocusable(false);
        editTextGender.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                showChooseItemAlertDialog(editTextGender, getResources().getStringArray(R.array.gender_option));
            }
        });

        editTextClass = findViewById(R.id.editTextClass);
        editTextClass.setHint(getString(R.string.select) + " " + getString(R.string.class_grade));
        editTextClass.setSingleLine(true);
        editTextClass.setText("");
        editTextClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        });

        imageViewSave = findViewById(R.id.imageViewSave);


        editTextAge.setOnEditorActionListener((exampleView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard(exampleView);
                editTextAge.clearFocus();
                showChooseItemAlertDialog(editTextGender, getResources().getStringArray(R.array.gender_option));
                return true;
            } else {
                return false;
            }
        });

        editTextClass.setOnEditorActionListener((exampleView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard(exampleView);
                editTextClass.clearFocus();
                showChooseTeamItemAlertDialog(editTextSelectTeam, arr);
                return true;
            } else {
                return false;
            }
        });

        editTextEmail.setOnEditorActionListener((exampleView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard(exampleView);
                editTextEmail.clearFocus();
                if (!screenName.equalsIgnoreCase(getString(R.string.add_coach))) {
                    showChooseItemAlertDialog(editTextPosition, positionTitleArray); // for coach
                }
                return true;
            } else {
                return false;
            }
        });


        imageViewSave.setOnClickListener(view -> {
            WebServices webServices = new WebServices();
            if (screenName.equalsIgnoreCase(getString(R.string.add_athlete))) {
                // add athlete api call
                try {
                    String athleteName = editTextName.getText().toString().trim();
                    String athleteLastName = editTextLastName.getText().toString().trim();
                    String age = editTextAge.getText().toString().trim();
                    String gender = editTextGender.getText().toString().trim();
                    String classGrade = editTextClass.getText().toString().trim();
                    String email_id = editTextEmailAthlete.getText().toString().trim();
                    String coach_id = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
                    String team_id = editTextSelectTeam.getTag().toString();

                    StringBuilder teamIdArray = new StringBuilder();

                    for (int po = 0; po < selectedItemsTeamId.size(); po++) {
                        team_id = teamIdArray.append(selectedItemsTeamId.get(po)).append(",").toString();
                    }

                    if (team_id != null && team_id.length() > 0 && team_id.charAt(team_id.length() - 1) == ',') {
                        team_id = team_id.substring(0, team_id.length() - 1);
                    }
                    if (athleteName.length() > 0 && team_id.length() > 0 && athleteLastName.length() > 0 && age.length() > 0 && gender.length() > 0 && email_id.length() > 0 && classGrade.length() > 0) {
                        if (checkEmail(email_id)) {
                            //UtilityClass.showWaitDialog(new Dialog(context),context);
                            webServices.addAthlete(athleteName, athleteLastName, age, gender, email_id, classGrade, coach_id, team_id, context, AddTeamCoachAthleteScreen.this);
                        } else {
                            UtilityClass.showAlertDailog(context, getString(R.string.invalid_email_address));
                        }
                    } else {
                        UtilityClass.showAlertDailog(context, getString(R.string.mandatory_all_fileds));
                    }

                } catch (Exception c) {

                }


            } else if (screenName.equalsIgnoreCase(getString(R.string.add_coach))) {
                // add coach api call
                whichApiCalled = getString(R.string.add_coach);
                String coachName = editTextCoachName.getText().toString().trim();
                //String position_title = editTextPosition.getTag().toString().trim();
                //Log.e("Position Title Tag", "$$$$$ " + position_title);
                String emailId = editTextEmail.getText().toString().trim();
                String reffered_coach_name = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
                Log.e("reffered_coach_name", "$$$$$ " + reffered_coach_name);
                //UtilityClass.showWaitDialog(new Dialog(context),context);


                if (coachName.length() > 0 && emailId.length() > 0) {
                    if (checkEmail(emailId)) {
                        //UtilityClass.showWaitDialog(new Dialog(context),context);
                        webServices.addCoach(coachName, team_id, emailId, LoginScreen.userId, context, AddTeamCoachAthleteScreen.this);
                    } else {
                        UtilityClass.showAlertDailog(context, getString(R.string.invalid_email_address));
                    }

                } else {
                    UtilityClass.showAlertDailog(context, getString(R.string.mandatory_all_fileds));
                }
            } else if (screenName.equalsIgnoreCase(getString(R.string.add_team))) {
                // add team api called
                String teamName = editTextTeam.getText().toString();

                if (teamName.trim().length() > 0) {
                    whichApiCalled = "add team";
                    String sportsId = "";//editTextSport.getTag().toString();
                    String coach_id = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
                    StringBuilder newTime = new StringBuilder();
                    StringBuilder sports_Id = new StringBuilder();
                    String coach_class_timing = "";
                    Boolean Gonext = true;
                    int COUNT = 0;

                    for (int x = 0; x < recyclerDataList.size(); x++) {
                        String TIMEINg = recyclerDataList.get(x).From + "-" + recyclerDataList.get(x).To + ",";
                        if (TIMEINg.contains("00:00-00:00")) {
                            Gonext = false;
                            break;
                        } else {
                            coach_class_timing = newTime.append(TIMEINg).toString();
                        }
                    }
                    if (!Gonext) {
                        UtilityClass.showAlertDailog(context, "Please enter class timing.");
                        return;
                    }

                    for (int x = 0; x < selectedItemsSPORTSId.size(); x++) {
                        sportsId = sports_Id.append(selectedItemsSPORTSId.get(x) + ",").toString();
                    }

                    if (sportsId != null && sportsId.length() > 0 && sportsId.charAt(sportsId.length() - 1) == ',') {
                        sportsId = sportsId.substring(0, sportsId.length() - 1);
                    }


                    if (coach_class_timing != null && coach_class_timing.length() > 0 && coach_class_timing.charAt(coach_class_timing.length() - 1) == ',') {
                        coach_class_timing = coach_class_timing.substring(0, coach_class_timing.length() - 1);
                    }

                    if (coach_class_timing == null) {
                        coach_class_timing = "";
                    }

                    ////Log.e(VolleyLog.TAG, "onClick: " + coach_class_timing);
                    //UtilityClass.showWaitDialog(new Dialog(context),context);

                    webServices.addTeam(teamName, coach_id, team_id, sportsId, coach_class_timing, context, AddTeamCoachAthleteScreen.this);
                } else {
                    UtilityClass.showAlertDailog(context, getString(R.string.team_name_missing));
                }

            }
        });

        try {
            if (Type.equalsIgnoreCase("EditTeamSchedule") || Type.equalsIgnoreCase("EditTeam")) {
                textViewScreenName.setText("Edit Team");
            }
        } catch (Exception v) {

        }
    }


    private void showChooseTeamItemAlertDialog(final EditText editText, final String[] myArray) {
        setTextTeam(editText);
        editText.setText("");
        String selectedIndex = "";

        for (int x = 0; x < selectedItemsTeamId.size(); x++) {
            for (int i = 0; i < myArray.length; i++) {
                if (selectedItemsTeamId.get(x).equals(myArray[i])) {
                    checkedItemsTEAM[i] = true;
                    selectedItemsTeams.add(myArray[i]);
                    selectedItemsTeamId.add(Integer.valueOf(getTeamsDetailsClas.get(i).getTeamId()));
                }
            }
        }
        setTextTeam(editText);


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
                setTextTeam(editText);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // removes the AlertDialog in the screen
            }
        }).show();
    }

    private void setTextTeam(EditText editText) {
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(selectedItemsTeams);
        selectedItemsTeams.clear();
        selectedItemsTeams.addAll(hashSet);

        HashSet<Integer> hashSet2 = new HashSet<Integer>();
        hashSet2.addAll(selectedItemsTeamId);
        selectedItemsTeamId.clear();
        selectedItemsTeamId.addAll(hashSet2);

        editText.setText("");
        String selectedIndex = "";

        for (int os = 0; os < selectedItemsTeams.size(); os++) {
            selectedIndex += selectedItemsTeams.get(os) + ", ";
        }
        if (selectedIndex != null && selectedIndex.length() > 0 && selectedIndex.charAt(selectedIndex.length() - 2) == ',') {
            selectedIndex = selectedIndex.substring(0, selectedIndex.length() - 2);
        }

        Log.e(VolleyLog.TAG, "onClick: " + selectedIndex);

        editText.setText(selectedIndex);
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

        editTextSport.setText("");
        String selectedIndex = "";
        StringBuilder v = new StringBuilder();
        for (int os = 0; os < selectedItemsSPORTS.size(); os++) {

            selectedIndex = v.append(selectedItemsSPORTS.get(os) + ", ").toString();
            //selectedIndex += selectedItemsSPORTS.get(os) + ", ";
            ////Log.e(VolleyLog.TAG, "setTextSports: "+selectedIndex);
        }
        if (selectedIndex != null && selectedIndex.length() > 0 && selectedIndex.charAt(selectedIndex.length() - 2) == ',') {
            selectedIndex = selectedIndex.substring(0, selectedIndex.length() - 2);
        }
        Log.e(VolleyLog.TAG, "setTextSports: " + selectedIndex);


        editTextSport.setText(selectedIndex);
    }


    private void showChooseSportsItemAlertDialog(final View view, final String[] myArray, String[] sportsIdArray) {
        // setTextSports();

        editTextSport.setText("");

        //for (int x = 0; x < LoginJson.get(0).getSports().size(); x++) {

        //}

        setTextSports();

        new AlertDialog.Builder(context).setMultiChoiceItems(myArray, checkedItemsSPORTS, new DialogInterface.OnMultiChoiceClickListener() {
            int count = 0;

            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                if (isChecked) {
                    selectedItemsSPORTS.add(myArray[i]);
                    selectedItemsSPORTSId.add(Integer.valueOf(SchoolDataService.sportsIdArray[i]));
                } else if (selectedItemsTeams.contains(myArray[i])) {
                    selectedItemsSPORTS.remove(myArray[i]);
                    selectedItemsSPORTSId.remove(Integer.valueOf(SchoolDataService.sportsIdArray[i]));
                }
            }
        }).setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                setTextSports();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // removes the AlertDialog in the screen
            }
        }).show();
    }


    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        // parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

    private void initializeTextView() {
        textViewCoachName = findViewById(R.id.textViewCoachName);
        textViewCoachName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        // textViewCoachName.setTypeface(CustomTypeface.load_Montserrat_Light_Fonts(context));

        textViewEmail = findViewById(R.id.textViewCoachName);
        textViewEmail.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewPosition = findViewById(R.id.textViewCoachName);
        textViewPosition.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewTeam = findViewById(R.id.textViewCoachName);
        textViewTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSport = findViewById(R.id.textViewSport);
        textViewSport.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

//        textViewAthleteName = (TextView) findViewById(R.id.textViewAthleteName);
//        textViewAthleteName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewEmailAthlete = findViewById(R.id.textViewEmailAthlete);
        textViewEmailAthlete.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewAge = findViewById(R.id.textViewAge);
        textViewAge.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewGender = findViewById(R.id.textViewGender);
        textViewGender.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewClass = findViewById(R.id.textViewClass);
        textViewClass.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSelectTeamText = findViewById(R.id.textViewSelectTeamText);
        textViewSelectTeamText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        textViewName = findViewById(R.id.textViewName);
        textViewName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewLastName = findViewById(R.id.textViewLastName);
        textViewLastName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


    }

    private void resetAllFields() {
        editTextCoachName.setText("");
        editTextEmail.setText("");
        editTextEmailAthlete.setText("");
        editTextTeam.setText("");
        editTextSport.setText("");
        editTextLastName.setText("");
        editTextName.setText("");
        editTextAge.setText("");
        editTextGender.setText("");
        editTextClass.setText("");
        editTextSelectTeam.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    private boolean checkEmail(String email) {

        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    private void hideKeyboard(View view) {
        // Check if no view has focus:
        view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void showChooseItemAlertDialog(final View view, final String[] myArray) {

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//        mBuilder.setTitle(getString(R.string.app_name));
        mBuilder.setSingleChoiceItems(myArray, -1, (dialogInterface, i) -> {
            switch (view.getId()) {
                case R.id.editTextGender:
                    mBuilder.setTitle(getString(R.string.select_gender));
                    editTextGender.setText(myArray[i]);
                    editTextClass.requestFocus();
                    showKeyboard();
                    break;
                case R.id.editTextLanguage:
                    mBuilder.setTitle(getString(R.string.select_language));
                    languageArray = getResources().getStringArray(R.array.language_option);
                    break;
                case R.id.editTextSport:
                    mBuilder.setTitle(getString(R.string.select_sport));
                    editTextSport.setText(myArray[i]);
                    editTextSport.setTag(sportsIdArray[i]);
                    break;
                case R.id.editTextPosition:
                    mBuilder.setTitle(getString(R.string.select_position));
                    editTextPosition.setText(myArray[i]);
                    editTextPosition.setTag(positionTitleIdArray[i]);
                    break;
                case R.id.editTextTeam:
                    mBuilder.setTitle(getString(R.string.select_team));
                    editTextTeam.setText(myArray[i]);
                    editTextTeam.setTag(teamIdArray[i]);
                    hideKeyboard(view);
                    showChooseSportsItemAlertDialog(editTextSport, sportsArray, sportsIdArray);
                    break;
                case R.id.editTextSelectTeam:
                    mBuilder.setTitle("Select Team");
                    editTextSelectTeam.setText(myArray[i]);
                    editTextSelectTeam.setTag(SchoolDataService.GetTeam.get(i).getTeamId());
                    hideKeyboard(view);
                    break;

            }

            dialogInterface.dismiss();
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }


    private void showChooseItemAlertDialogTeam(final View view, final String[] myArray) {

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//        mBuilder.setTitle(getString(R.string.app_name));
        mBuilder.setSingleChoiceItems(myArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (view.getId()) {
                    case R.id.editTextGender:
                        mBuilder.setTitle(getString(R.string.select_gender));
                        editTextGender.setText(myArray[i]);
                        break;
                    case R.id.editTextLanguage:
                        mBuilder.setTitle(getString(R.string.select_language));
                        languageArray = getResources().getStringArray(R.array.language_option);
                        break;
                    case R.id.editTextSport:
                        mBuilder.setTitle(getString(R.string.select_sport));
                        editTextSport.setText(myArray[i]);
                        editTextSport.setTag(sportsIdArray[i]);
                        break;
                    case R.id.editTextPosition:
                        mBuilder.setTitle(getString(R.string.select_position));
                        editTextPosition.setText(myArray[i]);
                        editTextPosition.setTag(positionTitleIdArray[i]);
                        break;
                    case R.id.editTextTeam:
                        mBuilder.setTitle(getString(R.string.select_team));
                        editTextTeam.setText(myArray[i]);
                        editTextTeam.setTag(myArray[i]);
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
        Log.e("Result  ", whichApiCalled + "  " + result);
        if (result != null && result.trim().length() > 0) {
            try {
                if (whichApiCalled.equalsIgnoreCase("add team")) {
                    JSONObject jsonObj = new JSONObject(result);


                    UtilityClass.showAlertDailog(context, jsonObj
                            .getString("responseMessage"));
                    if (WebServices.responseCode == 200) {
                        resetAllFields();
                        JSONArray jsonDataArray = jsonObj
                                .getJSONArray("data");
                        if (jsonDataArray != null && jsonDataArray.length() > 0) {
                            AddteamBoolean = true;
                            Gson gson = new Gson();
                            GetTeamX = Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetTeam[].class));
//                            SchoolDataService.GetTeam = null;
//                            SchoolDataService.GetTeam = GetTeamX;
                        }
                        getTeamDataFromServer();
                        recyclerDataList = new ArrayList<>();


                        recyclerViewForCoachOfTeam.setAdapter(null);
                        recyclerDataList.add(new RecyclerData("00:00", "00:00"));
                        classTime.notifyDataSetChanged();

                        CallApiForTeamByCoach();
                        onBackPressed();
                    }
                } else if (whichApiCalled.equalsIgnoreCase("deleteTeamCoach")) {
                    calendarEventAdapterTiming.notifyDataSetChanged();
                } else {
                    JSONObject jsonObj = new JSONObject(result);

                    UtilityClass.showAlertDailog(context, jsonObj
                            .getString("responseMessage"));
                    if (WebServices.responseCode == 200) {
                        resetAllFields();
                    }
                }

//                Log.e("**********", "" + responseMessage);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        UtilityClass.hide();
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

            vectorSportsData = new Vector<SportsDataClass>();
            JSONArray jsonDataArray = jsonObj
                    .getJSONArray("data");
            if (jsonDataArray != null && jsonDataArray.length() > 0) {
                vectorSportsData.clear();
                SportsDataClass objSportsData = null;
                sportsArray = new String[jsonDataArray.length()];
                sportsIdArray = new String[jsonDataArray.length()];
                for (int i = 0; i < jsonDataArray.length(); i++) {
                    objSportsData = new SportsDataClass();
                    JSONObject objJsonSportsData = jsonDataArray.getJSONObject(i);
                    objSportsData.setId(objJsonSportsData.getString(getString(R.string.sport_id_tag)));
                    objSportsData.setSports_name(objJsonSportsData.getString(getString(R.string.sport_name_tag)));
                    sportsArray[i] = objSportsData.getSports_name();
                    sportsIdArray[i] = objSportsData.getId();
//                            objSportsData.setDateSports(objSportsData.getString(getString(R.string.d)));
                    vectorSportsData.add(objSportsData);
                }
//                JSONObject userJson = new JSONObject(usersData);
            } else {
                UtilityClass.showAlertDailog(context, responseMessage);


            }
        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void setTimePickerInterval(TimePicker timePicker) {
        try {
            int TIME_PICKER_INTERVAL = 15;
            NumberPicker minutePicker = timePicker.findViewById(Resources.getSystem().getIdentifier(
                    "minute", "id", "android"));
            minutePicker.setMinValue(0);
            minutePicker.setMaxValue((60 / TIME_PICKER_INTERVAL) - 1);
            List<String> displayedValues = new ArrayList<String>();
            for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL) {
                displayedValues.add(String.format("%02d", i));
            }
            minutePicker.setDisplayedValues(displayedValues.toArray(new String[0]));
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e);
        }
    }

    @Override
    public void OnItemClick(int position) {

    }


    private class HolderOfAddTime extends RecyclerView.ViewHolder {
        private TextView textViewScreenName, textViewStartTime, textViewEndTime, textViewEnd, textViewStart;
        private RelativeLayout rLAyoutForstartTime, rLAyoutForEndTime;
        private ImageView AddTime;

        public HolderOfAddTime(View itemView) {
            super(itemView);
            textViewEndTime = itemView.findViewById(R.id.textViewEndTime);
            textViewStartTime = itemView.findViewById(R.id.textViewStartTime);
            textViewStart = itemView.findViewById(R.id.textViewstart);
            textViewEnd = itemView.findViewById(R.id.textViewEnd);

            rLAyoutForstartTime = itemView.findViewById(R.id.rLAyoutForstartTime);
            rLAyoutForEndTime = itemView.findViewById(R.id.rLAyoutForEndTime);
            AddTime = itemView.findViewById(R.id.AddTime);


            textViewStart.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewStartTime.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewEndTime.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewEnd.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        }
    }

    public class ClassTime extends RecyclerView.Adapter<HolderOfAddTime> {
        @Override
        public HolderOfAddTime onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.field, viewGroup, false);
            return new HolderOfAddTime(view);
        }


        @Override
        public void onBindViewHolder(final HolderOfAddTime holder, final int position) {

            holder.textViewStartTime.setText(recyclerDataList.get(position).getFrom());
            holder.textViewEndTime.setText(recyclerDataList.get(position).getTo());

            holder.rLAyoutForstartTime.setOnClickListener(view -> {
                DateFormat formatter = new SimpleDateFormat("HH:mm");

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = new Date();
                Date date2 = null;
                try {
                    date1 = format.parse(format2.format(date1) + " " + "00:00:01");
                    date2 = format.parse(format2.format(date1) + " 23:59:59");
                    System.out.println(date1 + "  " + date2 + "  " + format.format(date1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new SingleDateAndTimePickerDialog.Builder(context)
                        .bottomSheet()
                        .curved()
                        .minDateRange(date1).defaultDate(date1)
                        .maxDateRange(date2)
                        .backgroundColor(getResources().getColor(R.color.color_black_for_toggle_selected))
                        .titleTextColor(getResources().getColor(R.color.textColorYellow))
                        .displayAmPm(false)
                        .mainColor(getResources().getColor(R.color.textColorYellow))
                        .minutesStep(10)
                        .listener(new SingleDateAndTimePickerDialog.Listener() {
                            @Override
                            public void onDateSelected(Date date) {
                                holder.textViewStartTime.setText(formatter.format(date));
                                recyclerDataList.get(position).setFrom(holder.textViewStartTime.getText().toString());
                            }
                        }).display();
            });

            holder.rLAyoutForEndTime.setOnClickListener(view -> {
                DateFormat formatter = new SimpleDateFormat("HH:mm");


                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = new Date();
                Date date2 = null;
                try {
                    Log.e(VolleyLog.TAG, "onBindViewHolder: " + format2.format(date1) + ":00:01");
                    date1 = format.parse(format2.format(date1) + " 00:00:01");
                    date2 = format.parse(format2.format(date1) + " 23:59:59");
                    System.out.println(date1 + "  " + date2 + "  " + format.format(date1));
                } catch (ParseException e) {
                    Log.e(VolleyLog.TAG, "onBindViewHolder: " + e);
                }
                new SingleDateAndTimePickerDialog.Builder(context)
                        .bottomSheet()
                        .curved()
                        .minDateRange(date1).defaultDate(date1)
                        .maxDateRange(date2)
                        .backgroundColor(getResources().getColor(R.color.color_black_for_toggle_selected))
                        .titleTextColor(getResources().getColor(R.color.textColorYellow))
                        .displayAmPm(false)
                        .mainColor(getResources().getColor(R.color.textColorYellow))
                        .minutesStep(10)
                        .listener(new SingleDateAndTimePickerDialog.Listener() {
                            @Override
                            public void onDateSelected(Date date) {
                                holder.textViewEndTime.setText(formatter.format(date));
                                recyclerDataList.get(position).setTo(holder.textViewEndTime.getText().toString());
                            }
                        }).display();
            });
            holder.AddTime.setOnClickListener(view -> {
                if (position == recyclerDataList.size() - 1) {
                    recyclerDataList.add(new RecyclerData("00:00", "00:00"));
                } else {
                    //notifyItemRemoved(position);
                    recyclerDataList.remove(position);
                }
                notifyDataSetChanged();
            });

            if (position == recyclerDataList.size() - 1) {
                holder.AddTime.setImageDrawable(getResources().getDrawable(R.drawable.plus_with_circle_icon));
            } else {
                holder.AddTime.setImageDrawable(getResources().getDrawable(R.drawable.minus));
            }
        }

        @Override
        public int getItemCount() {
            return recyclerDataList.size();
        }
    }

    public class RecyclerData {
        String To;
        private String From;


        public RecyclerData(String From, String To) {
            this.To = To;
            this.From = From;
        }

        public String getFrom() {
            return From;
        }

        public void setFrom(String From) {
            this.From = From;
        }

        public String getTo() {
            return To;
        }

        public void setTo(String To) {
            this.To = To;
        }
    }


    private class CalendarEventAdapterTiming extends RecyclerView.Adapter<ViewHolder> {
        ViewBinderHelper binderHelper = new ViewBinderHelper();
        List<TeamCoach> teamCoachList = new ArrayList<>();

        CalendarEventAdapterTiming(List<TeamCoach> teamCoachList) {
            this.teamCoachList = teamCoachList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater vi = LayoutInflater.from(parent.getContext());
            View v = vi.inflate(R.layout.list_item_calendar_event, parent, false);
            return new ViewHolder(v);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            //holder.rclEventIcon.setCardBackgroundColor(Color.parseColor("#404040"));

            binderHelper.setOpenOnlyOne(true);
            binderHelper.bind(holder.swipe_layoutTiming, teamCoachList.get(position).getId());
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.rclEventIcon.getLayoutParams();
            params.setMargins(10, 10, 10, 10);
            holder.rclEventIcon.setLayoutParams(params);
            holder.timeRecycler.setVisibility(View.GONE);
            String defaultTitle = holder.itemView.getContext().getString(R.string.event_default_title);
            String title = "";


            holder.tvEventName.setText(UtilityClass.getNameAthlete(
                    teamCoachList.get(position).getFirstName(),
                    teamCoachList.get(position).getLastName(),
                    teamCoachList.get(position).getEmailId()
            ));
            holder.tvEventStatus.setText(teamCoachList.get(position).getSchoolName());

            holder.tvEventName.setTextColor(getResources().getColor(R.color.textColorYellow));
            holder.tvEventStatus.setTextColor(getResources().getColor(R.color.textColorYellow));

//            holder.FromTime.setText(TimingList.get(position).getFrom());
//            holder.ToTime.setText(TimingList.get(position).getTo());


            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

            holder.timeRecycler.setLayoutManager(linearLayoutManager1);

            holder.LayoutForAddTiming.setVisibility(View.GONE);

            //  holder.LayoutForColor.setBackgroundColor(event.getColor());

            holder.ViewOFPlans.setOnClickListener(view1 -> {

            });

            holder.Set_Time.setText("Delete");
            holder.Set_Time.setBackgroundColor(getResources().getColor(R.color.color_red_value));


            holder.Set_Time.setOnClickListener(view1 -> {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.swipe_layoutTiming.close(true);
                    }
                }, 10);

                if (teamCoachList.size() == 1) {
                    UtilityClass.showAlertDailog(context, "A team must have one coach.");
                    return;
                }
                WebServices webServices = new WebServices();
                whichApiCalled = "deleteTeamCoach";
                webServices.deleteTeamCoach(teamCoachList.get(position).getId(), context, AddTeamCoachAthleteScreen.this);
                teamCoachList.remove(position);
                notifyItemRemoved(position);
                //notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = teamCoachList.size();
            } catch (Exception v) {
            }
            return count;
        }


    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        CardView rclEventIcon;
        TextView tvEventName;
        TextView tvEventStatus, FromTime, ToTime, Set_Time;
        LinearLayout LayoutForAddTiming, LlayoutForEvents, LayoutForColor;
        RecyclerView timeRecycler;
        RelativeLayout ViewOFPlans;
        SwipeRevealLayout swipe_layoutTiming;


        ViewHolder(View view) {
            super(view);
            rclEventIcon = view.findViewById(R.id.rcl_calendar_event_icon);
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
        }


    }
}
