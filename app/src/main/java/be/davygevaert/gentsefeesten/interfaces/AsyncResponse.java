package be.davygevaert.gentsefeesten.interfaces;

import java.util.ArrayList;

import be.davygevaert.gentsefeesten.model.Parking;

/**
 * Created by Davy on 28/12/2015.
 */
public interface AsyncResponse {
    void processFinish(ArrayList<Parking> parkingArrayList);
}
