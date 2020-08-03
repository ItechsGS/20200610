package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleRelativeLayout;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.fragments.scheduleCalender;
import com.org.godspeed.response_JsonS.getVideoClass.LiveClassCheckIn;
import com.org.godspeed.response_JsonS.getVideoClass.LiveClassExerciseVideo;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.DividerItemDecorator;
import com.org.godspeed.utility.GlobalClass;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.zoom_video.ui.LoginUserStartJoinMeetingActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.facebook.GraphRequest.TAG;
import static com.org.godspeed.service.SchoolDataService.LoginJson;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES;


public class VideoListActivity extends Activity implements GodSpeedInterface {
    RecyclerView VideoList;
    List<LiveClassExerciseVideo> classExerciseVideoList = new ArrayList<>();
    VideoAdapter videoAdapter;
    ProgressDialog pDialog;
    ImageView imageViewBackArrow;
    TextView textViewScreenName;
    Context context;

    PlayerView videoFullScreenPlayerXX;
    SimpleExoPlayer playerXX;
    ProgressBar spinnerVideoDetailsXX;

    int PositionOFVideoList;
    String videoUri;
    int usertype = GlobalClass.ivar1;
    String whichApiCalled = "";
    WebServices webServices = new WebServices();
    BubblePopupWindow dialog;
    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video_list);

        context = this;
        classExerciseVideoList = new ArrayList<>(Arrays.asList(new Gson().fromJson(getIntent().getStringExtra("list"), LiveClassExerciseVideo[].class)));
        Log.d(VolleyLog.TAG, "onCreate: " + getIntent().getStringExtra("list"));
        VideoList = findViewById(R.id.VideoList);
        imageViewBackArrow = findViewById(R.id.imageViewBackArrow);
        textViewScreenName = findViewById(R.id.textViewScreenName);
        VideoList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DividerItemDecoration divider = new
                DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(
                ContextCompat.getDrawable(context, R.drawable.line_divider)
        );
        VideoList.addItemDecoration(divider);
        videoAdapter = new VideoAdapter(this);
        VideoList.setAdapter(videoAdapter);

        imageViewBackArrow.setOnClickListener(view -> {
            onBackPressed();
        });

        textViewScreenName.setText(getIntent().getStringExtra("screenname"));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
        finish();
    }


    @Override
    protected void onDestroy() {
        if (playerXX != null) {
            videoFullScreenPlayerXX = null;
            playerXX.release();
            spinnerVideoDetailsXX = null;
        }
        super.onDestroy();
    }

    @Override
    public void ApiResponse(String result) {
        Log.d(TAG, "ApiResponse: " + result);

        if (result != null && result.trim().length() > 0) {
            if (whichApiCalled.equalsIgnoreCase("add")) {
                //parseRequiredData(result);
                String responseMessage = "";
                try {
                    JSONObject jsonObj = new JSONObject(result);


                    String respCode = jsonObj
                            .getString("responseCode");

                    WebServices.responseCode = Integer.parseInt(respCode);

                    responseMessage = jsonObj
                            .getString("responseMessage");
                    if (WebServices.responseCode == 200) {
                        jsonObj.getString("data");

                        List<LiveClassCheckIn> liveClassCheckIn = new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonObj.getString("data"), LiveClassCheckIn.class)));
                        classExerciseVideoList.get(PositionOFVideoList).getLiveClassCheckInList().add(liveClassCheckIn.get(0));
                        videoAdapter.notifyDataSetChanged();
                    }
                } catch (Exception v) {

                }
            } else if (whichApiCalled.equalsIgnoreCase("delete")) {
            }
        } else {
            // UtilityClass.hide();
        }
        UtilityClass.hide();

    }

    private void showDialogOfCheckin(Context context, List<LiveClassCheckIn> f, View viewX) {


        View rootView = LayoutInflater.from(context).inflate(R.layout.cutom_dialogbox_athlete_screen, null);
        BubbleRelativeLayout bubbleView = rootView.findViewById(R.id.CardX);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(getResources().getColor(R.color.color_gray_for_toggle_unselected));

        dialog = new BubblePopupWindow(rootView, bubbleView);
        dialog.setCancelOnTouch(false);

        dialog.showArrowTo(viewX, BubbleStyle.ArrowDirection.Up);

        //rootView.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RelativeLayout mainRly = rootView.findViewById(R.id.mainRly);
        mainRly.invalidate();
        TextView EvenText = rootView.findViewById(R.id.EventName);

        EvenText.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));
        EvenText.setText("Check-Ins");

        LinearLayout rMainDialogLayout = rootView.findViewById(R.id.rMainDialogLayout);
        RelativeLayout RReventName;
        RReventName = rootView.findViewById(R.id.RReventName);


        RecyclerView dialogBoxRecyclerView = rootView.findViewById(R.id.dialogBoxRecyclerView);

        ImageView backEventDialog = rootView.findViewById(R.id.backEventDialog);
        ImageView SaveEventDialog = rootView.findViewById(R.id.SaveEventDialog);
        RecyclerView dialogBoxRecyclerData = rootView.findViewById(R.id.dialogBoxRecyclerData);

        dialogBoxRecyclerView.setHasFixedSize(true);
        dialogBoxRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider_dark_light));
        dialogBoxRecyclerView.addItemDecoration(dividerItemDecoration);

        dialogBoxRecyclerView.setAdapter(new AthleteCheckinRecycler(context, f));

