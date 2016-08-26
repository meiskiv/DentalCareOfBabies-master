package br.edu.ufcspa.detalcareofbabies.model.coletas;

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
public class PrimeiroMes {
    private int idCrianca;
    private int estCivil;
    private int rendaFamiliar;
    private int consultasPrenatais;
    private double pesoBebe;
    private double alturaBebe;
    private int nGestacoes;
    private int nPartNorm;
    private int nCesaria;
    private int nAbort;
    private int tipoPart; //
    private boolean cesariana;
    private boolean cSofrFet;
    private boolean cDesprop;
    private boolean cHemorragia;
    private boolean cParada;
    private boolean cEclampsia;
    private boolean cPosMatur;
    private boolean cPreEclamp;
    private boolean cDiabet;
    private boolean cTrompas;
    private boolean cRepeticao;
    private boolean cMortFetal;
    private boolean cEletiva;
    private boolean cOutra;
    private boolean cDesconh;
    private boolean Inducao;
    private boolean iPosMat;
    private boolean iPreEclamp;
    private boolean iBolsRot;
    private boolean iIsolmunizacao;
    private boolean iMorteFetal;
    private boolean iEletiva;
    private boolean iOutra;
    private boolean iDesconh;
    private int idadeCriaEmDias;
    private int condicaoSaude; //1 para Sadio 2 para Doente 3 Óbito
    private int alimentacao; //1 leite materno exclusivo 2 leite materno e mamadeira 3 mamadeira apenas
    private int diasHosp;
    private boolean icterisiaImp;
    private boolean trauma;
    private boolean diarreia;
    private boolean infecResp;
    private boolean outraInf;
    private String foto;



    public PrimeiroMes(){}

