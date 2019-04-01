package android.day6.lab2.showcountries.screens.mainscreen;

import android.content.Context;
import android.day6.lab2.showcountries.model.Country;
import android.day6.lab2.showcountries.model.networkservices.NetworkServiceImp;
import android.day6.lab2.showcountries.model.networkservices.NetworkServiceInterface;
import android.widget.Toast;

import java.util.List;

public class MainPresenterImpl implements MainContract.MainPresenter{

    MainContract.MainView activity;
    NetworkServiceInterface networkService ;
    boolean downloadDataIsDone;
    List<Country> myCountriesList;
    public MainPresenterImpl(MainContract.MainView activity){
        this.activity = activity;
        downloadDataIsDone =false;
    }

    @Override
    public void downloadDataIsDone(List<Country> countriesList) {
        downloadDataIsDone = true;
        myCountriesList= countriesList;
        activity.showCountry(countriesList.get(0));
    }

    @Override
    public int getPreviousCountry(int currentIndex) {
        if(downloadDataIsDone)
        {
            Country country;
            if(currentIndex>0)
                currentIndex--;
            else
                currentIndex=myCountriesList.size()-1;
            country = myCountriesList.get(currentIndex);
            activity.showCountry(country);
        }
        else
        {
            Toast.makeText((Context) activity, "Data not yet loaded", Toast.LENGTH_SHORT).show();
        }
        return currentIndex;
    }

    @Override
    public int getNextCountry(int currentIndex) {
        if(downloadDataIsDone)
        {
            Country country;
            if(currentIndex<myCountriesList.size()-1)
                currentIndex++;
            else
                currentIndex=0;
            country = myCountriesList.get(currentIndex);
            activity.showCountry(country);
        }
        else
        {
            Toast.makeText((Context) activity, "Data not yet loaded", Toast.LENGTH_SHORT).show();
        }
        return currentIndex;
    }

    @Override
    public void downloadCountriesData() {
        networkService = new NetworkServiceImp((Context) activity,this);
    }
}
