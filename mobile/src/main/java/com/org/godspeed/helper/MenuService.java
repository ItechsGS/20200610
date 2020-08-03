package com.org.godspeed.helper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;

import com.facebook.share.widget.ShareDialog;
import com.org.godspeed.R;
import com.org.godspeed.adapter.AppMenuAdapter;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.allOtherClasses.LoginScreen;
import com.org.godspeed.fragments.AthleteProfileFragment;
import com.org.godspeed.fragments.Change_password_Fragment;
import com.org.godspeed.fragments.CoachBoardFragments;
import com.org.godspeed.fragments.Competition_Board;
import com.org.godspeed.fragments.FragmentComparativeAnalytics;
import com.org.godspeed.fragments.FragmentTrainingAndFolder;
import com.org.godspeed.fragments.HelpScreenFragment;
import com.org.godspeed.fragments.WhiteBoard;
import com.org.godspeed.fragments.messageFragment;
import com.org.godspeed.fragments.scheduleCalender;
import com.org.godspeed.loginData.AppMenu;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LayoutForFolder;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.SearchAthlete;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.opened;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.screenname;
import static com.org.godspeed.allOtherClasses.LoginScreen.MEMBERSHIP_STATUS;
import static com.org.godspeed.helper.AthleteMenuService.setTitle;
import static com.org.godspeed.service.SchoolDataService.LoginJson;

public class MenuService {
    public Fragment fragment = null;
    Context mContext;
    ListView lstMenu;
    List<AppMenu> appMenuItems;
    FragmentManager fragmentManager;
    DrawerLayout mDrawerLayout;
    RelativeLayout mDrawerList;
    AppMenuAdapter adapter;
    ShareDialog shareDialog;
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            fragment = null;
            //Boolean ShowLayoutForFolder = false;
            Bundle args = new Bundle();
            switch (appMenuItems.get(position).getMenuIdentifier()) {
                case "COACHBOARD":
                    screenname = "coachboard";
                    LayoutForFolder.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText("C O A C H B O A R D");
                    fragment = new CoachBoardFragments();
                    imageViewMenuFilter.setVisibility(View.VISIBLE);
                    SearchAthlete.setVisibility(View.VISIBLE);
                    break;

                case "SCHEDULE":
                    screenname = "schedule";
                    LayoutForFolder.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.training_schedule));
                    fragment = new scheduleCalender();
                    imageViewMenuFilter.setVisibility(View.VISIBLE);
                    SearchAthlete.setVisibility(View.GONE);
                    args.putString("FromScreen", "schedule");
                    break;
                case "CLASSES":
                    screenname = "classes";
                    LayoutForFolder.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.video_classes));
                    fragment = new scheduleCalender();
                    args.putString("FromScreen", "VideoClass_Category");
                    imageViewMenuFilter.setVisibility(View.VISIBLE);
                    SearchAthlete.setVisibility(VISIBLE);
                    break;
                case "TRAINING BUILDER":

                    screenname = "training";
                    //Bundle bundle = new Bundle();
                    //args.putString("UtilityClass.UtilityClass.TAG", "0");
                    LayoutForFolder.setVisibility(View.VISIBLE);

                    CoachNevigationDrawerScreen.textViewScreenName.setText("TRAINING PROGRAM(s)");
                    //CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.VISIBLE);
                    //mContext.startActivity(new Intent(mContext, AthleteExerciseSetActivity.class));
                    imageViewMenuFilter.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.VISIBLE);
                    fragment = new FragmentTrainingAndFolder("0");
                    break;
                case "COMPETITION BOARD":
                    screenname = "competition_board";
                    imageViewMenuFilter.setVisibility(View.VISIBLE);
                    LayoutForFolder.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.VISIBLE);

                    setTitle("COMPETITION BOARD");
                    fragment = new Competition_Board();
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

                    break;
                case "PERFORMALYTICS":
                    screenname = "analytics";
                    LayoutForFolder.setVisibility(View.GONE);
                    imageViewMenuFilter.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.GONE);

                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.comparative_analytics));
                    fragment = new FragmentComparativeAnalytics();
                    break;
                case "PROFILE":
                    screenname = "profile";
                    LayoutForFolder.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.my_profile));
                    fragment = new AthleteProfileFragment();
                    LayoutForFolder.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.GONE);
                    break;
                case "CHANGE PASSWORD":
                    screenname = "ChangePassWord";
                    imageViewMenuFilter.setVisibility(View.GONE);
                    LayoutForFolder.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.GONE);

                    setTitle(mContext.getString(R.string.ChangePassWord));
                    fragment = new Change_password_Fragment();
                    break;
                case "MESSAGES":
                    setTitle(mContext.getString(R.string.messages));
                    fragment = new messageFragment();
                    imageViewMenuFilter.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.GONE);
                    break;

                case "SHOPPING":
                    setTitle("SHOPPING");
                    fragment = new HelpScreenFragment();
                    args.putString("FromScreen", "ShopPing");
                    imageViewMenuFilter.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.GONE);
                    break;


                case "MORE":
                    screenname = "help";
                    CoachNevigationDrawerScreen.textViewScreenName.setText(mContext.getString(R.string.help));
                    imageViewMenuFilter.setVisibility(View.GONE);
                    LayoutForFolder.setVisibility(View.GONE);
                    args.putString("FromScreen", "Help");
                    SearchAthlete.setVisibility(View.GONE);
                    fragment = new HelpScreenFragment();

                    break;