//
        rMainDialogLayout.getLayoutParams().height = CoachAddExerciseScreen.dpToPx(300);


    }

    private class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> implements Player.EventListener {
        SimpleExoPlayer Oldplayer;
        Context context;
        RelativeLayout myViewXX;

        int oldPOistio;
        // Insert your Video URL

        VideoAdapter(Context context) {
            this.context = context;
        }

        @Override
        public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater vi = LayoutInflater.from(parent.getContext());
            View v = vi.inflate(R.layout.videoview_main, parent, false);
            return new VideoViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final VideoViewHolder holder, int position) {

            RequestOptions requestOptions = new RequestOptions();


            Log.d(TAG, "onBindViewHolder: " + LoginJson.get(0).getUserType());


            if (classExerciseVideoList.get(position).getThumbnail_image() == null || classExerciseVideoList.get(position).getThumbnail_image().equalsIgnoreCase("")) {
                Glide.with(context)
                        .load(classExerciseVideoList.get(position).getVideoName())
                        .apply(requestOptions)
                        .thumbnail(Glide.with(context)
                                .load(classExerciseVideoList.get(position).getVideoName()))
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                holder.MyView.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(holder.thumbnail);

            } else {
                Glide.with(context).load(
                        WebServices.BASE_URL_FOR_IMAGES_ASSETS + classExerciseVideoList.get(position).getThumbnail_image())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.thumbnail);
            }
            if (classExerciseVideoList.get(position).getVideo_meeting_title() != null && !classExerciseVideoList.get(position).getVideo_meeting_title().equalsIgnoreCase("")) {
                holder.la.setVisibility(VISIBLE);

                holder.VideoTitleName.setText(classExerciseVideoList.get(position).getVideo_meeting_title());
            }
            holder.checkIN.setTag("false");

            for (int i = 0; i < classExerciseVideoList.get(position).getLiveClassCheckInList().size(); i++) {
                if (LoginJson.get(0).getUserId().equalsIgnoreCase(classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).getUser_id())) {
                    holder.checkIN.setTag("true");
                    holder.VideoTitleName.setTag(classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).getCheckinid());
                    break;
                }
            }

            holder.CheckinCount.setVisibility(classExerciseVideoList.get(position).getLiveClassCheckInList().size() > 0 ? VISIBLE : View.GONE);
            if (LoginJson.get(0).getUserType().equalsIgnoreCase("4")) {
                holder.CheckinCount.setVisibility(View.GONE);
            }

            holder.CheckinCount.setText(classExerciseVideoList.get(position).getLiveClassCheckInList().size() + "");
            holder.VideoTime.setText(classExerciseVideoList.get(position).getStart_time() + "\n" + classExerciseVideoList.get(position).getEnd_time());
            if (holder.checkIN.getTag().toString().equalsIgnoreCase("true")) {
                holder.checkIN.setColorFilter(getResources().getColor(R.color.Maincolor));
            } else {
                holder.checkIN.setColorFilter(getResources().getColor(R.color.grey));
            }
            holder.checkIN.setOnClickListener(v -> {
                whichApiCalled = "delete";
                if (holder.checkIN.getTag().toString().equalsIgnoreCase("true")) {
//                    webServices.deleteLiveClassCheckIn(holder.VideoTitleName.getTag().toString() + "",
//                            context, VideoListActivity.this);
                    holder.checkIN.setColorFilter(getResources().getColor(R.color.grey));
                    holder.checkIN.setTag("false");
                    for (int i = 0; i < classExerciseVideoList.get(position).getLiveClassCheckInList().size(); i++) {
                        if (LoginJson.get(0).getUserId().equalsIgnoreCase(classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).getUser_id())) {
                            classExerciseVideoList.get(position).getLiveClassCheckInList().remove(i);
                            //classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).setUser_id("");
                            notifyDataSetChanged();
                            // holder.checkIN.setTag("true");
                            // holder.VideoTitleName.setTag(classExerciseVideoList.get(position).getLiveClassCheckInList().get(i).getCheckinid());
                            break;
                        }
                    }
                } else {
                    whichApiCalled = "add";
//                    webServices.setLiveClassCheckIn(classExerciseVideoList.get(position).getId() + "",
//                            LoginJson.get(0).getUserId() + "", context, VideoListActivity.this);
                    holder.checkIN.setColorFilter(getResources().getColor(R.color.Maincolor));
                    PositionOFVideoList = position;
                    holder.checkIN.setTag("true");
                }
                scheduleCalender.RefreshUI = true;
            });

            holder.CheckinCount.setOnClickListener(v -> {
                showDialogOfCheckin(context, classExerciseVideoList.get(position).getLiveClassCheckInList(), v);
            });


            holder.MyView.setOnClickListener(view1 -> {
                if (classExerciseVideoList.get(position).getIs_live().equalsIgnoreCase("1")) {
                    Intent intent = new Intent(context, LoginUserStartJoinMeetingActivity.class).putExtra("Type", "1").putExtra("meetingNo", classExerciseVideoList.get(position).getVideoName()).putExtra("meetingPassword", classExerciseVideoList.get(position).getPassword());
                    startActivity(intent);
                    return;
                }

                LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = vi.inflate(R.layout.video_view, null);
                releasePlayer();
                oldPOistio = position;
                videoUri = classExerciseVideoList.get(position).getVideoName();
                spinnerVideoDetailsXX = v.findViewById(R.id.spinnerVideoDetails);
                videoFullScreenPlayerXX = v.findViewById(R.id.videoFullScreenPlayer);


                holder.myView.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                initializePlayer();
                setUp();
                myViewXX = holder.myView;
            });
            holder.thumbnail.setOnClickListener(view1 -> {
                if (classExerciseVideoList.get(position).getIs_live().equalsIgnoreCase("1")) {
                    Intent intent = new Intent(context, LoginUserStartJoinMeetingActivity.class).putExtra("Type", "1").putExtra("meetingNo", classExerciseVideoList.get(position).getVideoName()).putExtra("meetingPassword", classExerciseVideoList.get(position).getPassword());
                    startActivity(intent);
                    return;
                }
                LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = vi.inflate(R.layout.video_view, null);
                releasePlayer();
                oldPOistio = position;
                videoUri = classExerciseVideoList.get(position).getVideoName();
                spinnerVideoDetailsXX = v.findViewById(R.id.spinnerVideoDetails);
                videoFullScreenPlayerXX = v.findViewById(R.id.videoFullScreenPlayer);

                holder.myView.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                initializePlayer();
                setUp();
                myViewXX = holder.myView;
            });
        }

        private void setUp() {
            try {
                initializePlayer();

                buildMediaSource(Uri.parse(videoUri));
                Log.d(TAG, "setUp: " + Uri.parse(videoUri));
            } catch (Exception v) {

            }
        }


        private void initializePlayer() {

            if (playerXX == null) {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelection.Factory videoTrackSelectionFactory =
                        new AdaptiveTrackSelection.Factory(bandwidthMeter);
                TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
                DefaultLoadControl loadControl = new DefaultLoadControl.Builder().setBufferDurationsMs(32 * 1024, 64 * 1024, 1024, 1024).createDefaultLoadControl();
                playerXX = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

                videoFullScreenPlayerXX.setPlayer(playerXX);


                videoFullScreenPlayerXX.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);
                playerXX.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            }

        }

        private void buildMediaSource(Uri mUri) {
            // Measures bandwidth during playback. Can be null if not required.
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                    Util.getUserAgent(context, getString(R.string.app_name)), bandwidthMeter);
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mUri);

            playerXX.prepare(videoSource);

            playerXX.setPlayWhenReady(true);
            playerXX.addListener(this);
            playerXX.setRepeatMode(Player.REPEAT_MODE_ALL);
        }

        private void releasePlayer() {
            if (playerXX != null) {
                playerXX.release();
                playerXX = null;
                videoFullScreenPlayerXX = null;
                if (myViewXX != null) {
                    myViewXX.removeAllViews();
                }
//

            }
        }

        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

            switch (playbackState) {
                case Player.STATE_BUFFERING:
                    spinnerVideoDetailsXX.setVisibility(VISIBLE);
                    break;
                case Player.STATE_ENDED:
                    //resumePlayer();
                    // Activate the force enable
                    break;
                case Player.STATE_IDLE:
                    break;
                case Player.STATE_READY:
                    spinnerVideoDetailsXX.setVisibility(View.GONE);
                    break;
                default:
                    // status = PlaybackStatus.IDLE;
                    break;
            }
        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {
        }

        @Override
        public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
        }

        @Override
        public void onPositionDiscontinuity(int reason) {
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        @Override
        public void onSeekProcessed() {
        }


        @Override
        public int getItemCount() {
            return classExerciseVideoList.size();
        }


        private class VideoViewHolder extends RecyclerView.ViewHolder {

            VideoView videoview;
            RelativeLayout rLayout, myView, la;
            ImageView MyView;
            PlayerView videoFullScreenPlayer;
            TextView VideoTitleName, VideoTime, CheckinCount;

            ImageView imageViewExit, thumbnail, checkIN;


            VideoViewHolder(View view) {
                super(view);
                rLayout = view.findViewById(R.id.rLayout);
                videoview = view.findViewById(R.id.video_view_home);
                MyView = view.findViewById(R.id.MyView);


                videoFullScreenPlayer = view.findViewById(R.id.videoFullScreenPlayer);
                imageViewExit = view.findViewById(R.id.imageViewExit);
                //spinnerVideoDetails = view.findViewById(R.id.spinnerVideoDetails);
                myView = view.findViewById(R.id.myView);
                thumbnail = view.findViewById(R.id.thumbnail);
                VideoTitleName = view.findViewById(R.id.VideoTitleName);
                la = view.findViewById(R.id.la);
                VideoTime = view.findViewById(R.id.VideoTime);
                checkIN = view.findViewById(R.id.checkIN);
                CheckinCount = view.findViewById(R.id.CheckinCount);
                thumbnail.setVisibility(VISIBLE);

            }


        }


    }

    public class AthleteCheckinRecycler extends RecyclerView.Adapter<AthleteCheckinRecycler.RecyclerViewHolder2> {
        int Y;
        Context context;
        List<LiveClassCheckIn> f;

        public AthleteCheckinRecycler(Context context, List<LiveClassCheckIn> f) {
            this.f = f;
            this.context = context;
        }

        @Override
        public AthleteCheckinRecycler.RecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.user_item_layout, viewGroup, false);
            return new AthleteCheckinRecycler.RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final AthleteCheckinRecycler.RecyclerViewHolder2 Holder, final int i) {
            Holder.LevelText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            Holder.arrow1.setVisibility(GONE);
            Holder.LevelText.setText(f.get(i).getLast_name() + " " + f.get(i).getFirst_name());
            Holder.LevelText.setTextColor(Color.rgb(237, 187, 87));

            Glide.with(context).load(BASE_URL_FOR_IMAGES + f.get(i).getUser_image()).error(getResources().getDrawable(R.drawable.logo_f)).into(Holder.LevelImage);

            Holder.LevelLayout.setOnClickListener(view -> {

            });

        }


        @Override
        public int getItemCount() {
            int countofArray = f.size();

            return countofArray;
        }

        public class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
            TextView LevelText, AtheleteLevelExerciseName, textViewExerciseName, textViewExerciseDate,
                    AtheleteExerciseValueMultiple, MultiplyValueInc, MultiplyValueDcr;
            EditText AtheleteLevelExerciseValuesEditText;
            ImageView LevelImage, arrow1, rightSign;
            RelativeLayout LevelLayout, rAthleteLevelExercise, rLayoutForAthleteTraining, MainRLYLayoutLevel;

            public RecyclerViewHolder2(View itemView) {
                super(itemView);
                AtheleteLevelExerciseName = itemView.findViewById(R.id.AtheleteLevelExerciseName);
                AtheleteLevelExerciseValuesEditText = itemView.findViewById(R.id.AtheleteLevelExerciseValues);
                AtheleteExerciseValueMultiple = itemView.findViewById(R.id.AtheleteExerciseValueMultiple);
                LevelText = itemView.findViewById(R.id.LevelText);
                LevelImage = itemView.findViewById(R.id.LevelImage);
                LevelLayout = itemView.findViewById(R.id.LevelLayout);
                MultiplyValueInc = itemView.findViewById(R.id.MultiplyValueInc);
                MultiplyValueDcr = itemView.findViewById(R.id.MultiplyValueDcr);
                rAthleteLevelExercise = itemView.findViewById(R.id.rAthleteLevelExercise);
                rightSign = itemView.findViewById(R.id.rightSign);
                arrow1 = itemView.findViewById(R.id.arrow1);
                textViewExerciseDate = itemView.findViewById(R.id.textViewExerciseDate);
                textViewExerciseName = itemView.findViewById(R.id.textViewExerciseName);
                rLayoutForAthleteTraining = itemView.findViewById(R.id.rLayoutForAthleteTraining);
                MainRLYLayoutLevel = itemView.findViewById(R.id.MainRLYLayoutLevel);
            }
        }

    }


}
