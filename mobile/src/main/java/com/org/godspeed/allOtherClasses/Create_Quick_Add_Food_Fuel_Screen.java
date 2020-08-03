package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;

public class Create_Quick_Add_Food_Fuel_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_food_fuel);
        Context context = this;
        Log.e("Screen", "Create Quick Add Food Fuel");
        String screenName = getIntent().getExtras().getString("screenName");

        TextView textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        textViewScreenName.setText(screenName);


    }
}
