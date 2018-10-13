package com.aeturnum.scs.comparators;

import com.aeturnum.scs.model.DataCollectionPoint;

import java.util.Comparator;

public class DataCollectionPointIdComparator implements Comparator<DataCollectionPoint> {
    @Override
    public int compare(DataCollectionPoint o1, DataCollectionPoint o2) {
        return o1.getDataCollectionPointID() - o2.getDataCollectionPointID();
    }
}
