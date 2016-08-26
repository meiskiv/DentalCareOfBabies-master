package br.edu.ufcspa.detalcareofbabies.controller.cadastroInicial;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by edupooch on 25/01/16.
 */

public class TelaCadastroInicial3 extends Activity {

    /////////COMPONENTES/////////
    private Button btNext;
    private Spinner spinnerEscola;
    private Spinner spinnerTrab;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;


    //////VARIAVEIS//////////////
    private int escolaridade;
    private int trabalho;
    private boolean fuma;
    private boolean alcool;
    private boolean drogas;
    private boolean pressao;
    private boolean diabetes;
    private boolean avc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_inicial_3);
        iniciaComponentes();
    }

    private void iniciaComponentes() {

        checkBox1 = (CheckBox) findViewById(R.id.checkBoxFuma);
        checkBox2 = (CheckBox) findViewById(R.id.checkBoxAlcool);
        checkBox3 = (CheckBox) findViewById(R.id.checkBoxDrogas);
        checkBox4 = (CheckBox) findViewById(R.id.checkBoxPressao);
        checkBox5 = (CheckBox) findViewById(R.id.checkBoxDiabetes);
        checkBox6 = (CheckBox) findViewById(R.id.checkBoxAVC);

        btNext = (Button) findViewById(R.id.cadastro03btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    getFromComponents();
                    salvarDados();
                    startActivity(new Intent(getApplicationContext(), TelaCadastroInicial4.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_erro_valida,
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        ///////////////////////////SPINNERS/////////////////////////////////////////////////////////
        spinnerEscola = (Spinner) findViewById(R.id.spinner_escolaridade);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.escolaridade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEscola.setAdapter(adapter);
        ///////
        spinnerTrab = (Spinner) findViewById(R.id.spinner_trabalho);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.trabalho, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrab.setAdapter(adapter2);
        ////////////////////////////////////////////////////////////////////////////////////////////

    }

    private boolean validateFields() {
        return (spinnerTrab.getSelectedItemPosition() != 0 &&
                spinnerEscola.getSelectedItemPosition() != 0
        );
    }

    private void getFromComponents() {

        escolaridade = spinnerEscola.getSelectedItemPosition();
        trabalho = spinnerTrab.getSelectedItemPosition();
        fuma = checkBox1.isChecked();
        alcool = checkBox2.isChecked();
        drogas = checkBox3.isChecked();
        pressao = checkBox4.isChecked();
        diabetes = checkBox5.isChecked();
        avc = checkBox6.isChecked();
    }

    /////////////////////SALVAR NO SINGLETON////////////////////////////
    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        //single.usuario.setFormacao(Integer.getInteger(escolaridade));
        //single.usuario.setTipoTrab(Integer.getInteger(trabalho));
        single.usuario.setFumante(fuma);
        single.usuario.setAlcoolatra(alcool);
        single.usuario.setDrogas(drogas);
        single.usuario.setAvc(avc);
        single.usuario.setDiabet(diabetes);
        single.usuario.setHipert(pressao);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isAvc() {
        return avc;
    }

    public int getEscolaridade() {
        return escolaridade;
    }

    public int getTrabalho() {
        return trabalho;
    }

    public boolean isFuma() {
        return fuma;
    }

    public boolean isAlcool() {
        return alcool;
    }

    public boolean isDrogas() {
        return drogas;
    }

    public boolean isPressao() {
        return pressao;
    }

    public boolean isDiabetes() {
        return diabetes;
    }
}

