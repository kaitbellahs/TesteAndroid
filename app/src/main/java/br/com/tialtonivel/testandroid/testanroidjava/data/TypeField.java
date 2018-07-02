package br.com.tialtonivel.testandroid.testanroidjava.data;

import android.support.annotation.NonNull;

public enum TypeField {
    text("1"),
    telNumber("telnumber"),
    email("3");

    Object typeField;
    TypeField(Object object) {
        this.typeField = object;
    }

    @NonNull
    public static TypeField getFromObject(Object val) {
        for (TypeField typeField: values()) {
            if (typeField.typeField.equals(val)) {
                return  typeField;
            }
        }
        return text;
    }
}