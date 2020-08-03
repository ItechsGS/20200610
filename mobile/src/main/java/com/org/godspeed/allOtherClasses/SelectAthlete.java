package com.org.godspeed.allOtherClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.athleteData.AssingProgramDetail;
import com.org.godspeed.response_JsonS.athleteData.SelectedAthleteDEtail;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.service.SchoolDataService.getTeamsDetailsClas;

public class SelectAthlete extends Activity implements GodSpeedInterface {
    static SelectAthlete sActivityX;
    static Context sContextX;
    Context context;
    RecyclerView SelectTeam;
    ImageView imageViewBackArrow;
    TeamAdapterX teamAdapter;
    ProgramAdapterX programAdapter;
    Dialog dialog;
    TextView textViewScreenName;
    WebServices webServices = new WebServices();
    int trainingProgramDetailPosition;
    int positionofTeamMain;
    TextView CancelButtonOfSearch;
    ImageView calc_clear_txt_Prise;
    EditText calc_txt_Prise;
    Transition transition;
    private String whichApiCalled = "";
    private boolean isAnimationStarted = false;
    private Animation zoomIn, zoomOut;
    private ImageView imageViewForZoomInOut;
    private List<SelectedAthleteDEtail> AthleteDataX;
    private RelativeLayout SearchAthleteText;
    private RelativeLayout rSearchAthleteText;
    private ImageView imageViewSearch, imageViewFilterTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_select_team_and_athlete_screen);


        context = this;
        sActivityX = this;
        dialog = new Dialog(this);
        sContextX = this;

        forSearchBar();

        imageViewForZoomInOut = findViewById(R.id.imageViewForZoomInOut);


        SelectTeam = findViewById(R.id.SelectTeam);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        textViewScreenName = findViewById(R.id.textViewScreenName);
        imageViewSearch = findViewById(R.id.imageViewSearch);
        imageViewFilterTeam = findViewById(R.id.imageViewFilterTeam);


        textViewScreenName.setText("Select Athlete");


        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        });

        imageViewSearch.setOnClickListener(view1 -> toggle());

        getAthleteDataFromServer();

        calc_clear_txt_Prise.setVisibility(GONE);
        CancelButtonOfSearch.setOnClickListener(view1 -> {
            toggle();
            calc_txt_Prise.setText("");
        });

        calc_clear_txt_Prise.setOnClickListener(view1 -> {
            calc_txt_Prise.setText("");
        });

        calc_txt_Prise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                // adapter.filter(text);
                try {
                    teamAdapter.filter(text);
                } catch (Exception v) {

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


        //webServices.setAssignProgramAthlete("","","",context,SelectAthlete.this);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void forSearchBar() {
        SearchAthleteText = findViewById(R.id.SearchAthleteText);
        CancelButtonOfSearch = findViewById(R.id.CancelButtonOfSearch);
        rSearchAthleteText = findViewById(R.id.rSearchAthleteText);
        calc_txt_Prise = findViewById(R.id.calc_txt_Prise);
        calc_clear_txt_Prise = findViewById(R.id.calc_clear_txt_Prise);
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
            //hideSoftKeyboard();
            calc_txt_Prise.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(calc_txt_Prise, InputMethodManager.SHOW_IMPLICIT);
            //gridViewAthlete.setEnabled(false);
        }
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    private void getAthleteDataFromServer() {

        //UtilityClass.showWaitDialog(new Dialog(context),context);
        whichApiCalled = "athlete";

        webServices.getAthlete(LoginScreen.userId, getIntent().getStringExtra("TeamId"), context, SelectAthlete.this);
    }


    @Override
    public void ApiResponse(String result) {
        Log.d("Result", result);
        if (result != null && result.trim().length() > 0) {
            Log.d("Result", result);
            parseRequiredData(result);
        } else {
            UtilityClass.hide();
        }
    }


    private void parseRequiredData(String result) {
        Log.e(VolleyLog.TAG, "parseRequiredData:TAGS " + result);
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj
                    .getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj
                    .getString("responseMessage");

            Log.e("**********", "mymessge" + responseMessage);
            UtilityClass.hide();
            if (WebServices.responseCode == 200) {
                if (whichApiCalled.equalsIgnoreCase("setAssignProgramAthlete")) {

//

                    getAthleteDataFromServer();
                    final custom_alert_class mAlert = new custom_alert_class(context);
                    mAlert.setMessage(responseMessage);
                    mAlert.OneButton(true);

                    mAlert.setNegativeButton("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mAlert.dismiss();
                            finish();
                            onBackPressed();
                        }
                    });

                    mAlert.show();

                }
                JSONArray jsonDataArray = jsonObj
                        .getJSONArray("data");
                if (jsonDataArray != null && jsonDataArray.length() > 0) {
                    if (whichApiCalled.equalsIgnoreCase("athlete")) {
//                        for (int x = 0; x < jsonDataArray.length(); x++) {
//                            JSONObject jsonAthleteObjDataX = jsonDataArray.getJSONObject(x);
//                            Object json = jsonAthleteObjDataX.get("selected_athlete_level");
//                            Object json1 = jsonAthleteObjDataX.get("assing_program_details");
//                            Object json2 = jsonAthleteObjDataX.get("selected_athlete_goal");
//                            Object json3 = jsonAthleteObjDataX.get("athlete_level");
//                            Object json4 = jsonAthleteObjDataX.get("sports");
//
//                            if (json instanceof String) {
//                                jsonAthleteObjDataX.put("selected_athlete_level", new JSONArray());
//                            }
//                            if (json1 instanceof String) {
//                                jsonAthleteObjDataX.put("assing_program_details", new JSONArray());
//                            }
//                            if (json2 instanceof String) {
//                                jsonAthleteObjDataX.put("selected_athlete_goal", new JSONArray());
//                            }
//                            if (json3 instanceof String) {
//                                jsonAthleteObjDataX.put("athlete_level", new JSONArray());
//                            }
//                            if (json4 instanceof String) {
//                                jsonAthleteObjDataX.put("sports", new JSONArray());
//                            }
//                        }

                        Gson gson = new Gson();

                        AthleteDataX = new ArrayList<>();
                        AthleteDataX = Arrays.asList(gson.fromJson(jsonDataArray.toString(), SelectedAthleteDEtail[].class));

                        LinearLayoutManager llm = new LinearLayoutManager(context);
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        SelectTeam.setLayoutManager(llm);
                        teamAdapter = new TeamAdapterX(context);
                        SelectTeam.setAdapter(teamAdapter);
                        SelectTeam.setHasFixedSize(true);


                    }

                }
