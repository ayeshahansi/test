package com.aeturnum.scs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)// to ignore the unwanted propeties
public class WellHeadType {
    @JsonProperty("WellHeadTypeID")
    private int wellHeadTypeID;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    public int getWellHeadTypeID() {
return wellHeadTypeID;
    }

    public void setWellHeadTypeID(int wellHeadTypeID){
        this.wellHeadTypeID=wellHeadTypeID;


    }



}
