package com.org.godspeed.ViewHolderClasses;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.godspeed.R;


public class CircleOvelLongViewHolder extends RecyclerView.ViewHolder {
    public TextView teamName;
    public RelativeLayout rLayoutofTeam;

    public CircleOvelLongViewHolder(@NonNull View itemView) {
        super(itemView);
        teamName = itemView.findViewById(R.id.teamName);
        rLayoutofTeam = itemView.findViewById(R.id.rLayoutofTeam);
    }
}
