package br.edu.ufcspa.detalcareofbabies.controller.terceiroMes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

public class Tela2TerceiroMes extends AppCompatActivity {

    private Spinner spinnerAlimentacao3;
    private int alimentacao3;
    private RadioButton btSim;
    private RadioButton btNao;
    private boolean higiene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terceiro_mes_tela_2);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        ////////////////////////////////////////////////////////////////////////////////////////////
        spinnerAlimentacao3 = (Spinner) findViewById(R.id.spinner_alimentacao3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_alimentacao3, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlimentacao3.setAdapter(adapter);
        btSim = (RadioButton) findViewById(R.id.btSimHigiene);
        btNao = (RadioButton) findViewById(R.id.btNaoHigiene);


        ////////////////////////////////////////////////////////////////////////////////////////////

        Button btNext = (Button) findViewById(R.id.teceiroMesTela02btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    getFromComponents();
                    salvarDados();
                    startActivity(new Intent(getApplicationContext(), Tela3TerceiroMes.class));
                    finish();
                }

                 else {
                    Toast.makeText(getApplication(), R.string.toast_erro_valida, Toast.LENGTH_LONG).show();
                }


            }
        });
    }



    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.tercMes.setHigienizacaoBoca(higiene);
        single.tercMes.setAlimentacao(alimentacao3);
    }

    private void getFromComponents() {
        alimentacao3=spinnerAlimentacao3.getSelectedItemPosition();
        higiene= btSim.isChecked() ? btNao.isChecked() : false;
    }

    private boolean validateFields() {
        return ((spinnerAlimentacao3.getSelectedItemPosition() != 0) && (btSim.isChecked() || btNao.isChecked()));
    }

    ///////////////////////////////DIÁLOGO DO BOTAO VOLTAR//////////////////////////////////////////
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

