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
import be.davygevaert.gentsefeesten.model.Categorie;
import be.davygevaert.gentsefeesten.model.Event;
import be.davygevaert.gentsefeesten.tools.Animation;

/**
 * Created by Davy on 15/06/2016.
 */
public class CategorieRecyclerAdapter extends RecyclerView.Adapter<CategorieRecyclerAdapter.MyViewHolder> {

    private static final String TAG = CategorieRecyclerAdapter.class.getSimpleName();

    private Event mEvent;
    private List<Categorie> mCategorieen;
    private LayoutInflater mInflater;
    private Context context;

    public CategorieRecyclerAdapter(Context context, ArrayList<Categorie> categorieArrayList, Event event) {
        this.mCategorieen = categorieArrayList;
        this.mEvent = event;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item_categorie, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mCategorieen.size();
    }

    // this method is called when each row in the recyclerview is created
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Log.d(TAG, "onBindViewHolder " + position);

        Categorie currentObj = mCategorieen.get(position);
        holder.setCategorie(currentObj, position);
        holder.setListeners();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_categorie;
        ImageView iv_Categorie;
        int position;
        Intent intent;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tv_categorie = (TextView) itemView.findViewById(R.id.tv_categorie);
            iv_Categorie = (ImageView) itemView.findViewById(R.id.iv_categorie);
        }

        public void setCategorie(Categorie currentCategorie, int position) {

            for (Categorie c : mCategorieen) {
                this.tv_categorie.setText(currentCategorie.getTitel());
            }
            this.position = position;
        }

        // listeners
        public void setListeners() {
            tv_categorie.setOnClickListener(MyViewHolder.this);
            iv_Categorie.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View view) {

            intent = null;

            switch (view.getId()) {
                case R.id.tv_categorie:
                    intent = new Intent(context, EventActivity.class);
                    break;
                case R.id.iv_categorie:
                    intent = new Intent(context, EventActivity.class);
                    break;
            }

            // loggen van huidige positie
            Log.i(TAG, "Toon huidige positie : " + position);

            // verkrijg huidig Categorie object
            Categorie currentCategorieObj = (Categorie) mCategorieen.get(position);

            // meegeven huidig Event Object met gekozen short datum notatie aan lokale Intent
            intent.putExtra("huidigEventObj", mEvent);
            // meegeven huidig Categorie object aan lokale Intent
            intent.putExtra("huidigCategorieObj", currentCategorieObj);

            Animation.startActivity_With_Animation(intent, context, Constants.AnimType.SlideJava);
        }
    }
}
