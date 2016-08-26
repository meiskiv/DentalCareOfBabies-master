package br.edu.ufcspa.detalcareofbabies.model.entidade;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by icarus on 31/10/15.
 */
public class Usuario {
    private Integer id;
    private String nSus;
    private String nome;
    private String dataNasc;
    private int sexo;
    private int cor;
    private String cidade;
    private String telefone;
    private String email;
    private int formacao;
    private int tipoTrab;
    private boolean fumante;
    private boolean alcoolatra;
    private boolean drogas;
    private boolean hipert;
    private boolean diabet;
    private boolean avc;

    public Usuario(Integer id, String nSus, String nome, String dataNasc, int sexo, int cor, String cidade,
                   String telefone, String email, int formacao, int tipoTrab, boolean fumante, boolean alcoolatra,
                   boolean drogas, boolean hipert, boolean diabet, boolean avc) {
        this.id = id;
        this.nSus = nSus;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.cor = cor;
        this.cidade = cidade;
        this.telefone   = telefone;
        this.email = email;
        this.formacao = formacao;
        this.tipoTrab = tipoTrab;
        this.fumante = fumante;
        this.alcoolatra = alcoolatra;
        this.drogas = drogas;
        this.hipert = hipert;
        this.diabet = diabet;
        this.avc = avc;
    }
    public Usuario(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getnSus() {
        return nSus;
    }

    public void setnSus(String nSus) {
        this.nSus = nSus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFormacao() {
        return formacao;
    }

    public void setFormacao(int formacao) {
        this.formacao = formacao;
    }

    public int getTipoTrab() {
        return tipoTrab;
    }

    public void setTipoTrab(int tipoTrab) {
        this.tipoTrab = tipoTrab;
    }

    public boolean isFumante() {
        return fumante;
    }

    public void setFumante(boolean fumante) {
        this.fumante = fumante;
    }

    public boolean isAlcoolatra() {
        return alcoolatra;
    }

    public void setAlcoolatra(boolean alcoolatra) {
        this.alcoolatra = alcoolatra;
    }

    public boolean isDrogas() {
        return drogas;
    }

    public void setDrogas(boolean drogas) {
        this.drogas = drogas;
    }

    public boolean isHipert() {
        return hipert;
    }

    public void setHipert(boolean hipert) {
        this.hipert = hipert;
    }

    public boolean isDiabet() {
        return diabet;
    }

    public void setDiabet(boolean diabet) {
        this.diabet = diabet;
    }

    public boolean isAvc() {
        return avc;
    }

    public void setAvc(boolean avc) {
        this.avc = avc;
    }
    public JSONObject toJSON(){
        JSONObject c = new JSONObject();
        try {
            c.put("cartao_sus",getnSus());
            c.put("nome", getNome());
            c.put("nascimento",getDataNasc());
            c.put("sexo",getSexo());
            c.put("raca",getCor());
            c.put("cidade",getCidade());
            c.put("telefone1",getTelefone());
            c.put("telefone2","");
            c.put("telefone3","");
            c.put("email",getEmail());
            c.put("escolaridade",getFormacao());
            c.put("trabalho",getTipoTrab());
            c.put("cigarro",isFumante());
            c.put("alcool",isAlcoolatra());
            c.put("drogas",isDrogas());
            c.put("hipertensao",isHipert());
            c.put("diabetes",isDiabet());
            c.put("avc",isAvc());
        } catch (JSONException e) {
            Log.e("toJSON", "Erro em toJSON:" + e.getMessage());
        }
        return c;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nSus='" + nSus + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNasc='" + dataNasc + '\'' +
                ", sexo=" + sexo +
                ", cor=" + cor +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", formacao=" + formacao +
                ", tipoTrab=" + tipoTrab +
                ", fumante=" + fumante +
                ", alcoolatra=" + alcoolatra +
                ", drogas=" + drogas +
                ", hipert=" + hipert +
                ", diabet=" + diabet +
                ", avc=" + avc +
                '}';
    }
}

