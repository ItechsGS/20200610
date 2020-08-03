package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.screenname;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.utility.GlobalClass.logoutfromAthleteCoach;

public class ChooseUserTypeScreen extends Activity {

    private ImageView imageViewProfilePic, imageViewLeftToggle, imageViewRightToggle;
    private TextView textViewCoachName;
    private TextView textViewCoachTeam;
    private TextView textViewAthlete; //textViewTrainMy,
    private Context context;
    private VideoView vv;
    private boolean isAthleteUser = false;

    private boolean treatMyAccountAsAthlete = false;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_choose_screen);
        context = this;


        initializeView();
        // for download user profile image.
        //downloadUserImage();

        if (SplashScreen.PlayVideo) {
            initializeVideoView();
        }


    }

    private void initializeVideoView() {
        vv = findViewById(R.id.videoViewPlayer);
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                vv.start(); //need to make transition seamless.
            }
        });

        vv.setOnErrorListener((mediaPlayer, i, i1) -> {
            stopPlaying();
            return false;
        });


        if (!playFileRes(getVideoPath(0))) return;
        vv.requestFocus();
        vv.start();
    }

    private boolean playFileRes(String videoPath) {
        if (videoPath == null || "".equalsIgnoreCase(videoPath)) {
            stopPlaying();
            return false;
        } else {
            vv.setVideoURI(Uri.parse(videoPath));

            return true;
        }
    }

    private String getVideoPath(int id) {
        Log.i("Video", "num" + id);

        return ("android.resource://" + getPackageName() + "/raw/video_" + 1);
    }


    public void stopPlaying() {
        vv.setVisibility(View.GONE);
        running = false;
        vv.stopPlayback();

    }

    public void pausePlaying() {
        //rLayoutForTextViewHolder.setVisibility(View.GONE);
        running = false;
        vv.pause();

    }

    public void resumePlayer() {
        //rLayoutForTextViewHolder.setVisibility(View.VISIBLE);
        running = true;
        vv.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        // maskingProfileImage();
        screenname = "coachboard";

        Log.e(VolleyLog.TAG, "LoginScreen.isLogoutCalled : " + LoginScreen.isLogoutCalled);
        if (SplashScreen.PlayVideo) {
            resumePlayer();
        }
//        if (LoginScreen.isLogoutCalled) {
//            LoginScreen.isLogoutCalled = false;
//
//
//            ((Activity) context).startActivity(new Intent(context, LoginScreen.class));
//            ((Activity) context).overridePendingTransition(R.anim.exit, R.anim.enter);
//
//            Log.d(VolleyLog.TAG, "*************** LoginScreen *************");
//            finish();
//        }

    }

    private void initializeView() {

        textViewCoachName = findViewById(R.id.textViewCoachName);
        try {
            if (LoginJson.get(0).getLastName().equalsIgnoreCase("") && LoginJson.get(0).getFirstName().equalsIgnoreCase("")) {
                try {
                    textViewCoachName.setText(LoginJson.get(0).getEmailId().substring(0, LoginJson.get(0).getEmailId().indexOf("@")));
                } catch (Exception x) {
                    textViewCoachName.setText(LoginJson.get(0).getEmailId());
                }
            } else {

                if (LoginJson.get(0).getLastName().equalsIgnoreCase("")) {
                    textViewCoachName.setText(LoginJson.get(0).getFirstName());
                } else if (LoginJson.get(0).getFirstName().equalsIgnoreCase("")) {
                    textViewCoachName.setText(LoginJson.get(0).getLastName());
                } else {
                    textViewCoachName.setText(LoginJson.get(0).getLastName());
                }
            }
        } catch (Exception x) {

        }


        textViewCoachName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        textViewCoachName.setOnClickListener(v -> {
            isAthleteUser = false;
            GlobalClass.ivar1 = 2;
            moveToggleButton();

            logoutfromAthleteCoach = true;
        });

        textViewCoachTeam = findViewById(R.id.textViewTeam);
        textViewCoachTeam.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        textViewCoachTeam.setOnClickListener(v -> {
            isAthleteUser = true;
            GlobalClass.ivar1 = 1;
            moveToggleButton();//logoutfromAthleteCoach = false;
        });

        TextView textViewTrain = findViewById(R.id.textViewTrain);
        textViewTrain.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        imageViewRightToggle = findViewById(R.id.imageViewRightToggle);
        imageViewLeftToggle = findViewById(R.id.imageViewLeftToggle);


        imageViewProfilePic = findViewById(R.id.imageViewProfilePic);

        try {

            if (GlobalClass.userImage.length() != 0) {
                Glide.with(context).load(
                        WebServices.BASE_URL_FOR_IMAGES + GlobalClass.userImage).error(getResources().getDrawable(R.drawable.logo_f))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageViewProfilePic);
            } else {
                Glide.with(context).load(
                        getResources().getDrawable(R.drawable.logo_f))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageViewProfilePic);
            }
        } catch (Exception x) {
            Log.d(UtilityClass.TAG, "initializeView: " + x);
        }


