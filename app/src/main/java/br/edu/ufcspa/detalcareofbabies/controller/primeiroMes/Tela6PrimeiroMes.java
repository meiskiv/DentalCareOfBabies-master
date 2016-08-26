package br.edu.ufcspa.detalcareofbabies.controller.primeiroMes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
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

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.controller.Camera;
import br.edu.ufcspa.detalcareofbabies.controller.MyCustomDialog;
import br.edu.ufcspa.detalcareofbabies.controller.dicas.Dicas;
import br.edu.ufcspa.detalcareofbabies.controller.terceiroMes.Tela4TerceiroMes;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by edupooch on 03/02/16.
 */
public class Tela6PrimeiroMes extends AppCompatActivity {
    private ImageButton btCamera;
    private Button btNext;
    private String tagCam = "Camera";
    private static final int TIRA_FOTO = 101;
    ;
    private String fileName = null;
    private static boolean imagemSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primeiro_mes_tela_6);
        iniciaComponentes();
    }

    private void iniciaComponentes() {

/////////////////////////////////////////////////////BOTAO NEXT//////////////////////////////////////////
        btNext = (Button) findViewById(R.id.primeiroMesTela06btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Log.d("botao", "clicou no bt");
                    salvarBanco();
                    startActivity(new Intent(getApplicationContext(), Tela7PrimeiroMes.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_erro_foto,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btCamera = (ImageButton) findViewById(R.id.botao_camera_primeiro_mes);
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(tagCam, "clicou no botao camera");

                //Escolha origem da imagem
//                @Override
//                public void BtnCameraPressed() {
//                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
//                    builder.setCancelable(false);
//                    builder.setMessage(getString(R.string.dialog_camera));
//                    builder.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    });
//                    builder.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                    android.support.v7.app.AlertDialog alert = builder.create();
//                    alert.show();
//                }


                fileName = Camera.tiraFoto(Tela6PrimeiroMes.this);
                MySingleton single = MySingleton.getInstance();
                single.priMes.setFoto(fileName);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private boolean validateFields() {

        return (fileName != null);
    }


    //////////////////////////////SALVAR BANCO//////////////////////////////////////////////////////
    public void salvarBanco() {
        DataHelper data = new DataHelper(getApplicationContext());
        MySingleton single = MySingleton.getInstance();
        int idCrianca = data.getCrianca(1).getId();
        Log.d("banco", "id da criança bd:" + idCrianca);
        single.priMes.setIdCrianca(idCrianca);
        Log.d("banco", "id da criança single:" + single.priMes.getIdCrianca());
        data.inserirPrimeiroMes(single.priMes);
        data.updateStatusPrimMes(1);
        data.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Camera.TIRA_FOTO && resultCode == RESULT_OK) {
            Camera.carregaImagem(fileName, btCamera);
        }
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


