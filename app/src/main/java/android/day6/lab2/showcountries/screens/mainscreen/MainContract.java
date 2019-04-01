package android.day6.lab2.showcountries.screens.mainscreen;

import android.day6.lab2.showcountries.model.Country;

import java.util.List;

public interface MainContract {
    interface MainView {
        void showCountry(Country country);

    }

    interface MainPresenter {
        void downloadDataIsDone(List<Country> countriesList);
        int getPreviousCountry(int currentIndex);
        int getNextCountry(int currentIndex);
    }
}
