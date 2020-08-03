package com.org.godspeed.fragments;


/**
 * Created by Suhasini & Tanveer on 8/7/2019.
 */

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
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
import java.util.Random;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.service.SchoolDataService.LoginJson;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Competition_Board extends Fragment implements GodSpeedInterface {

    public static boolean isAnimationStarted;
    TextView CancelButtonOfSearch;
    ImageView calc_clear_txt_Prise;
    EditText calc_txt_Prise;
    View view;
    Transition transition;
    private EditText searchViewExerciseType;
    private ProgramsRecycler adapter;
    private Context context;
    private RecyclerView BoardRecyclerData;
    private RelativeLayout rSearchAthleteText;
    private List<SelectedAthleteDEtail> mDisplayedValues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_competition__board, container, false);
        context = getActivity();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        BoardRecyclerData = view.findViewById(R.id.Recyclerboard);
        forSearchBar();

        BoardRecyclerData.setLayoutManager(new GridLayoutManager(context, 2));

        WebServices webServices = new WebServices();
        webServices.getAthlete(LoginJson.get(0).getUserId(), "", "", "", context, Competition_Board.this);


        BoardRecyclerData.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration Hdivider = new DividerItemDecoration(BoardRecyclerData.getContext(), DividerItemDecoration.HORIZONTAL);
        Hdivider.setDrawable(ContextCompat.getDrawable(context, R.drawable.hborder));
        BoardRecyclerData.addItemDecoration(Hdivider);


        DividerItemDecoration Vdivider = new DividerItemDecoration(BoardRecyclerData.getContext(), DividerItemDecoration.VERTICAL);
        Vdivider.setDrawable(ContextCompat.getDrawable(context, R.drawable.hborder));
        BoardRecyclerData.addItemDecoration(Vdivider);


        CancelButtonOfSearch.setOnClickListener(view1 -> {
            toggle();
            calc_txt_Prise.setText("");
        });

        calc_clear_txt_Prise.setOnClickListener(view1 -> {
            calc_txt_Prise.setText("");
        });

        // SearchAthlete.setOnClickListener(view1 -> toggle());
