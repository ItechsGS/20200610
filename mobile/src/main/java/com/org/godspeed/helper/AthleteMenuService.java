package com.org.godspeed.helper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.VolleyLog;
import com.facebook.share.widget.ShareDialog;
import com.org.godspeed.R;
import com.org.godspeed.adapter.AppMenuAdapter;
import com.org.godspeed.allOtherClasses.AthleteExerciseSetActivity;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.allOtherClasses.LoginScreen;
import com.org.godspeed.fragments.AthleteProfileFragment;
import com.org.godspeed.fragments.Change_password_Fragment;
import com.org.godspeed.fragments.CoachBoardFragments;
import com.org.godspeed.fragments.Competition_Board;
import com.org.godspeed.fragments.FragmentComparativeAnalytics;
import com.org.godspeed.fragments.FragmentTrainingAndFolder;
import com.org.godspeed.fragments.HelpScreenFragment;
import com.org.godspeed.fragments.Subscription;
import com.org.godspeed.fragments.WhiteBoard;
import com.org.godspeed.fragments.messageFragment;
import com.org.godspeed.fragments.scheduleCalender;
import com.org.godspeed.loginData.AppMenu;
import com.org.godspeed.utility.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LayoutForFolder;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.SearchAthlete;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.screenname;
import static com.org.godspeed.allOtherClasses.LoginScreen.MEMBERSHIP_STATUS;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.utility.GlobalClass.logoutfromAthleteCoach;

