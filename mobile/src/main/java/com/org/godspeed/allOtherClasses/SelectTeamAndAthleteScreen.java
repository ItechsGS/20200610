package com.org.godspeed.allOtherClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleRelativeLayout;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.getTeams.GetTeam;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.LoginScreen.userTypeOf;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_ID_FOR_PRE;
import static com.org.godspeed.service.BackgroundLocationUpdateService.GetTeamORIGINAL;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.service.SchoolDataService.getSchoolsList;
import static com.org.godspeed.service.SchoolDataService.getTeamDataFromServer;
import static com.org.godspeed.utility.UtilityClass.hide;

public class SelectTeamAndAthleteScreen extends Activity implements GodSpeedInterface {
    static TextView textViewScreenName;
    static SelectTeamAndAthleteScreen sActivity;
    static Context sContext;
    private static WebServices webServices;
    private static String DateX = "";
    TextView CancelButtonOfSearch;
    Context context;
    RecyclerView SelectTeam;
    ImageView imageViewBackArrow;
    TeamAdapter teamAdapter;
    ProgramAdapter programAdapter;
    BubblePopupWindow dialog;
    View view;
    int trainingProgramDetailPosition;
    int positionofTeamMain;
    ImageView calc_clear_txt_Prise;
    EditText calc_txt_Prise;
    Transition transition;
    ImageView imageViewSearch, imageViewFilterTeam;
    LinearLayout lLayoutForTimeClass, lLayoutForSportsClass, lLayoutForSchoolClass;
    RelativeLayout RReventName;
    TextView TextViewForTimeClass, TextViewForSportsClass, TextViewForSchoolClass, TypeOfFilter;
    private String training_program_id = "";
    private String whichApiCalled;
    private RelativeLayout SearchAthleteText;
    private RelativeLayout rSearchAthleteText;
    private String showDialogOf = "";
    private List<String> arrayListSchool = new ArrayList<>();
    private ImageView backEventDialog, SaveEventDialog;
    private RelativeLayout rLayoutForBottomViewSettingsOptions, rLayoutForAddTeam, rLayoutForAddAthlete, rLayoutForAddCoach, rLayoutForDeleteAthlete;
    private TextView EvenText, textViewAthleteName, textViewHeightValue, textViewSMMValue, textViewKGValue, textViewBodyFatValue, TextViewAtheleteLevel;
    private String SchoolIDs = "";
    private LinearLayout lLayoutForFIlterOption;
    private RecyclerView dialogBoxRecyclerView, dialogBoxRecyclerData;
    private LinearLayout rMainDialogLayout;

    private List<GetTeam> getTeamsList = new ArrayList<>();

    private static void sendtoServer(String dateX, String teamid) {
        sActivity.whichApiCalled = "setAssignProgram";
        webServices.setAssignProgram(sActivity.getIntent().getStringExtra("training_program_id"), teamid, "", dateX, LoginJson.get(0).getUserId(), sContext, sActivity);
    }

    private static void sendtoServerDate(String dateX, String assignProgramId) {
        sActivity.whichApiCalled = "updateAssignProgramStartDate";
        //////Log.e(VolleyLog.TAG, "sendtoServerDate: " + "updateAssignProgramStartDate");
        ////Log.e(VolleyLog.TAG, "sendtoServerDate:before " + sActivity.getTeamsList.get(sActivity.positionofTeamMain).getTrainingProgramDetail().get(sActivity.trainingProgramDetailPosition).getStartDate());
        sActivity.getTeamsList.get(sActivity.positionofTeamMain).getTrainingProgramDetail().get(sActivity.trainingProgramDetailPosition).setStartDate(dateX);
        ////Log.e(VolleyLog.TAG, "sendtoServerDate: " + sActivity.getTeamsList.get(sActivity.positionofTeamMain).getTrainingProgramDetail().get(sActivity.trainingProgramDetailPosition).getStartDate());
        sActivity.teamAdapter.notifyItemChanged(sActivity.positionofTeamMain);
        webServices.updateAssignProgramStartDate(assignProgramId, dateX, sContext, sActivity);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_select_team_and_athlete_screen);

        webServices = new WebServices();

        context = this;
        sActivity = this;
        sContext = this;
        //   dialog = new Dialog(context);

        forSearchBar();
        SelectTeam = findViewById(R.id.SelectTeam);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        textViewScreenName = findViewById(R.id.textViewScreenName);
        imageViewFilterTeam = findViewById(R.id.imageViewFilterTeam);