//                JSONObject userJson = new JSONObject(usersData);
            } else {
                //UtilityClass.showAlertDailog(context, responseMessage);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    public void showDialogofTraining(Context context, int x, int y, String event, String eventData, String AthleteID) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_box_for_training_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setDimAmount(0);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        LinearLayout BeginTraining, Cancel, workoutSummary, SingleRow, CancelXc, YesX;
        TextView EventName, EventNameX, begin, workout, cancel, Yes, CancelX;

        BeginTraining = dialog.findViewById(R.id.BeginTraining);
        Cancel = dialog.findViewById(R.id.Cancel);
        workoutSummary = dialog.findViewById(R.id.workoutSummary);
        begin = dialog.findViewById(R.id.begin);
        workout = dialog.findViewById(R.id.workout);
        cancel = dialog.findViewById(R.id.cancel);
        SingleRow = dialog.findViewById(R.id.SingleRow);

        YesX = dialog.findViewById(R.id.YesX);
        CancelXc = dialog.findViewById(R.id.CancelXc);


        YesX.setOnClickListener(view -> {
            DialogFragment dialogfragment = new DatePickerDialogClass(AthleteID, "");
            dialogfragment.show(getFragmentManager(), "Date Picker Dialog");
            dialog.dismiss();
        });

        CancelXc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        SingleRow.setVisibility(View.VISIBLE);


        EventName = dialog.findViewById(R.id.EventName);

        EventNameX = dialog.findViewById(R.id.EventNameX);

        EventNameX.setText("Do you want to assign " + event + " Training Program?");


//        showDialogofTraining(context,0,0,GetTeam.get(position).getTeamName(),"",0);
        EventName.setTypeface(CustomTypeface.load_Montserrat_Bold_Fonts(context));
        EventNameX.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        begin.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        workout.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));
        cancel.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(context));

        BeginTraining.setVisibility(View.GONE);
        workoutSummary.setVisibility(View.GONE);
        Cancel.setVisibility(View.GONE);


        BeginTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        workoutSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void sendtoServerAthlete(String dateX, String AthleteId) {

        whichApiCalled = "setAssignProgramAthlete";
        webServices.setAssignProgramAthlete(AthleteId, getIntent().getStringExtra("training_program_id"), dateX, LoginJson.get(0).getUserId(), context, SelectAthlete.this);
    }

    private void updateAssignProgramStartDateAthlete(String dateX, String assignProgramId) {
        whichApiCalled = "updateAssignProgramStartDate";
        Log.e(VolleyLog.TAG, "sendtoServerDate: " + "updateAssignProgramStartDate");
        Log.e(VolleyLog.TAG, "sendtoServerDate:before " + getTeamsDetailsClas.get(positionofTeamMain).getTrainingProgramDetail().get(trainingProgramDetailPosition).getStartDate());
        AthleteDataX.get(sActivityX.positionofTeamMain).getAssingProgramDetails().get(trainingProgramDetailPosition).setStartDate(dateX);
        Log.e(VolleyLog.TAG, "sendtoServerDate: " + getTeamsDetailsClas.get(positionofTeamMain).getTrainingProgramDetail().get(trainingProgramDetailPosition).getStartDate());
        teamAdapter.notifyItemChanged(positionofTeamMain);
        webServices.updateAssignProgramStartDateAthlete(assignProgramId, dateX, context, SelectAthlete.this);

    }

    @SuppressLint("ValidFragment")
    public static class DatePickerDialogClass extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        String AthleteId = "";
        String assignProgramId = "";


        public DatePickerDialogClass(String AthleteId, String assignProgramId) {
            this.AthleteId = AthleteId;
            this.assignProgramId = assignProgramId;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    R.style.datepickerCustom, this, year, month, day);

            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Date date1 = null;
            try {
                date1 = new SimpleDateFormat("dd - MM - yyyy").parse(day + " - " + (month + 1) + " - " + year);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            android.text.format.DateFormat df = new android.text.format.DateFormat();
            String dateof = DateFormat.format("yyyy-MM-dd", date1).toString();
            //textViewScreenName.setTag(df.format("yyyy-MM-dd", date1).toString());

            if (assignProgramId.equalsIgnoreCase("")) {
                sActivityX.sendtoServerAthlete(dateof, AthleteId);
                UtilityClass.showWaitDialog(sActivityX.dialog, sActivityX.context);
            } else {
                sActivityX.updateAssignProgramStartDateAthlete(dateof, assignProgramId);
            }

            //webServices.setAssignProgram(getIntent().getStringExtra("training_program_id"),"","",df.toString(),context,SelectTeamAndAthleteScreen.this);

            //textViewDate.setText(df.format("dd - MMM - yyyy", date1));
            //GlobalClass.PhaseDate = df.format("dd - MMM - yyyy", date1).toString();
        }
    }

    private class TrainingScreen extends RecyclerView.ViewHolder {
        TextView textViewTrainingName, Delete, Rename, Assign, Edit, Copy, textViewCount, DateofProgram;
        RelativeLayout rrrss, rlayoutCount;
        SwipeRevealLayout rLayoutForTraining;
        ImageView folderIcon, imageViewTeamIcon, arrow;
        RecyclerView ProgramList;

        public TrainingScreen(@NonNull View convertView) {
            super(convertView);
            textViewTrainingName = convertView.findViewById(R.id.textViewTrainingName);
            textViewTrainingName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
            folderIcon = convertView.findViewById(R.id.folderIcon);
            rLayoutForTraining = convertView.findViewById(R.id.rLayoutForTraining);
            rrrss = convertView.findViewById(R.id.rrrss);
            rlayoutCount = convertView.findViewById(R.id.rlayoutCount);
            DateofProgram = convertView.findViewById(R.id.DateofProgram);

            Delete = convertView.findViewById(R.id.Delete);
            Rename = convertView.findViewById(R.id.Rename);
            Assign = convertView.findViewById(R.id.Assign);
            Edit = convertView.findViewById(R.id.Edit);
            textViewCount = convertView.findViewById(R.id.textViewCount);
            Copy = convertView.findViewById(R.id.Copy);
            arrow = convertView.findViewById(R.id.arrow);
            ProgramList = convertView.findViewById(R.id.ProgramList);
            LinearLayoutManager llm = new LinearLayoutManager(context);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            ProgramList.setLayoutManager(llm);
        }
    }

    public class TeamAdapterX extends RecyclerView.Adapter<TrainingScreen> {
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();
        private Context context;
        private LayoutInflater vi;
        private List<SelectedAthleteDEtail> AthleteDataY = AthleteDataX;

        public TeamAdapterX(Context context) {
            this.context = context;
            binderHelper.setOpenOnlyOne(true);
        }

        @Override
        public TrainingScreen onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_view_items_training, parent, false);
            return new TrainingScreen(itemView);
        }

        @Override
        public void onBindViewHolder(TrainingScreen holder, final int position) {
            holder.folderIcon.setVisibility(View.GONE);
            holder.rlayoutCount.setVisibility(View.VISIBLE);

            holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));


            holder.rLayoutForTraining.setLockDrag(true);


            holder.rLayoutForTraining.setOnClickListener(view -> {
                //showDialogofTraining(context,0,0,AthleteX,"",0);
                // showDialogofTraining(context,"",);
            });


            //holder.rLayoutForTraining.setOnClickListener(this);

            holder.arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if (holder.ProgramList.getVisibility() == View.VISIBLE) {
                        holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                        holder.ProgramList.setVisibility(View.GONE);
                    } else {
                        holder.ProgramList.setVisibility(View.VISIBLE);
                        holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                    }
                }
            });

            holder.textViewTrainingName.setTextColor(Color.parseColor("#edbb57"));
            holder.rrrss.setOnClickListener(view -> showDialogofTraining(context, 0, 0, getIntent().getStringExtra("TeamName"), "", AthleteDataY.get(position).getUserId()));

            try {

                holder.textViewCount.setText(AthleteDataY.get(position).getAssingProgramDetails().size() + "");
                //programAdapter = ;
                holder.ProgramList.setAdapter(new ProgramAdapterX(context, AthleteDataY.get(position).getAssingProgramDetails(), position));
            } catch (Exception x) {

            }

