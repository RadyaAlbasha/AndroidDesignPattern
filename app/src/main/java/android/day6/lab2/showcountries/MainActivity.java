package android.day6.lab2.showcountries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView txtRank;
    TextView txtCountry;
    TextView txtPopulation;
    ImageView imgFlag;
    Handler handler;
    static int index;
    String urlFlag;
    List<Country> myCountryList;
    JsonConniction jsonConniction;
    MyAsyncTask myAsyncTask;

    public MainActivity() {
        this.index = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRank = (TextView) findViewById(R.id.textViewRank);
        txtCountry = (TextView) findViewById(R.id.textViewCountry);
        txtPopulation = (TextView) findViewById(R.id.textViewPopulation);
        imgFlag = (ImageView) findViewById(R.id.imageViewFlag);
        jsonConniction=new JsonConniction();

        myCountryList = jsonConniction.getCountriesList();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                txtRank.setText(myCountryList.get(index).getRank());
                txtCountry.setText(myCountryList.get(index).getCountry());
                txtPopulation.setText(myCountryList.get(index).getPopulation());
                urlFlag=myCountryList.get(index).getFlag();
                Log.i("urlFlag",urlFlag);
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(urlFlag);
            }
        };
        Thread updater = new Thread(jsonConniction);
        updater.start();

    }

    class JsonConniction implements Runnable{

        private String url="https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
        private URL urlObj=null;
        //list of countries;
        private List<Country> countriesList = new ArrayList<Country>();
        private Country country;
        private HttpsURLConnection httpConn = null;
        private InputStream inputStream = null;

        public List<Country> getCountriesList() {
            return countriesList;
        }

        @Override
        public void run() {
            try {
                urlObj = new URL(url);
                httpConn = (HttpsURLConnection) urlObj.openConnection();
                httpConn.connect();
                inputStream = httpConn.getInputStream();
                String resultJson = convertStreamToString(inputStream);
                JSONObject jsonObg = new JSONObject(resultJson);
                JSONArray jArray = jsonObg.getJSONArray("worldpopulation");
                String countryFlag;
                for(int i=0 ; i < jArray.length() ; i++)
                {
                    country = new Country();
                    country.setRank(jArray.getJSONObject(i).getString("rank"));
                    country.setCountry(jArray.getJSONObject(i).getString("country"));
                    country.setPopulation(jArray.getJSONObject(i).getString("population"));
                    countryFlag=jArray.getJSONObject(i).getString("flag");
                    countryFlag=countryFlag.replaceFirst("http","https");
                    Log.i("replace_Country_Flag",countryFlag);
                    country.setFlag(countryFlag);
                    countriesList.add(country);
                }
                handler.sendEmptyMessage(0);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void getNextImg(View view) {
        if(index<myCountryList.size()-1)
            index++;
        else
            index=0;
        txtRank.setText(myCountryList.get(index).getRank());
        txtCountry.setText(myCountryList.get(index).getCountry());
        txtPopulation.setText(myCountryList.get(index).getPopulation());
        urlFlag=myCountryList.get(index).getFlag();
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(urlFlag);
    }

    public void getPrevImg(View view) {
        if(index>0)
            index--;
        else
            index=myCountryList.size()-1;
        txtRank.setText(myCountryList.get(index).getRank());
        txtCountry.setText(myCountryList.get(index).getCountry());
        txtPopulation.setText(myCountryList.get(index).getPopulation());
        urlFlag=myCountryList.get(index).getFlag();
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(urlFlag);
    }
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    ///////////////////////////////////////////////////////////////////////
    public Bitmap download(String url)throws IOException{
        Bitmap result = null;
        URL urlObj=null;
        HttpsURLConnection httpConn=null;
        InputStream inputStream=null;
        try {
            urlObj = new URL(url);
            httpConn = (HttpsURLConnection) urlObj.openConnection();
            httpConn.connect();
            inputStream=httpConn.getInputStream();
            result= BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream != null)
            {
                inputStream.close();
            }
        }
        return result;
    }
    public class MyAsyncTask extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... uris) {
            Bitmap result = null;
            try {
                result = download(uris[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgFlag.setImageBitmap(bitmap);
            Toast.makeText(MainActivity.this, "Download  Sucessfuly", Toast.LENGTH_SHORT).show();
        }

    }
}
