package br.com.tialtonivel.testandroid.testanroidjava.internet;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class http extends AsyncTask<String, Void, String> {

    public interface AsyncResponse {
        public void processFinish(String output);
    }


    public AsyncResponse delegate = null;

    public http(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... urls) {

        StringBuffer chaine = new StringBuffer("");
        try{
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "Test Android V1.0");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(120000);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }
        }
        catch (IOException e) {
            // Writing exception to log
            e.printStackTrace();
            return "{}";
        }
        return chaine.toString();
        //return "{}";
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}

