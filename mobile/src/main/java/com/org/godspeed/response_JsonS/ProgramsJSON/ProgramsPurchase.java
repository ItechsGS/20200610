
package com.org.godspeed.response_JsonS.ProgramsJSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProgramsPurchase implements Serializable {

    //    //private final static long serialVersionUID= -3879464332608095191L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("program")
    @Expose
    private List<Program> program = null;

    /**
     * No args constructor for use in serialization
     */


    /**
     * @param id
     * @param categoryName
     * @param program
     * @param image
     */
    public ProgramsPurchase(String id, String categoryName, String image, List<Program> program) {
        super();
        this.id = id;
        this.categoryName = categoryName;
        this.image = image;
        this.program = program;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Program> getProgram() {
        return program;
    }

    public void setProgram(List<Program> program) {
        this.program = program;
    }

}
