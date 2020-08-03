package com.org.godspeed.videoClassAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.org.godspeed.R;
import com.org.godspeed.response_JsonS.getVideoClass.LiveClassCheckIn;
import com.org.godspeed.utility.CustomTypeface;

import java.util.List;

import static android.view.View.GONE;
import static com.org.godspeed.utility.WebServices.BASE_URL_FOR_IMAGES;

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

        Glide.with(context).load(BASE_URL_FOR_IMAGES + f.get(i).getUser_image()).error(context.getResources().getDrawable(R.drawable.logo_f)).into(Holder.LevelImage);

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
