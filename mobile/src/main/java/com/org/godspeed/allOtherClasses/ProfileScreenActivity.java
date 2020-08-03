package com.org.godspeed.allOtherClasses;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;
import com.org.godspeed.fragments.AthleteProfileFragment;
import com.org.godspeed.utility.GodSpeedInterface;

import static com.org.godspeed.fragments.CoachBoardFragments.AthleteData;

public class ProfileScreenActivity extends Activity implements GodSpeedInterface {

    public static int position = 0;

    public static String coachID = "";
    public static String atheleteID = "";
    public static Boolean fromProfileScreenActivity = false;
    RelativeLayout rLayoutHeader, rcontent;
    ImageView imageViewBackArrow;
    //public static List<SelectedAthleteDEtail> AthleteData;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.profile_screen_activity);
        context = this;
        rLayoutHeader = findViewById(R.id.rLayoutHeader);
        rcontent = findViewById(R.id.rcontent);
        LoginScreen.CoachCheckAthlete = true;
        // position = getIntent().getExtras().getInt("position");
        Log.d(VolleyLog.TAG, "onCreate:position " + position);
        coachID = getIntent().getExtras().getString("coachID");
        atheleteID = getIntent().getExtras().getString("atheleteID");

        for (int iX = 0; iX < AthleteData.size(); iX++) {
            if (AthleteData.get(iX).getUserId().equalsIgnoreCase(atheleteID)) {
                position = iX;
            }
        }


        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            //overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
        });

        //Customer cust=(Customer)intent.getSerializableExtra("bundle");
        // AthleteData = UtilityClass.getPreferences(context,getString(R.string.athlete_level));
//        Gson gson = new Gson();
//                AthleteData = Arrays.asList(gson.fromJson(UtilityClass.getPreferences(context,"AtheleteLevelCoach"), SelectedAthleteDEtail[].class));
        fromProfileScreenActivity = true;
        getFragmentManager().beginTransaction().add(R.id.rcontent, new AthleteProfileFragment())
                .commit();
        //onBackPressed();
    }

    @Override
    public void onBackPressed() {
        fromProfileScreenActivity = false;
        LoginScreen.CoachCheckAthlete = false;
        super.onBackPressed();

        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void ApiResponse(String result) {

    }

    public void transactFragment(boolean reload) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (reload) {
            // getFragmentManager().popBackStack();
        }
        transaction.replace(R.id.rcontent, new AthleteProfileFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        if (true) {
//            getFragmentManager().popBackStack();
//        }
//        transaction.replace(R.id.rcontent, new AthleteProfileFragment());
//        transaction.addToBackStack(null);
//        transaction.commit();
        // transactFragment(true);
        //getFragmentManager().beginTransaction().add(R.id.rcontent, new AthleteProfileFragment()).commit();
        super.onResume();
    }
}
