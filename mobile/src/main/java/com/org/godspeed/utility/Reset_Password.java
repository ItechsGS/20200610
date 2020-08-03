package com.org.godspeed.utility;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.org.godspeed.fragments.Change_password_Fragment;

public class Reset_Password extends AppCompatActivity implements GodSpeedInterface {
    private Fragment mFragment;
    //   final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // setContentView(R.layout.activity_reset__password);
       /* android.app.Fragment fragment = new Change_password_Fragment();
        //v.setTi(context.getString(R.string.my_profile));
        AthleteMenuService.setTitle("MY PROFILE");
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, thirdFragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
//

//        Fragment fr = new Change_password_Fragment();
//        //fr.setArguments(args);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.content_frame, fr);
//        fragmentTransaction.commit();

        getFragmentManager().beginTransaction().add(android.R.id.content, new Change_password_Fragment()).commit();


    }

    @Override
    public void ApiResponse(String result) {

    }
}