//            if (AthleteDataY.get(position).getFirstName().equalsIgnoreCase("")) {
//                try {
//                    holder.textViewTrainingName.setText(AthleteDataY.get(position).getEmailId().substring(0, AthleteDataY.get(position).getEmailId().indexOf("@")));
//                } catch (Exception x) {
//
//                }
//            } else {
//                holder.textViewTrainingName.setText(AthleteDataY.get(position).getLastName() + " " + AthleteDataY.get(position).getFirstName());
//            }
            holder.textViewTrainingName.setText(UtilityClass.getNameAthlete(AthleteDataY.get(position).getFirstName(), AthleteDataY.get(position).getLastName(), AthleteDataY.get(position).getEmailId()));


            //holder.rLayoutForTraining.setPadding(5,5,5,5)
            //holder.textViewTrainingName.setPadding(25, 35, 0, 35);
        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = AthleteDataY.size();
            } catch (Exception c) {

            }
            return count;
        }


        public void filter(String s) {

            String text = s.toLowerCase();
            if (text.length() == 0) {
                AthleteDataY = AthleteDataX;
            } else {
                AthleteDataY = new ArrayList<SelectedAthleteDEtail>();
                for (int i = 0; i < AthleteDataX.size(); i++) {
                    String name = UtilityClass.getNameAthlete(AthleteDataX.get(i).getFirstName(), AthleteDataX.get(i).getLastName(), AthleteDataX.get(i).getEmailId());
                    if (name.toLowerCase().contains(text)) {
                        AthleteDataY.add(AthleteDataX.get(i));
                    }
                }
            }
            notifyDataSetChanged();
        }
    }


    public class ProgramAdapterX extends RecyclerView.Adapter<TrainingScreen> {
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();
        List<AssingProgramDetail> trainingProgramDetail;
        int positionofAthleteDataX = 0;
        private Context context;
        private LayoutInflater vi;


        public ProgramAdapterX(Context context, List<AssingProgramDetail> trainingProgramDetail, int positionofAthleteDataX) {
            this.context = context;
            binderHelper.setOpenOnlyOne(true);
            this.trainingProgramDetail = trainingProgramDetail;
            this.positionofAthleteDataX = positionofAthleteDataX;

        }

        @Override
        public TrainingScreen onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_view_items_training, parent, false);
            return new TrainingScreen(itemView);
        }

        @Override
        public void onBindViewHolder(TrainingScreen holder, final int position) {
            holder.folderIcon.setVisibility(View.GONE);
            holder.rlayoutCount.setVisibility(View.GONE);
            holder.arrow.setVisibility(View.GONE);
            holder.DateofProgram.setVisibility(View.VISIBLE);
            holder.DateofProgram.setText(trainingProgramDetail.get(position).getStartDate());

            holder.DateofProgram.setPadding(0, 0, 20, 0);


            binderHelper.bind(holder.rLayoutForTraining, String.valueOf(position));

            holder.Assign.setVisibility(View.GONE);
            holder.Copy.setVisibility(View.GONE);
            holder.Edit.setVisibility(View.GONE);
            holder.Rename.setVisibility(View.GONE);

            holder.textViewTrainingName.setTextColor(Color.parseColor("#cdcdcd"));
            holder.DateofProgram.setTextColor(Color.parseColor("#cdcdcd"));

            holder.DateofProgram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trainingProgramDetailPosition = position;
                    positionofTeamMain = positionofAthleteDataX;
                    DialogFragment dialogfragment = new DatePickerDialogClass("", trainingProgramDetail.get(position).getAssignProgramId());
                    dialogfragment.show(getFragmentManager(), "Date Picker Dialog");
                }
            });
            holder.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    webServices.deleteAssignProgram(trainingProgramDetail.get(position).getAssignProgramId(), context, SelectAthlete.this);
                    trainingProgramDetail.remove(position);
                    holder.rLayoutForTraining.close(true);
                    notifyDataSetChanged();
                    //updateAssignProgramStartDateAthlete
                }
            });

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.rrrss.getLayoutParams();
            layoutParams.setMargins(10, 5, 10, 5);

            holder.textViewTrainingName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            holder.textViewTrainingName.setText(trainingProgramDetail.get(position).getProgramName());
            //holder.rLayoutForTraining.setPadding(5,5,5,5)
            //holder.textViewTrainingName.setPadding(25, 10, 0, 10);
        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = trainingProgramDetail.size();
            } catch (Exception c) {

            }
            return count;
        }


    }
}
