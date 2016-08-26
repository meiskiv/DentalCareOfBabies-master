package br.edu.ufcspa.detalcareofbabies.controller.sextoMes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.edu.ufcspa.detalcareofbabies.R;

/**
 * Created by meiski on 24/08/2016.
 */
public class Tela6SextoMes extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_ficadica);
        Log.d("dica3 Sexto mes","iniciou");

        Button btOk = (Button)findViewById(R.id.btnDicas);
        btOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Tela7SextoMes.class));
                finish();
            }
        });

    }
}
