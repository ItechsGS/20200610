package com.org.godspeed.allOtherClasses;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.legacy.app.ActionBarDrawerToggle;

import com.android.volley.VolleyLog;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;
import com.org.godspeed.ProgramPurchase.ProgramPurchaseFragment;
import com.org.godspeed.R;
import com.org.godspeed.baseActivity.BaseActivity;
import com.org.godspeed.fragments.AthleteProfileFragment;
import com.org.godspeed.fragments.CoachBoardFragments;
import com.org.godspeed.fragments.Competition_Board;
import com.org.godspeed.fragments.FragmentComparativeAnalytics;
import com.org.godspeed.fragments.FragmentTrainingAndFolder;
import com.org.godspeed.fragments.HelpScreenFragment;
import com.org.godspeed.fragments.WhiteBoard;
import com.org.godspeed.fragments.scheduleCalender;
import com.org.godspeed.helper.AthleteMenuService;
import com.org.godspeed.response_JsonS.AthleteClasses.ArrayofClasses;
import com.org.godspeed.response_JsonS.FolderTraining.ArrayofFolder;
import com.org.godspeed.response_JsonS.getVideoClassCategory.GetVideoClassCategory;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.MaskImage;
import com.org.godspeed.utility.SchoolClass;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static android.view.View.GONE;
import static com.org.godspeed.utility.GlobalClass.OnlyAthlete;
import static com.org.godspeed.utility.UtilityClass.getDeviceTypeMobile;

/**
 * Created by Tanveer on 8/6/2017.
 */

public class CoachNevigationDrawerScreen extends BaseActivity implements GodSpeedInterface {

    private static final int ACTION_REQUEST_GALLERY = 501;
    private static final int ACTION_REQUEST_CAMERA = 502;
    private static final int ACTION_REQUEST_CANCEL_LIST_ITEM_SELECTION = 503;
    public static CoachNevigationDrawerScreen sActivityXX;
    public static String CopiedData = "";
    public static Boolean MoveFolder = false;
    public static Boolean MoveTraining = false;
    public static Boolean CAllAPI = false;
    public static int POSITION = 0;
    public static String FromSCreen = "";
    public static String screenname = "";
    public static List<ArrayofFolder> arrayofFoldersSelected = new ArrayList<>();
    public static Boolean PurchaseTP = false;
    public static String SportsIDForTPpurchase = "";
    public static List<GetVideoClassCategory> getVideoClassCategoryList = new ArrayList<>();
    public static LinearLayout PasteTF;
    public static String folderId = "";
    public static TextView textViewScreenName;
    public static ImageView imageViewAddTrainingProgram;
    public static ImageView imageViewSliderDrawerToggleIcon;
    public static ImageView imageViewSliderBackIcon;
    public static ImageView imageViewMenuFilter;
    public static TextView MyProfile;
    public static Vector<SchoolClass> vectorSchoolData = null;
    public static List<ArrayofClasses> arrayofClasses;
    public static RelativeLayout LayoutForFolder;
    public static ImageView SearchAthlete;
    public static boolean opened = false;
    public static boolean FACEBOOKX = false;
    public static Activity activity;
    public static FragmentManager fm;
    static Context sContextXX;
    public boolean treatMyAccountAsAthlete = false;
    public boolean isAthleteUser = false;
    public Fragment fragment = null;
    ShareDialog shareDialog;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private TextView textViewAppName;
    private Context context;
    private ImageView imageViewLeftArrow, imageViewRightArrow, imageViewSave;
    private int currentScreenVisibleIndex = 0;
    private RelativeLayout mDrawerList, rLayoutForLogoImage;
    private ProgressDialog prgDailog = null;
    private boolean isAgree = false;
    private SharedPreferences pref = null;
    private ImageView imageViewLogo;
    private PackageManager packageManager;
    private String cameraDoesNotSupportMessage = "Camera is not available.";
    private String imagePath = "", folderName = "",
            profile_pic_name = "profile_pic.png";
    private byte[] byte_arr = null;
    private File directory = null;
    private String whichApiCalled;
    /*FOR SUHASINI MAM*/
    private TextView ver, profile;
    private LinearLayout newTraining, newFolder;
    /*END*/
    private boolean exit = false;

