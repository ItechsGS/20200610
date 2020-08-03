package com.org.godspeed.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.org.godspeed.ProgramPurchase.ProgramPurchaseFragment;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.allOtherClasses.HelpScreenData;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LayoutForFolder;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LockDrawer;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.SearchAthlete;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.fm;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewMenuFilter;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewSliderBackIcon;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewSliderDrawerToggleIcon;

public class HelpScreenFragment extends Fragment {

    public Fragment fragment = null;
    private List<String> MoreMenuItems = new ArrayList<>();
    private RecyclerView listViewHelpOption;
    private ProgramsRecycler adapter;
    private Context context;
    private SearchView searchViewExerciseType;
    private TextView textViewScreenName;
    private ImageView imageViewBackArrow;
    private RelativeLayout rLayoutHeader;
    private RecyclerView programsRecyclrView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        View view = inflater.inflate(R.layout.layout_exercise_type_listview, container, false);
        context = getActivity();


        rLayoutHeader = view.findViewById(R.id.rLayoutHeader);
        rLayoutHeader.setVisibility(View.GONE);

        programsRecyclrView = view.findViewById(R.id.programsRecyclrView);
        programsRecyclrView.setVisibility(View.GONE);
        textViewScreenName = view.findViewById(R.id.textViewScreenName);
        textViewScreenName.setText(getString(R.string.help).toUpperCase());
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        if (getArguments().getString("FromScreen").equalsIgnoreCase("Help")) {
            MoreMenuItems.add("FAQ's");
            MoreMenuItems.add("CONTACT US");
            MoreMenuItems.add("SETTINGS");
            MoreMenuItems.add("SHARE");
        } else {

            MoreMenuItems.add("PROGRAMS");
            MoreMenuItems.add("SHOP");
            MoreMenuItems.add("PAYMENT HISTORY");

        }

        searchViewExerciseType = view.findViewById(R.id.searchViewExerciseType);
        searchViewExerciseType.setVisibility(View.GONE);


//        for (int i = 0; i < strExerciseTypeID.length; i++) {
//            ExerciseTypeClass objExerciseType = new ExerciseTypeClass();
//            objExerciseType.exerciseTypeName = strExerciseTypeID[i];
//            objExerciseType.isSelected = false;
//            vectorExerciseType.add(objExerciseType);
//        }

        adapter = new ProgramsRecycler(context);
        listViewHelpOption = view.findViewById(R.id.listViewExerciseType);
        listViewHelpOption.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
        listViewHelpOption.setAdapter(adapter);


        listViewHelpOption.setVisibility(VISIBLE);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) listViewHelpOption.getLayoutParams();
//        params.topMargin = 15;
        listViewHelpOption.setLayoutParams(params);

        imageViewBackArrow = view.findViewById(R.id.imageViewBackArrow);
        imageViewBackArrow.setVisibility(View.GONE);

        return view;

    }


