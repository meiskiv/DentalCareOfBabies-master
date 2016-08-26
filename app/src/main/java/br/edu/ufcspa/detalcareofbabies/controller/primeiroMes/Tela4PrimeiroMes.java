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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by icarus on 11/12/15.
 */
public class Tela4PrimeiroMes extends Activity{
    private Button btNext;
    private Spinner spinnerInducao;
    private int causaInducao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primeiro_mes_tela_4);
        iniciaComponentes();
    }

    private void iniciaComponentes(){
///////////////////////////////////SPINNER//////////////////////////////////////////////////////////
        spinnerInducao = (Spinner) findViewById(R.id.spinner_inducao);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_inducao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInducao.setAdapter(adapter);
///////////////////////////////////BOTAO////////////////////////////////////////////////////////////
        btNext=(Button)findViewById(R.id.primeiroMesTela04btNext);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerInducao.getSelectedItemPosition() != 0) {
                    getFromComponents();
                    startActivity(new Intent(getApplicationContext(), Tela5PrimeiroMes.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_erro_valida, Toast.LENGTH_LONG).show();

                }
            }
        });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    public void getFromComponents(){
        causaInducao = spinnerInducao.getSelectedItemPosition();
    }



    /////////////////////SALVAR NO SINGLETON////////////////////////////////////////////////////////
    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        switch (causaInducao){
            case 1:
                single.priMes.setiPosMat(true);
                break;
            case 2:
                single.priMes.setiPreEclamp(true);
                break;
            case 3:
                single.priMes.setiBolsRot(true);
                break;
            case 4:
                single.priMes.setiIsolmunizacao(true);
                break;
            case 5:
                single.priMes.setiMorteFetal(true);
                break;
            case 6:
                single.priMes.setiEletiva(true);
                break;
            case 7:
                single.priMes.setiOutra(true);
                break;
            case 8:
                single.priMes.setiDesconh(true);
                break;
        }
    }

    ////////////////////////////DIÁLOGO DO BOTAO VOLTAR/////////////////////////////////////////////////

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
