package android.day6.lab2.showcountries.screens.mainscreen;

import android.day6.lab2.showcountries.model.Country;

import java.util.List;

public interface MainContract {
    interface MainView {
        void downloadDataIsDone(List<Country> countriesList);

    }

    interface MainPresenter {

    }
}
