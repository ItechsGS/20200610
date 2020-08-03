package com.org.godspeed.allOtherClasses;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.LinkagePager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.QuestionnariesClass;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import static com.org.godspeed.utility.UtilityClass.hide;

public class PrivacyAndQuestionnariesScreen extends Activity implements GodSpeedInterface {

    public static boolean isPrivacyScreenOpened = false;
    ViewPager viewPager;
    int getcount = 0;
    int mycount = 1;
    Bundle bundle;
    Boolean ShowQuestions;
    Boolean ShowPrivacy;
    Boolean ShowQuestionsToggle = false;
    Boolean GoNext = false;
    private RelativeLayout rLayoutForPrivacyHeader, rLayoutForQuestionHeader;
    private boolean isAgree = false;
    private TextView textViewPrivacyAgree, Survey, textViewCancel, textViewDone, textViewSkip, textViewPrivacyTitle, textViewQuestion;
    //private ListViewAdapter adapter;
    private String htmlData = "";
    private WebView webViewAgreementContent;
    private ListView listViewQuestionnaries;
    private Context context;
    private CustomPagerAdapter adapterx;
    private JSONArray jsonQuestionArray;
    private Vector<QuestionnariesClass> vectorQuestionnaries;
    private String whichApiCalled = "";
    private boolean isAllQuestionAttempt = false;
    private int mysize;
    private boolean isAthleteUser = false;