        imageViewFilterTeam.setVisibility(VISIBLE);
        imageViewSearch = findViewById(R.id.imageViewSearch);

        training_program_id = getIntent().getStringExtra("training_program_id");

        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        });


        if (userTypeOf.equalsIgnoreCase("1") || userTypeOf.equalsIgnoreCase("2")) {
            imageViewFilterTeam.setVisibility(VISIBLE);
            try {
                if (SCHOOL_ID_FOR_PRE.equalsIgnoreCase("")) {
                    SchoolIDs = getSchoolsList.get(0).getSchoolId();
                } else {
                    SchoolIDs = SCHOOL_ID_FOR_PRE;
                }

                arrayListSchool.add(SchoolIDs);
            } catch (Exception v) {

            }
            GetTeamNew();

        } else {
            imageViewFilterTeam.setVisibility(GONE);
            if (GetTeamORIGINAL.size() != 0) {
                getTeamsList = new ArrayList<>(GetTeamORIGINAL);
                teamAdapter = new TeamAdapter(context, getTeamsList);
                SelectTeam.setAdapter(teamAdapter);
            } else {
                whichApiCalled = "team";
                WebServices webServices = new WebServices();
                webServices.getTeams(LoginJson.get(0).getUserId(), SchoolIDs, context, SelectTeamAndAthleteScreen.this);
            }
        }


        imageViewSearch.setOnClickListener(view1 -> toggle());


        imageViewFilterTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                imageViewFilterTeam.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                showDialogBox(x, y, "", 0, view);
            }
        });

        ////Log.e(VolleyLog.TAG, "onDateSet: "+teamid+" "+getIntent().getStringExtra("training_program_id")+ df);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        SelectTeam.setLayoutManager(llm);


        CancelButtonOfSearch.setOnClickListener(view1 -> {
            toggle();
            calc_txt_Prise.setText("");
        });


        calc_clear_txt_Prise.setOnClickListener(view1 -> {
            calc_txt_Prise.setText("");
        });

        calc_clear_txt_Prise.setVisibility(GONE);
        calc_txt_Prise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                teamAdapter.filter(s.toString().trim());
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


//        if(!textViewScreenName.getTag().toString().equalsIgnoreCase("")){
//            Log.e(VolleyLog.TAG, "onCreate: "+"jhhhdhhhhh");
//            //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//        }
    }

    private void GetTeamNew() {
        whichApiCalled = "team";
        WebServices webServices = new WebServices();
        webServices.getTeams(LoginJson.get(0).getUserId(),
                SchoolIDs, context, SelectTeamAndAthleteScreen.this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void forSearchBar() {
        SearchAthleteText = findViewById(R.id.SearchAthleteText);
        CancelButtonOfSearch = findViewById(R.id.CancelButtonOfSearch);
        rSearchAthleteText = findViewById(R.id.rSearchAthleteText);
        calc_txt_Prise = findViewById(R.id.calc_txt_Prise);
        calc_clear_txt_Prise = findViewById(R.id.calc_clear_txt_Prise);
        transition = new Slide(Gravity.TOP);

        transition.setDuration(300);
        transition.addTarget(R.id.rSearchAthleteText);

        //SearchAthlete.setOnClickListener(view -> toggle());
    }

    public void showDialogBox(int x, int y, String athlete_level, int position, View view) {
        ////Log.e(VolleyLog.TAG, "showDialogBox: " + athlete_level);
//        dialog = new BubblePopupWindow(view);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.cutom_dialogbox_athlete_screen);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.getWindow().setDimAmount(0);

        View AlertBoxView = LayoutInflater.from(context).inflate(R.layout.cutom_dialogbox_athlete_screen, null);
        BubbleRelativeLayout bubbleView = AlertBoxView.findViewById(R.id.CardX);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);

        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));
        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);
        dialog.setCancelOnTouch(false);


        dialog.showAtLocation(AlertBoxView, Gravity.CENTER, 0, 0);


        //imageViewAppIconForAnimation = dialog.findViewById(R.id.imageViewAppIconForAnimation);
        //AlertBoxView.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RelativeLayout mainRly = AlertBoxView.findViewById(R.id.mainRly);
        mainRly.invalidate();
        EvenText = AlertBoxView.findViewById(R.id.EventName);
        EvenText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

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
        lLayoutForSchoolClass.setOnClickListener(viewX -> {
            lLayoutForFIlterOption.setVisibility(View.GONE);
            TypeOfFilter.setVisibility(View.VISIBLE);
            TypeOfFilter.setText("Select School");
            dialogBoxRecyclerView.setVisibility(View.VISIBLE);
            dialogBoxRecyclerView.setAdapter(new AthleteLevelViewX(position, "School"));

            backEventDialog.setVisibility(View.VISIBLE);
        });

        SaveEventDialog.setVisibility(View.VISIBLE);


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


        dialogBoxRecyclerView.setAdapter(new AthleteLevelViewX(position, ""));
