package br.com.tialtonivel.testandroid.testanroidjava;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import br.com.tialtonivel.testandroid.testanroidjava.data.Cells;
import br.com.tialtonivel.testandroid.testanroidjava.data.Fields;
import br.com.tialtonivel.testandroid.testanroidjava.data.Fund;
import br.com.tialtonivel.testandroid.testanroidjava.data.ValidateForm;
import br.com.tialtonivel.testandroid.testanroidjava.gif.GifDecoderView;
import br.com.tialtonivel.testandroid.testanroidjava.internet.http;
import br.com.tialtonivel.testandroid.testanroidjava.models.InvAdapter;
import br.com.tialtonivel.testandroid.testanroidjava.models.Investment;
import br.com.tialtonivel.testandroid.testanroidjava.utils.Tools;
import br.com.tialtonivel.testandroid.testanroidjava.utils.internet;

import static br.com.tialtonivel.testandroid.testanroidjava.utils.Tools.dpToPx;
import static br.com.tialtonivel.testandroid.testanroidjava.utils.Tools.toPercent;

public class MainActivity extends AppCompatActivity {

    Context ctx = null;
    boolean page1Loaded = false;
    boolean page2Loaded = false;
    Button btnPage1;
    Button btnPage2;
    boolean working = false;
    GifDecoderView loading;
    LinearLayout page2;
    LinearLayout page2Content;
    LinearLayout page1;
    RelativeLayout mainActivity;
    ObjectMapper objectMapper;
    Typeface tf;
    ConstraintLayout thanks;
    List<Cells> cells;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ctx = this;

        objectMapper = new ObjectMapper();
        page2 = (LinearLayout) findViewById(R.id.page2);
        page2Content = (LinearLayout) findViewById(R.id.content);
        page1 = (LinearLayout) findViewById(R.id.page1);
        mainActivity = (RelativeLayout) findViewById(R.id.mainActivity);
        btnPage1 = (Button) findViewById(R.id.btnPage1);
        btnPage2 = (Button) findViewById(R.id.btnPage2);
        thanks = (ConstraintLayout) findViewById(R.id.thanks);
        page2.setVisibility(View.GONE);

        TextView title = findViewById(R.id.title);
        TextView tv_thankyou = findViewById(R.id.tv_thankyou);
        TextView tv_message_success = findViewById(R.id.tv_message_success);
        TextView tv_send_new_message = findViewById(R.id.tv_send_new_message);
        title.setTypeface(tf);
        tv_thankyou.setTypeface(tf);
        tv_message_success.setTypeface(tf);
        tv_send_new_message.setTypeface(tf);
        tv_send_new_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ValidateForm().clearForm(cells, page2Content);
                thanks.setVisibility(View.GONE);
                page2Content.setVisibility(View.VISIBLE);
            }
        });


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
                        System.out.print("Downloading Cells...");
                        if (!page2Loaded) {
                            if (new internet().IsConnected(ctx)) {
                                new http(new http.AsyncResponse() {

                                    @Override
                                    public void processFinish(String output) {
                                        System.out.println("Done.");

                                        try {
                                            Cells data = objectMapper.readValue(output, Cells.class);
                                            cells = data.cells;
                                            Fields fields = new Fields(cells, ctx, page2Content, thanks, page2);
                                            for (Cells cell : cells) {
                                                System.out.println(cell);
                                                View v = fields.createField(cell);
                                                if (v != null) page2Content.addView(v);
                                            }

                                            System.out.println(data);
                                            page2Loaded = true;
                                            ShowPage(page);
                                            ChangeBtnColor(page);
                                            working = false;
                                        } catch (Exception ex) {
                                            System.out.println(ex);
                                            working = false;
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
                        working = false;
                    } finally {
                        loading.setVisibility(View.GONE);
                    }
                }
            }
        });

        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tf = Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf");
                final int page = 1;
                if(!working) {
                    working = true;
                    HidePages();
                    try {
                        System.out.print("Downloading Funds...");
                        if (!page1Loaded) {
                            if (new internet().IsConnected(ctx)) {
                                new http(new http.AsyncResponse() {

                                    @Override
                                    public void processFinish(String output) {
                                        System.out.println("Done.");

                                        ConstraintLayout mainView = page1.findViewById(R.id.main);
                                        try {
                                            Fund data = objectMapper.readValue(output, Fund.class);
                                            System.out.println(data);
                                            for (int i=0; i<data.screen.getTexts().length; i++) {
                                                TextView textView = (TextView) mainView.getChildAt(i+1);
                                                textView.setTypeface(tf);
                                                textView.setText(data.screen.getTexts()[i]);
                                            }

                                            List<Investment> investments = new ArrayList<>();

                                            Investment title = new Investment();
                                            title.title = null;
                                            title.first = "Fundo";
                                            title.last = "CDI";
                                            investments.add(title);

                                            Fund.Screen.MoreInfo moreInfo = data.screen.moreInfo;
                                            String[] titles = new String[]{"No mês", "No Ano", "12 meses"};
                                            double[] fundos = new double[]{moreInfo.month.fund,moreInfo.year.fund, moreInfo.months12.fund};
                                            double[] cdis = new double[]{moreInfo.month.fund,moreInfo.year.CDI, moreInfo.months12.CDI};
                                            for (int i=0; i<titles.length; i++) {
                                                Investment investment = new Investment();
                                                investment.title = titles[i];
                                                investment.first = toPercent(fundos[i]);
                                                investment.last = toPercent(cdis[i]);
                                                investments.add(investment);
                                            }

                                            investments.add(new Investment()); //new line

                                            for(Fund.Screen.Info info: data.screen.info) {

                                                Investment investment = new Investment();
                                                investment.title = info.name;
                                                investment.last = info.data;
                                                investments.add(investment);
                                            }

                                            for(Fund.Screen.Info info: data.screen.downInfo) {

                                                Investment investment = new Investment();
                                                investment.title = info.name;
                                                investment.last = "Baixar";
                                                investment.download = true;
                                                investment.textColor = Color.RED;
                                                investments.add(investment);
                                            }


                                            RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
                                            recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                                            recyclerView.setAdapter(new InvAdapter(investments, ctx));
                                            recyclerView.setHasFixedSize(true);
                                            recyclerView.setNestedScrollingEnabled(false);
                                            ConstraintLayout.LayoutParams c = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();

                                            c.height = dpToPx(36 * investments.size(), ctx);
                                            recyclerView.setLayoutParams(c);
                                            showRisk(data.screen.risk, mainView);
                                            page1Loaded = true;
                                            ShowPage(page);
                                            ChangeBtnColor(page);
                                            working = false;
                                        } catch (Exception ex) {
                                            System.out.println(ex);
                                            working = false;
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
                        working = false;
                    } finally {
                        loading.setVisibility(View.GONE);
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
        System.out.println("");
        System.out.println("page: "+page);
    }

    public void HidePages(){
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


    void showRisk(int position, View root) {

        ConstraintLayout constraintLayout = root.findViewById(R.id.constraintLayout);
        View imageView = constraintLayout.getChildAt(position-1);
        imageView.setVisibility(View.VISIBLE);
        View view = constraintLayout.getChildAt(position+position);
        ConstraintLayout.LayoutParams c = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        c.height = dpToPx(8, ctx);
        view.setLayoutParams(c);
    }

}