//        rLayoutTrainMyAthelets = (RelativeLayout) findViewById(R.id.rLayoutTrainMyAthelets);

        //rLayoutTrainMyAthelets,
        RelativeLayout rLayoutMain = findViewById(R.id.rLayoutMain);
        rLayoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity();
                overridePendingTransition(R.anim.exit, R.anim.enter);

            }
        });

        RelativeLayout rLayoutTrain = findViewById(R.id.rLayoutTrain);

        moveToggleButton();

    }

    private void moveToggleButton() {
        if (imageViewRightToggle.getVisibility() == View.INVISIBLE) {
            imageViewRightToggle.setVisibility(View.VISIBLE);
            imageViewLeftToggle.setVisibility(View.INVISIBLE);
            textViewCoachTeam.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            textViewCoachName.setTextColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));
            treatMyAccountAsAthlete = false;

            GlobalClass.ivar1 = 1;
        } else {
            imageViewRightToggle.setVisibility(View.INVISIBLE);
            imageViewLeftToggle.setVisibility(View.VISIBLE);
            textViewCoachName.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            textViewCoachTeam.setTextColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));
            treatMyAccountAsAthlete = true;
            GlobalClass.ivar1 = 2;
        }
        Log.e(VolleyLog.TAG, "moveToggleButton: " + GlobalClass.ivar1);
    }

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        super.onKeyUp(keyCode, event);
//
//        if(pressCount == 0) {
//            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//
//                pressCount = 1;
//                mAudioManager.setStreamVolume(
//                        AudioManager.STREAM_MUSIC,
//                        volume_level,
//                        0);
//                return true;
//            }
//            if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//
//                pressCount = 1;
//                mAudioManager.setStreamVolume(
//                        AudioManager.STREAM_MUSIC,
//                        volume_level,
//                        0);
//                return true;
//            }
//
//        }
//
//        Log.e(VolleyLog.TAG, "onKeyUp: " + keyCode);
//        return false;
//    }


    private void startActivity() {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);


        String restoredAgreement = UtilityClass.getPreferences(context, "agree");
        String Showquestion = UtilityClass.getPreferences(context, "SAVED_DATE");


        Bundle bundle = new Bundle();
        bundle.putBoolean("isAthlete", isAthleteUser);
        bundle.putBoolean("treatMyAccountAsAthlete", treatMyAccountAsAthlete);
        if (treatMyAccountAsAthlete) {
            if (GlobalClass.ivar1 == 2) {
                bundle.putBoolean("showQuestions", true);
            } else {
                bundle.putBoolean("showPrivacy", true);
            }


            if (restoredAgreement == null ||

                    !restoredAgreement.equalsIgnoreCase("true") ||

                    Showquestion == null ||

                    !formattedDate.equalsIgnoreCase(Showquestion)) {
                startActivity(new Intent(ChooseUserTypeScreen.this, PrivacyAndQuestionnariesScreen.class).putExtras(bundle));
                overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** PrivacyAndQuestionnariesScreen *************");
            } else {
                startActivity(new Intent(ChooseUserTypeScreen.this, CoachNevigationDrawerScreen.class).putExtras(bundle));
                overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** CoachNevigationDrawerScreen *************");
            }

        } else {


            if (restoredAgreement == null || !restoredAgreement.equalsIgnoreCase("true")) {

                if (GlobalClass.ivar1 == 2) {
                    bundle.putBoolean("showPrivacy", true);
                }

                startActivity(new Intent(ChooseUserTypeScreen.this, PrivacyAndQuestionnariesScreen.class).putExtras(bundle));
                overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** PrivacyAndQuestionnariesScreen *************");
            } else {
                startActivity(new Intent(ChooseUserTypeScreen.this, CoachNevigationDrawerScreen.class).putExtras(bundle));
                overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** CoachNevigationDrawerScreen *************");
            }
        }


        finish();
    }

