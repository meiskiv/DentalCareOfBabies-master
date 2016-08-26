package br.edu.ufcspa.detalcareofbabies.model.entidade;

/**
 * Created by icarus on 27/01/16.
 */
public class StatusApp {
    private int id;
    private int cadastro;
    private int cadastroSync;
    private int priMes;
    private int priMesSync;
    private int tercMes;
    private int tercMesSync;
    private int sextMes;
    private int sextMesSync;
    private int umAno;
    private int umAnoSync;
    private int termoUso;

    public int getTermoUso() {
        return termoUso;
    }

    public void setTermoUso(int termoUso) {
        this.termoUso = termoUso;
    }

    public int getTercMesSync() {
        return tercMesSync;
    }

    public void setTercMesSync(int tercMesSync) {
        this.tercMesSync = tercMesSync;
    }

    public int getSextMesSync() {
        return sextMesSync;
    }

    public void setSextMesSync(int sextMesSync) {
        this.sextMesSync = sextMesSync;
    }

    public int getUmAnoSync() {
        return umAnoSync;
    }

    public void setUmAnoSync(int umAnoSync) {
        this.umAnoSync = umAnoSync;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCadastro() {
        return cadastro;
    }

    public void setCadastro(int cadastro) {
        this.cadastro = cadastro;
    }

    public int getCadastroSync() {
        return cadastroSync;
    }

    public void setCadastroSync(int cadastroSync) {
        this.cadastroSync = cadastroSync;
    }

    public int getPriMes() {
        return priMes;
    }

    public void setPriMes(int priMes) {
        this.priMes = priMes;
    }

    public int getPriMesSync() {
        return priMesSync;
    }

    public void setPriMesSync(int priMesSync) {
        this.priMesSync = priMesSync;
    }

    public int getTercMes() {
        return tercMes;
    }

    public void setTercMes(int tercMes) {
        this.tercMes = tercMes;
    }

    public int getSextMes() {
        return sextMes;
    }

    public void setSextMes(int sextMes) {
        this.sextMes = sextMes;
    }

    public int getUmAno() {
        return umAno;
    }

    public void setUmAno(int umAno) {
        this.umAno = umAno;
    }

    @Override
    public String toString() {
        return "StatusApp{" +
                "id=" + id +
                ", cadastro=" + cadastro +
                ", cadastroSync=" + cadastroSync +
                ", priMes=" + priMes +
                ", priMesSync=" + priMesSync +
                ", tercMes=" + tercMes +
                ", tercMesSync=" + tercMesSync +
                ", sextMes=" + sextMes +
                ", sextMesSync=" + sextMesSync +
                ", umAno=" + umAno +
                ", umAnoSync=" + umAnoSync +
                ", termoUso=" + termoUso +
                '}';
    }
}
