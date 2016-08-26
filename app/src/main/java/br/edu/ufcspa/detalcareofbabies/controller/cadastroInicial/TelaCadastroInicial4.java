package br.edu.ufcspa.detalcareofbabies.controller.cadastroInicial;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.controller.MyCustomDialog;
import br.edu.ufcspa.detalcareofbabies.controller.TelaInicial;
import br.edu.ufcspa.detalcareofbabies.model.entidade.Crianca;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by edupooch on 25/01/16.
 */
public class TelaCadastroInicial4 extends Activity implements DatePickerDialog.OnDateSetListener {

    ////COMPONENTES/////////////////////////////////////////////////////////////////////////////////
    private ImageButton btF;
    private ImageButton btM;
    private Switch switchSexoBebe;
    private Button btNext;
    private EditText edTextNomeBebe;
    private EditText edTextDataBebe;
    private EditText edTextAlturaBebe;
    private EditText edTextPesoBebe;
    private ImageButton btCamera;
    private Crianca crianca;
    private ImageView btData;
    ////VARIAVEIS////////////////////////////////////////////////////////////////////////////////////
    private String nomeBebe;
    private String dataBebe;
    private String alturaBebe;
    private String pesoBebe;
    private int sexoBebe;
    private static final int TIRA_FOTO=101;
    private static boolean imagemSelecionada;
    private String fileName;
    private Context context;
    //caminho foto: /mnt/sdcard/Pictures/JPEG_2016-01-31_13_40_01_-994706720.jpg


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_inicial_4);
        context=this;
        //getActionBar().hide();
        iniciaComponentes();

    }

    private void iniciaComponentes() {

        edTextNomeBebe=(EditText)findViewById(R.id.editTextNomeBebe);
        edTextDataBebe=(EditText)findViewById(R.id.editTextDataBebe);
        edTextAlturaBebe=(EditText)findViewById(R.id.editTextAlturaBebe);
        edTextPesoBebe=(EditText)findViewById(R.id.editTextPesoBebe);
        //btCamera=(ImageButton)findViewById(R.id.BtFotoBebe);
        edTextDataBebe.setKeyListener(null);


        edTextDataBebe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(TelaCadastroInicial4.this
                        ,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        btNext=(Button)findViewById(R.id.cadastro04BtNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFromComponents();
                if (validateFields()) {
                    salvarDadosSingleton();
                    //salvarNoBanco();
                    //MyCustomDialog.showMessage(context, "Cadastro inicial", "Cadastro inicial concluido com sucesso!");
                    startActivity(new Intent(getApplicationContext(), TelaCadastroInicial5.class));
                    finish();

                }else{
                    Toast.makeText(getApplication(),R.string.toast_erro_valida, Toast.LENGTH_SHORT).show();
                }


            }
        });
        btData=(ImageView) findViewById(R.id.btDataBebe);
        btData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(TelaCadastroInicial4.this
                        ,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        /////////////////////////////SEXO//////////////////////////////////
        switchSexoBebe = (Switch) findViewById(R.id.switchSexoBebe);
        switchSexoBebe.setTextOff("F");
        switchSexoBebe.setTextOn("M");


        switchSexoBebe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!switchSexoBebe.isChecked()) {
                    btM.setBackgroundResource(R.drawable.icon_male_disabled);
                    btF.setBackgroundResource(R.drawable.icon_fem_selected);

                } else {
                    btM.setBackgroundResource(R.drawable.icon_male_selected);
                    btF.setBackgroundResource(R.drawable.icon_fem_disabled);
                }
            }
        });


        btF = (ImageButton) findViewById(R.id.btFemBebe);
        btF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSexoBebe.setChecked(false);
                btM.setBackgroundResource(R.drawable.icon_male_disabled);
                btF.setBackgroundResource(R.drawable.icon_fem_selected);
            }
        });

        btM = (ImageButton) findViewById(R.id.btMascBebe);
        btM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSexoBebe.setChecked(true);
                btM.setBackgroundResource(R.drawable.icon_male_selected);
                btF.setBackgroundResource(R.drawable.icon_fem_disabled);
            }
        });


        if (!switchSexoBebe.isChecked()) {
            btM.setBackgroundResource(R.drawable.icon_male_disabled);
            btF.setBackgroundResource(R.drawable.icon_fem_selected);

        } else {
            btM.setBackgroundResource(R.drawable.icon_male_selected);
            btF.setBackgroundResource(R.drawable.icon_fem_disabled);
        }

        ///////////////////////////////////////////////////////////////////

    }
