package be.davygevaert.gentsefeesten.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import be.davygevaert.gentsefeesten.EventDetailActivity;
import be.davygevaert.gentsefeesten.R;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.model.Categorie;
import be.davygevaert.gentsefeesten.model.Data;
import be.davygevaert.gentsefeesten.model.Event;
import be.davygevaert.gentsefeesten.tools.Animation;

/**
 * Created by Davy on 24/06/2016.
 */
public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder> {

    private static final String TAG = EventRecyclerAdapter.class.getSimpleName();

    private List<Event> mEvents;
    private LayoutInflater mInflater;
    private Context context;
    private Data mData;
    private Categorie mCategorie;

    public EventRecyclerAdapter(Context context, List<Event> eventArrayList, Data data, Categorie categorie) {
        this.mEvents = eventArrayList;
        this.mData = data;
        this.mCategorie = categorie;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item_event, parent, false);

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
        //Log.d(TAG, "onBindViewHolder " + position);

        Event currentObj = mEvents.get(position);
        holder.setEvent(currentObj, position);
        holder.setListeners();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_startuur;
        TextView tv_titel;
        int position;
        Intent intent;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tv_startuur = (TextView) itemView.findViewById(R.id.tv_event_startuur);
            tv_titel = (TextView) itemView.findViewById(R.id.tv_event_titel);
        }

        public void setEvent(Event currentEvent, int position) {

            for (Event e : mEvents) {

                if (currentEvent.getStartuur().contains("false")) {
                    this.tv_startuur.setText("Hele\ndag");
                } else {
                    this.tv_startuur.setText(currentEvent.getStartuur());
                }

                this.tv_titel.setText(currentEvent.getTitel());
            }
            this.position = position;
        }

        // listeners
        public void setListeners() {
            tv_startuur.setOnClickListener(MyViewHolder.this);
            tv_titel.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View view) {

            intent = null;

            // loggen van huidige positie
            Log.i(TAG, "Toon huidige positie : " + position);

            // verkrijg huidig Event object
            Event currentEventObj = (Event) mEvents.get(position);

            switch (view.getId()) {
                case R.id.tv_event_startuur:
                    intent = new Intent(context, EventDetailActivity.class);

                    // meegeven huidig Event object aan lokale Intent
                    intent.putExtra("huidigEventObj", currentEventObj);

                    // meegeven huidig Data object aan lokale Intent
                    intent.putExtra("huidigDataObj", mData);

                    // meegeven huidig Categorie object aan lokale Intent
                    intent.putExtra("huidigCategorieObj", mCategorie);
                    break;
                case R.id.tv_event_titel:
                    intent = new Intent(context, EventDetailActivity.class);

                    // meegeven huidig Event object aan lokale Intent
                    intent.putExtra("huidigEventObj", currentEventObj);

                    // meegeven huidig Data object aan lokale Intent
                    intent.putExtra("huidigDataObj", mData);

                    // meegeven huidig Categorie object aan lokale Intent
                    intent.putExtra("huidigCategorieObj", mCategorie);
                    break;
            }

            Animation.startActivity_With_Animation(intent, context, Constants.AnimType.ExplodeJava);
        }
    }
}
