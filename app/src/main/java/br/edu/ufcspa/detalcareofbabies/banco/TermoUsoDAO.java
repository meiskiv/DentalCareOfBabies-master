package br.edu.ufcspa.detalcareofbabies.banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.edu.ufcspa.detalcareofbabies.model.entidade.StatusApp;

/**
 * Created by icarus on 03/04/16.
 */
public class TermoUsoDAO {
    public static void createTable(SQLiteDatabase db) {
        //SQLiteDatabase db = data.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS `termo_uso` (\n" +
                "  `id` INTEGER NOT NULL,\n" +
                "  `aceito` INTEGER NOT NULL\n" +
                ")");
    }
    public static void insert(SQLiteDatabase db, int flagTermoUso){
        ContentValues cv=  new ContentValues();
        cv.put("aceito", flagTermoUso);
        cv.put("id", 1);
        db.insert("termo_uso", null, cv);
    }
    public static void update(SQLiteDatabase db, int flagTermoUso){
        ContentValues cv=  new ContentValues();
        cv.put("aceito",flagTermoUso);
        db.update("termo_uso", cv, null, null);
    }
    public static int get(SQLiteDatabase db){
        String sql = "SELECT aceito FROM termo_uso";
        Cursor c = db.rawQuery(sql, null);
        int aceito= -2;
        if(c.moveToFirst()){
            aceito=c.getInt(0);
        }
        Log.d("BANCO","get termoUso FLag:"+aceito);
        return aceito;
    }

}
