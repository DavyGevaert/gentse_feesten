package be.davygevaert.gentsefeesten.constanten;

import be.davygevaert.gentsefeesten.R;

/**
 * Created by Davy on 14/06/2016.
 */
public class Constants {
    // String waarden data in assets folder
    public static final String GENTSE_FEESTEN_CATEGORIEEN =     "gentsefeestencategorien.json";
    public static final String GENTSE_FEESTEN_EVENTS =          "gentsefeestenevents.json";
    public static final String GENTSE_FEESTEN_LOCATIES =        "gentsefeestenlocaties.json";
    public static final String GENTSE_FEESTEN_ORGANISATOREN =   "gentsefeestenorganisatoren.json";

    public static final String URL_BEZETTING_PARKINGS_REALTIME = "https://datatank.stad.gent/4/mobiliteit/bezettingparkingsrealtime.json";

    // onderstaande wordt gebruikt voor animaties als nieuwe Activity aangeroepen wordt
    public static final String KEY_TYPE = "anim_type";

    public enum AnimType {
        ExplodeJava, SlideJava, FadeJava
    }
}
