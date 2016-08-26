package br.edu.ufcspa.detalcareofbabies.controller.sextoMes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.banco.StatusDAO;
import br.edu.ufcspa.detalcareofbabies.controller.Camera;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;
import br.edu.ufcspa.detalcareofbabies.model.entidade.StatusApp;

public class Tela2SextoMes extends AppCompatActivity {
    private ImageButton btCamera;
    private String tagCam = "Camera";
    private static final int TIRA_FOTO = 101;
    private String fileName = null;
    private static boolean imagemSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sexto_mes_tela_2);
        iniciaComponentes();
    }


    private void iniciaComponentes() {

        Button btNext = (Button) findViewById(R.id.btNext);
        assert btNext != null;
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileName != null) {
                    Log.d("botao", "clicou no bt");
                    salvarBanco();
                    showMessage("Fim de cadastro", "O cadastro do 6º mes foi concluido com sucesso");

                } else {
                    Toast.makeText(getApplication(), R.string.toast_erro_valida, Toast.LENGTH_LONG).show();
                }


            }
        });

        btCamera = (ImageButton) findViewById(R.id.botao_camera);
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(tagCam, "clicou no botao camera");

                fileName = Camera.tiraFoto(Tela2SextoMes.this);
                MySingleton single = MySingleton.getInstance();
                single.sextoMes.setFoto(fileName);
            }
        });
    }


    //////////////////////////////SALVAR BANCO//////////////////////////////////////////////////////
    public void salvarBanco() {
        DataHelper data = new DataHelper(getApplicationContext());
        MySingleton single = MySingleton.getInstance();
        ///int idCrianca = data.getCrianca(1).getId();
        //Log.d("banco", "id da criança bd:" + idCrianca);
        //single.tercMes.setIdCrianca(idCrianca);
        //Log.d("banco", "id da criança single:" + single.tercMes.getIdCrianca());

           data.inserirSextoMes(single.sextoMes);
           //data.updateStatusSextoMes(1);
        StatusDAO.updateStatusSextoMes(data.getWritableDatabase(),1);
        data.close();
    }

    ///////////////////////FOTO/////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Camera.TIRA_FOTO && resultCode == RESULT_OK) {
            Camera.carregaImagem(fileName, btCamera);
        }
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), Tela3SextoMes.class));
                finish();
            }
        });
        alert.show();

    }
    ///////////////////////////////DIÁLOGO DO BOTAO VOLTAR//////////////////////////////////////////
    @Override
    public void onBackPressed() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
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
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }
}
