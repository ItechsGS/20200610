
package com.org.godspeed.fragments;

/**
 * Created by Tanveer on 8/6/2017.
 */

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.ChooseUserTypeScreen;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;

import org.json.JSONException;
import org.json.JSONObject;

public class Change_password_Fragment extends android.app.Fragment implements GodSpeedInterface {
    public static boolean isAnimationStarted;
    RelativeLayout myv;
    //9214099144
    private ImageView Imageviewcross;
    private TextView textviewLoginWhite, editTextUserName, editTextPassword, editTextConfirm;
    private RelativeLayout rLayoutSubmitYellowButton;
    private String whichApiCalled = "";
    private Context context;
    private String userType = "";
    private ProgressDialog prgDailog = null;
    private String userId = "";
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change, container, false);
        context = getActivity();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        dialog = new Dialog(context);
        //BitmapFactory.decodeFile(bitmap.getPath(data.getData()))
        // Bitmap bitmap = BitmapFactory.decodeFile(context.getFilesDir() +backgroundImageName);
        // imageViewBackground = (ImageView) view.findViewById(R.id.imageViewBackground);
        // if (bitmap != null) {
        //   imageViewBackground.setImageBitmap(bitmap);
        // }
        myv = view.findViewById(R.id.myv);
        userId = UtilityClass.getPreferences(context,
                getString(R.string.user_id_tag));
        initializeTextView(view);
        initializeLayoutView(view);

        return view;
    }

    private void initializeLayoutView(View view) {

        rLayoutSubmitYellowButton = view.findViewById(R.id.rLayoutLoginButton);
        rLayoutSubmitYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //whichApiCalled = "athlete_password_reset";
                //UtilityClass.showWaitDialog(new Dialog(context),context);

                String emailAddress = editTextUserName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String prefTag = "ID_Crediental";
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                prefs.edit().remove(prefTag).apply();


                context.getSharedPreferences(prefTag, 0).edit().clear().apply();

                // String confirmpassword=editTextConfirm.getText().toString().trim();
                if (editTextPassword.getText().toString().equalsIgnoreCase(editTextConfirm.getText().toString())) {
                    whichApiCalled = "athlete_password_reset";
                    WebServices webServices = new WebServices();
                    webServices.ChangePassword(userId, editTextUserName.getText().toString(), editTextPassword.getText().toString(), context, Change_password_Fragment.this);
                } else {
                    UtilityClass.hide();
                    UtilityClass.showAlertDailog(context, getString(R.string.password_confirm_password_invalid));


                }
                //  goToActivityMainDrawer();
            }
        });

    }


    @Override
    public void ApiResponse(String result) {
        Log.e(VolleyLog.TAG, result);
        if (result != null && result.trim().length() > 0) {
            if (whichApiCalled.equalsIgnoreCase("athlete_password_reset")) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    String responseMessage = jsonObj
                            .getString("responseMessage");

                    //UtilityClass.showAlertDailog(context, responseMessage);
                    userType = UtilityClass.getPreferences(context,
                            getString(R.string.user_type_tag));

                    if (WebServices.responseCode == 200) {
                        //UtilityClass.showAlertDailog(context, responseMessage);


                        final custom_alert_class mAlert = new custom_alert_class(context);
                        mAlert.setMessage(responseMessage);
                        mAlert.SetCancel(false);
                        mAlert.OneButton(true);
                        mAlert.setPositveButton("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                goToActivityMainDrawer();
                                //adapter.notifyDataSetChanged();

                                mAlert.dismiss();
                            }
                        });
                        mAlert.show();


                    } else {
                        UtilityClass.showAlertDailog(context, responseMessage);
                    }

                    UtilityClass.hide();

                } catch (JSONException e) {

                    e.printStackTrace();
                } catch (Exception e) {

                    e.printStackTrace();
                }
                //   prgDailog.dismiss();
                whichApiCalled = "";
            }

        }
    }

    private void goToActivityMainDrawer() {
        Bundle bundle = new Bundle();

        if (userType.equalsIgnoreCase("3")) {
            bundle.putBoolean("isAthlete", false);
            bundle.putBoolean("isCoach", true);
            bundle.putBoolean("showQuestion", false);
            GlobalClass.ivar1 = 1;
            startActivity(new Intent(context, ChooseUserTypeScreen.class).putExtras(bundle));
            ((Activity) context).overridePendingTransition(R.anim.exit, R.anim.enter);
            ((Activity) context).finish();
            getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
            Log.d(VolleyLog.TAG, "*************** ChooseUserTypeScreen *************");

            //startActivity(new Intent(context, ChooseUserTypeScreen.class).putExtras(bundle));
            //overridePendingTransition(R.anim.exit, R.anim.enter);

        } else {
            bundle.putBoolean("isAthlete", true);
            bundle.putBoolean("isCoach", false);
            GlobalClass.ivar1 = 2;
            bundle.putBoolean("treatMyAccountAsAthlete", true);
            bundle.putBoolean("showQuestion", true);
            startActivity(new Intent(context, CoachNevigationDrawerScreen.class).putExtras(bundle));
            Log.d(VolleyLog.TAG, "*************** CoachNevigationDrawerScreen *************");

            ((Activity) context).overridePendingTransition(R.anim.exit, R.anim.enter);
            getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
            ((Activity) context).finish();

            //   overridePendingTransition(R.anim.exit, R.anim.enter);

            // finish();
        }
    }

    private void initializeTextView(View view) {
        editTextUserName = (EditText) view.findViewById(R.id.editTextUserName);
        editTextUserName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getActivity().getApplicationContext()));
        editTextUserName.setText(UtilityClass.getPreferences(context, getString(R.string.random_password)));

        Imageviewcross = view.findViewById(R.id.Imageviewcross);
        Imageviewcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivityMainDrawer();

            }
        });
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        editTextPassword.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getActivity().getApplicationContext()));
        editTextPassword.setText(UtilityClass.getPreferences(context, getString(R.string.new_password)));


        editTextConfirm = (EditText) view.findViewById(R.id.editTextConfirm);
        editTextConfirm.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getActivity().getApplicationContext()));
        editTextConfirm.setText(UtilityClass.getPreferences(context, getString(R.string.new_password)));

        textviewLoginWhite = view.findViewById(R.id.textviewLoginWhite);
        textviewLoginWhite.setTypeface(CustomTypeface.load_AGENCYB_Fonts(getActivity().getApplicationContext()));
    }


    //Downloading data asynchronously

}