package br.edu.ufcspa.detalcareofbabies.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.banco.TermoUsoDAO;

/**
 * Created by icarus on 03/04/16.
 */
public class TelaTermoUso extends Activity{
    private Button btOk;
    private Button btDiscordo;
    DataHelper data;



    public Button getBtOk() {
        return btOk;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_termos);
        data = new DataHelper(getApplicationContext());
        btOk = (Button)findViewById(R.id.botaoConcordo);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaTermoUso.this, SplashScreen.class));
                TermoUsoDAO.update(data.getWritableDatabase(), 1);
                data.close();
                finish();
            }
        });

        btDiscordo = (Button)findViewById(R.id.botaoDiscordo);
        btDiscordo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermoUsoDAO.update(data.getWritableDatabase(), 0);
                startActivity(new Intent(TelaTermoUso.this, SplashScreen.class));
                data.close();
                finish();
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        data=null;
        btOk=null;
        btDiscordo=null;
    }
}
