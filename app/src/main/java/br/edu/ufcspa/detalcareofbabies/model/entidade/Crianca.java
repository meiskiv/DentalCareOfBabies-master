package br.edu.ufcspa.detalcareofbabies.model.entidade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by icarus on 21/11/15.
 */
public class Crianca {

    private int id;
    private String nomeCria;
    private String dataNascCria;
    private int sexoCria;
    private double pesoCriaNasc;
    private double altCriaNasc;
    private String foto;

    public Crianca() {
    }

    public Crianca(String nomeCria, String dataNascCria, int sexoCria, double pesoCriaNasc, double altCriaNasc, String foto) {
        this.nomeCria = nomeCria;
        this.dataNascCria = dataNascCria;
        this.sexoCria = sexoCria;
        this.pesoCriaNasc = pesoCriaNasc;
        this.altCriaNasc = altCriaNasc;
        this.foto = foto;
    }

    public int getSexoCria() {
        return sexoCria;
    }

    public void setSexoCria(int sexoCria) {
        this.sexoCria = sexoCria;
    }

    public double getPesoCriaNasc() {
        return pesoCriaNasc;
    }

    public void setPesoCriaNasc(double pesoCriaNasc) {
        this.pesoCriaNasc = pesoCriaNasc;
    }

    public double getAltCriaNasc() {
        return altCriaNasc;
    }

    public void setAltCriaNasc(double altCriaNasc) {
        this.altCriaNasc = altCriaNasc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCria() {
        return nomeCria;
    }

    public void setNomeCria(String nomeCria) {
        this.nomeCria = nomeCria;
    }

    public String getDataNascCria() {
        return dataNascCria;
    }

    public void setDataNascCria(String dataNascCria) {
        this.dataNascCria = dataNascCria;
    }


    public void setAltCriaNasc(int altCriaNasc) {
        this.altCriaNasc = altCriaNasc;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public JSONObject toJSON(){
        JSONObject c = new JSONObject();
        String encodedImage="";
            if(getFoto()!=null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inScaled = false;
                options.inDither = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                Bitmap bmp = BitmapFactory.decodeFile(getFoto());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            }

        try {
            c.put("nome", getNomeCria());
            c.put("nascimento",getDataNascCria());
            c.put("sexo",getSexoCria());
            c.put("peso",getPesoCriaNasc());
            c.put("altura",getAltCriaNasc());
            if(encodedImage!=null){
                c.put("foto",encodedImage);
            }

        } catch (JSONException e) {
            Log.e("toJSON","Erro em toJSON:"+e.getMessage());
        }
        return c;
    }




    @Override
    public String toString() {
        return "Crianca{" +
                "id=" + id +
                ", nomeCria='" + nomeCria + '\'' +
                ", dataNascCria='" + dataNascCria + '\'' +
                ", sexoCria='" + sexoCria + '\'' +
                ", pesoCriaNasc=" + pesoCriaNasc +
                ", altCriaNasc=" + altCriaNasc +
                ", foto='" + foto + '\'' +
                '}';
    }
}

