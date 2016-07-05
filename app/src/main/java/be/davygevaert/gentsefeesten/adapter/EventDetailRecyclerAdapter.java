package be.davygevaert.gentsefeesten.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import be.davygevaert.gentsefeesten.R;
import be.davygevaert.gentsefeesten.model.Event;

/**
 * Created by Davy on 2/07/2016.
 */
public class EventDetailRecyclerAdapter extends RecyclerView.Adapter<EventDetailRecyclerAdapter.MyViewHolder> {

    private static final String TAG = EventDetailRecyclerAdapter.class.getSimpleName();

    private Event mEvent;
    private LayoutInflater mInflater;
    private Context context;

    public EventDetailRecyclerAdapter(Context context, Event event) {
        this.mEvent = event;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item_eventdetail, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return 1;       // in constructor wordt 1 Event object doorgegeven, als je hier 0 plaatst dat je denkt dat je geen List<Event> doorgeeft, dan werkt het gewoonweg niet
    }

    // this method is called when each row in the recyclerview is created
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Event currentObj = mEvent;
        holder.setEvent(currentObj);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_titel, tv_omschrijving, tv_categorie, tv_organisatie_links, tv_organisatie, tv_adres_links, tv_adres, tv_prijs_links, tv_prijs, tv_rolstoel, tv_website_links, tv_route_links;
        LinearLayout linearLayout;
        ImageView iv_foto, iv_rolstoel, iv_website, iv_route;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            // initialiseren variabelen
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_eventdetail);
            tv_titel = (TextView) itemView.findViewById(R.id.tv_eventdetail_titel);
            tv_categorie = (TextView) itemView.findViewById(R.id.tv_eventdetail_categorie);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_eventdetail_foto);
            tv_omschrijving = (TextView) itemView.findViewById(R.id.tv_eventdetail_omschrijving);
            tv_organisatie_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_organisatie_links);
            tv_organisatie = (TextView) itemView.findViewById(R.id.tv_eventdetail_organisatie);
            tv_adres_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_adres_links);
            tv_adres = (TextView) itemView.findViewById(R.id.tv_eventdetail_adres);
            tv_prijs_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_prijs_links);
            tv_prijs = (TextView) itemView.findViewById(R.id.tv_eventdetail_prijs);
            iv_rolstoel = (ImageView) itemView.findViewById(R.id.iv_rolstoel);
            tv_rolstoel = (TextView) itemView.findViewById(R.id.tv_eventdetail_rolstoel);
            tv_website_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_website_links);
            iv_website = (ImageView) itemView.findViewById(R.id.iv_website);
            tv_route_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_route_links);
            iv_route = (ImageView) itemView.findViewById(R.id.iv_route);
        }

        public void setEvent(final Event currentEvent) {

            // instellen titel event
            tv_titel.setText(currentEvent.getTitel());

            // instellen categorie event
            tv_categorie.setText(currentEvent.getCategorie_naam());

            // indien afbeelding false is, maak imageview dan onzichtbaar en zet de hoogte op 0, zodat alles eronder naar boven schuift
            if (currentEvent.getAfbeelding().contains("false")) {
                iv_foto.setVisibility(View.INVISIBLE);
                iv_foto.getLayoutParams().height = 0;
            } else {
                Glide.with(context).load(currentEvent.getAfbeelding()).into(iv_foto);
            }

            // indien omschrijving false is, maak textview dan onzichtbaar en zet de hoogte op 0, zodat alles eronder naar boven schuift
            if (currentEvent.getOmschrijving().contains("false")) {
                tv_omschrijving.setVisibility(View.INVISIBLE);
                tv_omschrijving.getLayoutParams().height = 0;
            } else {
                // instellen omschrijving event
                tv_omschrijving.setText(currentEvent.getOmschrijving());
            }

            // instellen organisatie event
            tv_organisatie.setText(currentEvent.getOrganisatie());


            // instellen adres event
            String adres = "";

            if (currentEvent.getStraat().contains("false")) {
                // niks doen
            } else {
                adres += currentEvent.getStraat() + "\n";
            }

            if (currentEvent.getPostcode().contains("false")) {
                // niks doen
            } else {
                adres += currentEvent.getPostcode() + " ";
            }

            if (currentEvent.getGemeente().contains("false")) {
                // niks doen
            } else {
                adres += currentEvent.getGemeente();
            }

            if (currentEvent.getStraat().contains("false") && currentEvent.getPostcode().contains("false") && currentEvent.getGemeente().contains("false")) {
                tv_adres_links.setVisibility(View.INVISIBLE);
                tv_adres_links.getLayoutParams().height = 0;
                tv_adres.setVisibility(View.INVISIBLE);
                tv_adres.getLayoutParams().height = 0;
            } else {
                tv_adres.setText(adres);
            }

            // instellen prijs event
            if (currentEvent.getPrijs().contains("false")) {
                tv_prijs.setText("Gratis");
            } else {
                tv_prijs.setText(currentEvent.getPrijs() + " €");
            }

            // instellen toegankelijkheid rolstoelgebruikers event
            if (currentEvent.getToegankelijk_rolstoel().contains("1")) {
                tv_rolstoel.setText("Ja");
            } else {
                iv_rolstoel.setVisibility(View.INVISIBLE);
                iv_rolstoel.getLayoutParams().height = 0;
                tv_rolstoel.setVisibility(View.INVISIBLE);
                tv_rolstoel.getLayoutParams().height = 0;
            }

            // instellen website event
            if (currentEvent.getUrl().contains("false")) {
                tv_website_links.setVisibility(View.INVISIBLE);
                tv_website_links.getLayoutParams().height = 0;
                iv_website.setVisibility(View.INVISIBLE);
                iv_website.getLayoutParams().height = 0;
            } else {
                iv_website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(currentEvent.getUrl()); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                    }
                });
            }

            // instellen locatie event met latitude en longitude
            if (currentEvent.getLatitude().isEmpty() && currentEvent.getLongitude().isEmpty()) {
                tv_route_links.setVisibility(View.INVISIBLE);
                tv_route_links.getLayoutParams().height = 0;
                iv_route.setVisibility(View.INVISIBLE);
                iv_route.getLayoutParams().height = 0;
            } else {

                iv_route.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?&daddr=" + currentEvent.getLatitude() + "," + currentEvent.getLongitude()));
                        context.startActivity(intent);
                    }
                });
            }
        }
    }
}