//
//
//        if (showDialogOf.equalsIgnoreCase("LEVEL")) {
//            WindowManager.LayoutParams wmlp = AlertBoxView.getWindow().getAttributes();
//            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
//            wmlp.x = x - 50;
//            wmlp.y = y;
//        }
//
//        dialog.show();

    }

    public void toggle() {


        //calc_txt_Prise.setText("");
        TransitionManager.beginDelayedTransition(rSearchAthleteText, transition);

        rSearchAthleteText.setVisibility(rSearchAthleteText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);


        if (rSearchAthleteText.getVisibility() == VISIBLE) {
            //hideSoftKeyboard();
            calc_txt_Prise.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(calc_txt_Prise, InputMethodManager.SHOW_IMPLICIT);
            //gridViewAthlete.setEnabled(false);
        }

    }

    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            Log.d("result", result);
            parseRequiredData(result);
        } else {
            UtilityClass.hide();
        }
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
                JSONArray jsonDataArray = null;
                try {
                    jsonDataArray = jsonObj
                            .getJSONArray("data");
                } catch (Exception b) {

                    if (whichApiCalled.equalsIgnoreCase("setAssignProgram") || whichApiCalled.equalsIgnoreCase("deleteAssignProgram")) {
                        //getDataFromServer();
                        hide();
                        getTeamDataFromServer();
                        GetTeamNew();


                        if (whichApiCalled.equalsIgnoreCase("setAssignProgram")) {

                            final custom_alert_class mAlert = new custom_alert_class(context);
                            mAlert.setMessage(responseMessage);
                            mAlert.OneButton(true);
                            mAlert.setPositveButton("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //adapter.notifyDataSetChanged();
                                    finish();
                                    onBackPressed();
                                }
                            });

                            mAlert.show();
                        }
                    } else {
                        UtilityClass.showAlertDailog(context, responseMessage);
                    }
                    return;
                }

                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    if (whichApiCalled.equalsIgnoreCase("setAssignProgram") || whichApiCalled.equalsIgnoreCase("deleteAssignProgram")) {
                        //getDataFromServer();

                        hide();
                        getTeamDataFromServer();
                        GetTeamNew();

                        if (whichApiCalled.equalsIgnoreCase("setAssignProgram")) {

                            final custom_alert_class mAlert = new custom_alert_class(context);
                            mAlert.setMessage(responseMessage);
                            mAlert.OneButton(true);
                            mAlert.setPositveButton("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //adapter.notifyDataSetChanged();
                                    finish();
                                    onBackPressed();
                                }
                            });

                            mAlert.show();
                        }
                    } else if (whichApiCalled.equalsIgnoreCase("team")) {
                        Gson gson = new Gson();
                        getTeamsList = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), GetTeam[].class)));
                        teamAdapter = new TeamAdapter(context, getTeamsList);
                        SelectTeam.setAdapter(teamAdapter);
                    }
                }
//                JSONObject userJson = new JSONObject(usersData);
            } else {
                //UtilityClass.showAlertDailog(context, responseMessage);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        UtilityClass.hide();
    }

    public void showDialogofTraining(Context context, int x, int y, String event, String eventData, int X, String TeamId) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_box_for_training_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setDimAmount(0);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        LinearLayout BeginTraining, Cancel, workoutSummary;
        TextView EventName, EventNameX, begin, workout, cancel;

        BeginTraining = dialog.findViewById(R.id.BeginTraining);
        Cancel = dialog.findViewById(R.id.Cancel);
        workoutSummary = dialog.findViewById(R.id.workoutSummary);
        begin = dialog.findViewById(R.id.begin);
        workout = dialog.findViewById(R.id.workout);
        cancel = dialog.findViewById(R.id.cancel);


        EventName = dialog.findViewById(R.id.EventName);

        EventNameX = dialog.findViewById(R.id.EventNameX);

        EventNameX.setText("Do you want to assign " + event + " Training Program?");


