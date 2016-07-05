package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 14/06/2016.
 */
public class Data implements Parcelable {
    private String timestamp;
    private String day;

    public Data() {
        // lege constructor
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Data(Parcel in) {
        timestamp = in.readString();
        day = in.readString();
    }

    public static final Creator<Data> CREATOR =
            new Creator<Data>() {
                @Override
                public Data createFromParcel(Parcel pc) {
                    return new Data(pc);
                }

                @Override
                public Data[] newArray(int size) {
                    return new Data[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timestamp);
        dest.writeString(day);
    }
}
