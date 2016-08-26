package br.edu.ufcspa.detalcareofbabies.model.coletas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by icarus on 27/11/15.
 */
public class UmAno {
    private double pesoBebe;
    private double alturaBebe;
    private boolean	amamenta;
    private int alimentacaoComplementar;
    private int	escovacaoDia;
    private boolean	chupeta;
    private String fotoPerfil;
    private String fotoFrente;

    public UmAno(){}

    public UmAno(double pesoBebe, double alturaBebe, boolean amamenta, int alimentacaoComplementar, int escovacaoDia, boolean chupeta, String fotoPerfil, String fotoFrente) {
        this.pesoBebe = pesoBebe;
        this.alturaBebe = alturaBebe;
        this.amamenta = amamenta;
        this.alimentacaoComplementar = alimentacaoComplementar;
        this.escovacaoDia = escovacaoDia;
        this.chupeta = chupeta;
        this.fotoPerfil = fotoPerfil;
        this.fotoFrente = fotoFrente;
    }

    public double getPesoBebe() {
        return pesoBebe;
    }

    public void setPesoBebe(double pesoBebe) {
        this.pesoBebe = pesoBebe;
    }

    public double getAlturaBebe() {
        return alturaBebe;
    }

    public void setAlturaBebe(double alturaBebe) {
        this.alturaBebe = alturaBebe;
    }

    public boolean isAmamenta() {
        return amamenta;
    }

    public void setAmamenta(boolean amamenta) {
        this.amamenta = amamenta;
    }

    public int getAlimentacaoComplementar() {
        return alimentacaoComplementar;
    }

    public void setAlimentacaoComplementar(int alimentacaoComplementar) {
        this.alimentacaoComplementar = alimentacaoComplementar;
    }

    public int getEscovacaoDia() {
        return escovacaoDia;
    }

    public void setEscovacaoDia(int escovacaoDia) {
        this.escovacaoDia = escovacaoDia;
    }

    public boolean isChupeta() {
        return chupeta;
    }

    public void setChupeta(boolean chupeta) {
        this.chupeta = chupeta;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getFotoFrente() {
        return fotoFrente;
    }

    public void setFotoFrente(String fotoFrente) {
        this.fotoFrente = fotoFrente;
    }

    @Override
    public String toString() {
        return "UmAno{" +
                "pesoBebe=" + pesoBebe +
                ", alturaBebe=" +  alturaBebe +
                ", amamenta=" + amamenta +
                ", alimentacaoComplementar=" + alimentacaoComplementar +
                ", escovacaoDia=" + escovacaoDia +
                ", chupeta=" + chupeta +
                ", fotoPerfil='" + fotoPerfil + '\'' +
                '}';
    }

    public JSONObject toJSON(int idCrianca){
        JSONObject json = new JSONObject();
        //JSONObject c = new JSONObject();
        String encodedImage="";
        if(getFotoPerfil()!=null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            options.inScaled = false;
            options.inDither = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap bmp = BitmapFactory.decodeFile(getFotoPerfil());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if(bmp!=null) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            }else{
                Log.e("json", "falha em criar bitmap pelo caminho da fotoPerfil:" + getFotoPerfil());
            }
        }
        try {
            json.put("id_crianca",idCrianca);
            json.put("peso_do_bebe",pesoBebe);
            json.put("altura_do_bebe",alturaBebe);
            json.put("amamenta",amamenta);
            json.put("alimetacao_complementar", alimentacaoComplementar);
            json.put("escovacao",escovacaoDia);
            json.put("chupeta",chupeta);
            if(encodedImage!=null){
                json.put("fotoPerfil", encodedImage);
            }else{
                json.put("fotoPerfil","null");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


}
