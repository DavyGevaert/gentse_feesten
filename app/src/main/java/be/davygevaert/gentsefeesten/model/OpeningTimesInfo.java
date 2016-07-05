package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 12/12/2015.
 */
public class OpeningTimesInfo implements Parcelable {

    // constructor 1
    public OpeningTimesInfo() {
        // empty constructor
    }

    // constructor 2
    public OpeningTimesInfo(long id, String code, String text) {
        this.id = id;
        this.code = code;
        this.text = text;
    }

    // private variables
    private long id;
    private String code;
    private String text;

    // public getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public OpeningTimesInfo(Parcel in) {
        id = in.readLong();
        code = in.readString();
        text = in.readString();
    }

    public static final Creator<OpeningTimesInfo> CREATOR =
            new Creator<OpeningTimesInfo>() {
                public OpeningTimesInfo createFromParcel(Parcel pc) {
                    return new OpeningTimesInfo(pc);
                }

                @Override
                public OpeningTimesInfo[] newArray (int size) {
                    return new OpeningTimesInfo[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(code);
        dest.writeString(text);
    }
}
