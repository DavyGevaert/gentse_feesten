package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 12/12/2015.
 */
public class Parking implements Parcelable {

    // constructor 1
    public Parking() {
        // empty constructor
    }

    // constructor 2
    public Parking(long id, String lastModifiedDate, String name, String description, String latitude, String longitude,
                   String address, String contactInfo, String blurAvailability, City city, Server server,
                   int suggestedFreeThreshold, int suggestedFullThreshold, int capacityRounding,
                   OpeningTimesInfo openingTimesInfo, Status status) {
        this.id = id;
        this.lastModifiedDate = lastModifiedDate;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.contactInfo = contactInfo;
        this.blurAvailability = blurAvailability;
        this.city = city;
        this.server = server;
        this.suggestedFreeThreshold = suggestedFreeThreshold;
        this.suggestedFullThreshold = suggestedFullThreshold;
        this.capacityRounding = capacityRounding;
        this.openingTimesInfo = openingTimesInfo;
        this.status = status;
    }

    // private variables
    private long id;
    private String lastModifiedDate;
    private String name;
    private String description;
    private String latitude;
    private String longitude;
    private String address;
    private String contactInfo;
    private String blurAvailability;
    private City city;
    private Server server;
    private int suggestedFreeThreshold;
    private int suggestedFullThreshold;
    private int capacityRounding;
    private OpeningTimesInfo openingTimesInfo;
    private Status status;

    // public getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getBlurAvailability() {
        return blurAvailability;
    }

    public void setBlurAvailability(String blurAvailability) {
        this.blurAvailability = blurAvailability;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public int getSuggestedFreeThreshold() {
        return suggestedFreeThreshold;
    }

    public void setSuggestedFreeThreshold(int suggestedFreeThreshold) {
        this.suggestedFreeThreshold = suggestedFreeThreshold;
    }

    public int getSuggestedFullTreshold() {
        return suggestedFullThreshold;
    }

    public void setSuggestedFullThreshold(int suggestedFullThreshold) {
        this.suggestedFullThreshold = suggestedFullThreshold;
    }

    public int getCapacityRounding() {
        return capacityRounding;
    }

    public void setCapacityRounding(int capacityRounding) {
        this.capacityRounding = capacityRounding;
    }

    public OpeningTimesInfo getOpeningTimesInfo() {
        return openingTimesInfo;
    }

    public void setOpeningTimesInfo(OpeningTimesInfo openingTimesInfo) {
        this.openingTimesInfo = openingTimesInfo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Parking(Parcel in) {
        id = in.readLong();
        lastModifiedDate = in.readString();
        name = in.readString();
        description = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        address = in.readString();
        contactInfo = in.readString();
        blurAvailability = in.readString();
        city = (City) in.readParcelable(City.class.getClassLoader());
        server = (Server) in.readParcelable(Server.class.getClassLoader());
        suggestedFreeThreshold = in.readInt();
        suggestedFullThreshold = in.readInt();
        capacityRounding = in.readInt();
        openingTimesInfo = (OpeningTimesInfo) in.readParcelable(OpeningTimesInfo.class.getClassLoader());
        status = (Status) in.readParcelable(Status.class.getClassLoader());
    }

    public static final Creator<Parking> CREATOR =
            new Creator<Parking>() {
                public Parking createFromParcel(Parcel pc) {
                    return new Parking(pc);
                }

                @Override
                public Parking[] newArray (int size) {
                    return new Parking[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(lastModifiedDate);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(address);
        dest.writeString(contactInfo);
        dest.writeString(blurAvailability);
        dest.writeParcelable(city, flags);
        dest.writeParcelable(server, flags);
        dest.writeInt(suggestedFreeThreshold);
        dest.writeInt(suggestedFullThreshold);
        dest.writeInt(capacityRounding);
        dest.writeParcelable(openingTimesInfo, flags);
        dest.writeParcelable(status, flags);
    }
}
