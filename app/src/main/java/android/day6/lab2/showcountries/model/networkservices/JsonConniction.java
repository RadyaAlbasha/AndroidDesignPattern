package android.day6.lab2.showcountries.model.networkservices;

import android.day6.lab2.showcountries.model.Country;
import android.day6.lab2.showcountries.model.network.HttpHandler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonConniction implements Runnable{

    private String url;
    private HttpHandler httpHandler;
    //list of countries;
    private List<Country> countriesList;
    private Country country;
    //Handler handler;

    NetworkServiceInterface networlInterface;

    public List<Country> getCountriesList() {
        return countriesList;
    }

    public JsonConniction(NetworkServiceInterface _networlInterface) {
        url="https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
        httpHandler = new HttpHandler();
        countriesList = new ArrayList<Country>();
        networlInterface = _networlInterface;
        //handler =_handler;
    }
    @Override
    public void run() {
        try {

            String resultJson = httpHandler.makeServiceCall(url);
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
            networlInterface.callHandler(countriesList);
           // handler.sendEmptyMessage(0);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
