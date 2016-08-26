package br.edu.ufcspa.detalcareofbabies.controller.primeiroMes;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by icarus on 11/12/15.
 */
public class Tela1PrimeiroMes extends Activity {

    ///COMPONENTES
    private Button btNext;
    private Spinner spinnerEstadoCivil;
    private Spinner spinnerRendaFamilia;
    private EditText edTextPreNatal;
    private EditText edTextPriMesPeso;
    private EditText edTextPriMesAlt;
    ///VARIAVEIS
    private int estadoCivil;
    private int rendaFamilia;
    private int preNatal;
    private double priMesPeso;
    private double priMesAltura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.primeiro_mes_tela_1);
        iniciaComponentes();



    }

    private void iniciaComponentes() {
        ////
        edTextPreNatal = (EditText) findViewById(R.id.editTextPreNatal);
        edTextPriMesPeso = (EditText) findViewById(R.id.editTextPrimeiroMesPeso);
        edTextPriMesAlt = (EditText) findViewById(R.id.editTextPrimeiroMesAltura);
        ////
        spinnerEstadoCivil = (Spinner) findViewById(R.id.spinnerEstadoC);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_primeiro_mes_est_civil, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstadoCivil.setAdapter(adapter);

        spinnerRendaFamilia = (Spinner) findViewById(R.id.spinnerRenda);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.array_primeiromes_renda, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRendaFamilia.setAdapter(adapter1);
        spinnerEstadoCivil.requestFocus();



        ////
        btNext = (Button) findViewById(R.id.primeiroMesTela01btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    if(getFromComponents()){
                    salvarDados();
                    startActivity(new Intent(getApplicationContext(), Tela2PrimeiroMes.class));
                    finish();
                    }
                } else {
                    Toast.makeText(getApplication(), R.string.toast_erro_valida, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean getFromComponents() {
        if(Double.parseDouble(edTextPriMesAlt.getText().toString())<=75.0 && Double.parseDouble(edTextPriMesAlt.getText().toString())>=15.0 &&
                Double.parseDouble(edTextPriMesPeso.getText().toString())>=0.5 && Double.parseDouble(edTextPriMesPeso.getText().toString())<=11){
            priMesAltura = Double.parseDouble(edTextPriMesAlt.getText().toString());
            priMesPeso = Double.parseDouble(edTextPriMesPeso.getText().toString());
            preNatal = Integer.parseInt(edTextPreNatal.getText().toString());
            estadoCivil = spinnerEstadoCivil.getSelectedItemPosition();//começa do 0
            rendaFamilia = spinnerRendaFamilia.getSelectedItemPosition(); //same

            return true;
        }else{
            Toast.makeText(getApplication(),R.string.dialog_pEa,Toast.LENGTH_LONG).show();

            return false;
        }


    }


    /////////////////////SALVAR NO SINGLETON////////////////////////////////////////////////////////
    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.priMes.setAlturaBebe(priMesAltura);
        single.priMes.setPesoBebe(priMesPeso);
        single.priMes.setConsultasPrenatais(preNatal);
        single.priMes.setEstCivil(estadoCivil);
        single.priMes.setRendaFamiliar(rendaFamilia);
    }


    private boolean validateFields() {
        return (!edTextPreNatal.getText().toString().isEmpty() &&
                !edTextPriMesAlt.getText().toString().isEmpty() &&
                !edTextPriMesPeso.getText().toString().isEmpty()&&
                !spinnerEstadoCivil.getSelectedItem().toString().equals("-Selecione-")&&
                !spinnerRendaFamilia.getSelectedItem().toString().equals("-Selecione-")
        );
    }


    ///////////////////////////////DIÁLOGO DO BOTAO VOLTAR/////////////////////////////////////////////////
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

    ///////////////////////////////////////GETTERS////////////////////////////////////////////


    public int getEstadoCivil() {
        return estadoCivil;
    }

    public int getRendaFamilia() {
        return rendaFamilia;
    }

    public int getPreNatal() {
        return preNatal;
    }

    public double getPriMesPeso() {
        return priMesPeso;
    }

    public double getPriMesAltura() {
        return priMesAltura;
    }
}
