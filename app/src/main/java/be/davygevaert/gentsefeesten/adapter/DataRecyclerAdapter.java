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
import be.davygevaert.gentsefeesten.model.Data;
import be.davygevaert.gentsefeesten.tools.Animation;

public class DataRecyclerAdapter extends RecyclerView.Adapter<DataRecyclerAdapter.MyViewHolder> {

    private static final String TAG = DataRecyclerAdapter.class.getSimpleName();

    private ArrayList<Data> mData;
    private LayoutInflater mInflater;
    private Context context;

    public DataRecyclerAdapter(Context context, ArrayList<Data> dataArrayList) {
        this.mData = dataArrayList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item_data, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // this method is called when each row in the recyclerview is created
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);

        Data currentObj = mData.get(position);
        holder.setData(currentObj, position);
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
        public void setData(Data current, int position) {

            switch (current.getTimestamp()) {
                case "1468533600":
                    this.tv_dagDatum.setText("15/07");
                    break;
                case "1468620000":
                    this.tv_dagDatum.setText("16/07");
                    break;
                case "1468706400":
                    this.tv_dagDatum.setText("17/07");
                    break;
                case "1468792800":
                    this.tv_dagDatum.setText("18/07");
                    break;
                case "1468879200":
                    this.tv_dagDatum.setText("19/07");
                    break;
                case "1468965600":
                    this.tv_dagDatum.setText("20/07");
                    break;
                case "1469052000":
                    this.tv_dagDatum.setText("21/07");
                    break;
                case "1469138400":
                    this.tv_dagDatum.setText("22/07");
                    break;
                case "1469224800":
                    this.tv_dagDatum.setText("23/07");
                    break;
                case "1469311200":
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

            // verkrijg huidig Data object
            Data currentDataObj = (Data) mData.get(position);

            // meegeven huidig Data object aan lokale Intent
            intent.putExtra("huidigDataObj", currentDataObj);

            Animation.startActivity_With_Animation(intent, context, Constants.AnimType.SlideJava);
        }
    }
}

