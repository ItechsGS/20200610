

package com.org.godspeed.fragments;

/**
 * Created by Tanveer on 6/8/2019.
 **/

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
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
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyLog;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.google.gson.Gson;
import com.org.godspeed.R;
import com.org.godspeed.allOtherClasses.CoachAddExerciseScreen;
import com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen;
import com.org.godspeed.allOtherClasses.LoginScreen;
import com.org.godspeed.allOtherClasses.SelectTeamAndAthleteScreen;
import com.org.godspeed.response_JsonS.FolderTraining.ArrayofFolder;
import com.org.godspeed.service.OnSwipeListener;
import com.org.godspeed.utility.CustomTypeface;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.TraningNameClass;
import com.org.godspeed.utility.UtilityClass;
import com.org.godspeed.utility.WebServices;
import com.org.godspeed.utility.custom_alert_class;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.CopiedData;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.LockDrawer;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.MoveFolder;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.MoveTraining;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.POSITION;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.PurchaseTP;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.SportsIDForTPpurchase;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.arrayofFoldersSelected;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.fm;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.folderId;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewSliderBackIcon;
import static com.org.godspeed.allOtherClasses.CoachNevigationDrawerScreen.imageViewSliderDrawerToggleIcon;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.WebServices.CallApi;


@SuppressLint("ValidFragment")
public class FragmentTrainingAndFolder extends Fragment implements GodSpeedInterface {

    Gson gson = new Gson();
    WebServices webServices = new WebServices();
    Fragment myObject;
    private Vector<TraningNameClass> vectorTrining;
    private TraningNameClass objTrainingData;
    private Context context;
    private List<ArrayofFolder> arrayofFolders;
    private RecyclerView recyclerView;
    private View view;
    private boolean add = false;
    private Paint p = new Paint();
    private String parentid = "";
    //private SwipeMenuListView listViewTrainingSet;
    private String gotoScreenwithID = "";
    private Fragment fragment = null;
    private EditText searchViewExerciseType;
    private ImageView imageViewForZoomInOut;
    //private TextView newTraining, newFolder;
    private String whichAPICALLED = "getFolder";
    private RecyclerView folderRecyclerView;
    private FolderAdapter folderAdapter;
    private Dialog dialog;
    private RelativeLayout rsearchViewExerciseType;
    private TextView CancelButtonOfSearch;
    private ImageView calc_clear_txt_Prise;
    private EditText calc_txt_Prise;
    private Transition transition;
    private RelativeLayout SearchAthleteText;
    private RelativeLayout rSearchAthleteText;
    private Bundle savedState = null;
    private boolean createdStateInDestroyView;
    private LinearLayout newTraining, newFolder;
    //private LinearLayout Paste;


    private Activity activity;


