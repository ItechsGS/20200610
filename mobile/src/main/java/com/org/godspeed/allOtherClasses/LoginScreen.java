package com.org.godspeed.allOtherClasses;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.RecyclerViewClickCheck;
import com.org.godspeed.baseActivity.BaseActivity;
import com.org.godspeed.loginData.Login;
import com.org.godspeed.loginData.Team;
import com.org.godspeed.response_JsonS.GetSport.GetSport;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.GetTeamsDetailsClas;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.Reset_Password;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;
import com.org.godspeed.utility.custom_popup_class;
import com.samsung.android.sdk.healthdata.HealthDataStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.screenname;
import static com.org.godspeed.allOtherClasses.SplashScreen.FCM_TOKEN;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_ID_FOR_PRE;
import static com.org.godspeed.allOtherClasses.SplashScreen.SCHOOL_NAME_FOR_PRE;
import static com.org.godspeed.service.BackgroundLocationUpdateService.CallApiForTeamByCoach;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.service.SchoolDataService.getPositionList;
import static com.org.godspeed.service.SchoolDataService.getSchoolsList;
import static com.org.godspeed.utility.GlobalClass.OnlyAthlete;
import static com.org.godspeed.utility.GlobalClass.logoutfromAthleteCoach;
import static com.org.godspeed.utility.GlobalClass.team_id;
import static com.org.godspeed.utility.UtilityClass.GetDeviceType;
import static com.org.godspeed.utility.UtilityClass.hide;

public class LoginScreen extends BaseActivity implements MediaPlayer.OnCompletionListener, GodSpeedInterface, RecyclerViewClickCheck {

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    public static String userId = "";
    //public static List<SelectedAthleteLevel> SelectedAthleteLevel;
    //public static List<AthleteLevel> athlete_level;
    //public static List<SelectedGoal> selected_goal;
    public static Boolean CoachCheckAthlete = false;
    public static int volume_level = 0;
    public static int MEMBERSHIP_STATUS = 0;
    public static boolean isLogoutCalled = false;
    public static String userTypeOf = "";
    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "." + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
    public Context context;
    LinearLayout savepassword;
    Gson gson = new Gson();
    MediaPlayer mp;
    int pressCount = 0;
    AudioManager mAudioManager;
    int maxVolume = 0;
    int volume = 0;
    Dialog dialog;
    String DEVICE_TYPE = getDeviceName() + "," + Build.VERSION.RELEASE;
    String IP_ADDRESS = "";
    WebServices webServices = new WebServices();
    List<Team> teamsLocal = new ArrayList<>();
    String SelectionTypeOfList = "";
    private TextView textViewLogin, textViewSignup, textViewBetter, textViewAthlete, textviewLoginWhite, textViewLoginButtonText, textViewSignupButtonText, textviewForgotPasswordWhite;
    private Typeface fontFaceBoldAgency, fontFaceRegularAgency;
    //    private ViewPager viewPager;
//    private PagerAdapter adapter;
//    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private int[] ids = {R.raw.video_1};//, R.raw.video_2, R.raw.video_3};
    private int[] videoTypes = {R.string.athletes, R.string.team, R.string.champions};
    private RelativeLayout rLayoutForLoginPopup, rLayoutLoginButton, rLayoutLoginSignupButton, rLayoutLoginYellowButton, rLayoutSignupYellowButton, rLayoutViewPager, rLayoutSubmitYellowButton;
    private boolean isLoginScreenOpened = false;
    private EditText editTextUserName, editTextPassword, editTextUserEmailForForgotPassword;
    private RelativeLayout rLayoutForSignupOption, rLayoutMultipleSignupButton, rLayoutSignupAsAthlete, rLayoutSignupAsCoach, rLAyoutForTeam, rLayoutForSignupFields, rLAyoutForBackgroundPic;
    private TextView textViewSignupHeading, textViewSignupAsCoach, textViewSignupAsAthlete, textviewForgotPassword;
    private ImageView imageViewCloseSignupLayout, imageViewCircle, imageViewCircleOne, imageViewCircleTwo, imageViewCloseForgotPasswordLayout;
    private ImageView rLayoutDivider5, rLayoutDivider6;
    private ScrollView scrollViewSignupCoachAthlete;
    private VideoView videoViewPlayer;
    private int currentVideoNumber = 0;
    private RelativeLayout rLayoutForForgotPassword;
    private int countHandlerValueForTextChange = 0;
    private boolean running = false;
    private int GALLERY = 1, CAMERA = 2;
    private ImageView imageViewBackground, checkbox;
    //private ScrollView scrollViewSignupCoachAthlete;
    private EditText editTextEmail, editTextSignupPassword, editTextConfirmPassword, editTextSchool, editTextTeam, editTextSelectPicture;
    private RelativeLayout rLayoutForTextViewHolder;
    private String whichApiCalled = "";
    private int userType = 0;
    private String password_type = "";
    private ProgressDialog prgDailog = null;
    private Animation zoomIn, zoomOut;
    private ImageView imageViewForZoomInOut;
    private boolean isAnimationStarted = false;
    private boolean savepasstoggle = false;
    private String backgroundImageName = "login_transparant_layer.png";
    //private StepCountReader mReporter;
    private boolean autoLoginToggle = false;
    private HealthDataStore mStore;
    //    String[] teamIdArray;
//    String[] teamNameArray;
    private String[] positionTitleIdArray, positionTitleArray;

    @Nullable
    public static String getNetworkInterfaceIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        String host = inetAddress.getHostAddress();
                        if (!TextUtils.isEmpty(host)) {
                            return host;
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
        return "";
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }

    private void resetSignIn() {
        editTextUserName.setText("");
        editTextPassword.setText("");
    }


    @Override
    public void OnItemClick(int position) {
        if (SelectionTypeOfList.equalsIgnoreCase("school")) {
            editTextSchool.setText(getSchoolsList.get(position).getSchoolName());
            editTextSchool.setTag(getSchoolsList.get(position).getSchoolId());
            if (rLAyoutForBackgroundPic.getVisibility() != View.VISIBLE) {
                whichApiCalled = "getTeamsBasedOnSchool";
                webServices.getTeamsBasedOnSchool(getSchoolsList.get(position).getSchoolId(), context, LoginScreen.this);
            }
        } else if (SelectionTypeOfList.equalsIgnoreCase("team")) {
            editTextTeam.setText(teamsLocal.get(position).getTeamName());
            editTextTeam.setTag(teamsLocal.get(position).getTeamId());
        } else if (SelectionTypeOfList.equalsIgnoreCase("position")) {
            editTextTeam.setText(getPositionList.get(position).getPositionTitleName());
            editTextTeam.setTag(getPositionList.get(position).getPositionTitleId());
        }
    }

