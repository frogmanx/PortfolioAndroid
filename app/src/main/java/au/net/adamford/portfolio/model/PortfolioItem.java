package au.net.adamford.portfolio.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adam on 17/11/2015.
 */
public class PortfolioItem implements Parcelable{

    public String title;
    String url;
    String imageUrl;
    public String description;
    int type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.imageUrl);
        dest.writeString(this.description);
        dest.writeInt(this.type);
    }

    public PortfolioItem() {
    }

    protected PortfolioItem(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.imageUrl = in.readString();
        this.description = in.readString();
        this.type = in.readInt();
    }

    public static final Creator<PortfolioItem> CREATOR = new Creator<PortfolioItem>() {
        public PortfolioItem createFromParcel(Parcel source) {
            return new PortfolioItem(source);
        }

        public PortfolioItem[] newArray(int size) {
            return new PortfolioItem[size];
        }
    };
}

