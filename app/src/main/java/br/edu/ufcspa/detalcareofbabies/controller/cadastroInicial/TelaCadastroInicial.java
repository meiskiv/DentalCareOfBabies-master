package br.edu.ufcspa.detalcareofbabies.controller.cadastroInicial;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by edupooch on 20/01/16.
 * <p/>
 * Esta classe comanda a primeira tela do cadastro inicial, "cadastro_inicial_1.xml",
 * e salva os inputs em variáveis.
 */

public class TelaCadastroInicial extends Activity implements DatePickerDialog.OnDateSetListener {
    //////////COMPONENTES////////////
    private Spinner spinnerCor;
    private ImageButton btF;
    private ImageButton btM;
    private Switch switchSexo;
    private Button btNext;
    private EditText edTextCartao;
    private EditText edTextNome;
    private EditText edTextData;
    private EditText edTextCidade;
    private ImageButton btData;
    //////////VARIAVEIS///////////////
    private String cartao;
    private String nome;
    private String data;
    private String cidade;
    private int sexo;
    private String cor;
    private Context context;
    /////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_inicial_1);
        iniciaComponentes();


    }

    @Override
    protected void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(TelaCadastroInicial.this);
    }

    private void iniciaComponentes() {

        /////////////////////////////////EDIT TEXTS//////////////////////////////////

        edTextCartao = (EditText) findViewById(R.id.editTextCartao);
        edTextNome = (EditText) findViewById(R.id.editTextNome);
        edTextData = (EditText) findViewById(R.id.editTextDataResponsavel);
        edTextCidade = (EditText) findViewById(R.id.editTextCidade);
        edTextData.setKeyListener(null);
        ///////////////////////////////BOTAO PROXIMO////////////////////////////////


        btNext = (Button) findViewById(R.id.cadastro01BtNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateFields()) {
                    getFromComponents();
                     salvarDados();
                    startActivity(new Intent(getApplicationContext(), TelaCadastroInicial2.class));
                    finish();
                } else {
                    Toast.makeText(getApplication(), R.string.toast_erro_valida,
                            Toast.LENGTH_LONG).show();

                }
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////

        edTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(TelaCadastroInicial.this
                        ,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        btData=(ImageButton) findViewById(R.id.btData_cad_inicial_1);
        btData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(TelaCadastroInicial.this
                        ,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        edTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(TelaCadastroInicial.this
                        ,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        ///////////////////////////SPINNER/////////////////////////////
        spinnerCor = (Spinner) findViewById(R.id.spinner_cor);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cor, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCor.setAdapter(adapter);

        /////////////////////////////////////////////////////////////////


        /////////////////////////////SEXO//////////////////////////////////

        switchSexo = (Switch) findViewById(R.id.switchSexo);
        switchSexo.setTextOff("F");
        switchSexo.setTextOn("M");
        switchSexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!switchSexo.isChecked()) {
                    btM.setBackgroundResource(R.drawable.icon_male_disabled);
                    btF.setBackgroundResource(R.drawable.icon_fem_selected);

                } else {
                    btM.setBackgroundResource(R.drawable.icon_male_selected);
                    btF.setBackgroundResource(R.drawable.icon_fem_disabled);
                }
            }
        });
        btF = (ImageButton) findViewById(R.id.imageButtonF);
        btF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSexo.setChecked(false);
                btM.setBackgroundResource(R.drawable.icon_male_disabled);
                btF.setBackgroundResource(R.drawable.icon_fem_selected);
            }
        });
        btM = (ImageButton) findViewById(R.id.imageButtonM);
        btM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSexo.setChecked(true);
                btM.setBackgroundResource(R.drawable.icon_male_selected);
                btF.setBackgroundResource(R.drawable.icon_fem_disabled);
            }
        });
        if (!switchSexo.isChecked()) {
            btM.setBackgroundResource(R.drawable.icon_male_disabled);
            btF.setBackgroundResource(R.drawable.icon_fem_selected);
        } else {
            btM.setBackgroundResource(R.drawable.icon_male_selected);
            btF.setBackgroundResource(R.drawable.icon_fem_disabled);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
    }


////////////////////////////////////////RECUPERA DADOS//////////////////////////////////////////////

    private void getFromComponents() {
        nome = edTextNome.getText().toString();
        data = edTextData.getText().toString();
        cartao = edTextCartao.getText().toString();
        cidade = edTextCidade.getText().toString();
        if (!switchSexo.isChecked()) {
            sexo = 1;
        } else {
            sexo = 2;
        }
        cor = spinnerCor.getSelectedItem().toString();
    }

/////////////////////////////////////////SALVAR NO SINGLETON////////////////////////////////////////
    public void salvarDados(){
        MySingleton single = MySingleton.getInstance();
        single.usuario.setNome(nome);
        single.usuario.setDataNasc(data);
        single.usuario.setnSus(cartao);
        single.usuario.setCidade(cidade);
        single.usuario.setSexo(sexo);
        //if(cor!=null) {
            //Log.d("spinner","Cor Selecionada:"+cor);
           // single.usuario.setCor(Integer.getInteger(cor));
        //}
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validateFields() { //verifica se os campos estao preenchidos
        return (!edTextCidade.getText().toString().isEmpty()
                && !edTextData.getText().toString().isEmpty() &&
                !edTextNome.getText().toString().isEmpty() &&
                spinnerCor.getSelectedItemPosition() != 0
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




///////////////////////////////////////////GETTERS//////////////////////////////////////////////////////////////
    public String getCor() {
        return cor;
    }

    public String getCartao() {
        return cartao;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getCidade() {
        return cidade;
    }

    public int getSexo() {
        return sexo;
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = ""+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        Log.d("datePicker",date);
        edTextData.setText(date);
    }
}