    //private
    public FragmentTrainingAndFolder(String parentid) {
        this.parentid = parentid;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (CallApi) {
            ((CoachNevigationDrawerScreen) getActivity()).yourPublicMethod();
            CallApi = false;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_training, container, false);
        activity = getActivity();
        CoachNevigationDrawerScreen.activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        forSearchBar();
        UtilityClass.hide();
        Log.e("Screen", "Fragment Training");
        vectorTrining = new Vector<TraningNameClass>();

        arrayofFolders = new ArrayList<>();


        rsearchViewExerciseType = view.findViewById(R.id.rsearchViewExerciseType);

        folderRecyclerView = view.findViewById(R.id.folderRecyclerView);
        newFolder = view.findViewById(R.id.newFolder);
        newTraining = view.findViewById(R.id.newTraining);


        newFolder.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            final EditText edittext = new EditText(activity);
            alert.setMessage("Create new folder");
            alert.setTitle(activity.getResources().getString(R.string.app_name));
            alert.setView(edittext);
            alert.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Editable YouEditTextValue = edittext.getText();
                    whichAPICALLED = "create_folder";
                    if (edittext.getText().toString().length() > 0) {

                        hide();
                        //UtilityClass.showWaitDialog(dialogX, activity);
                        webServices.CreateFolder(edittext.getText().toString(), LoginScreen.userId, parentid, activity, FragmentTrainingAndFolder.this);
                        dialog.dismiss();
                    } else {
                        UtilityClass.showAlertDailog(activity, "Please enter team name.");
                    }
                }
            });
            alert.setNegativeButton("Cancel", (dialog, whichButton) -> {
                // what ever you want to do with No option.
            });
            alert.show();
        });

        newTraining.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            final EditText edittext = new EditText(activity);
            alert.setMessage("Create new training program ");
            alert.setTitle(activity.getResources().getString(R.string.app_name));
            alert.setView(edittext);
            alert.setPositiveButton("Create", (dialog, whichButton) -> {
                Editable YouEditTextValue = edittext.getText();
                whichAPICALLED = "create_training";
                if (edittext.getText().toString().length() > 0) {
                    hide();
                    //UtilityClass.showWaitDialog(dialogX, activity);

                    gotoScreenwithID = edittext.getText().toString();

                    webServices.SetTrainingProgramName(edittext.getText().toString(), LoginScreen.userId, parentid, activity, FragmentTrainingAndFolder.this);
                } else {
                    UtilityClass.showAlertDailog(activity, "Please enter program name.");
                }
            });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // what ever you want to do with No option.
                }
            });
            alert.show();
        });

        hide();

        calc_clear_txt_Prise.setVisibility(GONE);

        calc_clear_txt_Prise.setOnClickListener(view1 -> {
            calc_txt_Prise.setText("");
        });

        CancelButtonOfSearch.setOnClickListener(view1 -> {
            toggle();
            calc_txt_Prise.setText("");
        });


        calc_txt_Prise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                folderAdapter.filter(text);
                if (text.length() > 0) {
                    calc_clear_txt_Prise.setVisibility(VISIBLE);
                } else {
                    calc_clear_txt_Prise.setVisibility(GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        CallApi();
        return view;
    }

    public void CallApi() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getArguments();
                String myInt = "";
                if (bundle != null) {
                    myInt = bundle.getString("tag");
                }
                Log.e(VolleyLog.TAG, "onCreateView:myInt " + myInt);
                Log.e(VolleyLog.TAG, "onCreateView:parentid " + parentid);
                whichAPICALLED = "getFolder";
                webServices.getFolder(LoginScreen.userId, parentid, activity, FragmentTrainingAndFolder.this);
            }
        }, 500);
    }


    private void forSearchBar() {
        SearchAthleteText = view.findViewById(R.id.SearchAthleteText);
        CancelButtonOfSearch = view.findViewById(R.id.CancelButtonOfSearch);
        rSearchAthleteText = view.findViewById(R.id.rSearchAthleteText);
        calc_txt_Prise = view.findViewById(R.id.calc_txt_Prise);
        calc_clear_txt_Prise = view.findViewById(R.id.calc_clear_txt_Prise);
        transition = new Slide(Gravity.TOP);
        transition.setDuration(300);
        transition.addTarget(R.id.rSearchAthleteText);
    }

    public void showToast() {
        Log.e(VolleyLog.TAG, "onCreateViewparentid: " + parentid);
        Bundle bundle = this.getArguments();
        String myInt = "";
        if (bundle != null) {
            myInt = bundle.getString("tag");
            Log.e(VolleyLog.TAG, "onCreateView: " + myInt);
        }
        Log.e(VolleyLog.TAG, "onCreateView: " + getArguments().getString("tag"));
        //  FragmentManager fm = getFragmentManager();
        Log.e(VolleyLog.TAG, parentid + " onCreateView: " + fm.getBackStackEntryCount());
        if (MoveFolder) {
            whichAPICALLED = "Move_folder";
            //if (myInt.equalsIgnoreCase("0")) {
            webServices.moveFolder(parentid, CopiedData, activity, FragmentTrainingAndFolder.this);
            //}
            ((CoachNevigationDrawerScreen) getActivity()).LayoutForFolder.setVisibility(GONE);
            CopiedData = "";
            MoveFolder = false;
            //
        } else if (MoveTraining) {
            whichAPICALLED = "Move_training";
            //if (myInt.equalsIgnoreCase("0")) {
            webServices.moveTrainingProgram(CopiedData, parentid, activity, FragmentTrainingAndFolder.this);
            //}
            ((CoachNevigationDrawerScreen) getActivity()).LayoutForFolder.setVisibility(GONE);
            CopiedData = "";
            MoveFolder = false;
        } else {
            if (fm.getBackStackEntryCount() == 0) {
                whichAPICALLED = "create_folder";
                webServices.copyTrainingProgramToFolder(CopiedData, "0", activity, FragmentTrainingAndFolder.this);

                ((CoachNevigationDrawerScreen) getActivity()).LayoutForFolder.setVisibility(GONE);
                CopiedData = "";
            } else {
                whichAPICALLED = "create_folder";
                webServices.copyTrainingProgramToFolder(CopiedData, parentid, activity, FragmentTrainingAndFolder.this);

                ((CoachNevigationDrawerScreen) getActivity()).LayoutForFolder.setVisibility(GONE);
                CopiedData = "";
            }
        }
        CallApi = true;

    }

    public void toggle() {
        //calc_txt_Prise.setText("");
        TransitionManager.beginDelayedTransition(rSearchAthleteText, transition);
        rSearchAthleteText.setVisibility(rSearchAthleteText.getVisibility() == GONE ? View.VISIBLE : GONE);
        if (rSearchAthleteText.getVisibility() == VISIBLE) {
            //hideSoftKeyboard();
            calc_txt_Prise.requestFocus();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(calc_txt_Prise, InputMethodManager.SHOW_IMPLICIT);
            //gridViewAthlete.setEnabled(false);
        }
    }

    private void showAlert(int position, String whichAPICALLED, String Message, String Name) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        final EditText edittext = new EditText(activity);
        edittext.setText(Name);
        alert.setMessage(Message);
        alert.setTitle(activity.getResources().getString(R.string.app_name));
        alert.setView(edittext);
        alert.setPositiveButton("Done", (dialog, whichButton) -> {
            Editable YouEditTextValue = edittext.getText();
            if (edittext.getText().toString().length() > 0) {
                //UtilityClass.showWaitDialog(dialogX, activity);
                arrayofFolders.get(position).setFolderName(edittext.getText().toString());
                folderAdapter.notifyDataSetChanged();
                //adapter.notifyDataSetChanged();
                if (whichAPICALLED.equalsIgnoreCase("RenameTraining")) {
                    hide();
                    webServices.RenameProgram(arrayofFolders.get(position).getId(), edittext.getText().toString(), activity, FragmentTrainingAndFolder.this);
                    arrayofFolders.get(position).setProgramName(edittext.getText().toString());
                    //folderAdapter.notifyItemChanged(position);
                } else {
                    hide();
                    webServices.RenameFolder(arrayofFolders.get(position).getId(), edittext.getText().toString(), activity, FragmentTrainingAndFolder.this);
                    arrayofFolders.get(position).setFolderName(edittext.getText().toString());
                    //folderAdapter.notifyItemChanged(position);
                }
            } else {
                UtilityClass.showAlertDailog(activity, "Please enter folder name.");
            }
            //adapter.notifyDataSetChanged();
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });
        alert.show();
    }

    private void showAlertDelete(int position, String whichAPICALLED, String Message) {


        final custom_alert_class mAlert = new custom_alert_class(getActivity());
        mAlert.setMessage(Message);
        mAlert.setPositveButton("Yes", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adapter.notifyDataSetChanged();
                if (whichAPICALLED.equalsIgnoreCase("Deletetraining")) {
                    webServices.DeleteTraining(arrayofFolders.get(position).getId(), activity, FragmentTrainingAndFolder.this);
                } else {
                    webServices.DeleteFolder(arrayofFolders.get(position).getId(), activity, FragmentTrainingAndFolder.this);
                }
                removePosition(position);

                mAlert.dismiss();
            }
        });
        mAlert.setNegativeButton("No", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlert.dismiss();
            }
        });
        mAlert.show();
    }

    public void removePosition(int position) {
        try {
            folderAdapter.notifyItemRemoved(position);
            arrayofFolders.remove(position);


//            Log.e(VolleyLog.TAG, "removePosition:arrayofFolders Before "+arrayofFolders.size());
//            Log.e(VolleyLog.TAG, "removePosition:arrayofFolders After "+arrayofFolders.size());
        } catch (Exception x) {

        }
    }


    @Override
    public void ApiResponse(String result) {
        if (result != null && result.trim().length() > 0) {
            Log.d("Result", result);
            parseFolderData(result);
        } else {
            UtilityClass.hide();
        }
    }

    private void parseFolderData(String result) {
        String responseMessage = "";
        try {
            JSONObject jsonObj = new JSONObject(result);


            String respCode = jsonObj.getString("responseCode");

            WebServices.responseCode = Integer.parseInt(respCode);

            responseMessage = jsonObj.getString("responseMessage");

            if (WebServices.responseCode == 200) {
                if (whichAPICALLED.equalsIgnoreCase("getFolder") || whichAPICALLED.equalsIgnoreCase("create_folder") || whichAPICALLED.equalsIgnoreCase("create_training") || whichAPICALLED.equalsIgnoreCase("Move_folder") ||
                        whichAPICALLED.equalsIgnoreCase("Move_training")) {
                    JSONArray jsonArray = jsonObj.getJSONArray("data");
                    try {
                        Log.e(VolleyLog.TAG, "parseFolderData:OLD " + arrayofFolders.size());
                    } catch (Exception v) {

                    }

                    arrayofFolders = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), ArrayofFolder[].class)));
                    Log.e(VolleyLog.TAG, "parseFolderData:new " + arrayofFolders.size());
                    folderAdapter = null;
                    folderAdapter = new FolderAdapter(activity, arrayofFolders);
                    folderRecyclerView.setAdapter(null);
                    LinearLayoutManager llm = new LinearLayoutManager(activity);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    folderRecyclerView.setLayoutManager(llm);
                    folderRecyclerView.setAdapter(folderAdapter);
                    folderAdapter.notifyDataSetChanged();


                } else if (whichAPICALLED.equalsIgnoreCase("DeleteFolder")) {

                } else if (whichAPICALLED.equalsIgnoreCase("setTrainingProgramForSale")) {
                    hide();
                }
