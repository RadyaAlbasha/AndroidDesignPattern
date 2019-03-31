package android.day6.lab2.showcountries.model.networkservices;

import android.content.Context;
import android.day6.lab2.showcountries.model.Country;
import android.day6.lab2.showcountries.screens.mainscreen.MainActivity;
import android.day6.lab2.showcountries.screens.mainscreen.MainContract;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class NetworkServiceImp implements NetworkServiceInterface{
    List<Country> myCountryList;
    JsonConniction jsonConniction;
    MyAsyncTask myAsyncTask;
    Handler handler;
    Context context;
    String urlFlag;
    //Bitmap imgFlagBitmap;
    MainContract.MainView activity;
    public NetworkServiceImp(MainContract.MainView _activity) {
        jsonConniction=new JsonConniction(this);
        context =(Context)_activity;
        activity = _activity;
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //txtRank.setText(myCountryList.get(index).getRank());
                //txtCountry.setText(myCountryList.get(index).getCountry());
                //txtPopulation.setText(myCountryList.get(index).getPopulation());
                //urlFlag=myCountryList.get(index).getFlag();
                //Log.i("urlFlag",urlFlag);
                for(int i=0; i < myCountryList.size() ;i++)
                {
                    urlFlag=myCountryList.get(i).getFlag();
                    downloadImageFlag(NetworkServiceImp.this, urlFlag,i);
                }
                Toast.makeText(context, "Download  Sucessfuly", Toast.LENGTH_SHORT).show();
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
    public void downloadImageFlag(NetworkServiceInterface networlInterface, String urlImg,int _index) {
        myAsyncTask = new MyAsyncTask(networlInterface,_index);
        myAsyncTask.execute(urlImg);
    }

    @Override
    public void downloadImageFlagIsDone(int _index,Bitmap imgFlag) {
        //imgFlagBitmap = imgFlag;
        myCountryList.get(_index).setImgFlag(imgFlag);
    }
}