//                case R.string.id_programs:
//                    screenname = "ProgramPurchaseFragment";
//                    setTitle(mContext.getString(R.string.programs));
//                    imageViewMenuFilter.setVisibility(View.VISIBLE);
//                    LayoutForFolder.setVisibility(View.GONE);
//                    SearchAthlete.setVisibility(View.GONE);
//                    CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
//                    fragment = new ProgramPurchaseFragment();
//                    break;
                case "LOGOUT":
                    screenname = "logout";
                    String prefTag = "ID_Crediental";
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                    prefs.edit().remove(prefTag).apply();
                    mContext.getSharedPreferences(prefTag, 0).edit().clear().apply();
                    LoginScreen.isLogoutCalled = true;
                    SearchAthlete.setVisibility(View.GONE);
                    imageViewMenuFilter.setVisibility(View.GONE);

                    ((CoachNevigationDrawerScreen) mContext).finish();
                    LayoutForFolder.setVisibility(View.GONE);


                    break;
//                case R.string.logout:
//

//                    //Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
//                    //Intent intent = new Intent(MenuService.this, LoginScreen.class)
//                   ((CoachNevigationDrawerScreen)mContext).finish();
////                    if (fragmentManager.isStateSaved() || !fragmentManager.popBackStackImmediate()) {
////                        mContext.finishAfterTransition();
////                    }
//                    break;
            }

            if (MEMBERSHIP_STATUS == 1 && !appMenuItems.get(position).getMenuIdentifier().equalsIgnoreCase("MORE") && appMenuItems.get(position).getMenuIdentifier().equalsIgnoreCase("LOGOUT")) {
                UtilityClass.showAlertDailog(mContext, "Your membership is expired! \n please renew it.");
                return;
            }

            CoachNevigationDrawerScreen.arrayofFoldersSelected = new ArrayList<>();
            CoachNevigationDrawerScreen.PurchaseTP = false;
            CoachNevigationDrawerScreen.SportsIDForTPpurchase = "";

            int usertype = GlobalClass.ivar1;
            if (usertype == 1) {
                if (CoachNevigationDrawerScreen.textViewScreenName.getText().toString().equalsIgnoreCase(mContext.getString(R.string.training))) {
                    LayoutForFolder.setVisibility(View.VISIBLE);
                } else {
                    LayoutForFolder.setVisibility(View.GONE);
                }
            }
