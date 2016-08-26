package br.edu.ufcspa.detalcareofbabies.controller.dicas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.controller.TelaInicial;
import br.edu.ufcspa.detalcareofbabies.controller.cadastroInicial.TelaCadastroInicial;
import br.edu.ufcspa.detalcareofbabies.controller.primeiroMes.Tela1PrimeiroMes;
import br.edu.ufcspa.detalcareofbabies.controller.terceiroMes.Tela1TerceiroMes;
import br.edu.ufcspa.detalcareofbabies.model.entidade.StatusApp;


/**
 * Created by meiski on 27/03/16.
 */
public class Dicas extends Activity {

    private Button btnOk;
    static Random gerador;
    private StatusApp status;
    private DataHelper data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new DataHelper(getApplicationContext());
        status = data.getStatus(data.getWritableDatabase());
        gerador = new Random();
        if (gerador !=null) {
            if ((status.getCadastro() == 0 && status.getPriMes() == 0) ||
                    (status.getCadastro() == 1 && status.getPriMes() == 0) ||
                    (status.getCadastro()==1 && status.getPriMes()==1) &&
                            status.getTercMes()==0) {
                Log.d("tagGeradora"," switch 1");
                switchStatus(1);
            } else if (status.getCadastro()==1 && status.getPriMes()==1 &&
                    status.getTercMes()==1 && status.getSextMes()==0) {
                Log.d("tagGeradora"," switch 3");
                switchStatus(3);
            } else if(status.getTercMes()==1 && status.getSextMes()==1 && status.getUmAno()==0) {
                Log.d("tagGeradora", "switch 4");
                switchStatus(9);
            }
// else if(status.getUmAno()==1){
//                switchStatus(x);
//        }

        }

    }

    private void switchStatus(int n){
            switch (gerador.nextInt(n)) {
                case (0):
                    setContentView(R.layout.primeiro_mes_tela_7_dica_maezona);
                    Log.d("tagGeradora", "case 0");
                    break;
                case (1):
                    setContentView(R.layout.terceiro_mes_tela_5_dica_hig);
                    Log.d("tagGeradora", "case 1 dica higienizaçao");
                    break;
                case (2):
                    setContentView(R.layout.terceiro_mes_tela_6_dica_aliment);
                    break;
                case(3):
                    setContentView(R.layout.sexto_mes_dica1);
                    Log.d("tagGeradora", "case 3");
                    break;
                case(4):
                    setContentView(R.layout.sexto_mes_dica2);
                    Log.d("tagGeradora", "case 4");
                    break;
                case(5):
                    setContentView(R.layout.sexto_mes_dica3);
                    Log.d("tagGeradora", "case 5");
                    break;
                case(6):
                    setContentView(R.layout.dicas_dpermanentes);
                    break;
                case(7):
                    setContentView(R.layout.tela_ficadica);
                    break;
                case(8):
                    setContentView(R.layout.tela_ficadica2);
                    break;
                }
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        btnOk = (Button)findViewById(R.id.btnDicas);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),TelaInicial.class));
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        btnOk=null;
        //setContentView(null);
        //Runtime.getRuntime().gc();
    }
}