package br.edu.ufcspa.detalcareofbabies.controller.terceiroMes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

public class Tela3TerceiroMes extends AppCompatActivity {
    private CheckBox chRefri;
    private CheckBox chCha;
    private CheckBox chAchocolatado;
    private CheckBox chCafe;
    private CheckBox chCaldo;
    private CheckBox chLeiteMat;
    private CheckBox chLeitePo;
    private CheckBox chSuco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terceiro_mes_tela_3);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        chSuco= (CheckBox)findViewById(R.id.checkBoxSuco);
        chLeitePo= (CheckBox)findViewById(R.id.checkBoxLeitePo);
        chLeiteMat=(CheckBox) findViewById(R.id.checkBoxLeiteMat);
        chCaldo=(CheckBox) findViewById(R.id.checkBoxCaldo);
        chCafe=(CheckBox) findViewById(R.id.checkBoxCafe);
        chAchocolatado=(CheckBox) findViewById(R.id.checkBoxAchocolatado);
        chCha=(CheckBox) findViewById(R.id.checkBoxCha);
        chRefri=(CheckBox) findViewById(R.id.checkBoxRefri);



        Button btNext = (Button) findViewById(R.id.teceiroMesTela03btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    salvarDados();
                    startActivity(new Intent(getApplicationContext(), Tela4TerceiroMes.class));
                    finish();

                } else {
                    Toast.makeText(getApplication(), R.string.toast_erro_valida, Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void getFromComponents() {

    }
    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.tercMes.setAchocolatado(chAchocolatado.isChecked());
        single.tercMes.setCha(chCha.isChecked());
        single.tercMes.setRefri(chRefri.isChecked());
        single.tercMes.setCafe(chCafe.isChecked());
        single.tercMes.setCaldoAlim(chCaldo.isChecked());
        single.tercMes.setLeiteMaterno(chLeiteMat.isChecked());
        single.tercMes.setLeiteEmPo(chLeitePo.isChecked());
        single.tercMes.setSuco(chSuco.isChecked());
    }


    private boolean validateFields() {
        return true;
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

