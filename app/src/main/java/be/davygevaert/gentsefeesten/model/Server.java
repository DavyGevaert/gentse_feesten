package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 12/12/2015.
 */
public class Server implements Parcelable {

    // constructor 1
    public Server() {
        // empty constructor
    }

    // constructor 2
    public Server(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // private variables
    private long id;
    private String name;

    // public getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Server(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public static final Creator<Server> CREATOR =
            new Creator<Server>() {
                public Server createFromParcel(Parcel pc) {
                    return new Server(pc);
                }

                @Override
                public Server[] newArray (int size) {
                    return new Server[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }
}
