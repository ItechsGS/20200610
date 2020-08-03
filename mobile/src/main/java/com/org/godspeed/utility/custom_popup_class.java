package com.org.godspeed.utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cpiz.android.bubbleview.BubbleLinearLayout;
import com.org.godspeed.R;
import com.org.godspeed.RecyclerViewClickCheck;
import com.org.godspeed.loginData.Team;
import com.org.godspeed.response_JsonS.GetSport.GetSport;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.GetTeamsDetailsClas;

import java.util.ArrayList;
import java.util.List;

import static com.org.godspeed.allOtherClasses.CoachAddExerciseScreen.dpToPx;
import static com.org.godspeed.service.SchoolDataService.getLanguageList;
import static com.org.godspeed.service.SchoolDataService.getPositionList;
import static com.org.godspeed.service.SchoolDataService.getSchoolsList;
import static com.org.godspeed.service.SchoolDataService.getSportsList;
import static com.org.godspeed.service.SchoolDataService.getTeamsDetailsClas;
import static com.org.godspeed.service.SchoolDataService.timezoneLists;


public class custom_popup_class extends Dialog {


    RecyclerView recyclerView;
    RecyclerView ExerciseRecyclerByType;
    ImageView backArrowEx;
    LinearLayout DialogMainRly;
    TextView HeaderName;
    Context context;
    ArrayList<Integer> selectedItemsId = new ArrayList<>();
    RecyclerViewClickCheck recyclerViewClickCheck;
    String tag;
    List<Team> teamsLocal = new ArrayList<>();
    private String message;
    private String title;
    private int icon = 0;
    private View.OnClickListener btYesListener = null;
    private View.OnClickListener btNoListener = null;
    private View.OnClickListener SendMessageCoach = null;
    private TextWatcher textWatcher = null;
    private String btYesText;
    private String btNoText;
    private String type;
    private Boolean OneButton = false;
    private Boolean CancelAble = true;
    private Boolean EditTextView = false;
    private List<GetTeamsDetailsClas> SelectedteamsLocal = new ArrayList<>();
    private List<GetSport> SelectedSportsLocal = new ArrayList<>();

    public custom_popup_class(Context context, String type, String tag, RecyclerViewClickCheck recyclerViewClickCheck, List<Team> teamsLocal) {
        super(context);
        this.context = context;
        this.type = type;
        this.recyclerViewClickCheck = recyclerViewClickCheck;
        this.tag = tag;
        this.teamsLocal = teamsLocal;
    }

    public custom_popup_class(Context context, String type, String tag, RecyclerViewClickCheck recyclerViewClickCheck) {
        super(context);
        this.context = context;
        this.type = type;
        this.recyclerViewClickCheck = recyclerViewClickCheck;
        this.tag = tag;
    }

    public custom_popup_class(Context context, String type, String tag, RecyclerViewClickCheck recyclerViewClickCheck, ArrayList<Integer> selectedItemsId) {
        super(context);
        this.context = context;
        this.type = type;
        this.selectedItemsId = selectedItemsId;
        this.recyclerViewClickCheck = recyclerViewClickCheck;
        this.tag = tag;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.empty_array);

        setCanceledOnTouchOutside(CancelAble);
        getWindow().setDimAmount(0);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        BubbleLinearLayout bubbleView = findViewById(R.id.mainRlyofAddset);
        bubbleView.setCornerRadius(70f);
        bubbleView.setArrowWidth(70f);
        bubbleView.setArrowHeight(30f);
        bubbleView.setFillColor(context.getResources().getColor(R.color.color_gray_for_toggle_unselected));

        CardView cardView = findViewById(R.id.VIewView);
        cardView.getLayoutParams().height = dpToPx(500);

        RelativeLayout SearchAthleteText = findViewById(R.id.SearchAthleteText);
        SearchAthleteText.setVisibility(View.GONE);


        EditText calc_txt_Prise = findViewById(R.id.calc_txt_Prise);
        ImageView calc_clear_txt_Prise = findViewById(R.id.calc_clear_txt_Prise);
        recyclerView = findViewById(R.id.custompopwindow);
        ExerciseRecyclerByType = findViewById(R.id.ExerciseRecyclerByType);
        backArrowEx = findViewById(R.id.backArrowEx);
        DialogMainRly = findViewById(R.id.DialogMainRly);


        HeaderName = findViewById(R.id.HeaderName);

