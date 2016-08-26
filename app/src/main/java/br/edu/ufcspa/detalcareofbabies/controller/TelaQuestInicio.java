package br.edu.ufcspa.detalcareofbabies.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.client.QuestClient;
import br.edu.ufcspa.detalcareofbabies.model.entidade.Quest;


/**
 * Created by icarus on 28/05/2016.
 */
public class TelaQuestInicio extends AppCompatActivity {
    private Button btCont;
    private Spinner spinnerQstInicial;
    private Spinner spinnerParentesco;
    private EditText edt;
    private String rSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_questionario_conheceu);

        spinnerQstInicial = (Spinner) findViewById(R.id.spinnerOndeConheceu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_onde_conheceu_app, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQstInicial.setAdapter(adapter);

        spinnerParentesco = (Spinner) findViewById(R.id.spinner_parentesco);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.array_parentesco, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParentesco.setAdapter(adapter2);



        edt=(EditText)findViewById(R.id.edtTxtOutro);



        spinnerQstInicial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==4) {
                    Log.d("activity","item outro selecionado");
                    edt.setVisibility(View.VISIBLE);
                    edt.requestFocus();
                }else{
                    if(edt.getVisibility()==View.VISIBLE)
                        edt.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btCont= (Button) findViewById(R.id.bt_continuar);
        btCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((getComponents())) {
                    if(spinnerQstInicial.getSelectedItemPosition()==4 && !edt.getText().toString().isEmpty()) {
                        rSpin = spinnerQstInicial.getSelectedItem().toString() +" "+ edt.getText().toString();
                        enviarDados();
                        Toast.makeText(getApplicationContext(),"Obrigado pela contribuição",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplication(), TelaInicial.class));
                        finish();
                    }else {
                        if (spinnerQstInicial.getSelectedItemPosition() != 4) {
                                rSpin=spinnerQstInicial.getSelectedItem().toString();
                                enviarDados();
                                Toast.makeText(getApplicationContext(),"Obrigado pela contribuição",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplication(), TelaInicial.class));
                                finish();
                        }else
                            Toast.makeText(getApplicationContext(),"Favor preencha o texto",Toast.LENGTH_SHORT).show();
                    }
            }else
                    Toast.makeText(getApplicationContext(),"Favor selecione uma opção válida",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public boolean getComponents(){
        if((spinnerQstInicial.getSelectedItemPosition()!=0) && spinnerParentesco.getSelectedItemPosition()!=0)//começa do 0{
                return true;
            else
                return false;
    }

    public void enviarDados(){
        Quest q = new Quest();
        q.setConheceu(rSpin);
        q.setParentesco(spinnerParentesco.getSelectedItem().toString());
        QuestClient qc = new QuestClient(getApplicationContext());
        qc.postJson(q.toJSON());
    }
}