//////////////////////////////RECUPERA DADOS/////////////////////////////////////

    private void getFromComponents(){
        nomeBebe = edTextNomeBebe.getText().toString();
        dataBebe = edTextDataBebe.getText().toString();
        pesoBebe = edTextPesoBebe.getText().toString();
        alturaBebe = edTextAlturaBebe.getText().toString();

        if (!switchSexoBebe.isChecked()) {
            sexoBebe = 1;
        } else {
            sexoBebe = 2;
        }

    }


    ////////////////////////////////SALVAR NO SINGLETON/////////////////////////////////////////////
    public void salvarDadosSingleton() {
        MySingleton single = MySingleton.getInstance();
        single.crianca.setNomeCria(nomeBebe);
        single.crianca.setAltCriaNasc(Double.valueOf(alturaBebe));
        single.crianca.setPesoCriaNasc(Double.valueOf(pesoBebe));
        single.crianca.setSexoCria(sexoBebe);
        single.crianca.setDataNascCria(dataBebe);
    }



////////////////////////////////////////////////////////////////////////////////////////////////////
    //------------------------------SALVAR NO BANCO -------------------------------//
    private void salvarNoBanco(){
        DataHelper data = new DataHelper(this);
        MySingleton singleton=MySingleton.getInstance();
        try {
            data.inserirCria(singleton.crianca);
            data.inserirResp(singleton.usuario);
            //-----------------------------ATUALIZA STATUS-------------------------------//
            data.updateStatusCadastro(1);
        }catch (Exception e){
            Log.d("banco","erro ao inserir:"+e.getMessage());
        }
    }

////////////////////////////////////VALIDA CAMPOS///////////////////////////////////////////////////

    private boolean validateFields(){ //verifica se os campos estao preenchidos
        return (!edTextNomeBebe.getText().toString().isEmpty()
                && !edTextDataBebe.getText().toString().isEmpty()
                && !edTextPesoBebe.getText().toString().isEmpty() &&
                !edTextAlturaBebe.getText().toString().isEmpty()

        );
    }
