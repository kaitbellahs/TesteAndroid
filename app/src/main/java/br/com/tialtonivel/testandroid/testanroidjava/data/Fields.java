package br.com.tialtonivel.testandroid.testanroidjava.data;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.CompoundButtonCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.tialtonivel.testandroid.testanroidjava.MainActivity;
import br.com.tialtonivel.testandroid.testanroidjava.R;
import br.com.tialtonivel.testandroid.testanroidjava.utils.InputValidator;

public class Fields {

    List<Cells> cells;
    View mView;
    ConstraintLayout thanks;
    Context ctx;
    LinearLayout page2;
    public Fields(List<Cells> cells, Context ctx, View mView, ConstraintLayout thanks, LinearLayout page2){
        this.cells = cells;
        this.mView = mView;
        this.thanks = thanks;
        this.ctx = ctx;
        this.page2 = page2;
    }
    public void setCheckBoxColor(CheckBox checkBox, int checkedColor, int uncheckedColor) {
        int states[][] = {{android.R.attr.state_checked}, {}};
        int colors[] = {checkedColor, uncheckedColor};
        CompoundButtonCompat.setButtonTintList(checkBox, new
                ColorStateList(states, colors));
    }
    public View createField(Cells cell){

        LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tilLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tilLayoutParams.setMargins(0, cell.topSpacing, 0, 0);
        Resources resources = ctx.getResources();
        Drawable drawable;
        switch (Type.getFromInt(cell.type)){
            case field:
                EditText et = new EditText(ctx);
                et.setHint(cell.message);
                et.setSingleLine(true);
                et.setLayoutParams(LayoutParams);
                et.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf"));
                et.setError("");
                TextInputLayout til = new TextInputLayout(ctx);
                switch (TypeField.getFromObject(cell.typefield)){
                    case text:
                        et.setInputType(InputType.TYPE_CLASS_TEXT);
                        et.addTextChangedListener(new InputValidator(til, et, cell.typefield));
                        break;
                    case email:
                        et.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
                        et.addTextChangedListener(new InputValidator(til, et, cell.typefield));
                        break;
                    case telNumber:
                        et.setInputType(InputType.TYPE_CLASS_PHONE);
                        et.addTextChangedListener(new InputValidator(til, et, cell.typefield, "(__) _____-_____"));
                        break;
                }
                til.setId(cell.id);
                til.setLayoutParams(tilLayoutParams);
                til.addView(et, LayoutParams);
                return til;
            case text:
                TextView tv = new TextView(ctx);
                tv.setId(cell.id);
                tv.setText(cell.message);
                tv.setLayoutParams(LayoutParams);
                tv.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf"));
                return tv;
            case checkbox:
                CheckBox ctv = new CheckBox(ctx);
                ctv.setId(cell.id);
                ctv.setText(cell.message);
                ctv.setLayoutParams(LayoutParams);
                setCheckBoxColor(ctv,Color.RED, Color.BLUE);
                ctv.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/dinprolight.otf"));
                return ctv;
            case send:
                Button bt = new Button(ctx);
                bt.setId(cell.id);
                bt.setText(cell.message);
                drawable = resources.getDrawable(R.drawable.rounded_shape);
                bt.setLayoutParams(LayoutParams);
                bt.setBackgroundDrawable(drawable);
                bt.setAllCaps(false);
                bt.setTextColor(Color.WHITE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(new ValidateForm().ValidateForm(cells, mView)){
                            mView.setVisibility(View.GONE);
                            thanks.setVisibility(View.VISIBLE);
                        }
                    }
                });
                return bt;
        }
        return null;
    }

}
