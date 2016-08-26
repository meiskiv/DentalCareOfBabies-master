package br.edu.ufcspa.detalcareofbabies.controller.primeiroMes;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.controller.MyCustomDialog;
import br.edu.ufcspa.detalcareofbabies.controller.TelaInicial;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by icarus on 11/12/15.
 */
public class Tela5PrimeiroMes extends Activity {
    ///

    private Button btNext;
    private Spinner spinnerCondicaoBebe;
    private Spinner spinnerAlimentacaoBebe;
    private Spinner spinnerAnormalidadeBebe;
    private EditText edTextDiasHospital;
    ////
    private int diasHospital;
    private int condicaoBebe;
    private int alimentacaoBebe;
    private int anormalidadeBebe;
    ////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primeiro_mes_tela_5);
        iniciaComponentes();

    }

    /////
    private void iniciaComponentes() {
        iniciaSpinners();
/////////////////////////////////////////////////////BOTAO//////////////////////////////////////////
        btNext = (Button) findViewById(R.id.primeiroMesTela05btNext);
        edTextDiasHospital= (EditText)findViewById(R.id.primeiro_mes_dias_hospital);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    getFromComponents();
                    salvarDados();
                    startActivity(new Intent(getApplicationContext(), Tela6PrimeiroMes.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_erro_valida,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private void iniciaSpinners() {
        ////////////////////////////////////////////////////////////////////////////////////////////
        spinnerCondicaoBebe = (Spinner) findViewById(R.id.spin_condicaoBebe);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_saudeBebe, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondicaoBebe.setAdapter(adapter);
        //
        spinnerAlimentacaoBebe = (Spinner) findViewById(R.id.spin_alimentacao);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.arrayAlimentacao, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlimentacaoBebe.setAdapter(adapter1);
        //
        spinnerAnormalidadeBebe = (Spinner) findViewById(R.id.spin_anormalidadeBebe);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.array_anormalidade, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnormalidadeBebe.setAdapter(adapter2);
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void getFromComponents() {
        diasHospital = Integer.parseInt(edTextDiasHospital.getText().toString());
        condicaoBebe = spinnerCondicaoBebe.getSelectedItemPosition();
        alimentacaoBebe = spinnerAlimentacaoBebe.getSelectedItemPosition();
        anormalidadeBebe = spinnerAnormalidadeBebe.getSelectedItemPosition();
    }
    /////////////////////SALVAR NO SINGLETON////////////////////////////////////////////////////////
    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.priMes.setDiasHosp(diasHospital);
        single.priMes.setAlimentacao(alimentacaoBebe);
        single.priMes.setCondicaoSaude(condicaoBebe);
        switch (anormalidadeBebe){
            case 1:
                single.priMes.setIcterisiaImp(true);
                break;
            case 2:
                single.priMes.setTrauma(true);
                break;
            case 3:
                single.priMes.setDiarreia(true);
                break;
            case 4:
                single.priMes.setInfecResp(true);
                break;
            case 5:
                single.priMes.setOutraInf(true);
                break;
        }
    }
    //
    private boolean validateFields() {
        return (!edTextDiasHospital.getText().toString().isEmpty() &&
                spinnerAlimentacaoBebe.getSelectedItemPosition() != 0 &&
                spinnerCondicaoBebe.getSelectedItemPosition() != 0);

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
////////////////////////////////////////////////////////////////////////////////////////////////////
}
