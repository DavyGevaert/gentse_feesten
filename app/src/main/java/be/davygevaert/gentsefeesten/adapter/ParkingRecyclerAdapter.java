package be.davygevaert.gentsefeesten.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.davygevaert.gentsefeesten.R;
import be.davygevaert.gentsefeesten.model.Parking;

/**
 * Created by Davy on 29/06/2016.
 */
public class ParkingRecyclerAdapter extends RecyclerView.Adapter<ParkingRecyclerAdapter.MyViewHolder> {

    private static final String TAG = LocatieRecyclerAdapter.class.getSimpleName();

    private List<Parking> mParkings;
    private LayoutInflater mInflater;
    private Context context;

    public ParkingRecyclerAdapter(Context context, ArrayList<Parking> parkingsArrayList) {
        this.mParkings = parkingsArrayList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item_parking, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mParkings.size();
    }

    // this method is called when each row in the recyclerview is created
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Log.d(TAG, "onBindViewHolder " + position);

        Parking currentObj = mParkings.get(position);
        holder.setParking(currentObj, position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView parking_naam;
        TextView parking_adres;
        TextView bezetting;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            parking_naam = (TextView) itemView.findViewById(R.id.tv_parking_naam);
            parking_adres = (TextView) itemView.findViewById(R.id.tv_parking_adres);
            bezetting = (TextView) itemView.findViewById(R.id.tv_bezetting);
        }

        public void setParking(Parking currentParking, int position) {

            for (Parking c : mParkings) {
                this.parking_naam.setText(currentParking.getName());
                this.parking_adres.setText(currentParking.getAddress());
                this.bezetting.setText(currentParking.getStatus().getTotalCapacity() - currentParking.getStatus().getAvailableCapacity() + "/" + (int) currentParking.getStatus().getTotalCapacity());
            }
            this.position = position;
        }
    }
}
