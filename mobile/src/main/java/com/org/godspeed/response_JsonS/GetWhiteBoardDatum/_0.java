
package com.org.godspeed.response_JsonS.GetWhiteBoardDatum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class _0 implements Serializable {

    private final static long serialVersionUID = 8901263763012824683L;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("piller_name")
    @Expose
    private String pillerName;
    @SerializedName("pillar_image")
    @Expose
    private String pillarImage;
    @SerializedName("pillar_status")
    @Expose
    private String pillarStatus;
    @SerializedName("pillar_detail")
    @Expose
    private PillarDetail pillarDetail;
    @SerializedName("excercise_all_types")
    @Expose
    private List<ExcerciseAllType> excerciseAllTypes = null;

    /**
     * No args constructor for use in serialization
     *
     */


    /**
     * @param pillerName
     * @param pillarStatus
     * @param pillarImage
     * @param excerciseAllTypes
     * @param id
     * @param pillarDetail
     */
    public _0(String id, String pillerName, String pillarImage, String pillarStatus, PillarDetail pillarDetail, List<ExcerciseAllType> excerciseAllTypes) {
        super();
        this.id = id;
        this.pillerName = pillerName;
        this.pillarImage = pillarImage;
        this.pillarStatus = pillarStatus;
        this.pillarDetail = pillarDetail;
        this.excerciseAllTypes = excerciseAllTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPillerName() {
        return pillerName;
    }

    public void setPillerName(String pillerName) {
        this.pillerName = pillerName;
    }

    public String getPillarImage() {
        return pillarImage;
    }

    public void setPillarImage(String pillarImage) {
        this.pillarImage = pillarImage;
    }

    public String getPillarStatus() {
        return pillarStatus;
    }

    public void setPillarStatus(String pillarStatus) {
        this.pillarStatus = pillarStatus;
    }

    public PillarDetail getPillarDetail() {
        return pillarDetail;
    }

    public void setPillarDetail(PillarDetail pillarDetail) {
        this.pillarDetail = pillarDetail;
    }

    public List<ExcerciseAllType> getExcerciseAllTypes() {
        return excerciseAllTypes;
    }

    public void setExcerciseAllTypes(List<ExcerciseAllType> excerciseAllTypes) {
        this.excerciseAllTypes = excerciseAllTypes;
    }

}
