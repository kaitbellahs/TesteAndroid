package br.com.tialtonivel.testandroid.testanroidjava.data;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.CompoundButtonCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.tialtonivel.testandroid.testanroidjava.R;

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
                    et.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                }else if(typefield == "3"){
                    et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
                }else if(typefield == "telnumber"){
                    et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE);
                }
                et.setError("");
                til.addView(et, LayoutParams);
                return til;
            case 2:
                TextView tv = new TextView(ctx);
                tv.setText(message);
                tv.setLayoutParams(LayoutParams);
                return tv;
            case 4:
                CheckBox ctv = new CheckBox(ctx);
                ctv.setText(message);
                ctv.setLayoutParams(LayoutParams);
                setCheckBoxColor(ctv,Color.RED, Color.BLUE);
                return ctv;
            case 5:
                Button bt = new Button(ctx);
                bt.setText(message);
                drawable = resources.getDrawable(R.drawable.rounded_shape);
                bt.setBackgroundDrawable(drawable);

                bt.setLayoutParams(LayoutParams);
                return bt;
        }
        return null;
    }

}
