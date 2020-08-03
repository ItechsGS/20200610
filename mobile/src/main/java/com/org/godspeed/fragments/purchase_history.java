
package com.org.godspeed.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.GetPurchaseHistory.GetPurchaseHistory;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.facebook.GraphRequest.TAG;
import static com.org.godspeed.service.SchoolDataService.LoginJson;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class purchase_history extends Fragment implements GodSpeedInterface {

    View view;

    Context context;

    WebServices webServices = new WebServices();
    AthleteDataHealth athleteDataHealth;
    List<GetPurchaseHistory> getPurchaseHistoryList = new ArrayList<>();
    private String whichApiCalled = "";
    private RecyclerView messageFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.message_screen_fragment, container, false);
        context = getActivity();

        messageFragment = view.findViewById(R.id.messageFragment);
        messageFragment.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

        messageFragment.setVisibility(View.VISIBLE);
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider_light));

        messageFragment.addItemDecoration(divider);
        whichApiCalled = "getPurchaseHistory";
        webServices.getPurchaseHistory(LoginJson.get(0).getUserId(), context, this);
        return view;
    }


    @Override
    public void ApiResponse(String result) {
        Log.d(VolleyLog.TAG, "ApiResponse: " + result);
        if (result != null && result.trim().length() > 0) {
            parseRequiredData(result);

        } else {
            UtilityClass.hide();
        }
    }

    private void parseRequiredData(String result) {
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");


            if (WebServices.responseCode == 200) {
                if (whichApiCalled.equalsIgnoreCase("getPurchaseHistory")) {
                    JSONArray jsonDataArray = jsonObj
                            .getJSONArray("data");
                    Gson gson = new Gson();
                    if (jsonDataArray != null && jsonDataArray.length() > 0) {
                        getPurchaseHistoryList = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), GetPurchaseHistory[].class)));
                        athleteDataHealth = new AthleteDataHealth(context, getPurchaseHistoryList);
                        messageFragment.setAdapter(athleteDataHealth);
                        UtilityClass.hide();
                    }
                } else if (whichApiCalled.equalsIgnoreCase("deleteTrainingProgramFromSale")) {
                    whichApiCalled = "getPurchaseHistory";
                    webServices.getPurchaseHistory(LoginJson.get(0).getUserId(), context, this);
                }
            } else {
                UtilityClass.hide();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            UtilityClass.hide();
        } catch (Exception e) {
            e.printStackTrace();
            UtilityClass.hide();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private class AthleteDataHealth extends RecyclerView.Adapter<AthleteDataHealth.AthleteDataHealthHolder> {
        List<GetPurchaseHistory> getPurchaseHistoryList;
        ViewBinderHelper binderHelper = new ViewBinderHelper();

        public AthleteDataHealth(Context context, List<GetPurchaseHistory> getPurchaseHistoryList) {
            this.getPurchaseHistoryList = getPurchaseHistoryList;
        }

        @Override
        public AthleteDataHealthHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.transaction_history_screen, viewGroup, false);
            return new AthleteDataHealthHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AthleteDataHealthHolder view, int position) {


            try {
                view.textViewAthleteName.setText(getPurchaseHistoryList.get(position).getProgramName());
                Glide.with(context).load(getResources().getDrawable(R.drawable.logo_f)).error(getResources().getDrawable(R.drawable.logo_f))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(view.imageViewProfilePic);
            } catch (Exception v) {
                view.textViewAthleteName.setText("");
            }

            view.msgText.setTextSize(18f);

            Date date = new Date();


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
            try {
                view.msgText.setText(getPurchaseHistoryList.get(position).getCreateTime());
                Log.d(TAG, "onBindViewHolder: " + getPurchaseHistoryList.get(position).getCreateTime());
                date = format.parse(getPurchaseHistoryList.get(position).getCreateTime());

            } catch (Exception e) {
                Log.d(TAG, "onBindViewHolder: " + e);
            }
            // String formattedDate = new SimpleDateFormat("dd-MMM-yyyy       HH:MM:SS").format(date);


        }


        @Override
        public int getItemCount() {
            return getPurchaseHistoryList.size();
        }


        public class AthleteDataHealthHolder extends RecyclerView.ViewHolder {
            ImageView imageViewProfilePic;

            TextView textViewAthleteName;

            TextView LevelText, msgText;

            RelativeLayout rLayoutC;

            LinearLayout msgLayout;

            public AthleteDataHealthHolder(@NonNull View itemView) {
                super(itemView);
                imageViewProfilePic = itemView.findViewById(R.id.imageViewProfilePic);
                msgLayout = itemView.findViewById(R.id.msgLayout);
                msgLayout.setVisibility(View.VISIBLE);
                textViewAthleteName = itemView.findViewById(R.id.textViewAthleteName);
                textViewAthleteName.setTextColor(getResources().getColor(R.color.textColorYellow));


                msgText = itemView.findViewById(R.id.msgText);

                rLayoutC = itemView.findViewById(R.id.rLayoutC);
                textViewAthleteName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            }
        }
    }

}