public class AthleteMenuService {
    public Fragment fragment = null;
    Context mContext;
    ListView lstMenu;
    List<AppMenu> appMenuItems;
    FragmentManager fragmentManager;
    DrawerLayout mDrawerLayout;
    RelativeLayout mDrawerList;
    Bundle bundle;
    boolean treatMyAccountAsAthlete;
    ShareDialog shareDialog;
    boolean isAthleteUser;
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            fragment = null;
            Bundle args = new Bundle();
            imageViewMenuFilter.setVisibility(GONE);
            LayoutForFolder.setVisibility(GONE);
            SearchAthlete.setVisibility(GONE);
            switch (appMenuItems.get(position).getMenuIdentifier()) {

                case "TRAINING BUILDER":

                    if (treatMyAccountAsAthlete) {
                        setTitle(LoginJson.get(0).getAppMenu().get(position).getMenuName());
                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(VISIBLE);
                        mContext.startActivity(new Intent(mContext, AthleteExerciseSetActivity.class));
                        Log.d(VolleyLog.TAG, "*************** AthleteExerciseSetActivity *************");

                        ((Activity) mContext).overridePendingTransition(R.anim.exit, R.anim.enter);
                        mDrawerLayout.closeDrawer(mDrawerList);
                    } else {
                        screenname = "training";
                        //Bundle bundle = new Bundle();
                        //args.putString("UtilityClass.UtilityClass.TAG", "0");
                        LayoutForFolder.setVisibility(GONE);

                        CoachNevigationDrawerScreen.textViewScreenName.setText("TRAINING PROGRAM(s)");
                        //CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.VISIBLE);
                        //mContext.startActivity(new Intent(mContext, AthleteExerciseSetActivity.class));
                        imageViewMenuFilter.setVisibility(View.GONE);
                        SearchAthlete.setVisibility(View.VISIBLE);
                        fragment = new FragmentTrainingAndFolder("0");
                    }
                    LayoutForFolder.setVisibility(GONE);
                    setTitle(appMenuItems.get(position).getMenuName());
                    // fragment = new FragmentTrainingAndFolder();
                    break;
                case "PERFORMALYTICS":
                    if (treatMyAccountAsAthlete) {
                        setTitle(LoginJson.get(0).getAppMenu().get(position).getMenuName());
                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(GONE);
                        fragment = new FragmentComparativeAnalytics();
                        mDrawerLayout.closeDrawer(mDrawerList);
                        // fragment = new FragmentTrainingAndFolder();

                    } else {
                        screenname = "analytics";
                        LayoutForFolder.setVisibility(View.GONE);
                        imageViewMenuFilter.setVisibility(View.GONE);
                        SearchAthlete.setVisibility(View.GONE);

                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                        CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.comparative_analytics));
                        fragment = new FragmentComparativeAnalytics();
                    }
                    setTitle(appMenuItems.get(position).getMenuName());
                    break;

                case "SCHEDULE":
                    if (treatMyAccountAsAthlete) {
                        screenname = "schedule";
                        LayoutForFolder.setVisibility(GONE);
                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(GONE);
                        setTitle(LoginJson.get(0).getAppMenu().get(position).getMenuName());
                        fragment = new scheduleCalender();
                        args.putString("FromScreen", "schedule");
                        imageViewMenuFilter.setVisibility(VISIBLE);
                        SearchAthlete.setVisibility(GONE);
                        // fragment = new FragmentTrainingAndFolder();

                    } else {

                        screenname = "schedule";
                        LayoutForFolder.setVisibility(View.GONE);
                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                        fragment = new scheduleCalender();
                        imageViewMenuFilter.setVisibility(View.VISIBLE);
                        SearchAthlete.setVisibility(View.GONE);
                        args.putString("FromScreen", "schedule");
                    }
                    setTitle(appMenuItems.get(position).getMenuName());
                    break;

                case "CLASSES":
                    if (treatMyAccountAsAthlete) {
                        screenname = "classes";
                        LayoutForFolder.setVisibility(View.GONE);
                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                        //CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.video_classes));
                        CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.video_classes));
                        fragment = new scheduleCalender();
                        args.putString("FromScreen", "VideoClass_Category");
                        imageViewMenuFilter.setVisibility(View.VISIBLE);
                        SearchAthlete.setVisibility(VISIBLE);
                    } else {
                        screenname = "classes";
                        LayoutForFolder.setVisibility(View.GONE);
                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                        CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.video_classes));
                        fragment = new scheduleCalender();
                        args.putString("FromScreen", "VideoClass_Category");
                        imageViewMenuFilter.setVisibility(View.VISIBLE);
                        SearchAthlete.setVisibility(VISIBLE);
                    }
                    CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.video_classes));
                    SearchAthlete.setVisibility(GONE);
                    imageViewMenuFilter.setVisibility(GONE);
                    break;
                case "SUBSCRIPTION":
                    // if(treatMyAccountAsAthlete) {
                    screenname = "suns";
                    LayoutForFolder.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                    //CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.video_classes));
                    CoachNevigationDrawerScreen.textViewScreenName.setText(appMenuItems.get(position).getMenuName());
                    fragment = new Subscription();
                    //args.putString("FromScreen", "VideoClass_Category");
                    //imageViewMenuFilter.setVisibility(View.VISIBLE);
                    //SearchAthlete.setVisibility(VISIBLE);
                    // }
                    CoachNevigationDrawerScreen.textViewScreenName.setText(appMenuItems.get(position).getMenuName());
                    SearchAthlete.setVisibility(GONE);
                    imageViewMenuFilter.setVisibility(GONE);
                    break;

                case "CHANGE PASSWORD":
                    if (treatMyAccountAsAthlete) {
                        setTitle(LoginJson.get(0).getAppMenu().get(position).getMenuName());
                        fragment = new Change_password_Fragment();
                    } else {
                        screenname = "ChangePassWord";
                        imageViewMenuFilter.setVisibility(View.GONE);
                        LayoutForFolder.setVisibility(View.GONE);
                        SearchAthlete.setVisibility(View.GONE);

                        setTitle(mContext.getString(R.string.ChangePassWord));
                        fragment = new Change_password_Fragment();
                    }
                    setTitle(appMenuItems.get(position).getMenuName());
                    break;

                case "PROFILE":
                    //setTitle(mContext.getString(R.string.my_profile));
                    if (treatMyAccountAsAthlete) {
                        setTitle(LoginJson.get(0).getAppMenu().get(position).getMenuName());
                        fragment = new AthleteProfileFragment();
                        AthleteProfileFragment.isCalanderOptionNeedToShow = true;
                    } else {
                        screenname = "profile";
                        LayoutForFolder.setVisibility(View.GONE);
                        CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                        CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.my_profile));
                        fragment = new AthleteProfileFragment();
                        LayoutForFolder.setVisibility(View.GONE);
                        SearchAthlete.setVisibility(View.GONE);
                    }
                    setTitle(appMenuItems.get(position).getMenuName());
                    break;


                case "MESSAGES":
                    if (treatMyAccountAsAthlete) {
                        //setTitle(mContext.getString(R.string.messages));
                        fragment = new messageFragment();
                    } else {
                        fragment = new messageFragment();
                        imageViewMenuFilter.setVisibility(View.GONE);
                        SearchAthlete.setVisibility(View.GONE);
                    }
                    setTitle(appMenuItems.get(position).getMenuName());
                    break;


                case "COACHBOARD":
                    screenname = "coachboard";
                    LayoutForFolder.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText("C O A C H B O A R D");
                    fragment = new CoachBoardFragments();
                    imageViewMenuFilter.setVisibility(View.VISIBLE);
                    SearchAthlete.setVisibility(View.VISIBLE);
                    break;


                case "COMPETITION BOARD":
                    screenname = "competition_board";
                    imageViewMenuFilter.setVisibility(View.VISIBLE);
                    LayoutForFolder.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.VISIBLE);

                    fragment = new Competition_Board();
                    setTitle(appMenuItems.get(position).getMenuName());
                    break;

                case "WHITEBOARD":
                    screenname = "white_board";
                    LayoutForFolder.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText("WHITE BOARD");
                    //CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.VISIBLE);
                    //mContext.startActivity(new Intent(mContext, AthleteExerciseSetActivity.class));
                    imageViewMenuFilter.setVisibility(View.VISIBLE);
                    SearchAthlete.setVisibility(View.VISIBLE);
                    fragment = new WhiteBoard();
                    setTitle(appMenuItems.get(position).getMenuName());

                    break;


                case "SHOPPING":
                    setTitle("SHOPPING");
                    fragment = new HelpScreenFragment();
                    args.putString("FromScreen", "ShopPing");
                    imageViewMenuFilter.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.GONE);
                    setTitle(appMenuItems.get(position).getMenuName());
                    break;

                case "MORE":
                    screenname = "help";
                    CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.help));
                    imageViewMenuFilter.setVisibility(View.GONE);
                    LayoutForFolder.setVisibility(View.GONE);
                    args.putString("FromScreen", "Help");
                    SearchAthlete.setVisibility(View.GONE);
                    fragment = new HelpScreenFragment();
                    setTitle(appMenuItems.get(position).getMenuName());

                    break;


                case "LOGOUT":
                    String prefTag = "ID_Crediental";
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                    prefs.edit().remove(prefTag).apply();

                    //mContext.finish();
                    mContext.getSharedPreferences(prefTag, 0).edit().clear().apply();

                    if (logoutfromAthleteCoach) {
                        LoginScreen.isLogoutCalled = true;
                        mContext.startActivity(new Intent(mContext, LoginScreen.class));
                        ((Activity) mContext).overridePendingTransition(R.anim.exit, R.anim.enter);


                        ((CoachNevigationDrawerScreen) mContext).finish();
                    } else {
                        LoginScreen.isLogoutCalled = false;
                        ((CoachNevigationDrawerScreen) mContext).finish();

                        mContext.startActivity(new Intent(mContext, LoginScreen.class));
                        Log.d(VolleyLog.TAG, "*************** LoginScreen *************");

                        ((Activity) mContext).overridePendingTransition(R.anim.exit, R.anim.enter);

                        ((CoachNevigationDrawerScreen) mContext).finish();
                    }

                    break;

            }
            RelativeLayout relativeLayout = new RelativeLayout(mContext);


            if (MEMBERSHIP_STATUS == 1 && !appMenuItems.get(position).getMenuIdentifier().equalsIgnoreCase("MORE") && !appMenuItems.get(position).getMenuIdentifier().equalsIgnoreCase("LOGOUT")) {
                UtilityClass.showAlertDailog(mContext, "Your membership is expired! \n please renew it.");
                return;
            }

            if (fragment != null) {
                fragment.setArguments(args);
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                mDrawerLayout.closeDrawer(mDrawerList);
            }


        }
    };
    private Object ViewHolder;

    public AthleteMenuService(Context context, ListView lMenu, FragmentManager fManager, DrawerLayout drawerLayout, RelativeLayout drawerList, boolean isAthleteUser, boolean treatMyAccountAsAthlete) {
        mContext = context;
        lstMenu = lMenu;
        fragmentManager = fManager;
        mDrawerLayout = drawerLayout;
        this.isAthleteUser = isAthleteUser;
        this.treatMyAccountAsAthlete = treatMyAccountAsAthlete;
        mDrawerList = drawerList;
    }

    //    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
