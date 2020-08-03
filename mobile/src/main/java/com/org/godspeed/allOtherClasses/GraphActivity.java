package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.android.volley.VolleyLog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult;
import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthPermissionManager;
import com.samsung.android.sdk.healthdata.HealthResultHolder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.org.godspeed.utility.UtilityClass.ONE_DAY;
import static com.org.godspeed.utility.UtilityClass.ONE_WEEK;
import static com.org.godspeed.utility.UtilityClass.getLastdateofyear;
import static com.org.godspeed.utility.UtilityClass.getMonthDateFirstdate;
import static com.org.godspeed.utility.UtilityClass.getMonthDateLastdate;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfToday;
import static com.org.godspeed.utility.UtilityClass.getStartTimeOfWeek;
import static com.org.godspeed.utility.UtilityClass.getfirstdateofyear;
import static java.lang.Float.NaN;
import static java.lang.String.format;

public class GraphActivity extends Activity implements GodSpeedInterface {

    PieChart pieChart;
    List<Entry> entries;
    List<String> PieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;
    LinearLayout LayoutDay, LayoutWeek, LayoutMonth, LayoutYear;
    TextView DayText, WeekText, YearText, MonthText;
    Context context;
    ImageView imageViewBackArrow;
    private float[] yData = {25.3f, 10.3f, 66.3f, 44.3f, 16.3f, 46.3f, 23.3f};
    private String[] xData = {"med", "hamza", "mohammed", "", "", "", ""};
    private HealthDataStore mStore;
    private float Walk = 0f;
    private float SleepTime = 0f;
    private float Distance = 0f;
    private final HealthResultHolder.ResultListener<HealthPermissionManager.PermissionResult> mPermissionListener = result -> {
        Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = result.getResultMap();
        // Show a permission alarm and clear step count if permissions are not acquired
        if (resultMap.containsValue(Boolean.FALSE)) {
            showPermissionAlarmDialog();
        } else {
            // Get the daily step count of a particular day and display it
            if (isPermissionAcquired()) {
                //getDataNow();
            }
        }
    };
    private final HealthDataStore.ConnectionListener mConnectionListener = new HealthDataStore.ConnectionListener() {
        @Override
        public void onConnected() {
            Log.e(VolleyLog.TAG, "onConnected");
            if (isPermissionAcquired()) {
                if (isPermissionAcquired()) {

                    long startTime = getStartTimeOfToday();
                    long endTime = startTime + ONE_DAY;
//
//              Samhealth myAsyncTasks = new Samhealth(startTime,endTime,"Day",mStore);
//            myAsyncTasks.execute();
                    getSteps(startTime, endTime, "Day");
                    //readTodayHeartRate(startTime, endTime, "Day");
                }
            } else {
                requestPermission();
            }
        }

        @Override
        public void onConnectionFailed(HealthConnectionErrorResult error) {
            Log.e(VolleyLog.TAG, "onConnectionFailed");
            showConnectionFailureDialog(error);
        }

        @Override
        public void onDisconnected() {
            Log.e(VolleyLog.TAG, "onDisconnected");
            if (!isFinishing()) {
                mStore.connectService();
            }
        }
    };
    private String TAG = "TAGATAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph2);


        context = this;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        pieChart = findViewById(R.id.PieChartForHealth);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);


        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
        });
        mStore = new HealthDataStore(context, mConnectionListener);
        mStore.connectService();

        //pieChart.setDescription(" Sales by employee In thousands $");
        pieChart.setRotationEnabled(true);
        //pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);

        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);

        //addDataSet();


        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.e(VolleyLog.TAG, "onValueSelected: value selected from chart");
                Log.d(VolleyLog.TAG, "onValueSelected: " + e.toString());
                Log.e(VolleyLog.TAG, "onValueSelected: " + h.toString());
            }

            @Override
            public void onNothingSelected() {

            }
        });

        LayoutDay = findViewById(R.id.LayoutDay);
        LayoutWeek = findViewById(R.id.LayoutWeek);
        LayoutMonth = findViewById(R.id.LayoutMonth);
        LayoutYear = findViewById(R.id.LayoutYear);

        DayText = findViewById(R.id.DayText);
        WeekText = findViewById(R.id.WeekText);
        MonthText = findViewById(R.id.MonthText);
        YearText = findViewById(R.id.YearText);


        LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));
        DayText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

        LayoutDay.setOnClickListener(view1 -> {

            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_left_yellow));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));


            DayText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));


            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));
            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));

            long startTime = getStartTimeOfToday();
            long endTime = startTime + ONE_DAY;
