package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import android.widget.VideoView;

import com.android.volley.VolleyLog;
import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.ExerciseTypeClass;

import java.util.Locale;
import java.util.Vector;

public class ExerciseNameScreen extends Activity implements SearchView.OnQueryTextListener, MediaPlayer.OnCompletionListener {

    private Vector<ExerciseTypeClass> vectorExerciseType;
    private String[] strExerciseTypeID = {"Deadlift - Pos 4", "Deadlift - Pos 3", "Deadlift - Pos 2", "Deadlift - Pos 1", "Hang Power Clean - Pos 4", "Hang Power Clean - Pos 3", "Hang Power Clean - Pos 2", "Hang Power Clean - Pos 1", "Power Clean - Block 4"};
    private ListView listViewExerciseType;
    private ListViewAdapter adapter;
    private Context context;
    private SearchView searchViewExerciseType;
    private TextView textViewScreenName;
    private ImageView imageViewBackArrow;
    private VideoView videoViewPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_exercise_type_listview);
        context = this;
        Log.e("Screen", "Exercise Name screen");
        textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setText(getString(R.string.add_excercise).toUpperCase());
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        videoViewPlayer = findViewById(R.id.videoViewPlayer);
        videoViewPlayer.setClickable(true);
        videoViewPlayer.setOnCompletionListener(this);

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
            objExerciseType.exerciseTypeName = strExerciseTypeID[i];
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
                overridePendingTransition(R.anim.exit, R.anim.enter);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DaysWiseExerciseList.isExerciseDataSaved) {
            onBackPressed();
            overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (videoViewPlayer.getVisibility() == View.VISIBLE) {
                videoViewPlayer.stopPlayback();
                videoViewPlayer.setVisibility(View.GONE);
                return false;
            } else
                return super.onKeyDown(keyCode, event);
        } else
            return super.onKeyDown(keyCode, event);
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

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        videoViewPlayer.setVisibility(View.GONE);
    }

    private boolean playFileRes(String videoPath) {
        if (videoPath == null || "".equalsIgnoreCase(videoPath)) {
            stopPlaying();
            return false;
        } else {

            videoViewPlayer.setVideoURI(Uri.parse(videoPath));
            return true;
        }
    }

    public void stopPlaying() {
        videoViewPlayer.stopPlayback();

    }

    public void pausePlaying() {

        videoViewPlayer.pause();

    }

    public void resumePlayer() {
        videoViewPlayer.start();
    }

    public class ViewHolder {
        TextView textViewExerciseType;
        RelativeLayout rLayoutForExerciseType, rLayoutForWatchVideo;
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

                holder.imageViewNext = convertView.findViewById(R.id.imageViewNext);
                holder.imageViewNext.setVisibility(View.VISIBLE);


                holder.rLayoutForExerciseType = convertView.findViewById(R.id.rLayoutForDaysName);

                holder.rLayoutForWatchVideo = convertView.findViewById(R.id.rLayoutForWatchVideo);
                holder.rLayoutForWatchVideo.setVisibility(View.VISIBLE);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ExerciseTypeClass objExerciseType = this.vectorExerciseTypeLocal.get(position);
            holder.textViewExerciseType.setText(objExerciseType.exerciseTypeName);
            holder.imageViewSelectUnSelectExerciseType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ////Toast.makeText(context, "Show Tick mark icon", Toast.LENGTH_LONG).show();
                    holder.imageViewSelectUnSelectExerciseType.setImageResource(R.drawable.pause_icon);
                }
            });
            holder.rLayoutForExerciseType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, ExerciseSetMetricsScreen.class));
                    overridePendingTransition(R.anim.exit, R.anim.enter);
                    Log.d(VolleyLog.TAG, "*************** ExerciseSetMetricsScreen *************");


                }
            });
            holder.rLayoutForWatchVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position > 2)
                        playFileRes("android.resource://" + getPackageName() + "/raw/video_" + 2);
                    else
                        playFileRes("android.resource://" + getPackageName() + "/raw/video_" + (position + 1));

                    videoViewPlayer.setVisibility(View.VISIBLE);
                    videoViewPlayer.start();
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
