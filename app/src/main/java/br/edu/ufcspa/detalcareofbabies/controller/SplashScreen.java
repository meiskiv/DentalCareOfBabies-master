package br.edu.ufcspa.detalcareofbabies.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.banco.QuestDAO;
import br.edu.ufcspa.detalcareofbabies.banco.TermoUsoDAO;
import br.edu.ufcspa.detalcareofbabies.client.SextoMesClient;
import br.edu.ufcspa.detalcareofbabies.client.UmAnoClient;
import br.edu.ufcspa.detalcareofbabies.model.coletas.SextoMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.UmAno;

/**
 * Created by icarus on 12/12/15.
 */
public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean term = false;
        boolean questCompleto=false;

        DataHelper dao = new DataHelper(getApplicationContext());

//        TerceiroMes t= new TerceiroMes(75,3.8,66.9,true,4,3,false,false,true,true,false,true,true,true,false,"http://minhaimagem");

/*        dao.updateStatusPrimMesSync(0);
        Log.d("banco", "tb fisrt " + dao.getPrimeiroMes(1).toString());
        Log.d("BANCO", "Tabelas:" + dao.getTestData());*/


       /* TESTES 6 MES e UM ANO
        SextoMes s = new SextoMes(2.5,1.24,true,false,false,true,false,false,true,true,false,true,true,true,"Mamão","blastoise.jpg");
        dao.inserirSextoMes(s);
        Log.d("splash", "sexto mês get:" + dao.getSextoMes().toString());

        UmAno u = new UmAno(3.14,2.13,false,5,4,true,"charizard.jpg");
        dao.inserirUmAno(u);
        Log.d("splash", "um ano get:" + dao.getUmAno().toString());*/

        /* TESTE COMUNICACAO JSON 6 MESES E UM ANO

        SextoMes s = new SextoMes(2.5,1.24,true,false,false,true,false,false,true,true,false,true,true,true,"Mamão","blastoise.jpg");
        SextoMesClient client = new SextoMesClient(this);
        client.postJson(s.toJSON(238));

        UmAno u = new UmAno(3.14,2.13,false,5,4,true,"charizard.jpg");
        UmAnoClient client2= new UmAnoClient(this);
        client2.postJson(u.toJSON(238));*/


        if(QuestDAO.get(dao.getWritableDatabase())==1){
             questCompleto=true;
        }


        if(TermoUsoDAO.get(dao.getWritableDatabase())>=0){
            term=true;
        }
        dao.close();
//        dao.updateStatusPrimMes(1);
//        dao.updateStatusPrimMesSync(1);
//        dao.inserirTerceiroMes(t);
//        Log.d("BANCO", "TErceiro mes do BD:" + dao.getTerceiroMes(1).toString());


//        TercMesClient client = new TercMesClient(this);
//
//        client.postJson(t.toJSON(75));
        final boolean finalTerm = term;

        final boolean finalQuestCompleto = questCompleto;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (finalTerm){
                    if(finalQuestCompleto){
                        startActivity(new Intent(getApplication(), TelaInicial.class));
                    }else{
                        startActivity(new Intent(getApplication(), TelaQuestInicio.class));
                    }

                }else{
                    startActivity(new Intent(getApplication(), TelaTermoUso.class));
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
