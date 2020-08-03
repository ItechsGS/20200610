package com.org.godspeed.ProgramPurchase;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.fragments.FragmentTrainingAndFolder;
import com.org.godspeed.response_JsonS.ProgramsJSON.ProgramsPurchase;
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

import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.CAllAPI;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LayoutForFolder;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LockDrawer;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.SearchAthlete;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.SportsIDForTPpurchase;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.fm;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewSliderBackIcon;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewSliderDrawerToggleIcon;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES_ASSETS;

public class ProgramPurchaseFragment extends Fragment implements GodSpeedInterface {

    Gson gson = new Gson();
    Boolean grid = true;
    private Context context;
    private TextView textViewScreenName;
    private ImageView imageViewBackArrow;
    private RelativeLayout rLayoutHeader;
    private WebServices webServices = null;
    private List<ProgramsPurchase> programs = new ArrayList<>();
    private RecyclerView programsRecyclrView;

    public void CallApi() {
        // Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        if (CAllAPI) {
            webServices = new WebServices();
            webServices.getPrograms(context, ProgramPurchaseFragment.this);
            CAllAPI = false;
        }
    }


    public void toggle() {
        Log.e(VolleyLog.TAG, "toggle: ");
        if (grid) {
            programsRecyclrView.setLayoutManager(new GridLayoutManager(context, 2));
            grid = false;
            programsRecyclrView.setAdapter(new ProgramsRecycler(context));
            imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.listtwo));
        } else {
            grid = true;
            programsRecyclrView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            programsRecyclrView.setAdapter(new ProgramsRecycler(context));
            imageViewMenuFilter.setImageDrawable(getResources().getDrawable(R.drawable.grid));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        View view = inflater.inflate(R.layout.layout_exercise_type_listview, container, false);
        context = getActivity();


        programsRecyclrView = view.findViewById(R.id.programsRecyclrView);
        toggle();

        Log.e("Screen", "Fragment Program Purchase");


        rLayoutHeader = view.findViewById(R.id.rLayoutHeader);
        rLayoutHeader.setVisibility(View.GONE);

        textViewScreenName = view.findViewById(R.id.textViewScreenName);
        textViewScreenName.setText(getString(R.string.programs).toUpperCase());
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));


        imageViewBackArrow = view.findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setVisibility(View.GONE);


        webServices = new WebServices();
        webServices.getPrograms(context, ProgramPurchaseFragment.this);
        return view;

    }


    @Override
    public void ApiResponse(String result) {
        Log.e("ResultPrograms", result);
        if (result != null && result.trim().length() > 0) {
            UtilityClass.SetPreferences(context, getString(R.string.programs), result);
            parseRequiredData(result);
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

            Log.e("**********", "" + responseMessage);
            if (WebServices.responseCode == 200) {

                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");

                programs = Arrays.asList(gson.fromJson(jsonDataArray.toString(), ProgramsPurchase[].class));
                programsRecyclrView.setAdapter(new ProgramsRecycler(context));

            } else {
                UtilityClass.showAlertDailog(context, responseMessage);

            }
        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        hide();

    }


    public class ProgramsRecycler extends RecyclerView.Adapter<RecyclerViewHolder2> {
        Context context;

        public ProgramsRecycler(Context context) {
            this.context = context;
        }

        @Override
        public RecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.programs_purchase, viewGroup, false);
            return new RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder2 Holder, final int i) {

            Holder.myImageViewText.setText(programs.get(i).getCategoryName());
            Holder.myImageViewText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(getActivity().getApplicationContext()));
            Glide.with(context).load(BASE_URL_FOR_IMAGES_ASSETS + "/programplan_images" +
                    programs.get(i).getImage()).into(Holder.myImageView);
            if (grid) {
                Holder.myImageView.setScaleType(ImageView.ScaleType.FIT_XY);

            } else {
                GridLayoutManager.LayoutParams relativeParams = (GridLayoutManager.LayoutParams) Holder.relativelayout.getLayoutParams();
                relativeParams.setMargins(0, 0, 8, 8);  // left, top, right, bottom
                Holder.relativelayout.setLayoutParams(relativeParams);

                Holder.myImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            Holder.AddExerciseForPurhase.setOnClickListener(view -> {
                if (LoginJson.get(0).getUserType().equalsIgnoreCase("1")) {
                    LockDrawer();
                    CoachNevigationDrawerScreen.PurchaseTP = true;
                    SportsIDForTPpurchase = programs.get(i).getId();

                    CoachNevigationDrawerScreen.FromSCreen = "ProgramPurchaseFragment";
                    CoachNevigationDrawerScreen.screenname = "training";
                    LayoutForFolder.setVisibility(View.GONE);
                    CoachNevigationDrawerScreen.textViewScreenName.setText("TRAINING PROGRAM(s)");
                    imageViewMenuFilter.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.VISIBLE);
                    Fragment fragment;
                    fragment = new FragmentTrainingAndFolder("0");

                    Bundle args = new Bundle();
                    args.putString("FromScreen", "ProgramPurchaseFragment");

                    fragment.setArguments(args);

                    fragment.setEnterTransition(new Slide(Gravity.RIGHT));
                    fragment.setExitTransition(new Slide(Gravity.LEFT));


                    fm.beginTransaction().add(R.id.content_frame, fragment, "0").addToBackStack("true").commit();
                    imageViewSliderDrawerToggleIcon.setVisibility(View.GONE);
                    imageViewSliderBackIcon.setVisibility(View.VISIBLE);
                }
            });
            if (LoginJson.get(0).getUserType().equalsIgnoreCase("1")) {
                Holder.myImageViewX.setVisibility(View.VISIBLE);
            }

            Holder.relativelayout.setOnClickListener(view -> {
                startActivity(new Intent(context, purchase_membership.class).putExtra("Program_Plan", new Gson().toJson(programs.get(i)))
                        .putExtra("ScreenName", programs.get(i).getCategoryName()));
                getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                //Log.d(VolleyLog.TAG, "*************** purchase_membership *************");

            });
        }

        @Override
        public int getItemCount() {
            return programs.size();
        }
    }

    private class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
        LinearLayout AddExerciseForPurhase;
        private RelativeLayout relativelayout;
        private TextView myImageViewText;
        private ImageView myImageView, myImageViewX;

        public RecyclerViewHolder2(@NonNull View itemView) {
            super(itemView);
            relativelayout = itemView.findViewById(R.id.relativelayout);
            myImageViewText = itemView.findViewById(R.id.myImageViewText);
            AddExerciseForPurhase = itemView.findViewById(R.id.AddExerciseForPurhase);
            myImageView = itemView.findViewById(R.id.myImageView);
            myImageViewX = itemView.findViewById(R.id.myImageViewX);
        }
    }


}
