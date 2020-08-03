package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail.ABR;
import com.org.godspeed.response_JsonS.AbrPojo.getAbrNames.GetAbrName;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.RefreshTrainingPhase;
import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.getAbrNames;
import static com.org.godspeed.utility.UtilityClass.hide;

public class AbrActivity extends Activity implements GodSpeedInterface {
    RecyclerView abrRecyclerView;
    Context context;
    DoseDetail recyclerViewDoseDetail;
    step5Adapter recyclerViewDoseData;
    WebServices webServices = new WebServices();
    ImageView imageViewBackArrow, imageViewSave;
    String whichapicAlled = "";
    ArrayList<String> SetList = new ArrayList<>();
    List<GetAbrName> ForsearchArrayX = new ArrayList<>();
    Boolean back = false;
    private List<ABR> abrs;
    private String id;
    private String SelectedABR = "";
    private String SelectedABRFORSHOW = "";
    private String SELECTED_DOSE = "";
    private Boolean bISSetExist = false, bISRepExist = false, bISWeightExist = false, bISHeightDistanceExist = false, bISTimeExist = false;
    private Boolean OPENED = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_abr);
        context = this;
        abrRecyclerView = findViewById(R.id.abrRecyclerView);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewSave = findViewById(R.id.imageViewSave);

        abrRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        SelectedABR = getIntent().getStringExtra("abrValue");
        Log.e(VolleyLog.TAG, "onCreate: " + SelectedABR);

        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        });

        SELECTED_DOSE = getIntent().getExtras().getString("dose_id");

        SetList = getIntent().getExtras().getStringArrayList("SetList");
        if (SELECTED_DOSE == null) {
            SELECTED_DOSE = "";
        }
        Log.e(VolleyLog.TAG, "onCreate: " + SetList);


        imageViewSave.setOnClickListener(view -> {
            if (SELECTED_DOSE.equalsIgnoreCase("")) {
                UtilityClass.showAlertDailog(context, "Please choose dose for exercise.");
                return;
            }
            whichapicAlled = "changeDoseValue";
            WebServices webServices = new WebServices();
            webServices.changeDoseValue(getIntent().getExtras().getString("training_exercise_id"), SELECTED_DOSE, getIntent().getExtras().getString("week"), getIntent().getExtras().getString("day"), context, AbrActivity.this);

        });

        if (getAbrNames.size() == 0) {
            whichapicAlled = "getAbrName";
            webServices.getPrescriptionAbr(context, AbrActivity.this);
        }
        EditText searchViewExerciseType = findViewById(R.id.searchViewExerciseType);
        searchViewExerciseType.setVisibility(View.VISIBLE);

        searchViewExerciseType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                recyclerViewDoseData.filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        filterDoses();

    }

    private void filterDoses() {

        bISSetExist = false;
        bISRepExist = false;
        bISWeightExist = false;
        bISHeightDistanceExist = false;
        bISTimeExist = false;
        for (int ixy = 0; ixy < SetList.size(); ixy++) {
            if (SetList.get(ixy).equalsIgnoreCase("5")) {
                bISSetExist = true;
            } else if (SetList.get(ixy).equalsIgnoreCase("18")) {
                bISRepExist = true;
            } else if (SetList.get(ixy).equalsIgnoreCase("6")) {
                bISWeightExist = true;
            } else if (SetList.get(ixy).equalsIgnoreCase("11") || SetList.get(ixy).equalsIgnoreCase("17")) {
                bISHeightDistanceExist = true;
            } else if (SetList.get(ixy).equalsIgnoreCase("19") || SetList.get(ixy).equalsIgnoreCase("14")) {
                bISTimeExist = true;
            }
        }


        if (bISTimeExist) {
            ForsearchArrayX = getAbrNames.stream().filter(article -> Integer.parseInt(article.getId()) >= 35 && Integer.parseInt(article.getId()) <= 42).collect(Collectors.toList());
        } else if (bISHeightDistanceExist) {
            ForsearchArrayX = getAbrNames.stream().filter(article -> Integer.parseInt(article.getId()) >= 43 && Integer.parseInt(article.getId()) <= 89).collect(Collectors.toList());
        } else if (bISSetExist && bISRepExist && bISWeightExist) {
            ForsearchArrayX = getAbrNames.stream().filter(article -> Integer.parseInt(article.getId()) >= 1 && Integer.parseInt(article.getId()) <= 34 ||
                    Integer.parseInt(article.getId()) >= 90 && Integer.parseInt(article.getId()) <= 94).collect(Collectors.toList());
        } else {
            ForsearchArrayX = getAbrNames;
        }

        recyclerViewDoseData = new step5Adapter(context, ForsearchArrayX);
        abrRecyclerView.setAdapter(recyclerViewDoseData);
        //notifyDataSetChanged();
        //recyclerViewDoseData.filter("");
        //recyclerViewDoseData.notifyDataSetChanged();
    }

    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            Log.d("Result", result);
            parseFolderData(result);
        } else {
            hide();
        }
    }

    private void parseFolderData(String result) {
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj.getString("responseMessage");
            Gson gson = new Gson();

            if (WebServices.responseCode == 200) {
                if (whichapicAlled.equalsIgnoreCase("SelectedABR")) {


                    String usersData = jsonObj
                            .getString("data");

                    JSONObject userJson = new JSONObject(usersData);

                    JSONObject jsonObjX = new JSONObject(userJson.getString("week"));
                    JSONArray js = new JSONArray();


                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("1", jsonObjX.getJSONArray("1"));
                    js.put(0, jsonObject);

                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("2", jsonObjX.getJSONArray("2"));
                    js.put(1, jsonObject2);

                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("3", jsonObjX.getJSONArray("3"));
                    js.put(2, jsonObject3);

                    JSONObject jsonObject4 = new JSONObject();
                    jsonObject4.put("4", jsonObjX.getJSONArray("4"));
                    js.put(3, jsonObject4);


                    Log.e(VolleyLog.TAG, "parseFolderData: " + js);
                    abrs = Arrays.asList(gson.fromJson(js.toString(), ABR[].class));

                    recyclerViewDoseDetail = new DoseDetail(context, abrs, false);
                    //getAbrDetail.get(0).get1().size()
                    recyclerViewDoseData.notifyDataSetChanged();
                    hide();
                } else if (whichapicAlled.equalsIgnoreCase("changeDoseValue")) {
                    RefreshTrainingPhase = true;
                    final custom_alert_class mAlert = new custom_alert_class(context);
                    mAlert.setMessage(responseMessage);
                    mAlert.OneButton(true);
                    mAlert.setCancelable(true);
                    mAlert.setNegativeButton("Ok", view -> {
                        back = true;
                        onBackPressed();

                    });
                    mAlert.show();

                    mAlert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            if (!back) {
                                onBackPressed();
                            }
                        }
                    });
                }
                ////Log.e(VolleyLog.TAG, "onBindViewHolder: "+getAbrDetail.get(0).AbrdetailsX().get1().get(0).getAbr());
            } else {
                UtilityClass.showAlertDailog(context, responseMessage);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);

    }


    private class ListofNamesViewHolder extends RecyclerView.ViewHolder {
        TextView DoseName, DosePhaseType;
        RecyclerView abrDetailRecyclerView;
        RelativeLayout ExcersiseSubmitButtonX, RSelectTickX, rlayoutDose;
        LinearLayout rLayoutforLBandREPS, RlLayout;
        RelativeLayout llayout, UnderLineFOrAbr;
        ImageView arrow, unSelectTick;
        ImageView showDetail, HideDetail, playVideo;

        ListofNamesViewHolder(@NonNull View itemView) {
            super(itemView);
            DoseName = itemView.findViewById(R.id.DoseName);
            DosePhaseType = itemView.findViewById(R.id.DosePhaseType);
            RlLayout = itemView.findViewById(R.id.RlLayout);
            playVideo = itemView.findViewById(R.id.playVideo);
            unSelectTick = itemView.findViewById(R.id.unSelectTick);
            ExcersiseSubmitButtonX = itemView.findViewById(R.id.ExcersiseSubmitButtonX);
            rLayoutforLBandREPS = itemView.findViewById(R.id.rLayoutforLBandREPS);
            llayout = itemView.findViewById(R.id.llayout);
            arrow = itemView.findViewById(R.id.arrow);
            abrDetailRecyclerView = itemView.findViewById(R.id.abrDetailRecyclerView);
            UnderLineFOrAbr = itemView.findViewById(R.id.UnderLineFOrAbr);
            LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            mLayoutManager1.setAutoMeasureEnabled(false);
            abrDetailRecyclerView.setLayoutManager(mLayoutManager1);
        }
    }


    public class step5Adapter extends RecyclerView.Adapter<ListofNamesViewHolder> {
        int Y = 0;
        int position;
        Context context;
        List<GetAbrName> ForsearchArray;
        List<GetAbrName> tempArray;


        public step5Adapter(Context context, List<GetAbrName> filterListbyId) {
            this.context = context;
            this.ForsearchArray = new ArrayList<>(filterListbyId);
            this.tempArray = new ArrayList<>(filterListbyId);
        }


        @NonNull
        @Override
        public ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.abr_name, viewGroup, false);
            return new ListofNamesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ListofNamesViewHolder holder, int i) {


            holder.DoseName.setText(ForsearchArray.get(i).getAbr());
            holder.abrDetailRecyclerView.setVisibility(GONE);
            holder.DoseName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

            holder.DosePhaseType.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.DosePhaseType.setText("(" + ForsearchArray.get(i).getPhase() + " " + ForsearchArray.get(i).getType() + ")");

            holder.DoseName.setTextColor(Color.parseColor("#E5E53B"));
            holder.DosePhaseType.setTextColor(Color.parseColor("#E5E53B"));

            holder.arrow.setPadding(5, 5, 5, 5);
            holder.playVideo.setVisibility(GONE);

            holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
            holder.llayout.setBackgroundColor(Color.parseColor("#232324"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.llayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            holder.llayout.setLayoutParams(layoutParams);
            holder.UnderLineFOrAbr.setVisibility(VISIBLE);

            try {
                if (SELECTED_DOSE.equalsIgnoreCase(ForsearchArray.get(i).getId())) {
                    holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                } else {
                    holder.unSelectTick.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
                }
            } catch (Exception x) {
            }

            if (SelectedABRFORSHOW.equalsIgnoreCase(ForsearchArray.get(i).getId()) && OPENED) {
                holder.abrDetailRecyclerView.setVisibility(VISIBLE);
                holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                try {
                    holder.abrDetailRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerViewDoseDetail = new DoseDetail(context, abrs, false);
                    holder.abrDetailRecyclerView.setAdapter(recyclerViewDoseDetail);
                } catch (Exception v) {
                }
            }

            holder.llayout.setOnClickListener(view -> {
                SELECTED_DOSE = ForsearchArray.get(i).getId();
                //selectedExerciseList.get(SelectedPosition).setGetDoses(getAbrNamesX);//  (new GetAbrName(ForsearchArray.get(i).getAbr(),ForsearchArray.get(i).getId()));
                notifyDataSetChanged();
            });

            holder.RlLayout.setOnClickListener(view -> {

                if (holder.abrDetailRecyclerView.getVisibility() == VISIBLE) {
                    holder.abrDetailRecyclerView.setVisibility(GONE);
                    holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                } else {
                    if (SelectedABRFORSHOW.equalsIgnoreCase(ForsearchArray.get(i).getId())) {
                        holder.abrDetailRecyclerView.setVisibility(VISIBLE);
                        holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                    } else {
                        SelectedABR = ForsearchArray.get(i).getId();
                        SelectedABRFORSHOW = ForsearchArray.get(i).getId();

                        holder.abrDetailRecyclerView.setVisibility(VISIBLE);
                        holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                        whichapicAlled = "SelectedABR";
                        hide();
                        abrs = new ArrayList<>();
                        webServices.getPrescriptionAbrDetail(ForsearchArray.get(i).getId(), context, AbrActivity.this);
                        notifyDataSetChanged();
                    }
                }
            });
        }


        @Override
        public int getItemCount() {
            int s = 0;
            try {
                s = ForsearchArray.size();
            } catch (Exception x) {
            }
            return s;
        }

        private void filter(String s) {

            String text = s.toLowerCase();

            text = text.toLowerCase().trim();
            if (text.length() == 0) {
                ForsearchArray = tempArray;
            } else {
                ForsearchArray = new ArrayList<>();
                for (int i = 0; i < tempArray.size(); i++) {
                    if (tempArray.get(i).getAbr().toLowerCase().contains(text) || tempArray.get(i).getType().toLowerCase().contains(text) || tempArray.get(i).getPhase().toLowerCase().contains(text)) {
                        ForsearchArray.add(tempArray.get(i));
                    }
                }
            }

            notifyDataSetChanged();
        }

    }


}
