package br.edu.ufcspa.detalcareofbabies.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;

/**
 * Created by edupooch on 08/08/2016.
 */
public class Camera {

    public static final int TIRA_FOTO = 101;

    public static String tiraFoto(Activity activity) {
        String fileName = null;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile;
            try {
                // Create an image file name
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File image = File.createTempFile(
                        imageFileName,  /* prefix */
                        ".jpg",         /* suffix */
                        storageDir      /* directory */
                );

                // Save a file: path for use with ACTION_VIEW intents
                fileName = image.getAbsolutePath();
                Log.d("foto", "caminho foto:" + fileName);

                photoFile = image;

            } catch (IOException ex) {
                // Error occurred while creating the File
                return null;
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                activity.startActivityForResult(takePictureIntent, TIRA_FOTO);

            }

        }
        return fileName;
    }


    public static void carregaImagem(String fileName,ImageButton btCamera) {
        if (fileName != null && !fileName.isEmpty()) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            Bitmap bm = BitmapFactory.decodeFile(fileName, options);
            if (bm.getWidth() > bm.getHeight()) {
                bm = Bitmap.createScaledBitmap(bm, 800, 600, false);
                Log.d("camera", "foto vertical");
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(fileName);
                    bm.compress(Bitmap.CompressFormat.PNG, 100, out);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null)
                            out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                Log.d("camera", "foto horizontal");
                bm = Bitmap.createScaledBitmap(bm, 800, 600, false);
            }
            btCamera.setImageBitmap(bm);
            Log.d("camera", "alterou imagem button para a foto");

        } else {
            Log.d("camera", "filename da imagem nao encontrado:" + fileName);
        }

    }
}