        HeaderName.setText(message);
        HeaderName.setHeight(dpToPx(40));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);


        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(new SchoolDataRecycler(context, type, tag, recyclerViewClickCheck, this, teamsLocal, selectedItemsId));

    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setCoachMessageButton(View.OnClickListener onClickListener) {
        dismiss();

        this.SendMessageCoach = onClickListener;
    }


    public class SchoolDataRecycler extends RecyclerView.Adapter<SchoolDataRecycler.ListofNamesViewHolder> {
        ArrayList<Integer> selectedItemsId = new ArrayList<>();
        private String Type = "";
        private RecyclerViewClickCheck recyclerViewClickCheck;
        private Context context;
        private custom_popup_class custom_popup_class;
        private String SelectedID = "";
        private List<Team> teamsLocal;

        public SchoolDataRecycler(Context context, String Type, String SelectedID, RecyclerViewClickCheck recyclerViewClickCheck, custom_popup_class custom_popup_class, List<Team> teamsLocal) {
            this.Type = Type;
            this.context = context;
            this.custom_popup_class = custom_popup_class;
            this.recyclerViewClickCheck = recyclerViewClickCheck;
            this.SelectedID = SelectedID;
            this.teamsLocal = teamsLocal;
        }

        public SchoolDataRecycler(Context context, String Type, String SelectedID, RecyclerViewClickCheck recyclerViewClickCheck, custom_popup_class custom_popup_class, List<Team> teamsLocal, ArrayList<Integer> selectedItemsId) {
            this.Type = Type;
            this.context = context;
            this.custom_popup_class = custom_popup_class;
            this.recyclerViewClickCheck = recyclerViewClickCheck;
            this.SelectedID = SelectedID;
            this.teamsLocal = teamsLocal;
            this.selectedItemsId = selectedItemsId;
        }


        @NonNull
        @Override
        public SchoolDataRecycler.ListofNamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.abr_name, parent, false);
            return new SchoolDataRecycler.ListofNamesViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onBindViewHolder(@NonNull SchoolDataRecycler.ListofNamesViewHolder holder, int position) {
            holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.unselected_tick_icon));

            holder.llayout.setTag("UNSELECTED");


            holder.llayout.setOnClickListener(view -> {
                if (Type.equalsIgnoreCase("teamList")) {
                    if (holder.llayout.getTag().toString().equalsIgnoreCase("UNSELECTED")) {
                        SelectedteamsLocal.add(getTeamsDetailsClas.get(position));
                        holder.llayout.setTag("SELECTED");
                        selectedItemsId.add(Integer.valueOf(getTeamsDetailsClas.get(position).getTeamId()));
                    } else {
                        for (int i = 0; i < SelectedteamsLocal.size(); i++) {
                            if (SelectedteamsLocal.get(i).getTeamId().equalsIgnoreCase(getTeamsDetailsClas.get(position).getTeamId())) {
                                SelectedteamsLocal.remove(i);
                                holder.llayout.setTag("UNSELECTED");
                                break;
                            }
                        }
                        for (int i = 0; i < selectedItemsId.size(); i++) {
                            if (selectedItemsId.get(i).toString().equalsIgnoreCase(getTeamsDetailsClas.get(position).getTeamId())) {
                                selectedItemsId.remove(i);
                                holder.llayout.setTag("UNSELECTED");
                                break;
                            }
                        }
                    }
                    recyclerViewClickCheck.OnItemClickListReturn(SelectedteamsLocal, null);
                    notifyDataSetChanged();
                } else if (Type.equalsIgnoreCase("sports")) {
                    if (holder.llayout.getTag().toString().equalsIgnoreCase("UNSELECTED")) {
                        SelectedSportsLocal.add(getSportsList.get(position));
                        holder.llayout.setTag("SELECTED");
                        selectedItemsId.add(Integer.valueOf(getSportsList.get(position).getSportId()));
                        recyclerViewClickCheck.OnItemClickListReturn(null, SelectedSportsLocal);
                    } else {
                        for (int i = 0; i < SelectedSportsLocal.size(); i++) {
                            if (SelectedSportsLocal.get(i).getSportId().equalsIgnoreCase(getSportsList.get(position).getSportId())) {
                                SelectedSportsLocal.remove(i);
                                holder.llayout.setTag("UNSELECTED");

                                break;
                            }
                        }
                        for (int i = 0; i < selectedItemsId.size(); i++) {
                            if (selectedItemsId.get(i).toString().equalsIgnoreCase(getSportsList.get(position).getSportId())) {
                                selectedItemsId.remove(i);
                                holder.llayout.setTag("UNSELECTED");
                                break;
                            }
                        }
                    }

                    recyclerViewClickCheck.OnItemClickListReturn(null, SelectedSportsLocal);

                    notifyDataSetChanged();

                } else {
                    holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.selected_tick_icon));
                    recyclerViewClickCheck.OnItemClick(position);
                    custom_popup_class.dismiss();
                }

            });
            if (Type.equalsIgnoreCase("school")) {
                holder.Name.setText(getSchoolsList.get(position).getSchoolName());
                if (SelectedID != null && SelectedID.equalsIgnoreCase(getSchoolsList.get(position).getSchoolId())) {
                    holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.selected_tick_icon));
                }
            } else if (Type.equalsIgnoreCase("position")) {
                holder.Name.setText(getPositionList.get(position).getPositionTitleName());
                if (SelectedID != null && SelectedID.equalsIgnoreCase(getPositionList.get(position).getPositionTitleId())) {
                    holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.selected_tick_icon));
                }
            } else if (Type.equalsIgnoreCase("team")) {

                holder.Name.setText(teamsLocal.get(position).getTeamName());
                if (SelectedID != null && SelectedID.equalsIgnoreCase(teamsLocal.get(position).getTeamId())) {
                    holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.selected_tick_icon));
                }
            } else if (Type.equalsIgnoreCase("teamList")) {
                holder.Name.setText(getTeamsDetailsClas.get(position).getTeamName());
                selectedItemsId.forEach(m -> {
                    if (m.toString().equalsIgnoreCase(getTeamsDetailsClas.get(position).getTeamId())) {
                        holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.selected_tick_icon));
                        holder.llayout.setTag("SELECTED");
                        SelectedteamsLocal.add(getTeamsDetailsClas.get(position));
                    }
                });
            } else if (Type.equalsIgnoreCase("sports")) {
                holder.Name.setText(getSportsList.get(position).getSportName());
                selectedItemsId.forEach(m -> {
                    if (m.toString().equalsIgnoreCase(getSportsList.get(position).getSportId())) {
                        holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.selected_tick_icon));
                        holder.llayout.setTag("SELECTED");
                        SelectedSportsLocal.add(getSportsList.get(position));
                    }
                });
            } else if (Type.equalsIgnoreCase("language")) {
                holder.Name.setText(getLanguageList.get(position).getLanguageName());
                if (SelectedID != null && SelectedID.equalsIgnoreCase(getLanguageList.get(position).getLanguageId())) {
                    holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.selected_tick_icon));
                }
            } else if (Type.equalsIgnoreCase("timezone")) {
                holder.Name.setText(timezoneLists.get(position).getTimeZoneName());
                if (SelectedID != null && SelectedID.equalsIgnoreCase(timezoneLists.get(position).getTimeZoneName())) {
                    holder.unSelectTick.setImageDrawable(context.getResources().getDrawable(R.drawable.selected_tick_icon));
                }
            }
        }

        @Override
        public int getItemCount() {
            int Count = 0;
            if (Type.equalsIgnoreCase("school")) {
                Count = getSchoolsList.size();
            } else if (Type.equalsIgnoreCase("position")) {
                Count = getPositionList.size();
            } else if (Type.equalsIgnoreCase("team")) {
                Count = teamsLocal.size();
            } else if (Type.equalsIgnoreCase("language")) {
                Count = getLanguageList.size();
            } else if (Type.equalsIgnoreCase("teamList")) {
                Count = getTeamsDetailsClas.size();
            } else if (Type.equalsIgnoreCase("sports")) {
                Count = getSportsList.size();
            } else if (Type.equalsIgnoreCase("timezone")) {
                Count = timezoneLists.size();
            }
            return Count;
        }


        public class ListofNamesViewHolder extends RecyclerView.ViewHolder {
            ImageView unSelectTick;
            TextView Name;

            LinearLayout RlLayout;

            RelativeLayout llayout;


            public ListofNamesViewHolder(@NonNull View itemView) {
                super(itemView);
                Name = itemView.findViewById(R.id.DoseName);
                unSelectTick = itemView.findViewById(R.id.unSelectTick);


                llayout = itemView.findViewById(R.id.llayout);

                RlLayout = itemView.findViewById(R.id.RlLayout);
                RlLayout.setVisibility(View.GONE);
            }
        }


    }


}
