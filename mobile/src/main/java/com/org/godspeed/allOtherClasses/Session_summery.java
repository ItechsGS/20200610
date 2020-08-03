package com.org.godspeed.allOtherClasses;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.VolleyLog;
import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.org.godspeed.R;
import com.org.godspeed.imageTest.MediaActivity;
import com.org.godspeed.imageTest.PhotoEditorActivity;
import com.org.godspeed.utility.CustomTypeface;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;


public class Session_summery extends MediaActivity {
    public static String IMAGE_PATH_FOR_SHARE = "";
    TextView VolumeLifted, vol_rpe, DurationVal, RpeLoadVal, textViewScreenName, Vol_Value;
    TextView RPE, duration, rpeload;
    ImageView LiftInfo, RpeInfo, DurationInfo, RepLoadInfo;
    ImageView imageViewBackArrow, TakeSS;
    SeekBar VolumeSB, RpeSB, DurationSB, RpeLoadSB;
    Context context;
    BubblePopupWindow dialog;
    Intent intent;
    int WeightCount = 1;
    LinearLayout rootContent;
    private float Timing = 1f;
    private int TimingInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_session_summery);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();

        StrictMode.setVmPolicy(builder.build());
        //StrictMode.setVmPolicy(builder.build());

        VolumeSB = findViewById(R.id.VolumeSB);
        rootContent = findViewById(R.id.MainLayoutSs);
        RpeSB = findViewById(R.id.RpeSB);
        DurationSB = findViewById(R.id.DurationSB);
        RpeLoadSB = findViewById(R.id.RpeLoadSB);

        TakeSS = findViewById(R.id.TakeSS);

        RpeLoadSB.setEnabled(false);
        DurationSB.setEnabled(false);
        VolumeSB.setEnabled(false);
        RpeLoadSB.setProgress(1);


        TakeSS.setOnClickListener(view -> {

            String c = "/storage/emulated/0/Pictures/PhotoEditor/IMG_20200323_181114.jpg";
//        Intent intent = new Intent(this, PhotoEditorActivity.class);
//        intent.putExtra("selectedImagePath", selectedImagePath);


//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("image/*");
//
//            File fileX=new File(c);
//            String   path=fileX.getPath();
//            Uri bmpUri = Uri.parse(path);
//            intent.putExtra(Intent.EXTRA_STREAM, bmpUri);
//            intent.putExtra(Intent.EXTRA_TEXT, "See my progress on godspeed.");
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            startActivity(Intent.createChooser(intent, "Share on ....."));

            openMedia();
            //takeScreenshot(ScreenshotType.FULL);
        });


