package br.edu.ufcspa.detalcareofbabies.client;

import android.content.Context;

/**
 * Created by icarus on 22/12/15.
 */
public class HttpClient {
    protected Context context;
    protected static final String URL="http://angelo.inf.ufrgs.br/dentalcare/";

    public HttpClient(Context context){
        this.context=context;
    }



}
