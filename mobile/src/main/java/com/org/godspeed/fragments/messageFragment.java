package com.org.godspeed.fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.chat.chat_activity;
import com.org.godspeed.response_JsonS.messageJson.MessageScreenData;
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

import static com.org.godspeed.service.SchoolDataService.LoginJson;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class messageFragment extends Fragment implements GodSpeedInterface {

    View view;

    Context context;

    WebServices webServices = new WebServices();
    AthleteDataHealth athleteDataHealth;
    List<MessageScreenData> messageScreenData = new ArrayList<>();
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
        whichApiCalled = "get_PushNotification_detail";
        webServices.get_PushNotification_detail(LoginJson.get(0).getUserId(), context, messageFragment.this);
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


            String respCode = jsonObj
                    .getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");


            if (WebServices.responseCode == 200) {

                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");
                Gson gson = new Gson();
                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    if (whichApiCalled.equalsIgnoreCase("get_PushNotification_detail")) {

                        messageScreenData = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonDataArray.toString(), MessageScreenData[].class)));
                        athleteDataHealth = new AthleteDataHealth(context, messageScreenData);
                        messageFragment.setAdapter(athleteDataHealth);
                        UtilityClass.hide();
                    }
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
        List<MessageScreenData> messageScreenData;

        public AthleteDataHealth(Context context, List<MessageScreenData> messageScreenData) {
            this.messageScreenData = messageScreenData;
        }

        @Override
        public AthleteDataHealthHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.message_screen, viewGroup, false);
            return new AthleteDataHealthHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AthleteDataHealthHolder view, int position) {
            try {
                view.textViewAthleteName.setText(UtilityClass.getNameAthlete(messageScreenData.get(position).getSenderDetail().getFirstName(), messageScreenData.get(position).getSenderDetail().getLastName(), messageScreenData.get(position).getSenderDetail().getEmailId()));
                Glide.with(context).load(
                        WebServices.BASE_URL_FOR_IMAGES + messageScreenData.get(position).getSenderDetail().getUserImage()).error(getResources().getDrawable(R.drawable.logo_f))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(view.imageViewProfilePic);
            } catch (Exception v) {
                view.textViewAthleteName.setText("");
            }


            view.msgTime.setText(messageScreenData.get(position).getPushNotificationDatetime());
            view.msgTime.setTextSize(14f);
            view.msgText.setTextSize(18f);
            view.msgText.setText(messageScreenData.get(position).getMessage());

            view.msgLayout.setOnClickListener(view1 -> {
                Bundle bundle = new Bundle();
                bundle.putString("client_name", UtilityClass.getNameAthlete(messageScreenData.get(position).getSenderDetail().getFirstName(), messageScreenData.get(position).getSenderDetail().getLastName(), messageScreenData.get(position).getSenderDetail().getEmailId()));
                bundle.putString("client_id", messageScreenData.get(position).getSenderDetail().getSenderUserId());
                bundle.putString("client_message", messageScreenData.get(position).getMessage());
                startActivity(new Intent(context, chat_activity.class).putExtras(bundle));
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                Log.d(VolleyLog.TAG, "*************** chat_activity *************");
            });

        }


        @Override
        public int getItemCount() {
            return messageScreenData.size();
        }


        public class AthleteDataHealthHolder extends RecyclerView.ViewHolder {
            ImageView imageViewProfilePic;

            TextView textViewAthleteName;

            TextView LevelText, msgText, msgTime;

            RelativeLayout rLayoutC, rmsgLayout;

            LinearLayout msgLayout;

            public AthleteDataHealthHolder(@NonNull View itemView) {
                super(itemView);
                imageViewProfilePic = itemView.findViewById(R.id.imageViewProfilePic);
                msgLayout = itemView.findViewById(R.id.msgLayout);
                msgLayout.setVisibility(View.VISIBLE);
                textViewAthleteName = itemView.findViewById(R.id.textViewAthleteName);
                textViewAthleteName.setTextColor(getResources().getColor(R.color.textColorYellow));


                msgText = itemView.findViewById(R.id.msgText);
                msgTime = itemView.findViewById(R.id.msgTime);


                rmsgLayout = itemView.findViewById(R.id.rmsgLayout);


                rLayoutC = itemView.findViewById(R.id.rLayoutC);
                textViewAthleteName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


                rmsgLayout.setVisibility(View.VISIBLE);

                // Level1.setSelected(true);
            }
        }
    }

}