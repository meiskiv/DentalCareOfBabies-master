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
public class SextoMes {
    private double pesoBebe;
    private double altBebe;
    private boolean frutas;
    private boolean ovos;
    private boolean	arroz;
    private boolean	feijao;
    private boolean	mingau;
    private boolean	legumes;
    private boolean	massas;
    private boolean	batata;
    private boolean carnes;
    private boolean	alimentoDoce;
    private boolean	refri;
    private boolean	dente;
    private String qualAlimentoDoce;
    private String foto;


    public SextoMes(){}

    public SextoMes(double pesoBebe, double altBebe, boolean frutas, boolean ovos, boolean arroz, boolean feijao,
                    boolean mingau, boolean legumes, boolean massas, boolean batata,
                    boolean carnes, boolean alimentoDoce, boolean refri, boolean dente,
                    String qualAlimentoDoce, String foto) {
        this.pesoBebe = pesoBebe;
        this.altBebe = altBebe;
        this.frutas = frutas;
        this.ovos = ovos;
        this.arroz = arroz;
        this.feijao = feijao;
        this.mingau = mingau;
        this.legumes = legumes;
        this.massas = massas;
        this.batata = batata;
        this.carnes = carnes;
        this.alimentoDoce = alimentoDoce;
        this.refri = refri;
        this.dente = dente;
        this.qualAlimentoDoce = qualAlimentoDoce;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getQualAlimentoDoce() {
        return qualAlimentoDoce;
    }

    public void setQualAlimentoDoce(String qualAlimentoDoce) {
        this.qualAlimentoDoce = qualAlimentoDoce;
    }

    public double getPesoBebe() {
        return pesoBebe;
    }

    public void setPesoBebe(double pesoBebe) {
        this.pesoBebe = pesoBebe;
    }

    public double getAltBebe() {
        return altBebe;
    }

    public void setAltBebe(double altBebe) {
        this.altBebe = altBebe;
    }

    public boolean isFrutas() {
        return frutas;
    }

    public void setFrutas(boolean frutas) {
        this.frutas = frutas;
    }

    public boolean isOvos() {
        return ovos;
    }

    public void setOvos(boolean ovos) {
        this.ovos = ovos;
    }

    public boolean isArroz() {
        return arroz;
    }

    public void setArroz(boolean arroz) {
        this.arroz = arroz;
    }

    public boolean isFeijao() {
        return feijao;
    }

    public void setFeijao(boolean feijao) {
        this.feijao = feijao;
    }

    public boolean isMingau() {
        return mingau;
    }

    public void setMingau(boolean mingau) {
        this.mingau = mingau;
    }

    public boolean isLegumes() {
        return legumes;
    }

    public void setLegumes(boolean legumes) {
        this.legumes = legumes;
    }

    public boolean isMassas() {
        return massas;
    }

    public void setMassas(boolean massas) {
        this.massas = massas;
    }

    public boolean isBatata() {
        return batata;
    }

    public void setBatata(boolean batata) {
        this.batata = batata;
    }

    public boolean isCarnes() {
        return carnes;
    }

    public void setCarnes(boolean carne) {
        this.carnes = carne;
    }

    public boolean isAlimentoDoce() {
        return alimentoDoce;
    }

    public void setAlimentoDoce(boolean alimentoDoce) {
        this.alimentoDoce = alimentoDoce;
    }

    public boolean isRefri() {
        return refri;
    }

    public void setRefri(boolean refri) {
        this.refri = refri;
    }

    public boolean isDente() {
        return dente;
    }

    public void setDente(boolean dente) {
        this.dente = dente;
    }

    @Override
    public String toString() {
        return "SextoMes{" +
                "pesoBebe=" + pesoBebe +
                ", altBebe=" + altBebe +
                ", frutas=" + frutas +
                ", ovos=" + ovos +
                ", arroz=" + arroz +
                ", feijao=" + feijao +
                ", mingau=" + mingau +
                ", legumes=" + legumes +
                ", massas=" + massas +
                ", batata=" + batata +
                ", carnes=" + carnes +
                ", alimentoDoce=" + alimentoDoce +
                ", refri=" + refri +
                ", dente=" + dente +
                ", qualAlimentoDoce='" + qualAlimentoDoce + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }


    public JSONObject toJSON(int idCrianca){
        JSONObject json = new JSONObject();
        //JSONObject c = new JSONObject();
        String encodedImage="";
        if(getFoto()!=null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            options.inScaled = false;
            options.inDither = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap bmp = BitmapFactory.decodeFile(getFoto());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if(bmp!=null) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            }else{
                Log.e("json", "falha em criar bitmap pelo caminho da foto:" + getFoto());
            }
        }
        try {
            json.put("id_crianca",idCrianca);
            json.put("peso_do_bebe",pesoBebe);
            json.put("altura_do_bebe",altBebe);
            json.put("frutas",frutas);
            json.put("ovos",ovos);
            json.put("arroz",arroz);
            json.put("feijao",feijao);
            json.put("mingau",mingau);
            json.put("legumes",legumes);
            json.put("massas",massas);
            json.put("batata",batata);
            json.put("carne",carnes);
            json.put("alimento_doce",alimentoDoce);
            json.put("qual_doce",qualAlimentoDoce);
            json.put("refrigerante",refri);
            json.put("dentes",dente);
            if(encodedImage!=null){
                json.put("foto", encodedImage);
            }else{
                json.put("foto","null");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