//    @Override
//    public void onBackPressed() {
//
//        super.onBackPressed();
//        this.overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
//    }

    @Override
    public void onBackPressed() {

//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Are you sure you want to exit?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//        new AlertDialog.Builder(context)
//                .setIcon(getResources().getDrawable(R.drawable.logo_main_small_alert))
//                .setTitle(getResources().getString(R.string.app_name))
//                .setMessage("Are you sure you want to exit?")
//                .setPositiveButton("Yes", (dialog, id) -> finish())
//                .setNegativeButton("No", (dialog, id) -> dialog.cancel())
//                .show();

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
    }

    private void maskingProfileImage() {
//        Canvas canvas = new Canvas();
//        Bitmap mainImage = BitmapFactory.decodeResource(getResources(), R.drawable.demo_photo);//get original image
//         Bitmap maskImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo);//get mask image
//        Bitmap result = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), Bitmap.Config.ARGB_8888);
//        canvas.setBitmap(result);
//        Paint paint = new Paint();
//        paint.setFilterBitmap(false);
//        canvas.drawBitmap(mainImage, 0, 0, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        canvas.drawBitmap(maskImage, 0, 0, paint);
//        paint.setXfermode(null);
        try {
            if (GlobalClass.userImage.length() != 0) {
                Glide.with(context).load(
                        WebServices.BASE_URL_FOR_IMAGES + GlobalClass.userImage).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageViewProfilePic);
            } else {
                Glide.with(context).load(
                        getResources().getDrawable(R.drawable.logo)).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageViewProfilePic);
            }
        } catch (Exception c) {

        }

//        Bitmap original = BitmapFactory.decodeResource(getResources(),
//                R.drawable.demo_photo);
//        Bitmap mask = BitmapFactory.decodeResource(getResourdialogces(),
//                R.drawable.logo);
//        original = Bitmap.createScaledBitmap(original, mask.getWidth(),
//                mask.getHeight(), true);
//
//        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(),
//                Bitmap.Config.ARGB_8888);
//        Canvas mCanvas = new Canvas(result);
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        mCanvas.drawBitmap(original, 0, 0, null);
//        mCanvas.drawBitmap(mask, 0, 0, paint);
//        paint.setXfermode(null);
//        imageViewProfilePic.setImageBitmap(result);
//        imageViewProfilePic.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        imageViewProfilePic.setBackgroundResource(R.drawable.logo);
//        imageViewProfilePic.setImageBitmap(result);
    }


    private void downloadUserImage() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String fileName = UtilityClass.getPreferences(context, getString(R.string.user_image_tag));

                File wallpaperDirectory = new File(
                        context.getFilesDir() + "/." + getString(R.string.app_name_for_directory));
                File f = new File(wallpaperDirectory, fileName);

                WebServices webServices = new WebServices();
                // webServices.downloadImage(context,WebServices.BASE_URL_FOR_IMAGES + fileName, fileName);

                f = null;
            }
        });
    }
}
