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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by icarus on 11/12/15.
 */
public class Tela2PrimeiroMes extends Activity {

    ///
    private Button btNext;
    private Spinner spinnerTipoParto;
    private EditText edTextAbortos;
    private EditText edTextGestacoes;
    private EditText edTextPartoNormal;
    private EditText edTextCesareas;
    ///
    private int gestacoes;
    private int partoNormal;
    private int Cesareas;
    private int abortos;
    private int tipoParto;
    ///


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primeiro_mes_tela_2);
        iniciaComponentes();


    }

    private void iniciaComponentes() {

        edTextCesareas = (EditText) findViewById(R.id.editTextCesareas);
        edTextGestacoes = (EditText) findViewById(R.id.editTextGestacoes);
        edTextPartoNormal = (EditText) findViewById(R.id.editTextPartoNormal);
        edTextAbortos = (EditText) findViewById(R.id.editTextAbortos);

        ///////////////////////////////////////////////////////////////////////////////////////////
        spinnerTipoParto = (Spinner) findViewById(R.id.spinner_tipoParto);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_tipoParto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoParto.setAdapter(adapter);

        ////////////////////////////////////////////////////////////////////////////////////////////
        btNext = (Button) findViewById(R.id.primeiroMesTela02btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateFields()) {
                    getFromComponents();
                    salvarDados();
                    //Muda para activity 3 se o parto for tipo cesariana
                    if (tipoParto == 8) {
                        startActivity(new Intent(getApplicationContext(), Tela3PrimeiroMes.class));
                    } else if (tipoParto == 4 || tipoParto == 5) { //Muda para activity 4 se o parto for do tipo indução
                        startActivity(new Intent(getApplicationContext(), Tela4PrimeiroMes.class));
                    } else { //se não for nenhum dos outros vai pra 5
                        startActivity(new Intent(getApplicationContext(), Tela5PrimeiroMes.class));
                    }
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_erro_valida,
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void getFromComponents() {
        gestacoes = Integer.parseInt(edTextGestacoes.getText().toString());
        partoNormal = Integer.parseInt(edTextPartoNormal.getText().toString());
        Cesareas = Integer.parseInt(edTextCesareas.getText().toString());
        abortos = Integer.parseInt(edTextAbortos.getText().toString());
        tipoParto = spinnerTipoParto.getSelectedItemPosition();//começa no 0

    }
    /////////////////////SALVAR NO SINGLETON////////////////////////////////////////////////////////
    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.priMes.setnGestacoes(gestacoes);
        single.priMes.setnPartNorm(partoNormal);
        single.priMes.setnCesaria(Cesareas);
        single.priMes.setnAbort(abortos);
        single.priMes.setTipoPart(tipoParto);
    }

    private boolean validateFields() {
        return (!edTextCesareas.getText().toString().isEmpty() &&
                !edTextPartoNormal.getText().toString().isEmpty() &&
                !edTextGestacoes.getText().toString().isEmpty() &&
                !edTextAbortos.getText().toString().isEmpty() &&
                !spinnerTipoParto.getSelectedItem().toString().equals("-Selecione-")
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
///////////////////////////////////////GETTERS//////////////////////////////////////////////////////


    public int getGestacoes() {
        return gestacoes;
    }

    public int getPartoNormal() {
        return partoNormal;
    }

    public int getCesareas() {
        return Cesareas;
    }

    public int getAbortos() {
        return abortos;
    }

    public int getTipoParto() {
        return tipoParto;
    }
}
