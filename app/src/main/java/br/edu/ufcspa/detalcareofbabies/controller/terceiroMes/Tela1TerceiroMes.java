package br.edu.ufcspa.detalcareofbabies.controller.terceiroMes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;
import info.hoang8f.android.segmented.SegmentedGroup;

public class Tela1TerceiroMes extends AppCompatActivity {

    ///////
    private RadioButton btSim;
    private RadioButton btNao;
    private RadioButton btFreq1;
    private RadioButton btFreq2;
    private RadioButton btFreq3;
    private LinearLayout layoutFrequencia;
    private EditText edTextAltura3;
    private EditText edTextPeso3;
    ///////
    private String altura3;
    private String peso3;
    private boolean isFreqChecked = false;
    ///////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terceiro_mes_tela_1);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        btSim = (RadioButton) findViewById(R.id.btSim);
        btNao = (RadioButton) findViewById(R.id.btNao);
        layoutFrequencia = (LinearLayout) findViewById(R.id.layoutFrequencia);
        btFreq1 = (RadioButton) findViewById(R.id.btChupeta1);
        btFreq2 = (RadioButton) findViewById(R.id.btChupeta2);
        btFreq3 = (RadioButton) findViewById(R.id.btChupeta3);
        Button btNext = (Button) findViewById(R.id.teceiroMesTela01btNext);
        edTextPeso3 = (EditText) findViewById(R.id.edTextPeso3);
        edTextAltura3 = (EditText) findViewById(R.id.edTextAltura3);
        ////////////////////////////////////////////////////////////////////////////////////////////
        btSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(700);

                AnimationSet animation = new AnimationSet(false);
                animation.addAnimation(fadeIn);

                layoutFrequencia.setAnimation(animation);
                layoutFrequencia.setVisibility(View.VISIBLE);
            }
        });

        btNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutFrequencia.setVisibility(View.INVISIBLE);
                btFreq1.setChecked(false);
                btFreq2.setChecked(false);
                btFreq3.setChecked(false);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    if(getFromComponents()) {
                        salvarDados();
                        startActivity(new Intent(getApplicationContext(), Tela2TerceiroMes.class));
                        finish();
                    }
                } else {
                    Toast.makeText(getApplication(), R.string.toast_erro_valida, Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean getFromComponents() {
        if((Double.parseDouble(edTextAltura3.getText().toString())<90.0 && 20.0<=Double.parseDouble(edTextAltura3.getText().toString()))&&
                (Double.parseDouble(edTextPeso3.getText().toString())<=25 && 2.0<= Double.parseDouble(edTextPeso3.getText().toString()))){
            altura3 = edTextAltura3.getText().toString();
            peso3 = edTextPeso3.getText().toString();
            return true;
        }else {
            Toast.makeText(getApplication(), R.string.dialog_pEa, Toast.LENGTH_LONG).show();
            return false;
        }

    }


    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.tercMes.setAltBebe(Double.valueOf(altura3));
        single.tercMes.setPesoBebe(Double.valueOf(peso3));
        single.tercMes.setUsaChupeta(btSim.isChecked());
        int freq=0;
        if (btSim.isChecked()){
            if(btFreq1.isChecked()){
                freq=1;
            }else if(btFreq2.isChecked()){
                freq=2;
            }else if(btFreq3.isChecked()){
                freq=3;
            }
        }
        single.tercMes.setFreqChup(freq);
    }





    ////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validateFields() {

        if (btSim.isChecked()) {
            isFreqChecked = btFreq1.isChecked() || btFreq2.isChecked() || btFreq3.isChecked();
        }

        if (btNao.isChecked()){
            isFreqChecked = true;
        }

        return (!edTextAltura3.getText().toString().isEmpty() &&
                !edTextPeso3.getText().toString().isEmpty() &&
                isFreqChecked &&
                btSim.isChecked() || btNao.isChecked()


        );
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
