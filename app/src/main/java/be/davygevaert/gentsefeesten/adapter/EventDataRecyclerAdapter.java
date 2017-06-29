package be.davygevaert.gentsefeesten.adapter;

/**
 * Created by Davy on 15/06/2016.
 */

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

import be.davygevaert.gentsefeesten.CategorieActivity;
import be.davygevaert.gentsefeesten.EventActivity;
import be.davygevaert.gentsefeesten.LocatieActivity;
import be.davygevaert.gentsefeesten.R;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.model.Event;
import be.davygevaert.gentsefeesten.tools.Animation;

public class EventDataRecyclerAdapter extends RecyclerView.Adapter<EventDataRecyclerAdapter.MyViewHolder> {

    private static final String TAG = EventDataRecyclerAdapter.class.getSimpleName();

    private ArrayList<Event> mEvents;
    private LayoutInflater mInflater;
    private Context context;

    public EventDataRecyclerAdapter(Context context, ArrayList<Event> eventsArrayList) {
        this.mEvents = eventsArrayList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item_data, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    // this method is called when each row in the recyclerview is created
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);

        Event currentObj = mEvents.get(position);
        holder.setEvent(currentObj, position);
        holder.setListeners();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_dagDatum;
        ImageView iv_Categorie, iv_Locatie, iv_Tijdstip;
        Intent intent;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tv_dagDatum = (TextView) itemView.findViewById(R.id.tv_dag_datum);
            iv_Categorie = (ImageView) itemView.findViewById(R.id.iv_categorie);
            iv_Locatie = (ImageView) itemView.findViewById(R.id.iv_locatie);
            iv_Tijdstip = (ImageView) itemView.findViewById(R.id.iv_tijdstip);
        }

        // verkorte dagnotatie in linkerkant rij element plaatsen, anders geen mooie rij-layout
        // deze methode wordt opgeroepen in methode 'onBindViewHolder'
        public void setEvent(Event current, int position) {

            /*
            for (Event e : mEvents) {
                this.tv_dagDatum.setText(current.getStartDatumShort());
            }
            */

            Log.i(TAG, "startdatumshort current : " + current.getStartDatumShort());


            switch (current.getStartDatumShort()) {
                case "06/07":
                    this.tv_dagDatum.setText("06/07");
                    break;
                case "07/07":
                    this.tv_dagDatum.setText("07/07");
                    break;
                case "08/07":
                    this.tv_dagDatum.setText("08/07");
                    break;
                case "09/07":
                    this.tv_dagDatum.setText("09/07");
                    break;
                case "10/07":
                    this.tv_dagDatum.setText("10/07");
                    break;
                case "11/07":
                    this.tv_dagDatum.setText("11/07");
                    break;
                case "12/07":
                    this.tv_dagDatum.setText("12/07");
                    break;
                case "13/07":
                    this.tv_dagDatum.setText("13/07");
                    break;
                case "14/07":
                    this.tv_dagDatum.setText("14/07");
                    break;
                case "15/07":
                    this.tv_dagDatum.setText("15/07");
                    break;
                case "16/07":
                    this.tv_dagDatum.setText("16/07");
                    break;
                case "17/07":
                    this.tv_dagDatum.setText("17/07");
                    break;
                case "18/07":
                    this.tv_dagDatum.setText("18/07");
                    break;
                case "19/07":
                    this.tv_dagDatum.setText("19/07");
                    break;
                case "20/07":
                    this.tv_dagDatum.setText("20/07");
                    break;
                case "21/07":
                    this.tv_dagDatum.setText("21/07");
                    break;
                case "22/07":
                    this.tv_dagDatum.setText("22/07");
                    break;
                case "23/07":
                    this.tv_dagDatum.setText("23/07");
                    break;
                case "24/07":
                    this.tv_dagDatum.setText("24/07");
                    break;

            }


            this.position = position;

        }

        // listeners imageview knoppen
        public void setListeners() {
            iv_Categorie.setOnClickListener(MyViewHolder.this);
            iv_Locatie.setOnClickListener(MyViewHolder.this);
            iv_Tijdstip.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View view) {

            intent = null;

            // kies een imageView (pictogram) en start de bijhorende Activity
            switch (view.getId()) {
                    case R.id.iv_categorie:
                        intent = new Intent(context, CategorieActivity.class);
                        break;
                    case R.id.iv_locatie:
                        intent = new Intent(context, LocatieActivity.class);
                        break;
                    case R.id.iv_tijdstip:
                        intent = new Intent(context, EventActivity.class);
                        break;
                }

            // loggen van huidige positie
            Log.i(TAG, "Toon huidige positie : " + position);

            // verkrijg huidig datum uit Event object
            Event currentEventObj = (Event) mEvents.get(position);

            // meegeven huidig datum uit Event object aan lokale Intent
            intent.putExtra("huidigEventObj", currentEventObj);

            Animation.startActivity_With_Animation(intent, context, Constants.AnimType.SlideJava);
        }
    }
}