//        imageViewMenuFilter.setOnClickListener(v -> {
//            CloseDrawer();
////                Fragment newFragment = new custom_dialog_filter();
////                FragmentTransaction transaction = getFragmentManager().beginTransaction();
////                transaction.replace(R.id.content_frame, newFragment);
////                transaction.addToBackStack(null);
////                transaction.commit();
//        });
        calc_clear_txt_Prise.setVisibility(GONE);
        calc_txt_Prise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                // adapter.filter(text);
                try {
                    adapter.filter(text);
                } catch (Exception m) {

                }
                if (text.length() > 0) {
                    calc_clear_txt_Prise.setVisibility(VISIBLE);
                } else {
                    calc_clear_txt_Prise.setVisibility(GONE);
                }


                //  adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        // BoardRecyclerData.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayout.VERTICAL));

        return view;
    }

    private void forSearchBar() {
        RelativeLayout searchAthleteText = view.findViewById(R.id.SearchAthleteText);
        CancelButtonOfSearch = view.findViewById(R.id.CancelButtonOfSearch);
        rSearchAthleteText = view.findViewById(R.id.rSearchAthleteText);
        calc_txt_Prise = view.findViewById(R.id.calc_txt_Prise);
        calc_clear_txt_Prise = view.findViewById(R.id.calc_clear_txt_Prise);
        transition = new Slide(Gravity.TOP);

        transition.setDuration(300);
        transition.addTarget(R.id.rSearchAthleteText);

        //SearchAthlete.setOnClickListener(view -> toggle());
    }


    public void toggle() {


        //calc_txt_Prise.setText("");
        TransitionManager.beginDelayedTransition(rSearchAthleteText, transition);

        rSearchAthleteText.setVisibility(rSearchAthleteText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

        if (rSearchAthleteText.getVisibility() == VISIBLE) {
            calc_txt_Prise.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(calc_txt_Prise, InputMethodManager.SHOW_IMPLICIT);
            //gridViewAthlete.setEnabled(false);
        }

    }

    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            parseRequiredData(result);
        } else {
            UtilityClass.hide();
        }

    }

    private void parseRequiredData(String result) {
        Log.d(UtilityClass.TAG, "parseRequiredData:TAGS " + result);
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj
                    .getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");

            Log.e("**********", "mymessge" + responseMessage);
            if (WebServices.responseCode == 200) {

                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");
                if (jsonDataArray != null && jsonDataArray.length() > 0) {

                    Gson gson = new Gson();
                    mDisplayedValues = new ArrayList<>(Arrays.asList(gson.fromJson(jsonDataArray.toString(), SelectedAthleteDEtail[].class)));

                    new Handler().postDelayed(() -> {
                        adapter = new ProgramsRecycler(context, mDisplayedValues);
                        BoardRecyclerData.setAdapter(adapter);
                    }, 60);

                }

            } else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public class ProgramsRecycler extends RecyclerView.Adapter<RecyclerViewHolder> {
        Context context;
        private List<SelectedAthleteDEtail> mDisplayedValuesX;

        public ProgramsRecycler(Context context, List<SelectedAthleteDEtail> mDisplayedValues) {
            this.context = context;
            this.mDisplayedValuesX = mDisplayedValues;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.competition_board_items, viewGroup, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            if (position == 0) {
                UtilityClass.hide();
            }
            holder.textviewteamname.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


            holder.textviewteamname.setText(UtilityClass.getNameAthlete(mDisplayedValuesX.get(position).getFirstName(), mDisplayedValuesX.get(position).getLastName(), mDisplayedValuesX.get(position).getEmailId()));


            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            holder.lLayoutboard.setBackgroundColor(color);

            final int min = 1;
            final int max = 100;
            final int random = new Random().nextInt((max - min) + 1) + min;
            holder.textviewper.setText(random + "%");
            //holder.setIsRecyclable(true);
//            if (position % 3 == 0) {
//
//                holder.textviewper.setText("13%");
//            } else if (position % 2 == 0) {
//                holder.lLayoutboard.setBackgroundColor(getResources().getColor(R.color.color_Greenlight)); // use default color
//
//            } else if (position % 7 == 0) {
//                holder.lLayoutboard.setBackgroundColor(getResources().getColor(R.color.color_orange)); // use default color
//                holder.textviewper.setText("90%");
//            } else if (position % 4 == 0) {
//                holder.lLayoutboard.setBackgroundColor(getResources().getColor(R.color.light_blue)); // use default color
//                holder.textviewper.setText("33%");
//            } else if (position % 5 == 0) {
//                holder.lLayoutboard.setBackgroundColor(getResources().getColor(R.color.light_orange)); // use default color
//                holder.textviewper.setText("89%");
//            } else if (position % 6 == 0) {
//                holder.lLayoutboard.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark)); // use default color
//                holder.textviewper.setText("89%");
//            } else {
//                holder.lLayoutboard.setBackgroundColor(getResources().getColor(R.color.light_blue)); // use default color
//                holder.textviewper.setText("26%");
//            }
            if (mDisplayedValuesX.get(position).getUserImage().equalsIgnoreCase("")) {
                holder.imageViewProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.logo_f));
            } else Glide.with(context).load(
                    WebServices.BASE_URL_FOR_IMAGES + mDisplayedValuesX.get(position).getUserImage()).error(getResources().getDrawable(R.drawable.logo_f))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                    .into(holder.imageViewProfilePic);
        }


        @Override
        public int getItemCount() {
            return mDisplayedValuesX.size();

        }


        public void filter(String s) {

            String text = s.toLowerCase();
            if (text.length() == 0) {
                mDisplayedValuesX = mDisplayedValues;
            } else {
                mDisplayedValuesX = new ArrayList<>();

                for (int i = 0; i < mDisplayedValues.size(); i++) {
                    String name = "";
                    name = UtilityClass.getNameAthlete(mDisplayedValues.get(i).getFirstName(), mDisplayedValues.get(i).getLastName(), mDisplayedValues.get(i).getEmailId());
                    if (name.toLowerCase().contains(text)) {
                        mDisplayedValuesX.add(mDisplayedValues.get(i));
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativelayout;
        private LinearLayout lLayoutboard;
        private TextView textviewteamname, textviewper;
        private ImageView myImageView, imageViewProfilePic;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            relativelayout = itemView.findViewById(R.id.relativelayout);
            lLayoutboard = itemView.findViewById(R.id.lLayoutboard);
            textviewteamname = itemView.findViewById(R.id.textviewteamname);
            textviewper = itemView.findViewById(R.id.textviewper);
            myImageView = itemView.findViewById(R.id.myImageView);
            imageViewProfilePic = itemView.findViewById(R.id.imageViewProfilePic);
        }
    }


}