package br.com.tialtonivel.testandroid.testanroidjava.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    public static String toPercent(double db) {
        try {
            return Double.toString(db).replace(".",",")+"%";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-";
    }


    public static int dpToPx(int dp, Context ctx) {
        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }



    public static boolean isValidEmail(CharSequence target) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,12})$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean isValidPhoneNumber(CharSequence target) {
        String lengt_phone = target.toString()
                .replace("(","")
                .replace(")","")
                .replace("-","")
                .replace(" ","");
        //System.out.println(lengt_phone);
        return (lengt_phone.length() == 10 || lengt_phone.length() == 11);
    }

    public static boolean isValidNome(CharSequence target) {
        String lengt_nome = target.toString()
                .replace("(","")
                .replace(")","")
                .replace("-","")
                .replace(" ","");
        //System.out.println(lengt_nome);
        return (lengt_nome.length() > 2);
    }
}
