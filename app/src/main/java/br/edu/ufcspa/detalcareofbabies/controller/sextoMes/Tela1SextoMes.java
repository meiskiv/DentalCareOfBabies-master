package br.edu.ufcspa.detalcareofbabies.controller.sextoMes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

public class Tela1SextoMes extends AppCompatActivity {

    ///////
    private Button btNext;
    private RadioButton btSimAlimentoDoce;
    private RadioButton btNaoAlimentoDoce;
    private EditText edTextQualAlimentoDoce;
    private RadioButton btSimRefri;
    private RadioButton btNaoRefri;
    private RadioButton btSimDente;
    private RadioButton btNaoDente;
    private EditText edTextAltura6;
    private EditText edTextPeso6;
    ///////
    private String altura6;
    private String peso6;
    //////////
    private CheckBox btFrutas;
    private CheckBox btOvos;
    private CheckBox btArroz;
    private CheckBox btFeijão;
    private CheckBox btMingau;
    private CheckBox btLegumes;
    private CheckBox btMassas;
    private CheckBox btBatata;
    private CheckBox btCarnes;
    ///////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sexto_mes_tela_1);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        btSimAlimentoDoce = (RadioButton) findViewById(R.id.btSimAlimentoDoce);
        btNaoAlimentoDoce = (RadioButton) findViewById(R.id.btNaoAlimentoDoce);
        edTextQualAlimentoDoce = (EditText) findViewById(R.id.edTextQualAlimentoDoce);

        btSimRefri= (RadioButton) findViewById(R.id.btSimRefri);
        btNaoRefri= (RadioButton) findViewById(R.id.btNaoRefri);
        btSimDente= (RadioButton) findViewById(R.id.btSimDente);
        btNaoDente= (RadioButton) findViewById(R.id.btNaoDente);

        btFrutas = (CheckBox) findViewById(R.id.frutas);
        btOvos = (CheckBox) findViewById(R.id.ovos);
        btArroz = (CheckBox) findViewById(R.id.arroz);
        btFeijão = (CheckBox) findViewById(R.id.feijao);
        btMingau = (CheckBox) findViewById(R.id.mingau);
        btLegumes = (CheckBox) findViewById(R.id.legumes);
        btMassas = (CheckBox) findViewById(R.id.massas);
        btBatata = (CheckBox) findViewById(R.id.batata);
        btCarnes = (CheckBox) findViewById(R.id.carnes);

        btNext = (Button) findViewById(R.id.btNext);
        edTextPeso6 = (EditText) findViewById(R.id.edTextPeso);
        edTextAltura6 = (EditText) findViewById(R.id.edTextAltura);
        ////////////////////////////////////////////////////////////////////////////////////////////
        btSimAlimentoDoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(700);

                AnimationSet animation = new AnimationSet(false);
                animation.addAnimation(fadeIn);

                edTextQualAlimentoDoce.setAnimation(animation);
                edTextQualAlimentoDoce.setVisibility(View.VISIBLE);
            }
        });

        btNaoAlimentoDoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTextQualAlimentoDoce.setVisibility(View.GONE);
           //     edTextQualAlimentoDoce.setText("");
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

        assert btNext != null;
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (validateFields()) {
                        if (getFromComponents()) {
                            salvarDados();
                            startActivity(new Intent(getApplicationContext(), Tela2SextoMes.class));
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
        if((Double.parseDouble(edTextAltura6.getText().toString())<90.0 &&
                20.0<=Double.parseDouble(edTextAltura6.getText().toString()))&&
                (Double.parseDouble(edTextPeso6.getText().toString())<=30.0 &&
                        2.0<= Double.parseDouble(edTextPeso6.getText().toString()))){
            altura6 = edTextAltura6.getText().toString();
            peso6 = edTextPeso6.getText().toString();
            return true;
        }else {
            Toast.makeText(getApplication(), R.string.dialog_pEa, Toast.LENGTH_LONG).show();
            return false;
        }

    }


    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.sextoMes.setAltBebe(Double.valueOf(altura6));
        single.sextoMes.setPesoBebe(Double.valueOf(peso6));
        single.sextoMes.setAlimentoDoce(btSimAlimentoDoce.isChecked());
        if (btSimAlimentoDoce.isChecked()){
            single.sextoMes.setQualAlimentoDoce(edTextQualAlimentoDoce.getText().toString());
        }
        single.sextoMes.setRefri(btSimRefri.isChecked());
        single.sextoMes.setDente(btSimDente.isChecked());
        single.sextoMes.setFrutas(btFrutas.isChecked());
        single.sextoMes.setOvos(btOvos.isChecked());
        single.sextoMes.setArroz(btArroz.isChecked());
        single.sextoMes.setMingau(btMingau.isChecked());
        single.sextoMes.setLegumes(btLegumes.isChecked());
        single.sextoMes.setMassas(btMassas.isChecked());
        single.sextoMes.setBatata(btBatata.isChecked());
        single.sextoMes.setCarnes(btCarnes.isChecked());



    }



    ////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validateFields() {
        boolean textoQualPreenchido = true;
        Log.d("validateFields","method");
        if (btSimAlimentoDoce.isChecked()) {
            textoQualPreenchido = !edTextQualAlimentoDoce.getText().toString().isEmpty();

            Log.d("ValidateFields","btSimAlimentoDoce is checked textoqpreenchido: "+textoQualPreenchido);

            boolean i = (!edTextAltura6.getText().toString().isEmpty() &&
                    !edTextPeso6.getText().toString().isEmpty() &&
                    (btSimAlimentoDoce.isChecked() || btNaoAlimentoDoce.isChecked()) &&
                    textoQualPreenchido &&
                    (btSimRefri.isChecked() || btNaoRefri.isChecked()) &&
                    (btSimDente.isChecked() || btNaoDente.isChecked()));

            Log.d("validateF","return: "+i);

            return (i);

        }else {
            return (!edTextAltura6.getText().toString().isEmpty() &&
                        !edTextPeso6.getText().toString().isEmpty() &&
                        btNaoAlimentoDoce.isChecked() &&
                    (btSimRefri.isChecked() || btNaoRefri.isChecked()) &&
                    (btSimDente.isChecked() || btNaoDente.isChecked())
                );
            }
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
