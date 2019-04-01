package android.day6.lab2.showcountries.model.network.networkservices;

import android.day6.lab2.showcountries.model.Country;
import android.graphics.Bitmap;

import java.util.List;

public interface NetworkServiceInterface {
    void callHandler(List<Country> countriesList);
    void downloadImageFlag(NetworkServiceInterface networlInterface, String urlImg,int _index);
    void downloadImageFlagIsDone(int _index,Bitmap imgFlag);
}