////////////////////////////////////////////CAMERA//////////////////////////////////////////////////

    @Override
    protected void onResume() {
        super.onResume();
//        crianca= new Crianca();
//        btCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                crianca.setNomeCria(edTextNomeBebe.getText().toString());
//                if (!imagemSelecionada ) {
//                    //Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    //String arquivo = Environment.getExternalStorageDirectory() + "/" + crianca.getNomeCria() + ".jpg";
//                    //crianca.setFotoPerfil(arquivo);
//                    //Log.d("camera:", "caminho foto:" + arquivo);
//                    //File file = new File(arquivo);
//                    //Uri outputFile = Uri.fromFile(file);
//                    //camera.putExtra(MediaStore.EXTRA_OUTPUT, outputFile);
//                    //startActivityForResult(camera, TIRA_FOTO);
//                    takePicture();
//                } else {
//                    if (crianca.getNomeCria().isEmpty())
//                        //Toast.makeText(getApplicationContext(), "Por favor, digite o nome da criança antes de tirar a foto", Toast.LENGTH_LONG).show();
//                    if (imagemSelecionada) {
//                        Log.d("camera", "imagem já foi selecionada");
//                        takePicture();
//                    }
//                }
//            }
//        });
    }
        /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TIRA_FOTO){
            if(resultCode==RESULT_CANCELED){
                Log.d("camera:", "intent result canceled");
            }
            if(resultCode==RESULT_OK){
                Log.d("camera:", "voltou foto  result ok");
                carregaImagem();
            }
            if(resultCode!= RESULT_OK){
                Log.d("camera:", "caminho foto nulo");
                //aluno.setFotoPerfil(null);
                Log.d("camera","result code:"+resultCode);
            }

        }
    }
    */


    public void recuperaFoto(){
        MySingleton single = MySingleton.getInstance();
        if(!single.crianca.getFoto().isEmpty()){
            fileName=single.crianca.getFoto();
            Log.d("camera","caminho foto no singleton:"+single.crianca.getFoto());
        }
    }



    private void carregaImagem(){
        if(fileName!= null && !fileName.isEmpty()){
            Bitmap bm = BitmapFactory.decodeFile(fileName);
            if(bm.getWidth()>bm.getHeight()){
                bm=Bitmap.createScaledBitmap(bm,120,100,false);
                Log.d("camera", "foto vertical");
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                bm = Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight(),matrix,true);
                FileOutputStream out = null;
                try{
                    out= new FileOutputStream(fileName);
                    bm.compress(Bitmap.CompressFormat.PNG,100,out);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(out!=null)
                            out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }else{
                Log.d("camera","foto horizontal");
                bm=Bitmap.createScaledBitmap(bm,120,100,false);
            }
            btCamera.setImageBitmap(bm);
            imagemSelecionada=true;
            Log.d("camera", "alterou imagem button para a foto");
        }else{
            Log.d("camera", "filename da imagem nao encontrado:"+fileName);
        }

    }

    /*
    private Bitmap decodeFile(File f) throws IOException {
        Bitmap b = null;

        //Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        FileInputStream fis = new FileInputStream(f);
        BitmapFactory.decodeStream(fis, null, o);
        fis.close();

        int scale = 1;
        if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
            scale = (int)Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
                    (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        try {
            fis = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        b = BitmapFactory.decodeStream(fis, null, o2);
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return b;
    }
    */



///////////////////////////////////DIÁLOGO DO BOTAO VOLTAR//////////////////////////////////////////
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

////////////////////////////////////////////////////////////////////////////////////////////////////
    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, TIRA_FOTO);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)        {
        if (requestCode == TIRA_FOTO && resultCode == RESULT_OK ) {

            //loadImageFromFile();
            carregaImagem();
        }
    }

    /* METODO PARA CARREGAR IMAGEM
    public void loadImageFromFile(){

        ImageButton view = (ImageButton)this.findViewById(R.id.BtFotoBebe);
        view.setVisibility(View.VISIBLE);


        int targetW = view.getWidth();
        int targetH = view.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileName, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor;
        try {
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        }catch (Exception e){
            Log.e("camera","erro ao pegar scaledFactor: "+e.getMessage());
            scaleFactor= 2;
        }

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;


        Bitmap bitmap = BitmapFactory.decodeFile(fileName, bmOptions);
        if(bitmap.getWidth() > bitmap.getHeight()){
            Log.d("camera","foto vertical");
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        }
        bitmap=Bitmap.createScaledBitmap(bitmap,100,100,false);
        view.setImageBitmap(bitmap);
        imagemSelecionada=true;
    }
    */

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp+"_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        fileName =  image.getAbsolutePath();
        Log.d("foto", "caminho foto:" + fileName);
        MySingleton single = MySingleton.getInstance();
        single.crianca.setFoto(fileName);
        return image;
    }


///////////////////////////////////////////////GETTERS//////////////////////////////////////////////
    public Crianca getCrianca() {
        return crianca;
    }

    public String getNomeBebe() {
        return nomeBebe;
    }

    public String getDataBebe() {
        return dataBebe;
    }

    public String getAlturaBebe() {
        return alturaBebe;
    }

    public String getPesoBebe() {
        return pesoBebe;
    }

    public int getSexoBebe() {
        return sexoBebe;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = ""+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        Log.d("datePicker",date);
        edTextDataBebe.setText(date);
    }

}







