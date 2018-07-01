package br.com.tialtonivel.testandroid.testanroidjava;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import br.com.tialtonivel.testandroid.testanroidjava.data.Cells;
import br.com.tialtonivel.testandroid.testanroidjava.data.Fields;
import br.com.tialtonivel.testandroid.testanroidjava.data.Fund;
import br.com.tialtonivel.testandroid.testanroidjava.gif.GifDecoderView;
import br.com.tialtonivel.testandroid.testanroidjava.internet.http;
import br.com.tialtonivel.testandroid.testanroidjava.utils.internet;

public class MainActivity extends Activity {

    Context ctx = null;
    boolean page1Loaded = false;
    boolean page2Loaded = false;
    Button btnPage1;
    Button btnPage2;
    boolean working = false;
    GifDecoderView loading;
    LinearLayout page1;
    LinearLayout page2;
    RelativeLayout mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String json = new HttpAsyncTask().execute("http://hmkcode.appspot.com/rest/controller/get.json");
        //
        this.ctx = this;
        page1 = (LinearLayout) findViewById(R.id.page1);
        page2 = (LinearLayout) findViewById(R.id.page2);
        mainActivity = (RelativeLayout) findViewById(R.id.mainActivity);
        btnPage1 = (Button) findViewById(R.id.btnPage1);
        btnPage2 = (Button) findViewById(R.id.btnPage2);
        page2.setVisibility(View.GONE);
        InputStream stream = null;
        try {
            stream = getAssets().open("loader.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loading = new GifDecoderView(this, stream);
        mainActivity.addView(loading);
        HidePages();

        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int page = 2;
                if(!working) {
                    working = true;
                    HidePages();
                    try {
                        System.out.println("Downloading fund...");
                        if (!page2Loaded) {
                            if (new internet().IsConnected(ctx)) {
                                new http(new http.AsyncResponse() {

                                    @Override
                                    public void processFinish(String output) {

                                        try {
                                            Cells data = new Gson().fromJson(output, Cells.class);
                                            List<Cells> cells = data.getCells();
                                            for (Cells cell : cells) {
                                                System.out.println(cell);
                                                View v = new Fields().createField(ctx, cell.getType(), cell.getMessage(), cell.getTypefield(),
                                                        cell.getHidden(), cell.getTopSpacing(), cell.getShow(), cell.getRequired());
                                                if (v != null) page2.addView(v);
                                            }

                                            System.out.println(data);
                                            page2Loaded = true;
                                            ShowPage(page);
                                            ChangeBtnColor(page);
                                            working = false;
                                        } catch (Exception ex) {
                                            System.out.println(ex);
                                        }
                                    }
                                }).execute("https://floating-mountain-50292.herokuapp.com/cells.json");
                            } else{
                                Toast.makeText(ctx, "Não esta conectado na Internet", Toast.LENGTH_LONG).show();
                                working = false;
                            }
                        } else {
                            ShowPage(page);
                            ChangeBtnColor(page);
                            working = false;
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    } finally {
                        loading.setVisibility(View.GONE);
                        working = false;
                    }
                }
            }
        });

        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int page = 1;
                if(!working) {
                    working = true;
                    HidePages();
                    try {
                        System.out.println("Downloading Cells...");
                        if (!page1Loaded) {
                            if (new internet().IsConnected(ctx)) {
                                new http(new http.AsyncResponse() {

                                    @Override
                                    public void processFinish(String output) {

                                        try {
                                            Fund data = new Gson().fromJson(output, Fund.class);
                                    /*List<Cells> cells = data;
                                    for (Cells cell : cells) {
                                        System.out.println(cell);
                                        View v = new Fields().createField(ctx, cell.getType(), cell.getMessage(), cell.getTypefield(),
                                                cell.getHidden(), cell.getTopSpacing(), cell.getShow(), cell.getRequired());
                                        if (v != null) page1.addView(v);
                                    }*/
                                            page1Loaded = true;
                                            ShowPage(page);
                                            ChangeBtnColor(page);
                                            working = false;
                                        } catch (Exception ex) {
                                            System.out.println(ex);
                                        }
                                    }
                                }).execute("https://floating-mountain-50292.herokuapp.com/fund.json");
                            } else {
                                Toast.makeText(ctx, "Não esta conectado na Internet", Toast.LENGTH_LONG).show();
                                working = false;
                            }
                        } else {
                            ShowPage(page);
                            ChangeBtnColor(page);
                            working = false;
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    } finally {
                        loading.setVisibility(View.GONE);
                        working = false;
                    }
                }
            }
        });
        btnPage2.callOnClick();
    }

    void ShowPage(int page){
        loading.setVisibility(View.GONE);
        page2.setVisibility(page==2 ? View.VISIBLE:View.GONE);
        page1.setVisibility(page==1 ? View.VISIBLE:View.GONE);
        System.out.println("page: "+page);
    }

    void HidePages(){
        loading.setVisibility(View.VISIBLE);
        page2.setVisibility(View.GONE);
        page1.setVisibility(View.GONE);
        System.out.println("Hide all pages.");
    }

    void ChangeBtnColor(int page) {
        int primary = getResources().getColor(R.color.btnPrimary);
        int pressed = getResources().getColor(R.color.btnPressed);
        btnPage1.setBackgroundColor(page==1 ? primary : pressed);
        btnPage2.setBackgroundColor(page==2 ? primary : pressed);
    }

}
