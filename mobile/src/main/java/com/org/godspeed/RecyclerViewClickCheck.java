package com.org.godspeed;

import com.org.godspeed.response_JsonS.GetSport.GetSport;
import com.org.godspeed.response_JsonS.TrainingProgramDetail.GetTeamsDetailsClas;

import java.util.List;

public interface RecyclerViewClickCheck {
    void OnItemClick(int position);

    void OnItemClickListReturn(List<GetTeamsDetailsClas> teamList, List<GetSport> getSportsList);
}
