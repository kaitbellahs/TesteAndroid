package br.com.tialtonivel.testandroid.testanroidjava;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import br.com.tialtonivel.testandroid.testanroidjava.data.Cells;
import br.com.tialtonivel.testandroid.testanroidjava.data.Fields;
import br.com.tialtonivel.testandroid.testanroidjava.internet.http;

public class MainActivity extends Activity {

    Context ctx = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String json = new HttpAsyncTask().execute("http://hmkcode.appspot.com/rest/controller/get.json");
        //
        this.ctx = this;
        try{
            final LinearLayout linearLayout = findViewById(R.id.page1);
            System.out.println("Downloading Cells...");
        new http(new http.AsyncResponse(){

            @Override
            public void processFinish(String output){

                try{
                    //System.out.println(output);
                    Cells data = new Gson().fromJson(output, Cells.class);
                    //System.out.println(data);
                    List<Cells> cells = data.getCells();
                    /*TextInputLayout til = new TextInputLayout(this);
                    EditText et = new EditText(getBaseContext());
                    til.addView(et);
                    linearLayout.addView(til);*/
                    for(Cells cell : cells){
                        System.out.println(cell);
                        View v = new Fields().createField(ctx, cell.getType(), cell.getMessage(), cell.getTypefield(), cell.getHidden(), cell.getTopSpacing(), cell.getShow(), cell.getRequired());
                        if(v!=null)linearLayout.addView(v);
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
        }).execute("https://floating-mountain-50292.herokuapp.com/cells.json");
        }catch (Exception ex){
            System.out.println(ex);
        }
    }



}
