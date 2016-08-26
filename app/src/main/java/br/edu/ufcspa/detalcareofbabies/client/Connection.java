package br.edu.ufcspa.detalcareofbabies.client;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by icarus on 31/01/16.
 */
public class Connection {
    public static boolean verificaConexao(Context c) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