//                }
//                else if(whichAPICALLED.equalsIgnoreCase("create_folder")){
//                    JSONArray jsonArray = jsonObj.getJSONArray("data");
//
//
//                    arrayofFolders = Arrays.asList(gson.fromJson(jsonArray.toString(), ArrayofFolder[].class));
//                    adapter = new ListViewAdapter(activity);
//                    listViewTrainingSet.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                }
//                else if(whichAPICALLED.equalsIgnoreCase("create_training")){
//                    arrayofFolders = Arrays.asList(gson.fromJson(jsonArray.toString(), ArrayofFolder[].class));
//                    adapter = new ListViewAdapter(activity);
//                    listViewTrainingSet.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                }
                //recyclerView.setAdapter(new CartListAdapter(activity));

            } else {
                UtilityClass.showAlertDailog(activity, responseMessage);
                hide();

            }
        } catch (Exception e) {
            Log.e(VolleyLog.TAG, "parseFolderData: " + e);
        }
        UtilityClass.hide();
    }

    private void startActivityX(String id) {
        startActivity(new Intent(activity, CoachAddExerciseScreen.class).putExtra("TrainingId", id).putExtra("Screen", "TrainingandFolder"));
        activity.overridePendingTransition(R.anim.exit, R.anim.enter);
        Log.d(VolleyLog.TAG, "*************** CoachAddExerciseScreen *************");
    }

    public void showDialogofTraining(Context context, int x, int y, String event, String eventData, String id) {
        dialog = new Dialog(activity);
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
            startActivity(new Intent(activity, SelectTeamAndAthleteScreen.class).putExtra("training_program_id", id));
            activity.overridePendingTransition(R.anim.exit, R.anim.enter);
            dialog.dismiss();
            Log.d(VolleyLog.TAG, "*************** SelectTeamAndAthleteScreen *************");
        });

        CancelXc.setOnClickListener(view -> dialog.dismiss());

        SingleRow.setVisibility(View.VISIBLE);


        EventName = dialog.findViewById(R.id.EventName);

        EventNameX = dialog.findViewById(R.id.EventNameX);

        EventNameX.setText("Do you want to assign " + event + " Training Program?");


