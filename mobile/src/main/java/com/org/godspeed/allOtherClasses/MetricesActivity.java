//package com.org.godspeed.allOtherClasses;
//
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.org.godspeed.R;
//import com.org.godspeed.fragments.AthleteProfileFragment;
//import com.org.godspeed.utility.CustomTypeface;
//import com.org.godspeed.utility.FlipAnimation;
//
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//public class MetricesActivity extends Activity {
//
//    private ImageView imageViewAppIconForAnimation;
//    private RelativeLayout rLayoutForWorkoutContainer, rLayoutForMetricsContainer, rLayoutforAnimation, rLayoutForWorkoutExerciseList, rLayoutForRawHRGraph, rLayoutForWorkoutExerciseSetsList, rLayoutForBeginWorkout;
//    private RelativeLayout rLayoutForPrep, rLayoutForMovement, rLayoutForElasticity, rLayoutForMovementSkill, rLayoutGraphRawHR;
//    private RelativeLayout rLayoutForWorkoutText,rLayoutForWorkoutTitle,rLAyoutForWorkoutOptionTitle,rLayoutForExerciseSetTitle;
//    private RelativeLayout rLayoutForVelocity,rLayoutForJumpHeight,rLayoutForRSI,rLayoutForAgilityTime,rLayoutForVerticalJump,rLayoutForSprintTime;
//    private RelativeLayout rLayoutForJumpHeightGraph;
//    private ListView listViewCalendar;
//    private ListViewAdapter adapter;
//    private Context context;
//    private GraphClass graphClass;
//    public static boolean isAutoCloseScreenAfterTimerComplete;
//    public static boolean isWorkoutComplete=false;
//    private String currentLayoutNameForFlipAnimation = "";
//    private TextView textViewWorkoutOptionTitle,textViewExerciseSetTitle,textViewMonthYearWithTitle,textViewRawHRGraphTitle;
//    private GridView gridViewExcercise,gridViewExcerciseSetParameters;
//    private GridViewAdapter gridAdapter;
//    private GridViewAdapterForExerciseSet gridAdapterForExerciseSet;
//    private String nameOfMonth="";
//    private int daysInMonth = 0,currentDate=0;
//    private Calendar cal;
//    private TextView textViewScreenName,textViewActivationText,textViewActivationMinutes,textViewDurationAthlete,textViewDurationPrecentage,textViewMetrics,textViewWorkout,textViewVelocity;
//    private TextView textViewJumpHeight,textViewRSI,textViewAgilityTime,textViewVerticalJump,textViewSprintTime,textViewActivationWorkout,textViewPrep,textViewMovement,textViewElasticity,textviewMovementSkill;
//    private TextView textViewEdit,textViewBeginWorkout;
//
//    private View.OnClickListener myClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            switch (v.getId()) {
//                case R.id.imageViewAppIconForAnimation:
//
//                    if(currentLayoutNameForFlipAnimation.equalsIgnoreCase("workout_option"))
//                    {
//
//                        rLayoutForMetricsContainer = (RelativeLayout) findViewById(R.id.rLayoutForMetricsContainer);
//                        rLayoutForWorkoutContainer = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutContainer);
//                        rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                        final FlipAnimation flipAnimation = new FlipAnimation(rLayoutForWorkoutContainer,rLayoutForMetricsContainer);
//
//                        if (rLayoutForMetricsContainer.getVisibility() == View.GONE) {
//                            flipAnimation.reverse();
//                        }
//                        rLayoutforAnimation.startAnimation(flipAnimation);
//                    }
//                    else {
//
//                    }
//                    break;
//                case R.id.rLayoutForVelocity:
//                    textViewRawHRGraphTitle.setText(getString(R.string.raw_hr).toUpperCase());
//                    textViewMonthYearWithTitle.setText("10:50pm - 8:02am");
//                    rLayoutGraphRawHR.removeAllViews();
//                    graphClass = new GraphClass();
//                    String[] legendArr = {"1d", "2d", "3d", "4d", "5d"};//,"6d","7d","8d","9d","10d"};//,"5"};
//                    float[] graph1 = {100, 20, 60, 40, 20};//,35,55,15,90,25};
//
//                    LineGraphVO graphRawHR = graphClass.makeLinearGraphAllSetting(legendArr,graph1,100,20);
//                    rLayoutGraphRawHR.addView(new LineGraphView(context, graphRawHR));
//
//
//
//
//                    currentLayoutNameForFlipAnimation="velocity_graph";
//                    rLayoutForRawHRGraph= (RelativeLayout) findViewById(R.id.rLayoutForRawHRGraph);
//                    rLayoutForMetricsContainer= (RelativeLayout) findViewById(R.id.rLayoutForMetricsContainer);
//                    rLayoutForMetricsContainer.setVisibility(View.VISIBLE);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    FlipAnimation flipExerciseListAnimation = new FlipAnimation(rLayoutForMetricsContainer,rLayoutForRawHRGraph);
//
//                    if (rLayoutForMetricsContainer.getVisibility() == View.GONE) {
//                        flipExerciseListAnimation.reverse();
//                    }
//                    rLayoutforAnimation.startAnimation(flipExerciseListAnimation);
//                    break;
//                case R.id.rLayoutForJumpHeight:
//
//                    textViewRawHRGraphTitle.setText(getString(R.string.jump_height).toUpperCase());
//                    textViewMonthYearWithTitle.setText("June 2017");
//                    graphClass = new GraphClass();
//                    legendArr = new String[]{"1d", "2d", "3d", "4d", "5d","6d","7d","8d","9d","10d"};//,"5"};
//                    graph1 = new float[]{1, 2.75f, 4, 2.1f, 0.50f,3,1,2,5,2.5f};
//
//                    LineGraphVO graphJumpHight = graphClass.makeLinearGraphAllSetting(legendArr,graph1,5,1);
//                    rLayoutGraphRawHR.removeAllViews();
//                    rLayoutGraphRawHR.addView(new LineGraphView(context, graphJumpHight));
//
//
//
//
//                    currentLayoutNameForFlipAnimation="jumph_graph";
//                    rLayoutForRawHRGraph= (RelativeLayout) findViewById(R.id.rLayoutForRawHRGraph);
//                    rLayoutForMetricsContainer= (RelativeLayout) findViewById(R.id.rLayoutForMetricsContainer);
//                    rLayoutForMetricsContainer.setVisibility(View.VISIBLE);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    flipExerciseListAnimation = new FlipAnimation(rLayoutForMetricsContainer,rLayoutForRawHRGraph);
//
//                    if (rLayoutForMetricsContainer.getVisibility() == View.GONE) {
//                        flipExerciseListAnimation.reverse();
//                    }
//                    rLayoutforAnimation.startAnimation(flipExerciseListAnimation);
//                    break;
//                case R.id.rLayoutForJumpHeightGraph:
//                    currentLayoutNameForFlipAnimation="back_from_graph";
//                    rLayoutForRawHRGraph= (RelativeLayout) findViewById(R.id.rLayoutForRawHRGraph);
//                    rLayoutForMetricsContainer= (RelativeLayout) findViewById(R.id.rLayoutForMetricsContainer);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    flipExerciseListAnimation = new FlipAnimation(rLayoutForMetricsContainer,rLayoutForRawHRGraph);
//
//                    if (rLayoutForMetricsContainer.getVisibility() == View.GONE) {
//                        flipExerciseListAnimation.reverse();
//                    }
//                    rLayoutforAnimation.startAnimation(flipExerciseListAnimation);
//                    break;
//                case R.id.rLayoutForPrep:
//                    textViewWorkoutOptionTitle.setText(getString(R.string.activation).toUpperCase() + " - " + getString(R.string.prep).toUpperCase() + " " + getString(R.string.workout).toUpperCase()) ;
//                    currentLayoutNameForFlipAnimation="workout_option_button";
//                    rLayoutForWorkoutExerciseList = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseList);
//                    rLayoutForWorkoutContainer = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutContainer);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    flipExerciseListAnimation = new FlipAnimation(rLayoutForWorkoutExerciseList,rLayoutForWorkoutContainer);
//
//                    if (rLayoutForWorkoutExerciseList.getVisibility() == View.GONE) {
//                        flipExerciseListAnimation.reverse();
//                    }
//                    rLayoutforAnimation.startAnimation(flipExerciseListAnimation);
//                    break;
//                case R.id.rLAyoutForWorkoutOptionTitle:
//                    currentLayoutNameForFlipAnimation="workout_option_title";
//                    rLayoutForWorkoutExerciseList = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseList);
//                    rLayoutForWorkoutContainer = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutContainer);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    FlipAnimation flipToWorkoutAnimation = new FlipAnimation(rLayoutForWorkoutExerciseList, rLayoutForWorkoutContainer);
//
//                    if (rLayoutForWorkoutExerciseList.getVisibility() == View.GONE) {
//                        flipToWorkoutAnimation.reverse();
//
//                    }
//
//                    rLayoutforAnimation.startAnimation(flipToWorkoutAnimation);
//                    break;
//                case R.id.rLayoutForExerciseSetTitle:
//                    currentLayoutNameForFlipAnimation="workout_exercise_set_title";
//                    rLayoutForWorkoutExerciseList = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseList);
//                    rLayoutForWorkoutExerciseSetsList= (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseSetsList);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    flipToWorkoutAnimation = new FlipAnimation(rLayoutForWorkoutExerciseSetsList,rLayoutForWorkoutExerciseList);
//
//                    if (rLayoutForWorkoutExerciseSetsList.getVisibility() == View.GONE) {
//                        flipToWorkoutAnimation.reverse();
//
//                    }
//
//                    rLayoutforAnimation.startAnimation(flipToWorkoutAnimation);
//                    break;
//                case R.id.rLayoutForMovement:
//                    break;
//                case R.id.rLayoutForElasticity:
//                    break;
//                case R.id.rLayoutForMovementSkill:
//                    break;
//                case R.id.rLayoutForBeginWorkout:
//                    startActivity(new Intent(context, AthleteExerciseSetActivity.class));
//                    overridePendingTransition(R.anim.exit, R.anim.enter);
//                    break;
//                case R.id.rLayoutForWorkoutText:
//                    currentLayoutNameForFlipAnimation="workout_option";
//                    rLayoutForWorkoutExerciseList = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseList);
//                    rLayoutForWorkoutContainer = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutContainer);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    FlipAnimation flipWorkoutListAnimation = new FlipAnimation(rLayoutForWorkoutContainer, rLayoutForWorkoutExerciseList);
//
//                    if (rLayoutForWorkoutContainer.getVisibility() == View.GONE) {
//                        flipWorkoutListAnimation.reverse();
//                    }
//                    rLayoutforAnimation.startAnimation(flipWorkoutListAnimation);
//                    flipWorkoutListAnimation = null;
//                    break;
//                case R.id.rLayoutForWorkoutTitle:
//                    currentLayoutNameForFlipAnimation="metrics_layout";
//                    rLayoutForMetricsContainer = (RelativeLayout) findViewById(R.id.rLayoutForMetricsContainer);
//                    rLayoutForWorkoutContainer = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutContainer);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    final FlipAnimation flipAnimation = new FlipAnimation(rLayoutForWorkoutContainer,rLayoutForMetricsContainer);
//
//                    if (rLayoutForMetricsContainer.getVisibility() == View.GONE) {
//                        flipAnimation.reverse();
//                    }
//                    rLayoutforAnimation.startAnimation(flipAnimation);
//                    break;
//            }
//
//        }
//    };
//
//    private void initializeTextView() {
//
//        textViewScreenName = (TextView) findViewById(R.id.textViewScreenName);
//        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//
//        textViewActivationText = (TextView) findViewById(R.id.textViewActivationText);
//        textViewActivationText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewActivationMinutes = (TextView) findViewById(R.id.textViewActivationMinutes);
//        textViewActivationMinutes.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewDurationAthlete = (TextView) findViewById(R.id.textViewDurationAthlete);
//        textViewDurationAthlete.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewDurationPrecentage = (TextView) findViewById(R.id.textViewDurationPrecentage);
//        textViewDurationPrecentage.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewMetrics = (TextView) findViewById(R.id.textViewMetrics);
//        textViewMetrics.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//
//        textViewWorkout = (TextView) findViewById(R.id.textViewWorkout);
//        textViewWorkout.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewVelocity = (TextView) findViewById(R.id.textViewVelocity);
//        textViewVelocity.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewJumpHeight = (TextView) findViewById(R.id.textViewJumpHeight);
//        textViewJumpHeight.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewRSI = (TextView) findViewById(R.id.textViewRSI);
//        textViewRSI.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewAgilityTime = (TextView) findViewById(R.id.textViewAgilityTime);
//        textViewAgilityTime.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewVerticalJump = (TextView) findViewById(R.id.textViewVerticalJump);
//        textViewVerticalJump.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewSprintTime = (TextView) findViewById(R.id.textViewSprintTime);
//        textViewSprintTime.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewActivationWorkout = (TextView) findViewById(R.id.textViewActivationWorkout);
//        textViewActivationWorkout.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewPrep = (TextView) findViewById(R.id.textViewPrep);
//        textViewPrep.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewMovement = (TextView) findViewById(R.id.textViewMovement);
//        textViewMovement.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewElasticity = (TextView) findViewById(R.id.textViewElasticity);
//        textViewElasticity.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textviewMovementSkill = (TextView) findViewById(R.id.textviewMovementSkill);
//        textviewMovementSkill.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//        textViewEdit = (TextView) findViewById(R.id.textViewEdit);
//        textViewEdit.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//
//        textViewBeginWorkout = (TextView) findViewById(R.id.textViewBeginWorkout);
//        textViewBeginWorkout.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (isAutoCloseScreenAfterTimerComplete) {
//            // this code will work only when timer will complete all piller workout.
//            isAutoCloseScreenAfterTimerComplete = false;
//            AthleteProfileFragment.isAutoCloseScreenAfterTimerComplete = true;
//            onBackPressed();
//            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_metrics);
//        context = this;
//        isAutoCloseScreenAfterTimerComplete = false;
//        imageViewAppIconForAnimation = (ImageView) findViewById(R.id.imageViewAppIconForAnimation);
//        Log.e("Screen", "Metrices");
//        cal = Calendar.getInstance();
//        daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
//        nameOfMonth = month_date.format(cal.getTime());
//        month_date = new SimpleDateFormat("DD");
//        currentDate = Integer.parseInt(month_date.format(cal.getTime()));
//
//        listViewCalendar = (ListView) findViewById(R.id.listViewCalendar);
//        listViewCalendar.setDivider(null);
//        adapter = new ListViewAdapter(context);
//        listViewCalendar.setAdapter(adapter);
//        listViewCalendar.setSelection(currentDate - 1);
//
//        imageViewAppIconForAnimation.setOnClickListener(myClickListener);
//
//
//        rLayoutForPrep = (RelativeLayout) findViewById(R.id.rLayoutForPrep);
//        rLayoutForPrep.setOnClickListener(myClickListener);
//
//        rLayoutForVelocity = (RelativeLayout) findViewById(R.id.rLayoutForVelocity);
//        rLayoutForVelocity.setOnClickListener(myClickListener);
//
//        rLayoutForJumpHeight = (RelativeLayout) findViewById(R.id.rLayoutForJumpHeight);
//        rLayoutForJumpHeight.setOnClickListener(myClickListener);
//
//        rLayoutForJumpHeightGraph = (RelativeLayout) findViewById(R.id.rLayoutForJumpHeightGraph);
//        rLayoutForJumpHeightGraph.setOnClickListener(myClickListener);
//
//        rLayoutForWorkoutText = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutText);
//        rLayoutForWorkoutText.setOnClickListener(myClickListener);
//
//        rLayoutForWorkoutTitle = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutTitle);
//        rLayoutForWorkoutTitle.setOnClickListener(myClickListener);
//
//        rLAyoutForWorkoutOptionTitle = (RelativeLayout) findViewById(R.id.rLAyoutForWorkoutOptionTitle);
//        rLAyoutForWorkoutOptionTitle.setOnClickListener(myClickListener);
//
//        rLayoutForExerciseSetTitle = (RelativeLayout) findViewById(R.id.rLayoutForExerciseSetTitle);
//        rLayoutForExerciseSetTitle.setOnClickListener(myClickListener);
//
//        textViewWorkoutOptionTitle = (TextView) findViewById(R.id.textViewWorkoutOptionTitle);
//
//        textViewExerciseSetTitle = (TextView) findViewById(R.id.textViewExerciseSetTitle);
//
//        textViewMonthYearWithTitle = (TextView) findViewById(R.id.textViewMonthYearWithTitle);
//        textViewRawHRGraphTitle = (TextView) findViewById(R.id.textViewRawHRGraphTitle);
//
//        gridViewExcercise = (GridView) findViewById(R.id.gridViewExcercise);
//        gridAdapter = new GridViewAdapter(context);
//        gridViewExcercise.setAdapter(gridAdapter);
//
//        gridViewExcerciseSetParameters = (GridView) findViewById(R.id.gridViewExcerciseSetParameters);
//        gridAdapterForExerciseSet = new GridViewAdapterForExerciseSet(context);
//        gridViewExcerciseSetParameters.setAdapter(gridAdapterForExerciseSet);
//
//        rLayoutForMovement = (RelativeLayout) findViewById(R.id.rLayoutForMovement);
//        rLayoutForElasticity = (RelativeLayout) findViewById(R.id.rLayoutForElasticity);
//        rLayoutForMovementSkill = (RelativeLayout) findViewById(R.id.rLayoutForMovementSkill);
//
//        rLayoutForBeginWorkout = (RelativeLayout) findViewById(R.id.rLayoutForBeginWorkout);
//        rLayoutForBeginWorkout.setOnClickListener(myClickListener);
//
//        rLayoutGraphRawHR = (RelativeLayout) findViewById(R.id.rLayoutGraphRawHR);
//
//        /*graphClass = new GraphClass();
//        String[] legendArr = {"1d", "2d", "3d", "4d", "5d"};//,"6d","7d","8d","9d","10d"};//,"5"};
//        float[] graph1 = {100, 20, 60, 40, 20};//,35,55,15,90,25};
//
//        LineGraphVO graphRawHR = graphClass.makeLinearGraphAllSetting(legendArr,graph1,100,20);
//        rLayoutGraphRawHR.addView(new LineGraphView(context, graphRawHR));
//
//        LineGraphVO graphJumpHight = graphClass.makeLinearGraphAllSetting(legendArr,graph1,100,20);
////        rLayoutGraphRawHR.addView(new LineGraphView(context, graphJumpHight));*/
//
//        rLayoutForMetricsContainer = (RelativeLayout) findViewById(R.id.rLayoutForMetricsContainer);
//        rLayoutForBeginWorkout = (RelativeLayout) findViewById(R.id.rLayoutForBeginWorkout);
//
//        if (!isWorkoutComplete) {
//            rLayoutForMetricsContainer.setVisibility(View.GONE);
//            rLayoutForBeginWorkout.setVisibility(View.VISIBLE);
//        } else {
//            rLayoutForMetricsContainer.setVisibility(View.VISIBLE);
//            rLayoutForBeginWorkout.setVisibility(View.GONE);
//        }
//
///*        rLayoutForMetricsContainer.setVisibility(View.VISIBLE);
//        rLayoutForBeginWorkout.setVisibility(View.GONE);*/
//
//        initializeTextView();
//
//    }
//
//    public class ViewHolder {
//        TextView textViewDate;
//        RelativeLayout rLayoutMain;
//        ImageView imageViewSelectionImage;
//    }
//
//    public class ListViewAdapter extends BaseAdapter {
//        private Context context;
//        private LayoutInflater vi;
//        private ViewHolder holder;
//
//        public ListViewAdapter(Context context) {
//            this.context = context;
//            vi = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//
//        }
//
//        @Override
//        public int getCount() {
//            return daysInMonth;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                holder = new ViewHolder();
//                convertView = vi.inflate(R.layout.list_items_calendar, null);
//                holder.textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
//                holder.textViewDate.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
//
//
//                holder.imageViewSelectionImage = (ImageView) convertView.findViewById(R.id.imageViewSelectionImage);
//                holder.rLayoutMain = (RelativeLayout) convertView.findViewById(R.id.rLayoutMain);
//
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
////            holder.textViewTeamName.setText(myTeamArray[position]);
//
//            if ((position+1) == currentDate) {
//                holder.imageViewSelectionImage.setImageResource(R.drawable.yellow_round_button_calendar);
//                holder.textViewDate.setTextColor(Color.BLACK);
//
//            } else {
//                holder.imageViewSelectionImage.setImageDrawable(null);
//                holder.textViewDate.setTextColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));
//
//            }
//            holder.textViewDate.setText(1 + position + " " + nameOfMonth);
//
//            holder.rLayoutMain.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//            return convertView;
//        }
//    }
//
//
//    public class GridViewHolder {
//        TextView textViewExerciseName;
//        RelativeLayout rLayoutForElasticity;
//        ImageView imageViewBackgroundButton;
//    }
//
//    public class GridViewAdapter extends BaseAdapter {
//        private Context context;
//        private LayoutInflater vi;
//        private GridViewHolder holder;
//
//        public GridViewAdapter(Context context) {
//            this.context = context;
//            vi = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public int getCount() {
//            return 10;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                holder = new GridViewHolder();
//                convertView = vi.inflate(R.layout.grid_items_exercise, null);
//                holder.textViewExerciseName = (TextView) convertView.findViewById(R.id.textViewExerciseName);
//                holder.textViewExerciseName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//
//                holder.imageViewBackgroundButton= (ImageView) convertView.findViewById(R.id.imageViewBackgroundButton);
//                holder.rLayoutForElasticity = (RelativeLayout) convertView.findViewById(R.id.rLayoutForElasticity);
//
//                convertView.setTag(holder);
//            } else {
//                holder = (GridViewHolder) convertView.getTag();
//            }
//
//
//            holder.textViewExerciseName.setText(getString(R.string.exercise).toUpperCase() + " " + (position + 1));
//
//            holder.rLayoutForElasticity.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    textViewExerciseSetTitle.setText(getString(R.string.activation).toUpperCase() + " " + getString(R.string.workout).toUpperCase() + " " + getString(R.string.exercise).toUpperCase() + " " + (position + 1));
//
//                    currentLayoutNameForFlipAnimation="workout_exercise_list_button";
//                    rLayoutForWorkoutExerciseList = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseList);
//                    rLayoutForWorkoutExerciseSetsList = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseSetsList);
//                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
//                    final FlipAnimation flipExerciseListAnimation = new FlipAnimation(rLayoutForWorkoutExerciseSetsList,rLayoutForWorkoutExerciseList);
//
//                    if (rLayoutForWorkoutExerciseSetsList.getVisibility() == View.GONE) {
//                        flipExerciseListAnimation.reverse();
////                        rLayoutForWorkoutExerciseList.setVisibility(View.GONE);
//                    }
////                    else
////                    {
////                        rLayoutForWorkoutExerciseList.setVisibility(View.VISIBLE);
////                    }
//                    rLayoutforAnimation.startAnimation(flipExerciseListAnimation);
//                }
//            });
//            return convertView;
//        }
//    }
//
//    public class GridViewHolderForExerciseSet {
//        TextView textViewExerciseSetParameters,textViewExerciseRepsParameters,textViewExerciseWtParameters,textViewExercisePerParameters;
//        ImageView imageViewAddExercise;
//        LinearLayout lLayoutMain;
//
//    }
//
//    public class GridViewAdapterForExerciseSet extends BaseAdapter {
//        private Context context;
//        private LayoutInflater vi;
//        private GridViewHolderForExerciseSet holder;
//
//        public GridViewAdapterForExerciseSet(Context context) {
//            this.context = context;
//            vi = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public int getCount() {
//            return 10;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                holder = new GridViewHolderForExerciseSet();
//                convertView = vi.inflate(R.layout.list_view_items_exercise_set, null);
////                holder.textViewExerciseSetParameters = (TextView) convertView.findViewById(R.id.textViewExerciseSetParameters);
////                holder.textViewExerciseSetParameters.setTypeface(CustomTypeface.load_Montserrat_Light_Fonts(context));
////
//                holder.lLayoutMain = (LinearLayout) convertView.findViewById(R.id.lLayoutMain);
//                holder.lLayoutMain.setWeightSum(4f);
//                holder.imageViewAddExercise= (ImageView) convertView.findViewById(R.id.imageViewAddExercise);
//                holder.imageViewAddExercise.setVisibility(View.GONE);
////                holder.rLayoutForElasticity = (RelativeLayout) convertView.findViewById(R.id.rLayoutForElasticity);
//
//                convertView.setTag(holder);
//            } else {
//                holder = (GridViewHolderForExerciseSet) convertView.getTag();
//            }
//
//
////            holder.textViewExerciseName.setText(getString(R.string.exercise).toUpperCase() + " " + (position + 1));
////
////            holder.rLayoutForElasticity.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    textViewExerciseSetTitle.setText(getString(R.string.activation).toUpperCase() + " " + getString(R.string.workout).toUpperCase() + " " + getString(R.string.exercise).toUpperCase() + " " + (position + 1));
////
////                    currentLayoutNameForFlipAnimation="workout_exercise_list_button";
////                    rLayoutForWorkoutExerciseList = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseList);
////                    rLayoutForWorkoutExerciseSetsList = (RelativeLayout) findViewById(R.id.rLayoutForWorkoutExerciseSetsList);
////                    rLayoutforAnimation = (RelativeLayout) findViewById(R.id.rLayoutforAnimation);
////                    final FlipAnimation flipExerciseListAnimation = new FlipAnimation(rLayoutForWorkoutExerciseSetsList,rLayoutForWorkoutExerciseList);
////
////                    if (rLayoutForWorkoutExerciseSetsList.getVisibility() == View.GONE) {
////                        flipExerciseListAnimation.reverse();
//////                        rLayoutForWorkoutExerciseList.setVisibility(View.GONE);
////                    }
//////                    else
//////                    {
//////                        rLayoutForWorkoutExerciseList.setVisibility(View.VISIBLE);
//////                    }
////                    rLayoutforAnimation.startAnimation(flipExerciseListAnimation);
////                }
////            });
//            return convertView;
//        }
//    }
//}
