package android.day6.lab2.showcountries.model.networkservices;

import android.content.Context;
import android.day6.lab2.showcountries.model.Country;
import android.os.Message;
import android.widget.ImageView;

import java.util.List;

public interface NetworkServiceInterface {
    void callHandler(List<Country> countriesList);
    void downloadImageFlag(Context context ,ImageView imageView , String urlImg);
}
