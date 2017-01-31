package codes.mutiny.moviedb.mvp;

/**
 * Created by nikola on 6/17/16.
 */
public interface Main {

    interface View {

        void getData();

        void setData(String data);

    }

    interface Presenter {

        void loadData();

    }

}
