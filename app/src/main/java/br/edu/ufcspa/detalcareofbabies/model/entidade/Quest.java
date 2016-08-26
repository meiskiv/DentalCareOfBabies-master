package br.edu.ufcspa.detalcareofbabies.model.entidade;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by icarus on 28/05/16.
 */
public class Quest {
    private String conheceu;
    private String parentesco;


    public String getConheceu() {
        return conheceu;
    }

    public void setConheceu(String conheceu) {
        this.conheceu = conheceu;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }



    public JSONObject toJSON(){
        JSONObject j = new JSONObject();
        try {
            j.put("conheceu_app",conheceu);
            j.put("parentesco",parentesco);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }


    @Override
    public String toString() {
        return "Quest{" +
                "conheceu='" + conheceu + '\'' +
                ", parentesco='" + parentesco + '\'' +
                '}';
    }
}
