package br.edu.ufcspa.detalcareofbabies.controller.terceiroMes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ufcspa.detalcareofbabies.R;

public class Tela6TerceiroMes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terceiro_mes_tela_6_dica_aliment);

        Button btOk = (Button) findViewById(R.id.btnDicas);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
