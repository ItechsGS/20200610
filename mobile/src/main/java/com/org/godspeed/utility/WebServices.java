package com.org.godspeed.utility;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.org.godspeed.ProgramPurchase.ProgramPurchaseFragment;
import com.org.godspeed.ProgramPurchase.purchase_membership;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.AbrActivity;
import com.org.godspeed.allOtherClasses.AddTeamCoachAthleteScreen;
import com.org.godspeed.allOtherClasses.AthleteExerciseSetActivity;
import com.org.godspeed.allOtherClasses.BodySkeletonInfoScreen;
import com.org.godspeed.allOtherClasses.CoachAddExerciseScreen;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.allOtherClasses.HelpScreenData;
import com.org.godspeed.allOtherClasses.LoginScreen;
import com.org.godspeed.allOtherClasses.PrivacyAndQuestionnariesScreen;
import com.org.godspeed.allOtherClasses.ProfileScreenActivity;
import com.org.godspeed.allOtherClasses.SelectAthlete;
import com.org.godspeed.allOtherClasses.SelectTeamAndAthleteScreen;
import com.org.godspeed.allOtherClasses.UserProfileScreen;
import com.org.godspeed.allOtherClasses.exercise_Activity;
import com.org.godspeed.chat.chat_activity;
import com.org.godspeed.fragments.AthleteProfileFragment;
import com.org.godspeed.fragments.Change_password_Fragment;
import com.org.godspeed.fragments.CoachBoardFragments;
import com.org.godspeed.fragments.Competition_Board;
import com.org.godspeed.fragments.FragmentComparativeAnalytics;
import com.org.godspeed.fragments.FragmentTrainingAndFolder;
import com.org.godspeed.fragments.Subscription;
import com.org.godspeed.fragments.WhiteBoard;
import com.org.godspeed.fragments.messageFragment;
import com.org.godspeed.fragments.purchase_history;
import com.org.godspeed.fragments.scheduleCalender;
import com.org.godspeed.service.BackgroundLocationUpdateService;
import com.org.godspeed.service.SchoolDataService;
import com.org.godspeed.zoom_video.ui.LoginUserStartJoinMeetingActivity;

import org.apache.http.params.HttpParams;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static com.org.godspeed.allOtherClasses.SplashScreen.GS_DEBUG_BUILD;
import static com.org.godspeed.utility.UtilityClass.hide;


public class WebServices {


//    public static final String BASE_URL = SplashScreen.GS_DEBUG_BUILD ? "http://www.dev.rpmhoover.com/" : "http://individuals.godspeedgo.com/";
//    public static final String BASE_URL_FOR_IMAGES = SplashScreen.GS_DEBUG_BUILD ? "http://www.dev.rpmhoover.com/assets/user_images" : "http://individuals.godspeedgo.com/assets/user_images";
//    public static final String BASE_URL_FOR_IMAGES_ASSETS = SplashScreen.GS_DEBUG_BUILD ? "http://www.dev.rpmhoover.com/assets" : "http://individuals.godspeedgo.com/assets";

