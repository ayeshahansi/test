package com.aeturnum.scs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LFGDataCollectionPointDescription {

	@JsonProperty("lfgDataCollectionPointDescritpionID")
	private int lFGDataCollectionPointDescritpionID;

	@JsonProperty("dataCollectionPointID")
	private int dataCollectionPointID;

	public int getLFGDataCollectionPointDescritpionID() {
		return lFGDataCollectionPointDescritpionID;
	}

	public void setLFGDataCollectionPointDescritpionID(int lFGDataCollectionPointDescritpionID) {
		this.lFGDataCollectionPointDescritpionID = lFGDataCollectionPointDescritpionID;
	}

	public int getDataCollectionPointID() {
		return dataCollectionPointID;
	}

	public void setDataCollectionPointID(int dataCollectionPointID) {
		this.dataCollectionPointID = dataCollectionPointID;

	}

	public boolean equals(Object o) {
		if (o instanceof LFGDataCollectionPointDescription
				&& ((LFGDataCollectionPointDescription) o)
						.getLFGDataCollectionPointDescritpionID() == this.lFGDataCollectionPointDescritpionID
				&& ((LFGDataCollectionPointDescription) o).getDataCollectionPointID() == this.dataCollectionPointID) {
			return true;
		}

		return false;
	}

}