    @Override
    public void OnItemClickListReturn(List<GetTeamsDetailsClas> teamList, List<GetSport> sports) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);

//        if (SplashScreen.GS_DEBUG_BUILD == false) {
//            SplashScreen.PlayVideo = true;
//        }

        GlobalClass.ivar1 = 1;
        IP_ADDRESS = getDeviceIpAddress();
        logoutfromAthleteCoach = false;
        context = this;
        dialog = new Dialog(context);

        /***************** *******************/
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volume_level = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        ////////////////////////////////////

        currentVideoNumber = 0;


//         shealth = new Samhealth(LoginScreen.this,context,mStore);
//        shealth.Check();
        //mReporter = new StepCountReader(mStore, mStepCountObserver);


        fontFaceRegularAgency = CustomTypeface.load_AGENCYR_Fonts(context);
        fontFaceBoldAgency = CustomTypeface.load_AGENCYB_Fonts(context);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        checkbox = findViewById(R.id.sqaureimg);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextPassword.setSelection(editTextPassword.getText().length());

        editTextUserEmailForForgotPassword = findViewById(R.id.editTextUserEmail);
        editTextUserEmailForForgotPassword.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        savepassword = findViewById(R.id.layoutviewSavePassword);
        if (SplashScreen.PlayVideo) {
            initializeVideoView();
            initializeVideoPager();
        }

        initializeTextView();
        isLoginScreenOpened = false;


        Bitmap bitmap = BitmapFactory.decodeFile(context.getFilesDir() + backgroundImageName);
        imageViewBackground = findViewById(R.id.imageViewBackground);
        //Glide.with(context).load(getResources().getDrawable(R.drawable.login_transparant_layer)).into(imageViewBackground);
        initializeEditTextView();

        scrollViewSignupCoachAthlete = findViewById(R.id.scrollViewSignupCoachAthlete);
        scrollViewSignupCoachAthlete.pageScroll(View.FOCUS_UP);

        rLayoutForSignupFields = findViewById(R.id.rLayoutForSignupFields);
        rLayoutForSignupFields.setVisibility(View.GONE);

        GetDeviceType(context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }


        initializeLayoutView();

        savepasstoggle = true;
        checkbox.setImageResource(R.drawable.checkbox_right);

        savepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!savepasstoggle) {
                    savepasstoggle = true;
                    checkbox.setImageResource(R.drawable.checkbox_right);
                } else {
                    savepasstoggle = false;
                    checkbox.setImageResource(R.drawable.checksquare);
                }
            }
        });


        /************** *****************/

        SharedPreferences prefs = getSharedPreferences("ID_Crediental", MODE_PRIVATE);
        String restoredemail = prefs.getString("email", null);
        String restoredpassword = prefs.getString("password", null);
        if (restoredemail != null && restoredpassword != null) {
            autoLoginToggle = true;
            editTextUserName.setText(restoredemail);
            editTextPassword.setText(restoredpassword);
            editTextEmail.setText(restoredemail);
            editTextEmail.setText(restoredpassword);
            whichApiCalled = "signin";
            WebServices webServices = new WebServices();
            hide();
            webServices.login(restoredemail, restoredpassword, IP_ADDRESS, DEVICE_TYPE, FCM_TOKEN, context, LoginScreen.this);
            initializeLayoutView();
        } else {

        }

        /************** *****************/

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        if (pressCount == 0) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                pressCount = 1;
                mAudioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        volume_level,
                        0);
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                pressCount = 1;
                mAudioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        volume_level,
                        0);
                return true;
            }
        }
        Log.e(VolleyLog.TAG, "onKeyUp: " + keyCode);
        return false;
    }

    private void initializeEditTextView() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextEmail.setSingleLine(true);

        editTextSignupPassword = findViewById(R.id.editTextSignupPassword);
        editTextSignupPassword.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        editTextSchool = findViewById(R.id.editTextSchool);
        editTextSchool.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextConfirmPassword.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextSchool.setSingleLine(true);
        editTextSchool.setEllipsize(TextUtils.TruncateAt.END);
        editTextSchool.setFocusable(false);
        if (!SCHOOL_ID_FOR_PRE.equalsIgnoreCase("")) {
            editTextSchool.setText(SCHOOL_NAME_FOR_PRE);
            editTextSchool.setTag(SCHOOL_ID_FOR_PRE);


            WebServices webServices = new WebServices();

            if (rLAyoutForBackgroundPic.getVisibility() != View.VISIBLE) {
                whichApiCalled = "getTeamsBasedOnSchool";
                webServices.getTeamsBasedOnSchool(SCHOOL_ID_FOR_PRE, context, LoginScreen.this);
            }
        }

        editTextSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectionTypeOfList = "school";
                custom_popup_class mAlert = new custom_popup_class(context, "school", editTextSchool.getTag().toString(), (RecyclerViewClickCheck) context);
                mAlert.setMessage("SELECT SCHOOL");
                mAlert.show();
                editTextTeam.setText("");
                editTextTeam.setTag("");


            }
        });


        editTextTeam = findViewById(R.id.editTextTeam);
        editTextTeam.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextTeam.setSingleLine(true);
        editTextTeam.setEllipsize(TextUtils.TruncateAt.END);
        editTextTeam.setFocusable(false);
        editTextTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rLAyoutForBackgroundPic.getVisibility() == View.VISIBLE) {


                    SelectionTypeOfList = "position";
                    custom_popup_class mAlert = new custom_popup_class(context, "position", editTextTeam.getTag().toString(), (RecyclerViewClickCheck) context);
                    mAlert.setMessage("SELECT POSITION");
                    mAlert.show();
                } else {
                    if (teamsLocal == null || teamsLocal.size() <= 0) {
                        UtilityClass.showAlertDailog(context, "Please select school.");
                        return;
                    }
                    SelectionTypeOfList = "team";
                    custom_popup_class mAlert = new custom_popup_class(context, "team", editTextTeam.getTag().toString(), (RecyclerViewClickCheck) context, teamsLocal);
                    mAlert.setMessage("SELECT TEAM");
                    mAlert.show();

                    //showChooseItemAlertDialog(editTextTeam, teamNameArray); // for athlete*/
                }
            }
        });

        editTextSelectPicture = findViewById(R.id.editTextSelectPicture);
        editTextSelectPicture.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        editTextSelectPicture.setHint(getString(R.string.tap_background_theme));
        editTextSelectPicture.setSingleLine(true);
        editTextSelectPicture.setFocusable(false);
        editTextSelectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                showPictureDialog();
            }
        });

        rLayoutDivider5 = findViewById(R.id.rLayoutDivider5);
        rLayoutDivider5.setVisibility(View.GONE);

        rLayoutDivider6 = findViewById(R.id.rLayoutDivider6);
        rLayoutDivider6.setVisibility(View.GONE);


        editTextConfirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    editTextSchool.requestFocus();
                    SelectionTypeOfList = "school";
                    custom_popup_class mAlert = new custom_popup_class(context, "school", editTextSchool.getTag().toString(), (RecyclerViewClickCheck) context);
                    mAlert.setMessage("SELECT SCHOOL");
                    mAlert.show();
                    //showChooseItemAlertDialog(editTextSchool, SchoolDataService.schoolArray);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void initializeVideoView() {
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        imageViewCircle = findViewById(R.id.imageViewCircle);
        imageViewCircleOne = findViewById(R.id.imageViewCircleOne);
        imageViewCircleTwo = findViewById(R.id.imageViewCircleTwo);

        rLayoutForTextViewHolder = findViewById(R.id.rLayoutForTextViewHolder);

        textviewLoginWhite = findViewById(R.id.textviewLoginWhite);
        textviewLoginWhite.setTypeface(fontFaceBoldAgency);

        textViewLoginButtonText = findViewById(R.id.textViewLoginButtonText);
        textViewLoginButtonText.setTypeface(fontFaceBoldAgency);

        textViewAthlete = findViewById(R.id.textViewAthlete);
        textViewAthlete.setTypeface(fontFaceBoldAgency);

        textViewBetter = findViewById(R.id.textViewBetter);
        textViewBetter.setTypeface(fontFaceRegularAgency);

        videoViewPlayer = findViewById(R.id.videoViewPlayer);
        videoViewPlayer.setOnCompletionListener(this);


        imageViewCircleOne.setImageResource(R.drawable.dots_gray);
        imageViewCircleTwo.setImageResource(R.drawable.dots_gray);

        videoViewPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                running = true;
                final int duration = videoViewPlayer.getDuration();
                new Thread(new Runnable() {
                    public void run() {
                        do {
                            textViewAthlete.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoViewPlayer.getCurrentPosition()) / 1000;
                                    startVideoTextMessageTimer(time);
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!running) break;
                        }
                        while (videoViewPlayer.getCurrentPosition() < duration);
                    }
                }).start();
            }
        });

        videoViewPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                if (SplashScreen.PlayVideo) {
                    stopPlaying();
                }
                return false;
            }
        });

