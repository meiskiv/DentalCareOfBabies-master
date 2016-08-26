package br.edu.ufcspa.detalcareofbabies.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.controller.dicas.Dicas;

/**
 * Created by Meiski on 05/02/2016.
 */
public class TelaInfo extends Activity {
    private Button btOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_informacoes);
        btOK =(Button)findViewById(R.id.btnOkTelaInfo);
        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
