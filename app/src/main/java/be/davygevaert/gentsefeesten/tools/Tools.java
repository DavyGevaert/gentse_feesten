package be.davygevaert.gentsefeesten.tools;

import be.davygevaert.gentsefeesten.model.Data;

/**
 * Created by Davy on 17/06/2016.
 */
public class Tools {
    public static String ConvertDay_To_DayStartingWithCap(Data mData) {
        String dayStartingWithCap =  mData.getDay().substring(0, 1).toUpperCase() + mData.getDay().substring(1);
        return dayStartingWithCap;
    }
}
