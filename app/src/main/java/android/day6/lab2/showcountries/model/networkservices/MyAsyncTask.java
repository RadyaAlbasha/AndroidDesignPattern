package android.day6.lab2.showcountries.model.networkservices;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MyAsyncTask extends AsyncTask<String,Void, Bitmap> {

    Context context;
    ImageView imgFlag;
    public MyAsyncTask(Context _context , ImageView _imgFlag) {
        this.context = _context;
        imgFlag = _imgFlag;
    }

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
        Toast.makeText(context, "Download  Sucessfuly", Toast.LENGTH_SHORT).show();
    }

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

}
