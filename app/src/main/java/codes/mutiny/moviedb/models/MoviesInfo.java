package codes.mutiny.moviedb.models;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by nikola on 28.01.17..
 */
public class MoviesInfo implements Parcelable {

    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public List<Movie> movieList;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("total_results")
    public int totalResults;

    protected MoviesInfo(Parcel in) {
        page = in.readInt();
        movieList = in.createTypedArrayList(Movie.CREATOR);
        totalPages = in.readInt();
        totalResults = in.readInt();
    }

    public static final Creator<MoviesInfo> CREATOR = new Creator<MoviesInfo>() {
        @Override
        public MoviesInfo createFromParcel(Parcel in) {
            return new MoviesInfo(in);
        }

        @Override
        public MoviesInfo[] newArray(int size) {
            return new MoviesInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeTypedList(movieList);
        dest.writeInt(totalPages);
        dest.writeInt(totalResults);
    }

}