//        videoViewPlayer.setOnTouchListener( new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if( ((VideoView)v).isPlaying() )
//                    ((VideoView)v).pause();
//                else
//                    ((VideoView)v).start();
//                return true;
//            }
//        });

        if (!playFileRes(getVideoPath(currentVideoNumber))) return;
        videoViewPlayer.requestFocus();
        videoViewPlayer.start();
    }

    private boolean playFileRes(String videoPath) {
        if (videoPath == null || "".equals(videoPath)) {
            stopPlaying();
            return false;
        } else {
            videoViewPlayer.setVideoURI(Uri.parse(videoPath));
            return true;
        }
    }

    public void stopPlaying() {
        rLayoutForTextViewHolder.setVisibility(View.GONE);
        running = false;
        videoViewPlayer.stopPlayback();
    }

    public void pausePlaying() {
        if (SplashScreen.PlayVideo) {
            rLayoutForTextViewHolder.setVisibility(View.GONE);
            running = false;
            videoViewPlayer.pause();
        }
    }

    public void resumePlayer() {
        if (SplashScreen.PlayVideo) {
            rLayoutForTextViewHolder.setVisibility(View.VISIBLE);
            running = true;
            videoViewPlayer.start();
        }
    }

    private void startVideoTextMessageTimer(int time) {
        rLayoutForTextViewHolder.setVisibility(View.VISIBLE);
        countHandlerValueForTextChange = time;
        if (countHandlerValueForTextChange == 15) {
            textViewAthlete.setText(videoTypes[0]);
            imageViewCircleOne.setImageResource(R.drawable.dots_gray);
            imageViewCircleTwo.setImageResource(R.drawable.dots_gray);
            imageViewCircle.setImageResource(R.drawable.dots_yellow);
        } else if (countHandlerValueForTextChange == 10) {
            textViewAthlete.setText(videoTypes[1]);
            imageViewCircleOne.setImageResource(R.drawable.dots_yellow);
            imageViewCircleTwo.setImageResource(R.drawable.dots_gray);
            imageViewCircle.setImageResource(R.drawable.dots_yellow);
        } else if (countHandlerValueForTextChange == 5) {
            textViewAthlete.setText(videoTypes[2]);
            imageViewCircleOne.setImageResource(R.drawable.dots_yellow);
            imageViewCircleTwo.setImageResource(R.drawable.dots_yellow);
            imageViewCircle.setImageResource(R.drawable.dots_yellow);
        }

//                countHandlerValueForTextChange = countHandlerValueForTextChange + 1;

//                //Toast.makeText(context,"Count = " + countHandlerValueForTextChange ,Toast.LENGTH_LONG).show();

//        Log.e("Count = " + countHandlerValueForTextChange,"***");
    }

    private void initializeLayoutView() {
        rLayoutForLoginPopup = findViewById(R.id.rLayoutForLoginPopup);
        rLayoutForLoginPopup.setVisibility(View.GONE);

        rLayoutForForgotPassword = findViewById(R.id.rLayoutForForgotPassword);
        rLayoutForForgotPassword.setVisibility(View.GONE);

//        rLayoutViewPager = (RelativeLayout) findViewById(R.id.rLayoutViewPager);


        rLayoutLoginButton = findViewById(R.id.rLayoutLoginButton);
        rLayoutLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilityClass.isDeviceInternetAvailable(context)) {

                    SharedPreferences prefs = getSharedPreferences("ID_Crediental", MODE_PRIVATE);
                    String restoredemail = prefs.getString("email", null);
                    String restoredpassword = prefs.getString("password", null);
                    if (restoredemail != null && restoredpassword != null) {

                        //  //Toast.makeText(context, restoredemail+"\n"+restoredpassword, Toast.LENGTH_SHORT).show();
                        autoLoginToggle = true;
                        //UtilityClass.showWaitDialog(new Dialog(context),context);
                        editTextUserName.setText(restoredemail);
                        editTextPassword.setText(restoredpassword);
                        editTextEmail.setText(restoredemail);
                        editTextEmail.setText(restoredpassword);
                        whichApiCalled = "signin";
                        WebServices webServices = new WebServices();

                        webServices.login(restoredemail, restoredpassword, IP_ADDRESS, DEVICE_TYPE, FCM_TOKEN, context, LoginScreen.this);
                        initializeLayoutView();
                    } else {
                        autoLoginToggle = false;
                        try {
                            videoViewPlayer.setVisibility(View.GONE);
                            pausePlaying();
                            initializeLayoutView();
                        } catch (Exception x) {

                        }
                        if (rLayoutForLoginPopup.getVisibility() == View.GONE) {
                            if (SplashScreen.PlayVideo) {
                                videoViewPlayer.setVisibility(View.GONE);
                                pausePlaying();
                            }
                            rLayoutForLoginPopup.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    if (SplashScreen.PlayVideo) {
                        videoViewPlayer.setVisibility(View.GONE);
                        pausePlaying();
                    }
                    rLayoutForLoginPopup.setVisibility(View.VISIBLE);
                }

//                rLayoutLoginButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
////                rLayoutViewPager.setVisibility(View.GONE);
////                ((VideoPagerAdapter) adapter).stopVideo();
//                        pausePlaying();
//                        videoViewPlayer.setVisibility(View.GONE);
//                        if (rLayoutForLoginPopup.getVisibility() == View.GONE) {
//                            rLayoutForLoginPopup.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });


            }
        });

        rLayoutLoginYellowButton = findViewById(R.id.rLayoutLoginYellowButton);
        rLayoutLoginYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                if (editTextUserName.getText().toString().equalsIgnoreCase("coach")) {
//                    bundle.putBoolean("isAthlete", false);
//                } else {
//                    bundle.putBoolean("isAthlete", true);
//                }


                hideKeyboard(rLayoutLoginYellowButton);
                whichApiCalled = "signin";
                String emailAddress = editTextUserName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (emailAddress.length() > 0 && password.length() > 0) {
                    if (checkEmail(emailAddress.trim()) == true) {
                        //if (UtilityClass.isDeviceInternetAvailable(context)) {

                        if (savepasstoggle) {
                            //UtilityClass.showWaitDialog(new Dialog(context),context);

                            SharedPreferences.Editor editor = getSharedPreferences("ID_Crediental", MODE_PRIVATE).edit();
                            ////Toast.makeText(context, editTextUserName.getText(), Toast.LENGTH_SHORT).show();

                            editor.putString("email", editTextUserName.getText().toString());
                            editor.putString("password", editTextPassword.getText().toString());

                            editor.apply();
                            WebServices webServices = new WebServices();
                            //();
                            webServices.login(emailAddress, password, IP_ADDRESS, DEVICE_TYPE, FCM_TOKEN, context, LoginScreen.this);

                        } else {
                            //UtilityClass.showWaitDialog(new Dialog(context),context);
                            WebServices webServices = new WebServices();
                            //();
                            webServices.login(emailAddress, password, IP_ADDRESS, DEVICE_TYPE, FCM_TOKEN, context, LoginScreen.this);
                        }


                        //}
//                        else {
//                            UtilityClass.showAlertDailog(context,
//                                    getString(R.string.internet_error));
//                        }
                    } else {
                        UtilityClass.showAlertDailog(context, getString(R.string.invalid_email_address));
                    }
                } else {
                    UtilityClass.showAlertDailog(context, getString(R.string.email_password_blank));
                }


//                startActivity(new Intent(LoginScreen.this, ChooseUserTypeScreen.class).putExtras(bundle));
            }
        });


        rLayoutSignupYellowButton = findViewById(R.id.rLayoutSignupYellowButton);
        rLayoutSignupYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password, confirmPassword, school, imageResourcePath = "", team_position_title = "";
                email = editTextEmail.getText().toString().trim();
                password = editTextSignupPassword.getText().toString().trim();
                confirmPassword = editTextConfirmPassword.getText().toString().trim();
                imageResourcePath = editTextSelectPicture.getText().toString().trim();
                team_position_title = editTextTeam.getTag().toString().trim();
                school = editTextSchool.getTag().toString().trim();

                if (email.length() > 0 && password.length() > 0 && confirmPassword.length() > 0 && school.length() > 0)// && (team_position_title.length()>0 && rLAyoutForBackgroundPic.getVisibility()==View.VISIBLE))
                {
                    if (password.equalsIgnoreCase(confirmPassword)) {
                        if (checkEmail(email)) {
                            //UtilityClass.showWaitDialog(new Dialog(context),context);
                            whichApiCalled = "signup";
                            WebServices webServices = new WebServices();
                            if (rLAyoutForBackgroundPic.getVisibility() == View.GONE || rLAyoutForBackgroundPic.getVisibility() == View.INVISIBLE) {
                                webServices.SignUp(email, password, school, team_position_title, "4", "", FCM_TOKEN, DEVICE_TYPE, IP_ADDRESS, context, LoginScreen.this);

                            } else {
                                webServices.SignUp(email, password, school, team_position_title, "3", imageResourcePath, FCM_TOKEN, DEVICE_TYPE, IP_ADDRESS, context, LoginScreen.this);
                            }
                        } else {
                            UtilityClass.showAlertDailog(context, getString(R.string.invalid_email_address));
                        }
                    } else {
                        UtilityClass.showAlertDailog(context, getString(R.string.password_confirm_password_invalid));
                    }
                } else {
                    if (rLAyoutForBackgroundPic.getVisibility() == View.VISIBLE) {
                        UtilityClass.showAlertDailog(context, getString(R.string.missing_fields_coach));
                    } else {
                        UtilityClass.showAlertDailog(context, getString(R.string.missing_fields_athlete));
                    }

                }
            }
        });
        rLayoutSubmitYellowButton = findViewById(R.id.rLayoutSubmitYellowButton);
        rLayoutSubmitYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichApiCalled = "forgot";
                WebServices webServices = new WebServices();
                String emailId = editTextUserEmailForForgotPassword.getText().toString().trim();
                if (emailId.length() > 0) {
                    if (checkEmail(emailId)) {
                        webServices.ForgotPassword(emailId, context, LoginScreen.this);
                    } else {
                        UtilityClass.showAlertDailog(context, getString(R.string.invalid_email_address));
                    }
                } else {
                    UtilityClass.showAlertDailog(context, getString(R.string.forgot_email_blank));
                }

            }
        });

        rLayoutMultipleSignupButton = findViewById(R.id.rLayoutMultipleSignupButton);

        // for signup button click.
        rLayoutLoginSignupButton = findViewById(R.id.rLayoutSignupButton);
        rLayoutLoginSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SplashScreen.PlayVideo) {
                    pausePlaying();
                    videoViewPlayer.setVisibility(View.GONE);
                }
                String prefTagX = "ID_Crediental";
                SharedPreferences preferences = getSharedPreferences(prefTagX, 0);
                preferences.edit().remove("email").apply();
                preferences.edit().remove("password").apply();
                editTextUserName.setText("");
                editTextPassword.setText("");
                editTextEmail.setText("");
                editTextEmail.setText("");
                savepasstoggle = false;
                if (rLayoutForSignupOption.getVisibility() == View.GONE) {
                    rLayoutForSignupOption.setVisibility(View.VISIBLE);
                    rLayoutMultipleSignupButton.setVisibility(View.VISIBLE);
                }
            }
        });

        rLayoutForSignupOption = findViewById(R.id.rLayoutForSignupOption);
        rLayoutForSignupOption.setClickable(false);
        rLayoutForSignupOption.setVisibility(View.GONE);


        rLayoutSignupAsAthlete = findViewById(R.id.rLayoutSignupAsAthlete);
        rLayoutSignupAsAthlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSignupProfilePopup(false);
            }
        });
        rLayoutSignupAsCoach = findViewById(R.id.rLayoutSignupAsCoach);
        rLayoutSignupAsCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSignupProfilePopup(true);
            }
        });

        rLAyoutForTeam = findViewById(R.id.rLAyoutForTeam_Position_Title);
        rLAyoutForTeam.setVisibility(View.GONE);


        rLAyoutForBackgroundPic = findViewById(R.id.rLAyoutForBackgroundPic);
        rLAyoutForBackgroundPic.setVisibility(View.GONE);


        imageViewCloseSignupLayout = findViewById(R.id.imageViewCloseSignupLayout);
        imageViewCloseSignupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSignupPopup();

            }
        });

        imageViewCloseForgotPasswordLayout = findViewById(R.id.imageViewCloseForgotPasswordLayout);
        imageViewCloseForgotPasswordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });
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
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private void resetSignupFields() {
        editTextSchool.setText("");
        editTextUserEmailForForgotPassword.setText("");
        editTextTeam.setText("");
        editTextSelectPicture.setText("");
        editTextConfirmPassword.setText("");
        editTextEmail.setText("");
        editTextSignupPassword.setText("");
        editTextTeam.setTag("");
        editTextSchool.setTag("");
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

    private void closeSignupPopup() {
        if (rLayoutForSignupOption.getVisibility() == View.VISIBLE & rLayoutForSignupFields.getVisibility() == View.GONE) {
            rLayoutForSignupOption.setVisibility(View.GONE);
//            rLayoutViewPager.setVisibility(View.VISIBLE);
            if (SplashScreen.PlayVideo) {
                videoViewPlayer.setVisibility(View.VISIBLE);
                resumePlayer();
            }


        } else if (rLayoutForSignupFields.getVisibility() == View.VISIBLE) {
            rLayoutForSignupFields.setVisibility(View.GONE);
            rLayoutForSignupOption.setVisibility(View.VISIBLE);
            rLayoutMultipleSignupButton.setVisibility(View.VISIBLE);
        }
    }

    private void showSignupProfilePopup(boolean isCoach) {
        scrollViewSignupCoachAthlete.fullScroll(ScrollView.FOCUS_UP);
        rLayoutMultipleSignupButton.setVisibility(View.GONE);
        resetSignupFields();
        if (isCoach) {
            rLAyoutForTeam.setVisibility(View.VISIBLE);
            editTextTeam.setHint(getString(R.string.position));
            rLayoutDivider5.setVisibility(View.VISIBLE);
            rLAyoutForBackgroundPic.setVisibility(View.VISIBLE);
            rLayoutDivider6.setVisibility(View.VISIBLE);

        } else {
            rLAyoutForTeam.setVisibility(View.VISIBLE);
            rLayoutDivider5.setVisibility(View.VISIBLE);
            editTextTeam.setHint(getString(R.string.team));
            rLAyoutForBackgroundPic.setVisibility(View.GONE);
            rLayoutDivider6.setVisibility(View.GONE);

        }
        rLayoutForSignupFields.setVisibility(View.VISIBLE);
    }

    private void initializeVideoPager() {

//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//
//        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
//        // Pass results to ViewPagerAdapter Class
//        adapter = new VideoPagerAdapter(context, ids);
//        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(0);
//
//        setUiPageViewController();
//
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e("On Scroll Position ", position + "  page no...");
//
////                if (position == 0) {
////                    ((VideoPagerAdapter) adapter).play(position);
////                    ((VideoPagerAdapter) adapter).setVideoTypes(videoTypes[position]);
////                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//
//                ((VideoPagerAdapter) adapter).play(position);
//                ((VideoPagerAdapter) adapter).setVideoTypes(videoTypes[position]);
//
////                playVideo(position);
//                for (int i = 0; i < dotsCount; i++) {
//                    dots[i].setImageDrawable(getResources().getDrawable(
//                            R.drawable.dots_gray));
//                }
//
//                dots[position].setImageDrawable(getResources().getDrawable(
//                        R.drawable.dots_yellow));
//            }
//
//            //
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//
//
//        });
    }

    private Uri getPath(int id) {
        Log.i("Video", "num" + id);
        return Uri.parse("android.resource://" + getPackageName() + "/raw/video_" + id);
    }

    private String getVideoPath(int id) {
        Log.i("Video", "num" + id);
        return ("android.resource://" + getPackageName() + "/raw/video_" + (id + 1));
    }

    private void initializeTextView() {
        textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSignup = findViewById(R.id.textViewSignup);
        textViewSignup.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewSignupHeading = findViewById(R.id.textViewSignupHeading);
        textViewSignupHeading.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewSignupButtonText = findViewById(R.id.textViewSignupButtonText);
        textViewSignupButtonText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textviewForgotPasswordWhite = findViewById(R.id.textviewForgotPasswordWhite);
        textviewForgotPasswordWhite.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewSignupAsCoach = findViewById(R.id.textViewSignupAsCoach);
        textViewSignupAsCoach.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewSignupAsAthlete = findViewById(R.id.textViewSignupAsAthlete);
        textViewSignupAsAthlete.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textviewForgotPassword = findViewById(R.id.textviewForgotPassword);
        textviewForgotPassword.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textviewForgotPassword.setOnClickListener(view -> {
            rLayoutForForgotPassword.setVisibility(View.VISIBLE);
            rLayoutForLoginPopup.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    protected void onResume() {
        if (SplashScreen.PlayVideo) {
            resumePlayer();
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Log.e(VolleyLog.TAG, "onBackPressed: " + rLayoutForLoginPopup.getVisibility());
//        if (rLayoutForLoginPopup.getVisibility() == View.VISIBLE) {
//            rLayoutForLoginPopup.setVisibility(View.GONE);
////            rLayoutViewPager.setVisibility(View.VISIBLE);
//            videoViewPlayer.setVisibility(View.VISIBLE);
//            resumePlayer();
//        } else if (rLayoutForSignupOption.getVisibility() == View.VISIBLE) {
//            closeSignupPopup();
//
//        } else if (rLayoutForForgotPassword.getVisibility() == View.VISIBLE) {
//            rLayoutForLoginPopup.setVisibility(View.VISIBLE);
//            rLayoutForForgotPassword.setVisibility(View.GONE);
//        } else {
//            super.onBackPressed();
//            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
//        }

        if (rLayoutForLoginPopup.getVisibility() == View.VISIBLE) {
            rLayoutForLoginPopup.setVisibility(View.GONE);
            //rLayoutViewPager.setVisibility(View.VISIBLE);
            if (SplashScreen.PlayVideo) {
                videoViewPlayer.setVisibility(View.VISIBLE);
                resumePlayer();
            }
        } else if (rLayoutForSignupOption.getVisibility() == View.VISIBLE) {
            closeSignupPopup();
            resumePlayer();
        } else if (rLayoutForForgotPassword.getVisibility() == View.VISIBLE) {
            resetSignIn();
            rLayoutForLoginPopup.setVisibility(View.VISIBLE);
            rLayoutForForgotPassword.setVisibility(View.GONE);
        } else
            super.onBackPressed();


        //overridePendingTransition(R.anim.rtl_anim,R.anim.ltr_anim);
        overridePendingTransition(R.anim.exit, R.anim.enter);
        //overridePendingTransition(R.anim.ltr_anim,R.anim.rtl_anim);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
//        if(currentVideoNumber<2)
//        {
//            currentVideoNumber = currentVideoNumber +1;
//            textViewAthlete.setText(videoTypes[currentVideoNumber]);
//            if(currentVideoNumber==1)
//            {
//                imageViewCircleOne.setImageResource(R.drawable.dots_yellow);
//                imageViewCircleTwo.setImageResource(R.drawable.dots_gray);
//            }
//            else
//            {
//                imageViewCircleTwo.setImageResource(R.drawable.dots_yellow);
//            }
//        }
//        else
//        {
//            currentVideoNumber=0;
//            textViewAthlete.setText(videoTypes[currentVideoNumber]);
//            imageViewCircleOne.setImageResource(R.drawable.dots_gray);
//            imageViewCircleTwo.setImageResource(R.drawable.dots_gray);
//
//        }

        running = false;

        new Handler().postDelayed(() -> {
            playFileRes(getVideoPath(currentVideoNumber));
            videoViewPlayer.requestFocus();
            videoViewPlayer.start();
        }, 1000);
    }

    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, whichApiCalled + "********* \n" + result);
        if (result != null && result.trim().length() > 0) {
            if (whichApiCalled.equalsIgnoreCase("signin") || whichApiCalled.equalsIgnoreCase("signup")) {
                // parse singin data...
                parseLoginData(result);
                ////UtilityClass.hide();

            } else if (whichApiCalled.equalsIgnoreCase("forgot")) {
                try {
                    JSONObject jsonObj = new JSONObject(result);

                    String responseCode = jsonObj
                            .getString("responseMessage");
                    UtilityClass.showAlertDailog(context, jsonObj
                            .getString("responseMessage"));
                    if (responseCode.equalsIgnoreCase("200")) {
                        onBackPressed();
                        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
                    }
//                Log.e("**********", "" + responseMessage);
                } catch (JSONException e) {

                    e.printStackTrace();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            } else if (whichApiCalled.equalsIgnoreCase("school")) {
                UtilityClass.SetPreferences(context, getString(R.string.school), result);
                whichApiCalled = "sport";
                WebServices webServices = new WebServices();
                webServices.getSports(context, LoginScreen.this);
            } else if (whichApiCalled.equalsIgnoreCase("sport")) {
                UtilityClass.SetPreferences(context, getString(R.string.sport), result);

                whichApiCalled = "position";
                WebServices webServices = new WebServices();
                webServices.getPosition(context, LoginScreen.this);
            } else if (whichApiCalled.equalsIgnoreCase("position")) {
                UtilityClass.SetPreferences(context, getString(R.string.position), result);
                whichApiCalled = "team";
                WebServices webServices = new WebServices();
                // webServices.getTeams("", context, LoginScreen.this);

            } else if (whichApiCalled.equalsIgnoreCase("team")) {
                UtilityClass.SetPreferences(context, getString(R.string.team), result);
                //imageViewForZoomInOut.setVisibility(View.GONE);
            } else if (whichApiCalled.equalsIgnoreCase("getTeamsBasedOnSchool")) {
                Log.e(VolleyLog.TAG, "ApiResponse: " + result);


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


                        teamsLocal = new ArrayList<>();
                        List<Team> teamsLocalX = new ArrayList<>(Arrays.asList(new Gson().fromJson(usersData, Team[].class)));
                        for (int i = 0; i < teamsLocalX.size(); i++) {
                            if (!teamsLocalX.get(i).getTeamId().equalsIgnoreCase("0"))
                                teamsLocal.add(teamsLocalX.get(i));
                        }


                        editTextTeam.performClick();

                        //showChooseItemAlertDialog(editTextTeam, teamNameArray);
                    } else {
                        UtilityClass.showAlertDailog(context, responseMessage);
                    }
                } catch (JSONException e) {

                    Log.e(VolleyLog.TAG, "parseLoginData:1111 ");
                    e.printStackTrace();
                    Log.e("Error in json parsing", e.getMessage());
                } catch (Exception e) {

                    Log.e(VolleyLog.TAG, "parseLoginData:2222 ");
                    e.printStackTrace();
                }
                hide();
            }
        } else {
            hide();
            //();
        }

    }

    private void parseLoginData(String result) {
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


                JSONObject userJson = new JSONObject(usersData);


                // JSONArray x =  new JSONArray(usersData);
                JSONArray Obj = new JSONArray();
                Obj.put(userJson);

                LoginJson = Arrays.asList(gson.fromJson(userJson.toString(), Login.class));

                //LoginJson.get(0).setMembership_status("1");

                try {

                    if (LoginJson.get(0).getMembership_status().equalsIgnoreCase("0")) {
                        MEMBERSHIP_STATUS = 0;
                    } else {
                        MEMBERSHIP_STATUS = 1;
                    }
                } catch (Exception m) {
                    MEMBERSHIP_STATUS = 0;
                }

                //Toast.makeText(context, LoginJson.get(0).getMembership_status()+"", Toast.LENGTH_SHORT).show();
                try {
                    team_id = LoginJson.get(0).getTeams().get(0).getTeamId();
                    UtilityClass.SetPreferences(context,
                            getString(R.string.team_id_tag),
                            team_id);


                    UtilityClass.SetPreferences(context,
                            getString(R.string.team_name_tag), LoginJson.get(0).getTeams().get(0).getTeamName());

                } catch (Exception x) {
                    Log.e(VolleyLog.TAG, "parseLoginData: " + x);
                }


                userId = userJson
                        .getString("user_id");
                UtilityClass.SetPreferences(context,
                        getString(R.string.user_id_tag), userId);
                CallApiForTeamByCoach();
                try {
                    GlobalClass.ivar2 = Integer.parseInt(userJson.getString("questionnaire"));
                } catch (Exception m) {
                }

                ////Toast.makeText(context, "HAHAHAHAHAHAHAHAH"+, Toast.LENGTH_SHORT).show();
                UtilityClass.SetPreferences(context, getString(R.string.user_image_tag), userJson.getString(getString(R.string.user_image_tag)));


                GlobalClass.userImage = userJson.getString(getString(R.string.user_image_tag));

                UtilityClass.SetPreferences(context, getString(R.string.school_id_tag), userJson
                        .getString(getString(R.string.school_id_tag)));

                UtilityClass.SetPreferences(context,
                        getString(R.string.school_name_tag),
                        userJson
                                .getString(getString(R.string.school_name_tag)));

                UtilityClass.SetPreferences(context,
                        getString(R.string.position_title_name_tag),
                        userJson
                                .getString(getString(R.string.position_title_name_tag)));

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
                        userJson.getString(getString(R.string.last_name_tag)));
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

                UtilityClass.SetPreferences(context, getString(R.string.suit_tag), userJson.getString(getString(R.string.suit_tag)));
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
                UtilityClass.SetPreferences(context,
                        getString(R.string.sport_id_tag),
                        userJson
                                .getString(getString(R.string.sport_id_tag)));

                UtilityClass.SetPreferences(context,
                        getString(R.string.sport_name_tag),
                        userJson
                                .getString(getString(R.string.sport_name_tag)));


                UtilityClass.SetPreferences(context,
                        getString(R.string.social_head_tag),
                        userJson
                                .getString(getString(R.string.social_head_tag)));


                UtilityClass.SetPreferences(context,
                        getString(R.string.athlete_photo_tag),
                        userJson
                                .getString(getString(R.string.athlete_photo_tag)));

                UtilityClass.SetPreferences(context,
                        getString(R.string.height_tag),
                        userJson
                                .getString(getString(R.string.height_tag)));

                UtilityClass.SetPreferences(context,
                        getString(R.string.weight_tag),
                        userJson
                                .getString(getString(R.string.weight_tag)));

                UtilityClass.SetPreferences(context,
                        getString(R.string.smm_tag),
                        userJson
                                .getString(getString(R.string.smm_tag)));


                UtilityClass.SetPreferences(context,
                        getString(R.string.body_fat_tag),
                        userJson
                                .getString(getString(R.string.body_fat_tag)));


                UtilityClass.SetPreferences(context,
                        getString(R.string.body_fat_tag),
                        userJson
                                .getString(getString(R.string.body_fat_tag)));


                try {
                    UtilityClass.SetPreferences(context, getString(R.string.password_type),
                            userJson.getString(getString(R.string.password_type)));
                } catch (Exception b) {
                }


//                try{
//                    if (!userJson.getString("assing_program_details").equals("")) {
//                        JSONArray users = userJson.getJSONArray("assing_program_details");
//                        String usersata = userJson
//                                .getString("assing_program_details");
//                        JSONArray useJson = new JSONArray(usersata);
//                        for (int i = 0; i < useJson.length(); i++) {
//                            JSONObject jsonObject = useJson.getJSONObject(i);
//                            if (GlobalClass.program_id == null) {
//                                GlobalClass.program_id = jsonObject.optString("assign_program_id").toString();
//                                GlobalClass.Date = jsonObject.optString("start_date").toString();
//                            }
//                        }
//                    }
//                }catch (Exception m){
//
//                }

                // start activity with coach or athlete information where userType = 4 for Athlete and userType = 3 is for coach...
                Bundle bundle = new Bundle();

                screenname = "coachboard";
                if (whichApiCalled.equalsIgnoreCase("signup")) {
                    if (userType.equalsIgnoreCase("3")) {
                        bundle.putBoolean("isAthlete", false);
                        bundle.putBoolean("isCoach", true);
                        bundle.putBoolean("showQuestion", false);
                        OnlyAthlete = "OnlyCoach";
                        GlobalClass.ivar1 = 1;
                        bundle.putString("volume_level", volume_level + "");
                        hide();
                        //startActivity(new Intent(LoginScreen.this, CoachAddExerciseScreen.class).putExtras(bundle));
                        startActivity(new Intent(LoginScreen.this, ChooseUserTypeScreen.class).putExtras(bundle));
                        overridePendingTransition(R.anim.exit, R.anim.enter);
                        Log.d(VolleyLog.TAG, "*************** ChooseUserTypeScreen *************");

                        mStore = null;
                        finish();
                    } else {
                        bundle.putBoolean("isAthlete", true);
                        bundle.putBoolean("isCoach", false);
                        OnlyAthlete = "OnlyAthlete";
                        GlobalClass.ivar1 = 2;

                        bundle.putBoolean("treatMyAccountAsAthlete", true);
                        bundle.putBoolean("showQuestion", true);
                        hide();
                        mStore = null;
                        startActivity(new Intent(LoginScreen.this, CoachNevigationDrawerScreen.class).putExtras(bundle));
                        overridePendingTransition(R.anim.exit, R.anim.enter);
                        Log.d(VolleyLog.TAG, "*************** CoachNevigationDrawerScreen *************");

                        finish();
                    }

                } else {
                    if (userJson.getString(getString(R.string.password_type)).equalsIgnoreCase("1")) {
                        hide();
                        startActivity(new Intent(LoginScreen.this, Reset_Password.class));
                        overridePendingTransition(R.anim.exit, R.anim.enter);
                        Log.d(VolleyLog.TAG, "*************** Reset_Password *************");

                    } else {
                        if (userType.equalsIgnoreCase("3") || userType.equalsIgnoreCase("2") || userType.equalsIgnoreCase("1")) {
                            bundle.putBoolean("isAthlete", false);
                            bundle.putBoolean("isCoach", true);
                            bundle.putBoolean("showQuestion", false);
                            OnlyAthlete = "OnlyCoach";
                            GlobalClass.ivar1 = 1;
                            bundle.putString("volume_level", volume_level + "");


                            hide();
                            startActivity(new Intent(LoginScreen.this, ChooseUserTypeScreen.class).putExtras(bundle));
                            overridePendingTransition(R.anim.exit, R.anim.enter);
                            Log.d(VolleyLog.TAG, "*************** ChooseUserTypeScreen *************");

                            finish();
                        } else {
                            bundle.putBoolean("isAthlete", true);
                            bundle.putBoolean("isCoach", false);
                            OnlyAthlete = "OnlyAthlete";
                            GlobalClass.ivar1 = 2;

                            bundle.putBoolean("treatMyAccountAsAthlete", true);
                            bundle.putBoolean("showQuestion", true);
                            hide();
                            startActivity(new Intent(LoginScreen.this, CoachNevigationDrawerScreen.class).putExtras(bundle));
                            overridePendingTransition(R.anim.exit, R.anim.enter);
                            Log.d(VolleyLog.TAG, "*************** CoachNevigationDrawerScreen *************");

                            finish();

                        }
                        Log.e(VolleyLog.TAG, "parseLoginData: ashdjbvsudhvjhdzfx");
                        //MyClass.hide();
                    }
                }
                if (!responseMessage.contains("Login")) {
                    ////Toast.makeText(context, responseMessage, Toast.LENGTH_LONG).show();
                }

                userTypeOf = LoginJson.get(0).getUserType();

            } else if (WebServices.responseCode == 205) {
                UtilityClass.showAlertDailog(context, responseMessage);
                SharedPreferences.Editor editor = getSharedPreferences("ID_Crediental", MODE_PRIVATE).edit();
                ////Toast.makeText(context, editTextUserName.getText(), Toast.LENGTH_SHORT).show();

                editor.putString("email", editTextEmail.getText().toString().trim());
                editor.putString("password", editTextSignupPassword.getText().toString().trim());

                editTextEmail.setText("");
                editTextConfirmPassword.setText("");
                editTextSignupPassword.setText("");
                editTextSelectPicture.setText("");
                editTextTeam.setText("");
                editTextSchool.setText("");

                editor.apply();
                autoLoginToggle = true;
            } else {
                if (autoLoginToggle) {
                    // autoLoginToggle = false;
                    String prefTagX = "ID_Crediental";
//                    SharedPreferences preferences = getSharedPreferences(prefTagX, 0);
//                    preferences.edit().remove("email").apply();
//                    preferences.edit().remove("password").apply();
//                    editTextUserName.setText("");
//                    editTextPassword.setText("");
//                    editTextEmail.setText("");
//                    editTextEmail.setText("");
//                    savepasstoggle = false;

                    videoViewPlayer.setVisibility(View.GONE);
                    pausePlaying();
                    rLayoutForLoginPopup.setVisibility(View.VISIBLE);

                    //initializeLayoutView();
                } else {
                    //UtilityClass.showAlertDailog(context, responseMessage);
                }

                UtilityClass.showAlertDailog(context, responseMessage);


            }
        } catch (JSONException e) {

            Log.e(VolleyLog.TAG, "parseLoginData:1111 ");
            e.printStackTrace();
            Log.e("Error in json parsing", e.getMessage());
        } catch (Exception e) {

            Log.e(VolleyLog.TAG, "parseLoginData:2222 ");
            e.printStackTrace();
        }
        hide();

    }

