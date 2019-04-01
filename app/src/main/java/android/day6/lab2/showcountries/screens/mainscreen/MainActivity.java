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
    MainContract.MainPresenter presenter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRank = (TextView) findViewById(R.id.textViewRank);
        txtCountry = (TextView) findViewById(R.id.textViewCountry);
        txtPopulation = (TextView) findViewById(R.id.textViewPopulation);
        imgFlag = (ImageView) findViewById(R.id.imageViewFlag);
        presenter = new MainPresenterImpl(this);
        presenter.downloadCountriesData();
    }

    public void getNextImg(View view) {
        index = presenter.getNextCountry(index);

    }

    public void getPrevImg(View view) {
        index = presenter.getPreviousCountry(index);
    }

    @Override
    public void showCountry(Country country) {
        txtRank.setText(country.getRank());
        txtCountry.setText(country.getCountry());
        txtPopulation.setText(country.getPopulation());
        imgFlag.setImageBitmap(country.getImgFlag());
    }

}
