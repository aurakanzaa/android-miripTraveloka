package com.example.ruwet.miriptraveloka2.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

/**
 * Created by aura on 11/01/18.
 */

public class inputValidation {
    private Context context;

    public inputValidation(Context context){
        this.context = context;
    }

    public boolean isInputFilled (EditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty()){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditEmail (AutoCompleteTextView textInputEditText, TextInputLayout textInputLayout, String message) {

        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;

    }

    public boolean isInputEditMatches (EditText textInputEditText1, EditText textInputEditText2, TextInputLayout textInputLayout, String message){
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if(!value1.contentEquals(value2)){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;

        }
        else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;

    }
    private void hideKeyboardFrom(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
