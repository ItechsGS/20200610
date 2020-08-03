package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.ExerciseTypeClass;

import java.util.Locale;
import java.util.Vector;

public class ExerciseSetMetricsScreen extends Activity implements SearchView.OnQueryTextListener {

    int[] strExerciseTypeID = {R.string.weight, R.string.set, R.string.reps, R.string.height, R.string.max_lift, R.string.time, R.string.calories, R.string.round, R.string.distance};
    private Vector<ExerciseTypeClass> vectorExerciseType;
    private ListView listViewExerciseType;
    private ListViewAdapter adapter;
    private Context context;
    private SearchView searchViewExerciseType;
    private TextView textViewScreenName;
    private ImageView imageViewBackArrow, imageViewSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_exercise_type_listview);
        context = this;

        Log.e("Screen", "Exercise Set Metrics");
        textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setText(getString(R.string.add_set).toUpperCase());
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        searchViewExerciseType = findViewById(R.id.searchViewExerciseType);
        searchViewExerciseType.setOnQueryTextListener(this);

        int searchFrameId = searchViewExerciseType.getContext().getResources().getIdentifier("android:id/search_edit_frame", null, null);
        View searchFrame = searchViewExerciseType.findViewById(searchFrameId);
        searchFrame.setBackgroundResource(R.drawable.bg_white_rounded);

        int searchPlateId = searchViewExerciseType.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = findViewById(searchPlateId);
        searchPlate.setBackgroundResource(R.drawable.bg_white_rounded);

        int searchBarId = searchViewExerciseType.getContext().getResources().getIdentifier("android:id/search_bar", null, null);
        View searchBar = findViewById(searchBarId);
        searchBar.setBackgroundResource(R.drawable.bg_white_rounded);

        vectorExerciseType = new Vector<ExerciseTypeClass>();
        for (int i = 0; i < strExerciseTypeID.length; i++) {
            ExerciseTypeClass objExerciseType = new ExerciseTypeClass();
            objExerciseType.exerciseTypeName = getString(strExerciseTypeID[i]);
            objExerciseType.isSelected = false;
            vectorExerciseType.add(objExerciseType);
        }

        adapter = new ListViewAdapter(context);
        listViewExerciseType = findViewById(R.id.listViewExerciseType);
        listViewExerciseType.setAdapter(adapter);
        listViewExerciseType.setDivider(null);


        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });

        imageViewSave = findViewById(R.id.imageViewSave);
        imageViewSave.setImageResource(R.drawable.save_training);
        imageViewSave.setVisibility(View.VISIBLE);
        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaysWiseExerciseList.isExerciseDataSaved = true;
                onBackPressed();
                overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }


    public class ViewHolder {
        TextView textViewExerciseType;
        RelativeLayout rLayoutForExerciseType;
        ImageView imageViewNext, imageViewSelectUnSelectExerciseType;
    }

    public class ListViewAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater vi;
        private ViewHolder holder;

        private Vector<ExerciseTypeClass> vectorExerciseTypeLocal = new Vector<ExerciseTypeClass>();

        public ListViewAdapter(Context context) {
            this.context = context;
            vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fillVectorDefaultValues();

        }

        private void fillVectorDefaultValues() {
            for (int i = 0; i < vectorExerciseType.size(); i++) {
                this.vectorExerciseTypeLocal.add(vectorExerciseType.get(i));
            }
        }

        @Override
        public int getCount() {
            return this.vectorExerciseTypeLocal.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = vi.inflate(R.layout.layout_exercise_list_days_based, null);
                holder.textViewExerciseType = convertView.findViewById(R.id.textViewExerciseName);
                holder.textViewExerciseType.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

                holder.imageViewSelectUnSelectExerciseType = convertView.findViewById(R.id.imageViewSelectUnSelectExerciseType);
                holder.imageViewSelectUnSelectExerciseType.setVisibility(View.VISIBLE);
                holder.imageViewSelectUnSelectExerciseType.setImageResource(R.drawable.unselected_tick_icon);
                holder.imageViewSelectUnSelectExerciseType.setClickable(false);
                holder.rLayoutForExerciseType = convertView.findViewById(R.id.rLayoutForDaysName);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ExerciseTypeClass objExerciseType = this.vectorExerciseTypeLocal.get(position);
            holder.textViewExerciseType.setText(objExerciseType.exerciseTypeName);
            if (objExerciseType.isSelected) {
                holder.imageViewSelectUnSelectExerciseType.setImageResource(R.drawable.selected_tick_icon);
            } else {
                holder.imageViewSelectUnSelectExerciseType.setImageResource(R.drawable.unselected_tick_icon);
            }
            holder.rLayoutForExerciseType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,"Show Tick mark icon",Toast.LENGTH_LONG).show();
                    objExerciseType.isSelected = !objExerciseType.isSelected;
                    adapter.notifyDataSetChanged();
                }
            });

            return convertView;
        }

        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            this.vectorExerciseTypeLocal.clear();
            if (charText.length() == 0) {
                fillVectorDefaultValues();
            } else {
                for (int i = 0; i < vectorExerciseType.size(); i++) {
                    ExerciseTypeClass obj = vectorExerciseType.get(i);
                    if (obj.exerciseTypeName.toLowerCase(Locale.getDefault()).contains(charText)) {
                        this.vectorExerciseTypeLocal.add(obj);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }
}