//        showDialogofTraining(context,0,0,GetTeam.get(position).getTeamName(),"",0);
        EventName.setTypeface(CustomTypeface.load_Montserrat_Bold_Fonts(context));
        EventNameX.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        begin.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        workout.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        cancel.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));

        begin.setText("Assign to Team");
        workout.setText("Assign to Athlete");
        cancel.setText("Cancel");

        BeginTraining.setOnClickListener(view -> {
            DialogFragment dialogfragment = new DatePickerDialogClass(TeamId, "");
            dialogfragment.show(getFragmentManager(), "Date Picker Dialog");
            dialog.dismiss();
        });

        workoutSummary.setOnClickListener(view -> {
            startActivity(new Intent(context, SelectAthlete.class).putExtra("TeamId", TeamId).putExtra("TeamName", event).putExtra("training_program_id", training_program_id));
            overridePendingTransition(R.anim.exit, R.anim.enter);
            dialog.dismiss();
            Log.d(VolleyLog.TAG, "*************** SelectAthlete *************");

        });

        Cancel.setOnClickListener(view -> dialog.dismiss());

//        if (!showDialogOf.equals("AthleteTraningProgram")) {
//            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
//            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
//            wmlp.x = x;
//            wmlp.y = y - 30;
//        }

        dialog.show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    @SuppressLint("ValidFragment")
    public static class DatePickerDialogClass extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        String teamid = "";
        String assignProgramId = "";

        public DatePickerDialogClass(String teamId, String assignProgramId) {
            this.teamid = teamId;
            this.assignProgramId = assignProgramId;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    R.style.datepickerCustom, this, year, month, day);

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

            textViewScreenName.setTag(DateFormat.format("yyyy-MM-dd", date1).toString());
            DateX = DateFormat.format("yyyy-MM-dd", date1).toString();
            if (assignProgramId.equalsIgnoreCase("")) {
                sendtoServer(DateX, teamid);
            } else {
                sendtoServerDate(DateX, assignProgramId);
            }

            UtilityClass.showWaitDialog(new Dialog(sActivity.context), sActivity.context);
            ////Log.e(VolleyLog.TAG, "onDateSet: "+DateX+);
            //webServices.setAssignProgram(getIntent().getStringExtra("training_program_id"),"","",df.toString(),context,SelectTeamAndAthleteScreen.this);

            //textViewDate.setText(df.format("dd - MMM - yyyy", date1));
            //GlobalClass.PhaseDate = df.format("dd - MMM - yyyy", date1).toString();
        }
    }

    private class AthleteLevelViewX extends RecyclerView.Adapter<AthleteLevelViewX.RecyclerViewHolder2> {
        int position;
        String Event;

        public AthleteLevelViewX(int position, String Event) {
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
        public void onBindViewHolder(final RecyclerViewHolder2 Holder, final int i) {

            Holder.LevelText.setVisibility(View.VISIBLE);
            Holder.LevelImage.setVisibility(View.GONE);

            Holder.LevelText.setTextColor(getResources().getColor(R.color.textColorYellow));

            Holder.LevelText.setText(getSchoolsList.get(i).getSchoolName());
            Holder.arrow1.setVisibility(View.GONE);

            ////Log.e(VolleyLog.TAG, "onBindViewHolder: " + arrayList.size());
            for (int vc = 0; vc < arrayListSchool.size(); vc++) {
                if (Integer.parseInt(getSchoolsList.get(i).getSchoolId()) == Integer.parseInt(arrayListSchool.get(vc))) {
                    Holder.rightSign.setVisibility(View.VISIBLE);
                }
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
                Log.d(UtilityClass.TAG, "onBindViewHolder: " + SchoolIDs);

                whichApiCalled = "team";
                WebServices webServices = new WebServices();
                webServices.getTeams(LoginJson.get(0).getUserId(),
                        SchoolIDs, context, SelectTeamAndAthleteScreen.this);
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
                        String GetID = getSchoolsList.get(i).getSchoolId();
                        arrayListSchool.add(GetID);
                    }
                    Log.d(UtilityClass.TAG, "onClick: " + arrayListSchool.size());
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

    private class TrainingScreen extends RecyclerView.ViewHolder {
        TextView textViewTrainingName, Delete, Move, Rename, Assign, Edit, Copy, textViewCount, DateofProgram;
        RelativeLayout rrrss, rlayoutCount;
        SwipeRevealLayout rLayoutForTraining;
        ImageView folderIcon, imageViewTeamIcon, arrow;
        RecyclerView ProgramList;

        public TrainingScreen(@NonNull View convertView) {
            super(convertView);
            textViewTrainingName = convertView.findViewById(R.id.textViewTrainingName);
            textViewTrainingName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            folderIcon = convertView.findViewById(R.id.folderIcon);
            rLayoutForTraining = convertView.findViewById(R.id.rLayoutForTraining);
            rrrss = convertView.findViewById(R.id.rrrss);
            rlayoutCount = convertView.findViewById(R.id.rlayoutCount);
            DateofProgram = convertView.findViewById(R.id.DateofProgram);

            Delete = convertView.findViewById(R.id.Delete);
            Move = convertView.findViewById(R.id.Move);
            Rename = convertView.findViewById(R.id.Rename);
            Assign = convertView.findViewById(R.id.Assign);
            Edit = convertView.findViewById(R.id.Edit);
            textViewCount = convertView.findViewById(R.id.textViewCount);
            Copy = convertView.findViewById(R.id.Copy);
            arrow = convertView.findViewById(R.id.arrow);
            ProgramList = convertView.findViewById(R.id.ProgramList);
            LinearLayoutManager llm = new LinearLayoutManager(context);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            ProgramList.setLayoutManager(llm);
        }
    }

    public class TeamAdapter extends RecyclerView.Adapter<TrainingScreen> {
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();
        private Context context;
        private LayoutInflater vi;
        private List<GetTeam> getTeamsDetailsClasList;

        public TeamAdapter(Context context, List<GetTeam> getTeamsDetailsClasList) {
            this.context = context;
            binderHelper.setOpenOnlyOne(true);
            this.getTeamsDetailsClasList = getTeamsDetailsClasList;
        }

        @Override
        public TrainingScreen onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_view_items_training, parent, false);
            return new TrainingScreen(itemView);
        }

        @Override
        public void onBindViewHolder(TrainingScreen holder, final int position) {

            holder.folderIcon.setVisibility(View.GONE);
            holder.rlayoutCount.setVisibility(View.VISIBLE);

            holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));


            holder.rLayoutForTraining.setLockDrag(true);

            if (getTeamsDetailsClasList.get(position).getTeamId().equalsIgnoreCase("0")) {
                holder.rrrss.setVisibility(GONE);
                if (SplashScreen.PlayVideo) {
                    holder.rrrss.setVisibility(GONE);
                } else {
                    // holder.rrrss.setVisibility(VISIBLE);
                }

            }
//            holder.rLayoutForTraining.setOnClickListener(view -> {
//               // showDialogofTraining(context,"",);
//            });


            //holder.rLayoutForTraining.setOnClickListener(this);

            holder.arrow.setOnClickListener(view -> {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                if (holder.ProgramList.getVisibility() == View.VISIBLE) {
                    holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    holder.ProgramList.setVisibility(View.GONE);
                } else {
                    holder.ProgramList.setVisibility(View.VISIBLE);
                    holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                }
            });

            holder.textViewTrainingName.setTextColor(Color.parseColor("#edbb57"));
            holder.rrrss.setOnClickListener(view -> {
                try {
                    showDialogofTraining(context, 0, 0, getTeamsDetailsClasList.get(position).getTeamName(), "", 0, getTeamsDetailsClasList.get(position).getTeamId());
                } catch (Exception x) {

                }
            });

            try {
                Log.d(UtilityClass.TAG, "onBindViewHolder: " + getTeamsDetailsClasList.get(position).getTrainingProgramDetail().size());

                holder.textViewCount.setText(getTeamsDetailsClasList.get(position).getTrainingProgramDetail().size() + "");
                //programAdapter = ;
                holder.ProgramList.setAdapter(new ProgramAdapter(context, getTeamsDetailsClasList.get(position).getTrainingProgramDetail(), position));
            } catch (Exception x) {

            }

//            if(userTypeOf.equalsIgnoreCase("1") ||userTypeOf.equalsIgnoreCase("2")|| userTypeOf.equalsIgnoreCase("3")  ){
//                if(getTeamsDetailsClasList.get(position).getTeamId().equalsIgnoreCase("0")){
            holder.textViewTrainingName.setText(getTeamsDetailsClasList.get(position).getTeamName());
//                }else {
//                    holder.textViewTrainingName.setText(getTeamsDetailsClasList.get(position).getTeamName() + " - "+ getTeamsDetailsClasList.get(position).getSchoolName());
//                }
//            }


            //holder.textViewTrainingName.setText(getTeamsDetailsClasList.get(position).getTeamName());
            //holder.rLayoutForTraining.setPadding(5,5,5,5)
            //holder.textViewTrainingName.setPadding(25, 20, 0, 20);
        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = getTeamsDetailsClasList.size();
            } catch (Exception c) {

            }
            return count;
        }


        public void filter(String s) {
            String text = s.toLowerCase();
            if (text.length() == 0) {
                getTeamsDetailsClasList = getTeamsList;
            } else {
                getTeamsDetailsClasList = new ArrayList<GetTeam>();
                for (int i = 0; i < getTeamsList.size(); i++) {
                    if (getTeamsList.get(i).getTeamName().toLowerCase().contains(text)) {
                        getTeamsDetailsClasList.add(getTeamsList.get(i));
                    }
                }
            }
            notifyDataSetChanged();
        }


    }


    public class ProgramAdapter extends RecyclerView.Adapter<TrainingScreen> {
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();
        List<com.org.godspeed.response_JsonS.getTeams.TrainingProgramDetail> trainingProgramDetail;
        int positionofTeam;
        private Context context;
        private LayoutInflater vi;

        public ProgramAdapter(Context context, List<com.org.godspeed.response_JsonS.getTeams.TrainingProgramDetail> trainingProgramDetail, int positionofTeam) {
            this.context = context;
            binderHelper.setOpenOnlyOne(true);
            this.trainingProgramDetail = trainingProgramDetail;
            this.positionofTeam = positionofTeam;
        }


        @Override
        public TrainingScreen onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_view_items_training, parent, false);
            return new TrainingScreen(itemView);
        }

        @Override
        public void onBindViewHolder(TrainingScreen holder, final int position) {
            holder.folderIcon.setVisibility(View.GONE);
            holder.rlayoutCount.setVisibility(View.GONE);
            holder.arrow.setVisibility(View.GONE);
            holder.DateofProgram.setVisibility(View.VISIBLE);
            holder.DateofProgram.setText(trainingProgramDetail.get(position).getStartDate());


            //holder.rLayoutForTraining.setPadding(10,00,0,10);
            holder.DateofProgram.setPadding(0, 0, 30, 0);


            binderHelper.bind(holder.rLayoutForTraining, String.valueOf(position));

            holder.Assign.setVisibility(View.GONE);
            holder.Copy.setVisibility(View.GONE);
            holder.Edit.setVisibility(View.GONE);
            holder.Rename.setVisibility(View.GONE);
            holder.Move.setVisibility(View.GONE);

            holder.textViewTrainingName.setTextColor(Color.parseColor("#cdcdcd"));
            holder.DateofProgram.setTextColor(Color.parseColor("#cdcdcd"));

            holder.DateofProgram.setOnClickListener(view -> {
                DialogFragment dialogfragment = new DatePickerDialogClass("", trainingProgramDetail.get(position).getAssignProgramId());
                dialogfragment.show(getFragmentManager(), "Date Picker Dialog");
                trainingProgramDetailPosition = position;
                positionofTeamMain = positionofTeam;
                webServices.updateAssignProgramStartDate("", "", context, SelectTeamAndAthleteScreen.this);
            });


            holder.Delete.setOnClickListener(view -> {
                hide();
                whichApiCalled = "deleteAssignProgram";
                webServices.deleteAssignProgram(trainingProgramDetail.get(position).getAssignProgramId(), context, SelectTeamAndAthleteScreen.this);
                holder.rLayoutForTraining.close(true);
                trainingProgramDetail.remove(position);
                notifyDataSetChanged();
            });

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.rrrss.getLayoutParams();
            layoutParams.setMargins(10, 5, 10, 5);
//            try{
//                holder.textViewCount.setText(trainingProgramDetail.get(position).().size()+"");
//
//            }catch (Exception x){
//
//            }
            holder.textViewTrainingName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            holder.textViewTrainingName.setText(trainingProgramDetail.get(position).getProgramName());
            //holder.rLayoutForTraining.setPadding(5,5,5,5)
            // holder.textViewTrainingName.setPadding(20, 10, 0, 10);
        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = trainingProgramDetail.size();
            } catch (Exception c) {

            }
            return count;
        }


    }
}
