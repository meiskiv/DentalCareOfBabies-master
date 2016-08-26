package br.edu.ufcspa.detalcareofbabies.client;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.banco.QuestDAO;

/**
 * Created by icarus on 28/05/16.
 */
public class QuestClient extends HttpClient {

    public QuestClient(Context context) {
        super(context);
    }
    public void postJson(JSONObject jsonBody){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL + "questionario.php", jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("result").equals("0")){
                        Log.e("Erro", "JSON Post erro");
                    }else {
                        if(response.getString("result").equals("1")){
                            Log.e("Response JSON", "JSON Post com sucesso:"+response.getString("result")+ response.toString());
                            DataHelper data = new DataHelper(context);
                            QuestDAO.update(data.getWritableDatabase(),1);
                            data=null;
                            //data.close();
                        }
                        Log.e("Response JSON", "JSON Post terceiro mes concluido");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("erro:",volleyError.toString());
            }
        });
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);
    }

}