//    private void showChooseItemAlertDialog(final View view, final String myArray[]) {
//        hideKeyboard(view);
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
////        mBuilder.setTitle(getString(R.string.app_name));
//        mBuilder.setSingleChoiceItems(myArray, -1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (view.getId()) {
//                    case R.id.editTextSchool:
//                        editTextSchool.setText(myArray[i]);
//                        editTextSchool.setTag(schoolIdArray[i]);
//                        editTextSchool.clearFocus();
//                        WebServices webServices = new WebServices();
//
//                        if (rLAyoutForBackgroundPic.getVisibility() == View.VISIBLE) {
//                            editTextTeam.requestFocus();
//
//
//                            positionTitleArray = new String[getPositionList.size()];
//                            positionTitleIdArray = new String[getPositionList.size()];
//                            for (int w = 0; w < getPositionList.size(); w++) {
//                                positionTitleArray[w] = getPositionList.get(w).getPositionTitleName();
//                                positionTitleIdArray[w] = getPositionList.get(w).getPositionTitleId();
//                            }
//                           // showChooseItemAlertDialog(editTextTeam, positionTitleArray); // for coach
//                        } else {
//                            whichApiCalled = "getTeamsBasedOnSchool";
//                            webServices.getTeamsBasedOnSchool(schoolIdArray[i], context, LoginScreen.this);
//                        }
//
//                        break;
//                    case R.id.editTextTeam:
//                        editTextTeam.setText(myArray[i]);
//                        if (rLAyoutForBackgroundPic.getVisibility() == View.GONE || rLAyoutForBackgroundPic.getVisibility() == View.INVISIBLE) {
//                            //editTextTeam.setTag(teamIdArray[i]);
//                        } else {
//                            editTextTeam.setTag(positionTitleIdArray[i]);
//                        }
//
//                        break;
//                }
//
//                dialogInterface.dismiss();
//            }
//        });
//        AlertDialog mDialog = mBuilder.create();
//        mDialog.show();
//
//    }

    private void showPictureDialog() {
        final custom_alert_class mAlert = new custom_alert_class(context);
        mAlert.setMessage("Select Action");
        mAlert.setPositveButton("Select photo from gallery", view -> {
            choosePhotoFromGallary();
            //adapter.notifyDataSetChanged();

            mAlert.dismiss();
        });
        mAlert.setNegativeButton("Capture photo from camera", view -> {
            takePhotoFromCamera();
            mAlert.dismiss();
        });
        mAlert.show();

    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
        overridePendingTransition(R.anim.exit, R.anim.enter);

    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    //imageViewBackground.setImageBitmap(bitmap);
                    editTextSelectPicture.setText(path);

                } catch (IOException e) {
                    e.printStackTrace();
                    ////Toast.makeText(LoginScreen.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

            String path = saveImage(thumbnail);
            imageViewBackground.setImageBitmap(thumbnail);
            editTextSelectPicture.setText(path);
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        File wallpaperDirectory = new File(
//                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        File wallpaperDirectory = new File(
                context.getFilesDir() + "/." + getString(R.string.app_name_for_directory));
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, backgroundImageName);
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
//            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    void showDialog(String message) {
        prgDailog.setMessage(message);
        prgDailog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                ////Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();

            } else {

                ////Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }//end onRequestPermissionsResult

    @NonNull
    private String getDeviceIpAddress() {
        String actualConnectedToNetwork = null;
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWifi.isConnected()) {
                actualConnectedToNetwork = getWifiIp();
            }
        }
        if (TextUtils.isEmpty(actualConnectedToNetwork)) {
            actualConnectedToNetwork = getNetworkInterfaceIpAddress();
        }
        if (TextUtils.isEmpty(actualConnectedToNetwork)) {
            actualConnectedToNetwork = "127.0.0.1";
        }
        return actualConnectedToNetwork;
    }

    @Nullable
    private String getWifiIp() {
        final WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (mWifiManager != null && mWifiManager.isWifiEnabled()) {
            int IP_ADDRESS = mWifiManager.getConnectionInfo().getIpAddress();
            return (IP_ADDRESS & 0xFF) + "." + ((IP_ADDRESS >> 8) & 0xFF) + "." + ((IP_ADDRESS >> 16) & 0xFF) + "."
                    + ((IP_ADDRESS >> 24) & 0xFF);
        }
        return null;
    }


}
