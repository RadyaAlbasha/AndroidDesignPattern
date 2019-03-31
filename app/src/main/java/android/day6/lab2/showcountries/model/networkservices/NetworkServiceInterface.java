package android.day6.lab2.showcountries.model.networkservices;

import android.content.Context;
import android.day6.lab2.showcountries.model.Country;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.List;

public interface NetworkServiceInterface {
    void callHandler(List<Country> countriesList);
    void downloadImageFlag(NetworkServiceInterface networlInterface, String urlImg,int _index);
    void downloadImageFlagIsDone(int _index,Bitmap imgFlag);
}
