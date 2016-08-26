package br.edu.ufcspa.detalcareofbabies.controller.umAno;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

public class Tela1UmAno extends AppCompatActivity {


    private RadioButton btSimAmamenta;
    private RadioButton btNaoAmamenta;
    private RadioButton btSimChupeta;
    private RadioButton btNaoChupeta;
    //
    private EditText edTextAliComplementar;
    private EditText edTextEscova;
    private EditText edTextAltura12;
    private EditText edTextPeso12;
    ///////
    private String altura12;
    private String peso6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.um_ano_tela1_activity);
        iniciaComponentes();
    }

    private void iniciaComponentes() {

        btSimAmamenta = (RadioButton) findViewById(R.id.btSimAmamenta);
        btNaoAmamenta = (RadioButton) findViewById(R.id.btNaoAmamenta);

        edTextAliComplementar = (EditText) findViewById(R.id.edTextAliComplementar);
        edTextEscova = (EditText) findViewById(R.id.edTextEscova);

        btSimChupeta = (RadioButton) findViewById(R.id.btSimChupeta);
        btNaoChupeta = (RadioButton) findViewById(R.id.btNaoChupeta);



        edTextPeso12 = (EditText) findViewById(R.id.edTextPeso);
        edTextAltura12 = (EditText) findViewById(R.id.edTextAltura);

        Button btNext = (Button) findViewById(R.id.btNext);
        assert btNext != null;
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    if (getFromComponents()) {
                        salvarDados();
                        //startActivity(new Intent(getApplicationContext(), Tela2UmAno.class));
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
        if ((Double.parseDouble(edTextAltura12.getText().toString()) < 90.0 &&
                20.0 <= Double.parseDouble(edTextAltura12.getText().toString())) &&
                (Double.parseDouble(edTextPeso12.getText().toString()) <= 30.0 &&
                        2.0 <= Double.parseDouble(edTextPeso12.getText().toString()))) {
            altura12 = edTextAltura12.getText().toString();
            peso6 = edTextPeso12.getText().toString();
            return true;
        } else {
            Toast.makeText(getApplication(), R.string.dialog_pEa, Toast.LENGTH_LONG).show();
            return false;
        }

    }


    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.umAno.setAlturaBebe(Double.valueOf(altura12));
        single.umAno.setPesoBebe(Double.valueOf(peso6));
        single.umAno.setAmamenta(btSimAmamenta.isChecked());
        single.umAno.setChupeta(btSimChupeta.isChecked());
        single.umAno.setEscovacaoDia(Integer.valueOf(edTextEscova.getText().toString()));
        single.umAno.setAlimentacaoComplementar(Integer.valueOf(edTextAliComplementar.getText().toString()));


    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validateFields() {
        Log.d("validateFields", "method");

        return (!edTextAltura12.getText().toString().isEmpty() &&
                !edTextPeso12.getText().toString().isEmpty() &&
                edTextEscova.getText().toString().isEmpty() &&
                edTextAliComplementar.getText().toString().isEmpty() &&
                (btSimAmamenta.isChecked() || btNaoAmamenta.isChecked()) &&
                (btSimChupeta.isChecked() || btNaoChupeta.isChecked())

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
