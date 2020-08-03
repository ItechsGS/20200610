
package com.org.godspeed.response_JsonS.Perform_analytic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PerformAnalytic implements Serializable {

    private final static long serialVersionUID = 7294668928721741567L;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    @SerializedName("exercies")
    @Expose
    private List<Exercy> exercies = null;

    /**
     * No args constructor for use in serialization
     */


    /**
     * @param exercies
     * @param typeName
     * @param typeId
     */
    public PerformAnalytic(String typeId, String typeName, List<Exercy> exercies) {
        super();
        this.typeId = typeId;
        this.typeName = typeName;
        this.exercies = exercies;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Exercy> getExercies() {
        return exercies;
    }

    public void setExercies(List<Exercy> exercies) {
        this.exercies = exercies;
    }

}