//    public class ListViewAdapter extends BaseAdapter {
//        private Context context;
//        private LayoutInflater vi;
//        private ViewHolder holder;
//        private Vector<ExerciseTypeClass> vectorExerciseTypeLocal = new Vector<ExerciseTypeClass>();
//
//        public ListViewAdapter(Context context) {
//            this.context = context;
//            vi = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            fillVectorDefaultValues();
//
//        }
//
//
//        @Override
//        public int getCount() {
//            return this.vectorExerciseTypeLocal.size();
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
//                convertView = vi.inflate(R.layout.layout_exercise_list_days_based, null);
//                holder.textViewExerciseType = (TextView) convertView.findViewById(R.id.textViewExerciseName);
//                holder.textViewExerciseType.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//                holder.textViewExerciseType.setTextColor(getResources().getColor(R.color.textColorYellow));
//                //holder.textViewExerciseType.setMaxLines(2);
//                //holder.textViewExerciseType.setEllipsize(TextUtils.TruncateAt.END);
//                //holder.textViewExerciseType.setPadding(10,0,5,10);
//
//                holder.textViewAmountForProgram = (TextView) convertView.findViewById(R.id.textViewWatchVideo);
//                holder.textViewAmountForProgram.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
//               // holder.textViewAmountForProgram.setPadding(0,0,0,0);
//
//                holder.imageViewVideoPlayIcon = (ImageView) convertView.findViewById(R.id.imageViewVideoPlayIcon);
//                holder.imageViewVideoPlayIcon.setVisibility(View.GONE);
//
//                holder.imageViewPlayVideoProgramIcon = (ImageView) convertView.findViewById(R.id.imageViewSelectUnSelectExerciseType);
//                holder.imageViewPlayVideoProgramIcon.setVisibility(View.GONE);
//
//                holder.imageViewNext = (ImageView) convertView.findViewById(R.id.imageViewNext);
//                holder.imageViewNextX = (ImageView) convertView.findViewById(R.id.imageViewNextX);
//                holder.imageViewNext.setVisibility(View.VISIBLE);
//                holder.imageViewNextX.setVisibility(View.VISIBLE);
//
//
//                holder.rLayoutForExerciseType = (RelativeLayout) convertView.findViewById(R.id.rLayoutForDaysName);
//
//                holder.rLayoutForWatchVideo = (CardView) convertView.findViewById(R.id.rLayoutForWatchVideo);
//                holder.rLayoutForWatchVideo.setVisibility(View.GONE);
//
//
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            final ExerciseTypeClass objExerciseType = this.vectorExerciseTypeLocal.get(position);
//            holder.textViewExerciseType.setText(objExerciseType.exerciseTypeName);
//            holder.imageViewPlayVideoProgramIcon.setOnClickListener(v -> {
//                ////Toast.makeText(context, "Show Tick mark icon", Toast.LENGTH_LONG).show();
//                holder.imageViewPlayVideoProgramIcon.setImageResource(R.drawable.pause_icon);
//            });
//            holder.rLayoutForExerciseType.setOnClickListener(v -> {
//
//                if(objExerciseType.exerciseTypeName.equalsIgnoreCase("RATE US")){
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp&hl=en"));
//                    startActivity(intent);
//                    getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
//                   return;
//                }
//                if(objExerciseType.exerciseTypeName.equalsIgnoreCase("SHARE")){
//                    Intent sendIntent = new Intent();
//// here comes the magic
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, "hii try godspeed...");
//                    sendIntent.setType("text/plain");
//                    startActivity(sendIntent);
//                    return;
//                }
//
//
//                startActivity(new Intent(context, HelpScreenData.class).putExtra("screenname", objExerciseType.exerciseTypeName));
//                ((Activity) context).overridePendingTransition(R.anim.exit, R.anim.enter);
//                Log.d(VolleyLog.TAG,"*************** HelpScreenData *************");
//
//
//            });
//
//
//                holder.textViewAmountForProgram.setText("$100 Buy");
//
//
//            return convertView;
//        }
//
//        // Filter Class
//        public void filter(String charText) {
//            charText = charText.toLowerCase(Locale.getDefault());
//            this.vectorExerciseTypeLocal.clear();
//            if (charText.length() == 0) {
//                fillVectorDefaultValues();
//            } else {
//                for (int i=0;i<vectorExerciseType.size();i++) {
//                    ExerciseTypeClass obj = vectorExerciseType.get(i);
//                    if (obj.exerciseTypeName.toLowerCase(Locale.getDefault()).contains(charText)) {
//                        this.vectorExerciseTypeLocal.add(obj);
//                    }
//                }
//            }
//            notifyDataSetChanged();
//        }
//
//    }

    public class ProgramsRecycler extends RecyclerView.Adapter<RecyclerViewHolder2> {
        Context context;
        private ViewBinderHelper binderHelper = new ViewBinderHelper();

        public ProgramsRecycler(Context context) {
            this.context = context;
        }

        @Override
        public RecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.layout_exercise_list_days_based, viewGroup, false);
            return new RecyclerViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder2 holder, int i) {
            binderHelper.setOpenOnlyOne(true);
            binderHelper.bind(holder.swipe_layoutMain, i + "");
            holder.swipe_layoutMain.setLockDrag(true);
            holder.textViewExerciseType.setText(MoreMenuItems.get(i));
            holder.imageViewPlayVideoProgramIcon.setOnClickListener(v -> {
                ////Toast.makeText(context, "Show Tick mark icon", Toast.LENGTH_LONG).show();
                holder.imageViewPlayVideoProgramIcon.setImageResource(R.drawable.pause_icon);
            });

            holder.rLayoutForExerciseType.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (getArguments().getString("FromScreen").equalsIgnoreCase("Help") && MoreMenuItems.get(i).equalsIgnoreCase("SETTINGS")) {
                        if (UtilityClass.getPreferences(context, "debug") != null && UtilityClass.getPreferences(context, "debug").equalsIgnoreCase("true")) {
                            UtilityClass.SetPreferences(context, "debug", "false");
                        } else {
                            UtilityClass.SetPreferences(context, "debug", "true");
                        }
                        ((Activity) context).finishAffinity();
                    }
                    return false;
                }
            });
            holder.rLayoutForExerciseType.setOnClickListener(v -> {
                if (getArguments().getString("FromScreen").equalsIgnoreCase("Help")) {
                    if (MoreMenuItems.get(i).equalsIgnoreCase("RATE US")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp&hl=en"));
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.exit, R.anim.enter);
                        return;
                    }
                    if (MoreMenuItems.get(i).equalsIgnoreCase("SHARE")) {
                        Intent sendIntent = new Intent();

                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "hii try godspeed...");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        return;
                    }
                    startActivity(new Intent(context, HelpScreenData.class).putExtra("screenname", MoreMenuItems.get(i)));
                    ((Activity) context).overridePendingTransition(R.anim.exit, R.anim.enter);
                    Log.d(VolleyLog.TAG, "*************** HelpScreenData *************");

                } else {
                    LockDrawer();
                    LayoutForFolder.setVisibility(View.GONE);

                    imageViewMenuFilter.setVisibility(View.GONE);
                    SearchAthlete.setVisibility(View.GONE);
                    Fragment fragment = null;
                    Bundle args = new Bundle();


                    if (MoreMenuItems.get(i).equalsIgnoreCase("PAYMENT HISTORY")) {
                        args.putString("FromScreen", "Shopping");
                        CoachNevigationDrawerScreen.FromSCreen = "Shopping";
                        CoachNevigationDrawerScreen.screenname = "paymenthistory";
                        CoachNevigationDrawerScreen.textViewScreenName.setText("PAYMENT HISTORY");
                        fragment = new purchase_history();
                    } else if (MoreMenuItems.get(i).equalsIgnoreCase("SHOP")) {
                        args.putString("FromScreen", "Shopping");
                        CoachNevigationDrawerScreen.FromSCreen = "Shopping";
                        CoachNevigationDrawerScreen.screenname = "SHOP";
                        CoachNevigationDrawerScreen.textViewScreenName.setText("SHOP");
                        fragment = new webview_shop();
                    } else if (MoreMenuItems.get(i).equalsIgnoreCase("PROGRAMS")) {
                        args.putString("FromScreen", "Shopping");
                        imageViewMenuFilter.setVisibility(VISIBLE);
                        CoachNevigationDrawerScreen.FromSCreen = "Shopping";
                        CoachNevigationDrawerScreen.screenname = "ProgramPurchaseFragment";
                        CoachNevigationDrawerScreen.textViewScreenName.setText("PROGRAMS");
                        fragment = new ProgramPurchaseFragment();
                    }

                    fragment.setArguments(args);
                    fragment.setEnterTransition(new Slide(Gravity.RIGHT));
                    fragment.setExitTransition(new Slide(Gravity.LEFT));
                    fm.beginTransaction().add(R.id.content_frame, fragment, "0").addToBackStack("true").commit();
                    imageViewSliderDrawerToggleIcon.setVisibility(View.GONE);
                    imageViewSliderBackIcon.setVisibility(VISIBLE);
                }

            });


        }

        @Override
        public int getItemCount() {
            return MoreMenuItems.size();
        }

    }

    private class RecyclerViewHolder2 extends RecyclerView.ViewHolder {
        private final RelativeLayout rLayoutForExerciseType;
        private final TextView textViewAmountForProgram;
        private final ImageView imageViewVideoPlayIcon;
        private final ImageView imageViewNext;
        private final ImageView imageViewPlayVideoProgramIcon;
        private final ImageView imageViewNextX;
        LinearLayout myLaX;
        SwipeRevealLayout swipe_layoutMain;
        private CardView rLayoutForWatchVideo;
        private TextView textViewExerciseType, textViewWatchVideo;
        private ImageView imageViewSelectUnSelectExerciseType;

        public RecyclerViewHolder2(@NonNull View itemView) {
            super(itemView);
            textViewExerciseType = itemView.findViewById(R.id.textViewExerciseName);
            myLaX = itemView.findViewById(R.id.myLaX);
            imageViewSelectUnSelectExerciseType = itemView.findViewById(R.id.imageViewSelectUnSelectExerciseType);
            textViewWatchVideo = itemView.findViewById(R.id.textViewWatchVideo);
            rLayoutForWatchVideo = itemView.findViewById(R.id.rLayoutForWatchVideo);
            swipe_layoutMain = itemView.findViewById(R.id.swipe_layoutMain);

            swipe_layoutMain.setLockDrag(true);
            textViewExerciseType.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
            textViewExerciseType.setTextColor(getResources().getColor(R.color.textColorYellow));


            textViewAmountForProgram = itemView.findViewById(R.id.textViewWatchVideo);
            textViewAmountForProgram.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

            imageViewVideoPlayIcon = itemView.findViewById(R.id.imageViewVideoPlayIcon);
            imageViewVideoPlayIcon.setVisibility(View.GONE);

            imageViewPlayVideoProgramIcon = itemView.findViewById(R.id.imageViewSelectUnSelectExerciseType);
            imageViewPlayVideoProgramIcon.setVisibility(View.GONE);

            imageViewNext = itemView.findViewById(R.id.imageViewNext);
            imageViewNextX = itemView.findViewById(R.id.imageViewNextX);
            imageViewNext.setVisibility(VISIBLE);
            imageViewNextX.setVisibility(VISIBLE);


            rLayoutForExerciseType = itemView.findViewById(R.id.rLayoutForDaysName);

            rLayoutForWatchVideo = itemView.findViewById(R.id.rLayoutForWatchVideo);
            rLayoutForWatchVideo.setVisibility(View.GONE);

        }
    }


}
