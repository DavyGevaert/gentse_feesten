package be.davygevaert.gentsefeesten.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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

        TextView tv_titel, tv_omschrijving, tv_categorie, tv_organisatie_links, tv_organisatie,
                tv_adres_links, tv_adres, tv_prijs_links, tv_prijs, tv_korting_links, tv_korting,
                tv_rolstoel, tv_website_links, tv_route_links, tv_archief_links;
        LinearLayout linearLayout;
        ImageView iv_foto, iv_rolstoel, iv_website, iv_route, iv_youtube;


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
            tv_korting_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_prijs_korting_links);
            tv_korting = (TextView) itemView.findViewById(R.id.tv_eventdetail_prijs_korting);
            iv_rolstoel = (ImageView) itemView.findViewById(R.id.iv_rolstoel);
            tv_rolstoel = (TextView) itemView.findViewById(R.id.tv_eventdetail_rolstoel);
            tv_website_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_website_links);
            iv_website = (ImageView) itemView.findViewById(R.id.iv_website);
            tv_route_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_route_links);
            iv_route = (ImageView) itemView.findViewById(R.id.iv_route);
            tv_archief_links = (TextView) itemView.findViewById(R.id.tv_eventdetail_archief_links);
            iv_youtube = (ImageView) itemView.findViewById(R.id.iv_youtube);
        }

        public void setEvent(final Event currentEvent) {
            // instellen titel event
            tv_titel.setText(currentEvent.getNaam());

            // instellen categorie event
            // inner join alles ?
            tv_categorie.setText(currentEvent.getCategorie().getTitel());

            // indien afbeelding false is, maak imageview dan onzichtbaar en zet de hoogte op 0, zodat alles eronder naar boven schuift
            if (currentEvent.getAfbeeldingUrl() != null) {
                Glide.with(context).load(currentEvent.getAfbeeldingUrl()).into(iv_foto);
            } else {
                iv_foto.setVisibility(View.INVISIBLE);
                iv_foto.getLayoutParams().height = 0;
            }

            if (currentEvent.getBeschrijving() != null) {
                // instellen omschrijving event
                tv_omschrijving.setText(currentEvent.getBeschrijving());
            } else {
                tv_omschrijving.setVisibility(View.INVISIBLE);
                tv_omschrijving.getLayoutParams().height = 0;
            }

            // instellen organisatie event
            // inner join
            tv_organisatie.setText(currentEvent.getOrganisator().getOrganisatieNaam());

            // instellen adres event
            String adres = "";

            adres += currentEvent.getLocatie().getStraat();
            adres += "\n";
            adres += currentEvent.getLocatie().getPostcode();
            adres += " ";
            adres += currentEvent.getLocatie().getGemeente();


            if (currentEvent.getLocatie().getStraat().isEmpty() &&
                    currentEvent.getLocatie().getPostcode().isEmpty() &&
                    currentEvent.getLocatie().getGemeente().isEmpty()) {
                tv_adres_links.setVisibility(View.INVISIBLE);
                tv_adres_links.getLayoutParams().height = 0;
                tv_adres.setVisibility(View.INVISIBLE);
                tv_adres.getLayoutParams().height = 0;

                tv_route_links.setVisibility(View.INVISIBLE);
                tv_route_links.getLayoutParams().height = 0;
                iv_route.setVisibility(View.VISIBLE);
                iv_route.getLayoutParams().height = 0;
            } else {
                if (currentEvent.getLocatie().getStraat().contains("-")) {      // uitzonderlijk dat straat met '-' opgevuld is
                    tv_adres_links.setVisibility(View.INVISIBLE);
                    tv_adres_links.getLayoutParams().height = 0;
                    tv_adres.setVisibility(View.INVISIBLE);
                    tv_adres.getLayoutParams().height = 0;

                    tv_route_links.setVisibility(View.INVISIBLE);
                    tv_route_links.getLayoutParams().height = 0;
                    iv_route.setVisibility(View.VISIBLE);
                    iv_route.getLayoutParams().height = 0;
                } else {
                    tv_adres.setText(adres);
                }
            }



            Log.i(TAG, "prijs inhoud : " + currentEvent.getPrijs() + " .");

            // instellen prijs event
            //tv_prijs.setText(currentEvent.getPrijs() + " €");

            if (currentEvent.getPrijs() == null || currentEvent.getPrijs().isEmpty()
                && currentEvent.getWisselkoers() == null || currentEvent.getWisselkoers().isEmpty()) {
                tv_prijs.setText("Gratis");
            } else {
                tv_prijs.setText(currentEvent.getPrijs() + " " + currentEvent.getWisselkoers());
            }

            if (currentEvent.getPrijs_omschrijving() == null || currentEvent.getPrijs_omschrijving().isEmpty()){
                // do nothing
            } else if (currentEvent.getPrijs_omschrijving().contains("â‚¬")) {
                String prijs_omschrijving = currentEvent.getPrijs_omschrijving().replace("â‚¬", "");

                tv_prijs.setText(currentEvent.getPrijs() + " " + currentEvent.getWisselkoers() + "\n" +
                        prijs_omschrijving);
            } else if (currentEvent.getPrijs_omschrijving().contains(",")) {
                String prijs_omschrijving = currentEvent.getPrijs_omschrijving().replace(", ", "\n");

                tv_prijs.setText(currentEvent.getPrijs() + " " + currentEvent.getWisselkoers() + "\n" +
                        prijs_omschrijving);
            }

            /*
            voorverkoopprijs en verkrijgbaarheid niet duidelijk voor eindgebruiker !!!
            verkrijgbaarheid locatie id array String met ,
            korting
             */

            if (currentEvent.getKorting() == null || currentEvent.getKorting().isEmpty()){
                tv_korting_links.setVisibility(View.INVISIBLE);
                tv_korting_links.getLayoutParams().height = 0;
                tv_korting.setVisibility(View.INVISIBLE);
                tv_korting.getLayoutParams().height = 0;
            } else if (currentEvent.getKorting().contains(", ")) {
                currentEvent.setKorting(currentEvent.getKorting().replace(", ", "\n"));
                tv_korting.setText(currentEvent.getKorting());
            } else {
                tv_korting.setText(currentEvent.getKorting());
            }

            // instellen toegankelijkheid rolstoelgebruikers event
            if (currentEvent.getRolstoelToegankelijkheid().contentEquals("true")) {
                tv_rolstoel.setText("Ja");
            } else if (currentEvent.getRolstoelToegankelijkheid().contentEquals("false")) {
                tv_rolstoel.setText("Neen");
            } else {
                iv_rolstoel.setVisibility(View.INVISIBLE);
                iv_rolstoel.getLayoutParams().height = 0;
                tv_rolstoel.setVisibility(View.INVISIBLE);
                tv_rolstoel.getLayoutParams().height = 0;
            }

            Log.i(TAG, "website url adres : " + currentEvent.getWebsiteUrl());


            // instellen website event
            if (currentEvent.getWebsiteUrl() == null || currentEvent.getWebsiteUrl().isEmpty()) {
                tv_website_links.setVisibility(View.INVISIBLE);
                tv_website_links.getLayoutParams().height = 0;
                iv_website.setVisibility(View.INVISIBLE);
                iv_website.getLayoutParams().height = 0;
            } else {
                iv_website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(currentEvent.getWebsiteUrl()); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                    }
                });
            }


            // instellen locatie event met Google Maps Urls (geen API key nodig !!!)
            if (currentEvent.getLocatie().getStraat().isEmpty() &&
                    currentEvent.getLocatie().getPostcode().isEmpty() &&
                    currentEvent.getLocatie().getGemeente().isEmpty()) {
                tv_route_links.setVisibility(View.INVISIBLE);
                tv_route_links.getLayoutParams().height = 0;
                iv_route.setVisibility(View.INVISIBLE);
                iv_route.getLayoutParams().height = 0;
            } else {

                iv_route.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Uri uri = Uri.parse("http://maps.google.com/?q=" +
                                                currentEvent.getLocatie().getStraat() + "+" +
                                                currentEvent.getLocatie().getPostcode() + "+" +
                                                currentEvent.getLocatie().getGemeente()
                                            ); // missing 'http://' will cause crashed

                        Log.i(TAG, "Uri : " + uri.toString());

                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                    }
                });
            }

            // instellen website event
            if (currentEvent.getVideoUrl() == null || currentEvent.getVideoUrl().isEmpty()) {
                tv_archief_links.setVisibility(View.INVISIBLE);
                tv_archief_links.getLayoutParams().height = 0;
                iv_youtube.setVisibility(View.INVISIBLE);
                iv_youtube.getLayoutParams().height = 0;
            } else {
                iv_youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(currentEvent.getVideoUrl()); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }
}
