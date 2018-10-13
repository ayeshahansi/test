package com.aeturnum.scs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)// to ignore the unwanted propeties 
public class DataCollectionPoint implements Comparable<DataCollectionPoint> {

    @JsonProperty("dataCollectionPointID")
    private int dataCollectionPointID;
    @JsonProperty("siteID")
    private int siteID;

    public int getDataCollectionPointID() {
        return dataCollectionPointID;
    }

    public void setDataCollectionPointID(int dataCollectionPointID) {
        this.dataCollectionPointID = dataCollectionPointID;
    }


    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    public boolean equals(Object o) {
        if (o instanceof DataCollectionPoint && ((DataCollectionPoint) o).getDataCollectionPointID() == this.dataCollectionPointID
                && ((DataCollectionPoint) o).getSiteID() == this.siteID) {
            return true;
        }

        return false;
    }

    @Override
    public int compareTo(DataCollectionPoint o) {
        int compareDataCollectionPointID = ((DataCollectionPoint) o).getDataCollectionPointID();

        // ascending order
        return this.dataCollectionPointID - compareDataCollectionPointID;

    }
}