    public static String BASE_URL = GS_DEBUG_BUILD ? "http://www.dev.godspeedgo.com/" : "https://www.godspeedgo.com/";
    public static String BASE_URL_FOR_IMAGES = GS_DEBUG_BUILD ? "http://www.dev.godspeedgo.com/assets/user_images" : "https://www.godspeedgo.com/assets/user_images";
    public static String BASE_URL_FOR_IMAGES_ASSETS = GS_DEBUG_BUILD ? "http://www.dev.godspeedgo.com/assets" : "https://www.godspeedgo.com/assets";
    public static boolean CallApi = false;
    public static String responseMessageOfServer = "";
    public static int call_homescreen;
    public static boolean isDilogShowing = true;
    public static int responseCode;
    private final String ENCRIPTION_KEY = "00f74597de203655b1ebf5f410f10eb8";
    private final String SECRET_KEY_FOR_CIPHER = "sn7rhs5nyi98TM990lavyh6loinyz9chyxw";
    private final String getTrainingProgramAssignDetailByIdMultiple = "Webservices/Training_Program/getTrainingProgramAssignDetailByIdMultiple";
    private final String getTrainingProgramDetailById = "Webservices/Training_Program/getTrainingProgramDetailById";
    private final String version = "1.0";
    private final String plateform = "android";
    private final String METHOD_LOGIN = "signIn";
    private final String METHOD_ADD_COACH = "addCoachToTeam";
    private final String METHOD_FORGOT_PASSWORD = "forgetPassword";
    private final String METHOD_UPDATE_PROFILE = "updateProfile";
    private final String METHOD_SIGNUP_USER = "signUp";
    private final String METHOD_FAQ = "Faq/get";
    private final String METHOD_CONTACT_US = "Contact_us/get";
    private final String METHOD_SETTINGS = "App_settings/get";
    private final String METHOD_RESET_PASSWORD = "Reset_Password";
    private final String METHOD_CHANGE_PASSWORD = "athlete_password_reset";
    private final String METHOD_GET_PROGRAMS = "GetPrograms";
    private final String METHOD_PRIVACY_POLLICY = "privacyPolicy";
    private final String METHOD_QUESTIONS_LIST = "getQuestionaire";
    private final String METHOD_ANSWERS_OF_QUST = "userAns";
    private final String METHOD_SUBMIT_ANSWERS = "submitResponse";
    private final String METHOD_GET_SCHOOL = "getSchools";
    private final String METHOD_GET_SPORTS = "getSports";
    private final String METHOD_GET_LANGUAGE = "getLanguages";
    private final String METHOD_ADD_TEAM = "setAddTeams";
    private final String METHOD_GET_TEAM = "getTeams";
    private final String METHOD_ADD_ATHLETE = "setAddAthlete";
    private final String METHOD_GET_POSITION = "getPositions";
    private final String METHOD_PROGRAM_TRAINING = "programsTraining";
    private final String METHOD_GET_ATHLETE_BY_TEAM = "getAthletes";
    private final String METHOD_SET_ATHLETE_ATTENDANCE = "setAttendance";
    private final String METHOD_SET_IMAGE = "setImage";
    private final String METHOD_Athlete_class = "Webservices/Athlete_class/get";
    private final String TAG = "s";
    private final int connectionSocektTimeout = 300000;
    private final int connectionRequestTimeout = 300000;
    String API_GET_TOKEN = WebServices.BASE_URL + "Webservices/Braintreepayment_gateway/get";
    String API_CHECKOUT = WebServices.BASE_URL + "Webservices/Braintreepayment_gateway/payment_checkout";
    int retry = 0;
    RequestQueue MyRequestQueue;
    Map<String, String> MyData;
    StringRequest MyStringRequest;
    private String response = "";
    private HttpParams httpParameters;
    private String apiURL = "";
    private String deviceId = "";
    private GodSpeedInterface objInterface;
    private ProgressDialog prgDailog;
    private String message = "";
    private UtilityClass objUtility = new UtilityClass();
    private String defaultDevicetoken = "123";
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                if (prgDailog != null && prgDailog.isShowing()) {
                    prgDailog.dismiss();
                }
                objInterface.ApiResponse(response);
            }
        }
    };
    private Context mContext;

    public void login(String userName, String password, String ip_address,
                      String device_type,
                      String device_token,
                      Context context,
                      LoginScreen activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_LOGIN;

        MyData.put("emailId", userName);
        MyData.put("password", password);
        MyData.put("device_type", device_type);
        MyData.put("ip_address", ip_address);
        MyData.put("device_token", device_token);

        Log.e("Login API Called", "**************");
        Log.e(VolleyLog.TAG, " \n**************** login :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

        //GiveBackResponseToActivity();
    }


    public void AddNotesWhiteBoard(String assign_program_id,
                                   String athlete_id,
                                   String notes,
                                   String training_exercise_id,
                                   String training_program_detail_id,
                                   Context context,
                                   WhiteBoard activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        Log.e("Login API Called", "**************");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Athlete_note/AddNotesWhiteBoard";


        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        MyData.put("assign_program_id", assign_program_id);

        MyData.put("athlete_id", athlete_id);

        MyData.put("notes", notes);

        MyData.put("training_exercise_id", training_exercise_id);

        MyData.put("training_program_detail_id", training_program_detail_id);
        CheckInterNetConnection(context, true);


        Log.e(VolleyLog.TAG, " \n****************      AddNotesWhiteBoard:  *****************" + apiURL + " \n " + MyData + " \n ****************");


    }


    public void GetWhiteBoardData(String assign_program_id,
                                  String team_id,
                                  String sport_id,
                                  String date, Context context,
                                  WhiteBoard activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        Log.e("Login API Called", "**************");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/GetWhiteBoardData/GetWhiteBoardData";

        MyData.put("assign_program_id", assign_program_id);

        MyData.put("team_id", team_id);

        MyData.put("sport_id", sport_id);

        MyData.put("date", date);

        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n****************   AddNotesWhiteBoard: **************** \n " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void setPurchaseDetail(String user_id,
                                  String training_program_id,
                                  String transactions_id,

                                  Context context,
                                  purchase_membership activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        Log.e("Login API Called", "**************");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Training_program_purchase_detail/setPurchaseDetail";

        MyData.put("user_id", user_id);

        MyData.put("training_program_id", training_program_id);

        MyData.put("transactions_id", transactions_id);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n****************   setPurchaseDetail: **************** \n " + apiURL + " \n " + MyData + " \n ****************");

    }


    public void CheckInterNetConnection(Context context, Boolean ShowDialogOn) {
        Dialog dialog;
        dialog = new Dialog(context);
        try {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        } catch (Exception v) {

        }
        dialog.setContentView(R.layout.custom_dialog_box_for_training_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setDimAmount(0);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        LinearLayout BeginTraining, Cancel, workoutSummary, SingleRow, CancelXc, YesX;
        TextView EventName, EventNameX, begin, workout, cancel, Yes, CancelX;

        BeginTraining = dialog.findViewById(R.id.BeginTraining);
        Cancel = dialog.findViewById(R.id.Cancel);
        Yes = dialog.findViewById(R.id.Yes);
        workoutSummary = dialog.findViewById(R.id.workoutSummary);
        begin = dialog.findViewById(R.id.begin);
        workout = dialog.findViewById(R.id.workout);
        cancel = dialog.findViewById(R.id.cancel);
        SingleRow = dialog.findViewById(R.id.SingleRow);

        YesX = dialog.findViewById(R.id.YesX);
        CancelXc = dialog.findViewById(R.id.CancelXc);


        Yes.setText("Retry");

        YesX.setOnClickListener(view -> {
            dialog.dismiss();
            CheckInterNetConnection(context, true);
        });

        CancelXc.setOnClickListener(view -> dialog.dismiss());

        SingleRow.setVisibility(View.VISIBLE);


        EventName = dialog.findViewById(R.id.EventName);

        EventNameX = dialog.findViewById(R.id.EventNameX);

        EventNameX.setText(context.getResources().getString(R.string.internet_error));


//        showDialogofTraining(context,0,0,GetTeam.get(position).getTeamName(),"",0);
        EventName.setTypeface(CustomTypeface.load_Montserrat_Bold_Fonts(context));
        EventNameX.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        begin.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        workout.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        cancel.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));

        BeginTraining.setVisibility(View.GONE);
        workoutSummary.setVisibility(View.GONE);
        Cancel.setVisibility(View.GONE);


        BeginTraining.setOnClickListener(view -> dialog.dismiss());

        workoutSummary.setOnClickListener(view -> dialog.dismiss());

        Cancel.setOnClickListener(view -> dialog.dismiss());

        Log.e(VolleyLog.TAG, "CheckInterNetConnection: " + UtilityClass.isDeviceInternetAvailable(context));
        if (UtilityClass.isDeviceInternetAvailable(context)) {
            if (ShowDialogOn) {
                UtilityClass.showWaitDialog(new Dialog(context), context);
            }
            GiveBackResponseToActivity();


        } else {
            try {
                dialog.show();
                Log.e(VolleyLog.TAG, " \n**************** CheckInterNetConnection: AA gyo");
            } catch (Exception v) {

            }

        }
    }


    public void getAssignProgram(String team_id, String date, Context context, WhiteBoard activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        Log.e("Login API Called", "**************");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getAssignProgram";


        MyData.put("team_id", team_id);

        MyData.put("date_training_program", date);

        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n****************   getAssignProgram:  **************** " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void addLiveClass(String category_id, String current_date, String video_id, String password, Context context, LoginUserStartJoinMeetingActivity activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Live_class_exercise/addLiveClass";

        MyData.put("category_id", category_id);
        MyData.put("current_date", current_date);
        MyData.put("video_name", video_id);
        MyData.put("password", password);


        Log.e(VolleyLog.TAG, " \n****************   addLiveClass :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);

    }


    public void GetAthleteHealth(String date_type, String user_id, String start_date, String New, Context context, AthleteProfileFragment activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Athlete_health/get_athlete_health_data";

        MyData.put("date_type", date_type);

        MyData.put("user_id", user_id);

        MyData.put("start_date", start_date);


        Log.e(VolleyLog.TAG, " \n****************   GetAthleteHealth :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }


    public void getAssignProgram(String athlete_id,
                                 String date, Context context,
                                 AthleteProfileFragment activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        Log.e("Login API Called", "**************");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getAssignProgram";

        MyData.put("athlete_id", athlete_id);

        MyData.put("date_training_program", date);

        Log.e(VolleyLog.TAG, " \n**************** getAssignProgram :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void setHeartBeat(
            String user_id,
            String heart_beat,
            String current_date,
            String heart_rate_type,
            Context context,
            AthleteProfileFragment activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        Log.e("Login API Called", "**************");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/setHeartBeat";

        MyData.put("user_id", user_id);
        MyData.put("heart_beat", heart_beat);
        MyData.put("current_date", current_date);
        MyData.put("heart_rate_type", heart_rate_type);
        Log.e(VolleyLog.TAG, " \n**************** setHeartBeat :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }


    public void getHeartBeat(
            String user_id,
            String start_date,
            String end_date,
            String heart_rate_type,
            Context context,
            AthleteProfileFragment activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        Log.e("Login API Called", "**************");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getHeartBeat";

        MyData.put("user_id", user_id);
        MyData.put("start_date", start_date);
        MyData.put("end_date", end_date);
        MyData.put("heart_rate_type", heart_rate_type);
        Log.e(VolleyLog.TAG, " \n**************** getHeartBeat :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }


//    private void CheckInterNetConnection(Context context, Boolean ShowDialogOn){
//        if(UtilityClass.isDeviceInternetAvailable(context)){
//            if(ShowDialogOn){
//                UtilityClass.showWaitDialog(new Dialog(context),context);
//            }
//            AsyncApiCalling objApiCalling = new AsyncApiCalling();
//            objApiCalling.execute(apiURL);
//            //objApiCalling.showDialog()
//
//        }else {
//            AlertDialog.Builder alert = new AlertDialog.Builder(context);
//            alert.setTitle(context.getResources().getString(R.string.internet_error));
//            alert.setIcon(android.R.drawable.star_on);
//            alert.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    CheckInterNetConnection(context, true);
//                }});
//            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                   hide();
//                    // finish();
//                } });
//            alert.show();
//        }
//    }


    public void updateAthleteExerciseStatus(String training_exercise_id_auto,
                                            String work_duration,
                                            String athlete_id,
                                            String status,
                                            String assign_program_id, Context context, AthleteExerciseSetActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Athleteexercisestatus/" + "updateAthleteExerciseStatus";

        MyData.put("training_exercise_id_auto", "" + training_exercise_id_auto);

        MyData.put("work_duration", work_duration);
        MyData.put("athlete_id", athlete_id);
        MyData.put("status", status);
        MyData.put("assign_program_id", assign_program_id);
        Log.e(VolleyLog.TAG, " \n**************** updateAthleteExerciseStatus :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }


    public void addAthleteActiveExerciseStatus(String training_program_id,
                                               String athlete_id,
                                               String phase,
                                               String week,
                                               String day,
                                               String pillar,
                                               String exercise_type_id,
                                               String exercise_id,
                                               Context context, AthleteExerciseSetActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercise/AddAthleteActiveExerciseStatus";

        MyData.put("training_program_id", training_program_id);
        MyData.put("athlete_id", athlete_id);
        MyData.put("phase", phase);
        MyData.put("week", week);
        MyData.put("day", day);
        MyData.put("pillar", pillar);
        MyData.put("exercise_type_id", exercise_type_id);
        MyData.put("exercise_id", exercise_id);

        Log.e(VolleyLog.TAG, " \n**************** addAthleteActiveExerciseStatus :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);


    }


    public void getTrainingProgramDetailById(String program_id, Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + getTrainingProgramDetailById;

        MyData.put("program_id", program_id);

        Log.e(VolleyLog.TAG, " \n**************** getTrainingProgramDetailById :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }


    public void getTrainingProgramDetailById(String program_id, Context context, WhiteBoard activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + getTrainingProgramAssignDetailByIdMultiple;


        MyData.put("assign_program_id", program_id);
        Log.e(VolleyLog.TAG, " \n**************** getTrainingProgramDetailById :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void copyPhaseWeekExercise(
            String training_program_id,
            String from_phase,
            String from_week,
            String to_phase,
            String to_week,
            String from_day,
            String to_day,
            Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercise/copyPhaseWeekExercise";

        MyData.put("training_program_id", training_program_id);
        MyData.put("from_phase", from_phase);
        MyData.put("from_week", from_week);
        MyData.put("to_phase", to_phase);
        MyData.put("to_week", to_week);
        MyData.put("from_day", from_day);
        MyData.put("to_day", to_day);
        Log.e(VolleyLog.TAG, " \n**************** copyPhaseWeekExercise :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }


    public void UpdateTrainingExerciseTimeDuration(String training_program_detail_id, String type_id, String time_duration, Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercise/UpdateTrainingExerciseTimeDuration";

        MyData.put("training_program_detail_id", training_program_detail_id);
        MyData.put("type_id", type_id);
        MyData.put("time_duration", time_duration);
        Log.e(VolleyLog.TAG, " \n**************** UpdateTrainingExerciseTimeDuration :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }


    public void add_injury(String athlete_id, String injury_part_name, String reported_by_id, Context context, BodySkeletonInfoScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Injury/add_injury";

        MyData.put("athlete_id", athlete_id);
        MyData.put("injury_part_name", injury_part_name);
        MyData.put("reported_by_id", reported_by_id);
        Log.e(VolleyLog.TAG, " \n**************** add_injury :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void getInjuries(String athlete_id, Context context, BodySkeletonInfoScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Injury/getInjury";

        MyData.put("athlete_id", athlete_id);
        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** getInjuries :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void AddInjuriesNote(String injury_id, String note_date, String notes, String notes_id, Context context, BodySkeletonInfoScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Injury/addNote";

        MyData.put("injury_id", injury_id);
        MyData.put("note_date", note_date);
        MyData.put("notes", notes);
        MyData.put("notes_id", notes_id);
        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** AddInjuriesNote :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void updateInjuryDate(String reported_date, String recovered_date, String injury_id, Context context, BodySkeletonInfoScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Injury/updateInjuryDate";


        MyData.put("injury_id", injury_id);
        MyData.put("reported_date", reported_date);
        MyData.put("recovered_date", recovered_date);
        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** updateInjuryDate :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }


    public void getPrescriptionAbr(Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getPrescriptionAbr";
        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** getPrescriptionAbr :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void getPrescriptionAbr(Context context, exercise_Activity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getPrescriptionAbr";


        Log.e(VolleyLog.TAG, " \n**************** getPrescriptionAbr :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void getPrescriptionAbr(Context context, AbrActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getPrescriptionAbr";

        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** getPrescriptionAbr :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void addTrainingExercise(String program_id,
                                    String phase,
                                    String week,
                                    String day,
                                    String pillar,
                                    String type_id,
                                    String exercise_id,
                                    String measurement_id,
                                    String DoseID,
                                    String exercise_type_group_id,
                                    Context context, exercise_Activity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercise/addTrainingExercise";


        MyData.put("program_id", program_id);
        MyData.put("phase", phase);
        MyData.put("week", week);
        MyData.put("day", day);
        MyData.put("pillar", pillar);
        MyData.put("type_id", type_id);
        MyData.put("exercise_id", exercise_id);
        MyData.put("measurement_id", measurement_id);
        MyData.put("dose_id", DoseID);
        MyData.put("exercise_type_group_id", exercise_type_group_id);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** addTrainingExercise :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void addTrainingWODExercise(String program_id,
                                       String phase,
                                       String week,
                                       String day,
                                       String pillar,
                                       String type_id,
                                       String exercise_id,
                                       String measurement_id,
                                       String DoseID,
                                       String exercise_type_group_id,
                                       String wod_type,
                                       String wod_dose_values,
                                       String wod_details,
                                       Context context, exercise_Activity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercise/addTrainingExercise";


        MyData.put("program_id", program_id);
        MyData.put("phase", phase);
        MyData.put("week", week);
        MyData.put("day", day);
        MyData.put("pillar", pillar);
        MyData.put("type_id", type_id);
        MyData.put("exercise_id", exercise_id);
        MyData.put("measurement_id", measurement_id);
        MyData.put("dose_id", DoseID);
        MyData.put("exercise_type_group_id", exercise_type_group_id);
        MyData.put("wod_type", wod_type);
        MyData.put("wod_dose_values", wod_dose_values);
        MyData.put("wod_details", wod_details);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** addTrainingExercise :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }


    public void Trainingprogram_detailadd(String program_id,
                                          String phase,
                                          String week,
                                          String day,
                                          String pillar,
                                          Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingprogram_detail/add";


        MyData.put("program_id", program_id);
        MyData.put("phase", phase);
        MyData.put("week", week);
        MyData.put("day", day);
        MyData.put("pillar", pillar);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** Trainingprogram_detailadd :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void Trainingexercisedelete(String id,
                                       Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercise/delete";

        MyData.put("id", id);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** Trainingexercisedelete :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void changeDoseValue(String training_exercise_id, String dose_id, String week, String day, Context context, AbrActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Dose/changeDoseValue";

        MyData.put("training_exercise_id", training_exercise_id);
        MyData.put("dose_id", dose_id);
        MyData.put("week", week);
        MyData.put("day", day);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** Trainingexercisedelete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
    }

    public void getPrescriptionAbrDetail(String abrID, Context context, AbrActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getPrescriptionAbrDetails";

        MyData.put("id", abrID);

        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** getPrescriptionAbrDetail :  \n  " + apiURL + " \n " + MyData + " \n ****************");


    }


    public void getPrescriptionAbrDetail(String abrID, Context context, exercise_Activity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getPrescriptionAbrDetails";


        MyData.put("id", abrID);

        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** getPrescriptionAbrDetail :  \n  " + apiURL + " \n " + MyData + " \n ****************");


    }

    public void SelectMeasurement(Context context, exercise_Activity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Myprofile/getExerciseSets";


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** SelectMeasurement :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void workoutSummary(String program_id, String athleteID, String workout_summary, String athlete_exercise_status, Context context, AthleteExerciseSetActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Training_Program/workoutSummary";

        MyData.put("assign_program_id", program_id);

        MyData.put("athlete_id", athleteID);
        MyData.put("workout_summary", workout_summary);
        MyData.put("athlete_exercise_status", athlete_exercise_status);

        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** getTrainingProgramAssignDetailByIdMultiple :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void getTrainingProgramAssignDetailByIdMultiple(String program_id, String athleteID, Context context, AthleteExerciseSetActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + getTrainingProgramAssignDetailByIdMultiple;

        MyData.put("assign_program_id", program_id);

        MyData.put("athlete_id", athleteID);

        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** getTrainingProgramAssignDetailByIdMultiple :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void getTrainingProgramAssignDetailByIdMultiple(String program_id, String athleteID, Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + getTrainingProgramAssignDetailByIdMultiple;

        MyData.put("assign_program_id", program_id);

        MyData.put("athlete_id", athleteID);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** getTrainingProgramAssignDetailByIdMultiple :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void deleteCustomDoseByCoach(String custom_dose_id, Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercisemeasurement/deleteCustomDoseByCoach";

        MyData.put("custom_dose_id", custom_dose_id);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** deleteCustomDoseByCoach :  \n  " + apiURL + " \n " + MyData + " \n ****************");


    }


    public void addCustomDoseByCoach(String training_exercise_id, String measurement_id, String measurement_value, String custom_dose_id, Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercisemeasurement/addCustomDoseByCoach";

        MyData.put("training_exercise_id", training_exercise_id);
        MyData.put("measurement_id", measurement_id);
        MyData.put("measurement_value", measurement_value);
        MyData.put("custom_dose_id", custom_dose_id);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** addCustomDoseByCoach :  \n  " + apiURL + " \n " + MyData + " \n ****************");


    }


    public void addExerciseNotes(String training_exercise_unique_id, String exercise_notes, String note_date, String note_id, Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Athlete_note/addExerciseNotes";

        MyData.put("training_exercise_unique_id", training_exercise_unique_id);
        MyData.put("exercise_notes", exercise_notes);
        MyData.put("note_date", note_date);
        MyData.put("note_id", note_id);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** addExerciseNotes :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }


    public void addExerciseTypeNotes(String training_exercise_unique_id, String exercise_type_notes, String note_date, String note_id, Context context, CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Athlete_note/addExerciseTypeNotes";

        MyData.put("training_exercise_unique_id", training_exercise_unique_id);
        MyData.put("exercise_type_notes", exercise_type_notes);
        MyData.put("note_date", note_date);
        MyData.put("note_id", note_id);

        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** addExerciseTypeNotes :  \n  " + apiURL + " \n " + MyData + " \n ****************");


    }

    public void get_Athlete_class(String coach_id, Context context, CoachNevigationDrawerScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + METHOD_Athlete_class;

        MyData.put("coach_id", coach_id);
        Log.e(VolleyLog.TAG, " \n**************** get_Athlete_class :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);

    }

    public void UpdateDoseAthlete(String assign_program_id,
                                  String athlete_id,
                                  String reps_percent,
                                  String reps_value,
                                  String training_exercise_auto_id,
                                  String weight,
                                  String training_exercise_id,
                                  String training_program_id,
                                  String training_exercise_dose_id_auto,
                                  String dose_details_id,
                                  Context context,
                                  AthleteExerciseSetActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Dose/updateDose";

        MyData.put("assign_program_id", "" + assign_program_id);
        MyData.put("athlete_id", athlete_id);
        MyData.put("reps_percent", reps_percent);
        MyData.put("reps_value", reps_value);
        MyData.put("training_exercise_auto_id", training_exercise_auto_id);
        MyData.put("weight", weight);
        MyData.put("training_exercise_id", training_exercise_id);
        MyData.put("training_program_id", training_program_id);
        MyData.put("training_exercise_dose_id_auto", training_exercise_dose_id_auto);
        MyData.put("dose_details_id", dose_details_id);


        CheckInterNetConnection(context, true);
        Log.e(VolleyLog.TAG, " \n**************** UpdateDoseAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }

    public void AddAndUpdateExerciseSet(
            String custom_dose_coach_id_auto,
            String training_exercise_id,
            String athlete_id,
            String assign_program_id,
            String measurement_id,
            String measurement_value,


            String custom_dose_coach_id_auto_copy, Context context,
            AthleteExerciseSetActivity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();

        apiURL = BASE_URL + "Webservices/Trainingexercisemeasurement/addAthleteCustomMeasurementValues";

        MyData.put("custom_dose_coach_id_auto", custom_dose_coach_id_auto);
        MyData.put("training_exercise_id", training_exercise_id);
        MyData.put("athlete_id", athlete_id);
        MyData.put("assign_program_id", assign_program_id);
        MyData.put("measurement_id", measurement_id);
        MyData.put("measurement_value", measurement_value);
        MyData.put("custom_dose_coach_id_auto_copy", custom_dose_coach_id_auto_copy);

        CheckInterNetConnection(context, false);
        Log.e(VolleyLog.TAG, " \n**************** AddAndUpdateExerciseSet :  \n  " + apiURL + " \n " + MyData + " \n ****************");

    }


    public void AddAndUpdateExerciseSet(
            String custom_dose_coach_id_auto,
            String training_exercise_id,
            String athlete_id,
            String assign_program_id,
            String measurement_id,
            String measurement_value,
            String custom_dose_coach_id_auto_copy, Context context,
            CoachAddExerciseScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Trainingexercisemeasurement/addAthleteCustomMeasurementValues";

        MyData.put("custom_dose_coach_id_auto", custom_dose_coach_id_auto);
        MyData.put("training_exercise_id", training_exercise_id);
        MyData.put("athlete_id", athlete_id);
        MyData.put("assign_program_id", assign_program_id);
        MyData.put("measurement_id", measurement_id);
        MyData.put("measurement_value", measurement_value);
        MyData.put("custom_dose_coach_id_auto_copy", custom_dose_coach_id_auto_copy);


        Log.e(VolleyLog.TAG, " \n**************** AddAndUpdateExerciseSet " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void AddAndUpdateExerciseSet(
            String custom_dose_coach_id_auto,
            String training_exercise_id,
            String athlete_id,
            String assign_program_id,
            String measurement_id,
            String measurement_value,
            String custom_dose_coach_id_auto_copy, Context context,
            WhiteBoard activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Trainingexercisemeasurement/addAthleteCustomMeasurementValues";

        MyData.put("custom_dose_coach_id_auto", custom_dose_coach_id_auto);
        MyData.put("training_exercise_id", training_exercise_id);
        MyData.put("athlete_id", athlete_id);
        MyData.put("assign_program_id", assign_program_id);
        MyData.put("measurement_id", measurement_id);
        MyData.put("measurement_value", measurement_value);
        MyData.put("custom_dose_coach_id_auto_copy", custom_dose_coach_id_auto_copy);


        Log.e(VolleyLog.TAG, " \n**************** AddAndUpdateExerciseSet " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void GetFAQ(Context context,
                       HelpScreenData activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/" + METHOD_FAQ;


        Log.e(VolleyLog.TAG, " \n**************** GetFAQ :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void getTimeZone(Context context, UserProfileScreen userProfileScreen) {
        mContext = context;
        objInterface = userProfileScreen;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/" + "Timezone/getTimeZone";


        Log.e(VolleyLog.TAG, " \n**************** getTimeZone :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }

    public void GetContact_us(Context context,
                              HelpScreenData activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/" + METHOD_CONTACT_US;


        Log.e(VolleyLog.TAG, " \n**************** GetContact_us :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void GetSettings(Context context,
                            HelpScreenData activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/" + METHOD_SETTINGS;


        CheckInterNetConnection(context, true);

    }

    public void ForgotPassword(String emailId, Context context,
                               LoginScreen activity) {
        // isLoginApiCalled = true;
        Log.e("Forgot Email id is = ", emailId + "&&&&&&&&&");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_FORGOT_PASSWORD;


        MyData.put("to_mail",
                emailId);


        Log.e(VolleyLog.TAG, " \n**************** ForgotPassword :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void SignUp(String emailId, String password, String school_id, String team_position_title_id, String userType, String backgroundThemePath, String device_token, String device_type, String ip_address, Context context,
                       LoginScreen activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_SIGNUP_USER;


        MyData.put("emailId",
                emailId);

        MyData.put("password", password);

        MyData.put("school_id", school_id);

        if (userType.equalsIgnoreCase("4")) {
            MyData.put("team_id", team_position_title_id);
            MyData.put("position_title_id", "");
        } else {
            MyData.put("position_title_id", team_position_title_id);
//                 MyData.put("background_image", "");
        }

        MyData.put("user_type", userType);
        MyData.put("device_token", device_token);
        MyData.put("device_type", device_type);
        MyData.put("ip_address", ip_address);


        Log.e(VolleyLog.TAG, " \n**************** SignUp :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }





    /*public void UpdateProfile(String firstname, String lastname, String dob, String gender, String language, String suit, String street, String state, String zip, String country, String city, String school_address, String sports, String social_head, String userid, String backgroundThemePath, Context context,
                       UpdatePofileInfoFragment activity) {
        // isLoginApiCalled = true;
        mContext = context;
         objInterface = activity;
 MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_UPDATE_PROFILE;
        
        


             MyData.put("first_name",
                            firstname);
             MyData.put("last_name", lastname);
             MyData.put("dob", dob);
             MyData.put("user_id", userid);
             MyData.put("gender", gender);
             MyData.put("language", language);
             MyData.put("suit", suit);
             MyData.put("street", street);
             MyData.put("city", city);
             MyData.put("state", state);
             MyData.put("zip", zip);
             MyData.put("country", country);
             MyData.put("school_address", school_address);
             MyData.put("social_head", social_head);
             MyData.put("sports", sports);
             MyData.put("background_image ", backgroundThemePath);



            CheckInterNetConnection(context);

    }*/

   /* public void UpdateProfile(String firstname,
                              String lastname,
                              String emailid ,
                              String dob,
                              String gender,
                              String languageId,
                              String suit,
                              String street,
                              String state,
                              String zip,
                              String country,
                              String city,
                              String school_address,
                              String sportId,
                              String social_head,
                              String userid,
                              String backgroundThemePath,
                              String schoolId,
                              String positionTitleId,
                              String teamId,
                              Context context,
                              UserProfileScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
         objInterface = activity;
 MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_UPDATE_PROFILE;
        
        


             MyData.put("first_name",
                            firstname);
             MyData.put("last_name", lastname);
             MyData.put("dob", dob);
             MyData.put("user_id", userid);
             MyData.put("gender", gender);
             MyData.put("language_id", languageId);
             MyData.put("suit", suit);
             MyData.put("street", street);
             MyData.put("city", city);
             MyData.put("state", state);
             MyData.put("zip", zip);
             MyData.put("country", country);
             MyData.put("school_address", school_address);
             MyData.put("social_head", social_head);
             MyData.put("sport_id", sportId);
             MyData.put("background_image ", backgroundThemePath);
             MyData.put("school_id", schoolId);
             MyData.put("position_title_id", positionTitleId);
             MyData.put("team_id", teamId);




            CheckInterNetConnection(context);

    }*/

    public void ChangePassword(String user_id, String random_password, String new_password, Context context, Change_password_Fragment activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_CHANGE_PASSWORD;


        MyData.put("user_id",
                user_id);
        MyData.put("random_password", random_password);
        // MyData.put("new_password", confirmpassword);

        MyData.put("new_password", new_password);


        Log.e(VolleyLog.TAG, " \n**************** ChangePassword :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void addTeam(String team_name, String coach_id, String team_id, String sports_id, String coach_class_timing, Context context,
                        AddTeamCoachAthleteScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_ADD_TEAM;


        MyData.put(context.getString(R.string.team_name_tag), team_name);

        MyData.put(context.getString(R.string.coach_id_tag), coach_id);

        MyData.put(context.getString(R.string.sport_id_tag), sports_id);
        MyData.put("coach_class_timing", coach_class_timing);
        MyData.put("team_id", team_id);


        Log.e(VolleyLog.TAG, " \n**************** addTeam :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    /****************************TRAINIING FRAGMENT *********************************/
    public void getFolder(String user_id, String parent_id, Context context,
                          FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Create_folder/get_folders";


        MyData.put("user_id", user_id);
        MyData.put("parent_id", parent_id);


        Log.e(VolleyLog.TAG, " \n**************** getFolder :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void setTrainingProgramForSale(String sport_id, String training_program_id, Context context, FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Training_program_purchase_detail/setTrainingProgramForSale";


        MyData.put("sport_id", sport_id);
        MyData.put("training_program_id", training_program_id);


        Log.e(VolleyLog.TAG, " \n**************** getFolder :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void deleteTrainingProgramFromSale(String id, Context context, purchase_membership activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Training_program_purchase_detail/deleteTrainingProgramFromSale";


        MyData.put("id", id);


        Log.e(VolleyLog.TAG, " \n**************** deleteTrainingProgramFromSale :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void GET_TOKEN(

            Context context, purchase_membership activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = API_GET_TOKEN;


        Log.e(VolleyLog.TAG, " \n**************** GET_TOKEN :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void sendPayments(String amount,
                             String payment_method_nonce,
                             String program_id,
                             String userid, Context context, purchase_membership activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = API_CHECKOUT;


        MyData.put("amount", amount);
        MyData.put("training_program_id", program_id);
        MyData.put("payment_method_nonce", payment_method_nonce);
        MyData.put("user_id", userid);


        Log.e(VolleyLog.TAG, " \n**************** GET_TOKEN :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void copyTrainingProgramToFolder(String training_program_id, String folder_id, Context context,
                                            FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Training_Program/copyTrainingProgramToFolder";


        MyData.put("training_program_id", training_program_id);
        MyData.put("folder_id", folder_id);

        Log.e(VolleyLog.TAG, " \n**************** copyTrainingProgramToFolder :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void moveFolder(String parent_folder_id, String folder_id, Context context, FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Create_folder/moveTPFolder";


        MyData.put("parent_folder_id", parent_folder_id);
        MyData.put("folder_id", folder_id);

        Log.e(VolleyLog.TAG, " \n**************** moveFolder :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void moveTrainingProgram(String training_program_id, String folder_id, Context context, FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Create_folder/moveTrainingProgram";


        MyData.put("training_program_id", training_program_id);
        MyData.put("folder_id", folder_id);

        Log.e(VolleyLog.TAG, " \n**************** moveTrainingProgram :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void CreateFolder(String folder_name, String user_id, String parent_id, Context context,
                             FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Create_folder/create_folder";


        MyData.put("user_id", user_id);
        MyData.put("parent_id", parent_id);
        MyData.put("folder_name", folder_name);


        Log.e(VolleyLog.TAG, " \n**************** CreateFolder :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void SetTrainingProgramName(String program_name, String user_id, String parent_id, Context context,
                                       FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/setTrainingProgramName";


        MyData.put("user_id", user_id);
        MyData.put("create_folder_parent_id", parent_id);
        MyData.put("program_name", program_name);


        Log.e(VolleyLog.TAG, " \n**************** SetTrainingProgramName :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void DeleteFolder(String folder_id, Context context,
                             FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Create_folder/deleteFolder";


        MyData.put("folder_id", folder_id);


        Log.e(VolleyLog.TAG, " \n**************** DeleteFolder :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);


    }


    public void DeleteTraining(String id, Context context,
                               FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Training_Program/delete";


        MyData.put("id", id);


        Log.e(VolleyLog.TAG, " \n**************** DeleteTraining :  \n  " + apiURL + " \n " + MyData + " \n ****************");

        CheckInterNetConnection(context, false);

    }

    public void RenameFolder(String folder_id, String folder_name, Context context,
                             FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Create_folder/renameFolder";


        MyData.put("folder_id", folder_id);
        MyData.put("folder_name", folder_name);


        Log.e(VolleyLog.TAG, " \n**************** RenameFolder :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);


    }


    public void RenameProgram(String program_id, String program_name, Context context, FragmentTrainingAndFolder activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Training_Program/update";


        MyData.put("program_id", program_id);
        MyData.put("program_name", program_name);


        Log.e(VolleyLog.TAG, " \n**************** RenameProgram :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);


    }

    /****************************TRAINIING FRAGMENT *********************************/


    public void addCoach(String coach_name, String team_id, String email_id, String coach_refered_by, Context context,
                         AddTeamCoachAthleteScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_ADD_COACH;


        // MyData.put(context.getString(R.string.coach_name_tag),coach_name);

        MyData.put("team_id", team_id);
        MyData.put("email_id", email_id);
        MyData.put("addedby_userid", coach_refered_by);

        Log.e(VolleyLog.TAG, " \n**************** addCoach :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void addAthlete(String athlete_name, String athleteLastName, String age, String gender, String email_id, String classgrade, String coach_id, String team_id, Context context,
                           AddTeamCoachAthleteScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_ADD_ATHLETE;


        MyData.put(context.getString(R.string.athlete_name_tag), athlete_name);
        MyData.put("last_name", athlete_name);

        MyData.put(context.getString(R.string.age_tag), age);

        MyData.put("email_id", email_id);
        MyData.put(context.getString(R.string.gender_tag), gender);
        MyData.put(context.getString(R.string.coach_id_tag), coach_id);
        MyData.put(context.getString(R.string.classgrade_tag), classgrade);
        MyData.put("team_id", team_id);

        Log.e(VolleyLog.TAG, " \n**************** addAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void deleteTeamCoach(String id, Context context, AddTeamCoachAthleteScreen activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/AssignTeamCoach/deleteTeamCoach";
        MyData.put("id", id);
        Log.e(VolleyLog.TAG, " \n**************** addAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }


    public void getSports(Context context,
                          LoginScreen activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_SPORTS;

        CheckInterNetConnection(context, false);
        //objApiCalling.execute(apiURL);


    }


//    public void UpdateProfile(
//            String userId,
//            String firstname,
//            String lastname,
//            String emailid,
//            String dob,
//            String gender,
//            String language_name,
//            String suit,
//            String street,
//            String zip,
//            String country,
//            String state,
//            String city,
//            String school_name,
//            String school_address,
//            String teamId,
//            String sport_name,
//            String social_media,
//            String Weight,
//            String Height,
//            String Bodyfat,
//            String Smm,
//            String backgroundThemePath,
//            String schoolId,
//            Context context,
//            UserProfileScreen activity) {
//        // isLoginApiCalled = true;
//        mContext = context;
//         objInterface = activity;
//    MyRequestQueue=Volley.newRequestQueue(context);
//            MyData=new HashMap<String, String>();
//        apiURL = BASE_URL + "Myprofile/" + METHOD_UPDATE_PROFILE;
//        httpParameters = new BasicHttpParams();
//        httpParameters.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//        HttpConnectionParams.setConnectionTimeout(httpParameters,
//                connectionRequestTimeout);
//        HttpConnectionParams.setSoTimeout(httpParameters,
//                connectionSocektTimeout);
//        httpClient = new DefaultHttpClient(httpParameters);
//        
//
//
//
//             MyData.put("user_id", userId);
//
//            nameValuePairs
//                    .add(new BasicNameValuePair("first_name",
//                            firstname);
//             MyData.put("last_name", lastname);
//             MyData.put("emailId",emailid);
//             MyData.put("dob", dob);
//             MyData.put("gender", gender);
//             MyData.put("language_id", language_name);
//            //   MyData.put("language_name",language_name);
//             MyData.put("suit", suit);
//             MyData.put("street", street);
//             MyData.put("zip", zip);
//             MyData.put("country", country);
//             MyData.put("state", state);
//             MyData.put("city", city);
//             MyData.put("school_id",school_name);
//             MyData.put("school_address", school_address);
//             MyData.put("team_id", "92");
//             MyData.put("sport_id", sport_name);
//             MyData.put("social_media", social_media);
//             MyData.put("weight", Weight);
//             MyData.put("height", Height);
//             MyData.put("body_fat", Bodyfat);
//             MyData.put("smm", Smm);
//             MyData.put("background_image ", backgroundThemePath);
//            //  MyData.put("position_title_id", positionTitleId);
//
//
//            
//
//            httpPost.setHeader("Content-Type",
//                    "application/x-www-form-urlencoded; charset=UTF-8");
//
//            
//
//            CheckInterNetConnection(context);
//            //objApiCalling.execute(apiURL);
//
//        } catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//    }

    public void UpdateProfile(
            String userId,
            String firstname,
            String lastname,
            String emailid,
            String dob,
            String gender,
            String language_id,
            String suit,
            String street,
            String zip,
            String country,
            String state,
            String city,
            String school_id,
            String school_address,
            String teamId,
            String sport_id,
            String social_head,
            String Weight,
            String Height,
            String Bodyfat,
            String Smm,
            String backgroundThemePath,
            String position_title_id,
            String neck,
            String bicep,
            String chest,
            String waist,
            String hips,
            String thigh,
            String timezone,
            Context context,
            UserProfileScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_UPDATE_PROFILE;

        MyData.put("user_id", userId);

        MyData.put("first_name",
                firstname);
        MyData.put("last_name", lastname);
        MyData.put("emailId", emailid);
        MyData.put("dob", dob);
        MyData.put("gender", gender);
        MyData.put("language_id", language_id);
        MyData.put("suit", suit);
        MyData.put("street", street);
        MyData.put("zip", zip);
        MyData.put("country", country);
        MyData.put("state", state);
        MyData.put("city", city);
        MyData.put("school_id", school_id);
        MyData.put("school_address", school_address);
        MyData.put("team_id", teamId);
        MyData.put("sport_id", sport_id);
        MyData.put("social_head", social_head);
        MyData.put("weight", Weight);
        MyData.put("height", Height);
        MyData.put("body_fat", Bodyfat);
        MyData.put("smm", Smm);
        MyData.put("background_image ", backgroundThemePath);
        MyData.put("position_title_id", position_title_id);
        MyData.put("neck", neck);
        MyData.put("bicep", bicep);
        MyData.put("chest", chest);
        MyData.put("waist", waist);
        MyData.put("hips", hips);
        MyData.put("thigh", thigh);
        MyData.put("time_zone", timezone);


        Log.e(VolleyLog.TAG, " \n**************** UpdateProfile :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void getPrograms(Context context,
                            ProgramPurchaseFragment activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_PROGRAMS;
        Log.e(VolleyLog.TAG, " \n**************** getPrograms :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void setProgramsTraining(Context context,
                                    ProgramPurchaseFragment activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_PROGRAMS;
        Log.e(VolleyLog.TAG, " \n**************** setProgramsTraining :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void getLanguage(Context context,
                            SchoolDataService activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_LANGUAGE;


        Log.e(VolleyLog.TAG, " \n**************** getLanguage :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();


    }


    public void getTeams(String coachId, Context context,
                         BackgroundLocationUpdateService activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_TEAM;


        MyData.put(context.getString(R.string.coach_id_tag),
                coachId);


        Log.e(VolleyLog.TAG, " \n**************** BackgroundLocationUpdateService :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();
    }


    public void getTeamsBasedOnSchool(String school_id, Context context,
                                      LoginScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_TEAM;


        MyData.put("school_id", school_id);


        Log.e(VolleyLog.TAG, " \n**************** BackgroundLocationUpdateService :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }


    public void getTeams(String coachId, String sport_id, String coach_class_timing_id, String school_id, Context context,
                         CoachBoardFragments activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_TEAM;

        MyData.put(context.getString(R.string.coach_id_tag),
                coachId);
        MyData.put("sport_id", sport_id);
        MyData.put("school_id", school_id);
        MyData.put("coach_class_timing_id", coach_class_timing_id);


        Log.e(VolleyLog.TAG, " \n**************** getTeams :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void send_PushNotification(String team_id,
                                      String athlete_id,
                                      String message,
                                      String sender_user_id,
                                      Context context,
                                      CoachBoardFragments activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/All_PushNotification_detail/send_PushNotification";

        if (team_id.equalsIgnoreCase("")) {
            MyData.put("athlete_id", athlete_id);
        } else {
            MyData.put("team_id", team_id);
        }


        MyData.put("message", message.trim());
        MyData.put("sender_user_id", sender_user_id);


        Log.e(VolleyLog.TAG, " \n**************** send_PushNotification :  \n  " + apiURL + " \n " + MyData + " \n ****************");

        CheckInterNetConnection(context, false);


    }

    public void send_PushNotification(String team_id,
                                      String athlete_id,
                                      String message,
                                      String sender_user_id,
                                      Context context,
                                      chat_activity activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/All_PushNotification_detail/send_PushNotification";

        if (team_id.equalsIgnoreCase("")) {
            MyData.put("athlete_id", athlete_id);
        } else {
            MyData.put("team_id", team_id);
        }


        MyData.put("message", message.trim());
        MyData.put("sender_user_id", sender_user_id);


        Log.e(VolleyLog.TAG, " \n**************** send_PushNotification :  \n  " + apiURL + " \n " + MyData + " \n ****************");

        CheckInterNetConnection(context, false);


    }


    public void getSchedules(String user_id, String from_date, String to_date, Context context,
                             scheduleCalender activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Athlete_class/getSchedules";


        MyData.put("user_id", user_id);
        MyData.put("from_date", from_date);
        MyData.put("to_date", to_date);


        Log.e(VolleyLog.TAG, " \n**************** getSchedules :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }

    public void getVideoClass(String user_id,
                              String category_id,
                              String from_date,

                              String to_date, Context context,
                              scheduleCalender activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Live_class_exercise/getVideoClass";


        MyData.put("user_id", user_id);
        MyData.put("category_id", category_id);
        MyData.put("from_date", from_date);
        MyData.put("to_date", to_date);


        Log.e(VolleyLog.TAG, " \n**************** getVideoClass :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }


    public void getTeams(String coachId, String sport_id, String school_id, Context context,
                         WhiteBoard activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_TEAM;


        MyData.put(context.getString(R.string.coach_id_tag),
                coachId);
        MyData.put("sport_id", sport_id);
        MyData.put("school_id", school_id);


        Log.e(VolleyLog.TAG, " \n**************** getTeams :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void getTeams(String coachId, String school_id, Context context,
                         SelectTeamAndAthleteScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_TEAM;

        MyData.put(context.getString(R.string.coach_id_tag),
                coachId);
        MyData.put("school_id",
                school_id);


        Log.e(VolleyLog.TAG, " \n**************** getTeams :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void Add_location(String latitude, String longitude, String date, String user_id, Context context,
                             BackgroundLocationUpdateService activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Add_location/insert";


        MyData.put("latitude", latitude);
        MyData.put("longitude", longitude);
        MyData.put("last_updated", date);
        MyData.put("user_id", user_id);
        Log.e(VolleyLog.TAG, " \n**************** Add_location :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();


        // }
    }


    public void getAthlete(String coachId, String teamId,
                           String sport_id,
                           String school_id,
                           Context context,
                           Competition_Board activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_ATHLETE_BY_TEAM;


        MyData.put(context.getString(R.string.coach_id_tag), coachId);
        MyData.put(context.getString(R.string.team_id_tag), teamId);
        MyData.put("sport_id", sport_id);
        MyData.put("school_id", school_id);

        Log.e(VolleyLog.TAG, " \n**************** getAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void Perform_analytic(String date_type,
                                 String team_id,
                                 String athlete_id,
                                 String from_date,
                                 String to_date,
                                 String exercise_type,
                                 String exercise_id,
                                 String data_avg_max,

                                 Context context,
                                 FragmentComparativeAnalytics activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        if (!exercise_type.equalsIgnoreCase("")) {
            exercise_id = "";
        } else if (!exercise_id.equalsIgnoreCase("")) {
            exercise_type = "";
        }
        apiURL = BASE_URL + "Webservices/Perform_analytic/" + (exercise_type.equalsIgnoreCase("") && exercise_id.equalsIgnoreCase("") ? "get_perform_analytics" : "get_perform_analytics_exercise");

        MyData = new HashMap<String, String>();
        if (date_type.equalsIgnoreCase("5")) {
            MyData.put("from_date", from_date);
            MyData.put("to_date", to_date);
        }

        if (!exercise_type.equalsIgnoreCase("")) {
            MyData.put("exercise_type", exercise_type);
            MyData.put("data_avg_max", data_avg_max);
        } else if (!exercise_id.equalsIgnoreCase("")) {
            MyData.put("data_avg_max", data_avg_max);
            MyData.put("exercise_id", exercise_id);
        }

        MyData.put("date_type", date_type);
        if (!athlete_id.equalsIgnoreCase("")) {
            MyData.put("athlete_id", athlete_id);
        } else {
            MyData.put("team_id", team_id);
        }

        Log.e(VolleyLog.TAG, " \n**************** Perform_analytic :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void getTeams(String coachId, String school_id, Context context,
                         FragmentComparativeAnalytics activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_TEAM;


        MyData = new HashMap<String, String>();

        MyData.put(context.getString(R.string.coach_id_tag), coachId);
        MyData.put("school_id", school_id);


        Log.e(VolleyLog.TAG, " \n**************** getTeams :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void getAthlete(String coachId, String teamId,
                           String sport_id,
                           String school_id,
                           Context context,
                           FragmentComparativeAnalytics activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_ATHLETE_BY_TEAM;


        MyData.put(context.getString(R.string.coach_id_tag), coachId);
        MyData.put(context.getString(R.string.team_id_tag), teamId);
        MyData.put("sport_id", sport_id);
        MyData.put("school_id", school_id);


        Log.e(VolleyLog.TAG, " \n**************** getAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void getAthlete(String coachId, String teamId,
                           String sport_id,
                           String school_id,
                           Context context, CoachBoardFragments activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_ATHLETE_BY_TEAM;

        MyData.put(context.getString(R.string.coach_id_tag), coachId);
        MyData.put(context.getString(R.string.team_id_tag), teamId);
        MyData.put("sport_id", sport_id);
        MyData.put("school_id", school_id);

        Log.e(VolleyLog.TAG, " \n**************** getAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void getAthlete(String coachId,
                           String teamId,
                           Context context,
                           SelectAthlete activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_ATHLETE_BY_TEAM;


        MyData.put(context.getString(R.string.coach_id_tag),
                coachId);

        MyData.put(context.getString(R.string.team_id_tag),
                teamId);


        Log.e(VolleyLog.TAG, " \n**************** getAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void setAssignProgram(String training_program_id, String teamId, String dose_id, String start_date, String sender_user_id, Context context,
                                 SelectTeamAndAthleteScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Assignprogram/setAssignProgram";


        MyData.put("training_program_id", training_program_id);

        MyData.put("team_id", teamId);

        MyData.put("dose_id", dose_id);

        MyData.put("start_date", start_date);

        MyData.put("sender_user_id", sender_user_id);


        Log.e(VolleyLog.TAG, " \n**************** setAssignProgram :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void setAssignProgramAthlete(String athlete_id,
                                        String training_program_id,
                                        String start_date,
                                        String sender_user_id,
                                        Context context,
                                        SelectAthlete activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Assignprogram/setAssignProgramToAthlete";

        MyData.put("training_program_id", training_program_id);

//            nameValuePairs
//                    .add(new BasicNameValuePair("team_id", team_id);

        MyData.put("athlete_id", athlete_id);


        MyData.put("start_date", start_date);
        MyData.put("sender_user_id", sender_user_id);


        Log.e(VolleyLog.TAG, " \n**************** setAssignProgramAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void updateAssignProgramStartDateAthlete(String assign_program_id,
                                                    String start_date,
                                                    Context context,
                                                    SelectAthlete activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Assignprogram/updateAssignProgramStartDate";


        MyData.put("assign_program_id", assign_program_id);


        MyData.put("start_date", start_date);


        Log.e(VolleyLog.TAG, " \n**************** updateAssignProgramStartDateAthlete :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);


    }

    public void updateAssignProgramStartDate(String assign_program_id,
                                             String start_date,
                                             Context context,
                                             SelectTeamAndAthleteScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Assignprogram/updateAssignProgramStartDate";

        MyData.put("assign_program_id", assign_program_id);


        MyData.put("start_date", start_date);


        Log.e(VolleyLog.TAG, " \n**************** updateAssignProgramStartDate :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);


    }

    public void deleteAssignProgram(String assign_program_id,
                                    Context context,
                                    SelectAthlete activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Assignprogram/deleteAssignProgram";


        MyData.put("assign_program_id", assign_program_id);


        Log.e(VolleyLog.TAG, " \n**************** deleteAssignProgram :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void deleteAssignProgram(String assign_program_id,
                                    Context context,
                                    SelectTeamAndAthleteScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Assignprogram/deleteAssignProgram";


        MyData.put("assign_program_id", assign_program_id);


        Log.e(VolleyLog.TAG, " \n**************** deleteAssignProgram :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void exerciseTypeSequenceChange(String training_exercise_id, String position,
                                           String training_program_detail_id, String exercise_type_group_id, String type_id, String exerciseName, Context context, CoachAddExerciseScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Trainingexercise/exerciseTypeSequenceChange";


        MyData.put("training_exercise_id", training_exercise_id);
        MyData.put("position", position);
        MyData.put("training_program_detail_id", training_program_detail_id);
        MyData.put("exercise_type_group_id", exercise_type_group_id);
        MyData.put("type_id", type_id);
        //MyData.put("exerciseName", exerciseName);


        Log.e(VolleyLog.TAG, " \n**************** exerciseTypeSequenceChange :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);

    }


    public void getPosition(Context context,
                            LoginScreen activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_POSITION;


        Log.e(VolleyLog.TAG, " \n**************** getPosition :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();


    }

    public void getQuestionaries(Context context,
                                 PrivacyAndQuestionnariesScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_QUESTIONS_LIST;


//            AsyncApiCalling objApiCalling = new AsyncApiCalling();
//            objApiCalling.execute(apiURL);
        Log.e(VolleyLog.TAG, " \n**************** getQuestionaries :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }

    public void submitAnswer(String question_Id, String answer_id, String userId, Context context,
                             PrivacyAndQuestionnariesScreen activity) {
        // isLoginApiCalled = true;
        Log.e("Login API Called", "**************");
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_SUBMIT_ANSWERS;


        MyData.put("ans_respons",
                answer_id);

        MyData.put("q_id", question_Id);
        MyData.put("user_id", userId);


//            AsyncApiCalling objApiCalling = new AsyncApiCalling();
//            objApiCalling.execute(apiURL);
        Log.e(VolleyLog.TAG, " \n**************** submitAnswer :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);


    }


    public void getPrivacyPolicy(Context context,
                                 PrivacyAndQuestionnariesScreen activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_PRIVACY_POLLICY;


//            AsyncApiCalling objApiCalling = new AsyncApiCalling();
//            objApiCalling.execute(apiURL);
        Log.e(VolleyLog.TAG, " \n**************** getPrivacyPolicy :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }

    public void get_PushNotification_detail(String athlete_id, Context context, messageFragment activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/All_PushNotification_detail/get_PushNotification_detail";


        MyData.put("athlete_id", athlete_id);
        Log.e(VolleyLog.TAG, " \n**************** getPrivacyPolicy :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }

    public void getPurchaseHistory(String user_id, Context context, purchase_history activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Braintreepayment_gateway/getPaymentHistory";


        MyData.put("user_id", user_id);
        Log.e(VolleyLog.TAG, " \n**************** getPrivacyPolicy :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }

    public void setAtheleteAttandance(Context context, String athelte_id, String coach_id, String athlete_attendance, CoachBoardFragments activity) {
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_SET_ATHLETE_ATTENDANCE;


        MyData.put(context.getString(R.string.coach_id_tag),
                coach_id);

        MyData.put("athlete_id",
                athelte_id);
        MyData.put(context.getString(R.string.athlete_attendance_tag),
                athlete_attendance);

        Log.e(VolleyLog.TAG, " \n**************** setAtheleteAttandance :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);
    }


    public void updateMultiplierData(Context context, AthleteProfileFragment activity, String athlete_id, String coach_id, String row_no, String value, String athlete_level_id) {


        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Exercise_multiplier/update";


        MyData.put("athlete_id", athlete_id);
        MyData.put("coach_id", coach_id);
        MyData.put("row_no", row_no);
        MyData.put("value", value);
        MyData.put("athlete_level_id", athlete_level_id);


        Log.e(VolleyLog.TAG, " \n**************** updateMultiplierData :  \n  " + apiURL + " \n " + MyData + " \n ****************");


        CheckInterNetConnection(context, false);
    }


    public void updateMultiplierData(Context context, CoachBoardFragments activity, String athlete_id, String coach_id, String row_no, String value, String athlete_level_id) {


        Log.e(TAG, "updateMultiplierData :  \n  " + athlete_id + " " + coach_id + " " + row_no + " " + value);
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Exercise_multiplier/update";


        MyData.put("athlete_id", athlete_id);
        MyData.put("coach_id", coach_id);
        MyData.put("row_no", row_no);
        MyData.put("value", value);
        MyData.put("athlete_level_id", athlete_level_id);


        Log.e(VolleyLog.TAG, " \n**************** updateMultiplierData :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);

    }


    public void setGoal(String athleteID, String coach_id, String select_Goal, Context context, AthleteProfileFragment activity) {


        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Goal/addAthleteSelectGoal";


        MyData.put("athlete_id", athleteID);
        MyData.put("coach_id", coach_id);
        MyData.put("select_goal", select_Goal);


        Log.e(VolleyLog.TAG, " \n**************** setGoal :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void deleteTeams(String team_id, String coach_id, Context context, CoachBoardFragments activity) {


        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/AssignTeamCoach/deleteTeams";


        MyData.put("team_id", team_id);
        MyData.put("coach_id", coach_id);


        Log.e(VolleyLog.TAG, " \n**************** setGoal :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }


    public void updateAthleteLevel(String athleteID, String coach_id, String selectlevel_id, Context context, AthleteProfileFragment activity) {


        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Exercise_multiplier/athleteSelectLevelAdd";


        MyData.put("athlete_id", athleteID);
        MyData.put("coach_id", coach_id);
        MyData.put("select_level", selectlevel_id);


        Log.e(VolleyLog.TAG, " \n**************** updateAthleteLevel :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void updateAthleteLevel(String athleteID, String coach_id, String selectlevel_id, Context context, CoachBoardFragments activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Exercise_multiplier/athleteSelectLevelAdd";


        MyData.put("athlete_id", athleteID);
        MyData.put("coach_id", coach_id);
        MyData.put("select_level", selectlevel_id);
        Log.e(VolleyLog.TAG, " \n**************** updateAthleteLevel :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);

    }

    public void getGoalArray(Context context, SchoolDataService activity) {
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Goal/get";


        Log.e(VolleyLog.TAG, " \n**************** getGoalArray :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();
    }

    public void getGoalArray(Context context, AthleteProfileFragment activity) {
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Goal/get";


        Log.e(VolleyLog.TAG, " \n**************** getGoalArray :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();
    }


    //////////////
//    public void updateMultiplierData(Context context, ProfileScreenActivity activity, String athlete_id, String coach_id, String row_no,
//                                     String value,String athlete_level_id) {
//
//
//        Log.e(TAG, "updateMultiplierData :  \n  " + athlete_id + " " + coach_id + " " + row_no + " " + value);
//        mContext = context;
//         objInterface = activity;
//        MyRequestQueue=Volley.newRequestQueue(context);
//        MyData=new HashMap<String, String>();
//        apiURL = BASE_URL + "Webservices/Exercise_multiplier/update";
//        
//
//        httpParameters = new BasicHttpParams();
//        httpParameters.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//        HttpConnectionParams.setConnectionTimeout(httpParameters,
//                connectionRequestTimeout);
//        HttpConnectionParams.setSoTimeout(httpParameters,
//                connectionSocektTimeout);
//        httpClient = new DefaultHttpClient(httpParameters);
//        
//
//
//
//             MyData.put("athlete_id", athlete_id);
//             MyData.put("coach_id", coach_id);
//             MyData.put("row_no", row_no);
//             MyData.put("value", value);
//             MyData.put("athlete_level_id", athlete_level_id);
//
//
//             Log.e(VolleyLog.TAG, " \n**************** updateMultiplierData :  \n  "+nameValuePairs);
//            
//
//            httpPost.setHeader("Content-Type",
//                    "application/x-www-form-urlencoded; charset=UTF-8");
//
//            
//
//            CheckInterNetConnection(context, false);
//            //objApiCalling.execute(apiURL);
//
//        } catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }

    public void getExerciseName(Context context, exercise_Activity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/getExerciseType";


        Log.e(VolleyLog.TAG, " \n**************** getExerciseName :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void GetExerciseNewSecond(Context context, exercise_Activity activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/getExercisenewsecond";


        Log.e(VolleyLog.TAG, " \n**************** GetExerciseNewSecond :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);

    }

    public void GetExerciseNewSecond(Context context, FragmentComparativeAnalytics activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/getExercisenewsecond";


        MyData = new HashMap<String, String>();


        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        CheckInterNetConnection(context, true);
        MyStringRequest = new StringRequest(Request.Method.POST, apiURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hide();
                objInterface.ApiResponse(response);

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                objInterface.ApiResponse(response);
            }
        }) {
            protected Map<String, String> getParams() {
                return MyData;
            }
        };
        MyStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                300000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        MyRequestQueue.add(MyStringRequest);

    }


    public void setGoal(String athleteID, String coach_id, String select_Goal, Context context, ProfileScreenActivity activity) {


        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Goal/addAthleteSelectGoal";


        MyData.put("athlete_id", athleteID);
        MyData.put("coach_id", coach_id);
        MyData.put("select_goal", select_Goal);


        Log.e(VolleyLog.TAG, " \n**************** setGoal :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);

    }


    public void setLiveClassCheckIn(String video_auto_id, String user_id, Context context, scheduleCalender activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/LiveClassCheckIn/setLiveClassCheckIn";


        MyData.put("video_auto_id", video_auto_id);
        MyData.put("user_id", user_id);


        Log.e(VolleyLog.TAG, " \n**************** setLiveClassCheckIn :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }

    public void deleteLiveClassCheckIn(
            String checkin_id,
            String user_id,
            Context context, scheduleCalender activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/LiveClassCheckIn/deleteLiveClassCheckIn";


        MyData.put("checkin_id", checkin_id);
        MyData.put("user_id", user_id);


        Log.e(VolleyLog.TAG, " \n**************** deleteLiveClassCheckIn :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, false);
    }

    public void addAthleteInTeam(String teamId, String athlete_id, Context context, CoachBoardFragments activity) {

        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/AddAthleteInTeam/addAthleteInTeam";


        MyData.put("team_id", teamId);
        MyData.put("athlete_id", athlete_id);


        Log.e(VolleyLog.TAG, " \n**************** addAthleteInTeam :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }

    public void getVideoClassCategory(Context context, scheduleCalender activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Exercise_category/getVideoClassCategory";


        Log.e(VolleyLog.TAG, " \n**************** getVideoClassCategory :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }

    public void getVideoClassCategory(Context context, Subscription activity) {
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Webservices/Exercise_category/getVideoClassCategory";


        Log.e(VolleyLog.TAG, " \n**************** getVideoClassCategory :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        CheckInterNetConnection(context, true);
    }


    public void uploadUserImage(final String userId, final byte[] imageByte, Context context, AthleteProfileFragment activity) {
        Calendar c = Calendar.getInstance();
        final String imageName = "AvatarImage" + c.getTimeInMillis() + "";
        mContext = context;
        objInterface = activity;
        deviceId = UtilityClass.getDeviceId(mContext);
        apiURL = BASE_URL + "Myprofile/" + METHOD_SET_IMAGE;

        final Hashtable<String, String> hash = new Hashtable<String, String>();

        try {
            hash.put(mContext.getString(R.string.user_id_tag),
                    userId);

            hash.put(mContext.getString(R.string.user_image_tag),
                    imageName);


            new Thread(() -> {

                response = "";
                SendFileHttp _sendFile;
                String result = "";
                try {
                    _sendFile = new SendFileHttp(apiURL, hash, mContext
                            .getString(R.string.user_image_tag), imageName
                            + ".jpeg", "image/jpeg", imageByte, mContext,
                            userId);
                    byte[] res = _sendFile.send();
                    response = new String(res);
                    handler.sendEmptyMessage(0);
                    System.out.println(result);

                } catch (Exception e) {
                    // myHandler.sendEmptyMessage(1);
                    e.printStackTrace();

                }
            }).start();


        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**************************** BackGROUND SERIVICE *******************************************/

    public void getSchools(Context context,
                           SchoolDataService activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_SCHOOL;

        Log.e(VolleyLog.TAG, " \n**************** getSchools :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();


    }

    public void getSports(Context context,
                          SchoolDataService activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_SPORTS;


        Log.e(VolleyLog.TAG, " \n**************** getSports :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();

    }

    public void getTeams(String school_id, Context context,
                         SchoolDataService activity) {
        // isLoginApiCalled = true;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_TEAM;


        MyData.put("school_id", school_id);


        Log.e(VolleyLog.TAG, " \n**************** getTeams :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();
    }


    public void getPosition(Context context,
                            SchoolDataService activity) {
        // isLoginApiCalled = true;
        isDilogShowing = false;
        mContext = context;
        objInterface = activity;
        MyRequestQueue = Volley.newRequestQueue(context);
        MyData = new HashMap<String, String>();
        apiURL = BASE_URL + "Myprofile/" + METHOD_GET_POSITION;


        Log.e(VolleyLog.TAG, " \n**************** getPosition :  \n  " + apiURL + " \n " + MyData + " \n ****************");
        GiveBackResponseToActivity();


    }

    public void Retry(Boolean ShowDialogOn) {
        hide();
        Dialog dialog = new Dialog(mContext);
        try {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        } catch (Exception v) {

        }
        dialog.setContentView(R.layout.custom_dialog_box_for_training_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setDimAmount(0);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        LinearLayout BeginTraining, Cancel, workoutSummary, SingleRow, CancelXc, YesX;
        TextView EventName, EventNameX, begin, workout, cancel, Yes, CancelX;

        BeginTraining = dialog.findViewById(R.id.BeginTraining);
        Cancel = dialog.findViewById(R.id.Cancel);
        Yes = dialog.findViewById(R.id.Yes);
        workoutSummary = dialog.findViewById(R.id.workoutSummary);
        begin = dialog.findViewById(R.id.begin);
        workout = dialog.findViewById(R.id.workout);
        cancel = dialog.findViewById(R.id.cancel);
        SingleRow = dialog.findViewById(R.id.SingleRow);

        YesX = dialog.findViewById(R.id.YesX);
        CancelXc = dialog.findViewById(R.id.CancelXc);


        Yes.setText("Retry");

        YesX.setOnClickListener(view -> {
            if (UtilityClass.isDeviceInternetAvailable(mContext)) {
                if (ShowDialogOn) {
                    UtilityClass.showWaitDialog(new Dialog(mContext), mContext);
                }
                retry = 0;
                Log.e(VolleyLog.TAG, " \n**************** Retry :  \n  " + apiURL + "  " + MyData + " \n ****************");
                GiveBackResponseToActivity();


                dialog.dismiss();
                //objApiCalling.showDialog()

            } else {

            }
        });

        CancelXc.setOnClickListener(view -> dialog.dismiss());

        SingleRow.setVisibility(View.VISIBLE);


        EventName = dialog.findViewById(R.id.EventName);

        EventNameX = dialog.findViewById(R.id.EventNameX);

        EventNameX.setText("Do you want to retry?");


//        showDialogofTraining(context,0,0,GetTeam.get(position).getTeamName(),"",0);
        EventName.setTypeface(CustomTypeface.load_Montserrat_Bold_Fonts(mContext));
        EventNameX.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(mContext));
        begin.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(mContext));
        workout.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(mContext));
        cancel.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(mContext));

        BeginTraining.setVisibility(View.GONE);
        workoutSummary.setVisibility(View.GONE);
        Cancel.setVisibility(View.GONE);


        BeginTraining.setOnClickListener(view -> dialog.dismiss());

        workoutSummary.setOnClickListener(view -> dialog.dismiss());

        Cancel.setOnClickListener(view -> dialog.dismiss());
        try {
            dialog.show();
        } catch (Exception x) {

        }


    }


    private void GiveBackResponseToActivity() {
        MyStringRequest = new StringRequest(Request.Method.POST, apiURL, response ->
                objInterface.ApiResponse(response), new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(VolleyLog.TAG, " \n**************** onErrorResponse :  \n  " + error);
                if (error.toString().contains("TimeoutError") && retry <= 1) {
                    retry = ++retry;
                    if (retry == 1) {
                        hide();
                        Retry(true);
                    } else {
                        GiveBackResponseToActivity();
                    }

                    return;
                } else {
                    //hide();objInterface.ApiResponse(error.toString());
                }
                //objInterface.ApiResponse(error.toString());

            }
        }) {
            protected Map<String, String> getParams() {
                return MyData;
            }
        };
        MyStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000 * 3,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyRequestQueue.add(MyStringRequest);
    }


}
