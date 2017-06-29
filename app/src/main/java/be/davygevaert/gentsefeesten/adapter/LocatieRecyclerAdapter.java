package be.davygevaert.gentsefeesten.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.davygevaert.gentsefeesten.EventActivity;
import be.davygevaert.gentsefeesten.R;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.model.Event;
import be.davygevaert.gentsefeesten.model.Locatie;
import be.davygevaert.gentsefeesten.tools.Animation;

/**
 * Created by Davy on 26/06/2016.
 */
public class LocatieRecyclerAdapter extends RecyclerView.Adapter<LocatieRecyclerAdapter.MyViewHolder> {

    private static final String TAG = LocatieRecyclerAdapter.class.getSimpleName();

    private Event mEvent;
    private List<Locatie> mLocaties;
    private LayoutInflater mInflater;
    private Context context;

    public LocatieRecyclerAdapter(Context context, ArrayList<Locatie> locatieArrayList, Event event) {
        this.mLocaties = locatieArrayList;
        this.mEvent = event;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item_locatie, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mLocaties.size();
    }

    // this method is called when each row in the recyclerview is created
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Log.d(TAG, "onBindViewHolder " + position);

        Locatie currentObj = mLocaties.get(position);
        holder.setLocatie(currentObj, position);
        holder.setListeners();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_locatie;
        ImageView iv_locatie;
        int position;
        Intent intent;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tv_locatie = (TextView) itemView.findViewById(R.id.tv_locatie);
            iv_locatie = (ImageView) itemView.findViewById(R.id.iv_locatie);
        }

        public void setLocatie(Locatie currentLocatie, int position) {

            for (Locatie c : mLocaties) {
                this.tv_locatie.setText(currentLocatie.getNaam());
            }
            this.position = position;
        }

        // listeners
        public void setListeners() {
            tv_locatie.setOnClickListener(MyViewHolder.this);
            iv_locatie.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View view) {

            intent = null;

            switch (view.getId()) {
                case R.id.tv_locatie:
                    intent = new Intent(context, EventActivity.class);
                    break;
                case R.id.iv_locatie:
                    intent = new Intent(context, EventActivity.class);
                    break;
            }

            // loggen van huidige positie
            Log.i(TAG, "Toon huidige positie : " + position);

            // verkrijg huidig Locatie object
            Locatie currentLocatieObj = (Locatie) mLocaties.get(position);

            // meegeven huidig Event Object met gekozen short datum notatie aan lokale Intent
            intent.putExtra("huidigEventObj", mEvent);
            // meegeven huidig Locatie object aan lokale Intent
            intent.putExtra("huidigLocatieObj", currentLocatieObj);

            Animation.startActivity_With_Animation(intent, context, Constants.AnimType.SlideJava);
        }
    }
}