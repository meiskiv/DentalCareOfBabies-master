package br.edu.ufcspa.detalcareofbabies.controller;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by icarus on 31/01/16.
 */
public class MyCustomDialog {


    public static void showMessage(Context context ,String title,String message ) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", null);
        alert.show();
    }
}