//
//              Samhealth myAsyncTasks = new Samhealth(startTime,endTime,"Day",mStore);
//            myAsyncTasks.execute();
            getSteps(startTime, endTime, "Day");
            // readTodayHeartRate(startTime, endTime, "Day");
        });
        LayoutWeek.setOnClickListener(view1 -> {

            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));


            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));

            WeekText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));

            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));

            long startTime = getStartTimeOfWeek();

            long endTime = startTime + ONE_WEEK;
            getSteps(startTime, endTime, "Week");
            //readTodayHeartRate(startTime, endTime, "Week");
        });
        LayoutMonth.setOnClickListener(view1 -> {
            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackgroundColor(getResources().getColor(R.color.textColorYellow));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));


            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));


            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
            YearText.setTextColor(getResources().getColor(R.color.textColorYellow));
            long startTime = getMonthDateFirstdate();

            long endTime = getMonthDateLastdate();
            //UtilityClass.getStartTimeOfToday();
            getSteps(startTime, endTime, "Month");
            //readTodayHeartRate(startTime, endTime, "Month");
            //.getLocationOnScreen();
        });
        LayoutYear.setOnClickListener(view1 -> {
            LayoutDay.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutWeek.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious));
            LayoutYear.setBackground(ContextCompat.getDrawable(context, R.drawable.textviewradious_right_yellow));


            DayText.setTextColor(getResources().getColor(R.color.textColorYellow));


            WeekText.setTextColor(getResources().getColor(R.color.textColorYellow));

            MonthText.setTextColor(getResources().getColor(R.color.textColorYellow));


            YearText.setTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));

            long startTime = getfirstdateofyear();

            long endTime = getLastdateofyear();
            //UtilityClass.getStartTimeOfToday();
            getSteps(startTime, endTime, "Year");
            //readTodayHeartRate(startTime, endTime, "Year");
        });


    }

    private void addDataSet() {

        //legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        pieChart.clear();
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(SleepTime, "Sleep"));
        entries.add(new PieEntry(Distance, "Walk"));
        entries.add(new PieEntry(24.0f, "Exercise"));
        entries.add(new PieEntry(30.8f, "Food"));
        entries.add(new PieEntry(30.8f, "Reading"));
        entries.add(new PieEntry(10f, "Work"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FFAEF47B"));
        colors.add(Color.parseColor("#FFC33974"));
        colors.add(Color.parseColor("#FFF0BD7A"));
        colors.add(Color.parseColor("#C33974"));
        colors.add(Color.parseColor("#EE7287"));
        colors.add(Color.parseColor("#7ADCEF"));


        PieDataSet set = new PieDataSet(entries, "");


        //set.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        set.setValueTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
        //pieChart.setValueTextColor(Color.BLACK);
        set.setValueLinePart1OffsetPercentage(10.f);
        set.setValueLinePart1Length(0.43f);
        set.setValueLinePart2Length(.1f);
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(30f);
        //set.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        pieChart.setEntryLabelColor(Color.BLUE);


        set.setColors(colors);

        PieData data = new PieData(set);
//        data.setValueTextColor(getResources().getColor(R.color.color_black_for_toggle_selected));
//        data.setValueTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        data.setValueTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        pieChart.setEntryLabelColor(getResources().getColor(R.color.color_white));
        pieChart.setCenterTextTypeface(CustomTypeface.load_AGENCYR_Fonts(context));


        data.setValueTextSize(15f);
        pieChart.setData(data);


        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);

        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);

        legend.setFormSize(10f); // set the size of the legend forms/shapes
        legend.setTypeface(CustomTypeface.load_AGENCYR_Fonts(this));
        legend.setTextSize(12f);
        legend.setTextColor(getResources().getColor(R.color.textColorYellow));

        pieChart.invalidate();
        AddLineChartData();

    }

    private void AddLineChartData() {
        BarChart barChart = findViewById(R.id.BarChartForQuestionAire);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        //barChart.setDescription("");
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);

        XAxis xl = barChart.getXAxis();
        xl.setGranularity(1f);
        YAxis leftAxis = barChart.getAxisLeft();
        xl.setCenterAxisLabels(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(30f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true
        barChart.getAxisRight().setEnabled(false);

        //data
        float groupSpace = 0.04f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.46f; // x2 dataset
        // (0.46 + 0.02) * 2 + 0.04 = 1.00 -> interval per "group"

        int startYear = 1980;
        int endYear = 1985;


        List<BarEntry> yVals1 = new ArrayList<BarEntry>();
        List<BarEntry> yVals2 = new ArrayList<BarEntry>();


        for (int i = startYear; i < endYear; i++) {
            yVals1.add(new BarEntry(i, 0.4f));
        }

        for (int i = startYear; i < endYear; i++) {
            yVals2.add(new BarEntry(i, 0.7f));
        }


        BarDataSet set1, set2;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) barChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            // create 2 datasets with different types
            set1 = new BarDataSet(yVals1, "Company A");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "Company B");
            set2.setColor(Color.rgb(164, 228, 251));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);

            BarData data = new BarData(dataSets);
            barChart.setData(data);
        }

        barChart.getBarData().setBarWidth(barWidth);
        barChart.getXAxis().setAxisMinValue(startYear);
        barChart.groupBars(startYear, groupSpace, barSpace);
        barChart.invalidate();
    }

    @Override
    public void ApiResponse(String result) {

    }

    public void getSteps(long mCurrentStartTime, long mCurrentEndTime, String valueoftype) {
        HealthDataResolver.AggregateRequest request;
        HealthDataResolver resolver = new HealthDataResolver(mStore, null);
        HealthDataResolver.AggregateRequest.TimeGroupUnit TimeGroupUnit;
        String alias = "";
        String TileTypeX = HealthConstants.StepCount.CALORIE;
        int amount = 0;


        if (valueoftype.equalsIgnoreCase("Week")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.WEEKLY;
            alias = "week";
            amount = 1;
        } else if (valueoftype.equalsIgnoreCase("Month")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.MONTHLY;
            alias = "month";
            amount = 1;
        } else if (valueoftype.equalsIgnoreCase("Year")) {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.MONTHLY;
            alias = "month";
            amount = 6;
        } else {
            TimeGroupUnit = HealthDataResolver.AggregateRequest.TimeGroupUnit.HOURLY;
            alias = "day";
            amount = 24;
        }


        request = new HealthDataResolver.AggregateRequest.Builder()
                .setDataType(HealthConstants.StepCount.HEALTH_DATA_TYPE)
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.SUM,
                        HealthConstants.StepCount.DISTANCE, "distance")
                .addFunction(HealthDataResolver.AggregateRequest.AggregateFunction.SUM,
                        HealthConstants.StepCount.COUNT, "step")
                .setLocalTimeRange(HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET,
                        mCurrentStartTime, mCurrentEndTime)
                .setTimeGroup(TimeGroupUnit, amount,
                        HealthConstants.StepCount.TIME_OFFSET,
                        HealthConstants.StepCount.END_TIME, alias)
                .build();

        try {
            resolver.aggregate(request).setResultListener(healthData -> {
                Cursor c = healthData.getResultCursor();
                if (c != null) {
                    float distance = 0;
                    float calorie = 0;
                    float step = 0;
                    int m = 0;
                    while (c.moveToNext()) {
                        distance += Float.parseFloat(c.getString(c.getColumnIndex("distance")));
                        step += Float.parseFloat(c.getString(c.getColumnIndex("step")));
                        m = ++m;
                    }

                    Log.e(VolleyLog.TAG, "m" + m);

                    pieChart.setCenterText(getResources().getText(R.string.app_name) + "\n Steps " + step);
                    pieChart.setCenterTextTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
                    pieChart.setCenterTextSize(17f);
                    Distance = (float) (distance * 0.001);
                    Walk = step;
                    if (Walk <= 0 || Walk == NaN) {
                        Walk = 0f;
                    }
                    c.close();
                } else {
                    Log.d("TEST", "There is no result.");
                }
            });
        } catch (Exception e) {
            Log.d("TEST", "Aggregating health data fails.");
        }


        //addDataSet();

        readYesterdaySleep(mCurrentStartTime, mCurrentEndTime);
    }


    private boolean isPermissionAcquired() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Check whether the permissions that this application needs are acquired
            Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = pmsManager.isPermissionAcquired(generatePermissionKeySet());
            return !resultMap.containsValue(Boolean.FALSE);
        } catch (Exception e) {
            Log.e("", "Permission request fails.", e);
        }
        return false;
    }

    private void showPermissionAlarmDialog() {
        if (isFinishing()) {
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(R.string.notice)
                .setMessage(R.string.msg_perm_acquired)
                .setPositiveButton(R.string.ok, (dialogInterface, x) -> {
                    if (LoginScreen.CoachCheckAthlete == false) {

                        long startTime = getStartTimeOfToday();
                        long endTime = startTime + ONE_DAY;
//
//              Samhealth myAsyncTasks = new Samhealth(startTime,endTime,"Day",mStore);
//            myAsyncTasks.execute();
                        getSteps(startTime, endTime, "Day");
                        //readTodayHeartRate(startTime, endTime, "Day");
                    }
                });
        // .show();
    }

    private Set<HealthPermissionManager.PermissionKey> generatePermissionKeySet() {
        Set<HealthPermissionManager.PermissionKey> pmsKeySet = new HashSet<>();
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.StepCount.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.HeartRate.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.USER_PROFILE_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.Sleep.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.WaterIntake.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.OxygenSaturation.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        return pmsKeySet;
    }

    private void requestPermission() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Show user permission UI for allowing user to change options
            pmsManager.requestPermissions(generatePermissionKeySet(), GraphActivity.this)
                    .setResultListener(mPermissionListener);
        } catch (Exception e) {
            Log.e("", "Permission setting fails.", e);
        }
    }

    private void showConnectionFailureDialog(final HealthConnectionErrorResult error) {
        if (isFinishing()) {
            return;
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        if (error.hasResolution()) {
            switch (error.getErrorCode()) {
                case HealthConnectionErrorResult.PLATFORM_NOT_INSTALLED:
                    alert.setMessage(R.string.msg_req_install);
                    break;
                case HealthConnectionErrorResult.OLD_VERSION_PLATFORM:
                    alert.setMessage(R.string.msg_req_upgrade);
                    break;
                case HealthConnectionErrorResult.PLATFORM_DISABLED:
                    alert.setMessage(R.string.msg_req_enable);
                    break;
                case HealthConnectionErrorResult.USER_AGREEMENT_NEEDED:
                    alert.setMessage(R.string.msg_req_agree);
                    break;
                default:
                    alert.setMessage(R.string.msg_req_available);
                    break;
            }
        } else {
            alert.setMessage(R.string.msg_conn_not_available);
        }

        alert.setPositiveButton(R.string.ok, (dialog, id) -> {
            if (error.hasResolution()) {
                error.resolve(GraphActivity.this);
            }
        });

        if (error.hasResolution()) {
            alert.setNegativeButton(R.string.cancel, null);
        }

        alert.show();
    }

    // Sleep
    private void readYesterdaySleep(long startTime, long endTime) {
        HealthDataResolver resolver = new HealthDataResolver(mStore, null);

        // Set time range to all day yesterday
        HealthDataResolver.Filter filter = HealthDataResolver.Filter.and(HealthDataResolver.Filter.greaterThanEquals(HealthConstants.Sleep.END_TIME, startTime), HealthDataResolver.Filter.lessThanEquals(HealthConstants.Sleep.END_TIME, endTime));

        HealthDataResolver.ReadRequest request = new HealthDataResolver.ReadRequest.Builder()
                .setDataType(HealthConstants.Sleep.HEALTH_DATA_TYPE)
                .setProperties(new String[]{HealthConstants.Sleep.START_TIME, HealthConstants.Sleep.END_TIME, HealthConstants.Sleep.TIME_OFFSET})
                .setFilter(filter)
                .build();

        try {
            resolver.read(request).setResultListener(new HealthResultHolder.ResultListener<HealthDataResolver.ReadResult>() {
                @Override
                public void onResult(HealthDataResolver.ReadResult result) {
                    Log.e(VolleyLog.TAG, "Getting sleep...");

                    long start, end;
                    Sleep sleep = null;
                    Cursor c = null;
                    float SleepTimeX = 0f;
                    int CountOfDays = 0;
                    try {
                        c = result.getResultCursor();
                        if (c != null) {
                            if (c.getCount() == 0) {
                                Log.e(VolleyLog.TAG, "No sleep entry found.");
                            }
                            while (c.moveToNext()) {
                                start = c.getLong(c.getColumnIndex(HealthConstants.Sleep.START_TIME));
                                end = c.getLong(c.getColumnIndex(HealthConstants.Sleep.END_TIME));

                                sleep = new Sleep(start, end);
                                CountOfDays = ++CountOfDays;
                                SleepTimeX += Float.parseFloat(String.valueOf(sleep));
                            }
                            SleepTime = (SleepTimeX / CountOfDays);

                            if (SleepTime != NaN) {
                                SleepTime = 0f;
                            }
                            Log.e(VolleyLog.TAG, "onResult: " + SleepTime);

                        }
                    } finally {
                        if (c != null) {
                            c.close();
                        }
                    }

                    // Save to s health data object
                }
            });
        } catch (Exception e) {
            Log.e(VolleyLog.TAG, e.getClass().getName() + " - " + e.getMessage());
            Log.e(VolleyLog.TAG, "Getting sleep failed.");
        }
        addDataSet();
    }

    public class MyValueFormatter extends ValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0"); // use one decimal if needed
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            return mFormat.format(value) + ""; // e.g. append a dollar-sign
        }
    }

    public class Sleep {
        private long start, end;


        public Sleep(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            Date startDate = new Date(start);
            Date endDate = new Date(end);

            long difference = end - start;
            String differenceString = format("%d.%d",
                    TimeUnit.MILLISECONDS.toHours(difference),
                    TimeUnit.MILLISECONDS.toMinutes(difference) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(difference))
            );

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm, dd.MM.yyyy");

            return differenceString;
            //mSleepcount.setText("Today's sleep"+differenceString);
//            return "Went to bed at " + formatter.format(startDate)
//                    + ", woke up at " + formatter.format(endDate)
//                    + ", total hours of sleep " + differenceString;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }
    }


}
