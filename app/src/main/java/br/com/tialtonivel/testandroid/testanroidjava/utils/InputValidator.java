package br.com.tialtonivel.testandroid.testanroidjava.utils;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import br.com.tialtonivel.testandroid.testanroidjava.data.TypeField;

import static br.com.tialtonivel.testandroid.testanroidjava.utils.Tools.isValidEmail;
import static br.com.tialtonivel.testandroid.testanroidjava.utils.Tools.isValidNome;
import static br.com.tialtonivel.testandroid.testanroidjava.utils.Tools.isValidPhoneNumber;

public class InputValidator implements TextWatcher {
    private final EditText editText;
    private String type;
    private boolean isRunning = false;
    private boolean isDeleting = false;
    private String mask;
    private String textFinal;
    private TextInputLayout til;

    public InputValidator(TextInputLayout til, EditText editText, String type) {
        this.editText = editText;
        this.type = type;
        this.til = til;
    }

    public InputValidator(TextInputLayout til, EditText editText, String type, String mask) {
        this.editText = editText;
        this.type = type;
        this.mask = mask;
        this.til = til;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        isDeleting = count > after;
    }

    @Override
    final public void afterTextChanged(Editable s) {
        if (isRunning) {
            return;
        }
        isRunning = true;
        String text = editText.getText().toString();
        switch (TypeField.getFromObject(type)){
            case telNumber:

            int editableLength = s.length();
            if (editableLength < mask.length()) {
                if (mask.charAt(editableLength) != '_') {
                    s.append(mask.charAt(editableLength));
                } else if (mask.charAt(editableLength-1) != '_') {
                    s.insert(editableLength-1, mask, editableLength-1, editableLength);
                }
                textFinal = editText.getText().toString();
            } else {
                editText.setText(textFinal);
                editText.setSelection(textFinal.length());
            }
            text = editText.getText().toString();
            if(!isValidPhoneNumber(text)) {
                til.setError("Telefone esta invalido");
                til.setErrorEnabled(true);
            }else {
                til.setError("");
                til.setErrorEnabled(false);
            }
        break;
            case text:
                if(!isValidNome(text)) {
                    til.setError("Nome esta invalido");
                    til.setErrorEnabled(true);
                }else {
                    til.setError("");
                    til.setErrorEnabled(false);
                }
        break;
            case email:
            if(!isValidEmail(text)){
                til.setError("Email esta invalido");
                til.setErrorEnabled(true);
            }else{
                til.setError("");
                til.setErrorEnabled(false);
            }
            break;
        }

        isRunning = false;
    }

    @Override
    final public void
    onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
