package be.davygevaert.gentsefeesten.model;

import java.util.ArrayList;
import java.util.List;

import be.davygevaert.gentsefeesten.R;

/**
 * Created by Davy on 27/06/2016.
 */
public class NavigationDrawerItem {
    private String titel;
    private int afbeeldingId;

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getAfbeeldingId() {
        return afbeeldingId;
    }

    public void setAfbeeldingId(int afbeeldingId) {
        this.afbeeldingId = afbeeldingId;
    }

    public static List<NavigationDrawerItem> getData() {
        List<NavigationDrawerItem> dataList = new ArrayList<>();

        int[] afbeeldingIds = getAfbeeldingen();
        String[] titels = getTitels();

        for (int i = 0; i < titels.length; i++) {
            NavigationDrawerItem navItem = new NavigationDrawerItem();
            navItem.setTitel(titels[i]);
            navItem.setAfbeeldingId(afbeeldingIds[i]);
            dataList.add(navItem);
        }
        return dataList;
    }

    private static int[] getAfbeeldingen() {

        return new int[] {
                R.drawable.ic_date_range_black_24dp,
                R.drawable.ic_local_parking_black_24dp,
                R.drawable.ic_info_black_24dp,
                R.drawable.ic_info_black_24dp
        };
    }

    private static String[] getTitels() {

        return new String[] {
                "Home",
                "Parking",
                "Info Gent",
                "Over deze applicatie"
        };
    }
}
