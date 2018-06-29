package br.com.tialtonivel.testandroid.testanroidjava.data;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.CompoundButtonCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.tialtonivel.testandroid.testanroidjava.R;
import br.com.tialtonivel.testandroid.testanroidjava.utils.MaskWatcher;

public class Fields {
    public void setCheckBoxColor(CheckBox checkBox, int checkedColor, int uncheckedColor) {
        int states[][] = {{android.R.attr.state_checked}, {}};
        int colors[] = {checkedColor, uncheckedColor};
        CompoundButtonCompat.setButtonTintList(checkBox, new
                ColorStateList(states, colors));
    }
    public View createField(Context ctx, int type, String message, String typefield, boolean hidden, int topSpacing, String show, boolean required){

        LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams tilLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LayoutParams.setMargins(0, topSpacing, 0, 0);
        Resources resources = ctx.getResources();
        Drawable drawable;
        switch (type){
            case 1:
                TextInputLayout til = new TextInputLayout(ctx);
                til.setLayoutParams(tilLayoutParams);
                TextInputEditText et = new TextInputEditText(ctx);
                et.setHint(message);
                et.setSingleLine(true);
                et.setLayoutParams(LayoutParams);
                if(typefield == "1"){
                    et.setInputType(InputType.TYPE_CLASS_TEXT);
                }else if(typefield == "3"){
                    et.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
                }else if(typefield == "telnumber"){
                    et.setInputType(InputType.TYPE_CLASS_PHONE);
                    et.addTextChangedListener(new MaskWatcher(et, "(__) _____-____"));
                }
                et.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf"));
                et.setError("");
                til.addView(et, LayoutParams);
                return til;
            case 2:
                TextView tv = new TextView(ctx);
                tv.setText(message);
                tv.setLayoutParams(LayoutParams);
                tv.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf"));
                return tv;
            case 4:
                CheckBox ctv = new CheckBox(ctx);
                ctv.setText(message);
                ctv.setLayoutParams(LayoutParams);
                setCheckBoxColor(ctv,Color.RED, Color.BLUE);
                ctv.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf"));
                return ctv;
            case 5:
                Button bt = new Button(ctx);
                bt.setText(message);
                drawable = resources.getDrawable(R.drawable.rounded_shape);
                bt.setBackgroundDrawable(drawable);
                bt.setLayoutParams(LayoutParams);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //verificar entradas
                    }
                });
                bt.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf"));
                return bt;
        }
        return null;
    }

}
