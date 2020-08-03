package com.org.godspeed.utility;

import java.util.List;

/**
 * Created by Tanveer on 07/24/2018.
 */

public class GetTeamsDetailsClass {

    public String team_id = "", team_name = "", coache_id = "", logo = "", background_image = "", gym_account_id = "", last_modify_time = "", sports_id = "", user_id = "";

    public List<Training_program_detail> training_program_details = null;

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getCoache_id() {
        return coache_id;
    }

    public void setCoache_id(String coache_id) {
        this.coache_id = coache_id;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getGym_account_id() {
        return gym_account_id;
    }

    public void setGym_account_id(String gym_account_id) {
        this.gym_account_id = gym_account_id;
    }

    public String getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(String last_modify_time) {
        this.last_modify_time = last_modify_time;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSports_id() {
        return sports_id;
    }

    public void setSports_id(String sports_id) {
        this.sports_id = sports_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}
