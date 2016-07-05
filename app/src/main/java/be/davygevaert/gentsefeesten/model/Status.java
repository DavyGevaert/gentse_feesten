package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 12/12/2015.
 */
public class Status implements Parcelable {

    // constructor 1
    public Status() {
        // empty constructor
    }

    // constructor 2
    public Status(long availableCapacity, long totalCapacity, boolean open, String suggestedCapacity, String activeRoute, String lastModifiedDate) {
        this.availableCapacity = availableCapacity;
        this.totalCapacity = totalCapacity;
        this.open = open;
        this.suggestedCapacity = suggestedCapacity;
        this.activeRoute = activeRoute;
        this.lastModifiedDate = lastModifiedDate;
    }

    // private variables
    private long availableCapacity;
    private long totalCapacity;
    private boolean open;
    private String suggestedCapacity;
    private String activeRoute;
    private String lastModifiedDate;

    // public getters & setters
    public long getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(long availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public long getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(long totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getSuggestedCapacity() {
        return suggestedCapacity;
    }

    public void setSuggestedCapacity(String suggestedCapacity) {
        this.suggestedCapacity = suggestedCapacity;
    }

    public String getActiveRoute() {
        return activeRoute;
    }

    public void setActiveRoute(String activeRoute) {
        this.activeRoute = activeRoute;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Status(Parcel in) {
        availableCapacity = in.readLong();
        totalCapacity = in.readLong();
        open = in.readByte() != 0;     //myBoolean == true if byte != 0
        suggestedCapacity = in.readString();
        activeRoute = in.readString();
        lastModifiedDate = in.readString();
    }

    public static final Creator<Status> CREATOR =
            new Creator<Status>() {
                public Status createFromParcel(Parcel pc) {
                    return new Status(pc);
                }

                @Override
                public Status[] newArray (int size) {
                    return new Status[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(availableCapacity);
        dest.writeLong(totalCapacity);
        dest.writeByte((byte) (open ? 1 : 0));     //if myBoolean == true, byte == 1
        dest.writeString(suggestedCapacity);
        dest.writeString(activeRoute);
        dest.writeString(lastModifiedDate);
    }
}
