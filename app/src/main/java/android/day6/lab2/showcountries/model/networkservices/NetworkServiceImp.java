package android.day6.lab2.showcountries.model.networkservices;

import android.content.Context;
import android.day6.lab2.showcountries.model.Country;
import android.day6.lab2.showcountries.screens.mainscreen.MainActivity;
import android.day6.lab2.showcountries.screens.mainscreen.MainContract;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class NetworkServiceImp implements NetworkServiceInterface{
    List<Country> myCountryList;
    JsonConniction jsonConniction;
    MyAsyncTask myAsyncTask;
    int index;
    Handler handler;
    Context context;
    String urlFlag;
    ImageView imgFlag;
    MainContract.MainView activity;
    public NetworkServiceImp(MainContract.MainView _activity, int _index , ImageView _imgFlag) {
        jsonConniction=new JsonConniction(this);
        //myCountryList = jsonConniction.getCountriesList();
        index = _index;
        context =(Context)_activity;
        imgFlag = _imgFlag;
        activity = _activity;
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //txtRank.setText(myCountryList.get(index).getRank());
                //txtCountry.setText(myCountryList.get(index).getCountry());
                //txtPopulation.setText(myCountryList.get(index).getPopulation());
                urlFlag=myCountryList.get(index).getFlag();
                Log.i("urlFlag",urlFlag);
                downloadImageFlag(context , imgFlag , urlFlag);
                activity.downloadDataIsDone(myCountryList);
                //myAsyncTask = new MyAsyncTask(context,imgFlag);
                //myAsyncTask.execute(urlFlag);
            }
        };

        Thread updater = new Thread(jsonConniction);
        updater.start();
    }

    public List<Country> getMyCountryList() {
        return myCountryList;
    }

    @Override
    public void callHandler(List<Country> countriesList) {
        myCountryList = countriesList;
        handler.sendEmptyMessage(0);

    }

    @Override
    public void downloadImageFlag(Context context, ImageView imageView, String urlImg) {
        myAsyncTask = new MyAsyncTask(context,imageView);
        myAsyncTask.execute(urlImg);
    }
}
