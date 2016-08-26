package br.edu.ufcspa.detalcareofbabies.controller.cadastroInicial;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by edupooch on 25/01/16.
 */
public class TelaCadastroInicial2 extends Activity {

    ////////FIELDS
    private Button btNext;
    private EditText edTextFone1;
    private EditText edTextFone2;
    private EditText edTextFone3;
    private EditText edTextEmail;
    //////VARIAVEIS
    private String fone1;
    private String fone2;
    private String fone3;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_inicial_2);
        iniciaComponentes();
    }


    private void iniciaComponentes() {
        edTextEmail = (EditText) findViewById(R.id.editTextEmail);
        edTextFone1 = (EditText) findViewById(R.id.editTextTelefone1);
        edTextFone2 = (EditText) findViewById(R.id.editTextTelefone2);
        edTextFone3 = (EditText) findViewById(R.id.editTextTelefone3);


        btNext = (Button) findViewById(R.id.cadastro02btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFromComponents();
                if (validateFields()) {
                    salvarDados();
                    startActivity(new Intent(getApplicationContext(), TelaCadastroInicial3.class));
                    finish();
                } else {
                    Toast.makeText(getApplication(), R.string.toast_erro_valida,
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    //////////////////////////RECUPERA DADOS////////////////////////////////////////////////////////

    private void getFromComponents() {
        fone1 = edTextFone1.getText().toString();
        fone2 = edTextFone1.getText().toString();
        fone3 = edTextFone1.getText().toString();
        email = edTextEmail.getText().toString();
    }

    /////////////////////SALVAR NO SINGLETON////////////////////////////////////////////////////////
    public void salvarDados() {
        MySingleton single = MySingleton.getInstance();
        single.usuario.setTelefone(fone1);
        single.usuario.setEmail(email);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validateFields() { //Verifica se todos os campos estão preenchidos
        return (!edTextFone1.getText().toString().isEmpty() &&
                !edTextEmail.getText().toString().isEmpty()
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

    ////////////////////////////////////GETTERS/////////////////////////////////////////////////////

    public String getFone1() {
        return fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public String getFone3() {
        return fone3;
    }

    public String getEmail() {
        return email;
    }
}