//        Bundle extras = intent.getExtras();
//        WeightCount = extras.getFloat("weight");

        intialiseTextview();
        RpeSB.setMax(10);
        RpeSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                RpeLoadSB.setProgress(i * TimingInt);

                RpeLoadVal.setText((i * TimingInt) + "");

                vol_rpe.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        RpeLoadVal.setText(RpeSB.getProgress() + "");

        // FontsOverride.setDefaultFont(this, "DEFAULT", "AGENCYB.ttf");
    }

    @Override
    protected void onPhotoTaken() {
        Intent intent = new Intent(this, PhotoEditorActivity.class);
        intent.putExtra("selectedImagePath", selectedImagePath);
        startActivity(intent);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (!IMAGE_PATH_FOR_SHARE.equalsIgnoreCase("")) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");

            File file = new File(IMAGE_PATH_FOR_SHARE);
            String path = file.getPath();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri bmpUri = Uri.parse("file://" + path);
            Log.d(VolleyLog.TAG, "onFinish: " + bmpUri);
            intent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            intent.putExtra(Intent.EXTRA_TEXT, "See my progress on godspeed.");

            startActivity(Intent.createChooser(intent, "Share on ....."));

            IMAGE_PATH_FOR_SHARE = "";
        }
    }

    public void showDialog(Context context, int x, int y, String event, String eventData, View view) {

        // dialog = new Dialog(Session_summery.this);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  dialog.setContentView(R.layout.custom_dialog_box);

        //  private BubblePopupWindow dialog;

        View AlertBoxView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_box, null);
        BubbleLinearLayout bubbleView = AlertBoxView.findViewById(R.id.mainRlyofAddset);
        bubbleView.setCornerRadius(30f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(AlertBoxView, bubbleView);

        dialog.showArrowTo(view, BubbleStyle.ArrowDirection.Up);
        dialog.setCancelOnTouch(false);

        AlertBoxView.getLayoutParams().width = dpToPx(200);

//        dialog.setCanceledOnTouchOutside(true);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        //dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.color_transparant));
//        dialog.getWindow().setDimAmount(0);
        //.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#801b5e20")));
        TextView txt = AlertBoxView.findViewById(R.id.EventName);
        txt.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txt.setText(event);


        TextView txtData = AlertBoxView.findViewById(R.id.eventData);
        txtData.setVisibility(View.VISIBLE);
        txtData.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        txtData.setText(eventData);
//
//        WindowManager.LayoutParams wmlp = AlertBoxView.getWindow().getAttributes();
//        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
//        wmlp.x = x;
//        wmlp.y = y;
//        dialog.show();
    }

    private void intialiseTextview() {
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });

        Vol_Value = findViewById(R.id.VolumeValue);
        Vol_Value.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));


        LiftInfo = findViewById(R.id.LiftInfo);
        RpeInfo = findViewById(R.id.RpeInfo);
        DurationInfo = findViewById(R.id.DurationInfo);
        RepLoadInfo = findViewById(R.id.RpeLoadInfo);

        LiftInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                LiftInfo.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                showDialog(context, x, y, "VOLUME LIFTED", "VOLUME LIFTED - Total Poundage and/or distance lifted/completed during a single season of training. ", view);
            }
        });

        RpeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                RpeInfo.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                showDialog(context, x, y, "RPE", "RPE (Rate of Perceived Exertion) - Post workout scale used to measure intensity of training session. The RPE Scale is between 0 - 10 \n0 - Nothing at all \n1 - Just noticeable", view);
            }
        });

        DurationInfo.setOnClickListener(view -> {
            int[] location = new int[2];
            DurationInfo.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            showDialog(context, x, y, "DURATION (MIN)", "Duration - Time it took to complete workout.", view);
        });

        RepLoadInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                RepLoadInfo.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                showDialog(context, x, y, "RPE LOAD", "RPE Load - Rate of Perceived Exertion score multiplied by the Duration time,", view);
            }
        });


        RPE = findViewById(R.id.RPE);
        RPE.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));

        duration = findViewById(R.id.duration);
        duration.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));

        rpeload = findViewById(R.id.rpeload);
        rpeload.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));

        VolumeLifted = findViewById(R.id.VolumeLifted);
        VolumeLifted.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));


        DurationVal = findViewById(R.id.DurationVal);
        DurationVal.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));


        textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));


        RpeLoadVal = findViewById(R.id.RpeLoadVal);
        RpeLoadVal.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));


        vol_rpe = findViewById(R.id.vol_rpe);
        vol_rpe.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try {
                WeightCount = bundle.getInt("weight");
                Timing = bundle.getFloat("Timing");
                TimingInt = Math.round(Timing);
                DurationSB.setProgress(TimingInt);
                DurationVal.setText(TimingInt + "");
                Vol_Value.setText(WeightCount + "");
            } catch (Exception xx) {
                Log.d(VolleyLog.TAG, "onCreate: " + xx);
            }
        }
        RpeLoadSB.setMax(10 * TimingInt);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    //ScreenshotType

    /*  Share Screenshot  */

    public enum ScreenshotType {
        FULL, CUSTOM
    }
}