//            if(fragment.equals(new CoachBoardFragments())){
//                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
//            }

            opened = false;

            imageViewMenuFilter.setImageDrawable(mContext.getDrawable(R.drawable.filtericon));


            Log.d(UtilityClass.TAG, "fragment: " + fragment);


            if (fragment == null) {
                screenname = "coachboard";
                CoachNevigationDrawerScreen.imageViewAddTrainingProgram.setVisibility(View.GONE);
                CoachNevigationDrawerScreen.textViewScreenName.setText("C O A C H B O A R D");
                fragment = new CoachBoardFragments();

                SearchAthlete.setVisibility(View.VISIBLE);
            } else {

            }

            //  fragment = new scheduleCalender();
            if (fragment != null) {
                fragment.setArguments(args);
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, "0").commit();
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        }
    };

    public MenuService(Context context, ListView lMenu, FragmentManager fManager, DrawerLayout drawerLayout, RelativeLayout drawerList) {
        mContext = context;
        lstMenu = lMenu;
        fragmentManager = fManager;
        mDrawerLayout = drawerLayout;
        mDrawerList = drawerList;
    }

    public void setMenu() {
        appMenuItems = new ArrayList<>();

//        appMenuItems.add(new AppMenu(R.string.id_coachboard, R.drawable.imgcoachboard, "COACHBOARD"));
//
//        appMenuItems.add(new AppMenu(R.string.id_training, R.drawable.training_menu_icon, "TRAINING BUILDER"));
//
//        appMenuItems.add(new AppMenu(R.string.id_analytics, R.drawable.analytics_menu_icon, "PERFORMALYTICS"));
//
//        //appMenuItems.add(new AppMenu(R.string.id_programs, R.drawable.program_icon, "PROGRAMS"));
//
//        appMenuItems.add(new AppMenu(R.string.id_competition_board, R.drawable.imgcompboard, "COMPETITION BOARD"));
//
//        appMenuItems.add(new AppMenu(R.string.id_white_board, R.drawable.img_whiteboard, "WHITE BOARD"));
//
//        appMenuItems.add(new AppMenu(R.string.training_schedule, R.drawable.img_scheduling, "SCHEDULE"));
//
//        appMenuItems.add(new AppMenu(R.string.classes, R.drawable.play_video_icon, "VIDEO CLASSES"));
//
//        appMenuItems.add(new AppMenu(R.string.id_profile, R.drawable.img_profile, "PROFILE"));
//
//        appMenuItems.add(new AppMenu(R.string.ChangePassWord, R.drawable.changepassw, "CHANGE PASSWORD"));
//
//        appMenuItems.add(new AppMenu(R.string.messages, R.drawable.messages , "MESSAGES"));
//
//        //appMenuItems.add(new AppMenu(R.string.history, R.drawable.history , "PURCHASE HISTORY"));
//
//        appMenuItems.add(new AppMenu(R.string.shop, R.drawable.cart_icon, "SHOPPING"));
//
//        appMenuItems.add(new AppMenu(R.string.id_social_media, R.drawable.social_media_icon_menu, "SOCIAL MEDIA"));
//
//        appMenuItems.add(new AppMenu(R.string.id_help, R.drawable.help, "MORE"));

        try {
            //appMenuItems.add(new AppMenu(R.string.id_training, R.drawable.training_menu_icon, "TRAINING"));

            for (int i = 0; i < LoginJson.get(0).getAppMenu().size(); i++) {
                appMenuItems.add(new com.org.godspeed.loginData.AppMenu(LoginJson.get(0).getAppMenu().get(i).getId() + "", LoginJson.get(0).getAppMenu().get(i).getMenuName() + "",
                        LoginJson.get(0).getAppMenu().get(i).getMenuIcon() + "", LoginJson.get(0).getAppMenu().get(i).getMenuIdentifier() + "",
                        LoginJson.get(0).getAppMenu().get(i).getStatus() + "",
                        LoginJson.get(0).getAppMenu().get(i).getMenuSequence() + "",
                        LoginJson.get(0).getAppMenu().get(i).getPosition() + ""));
            }
        } catch (Exception m) {
        }


        appMenuItems.add(new com.org.godspeed.loginData.AppMenu("", "LOGOUT" + "",
                R.drawable.logout, "LOGOUT" + "",
                "",
                "",
                ""));


        adapter = new AppMenuAdapter(mContext, appMenuItems);
        lstMenu.setAdapter(adapter);
        lstMenu.setOnItemClickListener(onItemClickListener);
    }


}