    private boolean treatMyAccountAsAthlete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_privacy_policy);

        context = this;
        Log.e("Screen", "Privacy And Questionnaries");
        isAllQuestionAttempt = false;
        isPrivacyScreenOpened = true;
        vectorQuestionnaries = new Vector<QuestionnariesClass>();
        viewPager = findViewById(R.id.viewpager);
        adapterx = new CustomPagerAdapter(this);

        Survey = findViewById(R.id.Survey);

        viewPager = findViewById(R.id.viewpager);

        ShowQuestions = getIntent().getExtras().getBoolean("showQuestions");
        ShowPrivacy = getIntent().getExtras().getBoolean("showPrivacy");


        PagerAdapter adapter = new CustomPagerAdapter(this);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer(true));
        viewPager.setAdapter(adapterx);
        adapter.notifyDataSetChanged();
        String restoredAgreement = UtilityClass.getPreferences(context, "agree");

        if (restoredAgreement != null && restoredAgreement.equalsIgnoreCase("true")) {
            isAgree = true;
        } else {
            if (UtilityClass.isDeviceInternetAvailable(context)) {
                whichApiCalled = "policy";
                WebServices webServices = new WebServices();
                //UtilityClass.showWaitDialog(new Dialog(context),context);
                webServices.getPrivacyPolicy(context, PrivacyAndQuestionnariesScreen.this);
            } else {
                UtilityClass.showAlertDailog(context, getString(R.string.internet_error));
            }
        }

        viewPager.setOffscreenPageLimit(3);
        //A little space between pages
        viewPager.setPageMargin((int) getResources().getDimension(R.dimen.dimen_20));
        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        viewPager.setClipChildren(false);

        getcount = viewPager.getAdapter().getCount();


        Survey.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int haha = position + 1;
                Survey.setText("Readiness Survey " + "(" + haha + "/" + vectorQuestionnaries.size() + ")");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        htmlData = UtilityClass.getPreferences(context, getString(R.string.policyText_tag));

        // isAgree = pref.getBoolean(getString(R.string.agree), false);
        webViewAgreementContent = findViewById(R.id.webViewAgreementContent);
        webViewAgreementContent.getSettings().setJavaScriptEnabled(true);
        webViewAgreementContent.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(
                        "javascript:document.body.style.setProperty(\"color\", \"white\");"
                );
            }
        });

        webViewAgreementContent.setBackgroundColor(Color.parseColor("#3a3a3a"));
        webViewAgreementContent.setVerticalScrollBarEnabled(false);
        webViewAgreementContent.setHorizontalScrollBarEnabled(false);

        textViewPrivacyAgree = findViewById(R.id.textViewPrivacyAgree);

        textViewPrivacyAgree.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        textViewPrivacyAgree.setOnClickListener(view -> {
            ////Toast.makeText(context, "I AGREE", Toast.LENGTH_SHORT).show();
            //SharedPreferences.Editor editor = getSharedPreferences("AGREE_Crediental", MODE_PRIVATE).edit();


            //editor.putString("agree", "");

            //editor.apply();

            if (htmlData != null && htmlData.trim().length() > 0) {
                UtilityClass.SetPreferences(context, "agree", "true");
                if (UtilityClass.isDeviceInternetAvailable(context)) {
                    if (GlobalClass.ivar1 != 1) {
                        whichApiCalled = "question_list";
                        WebServices webServices = new WebServices();
                        webServices.getQuestionaries(context, PrivacyAndQuestionnariesScreen.this);
                    } else {
                        GoNext = true;
                        GoNext();
                        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
                    }

                } else {
                    UtilityClass.showAlertDailog(context, getString(R.string.internet_error));
                }
            } else {
                UtilityClass.showAlertDailog(context, getString(R.string.policy_text_missing));
            }
        });

        textViewCancel = findViewById(R.id.textViewCancel);
        textViewCancel.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        textViewCancel.setOnClickListener(view -> {
            overridePendingTransition(R.anim.exit, R.anim.enter);

            finishAffinity();
        });


        textViewDone = findViewById(R.id.textViewDone);
        textViewSkip = findViewById(R.id.textViewSkip);
        textViewDone.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        if (GlobalClass.ivar2 == 0) {
            textViewSkip.setVisibility(View.INVISIBLE);
        }
        textViewSkip.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        textViewSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                GoNext = true;
                GoNext();

                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        String Showquestion = UtilityClass.getPreferences(context, "SAVED_DATE");

        if (formattedDate.equalsIgnoreCase(Showquestion)) {
            ShowQuestionsToggle = false;
        } else {
            if (GlobalClass.ivar1 != 1) {
                ShowQuestionsToggle = true;
            }
        }


        textViewDone.setOnClickListener(view -> {
            String question_id = "", answer_id = "";
            for (int i = 0; i < vectorQuestionnaries.size(); i++) {
                if (vectorQuestionnaries.get(i).getSelectedAnswer() > 0) {
                    isAllQuestionAttempt = true;
                    if (question_id.trim().length() == 0) {
                        question_id = vectorQuestionnaries.get(i).getQuestionId();
                    } else {
                        question_id = question_id + "," + vectorQuestionnaries.get(i).getQuestionId();
                    }
                    if (vectorQuestionnaries.get(i).getSelectedAnswer() == 1) {
                        if (answer_id.trim().length() == 0) {
                            answer_id = vectorQuestionnaries.get(i).getAnswerOneId();
                        } else {
                            answer_id = answer_id + "," + vectorQuestionnaries.get(i).getAnswerOneId();
                        }
                    } else if (vectorQuestionnaries.get(i).getSelectedAnswer() == 2) {
                        if (answer_id.trim().length() == 0) {
                            answer_id = vectorQuestionnaries.get(i).getAnswerTwoId();
                        } else {
                            answer_id = answer_id + "," + vectorQuestionnaries.get(i).getAnswerTwoId();
                        }
                    } else if (vectorQuestionnaries.get(i).getSelectedAnswer() == 3) {
                        if (answer_id.trim().length() == 0) {
                            answer_id = vectorQuestionnaries.get(i).getAnswerThreeId();
                        } else {
                            answer_id = answer_id + "," + vectorQuestionnaries.get(i).getAnswerThreeId();
                        }
                    } else if (vectorQuestionnaries.get(i).getSelectedAnswer() == 4) {
                        if (answer_id.trim().length() == 0) {
                            answer_id = vectorQuestionnaries.get(i).getAnswerFourId();
                        } else {
                            answer_id = answer_id + "," + vectorQuestionnaries.get(i).getAnswerFourId();
                        }
                    } else if (vectorQuestionnaries.get(i).getSelectedAnswer() == 5) {
                        if (answer_id.trim().length() == 0) {
                            answer_id = vectorQuestionnaries.get(i).getAnswerFiveId();
                        } else {
                            answer_id = answer_id + "," + vectorQuestionnaries.get(i).getAnswerFiveId();
                        }
                    }
                } else {
                    isAllQuestionAttempt = false;
                    break;
                }
            }

            if (isAllQuestionAttempt) {
                whichApiCalled = "submit_answer";
                String userId = UtilityClass.getPreferences(context, getString(R.string.user_id_tag));
                WebServices webServices = new WebServices();
                webServices.submitAnswer(question_id, answer_id, userId, context, PrivacyAndQuestionnariesScreen.this);
            } else {
                UtilityClass.showAlertDailog(context, getString(R.string.all_question_attement_error));
            }

        });
        textViewPrivacyTitle = findViewById(R.id.textViewPrivacyTitle);
        textViewPrivacyTitle.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

