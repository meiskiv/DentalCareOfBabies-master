package br.edu.ufcspa.detalcareofbabies.banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by icarus on 28/05/16.
 */
public class QuestDAO {
    public static void createTable(SQLiteDatabase db) {
        //SQLiteDatabase db = data.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS `questionario` (\n" +
                "  `id` INTEGER NOT NULL,\n" +
                "  `enviou` INTEGER NOT NULL\n" +
                ")");
    }
    public static void insert(SQLiteDatabase db, int flag){
        ContentValues cv=  new ContentValues();
        cv.put("enviou", flag);
        cv.put("id", 1);
        db.insert("questionario", null, cv);
    }
    public static void update(SQLiteDatabase db, int flag){
        ContentValues cv=  new ContentValues();
        cv.put("enviou",flag);
        db.update("questionario", cv, null, null);
    }
    public static int get(SQLiteDatabase db){
        String sql = "SELECT enviou FROM questionario";
        Cursor c = db.rawQuery(sql, null);
        int enviou= -2;
        if(c.moveToFirst()){
            enviou=c.getInt(0);
        }
        Log.d("BANCO", "get questionario Flag:" + enviou);
        return enviou;
    }
}
