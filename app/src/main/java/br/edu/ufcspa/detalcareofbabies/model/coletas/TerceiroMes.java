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
 *  classe modelo para tercMes de cadastro
 */
public class TerceiroMes {
    private int idTercMes;
    private double pesoBebe;
    private double altBebe;
    private boolean usaChupeta;
    private int freqChup;
    private int alimentacao;
    private boolean achocolatado;
    private boolean cha;
    private boolean cafe;
    private boolean suco;
    private boolean refri;
    private boolean caldoAlim;
    private boolean leiteEmPo;
    private boolean leiteMaterno;
    private boolean higienizacaoBoca;
    private String foto;

    public TerceiroMes(int idTercMes, double pesoBebe, double altBebe, boolean usaChupeta,
                       int freqChup, int alimentacao, boolean achocolatado, boolean cha, boolean cafe,
                       boolean suco, boolean refri, boolean caldoAlim, boolean leiteEmPo, boolean leiteMaterno,
                       boolean higienizacaoBoca, String foto) {
        this.idTercMes = idTercMes;
        this.pesoBebe = pesoBebe;
        this.altBebe = altBebe;
        this.usaChupeta = usaChupeta;
        this.freqChup = freqChup;
        this.alimentacao = alimentacao;
        this.achocolatado = achocolatado;
        this.cha = cha;
        this.cafe = cafe;
        this.suco = suco;
        this.refri = refri;
        this.caldoAlim = caldoAlim;
        this.leiteEmPo = leiteEmPo;
        this.leiteMaterno = leiteMaterno;
        this.higienizacaoBoca = higienizacaoBoca;
        this.foto = foto;
    }
    public TerceiroMes(){}

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdTercMes() {
        return idTercMes;
    }

    public void setIdTercMes(int idTercMes) {
        this.idTercMes = idTercMes;
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

    public boolean isUsaChupeta() {
        return usaChupeta;
    }

    public void setUsaChupeta(boolean usaChupeta) {
        this.usaChupeta = usaChupeta;
    }

    public int getFreqChup() {
        return freqChup;
    }

    public void setFreqChup(int freqChup) {
        this.freqChup = freqChup;
    }

    public int getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(int alimentacao) {
        this.alimentacao = alimentacao;
    }

    public boolean isAchocolatado() {
        return achocolatado;
    }

    public void setAchocolatado(boolean achocolatado) {
        this.achocolatado = achocolatado;
    }

    public boolean isCha() {
        return cha;
    }

    public void setCha(boolean cha) {
        this.cha = cha;
    }

    public boolean isCafe() {
        return cafe;
    }

    public void setCafe(boolean cafe) {
        this.cafe = cafe;
    }

    public boolean isSuco() {
        return suco;
    }

    public void setSuco(boolean suco) {
        this.suco = suco;
    }

    public boolean isRefri() {
        return refri;
    }

    public void setRefri(boolean refri) {
        this.refri = refri;
    }

    public boolean isCaldoAlim() {
        return caldoAlim;
    }

    public void setCaldoAlim(boolean caldoAlim) {
        this.caldoAlim = caldoAlim;
    }

    public boolean isLeiteEmPo() {
        return leiteEmPo;
    }

    public void setLeiteEmPo(boolean leiteEmPo) {
        this.leiteEmPo = leiteEmPo;
    }

    public boolean isLeiteMaterno() {
        return leiteMaterno;
    }

    public void setLeiteMaterno(boolean leiteMaterno) {
        this.leiteMaterno = leiteMaterno;
    }

    public boolean isHigienizacaoBoca() {
        return higienizacaoBoca;
    }

    public void setHigienizacaoBoca(boolean higienizacaoBoca) {
        this.higienizacaoBoca = higienizacaoBoca;
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
            json.put("chupeta",usaChupeta);
            json.put("frequencia_chupeta",freqChup);
            json.put("alimentacao",alimentacao);
            json.put("achocolatado",achocolatado);
            json.put("cha",cha);
            json.put("cafe",cafe);
            json.put("suco",suco);
            json.put("refrigerante",refri);
            json.put("caldo_de_alimentos",caldoAlim);
            json.put("leite_em_po",leiteEmPo);
            json.put("leite_materno",leiteMaterno);
            json.put("higienizacao_da_boca",higienizacaoBoca);

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

    @Override
    public String toString() {
        return "TerceiroMes{" +
                "idTercMes=" + idTercMes +
                ", pesoBebe=" + pesoBebe +
                ", altBebe=" + altBebe +
                ", usaChupeta=" + usaChupeta +
                ", freqChup=" + freqChup +
                ", alimentacao=" + alimentacao +
                ", achocolatado=" + achocolatado +
                ", cha=" + cha +
                ", cafe=" + cafe +
                ", suco=" + suco +
                ", refri=" + refri +
                ", caldoAlim=" + caldoAlim +
                ", leiteEmPo=" + leiteEmPo +
                ", leiteMaterno=" + leiteMaterno +
                ", higienizacaoBoca=" + higienizacaoBoca +
                ", foto='" + foto + '\'' +
                '}';
    }
}