//        textViewPrivacyTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_size_grid_item_name));

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewQuestion.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//        textViewQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimensionPixelSize(R.dimen.text_size_grid_item_name));

        rLayoutForPrivacyHeader = findViewById(R.id.rLayoutForPrivacyHeader);
        rLayoutForPrivacyHeader.setVisibility(View.GONE);

        rLayoutForQuestionHeader = findViewById(R.id.rLayoutForQuestionHeader);
        rLayoutForQuestionHeader.setVisibility(View.GONE);

        if (!isAgree) {
            rLayoutForPrivacyHeader.setVisibility(View.VISIBLE);
        } else {
            if (ShowQuestionsToggle) {
                whichApiCalled = "question_list";
                WebServices webServices = new WebServices();
                webServices.getQuestionaries(context, PrivacyAndQuestionnariesScreen.this);
            } else {
                GoNext = true;
                GoNext();
                overridePendingTransition(R.anim.exit, R.anim.enter);
            }

        }
    }


    @Override
    public void ApiResponse(String result) {
        Log.e("Results", result);

        if (whichApiCalled == "policy") {
            if (result != null && result.trim().length() > 0) {
                parsePrivacyPolicyData(result);
            } else {
                if (htmlData != null && htmlData.trim().length() > 0)
                    showDataInWebView();
                else
                    UtilityClass.showAlertDailog(context, getString(R.string.unalbe_load_data));
            }


        } else if (whichApiCalled.equalsIgnoreCase("question_list")) {

            if (result != null && result.trim().length() > 0) {
                UtilityClass.SetPreferences(context, getString(R.string.questionaries), result);
                vectorQuestionnaries.clear();
                parseQuestionaryData(result);
                rLayoutForQuestionHeader.setVisibility(View.VISIBLE);
                rLayoutForPrivacyHeader.setVisibility(View.GONE);

            } else
                UtilityClass.showAlertDailog(context, getString(R.string.unalbe_load_data));


        } else if (whichApiCalled.equalsIgnoreCase("submit_answer")) {
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
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);


                    //Toast.makeText(context,responseMessage,Toast.LENGTH_LONG).show();
                    UtilityClass.SetPreferences(context, "SAVED_DATE", formattedDate);
                    hide();

                    GoNext = true;
                    GoNext();

                    overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
                } else {
                    UtilityClass.showAlertDailog(context, responseMessage);
                }


            } catch (JSONException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }


    }

    private void parseQuestionaryData(String result) {
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

                jsonQuestionArray = jsonObj
                        .getJSONArray("data");
                Log.d(UtilityClass.TAG, "parseQuestionaryData: " + jsonQuestionArray);


                if (jsonQuestionArray != null && jsonQuestionArray.length() > 0) {
                    vectorQuestionnaries = new Vector<QuestionnariesClass>();
                    for (int i = 0; i < jsonQuestionArray.length(); i++) {
                        jsonObj = jsonQuestionArray.getJSONObject(i);
                        Log.d(UtilityClass.TAG, "parseQuestionaryData1: " + jsonObj.getString("category_name"));

                        String option1 = "", option2 = "", option3 = "", option4 = "", option5 = "";
                        String option1Id = "", option2Id = "", option3Id = "", option4Id = "", option5Id = "";
                        JSONArray jsonOptionArray = jsonObj.getJSONArray(getString(R.string.options_tag));
                        if (jsonOptionArray != null && jsonOptionArray.length() > 0) {

                            for (int j = 0; j < jsonOptionArray.length(); j++) {
                                JSONObject jsonObjOptions = jsonOptionArray.getJSONObject(j);
                                if (j == 0) {
                                    option1 = jsonObjOptions.getString(getString(R.string.options_names_tag));
                                    option1Id = jsonObjOptions.getString(getString(R.string.options_id_tag));
                                } else if (j == 1) {
                                    option2 = jsonObjOptions.getString(getString(R.string.options_names_tag));
                                    option2Id = jsonObjOptions.getString(getString(R.string.options_id_tag));
                                } else if (j == 2) {
                                    option3 = jsonObjOptions.getString(getString(R.string.options_names_tag));
                                    option3Id = jsonObjOptions.getString(getString(R.string.options_id_tag));
                                } else if (j == 3) {
                                    option4 = jsonObjOptions.getString(getString(R.string.options_names_tag));
                                    option4Id = jsonObjOptions.getString(getString(R.string.options_id_tag));
                                } else if (j == 4) {
                                    option5 = jsonObjOptions.getString(getString(R.string.options_names_tag));
                                    option5Id = jsonObjOptions.getString(getString(R.string.options_id_tag));
                                }
                            }
                            String question = jsonObj
                                    .getString(getString(R.string.questions_tag));
                            String questionId = jsonObj
                                    .getString(getString(R.string.id_tag));
                            String category_name = jsonObj
                                    .getString("category_name");
                            QuestionnariesClass objQuestions = new QuestionnariesClass(category_name, question, questionId, option1, option2, option3, option4, option5, option1Id, option2Id, option3Id, option4Id, option5Id);
                            vectorQuestionnaries.add(objQuestions);
                            adapterx.notifyDataSetChanged();
                            getcount = vectorQuestionnaries.size();
                            Survey.setText("Readiness Survey " + "(" + mycount + "/" + getcount + ")");
                        }
                    }
                }

            } else {
                UtilityClass.showAlertDailog(context, responseMessage);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        hide();
        mysize = vectorQuestionnaries.size();

    }

    @Override
    public void onBackPressed() {
        if (GoNext) {
            super.onBackPressed();
        }
    }

    private void GoNext() {
        if (GoNext) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isAthlete", getIntent().getExtras().getBoolean("isAthlete"));
            bundle.putBoolean("treatMyAccountAsAthlete", getIntent().getExtras().getBoolean("treatMyAccountAsAthlete"));
            startActivity(new Intent(PrivacyAndQuestionnariesScreen.this, CoachNevigationDrawerScreen.class).putExtras(bundle));
            overridePendingTransition(R.anim.exit, R.anim.enter);
            Log.d(VolleyLog.TAG, "*************** CoachNevigationDrawerScreen *************");
            finish();
        }
    }

    private void parsePrivacyPolicyData(String result) {
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

                String usersData = jsonObj
                        .getString("data");
                JSONObject userJson = new JSONObject(usersData);


                String lastUpdatedDateTime = UtilityClass.getPreferences(context, getString(R.string.latestUpdate_tag));
                if (lastUpdatedDateTime != null && lastUpdatedDateTime.trim().length() > 0) {
                    if (!lastUpdatedDateTime.trim().equalsIgnoreCase(userJson
                            .getString(getString(R.string.latestUpdate_tag)).trim())) {

                        htmlData = userJson
                                .getString(getString(R.string.policyText_tag));
                        UtilityClass.SetPreferences(context,
                                getString(R.string.policyText_tag),
                                htmlData);


                        UtilityClass.SetPreferences(context,
                                getString(R.string.latestUpdate_tag),
                                userJson
                                        .getString(getString(R.string.latestUpdate_tag)));
                    } else {
                        Log.e("Old data showing", "No changes from server for policy text");
                    }
                } else {

                    htmlData = userJson
                            .getString(getString(R.string.policyText_tag));
                    UtilityClass.SetPreferences(context,
                            getString(R.string.policyText_tag),
                            htmlData);

                    UtilityClass.SetPreferences(context,
                            getString(R.string.latestUpdate_tag),
                            userJson
                                    .getString(getString(R.string.latestUpdate_tag)));
                }


            } else {
                UtilityClass.showAlertDailog(context, responseMessage);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        showDataInWebView();
    }

    private void drag(int position) {
        //int currentPosition = viewPager.getCurrentItem();
        adapterx.notifyDataSetChanged();
        viewPager.setAdapter(null);
        viewPager.setAdapter(adapterx);
        Log.e(VolleyLog.TAG, "drag: " + position);

        Handler handler = new Handler();
        handler.post(new PageTurner(handler, viewPager));

        viewPager.setCurrentItem(position, true);
        viewPager.beginFakeDrag();
    }

    private void showDataInWebView() {

        // webViewAgreementContent.loadDataWithBaseURL("",myHtmlString, "text/html", "UTF-8", null);
        String myCustomStyleString = "<style type=\"text/css\">@font-face {font-family: agencyf;src: url(\"file:///android_asset/fonts/agencyf.ttf\")}body,* {font-family: agencyf; font-size: 15px;text-align: justify;}img{max-width:100%;height:auto; border-radius: 8px;}</style>";
        webViewAgreementContent.loadDataWithBaseURL("", myCustomStyleString + htmlData, "text/html", "utf-8", null);
        //webViewAgreementContent.loadDataWithBaseURL("", , "text/html", "UTF-8", "");
        hide();
    }

    public class ViewHolder {
        private TextView textViewQuestion, textViewAnswerOne, textViewAnswerTwo, textViewAnswerThree, textViewAnswerFour, textViewAnswerFive;

        private RelativeLayout rLayoutMain;
    }

    private class PageTurner implements Runnable {
        private Handler handler;
        private ViewPager pager;
        private int count = 10;
        private int offsexX = -10;
        private int offsexY = 2;

        private PageTurner(Handler handler, ViewPager pager) {
            this.handler = handler;
            this.pager = pager;
        }

        @Override
        public void run() {
            try {
                if (pager.isFakeDragging()) {
                    if (count < 30) {
                        count++;
                        pager.fakeDragBy(offsexX * offsexY);
                        handler.postDelayed(this, 10);

                    } else {
                        pager.endFakeDrag();
                    }
                }
            } catch (Exception m) {

            }

        }
    }

    public class CustomPagerAdapter extends PagerAdapter {

        ImageView category_Img;
        LayoutInflater layoutInflater;
        private Context mContext;
        private ViewHolder holder;
        private int selectedAnswerIndex = 0;
        private String selectedAnswers = "";
        private boolean mForceReinstantiateItem = false;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }


        @Override
        public int getCount() {
            int countss = vectorQuestionnaries.size();
            getcount = countss;
            return countss;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return (view == o);
        }

        @Override
        public int getItemPosition(Object object) {
            if (mForceReinstantiateItem) {
                mForceReinstantiateItem = false;
                return POSITION_NONE;
            } else {
                return super.getItemPosition(object);
            }
        }


        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            super.registerDataSetObserver(observer);
        }

        public float getPageWidth(final int position) {
            return 1f;
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            super.unregisterDataSetObserver(observer);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);


            View view = layoutInflater.inflate(R.layout.list_items_questionnaries, container, false);
            TextView categoryText = view.findViewById(R.id.questionCategory);
            TextView textView = view.findViewById(R.id.textViewQuestionOne);
            final TextView answer1 = view.findViewById(R.id.textViewAnswerOne);
            final TextView answer2 = view.findViewById(R.id.textViewAnswerTwo);
            final TextView answer3 = view.findViewById(R.id.textViewAnswerThree);
            final TextView answer4 = view.findViewById(R.id.textViewAnswerFour);
            final TextView answer5 = view.findViewById(R.id.textViewAnswerFive);
            final TextView Survey = view.findViewById(R.id.Survey);
            category_Img = view.findViewById(R.id.categoryImage);


            // view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));
            container.addView(view);


            textView.setText(vectorQuestionnaries.get(position).getQuestions());

            if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("STRENGTH")) {
                Drawable res = getResources().getDrawable(R.drawable.strength1);
                category_Img.setImageDrawable(res);
            }
            if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("MIND")) {
                Drawable res = getResources().getDrawable(R.drawable.mind);
                category_Img.setImageDrawable(res);
            }
            if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("HEART")) {
                Drawable res = getResources().getDrawable(R.drawable.heart_fuel);
                category_Img.setImageDrawable(res);
            }
            if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("REGEN")) {
                Drawable res = getResources().getDrawable(R.drawable.regeny);
                category_Img.setImageDrawable(res);
            }

            categoryText.setText(vectorQuestionnaries.get(position).getcategory_name());
            textView.setText(vectorQuestionnaries.get(position).getQuestions());
            answer1.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            answer2.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            answer3.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            answer4.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            answer5.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            categoryText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            textView.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            answer1.setTextColor(Color.WHITE);
            answer2.setTextColor(Color.WHITE);
            answer3.setTextColor(Color.WHITE);
            answer4.setTextColor(Color.WHITE);
            answer5.setTextColor(Color.WHITE);
            selectedAnswerIndex = vectorQuestionnaries.get(position).getSelectedAnswer();
            Log.d(UtilityClass.TAG, "instantiateItem:1 " + vectorQuestionnaries.get(position).getSelectedAnswer());
            if (selectedAnswerIndex == 1) {
                answer1.setTextColor(Color.BLACK);
                category_Img.setColorFilter(Color.rgb(206, 77, 88));
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("STRENGTH")) {
//
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("MIND")) {
//                    category_Img.setColorFilter(Color.rgb(206, 77, 88));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("HEART")) {
//                    category_Img.setColorFilter(Color.rgb(206, 77, 88));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("REGEN")) {
//                    category_Img.setColorFilter(Color.rgb(206, 77, 88));
//                }
            } else if (selectedAnswerIndex == 2) {
                answer2.setTextColor(Color.BLACK);
                category_Img.setColorFilter(Color.rgb(246, 168, 44));
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("STRENGTH")) {
//
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("MIND")) {
//                    category_Img.setColorFilter(Color.rgb(246, 168, 44));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("HEART")) {
//                    category_Img.setColorFilter(Color.rgb(246, 168, 44));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("REGEN")) {
//                    category_Img.setColorFilter(Color.rgb(246, 168, 44));
//                }

            } else if (selectedAnswerIndex == 3) {
                answer3.setTextColor(Color.BLACK);
                category_Img.setColorFilter(Color.rgb(254, 243, 76));
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("STRENGTH")) {
//
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("MIND")) {
//                    category_Img.setColorFilter(Color.rgb(254, 243, 76));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("HEART")) {
//                    category_Img.setColorFilter(Color.rgb(254, 243, 76));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("REGEN")) {
//                    category_Img.setColorFilter(Color.rgb(254, 243, 76));
//                }

            } else if (selectedAnswerIndex == 4) {
                answer4.setTextColor(Color.BLACK);
                category_Img.setColorFilter(Color.rgb(139, 197, 61));
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("STRENGTH")) {
//
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("MIND")) {
//                    category_Img.setColorFilter(Color.rgb(139, 197, 61));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("HEART")) {
//                    category_Img.setColorFilter(Color.rgb(139, 197, 61));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("REGEN")) {
//                    category_Img.setColorFilter(Color.rgb(139, 197, 61));
//                }

            } else if (selectedAnswerIndex == 5) {
                answer5.setTextColor(Color.BLACK);
                category_Img.setColorFilter(Color.rgb(92, 163, 243));
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("STRENGTH")) {
//
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("MIND")) {
//                    category_Img.setColorFilter(Color.rgb(92, 163, 243));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("HEART")) {
//                    category_Img.setColorFilter(Color.rgb(92, 163, 243));
//                }
//                if (vectorQuestionnaries.get(position).getcategory_name().equalsIgnoreCase("REGEN")) {
//                    category_Img.setColorFilter(Color.rgb(92, 163, 243));
//                }

            }

            answer1.setText(vectorQuestionnaries.get(position).getAnswerOne());
            if (answer1.getText().toString().trim().length() == 0) {
                answer1.setVisibility(View.INVISIBLE);
                answer1.setClickable(false);
            } else {
                answer1.setVisibility(View.VISIBLE);
                answer1.setClickable(true);
            }

            answer1.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    //ImageView lineColorCode = (ImageView)convertView.findViewById(R.id);
                    category_Img.setColorFilter(ContextCompat.getColor(mContext, R.color.option_1_color));
                    if (vectorQuestionnaries.get(position).getSelectedAnswer() != 1) {
                        vectorQuestionnaries.get(position).setSelectedAnswer(1);
                        category_Img.setColorFilter(R.color.option_1_color);
                        drag(position);
                    } else {
                        vectorQuestionnaries.get(position).setSelectedAnswer(0);
                    }
                }
            });


            answer2.setText(vectorQuestionnaries.get(position).getAnswerTwo());
            if (answer2.getText().toString().trim().length() == 0) {
                answer2.setVisibility(View.INVISIBLE);
                answer2.setClickable(false);
            } else {
                answer2.setVisibility(View.VISIBLE);
                answer2.setClickable(true);
            }
            answer2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (vectorQuestionnaries.get(position).getSelectedAnswer() != 2) {
                        vectorQuestionnaries.get(position).setSelectedAnswer(2);
                        drag(position);
                    } else {
                        vectorQuestionnaries.get(position).setSelectedAnswer(0);
                    }
                }

            });


            answer3.setText(vectorQuestionnaries.get(position).getAnswerThree());
            if (answer3.getText().toString().trim().length() == 0) {
                answer3.setVisibility(View.INVISIBLE);
                answer3.setClickable(false);
            } else {
                answer3.setVisibility(View.VISIBLE);
                answer3.setClickable(true);
            }

            answer3.setOnClickListener(view1 -> {

                //viewPager.arrowScroll(View.FOCUS_FORWARD);

                // category_Img.setColorFilter(ContextCompat.getColor(context, R.color.option_3_color), android.graphics.PorterDuff.Mode.MULTIPLY);
                if (vectorQuestionnaries.get(position).getSelectedAnswer() != 3) {
                    vectorQuestionnaries.get(position).setSelectedAnswer(3);
                    drag(position);
                } else {
                    vectorQuestionnaries.get(position).setSelectedAnswer(0);
                }
            });

            answer4.setText(vectorQuestionnaries.get(position).getAnswerFour());
            if (answer4.getText().toString().trim().length() == 0) {
                answer4.setVisibility(View.INVISIBLE);
                answer4.setClickable(false);
            } else {
                answer4.setVisibility(View.VISIBLE);
                answer4.setClickable(true);
            }
            answer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (vectorQuestionnaries.get(position).getSelectedAnswer() != 4) {
                        vectorQuestionnaries.get(position).setSelectedAnswer(4);
                        drag(position);
                    } else {
                        vectorQuestionnaries.get(position).setSelectedAnswer(0);
                    }
                }
            });

            answer5.setText(vectorQuestionnaries.get(position).getAnswerFive());
            if (answer5.getText().toString().trim().length() == 0) {
                answer5.setVisibility(View.INVISIBLE);
                answer5.setClickable(false);
            } else {
                answer5.setVisibility(View.VISIBLE);
                answer5.setClickable(true);
            }
            answer5.setOnClickListener(view12 -> {
                if (vectorQuestionnaries.get(position).getSelectedAnswer() != 5) {
                    vectorQuestionnaries.get(position).setSelectedAnswer(5);
                    drag(position);
                } else {
                    vectorQuestionnaries.get(position).setSelectedAnswer(0);
                }
            });

            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }


    }


    public class ZoomOutPageTransformer implements LinkagePager.PageTransformer, ViewPager.PageTransformer {
        private final float MIN_ALPHA = 0.7f;
        private float MIN_SCALE = 1f;


        public ZoomOutPageTransformer(boolean isZoomEnable) {
            if (isZoomEnable) {
                MIN_SCALE = 0.85f;
            } else {
                MIN_SCALE = 1f;
            }
        }

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            float vertMargin = pageHeight * (1 - MIN_SCALE) / 2;
            float horzMargin = pageWidth * (1 - MIN_SCALE) / 2;
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(MIN_ALPHA);
                view.setTranslationX(horzMargin - vertMargin / 2);


            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                vertMargin = pageHeight * (1 - scaleFactor) / 2;
                horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(MIN_ALPHA);
                view.setTranslationX(-horzMargin + vertMargin / 2);

            }
        }
    }
}