//        @Override
//        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//            if (appMenuItems.get(position).getMenuIdentifier().equalsIgnoreCase("LOGOUT")) {
//                if (UtilityClass.getPreferences(mContext, "GS_DEBUG_BUILD") != null && UtilityClass.getPreferences(mContext, "GS_DEBUG_BUILD").equalsIgnoreCase("TRUE")) {
//                    UtilityClass.SetPreferences(mContext, "GS_DEBUG_BUILD", "FALSE");
//                } else {
//                    UtilityClass.SetPreferences(mContext, "GS_DEBUG_BUILD", "TRUE");
//                }
//                ((Activity) mContext).finish();
//            }
//            return false;
//
//        }
//    };
    public static void setTitle(String title) {
        CoachNevigationDrawerScreen.textViewScreenName.setText(title);
    }

    public void setMenu() {
        appMenuItems = new ArrayList<AppMenu>();


//        appMenuItems.add(new AppMenu(R.string.id_analytics, R.drawable.img_analytics, "PERFORMALYTICS"));
//
//        appMenuItems.add( new AppMenu(R.string.id_analytics, R.drawable.login_transparant_layer, "My Profile"));
//
//        appMenuItems.add(new AppMenu(R.string.id_profile, R.drawable.img_profile, "PROFILE"));
//
//        appMenuItems.add(new AppMenu(R.string.id_account, R.drawable.accounticon, "ACCOUNT"));
//
//
//        appMenuItems.add(new AppMenu(R.string.training_schedule, R.drawable.img_scheduling, "SCHEDULE"));
//
//
//        appMenuItems.add(new AppMenu(R.string.classes, R.drawable.play_video_icon, "VIDEO CLASSES"));
//
//
//        //appMenuItems.add(new AppMenu(R.string.id_programs, R.drawable.program_icon, "PROGRAMS"));
//
//
//
//        appMenuItems.add(new AppMenu(R.string.ChangePassWord, R.drawable.changepassw, "CHANGE PASSWORD"));
//
//        appMenuItems.add(new AppMenu(R.string.messages, R.drawable.messages, "MESSAGES"));
//
//        //appMenuItems.add(new AppMenu(R.string.history, R.drawable.history , "PURCHASE HISTORY"));
//
//        appMenuItems.add(new AppMenu(R.string.shop, R.drawable.cart_icon, "SHOPPING"));
//
//
//
//        appMenuItems.add(new AppMenu(R.string.id_coachboard, R.drawable.social_media_icon_menu, "SOCIAL MEDIA"));
//
//        appMenuItems.add(new AppMenu(R.string.id_help, R.drawable.help, "MORE"));
        try {
            //appMenuItems.add(new AppMenu(R.string.id_training, R.drawable.training_menu_icon, "TRAINING"));

            for (int i = 0; i < LoginJson.get(0).getAppMenu().size(); i++) {
                appMenuItems.add(new AppMenu(LoginJson.get(0).getAppMenu().get(i).getId() + "", LoginJson.get(0).getAppMenu().get(i).getMenuName() + "",
                        LoginJson.get(0).getAppMenu().get(i).getMenuIcon() + "", LoginJson.get(0).getAppMenu().get(i).getMenuIdentifier() + "",
                        LoginJson.get(0).getAppMenu().get(i).getStatus() + "",
                        LoginJson.get(0).getAppMenu().get(i).getMenuSequence() + "",
                        LoginJson.get(0).getAppMenu().get(i).getPosition() + ""));
            }
        } catch (Exception m) {
        }


        appMenuItems.add(new AppMenu("", "LOGOUT" + "",
                R.drawable.logout, "LOGOUT" + "",
                "",
                "",
                ""));
        AppMenuAdapter adapter = new AppMenuAdapter(mContext, appMenuItems);


        lstMenu.setAdapter(adapter);
        lstMenu.setOnItemClickListener(onItemClickListener);

        //lstMenu.setOnLongClickListener((View.OnLongClickListener) onItemLongClickListener);
    }
}