    public PrimeiroMes(int idCrianca, int estCivil, int rendaFamiliar, int consultasPrenatais, double pesoBebe,
                       double alturaBebe, int nGestacoes, int nPartNorm, int nCesaria, int nAbort, int tipoPart,
                       boolean cesariana, boolean cSofrFet, boolean cDesprop, boolean cHemorragia, boolean cParada,
                       boolean cEclampsia, boolean cPosMatur, boolean cPreEclamp, boolean cDiabet, boolean cTrompas,
                       boolean cRepeticao, boolean cMortFetal, boolean cEletiva, boolean cOutra, boolean cDesconh,
                       boolean inducao, boolean iPosMat, boolean iPreEclamp, boolean iBolsRot, boolean iIsolmunizacao,
                       boolean iMorteFetal, boolean iEletiva, boolean iOutra, boolean iDesconh, int idadeCriaEmDias,
                       int condicaoSaude, int alimentacao, int diasHosp, boolean icterisiaImp, boolean trauma,
                       boolean diarreia, boolean infecResp, boolean outraInf,String fotin) {

        this.idCrianca = idCrianca;
        this.estCivil = estCivil;
        this.rendaFamiliar = rendaFamiliar;
        this.consultasPrenatais = consultasPrenatais;
        this.pesoBebe = pesoBebe;
        this.alturaBebe = alturaBebe;
        this.nGestacoes = nGestacoes;
        this.nPartNorm = nPartNorm;
        this.nCesaria = nCesaria;
        this.nAbort = nAbort;
        this.tipoPart = tipoPart;
        this.cesariana = cesariana;
        this.cSofrFet = cSofrFet;
        this.cDesprop = cDesprop;
        this.cHemorragia = cHemorragia;
        this.cParada = cParada;
        this.cEclampsia = cEclampsia;
        this.cPosMatur = cPosMatur;
        this.cPreEclamp = cPreEclamp;
        this.cDiabet = cDiabet;
        this.cTrompas = cTrompas;
        this.cRepeticao = cRepeticao;
        this.cMortFetal = cMortFetal;
        this.cEletiva = cEletiva;
        this.cOutra = cOutra;
        this.cDesconh = cDesconh;
        Inducao = inducao;
        this.iPosMat = iPosMat;
        this.iPreEclamp = iPreEclamp;
        this.iBolsRot = iBolsRot;
        this.iIsolmunizacao = iIsolmunizacao;
        this.iMorteFetal = iMorteFetal;
        this.iEletiva = iEletiva;
        this.iOutra = iOutra;
        this.iDesconh = iDesconh;
        this.idadeCriaEmDias = idadeCriaEmDias;
        this.condicaoSaude = condicaoSaude;
        this.alimentacao = alimentacao;
        this.diasHosp = diasHosp;
        this.icterisiaImp = icterisiaImp;
        this.trauma = trauma;
        this.diarreia = diarreia;
        this.infecResp = infecResp;
        this.outraInf = outraInf;
        this.foto=fotin;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isiDesconh() {
        return iDesconh;
    }

    public void setiDesconh(boolean iDesconh) {
        this.iDesconh = iDesconh;
    }

    public int getIdCrianca() {
        return idCrianca;
    }

    public void setIdCrianca(int idCrianca) {
        this.idCrianca = idCrianca;
    }

    public int getEstCivil() {
        return estCivil;
    }

    public void setEstCivil(int estCivil) {
        this.estCivil = estCivil;
    }

    public int getRendaFamiliar() {
        return rendaFamiliar;
    }

    public void setRendaFamiliar(int rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }

    public int getConsultasPrenatais() {
        return consultasPrenatais;
    }

    public void setConsultasPrenatais(int consultasPrenatais) {
        this.consultasPrenatais = consultasPrenatais;
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

    public int getnGestacoes() {
        return nGestacoes;
    }

    public void setnGestacoes(int nGestacoes) {
        this.nGestacoes = nGestacoes;
    }

    public int getnPartNorm() {
        return nPartNorm;
    }

    public void setnPartNorm(int nPartNorm) {
        this.nPartNorm = nPartNorm;
    }

    public int getnCesaria() {
        return nCesaria;
    }

    public void setnCesaria(int nCesaria) {
        this.nCesaria = nCesaria;
    }

    public int getnAbort() {
        return nAbort;
    }

    public void setnAbort(int nAbort) {
        this.nAbort = nAbort;
    }

    public int getTipoPart() {
        return tipoPart;
    }

    public void setTipoPart(int tipoPart) {
        this.tipoPart = tipoPart;
    }

    public boolean isCesariana() {
        return cesariana;
    }

    public void setCesariana(boolean cesariana) {
        this.cesariana = cesariana;
    }

    public boolean iscSofrFet() {
        return cSofrFet;
    }

    public void setcSofrFet(boolean cSofrFet) {
        this.cSofrFet = cSofrFet;
    }

    public boolean iscDesprop() {
        return cDesprop;
    }

    public void setcDesprop(boolean cDesprop) {
        this.cDesprop = cDesprop;
    }

    public boolean iscHemorragia() {
        return cHemorragia;
    }

    public void setcHemorragia(boolean cHemorragia) {
        this.cHemorragia = cHemorragia;
    }

    public boolean iscParada() {
        return cParada;
    }

    public void setcParada(boolean cParada) {
        this.cParada = cParada;
    }

    public boolean iscEclampsia() {
        return cEclampsia;
    }

    public void setcEclampsia(boolean cEclampsia) {
        this.cEclampsia = cEclampsia;
    }

    public boolean iscPosMatur() {
        return cPosMatur;
    }

    public void setcPosMatur(boolean cPosMatur) {
        this.cPosMatur = cPosMatur;
    }

    public boolean iscPreEclamp() {
        return cPreEclamp;
    }

    public void setcPreEclamp(boolean cPreEclamp) {
        this.cPreEclamp = cPreEclamp;
    }

    public boolean iscDiabet() {
        return cDiabet;
    }

    public void setcDiabet(boolean cDiabet) {
        this.cDiabet = cDiabet;
    }

    public boolean iscTrompas() {
        return cTrompas;
    }

    public void setcTrompas(boolean cTrompas) {
        this.cTrompas = cTrompas;
    }

    public boolean iscRepeticao() {
        return cRepeticao;
    }

    public void setcRepeticao(boolean cRepeticao) {
        this.cRepeticao = cRepeticao;
    }

    public boolean iscMortFetal() {
        return cMortFetal;
    }

    public void setcMortFetal(boolean cMortFetal) {
        this.cMortFetal = cMortFetal;
    }

    public boolean iscEletiva() {
        return cEletiva;
    }

    public void setcEletiva(boolean cEletiva) {
        this.cEletiva = cEletiva;
    }

    public boolean iscOutra() {
        return cOutra;
    }

    public void setcOutra(boolean cOutra) {
        this.cOutra = cOutra;
    }

    public boolean iscDesconh() {
        return cDesconh;
    }

    public void setcDesconh(boolean cDesconh) {
        this.cDesconh = cDesconh;
    }

    public boolean isInducao() {
        return Inducao;
    }

    public void setInducao(boolean inducao) {
        Inducao = inducao;
    }

    public boolean isiPosMat() {
        return iPosMat;
    }

    public void setiPosMat(boolean iPosMat) {
        this.iPosMat = iPosMat;
    }

    public boolean isiPreEclamp() {
        return iPreEclamp;
    }

    public void setiPreEclamp(boolean iPreEclamp) {
        this.iPreEclamp = iPreEclamp;
    }

    public boolean isiBolsRot() {
        return iBolsRot;
    }

    public void setiBolsRot(boolean iBolsRot) {
        this.iBolsRot = iBolsRot;
    }

    public boolean isiIsolmunizacao() {
        return iIsolmunizacao;
    }

    public void setiIsolmunizacao(boolean iIsolmunizacao) {
        this.iIsolmunizacao = iIsolmunizacao;
    }

    public boolean isiMorteFetal() {
        return iMorteFetal;
    }

    public void setiMorteFetal(boolean iMorteFetal) {
        this.iMorteFetal = iMorteFetal;
    }

    public boolean isiEletiva() {
        return iEletiva;
    }

    public void setiEletiva(boolean iEletiva) {
        this.iEletiva = iEletiva;
    }

    public boolean isiOutra() {
        return iOutra;
    }

    public void setiOutra(boolean iOutra) {
        this.iOutra = iOutra;
    }

    public int getIdadeCriaEmDias() {
        return idadeCriaEmDias;
    }

    public void setIdadeCriaEmDias(int idadeCriaEmDias) {
        this.idadeCriaEmDias = idadeCriaEmDias;
    }

    public int getCondicaoSaude() {
        return condicaoSaude;
    }

    public void setCondicaoSaude(int condicaoSaude) {
        this.condicaoSaude = condicaoSaude;
    }

    public int getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(int alimentacao) {
        this.alimentacao = alimentacao;
    }

    public int getDiasHosp() {
        return diasHosp;
    }

    public void setDiasHosp(int diasHosp) {
        this.diasHosp = diasHosp;
    }

    public boolean isIcterisiaImp() {
        return icterisiaImp;
    }

    public void setIcterisiaImp(boolean icterisiaImp) {
        this.icterisiaImp = icterisiaImp;
    }

    public boolean isTrauma() {
        return trauma;
    }

    public void setTrauma(boolean trauma) {
        this.trauma = trauma;
    }

    public boolean isDiarreia() {
        return diarreia;
    }

    public void setDiarreia(boolean diarreia) {
        this.diarreia = diarreia;
    }

    public boolean isInfecResp() {
        return infecResp;
    }

    public void setInfecResp(boolean infecResp) {
        this.infecResp = infecResp;
    }

    public boolean isOutraInf() {
        return outraInf;
    }

    public void setOutraInf(boolean outraInf) {
        this.outraInf = outraInf;
    }
    public JSONObject toJSON(int idCriancaServer){
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
                Log.e("json","falha em criar bitmap pelo caminho da foto:"+getFoto());
            }
        }
        try {
            json.put("id_crianca",idCriancaServer);
            json.put("estado_civil",estCivil);
            json.put("renda_familiar",rendaFamiliar);
            json.put("consultas_pre_natais",getConsultasPrenatais());
            json.put("peso_do_bebe",getPesoBebe());
            json.put("altura_do_bebe",getAlturaBebe());
            json.put("gestacoes",getnGestacoes());
            json.put("partos_normais",getnPartNorm());
            json.put("cesarias",getnCesaria());
            json.put("abortos",getnAbort());
            json.put("tipo_parto",getTipoPart());
            json.put("c_sofrimento_fetal",cSofrFet);
            json.put("c_desproporcao",iscDesprop());
            json.put("c_hemorragia",iscHemorragia());
            json.put("c_parada",iscParada());
            json.put("c_eclampsia",iscEclampsia());
            json.put("c_pos_maturidade",iscPosMatur());
            json.put("c_pre_eclampsia",iscPreEclamp());
            json.put("c_diabete",iscDiabet());
            json.put("c_trompas",iscTrompas());
            json.put("c_repeticao",iscRepeticao());
            json.put("c_morte_fetal",iscMortFetal());
            json.put("c_eletiva",iscEletiva());
            json.put("c_outra",iscOutra());
            json.put("c_desconhecida",iscDesconh());
            json.put("i_pos_maturidade",isiPosMat());
            json.put("i_pre_eclampsia",isiPreEclamp());
            json.put("i_bolsa_rota",isiBolsRot());
            json.put("i_iso_imunizacao",isiIsolmunizacao());
            json.put("i_morte_fetal",isiMorteFetal());
            json.put("i_eletiva",isiEletiva());
            json.put("i_outra",isiOutra());
            json.put("i_desconhecida",isiDesconh());
            json.put("idade_em_dias",getIdadeCriaEmDias());
            json.put("condicao_saude",getCondicaoSaude());
            json.put("alimentacao",getAlimentacao());
            json.put("dias_no_hospital",getDiasHosp());
            json.put("icterisia_importante",isIcterisiaImp());
            json.put("trauma",isTrauma());
            json.put("diarreia",isDiarreia());
            json.put("infeccao_respiratoria",isInfecResp());
            json.put("outra_infeccao",isOutraInf());
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
        return "PrimeiroMes{" +
                "idCrianca=" + idCrianca +
                ", estCivil=" + estCivil +
                ", rendaFamiliar=" + rendaFamiliar +
                ", consultasPrenatais=" + consultasPrenatais +
                ", pesoBebe=" + pesoBebe +
                ", alturaBebe=" + alturaBebe +
                ", nGestacoes=" + nGestacoes +
                ", nPartNorm=" + nPartNorm +
                ", nCesaria=" + nCesaria +
                ", nAbort=" + nAbort +
                ", tipoPart=" + tipoPart +
                ", cesariana=" + cesariana +
                ", cSofrFet=" + cSofrFet +
                ", cDesprop=" + cDesprop +
                ", cHemorragia=" + cHemorragia +
                ", cParada=" + cParada +
                ", cEclampsia=" + cEclampsia +
                ", cPosMatur=" + cPosMatur +
                ", cPreEclamp=" + cPreEclamp +
                ", cDiabet=" + cDiabet +
                ", cTrompas=" + cTrompas +
                ", cRepeticao=" + cRepeticao +
                ", cMortFetal=" + cMortFetal +
                ", cEletiva=" + cEletiva +
                ", cOutra=" + cOutra +
                ", cDesconh=" + cDesconh +
                ", Inducao=" + Inducao +
                ", iPosMat=" + iPosMat +
                ", iPreEclamp=" + iPreEclamp +
                ", iBolsRot=" + iBolsRot +
                ", iIsolmunizacao=" + iIsolmunizacao +
                ", iMorteFetal=" + iMorteFetal +
                ", iEletiva=" + iEletiva +
                ", iOutra=" + iOutra +
                ", iDesconh=" + iDesconh +
                ", idadeCriaEmDias=" + idadeCriaEmDias +
                ", condicaoSaude=" + condicaoSaude +
                ", alimentacao=" + alimentacao +
                ", diasHosp=" + diasHosp +
                ", icterisiaImp=" + icterisiaImp +
                ", trauma=" + trauma +
                ", diarreia=" + diarreia +
                ", infecResp=" + infecResp +
                ", outraInf=" + outraInf +
                ", foto='" + foto + '\'' +
                '}';
    }
}
