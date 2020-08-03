package com.org.godspeed.allOtherClasses;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.AbrPojo.getAbrDetail.ABR;

import java.util.List;

import static android.view.View.VISIBLE;

public class DoseDetail extends RecyclerView.Adapter<DoseDetail.abrDetail> {
    Context context;
    List<ABR> abrs;
    Boolean SelectExercise;

    public DoseDetail(Context context, List<ABR> abrs, Boolean SelectExercise) {
        this.abrs = abrs;
        this.context = context;
        this.SelectExercise = SelectExercise;
    }

    @NonNull
    @Override
    public DoseDetail.abrDetail onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.abr_detail_list, viewGroup, false);

        return new DoseDetail.abrDetail(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoseDetail.abrDetail holder, final int i) {
        holder.WeekCount.setText("Week " + (i + 1));

        if (SelectExercise) {
            holder.repsPercent1.setTextColor(context.getResources().getColor(R.color.color_red_value));
            holder.repsPercent2.setTextColor(context.getResources().getColor(R.color.color_red_value));
            holder.repsPercent3.setTextColor(context.getResources().getColor(R.color.color_red_value));
            holder.repsPercent4.setTextColor(context.getResources().getColor(R.color.color_red_value));
            holder.repsPercent5.setTextColor(context.getResources().getColor(R.color.color_red_value));
            holder.repsPercentLabel.setVisibility(VISIBLE);
        }

        //for(int x = 0;x<abrs.get(i).get1().size();x++){
        if (i == 0) {
            if (SelectExercise) {
                holder.WeekCount.setTextColor(context.getResources().getColor(R.color.textColorYellow));
            }

            holder.repsValue1.setText(abrs.get(0).get1().get(0).getDay1Value());
            holder.repsPercent1.setText(abrs.get(0).get1().get(0).getDay1Percent());

            holder.repsValue2.setText(abrs.get(0).get1().get(0).getDay2Value());
            holder.repsPercent2.setText(abrs.get(0).get1().get(0).getDay2Percent());

            holder.repsValue3.setText(abrs.get(0).get1().get(0).getDay3Value());
            holder.repsPercent3.setText(abrs.get(0).get1().get(0).getDay3Percent());

            holder.repsValue4.setText(abrs.get(0).get1().get(0).getDay4Value());
            holder.repsPercent4.setText(abrs.get(0).get1().get(0).getDay4Percent());

            holder.repsValue5.setText(abrs.get(0).get1().get(0).getDay5Value());
            holder.repsPercent5.setText(abrs.get(0).get1().get(0).getDay5Percent());

        }
        if (i == 1) {
            if (SelectExercise) {
                holder.WeekCount.setTextColor(Color.parseColor("#fc852d"));
            }

            holder.repsValue1.setText(abrs.get(1).get2().get(0).getDay1Value());
            holder.repsPercent1.setText(abrs.get(1).get2().get(0).getDay1Percent());


            holder.repsValue2.setText(abrs.get(1).get2().get(0).getDay2Value());
            holder.repsPercent2.setText(abrs.get(1).get2().get(0).getDay2Percent());

            holder.repsValue3.setText(abrs.get(1).get2().get(0).getDay3Value());
            holder.repsPercent3.setText(abrs.get(1).get2().get(0).getDay3Percent());

            holder.repsValue4.setText(abrs.get(1).get2().get(0).getDay4Value());
            holder.repsPercent4.setText(abrs.get(1).get2().get(0).getDay4Percent());

            holder.repsValue5.setText(abrs.get(1).get2().get(0).getDay5Value());
            holder.repsPercent5.setText(abrs.get(1).get2().get(0).getDay5Percent());
        }
        if (i == 2) {
            if (SelectExercise) {
                holder.WeekCount.setTextColor(Color.parseColor("#a02d26"));
            }

            holder.repsValue1.setText(abrs.get(2).get3().get(0).getDay1Value());
            holder.repsPercent1.setText(abrs.get(2).get3().get(0).getDay1Percent());


            holder.repsValue2.setText(abrs.get(2).get3().get(0).getDay2Value());
            holder.repsPercent2.setText(abrs.get(2).get3().get(0).getDay2Percent());


            holder.repsValue3.setText(abrs.get(2).get3().get(0).getDay3Value());
            holder.repsPercent3.setText(abrs.get(2).get3().get(0).getDay3Percent());


            holder.repsValue4.setText(abrs.get(2).get3().get(0).getDay4Value());
            holder.repsPercent4.setText(abrs.get(2).get3().get(0).getDay4Percent());


            holder.repsValue5.setText(abrs.get(2).get3().get(0).getDay5Value());
            holder.repsPercent5.setText(abrs.get(2).get3().get(0).getDay5Percent());

        }
        if (i == 3) {
            if (SelectExercise) {
                holder.WeekCount.setTextColor(Color.parseColor("#0f8912"));
            }
            holder.repsValue1.setText(abrs.get(3).get4().get(0).getDay1Value());
            holder.repsPercent1.setText(abrs.get(3).get4().get(0).getDay1Percent());


            holder.repsValue2.setText(abrs.get(3).get4().get(0).getDay2Value());
            holder.repsPercent2.setText(abrs.get(3).get4().get(0).getDay2Percent());


            holder.repsValue3.setText(abrs.get(3).get4().get(0).getDay3Value());
            holder.repsPercent3.setText(abrs.get(3).get4().get(0).getDay3Percent());


            holder.repsValue4.setText(abrs.get(3).get4().get(0).getDay4Value());
            holder.repsPercent4.setText(abrs.get(3).get4().get(0).getDay4Percent());


            holder.repsValue5.setText(abrs.get(3).get4().get(0).getDay5Value());
            holder.repsPercent5.setText(abrs.get(3).get4().get(0).getDay5Percent());

        }
    }

    @Override
    public int getItemCount() {
        int s = 0;
        try {
            s = abrs.size();
        } catch (Exception x) {
        }
        return s;

    }

    public class abrDetail extends RecyclerView.ViewHolder {
        TextView WeekCount, repsValue1, repsPercent1;
        TextView repsValue2, repsPercent2;
        TextView repsValue3, repsPercent3;
        TextView repsValue4, repsPercent4;
        TextView repsValue5, repsPercent5;
        LinearLayout repsPercentLabel;

        public abrDetail(@NonNull View itemView) {
            super(itemView);

            repsPercentLabel = itemView.findViewById(R.id.repsPercentLabel);

            WeekCount = itemView.findViewById(R.id.WeekCount);
            repsValue1 = itemView.findViewById(R.id.repsValue1);
            repsPercent1 = itemView.findViewById(R.id.repsPercent1);

            repsValue2 = itemView.findViewById(R.id.repsValue2);
            repsPercent2 = itemView.findViewById(R.id.repsPercent2);

            repsValue3 = itemView.findViewById(R.id.repsValue3);
            repsPercent3 = itemView.findViewById(R.id.repsPercent3);

            repsValue4 = itemView.findViewById(R.id.repsValue4);
            repsPercent4 = itemView.findViewById(R.id.repsPercent4);

            repsValue4 = itemView.findViewById(R.id.repsValue4);
            repsPercent4 = itemView.findViewById(R.id.repsPercent4);

            repsValue5 = itemView.findViewById(R.id.repsValue5);
            repsPercent5 = itemView.findViewById(R.id.repsPercent5);
        }
    }
}