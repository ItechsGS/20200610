package com.org.godspeed.allOtherClasses;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.HelpScreen.ContactU;
import com.org.godspeed.response_JsonS.HelpScreen.FAQ;
import com.org.godspeed.response_JsonS.HelpScreen.Setting;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static com.org.godspeed.allOtherClasses.LoginScreen.volume_level;
import static com.org.godspeed.utility.UtilityClass.hide;

//import com.org.godspeed.pojooftraining.PhaseArray.Json;

public class HelpScreenData extends AppCompatActivity implements GodSpeedInterface {
    private static final String TAG = "";
    TextView ScreenName, Name, address, contactcall, contactweb, TextviewName, MatricText, ImperialText, TextMatric, TextImperial;
    Context context;
    String screenname;
    ImageView logoImage;
    LinearLayout SettingLLayout, rLayoutMatric, llayoutText, lLayoutImperial;
    RelativeLayout layoutContactus, layoutsetting, layoutFaq, RlayoutSetting;
    Gson gson = new Gson();
    Boolean isChecked = true;
    List<FAQ> faq;
    List<ContactU> ContactUs;
    List<Setting> Settingu;
    AudioManager mAudioManager;
    private ImageView imageViewBackArrow;
    private RecyclerView recyclerView;
    private VideoView videoViewPlayer;
    private String[] FaqArray = null, ContactArray = null, contactId = null, SettingArray = null, SettingId = null, languageArray = null, FaqId = null, sportsIdArray = null;
    private Vector<FAQ> FaqData;
    private String whichApiCalled = "";
    private boolean running = false;
    private String Matric, Imperial;
    private int pressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_help_screen_data);
        ContactUs = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewFaqdata);
        layoutsetting = findViewById(R.id.layoutsetting);
        RlayoutSetting = findViewById(R.id.RlayoutSetting);
        llayoutText = findViewById(R.id.llayoutText);
        rLayoutMatric = findViewById(R.id.rLayoutMatric);
        lLayoutImperial = findViewById(R.id.lLayoutImperial);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        context = this;
        TextImperial = findViewById(R.id.TextImperial);
        TextMatric = findViewById(R.id.TextMatric);
        // Intent intent= getIntent();
        // ImperialText=setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        //MatricText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        TextviewName = findViewById(R.id.TextviewName);
        TextviewName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        screenname = getIntent().getStringExtra("screenname");
        layoutsetting = findViewById(R.id.layoutsetting);
        layoutContactus = findViewById(R.id.layoutContactus);
        layoutFaq = findViewById(R.id.layoutFaq);
        ScreenName = findViewById(R.id.textViewScreenName);
        Name = findViewById(R.id.Name);
        Name.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        address = findViewById(R.id.addresscon);
        address.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        contactcall = findViewById(R.id.contactcall);
        contactcall.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        videoViewPlayer = findViewById(R.id.videoViewPlayer);
        // videoViewPlayer.setOnCompletionListener(this);
        initializeVideoView();
        contactweb = findViewById(R.id.contactweb);
        contactweb.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        ScreenName.setText(screenname);
        ScreenName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(getApplicationContext()));
        //  if (TextImperial==)


        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

        rLayoutMatric.setOnClickListener(v -> {
            rLayoutMatric.setBackgroundColor(getResources().getColor(R.color.Maincolor));
            TextMatric.setTextColor(Color.parseColor("#4b4b4f"));
            lLayoutImperial.setBackground(ContextCompat.getDrawable(context, R.drawable.selectedradious));
            TextImperial.setTextColor(Color.parseColor("#edbb57"));
            UtilityClass.SetPreferences(context, "unit_type", getString(R.string.Matric));

        });


        if (UtilityClass.getPreferences(context, "unit_type") == null) {
            matric();
        } else {
            if (UtilityClass.getPreferences(context, "unit_type").equalsIgnoreCase(getString(R.string.Matric))) {
                matric();
            } else {
                imperial();
            }
        }


        lLayoutImperial.setOnClickListener(v -> {

            imperial();
        });

        if (screenname.equalsIgnoreCase("FAQ's")) {
            whichApiCalled = "Faq/get";
            layoutFaq.setVisibility(View.VISIBLE);
            WebServices webServices = new WebServices();
            webServices.GetFAQ(context, HelpScreenData.this);
        } else if (screenname.equalsIgnoreCase("CONTACT US")) {
            whichApiCalled = "Contact_us/get";
            layoutContactus.setVisibility(View.VISIBLE);
            WebServices webServices = new WebServices();
            webServices.GetContact_us(context, HelpScreenData.this);
        } else if (screenname.equalsIgnoreCase("SETTINGS")) {
            whichApiCalled = "App_settings/get";
            layoutsetting.setVisibility(View.VISIBLE);
            RlayoutSetting.setVisibility(View.VISIBLE);
            WebServices webServices = new WebServices();
            webServices.GetSettings(context, HelpScreenData.this);
        } else if (screenname.equalsIgnoreCase("RATE US")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp&hl=en"));
            startActivity(intent);
            overridePendingTransition(R.anim.exit, R.anim.enter);
            finish();
        }
        hide();


        imageViewBackArrow.setOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        });

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


    private void imperial() {
        lLayoutImperial.setBackgroundColor(getResources().getColor(R.color.Maincolor));
        TextImperial.setTextColor(Color.parseColor("#4b4b4f"));
        rLayoutMatric.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
        TextMatric.setTextColor(Color.parseColor("#edbb57"));
        //textViewIn.setText(getString(R.string.Matric));
        //textViewKg.setText("LB");
        ////Toast.makeText(context, ""+textViewIn+textViewIn, Toast.LENGTH_SHORT).show();
        UtilityClass.SetPreferences(context, "unit_type", getString(R.string.Imperial));
    }

    private void matric() {
        rLayoutMatric.setBackgroundColor(getResources().getColor(R.color.Maincolor));
        TextMatric.setTextColor(Color.parseColor("#4b4b4f"));
        lLayoutImperial.setBackground(ContextCompat.getDrawable(context, R.drawable.selectedradious));
        TextImperial.setTextColor(Color.parseColor("#edbb57"));
        UtilityClass.SetPreferences(context, "unit_type", getString(R.string.Matric));
    }

    private void initializeVideoView() {
        videoViewPlayer = findViewById(R.id.videoViewPlayer);
        //vv.setOnCompletionListener(this);
        videoViewPlayer.setOnCompletionListener(mp -> {
            videoViewPlayer.start(); //need to make transition seamless.
        });


        videoViewPlayer.setOnErrorListener((mediaPlayer, i, i1) -> {
            stopPlaying();
            return false;
        });


        if (!playFileRes(getVideoPath(0))) return;
        videoViewPlayer.requestFocus();
        videoViewPlayer.start();
    }

    private boolean playFileRes(String videoPath) {
        if (videoPath == null || "".equalsIgnoreCase(videoPath)) {
            stopPlaying();
            return false;
        } else {
            videoViewPlayer.setVideoURI(Uri.parse(videoPath));

            return true;
        }
    }

    private String getVideoPath(int id) {
        Log.i("Video", "num" + id);

        return ("android.resource://" + getPackageName() + "/raw/video_" + 1);
    }


    public void stopPlaying() {
        videoViewPlayer.setVisibility(View.GONE);
        running = false;
        videoViewPlayer.stopPlayback();

    }

    public void resumePlayer() {
        //rLayoutForTextViewHolder.setVisibility(View.VISIBLE);
        running = true;
        videoViewPlayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumePlayer();
        if (LoginScreen.isLogoutCalled) {
            LoginScreen.isLogoutCalled = false;
            overridePendingTransition(R.anim.exit, R.anim.enter);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            parseLoginData(result);

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

//                String usersData = jsonObj
//                        .getString("data");

                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");

                //JSONObject userJson = new JSONObject(usersData);
                if (whichApiCalled.equalsIgnoreCase("Faq/get")) {
                    if (jsonDataArray != null && jsonDataArray.length() > 0) {
                        layoutFaq.setVisibility(View.VISIBLE);
                        faq = Arrays.asList(gson.fromJson(jsonDataArray.toString(), FAQ[].class));
                        Log.e(VolleyLog.TAG, "parseLoginData: " + faq.get(0).getAnswer());
                        CustomAdapter customAdapter = new CustomAdapter(FaqData,
                                context);
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(mLayoutManager);
                        DividerItemDecoration divider = new
                                DividerItemDecoration(recyclerView.getContext(),
                                DividerItemDecoration.VERTICAL);
                        divider.setDrawable(
                                ContextCompat.getDrawable(context, R.drawable.line_divider)
                        );
                        recyclerView.addItemDecoration(divider);
                        recyclerView.setAdapter(customAdapter);

                        hide();
                    }

                } else if (whichApiCalled.equalsIgnoreCase("Contact_us/get")) {
                    layoutContactus.setVisibility(View.VISIBLE);
                    for (int i = 0; i < jsonDataArray.length(); i++) {
                        JSONObject dataobj = jsonDataArray.getJSONObject(i);
                        ContactU objSportsData = null;
                        ContactArray = new String[jsonDataArray.length()];
                        contactId = new String[jsonDataArray.length()];
                        objSportsData = new ContactU();
                        JSONObject objJsonSportsData = jsonDataArray.getJSONObject(i);
                        Name.setText(objJsonSportsData.getString("name"));
                        address.setText(objJsonSportsData.getString("address"));
                        contactcall.setText(objJsonSportsData.getString("phone_number"));
                        contactweb.setText(objJsonSportsData.getString("website"));
                        contactweb.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);

                                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                intent.setData(Uri.parse("http://www.godspeed.org"));
                                startActivity(intent);
                                overridePendingTransition(R.anim.exit, R.anim.enter);
                            }
                        });
                        contactcall.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:(205) 989-4110"));
                                startActivity(intent);
                                overridePendingTransition(R.anim.exit, R.anim.enter);
                            }
                        });
                        address.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String Location = "110 Little Valley  Ct";
                                String strUri = "http://maps.google.com/maps?q=loc:" + 33.3500477 + "," + -86.7898898 + " (" + "110 Little Valley  Ct" + ")";
                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                startActivity(intent);

                                overridePendingTransition(R.anim.exit, R.anim.enter);


                            }
                        });

                        ContactArray[i] = objSportsData.getAddress();
                        contactId[i] = objSportsData.getId();
                        ContactUs.add(objSportsData);

                    }
                    hide();
                } else if (whichApiCalled.equalsIgnoreCase("App_settings/get")) {

                }
            } else {
                UtilityClass.showAlertDailog(context, responseMessage);
            }
        } catch (JSONException e) {

            e.printStackTrace();
            Log.e("Error in json parsing", e.getMessage());
        } catch (Exception e) {

            e.printStackTrace();
        }
        hide();

    }


    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
        public int i;
        Context context;
        View view;
        String id;
        private List<FAQ> epic;

        public CustomAdapter(List<FAQ> epic, Context context) {
            //  this.epic = epic;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            //   View v = null;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_data, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
//
            int question = 1;
            question += i;
            myViewHolder.qusetion.setText("Q." + question + " " + faq.get(i).getQuestion());
            myViewHolder.qusetion.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            myViewHolder.answer.setText(faq.get(i).getAnswer());
            myViewHolder.answer.setVisibility(View.GONE);
            myViewHolder.answer.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            myViewHolder.qusetion.setOnClickListener(v -> {
                if (myViewHolder.answer.getVisibility() == View.GONE) {
                    myViewHolder.answer.setVisibility(View.VISIBLE);
                    myViewHolder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));

                } else {
                    myViewHolder.answer.setVisibility(View.GONE);
                    myViewHolder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_yellow));

                }
            });
            // btncoach = "209";
            // myViewHolder.btn1.getLayoutParams().width=300;

        }


        @Override
        public int getItemCount() {
            return faq.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public View view;
            TextView qusetion, answer, faq_question_id, id;
            ImageView arrow;

            public MyViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                arrow = itemView.findViewById(R.id.arrow);
                //=(TextView)itemView.findViewById(R.id.faq_question_id);
                qusetion = itemView.findViewById(R.id.question);
                answer = itemView.findViewById(R.id.answer);
            }
        }

    }


}







