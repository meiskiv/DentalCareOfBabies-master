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
import br.edu.ufcspa.detalcareofbabies.model.entidade.Usuario;

/**
 * Created by icarus on 22/12/15.
 */
public class UserClient extends HttpClient {
    private Usuario user;

//    public UserClient(Context context) {
//        super(context);
//    }

    public UserClient(Context context, Usuario user) {
        super(context);
        this.user = user;
    }

    public void postJson(JSONObject responsavel,JSONObject crianca){
        JSONObject jsonBody= new JSONObject();
        try {
            jsonBody.put("responsavel",responsavel);
            jsonBody.put("crianca",crianca);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL + "insert.php", jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("result").equals(0)){
                        Log.e("Erro", "JSON Post erro");
                    }else {
                        Log.e("Response JSON", "JSON Post com sucesso idcrianca:"+response.getString("result"));
                        DataHelper data= new DataHelper(context);
                        data.updateIdCrianca(Integer.parseInt(response.getString("result")));
                        data.updateStatusCadastroSync(1);
                        data.close();
                            Log.e("Response JSON", "JSON Post cadastro inicial concluido");

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