    public static void LockDrawer() {
        sActivityXX.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public static void ShareFB() {
        FACEBOOKX = true;
        ShareHashtag shareHashTag = new ShareHashtag.Builder().setHashtag("Hi... i just found an gym app called GodSpeed. \n!its awesome.").build();
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setShareHashtag(shareHashTag)
                    .setContentUrl(Uri.parse("https://godspeed.org"))
                    .build();
            sActivityXX.shareDialog.show(linkContent);  // Show facebook ShareDialog
        }
    }

    public static void CloseDrawer() {
        sActivityXX.mDrawerLayout.closeDrawers();
    }

    public static void UnlockDrawer() {

        sActivityXX.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

    }

    @Override
    protected void onDestroy() {
        try {

        } catch (Exception m) {
        }
        super.onDestroy();
    }

    private void checkDirectory() {
        packageManager = context.getPackageManager();
        folderName = "." + getString(R.string.app_name_for_directory);
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

            directory = new File(
                    android.os.Environment.getExternalStorageDirectory(),
                    folderName);
        } else {
            directory = context.getCacheDir();
        }
        if (!directory.exists()) {
            directory.mkdir();
        }

        imagePath = directory + "/" + profile_pic_name;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] scrcoords = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void yourPublicMethod() {
        FragmentTrainingAndFolder fragment = (FragmentTrainingAndFolder) getFragmentManager().findFragmentById(R.id.content_frame);
        fragment.CallApi();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_coachboard_screen);
        ver = findViewById(R.id.versionName);
        context = this;
        activity = this;
        fm = getFragmentManager();
        shareDialog = new ShareDialog(this);


        treatMyAccountAsAthlete = getIntent().getExtras().getBoolean("treatMyAccountAsAthlete");
        isAthleteUser = getIntent().getExtras().getBoolean("isAthlete");
        LoginScreen.isLogoutCalled = false;
        SingleLayout();
        checkDirectory();
        profile = findViewById(R.id.ProfileTag);


        PasteTF = findViewById(R.id.PasteTF);


        PasteTF.setOnClickListener(view -> {
            FragmentTrainingAndFolder fragment = (FragmentTrainingAndFolder) getFragmentManager().findFragmentById(R.id.content_frame);
            fragment.showToast();
        });
        LayoutForFolder = findViewById(R.id.LayoutForFolder);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        imageViewLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (UtilityClass.getPreferences(context, "debug") != null && UtilityClass.getPreferences(context, "debug").equalsIgnoreCase("true")) {
                    UtilityClass.SetPreferences(context, "debug", "false");
                } else {
                    UtilityClass.SetPreferences(context, "debug", "true");
                }
                ((Activity) context).finishAffinity();
                return false;
            }
        });


        profile.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));


        whichApiCalled = "getClass";

        WebServices webServices = new WebServices();
        webServices.get_Athlete_class(UtilityClass.getPreferences(context, getString(R.string.user_id_tag)), context, CoachNevigationDrawerScreen.this);


        imageViewLogo.setOnClickListener(view -> {
            CoachNevigationDrawerScreen.textViewScreenName.setText(context.getString(R.string.home));
            getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            FragmentTransaction tx = getFragmentManager().beginTransaction();
            if (OnlyAthlete.equalsIgnoreCase("OnlyCoach")) {
                onBackPressed();
                //tx.replace(R.id.content_frame, new CoachBoardFragments());
            } else if (OnlyAthlete.equalsIgnoreCase("OnlyAthlete")) {
                tx.replace(R.id.content_frame, new AthleteProfileFragment());
            }
            tx.commit();
            new Handler().postDelayed(() -> {
                mDrawerLayout.closeDrawer(mDrawerList);
            }, 300);
        });
        // singleLayout();
        pref = context.getSharedPreferences(getString(R.string.app_name), 0);
        // isAgree = pref.getBoolean(getString(R.string.agree), false);
        //////Toast.makeText(context, ""+ GlobalClass.ivar1 , Toast.LENGTH_SHORT).show();

        sActivityXX = this;
        sContextXX = this;

        loadContent();
        setDrawer();
        setupActionBar();

        PackageManager manager = this.getPackageManager();
        try {


            PackageInfo info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
            ver.setText("v" + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        if (!treatMyAccountAsAthlete) {
        } else {
        }

//        prgDailog = new ProgressDialog(context);
//        prgDailog.setMessage(context.getString(R.string.please_wait));
//        prgDailog.setTitle(context.getString(R.string.app_name));
//        prgDailog.setCancelable(false);


//        bundle.putBoolean("isAthlete", true);
//        bundle.putBoolean("treatMyAccountAsAthlete", true);
        imageViewSliderDrawerToggleIcon = findViewById(R.id.imageViewSliderDrawerToggleIcon);
        imageViewSliderDrawerToggleIcon.setOnClickListener(v -> {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawers();
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        imageViewSliderBackIcon = findViewById(R.id.imageViewSliderBackIcon);
        imageViewSliderBackIcon.setOnClickListener(v -> {

            // fm = getFragmentManager();

            if (fm.getBackStackEntryCount() > 0) {
                Log.i("MainActivityVideo", "popping backstack");
                imageViewSliderDrawerToggleIcon.setVisibility(View.VISIBLE);
                imageViewSliderBackIcon.setVisibility(GONE);
                UnlockDrawer();
            }
            onBackPressed();


        });


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.slide_bar_icon, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);

        String fileName = UtilityClass.getPreferences(context, getString(R.string.user_image_tag));
        File wallpaperDirectory = new File(context.getFilesDir() + "/." + getString(R.string.app_name_for_directory));
        File f = new File(wallpaperDirectory, fileName);
        if (f != null && f.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
            if (bitmap != null) {
                bitmap = MaskImage.maskingProfileImage(bitmap, (BitmapFactory.decodeResource(getResources(),
                        R.drawable.logo)));
                // imageViewLogo.setImageBitmap(bitmap);

                // imageViewLogo.setScaleType(ImageView.ScaleType.CENTER);
                //imageViewLogo.setBackgroundResource(R.drawable.logo_frame);
            }
        }
        f = null;


        SelectItem(0);
    }


    @SuppressLint("ResourceAsColor")
    private void SingleLayout() {


        RelativeLayout linearLayout = findViewById(R.id.ListLayout);


        //added LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//      linearLayout.setOrientation(RelativeLayout.VERTICAL);

        //add textView
        MyProfile = new TextView(this);
        MyProfile.setText("My Profile");
        MyProfile.setTextColor(R.color.color_white);
        MyProfile.setTextSize(13);
        //textView.setId(1);
        MyProfile.setLayoutParams(params);
        MyProfile.setVisibility(GONE);
        linearLayout.addView(MyProfile);


    }

    private void loadContent() {
        //textViewAppName = (TextView) findViewById(R.id.textViewAppName);
        //textViewAppName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
    }


    @SuppressLint("ResourceAsColor")
    private void setDrawer() {
        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        int width;

        if (getDeviceTypeMobile) {
            width = (int) (getResources().getDisplayMetrics().widthPixels / 1.5);
        } else {
            width = (int) (getResources().getDisplayMetrics().widthPixels / 2.5);
        }


        mDrawerList = findViewById(R.id.left_drawer);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mDrawerList.getLayoutParams();
        params.width = width;
        mDrawerList.setLayoutParams(params);

        mDrawerLayout.setDrawerShadow(R.color.color_transparant, GravityCompat.START);
        ListView lstMenuList = findViewById(R.id.lst_menu_items);

//        if (!treatMyAccountAsAthlete) {
//            new MenuService(context, lstMenuList, getFragmentManager(), mDrawerLayout, mDrawerList).setMenu();
//        } else {
        new AthleteMenuService(context, lstMenuList, getFragmentManager(), mDrawerLayout, mDrawerList, isAthleteUser, treatMyAccountAsAthlete).setMenu();
        //}


    }

    @Override
    public void onBackPressed() {
        //fm = getFragmentManager();


        if (fm.getBackStackEntryCount() > 0) {

            fm.popBackStack();


            imageViewSliderDrawerToggleIcon.setVisibility(GONE);
            imageViewSliderBackIcon.setVisibility(View.VISIBLE);
            LockDrawer();

            if (FromSCreen.equalsIgnoreCase("WhiteBoard")) {
                screenname = "schedule";
                FromSCreen = "";
                try {
                    SearchAthlete.setVisibility(GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText("SCHEDULE");
                    imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.img_scheduling_week));
                } catch (Exception v) {

                }
            }
            if (screenname.equalsIgnoreCase("classes")) {
                CoachNevigationDrawerScreen.textViewScreenName.setText(context.getString(R.string.video_classes));
                SearchAthlete.setVisibility(GONE);
                imageViewMenuFilter.setVisibility(GONE);
            }


        } else {
            if (GlobalClass.ivar1 == 2 && OnlyAthlete.equalsIgnoreCase("OnlyAthlete")) {


                final custom_alert_class mAlert = new custom_alert_class(context);
                mAlert.setMessage("Are you sure you want to exit?");
                mAlert.setPositveButton("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //adapter.notifyDataSetChanged();
                        finish();
                        mAlert.dismiss();
                    }
                });
                mAlert.setNegativeButton("No", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAlert.dismiss();
                    }
                });
                mAlert.show();
            } else {
//                if(opened){
//                    imageViewMenuFilter.performClick();
//                    return;
//                }
//                if (exit) {


                Bundle bundleX = new Bundle();
                bundleX.putBoolean("isAthlete", isAthleteUser);
                bundleX.putBoolean("treatMyAccountAsAthlete", treatMyAccountAsAthlete);
                startActivity(new Intent(context, ChooseUserTypeScreen.class).putExtras(bundleX));
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
                finish();
                return;
                // }

                // super.onBackPressed();
                // overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
//                } else {
//                    Toast.makeText(this, "Press Back again to Exit.",
//                            Toast.LENGTH_SHORT).show();
//                    exit = true;
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            exit = false;
//                        }
//                    }, 3 * 1000);
//
//                }

                //finish();
            }
        }
        new Handler().postDelayed(() -> {
            Log.e(VolleyLog.TAG, "onBackPressed: " + fm.getBackStackEntryCount() + "   " + CoachNevigationDrawerScreen.FromSCreen);
            if (fm.getBackStackEntryCount() == 0) {

                imageViewSliderDrawerToggleIcon.setVisibility(View.VISIBLE);
                imageViewSliderBackIcon.setVisibility(GONE);
                UnlockDrawer();

                try {
                    imageViewMenuFilter.setVisibility(GONE);
                    LayoutForFolder.setVisibility(GONE);
                    SearchAthlete.setVisibility(GONE);
                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(GONE);

                    if (CoachNevigationDrawerScreen.FromSCreen.equalsIgnoreCase("Shopping")) {
                        CoachNevigationDrawerScreen.textViewScreenName.setText("SHOPPING");
                    }
                    if (screenname.equalsIgnoreCase("training")) {
                        CoachNevigationDrawerScreen.textViewScreenName.setText("TRAINING PROGRAM(s)");
                        SearchAthlete.setVisibility(View.VISIBLE);
                    } else if (screenname.equalsIgnoreCase("schedule")) {
                        imageViewMenuFilter.setVisibility(View.VISIBLE);
                        CoachNevigationDrawerScreen.textViewScreenName.setText(this.getString(R.string.training_schedule));
                    }

                    FromSCreen = "";
                } catch (Exception v) {

                }


            }
            if (fm.getBackStackEntryCount() == 1) {
                if (FromSCreen.equalsIgnoreCase("ProgramPurchaseFragment")) {
                    screenname = "ProgramPurchaseFragment";
                    FromSCreen = "Shopping";
                    arrayofFoldersSelected = new ArrayList<>();
                    try {
                        CoachNevigationDrawerScreen.textViewScreenName.setText("Programs");
                        imageViewMenuFilter.setVisibility(View.VISIBLE);
                        LayoutForFolder.setVisibility(GONE);
                        SearchAthlete.setVisibility(GONE);
                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(GONE);

                        ProgramPurchaseFragment fragment = (ProgramPurchaseFragment) getFragmentManager().findFragmentById(R.id.content_frame);
                        fragment.CallApi();
                    } catch (Exception v) {

                    }
                }
            }
        }, 200);


        // show it
        //alertDialog.show();

    }

    private void setupActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(false);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#313133")));
        final View view = getLayoutInflater().inflate(R.layout.layout_drawer_action_bar, null);
        actionBar.setCustomView(view);


        textViewScreenName = view.findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));


        SearchAthlete = view.findViewById(R.id.SearchAthlete);
        SearchAthlete.setOnClickListener(view12 -> {
            if (screenname.equalsIgnoreCase("coachboard")) {
                CoachBoardFragments fragment = (CoachBoardFragments) getFragmentManager().findFragmentById(R.id.content_frame);
                fragment.toggle();
            } else if (screenname.equalsIgnoreCase("competition_board")) {
                Competition_Board fragment = (Competition_Board) getFragmentManager().findFragmentById(R.id.content_frame);
                fragment.toggle();
            } else if (screenname.equalsIgnoreCase("training")) {
                FragmentTrainingAndFolder fragment = (FragmentTrainingAndFolder) getFragmentManager().findFragmentById(R.id.content_frame);
                fragment.toggle();
            } else if (screenname.equalsIgnoreCase("white_board")) {
                WhiteBoard fragment = (WhiteBoard) getFragmentManager().findFragmentById(R.id.content_frame);
                fragment.toggle();
            } else if (screenname.equalsIgnoreCase("classes")) {
                scheduleCalender fragment = (scheduleCalender) fm.findFragmentById(R.id.content_frame);
                fragment.filterfrom(2);
            }
            mDrawerLayout.closeDrawer(mDrawerList);

            // if(screenname.equalsIgnoreCase(R.string.coac))

        });


        imageViewMenuFilter = view.findViewById(R.id.imageViewMenuFilter);

        imageViewMenuFilter.setOnClickListener(view1 -> {
            if (screenname.equalsIgnoreCase("coachboard")) {
                CoachBoardFragments fragment = (CoachBoardFragments) fm.findFragmentById(R.id.content_frame);
                fragment.filterfrom();
            } else if (screenname.equalsIgnoreCase("competition_board")) {
                CloseDrawer();
            } else if (screenname.equalsIgnoreCase("training")) {
                FragmentTrainingAndFolder fragment = (FragmentTrainingAndFolder) fm.findFragmentById(R.id.content_frame);

            } else if (screenname.equalsIgnoreCase("schedule") || screenname.equalsIgnoreCase("classes")) {
                scheduleCalender fragment = (scheduleCalender) fm.findFragmentById(R.id.content_frame);
                fragment.filterfrom(1);
            } else if (screenname.equalsIgnoreCase("white_board")) {
                try {
                    WhiteBoard fragment = (WhiteBoard) fm.findFragmentById(R.id.content_frame);
                    fragment.ShowWhiteBoardFilter();
                } catch (Exception v) {

                }

            } else if (screenname.equalsIgnoreCase("analytics")) {
                FragmentComparativeAnalytics fragment = (FragmentComparativeAnalytics) getFragmentManager().findFragmentById(R.id.content_frame);
                fragment.toggle();
            } else if (screenname.equalsIgnoreCase("ProgramPurchaseFragment")) {
                ProgramPurchaseFragment fragment = (ProgramPurchaseFragment) getFragmentManager().findFragmentById(R.id.content_frame);
                fragment.toggle();
            }


            mDrawerLayout.closeDrawer(mDrawerList);
        });

        //SearchAthleteText = view.findViewById(R.id.SearchAthleteText);

        imageViewMenuFilter.setVisibility(GONE);
        SearchAthlete.setVisibility(GONE);
        if (textViewScreenName.getText().toString().equalsIgnoreCase(getString(R.string.coachboard))) {
            if (GlobalClass.ivar1 == 1) {
                imageViewMenuFilter.setVisibility(View.VISIBLE);
                SearchAthlete.setVisibility(View.VISIBLE);
            }
        }


        imageViewLeftArrow = view.findViewById(R.id.imageViewLeftArrow);
        imageViewLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentScreenVisibleIndex > 0) {
                    currentScreenVisibleIndex = currentScreenVisibleIndex - 1;
                    SelectItem(currentScreenVisibleIndex);
                }
            }
        });

        imageViewRightArrow = view.findViewById(R.id.imageViewRightArrow);
        imageViewRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentScreenVisibleIndex == 0 && currentScreenVisibleIndex < 3) {
                    currentScreenVisibleIndex = currentScreenVisibleIndex + 1;
                    SelectItem(currentScreenVisibleIndex);
                }
            }
        });

        imageViewAddTrainingProgram = view.findViewById(R.id.imageViewAddTrainingProgram);
        imageViewAddTrainingProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CoachAddExerciseScreen.class));
                overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** CoachAddExerciseScreen *************");


            }
        });

        imageViewSave = view.findViewById(R.id.imageViewSave);
        imageViewSave.setVisibility(GONE);
        imageViewSave.setOnClickListener(v -> {
            WebServices webServices = new WebServices();
        });
    }

    public void myOnResume() {
        this.onResume();
    }


    @Override
    protected void onResume() {
        super.onResume();
        pref = context.getSharedPreferences(getString(R.string.app_name), 0);
        Log.e(VolleyLog.TAG, "onResume: ");
        try {
            if (screenname.equalsIgnoreCase("ProgramPurchaseFragment")) {
                ProgramPurchaseFragment fragment = (ProgramPurchaseFragment) getFragmentManager().findFragmentById(R.id.content_frame);
                fragment.CallApi();
            }
        } catch (Exception m) {
        }

        //AthleteProfileFragment.onresume();
//        isAgree = pref.getBoolean(getString(R.string.agree), false);
//        if(!isAgree && PrivacyAndQuestionnariesScreen.isPrivacyScreenOpened)
//        {
//            onBackPressed();
//            PrivacyAndQuestionnariesScreen.isPrivacyScreenOpened=false;
//        }

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void ApiResponse(String result) {
        if (!result.equalsIgnoreCase("")) {
            parseRequiredData(result);
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

            Log.e("**********", "" + responseMessage);
            if (WebServices.responseCode == 200) {

//                JSONArray jsonDataArray = jsonObj
//                        .getJSONArray("data");
                String usersData = jsonObj
                        .getString("data");
                if (whichApiCalled.equalsIgnoreCase("getClass")) {
                    Gson gson = new Gson();

                    arrayofClasses = new ArrayList<>(Arrays.asList(gson.fromJson(usersData, ArrayofClasses.class)));
                    Log.e(VolleyLog.TAG, "parseRequiredData:arrayofClasses " + arrayofClasses.get(0).getOptions().get(0).getFrom());
                }

//                JSONObject userJson = new JSONObject(usersData);
            } else {
                // UtilityClass.showAlertDailog(context, responseMessage);

            }

        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void SelectItem(int possition) {
        fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                imageViewAddTrainingProgram.setVisibility(GONE);
                if (!treatMyAccountAsAthlete) {
                    textViewScreenName.setText("C O A C H B O A R D");
                    fragment = new CoachBoardFragments();
                } else {
                    textViewScreenName.setText(getString(R.string.home));
                    fragment = new AthleteProfileFragment();
                    AthleteProfileFragment.isCalanderOptionNeedToShow = treatMyAccountAsAthlete;
                }
                break;
            case 1:
                textViewScreenName.setText(getString(R.string.training));
                imageViewAddTrainingProgram.setVisibility(View.VISIBLE);
                fragment = new FragmentTrainingAndFolder("0");
                break;
            case 2:
                imageViewAddTrainingProgram.setVisibility(GONE);
                if (!treatMyAccountAsAthlete) {
                    textViewScreenName.setText(getString(R.string.comparative_analytics));
                    fragment = new FragmentComparativeAnalytics();

                } else {
                    textViewScreenName.setText(getString(R.string.analytics));
                    //fragment = new FragmentAnalytics();
                }
                break;
            case 3:
                imageViewAddTrainingProgram.setVisibility(GONE);
                if (!treatMyAccountAsAthlete) {
                    textViewScreenName.setText(getString(R.string.my_profile));
                    //fragment = new UpdatePofileInfoFragment();
                } else {
                    textViewScreenName.setText(getString(R.string.my_profile));
                    fragment = new AthleteProfileFragment();
                    AthleteProfileFragment.isCalanderOptionNeedToShow = treatMyAccountAsAthlete;
                }
                break;
            case 4:
                if (!treatMyAccountAsAthlete) {
                    textViewScreenName.setText(getString(R.string.coachboard));
                    fragment = new CoachBoardFragments();
                } else {
                    imageViewAddTrainingProgram.setVisibility(GONE);
                    textViewScreenName.setText(getString(R.string.programs));
                    fragment = new ProgramPurchaseFragment();
                }
                break;
            case 5:
                break;
            case 6:
                textViewScreenName.setText(getString(R.string.help));
                fragment = new HelpScreenFragment();
                break;

            default:
                break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (FACEBOOKX) {
            FACEBOOKX = false;
            return;
        }

        imageViewLogo.setEnabled(true);
        byte_arr = null;


        if (resultCode == RESULT_CANCELED) {
            return;
        }
        String path = "";
        Bitmap bitmap = null, bitmapForMask = null;
        if (requestCode == ACTION_REQUEST_GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    bitmapForMask = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    path = saveImage(bitmap);
//                    imageViewLogo.setImageBitmap(bitmap);
//                    editTextSelectPicture.setText(path);

                } catch (IOException e) {
                    e.printStackTrace();
                    //////Toast.makeText(CoachNevigationDrawerScreen.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == ACTION_REQUEST_CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            bitmapForMask = (Bitmap) data.getExtras().get("data");
            path = saveImage(bitmap);
//            imageViewLogo.setImageBitmap(bitmap);

            //////Toast.makeText(CoachNevigationDrawerScreen.this, "Image Saved on path = " + path, Toast.LENGTH_SHORT).show();
        }

        bitmapForMask = MaskImage.maskingProfileImage(bitmapForMask, (BitmapFactory.decodeResource(getResources(),
                R.drawable.logo)));
        //
        // imageViewLogo.setImageBitmap(bitmapForMask);
        imageViewLogo.setScaleType(ImageView.ScaleType.CENTER);
        imageViewLogo.setBackgroundResource(R.drawable.logo_frame);

        if (bitmap != null) {
            File f = new File(imagePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    stream);
            // compress
            byte_arr = stream.toByteArray();

        }
//                            loadProfilePhoto();
        if (byte_arr != null) {
            WebServices webService = new WebServices();
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
            File f = new File(wallpaperDirectory, profile_pic_name);
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

    private String getPath(Uri uri, Intent intent) {

        imagePath = "";
        if (uri != null) {
            Log.e("selectedImagePath", "selectedImagePath" + uri);
            String type = uri.getPathSegments().get(1);

            if (type.compareTo("images") == 0) {
                String[] projection = {MediaStore.Images.Media._ID,
                        MediaStore.Images.Media.DATE_TAKEN,
                        MediaStore.Images.Media.DATA,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.SIZE,
                        MediaStore.Images.Media.MIME_TYPE};
                Cursor cursor = managedQuery(uri, projection, // Which
                        // columns
                        // to return
                        null, // WHERE clause; which rows to return (all
                        // rows)
                        null, // WHERE clause selection arguments (none)
                        null); // Order-by clause (ascending by name)

                cursor.moveToFirst();
                // /*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/**/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*//*//*/*
                int index = 0;
                String[] projection2 = {MediaStore.Images.Media._ID,
                        MediaStore.Images.Media.DATE_TAKEN,
                        MediaStore.Images.Media.DATA,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.SIZE,
                        MediaStore.Images.Media.MIME_TYPE};

                final Cursor cursor23 = managedQuery(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        projection2, // Which
                        // columns
                        // to return
                        null, // WHERE clause; which rows to return (all rows)
                        null, // WHERE clause selection arguments (none)
                        null); // Order-by clause (ascending by name)

                String[] img = {MediaStore.Images.Thumbnails._ID,
                        MediaStore.Images.Thumbnails.IMAGE_ID};

                String selection = MediaStore.Images.Thumbnails.IMAGE_ID + "=?";
                String[] selectionArgs = {"" + cursor.getInt(0) + ""};
                Log.e("typetype", "typetype" + cursor.getInt(0));
                final Cursor imagecursor = managedQuery(
                        MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, img,
                        selection, selectionArgs,
                        MediaStore.Images.Thumbnails.IMAGE_ID + "");

                cursor23.moveToFirst();
                imagecursor.moveToFirst();
                if (imagecursor.getCount() > 0) {
                    Log.e("here we get", " thingssss" + imagecursor.getInt(0));
                    index = imagecursor.getInt(0);
                }

                // /*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*
                Log.e("data ", "data" + cursor.getString(2));

                imagePath = cursor.getString(2);

            } else {
                imagePath = getPathForImage(uri);
            }

        }

        return imagePath;// cursor.getString(column_index);

    }

    public String getPathForImage(Uri uri) {
        String selectedImagePath;
        // 1:MEDIA GALLERY --- query from MediaStore.Images.Media.DATA
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            selectedImagePath = cursor.getString(column_index);
        } else {
            selectedImagePath = null;
        }

        if (selectedImagePath == null) {
            // 2:OI FILE Manager --- call method: uri.getPath()
            selectedImagePath = uri.getPath();
        }
        return selectedImagePath;
    }

    // Decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE = 100;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE
                    && o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    private void loadProfilePhoto() {
        final String imageData = UtilityClass.getImagePreferences(context);
        if (imageData != null) {
            // assert imageData != null;
            // if (!imageData.equals("photo")) {
            final byte[] b = Base64.decode(imageData, Base64.DEFAULT);
            final InputStream is = new ByteArrayInputStream(b);
            final Bitmap bitmap = BitmapFactory.decodeStream(is);
            //  imageViewLogo.setImageBitmap(bitmap);
            // }
        }
    }


    public void removePosition() {
    }
}


//a