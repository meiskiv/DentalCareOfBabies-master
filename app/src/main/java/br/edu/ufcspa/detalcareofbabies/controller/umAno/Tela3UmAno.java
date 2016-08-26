package br.edu.ufcspa.detalcareofbabies.controller.umAno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.controller.Camera;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

public class Tela3UmAno extends AppCompatActivity {
    private String fileName;
    private ImageButton btCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.um_ano_tela_3);

        btCamera = (ImageButton) findViewById(R.id.botao_camera);
        assert btCamera != null;
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = Camera.tiraFoto(Tela3UmAno.this);

                MySingleton single = MySingleton.getInstance();
                single.umAno.setFotoFrente(fileName);
            }
        });


        Button btNext = (Button) findViewById(R.id.terceiroMesTela04btNext);
        assert btNext != null;
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fileName != null) {
                    // salvarBanco();
                   // startActivity(new Intent(Tela3UmAno.this,Tela4UmAno.class));
                } else {
                    Toast.makeText(getApplication(), R.string.toast_erro_foto, Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Camera.TIRA_FOTO && resultCode == RESULT_OK) {
            Camera.carregaImagem(fileName,btCamera);
        }
    }

}
