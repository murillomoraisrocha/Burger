package br.com.app.burg.burger.utils;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

/**
 * Created by Márcio on 27/10/2017.
 */

public class Validation {

    public static void displayErrorMessages(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(MyApplication.getContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
