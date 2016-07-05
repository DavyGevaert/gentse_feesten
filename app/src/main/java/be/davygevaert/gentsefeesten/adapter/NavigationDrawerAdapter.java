package be.davygevaert.gentsefeesten.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.davygevaert.gentsefeesten.DataActivity;
import be.davygevaert.gentsefeesten.InfoActivity;
import be.davygevaert.gentsefeesten.InfoGentActivity;
import be.davygevaert.gentsefeesten.ParkingActivity;
import be.davygevaert.gentsefeesten.R;
import be.davygevaert.gentsefeesten.asynctask.VerkrijgParkingsTask;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.interfaces.AsyncResponse;
import be.davygevaert.gentsefeesten.model.NavigationDrawerItem;
import be.davygevaert.gentsefeesten.model.Parking;
import be.davygevaert.gentsefeesten.tools.Animation;

/**
 * Created by Davy on 27/06/2016.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {

    private List<NavigationDrawerItem> mDataList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Parking> parkings;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.mDataList = data;
        this.parkings = new ArrayList<Parking>();

        new VerkrijgParkingsTask(new AsyncResponse() {
            @Override
            public void processFinish(ArrayList<Parking> parkingArrayList) {
                parkings = parkingArrayList;
            }
        }).execute(Constants.URL_BEZETTING_PARKINGS_REALTIME);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NavigationDrawerItem current = mDataList.get(position);

        holder.iv_Icoon.setImageResource(current.getAfbeeldingId());
        holder.tv_titel.setText(current.getTitel());

        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_Icoon;
        TextView tv_titel;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            iv_Icoon = (ImageView) itemView.findViewById(R.id.img_nav_drawer_item_icoon);
            tv_titel = (TextView) itemView.findViewById(R.id.tv_nav_drawer_item_titel);
        }


        // listeners
        public void setListeners() {
            tv_titel.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View view) {

            Intent intent = null;

            switch (view.getId()) {

                    case R.id.tv_nav_drawer_item_titel:

                            switch (getPosition()) {

                                case 0:
                                    // Toast.makeText(context, "geklikt op positie : " + getPosition(), Toast.LENGTH_LONG).show();

                                    // hier mag je de activity sluiten, daar ik veronderstel dat de eindgebruiker terug per dag wil beginnen zoeken ...
                                    ((Activity) context).finish();
                                    intent = new Intent(context, DataActivity.class);
                                    //intent.putExtra("navigatie_naar_home_vanuit_navigation_drawer", "reset");
                                    break;
                                case 1:
                                    // Toast.makeText(context, "geklikt op positie : " + getPosition(), Toast.LENGTH_LONG).show();

                                    intent = new Intent(context, ParkingActivity.class);
                                    intent.putParcelableArrayListExtra("parkings", parkings);
                                    break;
                                case 2:
                                    // Toast.makeText(context, "geklikt op positie : " + getPosition(), Toast.LENGTH_LONG).show();
                                    intent = new Intent(context, InfoGentActivity.class);
                                    break;
                                case 3:
                                    // Toast.makeText(context, "geklikt op positie : " + getPosition(), Toast.LENGTH_LONG).show();
                                    intent = new Intent(context, InfoActivity.class);
                                    break;

                            }
                    break;
            }

            Animation.startActivity_With_Animation(intent, context, Constants.AnimType.FadeJava);
        }
    }
}
