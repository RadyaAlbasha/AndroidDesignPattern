package android.day6.lab2.showcountries.screens.mainscreen;

import android.day6.lab2.showcountries.R;
import android.day6.lab2.showcountries.model.Country;
import android.day6.lab2.showcountries.model.networkservices.JsonConniction;
import android.day6.lab2.showcountries.model.networkservices.MyAsyncTask;
import android.day6.lab2.showcountries.model.networkservices.NetworkServiceImp;
import android.day6.lab2.showcountries.model.networkservices.NetworkServiceInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements MainContract.MainView{

    TextView txtRank;
    TextView txtCountry;
    TextView txtPopulation;
    ImageView imgFlag;
    static int index;
    String urlFlag;
    List<Country> myCountryList;
    NetworkServiceInterface networkService ;

    boolean downloadDataIsDoneFlag;

    public MainActivity() {
        downloadDataIsDoneFlag = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRank = (TextView) findViewById(R.id.textViewRank);
        txtCountry = (TextView) findViewById(R.id.textViewCountry);
        txtPopulation = (TextView) findViewById(R.id.textViewPopulation);
        imgFlag = (ImageView) findViewById(R.id.imageViewFlag);
        networkService = new NetworkServiceImp(MainActivity.this);
    }

    public void getNextImg(View view) {
        if(downloadDataIsDoneFlag)
        {
            if(index<myCountryList.size()-1)
                index++;
            else
                index=0;
            txtRank.setText(myCountryList.get(index).getRank());
            txtCountry.setText(myCountryList.get(index).getCountry());
            txtPopulation.setText(myCountryList.get(index).getPopulation());
            urlFlag=myCountryList.get(index).getFlag();
            imgFlag.setImageBitmap(myCountryList.get(index).getImgFlag());
        }
        else
        {
            Toast.makeText(this, "Data not yet loaded", Toast.LENGTH_SHORT).show();
        }
    }

    public void getPrevImg(View view) {
        if(downloadDataIsDoneFlag)
        {
            if(index>0)
                index--;
            else
                index=myCountryList.size()-1;
            txtRank.setText(myCountryList.get(index).getRank());
            txtCountry.setText(myCountryList.get(index).getCountry());
            txtPopulation.setText(myCountryList.get(index).getPopulation());
            urlFlag=myCountryList.get(index).getFlag();
            imgFlag.setImageBitmap(myCountryList.get(index).getImgFlag());
        }
       else
        {
            Toast.makeText(this, "Data not yet loaded", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void downloadDataIsDone(List<Country> countriesList) {
        myCountryList = countriesList;
        txtRank.setText(myCountryList.get(index).getRank());
        txtCountry.setText(myCountryList.get(index).getCountry());
        txtPopulation.setText(myCountryList.get(index).getPopulation());
        imgFlag.setImageBitmap(myCountryList.get(index).getImgFlag());
        downloadDataIsDoneFlag =true;
    }
}
