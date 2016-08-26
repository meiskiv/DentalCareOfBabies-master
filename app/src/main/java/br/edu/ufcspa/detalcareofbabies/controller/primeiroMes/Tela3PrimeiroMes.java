package br.edu.ufcspa.detalcareofbabies.controller.primeiroMes;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;


/**
 * Created by icarus on 11/12/15.
 */
public class Tela3PrimeiroMes extends Activity {
    /////
    private Button btNext;
    private Spinner spinnerCesarea;
    /////
    private int causaCesaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primeiro_mes_tela_3);
        iniciaComponentes();

    }


    private void iniciaComponentes() {
///////////////////////////////////SPINNER//////////////////////////////////////////////////////////
        spinnerCesarea = (Spinner) findViewById(R.id.spinner_cesarea);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_cesarea, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCesarea.setAdapter(adapter);
///////////////////////////////////BOTAO////////////////////////////////////////////////////////////
        btNext = (Button) findViewById(R.id.primeiroMesTela03btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerCesarea.getSelectedItemPosition() != 0) {
                    getFromComponents();
                    salvarDados();
                    startActivity(new Intent(getApplicationContext(), Tela5PrimeiroMes.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_erro_valida, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void getFromComponents() {
        causaCesaria = spinnerCesarea.getSelectedItemPosition(); //ver no array de itens as correspondências
    }

    /////////////////////SALVAR NO SINGLETON////////////////////////////////////////////////////////
    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
       switch (causaCesaria){
           case 1:
               single.priMes.setcSofrFet(true);
               break;
           case 2:
               single.priMes.setcDesprop(true);
               break;
           case 3:
               single.priMes.setcHemorragia(true);
               break;
           case 4:
               single.priMes.setcParada(true);
               break;
           case 5:
               single.priMes.setcPreEclamp(true);
               break;
           case 6:
               single.priMes.setcEclampsia(true);
               break;
           case 7:
               single.priMes.setcPosMatur(true);
               break;
           case 8:
               single.priMes.setcDiabet(true);
               break;
           case 9:
               single.priMes.setcTrompas(true);
               break;
           case 10:
               single.priMes.setcRepeticao(true);
               break;
           case 11:
               single.priMes.setcMortFetal(true);
               break;
           case 12:
               single.priMes.setcEletiva(true);
               break;
           case 13:
               single.priMes.setcOutra(true);
               break;
           case 14:
               single.priMes.setcDesconh(true);
               break;
       }

    }



    //////////////////////////////////DIÁLOGO DO BOTAO VOLTAR///////////////////////////////////////////
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getString(R.string.dialog_abandonar));
        builder.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}


