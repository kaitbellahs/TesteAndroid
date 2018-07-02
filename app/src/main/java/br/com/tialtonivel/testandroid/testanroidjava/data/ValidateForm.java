package br.com.tialtonivel.testandroid.testanroidjava.data;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;

import java.util.List;

import static br.com.tialtonivel.testandroid.testanroidjava.utils.Tools.isValidEmail;
import static br.com.tialtonivel.testandroid.testanroidjava.utils.Tools.isValidPhoneNumber;

public class ValidateForm {

    public void clearForm(List<Cells> items, View mView) {

        for (Cells cell : items) {

            View view = mView.findViewById(cell.id);

            if (Type.getFromInt(cell.type).equals(Type.field)) {

                TextInputLayout textInputLayout = (TextInputLayout) view;

                ((TextInputLayout) view).setError("");
                ((TextInputLayout) view).setErrorEnabled(false);

                textInputLayout.getEditText().setText("");
            }else if (Type.getFromInt(cell.type).equals(Type.checkbox)) {
                ((CheckBox) view).setChecked(false);
            }
        }
    }
    public boolean ValidateForm(List<Cells> items, View mView) {


        for (Cells cell : items) {

            View view = mView.findViewById(cell.id);

            if (Type.getFromInt(cell.type).equals(Type.field)) {

                TextInputLayout textInputLayout = (TextInputLayout) view;

                //mView.removeErrorFromView(textInputLayout);

                ((TextInputLayout) view).setError("");
                ((TextInputLayout) view).setErrorEnabled(false);

                if (cell.required && textInputLayout.getEditText().getText().toString().equals("")) {

                    ((TextInputLayout) view).setError("Este Campo não pode ser vazio");
                    ((TextInputLayout) view).setErrorEnabled(true);
                    return false;
                }

                if (TypeField.getFromObject(cell.typefield).equals(TypeField.email) && !isValidEmail(textInputLayout.getEditText().getText().toString())) {

                    ((TextInputLayout) view).setError("Por favor preencha um email válido");
                    ((TextInputLayout) view).setErrorEnabled(true);
                    return false;
                }

                if (TypeField.getFromObject(cell.typefield).equals(TypeField.telNumber) && !isValidPhoneNumber(textInputLayout.getEditText().getText().toString())) {

                    ((TextInputLayout) view).setError("Por favor preencha um telefone válido");
                    ((TextInputLayout) view).setErrorEnabled(true);
                    return false;
                }
            }
        }
        return true;

        //mView.showMessageSuccess(true);
    }
}