//        showDialogofTraining(activity,0,0,GetTeam.get(position).getTeamName(),"",0);
        EventName.setTypeface(CustomTypeface.load_Montserrat_Bold_Fonts(activity));
        EventNameX.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(activity));
        begin.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(activity));
        workout.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(activity));
        cancel.setTypeface(CustomTypeface.load_Montserrat_Regular_Fonts(activity));

        BeginTraining.setVisibility(GONE);
        workoutSummary.setVisibility(GONE);
        Cancel.setVisibility(GONE);


        BeginTraining.setOnClickListener(view -> dialog.dismiss());

        workoutSummary.setOnClickListener(view -> dialog.dismiss());

        Cancel.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    public class ViewHolder {
        TextView textViewTrainingName;
        RelativeLayout rrrss;
        FrameLayout rLayoutForTraining;
        ImageView folderIcon, imageViewTeamIcon;

    }

    public class ListViewAdapter extends BaseAdapter implements View.OnClickListener {
        private Context context;
        private LayoutInflater vi;
        private ViewHolder holder;
        private List<ArrayofFolder> mDisplayedValues = arrayofFolders;    // Values to be displayed

        public ListViewAdapter(Context context) {
            this.context = activity;
            vi = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            int val = 0;
            try {
                val = mDisplayedValues.size();
                Log.e(VolleyLog.TAG, "getCount: " + mDisplayedValues.size());
            } catch (Exception c) {

            }
            return val;
        }


        @Override
        public int getItemViewType(int position) {
            int menu = 1;
            if (mDisplayedValues.get(position).getProgramName() != null) {
                menu = 0;
            }
            // current menu type
            return menu;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = vi.inflate(R.layout.list_view_items_training, null);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


//                        @Override
//                        public boolean onTouch(View v, MotionEvent event) {
//                            Log.e(TAG, "onTouch: ");
//                            try {
//
//                            } catch (Exception x) {
//                            }
//                            return false;
//                        }


//            holder.rrrss.setOnClickListener(view -> {
//                Log.e(VolleyLog.TAG, "onMenuItemClick:Inlist " + arrayofFolders.get(position).getId());
//
//            });

            return convertView;
        }


        @Override
        public void onClick(View view) {
            //Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
        }
    }


    private class TrainingScreen extends RecyclerView.ViewHolder {
        TextView textViewTrainingName, Delete, Rename, Assign, Edit, Copy, Move;
        RelativeLayout rrrss;
        SwipeRevealLayout rLayoutForTraining;
        ImageView folderIcon, imageViewTeamIcon, arrow;


        public TrainingScreen(@NonNull View convertView) {
            super(convertView);
            textViewTrainingName = convertView.findViewById(R.id.textViewTrainingName);
            textViewTrainingName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(activity));
            folderIcon = convertView.findViewById(R.id.folderIcon);
            rLayoutForTraining = convertView.findViewById(R.id.rLayoutForTraining);
            rrrss = convertView.findViewById(R.id.rrrss);

            Delete = convertView.findViewById(R.id.Delete);
            Rename = convertView.findViewById(R.id.Rename);
            Assign = convertView.findViewById(R.id.Assign);
            Edit = convertView.findViewById(R.id.Edit);
            Copy = convertView.findViewById(R.id.Copy);
            Move = convertView.findViewById(R.id.Move);
            arrow = convertView.findViewById(R.id.arrow);
        }
    }


    public class FolderAdapter extends RecyclerView.Adapter<TrainingScreen> {
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();
        private Context context;
        private LayoutInflater vi;
        private List<ArrayofFolder> mDisplayedValues;

        public FolderAdapter(Context context, List<ArrayofFolder> arrayofFolders) {
            this.context = activity;
            this.mDisplayedValues = arrayofFolders;
            binderHelper.setOpenOnlyOne(true);
        }

        @Override
        public TrainingScreen onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_view_items_training, parent, false);
            return new TrainingScreen(itemView);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }


        @Override
        public void setHasStableIds(boolean hasStableIds) {
            super.setHasStableIds(hasStableIds);
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBindViewHolder(TrainingScreen holder, final int position) {
            binderHelper.setOpenOnlyOne(true);
            holder.textViewTrainingName.setTypeface(CustomTypeface.load_AGENCYR_Fonts(activity));


            holder.arrow.setTag("UNSELECTED");
            binderHelper.bind(holder.rLayoutForTraining, position + mDisplayedValues.get(position).getId());

            if (mDisplayedValues.get(position).getProgramName() != null) {
                holder.textViewTrainingName.setText(mDisplayedValues.get(position).getProgramName());
                holder.folderIcon.setImageDrawable(activity.getResources().getDrawable(R.drawable.trainingicon));
                holder.Assign.setVisibility(View.VISIBLE);
                holder.Edit.setVisibility(View.VISIBLE);
                holder.Copy.setVisibility(View.VISIBLE);
                holder.Move.setVisibility(VISIBLE);
                holder.rLayoutForTraining.setTag("program" + position);

                if (PurchaseTP) {
                    holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.unselected_tick_icon));
                    arrayofFoldersSelected.forEach(m -> {
                        if (m.getId().equalsIgnoreCase(mDisplayedValues.get(position).getId())) {
                            holder.arrow.setImageDrawable(getResources().getDrawable(R.drawable.selected_tick_icon));
                            holder.arrow.setTag("SELECTED");
                        } else {

                        }
                        // notifyDataSetChanged();
                    });
                } else {
                    holder.arrow.setColorFilter(ContextCompat.getColor(context, R.color.textColorYellow), android.graphics.PorterDuff.Mode.MULTIPLY);
                }
            } else {
                holder.textViewTrainingName.setText(mDisplayedValues.get(position).getFolderName());
                holder.folderIcon.setImageDrawable(activity.getResources().getDrawable(R.drawable.foldericon));
                holder.Assign.setVisibility(GONE);
                holder.Edit.setVisibility(GONE);
                holder.Copy.setVisibility(GONE);
                holder.Move.setVisibility(VISIBLE);
                holder.rLayoutForTraining.setTag("folder" + position);
                holder.arrow.setColorFilter(ContextCompat.getColor(context, R.color.textColorYellow), android.graphics.PorterDuff.Mode.MULTIPLY);
            }


            holder.rLayoutForTraining.setSwipeListener(new SwipeRevealLayout.SwipeListener() {
                @Override
                public void onClosed(SwipeRevealLayout view) {

                }

                @Override
                public void onOpened(SwipeRevealLayout view) {
                }

                @Override
                public void onSlide(SwipeRevealLayout view, float slideOffset) {
                    if (mDisplayedValues.get(position).getProgramName() != null) {
                        holder.Assign.setVisibility(View.VISIBLE);
                        holder.Edit.setVisibility(View.VISIBLE);
                        holder.Copy.setVisibility(View.VISIBLE);
                        holder.Move.setVisibility(VISIBLE);
                    } else {
                        holder.Assign.setVisibility(GONE);
                        holder.Edit.setVisibility(GONE);
                        holder.Copy.setVisibility(GONE);
                        holder.Move.setVisibility(VISIBLE);
                    }
                }
            });

            holder.Move.setOnClickListener(view1 -> {
                //whichAPICALLED = "Move";
                if (mDisplayedValues.get(position).getProgramName() != null) {
                    MoveFolder = false;
                    MoveTraining = true;
                } else {
                    MoveFolder = true;
                    MoveTraining = false;
                }
                holder.rLayoutForTraining.close(true);
                CopiedData = mDisplayedValues.get(position).getId();
                //sActivityXX.yourPaste();
                ((CoachNevigationDrawerScreen) getActivity()).LayoutForFolder.setVisibility(View.VISIBLE);
            });

            if (gotoScreenwithID.equalsIgnoreCase(mDisplayedValues.get(position).getProgramName())) {
                hide();
                startActivityX(mDisplayedValues.get(position).getId());
                activity.overridePendingTransition(R.anim.exit, R.anim.enter);
                gotoScreenwithID = "";
            }

            holder.Delete.setOnClickListener(view -> {
                holder.rLayoutForTraining.close(true);
                POSITION = position;
                if (mDisplayedValues.get(position).getProgramName() != null) {
                    showAlertDelete(position, "Deletetraining", "Do you want to delete " + mDisplayedValues.get(position).getProgramName());
                } else {
                    showAlertDelete(position, "Delete", "Do you want to delete " + mDisplayedValues.get(position).getFolderName());
                }
            });
            holder.Copy.setOnClickListener(view -> {
                whichAPICALLED = "Copy";
                holder.rLayoutForTraining.close(true);
                CopiedData = mDisplayedValues.get(position).getId();
                ((CoachNevigationDrawerScreen) getActivity()).LayoutForFolder.setVisibility(View.VISIBLE);
                //showAlert(position,"");
            });
            holder.Assign.setOnClickListener(view -> {
                showDialogofTraining(activity, 0, 0, "", "", mDisplayedValues.get(position).getId());
                holder.rLayoutForTraining.close(true);
            });
            holder.Rename.setOnClickListener(view -> {
                POSITION = position;
                if (mDisplayedValues.get(position).getProgramName() != null) {
                    whichAPICALLED = "RenameTraining";
                    showAlert(position, whichAPICALLED, "Rename Training", mDisplayedValues.get(position).getProgramName());
                } else {
                    whichAPICALLED = "RenameFolder";
                    showAlert(position, whichAPICALLED, "Rename Folder", mDisplayedValues.get(position).getFolderName());
                }
                holder.rLayoutForTraining.close(true);
            });
            holder.Edit.setOnClickListener(view -> {
                startActivityX(mDisplayedValues.get(position).getId());
                holder.rLayoutForTraining.close(true);
            });


            holder.rLayoutForTraining.setOnTouchListener(new OnSwipeListener(activity) {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick() {
                    super.onClick();
                    holder.rLayoutForTraining.close(true);
                    if (mDisplayedValues.get(position).getProgramName() != null) {
                        if (PurchaseTP) {
                            if (holder.arrow.getTag().toString().equalsIgnoreCase("SELECTED")) {
                                for (int i = 0; i < arrayofFoldersSelected.size(); i++) {
                                    if (arrayofFoldersSelected.get(i).getId().equalsIgnoreCase(mDisplayedValues.get(position).getId())) {
                                        //arrayofFoldersSelected.remove(i);
                                        //notifyDataSetChanged();
                                        break;
                                    }
                                }
                            } else {
                                whichAPICALLED = "setTrainingProgramForSale";
                                CoachNevigationDrawerScreen.CAllAPI = true;
                                webServices.setTrainingProgramForSale(SportsIDForTPpurchase + "", mDisplayedValues.get(position).getId(), context, FragmentTrainingAndFolder.this);
                                arrayofFoldersSelected.add(mDisplayedValues.get(position));
                                notifyDataSetChanged();
                            }
                        } else {
                            startActivityX(mDisplayedValues.get(position).getId());
                        }

                    } else {
                        fragment = new FragmentTrainingAndFolder(mDisplayedValues.get(position).getId());
                        Bundle bundle = new Bundle();
                        bundle.putString("tag", mDisplayedValues.get(position).getId());
                        fragment.setArguments(bundle);
                        fragment.setEnterTransition(new Slide(Gravity.RIGHT));
                        fragment.setExitTransition(new Slide(Gravity.LEFT));
                        Log.e(VolleyLog.TAG, "getView: " + mDisplayedValues.get(position).getId());
                        folderId = parentid;
                        fm.beginTransaction().add(R.id.content_frame, fragment, parentid).hide(FragmentTrainingAndFolder.this).addToBackStack(null).commit();
                        imageViewSliderDrawerToggleIcon.setVisibility(View.GONE);
                        imageViewSliderBackIcon.setVisibility(View.VISIBLE);
                        LockDrawer();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            int count = 0;
            try {
                count = mDisplayedValues.size();
            } catch (Exception c) {

            }
            return count;
        }

        public void filter(String s) {
            String text = s.toLowerCase();
            int countx = 0;
            Log.e(VolleyLog.TAG, "filter: v " + text);
            text = text.toLowerCase().trim();

            Log.e(VolleyLog.TAG, "filter:v " + arrayofFolders.size() + "        " + mDisplayedValues.size());
            if (text.length() == 0) {
                mDisplayedValues = arrayofFolders;
            } else {
                mDisplayedValues = new ArrayList<ArrayofFolder>();
                for (int i = 0; i < arrayofFolders.size(); i++) {
                    String userId = "";
                    String id = "";
                    String ProgramName = null;
                    String FolderName = null;
                    if (arrayofFolders.get(i).getProgramName() != null) {
                        if (arrayofFolders.get(i).getProgramName().toLowerCase().contains(text)) {

                            mDisplayedValues.add(arrayofFolders.get(i));
                            countx += 1;
                        }
                    } else if (arrayofFolders.get(i).getFolderName() != null) {
                        if (arrayofFolders.get(i).getFolderName().toLowerCase().contains(text)) {

                            mDisplayedValues.add(arrayofFolders.get(i));
                            countx += 1;
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }


    }
}

